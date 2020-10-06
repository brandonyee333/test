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

package com.liferay.osb.asah.backend.dog;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.liferay.osb.asah.backend.dog.experiment.ExperimentMetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.DXPVariant;
import com.liferay.osb.asah.backend.model.Experiment;
import com.liferay.osb.asah.backend.model.ExperimentSettings;
import com.liferay.osb.asah.backend.model.Goal;
import com.liferay.osb.asah.backend.model.GoalMetric;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.PageMetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.common.dxp.DXPClient;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.model.DXPVariantSettings;
import com.liferay.osb.asah.common.model.ExperimentMetrics;
import com.liferay.osb.asah.common.model.ExperimentMetricsBag;
import com.liferay.osb.asah.common.model.ExperimentStatus;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.VariantMetrics;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.io.IOException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 * @author Marcellus Tavares
 */
@Component
public class ExperimentDog {

	public Experiment addExperiment(Experiment experiment) {
		experiment.setChannelId(_getChannelId(experiment));
		experiment.setId(null);

		if (experiment.getModifiedDate() == null) {
			experiment.setModifiedDate(experiment.getCreateDate());
		}

		JSONObject experimentJSONObject = _faroInfoElasticsearchInvoker.add(
			"experiments",
			_objectMapper.convertValue(experiment, JSONObject.class));

		experiment.setId(experimentJSONObject.optString("id"));

		return experiment;
	}

	public ExperimentMetrics calculateExperimentMetrics(String experimentId) {
		return _experimentMetricDog.calculateMetrics(
			getExperiment(experimentId));
	}

	public boolean deleteExperiment(String experimentId, boolean updateDXP) {
		Experiment experiment = getExperiment(experimentId);

		ExperimentStatus experimentStatus = experiment.getExperimentStatus();

		if (!experimentStatus.isDeleteAllowed()) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				String.format(
					"Unable to delete an experiment in the %s status",
					experimentStatus));
		}

		if (updateDXP) {
			_dxpClient.deleteDXPExperiment(
				experiment.getDataSourceId(), experiment.getId());
		}

		return _faroInfoElasticsearchInvoker.delete(
			"experiments", experimentId);
	}

	public Experiment fetchExperiment(String experimentId) {
		SearchHits searchHits = _dataDog.querySearchHits(
			"experiments", _faroInfoElasticsearchInvoker,
			_buildExperimentSearchSourceBuilder(experimentId));

		long totalHitsCount = HitsUtil.getTotalHitsCount(searchHits);

		if (totalHitsCount != 1) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"Unable to retrieve experiment for the ID %s. Total " +
							"hits returned %d.",
						experimentId, totalHitsCount));
			}

			return null;
		}

		SearchHit searchHit = searchHits.getAt(0);

		try {
			return _objectMapper.readValue(
				searchHit.getSourceAsString(), Experiment.class);
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to process search request", ioe);
		}
	}

	public Experiment getExperiment(String experimentId) {
		Experiment experiment = fetchExperiment(experimentId);

		if (experiment == null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no experiment with ID " + experimentId);
		}

		return experiment;
	}

	public Long getExperimentEstimatedDaysDuration(
		double confidenceLevel, List<DXPVariantSettings> dxpVariantsSettings,
		String experimentId) {

		if ((confidenceLevel < 80) || (confidenceLevel > 99)) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Confidence level value must be between 80 and 99");
		}

		Experiment experiment = getExperiment(experimentId);

		experiment.setConfidenceLevel(confidenceLevel);

		return _experimentMetricDog.estimateDaysDuration(
			dxpVariantsSettings, experiment);
	}

	public ExperimentMetrics getExperimentMetrics(String experimentId) {
		List<ExperimentMetrics> experimentMetricsList =
			getExperimentMetricsList(experimentId);

		if (experimentMetricsList.isEmpty()) {
			return _createEmptyExperimentMetrics(experimentId);
		}

		return experimentMetricsList.get(experimentMetricsList.size() - 1);
	}

	public List<ExperimentMetrics> getExperimentMetricsList(
		String experimentId) {

		Experiment experiment = getExperiment(experimentId);

		if (experiment.getExperimentStatus() == ExperimentStatus.DRAFT) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Unable to retrieve experiment metrics while experiment is " +
					"draft");
		}

		ExperimentMetricsBag experimentMetricsBag = _fetchExperimentMetricsBag(
			experiment.getId());

		if (experimentMetricsBag == null) {
			return Collections.emptyList();
		}

		return experimentMetricsBag.getExperimentMetricsList();
	}

	public ResultBag<Experiment> getExperimentResultBag(
		String channelId, String keywords, int size, Sort sort, int start) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"experiments", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				SortBuilderUtil.fieldSort(sort),
				_buildKeywordsQueryBuilder(channelId, keywords), size, start));

		return DogUtil.createResultBag(Experiment.class, searchHits);
	}

	public List<HistogramMetric> getExperimentSessionHistogramMetrics(
		String experimentId, String variantId) {

		Experiment experiment = fetchExperiment(experimentId);

		if (experiment == null) {
			return Collections.emptyList();
		}

		SearchQueryContext searchQueryContext = new SearchQueryContext() {
			{
				setExperimentId(experimentId);
				setTimeRange(
					_getTimeRange(experiment.getStartedDateLocalDateTime()));
				setVariantId(variantId);
			}
		};

		return _histogramDog.getHistogramMetrics(
			false, PageMetricType.SESSIONS, searchQueryContext);
	}

	public Long getExperimentSessions(String experimentId) {
		Aggregations aggregations = _dataDog.queryAggregations(
			"pages", _buildTotalSessionsSearchSourceBuilder(experimentId));

		if (DogUtil.isEmpty(aggregations)) {
			return 0L;
		}

		return DogUtil.getCardinalityAsLong(aggregations.get("sessions_count"));
	}

	public Long getVariantUniqueVisitors(
		String experimentId, String variantId) {

		Aggregations aggregations = _dataDog.queryAggregations(
			"pages",
			_buildVariantUniqueVisitorsSearchSourceBuilder(
				experimentId, variantId));

		if (DogUtil.isEmpty(aggregations)) {
			return 0L;
		}

		return DogUtil.getCardinalityAsLong(
			aggregations.get("individuals_count"));
	}

	public Experiment patchExperiment(
		Experiment experiment, ExperimentSettings experimentSettings,
		boolean updateDXP) {

		Experiment existingExperiment = getExperiment(experiment.getId());

		if (experiment.getDescription() != null) {
			existingExperiment.setDescription(experiment.getDescription());
		}

		if (experiment.getDXPExperienceName() != null) {
			existingExperiment.setDXPExperienceName(
				experiment.getDXPExperienceName());
		}

		if (experiment.getDXPSegmentName() != null) {
			existingExperiment.setDXPSegmentName(
				experiment.getDXPSegmentName());
		}

		_setExperimentSettings(existingExperiment, experimentSettings);

		if (experiment.getExperimentStatus() != null) {
			_setExperimentStatus(
				existingExperiment, experiment.getExperimentStatus());
			_setPublishedDXPVariantId(
				existingExperiment, experiment.getPublishedDXPVariantId());

			if (updateDXP) {
				_setExperimentStatusDXP(
					existingExperiment, experimentSettings,
					experiment.getExperimentStatus());
			}
		}

		if (experiment.getName() != null) {
			existingExperiment.setName(experiment.getName());
		}

		if (experiment.getGoal() != null) {
			existingExperiment.setGoal(experiment.getGoal());
		}

		if (experiment.getPageRelativePath() != null) {
			existingExperiment.setPageRelativePath(
				experiment.getPageRelativePath());
		}

		if (experiment.getPageTitle() != null) {
			existingExperiment.setPageTitle(experiment.getPageTitle());
		}

		if (experiment.getPageURL() != null) {
			existingExperiment.setPageURL(experiment.getPageURL());
		}

		return updateExperiment(existingExperiment);
	}

	public Experiment updateExperiment(Experiment experiment) {
		experiment.setModifiedDate(new Date());

		_faroInfoElasticsearchInvoker.update(
			"experiments",
			_objectMapper.convertValue(experiment, JSONObject.class));

		return experiment;
	}

	private SearchSourceBuilder _buildExperimentMetricsSearchSourceBuilder(
		String experimentId) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.fetchSource("metrics", null);
		searchSourceBuilder.query(QueryBuilders.termQuery("id", experimentId));
		searchSourceBuilder.size(1);

		return searchSourceBuilder;
	}

	private SearchSourceBuilder _buildExperimentSearchSourceBuilder(
		String experimentId) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.fetchSource(null, "metrics");
		searchSourceBuilder.query(QueryBuilders.termQuery("id", experimentId));
		searchSourceBuilder.size(1);

		return searchSourceBuilder;
	}

	private QueryBuilder _buildKeywordsQueryBuilder(
		String channelId, String keywords) {

		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "channelId", channelId);

		if (StringUtils.isNotBlank(keywords)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.queryStringQuery(
						String.format(
							"name:*%1$s* OR description:*%1$s*",
							QueryUtil.escapeKeywords(keywords)))
				).should(
					QueryBuilders.multiMatchQuery(
						keywords, "name", "description"
					).fuzziness(
						Fuzziness.AUTO
					)
				).should(
					QueryBuilders.wildcardQuery(
						"pageURL.search", "*" + keywords + "*")
				));
		}

		if (boolQueryBuilder.hasClauses()) {
			return boolQueryBuilder;
		}

		return QueryBuilders.matchAllQuery();
	}

	private SearchSourceBuilder _buildTotalSessionsSearchSourceBuilder(
		String experimentId) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		CardinalityAggregationBuilder cardinalityAggregationBuilder =
			AggregationBuilders.cardinality("sessions_count");

		cardinalityAggregationBuilder.field("sessionId");

		cardinalityAggregationBuilder.precisionThreshold(2000);

		searchSourceBuilder.aggregation(cardinalityAggregationBuilder);

		searchSourceBuilder.query(
			QueryBuilders.termQuery("experimentId", experimentId));
		searchSourceBuilder.size(0);

		return searchSourceBuilder;
	}

	private SearchSourceBuilder _buildVariantUniqueVisitorsSearchSourceBuilder(
		String experimentId, String variantId) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		CardinalityAggregationBuilder cardinalityAggregationBuilder =
			AggregationBuilders.cardinality("individuals_count");

		cardinalityAggregationBuilder.field("individualId");

		cardinalityAggregationBuilder.precisionThreshold(2000);

		searchSourceBuilder.aggregation(cardinalityAggregationBuilder);

		searchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("experimentId", experimentId)
			).filter(
				QueryBuilders.termQuery("variantId", variantId)
			));
		searchSourceBuilder.size(0);

		return searchSourceBuilder;
	}

	private ExperimentMetrics _createEmptyExperimentMetrics(
		String experimentId) {

		ExperimentMetrics experimentMetrics = new ExperimentMetrics();

		Experiment experiment = getExperiment(experimentId);

		for (DXPVariant dxpVariant : experiment.getDXPVariants()) {
			experimentMetrics.addVariantMetrics(
				new VariantMetrics(
					dxpVariant.isControl(), dxpVariant.getDXPVariantId()));
		}

		experimentMetrics.setElapsedDays(_getExperimentElapsedDays(experiment));
		experimentMetrics.setEstimatedDaysLeft(null);

		return experimentMetrics;
	}

	private ExperimentMetricsBag _fetchExperimentMetricsBag(
		String experimentId) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"experiments", _faroInfoElasticsearchInvoker,
			_buildExperimentMetricsSearchSourceBuilder(experimentId));

		long totalHitsCount = HitsUtil.getTotalHitsCount(searchHits);

		if (totalHitsCount != 1) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"Unable to retrieve experiment metrics for " +
							"experiment ID %s. Returned %d total hits.",
						experimentId, totalHitsCount));
			}

			return null;
		}

		SearchHit searchHit = searchHits.getAt(0);

		try {
			return _objectMapper.readValue(
				searchHit.getSourceAsString(), ExperimentMetricsBag.class);
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to process search request", ioe);
		}
	}

	private String _getChannelId(Experiment experiment) {
		if (StringUtils.isNotEmpty(experiment.getChannelId())) {
			return experiment.getChannelId();
		}

		String channelId = _faroInfoDataSourceDog.getChannelId(
			experiment.getDataSourceId());

		if (channelId != null) {
			return channelId;
		}

		throw new OSBAsahException(
			HttpStatus.BAD_REQUEST,
			"Unable to create an experiment without channel ID");
	}

	private long _getExperimentElapsedDays(Experiment experiment) {
		Date experimentStartedDate = experiment.getStartedDate();

		return ChronoUnit.DAYS.between(
			experimentStartedDate.toInstant(), Instant.now());
	}

	private TimeRange _getTimeRange(LocalDateTime startedDateLocalDateTime) {
		LocalDate startLocalDate = LocalDate.now(ZoneOffset.UTC);

		if (startedDateLocalDateTime != null) {
			startLocalDate = startedDateLocalDateTime.toLocalDate();
		}

		return TimeRange.of(startLocalDate);
	}

	private void _setExperimentSettings(
		Experiment experiment, ExperimentSettings experimentSettings) {

		if (experimentSettings == null) {
			return;
		}

		Double confidenceLevel = experimentSettings.getConfidenceLevel();

		if ((confidenceLevel < 80) || (confidenceLevel > 99)) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Confidence level value must be between 80 and 99");
		}

		experiment.setConfidenceLevel(confidenceLevel);

		Map<String, DXPVariantSettings> dxpVariantsSettingsMap =
			experimentSettings.getDXPVariantsSettingsMap();

		double dxpVariantsTrafficSum = 0;

		for (DXPVariant dxpVariant : experiment.getDXPVariants()) {
			DXPVariantSettings dxpVariantSettings = dxpVariantsSettingsMap.get(
				dxpVariant.getDXPVariantId());

			dxpVariant.setTrafficSplit(dxpVariantSettings.getTrafficSplit());

			dxpVariantsTrafficSum += dxpVariantSettings.getTrafficSplit();
		}

		if (dxpVariantsTrafficSum != 100) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"The sum of all variant traffic splits must be 100");
		}
	}

	private void _setExperimentStatus(
		Experiment experiment, ExperimentStatus experimentStatus) {

		if ((experimentStatus == ExperimentStatus.FINISHED_NO_WINNER) ||
			(experimentStatus == ExperimentStatus.FINISHED_WINNER)) {

			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				String.format(
					"This API is not allowed to set the status %s",
					experimentStatus));
		}

		if (!ExperimentStatus.isValidTransition(
				experiment.getExperimentStatus(), experimentStatus)) {

			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				String.format(
					"Invalid status transition: from %s to %s",
					experiment.getExperimentStatus(), experimentStatus));
		}

		experiment.setExperimentStatus(experimentStatus);

		if (experimentStatus == ExperimentStatus.RUNNING) {
			experiment.setStartedDate(new Date());

			Goal goal = experiment.getGoal();

			if ((goal.getGoalMetric() == GoalMetric.CLICK_RATE) &&
				StringUtils.isBlank(goal.getTarget())) {

				throw new OSBAsahException(
					HttpStatus.BAD_REQUEST,
					String.format(
						"Transition to %s requires target element",
						experimentStatus));
			}
		}
		else if (experimentStatus == ExperimentStatus.TERMINATED) {
			experiment.setFinishedDate(new Date());
		}
	}

	private void _setExperimentStatusDXP(
		Experiment experiment, ExperimentSettings experimentSettings,
		ExperimentStatus experimentStatus) {

		if (experimentStatus != ExperimentStatus.RUNNING) {
			String dxpVariantId = null;

			if (experimentStatus == ExperimentStatus.COMPLETED) {
				dxpVariantId = experiment.getPublishedDXPVariantId();
			}

			_dxpClient.updateDXPExperimentStatus(
				experiment.getDataSourceId(), experiment.getId(),
				experimentStatus, dxpVariantId);

			return;
		}

		if (experimentSettings == null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Confidence level and variants' traffic split must be valid");
		}

		_dxpClient.runDXPExperiment(
			experimentSettings.getConfidenceLevel(),
			experiment.getDataSourceId(),
			experimentSettings.getDXPVariantsSettings(), experiment.getId());
	}

	private void _setPublishedDXPVariantId(
		Experiment experiment, String publishedDXPVariantId) {

		ExperimentStatus experimentStatus = experiment.getExperimentStatus();

		if (experimentStatus == ExperimentStatus.COMPLETED) {
			if (publishedDXPVariantId == null) {
				throw new OSBAsahException(
					HttpStatus.BAD_REQUEST,
					String.format(
						"Transition to %s requires published DXP variant ID",
						experimentStatus));
			}

			experiment.setPublishedDXPVariantId(publishedDXPVariantId);
		}
	}

	private static final Log _log = LogFactory.getLog(ExperimentDog.class);

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private DXPClient _dxpClient;

	@Autowired
	private ExperimentMetricDog _experimentMetricDog;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private HistogramDog _histogramDog;

	private final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new JavaTimeModule());
			registerModule(new Jdk8Module());
			registerModule(new JsonOrgModule());
		}
	};

}