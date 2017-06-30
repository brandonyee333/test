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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SupportWorkerSeverity}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportWorkerSeverity
 * @generated
 */
public class SupportWorkerSeverityWrapper implements SupportWorkerSeverity,
	ModelWrapper<SupportWorkerSeverity> {
	public SupportWorkerSeverityWrapper(
		SupportWorkerSeverity supportWorkerSeverity) {
		_supportWorkerSeverity = supportWorkerSeverity;
	}

	public Class<?> getModelClass() {
		return SupportWorkerSeverity.class;
	}

	public String getModelClassName() {
		return SupportWorkerSeverity.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportWorkerSeverityId", getSupportWorkerSeverityId());
		attributes.put("supportWorkerId", getSupportWorkerId());
		attributes.put("severity", getSeverity());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportWorkerSeverityId = (Long)attributes.get(
				"supportWorkerSeverityId");

		if (supportWorkerSeverityId != null) {
			setSupportWorkerSeverityId(supportWorkerSeverityId);
		}

		Long supportWorkerId = (Long)attributes.get("supportWorkerId");

		if (supportWorkerId != null) {
			setSupportWorkerId(supportWorkerId);
		}

		Integer severity = (Integer)attributes.get("severity");

		if (severity != null) {
			setSeverity(severity);
		}
	}

	/**
	* Returns the primary key of this support worker severity.
	*
	* @return the primary key of this support worker severity
	*/
	public long getPrimaryKey() {
		return _supportWorkerSeverity.getPrimaryKey();
	}

	/**
	* Sets the primary key of this support worker severity.
	*
	* @param primaryKey the primary key of this support worker severity
	*/
	public void setPrimaryKey(long primaryKey) {
		_supportWorkerSeverity.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the support worker severity ID of this support worker severity.
	*
	* @return the support worker severity ID of this support worker severity
	*/
	public long getSupportWorkerSeverityId() {
		return _supportWorkerSeverity.getSupportWorkerSeverityId();
	}

	/**
	* Sets the support worker severity ID of this support worker severity.
	*
	* @param supportWorkerSeverityId the support worker severity ID of this support worker severity
	*/
	public void setSupportWorkerSeverityId(long supportWorkerSeverityId) {
		_supportWorkerSeverity.setSupportWorkerSeverityId(supportWorkerSeverityId);
	}

	/**
	* Returns the support worker ID of this support worker severity.
	*
	* @return the support worker ID of this support worker severity
	*/
	public long getSupportWorkerId() {
		return _supportWorkerSeverity.getSupportWorkerId();
	}

	/**
	* Sets the support worker ID of this support worker severity.
	*
	* @param supportWorkerId the support worker ID of this support worker severity
	*/
	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerSeverity.setSupportWorkerId(supportWorkerId);
	}

	/**
	* Returns the severity of this support worker severity.
	*
	* @return the severity of this support worker severity
	*/
	public int getSeverity() {
		return _supportWorkerSeverity.getSeverity();
	}

	/**
	* Sets the severity of this support worker severity.
	*
	* @param severity the severity of this support worker severity
	*/
	public void setSeverity(int severity) {
		_supportWorkerSeverity.setSeverity(severity);
	}

	public boolean isNew() {
		return _supportWorkerSeverity.isNew();
	}

	public void setNew(boolean n) {
		_supportWorkerSeverity.setNew(n);
	}

	public boolean isCachedModel() {
		return _supportWorkerSeverity.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_supportWorkerSeverity.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _supportWorkerSeverity.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _supportWorkerSeverity.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_supportWorkerSeverity.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _supportWorkerSeverity.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_supportWorkerSeverity.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SupportWorkerSeverityWrapper((SupportWorkerSeverity)_supportWorkerSeverity.clone());
	}

	public int compareTo(
		com.liferay.osb.model.SupportWorkerSeverity supportWorkerSeverity) {
		return _supportWorkerSeverity.compareTo(supportWorkerSeverity);
	}

	@Override
	public int hashCode() {
		return _supportWorkerSeverity.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.SupportWorkerSeverity> toCacheModel() {
		return _supportWorkerSeverity.toCacheModel();
	}

	public com.liferay.osb.model.SupportWorkerSeverity toEscapedModel() {
		return new SupportWorkerSeverityWrapper(_supportWorkerSeverity.toEscapedModel());
	}

	public com.liferay.osb.model.SupportWorkerSeverity toUnescapedModel() {
		return new SupportWorkerSeverityWrapper(_supportWorkerSeverity.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _supportWorkerSeverity.toString();
	}

	public java.lang.String toXmlString() {
		return _supportWorkerSeverity.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportWorkerSeverity.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportWorkerSeverityWrapper)) {
			return false;
		}

		SupportWorkerSeverityWrapper supportWorkerSeverityWrapper = (SupportWorkerSeverityWrapper)obj;

		if (Validator.equals(_supportWorkerSeverity,
					supportWorkerSeverityWrapper._supportWorkerSeverity)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SupportWorkerSeverity getWrappedSupportWorkerSeverity() {
		return _supportWorkerSeverity;
	}

	public SupportWorkerSeverity getWrappedModel() {
		return _supportWorkerSeverity;
	}

	public void resetOriginalValues() {
		_supportWorkerSeverity.resetOriginalValues();
	}

	private SupportWorkerSeverity _supportWorkerSeverity;
}