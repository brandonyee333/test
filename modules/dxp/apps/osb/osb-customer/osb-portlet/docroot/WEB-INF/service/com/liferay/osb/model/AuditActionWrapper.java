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
 * This class is a wrapper for {@link AuditAction}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AuditAction
 * @generated
 */
public class AuditActionWrapper implements AuditAction,
	ModelWrapper<AuditAction> {
	public AuditActionWrapper(AuditAction auditAction) {
		_auditAction = auditAction;
	}

	public Class<?> getModelClass() {
		return AuditAction.class;
	}

	public String getModelClassName() {
		return AuditAction.class.getName();
	}

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

	/**
	* Returns the primary key of this audit action.
	*
	* @return the primary key of this audit action
	*/
	public long getPrimaryKey() {
		return _auditAction.getPrimaryKey();
	}

	/**
	* Sets the primary key of this audit action.
	*
	* @param primaryKey the primary key of this audit action
	*/
	public void setPrimaryKey(long primaryKey) {
		_auditAction.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the audit action ID of this audit action.
	*
	* @return the audit action ID of this audit action
	*/
	public long getAuditActionId() {
		return _auditAction.getAuditActionId();
	}

	/**
	* Sets the audit action ID of this audit action.
	*
	* @param auditActionId the audit action ID of this audit action
	*/
	public void setAuditActionId(long auditActionId) {
		_auditAction.setAuditActionId(auditActionId);
	}

	/**
	* Returns the modified date of this audit action.
	*
	* @return the modified date of this audit action
	*/
	public java.util.Date getModifiedDate() {
		return _auditAction.getModifiedDate();
	}

	/**
	* Sets the modified date of this audit action.
	*
	* @param modifiedDate the modified date of this audit action
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_auditAction.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this audit action.
	*
	* @return the fully qualified class name of this audit action
	*/
	public java.lang.String getClassName() {
		return _auditAction.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_auditAction.setClassName(className);
	}

	/**
	* Returns the class name ID of this audit action.
	*
	* @return the class name ID of this audit action
	*/
	public long getClassNameId() {
		return _auditAction.getClassNameId();
	}

	/**
	* Sets the class name ID of this audit action.
	*
	* @param classNameId the class name ID of this audit action
	*/
	public void setClassNameId(long classNameId) {
		_auditAction.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this audit action.
	*
	* @return the class p k of this audit action
	*/
	public long getClassPK() {
		return _auditAction.getClassPK();
	}

	/**
	* Sets the class p k of this audit action.
	*
	* @param classPK the class p k of this audit action
	*/
	public void setClassPK(long classPK) {
		_auditAction.setClassPK(classPK);
	}

	/**
	* Returns the mapping class p k of this audit action.
	*
	* @return the mapping class p k of this audit action
	*/
	public long getMappingClassPK() {
		return _auditAction.getMappingClassPK();
	}

	/**
	* Sets the mapping class p k of this audit action.
	*
	* @param mappingClassPK the mapping class p k of this audit action
	*/
	public void setMappingClassPK(long mappingClassPK) {
		_auditAction.setMappingClassPK(mappingClassPK);
	}

	/**
	* Returns the action of this audit action.
	*
	* @return the action of this audit action
	*/
	public int getAction() {
		return _auditAction.getAction();
	}

	/**
	* Sets the action of this audit action.
	*
	* @param action the action of this audit action
	*/
	public void setAction(int action) {
		_auditAction.setAction(action);
	}

	public boolean isNew() {
		return _auditAction.isNew();
	}

	public void setNew(boolean n) {
		_auditAction.setNew(n);
	}

	public boolean isCachedModel() {
		return _auditAction.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_auditAction.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _auditAction.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _auditAction.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_auditAction.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _auditAction.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_auditAction.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AuditActionWrapper((AuditAction)_auditAction.clone());
	}

	public int compareTo(com.liferay.osb.model.AuditAction auditAction) {
		return _auditAction.compareTo(auditAction);
	}

	@Override
	public int hashCode() {
		return _auditAction.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AuditAction> toCacheModel() {
		return _auditAction.toCacheModel();
	}

	public com.liferay.osb.model.AuditAction toEscapedModel() {
		return new AuditActionWrapper(_auditAction.toEscapedModel());
	}

	public com.liferay.osb.model.AuditAction toUnescapedModel() {
		return new AuditActionWrapper(_auditAction.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _auditAction.toString();
	}

	public java.lang.String toXmlString() {
		return _auditAction.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_auditAction.persist();
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

		if (Validator.equals(_auditAction, auditActionWrapper._auditAction)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AuditAction getWrappedAuditAction() {
		return _auditAction;
	}

	public AuditAction getWrappedModel() {
		return _auditAction;
	}

	public void resetOriginalValues() {
		_auditAction.resetOriginalValues();
	}

	private AuditAction _auditAction;
}