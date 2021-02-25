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
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class EventDefinitionDog {

	public EventDefinition addEventDefinition(
		String description, String displayName, String name, String type) {

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

	public Long countEventDefinitions(String eventType) {
		return _eventDefinitionRepository.countByType(eventType);
	}

	public EventDefinition fetchEventDefinitionByName(String name) {
		return _eventDefinitionRepository.findByName(name);
	}

	public List<EventDefinition> getEventDefinitions(
		String eventType, int page, int size) {

		if (eventType != null) {
			return _eventDefinitionRepository.findByType(
				PageRequest.of(
					page, size,
					Sort.by(Collections.singletonList(Sort.Order.asc("name")))),
				eventType);
		}

		return _eventDefinitionRepository.findAll(
			PageRequest.of(
				page, size,
				Sort.by(Collections.singletonList(Sort.Order.asc("name")))));
	}

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

}