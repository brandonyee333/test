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

package com.liferay.osb.asah.backend.model;

import java.util.Date;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class FieldMapping {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FieldMapping)) {
			return false;
		}

		FieldMapping fieldMapping = (FieldMapping)obj;

		if (Objects.equals(_context, fieldMapping._context) &&
			Objects.equals(_dateCreated, fieldMapping._dateCreated) &&
			Objects.equals(_dateModified, fieldMapping._dateModified) &&
			Objects.equals(_fieldName, fieldMapping._fieldName) &&
			Objects.equals(_fieldType, fieldMapping._fieldType) &&
			Objects.equals(_id, fieldMapping._id) &&
			Objects.equals(_ownerType, fieldMapping._ownerType) &&
			Objects.equals(_restricted, fieldMapping._restricted)) {

			return true;
		}

		return false;
	}

	public String getContext() {
		return _context;
	}

	public Date getDateCreated() {
		if (_dateCreated == null) {
			return null;
		}

		return new Date(_dateCreated.getTime());
	}

	public Date getDateModified() {
		if (_dateModified == null) {
			return null;
		}

		return new Date(_dateModified.getTime());
	}

	public String getFieldName() {
		return _fieldName;
	}

	public String getFieldType() {
		return _fieldType;
	}

	public String getId() {
		return _id;
	}

	public String getOwnerType() {
		return _ownerType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_context, _dateCreated, _dateModified, _fieldName, _fieldType, _id,
			_ownerType, _restricted);
	}

	public boolean isRestricted() {
		return _restricted;
	}

	public void setContext(String context) {
		_context = context;
	}

	public void setDateCreated(Date dateCreated) {
		if (dateCreated != null) {
			_dateCreated = new Date(dateCreated.getTime());
		}
	}

	public void setDateModified(Date dateModified) {
		if (dateModified != null) {
			_dateModified = new Date(dateModified.getTime());
		}
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	public void setFieldType(String fieldType) {
		_fieldType = fieldType;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setOwnerType(String ownerType) {
		_ownerType = ownerType;
	}

	public void setRestricted(boolean restricted) {
		_restricted = restricted;
	}

	private String _context;
	private Date _dateCreated;
	private Date _dateModified;
	private String _fieldName;
	private String _fieldType;
	private String _id;
	private String _ownerType;
	private boolean _restricted;

}