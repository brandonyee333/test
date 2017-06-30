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
 * This class is a wrapper for {@link AccountAttachment}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountAttachment
 * @generated
 */
public class AccountAttachmentWrapper implements AccountAttachment,
	ModelWrapper<AccountAttachment> {
	public AccountAttachmentWrapper(AccountAttachment accountAttachment) {
		_accountAttachment = accountAttachment;
	}

	public Class<?> getModelClass() {
		return AccountAttachment.class;
	}

	public String getModelClassName() {
		return AccountAttachment.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountAttachmentId", getAccountAttachmentId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("accountProjectId", getAccountProjectId());
		attributes.put("fileName", getFileName());
		attributes.put("fileSize", getFileSize());
		attributes.put("type", getType());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountAttachmentId = (Long)attributes.get("accountAttachmentId");

		if (accountAttachmentId != null) {
			setAccountAttachmentId(accountAttachmentId);
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

		Long accountProjectId = (Long)attributes.get("accountProjectId");

		if (accountProjectId != null) {
			setAccountProjectId(accountProjectId);
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
	* Returns the primary key of this account attachment.
	*
	* @return the primary key of this account attachment
	*/
	public long getPrimaryKey() {
		return _accountAttachment.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account attachment.
	*
	* @param primaryKey the primary key of this account attachment
	*/
	public void setPrimaryKey(long primaryKey) {
		_accountAttachment.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account attachment ID of this account attachment.
	*
	* @return the account attachment ID of this account attachment
	*/
	public long getAccountAttachmentId() {
		return _accountAttachment.getAccountAttachmentId();
	}

	/**
	* Sets the account attachment ID of this account attachment.
	*
	* @param accountAttachmentId the account attachment ID of this account attachment
	*/
	public void setAccountAttachmentId(long accountAttachmentId) {
		_accountAttachment.setAccountAttachmentId(accountAttachmentId);
	}

	/**
	* Returns the user ID of this account attachment.
	*
	* @return the user ID of this account attachment
	*/
	public long getUserId() {
		return _accountAttachment.getUserId();
	}

	/**
	* Sets the user ID of this account attachment.
	*
	* @param userId the user ID of this account attachment
	*/
	public void setUserId(long userId) {
		_accountAttachment.setUserId(userId);
	}

	/**
	* Returns the user uuid of this account attachment.
	*
	* @return the user uuid of this account attachment
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachment.getUserUuid();
	}

	/**
	* Sets the user uuid of this account attachment.
	*
	* @param userUuid the user uuid of this account attachment
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_accountAttachment.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this account attachment.
	*
	* @return the user name of this account attachment
	*/
	public java.lang.String getUserName() {
		return _accountAttachment.getUserName();
	}

	/**
	* Sets the user name of this account attachment.
	*
	* @param userName the user name of this account attachment
	*/
	public void setUserName(java.lang.String userName) {
		_accountAttachment.setUserName(userName);
	}

	/**
	* Returns the create date of this account attachment.
	*
	* @return the create date of this account attachment
	*/
	public java.util.Date getCreateDate() {
		return _accountAttachment.getCreateDate();
	}

	/**
	* Sets the create date of this account attachment.
	*
	* @param createDate the create date of this account attachment
	*/
	public void setCreateDate(java.util.Date createDate) {
		_accountAttachment.setCreateDate(createDate);
	}

	/**
	* Returns the account entry ID of this account attachment.
	*
	* @return the account entry ID of this account attachment
	*/
	public long getAccountEntryId() {
		return _accountAttachment.getAccountEntryId();
	}

	/**
	* Sets the account entry ID of this account attachment.
	*
	* @param accountEntryId the account entry ID of this account attachment
	*/
	public void setAccountEntryId(long accountEntryId) {
		_accountAttachment.setAccountEntryId(accountEntryId);
	}

	/**
	* Returns the account project ID of this account attachment.
	*
	* @return the account project ID of this account attachment
	*/
	public long getAccountProjectId() {
		return _accountAttachment.getAccountProjectId();
	}

	/**
	* Sets the account project ID of this account attachment.
	*
	* @param accountProjectId the account project ID of this account attachment
	*/
	public void setAccountProjectId(long accountProjectId) {
		_accountAttachment.setAccountProjectId(accountProjectId);
	}

	/**
	* Returns the file name of this account attachment.
	*
	* @return the file name of this account attachment
	*/
	public java.lang.String getFileName() {
		return _accountAttachment.getFileName();
	}

	/**
	* Sets the file name of this account attachment.
	*
	* @param fileName the file name of this account attachment
	*/
	public void setFileName(java.lang.String fileName) {
		_accountAttachment.setFileName(fileName);
	}

	/**
	* Returns the file size of this account attachment.
	*
	* @return the file size of this account attachment
	*/
	public long getFileSize() {
		return _accountAttachment.getFileSize();
	}

	/**
	* Sets the file size of this account attachment.
	*
	* @param fileSize the file size of this account attachment
	*/
	public void setFileSize(long fileSize) {
		_accountAttachment.setFileSize(fileSize);
	}

	/**
	* Returns the type of this account attachment.
	*
	* @return the type of this account attachment
	*/
	public int getType() {
		return _accountAttachment.getType();
	}

	/**
	* Sets the type of this account attachment.
	*
	* @param type the type of this account attachment
	*/
	public void setType(int type) {
		_accountAttachment.setType(type);
	}

	public boolean isNew() {
		return _accountAttachment.isNew();
	}

	public void setNew(boolean n) {
		_accountAttachment.setNew(n);
	}

	public boolean isCachedModel() {
		return _accountAttachment.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_accountAttachment.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _accountAttachment.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _accountAttachment.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_accountAttachment.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _accountAttachment.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_accountAttachment.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountAttachmentWrapper((AccountAttachment)_accountAttachment.clone());
	}

	public int compareTo(
		com.liferay.osb.model.AccountAttachment accountAttachment) {
		return _accountAttachment.compareTo(accountAttachment);
	}

	@Override
	public int hashCode() {
		return _accountAttachment.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AccountAttachment> toCacheModel() {
		return _accountAttachment.toCacheModel();
	}

	public com.liferay.osb.model.AccountAttachment toEscapedModel() {
		return new AccountAttachmentWrapper(_accountAttachment.toEscapedModel());
	}

	public com.liferay.osb.model.AccountAttachment toUnescapedModel() {
		return new AccountAttachmentWrapper(_accountAttachment.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accountAttachment.toString();
	}

	public java.lang.String toXmlString() {
		return _accountAttachment.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_accountAttachment.persist();
	}

	public boolean fileExists()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountAttachment.fileExists();
	}

	public int getContentLength() {
		return _accountAttachment.getContentLength();
	}

	public java.lang.String getFileDir() {
		return _accountAttachment.getFileDir();
	}

	public java.lang.String getTypeLabel() {
		return _accountAttachment.getTypeLabel();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountAttachmentWrapper)) {
			return false;
		}

		AccountAttachmentWrapper accountAttachmentWrapper = (AccountAttachmentWrapper)obj;

		if (Validator.equals(_accountAttachment,
					accountAttachmentWrapper._accountAttachment)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AccountAttachment getWrappedAccountAttachment() {
		return _accountAttachment;
	}

	public AccountAttachment getWrappedModel() {
		return _accountAttachment;
	}

	public void resetOriginalValues() {
		_accountAttachment.resetOriginalValues();
	}

	private AccountAttachment _accountAttachment;
}