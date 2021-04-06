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
import com.liferay.osb.asah.common.model.BlockedEventDefinition;
import com.liferay.osb.asah.common.model.EventDefinition;

/**
 * @author Leslie Wong
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("blocked-custom-event-definition")
public class BlockedCustomEventDefinitionDTO {

	public BlockedCustomEventDefinitionDTO() {
	}

	public BlockedCustomEventDefinitionDTO(EventDefinition eventDefinition) {
		_id = String.valueOf(eventDefinition.getId());

		BlockedEventDefinition blockedEventDefinition =
			eventDefinition.getBlockedEventDefinition();

		_lastSeenDate = DateUtil.toUTCString(
			blockedEventDefinition.getLastSeenDate());
		_lastSeenURL = blockedEventDefinition.getLastSeenURL();

		_name = eventDefinition.getName();
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("lastSeenDate")
	public String getLastSeenDate() {
		return _lastSeenDate;
	}

	@JsonProperty("lastSeenURL")
	public String getLastSeenURL() {
		return _lastSeenURL;
	}

	@JsonProperty("name")
	public String getName() {
		return _name;
	}

	private String _id;
	private String _lastSeenDate;
	private String _lastSeenURL;
	private String _name;

}