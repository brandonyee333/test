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
import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.graphql.GraphQLType;
import com.liferay.osb.asah.common.model.Tuple2;
import com.liferay.osb.asah.common.model.UserSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@GraphQLType("UserSession")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("userSessions")
public class UserSessionDTO {

	public UserSessionDTO(
		Map<String, Long> eventAttributeDefinitionIds,
		List<Tuple2<Event, EventDefinition>> tuple2s, UserSession userSession) {

		_browserName = userSession.getBrowserName();
		_completeDate = userSession.getCompleteDate();
		_contentLanguageId = userSession.getContentLanguageId();
		_createDate = userSession.getDate();
		_devicePixelRatio = userSession.getDevicePixelRatio();
		_deviceType = userSession.getDeviceType();
		_languageId = userSession.getLanguageId();
		_screenHeight = userSession.getScreenHeight();
		_screenWidth = userSession.getScreenWidth();
		_timezoneOffset = userSession.getTimezoneOffset();
		_userAgent = userSession.getUserAgent();

		tuple2s.forEach(
			tuple -> _eventDTOs.add(
				new EventDTO(
					tuple.getT1(), eventAttributeDefinitionIds,
					tuple.getT2(
					).getName())));
	}

	public String getBrowserName() {
		return _browserName;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getCompleteDate() {
		if (_completeDate == null) {
			return null;
		}

		return new Date(_completeDate.getTime());
	}

	public String getContentLanguageId() {
		return _contentLanguageId;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	public String getDevicePixelRatio() {
		return _devicePixelRatio;
	}

	public String getDeviceType() {
		return _deviceType;
	}

	@GraphQLProperty("events")
	@JsonProperty("events")
	public List<EventDTO> getEventDTOs() {
		return _eventDTOs;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public String getScreenHeight() {
		return _screenHeight;
	}

	public String getScreenWidth() {
		return _screenWidth;
	}

	public String getTimezoneOffset() {
		return _timezoneOffset;
	}

	public String getUserAgent() {
		return _userAgent;
	}

	private final String _browserName;
	private final Date _completeDate;
	private final String _contentLanguageId;
	private final Date _createDate;
	private final String _devicePixelRatio;
	private final String _deviceType;
	private final List<EventDTO> _eventDTOs = new ArrayList<>();
	private final String _languageId;
	private final String _screenHeight;
	private final String _screenWidth;
	private final String _timezoneOffset;
	private final String _userAgent;

}