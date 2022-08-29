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

import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class IndividualMetricDog {

	public IndividualMetricDog(
		BQIdentityRepository bqIdentityRepository, TimeZoneDog timeZoneDog) {

		_bqIdentityRepository = bqIdentityRepository;
		_timeZoneDog = timeZoneDog;
	}

	public IndividualMetric getIndividualMetric(
		SearchQueryContext searchQueryContext, Set<String> selectedMetrics) {

		IndividualMetric individualMetric = new IndividualMetric();

		if (selectedMetrics.contains(
				IndividualMetricType.ANONYMOUS_INDIVIDUALS.getName())) {

			individualMetric.setAnonymousIndividualsMetric(
				_getIndividualCountMetric(
					IndividualMetricType.ANONYMOUS_INDIVIDUALS,
					searchQueryContext));
		}

		if (selectedMetrics.contains(
				IndividualMetricType.KNOWN_INDIVIDUALS.getName())) {

			individualMetric.setKnownIndividualsMetric(
				_getIndividualCountMetric(
					IndividualMetricType.KNOWN_INDIVIDUALS,
					searchQueryContext));
		}

		if (selectedMetrics.contains(
				IndividualMetricType.TOTAL_INDIVIDUALS.getName())) {

			individualMetric.setTotalIndividualsMetric(
				_getIndividualCountMetric(
					IndividualMetricType.TOTAL_INDIVIDUALS,
					searchQueryContext));
		}

		if (selectedMetrics.contains("defaultMetric")) {
			individualMetric.setKnownIndividualsMetric(
				_getIndividualCountMetric(
					IndividualMetricType.KNOWN_INDIVIDUALS,
					searchQueryContext));
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
			searchQueryContext.isActive(), channelId, localDate, metricType);
	}

	private Metric _getIndividualCountMetric(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		Metric metric = new Metric(metricType);

		LocalDate localDate = LocalDate.now(_timeZoneDog.getZoneId());

		LocalDate previousLocalDate = _getPreviousLocalDate(
			localDate, searchQueryContext.getTimeRange());

		metric.setPreviousValue(
			getIndividualsCount(
				previousLocalDate, metricType, searchQueryContext));
		metric.setPreviousValueKey(previousLocalDate.toString());

		metric.setValue(
			getIndividualsCount(localDate, metricType, searchQueryContext));
		metric.setValueKey(localDate.toString());

		return metric;
	}

	private LocalDate _getPreviousLocalDate(
		LocalDate localDate, TimeRange timeRange) {

		LocalDate previousLocalDate = LocalDate.from(localDate);

		return previousLocalDate.minusDays(timeRange.getDeltaDays());
	}

	private final BQIdentityRepository _bqIdentityRepository;
	private final TimeZoneDog _timeZoneDog;

}