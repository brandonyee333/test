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
 * This class is a wrapper for {@link AccountWorker}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountWorker
 * @generated
 */
@ProviderType
public class AccountWorkerWrapper implements AccountWorker,
	ModelWrapper<AccountWorker> {
	public AccountWorkerWrapper(AccountWorker accountWorker) {
		_accountWorker = accountWorker;
	}

	@Override
	public Class<?> getModelClass() {
		return AccountWorker.class;
	}

	@Override
	public String getModelClassName() {
		return AccountWorker.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountWorkerId", getAccountWorkerId());
		attributes.put("userId", getUserId());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("role", getRole());

		return attributes;
	}

	@Override
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
	}

	@Override
	public boolean isCachedModel() {
		return _accountWorker.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accountWorker.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accountWorker.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accountWorker.getExpandoBridge();
	}

	@Override
	public AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountWorker.getAccountEntry();
	}

	@Override
	public AccountWorker toEscapedModel() {
		return new AccountWorkerWrapper(_accountWorker.toEscapedModel());
	}

	@Override
	public AccountWorker toUnescapedModel() {
		return new AccountWorkerWrapper(_accountWorker.toUnescapedModel());
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccountWorker> toCacheModel() {
		return _accountWorker.toCacheModel();
	}

	@Override
	public int compareTo(AccountWorker accountWorker) {
		return _accountWorker.compareTo(accountWorker);
	}

	/**
	* Returns the role of this account worker.
	*
	* @return the role of this account worker
	*/
	@Override
	public int getRole() {
		return _accountWorker.getRole();
	}

	@Override
	public int hashCode() {
		return _accountWorker.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountWorker.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AccountWorkerWrapper((AccountWorker)_accountWorker.clone());
	}

	@Override
	public java.lang.String getKey() {
		return _accountWorker.getKey();
	}

	@Override
	public java.lang.String getRoleLabel() {
		return _accountWorker.getRoleLabel();
	}

	/**
	* Returns the user uuid of this account worker.
	*
	* @return the user uuid of this account worker
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _accountWorker.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _accountWorker.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _accountWorker.toXmlString();
	}

	/**
	* Returns the account entry ID of this account worker.
	*
	* @return the account entry ID of this account worker
	*/
	@Override
	public long getAccountEntryId() {
		return _accountWorker.getAccountEntryId();
	}

	/**
	* Returns the account worker ID of this account worker.
	*
	* @return the account worker ID of this account worker
	*/
	@Override
	public long getAccountWorkerId() {
		return _accountWorker.getAccountWorkerId();
	}

	/**
	* Returns the primary key of this account worker.
	*
	* @return the primary key of this account worker
	*/
	@Override
	public long getPrimaryKey() {
		return _accountWorker.getPrimaryKey();
	}

	/**
	* Returns the user ID of this account worker.
	*
	* @return the user ID of this account worker
	*/
	@Override
	public long getUserId() {
		return _accountWorker.getUserId();
	}

	@Override
	public void persist() {
		_accountWorker.persist();
	}

	/**
	* Sets the account entry ID of this account worker.
	*
	* @param accountEntryId the account entry ID of this account worker
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountWorker.setAccountEntryId(accountEntryId);
	}

	/**
	* Sets the account worker ID of this account worker.
	*
	* @param accountWorkerId the account worker ID of this account worker
	*/
	@Override
	public void setAccountWorkerId(long accountWorkerId) {
		_accountWorker.setAccountWorkerId(accountWorkerId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accountWorker.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accountWorker.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accountWorker.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accountWorker.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_accountWorker.setNew(n);
	}

	/**
	* Sets the primary key of this account worker.
	*
	* @param primaryKey the primary key of this account worker
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_accountWorker.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accountWorker.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role of this account worker.
	*
	* @param role the role of this account worker
	*/
	@Override
	public void setRole(int role) {
		_accountWorker.setRole(role);
	}

	/**
	* Sets the user ID of this account worker.
	*
	* @param userId the user ID of this account worker
	*/
	@Override
	public void setUserId(long userId) {
		_accountWorker.setUserId(userId);
	}

	/**
	* Sets the user uuid of this account worker.
	*
	* @param userUuid the user uuid of this account worker
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_accountWorker.setUserUuid(userUuid);
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

		if (Objects.equals(_accountWorker, accountWorkerWrapper._accountWorker)) {
			return true;
		}

		return false;
	}

	@Override
	public AccountWorker getWrappedModel() {
		return _accountWorker;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accountWorker.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accountWorker.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accountWorker.resetOriginalValues();
	}

	private final AccountWorker _accountWorker;
}