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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.EventAnalysis;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.EventRepository;

import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class EventAnalysisDog {

	public EventAnalysis getEventAnalysis(
		AnalysisType analysisType, Long channelId, Long eventDefinitionId,
		TimeRange timeRange) {

		EventAnalysis eventAnalysis = new EventAnalysis();

		eventAnalysis.setCount(1);
		eventAnalysis.setPage(0);

		Date rangeEndDate = DateUtil.toDate(
			timeRange.getEndLocalDateTime(),
			ZoneId.of(TimeZoneDogUtil.getTimeZoneId()));
		Date rangeStartDate = DateUtil.toDate(
			timeRange.getStartLocalDateTime(),
			ZoneId.of(TimeZoneDogUtil.getTimeZoneId()));


		long totalEvents =
			_eventRepository.
				countByChannelIdAndEventDefinitionIdAndEventDateBetween(
					channelId, eventDefinitionId, rangeStartDate, rangeEndDate);

		eventAnalysis.setTotalEvents(totalEvents);

		if (analysisType.equals(AnalysisType.TOTAL)) {
			eventAnalysis.setValue(totalEvents);
		}
		else if (analysisType.equals(AnalysisType.UNIQUE)) {
			eventAnalysis.setValue(
				_eventRepository.countUniqueIndividuals(
					channelId, eventDefinitionId, rangeEndDate, rangeStartDate));
		}

		return eventAnalysis;
	}

	@Autowired
	private EventRepository _eventRepository;

}