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

package com.liferay.osb.customer.github.model;

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
 * This class is a wrapper for {@link Collaborator}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Collaborator
 * @generated
 */
@ProviderType
public class CollaboratorWrapper implements Collaborator,
	ModelWrapper<Collaborator> {
	public CollaboratorWrapper(Collaborator collaborator) {
		_collaborator = collaborator;
	}

	@Override
	public Class<?> getModelClass() {
		return Collaborator.class;
	}

	@Override
	public String getModelClassName() {
		return Collaborator.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("collaboratorId", getCollaboratorId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("fullName", getFullName());
		attributes.put("gitHubUserName", getGitHubUserName());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long collaboratorId = (Long)attributes.get("collaboratorId");

		if (collaboratorId != null) {
			setCollaboratorId(collaboratorId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		String fullName = (String)attributes.get("fullName");

		if (fullName != null) {
			setFullName(fullName);
		}

		String gitHubUserName = (String)attributes.get("gitHubUserName");

		if (gitHubUserName != null) {
			setGitHubUserName(gitHubUserName);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Collaborator toEscapedModel() {
		return new CollaboratorWrapper(_collaborator.toEscapedModel());
	}

	@Override
	public Collaborator toUnescapedModel() {
		return new CollaboratorWrapper(_collaborator.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _collaborator.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _collaborator.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _collaborator.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _collaborator.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Collaborator> toCacheModel() {
		return _collaborator.toCacheModel();
	}

	@Override
	public int compareTo(Collaborator collaborator) {
		return _collaborator.compareTo(collaborator);
	}

	/**
	* Returns the status of this collaborator.
	*
	* @return the status of this collaborator
	*/
	@Override
	public int getStatus() {
		return _collaborator.getStatus();
	}

	@Override
	public int hashCode() {
		return _collaborator.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _collaborator.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new CollaboratorWrapper((Collaborator)_collaborator.clone());
	}

	/**
	* Returns the email address of this collaborator.
	*
	* @return the email address of this collaborator
	*/
	@Override
	public java.lang.String getEmailAddress() {
		return _collaborator.getEmailAddress();
	}

	/**
	* Returns the full name of this collaborator.
	*
	* @return the full name of this collaborator
	*/
	@Override
	public java.lang.String getFullName() {
		return _collaborator.getFullName();
	}

	/**
	* Returns the git hub user name of this collaborator.
	*
	* @return the git hub user name of this collaborator
	*/
	@Override
	public java.lang.String getGitHubUserName() {
		return _collaborator.getGitHubUserName();
	}

	/**
	* Returns the user uuid of this collaborator.
	*
	* @return the user uuid of this collaborator
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _collaborator.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _collaborator.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _collaborator.toXmlString();
	}

	/**
	* Returns the create date of this collaborator.
	*
	* @return the create date of this collaborator
	*/
	@Override
	public Date getCreateDate() {
		return _collaborator.getCreateDate();
	}

	/**
	* Returns the account entry ID of this collaborator.
	*
	* @return the account entry ID of this collaborator
	*/
	@Override
	public long getAccountEntryId() {
		return _collaborator.getAccountEntryId();
	}

	/**
	* Returns the collaborator ID of this collaborator.
	*
	* @return the collaborator ID of this collaborator
	*/
	@Override
	public long getCollaboratorId() {
		return _collaborator.getCollaboratorId();
	}

	/**
	* Returns the primary key of this collaborator.
	*
	* @return the primary key of this collaborator
	*/
	@Override
	public long getPrimaryKey() {
		return _collaborator.getPrimaryKey();
	}

	/**
	* Returns the user ID of this collaborator.
	*
	* @return the user ID of this collaborator
	*/
	@Override
	public long getUserId() {
		return _collaborator.getUserId();
	}

	@Override
	public void persist() {
		_collaborator.persist();
	}

	/**
	* Sets the account entry ID of this collaborator.
	*
	* @param accountEntryId the account entry ID of this collaborator
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_collaborator.setAccountEntryId(accountEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_collaborator.setCachedModel(cachedModel);
	}

	/**
	* Sets the collaborator ID of this collaborator.
	*
	* @param collaboratorId the collaborator ID of this collaborator
	*/
	@Override
	public void setCollaboratorId(long collaboratorId) {
		_collaborator.setCollaboratorId(collaboratorId);
	}

	/**
	* Sets the create date of this collaborator.
	*
	* @param createDate the create date of this collaborator
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_collaborator.setCreateDate(createDate);
	}

	/**
	* Sets the email address of this collaborator.
	*
	* @param emailAddress the email address of this collaborator
	*/
	@Override
	public void setEmailAddress(java.lang.String emailAddress) {
		_collaborator.setEmailAddress(emailAddress);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_collaborator.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_collaborator.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_collaborator.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the full name of this collaborator.
	*
	* @param fullName the full name of this collaborator
	*/
	@Override
	public void setFullName(java.lang.String fullName) {
		_collaborator.setFullName(fullName);
	}

	/**
	* Sets the git hub user name of this collaborator.
	*
	* @param gitHubUserName the git hub user name of this collaborator
	*/
	@Override
	public void setGitHubUserName(java.lang.String gitHubUserName) {
		_collaborator.setGitHubUserName(gitHubUserName);
	}

	@Override
	public void setNew(boolean n) {
		_collaborator.setNew(n);
	}

	/**
	* Sets the primary key of this collaborator.
	*
	* @param primaryKey the primary key of this collaborator
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_collaborator.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_collaborator.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this collaborator.
	*
	* @param status the status of this collaborator
	*/
	@Override
	public void setStatus(int status) {
		_collaborator.setStatus(status);
	}

	/**
	* Sets the user ID of this collaborator.
	*
	* @param userId the user ID of this collaborator
	*/
	@Override
	public void setUserId(long userId) {
		_collaborator.setUserId(userId);
	}

	/**
	* Sets the user uuid of this collaborator.
	*
	* @param userUuid the user uuid of this collaborator
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_collaborator.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CollaboratorWrapper)) {
			return false;
		}

		CollaboratorWrapper collaboratorWrapper = (CollaboratorWrapper)obj;

		if (Objects.equals(_collaborator, collaboratorWrapper._collaborator)) {
			return true;
		}

		return false;
	}

	@Override
	public Collaborator getWrappedModel() {
		return _collaborator;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _collaborator.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _collaborator.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_collaborator.resetOriginalValues();
	}

	private final Collaborator _collaborator;
}