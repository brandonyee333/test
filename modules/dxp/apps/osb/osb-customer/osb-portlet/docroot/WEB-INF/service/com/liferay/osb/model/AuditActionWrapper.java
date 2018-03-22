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
 * This class is a wrapper for {@link AuditAction}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditAction
 * @generated
 */
@ProviderType
public class AuditActionWrapper implements AuditAction,
	ModelWrapper<AuditAction> {
	public AuditActionWrapper(AuditAction auditAction) {
		_auditAction = auditAction;
	}

	@Override
	public Class<?> getModelClass() {
		return AuditAction.class;
	}

	@Override
	public String getModelClassName() {
		return AuditAction.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("auditActionId", getAuditActionId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("mappingClassPK", getMappingClassPK());
		attributes.put("action", getAction());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long auditActionId = (Long)attributes.get("auditActionId");

		if (auditActionId != null) {
			setAuditActionId(auditActionId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long mappingClassPK = (Long)attributes.get("mappingClassPK");

		if (mappingClassPK != null) {
			setMappingClassPK(mappingClassPK);
		}

		Integer action = (Integer)attributes.get("action");

		if (action != null) {
			setAction(action);
		}
	}

	@Override
	public AuditAction toEscapedModel() {
		return new AuditActionWrapper(_auditAction.toEscapedModel());
	}

	@Override
	public AuditAction toUnescapedModel() {
		return new AuditActionWrapper(_auditAction.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _auditAction.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _auditAction.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _auditAction.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _auditAction.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AuditAction> toCacheModel() {
		return _auditAction.toCacheModel();
	}

	@Override
	public int compareTo(AuditAction auditAction) {
		return _auditAction.compareTo(auditAction);
	}

	/**
	* Returns the action of this audit action.
	*
	* @return the action of this audit action
	*/
	@Override
	public int getAction() {
		return _auditAction.getAction();
	}

	@Override
	public int hashCode() {
		return _auditAction.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _auditAction.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AuditActionWrapper((AuditAction)_auditAction.clone());
	}

	/**
	* Returns the fully qualified class name of this audit action.
	*
	* @return the fully qualified class name of this audit action
	*/
	@Override
	public java.lang.String getClassName() {
		return _auditAction.getClassName();
	}

	@Override
	public java.lang.String toString() {
		return _auditAction.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _auditAction.toXmlString();
	}

	/**
	* Returns the modified date of this audit action.
	*
	* @return the modified date of this audit action
	*/
	@Override
	public Date getModifiedDate() {
		return _auditAction.getModifiedDate();
	}

	/**
	* Returns the audit action ID of this audit action.
	*
	* @return the audit action ID of this audit action
	*/
	@Override
	public long getAuditActionId() {
		return _auditAction.getAuditActionId();
	}

	/**
	* Returns the class name ID of this audit action.
	*
	* @return the class name ID of this audit action
	*/
	@Override
	public long getClassNameId() {
		return _auditAction.getClassNameId();
	}

	/**
	* Returns the class pk of this audit action.
	*
	* @return the class pk of this audit action
	*/
	@Override
	public long getClassPK() {
		return _auditAction.getClassPK();
	}

	/**
	* Returns the mapping class pk of this audit action.
	*
	* @return the mapping class pk of this audit action
	*/
	@Override
	public long getMappingClassPK() {
		return _auditAction.getMappingClassPK();
	}

	/**
	* Returns the primary key of this audit action.
	*
	* @return the primary key of this audit action
	*/
	@Override
	public long getPrimaryKey() {
		return _auditAction.getPrimaryKey();
	}

	@Override
	public void persist() {
		_auditAction.persist();
	}

	/**
	* Sets the action of this audit action.
	*
	* @param action the action of this audit action
	*/
	@Override
	public void setAction(int action) {
		_auditAction.setAction(action);
	}

	/**
	* Sets the audit action ID of this audit action.
	*
	* @param auditActionId the audit action ID of this audit action
	*/
	@Override
	public void setAuditActionId(long auditActionId) {
		_auditAction.setAuditActionId(auditActionId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_auditAction.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_auditAction.setClassName(className);
	}

	/**
	* Sets the class name ID of this audit action.
	*
	* @param classNameId the class name ID of this audit action
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_auditAction.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this audit action.
	*
	* @param classPK the class pk of this audit action
	*/
	@Override
	public void setClassPK(long classPK) {
		_auditAction.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_auditAction.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_auditAction.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_auditAction.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the mapping class pk of this audit action.
	*
	* @param mappingClassPK the mapping class pk of this audit action
	*/
	@Override
	public void setMappingClassPK(long mappingClassPK) {
		_auditAction.setMappingClassPK(mappingClassPK);
	}

	/**
	* Sets the modified date of this audit action.
	*
	* @param modifiedDate the modified date of this audit action
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_auditAction.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_auditAction.setNew(n);
	}

	/**
	* Sets the primary key of this audit action.
	*
	* @param primaryKey the primary key of this audit action
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_auditAction.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_auditAction.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditActionWrapper)) {
			return false;
		}

		AuditActionWrapper auditActionWrapper = (AuditActionWrapper)obj;

		if (Objects.equals(_auditAction, auditActionWrapper._auditAction)) {
			return true;
		}

		return false;
	}

	@Override
	public AuditAction getWrappedModel() {
		return _auditAction;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _auditAction.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _auditAction.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_auditAction.resetOriginalValues();
	}

	private final AuditAction _auditAction;
}