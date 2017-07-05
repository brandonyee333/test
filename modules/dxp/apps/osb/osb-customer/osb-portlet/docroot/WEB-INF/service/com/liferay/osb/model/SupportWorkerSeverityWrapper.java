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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link SupportWorkerSeverity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerSeverity
 * @generated
 */
@ProviderType
public class SupportWorkerSeverityWrapper implements SupportWorkerSeverity,
	ModelWrapper<SupportWorkerSeverity> {
	public SupportWorkerSeverityWrapper(
		SupportWorkerSeverity supportWorkerSeverity) {
		_supportWorkerSeverity = supportWorkerSeverity;
	}

	@Override
	public Class<?> getModelClass() {
		return SupportWorkerSeverity.class;
	}

	@Override
	public String getModelClassName() {
		return SupportWorkerSeverity.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportWorkerSeverityId", getSupportWorkerSeverityId());
		attributes.put("supportWorkerId", getSupportWorkerId());
		attributes.put("severity", getSeverity());

		return attributes;
	}

	@Override
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

	@Override
	public SupportWorkerSeverity toEscapedModel() {
		return new SupportWorkerSeverityWrapper(_supportWorkerSeverity.toEscapedModel());
	}

	@Override
	public SupportWorkerSeverity toUnescapedModel() {
		return new SupportWorkerSeverityWrapper(_supportWorkerSeverity.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _supportWorkerSeverity.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _supportWorkerSeverity.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _supportWorkerSeverity.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _supportWorkerSeverity.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SupportWorkerSeverity> toCacheModel() {
		return _supportWorkerSeverity.toCacheModel();
	}

	@Override
	public int compareTo(SupportWorkerSeverity supportWorkerSeverity) {
		return _supportWorkerSeverity.compareTo(supportWorkerSeverity);
	}

	/**
	* Returns the severity of this support worker severity.
	*
	* @return the severity of this support worker severity
	*/
	@Override
	public int getSeverity() {
		return _supportWorkerSeverity.getSeverity();
	}

	@Override
	public int hashCode() {
		return _supportWorkerSeverity.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportWorkerSeverity.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SupportWorkerSeverityWrapper((SupportWorkerSeverity)_supportWorkerSeverity.clone());
	}

	@Override
	public java.lang.String toString() {
		return _supportWorkerSeverity.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _supportWorkerSeverity.toXmlString();
	}

	/**
	* Returns the primary key of this support worker severity.
	*
	* @return the primary key of this support worker severity
	*/
	@Override
	public long getPrimaryKey() {
		return _supportWorkerSeverity.getPrimaryKey();
	}

	/**
	* Returns the support worker ID of this support worker severity.
	*
	* @return the support worker ID of this support worker severity
	*/
	@Override
	public long getSupportWorkerId() {
		return _supportWorkerSeverity.getSupportWorkerId();
	}

	/**
	* Returns the support worker severity ID of this support worker severity.
	*
	* @return the support worker severity ID of this support worker severity
	*/
	@Override
	public long getSupportWorkerSeverityId() {
		return _supportWorkerSeverity.getSupportWorkerSeverityId();
	}

	@Override
	public void persist() {
		_supportWorkerSeverity.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_supportWorkerSeverity.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_supportWorkerSeverity.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_supportWorkerSeverity.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_supportWorkerSeverity.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_supportWorkerSeverity.setNew(n);
	}

	/**
	* Sets the primary key of this support worker severity.
	*
	* @param primaryKey the primary key of this support worker severity
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_supportWorkerSeverity.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_supportWorkerSeverity.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the severity of this support worker severity.
	*
	* @param severity the severity of this support worker severity
	*/
	@Override
	public void setSeverity(int severity) {
		_supportWorkerSeverity.setSeverity(severity);
	}

	/**
	* Sets the support worker ID of this support worker severity.
	*
	* @param supportWorkerId the support worker ID of this support worker severity
	*/
	@Override
	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerSeverity.setSupportWorkerId(supportWorkerId);
	}

	/**
	* Sets the support worker severity ID of this support worker severity.
	*
	* @param supportWorkerSeverityId the support worker severity ID of this support worker severity
	*/
	@Override
	public void setSupportWorkerSeverityId(long supportWorkerSeverityId) {
		_supportWorkerSeverity.setSupportWorkerSeverityId(supportWorkerSeverityId);
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

		if (Objects.equals(_supportWorkerSeverity,
					supportWorkerSeverityWrapper._supportWorkerSeverity)) {
			return true;
		}

		return false;
	}

	@Override
	public SupportWorkerSeverity getWrappedModel() {
		return _supportWorkerSeverity;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _supportWorkerSeverity.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _supportWorkerSeverity.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_supportWorkerSeverity.resetOriginalValues();
	}

	private final SupportWorkerSeverity _supportWorkerSeverity;
}