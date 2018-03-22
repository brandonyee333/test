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
 * This class is a wrapper for {@link AccountAttachment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountAttachment
 * @generated
 */
@ProviderType
public class AccountAttachmentWrapper implements AccountAttachment,
	ModelWrapper<AccountAttachment> {
	public AccountAttachmentWrapper(AccountAttachment accountAttachment) {
		_accountAttachment = accountAttachment;
	}

	@Override
	public Class<?> getModelClass() {
		return AccountAttachment.class;
	}

	@Override
	public String getModelClassName() {
		return AccountAttachment.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public AccountAttachment toEscapedModel() {
		return new AccountAttachmentWrapper(_accountAttachment.toEscapedModel());
	}

	@Override
	public AccountAttachment toUnescapedModel() {
		return new AccountAttachmentWrapper(_accountAttachment.toUnescapedModel());
	}

	@Override
	public boolean fileExists()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountAttachment.fileExists();
	}

	@Override
	public boolean isCachedModel() {
		return _accountAttachment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accountAttachment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accountAttachment.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accountAttachment.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccountAttachment> toCacheModel() {
		return _accountAttachment.toCacheModel();
	}

	@Override
	public int compareTo(AccountAttachment accountAttachment) {
		return _accountAttachment.compareTo(accountAttachment);
	}

	@Override
	public int getContentLength() {
		return _accountAttachment.getContentLength();
	}

	/**
	* Returns the type of this account attachment.
	*
	* @return the type of this account attachment
	*/
	@Override
	public int getType() {
		return _accountAttachment.getType();
	}

	@Override
	public int hashCode() {
		return _accountAttachment.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountAttachment.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AccountAttachmentWrapper((AccountAttachment)_accountAttachment.clone());
	}

	@Override
	public java.lang.String getFileDir() {
		return _accountAttachment.getFileDir();
	}

	/**
	* Returns the file name of this account attachment.
	*
	* @return the file name of this account attachment
	*/
	@Override
	public java.lang.String getFileName() {
		return _accountAttachment.getFileName();
	}

	@Override
	public java.lang.String getTypeLabel() {
		return _accountAttachment.getTypeLabel();
	}

	/**
	* Returns the user name of this account attachment.
	*
	* @return the user name of this account attachment
	*/
	@Override
	public java.lang.String getUserName() {
		return _accountAttachment.getUserName();
	}

	/**
	* Returns the user uuid of this account attachment.
	*
	* @return the user uuid of this account attachment
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _accountAttachment.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _accountAttachment.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _accountAttachment.toXmlString();
	}

	/**
	* Returns the create date of this account attachment.
	*
	* @return the create date of this account attachment
	*/
	@Override
	public Date getCreateDate() {
		return _accountAttachment.getCreateDate();
	}

	/**
	* Returns the account attachment ID of this account attachment.
	*
	* @return the account attachment ID of this account attachment
	*/
	@Override
	public long getAccountAttachmentId() {
		return _accountAttachment.getAccountAttachmentId();
	}

	/**
	* Returns the account entry ID of this account attachment.
	*
	* @return the account entry ID of this account attachment
	*/
	@Override
	public long getAccountEntryId() {
		return _accountAttachment.getAccountEntryId();
	}

	/**
	* Returns the account project ID of this account attachment.
	*
	* @return the account project ID of this account attachment
	*/
	@Override
	public long getAccountProjectId() {
		return _accountAttachment.getAccountProjectId();
	}

	/**
	* Returns the file size of this account attachment.
	*
	* @return the file size of this account attachment
	*/
	@Override
	public long getFileSize() {
		return _accountAttachment.getFileSize();
	}

	/**
	* Returns the primary key of this account attachment.
	*
	* @return the primary key of this account attachment
	*/
	@Override
	public long getPrimaryKey() {
		return _accountAttachment.getPrimaryKey();
	}

	/**
	* Returns the user ID of this account attachment.
	*
	* @return the user ID of this account attachment
	*/
	@Override
	public long getUserId() {
		return _accountAttachment.getUserId();
	}

	@Override
	public void persist() {
		_accountAttachment.persist();
	}

	/**
	* Sets the account attachment ID of this account attachment.
	*
	* @param accountAttachmentId the account attachment ID of this account attachment
	*/
	@Override
	public void setAccountAttachmentId(long accountAttachmentId) {
		_accountAttachment.setAccountAttachmentId(accountAttachmentId);
	}

	/**
	* Sets the account entry ID of this account attachment.
	*
	* @param accountEntryId the account entry ID of this account attachment
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountAttachment.setAccountEntryId(accountEntryId);
	}

	/**
	* Sets the account project ID of this account attachment.
	*
	* @param accountProjectId the account project ID of this account attachment
	*/
	@Override
	public void setAccountProjectId(long accountProjectId) {
		_accountAttachment.setAccountProjectId(accountProjectId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accountAttachment.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this account attachment.
	*
	* @param createDate the create date of this account attachment
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_accountAttachment.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accountAttachment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accountAttachment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accountAttachment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file name of this account attachment.
	*
	* @param fileName the file name of this account attachment
	*/
	@Override
	public void setFileName(java.lang.String fileName) {
		_accountAttachment.setFileName(fileName);
	}

	/**
	* Sets the file size of this account attachment.
	*
	* @param fileSize the file size of this account attachment
	*/
	@Override
	public void setFileSize(long fileSize) {
		_accountAttachment.setFileSize(fileSize);
	}

	@Override
	public void setNew(boolean n) {
		_accountAttachment.setNew(n);
	}

	/**
	* Sets the primary key of this account attachment.
	*
	* @param primaryKey the primary key of this account attachment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_accountAttachment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accountAttachment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type of this account attachment.
	*
	* @param type the type of this account attachment
	*/
	@Override
	public void setType(int type) {
		_accountAttachment.setType(type);
	}

	/**
	* Sets the user ID of this account attachment.
	*
	* @param userId the user ID of this account attachment
	*/
	@Override
	public void setUserId(long userId) {
		_accountAttachment.setUserId(userId);
	}

	/**
	* Sets the user name of this account attachment.
	*
	* @param userName the user name of this account attachment
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_accountAttachment.setUserName(userName);
	}

	/**
	* Sets the user uuid of this account attachment.
	*
	* @param userUuid the user uuid of this account attachment
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_accountAttachment.setUserUuid(userUuid);
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

		if (Objects.equals(_accountAttachment,
					accountAttachmentWrapper._accountAttachment)) {
			return true;
		}

		return false;
	}

	@Override
	public AccountAttachment getWrappedModel() {
		return _accountAttachment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accountAttachment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accountAttachment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accountAttachment.resetOriginalValues();
	}

	private final AccountAttachment _accountAttachment;
}