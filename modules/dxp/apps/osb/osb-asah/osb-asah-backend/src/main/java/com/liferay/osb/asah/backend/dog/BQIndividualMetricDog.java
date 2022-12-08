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

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.IndividualMetric;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.IndividualMetricType;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class BQIndividualMetricDog {

	public BQIndividualMetricDog(
		BQIdentityRepository bqIdentityRepository, TimeZoneDog timeZoneDog) {

		_bqIdentityRepository = bqIdentityRepository;
		_timeZoneDog = timeZoneDog;
	}

	public IndividualMetric getIndividualMetric(
		SearchQueryContext searchQueryContext, Set<String> selectedMetrics) {

		IndividualMetric individualMetric = new IndividualMetric();

		List<Consumer<Metric>> consumers = new ArrayList<>();
		List<MetricType> metricTypes = new ArrayList<>();

		if (selectedMetrics.contains(
				IndividualMetricType.ANONYMOUS_INDIVIDUALS.getName())) {

			consumers.add(individualMetric::setAnonymousIndividualsMetric);
			metricTypes.add(IndividualMetricType.ANONYMOUS_INDIVIDUALS);
		}

		if (selectedMetrics.contains(
				IndividualMetricType.KNOWN_INDIVIDUALS.getName()) ||
			selectedMetrics.contains("defaultMetric")) {

			consumers.add(individualMetric::setKnownIndividualsMetric);
			metricTypes.add(IndividualMetricType.KNOWN_INDIVIDUALS);
		}

		if (selectedMetrics.contains(
				IndividualMetricType.TOTAL_INDIVIDUALS.getName()) &&
			(!selectedMetrics.contains(
				IndividualMetricType.ANONYMOUS_INDIVIDUALS.getName()) ||
			 !selectedMetrics.contains(
				 IndividualMetricType.KNOWN_INDIVIDUALS.getName()))) {

			consumers.add(individualMetric::setTotalIndividualsMetric);
			metricTypes.add(IndividualMetricType.TOTAL_INDIVIDUALS);
		}

		List<Metric> metrics = _getIndividualCountMetrics(
			metricTypes, searchQueryContext);

		for (int i = 0; i < metrics.size(); i++) {
			Consumer<Metric> metricConsumer = consumers.get(i);

			metricConsumer.accept(metrics.get(i));
		}

		if (selectedMetrics.contains(
				IndividualMetricType.ANONYMOUS_INDIVIDUALS.getName()) &&
			selectedMetrics.contains(
				IndividualMetricType.KNOWN_INDIVIDUALS.getName()) &&
			selectedMetrics.contains(
				IndividualMetricType.TOTAL_INDIVIDUALS.getName())) {

			Metric totalIndividualsMetric = new Metric(
				IndividualMetricType.TOTAL_INDIVIDUALS);

			Double previousValue = 0.0;
			Double value = 0.0;

			for (Metric metric : metrics) {
				if ((metric.getMetricType() !=
						IndividualMetricType.ANONYMOUS_INDIVIDUALS) &&
					(metric.getMetricType() !=
						IndividualMetricType.KNOWN_INDIVIDUALS)) {

					continue;
				}

				previousValue += metric.getPreviousValue();

				totalIndividualsMetric.setPreviousValue(previousValue);

				totalIndividualsMetric.setPreviousValueKey(
					metric.getPreviousValueKey());

				value += metric.getValue();

				totalIndividualsMetric.setValue(value);

				totalIndividualsMetric.setValueKey(metric.getValueKey());
			}

			individualMetric.setTotalIndividualsMetric(totalIndividualsMetric);
		}

		return individualMetric;
	}

	public double getIndividualsCount(
		LocalDate localDate, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		Long channelId = null;

		if (searchQueryContext.getChannelId() != null) {
			channelId = Long.valueOf(searchQueryContext.getChannelId());
		}

		return _bqIdentityRepository.getIndividualsCount(
			searchQueryContext.isActive(), channelId, localDate, metricType,
			_timeZoneDog.getZoneId());
	}

	private List<Metric> _getIndividualCountMetrics(
		List<MetricType> metricTypes, SearchQueryContext searchQueryContext) {

		List<Metric> metrics = new ArrayList<>();

		Long channelId = null;

		if (searchQueryContext.getChannelId() != null) {
			channelId = Long.valueOf(searchQueryContext.getChannelId());
		}

		LocalDate localDate = LocalDate.now(_timeZoneDog.getZoneId());

		LocalDate previousLocalDate = _getPreviousLocalDate(
			localDate, searchQueryContext.getTimeRange());

		List<Long> individualsCounts =
			_bqIdentityRepository.getIndividualsCounts(
				searchQueryContext.isActive(), channelId,
				Arrays.asList(localDate, previousLocalDate), metricTypes,
				_timeZoneDog.getZoneId());

		for (int i = 0; i < metricTypes.size(); i++) {
			Metric metric = new Metric(metricTypes.get(i));

			Long previousValue = individualsCounts.get(i + metricTypes.size());

			metric.setPreviousValue(previousValue.doubleValue());

			metric.setPreviousValueKey(previousLocalDate.toString());

			Long value = individualsCounts.get(i);

			metric.setValue(value.doubleValue());

			metric.setValueKey(localDate.toString());

			metrics.add(metric);
		}

		return metrics;
	}

	private LocalDate _getPreviousLocalDate(
		LocalDate localDate, TimeRange timeRange) {

		LocalDate previousLocalDate = LocalDate.from(localDate);

		return previousLocalDate.minusDays(timeRange.getDeltaDays());
	}

	private final BQIdentityRepository _bqIdentityRepository;
	private final TimeZoneDog _timeZoneDog;

}