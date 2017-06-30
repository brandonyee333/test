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
 * This class is a wrapper for {@link AccountEnvironmentAttachment}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountEnvironmentAttachment
 * @generated
 */
public class AccountEnvironmentAttachmentWrapper
	implements AccountEnvironmentAttachment,
		ModelWrapper<AccountEnvironmentAttachment> {
	public AccountEnvironmentAttachmentWrapper(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		_accountEnvironmentAttachment = accountEnvironmentAttachment;
	}

	public Class<?> getModelClass() {
		return AccountEnvironmentAttachment.class;
	}

	public String getModelClassName() {
		return AccountEnvironmentAttachment.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountEnvironmentAttachmentId",
			getAccountEnvironmentAttachmentId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEnvironmentId", getAccountEnvironmentId());
		attributes.put("fileName", getFileName());
		attributes.put("fileSize", getFileSize());
		attributes.put("type", getType());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountEnvironmentAttachmentId = (Long)attributes.get(
				"accountEnvironmentAttachmentId");

		if (accountEnvironmentAttachmentId != null) {
			setAccountEnvironmentAttachmentId(accountEnvironmentAttachmentId);
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

		Long accountEnvironmentId = (Long)attributes.get("accountEnvironmentId");

		if (accountEnvironmentId != null) {
			setAccountEnvironmentId(accountEnvironmentId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Long fileSize = (Long)attributes.get("fileSize");

		if (fileSize != null) {
			setFileSize(fileSize);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	/**
	* Returns the primary key of this account environment attachment.
	*
	* @return the primary key of this account environment attachment
	*/
	public long getPrimaryKey() {
		return _accountEnvironmentAttachment.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account environment attachment.
	*
	* @param primaryKey the primary key of this account environment attachment
	*/
	public void setPrimaryKey(long primaryKey) {
		_accountEnvironmentAttachment.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account environment attachment ID of this account environment attachment.
	*
	* @return the account environment attachment ID of this account environment attachment
	*/
	public long getAccountEnvironmentAttachmentId() {
		return _accountEnvironmentAttachment.getAccountEnvironmentAttachmentId();
	}

	/**
	* Sets the account environment attachment ID of this account environment attachment.
	*
	* @param accountEnvironmentAttachmentId the account environment attachment ID of this account environment attachment
	*/
	public void setAccountEnvironmentAttachmentId(
		long accountEnvironmentAttachmentId) {
		_accountEnvironmentAttachment.setAccountEnvironmentAttachmentId(accountEnvironmentAttachmentId);
	}

	/**
	* Returns the user ID of this account environment attachment.
	*
	* @return the user ID of this account environment attachment
	*/
	public long getUserId() {
		return _accountEnvironmentAttachment.getUserId();
	}

	/**
	* Sets the user ID of this account environment attachment.
	*
	* @param userId the user ID of this account environment attachment
	*/
	public void setUserId(long userId) {
		_accountEnvironmentAttachment.setUserId(userId);
	}

	/**
	* Returns the user uuid of this account environment attachment.
	*
	* @return the user uuid of this account environment attachment
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachment.getUserUuid();
	}

	/**
	* Sets the user uuid of this account environment attachment.
	*
	* @param userUuid the user uuid of this account environment attachment
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_accountEnvironmentAttachment.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this account environment attachment.
	*
	* @return the user name of this account environment attachment
	*/
	public java.lang.String getUserName() {
		return _accountEnvironmentAttachment.getUserName();
	}

	/**
	* Sets the user name of this account environment attachment.
	*
	* @param userName the user name of this account environment attachment
	*/
	public void setUserName(java.lang.String userName) {
		_accountEnvironmentAttachment.setUserName(userName);
	}

	/**
	* Returns the create date of this account environment attachment.
	*
	* @return the create date of this account environment attachment
	*/
	public java.util.Date getCreateDate() {
		return _accountEnvironmentAttachment.getCreateDate();
	}

	/**
	* Sets the create date of this account environment attachment.
	*
	* @param createDate the create date of this account environment attachment
	*/
	public void setCreateDate(java.util.Date createDate) {
		_accountEnvironmentAttachment.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this account environment attachment.
	*
	* @return the modified date of this account environment attachment
	*/
	public java.util.Date getModifiedDate() {
		return _accountEnvironmentAttachment.getModifiedDate();
	}

	/**
	* Sets the modified date of this account environment attachment.
	*
	* @param modifiedDate the modified date of this account environment attachment
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_accountEnvironmentAttachment.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the account environment ID of this account environment attachment.
	*
	* @return the account environment ID of this account environment attachment
	*/
	public long getAccountEnvironmentId() {
		return _accountEnvironmentAttachment.getAccountEnvironmentId();
	}

	/**
	* Sets the account environment ID of this account environment attachment.
	*
	* @param accountEnvironmentId the account environment ID of this account environment attachment
	*/
	public void setAccountEnvironmentId(long accountEnvironmentId) {
		_accountEnvironmentAttachment.setAccountEnvironmentId(accountEnvironmentId);
	}

	/**
	* Returns the file name of this account environment attachment.
	*
	* @return the file name of this account environment attachment
	*/
	public java.lang.String getFileName() {
		return _accountEnvironmentAttachment.getFileName();
	}

	/**
	* Sets the file name of this account environment attachment.
	*
	* @param fileName the file name of this account environment attachment
	*/
	public void setFileName(java.lang.String fileName) {
		_accountEnvironmentAttachment.setFileName(fileName);
	}

	/**
	* Returns the file size of this account environment attachment.
	*
	* @return the file size of this account environment attachment
	*/
	public long getFileSize() {
		return _accountEnvironmentAttachment.getFileSize();
	}

	/**
	* Sets the file size of this account environment attachment.
	*
	* @param fileSize the file size of this account environment attachment
	*/
	public void setFileSize(long fileSize) {
		_accountEnvironmentAttachment.setFileSize(fileSize);
	}

	/**
	* Returns the type of this account environment attachment.
	*
	* @return the type of this account environment attachment
	*/
	public int getType() {
		return _accountEnvironmentAttachment.getType();
	}

	/**
	* Sets the type of this account environment attachment.
	*
	* @param type the type of this account environment attachment
	*/
	public void setType(int type) {
		_accountEnvironmentAttachment.setType(type);
	}

	public boolean isNew() {
		return _accountEnvironmentAttachment.isNew();
	}

	public void setNew(boolean n) {
		_accountEnvironmentAttachment.setNew(n);
	}

	public boolean isCachedModel() {
		return _accountEnvironmentAttachment.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_accountEnvironmentAttachment.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _accountEnvironmentAttachment.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _accountEnvironmentAttachment.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_accountEnvironmentAttachment.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _accountEnvironmentAttachment.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_accountEnvironmentAttachment.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountEnvironmentAttachmentWrapper((AccountEnvironmentAttachment)_accountEnvironmentAttachment.clone());
	}

	public int compareTo(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment) {
		return _accountEnvironmentAttachment.compareTo(accountEnvironmentAttachment);
	}

	@Override
	public int hashCode() {
		return _accountEnvironmentAttachment.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AccountEnvironmentAttachment> toCacheModel() {
		return _accountEnvironmentAttachment.toCacheModel();
	}

	public com.liferay.osb.model.AccountEnvironmentAttachment toEscapedModel() {
		return new AccountEnvironmentAttachmentWrapper(_accountEnvironmentAttachment.toEscapedModel());
	}

	public com.liferay.osb.model.AccountEnvironmentAttachment toUnescapedModel() {
		return new AccountEnvironmentAttachmentWrapper(_accountEnvironmentAttachment.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accountEnvironmentAttachment.toString();
	}

	public java.lang.String toXmlString() {
		return _accountEnvironmentAttachment.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountEnvironmentAttachment.persist();
	}

	public boolean fileExists()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountEnvironmentAttachment.fileExists();
	}

	public int getContentLength() {
		return _accountEnvironmentAttachment.getContentLength();
	}

	public java.lang.String getFileDir() {
		return _accountEnvironmentAttachment.getFileDir();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountEnvironmentAttachmentWrapper)) {
			return false;
		}

		AccountEnvironmentAttachmentWrapper accountEnvironmentAttachmentWrapper = (AccountEnvironmentAttachmentWrapper)obj;

		if (Validator.equals(_accountEnvironmentAttachment,
					accountEnvironmentAttachmentWrapper._accountEnvironmentAttachment)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AccountEnvironmentAttachment getWrappedAccountEnvironmentAttachment() {
		return _accountEnvironmentAttachment;
	}

	public AccountEnvironmentAttachment getWrappedModel() {
		return _accountEnvironmentAttachment;
	}

	public void resetOriginalValues() {
		_accountEnvironmentAttachment.resetOriginalValues();
	}

	private AccountEnvironmentAttachment _accountEnvironmentAttachment;
}