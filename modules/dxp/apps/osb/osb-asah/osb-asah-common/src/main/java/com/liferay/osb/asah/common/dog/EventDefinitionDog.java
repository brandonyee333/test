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
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class EventDefinitionDog {

	public EventDefinition addEventDefinition(
		String description, String displayName, String name, String type) {

		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Event name cannot be null");
		}

		String key = ProjectIdThreadLocal.getProjectId() + "#" + name;

		if (_eventDefinitions.containsKey(key)) {
			throw new IllegalArgumentException(
				"Event name " + name + " already exists");
		}

		EventDefinition eventDefinition = new EventDefinition();

		eventDefinition.setDescription(description);
		eventDefinition.setDisplayName(displayName);
		eventDefinition.setName(name);
		eventDefinition.setType(type);

		eventDefinition = _eventDefinitionRepository.save(eventDefinition);

		if (eventDefinition != null) {
			_eventDefinitions.put(
				ProjectIdThreadLocal.getProjectId() + "#" +
					eventDefinition.getName(),
				eventDefinition);
		}

		return eventDefinition;
	}

	public EventDefinition getEventDefinitionByName(String name) {
		EventDefinition eventDefinition = _eventDefinitions.get(
			ProjectIdThreadLocal.getProjectId() + "#" + name);

		if (eventDefinition != null) {
			return eventDefinition;
		}

		return _eventDefinitionRepository.findByName(name);
	}

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

	private final Map<String, EventDefinition> _eventDefinitions =
		new HashMap<>();

}