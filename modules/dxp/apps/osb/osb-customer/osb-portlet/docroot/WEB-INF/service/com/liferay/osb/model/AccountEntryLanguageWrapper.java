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
 * This class is a wrapper for {@link AccountEntryLanguage}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountEntryLanguage
 * @generated
 */
public class AccountEntryLanguageWrapper implements AccountEntryLanguage,
	ModelWrapper<AccountEntryLanguage> {
	public AccountEntryLanguageWrapper(
		AccountEntryLanguage accountEntryLanguage) {
		_accountEntryLanguage = accountEntryLanguage;
	}

	public Class<?> getModelClass() {
		return AccountEntryLanguage.class;
	}

	public String getModelClassName() {
		return AccountEntryLanguage.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountEntryLanguageId", getAccountEntryLanguageId());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("languageId", getLanguageId());

		return attributes;
	}

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

	/**
	* Returns the primary key of this account entry language.
	*
	* @return the primary key of this account entry language
	*/
	public long getPrimaryKey() {
		return _accountEntryLanguage.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account entry language.
	*
	* @param primaryKey the primary key of this account entry language
	*/
	public void setPrimaryKey(long primaryKey) {
		_accountEntryLanguage.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account entry language ID of this account entry language.
	*
	* @return the account entry language ID of this account entry language
	*/
	public long getAccountEntryLanguageId() {
		return _accountEntryLanguage.getAccountEntryLanguageId();
	}

	/**
	* Sets the account entry language ID of this account entry language.
	*
	* @param accountEntryLanguageId the account entry language ID of this account entry language
	*/
	public void setAccountEntryLanguageId(long accountEntryLanguageId) {
		_accountEntryLanguage.setAccountEntryLanguageId(accountEntryLanguageId);
	}

	/**
	* Returns the account entry ID of this account entry language.
	*
	* @return the account entry ID of this account entry language
	*/
	public long getAccountEntryId() {
		return _accountEntryLanguage.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this account entry language.
	*
	* @param accountEntryId the account entry ID of this account entry language
	*/
	public void setAccountEntryId(long accountEntryId) {
		_accountEntryLanguage.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the language ID of this account entry language.
	*
	* @return the language ID of this account entry language
	*/
	public java.lang.String getLanguageId() {
		return _accountEntryLanguage.getLanguageId();
	}

	/**
	* Sets the language ID of this account entry language.
	*
	* @param languageId the language ID of this account entry language
	*/
	public void setLanguageId(java.lang.String languageId) {
		_accountEntryLanguage.setLanguageId(languageId);
	}

	public boolean isNew() {
		return _accountEntryLanguage.isNew();
	}

	public void setNew(boolean n) {
		_accountEntryLanguage.setNew(n);
	}

	public boolean isCachedModel() {
		return _accountEntryLanguage.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_accountEntryLanguage.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _accountEntryLanguage.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _accountEntryLanguage.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_accountEntryLanguage.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _accountEntryLanguage.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_accountEntryLanguage.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountEntryLanguageWrapper((AccountEntryLanguage)_accountEntryLanguage.clone());
	}

	public int compareTo(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage) {
		return _accountEntryLanguage.compareTo(accountEntryLanguage);
	}

	@Override
	public int hashCode() {
		return _accountEntryLanguage.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AccountEntryLanguage> toCacheModel() {
		return _accountEntryLanguage.toCacheModel();
	}

	public com.liferay.osb.model.AccountEntryLanguage toEscapedModel() {
		return new AccountEntryLanguageWrapper(_accountEntryLanguage.toEscapedModel());
	}

	public com.liferay.osb.model.AccountEntryLanguage toUnescapedModel() {
		return new AccountEntryLanguageWrapper(_accountEntryLanguage.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accountEntryLanguage.toString();
	}

	public java.lang.String toXmlString() {
		return _accountEntryLanguage.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountEntryLanguage.persist();
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

		if (Validator.equals(_accountEntryLanguage,
					accountEntryLanguageWrapper._accountEntryLanguage)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AccountEntryLanguage getWrappedAccountEntryLanguage() {
		return _accountEntryLanguage;
	}

	public AccountEntryLanguage getWrappedModel() {
		return _accountEntryLanguage;
	}

	public void resetOriginalValues() {
		_accountEntryLanguage.resetOriginalValues();
	}

	private AccountEntryLanguage _accountEntryLanguage;
}