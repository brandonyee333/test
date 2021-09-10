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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.graphql.GraphQLType;

/**
 * @author Leslie Wong
 */
@GraphQLType("BlockedCustomEventDefinition")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("blocked-custom-event-definition")
public class BlockedCustomEventDefinitionDTO {

	public BlockedCustomEventDefinitionDTO() {
	}

	public BlockedCustomEventDefinitionDTO(EventDefinition eventDefinition) {
		if (eventDefinition.getBlockedLastSeenDate() != null) {
			_blockedLastSeenDate = DateUtil.toUTCString(
				eventDefinition.getBlockedLastSeenDate());
		}

		_blockedLastSeenURL = eventDefinition.getBlockedLastSeenURL();
		_hidden = eventDefinition.isHidden();
		_id = String.valueOf(eventDefinition.getId());
		_name = eventDefinition.getName();
	}

	@GraphQLProperty("lastSeenDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("lastSeenDate")
	public String getBlockedLastSeenDate() {
		return _blockedLastSeenDate;
	}

	@GraphQLProperty("lastSeenURL")
	@JsonProperty("lastSeenURL")
	public String getBlockedLastSeenURL() {
		return _blockedLastSeenURL;
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonProperty("name")
	public String getName() {
		return _name;
	}

	@JsonProperty("hidden")
	public boolean isHidden() {
		return _hidden;
	}

	private String _blockedLastSeenDate;
	private String _blockedLastSeenURL;
	private boolean _hidden;
	private String _id;
	private String _name;

}