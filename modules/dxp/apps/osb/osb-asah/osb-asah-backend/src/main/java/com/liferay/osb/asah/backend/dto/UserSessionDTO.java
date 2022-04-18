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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQEvent;
import com.liferay.osb.asah.common.entity.BQSession;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.graphql.GraphQLType;
import com.liferay.osb.asah.common.model.Tuple2;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.StringUtil;

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
		List<Tuple2<BQEvent, EventDefinition>> tuple2s, BQSession bqSession,
		ObjectMapper objectMapper) {

		Tuple2<BQEvent, EventDefinition> tuple2 = tuple2s.get(0);

		BQEvent bqEvent = tuple2.getT1();

		try {
			Map<String, String> context = objectMapper.readValue(
				StringUtil.get(bqEvent.getContext(), "{}"),
				new TypeReference<Map<String, String>>() {
				});

			_browserName = bqEvent.getBrowserName();
			_completeDate = bqSession.getSessionEnd();
			_contentLanguageId = bqEvent.getContentLanguageId();
			_createDate = bqSession.getSessionStart();
			_devicePixelRatio = MapUtil.getString(
				context, "devicePixelRatio", "");
			_deviceType = bqEvent.getDeviceType();
			_languageId = bqEvent.getLanguageId();
			_screenHeight = MapUtil.getString(context, "screenHeight", "");
			_screenWidth = MapUtil.getString(context, "screenWidth", "");
			_timezoneOffset = MapUtil.getString(context, "timezoneOffset", "");
			_userAgent = MapUtil.getString(context, "userAgent", "");

			tuple2s.forEach(
				tuple -> _bqEventDTOs.add(
					new BQEventDTO(
						tuple.getT1(),
						tuple.getT2(
						).getName())));
		}
		catch (JsonProcessingException jsonProcessingException) {
			throw new RuntimeException(jsonProcessingException);
		}
	}

	@GraphQLProperty("events")
	@JsonProperty("events")
	public List<BQEventDTO> getBQEventDTOs() {
		return _bqEventDTOs;
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

	private final List<BQEventDTO> _bqEventDTOs = new ArrayList<>();
	private final String _browserName;
	private final Date _completeDate;
	private final String _contentLanguageId;
	private final Date _createDate;
	private final String _devicePixelRatio;
	private final String _deviceType;
	private final String _languageId;
	private final String _screenHeight;
	private final String _screenWidth;
	private final String _timezoneOffset;
	private final String _userAgent;

}