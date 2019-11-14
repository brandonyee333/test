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
 * This class is a wrapper for {@link AccountCustomer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountCustomer
 * @generated
 */
@ProviderType
public class AccountCustomerWrapper implements AccountCustomer,
	ModelWrapper<AccountCustomer> {
	public AccountCustomerWrapper(AccountCustomer accountCustomer) {
		_accountCustomer = accountCustomer;
	}

	@Override
	public Class<?> getModelClass() {
		return AccountCustomer.class;
	}

	@Override
	public String getModelClassName() {
		return AccountCustomer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountCustomerId", getAccountCustomerId());
		attributes.put("userId", getUserId());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("role", getRole());
		attributes.put("closedWatcher", getClosedWatcher());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountCustomerId = (Long)attributes.get("accountCustomerId");

		if (accountCustomerId != null) {
			setAccountCustomerId(accountCustomerId);
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

		Boolean closedWatcher = (Boolean)attributes.get("closedWatcher");

		if (closedWatcher != null) {
			setClosedWatcher(closedWatcher);
		}
	}

	/**
	* Returns the closed watcher of this account customer.
	*
	* @return the closed watcher of this account customer
	*/
	@Override
	public boolean getClosedWatcher() {
		return _accountCustomer.getClosedWatcher();
	}

	@Override
	public boolean isCachedModel() {
		return _accountCustomer.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this account customer is closed watcher.
	*
	* @return <code>true</code> if this account customer is closed watcher; <code>false</code> otherwise
	*/
	@Override
	public boolean isClosedWatcher() {
		return _accountCustomer.isClosedWatcher();
	}

	@Override
	public boolean isEscapedModel() {
		return _accountCustomer.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accountCustomer.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accountCustomer.getExpandoBridge();
	}

	@Override
	public AccountCustomer toEscapedModel() {
		return new AccountCustomerWrapper(_accountCustomer.toEscapedModel());
	}

	@Override
	public AccountCustomer toUnescapedModel() {
		return new AccountCustomerWrapper(_accountCustomer.toUnescapedModel());
	}

	@Override
	public AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountCustomer.getAccountEntry();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccountCustomer> toCacheModel() {
		return _accountCustomer.toCacheModel();
	}

	@Override
	public int compareTo(AccountCustomer accountCustomer) {
		return _accountCustomer.compareTo(accountCustomer);
	}

	/**
	* Returns the role of this account customer.
	*
	* @return the role of this account customer
	*/
	@Override
	public int getRole() {
		return _accountCustomer.getRole();
	}

	@Override
	public int hashCode() {
		return _accountCustomer.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountCustomer.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AccountCustomerWrapper((AccountCustomer)_accountCustomer.clone());
	}

	@Override
	public java.lang.String getRoleLabel() {
		return _accountCustomer.getRoleLabel();
	}

	/**
	* Returns the user uuid of this account customer.
	*
	* @return the user uuid of this account customer
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _accountCustomer.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _accountCustomer.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _accountCustomer.toXmlString();
	}

	/**
	* Returns the account customer ID of this account customer.
	*
	* @return the account customer ID of this account customer
	*/
	@Override
	public long getAccountCustomerId() {
		return _accountCustomer.getAccountCustomerId();
	}

	/**
	* Returns the account entry ID of this account customer.
	*
	* @return the account entry ID of this account customer
	*/
	@Override
	public long getAccountEntryId() {
		return _accountCustomer.getAccountEntryId();
	}

	/**
	* Returns the primary key of this account customer.
	*
	* @return the primary key of this account customer
	*/
	@Override
	public long getPrimaryKey() {
		return _accountCustomer.getPrimaryKey();
	}

	/**
	* Returns the user ID of this account customer.
	*
	* @return the user ID of this account customer
	*/
	@Override
	public long getUserId() {
		return _accountCustomer.getUserId();
	}

	@Override
	public void persist() {
		_accountCustomer.persist();
	}

	/**
	* Sets the account customer ID of this account customer.
	*
	* @param accountCustomerId the account customer ID of this account customer
	*/
	@Override
	public void setAccountCustomerId(long accountCustomerId) {
		_accountCustomer.setAccountCustomerId(accountCustomerId);
	}

	/**
	* Sets the account entry ID of this account customer.
	*
	* @param accountEntryId the account entry ID of this account customer
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountCustomer.setAccountEntryId(accountEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accountCustomer.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this account customer is closed watcher.
	*
	* @param closedWatcher the closed watcher of this account customer
	*/
	@Override
	public void setClosedWatcher(boolean closedWatcher) {
		_accountCustomer.setClosedWatcher(closedWatcher);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accountCustomer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accountCustomer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accountCustomer.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_accountCustomer.setNew(n);
	}

	/**
	* Sets the primary key of this account customer.
	*
	* @param primaryKey the primary key of this account customer
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_accountCustomer.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accountCustomer.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role of this account customer.
	*
	* @param role the role of this account customer
	*/
	@Override
	public void setRole(int role) {
		_accountCustomer.setRole(role);
	}

	/**
	* Sets the user ID of this account customer.
	*
	* @param userId the user ID of this account customer
	*/
	@Override
	public void setUserId(long userId) {
		_accountCustomer.setUserId(userId);
	}

	/**
	* Sets the user uuid of this account customer.
	*
	* @param userUuid the user uuid of this account customer
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_accountCustomer.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountCustomerWrapper)) {
			return false;
		}

		AccountCustomerWrapper accountCustomerWrapper = (AccountCustomerWrapper)obj;

		if (Objects.equals(_accountCustomer,
					accountCustomerWrapper._accountCustomer)) {
			return true;
		}

		return false;
	}

	@Override
	public AccountCustomer getWrappedModel() {
		return _accountCustomer;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accountCustomer.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accountCustomer.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accountCustomer.resetOriginalValues();
	}

	private final AccountCustomer _accountCustomer;
}