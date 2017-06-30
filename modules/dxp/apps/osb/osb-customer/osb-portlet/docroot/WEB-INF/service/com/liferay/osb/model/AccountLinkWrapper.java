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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AccountLink}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountLink
 * @generated
 */
public class AccountLinkWrapper implements AccountLink,
	ModelWrapper<AccountLink> {
	public AccountLinkWrapper(AccountLink accountLink) {
		_accountLink = accountLink;
	}

	public Class<?> getModelClass() {
		return AccountLink.class;
	}

	public String getModelClassName() {
		return AccountLink.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountLinkId", getAccountLinkId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("url", getUrl());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountLinkId = (Long)attributes.get("accountLinkId");

		if (accountLinkId != null) {
			setAccountLinkId(accountLinkId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}
	}

	/**
	* Returns the primary key of this account link.
	*
	* @return the primary key of this account link
	*/
	public long getPrimaryKey() {
		return _accountLink.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account link.
	*
	* @param primaryKey the primary key of this account link
	*/
	public void setPrimaryKey(long primaryKey) {
		_accountLink.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account link ID of this account link.
	*
	* @return the account link ID of this account link
	*/
	public long getAccountLinkId() {
		return _accountLink.getAccountLinkId();
	}

	/**
	* Sets the account link ID of this account link.
	*
	* @param accountLinkId the account link ID of this account link
	*/
	public void setAccountLinkId(long accountLinkId) {
		_accountLink.setAccountLinkId(accountLinkId);
	}

	/**
	* Returns the user ID of this account link.
	*
	* @return the user ID of this account link
	*/
	public long getUserId() {
		return _accountLink.getUserId();
	}

	/**
	* Sets the user ID of this account link.
	*
	* @param userId the user ID of this account link
	*/
	public void setUserId(long userId) {
		_accountLink.setUserId(userId);
	}

	/**
	* Returns the user uuid of this account link.
	*
	* @return the user uuid of this account link
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountLink.getUserUuid();
	}

	/**
	* Sets the user uuid of this account link.
	*
	* @param userUuid the user uuid of this account link
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_accountLink.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this account link.
	*
	* @return the user name of this account link
	*/
	public java.lang.String getUserName() {
		return _accountLink.getUserName();
	}

	/**
	* Sets the user name of this account link.
	*
	* @param userName the user name of this account link
	*/
	public void setUserName(java.lang.String userName) {
		_accountLink.setUserName(userName);
	}

	/**
	* Returns the create date of this account link.
	*
	* @return the create date of this account link
	*/
	public java.util.Date getCreateDate() {
		return _accountLink.getCreateDate();
	}

	/**
	* Sets the create date of this account link.
	*
	* @param createDate the create date of this account link
	*/
	public void setCreateDate(java.util.Date createDate) {
		_accountLink.setCreateDate(createDate);
	}

	/**
	* Returns the account entry ID of this account link.
	*
	* @return the account entry ID of this account link
	*/
	public long getAccountEntryId() {
		return _accountLink.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this account link.
	*
	* @param accountEntryId the account entry ID of this account link
	*/
	public void setAccountEntryId(long accountEntryId) {
		_accountLink.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the url of this account link.
	*
	* @return the url of this account link
	*/
	public java.lang.String getUrl() {
		return _accountLink.getUrl();
	}

	/**
	* Sets the url of this account link.
	*
	* @param url the url of this account link
	*/
	public void setUrl(java.lang.String url) {
		_accountLink.setUrl(url);
	}

	public boolean isNew() {
		return _accountLink.isNew();
	}

	public void setNew(boolean n) {
		_accountLink.setNew(n);
	}

	public boolean isCachedModel() {
		return _accountLink.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_accountLink.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _accountLink.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _accountLink.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_accountLink.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _accountLink.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_accountLink.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountLinkWrapper((AccountLink)_accountLink.clone());
	}

	public int compareTo(com.liferay.osb.model.AccountLink accountLink) {
		return _accountLink.compareTo(accountLink);
	}

	@Override
	public int hashCode() {
		return _accountLink.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AccountLink> toCacheModel() {
		return _accountLink.toCacheModel();
	}

	public com.liferay.osb.model.AccountLink toEscapedModel() {
		return new AccountLinkWrapper(_accountLink.toEscapedModel());
	}

	public com.liferay.osb.model.AccountLink toUnescapedModel() {
		return new AccountLinkWrapper(_accountLink.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accountLink.toString();
	}

	public java.lang.String toXmlString() {
		return _accountLink.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountLink.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountLinkWrapper)) {
			return false;
		}

		AccountLinkWrapper accountLinkWrapper = (AccountLinkWrapper)obj;

		if (Validator.equals(_accountLink, accountLinkWrapper._accountLink)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AccountLink getWrappedAccountLink() {
		return _accountLink;
	}

	public AccountLink getWrappedModel() {
		return _accountLink;
	}

	public void resetOriginalValues() {
		_accountLink.resetOriginalValues();
	}

	private AccountLink _accountLink;
}