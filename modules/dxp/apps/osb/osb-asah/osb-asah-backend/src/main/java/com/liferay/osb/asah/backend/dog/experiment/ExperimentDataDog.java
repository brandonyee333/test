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

import com.liferay.osb.asah.backend.dog.HistogramDog;
import com.liferay.osb.asah.backend.dog.MetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.HistogramMetricBag;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.PageMetric;
import com.liferay.osb.asah.backend.repository.PageAssetMetricRepository;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.PageMetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.util.SetUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		Long experimentId, PageMetricType goalPageMetricType,
		TimeRange timeRange, String variantId) {

		Optional<PageMetric> pageMetricOptional =
			_pageAssetMetricRepository.getExperimentPageMetric(
				experimentId,
				SetUtil.of(goalPageMetricType, PageMetricType.SESSIONS),
				timeRange, variantId);

		return _toExperimentDataPoint(goalPageMetricType, pageMetricOptional);
	}

	public ExperimentDataPoint<Double> fetchDichotomousDataPoint(
		String canonicalUrl, PageMetricType goalPageMetricType,
		TimeRange timeRange) {

		Optional<PageMetric> pageMetricOptional =
			_pageAssetMetricRepository.getExperimentPageMetric(
				canonicalUrl,
				SetUtil.of(goalPageMetricType, PageMetricType.SESSIONS),
				timeRange);

		return _toExperimentDataPoint(goalPageMetricType, pageMetricOptional);
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
				setIncludePrevious(Boolean.FALSE);
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
				PageMetricType.SESSIONS, searchQueryContext);

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

	private ExperimentDataPoint<Double> _toExperimentDataPoint(
		PageMetricType goalPageMetricType,
		Optional<PageMetric> pageMetricOptional) {

		if (!pageMetricOptional.isPresent()) {
			return null;
		}

		PageMetric pageMetric = pageMetricOptional.get();

		if (goalPageMetricType == PageMetricType.CLICK_THROUGH_RATE) {
			return new ExperimentDataPoint<>(
				_getMetricValueAsLong(pageMetric.getSessionsMetric()),
				_getMetricValue(pageMetric.getCTRMetric()));
		}

		return new ExperimentDataPoint<>(
			_getMetricValueAsLong(pageMetric.getSessionsMetric()),
			_getMetricValue(pageMetric.getBounceMetric()));
	}

	@Autowired
	private HistogramDog _histogramDog;

	@Autowired
	private MetricDog _metricDog;

	@Autowired
	private PageAssetMetricRepository _pageAssetMetricRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}