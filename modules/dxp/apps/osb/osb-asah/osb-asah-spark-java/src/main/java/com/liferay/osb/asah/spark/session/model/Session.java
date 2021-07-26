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

package com.liferay.osb.asah.spark.session.model;

import com.liferay.osb.asah.spark.common.DateUtil;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Robson Pastor
 */
public class Session {

	public void addEvent(Event event) {
		Map<String, String> eventContext = event.getContext();

		String canonicalUrl = eventContext.get("canonicalUrl");

		if (canonicalUrl != null) {
			_canonicalUrls.add(canonicalUrl);
		}

		_events.add(event);

		_firstEventDate = DateUtil.min(event.getEventDate(), _firstEventDate);

		if (Event.isInteraction(event.getEventId())) {
			_interactionsCount += 1;
		}

		if (Event.isPageViewed(event.getApplicationId(), event.getEventId())) {
			_pageViewsCount += 1;
		}

		_lastEventDate = DateUtil.max(event.getEventDate(), _lastEventDate);

		String referrer = eventContext.get("referrer");

		if (referrer != null) {
			_referrers.add(referrer);
		}

		String url = eventContext.get("url");

		if (url != null) {
			_urls.add(url);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Session)) {
			return false;
		}

		Session session = (Session)obj;

		if (Objects.equals(_browserName, session._browserName) &&
			Objects.equals(_canonicalUrls, session._canonicalUrls) &&
			Objects.equals(_channelId, session._channelId) &&
			Objects.equals(_city, session._city) &&
			Objects.equals(_clientIp, session._clientIp) &&
			Objects.equals(_country, session._country) &&
			Objects.equals(_dataSourceId, session._dataSourceId) &&
			Objects.equals(_date, session._date) &&
			Objects.equals(_deviceType, session._deviceType) &&
			Objects.equals(_events, session._events) &&
			Objects.equals(_finished, session._finished) &&
			Objects.equals(_firstEventDate, session._firstEventDate) &&
			Objects.equals(_interactionsCount, session._interactionsCount) &&
			Objects.equals(_interactionNumber, session._interactionNumber) &&
			Objects.equals(_lastEventDate, session._lastEventDate) &&
			Objects.equals(_pageViewsCount, session._pageViewsCount) &&
			Objects.equals(_platformName, session._platformName) &&
			Objects.equals(_projectId, session._projectId) &&
			Objects.equals(_referrers, session._referrers) &&
			Objects.equals(_region, session._region) &&
			Objects.equals(_sessionId, session._sessionId) &&
			Objects.equals(_urls, session._urls) &&
			Objects.equals(_userId, session._userId)) {

			return true;
		}

		return false;
	}

	public String getBrowserName() {
		return _browserName;
	}

	public Set<String> getCanonicalUrls() {
		return _canonicalUrls;
	}

	public String getChannelId() {
		return _channelId;
	}

	public String getCity() {
		return _city;
	}

	public String getClientIp() {
		return _clientIp;
	}

	public String getCountry() {
		return _country;
	}

	public String getDataSourceId() {
		return _dataSourceId;
	}

	public Date getDate() {
		return _date;
	}

	public String getDeviceType() {
		return _deviceType;
	}

	public List<Event> getEvents() {
		return _events;
	}

	public Boolean getFinished() {
		return _finished;
	}

	public Timestamp getFirstEventDate() {
		return _firstEventDate;
	}

	public int getInteractionNumber() {
		return _interactionNumber;
	}

	public int getInteractionsCount() {
		return _interactionsCount;
	}

	public Timestamp getLastEventDate() {
		return _lastEventDate;
	}

	public int getPageViewsCount() {
		return _pageViewsCount;
	}

	public String getPlatformName() {
		return _platformName;
	}

	public String getProjectId() {
		return _projectId;
	}

	public Set<String> getReferrers() {
		return _referrers;
	}

	public String getRegion() {
		return _region;
	}

	public String getSessionId() {
		return _sessionId;
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
			_browserName, _canonicalUrls, _channelId, _city, _clientIp,
			_country, _dataSourceId, _date, _deviceType, _events, _finished,
			_firstEventDate, _interactionsCount, _interactionNumber,
			_lastEventDate, _pageViewsCount, _platformName, _projectId,
			_referrers, _region, _sessionId, _urls, _userId);
	}

	public boolean isBounced() {
		if ((_interactionsCount < 1) && (_pageViewsCount < 2)) {
			return true;
		}

		return false;
	}

	public void setBrowserName(String browserName) {
		_browserName = browserName;
	}

	public void setCanonicalUrls(Set<String> canonicalUrls) {
		_canonicalUrls = canonicalUrls;
	}

	public void setChannelId(String channelId) {
		_channelId = channelId;
	}

	public void setCity(String city) {
		_city = city;
	}

	public void setClientIp(String clientIp) {
		_clientIp = clientIp;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public void setDataSourceId(String dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDate(Date date) {
		_date = date;
	}

	public void setDeviceType(String deviceType) {
		_deviceType = deviceType;
	}

	public void setEvents(List<Event> events) {
		_events = events;
	}

	public void setFinished(Boolean finished) {
		_finished = finished;
	}

	public void setFirstEventDate(Timestamp firstEventDate) {
		_firstEventDate = firstEventDate;
	}

	public void setInteractionNumber(int interactionNumber) {
		_interactionNumber = interactionNumber;
	}

	public void setInteractionsCount(int interactionsCount) {
		_interactionsCount = interactionsCount;
	}

	public void setLastEventDate(Timestamp lastEventDate) {
		_lastEventDate = lastEventDate;
	}

	public void setPageViewsCount(int pageViewsCount) {
		_pageViewsCount = pageViewsCount;
	}

	public void setPlatformName(String platformName) {
		_platformName = platformName;
	}

	public void setProjectId(String projectId) {
		_projectId = projectId;
	}

	public void setReferrers(Set<String> referrers) {
		_referrers = referrers;
	}

	public void setRegion(String region) {
		_region = region;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public void setUrls(Set<String> urls) {
		_urls = urls;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	private String _browserName;
	private Set<String> _canonicalUrls = new HashSet<>();
	private String _channelId;
	private String _city;
	private String _clientIp;
	private String _country;
	private String _dataSourceId;
	private Date _date;
	private String _deviceType;
	private List<Event> _events = new ArrayList<>();
	private Boolean _finished;
	private Timestamp _firstEventDate;
	private int _interactionNumber;
	private int _interactionsCount;
	private Timestamp _lastEventDate;
	private int _pageViewsCount;
	private String _platformName;
	private String _projectId;
	private Set<String> _referrers = new HashSet<>();
	private String _region;
	private String _sessionId;
	private Set<String> _urls = new HashSet<>();
	private String _userId;

}