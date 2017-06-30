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
 * This class is a wrapper for {@link AccountProject}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountProject
 * @generated
 */
public class AccountProjectWrapper implements AccountProject,
	ModelWrapper<AccountProject> {
	public AccountProjectWrapper(AccountProject accountProject) {
		_accountProject = accountProject;
	}

	public Class<?> getModelClass() {
		return AccountProject.class;
	}

	public String getModelClassName() {
		return AccountProject.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountProjectId", getAccountProjectId());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("name", getName());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountProjectId = (Long)attributes.get("accountProjectId");

		if (accountProjectId != null) {
			setAccountProjectId(accountProjectId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	/**
	* Returns the primary key of this account project.
	*
	* @return the primary key of this account project
	*/
	public long getPrimaryKey() {
		return _accountProject.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account project.
	*
	* @param primaryKey the primary key of this account project
	*/
	public void setPrimaryKey(long primaryKey) {
		_accountProject.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account project ID of this account project.
	*
	* @return the account project ID of this account project
	*/
	public long getAccountProjectId() {
		return _accountProject.getAccountProjectId();
	}

	/**
	* Sets the account project ID of this account project.
	*
	* @param accountProjectId the account project ID of this account project
	*/
	public void setAccountProjectId(long accountProjectId) {
		_accountProject.setAccountProjectId(accountProjectId);
	}

	/**
	* Returns the modified user ID of this account project.
	*
	* @return the modified user ID of this account project
	*/
	public long getModifiedUserId() {
		return _accountProject.getModifiedUserId();
	}

	/**
	* Sets the modified user ID of this account project.
	*
	* @param modifiedUserId the modified user ID of this account project
	*/
	public void setModifiedUserId(long modifiedUserId) {
		_accountProject.setModifiedUserId(modifiedUserId);
	}

	/**
	* Returns the modified user uuid of this account project.
	*
	* @return the modified user uuid of this account project
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getModifiedUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProject.getModifiedUserUuid();
	}

	/**
	* Sets the modified user uuid of this account project.
	*
	* @param modifiedUserUuid the modified user uuid of this account project
	*/
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_accountProject.setModifiedUserUuid(modifiedUserUuid);
	}

	/**
	* Returns the modified user name of this account project.
	*
	* @return the modified user name of this account project
	*/
	public java.lang.String getModifiedUserName() {
		return _accountProject.getModifiedUserName();
	}

	/**
	* Sets the modified user name of this account project.
	*
	* @param modifiedUserName the modified user name of this account project
	*/
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_accountProject.setModifiedUserName(modifiedUserName);
	}

	/**
	* Returns the modified date of this account project.
	*
	* @return the modified date of this account project
	*/
	public java.util.Date getModifiedDate() {
		return _accountProject.getModifiedDate();
	}

	/**
	* Sets the modified date of this account project.
	*
	* @param modifiedDate the modified date of this account project
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_accountProject.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the account entry ID of this account project.
	*
	* @return the account entry ID of this account project
	*/
	public long getAccountEntryId() {
		return _accountProject.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this account project.
	*
	* @param accountEntryId the account entry ID of this account project
	*/
	public void setAccountEntryId(long accountEntryId) {
		_accountProject.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the name of this account project.
	*
	* @return the name of this account project
	*/
	public java.lang.String getName() {
		return _accountProject.getName();
	}

	/**
	* Sets the name of this account project.
	*
	* @param name the name of this account project
	*/
	public void setName(java.lang.String name) {
		_accountProject.setName(name);
	}

	public boolean isNew() {
		return _accountProject.isNew();
	}

	public void setNew(boolean n) {
		_accountProject.setNew(n);
	}

	public boolean isCachedModel() {
		return _accountProject.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_accountProject.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _accountProject.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _accountProject.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_accountProject.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _accountProject.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_accountProject.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountProjectWrapper((AccountProject)_accountProject.clone());
	}

	public int compareTo(com.liferay.osb.model.AccountProject accountProject) {
		return _accountProject.compareTo(accountProject);
	}

	@Override
	public int hashCode() {
		return _accountProject.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AccountProject> toCacheModel() {
		return _accountProject.toCacheModel();
	}

	public com.liferay.osb.model.AccountProject toEscapedModel() {
		return new AccountProjectWrapper(_accountProject.toEscapedModel());
	}

	public com.liferay.osb.model.AccountProject toUnescapedModel() {
		return new AccountProjectWrapper(_accountProject.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accountProject.toString();
	}

	public java.lang.String toXmlString() {
		return _accountProject.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountProject.persist();
	}

	public void addData(
		com.liferay.osb.model.AccountInformation accountInformation) {
		_accountProject.addData(accountInformation);
	}

	public java.lang.String getData(int fieldId) {
		return _accountProject.getData(fieldId);
	}

	public void setData(
		java.util.List<com.liferay.osb.model.AccountInformation> accountInformationList) {
		_accountProject.setData(accountInformationList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountProjectWrapper)) {
			return false;
		}

		AccountProjectWrapper accountProjectWrapper = (AccountProjectWrapper)obj;

		if (Validator.equals(_accountProject,
					accountProjectWrapper._accountProject)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AccountProject getWrappedAccountProject() {
		return _accountProject;
	}

	public AccountProject getWrappedModel() {
		return _accountProject;
	}

	public void resetOriginalValues() {
		_accountProject.resetOriginalValues();
	}

	private AccountProject _accountProject;
}