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

package com.liferay.osb.asah.backend.dog.experiment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregation;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liferay.osb.asah.backend.dog.HistogramDog;
import com.liferay.osb.asah.backend.dog.MetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.HistogramMetricBag;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.PageMetric;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.PageMetricType;
import com.liferay.osb.asah.common.model.TimeRange;

/**
 * @author Marcellus Tavares
 */
@Component
public class ExperimentDataDog {

	public List<ExperimentDataPoint<Double[]>> fetchContinuousDataPoints(
		MetricType metricType, LocalDateTime startLocalDateTime,
		String variantId) {

		return _fetchContinuousDataPoints(
			null, metricType, startLocalDateTime, variantId);
	}

	public List<ExperimentDataPoint<Double[]>> fetchContinuousDataPoints(
		String experienceId, MetricType metricType) {

		LocalDateTime localDateTime = LocalDateTime.now(ZoneOffset.UTC);

		return _fetchContinuousDataPoints(
			experienceId, metricType, localDateTime.minusDays(6), null);
	}

	public ExperimentDataPoint<Double> fetchDichotomousDataPoint(
		Long dataSourceId, String dxpExperienceId, Long experimentId,
		MetricType metricType, String pageURL, TimeRange timeRange,
		String variantId) {

		SearchQueryContext searchQueryContext = new SearchQueryContext() {
			{
				setDataSourceId(
					Optional.ofNullable(
						dataSourceId
					).map(
						String::valueOf
					).orElse(
						null
					));
				setExperienceId(dxpExperienceId);
				setExperimentId(experimentId);
				setTimeRange(timeRange);
				setURL(pageURL);
				setVariantId(variantId);
			}
		};

		PageMetric pageMetric = _metricDog.getAssetMetric(
			searchQueryContext,
			new HashSet<String>() {
				{
					add(PageMetricType.BOUNCE.getName());
					add(PageMetricType.CLICK_THROUGH_RATE.getName());
					add(PageMetricType.SESSIONS.getName());
				}
			});

		if (metricType == PageMetricType.CLICK_THROUGH_RATE) {
			return new ExperimentDataPoint<>(
				_getMetricValueAsLong(pageMetric.getSessionsMetric()),
				_getMetricValue(pageMetric.getCTRMetric()));
		}

		return new ExperimentDataPoint<>(
			_getMetricValueAsLong(pageMetric.getSessionsMetric()),
			_getMetricValue(pageMetric.getBounceMetric()));
	}

	private List<ExperimentDataPoint<Double[]>> _fetchContinuousDataPoints(
		String experienceId, MetricType metricType,
		LocalDateTime startLocalDateTime, String variantId) {

		LocalDate endLocalDate = LocalDate.now(_timeZoneDog.getZoneId());

		endLocalDate = endLocalDate.minusDays(1);

		LocalDate startLocalDate = DateUtil.toUTCLocalDate(
			startLocalDateTime, _timeZoneDog.getZoneId());

		List<ExperimentDataPoint<Double[]>> experimentDataPoints =
			new ArrayList<>();

		SearchQueryContext searchQueryContext = new SearchQueryContext() {
			{
				setExperienceId(experienceId);
				setVariantId(variantId);
			}
		};

		while (ChronoUnit.DAYS.between(endLocalDate, startLocalDate) <= 0) {
			List<Double> continuousExperimentDataPointValues =
				_fetchContinuousDataPointValues(
					startLocalDate, metricType, searchQueryContext);

			experimentDataPoints.add(
				new ExperimentDataPoint<>(
					0,
					continuousExperimentDataPointValues.toArray(
						new Double[0])));

			startLocalDate = startLocalDate.plusDays(1);
		}

		searchQueryContext.setTimeRange(
			TimeRange.of(endLocalDate, startLocalDateTime.toLocalDate()));

		HistogramMetricBag histogramMetricBag =
			_histogramDog.getHistogramMetricBag(
				false, PageMetricType.SESSIONS, searchQueryContext);

		List<HistogramMetric> sessionHistogramMetrics =
			histogramMetricBag.getMetrics();

		for (int i = 0; i < sessionHistogramMetrics.size(); i++) {
			ExperimentDataPoint<Double[]> experimentDataPoint =
				experimentDataPoints.get(i);

			experimentDataPoint.setTrials(
				_getMetricValueAsLong(sessionHistogramMetrics.get(i)));
		}

		return experimentDataPoints;
	}

	private List<Double> _fetchContinuousDataPointValues(
		LocalDate localDate, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		return new LinkedList<>();
	}

	private double _getMetricValue(Metric metric) {
		return metric.getValue();
	}

	private long _getMetricValueAsLong(Metric metric) {
		Double value = metric.getValue();

		return value.longValue();
	}

	@Autowired
	private HistogramDog _histogramDog;

	@Autowired
	private MetricDog _metricDog;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}