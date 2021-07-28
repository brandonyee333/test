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

import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.BreakdownItem;
import com.liferay.osb.asah.common.model.EventAnalysis;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.EventRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
		AnalysisType analysisType, Long channelId, boolean compareToPrevious,
		List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters, Long eventDefinitionId,
		int page, int size, TimeRange timeRange) {

		EventAnalysis eventAnalysis = new EventAnalysis();

		eventAnalysis.setCount(1);
		eventAnalysis.setPage(0);

		if (analysisType.equals(AnalysisType.AVERAGE)) {
			eventAnalysis.setValue(
				_eventRepository.getAverageEventCountPerIndividual(
					channelId, eventAnalysisFilters, eventDefinitionId,
					timeRange.getEndDate(), timeRange.getStartDate()));
		}
		else if (analysisType.equals(AnalysisType.TOTAL)) {
			eventAnalysis.setValue(
				_eventRepository.countTotalEvents(
					channelId, eventAnalysisFilters, eventDefinitionId,
					timeRange.getEndDate(), timeRange.getStartDate()));
		}
		else if (analysisType.equals(AnalysisType.UNIQUE)) {
			eventAnalysis.setValue(
				_eventRepository.countUniqueIndividuals(
					channelId, eventAnalysisFilters, eventDefinitionId,
					timeRange.getEndDate(), timeRange.getStartDate()));
		}

		if (compareToPrevious) {
			TimeRange previousTimeRange = timeRange.getPreviousTimeRange();

			if (analysisType.equals(AnalysisType.AVERAGE)) {
				eventAnalysis.setPreviousValue(
					_eventRepository.getAverageEventCountPerIndividual(
						channelId, eventAnalysisFilters, eventDefinitionId,
						previousTimeRange.getEndDate(),
						previousTimeRange.getStartDate()));
			}
			else if (analysisType.equals(AnalysisType.TOTAL)) {
				eventAnalysis.setPreviousValue(
					_eventRepository.countTotalEvents(
						channelId, eventAnalysisFilters, eventDefinitionId,
						previousTimeRange.getEndDate(),
						previousTimeRange.getStartDate()));
			}
			else if (analysisType.equals(AnalysisType.UNIQUE)) {
				eventAnalysis.setPreviousValue(
					_eventRepository.countUniqueIndividuals(
						channelId, eventAnalysisFilters, eventDefinitionId,
						previousTimeRange.getEndDate(),
						previousTimeRange.getStartDate()));
			}
		}

		eventAnalysis.setBreakdownItems(
			_getBreakdownItems(
				analysisType, channelId, compareToPrevious,
				eventAnalysis.getValue(), eventDefinitionId,
				eventAnalysisBreakdowns, eventAnalysisFilters,
				PageRequest.of(page, size), eventAnalysis.getPreviousValue(),
				timeRange));

		return eventAnalysis;
	}

	private List<BreakdownItem> _createBreakdownItems(
		AnalysisType analysisType, Long channelId, boolean compareToPrevious,
		EventAnalysisBreakdown eventAnalysisBreakdown,
		List<EventAnalysisFilter> eventAnalysisFilters,
		Map<Object, Number> eventAttributeValues, Long eventDefinitionId,
		String eventDefinitionName, boolean lastBreakdown,
		BreakdownItem parentBreakdownItem, TimeRange timeRange) {

		List<BreakdownItem> breakdownItems = new ArrayList<>();

		for (Map.Entry<Object, Number> entry :
				eventAttributeValues.entrySet()) {

			BreakdownItem breakdownItem = new BreakdownItem();

			List<EventAnalysisFilter> breakdownEventAnalysisFilters =
				new ArrayList<>();

			if (parentBreakdownItem != null) {
				breakdownEventAnalysisFilters.addAll(
					parentBreakdownItem.getEventAnalysisFilters());
			}

			breakdownEventAnalysisFilters.add(
				new EventAnalysisFilter(
					eventAnalysisBreakdown.getAttributeId(),
					eventAnalysisBreakdown.getAttributeType(),
					eventAnalysisBreakdown.getDataType(), "eq",
					Collections.singletonList(String.valueOf(entry.getKey()))));

			breakdownItem.setEventAnalysisFilters(
				breakdownEventAnalysisFilters);

			breakdownItem.setLeafNode(false);
			breakdownItem.setName(String.valueOf(entry.getKey()));

			if (compareToPrevious) {
				TimeRange previousTimeRange = timeRange.getPreviousTimeRange();

				List<EventAnalysisFilter> previousEventAnalysisFilters =
					new ArrayList<>(eventAnalysisFilters);

				previousEventAnalysisFilters.addAll(
					breakdownItem.getEventAnalysisFilters());

				if (analysisType.equals(AnalysisType.AVERAGE)) {
					breakdownItem.setPreviousValue(
						_eventRepository.getAverageEventCountPerIndividual(
							channelId, previousEventAnalysisFilters,
							eventDefinitionId, previousTimeRange.getEndDate(),
							previousTimeRange.getStartDate()));
				}
				else if (analysisType.equals(AnalysisType.TOTAL)) {
					breakdownItem.setPreviousValue(
						_eventRepository.countTotalEvents(
							channelId, previousEventAnalysisFilters,
							eventDefinitionId, previousTimeRange.getEndDate(),
							previousTimeRange.getStartDate()));
				}
				else if (analysisType.equals(AnalysisType.UNIQUE)) {
					breakdownItem.setPreviousValue(
						_eventRepository.countUniqueIndividuals(
							channelId, previousEventAnalysisFilters,
							eventDefinitionId, previousTimeRange.getEndDate(),
							previousTimeRange.getStartDate()));
				}
			}

			breakdownItem.setValue(entry.getValue());

			if (lastBreakdown) {
				breakdownItem.setBreakdownItems(
					_createLeafNodeBreakdownItems(
						eventDefinitionName, breakdownItem.getPreviousValue(),
						breakdownItem.getValue()));
			}

			breakdownItems.add(breakdownItem);
		}

		return breakdownItems;
	}

	private List<BreakdownItem> _createComparisonBreakdownItems(
		Number previousValue, Number value) {

		BreakdownItem allIndividualsBreakdownItem = new BreakdownItem();

		allIndividualsBreakdownItem.setName("All Individuals");
		allIndividualsBreakdownItem.setPreviousValue(previousValue);
		allIndividualsBreakdownItem.setValue(value);

		return Collections.singletonList(allIndividualsBreakdownItem);
	}

	private List<BreakdownItem> _createLeafNodeBreakdownItems(
		String eventDefinitionName, Number previousValue, Number value) {

		BreakdownItem eventDefinitionBreakdownItem = new BreakdownItem();

		eventDefinitionBreakdownItem.setBreakdownItems(
			_createComparisonBreakdownItems(previousValue, value));
		eventDefinitionBreakdownItem.setLeafNode(true);
		eventDefinitionBreakdownItem.setName(eventDefinitionName);
		eventDefinitionBreakdownItem.setPreviousValue(previousValue);
		eventDefinitionBreakdownItem.setValue(value);

		return Collections.singletonList(eventDefinitionBreakdownItem);
	}

	private List<BreakdownItem> _getBreakdownItems(
		AnalysisType analysisType, Long channelId, boolean compareToPrevious,
		Number eventAnalysisValue, Long eventDefinitionId,
		List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters, Pageable pageable,
		Number previousEventAnalysisValue, TimeRange timeRange) {

		EventDefinition eventDefinition =
			_eventDefinitionDog.getEventDefinition(eventDefinitionId);

		if (eventAnalysisBreakdowns.isEmpty()) {
			return _createLeafNodeBreakdownItems(
				eventDefinition.getDisplayName(), previousEventAnalysisValue,
				eventAnalysisValue);
		}

		EventAnalysisBreakdown eventAnalysisBreakdown =
			eventAnalysisBreakdowns.get(0);

		boolean lastBreakdown = false;

		if (eventAnalysisBreakdowns.size() == 1) {
			lastBreakdown = true;
		}

		List<BreakdownItem> breakdownItems = _createBreakdownItems(
			analysisType, channelId, compareToPrevious, eventAnalysisBreakdown,
			eventAnalysisFilters,
			_eventRepository.getEventAttributeValues(
				analysisType, null, channelId, eventAnalysisFilters,
				Long.valueOf(eventAnalysisBreakdown.getAttributeId()),
				eventDefinitionId, pageable, timeRange.getEndDate(),
				timeRange.getStartDate(), eventAnalysisBreakdown.getSortType()),
			eventDefinitionId, eventDefinition.getDisplayName(), lastBreakdown,
			null, timeRange);

		_setChildrenBreakdownItems(
			analysisType, breakdownItems, channelId, compareToPrevious, 1,
			eventDefinitionId, eventAnalysisBreakdowns, eventAnalysisFilters,
			eventDefinition.getDisplayName(), PageRequest.of(0, 5), timeRange);

		return breakdownItems;
	}

	private void _setChildrenBreakdownItems(
		AnalysisType analysisType, List<BreakdownItem> parentBreakdownItems,
		Long channelId, boolean compareToPrevious, int count,
		Long eventDefinitionId,
		List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters,
		String eventDefinitionName, Pageable pageable, TimeRange timeRange) {

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
				analysisType, channelId, compareToPrevious,
				eventAnalysisBreakdown, eventAnalysisFilters,
				_eventRepository.getEventAttributeValues(
					analysisType, parentBreakdownItem, channelId,
					eventAnalysisFilters,
					Long.valueOf(eventAnalysisBreakdown.getAttributeId()),
					eventDefinitionId, pageable, timeRange.getEndDate(),
					timeRange.getStartDate(),
					eventAnalysisBreakdown.getSortType()),
				eventDefinitionId, eventDefinitionName, lastBreakdown,
				parentBreakdownItem, timeRange);

			parentBreakdownItem.setBreakdownItems(breakdownItems);

			_setChildrenBreakdownItems(
				analysisType, breakdownItems, channelId, compareToPrevious,
				count + 1, eventDefinitionId, eventAnalysisBreakdowns,
				eventAnalysisFilters, eventDefinitionName, pageable, timeRange);
		}
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventRepository _eventRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}