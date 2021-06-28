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
public class EventAttribute implements Persistable<Long> {

	public EventAttribute() {
	}

	public EventAttribute(
		Date eventDate, Long eventAttributeDefinitionId, String value) {

		if (eventDate != null) {
			_eventDate = new Date(eventDate.getTime());
		}

		_eventAttributeDefinitionId = eventAttributeDefinitionId;
		_value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventAttribute)) {
			return false;
		}

		EventAttribute eventAttribute = (EventAttribute)obj;

		if (Objects.equals(
				_eventAttributeDefinitionId,
				eventAttribute._eventAttributeDefinitionId) &&
			Objects.equals(_eventDate, eventAttribute._eventDate) &&
			Objects.equals(_eventId, eventAttribute._eventId) &&
			Objects.equals(_id, eventAttribute._id) &&
			Objects.equals(_value, eventAttribute._value)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getEventAttributeDefinitionId() {
		return _eventAttributeDefinitionId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getEventDate() {
		if (_eventDate == null) {
			return null;
		}

		return new Date(_eventDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getEventId() {
		return _eventId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_eventAttributeDefinitionId, _eventDate, _eventId, _id, _value);
	}

	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setEventAttributeDefinitionId(Long eventAttributeDefinitionId) {
		_eventAttributeDefinitionId = eventAttributeDefinitionId;
	}

	public void setEventDate(Date eventDate) {
		if (eventDate != null) {
			_eventDate = new Date(eventDate.getTime());
		}
	}

	public void setEventId(Long eventId) {
		_eventId = eventId;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(boolean isNew) {
		_isNew = isNew;
	}

	public void setValue(String value) {
		_value = value;
	}

	@Transient
	private Long _eventAttributeDefinitionId;

	@Transient
	private Date _eventDate;

	@Transient
	private Long _eventId;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _value;

}