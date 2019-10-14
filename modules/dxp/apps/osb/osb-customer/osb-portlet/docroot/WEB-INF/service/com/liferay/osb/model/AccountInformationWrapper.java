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
 * This class is a wrapper for {@link AccountInformation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountInformation
 * @generated
 */
@ProviderType
public class AccountInformationWrapper implements AccountInformation,
	ModelWrapper<AccountInformation> {
	public AccountInformationWrapper(AccountInformation accountInformation) {
		_accountInformation = accountInformation;
	}

	@Override
	public Class<?> getModelClass() {
		return AccountInformation.class;
	}

	@Override
	public String getModelClassName() {
		return AccountInformation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountInformationId", getAccountInformationId());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("accountProjectId", getAccountProjectId());
		attributes.put("fieldId", getFieldId());
		attributes.put("data", getData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountInformationId = (Long)attributes.get("accountInformationId");

		if (accountInformationId != null) {
			setAccountInformationId(accountInformationId);
		}

		Long modifiedUserId = (Long)attributes.get("modifiedUserId");

		if (modifiedUserId != null) {
			setModifiedUserId(modifiedUserId);
		}

		String modifiedUserName = (String)attributes.get("modifiedUserName");

		if (modifiedUserName != null) {
			setModifiedUserName(modifiedUserName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long accountProjectId = (Long)attributes.get("accountProjectId");

		if (accountProjectId != null) {
			setAccountProjectId(accountProjectId);
		}

		Integer fieldId = (Integer)attributes.get("fieldId");

		if (fieldId != null) {
			setFieldId(fieldId);
		}

		String data = (String)attributes.get("data");

		if (data != null) {
			setData(data);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _accountInformation.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accountInformation.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accountInformation.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accountInformation.getExpandoBridge();
	}

	@Override
	public AccountInformation toEscapedModel() {
		return new AccountInformationWrapper(_accountInformation.toEscapedModel());
	}

	@Override
	public AccountInformation toUnescapedModel() {
		return new AccountInformationWrapper(_accountInformation.toUnescapedModel());
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccountInformation> toCacheModel() {
		return _accountInformation.toCacheModel();
	}

	@Override
	public int compareTo(AccountInformation accountInformation) {
		return _accountInformation.compareTo(accountInformation);
	}

	/**
	* Returns the field ID of this account information.
	*
	* @return the field ID of this account information
	*/
	@Override
	public int getFieldId() {
		return _accountInformation.getFieldId();
	}

	@Override
	public int hashCode() {
		return _accountInformation.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountInformation.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AccountInformationWrapper((AccountInformation)_accountInformation.clone());
	}

	/**
	* Returns the data of this account information.
	*
	* @return the data of this account information
	*/
	@Override
	public java.lang.String getData() {
		return _accountInformation.getData();
	}

	/**
	* Returns the modified user name of this account information.
	*
	* @return the modified user name of this account information
	*/
	@Override
	public java.lang.String getModifiedUserName() {
		return _accountInformation.getModifiedUserName();
	}

	/**
	* Returns the modified user uuid of this account information.
	*
	* @return the modified user uuid of this account information
	*/
	@Override
	public java.lang.String getModifiedUserUuid() {
		return _accountInformation.getModifiedUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _accountInformation.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _accountInformation.toXmlString();
	}

	/**
	* Returns the modified date of this account information.
	*
	* @return the modified date of this account information
	*/
	@Override
	public Date getModifiedDate() {
		return _accountInformation.getModifiedDate();
	}

	/**
	* Returns the account entry ID of this account information.
	*
	* @return the account entry ID of this account information
	*/
	@Override
	public long getAccountEntryId() {
		return _accountInformation.getAccountEntryId();
	}

	/**
	* Returns the account information ID of this account information.
	*
	* @return the account information ID of this account information
	*/
	@Override
	public long getAccountInformationId() {
		return _accountInformation.getAccountInformationId();
	}

	/**
	* Returns the account project ID of this account information.
	*
	* @return the account project ID of this account information
	*/
	@Override
	public long getAccountProjectId() {
		return _accountInformation.getAccountProjectId();
	}

	/**
	* Returns the modified user ID of this account information.
	*
	* @return the modified user ID of this account information
	*/
	@Override
	public long getModifiedUserId() {
		return _accountInformation.getModifiedUserId();
	}

	/**
	* Returns the primary key of this account information.
	*
	* @return the primary key of this account information
	*/
	@Override
	public long getPrimaryKey() {
		return _accountInformation.getPrimaryKey();
	}

	@Override
	public void persist() {
		_accountInformation.persist();
	}

	/**
	* Sets the account entry ID of this account information.
	*
	* @param accountEntryId the account entry ID of this account information
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountInformation.setAccountEntryId(accountEntryId);
	}

	/**
	* Sets the account information ID of this account information.
	*
	* @param accountInformationId the account information ID of this account information
	*/
	@Override
	public void setAccountInformationId(long accountInformationId) {
		_accountInformation.setAccountInformationId(accountInformationId);
	}

	/**
	* Sets the account project ID of this account information.
	*
	* @param accountProjectId the account project ID of this account information
	*/
	@Override
	public void setAccountProjectId(long accountProjectId) {
		_accountInformation.setAccountProjectId(accountProjectId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accountInformation.setCachedModel(cachedModel);
	}

	/**
	* Sets the data of this account information.
	*
	* @param data the data of this account information
	*/
	@Override
	public void setData(java.lang.String data) {
		_accountInformation.setData(data);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accountInformation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accountInformation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accountInformation.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field ID of this account information.
	*
	* @param fieldId the field ID of this account information
	*/
	@Override
	public void setFieldId(int fieldId) {
		_accountInformation.setFieldId(fieldId);
	}

	/**
	* Sets the modified date of this account information.
	*
	* @param modifiedDate the modified date of this account information
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_accountInformation.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the modified user ID of this account information.
	*
	* @param modifiedUserId the modified user ID of this account information
	*/
	@Override
	public void setModifiedUserId(long modifiedUserId) {
		_accountInformation.setModifiedUserId(modifiedUserId);
	}

	/**
	* Sets the modified user name of this account information.
	*
	* @param modifiedUserName the modified user name of this account information
	*/
	@Override
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_accountInformation.setModifiedUserName(modifiedUserName);
	}

	/**
	* Sets the modified user uuid of this account information.
	*
	* @param modifiedUserUuid the modified user uuid of this account information
	*/
	@Override
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_accountInformation.setModifiedUserUuid(modifiedUserUuid);
	}

	@Override
	public void setNew(boolean n) {
		_accountInformation.setNew(n);
	}

	/**
	* Sets the primary key of this account information.
	*
	* @param primaryKey the primary key of this account information
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_accountInformation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accountInformation.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountInformationWrapper)) {
			return false;
		}

		AccountInformationWrapper accountInformationWrapper = (AccountInformationWrapper)obj;

		if (Objects.equals(_accountInformation,
					accountInformationWrapper._accountInformation)) {
			return true;
		}

		return false;
	}

	@Override
	public AccountInformation getWrappedModel() {
		return _accountInformation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accountInformation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accountInformation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accountInformation.resetOriginalValues();
	}

	private final AccountInformation _accountInformation;
}