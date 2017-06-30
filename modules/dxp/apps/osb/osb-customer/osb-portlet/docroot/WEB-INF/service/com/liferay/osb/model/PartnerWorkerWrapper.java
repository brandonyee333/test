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
 * This class is a wrapper for {@link PartnerWorker}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PartnerWorker
 * @generated
 */
public class PartnerWorkerWrapper implements PartnerWorker,
	ModelWrapper<PartnerWorker> {
	public PartnerWorkerWrapper(PartnerWorker partnerWorker) {
		_partnerWorker = partnerWorker;
	}

	public Class<?> getModelClass() {
		return PartnerWorker.class;
	}

	public String getModelClassName() {
		return PartnerWorker.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("partnerWorkerId", getPartnerWorkerId());
		attributes.put("userId", getUserId());
		attributes.put("partnerEntryId", getPartnerEntryId());
		attributes.put("role", getRole());
		attributes.put("notifications", getNotifications());

		return attributes;
	}

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

		Integer notifications = (Integer)attributes.get("notifications");

		if (notifications != null) {
			setNotifications(notifications);
		}
	}

	/**
	* Returns the primary key of this partner worker.
	*
	* @return the primary key of this partner worker
	*/
	public long getPrimaryKey() {
		return _partnerWorker.getPrimaryKey();
	}

	/**
	* Sets the primary key of this partner worker.
	*
	* @param primaryKey the primary key of this partner worker
	*/
	public void setPrimaryKey(long primaryKey) {
		_partnerWorker.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the partner worker ID of this partner worker.
	*
	* @return the partner worker ID of this partner worker
	*/
	public long getPartnerWorkerId() {
		return _partnerWorker.getPartnerWorkerId();
	}

	/**
	* Sets the partner worker ID of this partner worker.
	*
	* @param partnerWorkerId the partner worker ID of this partner worker
	*/
	public void setPartnerWorkerId(long partnerWorkerId) {
		_partnerWorker.setPartnerWorkerId(partnerWorkerId);
	}

	/**
	* Returns the user ID of this partner worker.
	*
	* @return the user ID of this partner worker
	*/
	public long getUserId() {
		return _partnerWorker.getUserId();
	}

	/**
	* Sets the user ID of this partner worker.
	*
	* @param userId the user ID of this partner worker
	*/
	public void setUserId(long userId) {
		_partnerWorker.setUserId(userId);
	}

	/**
	* Returns the user uuid of this partner worker.
	*
	* @return the user uuid of this partner worker
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorker.getUserUuid();
	}

	/**
	* Sets the user uuid of this partner worker.
	*
	* @param userUuid the user uuid of this partner worker
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_partnerWorker.setUserUuid(userUuid);
	}

	/**
	* Returns the partner entry ID of this partner worker.
	*
	* @return the partner entry ID of this partner worker
	*/
	public long getPartnerEntryId() {
		return _partnerWorker.getPartnerEntryId();
	}

	/**
	* Sets the partner entry ID of this partner worker.
	*
	* @param partnerEntryId the partner entry ID of this partner worker
	*/
	public void setPartnerEntryId(long partnerEntryId) {
		_partnerWorker.setPartnerEntryId(partnerEntryId);
	}

	/**
	* Returns the role of this partner worker.
	*
	* @return the role of this partner worker
	*/
	public int getRole() {
		return _partnerWorker.getRole();
	}

	/**
	* Sets the role of this partner worker.
	*
	* @param role the role of this partner worker
	*/
	public void setRole(int role) {
		_partnerWorker.setRole(role);
	}

	/**
	* Returns the notifications of this partner worker.
	*
	* @return the notifications of this partner worker
	*/
	public int getNotifications() {
		return _partnerWorker.getNotifications();
	}

	/**
	* Sets the notifications of this partner worker.
	*
	* @param notifications the notifications of this partner worker
	*/
	public void setNotifications(int notifications) {
		_partnerWorker.setNotifications(notifications);
	}

	public boolean isNew() {
		return _partnerWorker.isNew();
	}

	public void setNew(boolean n) {
		_partnerWorker.setNew(n);
	}

	public boolean isCachedModel() {
		return _partnerWorker.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_partnerWorker.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _partnerWorker.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _partnerWorker.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_partnerWorker.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _partnerWorker.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_partnerWorker.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new PartnerWorkerWrapper((PartnerWorker)_partnerWorker.clone());
	}

	public int compareTo(com.liferay.osb.model.PartnerWorker partnerWorker) {
		return _partnerWorker.compareTo(partnerWorker);
	}

	@Override
	public int hashCode() {
		return _partnerWorker.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.PartnerWorker> toCacheModel() {
		return _partnerWorker.toCacheModel();
	}

	public com.liferay.osb.model.PartnerWorker toEscapedModel() {
		return new PartnerWorkerWrapper(_partnerWorker.toEscapedModel());
	}

	public com.liferay.osb.model.PartnerWorker toUnescapedModel() {
		return new PartnerWorkerWrapper(_partnerWorker.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _partnerWorker.toString();
	}

	public java.lang.String toXmlString() {
		return _partnerWorker.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_partnerWorker.persist();
	}

	public java.lang.String getNotificationsLabel() {
		return _partnerWorker.getNotificationsLabel();
	}

	public com.liferay.osb.model.PartnerEntry getPartnerEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorker.getPartnerEntry();
	}

	public java.lang.String getRoleLabel() {
		return _partnerWorker.getRoleLabel();
	}

	public boolean isActive()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerWorker.isActive();
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

		if (Validator.equals(_partnerWorker, partnerWorkerWrapper._partnerWorker)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public PartnerWorker getWrappedPartnerWorker() {
		return _partnerWorker;
	}

	public PartnerWorker getWrappedModel() {
		return _partnerWorker;
	}

	public void resetOriginalValues() {
		_partnerWorker.resetOriginalValues();
	}

	private PartnerWorker _partnerWorker;
}