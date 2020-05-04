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

package com.liferay.osb.asah.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.liferay.osb.asah.common.date.DateUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author André Miranda
 */
public class UserSession implements Serializable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof UserSession)) {
			return false;
		}

		UserSession userSession = (UserSession)obj;

		if (Objects.equals(_acquisition, userSession._acquisition) &&
			Objects.equals(_bounced, userSession._bounced) &&
			Objects.equals(_browserName, userSession._browserName) &&
			Objects.equals(_channelId, userSession._channelId) &&
			Objects.equals(_city, userSession._city) &&
			Objects.equals(_completed, userSession._completed) &&
			Objects.equals(_completeDate, userSession._completeDate) &&
			Objects.equals(_completeReason, userSession._completeReason) &&
			Objects.equals(_country, userSession._country) &&
			Objects.equals(_dataSourceId, userSession._dataSourceId) &&
			Objects.equals(_date, userSession._date) &&
			Objects.equals(_duration, userSession._duration) &&
			Objects.equals(_entryPage, userSession._entryPage) &&
			Objects.equals(_exitPage, userSession._exitPage) &&
			Objects.equals(_firstEventDate, userSession._firstEventDate) &&
			Objects.equals(_id, userSession._id) &&
			Objects.equals(_individualId, userSession._individualId) &&
			Objects.equals(_interactions, userSession._interactions) &&
			Objects.equals(_lastEventDate, userSession._lastEventDate) &&
			Objects.equals(_platformName, userSession._platformName) &&
			Objects.equals(_region, userSession._region) &&
			Objects.equals(_userId, userSession._userId)) {

			return true;
		}

		return false;
	}

	public Acquisition getAcquisition() {
		return _acquisition;
	}

	public Boolean getBounced() {
		return _bounced;
	}

	public String getBrowserName() {
		return _browserName;
	}

	public String getChannelId() {
		return _channelId;
	}

	public String getCity() {
		return _city;
	}

	public Boolean getCompleted() {
		return _completed;
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

	public String getCompleteReason() {
		return _completeReason;
	}

	public String getCountry() {
		return _country;
	}

	public String getDataSourceId() {
		return _dataSourceId;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getDate() {
		if (_date == null) {
			return null;
		}

		return new Date(_date.getTime());
	}

	public Long getDuration() {
		return _duration;
	}

	public String getEntryPage() {
		return _entryPage;
	}

	public String getExitPage() {
		return _exitPage;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getFirstEventDate() {
		if (_firstEventDate == null) {
			return null;
		}

		return new Date(_firstEventDate.getTime());
	}

	public String getId() {
		return _id;
	}

	public String getIndividualId() {
		return _individualId;
	}

	public List<AnalyticsEvent> getInteractions() {
		return _interactions;
	}

	public Long getInteractionsCount() {
		return _interactionsCount;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getLastEventDate() {
		if (_lastEventDate == null) {
			return null;
		}

		return new Date(_lastEventDate.getTime());
	}

	public Long getPageViewsCount() {
		return _pageViewsCount;
	}

	public String getPlatformName() {
		return _platformName;
	}

	public String getRegion() {
		return _region;
	}

	public String getUserId() {
		return _userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_acquisition, _bounced, _browserName, _channelId, _city, _completed,
			_completeDate, _completeReason, _country, _dataSourceId, _date,
			_duration, _entryPage, _exitPage, _firstEventDate, _id,
			_individualId, _interactions, _lastEventDate, _platformName,
			_region, _userId);
	}

	public void setAcquisition(Acquisition acquisition) {
		_acquisition = acquisition;
	}

	public void setBounced(Boolean bounced) {
		_bounced = bounced;
	}

	public void setBrowserName(String browserName) {
		_browserName = browserName;
	}

	public void setChannelId(String channelId) {
		_channelId = channelId;
	}

	public void setCity(String city) {
		_city = city;
	}

	public void setCompleted(Boolean completed) {
		_completed = completed;
	}

	public void setCompleteDate(Date completeDate) {
		if (completeDate != null) {
			_completeDate = new Date(completeDate.getTime());
		}
	}

	public void setCompleteReason(String completeReason) {
		_completeReason = completeReason;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public void setDataSourceId(String dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDate(Date date) {
		if (date != null) {
			_date = new Date(date.getTime());
		}
	}

	public void setDuration(Long duration) {
		_duration = duration;
	}

	public void setEntryPage(String entryPage) {
		_entryPage = entryPage;
	}

	public void setExitPage(String exitPage) {
		_exitPage = exitPage;
	}

	public void setFirstEventDate(Date firstEventDate) {
		if (firstEventDate != null) {
			_firstEventDate = new Date(firstEventDate.getTime());
		}
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIndividualId(String individualId) {
		_individualId = individualId;
	}

	public void setInteractions(List<AnalyticsEvent> interactions) {
		_interactions = interactions;
	}

	public void setInteractionsCount(Long interactionsCount) {
		_interactionsCount = interactionsCount;
	}

	public void setLastEventDate(Date lastEventDate) {
		if (lastEventDate != null) {
			_lastEventDate = new Date(lastEventDate.getTime());
		}
	}

	public void setPageViewsCount(Long pageViewsCount) {
		_pageViewsCount = pageViewsCount;
	}

	public void setPlatformName(String platformName) {
		_platformName = platformName;
	}

	public void setRegion(String region) {
		_region = region;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	private Acquisition _acquisition;
	private Boolean _bounced;
	private String _browserName;
	private String _channelId;
	private String _city;
	private Boolean _completed;
	private Date _completeDate;
	private String _completeReason;
	private String _country;
	private String _dataSourceId;
	private Date _date;
	private Long _duration;
	private String _entryPage;
	private String _exitPage;
	private Date _firstEventDate;
	private String _id;
	private String _individualId;
	private List<AnalyticsEvent> _interactions;
	private Long _interactionsCount;
	private Date _lastEventDate;
	private Long _pageViewsCount;
	private String _platformName;
	private String _region;
	private String _userId;

}