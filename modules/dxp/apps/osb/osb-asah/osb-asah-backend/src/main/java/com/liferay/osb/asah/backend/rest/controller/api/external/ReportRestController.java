/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.backend.rest.controller.api.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.backend.dog.AccountDog;
import com.liferay.osb.asah.backend.dog.ActivityDog;
import com.liferay.osb.asah.backend.dog.DataExportTaskDog;
import com.liferay.osb.asah.backend.dog.GeolocationDog;
import com.liferay.osb.asah.backend.dog.IndividualDog;
import com.liferay.osb.asah.backend.dog.InterestDog;
import com.liferay.osb.asah.backend.dog.MetricDog;
import com.liferay.osb.asah.backend.dog.MetricTypeDog;
import com.liferay.osb.asah.backend.dog.SegmentDog;
import com.liferay.osb.asah.backend.dog.TechnologyDog;
import com.liferay.osb.asah.backend.dog.UserDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.Account;
import com.liferay.osb.asah.backend.model.Activity;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.Individual;
import com.liferay.osb.asah.backend.model.Interest;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.PageMetric;
import com.liferay.osb.asah.backend.model.PageMetricType;
import com.liferay.osb.asah.backend.model.Segment;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.model.Trend;
import com.liferay.osb.asah.backend.rest.controller.BaseRestController;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.DataExportTask;
import com.liferay.osb.asah.common.model.DataExportTaskStatus;
import com.liferay.osb.asah.common.model.DataExportTaskType;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;

import java.io.File;
import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcellus Tavares
 */
@RequestMapping(produces = "application/json", value = "/api/reports")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.api.external.ReportRestController"
)
public class ReportRestController extends BaseRestController {

	@GetMapping("/accounts/{accountId}")
	public Resource<Account> getAccountResource(
		@PathVariable String accountId) {

		return _toAccountResource(_accountDog.getAccount(accountId));
	}

	@GetMapping("/accounts")
	public ResultBagResource<Account> getAccountResultBagResource(
		@RequestParam(defaultValue = "0") Integer page) {

		ResultBag<Account> accountResultBag = _accountDog.getAccountResultBag(
			_PAGE_SIZE, page * _PAGE_SIZE);

		return _toResultBagResource(
			_getAccountResultBagResource(page + 1), page,
			_getAccountResultBagResource(page - 1), accountResultBag,
			this::_toAccountResource);
	}

	@GetMapping("/export/{type}")
	public ResponseEntity getDataExportTask(@PathVariable String type) {
		DataExportTask dataExportTask =
			_dataExportTaskDog.fetchLastDataExportTask(
				StringUtils.upperCase(type));

		if (dataExportTask == null) {
			return _addDataExportTask(type);
		}

		DataExportTaskStatus dataExportTaskStatus =
			dataExportTask.getDataExportTaskStatus();

		if (dataExportTaskStatus == DataExportTaskStatus.ERROR) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					String.format(
						"Data export task with ID %s has failed. Submitting " +
							"a new export task.",
						dataExportTask.getId()));
			}

			return _addDataExportTask(type);
		}

		if ((dataExportTaskStatus == DataExportTaskStatus.PENDING) ||
			(dataExportTaskStatus == DataExportTaskStatus.RUNNING)) {

			return _buildAcceptedResponseEntity(dataExportTask);
		}

		long elapsedCompletedTime = DateUtil.getDeltaMilliseconds(
			dataExportTask.getCompletedDate(), new Date());

		if (elapsedCompletedTime > (30 * DateUtil.MINUTE)) {
			return _addDataExportTask(type);
		}

		return _buildAcceptedResponseEntity(dataExportTask);
	}

	@GetMapping("/export/{type}/file")
	public ResponseEntity getDataExportTaskFile(@PathVariable String type) {
		DataExportTask dataExportTask =
			_dataExportTaskDog.fetchLastDataExportTask(
				StringUtils.upperCase(type));

		if (dataExportTask == null) {
			return _buildBadRequestResponseEntity();
		}

		DataExportTaskStatus dataExportTaskStatus =
			dataExportTask.getDataExportTaskStatus();

		if (dataExportTaskStatus != DataExportTaskStatus.COMPLETED) {
			return _buildBadRequestResponseEntity();
		}

		ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok();

		bodyBuilder.contentType(MediaType.APPLICATION_OCTET_STREAM);

		DataExportTaskType dataExportTaskType =
			dataExportTask.getDataExportTaskType();

		String fileName = String.format(
			"%s-data-%s.json",
			StringUtils.lowerCase(dataExportTaskType.toString()),
			DateUtil.toUTCString(dataExportTask.getCompletedDate()));

		bodyBuilder.header(
			HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + fileName + "\"");

		File file = _dataExportTaskDog.getDataExportTaskFile(
			dataExportTask.getId());

		return bodyBuilder.body(new FileSystemResource(file.getAbsolutePath()));
	}

	@GetMapping("/individuals/{individualId}/activities")
	public ResultBagResource<Activity> getIndividualActivityResultBagResource(
		@PathVariable String individualId,
		@RequestParam(defaultValue = "0") Integer page) {

		ResultBag<Activity> activityResultBag =
			_activityDog.getActivityResultBag(
				individualId, _PAGE_SIZE, page * _PAGE_SIZE);

		return _toResultBagResource(
			_getIndividualActivityResultBagResource(individualId, page + 1),
			page,
			_getIndividualActivityResultBagResource(individualId, page - 1),
			activityResultBag,
			activity -> _toChildResource(individualId, activity));
	}

	@GetMapping("/individuals/{individualId}/interests")
	public ResultBagResource<Interest> getIndividualInterestResultBagResource(
		@PathVariable String individualId,
		@RequestParam(defaultValue = "0") Integer page) {

		ResultBag<Interest> interestResultBag =
			_interestDog.getInterestResultBag(
				individualId, "individual", _PAGE_SIZE, page * _PAGE_SIZE);

		return _toResultBagResource(
			_getIndividualInterestResultBagResource(individualId, page + 1),
			page,
			_getIndividualInterestResultBagResource(individualId, page - 1),
			interestResultBag,
			interest -> _toChildResource(individualId, interest));
	}

	@GetMapping("/individuals/{individualId}")
	public Resource<Individual> getIndividualResource(
		@PathVariable String individualId) {

		return _toIndividualResource(
			_individualDog.getIndividual(individualId));
	}

	@GetMapping("/individuals")
	public ResultBagResource<Individual> getIndividualResultBagResource(
		@RequestParam(defaultValue = "0") Integer page,
		@RequestParam(defaultValue = "") String query) {

		ResultBag<Individual> individualResultBag =
			_individualDog.getIndividualResultBag(
				null, query, _PAGE_SIZE, page * _PAGE_SIZE);

		return _toResultBagResource(
			_getIndividualResultBagResource(page + 1, query), page,
			_getIndividualResultBagResource(page - 1, query),
			individualResultBag, this::_toIndividualResource);
	}

	@GetMapping("/individuals/{individualId}/segments")
	public ResultBagResource<Segment> getIndividualSegmentResultBagResource(
		@PathVariable String individualId) {

		Individual individual = _individualDog.getIndividual(individualId);

		List<String> individualSegmentIds =
			individual.getIndividualSegmentIds();

		List<Segment> segments = _segmentDog.getSegments(
			individualSegmentIds.toArray(new String[0]));

		return _toResultBagResource(
			null, 0, null, new ResultBag<>(segments, segments.size()),
			segment -> _toChildResource(individualId, segment));
	}

	@GetMapping
	public ResourceSupport getLinksResourceSupport() {
		return new ResourceSupport() {
			{
				add(
					Arrays.asList(
						ControllerLinkBuilder.linkTo(
							_getAccountResultBagResource(null)
						).withRel(
							"accounts"
						),
						ControllerLinkBuilder.linkTo(
							_getIndividualResultBagResource(null, null)
						).withRel(
							"individuals"
						),
						ControllerLinkBuilder.linkTo(
							_getPageReportResultBagResource(
								null, null, null, null, null)
						).withRel(
							"pages"
						),
						ControllerLinkBuilder.linkTo(
							_getSegmentResultBagResource(null)
						).withRel(
							"segments"
						)));
			}
		};
	}

	@GetMapping("/pages/{pageURL}")
	public Resource<PageReport> getPageReportResource(
		@RequestParam(defaultValue = "", name = "expand") Set<String> expands,
		@RequestParam(defaultValue = "") String pageTitle,
		@PathVariable String pageURL,
		@RequestParam(defaultValue = "30") int rangeKey) {

		SearchQueryContext searchQueryContext = new SearchQueryContext() {
			{
				setAssetId(_decodeURL(pageURL));
				setAssetType(AssetType.PAGE);
				setTimeRange(TimeRange.of(rangeKey));

				if (StringUtils.isNotEmpty(pageTitle)) {
					setTitle(pageTitle);
				}
			}
		};

		return _toPageReportResource(
			_toPageReport(
				expands,
				_metricDog.getAssetMetric(
					searchQueryContext, _getPageMetricTypeNames()),
				searchQueryContext),
			rangeKey);
	}

	@GetMapping("/pages")
	public ResultBagResource<PageReport> getPageReportResultBagResource(
		@RequestParam(defaultValue = "0") Integer page,
		@RequestParam(defaultValue = "") String keywords,
		@RequestParam(defaultValue = "30") Integer rangeKey,
		@RequestParam(defaultValue = "viewsMetric") String sortMetric,
		@RequestParam(defaultValue = "desc") String sortOrder) {

		ResultBag<PageMetric> pageMetricResultBag = new ResultBag<>();

		SearchQueryContext searchQueryContext = new SearchQueryContext() {
			{
				setAssetType(AssetType.PAGE);
				setKeywords(keywords);
				setTimeRange(TimeRange.of(rangeKey));
			}
		};

		int assetMetricsCount = _metricDog.getAssetMetricsCount(
			searchQueryContext);

		List<PageMetric> pageMetrics = _metricDog.getAssetMetrics(
			assetMetricsCount, searchQueryContext, _getPageMetricTypeNames(),
			_PAGE_SIZE, _createSort(sortMetric, sortOrder), page * _PAGE_SIZE);

		pageMetricResultBag.setResults(pageMetrics);

		pageMetricResultBag.setTotal(assetMetricsCount);

		return _toResultBagResource(
			_getPageReportResultBagResource(
				page + 1, keywords, rangeKey, sortMetric, sortOrder),
			page,
			_getPageReportResultBagResource(
				page - 1, keywords, rangeKey, sortMetric, sortOrder),
			pageMetricResultBag,
			pageMetric -> _toPageReportResource(
				new PageReport(pageMetric), rangeKey));
	}

	@GetMapping("/segments/{individualSegmentId}/individuals")
	public ResultBagResource<Individual> getSegmentIndividualResultBagResource(
		@PathVariable String individualSegmentId,
		@RequestParam(defaultValue = "0") Integer page,
		@RequestParam(defaultValue = "") String query) {

		ResultBag<Individual> individualResultBag =
			_individualDog.getIndividualResultBag(
				individualSegmentId, query, _PAGE_SIZE, page * _PAGE_SIZE);

		return _toResultBagResource(
			_getSegmentIndividualResultBagResource(
				individualSegmentId, page + 1, query),
			page,
			_getSegmentIndividualResultBagResource(
				individualSegmentId, page - 1, query),
			individualResultBag, this::_toIndividualResource);
	}

	@GetMapping("/segments/{segmentId}")
	public Resource<Segment> getSegmentResource(
		@PathVariable String segmentId) {

		return _toSegmentResource(_segmentDog.getSegment(segmentId));
	}

	@GetMapping("/segments")
	public ResultBagResource<Segment> getSegmentResultBagResource(
		@RequestParam(defaultValue = "0") Integer page) {

		ResultBag<Segment> segmentResultBag = _segmentDog.getSegmentResultBag(
			_PAGE_SIZE, page * _PAGE_SIZE);

		return _toResultBagResource(
			_getSegmentResultBagResource(page + 1), page,
			_getSegmentResultBagResource(page - 1), segmentResultBag,
			this::_toSegmentResource);
	}

	private ResponseEntity _addDataExportTask(String type) {
		return _buildAcceptedResponseEntity(
			_dataExportTaskDog.addDataExportTask(StringUtils.upperCase(type)));
	}

	private <T> ResponseEntity<T> _buildAcceptedResponseEntity(T body) {
		ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(
			HttpStatus.SC_ACCEPTED);

		return bodyBuilder.body(body);
	}

	private ResponseEntity _buildBadRequestResponseEntity() {
		ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(
			HttpStatus.SC_BAD_REQUEST);

		return bodyBuilder.build();
	}

	private Sort _createSort(String metricTypeString, String sortOrderString) {
		MetricType metricType = _metricTypeDog.getMetricType(
			AssetType.PAGE, metricTypeString);

		return new Sort(metricType.getAggregationName(), sortOrderString);
	}

	private String _decodeURL(String url) {
		if (url == null) {
			return null;
		}

		try {
			Base64.Decoder urlDecoder = Base64.getUrlDecoder();

			return new String(
				urlDecoder.decode(url), StandardCharsets.UTF_8.toString());
		}
		catch (UnsupportedEncodingException uee) {
			throw new IllegalStateException(uee);
		}
	}

	private String _encodeURL(String url) {
		if (url == null) {
			return null;
		}

		try {
			Base64.Encoder urlEncoder = Base64.getUrlEncoder();

			return urlEncoder.encodeToString(
				url.getBytes(StandardCharsets.UTF_8.toString()));
		}
		catch (UnsupportedEncodingException uee) {
			throw new IllegalStateException(uee);
		}
	}

	private void _expandMetricReport(
		List<Metric> metrics, Consumer<MetricReport> metricReportConsumer) {

		Stream<Metric> stream = metrics.stream();

		stream.map(
			metric -> new MetricReport(
				metric.getValueKey(), metric.getPreviousValue(),
				metric.getValue())
		).forEach(
			metricReportConsumer
		);
	}

	private void _expandMetricReportAudience(
		MetricReport metricReport, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		AudienceReport audienceReport = new AudienceReport();

		audienceReport._anonymousUsersCount = _userDog.getAnonymousUsersCount(
			metricType, searchQueryContext);
		audienceReport._knownUsersCount = _userDog.getKnownUsersCount(
			metricType, searchQueryContext);
		audienceReport._nonsegmentedKnownUsersCount =
			_userDog.getNonsegmentedKnownUsersCount(
				metricType, searchQueryContext);
		audienceReport._segmentedKnownUsersCount =
			_userDog.getSegmentedKnownUsersCount(
				metricType, searchQueryContext);

		ResultBag<Metric> segmentMetricResultBag =
			_segmentDog.getSegmentMetricResultBag(
				metricType, searchQueryContext);

		List<Metric> results = segmentMetricResultBag.getResults();

		Stream<Metric> stream = results.stream();

		audienceReport._segmentMetricReportResultBag = new ResultBag<>(
			stream.map(
				MetricReport::new
			).collect(
				Collectors.toList()
			),
			segmentMetricResultBag.getTotal());

		metricReport._audienceReport = audienceReport;
	}

	private ResultBagResource<Account> _getAccountResultBagResource(
		Integer page) {

		return ControllerLinkBuilder.methodOn(
			ReportRestController.class
		).getAccountResultBagResource(
			page
		);
	}

	private ResultBagResource<Activity> _getIndividualActivityResultBagResource(
		String individualId, Integer page) {

		return ControllerLinkBuilder.methodOn(
			ReportRestController.class
		).getIndividualActivityResultBagResource(
			individualId, page
		);
	}

	private ResultBagResource<Interest> _getIndividualInterestResultBagResource(
		String individualId, Integer page) {

		return ControllerLinkBuilder.methodOn(
			ReportRestController.class
		).getIndividualInterestResultBagResource(
			individualId, page
		);
	}

	private ResultBagResource<Individual> _getIndividualResultBagResource(
		Integer page, String query) {

		return ControllerLinkBuilder.methodOn(
			ReportRestController.class
		).getIndividualResultBagResource(
			page, query
		);
	}

	private Set<String> _getPageMetricTypeNames() {
		return Stream.of(
			PageMetricType.values()
		).map(
			PageMetricType::getName
		).collect(
			Collectors.toSet()
		);
	}

	private ResultBagResource<PageReport> _getPageReportResultBagResource(
		Integer page, String keywords, Integer rangeKey, String sortMetric,
		String sortOrder) {

		return ControllerLinkBuilder.methodOn(
			ReportRestController.class
		).getPageReportResultBagResource(
			page, keywords, rangeKey, sortMetric, sortOrder
		);
	}

	private ResultBagResource<Individual>
		_getSegmentIndividualResultBagResource(
			String individualSegmentId, Integer page, String query) {

		return ControllerLinkBuilder.methodOn(
			ReportRestController.class
		).getSegmentIndividualResultBagResource(
			individualSegmentId, page, query
		);
	}

	private ResultBagResource<Segment> _getSegmentResultBagResource(
		Integer page) {

		return ControllerLinkBuilder.methodOn(
			ReportRestController.class
		).getSegmentResultBagResource(
			page
		);
	}

	private Resource<Account> _toAccountResource(Account account) {
		return new Resource<>(
			account,
			ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(
					ReportRestController.class
				).getAccountResource(
					account.getId()
				)
			).withSelfRel());
	}

	private <T> Resource<T> _toChildResource(String parentId, T t) {
		return new Resource<>(
			t,
			ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(
					ReportRestController.class
				).getIndividualResource(
					parentId
				)
			).withRel(
				"parent"
			));
	}

	private Resource<Individual> _toIndividualResource(Individual individual) {
		return new Resource<>(
			individual,
			ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(
					ReportRestController.class
				).getIndividualResource(
					individual.getId()
				)
			).withSelfRel(),
			ControllerLinkBuilder.linkTo(
				_getIndividualActivityResultBagResource(
					individual.getId(), null)
			).withRel(
				"activities"
			),
			ControllerLinkBuilder.linkTo(
				_getIndividualInterestResultBagResource(
					individual.getId(), null)
			).withRel(
				"interests"
			),
			ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(
					ReportRestController.class
				).getIndividualSegmentResultBagResource(
					individual.getId()
				)
			).withRel(
				"segments"
			));
	}

	private <T, R> List<Resource<R>> _toListResource(
		List<T> results,
		Function<T, Resource<R>> resultResourceMapperFunction) {

		Stream<T> stream = results.stream();

		return stream.map(
			resultResourceMapperFunction
		).collect(
			Collectors.toList()
		);
	}

	private PageReport _toPageReport(
		Set<String> expands, PageMetric pageMetric,
		SearchQueryContext searchQueryContext) {

		Map<String, MetricReport> metricReports = new HashMap<>();

		for (Metric metric : pageMetric.getAvailableMetrics()) {
			MetricReport metricReport = new MetricReport(metric);

			if (expands.contains("audience")) {
				_expandMetricReportAudience(
					metricReport, metric.getMetricType(), searchQueryContext);
			}

			if (expands.contains("browser")) {
				List<Metric> browserMetrics = _technologyDog.getBrowserMetrics(
					metric.getMetricType(), searchQueryContext);

				_expandMetricReport(
					browserMetrics, metricReport::_addBrowserMetricReport);
			}

			if (expands.contains("device")) {
				List<Metric> deviceMetrics = _technologyDog.getDeviceMetrics(
					metric.getMetricType(), searchQueryContext);

				_expandMetricReport(
					deviceMetrics, metricReport::_addDeviceMetricReport);
			}

			if (expands.contains("location")) {
				List<Metric> geolocationMetrics =
					_geolocationDog.getGeolocationMetrics(
						metric.getMetricType(), searchQueryContext);

				_expandMetricReport(
					geolocationMetrics,
					metricReport::_addGeolocationMetricReport);
			}

			MetricType metricType = metric.getMetricType();

			metricReports.put(metricType.getName(), metricReport);
		}

		return new PageReport(metricReports, pageMetric);
	}

	private Resource<PageReport> _toPageReportResource(
		PageReport pageReport, int rangeKey) {

		return new Resource<>(
			pageReport,
			ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(
					ReportRestController.class
				).getPageReportResource(
					Collections.emptySet(), pageReport.getTitle(),
					_encodeURL(pageReport.getURL()), rangeKey
				)
			).withSelfRel());
	}

	private <T, R> ResultBagResource<R> _toResultBagResource(
		Object nextPageMethodInvocation, int page,
		Object prevPageMethodInvocation, ResultBag<T> resultBag,
		Function<T, Resource<R>> resultResourceMapperFunction) {

		ResultBagResource<R> resultBagResource = new ResultBagResource<>(
			new ResultBag<>(
				_toListResource(
					resultBag.getResults(), resultResourceMapperFunction),
				resultBag.getTotal()));

		if (((page + 1L) * _PAGE_SIZE) < resultBag.getTotal()) {
			resultBagResource.add(
				ControllerLinkBuilder.linkTo(
					nextPageMethodInvocation
				).withRel(
					"next"
				));
		}

		if (page > 0) {
			resultBagResource.add(
				ControllerLinkBuilder.linkTo(
					prevPageMethodInvocation
				).withRel(
					"prev"
				));
		}

		return resultBagResource;
	}

	private Resource<Segment> _toSegmentResource(Segment segment) {
		return new Resource<>(
			segment,
			ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(
					ReportRestController.class
				).getSegmentResource(
					segment.getId()
				)
			).withSelfRel(),
			ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(
					ReportRestController.class
				).getSegmentIndividualResultBagResource(
					segment.getId(), 0, null
				)
			).withRel(
				"individuals"
			));
	}

	private static final int _PAGE_SIZE = 20;

	private static final Log _log = LogFactory.getLog(
		ReportRestController.class);

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private ActivityDog _activityDog;

	@Autowired
	private DataExportTaskDog _dataExportTaskDog;

	@Autowired
	private GeolocationDog _geolocationDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private InterestDog _interestDog;

	@Autowired
	private MetricDog _metricDog;

	@Autowired
	private MetricTypeDog _metricTypeDog;

	@Autowired
	private SegmentDog _segmentDog;

	@Autowired
	private TechnologyDog _technologyDog;

	@Autowired
	private UserDog _userDog;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private static class AudienceReport {

		public Long getAnonymousUsersCount() {
			return _anonymousUsersCount;
		}

		public Long getKnownUsersCount() {
			return _knownUsersCount;
		}

		public Long getNonsegmentedKnownUsersCount() {
			return _nonsegmentedKnownUsersCount;
		}

		public Long getSegmentedKnownUsersCount() {
			return _segmentedKnownUsersCount;
		}

		@JsonProperty("segments")
		public ResultBag<MetricReport> getSegmentMetricReportResultBag() {
			return _segmentMetricReportResultBag;
		}

		private Long _anonymousUsersCount;
		private Long _knownUsersCount;
		private Long _nonsegmentedKnownUsersCount;
		private Long _segmentedKnownUsersCount;
		private ResultBag<MetricReport> _segmentMetricReportResultBag;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private static class MetricReport {

		public MetricReport(Metric metric) {
			_previousValue = metric.getPreviousValue();
			_trend = metric.getTrend();
			_value = metric.getValue();
			_valueKey = metric.getValueKey();
		}

		public MetricReport(String name, Double previousValue, Double value) {
			_name = name;
			_previousValue = previousValue;
			_value = value;
		}

		@JsonProperty("audience")
		public AudienceReport getAudienceReport() {
			return _audienceReport;
		}

		@JsonProperty("browsers")
		public List<MetricReport> getBrowserMetricReports() {
			return _browserMetricReports;
		}

		@JsonProperty("devices")
		public List<MetricReport> getDeviceMetricReports() {
			return _deviceMetricReports;
		}

		@JsonProperty("locations")
		public List<MetricReport> getGeolocationMetricReports() {
			return _geolocationMetricReports;
		}

		public String getName() {
			return _name;
		}

		public Double getPreviousValue() {
			return _previousValue;
		}

		public Trend getTrend() {
			if ((_trend == null) || (_trend.getPercentage() == null)) {
				return null;
			}

			return _trend;
		}

		public Double getValue() {
			return _value;
		}

		public String getValueKey() {
			return _valueKey;
		}

		private void _addBrowserMetricReport(MetricReport metricReport) {
			if (_browserMetricReports == null) {
				_browserMetricReports = new ArrayList<>();
			}

			_browserMetricReports.add(metricReport);
		}

		private void _addDeviceMetricReport(MetricReport metricReport) {
			if (_deviceMetricReports == null) {
				_deviceMetricReports = new ArrayList<>();
			}

			_deviceMetricReports.add(metricReport);
		}

		private void _addGeolocationMetricReport(MetricReport metricReport) {
			if (_geolocationMetricReports == null) {
				_geolocationMetricReports = new ArrayList<>();
			}

			_geolocationMetricReports.add(metricReport);
		}

		private AudienceReport _audienceReport;
		private List<MetricReport> _browserMetricReports;
		private List<MetricReport> _deviceMetricReports;
		private List<MetricReport> _geolocationMetricReports;
		private String _name;
		private final Double _previousValue;
		private Trend _trend;
		private final Double _value;
		private String _valueKey;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private static class PageReport {

		public PageReport(
			Map<String, MetricReport> metricReports, PageMetric pageMetric) {

			_metricReports = metricReports;
			_pageMetric = pageMetric;
		}

		public PageReport(PageMetric pageMetric) {
			for (Metric metric : pageMetric.getAvailableMetrics()) {
				MetricType metricType = metric.getMetricType();

				_metricReports.put(
					metricType.getName(), new MetricReport(metric));
			}

			_pageMetric = pageMetric;
		}

		@JsonProperty("metrics")
		public Map<String, MetricReport> getMetricReports() {
			return Collections.synchronizedMap(_metricReports);
		}

		public String getTitle() {
			return _pageMetric.getAssetTitle();
		}

		@JsonProperty("url")
		public String getURL() {
			return _pageMetric.getAssetId();
		}

		private Map<String, MetricReport> _metricReports = new HashMap<>();
		private final PageMetric _pageMetric;

	}

	private static class ResultBagResource<T>
		extends Resource<ResultBag<Resource<T>>> {

		public ResultBagResource(
			ResultBag<Resource<T>> content, Link... links) {

			super(content, links);
		}

	}

}