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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.graphql.GraphQLType;

import java.util.List;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@GraphQLType("EventsByUserSession")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("eventsByUserSession")
public class EventsByUserSessionDTO {

	public EventsByUserSessionDTO(
		List<UserSessionDTO> userSessionDTOs, Integer totalEvents) {

		_userSessionDTOs = userSessionDTOs;
		_totalEvents = totalEvents;
	}

	public Integer getTotalEvents() {
		return _totalEvents;
	}

	@GraphQLProperty("userSessions")
	@JsonProperty("userSessions")
	public List<UserSessionDTO> getUserSessionDTOs() {
		return _userSessionDTOs;
	}

	private final Integer _totalEvents;
	private final List<UserSessionDTO> _userSessionDTOs;

}