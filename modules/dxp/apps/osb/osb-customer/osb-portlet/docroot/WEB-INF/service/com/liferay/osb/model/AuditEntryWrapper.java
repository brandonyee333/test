/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AuditEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AuditEntry
 * @generated
 */
public class AuditEntryWrapper implements AuditEntry, ModelWrapper<AuditEntry> {
	public AuditEntryWrapper(AuditEntry auditEntry) {
		_auditEntry = auditEntry;
	}

	public Class<?> getModelClass() {
		return AuditEntry.class;
	}

	public String getModelClassName() {
		return AuditEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("auditEntryId", getAuditEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("previousAuditEntryId", getPreviousAuditEntryId());
		attributes.put("auditSetId", getAuditSetId());
		attributes.put("fieldClassNameId", getFieldClassNameId());
		attributes.put("fieldClassPK", getFieldClassPK());
		attributes.put("action", getAction());
		attributes.put("field", getField());
		attributes.put("visibility", getVisibility());
		attributes.put("oldLabel", getOldLabel());
		attributes.put("oldValue", getOldValue());
		attributes.put("newLabel", getNewLabel());
		attributes.put("newValue", getNewValue());
		attributes.put("i18n", getI18n());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long auditEntryId = (Long)attributes.get("auditEntryId");

		if (auditEntryId != null) {
			setAuditEntryId(auditEntryId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long previousAuditEntryId = (Long)attributes.get("previousAuditEntryId");

		if (previousAuditEntryId != null) {
			setPreviousAuditEntryId(previousAuditEntryId);
		}

		Long auditSetId = (Long)attributes.get("auditSetId");

		if (auditSetId != null) {
			setAuditSetId(auditSetId);
		}

		Long fieldClassNameId = (Long)attributes.get("fieldClassNameId");

		if (fieldClassNameId != null) {
			setFieldClassNameId(fieldClassNameId);
		}

		Long fieldClassPK = (Long)attributes.get("fieldClassPK");

		if (fieldClassPK != null) {
			setFieldClassPK(fieldClassPK);
		}

		Integer action = (Integer)attributes.get("action");

		if (action != null) {
			setAction(action);
		}

		Integer field = (Integer)attributes.get("field");

		if (field != null) {
			setField(field);
		}

		Integer visibility = (Integer)attributes.get("visibility");

		if (visibility != null) {
			setVisibility(visibility);
		}

		String oldLabel = (String)attributes.get("oldLabel");

		if (oldLabel != null) {
			setOldLabel(oldLabel);
		}

		String oldValue = (String)attributes.get("oldValue");

		if (oldValue != null) {
			setOldValue(oldValue);
		}

		String newLabel = (String)attributes.get("newLabel");

		if (newLabel != null) {
			setNewLabel(newLabel);
		}

		String newValue = (String)attributes.get("newValue");

		if (newValue != null) {
			setNewValue(newValue);
		}

		Boolean i18n = (Boolean)attributes.get("i18n");

		if (i18n != null) {
			setI18n(i18n);
		}
	}

	/**
	* Returns the primary key of this audit entry.
	*
	* @return the primary key of this audit entry
	*/
	public long getPrimaryKey() {
		return _auditEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this audit entry.
	*
	* @param primaryKey the primary key of this audit entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_auditEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the audit entry ID of this audit entry.
	*
	* @return the audit entry ID of this audit entry
	*/
	public long getAuditEntryId() {
		return _auditEntry.getAuditEntryId();
	}

	/**
	* Sets the audit entry ID of this audit entry.
	*
	* @param auditEntryId the audit entry ID of this audit entry
	*/
	public void setAuditEntryId(long auditEntryId) {
		_auditEntry.setAuditEntryId(auditEntryId);
	}

	/**
	* Returns the user ID of this audit entry.
	*
	* @return the user ID of this audit entry
	*/
	public long getUserId() {
		return _auditEntry.getUserId();
	}

	/**
	* Sets the user ID of this audit entry.
	*
	* @param userId the user ID of this audit entry
	*/
	public void setUserId(long userId) {
		_auditEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this audit entry.
	*
	* @return the user uuid of this audit entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this audit entry.
	*
	* @param userUuid the user uuid of this audit entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_auditEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this audit entry.
	*
	* @return the user name of this audit entry
	*/
	public java.lang.String getUserName() {
		return _auditEntry.getUserName();
	}

	/**
	* Sets the user name of this audit entry.
	*
	* @param userName the user name of this audit entry
	*/
	public void setUserName(java.lang.String userName) {
		_auditEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this audit entry.
	*
	* @return the create date of this audit entry
	*/
	public java.util.Date getCreateDate() {
		return _auditEntry.getCreateDate();
	}

	/**
	* Sets the create date of this audit entry.
	*
	* @param createDate the create date of this audit entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_auditEntry.setCreateDate(createDate);
	}

	/**
	* Returns the fully qualified class name of this audit entry.
	*
	* @return the fully qualified class name of this audit entry
	*/
	public java.lang.String getClassName() {
		return _auditEntry.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_auditEntry.setClassName(className);
	}

	/**
	* Returns the class name ID of this audit entry.
	*
	* @return the class name ID of this audit entry
	*/
	public long getClassNameId() {
		return _auditEntry.getClassNameId();
	}

	/**
	* Sets the class name ID of this audit entry.
	*
	* @param classNameId the class name ID of this audit entry
	*/
	public void setClassNameId(long classNameId) {
		_auditEntry.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this audit entry.
	*
	* @return the class p k of this audit entry
	*/
	public long getClassPK() {
		return _auditEntry.getClassPK();
	}

	/**
	* Sets the class p k of this audit entry.
	*
	* @param classPK the class p k of this audit entry
	*/
	public void setClassPK(long classPK) {
		_auditEntry.setClassPK(classPK);
	}

	/**
	* Returns the previous audit entry ID of this audit entry.
	*
	* @return the previous audit entry ID of this audit entry
	*/
	public long getPreviousAuditEntryId() {
		return _auditEntry.getPreviousAuditEntryId();
	}

	/**
	* Sets the previous audit entry ID of this audit entry.
	*
	* @param previousAuditEntryId the previous audit entry ID of this audit entry
	*/
	public void setPreviousAuditEntryId(long previousAuditEntryId) {
		_auditEntry.setPreviousAuditEntryId(previousAuditEntryId);
	}

	/**
	* Returns the audit set ID of this audit entry.
	*
	* @return the audit set ID of this audit entry
	*/
	public long getAuditSetId() {
		return _auditEntry.getAuditSetId();
	}

	/**
	* Sets the audit set ID of this audit entry.
	*
	* @param auditSetId the audit set ID of this audit entry
	*/
	public void setAuditSetId(long auditSetId) {
		_auditEntry.setAuditSetId(auditSetId);
	}

	/**
	* Returns the field class name ID of this audit entry.
	*
	* @return the field class name ID of this audit entry
	*/
	public long getFieldClassNameId() {
		return _auditEntry.getFieldClassNameId();
	}

	/**
	* Sets the field class name ID of this audit entry.
	*
	* @param fieldClassNameId the field class name ID of this audit entry
	*/
	public void setFieldClassNameId(long fieldClassNameId) {
		_auditEntry.setFieldClassNameId(fieldClassNameId);
	}

	/**
	* Returns the field class p k of this audit entry.
	*
	* @return the field class p k of this audit entry
	*/
	public long getFieldClassPK() {
		return _auditEntry.getFieldClassPK();
	}

	/**
	* Sets the field class p k of this audit entry.
	*
	* @param fieldClassPK the field class p k of this audit entry
	*/
	public void setFieldClassPK(long fieldClassPK) {
		_auditEntry.setFieldClassPK(fieldClassPK);
	}

	/**
	* Returns the action of this audit entry.
	*
	* @return the action of this audit entry
	*/
	public int getAction() {
		return _auditEntry.getAction();
	}

	/**
	* Sets the action of this audit entry.
	*
	* @param action the action of this audit entry
	*/
	public void setAction(int action) {
		_auditEntry.setAction(action);
	}

	/**
	* Returns the field of this audit entry.
	*
	* @return the field of this audit entry
	*/
	public int getField() {
		return _auditEntry.getField();
	}

	/**
	* Sets the field of this audit entry.
	*
	* @param field the field of this audit entry
	*/
	public void setField(int field) {
		_auditEntry.setField(field);
	}

	/**
	* Returns the visibility of this audit entry.
	*
	* @return the visibility of this audit entry
	*/
	public int getVisibility() {
		return _auditEntry.getVisibility();
	}

	/**
	* Sets the visibility of this audit entry.
	*
	* @param visibility the visibility of this audit entry
	*/
	public void setVisibility(int visibility) {
		_auditEntry.setVisibility(visibility);
	}

	/**
	* Returns the old label of this audit entry.
	*
	* @return the old label of this audit entry
	*/
	public java.lang.String getOldLabel() {
		return _auditEntry.getOldLabel();
	}

	/**
	* Sets the old label of this audit entry.
	*
	* @param oldLabel the old label of this audit entry
	*/
	public void setOldLabel(java.lang.String oldLabel) {
		_auditEntry.setOldLabel(oldLabel);
	}

	/**
	* Returns the old value of this audit entry.
	*
	* @return the old value of this audit entry
	*/
	public java.lang.String getOldValue() {
		return _auditEntry.getOldValue();
	}

	/**
	* Sets the old value of this audit entry.
	*
	* @param oldValue the old value of this audit entry
	*/
	public void setOldValue(java.lang.String oldValue) {
		_auditEntry.setOldValue(oldValue);
	}

	/**
	* Returns the new label of this audit entry.
	*
	* @return the new label of this audit entry
	*/
	public java.lang.String getNewLabel() {
		return _auditEntry.getNewLabel();
	}

	/**
	* Sets the new label of this audit entry.
	*
	* @param newLabel the new label of this audit entry
	*/
	public void setNewLabel(java.lang.String newLabel) {
		_auditEntry.setNewLabel(newLabel);
	}

	/**
	* Returns the new value of this audit entry.
	*
	* @return the new value of this audit entry
	*/
	public java.lang.String getNewValue() {
		return _auditEntry.getNewValue();
	}

	/**
	* Sets the new value of this audit entry.
	*
	* @param newValue the new value of this audit entry
	*/
	public void setNewValue(java.lang.String newValue) {
		_auditEntry.setNewValue(newValue);
	}

	/**
	* Returns the i18n of this audit entry.
	*
	* @return the i18n of this audit entry
	*/
	public boolean getI18n() {
		return _auditEntry.getI18n();
	}

	/**
	* Returns <code>true</code> if this audit entry is i18n.
	*
	* @return <code>true</code> if this audit entry is i18n; <code>false</code> otherwise
	*/
	public boolean isI18n() {
		return _auditEntry.isI18n();
	}

	/**
	* Sets whether this audit entry is i18n.
	*
	* @param i18n the i18n of this audit entry
	*/
	public void setI18n(boolean i18n) {
		_auditEntry.setI18n(i18n);
	}

	public boolean isNew() {
		return _auditEntry.isNew();
	}

	public void setNew(boolean n) {
		_auditEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _auditEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_auditEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _auditEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _auditEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_auditEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _auditEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_auditEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AuditEntryWrapper((AuditEntry)_auditEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.AuditEntry auditEntry) {
		return _auditEntry.compareTo(auditEntry);
	}

	@Override
	public int hashCode() {
		return _auditEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AuditEntry> toCacheModel() {
		return _auditEntry.toCacheModel();
	}

	public com.liferay.osb.model.AuditEntry toEscapedModel() {
		return new AuditEntryWrapper(_auditEntry.toEscapedModel());
	}

	public com.liferay.osb.model.AuditEntry toUnescapedModel() {
		return new AuditEntryWrapper(_auditEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _auditEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _auditEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_auditEntry.persist();
	}

	public java.lang.String getActionLabel() {
		return _auditEntry.getActionLabel();
	}

	public java.lang.String getFieldClassNameIdLabel() {
		return _auditEntry.getFieldClassNameIdLabel();
	}

	public java.lang.String getFieldLabel() {
		return _auditEntry.getFieldLabel();
	}

	public java.lang.String getVisibilityLabel() {
		return _auditEntry.getVisibilityLabel();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditEntryWrapper)) {
			return false;
		}

		AuditEntryWrapper auditEntryWrapper = (AuditEntryWrapper)obj;

		if (Validator.equals(_auditEntry, auditEntryWrapper._auditEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AuditEntry getWrappedAuditEntry() {
		return _auditEntry;
	}

	public AuditEntry getWrappedModel() {
		return _auditEntry;
	}

	public void resetOriginalValues() {
		_auditEntry.resetOriginalValues();
	}

	private AuditEntry _auditEntry;
}