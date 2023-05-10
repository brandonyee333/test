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

import com.liferay.osb.asah.backend.dog.helper.MetricHelper;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.EventMetricType;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.HistogramMetricBag;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQEventRepository;

import java.time.Clock;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Component
public class EventHistogramDog {

	public HistogramMetricBag getEventsCountHistogram(
		SearchQueryContext searchQueryContext) {

		TimeRange timeRange = searchQueryContext.getTimeRange();

		return _createHistogramBag(
			EventMetricType.TOTAL_EVENTS, searchQueryContext,
			_bqEventRepository.getBQEventsCountGroupByEventDate(
				searchQueryContext.getChannelIdAsLong(),
				searchQueryContext.getEntityId(),
				_getInterval(searchQueryContext),
				searchQueryContext.getKeywords(),
				timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId()));
	}

	public HistogramMetricBag getSessionsCountHistogram(
		SearchQueryContext searchQueryContext) {

		TimeRange timeRange = searchQueryContext.getTimeRange();

		return _createHistogramBag(
			EventMetricType.TOTAL_SESSIONS, searchQueryContext,
			_bqEventRepository.getEventSessionsCountGroupByEventDate(
				searchQueryContext.getChannelIdAsLong(),
				searchQueryContext.getEntityId(),
				_getInterval(searchQueryContext),
				searchQueryContext.getKeywords(),
				timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId()));
	}

	private HistogramMetricBag _createHistogramBag(
		MetricType metricType, SearchQueryContext searchQueryContext,
		Map<String, Integer> histogramMetricValues) {

		HistogramMetricBag histogramMetricBag =
			_metricHelper.createHistogramMetricBag(
				Clock.system(_timeZoneDog.getZoneId()),
				searchQueryContext.isIncludePrevious(),
				searchQueryContext.getInterval(), metricType,
				searchQueryContext.getTimeRange());

		for (HistogramMetric histogramMetric :
				histogramMetricBag.getMetrics()) {

			if (histogramMetricValues.containsKey(histogramMetric.getKey())) {
				histogramMetric.setValue(
					(double)histogramMetricValues.get(
						histogramMetric.getKey()));
			}
		}

		return histogramMetricBag;
	}

	private Interval _getInterval(SearchQueryContext searchQueryContext) {
		TimeRange timeRange = searchQueryContext.getTimeRange();

		if (timeRange.equals(TimeRange.LAST_24_HOURS) ||
			timeRange.equals(TimeRange.YESTERDAY)) {

			return Interval.HOUR;
		}

		return searchQueryContext.getInterval();
	}

	@Autowired
	private BQEventRepository _bqEventRepository;

	@Autowired
	private MetricHelper _metricHelper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}