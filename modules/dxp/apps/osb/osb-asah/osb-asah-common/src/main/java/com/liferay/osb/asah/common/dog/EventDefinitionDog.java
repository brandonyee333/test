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

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
public class EventDefinitionDog {

	public EventDefinition addEventDefinition(
		String description, String displayName, Date lastSeenDate,
		String lastSeenUrl, String name, EventDefinition.Type type) {

		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Event name is null");
		}

		EventDefinition eventDefinition = new EventDefinition();

		if ((type == EventDefinition.Type.CUSTOM) &&
			(countEventDefinitions(false, null, type) >=
				_EVENT_DEFINITION_THRESHOLD)) {

			eventDefinition.setBlocked(true);
			eventDefinition.setLastSeenDate(lastSeenDate);
			eventDefinition.setLastSeenUrl(lastSeenUrl);

			if (_log.isWarnEnabled()) {
				_log.warn(
					String.format(
						"Blocking event definition %s as limit is reached",
						name));
			}
		}

		eventDefinition.setDescription(description);
		eventDefinition.setDisplayName(displayName);
		eventDefinition.setName(name);
		eventDefinition.setType(type);

		return _eventDefinitionRepository.save(eventDefinition);
	}

	public Long countEventDefinitions(
		Boolean blocked, String keyword, EventDefinition.Type type) {

		return _eventDefinitionRepository.countEventDefinitions(
			blocked, keyword, type);
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

	public Page<EventDefinition> getEventDefinitionsPage(
		Boolean blocked, String keyword, int page, int size, Sort sort,
		EventDefinition.Type type) {

		_validate(sort);

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_eventDefinitionRepository.searchEventDefinitions(
				blocked, keyword, pageRequest, type),
			pageRequest,
			() -> _eventDefinitionRepository.countEventDefinitions(
				blocked, keyword, type));
	}

	public EventDefinition updateEventDefinition(
		String description, String displayName, Long eventDefinitionId,
		Date lastSeenDate, String lastSeenUrl) {

		EventDefinition eventDefinition = getEventDefinition(eventDefinitionId);

		if (StringUtils.isNotBlank(description)) {
			eventDefinition.setDescription(description);
		}

		if (StringUtils.isNotBlank(displayName)) {
			eventDefinition.setDisplayName(displayName);
		}

		if (lastSeenDate != null) {
			eventDefinition.setLastSeenDate(lastSeenDate);
		}

		if (StringUtils.isNotBlank(lastSeenUrl)) {
			eventDefinition.setLastSeenUrl(lastSeenUrl);
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

	private static final int _EVENT_DEFINITION_THRESHOLD = 100;

	private static final Log _log = LogFactory.getLog(EventDefinitionDog.class);

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

}