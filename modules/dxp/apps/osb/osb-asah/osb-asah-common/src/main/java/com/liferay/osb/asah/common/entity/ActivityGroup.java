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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.time.LocalDateTime;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Inácio Nery
 */
@Table
public class ActivityGroup implements Persistable<Long> {

	public ActivityGroup() {
	}

	public ActivityGroup(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ActivityGroup)) {
			return false;
		}

		ActivityGroup activityGroup = (ActivityGroup)obj;

		if (Objects.equals(_activityType, activityGroup._activityType) &&
			Objects.equals(_channelId, activityGroup._channelId) &&
			Objects.equals(_dataSourceId, activityGroup._dataSourceId) &&
			Objects.equals(_dayDate, activityGroup._dayDate) &&
			Objects.equals(_endDate, activityGroup._endDate) &&
			Objects.equals(
				_endLocalDateTime, activityGroup._endLocalDateTime) &&
			Objects.equals(_id, activityGroup._id) &&
			Objects.equals(_ownerId, activityGroup._ownerId) &&
			Objects.equals(_startDate, activityGroup._startDate) &&
			Objects.equals(
				_startLocalDateTime, activityGroup._startLocalDateTime) &&
			Objects.equals(_userId, activityGroup._userId)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getActivityType() {
		return _activityType;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getChannelId() {
		return _channelId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("day")
	public Date getDayDate() {
		if (_dayDate == null) {
			return null;
		}

		return new Date(_dayDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("endTime")
	public Date getEndDate() {
		if (_endDate == null) {
			return null;
		}

		return new Date(_endDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonProperty("endTimeLocal")
	@JsonSerialize(using = ToStringSerializer.class)
	public LocalDateTime getEndLocalDateTime() {
		return _endLocalDateTime;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getOwnerId() {
		return _ownerId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("startTime")
	public Date getStartDate() {
		if (_startDate == null) {
			return null;
		}

		return new Date(_startDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonProperty("startTimeLocal")
	@JsonSerialize(using = ToStringSerializer.class)
	public LocalDateTime getStartLocalDateTime() {
		return _startLocalDateTime;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getUserId() {
		return _userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_activityType, _channelId, _dataSourceId, _dayDate, _endDate,
			_endLocalDateTime, _id, _ownerId, _startDate, _startLocalDateTime,
			_userId);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setActivityType(String activityType) {
		_activityType = activityType;
	}

	public void setChannelId(Long channelId) {
		_channelId = channelId;
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDayDate(Date dayDate) {
		if (dayDate != null) {
			_dayDate = new Date(dayDate.getTime());
		}
	}

	public void setEndDate(Date endDate) {
		if (endDate != null) {
			_endDate = new Date(endDate.getTime());
		}
	}

	public void setEndLocalDateTime(LocalDateTime endLocalDateTime) {
		_endLocalDateTime = endLocalDateTime;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setOwnerId(Long ownerId) {
		_ownerId = ownerId;
	}

	public void setStartDate(Date startDate) {
		if (startDate != null) {
			_startDate = new Date(startDate.getTime());
		}
	}

	public void setStartLocalDateTime(LocalDateTime startLocalDateTime) {
		_startLocalDateTime = startLocalDateTime;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	@Transient
	private String _activityType;

	@Transient
	private Long _channelId;

	@Transient
	private Long _dataSourceId;

	@Transient
	private Date _dayDate;

	@Transient
	private Date _endDate;

	@Transient
	private LocalDateTime _endLocalDateTime;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private Long _ownerId;

	@Transient
	private Date _startDate;

	@Transient
	private LocalDateTime _startLocalDateTime;

	@Transient
	private String _userId;

}