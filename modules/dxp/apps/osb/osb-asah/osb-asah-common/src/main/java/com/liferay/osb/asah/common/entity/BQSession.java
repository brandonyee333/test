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
import java.util.Set;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Leslie Wong
 */
@Table
public class BQSession implements Persistable<String> {

	public BQSession() {
	}

	public BQSession(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQSession)) {
			return false;
		}

		BQSession bqSession = (BQSession)obj;

		if (Objects.equals(
				_acquisitionCampaign, bqSession._acquisitionCampaign) &&
			Objects.equals(
				_acquisitionChannel, bqSession._acquisitionChannel) &&
			Objects.equals(
				_acquisitionContent, bqSession._acquisitionContent) &&
			Objects.equals(_acquisitionMedium, bqSession._acquisitionMedium) &&
			Objects.equals(_acquisitionSource, bqSession._acquisitionSource) &&
			Objects.equals(_acquisitionTerm, bqSession._acquisitionTerm) &&
			Objects.equals(_bounce, bqSession._bounce) &&
			Objects.equals(_browserName, bqSession._browserName) &&
			Objects.equals(_channelId, bqSession._channelId) &&
			Objects.equals(_city, bqSession._city) &&
			Objects.equals(_country, bqSession._country) &&
			Objects.equals(_deviceType, bqSession._deviceType) &&
			Objects.equals(_duration, bqSession._duration) &&
			Objects.equals(_id, bqSession._id) &&
			Objects.equals(_platformName, bqSession._platformName) &&
			Objects.equals(_referrers, bqSession._referrers) &&
			Objects.equals(_region, bqSession._region) &&
			Objects.equals(_sessionEnd, bqSession._sessionEnd) &&
			Objects.equals(_sessionStart, bqSession._sessionStart) &&
			Objects.equals(_userId, bqSession._userId)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAcquisitionCampaign() {
		return _acquisitionCampaign;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAcquisitionChannel() {
		return _acquisitionChannel;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAcquisitionContent() {
		return _acquisitionContent;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAcquisitionMedium() {
		return _acquisitionMedium;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAcquisitionSource() {
		return _acquisitionSource;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAcquisitionTerm() {
		return _acquisitionTerm;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Integer getBounce() {
		return _bounce;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getBrowserName() {
		return _browserName;
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
	public String getCountry() {
		return _country;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDeviceType() {
		return _deviceType;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getDuration() {
		return _duration;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getPlatformName() {
		return _platformName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<String> getReferrers() {
		return _referrers;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getRegion() {
		return _region;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getSessionEnd() {
		if (_sessionEnd == null) {
			return null;
		}

		return new Date(_sessionEnd.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getSessionStart() {
		if (_sessionStart == null) {
			return null;
		}

		return new Date(_sessionStart.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<String> getUrls() {
		return _urls;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getUserId() {
		return _userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_acquisitionCampaign, _acquisitionChannel, _acquisitionContent,
			_acquisitionMedium, _acquisitionSource, _acquisitionTerm, _bounce,
			_browserName, _channelId, _city, _country, _deviceType, _duration,
			_id, _platformName, _referrers, _region, _sessionEnd, _sessionStart,
			_userId);
	}

	@Override
	public boolean isNew() {
		return true;
	}

	public void setAcquisitionCampaign(String acquisitionCampaign) {
		_acquisitionCampaign = acquisitionCampaign;
	}

	public void setAcquisitionChannel(String acquisitionChannel) {
		_acquisitionChannel = acquisitionChannel;
	}

	public void setAcquisitionContent(String acquisitionContent) {
		_acquisitionContent = acquisitionContent;
	}

	public void setAcquisitionMedium(String acquisitionMedium) {
		_acquisitionMedium = acquisitionMedium;
	}

	public void setAcquisitionSource(String acquisitionSource) {
		_acquisitionSource = acquisitionSource;
	}

	public void setAcquisitionTerm(String acquisitionTerm) {
		_acquisitionTerm = acquisitionTerm;
	}

	public void setBounce(Integer bounce) {
		_bounce = bounce;
	}

	public void setBrowserName(String browserName) {
		_browserName = browserName;
	}

	public void setChannelId(Long channelId) {
		_channelId = channelId;
	}

	public void setCity(String city) {
		_city = city;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public void setDeviceType(String deviceType) {
		_deviceType = deviceType;
	}

	public void setDuration(Long duration) {
		_duration = duration;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setPlatformName(String platformName) {
		_platformName = platformName;
	}

	public void setReferrers(Set<String> referrers) {
		_referrers = referrers;
	}

	public void setRegion(String region) {
		_region = region;
	}

	public void setSessionEnd(Date sessionEnd) {
		if (sessionEnd != null) {
			_sessionEnd = new Date(sessionEnd.getTime());
		}
		else {
			_sessionEnd = null;
		}
	}

	public void setSessionStart(Date sessionStart) {
		if (sessionStart != null) {
			_sessionStart = new Date(sessionStart.getTime());
		}
		else {
			_sessionStart = null;
		}
	}

	public void setUrls(Set<String> urls) {
		_urls = urls;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	@Transient
	private String _acquisitionCampaign;

	@Transient
	private String _acquisitionChannel;

	@Transient
	private String _acquisitionContent;

	@Transient
	private String _acquisitionMedium;

	@Transient
	private String _acquisitionSource;

	@Transient
	private String _acquisitionTerm;

	@Transient
	private Integer _bounce;

	@Transient
	private String _browserName;

	@Transient
	private Long _channelId;

	@Transient
	private String _city;

	@Transient
	private String _country;

	@Transient
	private String _deviceType;

	@Transient
	private Long _duration;

	@Transient
	private String _id;

	@Transient
	private String _platformName;

	@Transient
	private Set<String> _referrers;

	@Transient
	private String _region;

	@Transient
	private Date _sessionEnd;

	@Transient
	private Date _sessionStart;

	@Transient
	private Set<String> _urls;

	@Transient
	private String _userId;

}