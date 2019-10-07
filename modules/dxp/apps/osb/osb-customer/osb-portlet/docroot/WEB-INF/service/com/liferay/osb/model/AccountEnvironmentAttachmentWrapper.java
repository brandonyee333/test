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
 * This class is a wrapper for {@link AccountEnvironmentAttachment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachment
 * @generated
 */
@ProviderType
public class AccountEnvironmentAttachmentWrapper
	implements AccountEnvironmentAttachment,
		ModelWrapper<AccountEnvironmentAttachment> {
	public AccountEnvironmentAttachmentWrapper(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		_accountEnvironmentAttachment = accountEnvironmentAttachment;
	}

	@Override
	public Class<?> getModelClass() {
		return AccountEnvironmentAttachment.class;
	}

	@Override
	public String getModelClassName() {
		return AccountEnvironmentAttachment.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public AccountEnvironmentAttachment toEscapedModel() {
		return new AccountEnvironmentAttachmentWrapper(_accountEnvironmentAttachment.toEscapedModel());
	}

	@Override
	public AccountEnvironmentAttachment toUnescapedModel() {
		return new AccountEnvironmentAttachmentWrapper(_accountEnvironmentAttachment.toUnescapedModel());
	}

	@Override
	public boolean fileExists()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentAttachment.fileExists();
	}

	@Override
	public boolean isCachedModel() {
		return _accountEnvironmentAttachment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accountEnvironmentAttachment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accountEnvironmentAttachment.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accountEnvironmentAttachment.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccountEnvironmentAttachment> toCacheModel() {
		return _accountEnvironmentAttachment.toCacheModel();
	}

	@Override
	public int compareTo(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		return _accountEnvironmentAttachment.compareTo(accountEnvironmentAttachment);
	}

	@Override
	public int getContentLength() {
		return _accountEnvironmentAttachment.getContentLength();
	}

	/**
	* Returns the type of this account environment attachment.
	*
	* @return the type of this account environment attachment
	*/
	@Override
	public int getType() {
		return _accountEnvironmentAttachment.getType();
	}

	@Override
	public int hashCode() {
		return _accountEnvironmentAttachment.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountEnvironmentAttachment.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AccountEnvironmentAttachmentWrapper((AccountEnvironmentAttachment)_accountEnvironmentAttachment.clone());
	}

	@Override
	public java.lang.String getFileDir() {
		return _accountEnvironmentAttachment.getFileDir();
	}

	/**
	* Returns the file name of this account environment attachment.
	*
	* @return the file name of this account environment attachment
	*/
	@Override
	public java.lang.String getFileName() {
		return _accountEnvironmentAttachment.getFileName();
	}

	/**
	* Returns the user name of this account environment attachment.
	*
	* @return the user name of this account environment attachment
	*/
	@Override
	public java.lang.String getUserName() {
		return _accountEnvironmentAttachment.getUserName();
	}

	/**
	* Returns the user uuid of this account environment attachment.
	*
	* @return the user uuid of this account environment attachment
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _accountEnvironmentAttachment.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _accountEnvironmentAttachment.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _accountEnvironmentAttachment.toXmlString();
	}

	/**
	* Returns the create date of this account environment attachment.
	*
	* @return the create date of this account environment attachment
	*/
	@Override
	public Date getCreateDate() {
		return _accountEnvironmentAttachment.getCreateDate();
	}

	/**
	* Returns the modified date of this account environment attachment.
	*
	* @return the modified date of this account environment attachment
	*/
	@Override
	public Date getModifiedDate() {
		return _accountEnvironmentAttachment.getModifiedDate();
	}

	/**
	* Returns the account environment attachment ID of this account environment attachment.
	*
	* @return the account environment attachment ID of this account environment attachment
	*/
	@Override
	public long getAccountEnvironmentAttachmentId() {
		return _accountEnvironmentAttachment.getAccountEnvironmentAttachmentId();
	}

	/**
	* Returns the account environment ID of this account environment attachment.
	*
	* @return the account environment ID of this account environment attachment
	*/
	@Override
	public long getAccountEnvironmentId() {
		return _accountEnvironmentAttachment.getAccountEnvironmentId();
	}

	/**
	* Returns the file size of this account environment attachment.
	*
	* @return the file size of this account environment attachment
	*/
	@Override
	public long getFileSize() {
		return _accountEnvironmentAttachment.getFileSize();
	}

	/**
	* Returns the primary key of this account environment attachment.
	*
	* @return the primary key of this account environment attachment
	*/
	@Override
	public long getPrimaryKey() {
		return _accountEnvironmentAttachment.getPrimaryKey();
	}

	/**
	* Returns the user ID of this account environment attachment.
	*
	* @return the user ID of this account environment attachment
	*/
	@Override
	public long getUserId() {
		return _accountEnvironmentAttachment.getUserId();
	}

	@Override
	public void persist() {
		_accountEnvironmentAttachment.persist();
	}

	/**
	* Sets the account environment attachment ID of this account environment attachment.
	*
	* @param accountEnvironmentAttachmentId the account environment attachment ID of this account environment attachment
	*/
	@Override
	public void setAccountEnvironmentAttachmentId(
		long accountEnvironmentAttachmentId) {
		_accountEnvironmentAttachment.setAccountEnvironmentAttachmentId(accountEnvironmentAttachmentId);
	}

	/**
	* Sets the account environment ID of this account environment attachment.
	*
	* @param accountEnvironmentId the account environment ID of this account environment attachment
	*/
	@Override
	public void setAccountEnvironmentId(long accountEnvironmentId) {
		_accountEnvironmentAttachment.setAccountEnvironmentId(accountEnvironmentId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accountEnvironmentAttachment.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this account environment attachment.
	*
	* @param createDate the create date of this account environment attachment
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_accountEnvironmentAttachment.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accountEnvironmentAttachment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accountEnvironmentAttachment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accountEnvironmentAttachment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file name of this account environment attachment.
	*
	* @param fileName the file name of this account environment attachment
	*/
	@Override
	public void setFileName(java.lang.String fileName) {
		_accountEnvironmentAttachment.setFileName(fileName);
	}

	/**
	* Sets the file size of this account environment attachment.
	*
	* @param fileSize the file size of this account environment attachment
	*/
	@Override
	public void setFileSize(long fileSize) {
		_accountEnvironmentAttachment.setFileSize(fileSize);
	}

	/**
	* Sets the modified date of this account environment attachment.
	*
	* @param modifiedDate the modified date of this account environment attachment
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_accountEnvironmentAttachment.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_accountEnvironmentAttachment.setNew(n);
	}

	/**
	* Sets the primary key of this account environment attachment.
	*
	* @param primaryKey the primary key of this account environment attachment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_accountEnvironmentAttachment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accountEnvironmentAttachment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type of this account environment attachment.
	*
	* @param type the type of this account environment attachment
	*/
	@Override
	public void setType(int type) {
		_accountEnvironmentAttachment.setType(type);
	}

	/**
	* Sets the user ID of this account environment attachment.
	*
	* @param userId the user ID of this account environment attachment
	*/
	@Override
	public void setUserId(long userId) {
		_accountEnvironmentAttachment.setUserId(userId);
	}

	/**
	* Sets the user name of this account environment attachment.
	*
	* @param userName the user name of this account environment attachment
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_accountEnvironmentAttachment.setUserName(userName);
	}

	/**
	* Sets the user uuid of this account environment attachment.
	*
	* @param userUuid the user uuid of this account environment attachment
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_accountEnvironmentAttachment.setUserUuid(userUuid);
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

		if (Objects.equals(_accountEnvironmentAttachment,
					accountEnvironmentAttachmentWrapper._accountEnvironmentAttachment)) {
			return true;
		}

		return false;
	}

	@Override
	public AccountEnvironmentAttachment getWrappedModel() {
		return _accountEnvironmentAttachment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accountEnvironmentAttachment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accountEnvironmentAttachment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accountEnvironmentAttachment.resetOriginalValues();
	}

	private final AccountEnvironmentAttachment _accountEnvironmentAttachment;
}