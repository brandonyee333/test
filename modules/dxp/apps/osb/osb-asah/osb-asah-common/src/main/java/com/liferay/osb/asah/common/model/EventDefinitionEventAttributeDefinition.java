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
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Leslie Wong
 */
@Table
public class EventDefinitionEventAttributeDefinition {

	public EventDefinitionEventAttributeDefinition() {
	}

	public EventDefinitionEventAttributeDefinition(
		Long eventDefinitionId, String sampleValue) {

		_eventDefinitionId = eventDefinitionId;
		_sampleValue = sampleValue;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventDefinitionEventAttributeDefinition)) {
			return false;
		}

		EventDefinitionEventAttributeDefinition
			eventDefinitionEventAttributeDefinition =
				(EventDefinitionEventAttributeDefinition)obj;

		if (Objects.equals(
				_eventDefinitionId,
				eventDefinitionEventAttributeDefinition._eventDefinitionId) &&
			Objects.equals(
				_sampleValue,
				eventDefinitionEventAttributeDefinition._sampleValue)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getEventDefinitionId() {
		return _eventDefinitionId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getSampleValue() {
		return _sampleValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_eventDefinitionId, _sampleValue);
	}

	public void setEventDefinitionId(Long eventDefinitionId) {
		_eventDefinitionId = eventDefinitionId;
	}

	public void setSampleValue(String sampleValue) {
		_sampleValue = sampleValue;
	}

	@Transient
	private Long _eventDefinitionId;

	@Transient
	private String _sampleValue;

}