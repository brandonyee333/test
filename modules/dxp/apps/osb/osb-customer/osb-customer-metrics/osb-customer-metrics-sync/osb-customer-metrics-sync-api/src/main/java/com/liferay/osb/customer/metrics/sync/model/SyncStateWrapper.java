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

package com.liferay.osb.customer.metrics.sync.model;

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
 * This class is a wrapper for {@link SyncState}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncState
 * @generated
 */
@ProviderType
public class SyncStateWrapper implements SyncState, ModelWrapper<SyncState> {
	public SyncStateWrapper(SyncState syncState) {
		_syncState = syncState;
	}

	@Override
	public Class<?> getModelClass() {
		return SyncState.class;
	}

	@Override
	public String getModelClassName() {
		return SyncState.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("syncStateId", getSyncStateId());
		attributes.put("modelName", getModelName());
		attributes.put("lastRunTime", getLastRunTime());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long syncStateId = (Long)attributes.get("syncStateId");

		if (syncStateId != null) {
			setSyncStateId(syncStateId);
		}

		String modelName = (String)attributes.get("modelName");

		if (modelName != null) {
			setModelName(modelName);
		}

		Long lastRunTime = (Long)attributes.get("lastRunTime");

		if (lastRunTime != null) {
			setLastRunTime(lastRunTime);
		}
	}

	@Override
	public SyncState toEscapedModel() {
		return new SyncStateWrapper(_syncState.toEscapedModel());
	}

	@Override
	public SyncState toUnescapedModel() {
		return new SyncStateWrapper(_syncState.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _syncState.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _syncState.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _syncState.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _syncState.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SyncState> toCacheModel() {
		return _syncState.toCacheModel();
	}

	@Override
	public int compareTo(SyncState syncState) {
		return _syncState.compareTo(syncState);
	}

	@Override
	public int hashCode() {
		return _syncState.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _syncState.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SyncStateWrapper((SyncState)_syncState.clone());
	}

	/**
	* Returns the model name of this sync state.
	*
	* @return the model name of this sync state
	*/
	@Override
	public java.lang.String getModelName() {
		return _syncState.getModelName();
	}

	@Override
	public java.lang.String toString() {
		return _syncState.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _syncState.toXmlString();
	}

	/**
	* Returns the last run time of this sync state.
	*
	* @return the last run time of this sync state
	*/
	@Override
	public long getLastRunTime() {
		return _syncState.getLastRunTime();
	}

	/**
	* Returns the primary key of this sync state.
	*
	* @return the primary key of this sync state
	*/
	@Override
	public long getPrimaryKey() {
		return _syncState.getPrimaryKey();
	}

	/**
	* Returns the sync state ID of this sync state.
	*
	* @return the sync state ID of this sync state
	*/
	@Override
	public long getSyncStateId() {
		return _syncState.getSyncStateId();
	}

	@Override
	public void persist() {
		_syncState.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_syncState.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_syncState.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_syncState.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_syncState.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last run time of this sync state.
	*
	* @param lastRunTime the last run time of this sync state
	*/
	@Override
	public void setLastRunTime(long lastRunTime) {
		_syncState.setLastRunTime(lastRunTime);
	}

	/**
	* Sets the model name of this sync state.
	*
	* @param modelName the model name of this sync state
	*/
	@Override
	public void setModelName(java.lang.String modelName) {
		_syncState.setModelName(modelName);
	}

	@Override
	public void setNew(boolean n) {
		_syncState.setNew(n);
	}

	/**
	* Sets the primary key of this sync state.
	*
	* @param primaryKey the primary key of this sync state
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_syncState.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_syncState.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sync state ID of this sync state.
	*
	* @param syncStateId the sync state ID of this sync state
	*/
	@Override
	public void setSyncStateId(long syncStateId) {
		_syncState.setSyncStateId(syncStateId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncStateWrapper)) {
			return false;
		}

		SyncStateWrapper syncStateWrapper = (SyncStateWrapper)obj;

		if (Objects.equals(_syncState, syncStateWrapper._syncState)) {
			return true;
		}

		return false;
	}

	@Override
	public SyncState getWrappedModel() {
		return _syncState;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _syncState.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _syncState.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_syncState.resetOriginalValues();
	}

	private final SyncState _syncState;
}