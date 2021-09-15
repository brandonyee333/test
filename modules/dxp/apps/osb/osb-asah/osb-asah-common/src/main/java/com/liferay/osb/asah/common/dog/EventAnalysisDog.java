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

import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.BreakdownItem;
import com.liferay.osb.asah.common.model.DateGrouping;
import com.liferay.osb.asah.common.model.EventAnalysis;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.EventRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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

		_validateEventAnalysisBreakdowns(eventAnalysisBreakdowns);

		EventAnalysis eventAnalysis = new EventAnalysis();

		eventAnalysis.setCount(
			_getTotalPageCount(
				channelId, eventAnalysisBreakdowns, eventAnalysisFilters,
				eventDefinitionId, timeRange));
		eventAnalysis.setPage(page);
		eventAnalysis.setValue(
			_getAnalysisCount(
				analysisType, channelId, eventAnalysisFilters,
				eventDefinitionId, timeRange));

		if (compareToPrevious) {
			eventAnalysis.setPreviousValue(
				_getAnalysisCount(
					analysisType, channelId, eventAnalysisFilters,
					eventDefinitionId, timeRange.getPreviousTimeRange()));
		}

		eventAnalysis.setBreakdownItems(
			_getBreakdownItems(
				analysisType, channelId, compareToPrevious, 0,
				eventAnalysis.getValue(), eventAnalysisBreakdowns,
				eventAnalysisFilters, eventDefinitionId,
				PageRequest.of(page, size), null,
				eventAnalysis.getPreviousValue(), timeRange));

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
				_getEventAnalysisFilter(
					eventAnalysisBreakdown, String.valueOf(entry.getKey())));

			breakdownItem.setEventAnalysisFilters(
				breakdownEventAnalysisFilters);

			breakdownItem.setLeafNode(false);
			breakdownItem.setName(
				_getBreakdownItemName(
					eventAnalysisBreakdown, String.valueOf(entry.getKey())));

			if (compareToPrevious) {
				List<EventAnalysisFilter> previousEventAnalysisFilters =
					new ArrayList<>(eventAnalysisFilters);

				previousEventAnalysisFilters.addAll(
					breakdownItem.getEventAnalysisFilters());

				breakdownItem.setPreviousValue(
					_getAnalysisCount(
						analysisType, channelId, previousEventAnalysisFilters,
						eventDefinitionId, timeRange.getPreviousTimeRange()));
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

	private Number _getAnalysisCount(
		AnalysisType analysisType, Long channelId,
		List<EventAnalysisFilter> eventAnalysisFilters, Long eventDefinitionId,
		TimeRange timeRange) {

		if (analysisType.equals(AnalysisType.AVERAGE)) {
			return _eventRepository.getAverageEventCountPerIndividual(
				channelId, eventAnalysisFilters, eventDefinitionId,
				timeRange.getEndDate(), timeRange.getStartDate());
		}

		if (analysisType.equals(AnalysisType.TOTAL)) {
			return _eventRepository.countTotalEvents(
				channelId, eventAnalysisFilters, eventDefinitionId,
				timeRange.getEndDate(), timeRange.getStartDate());
		}

		if (analysisType.equals(AnalysisType.UNIQUE)) {
			return _eventRepository.countUniqueIndividuals(
				channelId, eventAnalysisFilters, eventDefinitionId,
				timeRange.getEndDate(), timeRange.getStartDate());
		}

		return null;
	}

	private String _getBreakdownItemName(
		EventAnalysisBreakdown eventAnalysisBreakdown, String key) {

		if (StringUtil.isNull(key)) {
			return "undefined";
		}

		EventAttributeDefinition.DataType dataType =
			eventAnalysisBreakdown.getDataType();

		if (dataType.equals(EventAttributeDefinition.DataType.DURATION)) {
			long binFloor = Long.parseLong(key);
			Number binSize = eventAnalysisBreakdown.getBinSize();

			return binFloor + " to " + (binFloor + binSize.longValue());
		}

		if (dataType.equals(EventAttributeDefinition.DataType.NUMBER)) {
			float binFloor = Float.parseFloat(key);
			Number binSize = eventAnalysisBreakdown.getBinSize();

			return binFloor + " to " + (binFloor + binSize.floatValue());
		}

		return key;
	}

	private List<BreakdownItem> _getBreakdownItems(
		AnalysisType analysisType, Long channelId, boolean compareToPrevious,
		int count, Number eventAnalysisValue,
		List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters, Long eventDefinitionId,
		Pageable pageable, BreakdownItem parentBreakdownItem,
		Number previousEventAnalysisValue, TimeRange timeRange) {

		EventDefinition eventDefinition =
			_eventDefinitionDog.getEventDefinition(eventDefinitionId);

		if (eventAnalysisBreakdowns.isEmpty()) {
			return _createLeafNodeBreakdownItems(
				eventDefinition.getDisplayName(), previousEventAnalysisValue,
				eventAnalysisValue);
		}

		EventAnalysisBreakdown eventAnalysisBreakdown =
			eventAnalysisBreakdowns.get(count);

		boolean lastBreakdown = false;

		if (count == (eventAnalysisBreakdowns.size() - 1)) {
			lastBreakdown = true;
		}

		List<BreakdownItem> breakdownItems = _createBreakdownItems(
			analysisType, channelId, compareToPrevious, eventAnalysisBreakdown,
			eventAnalysisFilters,
			_eventRepository.getEventAttributeValues(
				analysisType, parentBreakdownItem, channelId,
				eventAnalysisBreakdown, eventAnalysisFilters, eventDefinitionId,
				pageable, timeRange.getEndDate(), timeRange.getStartDate()),
			eventDefinitionId, eventDefinition.getDisplayName(), lastBreakdown,
			parentBreakdownItem, timeRange);

		if (!lastBreakdown) {
			for (BreakdownItem breakdownItem : breakdownItems) {
				breakdownItem.setBreakdownItems(
					_getBreakdownItems(
						analysisType, channelId, compareToPrevious, count + 1,
						eventAnalysisValue, eventAnalysisBreakdowns,
						eventAnalysisFilters, eventDefinitionId,
						PageRequest.of(0, 5), breakdownItem,
						previousEventAnalysisValue, timeRange));
			}
		}

		return breakdownItems;
	}

	private EventAnalysisFilter _getEventAnalysisFilter(
		EventAnalysisBreakdown eventAnalysisBreakdown, String value) {

		if (StringUtil.isNull(value)) {
			return new EventAnalysisFilter(
				eventAnalysisBreakdown.getAttributeId(),
				eventAnalysisBreakdown.getAttributeType(),
				eventAnalysisBreakdown.getDataType(), "eq",
				Collections.singletonList(null));
		}

		EventAttributeDefinition.DataType dataType =
			eventAnalysisBreakdown.getDataType();

		if (dataType.equals(EventAttributeDefinition.DataType.DATE)) {
			DateGrouping dateGrouping =
				eventAnalysisBreakdown.getDateGrouping();

			return new EventAnalysisFilter(
				eventAnalysisBreakdown.getAttributeId(),
				eventAnalysisBreakdown.getAttributeType(),
				eventAnalysisBreakdown.getDataType(), "dateGrouping",
				Arrays.asList(dateGrouping.toString(), value));
		}

		if (dataType.equals(EventAttributeDefinition.DataType.DURATION) ||
			dataType.equals(EventAttributeDefinition.DataType.NUMBER)) {

			return new EventAnalysisFilter(
				eventAnalysisBreakdown.getAttributeId(),
				eventAnalysisBreakdown.getAttributeType(),
				eventAnalysisBreakdown.getDataType(), "bin",
				Arrays.asList(
					String.valueOf(eventAnalysisBreakdown.getBinSize()),
					value));
		}

		return new EventAnalysisFilter(
			eventAnalysisBreakdown.getAttributeId(),
			eventAnalysisBreakdown.getAttributeType(),
			eventAnalysisBreakdown.getDataType(), "eq",
			Collections.singletonList(value));
	}

	private long _getTotalPageCount(
		Long channelId, List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters, Long eventDefinitionId,
		TimeRange timeRange) {

		if (CollectionUtils.isEmpty(eventAnalysisBreakdowns)) {
			return 1;
		}

		return _eventRepository.getEventAttributeValuesCount(
			channelId, eventAnalysisBreakdowns.get(0), eventAnalysisFilters,
			eventDefinitionId, timeRange.getEndDate(),
			timeRange.getStartDate());
	}

	private void _validateEventAnalysisBreakdowns(
		List<EventAnalysisBreakdown> eventAnalysisBreakdowns) {

		for (EventAnalysisBreakdown eventAnalysisBreakdown :
				eventAnalysisBreakdowns) {

			EventAttributeDefinition.DataType dataType =
				eventAnalysisBreakdown.getDataType();

			if (dataType.equals(EventAttributeDefinition.DataType.DATE)) {
				if (eventAnalysisBreakdown.getDateGrouping() == null) {
					throw new OSBAsahException(
						HttpStatus.BAD_REQUEST, "Date grouping cannot be null");
				}
			}
			else if (dataType.equals(
						EventAttributeDefinition.DataType.DURATION) ||
					 dataType.equals(
						 EventAttributeDefinition.DataType.NUMBER)) {

				Number binSize = eventAnalysisBreakdown.getBinSize();

				if ((binSize == null) || (binSize.doubleValue() <= 0)) {
					throw new OSBAsahException(
						HttpStatus.BAD_REQUEST, "Invalid bin size " + binSize);
				}
			}
		}
	}

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventRepository _eventRepository;

}