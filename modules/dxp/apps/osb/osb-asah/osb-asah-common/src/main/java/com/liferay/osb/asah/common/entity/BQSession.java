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
public class BQSession implements Persistable<String> {

	@AccessType(AccessType.Type.PROPERTY)
	public Long getChannelId() {
		return _channelId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
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

	@Override
	public boolean isNew() {
		return true;
	}

	public void setChannelId(Long channelId) {
		_channelId = channelId;
	}

	public void setId(String id) {
		_id = id;
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

	@Transient
	private Long _channelId;

	@Transient
	private String _id;

	@Transient
	private Date _sessionEnd;

	@Transient
	private Date _sessionStart;

}