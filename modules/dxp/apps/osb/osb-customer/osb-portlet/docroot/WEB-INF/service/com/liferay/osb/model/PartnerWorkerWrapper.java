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
 * This class is a wrapper for {@link PartnerWorker}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorker
 * @generated
 */
@ProviderType
public class PartnerWorkerWrapper implements PartnerWorker,
	ModelWrapper<PartnerWorker> {
	public PartnerWorkerWrapper(PartnerWorker partnerWorker) {
		_partnerWorker = partnerWorker;
	}

	@Override
	public Class<?> getModelClass() {
		return PartnerWorker.class;
	}

	@Override
	public String getModelClassName() {
		return PartnerWorker.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("partnerWorkerId", getPartnerWorkerId());
		attributes.put("userId", getUserId());
		attributes.put("partnerEntryId", getPartnerEntryId());
		attributes.put("role", getRole());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long partnerWorkerId = (Long)attributes.get("partnerWorkerId");

		if (partnerWorkerId != null) {
			setPartnerWorkerId(partnerWorkerId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long partnerEntryId = (Long)attributes.get("partnerEntryId");

		if (partnerEntryId != null) {
			setPartnerEntryId(partnerEntryId);
		}

		Integer role = (Integer)attributes.get("role");

		if (role != null) {
			setRole(role);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _partnerWorker.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _partnerWorker.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _partnerWorker.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _partnerWorker.getExpandoBridge();
	}

	@Override
	public PartnerEntry getPartnerEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerWorker.getPartnerEntry();
	}

	@Override
	public PartnerWorker toEscapedModel() {
		return new PartnerWorkerWrapper(_partnerWorker.toEscapedModel());
	}

	@Override
	public PartnerWorker toUnescapedModel() {
		return new PartnerWorkerWrapper(_partnerWorker.toUnescapedModel());
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PartnerWorker> toCacheModel() {
		return _partnerWorker.toCacheModel();
	}

	@Override
	public int compareTo(PartnerWorker partnerWorker) {
		return _partnerWorker.compareTo(partnerWorker);
	}

	/**
	* Returns the role of this partner worker.
	*
	* @return the role of this partner worker
	*/
	@Override
	public int getRole() {
		return _partnerWorker.getRole();
	}

	@Override
	public int hashCode() {
		return _partnerWorker.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _partnerWorker.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PartnerWorkerWrapper((PartnerWorker)_partnerWorker.clone());
	}

	@Override
	public java.lang.String getRoleLabel() {
		return _partnerWorker.getRoleLabel();
	}

	/**
	* Returns the user uuid of this partner worker.
	*
	* @return the user uuid of this partner worker
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _partnerWorker.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _partnerWorker.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _partnerWorker.toXmlString();
	}

	/**
	* Returns the partner entry ID of this partner worker.
	*
	* @return the partner entry ID of this partner worker
	*/
	@Override
	public long getPartnerEntryId() {
		return _partnerWorker.getPartnerEntryId();
	}

	/**
	* Returns the partner worker ID of this partner worker.
	*
	* @return the partner worker ID of this partner worker
	*/
	@Override
	public long getPartnerWorkerId() {
		return _partnerWorker.getPartnerWorkerId();
	}

	/**
	* Returns the primary key of this partner worker.
	*
	* @return the primary key of this partner worker
	*/
	@Override
	public long getPrimaryKey() {
		return _partnerWorker.getPrimaryKey();
	}

	/**
	* Returns the user ID of this partner worker.
	*
	* @return the user ID of this partner worker
	*/
	@Override
	public long getUserId() {
		return _partnerWorker.getUserId();
	}

	@Override
	public void persist() {
		_partnerWorker.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_partnerWorker.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_partnerWorker.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_partnerWorker.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_partnerWorker.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_partnerWorker.setNew(n);
	}

	/**
	* Sets the partner entry ID of this partner worker.
	*
	* @param partnerEntryId the partner entry ID of this partner worker
	*/
	@Override
	public void setPartnerEntryId(long partnerEntryId) {
		_partnerWorker.setPartnerEntryId(partnerEntryId);
	}

	/**
	* Sets the partner worker ID of this partner worker.
	*
	* @param partnerWorkerId the partner worker ID of this partner worker
	*/
	@Override
	public void setPartnerWorkerId(long partnerWorkerId) {
		_partnerWorker.setPartnerWorkerId(partnerWorkerId);
	}

	/**
	* Sets the primary key of this partner worker.
	*
	* @param primaryKey the primary key of this partner worker
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_partnerWorker.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_partnerWorker.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role of this partner worker.
	*
	* @param role the role of this partner worker
	*/
	@Override
	public void setRole(int role) {
		_partnerWorker.setRole(role);
	}

	/**
	* Sets the user ID of this partner worker.
	*
	* @param userId the user ID of this partner worker
	*/
	@Override
	public void setUserId(long userId) {
		_partnerWorker.setUserId(userId);
	}

	/**
	* Sets the user uuid of this partner worker.
	*
	* @param userUuid the user uuid of this partner worker
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_partnerWorker.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PartnerWorkerWrapper)) {
			return false;
		}

		PartnerWorkerWrapper partnerWorkerWrapper = (PartnerWorkerWrapper)obj;

		if (Objects.equals(_partnerWorker, partnerWorkerWrapper._partnerWorker)) {
			return true;
		}

		return false;
	}

	@Override
	public PartnerWorker getWrappedModel() {
		return _partnerWorker;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _partnerWorker.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _partnerWorker.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_partnerWorker.resetOriginalValues();
	}

	private final PartnerWorker _partnerWorker;
}