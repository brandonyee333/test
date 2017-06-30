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
 * This class is a wrapper for {@link SupportWorkerComponent}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportWorkerComponent
 * @generated
 */
public class SupportWorkerComponentWrapper implements SupportWorkerComponent,
	ModelWrapper<SupportWorkerComponent> {
	public SupportWorkerComponentWrapper(
		SupportWorkerComponent supportWorkerComponent) {
		_supportWorkerComponent = supportWorkerComponent;
	}

	public Class<?> getModelClass() {
		return SupportWorkerComponent.class;
	}

	public String getModelClassName() {
		return SupportWorkerComponent.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportWorkerComponentId", getSupportWorkerComponentId());
		attributes.put("supportWorkerId", getSupportWorkerId());
		attributes.put("component", getComponent());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportWorkerComponentId = (Long)attributes.get(
				"supportWorkerComponentId");

		if (supportWorkerComponentId != null) {
			setSupportWorkerComponentId(supportWorkerComponentId);
		}

		Long supportWorkerId = (Long)attributes.get("supportWorkerId");

		if (supportWorkerId != null) {
			setSupportWorkerId(supportWorkerId);
		}

		Integer component = (Integer)attributes.get("component");

		if (component != null) {
			setComponent(component);
		}
	}

	/**
	* Returns the primary key of this support worker component.
	*
	* @return the primary key of this support worker component
	*/
	public long getPrimaryKey() {
		return _supportWorkerComponent.getPrimaryKey();
	}

	/**
	* Sets the primary key of this support worker component.
	*
	* @param primaryKey the primary key of this support worker component
	*/
	public void setPrimaryKey(long primaryKey) {
		_supportWorkerComponent.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the support worker component ID of this support worker component.
	*
	* @return the support worker component ID of this support worker component
	*/
	public long getSupportWorkerComponentId() {
		return _supportWorkerComponent.getSupportWorkerComponentId();
	}

	/**
	* Sets the support worker component ID of this support worker component.
	*
	* @param supportWorkerComponentId the support worker component ID of this support worker component
	*/
	public void setSupportWorkerComponentId(long supportWorkerComponentId) {
		_supportWorkerComponent.setSupportWorkerComponentId(supportWorkerComponentId);
	}

	/**
	* Returns the support worker ID of this support worker component.
	*
	* @return the support worker ID of this support worker component
	*/
	public long getSupportWorkerId() {
		return _supportWorkerComponent.getSupportWorkerId();
	}

	/**
	* Sets the support worker ID of this support worker component.
	*
	* @param supportWorkerId the support worker ID of this support worker component
	*/
	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerComponent.setSupportWorkerId(supportWorkerId);
	}

	/**
	* Returns the component of this support worker component.
	*
	* @return the component of this support worker component
	*/
	public int getComponent() {
		return _supportWorkerComponent.getComponent();
	}

	/**
	* Sets the component of this support worker component.
	*
	* @param component the component of this support worker component
	*/
	public void setComponent(int component) {
		_supportWorkerComponent.setComponent(component);
	}

	public boolean isNew() {
		return _supportWorkerComponent.isNew();
	}

	public void setNew(boolean n) {
		_supportWorkerComponent.setNew(n);
	}

	public boolean isCachedModel() {
		return _supportWorkerComponent.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_supportWorkerComponent.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _supportWorkerComponent.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _supportWorkerComponent.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_supportWorkerComponent.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _supportWorkerComponent.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_supportWorkerComponent.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SupportWorkerComponentWrapper((SupportWorkerComponent)_supportWorkerComponent.clone());
	}

	public int compareTo(
		com.liferay.osb.model.SupportWorkerComponent supportWorkerComponent) {
		return _supportWorkerComponent.compareTo(supportWorkerComponent);
	}

	@Override
	public int hashCode() {
		return _supportWorkerComponent.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.SupportWorkerComponent> toCacheModel() {
		return _supportWorkerComponent.toCacheModel();
	}

	public com.liferay.osb.model.SupportWorkerComponent toEscapedModel() {
		return new SupportWorkerComponentWrapper(_supportWorkerComponent.toEscapedModel());
	}

	public com.liferay.osb.model.SupportWorkerComponent toUnescapedModel() {
		return new SupportWorkerComponentWrapper(_supportWorkerComponent.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _supportWorkerComponent.toString();
	}

	public java.lang.String toXmlString() {
		return _supportWorkerComponent.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportWorkerComponent.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportWorkerComponentWrapper)) {
			return false;
		}

		SupportWorkerComponentWrapper supportWorkerComponentWrapper = (SupportWorkerComponentWrapper)obj;

		if (Validator.equals(_supportWorkerComponent,
					supportWorkerComponentWrapper._supportWorkerComponent)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SupportWorkerComponent getWrappedSupportWorkerComponent() {
		return _supportWorkerComponent;
	}

	public SupportWorkerComponent getWrappedModel() {
		return _supportWorkerComponent;
	}

	public void resetOriginalValues() {
		_supportWorkerComponent.resetOriginalValues();
	}

	private SupportWorkerComponent _supportWorkerComponent;
}