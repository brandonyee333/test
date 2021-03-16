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

import com.liferay.osb.asah.common.model.EventDefinition;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class EventDefinitionDog {

	public EventDefinition addEventDefinition(
		String description, String displayName, String name,
		EventDefinition.Type type) {

		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Event name is null");
		}

		EventDefinition eventDefinition = new EventDefinition();

		eventDefinition.setDescription(description);
		eventDefinition.setDisplayName(displayName);
		eventDefinition.setName(name);
		eventDefinition.setType(type);

		return _eventDefinitionRepository.save(eventDefinition);
	}

	public Long countEventDefinitions(
		String keyword, EventDefinition.Type type) {

		return _eventDefinitionRepository.countEventDefinitions(keyword, type);
	}

	public EventDefinition fetchEventDefinitionByDisplayName(
		String displayName) {

		Optional<EventDefinition> eventDefinitionOptional =
			_eventDefinitionRepository.findByDisplayName(displayName);

		return eventDefinitionOptional.orElse(null);
	}

	public EventDefinition fetchEventDefinitionByName(String name) {
		Optional<EventDefinition> eventDefinitionOptional =
			_eventDefinitionRepository.findByName(name);

		return eventDefinitionOptional.orElse(null);
	}

	public EventDefinition getEventDefinition(Long eventDefinitionId) {
		Optional<EventDefinition> eventDefinitionOptional =
			_eventDefinitionRepository.findById(eventDefinitionId);

		return eventDefinitionOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no event definition with ID " + eventDefinitionId));
	}

	public List<EventDefinition> getEventDefinitions(
		String keyword, int page, int size, Sort sort,
		EventDefinition.Type type) {

		_validate(sort);

		return _eventDefinitionRepository.searchEventDefinitions(
			keyword, page, size, sort, type);
	}

	public EventDefinition updateEventDefinition(
		String description, String displayName, Long eventDefinitionId) {

		EventDefinition eventDefinition = getEventDefinition(eventDefinitionId);

		if (StringUtils.isNotBlank(description)) {
			eventDefinition.setDescription(description);
		}

		if (StringUtils.isNotBlank(displayName)) {
			eventDefinition.setDisplayName(displayName);
		}

		return _eventDefinitionRepository.save(eventDefinition);
	}

	private void _validate(Sort sort) {
		String sortColumn = sort.getColumn();

		if (!Objects.equals(sortColumn, "name") &&
			!Objects.equals(sortColumn, "displayName")) {

			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Unable to sort event definitions by " + sortColumn);
		}
	}

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

}