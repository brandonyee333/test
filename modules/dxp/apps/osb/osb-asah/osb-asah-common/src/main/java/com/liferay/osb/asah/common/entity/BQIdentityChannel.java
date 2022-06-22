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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Rachael Koestartyo
 */
@Table
public class BQIdentityChannel {

	public BQIdentityChannel() {
	}

	public BQIdentityChannel(
		Long activitiesCount, Long channelId, Long identityId,
		Date lastActivityDate, Date previousActivityDate) {

		_activitiesCount = activitiesCount;
		_channelId = channelId;
		_identityId = identityId;

		if (lastActivityDate != null) {
			_lastActivityDate = new Date(lastActivityDate.getTime());
		}

		if (previousActivityDate != null) {
			_previousActivityDate = new Date(previousActivityDate.getTime());
		}
	}

	public BQIdentityChannel(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQIdentityChannel)) {
			return false;
		}

		BQIdentityChannel bqIdentityChannel = (BQIdentityChannel)obj;

		if (Objects.equals(
				_activitiesCount, bqIdentityChannel._activitiesCount) &&
			Objects.equals(_channelId, bqIdentityChannel._channelId) &&
			Objects.equals(_identityId, bqIdentityChannel._identityId) &&
			Objects.equals(
				_lastActivityDate, bqIdentityChannel._lastActivityDate) &&
			Objects.equals(
				_previousActivityDate,
				bqIdentityChannel._previousActivityDate)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getActivitiesCount() {
		return _activitiesCount;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getChannelId() {
		return _channelId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getIdentityId() {
		return _identityId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getLastActivityDate() {
		if (_lastActivityDate == null) {
			return null;
		}

		return new Date(_lastActivityDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getPreviousActivityDate() {
		if (_previousActivityDate == null) {
			return null;
		}

		return new Date(_previousActivityDate.getTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_activitiesCount, _channelId, _identityId, _lastActivityDate,
			_previousActivityDate);
	}

	public void setActivitiesCount(Long activitiesCount) {
		_activitiesCount = activitiesCount;
	}

	public void setChannelId(Long channelId) {
		_channelId = channelId;
	}

	public void setIdentityId(Long identityId) {
		_identityId = identityId;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		if (lastActivityDate != null) {
			_lastActivityDate = new Date(lastActivityDate.getTime());
		}
	}

	public void setPreviousActivityDate(Date previousActivityDate) {
		if (previousActivityDate != null) {
			_previousActivityDate = new Date(previousActivityDate.getTime());
		}
	}

	@Transient
	private Long _activitiesCount;

	@Transient
	private Long _channelId;

	@Transient
	private Long _identityId;

	@Transient
	private Date _lastActivityDate;

	@Transient
	private Date _previousActivityDate;

}