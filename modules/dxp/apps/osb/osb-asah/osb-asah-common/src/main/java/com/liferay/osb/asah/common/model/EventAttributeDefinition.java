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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Leslie Wong
 */
@Table
public class EventAttributeDefinition implements Persistable<Long> {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventAttributeDefinition)) {
			return false;
		}

		EventAttributeDefinition eventDefinition =
			(EventAttributeDefinition)obj;

		if (Objects.equals(_dataType, eventDefinition._dataType) &&
			Objects.equals(_description, eventDefinition._description) &&
			Objects.equals(_displayName, eventDefinition._displayName) &&
			Objects.equals(
				_eventDefinitionEventAttributeDefinitions,
				eventDefinition._eventDefinitionEventAttributeDefinitions) &&
			Objects.equals(_id, eventDefinition._id) &&
			Objects.equals(_name, eventDefinition._name)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDataType() {
		return _dataType;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDescription() {
		return _description;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDisplayName() {
		return _displayName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@MappedCollection(idColumn = "eventattributedefinitionid")
	public Set<EventDefinitionEventAttributeDefinition>
		getEventDefinitionEventAttributeDefinitions() {

		return _eventDefinitionEventAttributeDefinitions;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getName() {
		return _name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_dataType, _description, _displayName,
			_eventDefinitionEventAttributeDefinitions, _id, _name);
	}

	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setDataType(String dataType) {
		_dataType = dataType;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	public void setEventDefinitionEventAttributeDefinitions(
		Set<EventDefinitionEventAttributeDefinition>
			eventDefinitionEventAttributeDefinition) {

		_eventDefinitionEventAttributeDefinitions =
			eventDefinitionEventAttributeDefinition;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(boolean isNew) {
		_isNew = isNew;
	}

	public void setName(String name) {
		_name = name;
	}

	@Transient
	private String _dataType;

	@Transient
	private String _description;

	@Transient
	private String _displayName;

	@Transient
	private Set<EventDefinitionEventAttributeDefinition>
		_eventDefinitionEventAttributeDefinitions = new HashSet<>();

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _name;

}