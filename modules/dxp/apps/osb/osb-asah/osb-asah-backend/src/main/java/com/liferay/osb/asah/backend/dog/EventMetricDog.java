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
import com.liferay.osb.asah.backend.model.EventMetric;
import com.liferay.osb.asah.backend.model.EventMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQEventRepository;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class EventMetricDog {

	public EventMetric getEventMetric(SearchQueryContext searchQueryContext) {
		EventMetric eventMetric = new EventMetric();

		Metric totalEventsMetric = new Metric(EventMetricType.TOTAL_EVENTS);

		TimeRange timeRange = searchQueryContext.getTimeRange();

		Set<String> userIds = _individualDog.getIndividualUserIds(
			Long.valueOf(searchQueryContext.getEntityId()));

		totalEventsMetric.setValue(
			(double)_bqEventRepository.countBQEvents(
				Long.valueOf(searchQueryContext.getChannelId()),
				searchQueryContext.getKeywords(),
				timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
				userIds));

		eventMetric.setTotalEventsMetric(totalEventsMetric);

		Metric totalSessionsMetric = new Metric(EventMetricType.TOTAL_SESSIONS);

		totalSessionsMetric.setValue(
			(double)_bqEventRepository.countEventSessions(
				Long.valueOf(searchQueryContext.getChannelId()),
				searchQueryContext.getKeywords(),
				timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(), _timeZoneDog.getTimeZoneId(),
				userIds));

		eventMetric.setTotalSessionsMetric(totalSessionsMetric);

		return eventMetric;
	}

	@Autowired
	private BQEventRepository _bqEventRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}