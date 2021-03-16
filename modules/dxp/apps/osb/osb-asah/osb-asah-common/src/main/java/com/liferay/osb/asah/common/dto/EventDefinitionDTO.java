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

import com.liferay.osb.asah.common.model.EventDefinition;

/**
 * @author Leslie Wong
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("event-definition")
public class EventDefinitionDTO {

	public EventDefinitionDTO() {
	}

	public EventDefinitionDTO(EventDefinition eventDefinition) {
		_description = eventDefinition.getDescription();
		_displayName = eventDefinition.getDisplayName();
		_id = String.valueOf(eventDefinition.getId());
		_name = eventDefinition.getName();
		_type = eventDefinition.getType();
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

	@JsonProperty("type")
	public EventDefinition.Type getType() {
		return _type;
	}

	private String _description;
	private String _displayName;
	private String _id;
	private String _name;
	private EventDefinition.Type _type;

}