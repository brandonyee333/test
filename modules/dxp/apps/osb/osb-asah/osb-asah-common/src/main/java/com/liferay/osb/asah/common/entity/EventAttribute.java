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
		String attributeValue, Long eventAttributeDefinitionId) {

		_attributeValue = attributeValue;
		_eventAttributeDefinitionId = eventAttributeDefinitionId;
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
			Objects.equals(_attributeValue, eventAttribute._attributeValue) &&
			Objects.equals(_eventId, eventAttribute._eventId) &&
			Objects.equals(_id, eventAttribute._id)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAttributeValue() {
		return _attributeValue;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getEventAttributeDefinitionId() {
		return _eventAttributeDefinitionId;
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

	@Override
	public int hashCode() {
		return Objects.hash(
			_eventAttributeDefinitionId, _attributeValue, _eventId, _id);
	}

	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setAttributeValue(String attributeValue) {
		_attributeValue = attributeValue;
	}

	public void setEventAttributeDefinitionId(Long eventAttributeDefinitionId) {
		_eventAttributeDefinitionId = eventAttributeDefinitionId;
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

	@Transient
	private String _attributeValue;

	@Transient
	private Long _eventAttributeDefinitionId;

	@Transient
	private Long _eventId;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

}