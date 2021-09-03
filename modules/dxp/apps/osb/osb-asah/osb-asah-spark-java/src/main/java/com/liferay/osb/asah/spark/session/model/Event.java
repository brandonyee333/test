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

import java.io.Serializable;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Robson Pastor
 */
public class Event implements Serializable {

	public static boolean isInteraction(String eventId) {
		if (_noninteractionEventIds.contains(eventId)) {
			return false;
		}

		return true;
	}

	public static boolean isPageViewed(String applicationId, String eventId) {
		if (Objects.equals(applicationId, "Page") &&
			Objects.equals(eventId, "pageViewed")) {

			return true;
		}

		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Event)) {
			return false;
		}

		Event event = (Event)obj;

		if (Objects.equals(_applicationId, event._applicationId) &&
			Objects.equals(_channelId, event._channelId) &&
			Objects.equals(_clientIp, event._clientIp) &&
			Objects.equals(_context, event._context) &&
			Objects.equals(_createDate, event._createDate) &&
			Objects.equals(_dataSourceId, event._dataSourceId) &&
			Objects.equals(_date, event._date) &&
			Objects.equals(_eventDate, event._eventDate) &&
			Objects.equals(_eventId, event._eventId) &&
			Objects.equals(_eventProperties, event._eventProperties) &&
			Objects.equals(_id, event._id) &&
			Objects.equals(_individualId, event._individualId) &&
			Objects.equals(_iterationNumber, event._iterationNumber) &&
			Objects.equals(_knownIndividual, event._knownIndividual) &&
			Objects.equals(_normalizedEventDate, event._normalizedEventDate) &&
			Objects.equals(_projectId, event._projectId) &&
			Objects.equals(_segmentNames, event._segmentNames) &&
			Objects.equals(_userId, event._userId)) {

			return true;
		}

		return false;
	}

	public String getApplicationId() {
		return _applicationId;
	}

	public String getChannelId() {
		return _channelId;
	}

	public String getClientIp() {
		return _clientIp;
	}

	public Map<String, String> getContext() {
		return _context;
	}

	public Timestamp getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Timestamp(_createDate.getTime());
	}

	public String getDataSourceId() {
		return _dataSourceId;
	}

	public Date getDate() {
		if (_date == null) {
			return null;
		}

		return new Date(_date.getTime());
	}

	public Timestamp getEventDate() {
		if (_eventDate == null) {
			return null;
		}

		return new Timestamp(_eventDate.getTime());
	}

	public String getEventId() {
		return _eventId;
	}

	public Map<String, String> getEventProperties() {
		return _eventProperties;
	}

	public String getId() {
		return _id;
	}

	public String getIndividualId() {
		return _individualId;
	}

	public int getIterationNumber() {
		return _iterationNumber;
	}

	public Boolean getKnownIndividual() {
		return _knownIndividual;
	}

	public Long getNormalizedEventDate() {
		return _normalizedEventDate;
	}

	public String getProjectId() {
		return _projectId;
	}

	public List<String> getSegmentNames() {
		return _segmentNames;
	}

	public String getUserId() {
		return _userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_applicationId, _channelId, _clientIp, _context, _createDate,
			_dataSourceId, _date, _eventDate, _eventId, _eventProperties, _id,
			_individualId, _iterationNumber, _knownIndividual,
			_normalizedEventDate, _projectId, _segmentNames, _userId);
	}

	public void setApplicationId(String applicationId) {
		_applicationId = applicationId;
	}

	public void setChannelId(String channelId) {
		_channelId = channelId;
	}

	public void setClientIp(String clientIp) {
		_clientIp = clientIp;
	}

	public void setContext(Map<String, String> context) {
		_context = context;
	}

	public void setCreateDate(Timestamp createDate) {
		if (createDate != null) {
			_createDate = new Timestamp(createDate.getTime());
		}
	}

	public void setDataSourceId(String dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDate(Date date) {
		if (date != null) {
			_date = new Date(date.getTime());
		}
	}

	public void setEventDate(Timestamp eventDate) {
		if (eventDate != null) {
			_eventDate = new Timestamp(eventDate.getTime());
		}
	}

	public void setEventId(String eventId) {
		_eventId = eventId;
	}

	public void setEventProperties(Map<String, String> eventProperties) {
		_eventProperties = eventProperties;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIndividualId(String individualId) {
		_individualId = individualId;
	}

	public void setIterationNumber(int iterationNumber) {
		_iterationNumber = iterationNumber;
	}

	public void setKnownIndividual(Boolean knownIndividual) {
		_knownIndividual = knownIndividual;
	}

	public void setNormalizedEventDate(Long normalizedEventDate) {
		_normalizedEventDate = normalizedEventDate;
	}

	public void setProjectId(String projectId) {
		_projectId = projectId;
	}

	public void setSegmentNames(List<String> segmentNames) {
		_segmentNames = segmentNames;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	private static final Set<String> _noninteractionEventIds =
		new HashSet<String>() {
			{
				add("blogViewed");
				add("documentPreviewed");
				add("formViewed");
				add("pageLoaded");
				add("pageUnloaded");
				add("pageViewed");
				add("webContentViewed");
			}
		};

	private String _applicationId;
	private String _channelId;
	private String _clientIp;
	private Map<String, String> _context = new HashMap<>();
	private Timestamp _createDate;
	private String _dataSourceId;
	private Date _date;
	private Timestamp _eventDate;
	private String _eventId;
	private Map<String, String> _eventProperties = new HashMap<>();
	private String _id;
	private String _individualId;
	private int _iterationNumber;
	private Boolean _knownIndividual;
	private Long _normalizedEventDate;
	private String _projectId;
	private List<String> _segmentNames = new ArrayList<>();
	private String _userId;

}