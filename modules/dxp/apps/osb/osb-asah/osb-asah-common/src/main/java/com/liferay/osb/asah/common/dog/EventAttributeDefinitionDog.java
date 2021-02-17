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

import com.liferay.osb.asah.common.model.EventAttributeDefinition;
import com.liferay.osb.asah.common.repository.EventAttributeDefinitionRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class EventAttributeDefinitionDog {

	public EventAttributeDefinition addEventAttributeDefinition(
		String dataType, String description, String displayName,
		Set<Long> eventDefinitionIds, String name) {

		EventAttributeDefinition eventAttributeDefinition =
			new EventAttributeDefinition();

		eventAttributeDefinition.setDataType(dataType);
		eventAttributeDefinition.setDescription(description);
		eventAttributeDefinition.setDisplayName(displayName);
		eventAttributeDefinition.setEventDefinitionIds(eventDefinitionIds);
		eventAttributeDefinition.setName(name);

		return _eventAttributeDefinitionRepository.save(
			eventAttributeDefinition);
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

	public EventAttributeDefinition getEventAttributeDefinitionByName(
		String name) {

		return _eventAttributeDefinitionRepository.findByName(name);
	}

	public EventAttributeDefinition patchEventAttributeDefinition(
		String dataType, String description, String displayName,
		Long eventAttributeDefinitionId, Set<Long> eventDefinitionIds,
		String name) {

		EventAttributeDefinition eventAttributeDefinition =
			getEventAttributeDefinition(eventAttributeDefinitionId);

		if (StringUtils.isNotBlank(dataType)) {
			eventAttributeDefinition.setDataType(dataType);
		}

		if (StringUtils.isNotBlank(description)) {
			eventAttributeDefinition.setDescription(description);
		}

		if (StringUtils.isNotBlank(displayName)) {
			eventAttributeDefinition.setDisplayName(displayName);
		}

		if (StringUtils.isNotBlank(name)) {
			eventAttributeDefinition.setName(name);
		}

		if (eventDefinitionIds != null) {
			eventAttributeDefinition.setEventDefinitionIds(eventDefinitionIds);
		}

		return _eventAttributeDefinitionRepository.save(
			eventAttributeDefinition);
	}

	@Autowired
	private EventAttributeDefinitionRepository
		_eventAttributeDefinitionRepository;

}