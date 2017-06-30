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
 * This class is a wrapper for {@link AccountInformation}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountInformation
 * @generated
 */
public class AccountInformationWrapper implements AccountInformation,
	ModelWrapper<AccountInformation> {
	public AccountInformationWrapper(AccountInformation accountInformation) {
		_accountInformation = accountInformation;
	}

	public Class<?> getModelClass() {
		return AccountInformation.class;
	}

	public String getModelClassName() {
		return AccountInformation.class.getName();
	}

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

	/**
	* Returns the primary key of this account information.
	*
	* @return the primary key of this account information
	*/
	public long getPrimaryKey() {
		return _accountInformation.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account information.
	*
	* @param primaryKey the primary key of this account information
	*/
	public void setPrimaryKey(long primaryKey) {
		_accountInformation.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account information ID of this account information.
	*
	* @return the account information ID of this account information
	*/
	public long getAccountInformationId() {
		return _accountInformation.getAccountInformationId();
	}

	/**
	* Sets the account information ID of this account information.
	*
	* @param accountInformationId the account information ID of this account information
	*/
	public void setAccountInformationId(long accountInformationId) {
		_accountInformation.setAccountInformationId(accountInformationId);
	}

	/**
	* Returns the modified user ID of this account information.
	*
	* @return the modified user ID of this account information
	*/
	public long getModifiedUserId() {
		return _accountInformation.getModifiedUserId();
	}

	/**
	* Sets the modified user ID of this account information.
	*
	* @param modifiedUserId the modified user ID of this account information
	*/
	public void setModifiedUserId(long modifiedUserId) {
		_accountInformation.setModifiedUserId(modifiedUserId);
	}

	/**
	* Returns the modified user uuid of this account information.
	*
	* @return the modified user uuid of this account information
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getModifiedUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountInformation.getModifiedUserUuid();
	}

	/**
	* Sets the modified user uuid of this account information.
	*
	* @param modifiedUserUuid the modified user uuid of this account information
	*/
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_accountInformation.setModifiedUserUuid(modifiedUserUuid);
	}

	/**
	* Returns the modified user name of this account information.
	*
	* @return the modified user name of this account information
	*/
	public java.lang.String getModifiedUserName() {
		return _accountInformation.getModifiedUserName();
	}

	/**
	* Sets the modified user name of this account information.
	*
	* @param modifiedUserName the modified user name of this account information
	*/
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_accountInformation.setModifiedUserName(modifiedUserName);
	}

	/**
	* Returns the modified date of this account information.
	*
	* @return the modified date of this account information
	*/
	public java.util.Date getModifiedDate() {
		return _accountInformation.getModifiedDate();
	}

	/**
	* Sets the modified date of this account information.
	*
	* @param modifiedDate the modified date of this account information
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_accountInformation.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the account entry ID of this account information.
	*
	* @return the account entry ID of this account information
	*/
	public long getAccountEntryId() {
		return _accountInformation.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this account information.
	*
	* @param accountEntryId the account entry ID of this account information
	*/
	public void setAccountEntryId(long accountEntryId) {
		_accountInformation.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the account project ID of this account information.
	*
	* @return the account project ID of this account information
	*/
	public long getAccountProjectId() {
		return _accountInformation.getAccountProjectId();
	}

	/**
	* Sets the account project ID of this account information.
	*
	* @param accountProjectId the account project ID of this account information
	*/
	public void setAccountProjectId(long accountProjectId) {
		_accountInformation.setAccountProjectId(accountProjectId);
	}

	/**
	* Returns the field ID of this account information.
	*
	* @return the field ID of this account information
	*/
	public int getFieldId() {
		return _accountInformation.getFieldId();
	}

	/**
	* Sets the field ID of this account information.
	*
	* @param fieldId the field ID of this account information
	*/
	public void setFieldId(int fieldId) {
		_accountInformation.setFieldId(fieldId);
	}

	/**
	* Returns the data of this account information.
	*
	* @return the data of this account information
	*/
	public java.lang.String getData() {
		return _accountInformation.getData();
	}

	/**
	* Sets the data of this account information.
	*
	* @param data the data of this account information
	*/
	public void setData(java.lang.String data) {
		_accountInformation.setData(data);
	}

	public boolean isNew() {
		return _accountInformation.isNew();
	}

	public void setNew(boolean n) {
		_accountInformation.setNew(n);
	}

	public boolean isCachedModel() {
		return _accountInformation.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_accountInformation.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _accountInformation.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _accountInformation.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_accountInformation.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _accountInformation.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_accountInformation.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountInformationWrapper((AccountInformation)_accountInformation.clone());
	}

	public int compareTo(
		com.liferay.osb.model.AccountInformation accountInformation) {
		return _accountInformation.compareTo(accountInformation);
	}

	@Override
	public int hashCode() {
		return _accountInformation.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AccountInformation> toCacheModel() {
		return _accountInformation.toCacheModel();
	}

	public com.liferay.osb.model.AccountInformation toEscapedModel() {
		return new AccountInformationWrapper(_accountInformation.toEscapedModel());
	}

	public com.liferay.osb.model.AccountInformation toUnescapedModel() {
		return new AccountInformationWrapper(_accountInformation.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accountInformation.toString();
	}

	public java.lang.String toXmlString() {
		return _accountInformation.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountInformation.persist();
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

		if (Validator.equals(_accountInformation,
					accountInformationWrapper._accountInformation)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AccountInformation getWrappedAccountInformation() {
		return _accountInformation;
	}

	public AccountInformation getWrappedModel() {
		return _accountInformation;
	}

	public void resetOriginalValues() {
		_accountInformation.resetOriginalValues();
	}

	private AccountInformation _accountInformation;
}