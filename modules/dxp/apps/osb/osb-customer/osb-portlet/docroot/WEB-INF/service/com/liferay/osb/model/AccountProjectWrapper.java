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
 * This class is a wrapper for {@link AccountProject}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountProject
 * @generated
 */
@ProviderType
public class AccountProjectWrapper implements AccountProject,
	ModelWrapper<AccountProject> {
	public AccountProjectWrapper(AccountProject accountProject) {
		_accountProject = accountProject;
	}

	@Override
	public Class<?> getModelClass() {
		return AccountProject.class;
	}

	@Override
	public String getModelClassName() {
		return AccountProject.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public AccountProject toEscapedModel() {
		return new AccountProjectWrapper(_accountProject.toEscapedModel());
	}

	@Override
	public AccountProject toUnescapedModel() {
		return new AccountProjectWrapper(_accountProject.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _accountProject.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accountProject.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accountProject.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accountProject.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccountProject> toCacheModel() {
		return _accountProject.toCacheModel();
	}

	@Override
	public int compareTo(AccountProject accountProject) {
		return _accountProject.compareTo(accountProject);
	}

	@Override
	public int hashCode() {
		return _accountProject.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountProject.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AccountProjectWrapper((AccountProject)_accountProject.clone());
	}

	@Override
	public java.lang.String getData(int fieldId) {
		return _accountProject.getData(fieldId);
	}

	/**
	* Returns the modified user name of this account project.
	*
	* @return the modified user name of this account project
	*/
	@Override
	public java.lang.String getModifiedUserName() {
		return _accountProject.getModifiedUserName();
	}

	/**
	* Returns the modified user uuid of this account project.
	*
	* @return the modified user uuid of this account project
	*/
	@Override
	public java.lang.String getModifiedUserUuid() {
		return _accountProject.getModifiedUserUuid();
	}

	/**
	* Returns the name of this account project.
	*
	* @return the name of this account project
	*/
	@Override
	public java.lang.String getName() {
		return _accountProject.getName();
	}

	@Override
	public java.lang.String toString() {
		return _accountProject.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _accountProject.toXmlString();
	}

	/**
	* Returns the modified date of this account project.
	*
	* @return the modified date of this account project
	*/
	@Override
	public Date getModifiedDate() {
		return _accountProject.getModifiedDate();
	}

	/**
	* Returns the account entry ID of this account project.
	*
	* @return the account entry ID of this account project
	*/
	@Override
	public long getAccountEntryId() {
		return _accountProject.getAccountEntryId();
	}

	/**
	* Returns the account project ID of this account project.
	*
	* @return the account project ID of this account project
	*/
	@Override
	public long getAccountProjectId() {
		return _accountProject.getAccountProjectId();
	}

	/**
	* Returns the modified user ID of this account project.
	*
	* @return the modified user ID of this account project
	*/
	@Override
	public long getModifiedUserId() {
		return _accountProject.getModifiedUserId();
	}

	/**
	* Returns the primary key of this account project.
	*
	* @return the primary key of this account project
	*/
	@Override
	public long getPrimaryKey() {
		return _accountProject.getPrimaryKey();
	}

	@Override
	public void addData(AccountInformation accountInformation) {
		_accountProject.addData(accountInformation);
	}

	@Override
	public void persist() {
		_accountProject.persist();
	}

	/**
	* Sets the account entry ID of this account project.
	*
	* @param accountEntryId the account entry ID of this account project
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountProject.setAccountEntryId(accountEntryId);
	}

	/**
	* Sets the account project ID of this account project.
	*
	* @param accountProjectId the account project ID of this account project
	*/
	@Override
	public void setAccountProjectId(long accountProjectId) {
		_accountProject.setAccountProjectId(accountProjectId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accountProject.setCachedModel(cachedModel);
	}

	@Override
	public void setData(
		java.util.List<AccountInformation> accountInformationList) {
		_accountProject.setData(accountInformationList);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accountProject.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accountProject.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accountProject.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this account project.
	*
	* @param modifiedDate the modified date of this account project
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_accountProject.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the modified user ID of this account project.
	*
	* @param modifiedUserId the modified user ID of this account project
	*/
	@Override
	public void setModifiedUserId(long modifiedUserId) {
		_accountProject.setModifiedUserId(modifiedUserId);
	}

	/**
	* Sets the modified user name of this account project.
	*
	* @param modifiedUserName the modified user name of this account project
	*/
	@Override
	public void setModifiedUserName(java.lang.String modifiedUserName) {
		_accountProject.setModifiedUserName(modifiedUserName);
	}

	/**
	* Sets the modified user uuid of this account project.
	*
	* @param modifiedUserUuid the modified user uuid of this account project
	*/
	@Override
	public void setModifiedUserUuid(java.lang.String modifiedUserUuid) {
		_accountProject.setModifiedUserUuid(modifiedUserUuid);
	}

	/**
	* Sets the name of this account project.
	*
	* @param name the name of this account project
	*/
	@Override
	public void setName(java.lang.String name) {
		_accountProject.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_accountProject.setNew(n);
	}

	/**
	* Sets the primary key of this account project.
	*
	* @param primaryKey the primary key of this account project
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_accountProject.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accountProject.setPrimaryKeyObj(primaryKeyObj);
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

		if (Objects.equals(_accountProject,
					accountProjectWrapper._accountProject)) {
			return true;
		}

		return false;
	}

	@Override
	public AccountProject getWrappedModel() {
		return _accountProject;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accountProject.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accountProject.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accountProject.resetOriginalValues();
	}

	private final AccountProject _accountProject;
}