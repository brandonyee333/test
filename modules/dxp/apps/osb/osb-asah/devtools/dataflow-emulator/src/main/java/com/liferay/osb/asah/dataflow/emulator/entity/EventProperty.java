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
 * @author Leslie Wong
 */
@Table
public class EventProperty implements Persistable<String> {

	@AccessType(AccessType.Type.PROPERTY)
	public Long getChannelId() {
		return _channelId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getEventDate() {
		return _eventDate;
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
	public boolean isNew() {
		return true;
	}

	public void setChannelId(Long channelId) {
		_channelId = channelId;
	}

	public void setEventDate(Date eventDate) {
		_eventDate = eventDate;
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