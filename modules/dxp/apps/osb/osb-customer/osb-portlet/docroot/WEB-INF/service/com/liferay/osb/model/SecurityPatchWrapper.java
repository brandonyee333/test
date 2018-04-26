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
 * This class is a wrapper for {@link SecurityPatch}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecurityPatch
 * @generated
 */
@ProviderType
public class SecurityPatchWrapper implements SecurityPatch,
	ModelWrapper<SecurityPatch> {
	public SecurityPatchWrapper(SecurityPatch securityPatch) {
		_securityPatch = securityPatch;
	}

	@Override
	public Class<?> getModelClass() {
		return SecurityPatch.class;
	}

	@Override
	public String getModelClassName() {
		return SecurityPatch.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("securityPatchId", getSecurityPatchId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("portletId", getPortletId());
		attributes.put("envLFR", getEnvLFR());
		attributes.put("name", getName());
		attributes.put("fileName", getFileName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long securityPatchId = (Long)attributes.get("securityPatchId");

		if (securityPatchId != null) {
			setSecurityPatchId(securityPatchId);
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

		String portletId = (String)attributes.get("portletId");

		if (portletId != null) {
			setPortletId(portletId);
		}

		Integer envLFR = (Integer)attributes.get("envLFR");

		if (envLFR != null) {
			setEnvLFR(envLFR);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}
	}

	@Override
	public SecurityPatch toEscapedModel() {
		return new SecurityPatchWrapper(_securityPatch.toEscapedModel());
	}

	@Override
	public SecurityPatch toUnescapedModel() {
		return new SecurityPatchWrapper(_securityPatch.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _securityPatch.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _securityPatch.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _securityPatch.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _securityPatch.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SecurityPatch> toCacheModel() {
		return _securityPatch.toCacheModel();
	}

	@Override
	public int compareTo(SecurityPatch securityPatch) {
		return _securityPatch.compareTo(securityPatch);
	}

	/**
	* Returns the env lfr of this security patch.
	*
	* @return the env lfr of this security patch
	*/
	@Override
	public int getEnvLFR() {
		return _securityPatch.getEnvLFR();
	}

	@Override
	public int hashCode() {
		return _securityPatch.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _securityPatch.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SecurityPatchWrapper((SecurityPatch)_securityPatch.clone());
	}

	/**
	* Returns the file name of this security patch.
	*
	* @return the file name of this security patch
	*/
	@Override
	public java.lang.String getFileName() {
		return _securityPatch.getFileName();
	}

	/**
	* Returns the name of this security patch.
	*
	* @return the name of this security patch
	*/
	@Override
	public java.lang.String getName() {
		return _securityPatch.getName();
	}

	/**
	* Returns the portlet ID of this security patch.
	*
	* @return the portlet ID of this security patch
	*/
	@Override
	public java.lang.String getPortletId() {
		return _securityPatch.getPortletId();
	}

	/**
	* Returns the user name of this security patch.
	*
	* @return the user name of this security patch
	*/
	@Override
	public java.lang.String getUserName() {
		return _securityPatch.getUserName();
	}

	/**
	* Returns the user uuid of this security patch.
	*
	* @return the user uuid of this security patch
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _securityPatch.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _securityPatch.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _securityPatch.toXmlString();
	}

	/**
	* Returns the create date of this security patch.
	*
	* @return the create date of this security patch
	*/
	@Override
	public Date getCreateDate() {
		return _securityPatch.getCreateDate();
	}

	/**
	* Returns the account entry ID of this security patch.
	*
	* @return the account entry ID of this security patch
	*/
	@Override
	public long getAccountEntryId() {
		return _securityPatch.getAccountEntryId();
	}

	/**
	* Returns the primary key of this security patch.
	*
	* @return the primary key of this security patch
	*/
	@Override
	public long getPrimaryKey() {
		return _securityPatch.getPrimaryKey();
	}

	/**
	* Returns the security patch ID of this security patch.
	*
	* @return the security patch ID of this security patch
	*/
	@Override
	public long getSecurityPatchId() {
		return _securityPatch.getSecurityPatchId();
	}

	/**
	* Returns the user ID of this security patch.
	*
	* @return the user ID of this security patch
	*/
	@Override
	public long getUserId() {
		return _securityPatch.getUserId();
	}

	@Override
	public void persist() {
		_securityPatch.persist();
	}

	/**
	* Sets the account entry ID of this security patch.
	*
	* @param accountEntryId the account entry ID of this security patch
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_securityPatch.setAccountEntryId(accountEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_securityPatch.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this security patch.
	*
	* @param createDate the create date of this security patch
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_securityPatch.setCreateDate(createDate);
	}

	/**
	* Sets the env lfr of this security patch.
	*
	* @param envLFR the env lfr of this security patch
	*/
	@Override
	public void setEnvLFR(int envLFR) {
		_securityPatch.setEnvLFR(envLFR);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_securityPatch.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_securityPatch.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_securityPatch.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file name of this security patch.
	*
	* @param fileName the file name of this security patch
	*/
	@Override
	public void setFileName(java.lang.String fileName) {
		_securityPatch.setFileName(fileName);
	}

	/**
	* Sets the name of this security patch.
	*
	* @param name the name of this security patch
	*/
	@Override
	public void setName(java.lang.String name) {
		_securityPatch.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_securityPatch.setNew(n);
	}

	/**
	* Sets the portlet ID of this security patch.
	*
	* @param portletId the portlet ID of this security patch
	*/
	@Override
	public void setPortletId(java.lang.String portletId) {
		_securityPatch.setPortletId(portletId);
	}

	/**
	* Sets the primary key of this security patch.
	*
	* @param primaryKey the primary key of this security patch
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_securityPatch.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_securityPatch.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the security patch ID of this security patch.
	*
	* @param securityPatchId the security patch ID of this security patch
	*/
	@Override
	public void setSecurityPatchId(long securityPatchId) {
		_securityPatch.setSecurityPatchId(securityPatchId);
	}

	/**
	* Sets the user ID of this security patch.
	*
	* @param userId the user ID of this security patch
	*/
	@Override
	public void setUserId(long userId) {
		_securityPatch.setUserId(userId);
	}

	/**
	* Sets the user name of this security patch.
	*
	* @param userName the user name of this security patch
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_securityPatch.setUserName(userName);
	}

	/**
	* Sets the user uuid of this security patch.
	*
	* @param userUuid the user uuid of this security patch
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_securityPatch.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SecurityPatchWrapper)) {
			return false;
		}

		SecurityPatchWrapper securityPatchWrapper = (SecurityPatchWrapper)obj;

		if (Objects.equals(_securityPatch, securityPatchWrapper._securityPatch)) {
			return true;
		}

		return false;
	}

	@Override
	public SecurityPatch getWrappedModel() {
		return _securityPatch;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _securityPatch.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _securityPatch.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_securityPatch.resetOriginalValues();
	}

	private final SecurityPatch _securityPatch;
}