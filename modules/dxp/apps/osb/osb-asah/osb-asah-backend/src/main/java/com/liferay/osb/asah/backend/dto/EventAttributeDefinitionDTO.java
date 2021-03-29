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

package com.liferay.osb.asah.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.model.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.EventDefinitionEventAttributeDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Leslie Wong
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("event-attribute-definition")
public class EventAttributeDefinitionDTO {

	public EventAttributeDefinitionDTO() {
	}

	public EventAttributeDefinitionDTO(
		EventAttributeDefinition eventAttributeDefinition) {

		_dataType = eventAttributeDefinition.getDataType();
		_description = eventAttributeDefinition.getDescription();
		_displayName = eventAttributeDefinition.getDisplayName();
		_id = String.valueOf(eventAttributeDefinition.getId());
		_name = eventAttributeDefinition.getName();

		List<EventDefinitionEventAttributeDefinition>
			eventDefinitionEventAttributeDefinitions = new ArrayList<>(
				eventAttributeDefinition.
					getEventDefinitionEventAttributeDefinitions());

		Collections.sort(
			eventDefinitionEventAttributeDefinitions,
			Comparator.comparing(
				EventDefinitionEventAttributeDefinition::getEventDefinitionId));

		EventDefinitionEventAttributeDefinition
			eventDefinitionEventAttributeDefinition =
				eventDefinitionEventAttributeDefinitions.get(0);

		_sampleValue = eventDefinitionEventAttributeDefinition.getSampleValue();
	}

	public EventAttributeDefinitionDTO(
		EventAttributeDefinition eventAttributeDefinition,
		Long eventDefinitionId) {

		this(eventAttributeDefinition);

		Set<EventDefinitionEventAttributeDefinition>
			eventDefinitionEventAttributeDefinitions =
				eventAttributeDefinition.
					getEventDefinitionEventAttributeDefinitions();

		for (EventDefinitionEventAttributeDefinition
				eventDefinitionEventAttributeDefinition :
					eventDefinitionEventAttributeDefinitions) {

			if (Objects.equals(
					eventDefinitionId,
					eventDefinitionEventAttributeDefinition.
						getEventDefinitionId())) {

				_sampleValue =
					eventDefinitionEventAttributeDefinition.getSampleValue();

				break;
			}
		}
	}

	@JsonProperty("dataType")
	public EventAttributeDefinition.DataType getDataType() {
		return _dataType;
	}

	@JsonProperty("description")
	public String getDescription() {
		return _description;
	}

	@JsonProperty("displayName")
	public String getDisplayName() {
		return _displayName;
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonProperty("name")
	public String getName() {
		return _name;
	}

	@JsonProperty("sampleValue")
	public String getSampleValue() {
		return _sampleValue;
	}

	private EventAttributeDefinition.DataType _dataType;
	private String _description;
	private String _displayName;
	private String _id;
	private String _name;
	private String _sampleValue;

}