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
import com.liferay.osb.asah.common.model.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.EventAttributeDefinitionRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.ArrayUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class EventAttributeDefinitionDog {

	public EventAttributeDefinition addEventAttributeDefinition(
		String description, String displayName, Long eventDefinitionId,
		String name, String sampleValue) {

		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Event attribute name is null");
		}

		EventAttributeDefinition eventAttributeDefinition =
			new EventAttributeDefinition();

		eventAttributeDefinition.setDataType(getDataType(name, sampleValue));
		eventAttributeDefinition.setDescription(description);
		eventAttributeDefinition.setDisplayName(displayName);
		eventAttributeDefinition.setEventDefinitionEventAttributeDefinitions(
			Collections.singleton(
				new EventDefinitionEventAttributeDefinition(
					eventDefinitionId, sampleValue)));

		eventAttributeDefinition.setName(name);

		return _eventAttributeDefinitionRepository.save(
			eventAttributeDefinition);
	}

	public Long countEventAttributeDefinitions(String name) {
		return _eventAttributeDefinitionRepository.
			countEventAttributeDefinitions(name);
	}

	public EventAttributeDefinition fetchEventAttributeDefinitionByDisplayName(
		String displayName) {

		Optional<EventAttributeDefinition> eventAttributeDefinitionOptional =
			_eventAttributeDefinitionRepository.findByDisplayName(displayName);

		return eventAttributeDefinitionOptional.orElse(null);
	}

	public EventAttributeDefinition fetchEventAttributeDefinitionByName(
		String name) {

		return _eventAttributeDefinitionRepository.findByName(name);
	}

	public EventAttributeDefinition.DataType getDataType(
		String propertyName, String propertyValue) {

		if (StringUtils.isEmpty(propertyValue)) {
			throw new IllegalArgumentException(
				"Unable to determine data type for property " + propertyName);
		}

		if (_isBoolean(propertyValue)) {
			return EventAttributeDefinition.DataType.BOOLEAN;
		}

		if (_isDate(propertyValue)) {
			return EventAttributeDefinition.DataType.DATE;
		}

		if (NumberUtils.isParsable(propertyValue)) {
			String lowerCasePropertyName = propertyName.toLowerCase();

			if (lowerCasePropertyName.contains("duration") &&
				!propertyValue.startsWith("-")) {

				return EventAttributeDefinition.DataType.DURATION;
			}

			return EventAttributeDefinition.DataType.NUMBER;
		}

		return EventAttributeDefinition.DataType.STRING;
	}

	public EventAttributeDefinition getEventAttributeDefinition(
		Long eventAttributeDefinitionId) {

		Optional<EventAttributeDefinition> eventAttributeDefinitionOptional =
			_eventAttributeDefinitionRepository.findById(
				eventAttributeDefinitionId);

		return eventAttributeDefinitionOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no event attribute definition with ID " +
					eventAttributeDefinitionId));
	}

	public List<EventAttributeDefinition>
		getEventAttributeDefinitionsByEventDefinitionId(
			Long eventDefinitionId) {

		return _eventAttributeDefinitionRepository.findByEventDefinitionId(
			eventDefinitionId);
	}

	public Page<EventAttributeDefinition> getEventAttributeDefinitionsPage(
		String name, int page, int size, Sort sort) {

		_validate(sort);

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_eventAttributeDefinitionRepository.searchEventAttributeDefinitions(
				name, pageRequest),
			pageRequest,
			() ->
				_eventAttributeDefinitionRepository.
					countEventAttributeDefinitions(name));
	}

	public EventAttributeDefinition updateEventAttributeDefinition(
		EventAttributeDefinition.DataType dataType, String description,
		String displayName, Long eventAttributeDefinitionId,
		Set<EventDefinitionEventAttributeDefinition>
			eventDefinitionEventAttributeDefinitions,
		String name) {

		EventAttributeDefinition eventAttributeDefinition =
			getEventAttributeDefinition(eventAttributeDefinitionId);

		if (dataType != null) {
			eventAttributeDefinition.setDataType(dataType);
		}

		if (StringUtils.isNotBlank(description)) {
			eventAttributeDefinition.setDescription(description);
		}

		if (StringUtils.isNotBlank(displayName)) {
			EventAttributeDefinition eventAttributeDefinitionByDisplayName =
				fetchEventAttributeDefinitionByDisplayName(displayName);

			if ((eventAttributeDefinitionByDisplayName != null) &&
				!eventAttributeDefinitionId.equals(
					eventAttributeDefinitionByDisplayName.getId())) {

				throw new OSBAsahException(
					HttpStatus.BAD_REQUEST,
					String.format(
						"Display name %s is already used", displayName));
			}

			eventAttributeDefinition.setDisplayName(displayName);
		}

		if (StringUtils.isNotBlank(name)) {
			eventAttributeDefinition.setName(name);
		}

		if (eventDefinitionEventAttributeDefinitions != null) {
			eventAttributeDefinition.
				setEventDefinitionEventAttributeDefinitions(
					eventDefinitionEventAttributeDefinitions);
		}

		return _eventAttributeDefinitionRepository.save(
			eventAttributeDefinition);
	}

	private boolean _isBoolean(String value) {
		return ArrayUtil.contains(_BOOLEANS, value.toLowerCase());
	}

	private boolean _isDate(String value) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(
				DateUtil.PATTERN_ISO_8601);

			dateFormat.parse(value);

			return true;
		}
		catch (ParseException parseException) {
			return false;
		}
	}

	private void _validate(Sort sort) {
		String sortColumn = sort.getColumn();

		if (!Objects.equals(sortColumn, "name") &&
			!Objects.equals(sortColumn, "displayName")) {

			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Unable to sort event attribute definitions by " + sortColumn);
		}
	}

	private static final String[] _BOOLEANS = {"false", "true"};

	@Autowired
	private EventAttributeDefinitionRepository
		_eventAttributeDefinitionRepository;

}