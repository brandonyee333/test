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
 * This class is a wrapper for {@link AccountEnvironment}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountEnvironment
 * @generated
 */
public class AccountEnvironmentWrapper implements AccountEnvironment,
	ModelWrapper<AccountEnvironment> {
	public AccountEnvironmentWrapper(AccountEnvironment accountEnvironment) {
		_accountEnvironment = accountEnvironment;
	}

	public Class<?> getModelClass() {
		return AccountEnvironment.class;
	}

	public String getModelClassName() {
		return AccountEnvironment.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountEnvironmentId", getAccountEnvironmentId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("productEntryId", getProductEntryId());
		attributes.put("name", getName());
		attributes.put("envOS", getEnvOS());
		attributes.put("envOSCustom", getEnvOSCustom());
		attributes.put("envDB", getEnvDB());
		attributes.put("envJVM", getEnvJVM());
		attributes.put("envAS", getEnvAS());
		attributes.put("envLFR", getEnvLFR());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountEnvironmentId = (Long)attributes.get("accountEnvironmentId");

		if (accountEnvironmentId != null) {
			setAccountEnvironmentId(accountEnvironmentId);
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

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Integer envOS = (Integer)attributes.get("envOS");

		if (envOS != null) {
			setEnvOS(envOS);
		}

		String envOSCustom = (String)attributes.get("envOSCustom");

		if (envOSCustom != null) {
			setEnvOSCustom(envOSCustom);
		}

		Integer envDB = (Integer)attributes.get("envDB");

		if (envDB != null) {
			setEnvDB(envDB);
		}

		Integer envJVM = (Integer)attributes.get("envJVM");

		if (envJVM != null) {
			setEnvJVM(envJVM);
		}

		Integer envAS = (Integer)attributes.get("envAS");

		if (envAS != null) {
			setEnvAS(envAS);
		}

		Integer envLFR = (Integer)attributes.get("envLFR");

		if (envLFR != null) {
			setEnvLFR(envLFR);
		}
	}

	/**
	* Returns the primary key of this account environment.
	*
	* @return the primary key of this account environment
	*/
	public long getPrimaryKey() {
		return _accountEnvironment.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account environment.
	*
	* @param primaryKey the primary key of this account environment
	*/
	public void setPrimaryKey(long primaryKey) {
		_accountEnvironment.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account environment ID of this account environment.
	*
	* @return the account environment ID of this account environment
	*/
	public long getAccountEnvironmentId() {
		return _accountEnvironment.getAccountEnvironmentId();
	}

	/**
	* Sets the account environment ID of this account environment.
	*
	* @param accountEnvironmentId the account environment ID of this account environment
	*/
	public void setAccountEnvironmentId(long accountEnvironmentId) {
		_accountEnvironment.setAccountEnvironmentId(accountEnvironmentId);
	}

	/**
	* Returns the user ID of this account environment.
	*
	* @return the user ID of this account environment
	*/
	public long getUserId() {
		return _accountEnvironment.getUserId();
	}

	/**
	* Sets the user ID of this account environment.
	*
	* @param userId the user ID of this account environment
	*/
	public void setUserId(long userId) {
		_accountEnvironment.setUserId(userId);
	}

	/**
	* Returns the user uuid of this account environment.
	*
	* @return the user uuid of this account environment
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironment.getUserUuid();
	}

	/**
	* Sets the user uuid of this account environment.
	*
	* @param userUuid the user uuid of this account environment
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_accountEnvironment.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this account environment.
	*
	* @return the user name of this account environment
	*/
	public java.lang.String getUserName() {
		return _accountEnvironment.getUserName();
	}

	/**
	* Sets the user name of this account environment.
	*
	* @param userName the user name of this account environment
	*/
	public void setUserName(java.lang.String userName) {
		_accountEnvironment.setUserName(userName);
	}

	/**
	* Returns the create date of this account environment.
	*
	* @return the create date of this account environment
	*/
	public java.util.Date getCreateDate() {
		return _accountEnvironment.getCreateDate();
	}

	/**
	* Sets the create date of this account environment.
	*
	* @param createDate the create date of this account environment
	*/
	public void setCreateDate(java.util.Date createDate) {
		_accountEnvironment.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this account environment.
	*
	* @return the modified date of this account environment
	*/
	public java.util.Date getModifiedDate() {
		return _accountEnvironment.getModifiedDate();
	}

	/**
	* Sets the modified date of this account environment.
	*
	* @param modifiedDate the modified date of this account environment
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_accountEnvironment.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the account entry ID of this account environment.
	*
	* @return the account entry ID of this account environment
	*/
	public long getAccountEntryId() {
		return _accountEnvironment.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this account environment.
	*
	* @param accountEntryId the account entry ID of this account environment
	*/
	public void setAccountEntryId(long accountEntryId) {
		_accountEnvironment.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the product entry ID of this account environment.
	*
	* @return the product entry ID of this account environment
	*/
	public long getProductEntryId() {
		return _accountEnvironment.getProductEntryId();
	}

	/**
	* Sets the product entry ID of this account environment.
	*
	* @param productEntryId the product entry ID of this account environment
	*/
	public void setProductEntryId(long productEntryId) {
		_accountEnvironment.setProductEntryId(productEntryId);
	}

	/**
	* Returns the name of this account environment.
	*
	* @return the name of this account environment
	*/
	public java.lang.String getName() {
		return _accountEnvironment.getName();
	}

	/**
	* Sets the name of this account environment.
	*
	* @param name the name of this account environment
	*/
	public void setName(java.lang.String name) {
		_accountEnvironment.setName(name);
	}

	/**
	* Returns the env o s of this account environment.
	*
	* @return the env o s of this account environment
	*/
	public int getEnvOS() {
		return _accountEnvironment.getEnvOS();
	}

	/**
	* Sets the env o s of this account environment.
	*
	* @param envOS the env o s of this account environment
	*/
	public void setEnvOS(int envOS) {
		_accountEnvironment.setEnvOS(envOS);
	}

	/**
	* Returns the env o s custom of this account environment.
	*
	* @return the env o s custom of this account environment
	*/
	public java.lang.String getEnvOSCustom() {
		return _accountEnvironment.getEnvOSCustom();
	}

	/**
	* Sets the env o s custom of this account environment.
	*
	* @param envOSCustom the env o s custom of this account environment
	*/
	public void setEnvOSCustom(java.lang.String envOSCustom) {
		_accountEnvironment.setEnvOSCustom(envOSCustom);
	}

	/**
	* Returns the env d b of this account environment.
	*
	* @return the env d b of this account environment
	*/
	public int getEnvDB() {
		return _accountEnvironment.getEnvDB();
	}

	/**
	* Sets the env d b of this account environment.
	*
	* @param envDB the env d b of this account environment
	*/
	public void setEnvDB(int envDB) {
		_accountEnvironment.setEnvDB(envDB);
	}

	/**
	* Returns the env j v m of this account environment.
	*
	* @return the env j v m of this account environment
	*/
	public int getEnvJVM() {
		return _accountEnvironment.getEnvJVM();
	}

	/**
	* Sets the env j v m of this account environment.
	*
	* @param envJVM the env j v m of this account environment
	*/
	public void setEnvJVM(int envJVM) {
		_accountEnvironment.setEnvJVM(envJVM);
	}

	/**
	* Returns the env a s of this account environment.
	*
	* @return the env a s of this account environment
	*/
	public int getEnvAS() {
		return _accountEnvironment.getEnvAS();
	}

	/**
	* Sets the env a s of this account environment.
	*
	* @param envAS the env a s of this account environment
	*/
	public void setEnvAS(int envAS) {
		_accountEnvironment.setEnvAS(envAS);
	}

	/**
	* Returns the env l f r of this account environment.
	*
	* @return the env l f r of this account environment
	*/
	public int getEnvLFR() {
		return _accountEnvironment.getEnvLFR();
	}

	/**
	* Sets the env l f r of this account environment.
	*
	* @param envLFR the env l f r of this account environment
	*/
	public void setEnvLFR(int envLFR) {
		_accountEnvironment.setEnvLFR(envLFR);
	}

	public boolean isNew() {
		return _accountEnvironment.isNew();
	}

	public void setNew(boolean n) {
		_accountEnvironment.setNew(n);
	}

	public boolean isCachedModel() {
		return _accountEnvironment.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_accountEnvironment.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _accountEnvironment.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _accountEnvironment.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_accountEnvironment.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _accountEnvironment.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_accountEnvironment.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountEnvironmentWrapper((AccountEnvironment)_accountEnvironment.clone());
	}

	public int compareTo(
		com.liferay.osb.model.AccountEnvironment accountEnvironment) {
		return _accountEnvironment.compareTo(accountEnvironment);
	}

	@Override
	public int hashCode() {
		return _accountEnvironment.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AccountEnvironment> toCacheModel() {
		return _accountEnvironment.toCacheModel();
	}

	public com.liferay.osb.model.AccountEnvironment toEscapedModel() {
		return new AccountEnvironmentWrapper(_accountEnvironment.toEscapedModel());
	}

	public com.liferay.osb.model.AccountEnvironment toUnescapedModel() {
		return new AccountEnvironmentWrapper(_accountEnvironment.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accountEnvironment.toString();
	}

	public java.lang.String toXmlString() {
		return _accountEnvironment.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountEnvironment.persist();
	}

	public java.lang.String getEnvASLabel() {
		return _accountEnvironment.getEnvASLabel();
	}

	public java.lang.String getEnvDBLabel() {
		return _accountEnvironment.getEnvDBLabel();
	}

	public java.lang.String getEnvJVMLabel() {
		return _accountEnvironment.getEnvJVMLabel();
	}

	public java.lang.String getEnvLFRLabel() {
		return _accountEnvironment.getEnvLFRLabel();
	}

	public java.lang.String getEnvOSLabel() {
		return _accountEnvironment.getEnvOSLabel();
	}

	public java.lang.String getSupportPhaseLabel() {
		return _accountEnvironment.getSupportPhaseLabel();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountEnvironmentWrapper)) {
			return false;
		}

		AccountEnvironmentWrapper accountEnvironmentWrapper = (AccountEnvironmentWrapper)obj;

		if (Validator.equals(_accountEnvironment,
					accountEnvironmentWrapper._accountEnvironment)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AccountEnvironment getWrappedAccountEnvironment() {
		return _accountEnvironment;
	}

	public AccountEnvironment getWrappedModel() {
		return _accountEnvironment;
	}

	public void resetOriginalValues() {
		_accountEnvironment.resetOriginalValues();
	}

	private AccountEnvironment _accountEnvironment;
}