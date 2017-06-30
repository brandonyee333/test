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
 * This class is a wrapper for {@link SecurityPatch}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SecurityPatch
 * @generated
 */
public class SecurityPatchWrapper implements SecurityPatch,
	ModelWrapper<SecurityPatch> {
	public SecurityPatchWrapper(SecurityPatch securityPatch) {
		_securityPatch = securityPatch;
	}

	public Class<?> getModelClass() {
		return SecurityPatch.class;
	}

	public String getModelClassName() {
		return SecurityPatch.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("securityPatchId", getSecurityPatchId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("ticketAttachmentId", getTicketAttachmentId());
		attributes.put("portletId", getPortletId());
		attributes.put("envLFR", getEnvLFR());
		attributes.put("name", getName());
		attributes.put("fileName", getFileName());

		return attributes;
	}

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

		Long ticketAttachmentId = (Long)attributes.get("ticketAttachmentId");

		if (ticketAttachmentId != null) {
			setTicketAttachmentId(ticketAttachmentId);
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

	/**
	* Returns the primary key of this security patch.
	*
	* @return the primary key of this security patch
	*/
	public long getPrimaryKey() {
		return _securityPatch.getPrimaryKey();
	}

	/**
	* Sets the primary key of this security patch.
	*
	* @param primaryKey the primary key of this security patch
	*/
	public void setPrimaryKey(long primaryKey) {
		_securityPatch.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the security patch ID of this security patch.
	*
	* @return the security patch ID of this security patch
	*/
	public long getSecurityPatchId() {
		return _securityPatch.getSecurityPatchId();
	}

	/**
	* Sets the security patch ID of this security patch.
	*
	* @param securityPatchId the security patch ID of this security patch
	*/
	public void setSecurityPatchId(long securityPatchId) {
		_securityPatch.setSecurityPatchId(securityPatchId);
	}

	/**
	* Returns the user ID of this security patch.
	*
	* @return the user ID of this security patch
	*/
	public long getUserId() {
		return _securityPatch.getUserId();
	}

	/**
	* Sets the user ID of this security patch.
	*
	* @param userId the user ID of this security patch
	*/
	public void setUserId(long userId) {
		_securityPatch.setUserId(userId);
	}

	/**
	* Returns the user uuid of this security patch.
	*
	* @return the user uuid of this security patch
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _securityPatch.getUserUuid();
	}

	/**
	* Sets the user uuid of this security patch.
	*
	* @param userUuid the user uuid of this security patch
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_securityPatch.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this security patch.
	*
	* @return the user name of this security patch
	*/
	public java.lang.String getUserName() {
		return _securityPatch.getUserName();
	}

	/**
	* Sets the user name of this security patch.
	*
	* @param userName the user name of this security patch
	*/
	public void setUserName(java.lang.String userName) {
		_securityPatch.setUserName(userName);
	}

	/**
	* Returns the create date of this security patch.
	*
	* @return the create date of this security patch
	*/
	public java.util.Date getCreateDate() {
		return _securityPatch.getCreateDate();
	}

	/**
	* Sets the create date of this security patch.
	*
	* @param createDate the create date of this security patch
	*/
	public void setCreateDate(java.util.Date createDate) {
		_securityPatch.setCreateDate(createDate);
	}

	/**
	* Returns the account entry ID of this security patch.
	*
	* @return the account entry ID of this security patch
	*/
	public long getAccountEntryId() {
		return _securityPatch.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this security patch.
	*
	* @param accountEntryId the account entry ID of this security patch
	*/
	public void setAccountEntryId(long accountEntryId) {
		_securityPatch.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the ticket attachment ID of this security patch.
	*
	* @return the ticket attachment ID of this security patch
	*/
	public long getTicketAttachmentId() {
		return _securityPatch.getTicketAttachmentId();
	}

	/**
	* Sets the ticket attachment ID of this security patch.
	*
	* @param ticketAttachmentId the ticket attachment ID of this security patch
	*/
	public void setTicketAttachmentId(long ticketAttachmentId) {
		_securityPatch.setTicketAttachmentId(ticketAttachmentId);
	}

	/**
	* Returns the portlet ID of this security patch.
	*
	* @return the portlet ID of this security patch
	*/
	public java.lang.String getPortletId() {
		return _securityPatch.getPortletId();
	}

	/**
	* Sets the portlet ID of this security patch.
	*
	* @param portletId the portlet ID of this security patch
	*/
	public void setPortletId(java.lang.String portletId) {
		_securityPatch.setPortletId(portletId);
	}

	/**
	* Returns the env l f r of this security patch.
	*
	* @return the env l f r of this security patch
	*/
	public int getEnvLFR() {
		return _securityPatch.getEnvLFR();
	}

	/**
	* Sets the env l f r of this security patch.
	*
	* @param envLFR the env l f r of this security patch
	*/
	public void setEnvLFR(int envLFR) {
		_securityPatch.setEnvLFR(envLFR);
	}

	/**
	* Returns the name of this security patch.
	*
	* @return the name of this security patch
	*/
	public java.lang.String getName() {
		return _securityPatch.getName();
	}

	/**
	* Sets the name of this security patch.
	*
	* @param name the name of this security patch
	*/
	public void setName(java.lang.String name) {
		_securityPatch.setName(name);
	}

	/**
	* Returns the file name of this security patch.
	*
	* @return the file name of this security patch
	*/
	public java.lang.String getFileName() {
		return _securityPatch.getFileName();
	}

	/**
	* Sets the file name of this security patch.
	*
	* @param fileName the file name of this security patch
	*/
	public void setFileName(java.lang.String fileName) {
		_securityPatch.setFileName(fileName);
	}

	public boolean isNew() {
		return _securityPatch.isNew();
	}

	public void setNew(boolean n) {
		_securityPatch.setNew(n);
	}

	public boolean isCachedModel() {
		return _securityPatch.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_securityPatch.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _securityPatch.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _securityPatch.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_securityPatch.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _securityPatch.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_securityPatch.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SecurityPatchWrapper((SecurityPatch)_securityPatch.clone());
	}

	public int compareTo(com.liferay.osb.model.SecurityPatch securityPatch) {
		return _securityPatch.compareTo(securityPatch);
	}

	@Override
	public int hashCode() {
		return _securityPatch.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.SecurityPatch> toCacheModel() {
		return _securityPatch.toCacheModel();
	}

	public com.liferay.osb.model.SecurityPatch toEscapedModel() {
		return new SecurityPatchWrapper(_securityPatch.toEscapedModel());
	}

	public com.liferay.osb.model.SecurityPatch toUnescapedModel() {
		return new SecurityPatchWrapper(_securityPatch.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _securityPatch.toString();
	}

	public java.lang.String toXmlString() {
		return _securityPatch.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_securityPatch.persist();
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

		if (Validator.equals(_securityPatch, securityPatchWrapper._securityPatch)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SecurityPatch getWrappedSecurityPatch() {
		return _securityPatch;
	}

	public SecurityPatch getWrappedModel() {
		return _securityPatch;
	}

	public void resetOriginalValues() {
		_securityPatch.resetOriginalValues();
	}

	private SecurityPatch _securityPatch;
}