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

import com.liferay.osb.asah.backend.dog.configuration.DogConfiguration;
import com.liferay.osb.asah.backend.dog.helper.MetricHelper;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.dog.resolver.MetricResolver;
import com.liferay.osb.asah.backend.model.CohortHeatMapMetric;
import com.liferay.osb.asah.backend.model.Interval;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.SiteMetricType;
import com.liferay.osb.asah.backend.model.VisitorCohortMetric;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.WeekFields;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.pipeline.InternalSimpleValue;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.joda.time.DateTimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class VisitorCohortHeatMapDog {

	@Autowired
	public VisitorCohortHeatMapDog(List<DogConfiguration> dogConfigurations) {
		_dogConfigurationBag = new DogConfigurationBag(dogConfigurations);
	}

	public List<CohortHeatMapMetric> getCohortHeatMapMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollection(),
			_buildSearchSourceBuilder(
				dogConfiguration.getMetricResolver(metricType), metricType,
				searchQueryContext));

		if (DogUtil.isEmpty(aggregations)) {
			return Collections.emptyList();
		}

		Map<String, VisitorCohortMetric> visitorCohortMetrics =
			_metricHelper.createVisitorCohortMetrics(
				Clock.system(ZoneId.of(searchQueryContext.getTimeZoneId())),
				searchQueryContext.getInterval(), metricType);

		Histogram histogram = aggregations.get("ranges");

		for (Histogram.Bucket histogramBucket : histogram.getBuckets()) {
			Set<String> individualIds = new HashSet<>();

			Aggregations histogramBucketAggregations =
				histogramBucket.getAggregations();

			InternalFilter internalFilter = histogramBucketAggregations.get(
				"individual_id_filter");

			Aggregations internalFilterAggregations =
				internalFilter.getAggregations();

			Terms terms = internalFilterAggregations.get("individualIds");

			for (Terms.Bucket termsBucket : terms.getBuckets()) {
				individualIds.add(termsBucket.getKeyAsString());
			}

			LocalDate localDate = LocalDate.parse(
				histogramBucket.getKeyAsString());

			VisitorCohortMetric visitorCohortMetric = visitorCohortMetrics.get(
				localDate.toString());

			if (visitorCohortMetric == null) {
				continue;
			}

			visitorCohortMetric.setIndividualIds(individualIds);

			InternalSimpleValue internalSimpleValue =
				histogramBucketAggregations.get(
					metricType.getAggregationName());

			if (internalSimpleValue == null) {
				visitorCohortMetric.setValue(0.0);
			}
			else {
				visitorCohortMetric.setValue(internalSimpleValue.getValue());
			}
		}

		return _createCohortHeatMapMetrics(visitorCohortMetrics, metricType);
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		MetricResolver metricResolver, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		Interval interval = searchQueryContext.getInterval();

		DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
			_getDateHistogramAggregationBuilder(
				interval, searchQueryContext.getTimeZoneId());

		dateHistogramAggregationBuilder.subAggregation(
			_createIndividualIdsAggregationBuilder(metricType));

		Set<AggregationBuilder> aggregationBuilders =
			metricResolver.getAggregationBuilders();

		aggregationBuilders.forEach(
			dateHistogramAggregationBuilder::subAggregation);

		Set<PipelineAggregationBuilder> pipelineAggregatorBuilders =
			metricResolver.getPipelineAggregationBuilders();

		pipelineAggregatorBuilders.forEach(
			dateHistogramAggregationBuilder::subAggregation);

		LocalDateTime localDateTime = LocalDateTime.of(
			LocalDate.now(
				Clock.system(ZoneId.of(searchQueryContext.getTimeZoneId()))),
			LocalTime.MIDNIGHT);

		LocalDateTime startLocalDateTime = null;

		if (Interval.DAY.equals(interval)) {
			startLocalDateTime = localDateTime.minusDays(7);
		}
		else if (Interval.MONTH.equals(interval)) {
			localDateTime = localDateTime.withDayOfMonth(1);

			startLocalDateTime = localDateTime.minusMonths(6);
		}
		else if (Interval.WEEK.equals(interval)) {
			startLocalDateTime = localDateTime.minusWeeks(6);

			WeekFields weekFields = WeekFields.of(Locale.US);

			startLocalDateTime = startLocalDateTime.with(
				weekFields.getFirstDayOfWeek());
		}
		else {
			throw new RuntimeException("Invalid interval: " + interval);
		}

		ZonedDateTime zonedDateTime = startLocalDateTime.atZone(
			ZoneId.of(searchQueryContext.getTimeZoneId()));

		searchQueryContext.setDataSourceId(searchQueryContext.getAssetId());

		return _searchQueryHelper.createSearchSourceBuilder(
			dateHistogramAggregationBuilder, Optional.empty(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"eventDate"
				).gte(
					String.valueOf(zonedDateTime.toLocalDate())
				)),
			searchQueryContext);
	}

	private List<CohortHeatMapMetric> _createCohortHeatMapMetrics(
		Map<String, VisitorCohortMetric> visitorCohortMetrics,
		MetricType metricType) {

		Map<Pair<Integer, Integer>, CohortHeatMapMetric> cohortHeatMapMetrics =
			new TreeMap<>();

		List<String> visitorCohortMetricKeys = new LinkedList<>(
			visitorCohortMetrics.keySet());

		for (int row = 0; row < visitorCohortMetricKeys.size(); row++) {
			for (int col = visitorCohortMetricKeys.size() - 1; col >= row;
				 col--) {

				Metric metric = new Metric(metricType);

				double retention = 0.0;

				if (col == row) {
					VisitorCohortMetric visitorCohortMetric =
						visitorCohortMetrics.get(
							visitorCohortMetricKeys.get(row));

					metric.setValue(visitorCohortMetric.getValue());

					if (visitorCohortMetric.getValue() > 0) {
						retention = 100.0;
					}
				}
				else {
					VisitorCohortMetric visitorCohortMetric1 =
						visitorCohortMetrics.get(
							visitorCohortMetricKeys.get(row));
					VisitorCohortMetric visitorCohortMetric2 =
						visitorCohortMetrics.get(
							visitorCohortMetricKeys.get(col));

					Set<String> individualIds =
						visitorCohortMetric1.getIndividualIds();

					Set<String> intersection = new HashSet<>(individualIds);

					intersection.retainAll(
						visitorCohortMetric2.getIndividualIds());

					metric.setValue((double)intersection.size());

					if (!individualIds.isEmpty()) {
						retention =
							(double)intersection.size() /
								(double)individualIds.size() * 100.0;
					}
				}

				cohortHeatMapMetrics.put(
					Pair.of(col - row, row + 1),
					new CohortHeatMapMetric(
						String.valueOf(col - row), metric, retention,
						String.valueOf(row + 1),
						visitorCohortMetricKeys.get(row)));
			}
		}

		for (int col = 0; col < visitorCohortMetricKeys.size(); col++) {
			CohortHeatMapMetric cohortHeatMapMetric = new CohortHeatMapMetric(
				String.valueOf(col), new Metric(metricType), 0.0, "0", null);

			double total = 0;

			for (int row = 1; row <= (visitorCohortMetricKeys.size() - col);
				 row++) {

				CohortHeatMapMetric curCohortHeatMapMetric =
					cohortHeatMapMetrics.get(Pair.of(0, row));

				total += curCohortHeatMapMetric.getValue();
			}

			double columnTotal = 0;

			for (int row = 1; row <= (visitorCohortMetricKeys.size() - col);
				 row++) {

				CohortHeatMapMetric curCohortHeatMapMetric =
					cohortHeatMapMetrics.get(Pair.of(col, row));

				columnTotal += curCohortHeatMapMetric.getValue();
			}

			if (total > 0) {
				cohortHeatMapMetric.setRetention(columnTotal / total * 100);
				cohortHeatMapMetric.setValue(columnTotal);
			}

			cohortHeatMapMetrics.put(Pair.of(col, 0), cohortHeatMapMetric);
		}

		cohortHeatMapMetrics.remove(Pair.of(0, visitorCohortMetricKeys.size()));

		return new ArrayList<>(cohortHeatMapMetrics.values());
	}

	private AggregationBuilder _createIndividualIdsAggregationBuilder(
		MetricType metricType) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.rangeQuery(
				SiteMetricType.VIEWS.getFieldName()
			).gt(
				0
			));

		QueryBuilder knownVisitorQueryBuilder = BoolQueryBuilderUtil.should(
			QueryBuilders.termQuery("knownIndividual", true)
		).should(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("individualId")
			).mustNot(
				QueryBuilders.existsQuery("knownIndividual")
			)
		);

		if (metricType == SiteMetricType.ANONYMOUS_VISITORS) {
			boolQueryBuilder.mustNot(knownVisitorQueryBuilder);
		}
		else if (metricType == SiteMetricType.KNOWN_VISITORS) {
			boolQueryBuilder.filter(knownVisitorQueryBuilder);
		}

		FilterAggregationBuilder filterAggregationBuilder =
			AggregationBuilders.filter(
				"individual_id_filter", boolQueryBuilder);

		filterAggregationBuilder.subAggregation(
			AggregationBuilders.terms(
				"individualIds"
			).field(
				"individualId"
			).size(
				Integer.MAX_VALUE
			));

		return filterAggregationBuilder;
	}

	private DateHistogramAggregationBuilder _getDateHistogramAggregationBuilder(
		Interval interval, String timeZoneId) {

		DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
			AggregationBuilders.dateHistogram("ranges");

		dateHistogramAggregationBuilder.field("eventDate");

		if (Interval.MONTH.equals(interval)) {
			dateHistogramAggregationBuilder.dateHistogramInterval(
				DateHistogramInterval.MONTH);
		}
		else if (Interval.WEEK.equals(interval)) {
			dateHistogramAggregationBuilder.dateHistogramInterval(
				DateHistogramInterval.WEEK);
			dateHistogramAggregationBuilder.offset("-1d");
		}
		else {
			dateHistogramAggregationBuilder.dateHistogramInterval(
				DateHistogramInterval.DAY);
		}

		dateHistogramAggregationBuilder.format("8u-MM-dd");
		dateHistogramAggregationBuilder.timeZone(
			DateTimeZone.forID(timeZoneId));

		return dateHistogramAggregationBuilder;
	}

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

	@Autowired
	private MetricHelper _metricHelper;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}