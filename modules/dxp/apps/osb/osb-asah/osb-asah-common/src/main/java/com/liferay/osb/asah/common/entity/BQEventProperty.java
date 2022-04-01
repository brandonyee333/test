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
 * @author Leslie Wong
 */
@Table
public class BQEventProperty implements Persistable<String> {

	public BQEventProperty() {
	}

	public BQEventProperty(Date eventDate, String name, String value) {
		if (eventDate != null) {
			_eventDate = new Date(eventDate.getTime());
		}

		_name = name;
		_value = value;
	}

	public BQEventProperty(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQEventProperty)) {
			return false;
		}

		BQEventProperty bqEventProperty = (BQEventProperty)obj;

		if (Objects.equals(_channelId, bqEventProperty._channelId) &&
			Objects.equals(_eventDate, bqEventProperty._eventDate) &&
			Objects.equals(_id, bqEventProperty._id) &&
			Objects.equals(_name, bqEventProperty._name) &&
			Objects.equals(_projectId, bqEventProperty._projectId) &&
			Objects.equals(_value, bqEventProperty._value)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getChannelId() {
		return _channelId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getEventDate() {
		if (_eventDate == null) {
			return null;
		}

		return new Date(_eventDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getName() {
		return _name;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getProjectId() {
		return _projectId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_channelId, _eventDate, _id, _name, _projectId, _value);
	}

	@Override
	public boolean isNew() {
		return true;
	}

	public void setChannelId(Long channelId) {
		_channelId = channelId;
	}

	public void setEventDate(Date eventDate) {
		if (eventDate != null) {
			_eventDate = new Date(eventDate.getTime());
		}
		else {
			_eventDate = null;
		}
	}

	public void setId(String id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setProjectId(String projectId) {
		_projectId = projectId;
	}

	public void setValue(String value) {
		_value = value;
	}

	@Transient
	private Long _channelId;

	@Transient
	private Date _eventDate;

	@Transient
	private String _id;

	@Transient
	private String _name;

	@Transient
	private String _projectId;

	@Transient
	private String _value;

}