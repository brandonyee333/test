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

package com.liferay.osb.asah.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.liferay.osb.asah.common.date.DateUtil;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class Activity {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Activity)) {
			return false;
		}

		Activity activity = (Activity)obj;

		if (Objects.equals(_applicationId, activity._applicationId) &&
			Objects.equals(_eventId, activity._eventId) &&
			Objects.equals(_eventProperties, activity._eventProperties) &&
			Objects.equals(_ownerId, activity._ownerId) &&
			Objects.equals(_startTime, activity._startTime)) {

			return true;
		}

		return false;
	}

	public String getApplicationId() {
		return _applicationId;
	}

	public String getEventId() {
		return _eventId;
	}

	public Map<String, String> getEventProperties() {
		return _eventProperties;
	}

	public String getOwnerId() {
		return _ownerId;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getStartTime() {
		if (_startTime == null) {
			return null;
		}

		return new Date(_startTime.getTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_applicationId, _eventId, _eventProperties, _ownerId, _startTime);
	}

	public void setApplicationId(String applicationId) {
		_applicationId = applicationId;
	}

	public void setEventId(String eventId) {
		_eventId = eventId;
	}

	public void setEventProperties(Map<String, String> eventProperties) {
		_eventProperties = eventProperties;
	}

	public void setOwnerId(String ownerId) {
		_ownerId = ownerId;
	}

	public void setStartTime(Date startTime) {
		if (startTime != null) {
			_startTime = new Date(startTime.getTime());
		}
	}

	private String _applicationId;
	private String _eventId;
	private Map<String, String> _eventProperties;
	private String _ownerId;
	private Date _startTime;

}