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

/**
 * @author Leslie Wong
 */
public class BQSession {

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

	public String getAcquisitionCampaign() {
		return _acquisitionCampaign;
	}

	public String getAcquisitionChannel() {
		return _acquisitionChannel;
	}

	public String getAcquisitionContent() {
		return _acquisitionContent;
	}

	public String getAcquisitionMedium() {
		return _acquisitionMedium;
	}

	public String getAcquisitionSource() {
		return _acquisitionSource;
	}

	public String getAcquisitionTerm() {
		return _acquisitionTerm;
	}

	public Integer getBounce() {
		return _bounce;
	}

	public String getBrowserName() {
		return _browserName;
	}

	public Long getChannelId() {
		return _channelId;
	}

	public String getCity() {
		return _city;
	}

	public String getCountry() {
		return _country;
	}

	public String getDeviceType() {
		return _deviceType;
	}

	public Long getDuration() {
		return _duration;
	}

	public String getId() {
		return _id;
	}

	public String getPlatformName() {
		return _platformName;
	}

	public Set<String> getReferrers() {
		return _referrers;
	}

	public String getRegion() {
		return _region;
	}

	public Date getSessionEnd() {
		if (_sessionEnd == null) {
			return null;
		}

		return new Date(_sessionEnd.getTime());
	}

	public Date getSessionStart() {
		if (_sessionStart == null) {
			return null;
		}

		return new Date(_sessionStart.getTime());
	}

	public Set<String> getUrls() {
		return _urls;
	}

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

	private String _acquisitionCampaign;
	private String _acquisitionChannel;
	private String _acquisitionContent;
	private String _acquisitionMedium;
	private String _acquisitionSource;
	private String _acquisitionTerm;
	private Integer _bounce;
	private String _browserName;
	private Long _channelId;
	private String _city;
	private String _country;
	private String _deviceType;
	private Long _duration;
	private String _id;
	private String _platformName;
	private Set<String> _referrers;
	private String _region;
	private Date _sessionEnd;
	private Date _sessionStart;
	private Set<String> _urls;
	private String _userId;

}