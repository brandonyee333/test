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
 * This class is a wrapper for {@link SupportWorkerComponent}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerComponent
 * @generated
 */
@ProviderType
public class SupportWorkerComponentWrapper implements SupportWorkerComponent,
	ModelWrapper<SupportWorkerComponent> {
	public SupportWorkerComponentWrapper(
		SupportWorkerComponent supportWorkerComponent) {
		_supportWorkerComponent = supportWorkerComponent;
	}

	@Override
	public Class<?> getModelClass() {
		return SupportWorkerComponent.class;
	}

	@Override
	public String getModelClassName() {
		return SupportWorkerComponent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportWorkerComponentId", getSupportWorkerComponentId());
		attributes.put("supportWorkerId", getSupportWorkerId());
		attributes.put("component", getComponent());

		return attributes;
	}

	@Override
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

	@Override
	public SupportWorkerComponent toEscapedModel() {
		return new SupportWorkerComponentWrapper(_supportWorkerComponent.toEscapedModel());
	}

	@Override
	public SupportWorkerComponent toUnescapedModel() {
		return new SupportWorkerComponentWrapper(_supportWorkerComponent.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _supportWorkerComponent.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _supportWorkerComponent.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _supportWorkerComponent.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _supportWorkerComponent.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SupportWorkerComponent> toCacheModel() {
		return _supportWorkerComponent.toCacheModel();
	}

	@Override
	public int compareTo(SupportWorkerComponent supportWorkerComponent) {
		return _supportWorkerComponent.compareTo(supportWorkerComponent);
	}

	/**
	* Returns the component of this support worker component.
	*
	* @return the component of this support worker component
	*/
	@Override
	public int getComponent() {
		return _supportWorkerComponent.getComponent();
	}

	@Override
	public int hashCode() {
		return _supportWorkerComponent.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportWorkerComponent.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SupportWorkerComponentWrapper((SupportWorkerComponent)_supportWorkerComponent.clone());
	}

	@Override
	public java.lang.String toString() {
		return _supportWorkerComponent.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _supportWorkerComponent.toXmlString();
	}

	/**
	* Returns the primary key of this support worker component.
	*
	* @return the primary key of this support worker component
	*/
	@Override
	public long getPrimaryKey() {
		return _supportWorkerComponent.getPrimaryKey();
	}

	/**
	* Returns the support worker component ID of this support worker component.
	*
	* @return the support worker component ID of this support worker component
	*/
	@Override
	public long getSupportWorkerComponentId() {
		return _supportWorkerComponent.getSupportWorkerComponentId();
	}

	/**
	* Returns the support worker ID of this support worker component.
	*
	* @return the support worker ID of this support worker component
	*/
	@Override
	public long getSupportWorkerId() {
		return _supportWorkerComponent.getSupportWorkerId();
	}

	@Override
	public void persist() {
		_supportWorkerComponent.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_supportWorkerComponent.setCachedModel(cachedModel);
	}

	/**
	* Sets the component of this support worker component.
	*
	* @param component the component of this support worker component
	*/
	@Override
	public void setComponent(int component) {
		_supportWorkerComponent.setComponent(component);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_supportWorkerComponent.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_supportWorkerComponent.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_supportWorkerComponent.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_supportWorkerComponent.setNew(n);
	}

	/**
	* Sets the primary key of this support worker component.
	*
	* @param primaryKey the primary key of this support worker component
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_supportWorkerComponent.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_supportWorkerComponent.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the support worker component ID of this support worker component.
	*
	* @param supportWorkerComponentId the support worker component ID of this support worker component
	*/
	@Override
	public void setSupportWorkerComponentId(long supportWorkerComponentId) {
		_supportWorkerComponent.setSupportWorkerComponentId(supportWorkerComponentId);
	}

	/**
	* Sets the support worker ID of this support worker component.
	*
	* @param supportWorkerId the support worker ID of this support worker component
	*/
	@Override
	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerComponent.setSupportWorkerId(supportWorkerId);
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

		if (Objects.equals(_supportWorkerComponent,
					supportWorkerComponentWrapper._supportWorkerComponent)) {
			return true;
		}

		return false;
	}

	@Override
	public SupportWorkerComponent getWrappedModel() {
		return _supportWorkerComponent;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _supportWorkerComponent.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _supportWorkerComponent.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_supportWorkerComponent.resetOriginalValues();
	}

	private final SupportWorkerComponent _supportWorkerComponent;
}