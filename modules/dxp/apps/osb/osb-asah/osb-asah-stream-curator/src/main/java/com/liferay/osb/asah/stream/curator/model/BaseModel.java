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

package com.liferay.osb.asah.stream.curator.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.liferay.osb.asah.common.date.DateUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseModel implements Model {

	@Override
	public void addSegmentNames(Set<String> segmentNames) {
		_segmentNames.addAll(segmentNames);
	}

	@Override
	public String getBrowserName() {
		return _browserName;
	}

	@Override
	public String getChannelId() {
		return _channelId;
	}

	@Override
	public String getCity() {
		return _city;
	}

	@Override
	public String getContentLanguageId() {
		return _contentLanguageId;
	}

	@Override
	public String getCountry() {
		return _country;
	}

	@Override
	public String getDataSourceId() {
		return _dataSourceId;
	}

	@Override
	public String getDeviceType() {
		return _deviceType;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@Override
	public Date getEventDate() {
		if (_eventDate == null) {
			return null;
		}

		return new Date(_eventDate.getTime());
	}

	@Override
	public String getExperienceId() {
		return _experienceId;
	}

	@Override
	public String getExperimentId() {
		return _experimentId;
	}

	@Override
	public String getId() {
		return _id;
	}

	@Override
	public String getIndividualId() {
		return _individualId;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@Override
	public Date getLastEventDate() {
		if (_lastEventDate == null) {
			return null;
		}

		return new Date(_lastEventDate.getTime());
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@Override
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@Override
	public String getPlatformName() {
		return _platformName;
	}

	@Override
	public String getPrimaryKey() {
		return _primaryKey;
	}

	@Override
	public String getRegion() {
		return _region;
	}

	@Override
	public Set<String> getSegmentNames() {
		return _segmentNames;
	}

	@Override
	public String getSessionId() {
		return _sessionId;
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public String getUserId() {
		return _userId;
	}

	@Override
	public String getVariantId() {
		return _variantId;
	}

	@Override
	public boolean isKnownIndividual() {
		return _knownIndividual;
	}

	@Override
	public void setBrowserName(String browserName) {
		_browserName = browserName;
	}

	@Override
	public void setChannelId(String channelId) {
		_channelId = channelId;
	}

	@Override
	public void setCity(String city) {
		_city = city;
	}

	@Override
	public void setContentLanguageId(String contentLanguageId) {
		_contentLanguageId = contentLanguageId;
	}

	@Override
	public void setCountry(String country) {
		_country = country;
	}

	@Override
	public void setDataSourceId(String dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	@Override
	public void setDeviceType(String deviceType) {
		_deviceType = deviceType;
	}

	@Override
	public void setEventDate(Date eventDate) {
		if (eventDate != null) {
			_eventDate = new Date(eventDate.getTime());
		}
	}

	@Override
	public void setExperienceId(String experienceId) {
		_experienceId = experienceId;
	}

	@Override
	public void setExperimentId(String experimentId) {
		_experimentId = experimentId;
	}

	@Override
	public void setId(String id) {
		_id = id;
	}

	@Override
	public void setIndividualId(String individualId) {
		_individualId = individualId;
	}

	@Override
	public void setKnownIndividual(boolean knownIndividual) {
		_knownIndividual = knownIndividual;
	}

	@Override
	public void setLastEventDate(Date lastEventDate) {
		if (lastEventDate != null) {
			_lastEventDate = new Date(lastEventDate.getTime());
		}
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	@Override
	public void setPlatformName(String platformName) {
		_platformName = platformName;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		_primaryKey = primaryKey;
	}

	@Override
	public void setRegion(String region) {
		_region = region;
	}

	@Override
	public void setSegmentNames(Set<String> segmentNames) {
		_segmentNames = segmentNames;
	}

	@Override
	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	@Override
	public void setTitle(String title) {
		if (title != null) {
			_title = title;
		}
	}

	@Override
	public void setUserId(String userId) {
		_userId = userId;
	}

	@Override
	public void setVariantId(String variantId) {
		_variantId = variantId;
	}

	private String _browserName;
	private String _channelId;
	private String _city;
	private String _contentLanguageId;
	private String _country;
	private String _dataSourceId;
	private String _deviceType;
	private Date _eventDate;
	private String _experienceId;
	private String _experimentId;
	private String _id;
	private String _individualId;
	private boolean _knownIndividual;
	private Date _lastEventDate;
	private Date _modifiedDate;
	private String _platformName;
	private String _primaryKey;
	private String _region;
	private Set<String> _segmentNames = new HashSet<>();
	private String _sessionId;
	private String _title = "";
	private String _userId;
	private String _variantId;

}