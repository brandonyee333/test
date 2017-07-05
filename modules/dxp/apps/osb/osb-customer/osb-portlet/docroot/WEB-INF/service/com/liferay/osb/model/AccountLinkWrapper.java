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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AccountLink}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountLink
 * @generated
 */
@ProviderType
public class AccountLinkWrapper implements AccountLink,
	ModelWrapper<AccountLink> {
	public AccountLinkWrapper(AccountLink accountLink) {
		_accountLink = accountLink;
	}

	@Override
	public Class<?> getModelClass() {
		return AccountLink.class;
	}

	@Override
	public String getModelClassName() {
		return AccountLink.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public AccountLink toEscapedModel() {
		return new AccountLinkWrapper(_accountLink.toEscapedModel());
	}

	@Override
	public AccountLink toUnescapedModel() {
		return new AccountLinkWrapper(_accountLink.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _accountLink.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accountLink.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accountLink.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accountLink.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccountLink> toCacheModel() {
		return _accountLink.toCacheModel();
	}

	@Override
	public int compareTo(AccountLink accountLink) {
		return _accountLink.compareTo(accountLink);
	}

	@Override
	public int hashCode() {
		return _accountLink.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountLink.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AccountLinkWrapper((AccountLink)_accountLink.clone());
	}

	/**
	* Returns the url of this account link.
	*
	* @return the url of this account link
	*/
	@Override
	public java.lang.String getUrl() {
		return _accountLink.getUrl();
	}

	/**
	* Returns the user name of this account link.
	*
	* @return the user name of this account link
	*/
	@Override
	public java.lang.String getUserName() {
		return _accountLink.getUserName();
	}

	/**
	* Returns the user uuid of this account link.
	*
	* @return the user uuid of this account link
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _accountLink.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _accountLink.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _accountLink.toXmlString();
	}

	/**
	* Returns the create date of this account link.
	*
	* @return the create date of this account link
	*/
	@Override
	public Date getCreateDate() {
		return _accountLink.getCreateDate();
	}

	/**
	* Returns the account entry ID of this account link.
	*
	* @return the account entry ID of this account link
	*/
	@Override
	public long getAccountEntryId() {
		return _accountLink.getAccountEntryId();
	}

	/**
	* Returns the account link ID of this account link.
	*
	* @return the account link ID of this account link
	*/
	@Override
	public long getAccountLinkId() {
		return _accountLink.getAccountLinkId();
	}

	/**
	* Returns the primary key of this account link.
	*
	* @return the primary key of this account link
	*/
	@Override
	public long getPrimaryKey() {
		return _accountLink.getPrimaryKey();
	}

	/**
	* Returns the user ID of this account link.
	*
	* @return the user ID of this account link
	*/
	@Override
	public long getUserId() {
		return _accountLink.getUserId();
	}

	@Override
	public void persist() {
		_accountLink.persist();
	}

	/**
	* Sets the account entry ID of this account link.
	*
	* @param accountEntryId the account entry ID of this account link
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountLink.setAccountEntryId(accountEntryId);
	}

	/**
	* Sets the account link ID of this account link.
	*
	* @param accountLinkId the account link ID of this account link
	*/
	@Override
	public void setAccountLinkId(long accountLinkId) {
		_accountLink.setAccountLinkId(accountLinkId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accountLink.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this account link.
	*
	* @param createDate the create date of this account link
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_accountLink.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accountLink.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accountLink.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accountLink.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_accountLink.setNew(n);
	}

	/**
	* Sets the primary key of this account link.
	*
	* @param primaryKey the primary key of this account link
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_accountLink.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accountLink.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the url of this account link.
	*
	* @param url the url of this account link
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_accountLink.setUrl(url);
	}

	/**
	* Sets the user ID of this account link.
	*
	* @param userId the user ID of this account link
	*/
	@Override
	public void setUserId(long userId) {
		_accountLink.setUserId(userId);
	}

	/**
	* Sets the user name of this account link.
	*
	* @param userName the user name of this account link
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_accountLink.setUserName(userName);
	}

	/**
	* Sets the user uuid of this account link.
	*
	* @param userUuid the user uuid of this account link
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_accountLink.setUserUuid(userUuid);
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

		if (Objects.equals(_accountLink, accountLinkWrapper._accountLink)) {
			return true;
		}

		return false;
	}

	@Override
	public AccountLink getWrappedModel() {
		return _accountLink;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accountLink.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accountLink.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accountLink.resetOriginalValues();
	}

	private final AccountLink _accountLink;
}