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

package com.liferay.osb.asah.dataflow.emulator.entity;

import java.util.Date;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcellus Tavares
 */
@Table
public class Event implements Persistable<String> {

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
	public String getCountry() {
		return _country;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getCreateDate() {
		return _createDate;
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
		return _eventDate;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEventId() {
		return _eventId;
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
	public String getLanguageId() {
		return _languageId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getPlatformName() {
		return _platformName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getProjectId() {
		return _projectId;
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

	public void setCountry(String country) {
		_country = country;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
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
		_eventDate = eventDate;
	}

	public void setEventId(String eventId) {
		_eventId = eventId;
	}

	public void setExperienceId(String experienceId) {
		_experienceId = experienceId;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	public void setPlatformName(String platformName) {
		_platformName = platformName;
	}

	public void setProjectId(String projectId) {
		_projectId = projectId;
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
	private String _experienceId;

	@Transient
	private String _id;

	@Transient
	private String _languageId;

	@Transient
	private String _platformName;

	@Transient
	private String _projectId;

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