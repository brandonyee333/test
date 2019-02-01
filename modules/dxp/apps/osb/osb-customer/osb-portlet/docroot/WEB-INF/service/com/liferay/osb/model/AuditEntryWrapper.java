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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AuditEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntry
 * @generated
 */
@ProviderType
public class AuditEntryWrapper implements AuditEntry, ModelWrapper<AuditEntry> {
	public AuditEntryWrapper(AuditEntry auditEntry) {
		_auditEntry = auditEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return AuditEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AuditEntry.class.getName();
	}

	@Override
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
		attributes.put("description", getDescription());
		attributes.put("i18n", getI18n());

		return attributes;
	}

	@Override
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

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Boolean i18n = (Boolean)attributes.get("i18n");

		if (i18n != null) {
			setI18n(i18n);
		}
	}

	@Override
	public AuditEntry toEscapedModel() {
		return new AuditEntryWrapper(_auditEntry.toEscapedModel());
	}

	@Override
	public AuditEntry toUnescapedModel() {
		return new AuditEntryWrapper(_auditEntry.toUnescapedModel());
	}

	/**
	* Returns the i18n of this audit entry.
	*
	* @return the i18n of this audit entry
	*/
	@Override
	public boolean getI18n() {
		return _auditEntry.getI18n();
	}

	@Override
	public boolean isCachedModel() {
		return _auditEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _auditEntry.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this audit entry is i18n.
	*
	* @return <code>true</code> if this audit entry is i18n; <code>false</code> otherwise
	*/
	@Override
	public boolean isI18n() {
		return _auditEntry.isI18n();
	}

	@Override
	public boolean isNew() {
		return _auditEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _auditEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AuditEntry> toCacheModel() {
		return _auditEntry.toCacheModel();
	}

	@Override
	public int compareTo(AuditEntry auditEntry) {
		return _auditEntry.compareTo(auditEntry);
	}

	/**
	* Returns the action of this audit entry.
	*
	* @return the action of this audit entry
	*/
	@Override
	public int getAction() {
		return _auditEntry.getAction();
	}

	/**
	* Returns the field of this audit entry.
	*
	* @return the field of this audit entry
	*/
	@Override
	public int getField() {
		return _auditEntry.getField();
	}

	/**
	* Returns the visibility of this audit entry.
	*
	* @return the visibility of this audit entry
	*/
	@Override
	public int getVisibility() {
		return _auditEntry.getVisibility();
	}

	@Override
	public int hashCode() {
		return _auditEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _auditEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AuditEntryWrapper((AuditEntry)_auditEntry.clone());
	}

	@Override
	public java.lang.String getActionLabel() {
		return _auditEntry.getActionLabel();
	}

	/**
	* Returns the fully qualified class name of this audit entry.
	*
	* @return the fully qualified class name of this audit entry
	*/
	@Override
	public java.lang.String getClassName() {
		return _auditEntry.getClassName();
	}

	/**
	* Returns the description of this audit entry.
	*
	* @return the description of this audit entry
	*/
	@Override
	public java.lang.String getDescription() {
		return _auditEntry.getDescription();
	}

	@Override
	public java.lang.String getFieldClassNameIdLabel() {
		return _auditEntry.getFieldClassNameIdLabel();
	}

	@Override
	public java.lang.String getFieldLabel() {
		return _auditEntry.getFieldLabel();
	}

	/**
	* Returns the new label of this audit entry.
	*
	* @return the new label of this audit entry
	*/
	@Override
	public java.lang.String getNewLabel() {
		return _auditEntry.getNewLabel();
	}

	/**
	* Returns the new value of this audit entry.
	*
	* @return the new value of this audit entry
	*/
	@Override
	public java.lang.String getNewValue() {
		return _auditEntry.getNewValue();
	}

	/**
	* Returns the old label of this audit entry.
	*
	* @return the old label of this audit entry
	*/
	@Override
	public java.lang.String getOldLabel() {
		return _auditEntry.getOldLabel();
	}

	/**
	* Returns the old value of this audit entry.
	*
	* @return the old value of this audit entry
	*/
	@Override
	public java.lang.String getOldValue() {
		return _auditEntry.getOldValue();
	}

	/**
	* Returns the user name of this audit entry.
	*
	* @return the user name of this audit entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _auditEntry.getUserName();
	}

	/**
	* Returns the user uuid of this audit entry.
	*
	* @return the user uuid of this audit entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _auditEntry.getUserUuid();
	}

	@Override
	public java.lang.String getVisibilityLabel() {
		return _auditEntry.getVisibilityLabel();
	}

	@Override
	public java.lang.String toString() {
		return _auditEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _auditEntry.toXmlString();
	}

	/**
	* Returns the create date of this audit entry.
	*
	* @return the create date of this audit entry
	*/
	@Override
	public Date getCreateDate() {
		return _auditEntry.getCreateDate();
	}

	/**
	* Returns the audit entry ID of this audit entry.
	*
	* @return the audit entry ID of this audit entry
	*/
	@Override
	public long getAuditEntryId() {
		return _auditEntry.getAuditEntryId();
	}

	/**
	* Returns the audit set ID of this audit entry.
	*
	* @return the audit set ID of this audit entry
	*/
	@Override
	public long getAuditSetId() {
		return _auditEntry.getAuditSetId();
	}

	/**
	* Returns the class name ID of this audit entry.
	*
	* @return the class name ID of this audit entry
	*/
	@Override
	public long getClassNameId() {
		return _auditEntry.getClassNameId();
	}

	/**
	* Returns the class pk of this audit entry.
	*
	* @return the class pk of this audit entry
	*/
	@Override
	public long getClassPK() {
		return _auditEntry.getClassPK();
	}

	/**
	* Returns the field class name ID of this audit entry.
	*
	* @return the field class name ID of this audit entry
	*/
	@Override
	public long getFieldClassNameId() {
		return _auditEntry.getFieldClassNameId();
	}

	/**
	* Returns the field class pk of this audit entry.
	*
	* @return the field class pk of this audit entry
	*/
	@Override
	public long getFieldClassPK() {
		return _auditEntry.getFieldClassPK();
	}

	/**
	* Returns the previous audit entry ID of this audit entry.
	*
	* @return the previous audit entry ID of this audit entry
	*/
	@Override
	public long getPreviousAuditEntryId() {
		return _auditEntry.getPreviousAuditEntryId();
	}

	/**
	* Returns the primary key of this audit entry.
	*
	* @return the primary key of this audit entry
	*/
	@Override
	public long getPrimaryKey() {
		return _auditEntry.getPrimaryKey();
	}

	/**
	* Returns the user ID of this audit entry.
	*
	* @return the user ID of this audit entry
	*/
	@Override
	public long getUserId() {
		return _auditEntry.getUserId();
	}

	@Override
	public void persist() {
		_auditEntry.persist();
	}

	/**
	* Sets the action of this audit entry.
	*
	* @param action the action of this audit entry
	*/
	@Override
	public void setAction(int action) {
		_auditEntry.setAction(action);
	}

	/**
	* Sets the audit entry ID of this audit entry.
	*
	* @param auditEntryId the audit entry ID of this audit entry
	*/
	@Override
	public void setAuditEntryId(long auditEntryId) {
		_auditEntry.setAuditEntryId(auditEntryId);
	}

	/**
	* Sets the audit set ID of this audit entry.
	*
	* @param auditSetId the audit set ID of this audit entry
	*/
	@Override
	public void setAuditSetId(long auditSetId) {
		_auditEntry.setAuditSetId(auditSetId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_auditEntry.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_auditEntry.setClassName(className);
	}

	/**
	* Sets the class name ID of this audit entry.
	*
	* @param classNameId the class name ID of this audit entry
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_auditEntry.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this audit entry.
	*
	* @param classPK the class pk of this audit entry
	*/
	@Override
	public void setClassPK(long classPK) {
		_auditEntry.setClassPK(classPK);
	}

	/**
	* Sets the create date of this audit entry.
	*
	* @param createDate the create date of this audit entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_auditEntry.setCreateDate(createDate);
	}

	/**
	* Sets the description of this audit entry.
	*
	* @param description the description of this audit entry
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_auditEntry.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_auditEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_auditEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_auditEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field of this audit entry.
	*
	* @param field the field of this audit entry
	*/
	@Override
	public void setField(int field) {
		_auditEntry.setField(field);
	}

	/**
	* Sets the field class name ID of this audit entry.
	*
	* @param fieldClassNameId the field class name ID of this audit entry
	*/
	@Override
	public void setFieldClassNameId(long fieldClassNameId) {
		_auditEntry.setFieldClassNameId(fieldClassNameId);
	}

	/**
	* Sets the field class pk of this audit entry.
	*
	* @param fieldClassPK the field class pk of this audit entry
	*/
	@Override
	public void setFieldClassPK(long fieldClassPK) {
		_auditEntry.setFieldClassPK(fieldClassPK);
	}

	/**
	* Sets whether this audit entry is i18n.
	*
	* @param i18n the i18n of this audit entry
	*/
	@Override
	public void setI18n(boolean i18n) {
		_auditEntry.setI18n(i18n);
	}

	@Override
	public void setNew(boolean n) {
		_auditEntry.setNew(n);
	}

	/**
	* Sets the new label of this audit entry.
	*
	* @param newLabel the new label of this audit entry
	*/
	@Override
	public void setNewLabel(java.lang.String newLabel) {
		_auditEntry.setNewLabel(newLabel);
	}

	/**
	* Sets the new value of this audit entry.
	*
	* @param newValue the new value of this audit entry
	*/
	@Override
	public void setNewValue(java.lang.String newValue) {
		_auditEntry.setNewValue(newValue);
	}

	/**
	* Sets the old label of this audit entry.
	*
	* @param oldLabel the old label of this audit entry
	*/
	@Override
	public void setOldLabel(java.lang.String oldLabel) {
		_auditEntry.setOldLabel(oldLabel);
	}

	/**
	* Sets the old value of this audit entry.
	*
	* @param oldValue the old value of this audit entry
	*/
	@Override
	public void setOldValue(java.lang.String oldValue) {
		_auditEntry.setOldValue(oldValue);
	}

	/**
	* Sets the previous audit entry ID of this audit entry.
	*
	* @param previousAuditEntryId the previous audit entry ID of this audit entry
	*/
	@Override
	public void setPreviousAuditEntryId(long previousAuditEntryId) {
		_auditEntry.setPreviousAuditEntryId(previousAuditEntryId);
	}

	/**
	* Sets the primary key of this audit entry.
	*
	* @param primaryKey the primary key of this audit entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_auditEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_auditEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this audit entry.
	*
	* @param userId the user ID of this audit entry
	*/
	@Override
	public void setUserId(long userId) {
		_auditEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this audit entry.
	*
	* @param userName the user name of this audit entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_auditEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this audit entry.
	*
	* @param userUuid the user uuid of this audit entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_auditEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the visibility of this audit entry.
	*
	* @param visibility the visibility of this audit entry
	*/
	@Override
	public void setVisibility(int visibility) {
		_auditEntry.setVisibility(visibility);
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

		if (Objects.equals(_auditEntry, auditEntryWrapper._auditEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public AuditEntry getWrappedModel() {
		return _auditEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _auditEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _auditEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_auditEntry.resetOriginalValues();
	}

	private final AuditEntry _auditEntry;
}