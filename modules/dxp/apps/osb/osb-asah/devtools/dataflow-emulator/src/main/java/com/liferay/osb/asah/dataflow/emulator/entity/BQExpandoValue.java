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

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcos Martins
 */
@Table
public class BQExpandoValue implements Persistable<Long> {

	@AccessType(AccessType.Type.PROPERTY)
	public Long getClassPK() {
		return _classPK;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public ClassType getClassType() {
		return _classType;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getColumnId() {
		return _columnId;
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
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setClassPK(Long classPK) {
		_classPK = classPK;
	}

	public void setClassType(ClassType classType) {
		_classType = classType;
	}

	public void setColumnId(Long columnId) {
		_columnId = columnId;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setValue(String value) {
		_value = value;
	}

	public enum ClassType {

		ACCOUNT, INDIVIDUAL, ORGANIZATION

	}

	@Transient
	private Long _classPK;

	@Transient
	private ClassType _classType;

	@Transient
	private Long _columnId;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _value;

}