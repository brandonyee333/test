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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.BreakdownItem;
import com.liferay.osb.asah.common.model.EventAnalysis;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.EventRepository;

import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class EventAnalysisDog {

	public EventAnalysis getEventAnalysis(
		AnalysisType analysisType, Long channelId,
		List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters, Long eventDefinitionId,
		int page, int size, TimeRange timeRange) {

		EventAnalysis eventAnalysis = new EventAnalysis();

		eventAnalysis.setCount(1);
		eventAnalysis.setPage(0);

		Date rangeEndDate = DateUtil.toDate(
			timeRange.getEndLocalDateTime(),
			ZoneId.of(_timeZoneDog.getTimeZoneId()));
		Date rangeStartDate = DateUtil.toDate(
			timeRange.getStartLocalDateTime(),
			ZoneId.of(_timeZoneDog.getTimeZoneId()));

		if (analysisType.equals(AnalysisType.AVERAGE)) {
			eventAnalysis.setValue(
				_eventRepository.getAverageEventCountPerIndividual(
					channelId, eventAnalysisFilters, eventDefinitionId,
					rangeEndDate, rangeStartDate));
		}
		else if (analysisType.equals(AnalysisType.TOTAL)) {
			eventAnalysis.setValue(
				_eventRepository.countTotalEvents(
					channelId, eventAnalysisFilters, eventDefinitionId,
					rangeEndDate, rangeStartDate));
		}
		else if (analysisType.equals(AnalysisType.UNIQUE)) {
			eventAnalysis.setValue(
				_eventRepository.countUniqueIndividuals(
					channelId, eventAnalysisFilters, eventDefinitionId,
					rangeEndDate, rangeStartDate));
		}

		eventAnalysis.setBreakdownItems(
			_getBreakdownItems(
				analysisType, channelId, eventAnalysis.getValue(),
				eventDefinitionId, eventAnalysisBreakdowns,
				eventAnalysisFilters, PageRequest.of(page, size), rangeEndDate,
				rangeStartDate));

		return eventAnalysis;
	}

	private List<BreakdownItem> _createBreakdownItems(
		EventAnalysisBreakdown eventAnalysisBreakdown,
		Map<Object, Number> eventAttributeValues, String eventDefinitionName,
		boolean lastBreakdown, BreakdownItem parentBreakdownItem) {

		List<BreakdownItem> breakdownItems = new ArrayList<>();

		for (Map.Entry<Object, Number> entry :
				eventAttributeValues.entrySet()) {

			BreakdownItem breakdownItem = new BreakdownItem();

			if (lastBreakdown) {
				breakdownItem.setBreakdownItems(
					_createLeafNodeBreakdownItems(
						eventDefinitionName, entry.getValue()));
			}

			List<Pair<String, Object>> eventAttributeDefinitionIdValuePairs =
				new ArrayList<>();

			if (parentBreakdownItem != null) {
				eventAttributeDefinitionIdValuePairs.addAll(
					parentBreakdownItem.
						getEventAttributeDefinitionIdValuePairs());
			}

			eventAttributeDefinitionIdValuePairs.add(
				Pair.of(
					eventAnalysisBreakdown.getAttributeId(), entry.getKey()));

			breakdownItem.setEventAttributeDefinitionIdValuePairs(
				eventAttributeDefinitionIdValuePairs);

			breakdownItem.setLeafNode(false);
			breakdownItem.setName(String.valueOf(entry.getKey()));
			breakdownItem.setValue(entry.getValue());

			breakdownItems.add(breakdownItem);
		}

		return breakdownItems;
	}

	private List<BreakdownItem> _createComparisonBreakdownItems(Number value) {
		BreakdownItem allIndividualsBreakdownItem = new BreakdownItem();

		allIndividualsBreakdownItem.setName("All Individuals");
		allIndividualsBreakdownItem.setValue(value);

		return Collections.singletonList(allIndividualsBreakdownItem);
	}

	private List<BreakdownItem> _createLeafNodeBreakdownItems(
		String eventDefinitionName, Number value) {

		BreakdownItem eventDefinitionBreakdownItem = new BreakdownItem();

		eventDefinitionBreakdownItem.setBreakdownItems(
			_createComparisonBreakdownItems(value));
		eventDefinitionBreakdownItem.setLeafNode(true);
		eventDefinitionBreakdownItem.setName(eventDefinitionName);

		eventDefinitionBreakdownItem.setValue(value);

		return Collections.singletonList(eventDefinitionBreakdownItem);
	}

	private List<BreakdownItem> _getBreakdownItems(
		AnalysisType analysisType, Long channelId, Number eventAnalysisValue,
		Long eventDefinitionId,
		List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters, Pageable pageable,
		Date rangeEndDate, Date rangeStartDate) {

		EventDefinition eventDefinition =
			_eventDefinitionDog.getEventDefinition(eventDefinitionId);

		if (eventAnalysisBreakdowns.isEmpty()) {
			return _createLeafNodeBreakdownItems(
				eventDefinition.getDisplayName(), eventAnalysisValue);
		}

		EventAnalysisBreakdown eventAnalysisBreakdown =
			eventAnalysisBreakdowns.get(0);

		boolean lastBreakdown = false;

		if (eventAnalysisBreakdowns.size() == 1) {
			lastBreakdown = true;
		}

		List<BreakdownItem> breakdownItems = _createBreakdownItems(
			eventAnalysisBreakdown,
			_eventRepository.getEventAttributeValues(
				analysisType, null, channelId, eventAnalysisFilters,
				Long.valueOf(eventAnalysisBreakdown.getAttributeId()),
				eventDefinitionId, pageable, rangeEndDate, rangeStartDate,
				eventAnalysisBreakdown.getSortType()),
			eventDefinition.getDisplayName(), lastBreakdown, null);

		_setChildrenBreakdownItems(
			analysisType, breakdownItems, channelId, 1, eventDefinitionId,
			eventAnalysisBreakdowns, eventAnalysisFilters,
			eventDefinition.getDisplayName(), PageRequest.of(0, 5),
			rangeEndDate, rangeStartDate);

		return breakdownItems;
	}

	private void _setChildrenBreakdownItems(
		AnalysisType analysisType, List<BreakdownItem> parentBreakdownItems,
		Long channelId, int count, Long eventDefinitionId,
		List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters,
		String eventDefinitionName, Pageable pageable, Date rangeEndDate,
		Date rangeStartDate) {

		if (count >= eventAnalysisBreakdowns.size()) {
			return;
		}

		boolean lastBreakdown = false;

		if (count == (eventAnalysisBreakdowns.size() - 1)) {
			lastBreakdown = true;
		}

		EventAnalysisBreakdown eventAnalysisBreakdown =
			eventAnalysisBreakdowns.get(count);

		for (BreakdownItem parentBreakdownItem : parentBreakdownItems) {
			List<BreakdownItem> breakdownItems = _createBreakdownItems(
				eventAnalysisBreakdown,
				_eventRepository.getEventAttributeValues(
					analysisType, parentBreakdownItem, channelId,
					eventAnalysisFilters,
					Long.valueOf(eventAnalysisBreakdown.getAttributeId()),
					eventDefinitionId, pageable, rangeEndDate, rangeStartDate,
					eventAnalysisBreakdown.getSortType()),
				eventDefinitionName, lastBreakdown, parentBreakdownItem);

			parentBreakdownItem.setBreakdownItems(breakdownItems);

			_setChildrenBreakdownItems(
				analysisType, breakdownItems, channelId, count + 1,
				eventDefinitionId, eventAnalysisBreakdowns,
				eventAnalysisFilters, eventDefinitionName, pageable,
				rangeEndDate, rangeStartDate);
		}
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventRepository _eventRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}