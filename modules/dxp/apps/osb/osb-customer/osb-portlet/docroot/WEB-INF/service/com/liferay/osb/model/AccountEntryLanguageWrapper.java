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
 * This class is a wrapper for {@link AccountEntryLanguage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLanguage
 * @generated
 */
@ProviderType
public class AccountEntryLanguageWrapper implements AccountEntryLanguage,
	ModelWrapper<AccountEntryLanguage> {
	public AccountEntryLanguageWrapper(
		AccountEntryLanguage accountEntryLanguage) {
		_accountEntryLanguage = accountEntryLanguage;
	}

	@Override
	public Class<?> getModelClass() {
		return AccountEntryLanguage.class;
	}

	@Override
	public String getModelClassName() {
		return AccountEntryLanguage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountEntryLanguageId", getAccountEntryLanguageId());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("languageId", getLanguageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountEntryLanguageId = (Long)attributes.get(
				"accountEntryLanguageId");

		if (accountEntryLanguageId != null) {
			setAccountEntryLanguageId(accountEntryLanguageId);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _accountEntryLanguage.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accountEntryLanguage.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accountEntryLanguage.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accountEntryLanguage.getExpandoBridge();
	}

	@Override
	public AccountEntryLanguage toEscapedModel() {
		return new AccountEntryLanguageWrapper(_accountEntryLanguage.toEscapedModel());
	}

	@Override
	public AccountEntryLanguage toUnescapedModel() {
		return new AccountEntryLanguageWrapper(_accountEntryLanguage.toUnescapedModel());
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccountEntryLanguage> toCacheModel() {
		return _accountEntryLanguage.toCacheModel();
	}

	@Override
	public int compareTo(AccountEntryLanguage accountEntryLanguage) {
		return _accountEntryLanguage.compareTo(accountEntryLanguage);
	}

	@Override
	public int hashCode() {
		return _accountEntryLanguage.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountEntryLanguage.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AccountEntryLanguageWrapper((AccountEntryLanguage)_accountEntryLanguage.clone());
	}

	/**
	* Returns the language ID of this account entry language.
	*
	* @return the language ID of this account entry language
	*/
	@Override
	public java.lang.String getLanguageId() {
		return _accountEntryLanguage.getLanguageId();
	}

	@Override
	public java.lang.String toString() {
		return _accountEntryLanguage.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _accountEntryLanguage.toXmlString();
	}

	/**
	* Returns the account entry ID of this account entry language.
	*
	* @return the account entry ID of this account entry language
	*/
	@Override
	public long getAccountEntryId() {
		return _accountEntryLanguage.getAccountEntryId();
	}

	/**
	* Returns the account entry language ID of this account entry language.
	*
	* @return the account entry language ID of this account entry language
	*/
	@Override
	public long getAccountEntryLanguageId() {
		return _accountEntryLanguage.getAccountEntryLanguageId();
	}

	/**
	* Returns the primary key of this account entry language.
	*
	* @return the primary key of this account entry language
	*/
	@Override
	public long getPrimaryKey() {
		return _accountEntryLanguage.getPrimaryKey();
	}

	@Override
	public void persist() {
		_accountEntryLanguage.persist();
	}

	/**
	* Sets the account entry ID of this account entry language.
	*
	* @param accountEntryId the account entry ID of this account entry language
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountEntryLanguage.setAccountEntryId(accountEntryId);
	}

	/**
	* Sets the account entry language ID of this account entry language.
	*
	* @param accountEntryLanguageId the account entry language ID of this account entry language
	*/
	@Override
	public void setAccountEntryLanguageId(long accountEntryLanguageId) {
		_accountEntryLanguage.setAccountEntryLanguageId(accountEntryLanguageId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accountEntryLanguage.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accountEntryLanguage.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accountEntryLanguage.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accountEntryLanguage.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the language ID of this account entry language.
	*
	* @param languageId the language ID of this account entry language
	*/
	@Override
	public void setLanguageId(java.lang.String languageId) {
		_accountEntryLanguage.setLanguageId(languageId);
	}

	@Override
	public void setNew(boolean n) {
		_accountEntryLanguage.setNew(n);
	}

	/**
	* Sets the primary key of this account entry language.
	*
	* @param primaryKey the primary key of this account entry language
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_accountEntryLanguage.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accountEntryLanguage.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountEntryLanguageWrapper)) {
			return false;
		}

		AccountEntryLanguageWrapper accountEntryLanguageWrapper = (AccountEntryLanguageWrapper)obj;

		if (Objects.equals(_accountEntryLanguage,
					accountEntryLanguageWrapper._accountEntryLanguage)) {
			return true;
		}

		return false;
	}

	@Override
	public AccountEntryLanguage getWrappedModel() {
		return _accountEntryLanguage;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accountEntryLanguage.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accountEntryLanguage.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accountEntryLanguage.resetOriginalValues();
	}

	private final AccountEntryLanguage _accountEntryLanguage;
}