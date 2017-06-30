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
 * This class is a wrapper for {@link AccountWorker}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountWorker
 * @generated
 */
public class AccountWorkerWrapper implements AccountWorker,
	ModelWrapper<AccountWorker> {
	public AccountWorkerWrapper(AccountWorker accountWorker) {
		_accountWorker = accountWorker;
	}

	public Class<?> getModelClass() {
		return AccountWorker.class;
	}

	public String getModelClassName() {
		return AccountWorker.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountWorkerId", getAccountWorkerId());
		attributes.put("userId", getUserId());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("role", getRole());
		attributes.put("notifications", getNotifications());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountWorkerId = (Long)attributes.get("accountWorkerId");

		if (accountWorkerId != null) {
			setAccountWorkerId(accountWorkerId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
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
	* Returns the primary key of this account worker.
	*
	* @return the primary key of this account worker
	*/
	public long getPrimaryKey() {
		return _accountWorker.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account worker.
	*
	* @param primaryKey the primary key of this account worker
	*/
	public void setPrimaryKey(long primaryKey) {
		_accountWorker.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account worker ID of this account worker.
	*
	* @return the account worker ID of this account worker
	*/
	public long getAccountWorkerId() {
		return _accountWorker.getAccountWorkerId();
	}

	/**
	* Sets the account worker ID of this account worker.
	*
	* @param accountWorkerId the account worker ID of this account worker
	*/
	public void setAccountWorkerId(long accountWorkerId) {
		_accountWorker.setAccountWorkerId(accountWorkerId);
	}

	/**
	* Returns the user ID of this account worker.
	*
	* @return the user ID of this account worker
	*/
	public long getUserId() {
		return _accountWorker.getUserId();
	}

	/**
	* Sets the user ID of this account worker.
	*
	* @param userId the user ID of this account worker
	*/
	public void setUserId(long userId) {
		_accountWorker.setUserId(userId);
	}

	/**
	* Returns the user uuid of this account worker.
	*
	* @return the user uuid of this account worker
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountWorker.getUserUuid();
	}

	/**
	* Sets the user uuid of this account worker.
	*
	* @param userUuid the user uuid of this account worker
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_accountWorker.setUserUuid(userUuid);
	}

	/**
	* Returns the account entry ID of this account worker.
	*
	* @return the account entry ID of this account worker
	*/
	public long getAccountEntryId() {
		return _accountWorker.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this account worker.
	*
	* @param accountEntryId the account entry ID of this account worker
	*/
	public void setAccountEntryId(long accountEntryId) {
		_accountWorker.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the role of this account worker.
	*
	* @return the role of this account worker
	*/
	public int getRole() {
		return _accountWorker.getRole();
	}

	/**
	* Sets the role of this account worker.
	*
	* @param role the role of this account worker
	*/
	public void setRole(int role) {
		_accountWorker.setRole(role);
	}

	/**
	* Returns the notifications of this account worker.
	*
	* @return the notifications of this account worker
	*/
	public int getNotifications() {
		return _accountWorker.getNotifications();
	}

	/**
	* Sets the notifications of this account worker.
	*
	* @param notifications the notifications of this account worker
	*/
	public void setNotifications(int notifications) {
		_accountWorker.setNotifications(notifications);
	}

	public boolean isNew() {
		return _accountWorker.isNew();
	}

	public void setNew(boolean n) {
		_accountWorker.setNew(n);
	}

	public boolean isCachedModel() {
		return _accountWorker.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_accountWorker.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _accountWorker.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _accountWorker.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_accountWorker.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _accountWorker.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_accountWorker.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountWorkerWrapper((AccountWorker)_accountWorker.clone());
	}

	public int compareTo(com.liferay.osb.model.AccountWorker accountWorker) {
		return _accountWorker.compareTo(accountWorker);
	}

	@Override
	public int hashCode() {
		return _accountWorker.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AccountWorker> toCacheModel() {
		return _accountWorker.toCacheModel();
	}

	public com.liferay.osb.model.AccountWorker toEscapedModel() {
		return new AccountWorkerWrapper(_accountWorker.toEscapedModel());
	}

	public com.liferay.osb.model.AccountWorker toUnescapedModel() {
		return new AccountWorkerWrapper(_accountWorker.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accountWorker.toString();
	}

	public java.lang.String toXmlString() {
		return _accountWorker.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountWorker.persist();
	}

	public com.liferay.osb.model.AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountWorker.getAccountEntry();
	}

	public java.lang.String getKey() {
		return _accountWorker.getKey();
	}

	public java.lang.String getNotificationsLabel() {
		return _accountWorker.getNotificationsLabel();
	}

	public java.lang.String getRoleLabel() {
		return _accountWorker.getRoleLabel();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountWorkerWrapper)) {
			return false;
		}

		AccountWorkerWrapper accountWorkerWrapper = (AccountWorkerWrapper)obj;

		if (Validator.equals(_accountWorker, accountWorkerWrapper._accountWorker)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AccountWorker getWrappedAccountWorker() {
		return _accountWorker;
	}

	public AccountWorker getWrappedModel() {
		return _accountWorker;
	}

	public void resetOriginalValues() {
		_accountWorker.resetOriginalValues();
	}

	private AccountWorker _accountWorker;
}