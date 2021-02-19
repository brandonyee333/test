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

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Leslie Wong
 */
@Table
public class Event implements Persistable<Long> {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Event)) {
			return false;
		}

		Event event = (Event)obj;

		if (Objects.equals(_analyticsEventId, event._analyticsEventId) &&
			Objects.equals(_applicationId, event._applicationId) &&
			Objects.equals(_channelId, event._channelId) &&
			Objects.equals(_createDate, event._createDate) &&
			Objects.equals(_dataSourceId, event._dataSourceId) &&
			Objects.equals(_eventDate, event._eventDate) &&
			Objects.equals(_eventDefinitionId, event._eventDefinitionId) &&
			Objects.equals(_id, event._id) &&
			Objects.equals(_userId, event._userId)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAnalyticsEventId() {
		return _analyticsEventId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getApplicationId() {
		return _applicationId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getChannelId() {
		return _channelId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getCreateDate() {
		return _createDate;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDataSourceId() {
		return _dataSourceId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getEventDate() {
		return _eventDate;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getEventDefinitionId() {
		return _eventDefinitionId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getUserId() {
		return _userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_analyticsEventId, _applicationId, _channelId, _createDate,
			_dataSourceId, _eventDate, _eventDefinitionId, _id, _userId);
	}

	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setAnalyticsEventId(String analyticsEventId) {
		_analyticsEventId = analyticsEventId;
	}

	public void setApplicationId(String applicationId) {
		_applicationId = applicationId;
	}

	public void setChannelId(Long channelId) {
		_channelId = channelId;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public void setDataSourceId(String dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setEventDate(Date eventDate) {
		_eventDate = eventDate;
	}

	public void setEventDefinitionId(Long eventDefinitionId) {
		_eventDefinitionId = eventDefinitionId;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(boolean isNew) {
		_isNew = isNew;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	@Transient
	private String _analyticsEventId;

	@Transient
	private String _applicationId;

	@Transient
	private Long _channelId;

	@Transient
	private Date _createDate;

	@Transient
	private String _dataSourceId;

	@Transient
	private Date _eventDate;

	@Transient
	private Long _eventDefinitionId;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _userId;

}