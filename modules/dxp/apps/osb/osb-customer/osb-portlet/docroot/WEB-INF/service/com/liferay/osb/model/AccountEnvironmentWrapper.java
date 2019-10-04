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
 * This class is a wrapper for {@link AccountEnvironment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironment
 * @generated
 */
@ProviderType
public class AccountEnvironmentWrapper implements AccountEnvironment,
	ModelWrapper<AccountEnvironment> {
	public AccountEnvironmentWrapper(AccountEnvironment accountEnvironment) {
		_accountEnvironment = accountEnvironment;
	}

	@Override
	public Class<?> getModelClass() {
		return AccountEnvironment.class;
	}

	@Override
	public String getModelClassName() {
		return AccountEnvironment.class.getName();
	}

	@Override
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
		attributes.put("envCommerce", getEnvCommerce());
		attributes.put("envBrowser", getEnvBrowser());
		attributes.put("envCS", getEnvCS());
		attributes.put("envSearch", getEnvSearch());

		return attributes;
	}

	@Override
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

		Integer envCommerce = (Integer)attributes.get("envCommerce");

		if (envCommerce != null) {
			setEnvCommerce(envCommerce);
		}

		Integer envBrowser = (Integer)attributes.get("envBrowser");

		if (envBrowser != null) {
			setEnvBrowser(envBrowser);
		}

		Integer envCS = (Integer)attributes.get("envCS");

		if (envCS != null) {
			setEnvCS(envCS);
		}

		String envSearch = (String)attributes.get("envSearch");

		if (envSearch != null) {
			setEnvSearch(envSearch);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _accountEnvironment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accountEnvironment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accountEnvironment.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accountEnvironment.getExpandoBridge();
	}

	@Override
	public AccountEnvironment toEscapedModel() {
		return new AccountEnvironmentWrapper(_accountEnvironment.toEscapedModel());
	}

	@Override
	public AccountEnvironment toUnescapedModel() {
		return new AccountEnvironmentWrapper(_accountEnvironment.toUnescapedModel());
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccountEnvironment> toCacheModel() {
		return _accountEnvironment.toCacheModel();
	}

	@Override
	public int compareTo(AccountEnvironment accountEnvironment) {
		return _accountEnvironment.compareTo(accountEnvironment);
	}

	/**
	* Returns the env as of this account environment.
	*
	* @return the env as of this account environment
	*/
	@Override
	public int getEnvAS() {
		return _accountEnvironment.getEnvAS();
	}

	/**
	* Returns the env browser of this account environment.
	*
	* @return the env browser of this account environment
	*/
	@Override
	public int getEnvBrowser() {
		return _accountEnvironment.getEnvBrowser();
	}

	/**
	* Returns the env cs of this account environment.
	*
	* @return the env cs of this account environment
	*/
	@Override
	public int getEnvCS() {
		return _accountEnvironment.getEnvCS();
	}

	/**
	* Returns the env commerce of this account environment.
	*
	* @return the env commerce of this account environment
	*/
	@Override
	public int getEnvCommerce() {
		return _accountEnvironment.getEnvCommerce();
	}

	/**
	* Returns the env db of this account environment.
	*
	* @return the env db of this account environment
	*/
	@Override
	public int getEnvDB() {
		return _accountEnvironment.getEnvDB();
	}

	/**
	* Returns the env jvm of this account environment.
	*
	* @return the env jvm of this account environment
	*/
	@Override
	public int getEnvJVM() {
		return _accountEnvironment.getEnvJVM();
	}

	/**
	* Returns the env lfr of this account environment.
	*
	* @return the env lfr of this account environment
	*/
	@Override
	public int getEnvLFR() {
		return _accountEnvironment.getEnvLFR();
	}

	/**
	* Returns the env os of this account environment.
	*
	* @return the env os of this account environment
	*/
	@Override
	public int getEnvOS() {
		return _accountEnvironment.getEnvOS();
	}

	@Override
	public int hashCode() {
		return _accountEnvironment.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountEnvironment.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AccountEnvironmentWrapper((AccountEnvironment)_accountEnvironment.clone());
	}

	@Override
	public java.lang.String getEnvASLabel() {
		return _accountEnvironment.getEnvASLabel();
	}

	@Override
	public java.lang.String getEnvBrowserLabel() {
		return _accountEnvironment.getEnvBrowserLabel();
	}

	@Override
	public java.lang.String getEnvCSLabel() {
		return _accountEnvironment.getEnvCSLabel();
	}

	@Override
	public java.lang.String getEnvCommerceLabel() {
		return _accountEnvironment.getEnvCommerceLabel();
	}

	@Override
	public java.lang.String getEnvDBLabel() {
		return _accountEnvironment.getEnvDBLabel();
	}

	@Override
	public java.lang.String getEnvJVMLabel() {
		return _accountEnvironment.getEnvJVMLabel();
	}

	@Override
	public java.lang.String getEnvLFRLabel() {
		return _accountEnvironment.getEnvLFRLabel();
	}

	/**
	* Returns the env os custom of this account environment.
	*
	* @return the env os custom of this account environment
	*/
	@Override
	public java.lang.String getEnvOSCustom() {
		return _accountEnvironment.getEnvOSCustom();
	}

	@Override
	public java.lang.String getEnvOSLabel() {
		return _accountEnvironment.getEnvOSLabel();
	}

	/**
	* Returns the env search of this account environment.
	*
	* @return the env search of this account environment
	*/
	@Override
	public java.lang.String getEnvSearch() {
		return _accountEnvironment.getEnvSearch();
	}

	/**
	* Returns the name of this account environment.
	*
	* @return the name of this account environment
	*/
	@Override
	public java.lang.String getName() {
		return _accountEnvironment.getName();
	}

	@Override
	public java.lang.String getSupportPhaseLabel() {
		return _accountEnvironment.getSupportPhaseLabel();
	}

	/**
	* Returns the user name of this account environment.
	*
	* @return the user name of this account environment
	*/
	@Override
	public java.lang.String getUserName() {
		return _accountEnvironment.getUserName();
	}

	/**
	* Returns the user uuid of this account environment.
	*
	* @return the user uuid of this account environment
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _accountEnvironment.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _accountEnvironment.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _accountEnvironment.toXmlString();
	}

	/**
	* Returns the create date of this account environment.
	*
	* @return the create date of this account environment
	*/
	@Override
	public Date getCreateDate() {
		return _accountEnvironment.getCreateDate();
	}

	/**
	* Returns the modified date of this account environment.
	*
	* @return the modified date of this account environment
	*/
	@Override
	public Date getModifiedDate() {
		return _accountEnvironment.getModifiedDate();
	}

	@Override
	public java.util.List<java.lang.String> getEnvSearchLabels() {
		return _accountEnvironment.getEnvSearchLabels();
	}

	/**
	* Returns the account entry ID of this account environment.
	*
	* @return the account entry ID of this account environment
	*/
	@Override
	public long getAccountEntryId() {
		return _accountEnvironment.getAccountEntryId();
	}

	/**
	* Returns the account environment ID of this account environment.
	*
	* @return the account environment ID of this account environment
	*/
	@Override
	public long getAccountEnvironmentId() {
		return _accountEnvironment.getAccountEnvironmentId();
	}

	/**
	* Returns the primary key of this account environment.
	*
	* @return the primary key of this account environment
	*/
	@Override
	public long getPrimaryKey() {
		return _accountEnvironment.getPrimaryKey();
	}

	/**
	* Returns the product entry ID of this account environment.
	*
	* @return the product entry ID of this account environment
	*/
	@Override
	public long getProductEntryId() {
		return _accountEnvironment.getProductEntryId();
	}

	/**
	* Returns the user ID of this account environment.
	*
	* @return the user ID of this account environment
	*/
	@Override
	public long getUserId() {
		return _accountEnvironment.getUserId();
	}

	@Override
	public void persist() {
		_accountEnvironment.persist();
	}

	/**
	* Sets the account entry ID of this account environment.
	*
	* @param accountEntryId the account entry ID of this account environment
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountEnvironment.setAccountEntryId(accountEntryId);
	}

	/**
	* Sets the account environment ID of this account environment.
	*
	* @param accountEnvironmentId the account environment ID of this account environment
	*/
	@Override
	public void setAccountEnvironmentId(long accountEnvironmentId) {
		_accountEnvironment.setAccountEnvironmentId(accountEnvironmentId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accountEnvironment.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this account environment.
	*
	* @param createDate the create date of this account environment
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_accountEnvironment.setCreateDate(createDate);
	}

	/**
	* Sets the env as of this account environment.
	*
	* @param envAS the env as of this account environment
	*/
	@Override
	public void setEnvAS(int envAS) {
		_accountEnvironment.setEnvAS(envAS);
	}

	/**
	* Sets the env browser of this account environment.
	*
	* @param envBrowser the env browser of this account environment
	*/
	@Override
	public void setEnvBrowser(int envBrowser) {
		_accountEnvironment.setEnvBrowser(envBrowser);
	}

	/**
	* Sets the env cs of this account environment.
	*
	* @param envCS the env cs of this account environment
	*/
	@Override
	public void setEnvCS(int envCS) {
		_accountEnvironment.setEnvCS(envCS);
	}

	/**
	* Sets the env commerce of this account environment.
	*
	* @param envCommerce the env commerce of this account environment
	*/
	@Override
	public void setEnvCommerce(int envCommerce) {
		_accountEnvironment.setEnvCommerce(envCommerce);
	}

	/**
	* Sets the env db of this account environment.
	*
	* @param envDB the env db of this account environment
	*/
	@Override
	public void setEnvDB(int envDB) {
		_accountEnvironment.setEnvDB(envDB);
	}

	/**
	* Sets the env jvm of this account environment.
	*
	* @param envJVM the env jvm of this account environment
	*/
	@Override
	public void setEnvJVM(int envJVM) {
		_accountEnvironment.setEnvJVM(envJVM);
	}

	/**
	* Sets the env lfr of this account environment.
	*
	* @param envLFR the env lfr of this account environment
	*/
	@Override
	public void setEnvLFR(int envLFR) {
		_accountEnvironment.setEnvLFR(envLFR);
	}

	/**
	* Sets the env os of this account environment.
	*
	* @param envOS the env os of this account environment
	*/
	@Override
	public void setEnvOS(int envOS) {
		_accountEnvironment.setEnvOS(envOS);
	}

	/**
	* Sets the env os custom of this account environment.
	*
	* @param envOSCustom the env os custom of this account environment
	*/
	@Override
	public void setEnvOSCustom(java.lang.String envOSCustom) {
		_accountEnvironment.setEnvOSCustom(envOSCustom);
	}

	/**
	* Sets the env search of this account environment.
	*
	* @param envSearch the env search of this account environment
	*/
	@Override
	public void setEnvSearch(java.lang.String envSearch) {
		_accountEnvironment.setEnvSearch(envSearch);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accountEnvironment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accountEnvironment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accountEnvironment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this account environment.
	*
	* @param modifiedDate the modified date of this account environment
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_accountEnvironment.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this account environment.
	*
	* @param name the name of this account environment
	*/
	@Override
	public void setName(java.lang.String name) {
		_accountEnvironment.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_accountEnvironment.setNew(n);
	}

	/**
	* Sets the primary key of this account environment.
	*
	* @param primaryKey the primary key of this account environment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_accountEnvironment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accountEnvironment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product entry ID of this account environment.
	*
	* @param productEntryId the product entry ID of this account environment
	*/
	@Override
	public void setProductEntryId(long productEntryId) {
		_accountEnvironment.setProductEntryId(productEntryId);
	}

	/**
	* Sets the user ID of this account environment.
	*
	* @param userId the user ID of this account environment
	*/
	@Override
	public void setUserId(long userId) {
		_accountEnvironment.setUserId(userId);
	}

	/**
	* Sets the user name of this account environment.
	*
	* @param userName the user name of this account environment
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_accountEnvironment.setUserName(userName);
	}

	/**
	* Sets the user uuid of this account environment.
	*
	* @param userUuid the user uuid of this account environment
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_accountEnvironment.setUserUuid(userUuid);
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

		if (Objects.equals(_accountEnvironment,
					accountEnvironmentWrapper._accountEnvironment)) {
			return true;
		}

		return false;
	}

	@Override
	public AccountEnvironment getWrappedModel() {
		return _accountEnvironment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accountEnvironment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accountEnvironment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accountEnvironment.resetOriginalValues();
	}

	private final AccountEnvironment _accountEnvironment;
}