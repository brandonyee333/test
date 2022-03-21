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

package com.liferay.osb.asah.common.entity;

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Matthew Kong
 */
@Table
public class BQEvent implements Persistable<String> {

	public BQEvent() {
	}

	public BQEvent(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	public BQEvent(
		String applicationId, String browserName, String canonicalUrl,
		Long channelId, String city, String contentLanguageId, String context,
		String country, Date createDate, Long dataSourceId, String description,
		String deviceType, Date eventDate, String eventId,
		String eventProperties, String experienceId, String id, String keywords,
		String languageId, String platformName, String projectTimeZoneId,
		String referrer, String region, String sessionId, String timezoneOffset,
		String title, String url, String userId, String variantId) {

		_applicationId = applicationId;
		_browserName = browserName;
		_canonicalUrl = canonicalUrl;
		_channelId = channelId;
		_city = city;
		_contentLanguageId = contentLanguageId;
		_context = context;
		_country = country;
		_createDate = createDate;
		_dataSourceId = dataSourceId;
		_description = description;
		_deviceType = deviceType;
		_eventDate = eventDate;
		_eventId = eventId;
		_eventProperties = eventProperties;
		_experienceId = experienceId;
		_id = id;
		_keywords = keywords;
		_languageId = languageId;
		_platformName = platformName;
		_projectTimeZoneId = projectTimeZoneId;
		_referrer = referrer;
		_region = region;
		_sessionId = sessionId;
		_timezoneOffset = timezoneOffset;
		_title = title;
		_url = url;
		_userId = userId;
		_variantId = variantId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQEvent)) {
			return false;
		}

		BQEvent bqEvent = (BQEvent)obj;

		if (Objects.equals(_applicationId, bqEvent._applicationId) &&
			Objects.equals(_browserName, bqEvent._browserName) &&
			Objects.equals(_canonicalUrl, bqEvent._canonicalUrl) &&
			Objects.equals(_channelId, bqEvent._channelId) &&
			Objects.equals(_city, bqEvent._city) &&
			Objects.equals(_contentLanguageId, bqEvent._contentLanguageId) &&
			Objects.equals(_context, bqEvent._context) &&
			Objects.equals(_country, bqEvent._country) &&
			Objects.equals(_createDate, bqEvent._createDate) &&
			Objects.equals(_dataSourceId, bqEvent._dataSourceId) &&
			Objects.equals(_description, bqEvent._description) &&
			Objects.equals(_deviceType, bqEvent._deviceType) &&
			Objects.equals(_eventDate, bqEvent._eventDate) &&
			Objects.equals(_eventId, bqEvent._eventId) &&
			Objects.equals(_eventProperties, bqEvent._eventProperties) &&
			Objects.equals(_experienceId, bqEvent._experienceId) &&
			Objects.equals(_id, bqEvent._id) &&
			Objects.equals(_keywords, bqEvent._keywords) &&
			Objects.equals(_languageId, bqEvent._languageId) &&
			Objects.equals(_platformName, bqEvent._platformName) &&
			Objects.equals(_projectTimeZoneId, bqEvent._projectTimeZoneId) &&
			Objects.equals(_referrer, bqEvent._referrer) &&
			Objects.equals(_region, bqEvent._region) &&
			Objects.equals(_sessionId, bqEvent._sessionId) &&
			Objects.equals(_timezoneOffset, bqEvent._timezoneOffset) &&
			Objects.equals(_title, bqEvent._title) &&
			Objects.equals(_url, bqEvent._url) &&
			Objects.equals(_userId, bqEvent._userId) &&
			Objects.equals(_variantId, bqEvent._variantId)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getApplicationId() {
		return _applicationId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getBrowserName() {
		return _browserName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getCanonicalUrl() {
		return _canonicalUrl;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getChannelId() {
		return _channelId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getCity() {
		return _city;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getContentLanguageId() {
		return _contentLanguageId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getContext() {
		return _context;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getCountry() {
		return _country;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDescription() {
		return _description;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDeviceType() {
		return _deviceType;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getEventDate() {
		if (_eventDate == null) {
			return null;
		}

		return new Date(_eventDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEventId() {
		return _eventId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEventProperties() {
		return _eventProperties;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getExperienceId() {
		return _experienceId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getKeywords() {
		return _keywords;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getLanguageId() {
		return _languageId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getPlatformName() {
		return _platformName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getProjectTimeZoneId() {
		return _projectTimeZoneId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getReferrer() {
		return _referrer;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getRegion() {
		return _region;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getSessionId() {
		return _sessionId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getTimezoneOffset() {
		return _timezoneOffset;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getTitle() {
		return _title;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getUrl() {
		return _url;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getUserId() {
		return _userId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getVariantId() {
		return _variantId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_applicationId, _browserName, _canonicalUrl, _channelId, _city,
			_contentLanguageId, _context, _country, _createDate, _dataSourceId,
			_description, _deviceType, _eventDate, _eventId, _eventProperties,
			_experienceId, _id, _keywords, _languageId, _platformName,
			_projectTimeZoneId, _referrer, _region, _sessionId, _timezoneOffset,
			_title, _url, _userId, _variantId);
	}

	@Override
	public boolean isNew() {
		return true;
	}

	public void setApplicationId(String applicationId) {
		_applicationId = applicationId;
	}

	public void setBrowserName(String browserName) {
		_browserName = browserName;
	}

	public void setCanonicalUrl(String canonicalUrl) {
		_canonicalUrl = canonicalUrl;
	}

	public void setChannelId(Long channelId) {
		_channelId = channelId;
	}

	public void setCity(String city) {
		_city = city;
	}

	public void setContentLanguageId(String contentLanguageId) {
		_contentLanguageId = contentLanguageId;
	}

	public void setContext(String context) {
		_context = context;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
		else {
			_createDate = null;
		}
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setDeviceType(String deviceType) {
		_deviceType = deviceType;
	}

	public void setEventDate(Date eventDate) {
		if (eventDate != null) {
			_eventDate = new Date(eventDate.getTime());
		}
		else {
			_eventDate = null;
		}
	}

	public void setEventId(String eventId) {
		_eventId = eventId;
	}

	public void setEventProperties(String eventProperties) {
		_eventProperties = eventProperties;
	}

	public void setExperienceId(String experienceId) {
		_experienceId = experienceId;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setKeywords(String keywords) {
		_keywords = keywords;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	public void setPlatformName(String platformName) {
		_platformName = platformName;
	}

	public void setProjectTimeZoneId(String projectTimeZoneId) {
		_projectTimeZoneId = projectTimeZoneId;
	}

	public void setReferrer(String referrer) {
		_referrer = referrer;
	}

	public void setRegion(String region) {
		_region = region;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public void setTimezoneOffset(String timezoneOffset) {
		_timezoneOffset = timezoneOffset;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	public void setVariantId(String variantId) {
		_variantId = variantId;
	}

	@Transient
	private String _applicationId;

	@Transient
	private String _browserName;

	@Transient
	private String _canonicalUrl;

	@Transient
	private Long _channelId;

	@Transient
	private String _city;

	@Transient
	private String _contentLanguageId;

	@Transient
	private String _context;

	@Transient
	private String _country;

	@Transient
	private Date _createDate;

	@Transient
	private Long _dataSourceId;

	@Transient
	private String _description;

	@Transient
	private String _deviceType;

	@Transient
	private Date _eventDate;

	@Transient
	private String _eventId;

	@Transient
	private String _eventProperties;

	@Transient
	private String _experienceId;

	@Transient
	private String _id;

	@Transient
	private String _keywords;

	@Transient
	private String _languageId;

	@Transient
	private String _platformName;

	@Transient
	private String _projectTimeZoneId;

	@Transient
	private String _referrer;

	@Transient
	private String _region;

	@Transient
	private String _sessionId;

	@Transient
	private String _timezoneOffset;

	@Transient
	private String _title;

	@Transient
	private String _url;

	@Transient
	private String _userId;

	@Transient
	private String _variantId;

}