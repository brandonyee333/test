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
 * This class is a wrapper for {@link SupportWorker}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorker
 * @generated
 */
@ProviderType
public class SupportWorkerWrapper implements SupportWorker,
	ModelWrapper<SupportWorker> {
	public SupportWorkerWrapper(SupportWorker supportWorker) {
		_supportWorker = supportWorker;
	}

	@Override
	public Class<?> getModelClass() {
		return SupportWorker.class;
	}

	@Override
	public String getModelClassName() {
		return SupportWorker.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportWorkerId", getSupportWorkerId());
		attributes.put("userId", getUserId());
		attributes.put("supportTeamId", getSupportTeamId());
		attributes.put("supportLaborId", getSupportLaborId());
		attributes.put("autoAssign", getAutoAssign());
		attributes.put("assignedWork", getAssignedWork());
		attributes.put("maxWork", getMaxWork());
		attributes.put("escalationLevel", getEscalationLevel());
		attributes.put("role", getRole());
		attributes.put("escalationLevel2Role", getEscalationLevel2Role());
		attributes.put("notifications", getNotifications());
		attributes.put("clockedIn", getClockedIn());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportWorkerId = (Long)attributes.get("supportWorkerId");

		if (supportWorkerId != null) {
			setSupportWorkerId(supportWorkerId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long supportTeamId = (Long)attributes.get("supportTeamId");

		if (supportTeamId != null) {
			setSupportTeamId(supportTeamId);
		}

		Long supportLaborId = (Long)attributes.get("supportLaborId");

		if (supportLaborId != null) {
			setSupportLaborId(supportLaborId);
		}

		Boolean autoAssign = (Boolean)attributes.get("autoAssign");

		if (autoAssign != null) {
			setAutoAssign(autoAssign);
		}

		Double assignedWork = (Double)attributes.get("assignedWork");

		if (assignedWork != null) {
			setAssignedWork(assignedWork);
		}

		Double maxWork = (Double)attributes.get("maxWork");

		if (maxWork != null) {
			setMaxWork(maxWork);
		}

		Integer escalationLevel = (Integer)attributes.get("escalationLevel");

		if (escalationLevel != null) {
			setEscalationLevel(escalationLevel);
		}

		Integer role = (Integer)attributes.get("role");

		if (role != null) {
			setRole(role);
		}

		Integer escalationLevel2Role = (Integer)attributes.get(
				"escalationLevel2Role");

		if (escalationLevel2Role != null) {
			setEscalationLevel2Role(escalationLevel2Role);
		}

		Integer notifications = (Integer)attributes.get("notifications");

		if (notifications != null) {
			setNotifications(notifications);
		}

		Boolean clockedIn = (Boolean)attributes.get("clockedIn");

		if (clockedIn != null) {
			setClockedIn(clockedIn);
		}
	}

	@Override
	public SupportLabor getSupportLabor()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorker.getSupportLabor();
	}

	@Override
	public SupportTeam getSupportTeam()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorker.getSupportTeam();
	}

	@Override
	public SupportWorker toEscapedModel() {
		return new SupportWorkerWrapper(_supportWorker.toEscapedModel());
	}

	@Override
	public SupportWorker toUnescapedModel() {
		return new SupportWorkerWrapper(_supportWorker.toUnescapedModel());
	}

	/**
	* Returns the auto assign of this support worker.
	*
	* @return the auto assign of this support worker
	*/
	@Override
	public boolean getAutoAssign() {
		return _supportWorker.getAutoAssign();
	}

	/**
	* Returns the clocked in of this support worker.
	*
	* @return the clocked in of this support worker
	*/
	@Override
	public boolean getClockedIn() {
		return _supportWorker.getClockedIn();
	}

	@Override
	public boolean isActive()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorker.isActive();
	}

	/**
	* Returns <code>true</code> if this support worker is auto assign.
	*
	* @return <code>true</code> if this support worker is auto assign; <code>false</code> otherwise
	*/
	@Override
	public boolean isAutoAssign() {
		return _supportWorker.isAutoAssign();
	}

	@Override
	public boolean isAvailable()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorker.isAvailable();
	}

	@Override
	public boolean isCachedModel() {
		return _supportWorker.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this support worker is clocked in.
	*
	* @return <code>true</code> if this support worker is clocked in; <code>false</code> otherwise
	*/
	@Override
	public boolean isClockedIn() {
		return _supportWorker.isClockedIn();
	}

	@Override
	public boolean isEscapedModel() {
		return _supportWorker.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _supportWorker.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _supportWorker.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SupportWorker> toCacheModel() {
		return _supportWorker.toCacheModel();
	}

	/**
	* Returns the assigned work of this support worker.
	*
	* @return the assigned work of this support worker
	*/
	@Override
	public double getAssignedWork() {
		return _supportWorker.getAssignedWork();
	}

	/**
	* Returns the max work of this support worker.
	*
	* @return the max work of this support worker
	*/
	@Override
	public double getMaxWork() {
		return _supportWorker.getMaxWork();
	}

	@Override
	public int compareTo(SupportWorker supportWorker) {
		return _supportWorker.compareTo(supportWorker);
	}

	/**
	* Returns the escalation level of this support worker.
	*
	* @return the escalation level of this support worker
	*/
	@Override
	public int getEscalationLevel() {
		return _supportWorker.getEscalationLevel();
	}

	/**
	* Returns the escalation level2 role of this support worker.
	*
	* @return the escalation level2 role of this support worker
	*/
	@Override
	public int getEscalationLevel2Role() {
		return _supportWorker.getEscalationLevel2Role();
	}

	/**
	* Returns the notifications of this support worker.
	*
	* @return the notifications of this support worker
	*/
	@Override
	public int getNotifications() {
		return _supportWorker.getNotifications();
	}

	/**
	* Returns the role of this support worker.
	*
	* @return the role of this support worker
	*/
	@Override
	public int getRole() {
		return _supportWorker.getRole();
	}

	@Override
	public int hashCode() {
		return _supportWorker.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportWorker.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Long getTimeUntilClose()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorker.getTimeUntilClose();
	}

	@Override
	public java.lang.Long getTimeUntilOpen()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportWorker.getTimeUntilOpen();
	}

	@Override
	public java.lang.Object clone() {
		return new SupportWorkerWrapper((SupportWorker)_supportWorker.clone());
	}

	@Override
	public java.lang.String getNotificationsLabel() {
		return _supportWorker.getNotificationsLabel();
	}

	@Override
	public java.lang.String getRoleLabel() {
		return _supportWorker.getRoleLabel();
	}

	/**
	* Returns the user uuid of this support worker.
	*
	* @return the user uuid of this support worker
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _supportWorker.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _supportWorker.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _supportWorker.toXmlString();
	}

	@Override
	public java.util.List<java.lang.Integer> getAccountTiers() {
		return _supportWorker.getAccountTiers();
	}

	@Override
	public java.util.List<java.lang.Integer> getComponents() {
		return _supportWorker.getComponents();
	}

	@Override
	public java.util.List<java.lang.Integer> getSeverities() {
		return _supportWorker.getSeverities();
	}

	/**
	* Returns the primary key of this support worker.
	*
	* @return the primary key of this support worker
	*/
	@Override
	public long getPrimaryKey() {
		return _supportWorker.getPrimaryKey();
	}

	/**
	* Returns the support labor ID of this support worker.
	*
	* @return the support labor ID of this support worker
	*/
	@Override
	public long getSupportLaborId() {
		return _supportWorker.getSupportLaborId();
	}

	/**
	* Returns the support team ID of this support worker.
	*
	* @return the support team ID of this support worker
	*/
	@Override
	public long getSupportTeamId() {
		return _supportWorker.getSupportTeamId();
	}

	/**
	* Returns the support worker ID of this support worker.
	*
	* @return the support worker ID of this support worker
	*/
	@Override
	public long getSupportWorkerId() {
		return _supportWorker.getSupportWorkerId();
	}

	/**
	* Returns the user ID of this support worker.
	*
	* @return the user ID of this support worker
	*/
	@Override
	public long getUserId() {
		return _supportWorker.getUserId();
	}

	@Override
	public void persist() {
		_supportWorker.persist();
	}

	/**
	* Sets the assigned work of this support worker.
	*
	* @param assignedWork the assigned work of this support worker
	*/
	@Override
	public void setAssignedWork(double assignedWork) {
		_supportWorker.setAssignedWork(assignedWork);
	}

	/**
	* Sets whether this support worker is auto assign.
	*
	* @param autoAssign the auto assign of this support worker
	*/
	@Override
	public void setAutoAssign(boolean autoAssign) {
		_supportWorker.setAutoAssign(autoAssign);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_supportWorker.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this support worker is clocked in.
	*
	* @param clockedIn the clocked in of this support worker
	*/
	@Override
	public void setClockedIn(boolean clockedIn) {
		_supportWorker.setClockedIn(clockedIn);
	}

	/**
	* Sets the escalation level of this support worker.
	*
	* @param escalationLevel the escalation level of this support worker
	*/
	@Override
	public void setEscalationLevel(int escalationLevel) {
		_supportWorker.setEscalationLevel(escalationLevel);
	}

	/**
	* Sets the escalation level2 role of this support worker.
	*
	* @param escalationLevel2Role the escalation level2 role of this support worker
	*/
	@Override
	public void setEscalationLevel2Role(int escalationLevel2Role) {
		_supportWorker.setEscalationLevel2Role(escalationLevel2Role);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_supportWorker.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_supportWorker.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_supportWorker.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the max work of this support worker.
	*
	* @param maxWork the max work of this support worker
	*/
	@Override
	public void setMaxWork(double maxWork) {
		_supportWorker.setMaxWork(maxWork);
	}

	@Override
	public void setNew(boolean n) {
		_supportWorker.setNew(n);
	}

	/**
	* Sets the notifications of this support worker.
	*
	* @param notifications the notifications of this support worker
	*/
	@Override
	public void setNotifications(int notifications) {
		_supportWorker.setNotifications(notifications);
	}

	/**
	* Sets the primary key of this support worker.
	*
	* @param primaryKey the primary key of this support worker
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_supportWorker.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_supportWorker.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role of this support worker.
	*
	* @param role the role of this support worker
	*/
	@Override
	public void setRole(int role) {
		_supportWorker.setRole(role);
	}

	/**
	* Sets the support labor ID of this support worker.
	*
	* @param supportLaborId the support labor ID of this support worker
	*/
	@Override
	public void setSupportLaborId(long supportLaborId) {
		_supportWorker.setSupportLaborId(supportLaborId);
	}

	/**
	* Sets the support team ID of this support worker.
	*
	* @param supportTeamId the support team ID of this support worker
	*/
	@Override
	public void setSupportTeamId(long supportTeamId) {
		_supportWorker.setSupportTeamId(supportTeamId);
	}

	/**
	* Sets the support worker ID of this support worker.
	*
	* @param supportWorkerId the support worker ID of this support worker
	*/
	@Override
	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorker.setSupportWorkerId(supportWorkerId);
	}

	/**
	* Sets the user ID of this support worker.
	*
	* @param userId the user ID of this support worker
	*/
	@Override
	public void setUserId(long userId) {
		_supportWorker.setUserId(userId);
	}

	/**
	* Sets the user uuid of this support worker.
	*
	* @param userUuid the user uuid of this support worker
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_supportWorker.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportWorkerWrapper)) {
			return false;
		}

		SupportWorkerWrapper supportWorkerWrapper = (SupportWorkerWrapper)obj;

		if (Objects.equals(_supportWorker, supportWorkerWrapper._supportWorker)) {
			return true;
		}

		return false;
	}

	@Override
	public SupportWorker getWrappedModel() {
		return _supportWorker;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _supportWorker.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _supportWorker.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_supportWorker.resetOriginalValues();
	}

	private final SupportWorker _supportWorker;
}