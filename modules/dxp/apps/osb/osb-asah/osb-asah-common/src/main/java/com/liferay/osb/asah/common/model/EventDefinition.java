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
public class EventDefinition implements Persistable<Long> {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventDefinition)) {
			return false;
		}

		EventDefinition eventDefinition = (EventDefinition)obj;

		if (Objects.equals(_description, eventDefinition._description) &&
			Objects.equals(_displayName, eventDefinition._displayName) &&
			Objects.equals(_id, eventDefinition._id) &&
			Objects.equals(_name, eventDefinition._name) &&
			Objects.equals(_type, eventDefinition._type)) {

			return true;
		}

		return false;
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
	@Id
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getName() {
		return _name;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getType() {
		return _type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_description, _displayName, _id, _name, _type);
	}

	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setType(String type) {
		_type = type;
	}

	@Transient
	private String _description;

	@Transient
	private String _displayName;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _name;

	@Transient
	private String _type;

}