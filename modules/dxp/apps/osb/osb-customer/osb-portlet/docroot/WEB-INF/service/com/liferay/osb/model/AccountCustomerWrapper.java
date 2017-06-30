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
 * This class is a wrapper for {@link AccountCustomer}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountCustomer
 * @generated
 */
public class AccountCustomerWrapper implements AccountCustomer,
	ModelWrapper<AccountCustomer> {
	public AccountCustomerWrapper(AccountCustomer accountCustomer) {
		_accountCustomer = accountCustomer;
	}

	public Class<?> getModelClass() {
		return AccountCustomer.class;
	}

	public String getModelClassName() {
		return AccountCustomer.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountCustomerId", getAccountCustomerId());
		attributes.put("userId", getUserId());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("role", getRole());
		attributes.put("notifications", getNotifications());

		return attributes;
	}

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

		Integer notifications = (Integer)attributes.get("notifications");

		if (notifications != null) {
			setNotifications(notifications);
		}
	}

	/**
	* Returns the primary key of this account customer.
	*
	* @return the primary key of this account customer
	*/
	public long getPrimaryKey() {
		return _accountCustomer.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account customer.
	*
	* @param primaryKey the primary key of this account customer
	*/
	public void setPrimaryKey(long primaryKey) {
		_accountCustomer.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account customer ID of this account customer.
	*
	* @return the account customer ID of this account customer
	*/
	public long getAccountCustomerId() {
		return _accountCustomer.getAccountCustomerId();
	}

	/**
	* Sets the account customer ID of this account customer.
	*
	* @param accountCustomerId the account customer ID of this account customer
	*/
	public void setAccountCustomerId(long accountCustomerId) {
		_accountCustomer.setAccountCustomerId(accountCustomerId);
	}

	/**
	* Returns the user ID of this account customer.
	*
	* @return the user ID of this account customer
	*/
	public long getUserId() {
		return _accountCustomer.getUserId();
	}

	/**
	* Sets the user ID of this account customer.
	*
	* @param userId the user ID of this account customer
	*/
	public void setUserId(long userId) {
		_accountCustomer.setUserId(userId);
	}

	/**
	* Returns the user uuid of this account customer.
	*
	* @return the user uuid of this account customer
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomer.getUserUuid();
	}

	/**
	* Sets the user uuid of this account customer.
	*
	* @param userUuid the user uuid of this account customer
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_accountCustomer.setUserUuid(userUuid);
	}

	/**
	* Returns the account entry ID of this account customer.
	*
	* @return the account entry ID of this account customer
	*/
	public long getAccountEntryId() {
		return _accountCustomer.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this account customer.
	*
	* @param accountEntryId the account entry ID of this account customer
	*/
	public void setAccountEntryId(long accountEntryId) {
		_accountCustomer.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the role of this account customer.
	*
	* @return the role of this account customer
	*/
	public int getRole() {
		return _accountCustomer.getRole();
	}

	/**
	* Sets the role of this account customer.
	*
	* @param role the role of this account customer
	*/
	public void setRole(int role) {
		_accountCustomer.setRole(role);
	}

	/**
	* Returns the notifications of this account customer.
	*
	* @return the notifications of this account customer
	*/
	public int getNotifications() {
		return _accountCustomer.getNotifications();
	}

	/**
	* Sets the notifications of this account customer.
	*
	* @param notifications the notifications of this account customer
	*/
	public void setNotifications(int notifications) {
		_accountCustomer.setNotifications(notifications);
	}

	public boolean isNew() {
		return _accountCustomer.isNew();
	}

	public void setNew(boolean n) {
		_accountCustomer.setNew(n);
	}

	public boolean isCachedModel() {
		return _accountCustomer.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_accountCustomer.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _accountCustomer.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _accountCustomer.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_accountCustomer.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _accountCustomer.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_accountCustomer.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountCustomerWrapper((AccountCustomer)_accountCustomer.clone());
	}

	public int compareTo(com.liferay.osb.model.AccountCustomer accountCustomer) {
		return _accountCustomer.compareTo(accountCustomer);
	}

	@Override
	public int hashCode() {
		return _accountCustomer.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AccountCustomer> toCacheModel() {
		return _accountCustomer.toCacheModel();
	}

	public com.liferay.osb.model.AccountCustomer toEscapedModel() {
		return new AccountCustomerWrapper(_accountCustomer.toEscapedModel());
	}

	public com.liferay.osb.model.AccountCustomer toUnescapedModel() {
		return new AccountCustomerWrapper(_accountCustomer.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accountCustomer.toString();
	}

	public java.lang.String toXmlString() {
		return _accountCustomer.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountCustomer.persist();
	}

	public com.liferay.osb.model.AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountCustomer.getAccountEntry();
	}

	public java.lang.String getNotificationsLabel() {
		return _accountCustomer.getNotificationsLabel();
	}

	public java.lang.String getRoleLabel() {
		return _accountCustomer.getRoleLabel();
	}

	public boolean hasNotificationsOn() {
		return _accountCustomer.hasNotificationsOn();
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

		if (Validator.equals(_accountCustomer,
					accountCustomerWrapper._accountCustomer)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AccountCustomer getWrappedAccountCustomer() {
		return _accountCustomer;
	}

	public AccountCustomer getWrappedModel() {
		return _accountCustomer;
	}

	public void resetOriginalValues() {
		_accountCustomer.resetOriginalValues();
	}

	private AccountCustomer _accountCustomer;
}