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
 * This class is a wrapper for {@link SupportWorker}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportWorker
 * @generated
 */
public class SupportWorkerWrapper implements SupportWorker,
	ModelWrapper<SupportWorker> {
	public SupportWorkerWrapper(SupportWorker supportWorker) {
		_supportWorker = supportWorker;
	}

	public Class<?> getModelClass() {
		return SupportWorker.class;
	}

	public String getModelClassName() {
		return SupportWorker.class.getName();
	}

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

	/**
	* Returns the primary key of this support worker.
	*
	* @return the primary key of this support worker
	*/
	public long getPrimaryKey() {
		return _supportWorker.getPrimaryKey();
	}

	/**
	* Sets the primary key of this support worker.
	*
	* @param primaryKey the primary key of this support worker
	*/
	public void setPrimaryKey(long primaryKey) {
		_supportWorker.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the support worker ID of this support worker.
	*
	* @return the support worker ID of this support worker
	*/
	public long getSupportWorkerId() {
		return _supportWorker.getSupportWorkerId();
	}

	/**
	* Sets the support worker ID of this support worker.
	*
	* @param supportWorkerId the support worker ID of this support worker
	*/
	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorker.setSupportWorkerId(supportWorkerId);
	}

	/**
	* Returns the user ID of this support worker.
	*
	* @return the user ID of this support worker
	*/
	public long getUserId() {
		return _supportWorker.getUserId();
	}

	/**
	* Sets the user ID of this support worker.
	*
	* @param userId the user ID of this support worker
	*/
	public void setUserId(long userId) {
		_supportWorker.setUserId(userId);
	}

	/**
	* Returns the user uuid of this support worker.
	*
	* @return the user uuid of this support worker
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorker.getUserUuid();
	}

	/**
	* Sets the user uuid of this support worker.
	*
	* @param userUuid the user uuid of this support worker
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_supportWorker.setUserUuid(userUuid);
	}

	/**
	* Returns the support team ID of this support worker.
	*
	* @return the support team ID of this support worker
	*/
	public long getSupportTeamId() {
		return _supportWorker.getSupportTeamId();
	}

	/**
	* Sets the support team ID of this support worker.
	*
	* @param supportTeamId the support team ID of this support worker
	*/
	public void setSupportTeamId(long supportTeamId) {
		_supportWorker.setSupportTeamId(supportTeamId);
	}

	/**
	* Returns the support labor ID of this support worker.
	*
	* @return the support labor ID of this support worker
	*/
	public long getSupportLaborId() {
		return _supportWorker.getSupportLaborId();
	}

	/**
	* Sets the support labor ID of this support worker.
	*
	* @param supportLaborId the support labor ID of this support worker
	*/
	public void setSupportLaborId(long supportLaborId) {
		_supportWorker.setSupportLaborId(supportLaborId);
	}

	/**
	* Returns the auto assign of this support worker.
	*
	* @return the auto assign of this support worker
	*/
	public boolean getAutoAssign() {
		return _supportWorker.getAutoAssign();
	}

	/**
	* Returns <code>true</code> if this support worker is auto assign.
	*
	* @return <code>true</code> if this support worker is auto assign; <code>false</code> otherwise
	*/
	public boolean isAutoAssign() {
		return _supportWorker.isAutoAssign();
	}

	/**
	* Sets whether this support worker is auto assign.
	*
	* @param autoAssign the auto assign of this support worker
	*/
	public void setAutoAssign(boolean autoAssign) {
		_supportWorker.setAutoAssign(autoAssign);
	}

	/**
	* Returns the assigned work of this support worker.
	*
	* @return the assigned work of this support worker
	*/
	public double getAssignedWork() {
		return _supportWorker.getAssignedWork();
	}

	/**
	* Sets the assigned work of this support worker.
	*
	* @param assignedWork the assigned work of this support worker
	*/
	public void setAssignedWork(double assignedWork) {
		_supportWorker.setAssignedWork(assignedWork);
	}

	/**
	* Returns the max work of this support worker.
	*
	* @return the max work of this support worker
	*/
	public double getMaxWork() {
		return _supportWorker.getMaxWork();
	}

	/**
	* Sets the max work of this support worker.
	*
	* @param maxWork the max work of this support worker
	*/
	public void setMaxWork(double maxWork) {
		_supportWorker.setMaxWork(maxWork);
	}

	/**
	* Returns the escalation level of this support worker.
	*
	* @return the escalation level of this support worker
	*/
	public int getEscalationLevel() {
		return _supportWorker.getEscalationLevel();
	}

	/**
	* Sets the escalation level of this support worker.
	*
	* @param escalationLevel the escalation level of this support worker
	*/
	public void setEscalationLevel(int escalationLevel) {
		_supportWorker.setEscalationLevel(escalationLevel);
	}

	/**
	* Returns the role of this support worker.
	*
	* @return the role of this support worker
	*/
	public int getRole() {
		return _supportWorker.getRole();
	}

	/**
	* Sets the role of this support worker.
	*
	* @param role the role of this support worker
	*/
	public void setRole(int role) {
		_supportWorker.setRole(role);
	}

	/**
	* Returns the escalation level2 role of this support worker.
	*
	* @return the escalation level2 role of this support worker
	*/
	public int getEscalationLevel2Role() {
		return _supportWorker.getEscalationLevel2Role();
	}

	/**
	* Sets the escalation level2 role of this support worker.
	*
	* @param escalationLevel2Role the escalation level2 role of this support worker
	*/
	public void setEscalationLevel2Role(int escalationLevel2Role) {
		_supportWorker.setEscalationLevel2Role(escalationLevel2Role);
	}

	/**
	* Returns the notifications of this support worker.
	*
	* @return the notifications of this support worker
	*/
	public int getNotifications() {
		return _supportWorker.getNotifications();
	}

	/**
	* Sets the notifications of this support worker.
	*
	* @param notifications the notifications of this support worker
	*/
	public void setNotifications(int notifications) {
		_supportWorker.setNotifications(notifications);
	}

	/**
	* Returns the clocked in of this support worker.
	*
	* @return the clocked in of this support worker
	*/
	public boolean getClockedIn() {
		return _supportWorker.getClockedIn();
	}

	/**
	* Returns <code>true</code> if this support worker is clocked in.
	*
	* @return <code>true</code> if this support worker is clocked in; <code>false</code> otherwise
	*/
	public boolean isClockedIn() {
		return _supportWorker.isClockedIn();
	}

	/**
	* Sets whether this support worker is clocked in.
	*
	* @param clockedIn the clocked in of this support worker
	*/
	public void setClockedIn(boolean clockedIn) {
		_supportWorker.setClockedIn(clockedIn);
	}

	public boolean isNew() {
		return _supportWorker.isNew();
	}

	public void setNew(boolean n) {
		_supportWorker.setNew(n);
	}

	public boolean isCachedModel() {
		return _supportWorker.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_supportWorker.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _supportWorker.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _supportWorker.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_supportWorker.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _supportWorker.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_supportWorker.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SupportWorkerWrapper((SupportWorker)_supportWorker.clone());
	}

	public int compareTo(com.liferay.osb.model.SupportWorker supportWorker) {
		return _supportWorker.compareTo(supportWorker);
	}

	@Override
	public int hashCode() {
		return _supportWorker.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.SupportWorker> toCacheModel() {
		return _supportWorker.toCacheModel();
	}

	public com.liferay.osb.model.SupportWorker toEscapedModel() {
		return new SupportWorkerWrapper(_supportWorker.toEscapedModel());
	}

	public com.liferay.osb.model.SupportWorker toUnescapedModel() {
		return new SupportWorkerWrapper(_supportWorker.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _supportWorker.toString();
	}

	public java.lang.String toXmlString() {
		return _supportWorker.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportWorker.persist();
	}

	public java.util.List<java.lang.Integer> getAccountTiers()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorker.getAccountTiers();
	}

	public java.util.List<java.lang.Integer> getComponents()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorker.getComponents();
	}

	public java.lang.String getNotificationsLabel() {
		return _supportWorker.getNotificationsLabel();
	}

	public java.lang.String getRoleLabel() {
		return _supportWorker.getRoleLabel();
	}

	public java.util.List<java.lang.Integer> getSeverities()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportWorker.getSeverities();
	}

	public com.liferay.osb.model.SupportLabor getSupportLabor()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorker.getSupportLabor();
	}

	public com.liferay.osb.model.SupportTeam getSupportTeam()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorker.getSupportTeam();
	}

	public java.lang.Long getTimeUntilClose()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorker.getTimeUntilClose();
	}

	public java.lang.Long getTimeUntilOpen()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorker.getTimeUntilOpen();
	}

	public boolean isActive()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorker.isActive();
	}

	public boolean isAvailable()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportWorker.isAvailable();
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

		if (Validator.equals(_supportWorker, supportWorkerWrapper._supportWorker)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SupportWorker getWrappedSupportWorker() {
		return _supportWorker;
	}

	public SupportWorker getWrappedModel() {
		return _supportWorker;
	}

	public void resetOriginalValues() {
		_supportWorker.resetOriginalValues();
	}

	private SupportWorker _supportWorker;
}