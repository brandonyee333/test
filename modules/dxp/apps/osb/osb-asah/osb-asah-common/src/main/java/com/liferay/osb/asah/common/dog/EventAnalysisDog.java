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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.entity.EventAnalysis;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.BreakdownItem;
import com.liferay.osb.asah.common.model.DateGrouping;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.EventAnalysisResult;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQEventRepository;
import com.liferay.osb.asah.common.repository.EventAnalysisRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahDuplicateNameException;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahNameException;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.StringUtil;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author Matthew Kong
 */
@Component
public class EventAnalysisDog {

	public EventAnalysis addEventAnalysis(
		AnalysisType analysisType, Long channelId, boolean compareToPrevious,
		List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters, Long eventDefinitionId,
		String name, LocalDate rangeEndLocalDate, Integer rangeKey,
		LocalDate rangeStartLocalDate, Long userId, String userName) {

		_validateEventAnalysisName(channelId, null, name);
		_validateEventAnalysisBreakdowns(eventAnalysisBreakdowns);

		Date date = new Date();

		EventAnalysis eventAnalysis = new EventAnalysis();

		try {
			eventAnalysis.setEventAnalysisBreakdownJSONArray(
				_objectMapper.convertValue(
					eventAnalysisBreakdowns, JSONArray.class));
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		eventAnalysis.setChannelId(channelId);
		eventAnalysis.setCompareToPrevious(compareToPrevious);
		eventAnalysis.setCreateDate(date);
		eventAnalysis.setCreatedByUserId(userId);
		eventAnalysis.setCreatedByUserName(userName);
		eventAnalysis.setEventAnalysisType(analysisType.name());
		eventAnalysis.setEventDefinitionId(eventDefinitionId);

		try {
			eventAnalysis.setEventAnalysisFilterJSONArray(
				_objectMapper.convertValue(
					eventAnalysisFilters, JSONArray.class));
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		eventAnalysis.setModifiedByUserId(userId);
		eventAnalysis.setModifiedByUserName(userName);
		eventAnalysis.setModifiedDate(date);
		eventAnalysis.setName(name);
		eventAnalysis.setRangeEndLocalDate(rangeEndLocalDate);
		eventAnalysis.setRangeKey(rangeKey);
		eventAnalysis.setRangeStartLocalDate(rangeStartLocalDate);

		return _eventAnalysisRepository.save(eventAnalysis);
	}

	public void deleteEventAnalyses(List<Long> eventAnalysisIds) {
		_eventAnalysisRepository.deleteByIdIn(new HashSet<>(eventAnalysisIds));
	}

	public EventAnalysis getEventAnalysis(Long eventAnalysisId) {
		Optional<EventAnalysis> eventAnalysisOptional =
			_eventAnalysisRepository.findById(eventAnalysisId);

		return eventAnalysisOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no event analysis with ID " + eventAnalysisId));
	}

	public Page<EventAnalysis> getEventAnalysisPage(
		Long channelId, @Nullable String keywords, int page, int size,
		Sort sort) {

		_validate(sort);

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_eventAnalysisRepository.searchEventAnalyses(
				channelId, keywords, pageRequest),
			pageRequest,
			() -> _eventAnalysisRepository.countEventAnalyses(
				channelId, keywords));
	}

	public EventAnalysisResult getEventAnalysisResult(
		AnalysisType analysisType, Long channelId, boolean compareToPrevious,
		List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters, Long eventDefinitionId,
		int page, int size, TimeRange timeRange) {

		_validateEventAnalysisBreakdowns(eventAnalysisBreakdowns);

		EventAnalysisResult eventAnalysisResult = new EventAnalysisResult();

		eventAnalysisResult.setPage(page);

		String projectId = ProjectIdThreadLocal.getProjectId();

		CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(
			() -> {
				ProjectIdThreadLocal.setProjectId(projectId);

				eventAnalysisResult.setValue(
					_getAnalysisCount(
						analysisType, channelId, eventAnalysisFilters,
						eventDefinitionId, timeRange));
			},
			_executorService);

		if (compareToPrevious) {
			completableFuture = CompletableFuture.allOf(
				completableFuture,
				CompletableFuture.runAsync(
					() -> {
						ProjectIdThreadLocal.setProjectId(projectId);

						eventAnalysisResult.setPreviousValue(
							_getAnalysisCount(
								analysisType, channelId, eventAnalysisFilters,
								eventDefinitionId,
								timeRange.getPreviousTimeRange()));
					},
					_executorService));
		}

		completableFuture = CompletableFuture.allOf(
			completableFuture.thenRunAsync(
				() -> {
					ProjectIdThreadLocal.setProjectId(projectId);

					eventAnalysisResult.setBreakdownItems(
						_getBreakdownItems(
							analysisType, channelId, compareToPrevious, 0,
							eventAnalysisResult.getValue(),
							eventAnalysisBreakdowns, eventAnalysisFilters,
							eventDefinitionId, PageRequest.of(page, size), null,
							eventAnalysisResult.getPreviousValue(), timeRange));
				},
				_executorService),
			CompletableFuture.runAsync(
				() -> {
					ProjectIdThreadLocal.setProjectId(projectId);

					eventAnalysisResult.setCount(
						_getTotalPagesCount(
							channelId, eventAnalysisBreakdowns,
							eventAnalysisFilters, eventDefinitionId,
							timeRange));
				},
				_executorService));

		completableFuture.join();

		return eventAnalysisResult;
	}

	public EventAnalysis updateEventAnalysis(
		AnalysisType analysisType, Long channelId, boolean compareToPrevious,
		List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters, Long eventAnalysisId,
		Long eventDefinitionId, String name, LocalDate rangeEndLocalDate,
		Integer rangeKey, LocalDate rangeStartLocalDate, Long userId,
		String userName) {

		_validateEventAnalysisName(channelId, eventAnalysisId, name);
		_validateEventAnalysisBreakdowns(eventAnalysisBreakdowns);

		EventAnalysis eventAnalysis = getEventAnalysis(eventAnalysisId);

		try {
			eventAnalysis.setEventAnalysisBreakdownJSONArray(
				_objectMapper.convertValue(
					eventAnalysisBreakdowns, JSONArray.class));
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		eventAnalysis.setChannelId(channelId);
		eventAnalysis.setCompareToPrevious(compareToPrevious);
		eventAnalysis.setEventAnalysisType(analysisType.name());
		eventAnalysis.setEventDefinitionId(eventDefinitionId);

		try {
			eventAnalysis.setEventAnalysisFilterJSONArray(
				_objectMapper.convertValue(
					eventAnalysisFilters, JSONArray.class));
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		eventAnalysis.setModifiedByUserId(userId);
		eventAnalysis.setModifiedByUserName(userName);
		eventAnalysis.setModifiedDate(new Date());
		eventAnalysis.setName(name);
		eventAnalysis.setRangeEndLocalDate(rangeEndLocalDate);
		eventAnalysis.setRangeKey(rangeKey);
		eventAnalysis.setRangeStartLocalDate(rangeStartLocalDate);

		return _eventAnalysisRepository.save(eventAnalysis);
	}

	private List<BreakdownItem> _createBreakdownItems(
		AnalysisType analysisType, Map<Object, Number> bqEventPropertyValues,
		Long channelId, boolean compareToPrevious,
		EventAnalysisBreakdown eventAnalysisBreakdown,
		List<EventAnalysisFilter> eventAnalysisFilters, Long eventDefinitionId,
		String eventDefinitionName, boolean lastBreakdown,
		BreakdownItem parentBreakdownItem, TimeRange timeRange) {

		List<BreakdownItem> breakdownItems = new ArrayList<>();

		for (Map.Entry<Object, Number> entry :
				bqEventPropertyValues.entrySet()) {

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
			return _bqEventRepository.getAverageBQEventCountPerIndividual(
				channelId, eventAnalysisFilters, eventDefinitionId,
				timeRange.getEndDate(), timeRange.getStartDate(),
				_timeZoneDog.getTimeZoneId());
		}

		if (analysisType.equals(AnalysisType.TOTAL)) {
			return _bqEventRepository.countTotalBQEvents(
				channelId, eventAnalysisFilters, eventDefinitionId,
				timeRange.getEndDate(), timeRange.getStartDate(),
				_timeZoneDog.getTimeZoneId());
		}

		if (analysisType.equals(AnalysisType.UNIQUE)) {
			return _bqEventRepository.countUniqueIndividuals(
				channelId, eventAnalysisFilters, eventDefinitionId,
				timeRange.getEndDate(), timeRange.getStartDate(),
				_timeZoneDog.getTimeZoneId());
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
			long binFloor = Math.abs(Long.parseLong(key));
			Number binSize = eventAnalysisBreakdown.getBinSize();

			return binFloor + " - " + (binFloor + binSize.longValue() - 1000);
		}

		if (dataType.equals(EventAttributeDefinition.DataType.NUMBER)) {
			int binFloor = Math.round(Float.parseFloat(key));
			Number binSize = eventAnalysisBreakdown.getBinSize();

			int binCeil = binFloor + binSize.intValue() - 1;

			if (binFloor < 0) {
				String name = "(" + binFloor + ") - ";

				if (binCeil < 0) {
					return name + "(" + binCeil + ")";
				}

				return name + binCeil;
			}

			return binFloor + " - " + binCeil;
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
			analysisType,
			_bqEventRepository.getBQEventPropertyValues(
				analysisType, parentBreakdownItem, channelId,
				eventAnalysisBreakdown, eventAnalysisFilters, eventDefinitionId,
				pageable, timeRange.getEndDate(), timeRange.getStartDate(),
				_timeZoneDog.getTimeZoneId()),
			channelId, compareToPrevious, eventAnalysisBreakdown,
			eventAnalysisFilters, eventDefinitionId,
			eventDefinition.getDisplayName(), lastBreakdown,
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
				eventAnalysisBreakdown.getDataType(),
				eventAnalysisBreakdown.getDescription(),
				eventAnalysisBreakdown.getDisplayName(), "eq",
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
				eventAnalysisBreakdown.getDataType(),
				eventAnalysisBreakdown.getDescription(),
				eventAnalysisBreakdown.getDisplayName(), "dateGrouping",
				Arrays.asList(dateGrouping.toString(), value));
		}

		if (dataType.equals(EventAttributeDefinition.DataType.DURATION) ||
			dataType.equals(EventAttributeDefinition.DataType.NUMBER)) {

			return new EventAnalysisFilter(
				eventAnalysisBreakdown.getAttributeId(),
				eventAnalysisBreakdown.getAttributeType(),
				eventAnalysisBreakdown.getDataType(),
				eventAnalysisBreakdown.getDescription(),
				eventAnalysisBreakdown.getDisplayName(), "bin",
				Arrays.asList(
					String.valueOf(eventAnalysisBreakdown.getBinSize()),
					value));
		}

		return new EventAnalysisFilter(
			eventAnalysisBreakdown.getAttributeId(),
			eventAnalysisBreakdown.getAttributeType(),
			eventAnalysisBreakdown.getDataType(),
			eventAnalysisBreakdown.getDescription(),
			eventAnalysisBreakdown.getDisplayName(), "eq",
			Collections.singletonList(value));
	}

	private long _getTotalPagesCount(
		Long channelId, List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters, Long eventDefinitionId,
		TimeRange timeRange) {

		if (CollectionUtils.isEmpty(eventAnalysisBreakdowns)) {
			return 1;
		}

		return _bqEventRepository.getBQEventPropertyValuesCount(
			channelId, eventAnalysisBreakdowns.get(0), eventAnalysisFilters,
			eventDefinitionId, timeRange.getEndDate(), timeRange.getStartDate(),
			_timeZoneDog.getTimeZoneId());
	}

	private void _validate(Sort sort) {
		String sortColumn = sort.getColumn();

		if (Objects.equals(sortColumn, "createdByUserName") ||
			Objects.equals(sortColumn, "name")) {

			return;
		}

		throw new OSBAsahException(
			HttpStatus.BAD_REQUEST,
			"Unable to sort event analysis by " + sortColumn);
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
						HttpStatus.BAD_REQUEST, "Date grouping is null");
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

	private void _validateEventAnalysisName(
		Long channelId, @Nullable Long eventAnalysisId, String name) {

		if (StringUtils.isBlank(name)) {
			throw new OSBAsahNameException();
		}

		Optional<EventAnalysis> eventAnalysisOptional =
			_eventAnalysisRepository.findByChannelIdAndNameIgnoreCase(
				channelId, name);

		EventAnalysis eventAnalysis = eventAnalysisOptional.orElse(null);

		if ((eventAnalysis != null) &&
			!Objects.equals(eventAnalysis.getId(), eventAnalysisId)) {

			throw new OSBAsahDuplicateNameException();
		}
	}

	private static final Log _log = LogFactory.getLog(EventAnalysisDog.class);

	@Autowired
	private BQEventRepository _bqEventRepository;

	@Autowired
	private EventAnalysisRepository _eventAnalysisRepository;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	private final ExecutorService _executorService =
		Executors.newFixedThreadPool(4);

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}