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
 * This class is a wrapper for {@link CorpMembershipRequest}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpMembershipRequest
 * @generated
 */
public class CorpMembershipRequestWrapper implements CorpMembershipRequest,
	ModelWrapper<CorpMembershipRequest> {
	public CorpMembershipRequestWrapper(
		CorpMembershipRequest corpMembershipRequest) {
		_corpMembershipRequest = corpMembershipRequest;
	}

	public Class<?> getModelClass() {
		return CorpMembershipRequest.class;
	}

	public String getModelClassName() {
		return CorpMembershipRequest.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("corpMembershipRequestId", getCorpMembershipRequestId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("corpEntryId", getCorpEntryId());
		attributes.put("key", getKey());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("status", getStatus());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long corpMembershipRequestId = (Long)attributes.get(
				"corpMembershipRequestId");

		if (corpMembershipRequestId != null) {
			setCorpMembershipRequestId(corpMembershipRequestId);
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

		Long corpEntryId = (Long)attributes.get("corpEntryId");

		if (corpEntryId != null) {
			setCorpEntryId(corpEntryId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	* Returns the primary key of this corp membership request.
	*
	* @return the primary key of this corp membership request
	*/
	public long getPrimaryKey() {
		return _corpMembershipRequest.getPrimaryKey();
	}

	/**
	* Sets the primary key of this corp membership request.
	*
	* @param primaryKey the primary key of this corp membership request
	*/
	public void setPrimaryKey(long primaryKey) {
		_corpMembershipRequest.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the corp membership request ID of this corp membership request.
	*
	* @return the corp membership request ID of this corp membership request
	*/
	public long getCorpMembershipRequestId() {
		return _corpMembershipRequest.getCorpMembershipRequestId();
	}

	/**
	* Sets the corp membership request ID of this corp membership request.
	*
	* @param corpMembershipRequestId the corp membership request ID of this corp membership request
	*/
	public void setCorpMembershipRequestId(long corpMembershipRequestId) {
		_corpMembershipRequest.setCorpMembershipRequestId(corpMembershipRequestId);
	}

	/**
	* Returns the user ID of this corp membership request.
	*
	* @return the user ID of this corp membership request
	*/
	public long getUserId() {
		return _corpMembershipRequest.getUserId();
	}

	/**
	* Sets the user ID of this corp membership request.
	*
	* @param userId the user ID of this corp membership request
	*/
	public void setUserId(long userId) {
		_corpMembershipRequest.setUserId(userId);
	}

	/**
	* Returns the user uuid of this corp membership request.
	*
	* @return the user uuid of this corp membership request
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequest.getUserUuid();
	}

	/**
	* Sets the user uuid of this corp membership request.
	*
	* @param userUuid the user uuid of this corp membership request
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_corpMembershipRequest.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this corp membership request.
	*
	* @return the user name of this corp membership request
	*/
	public java.lang.String getUserName() {
		return _corpMembershipRequest.getUserName();
	}

	/**
	* Sets the user name of this corp membership request.
	*
	* @param userName the user name of this corp membership request
	*/
	public void setUserName(java.lang.String userName) {
		_corpMembershipRequest.setUserName(userName);
	}

	/**
	* Returns the create date of this corp membership request.
	*
	* @return the create date of this corp membership request
	*/
	public java.util.Date getCreateDate() {
		return _corpMembershipRequest.getCreateDate();
	}

	/**
	* Sets the create date of this corp membership request.
	*
	* @param createDate the create date of this corp membership request
	*/
	public void setCreateDate(java.util.Date createDate) {
		_corpMembershipRequest.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this corp membership request.
	*
	* @return the modified date of this corp membership request
	*/
	public java.util.Date getModifiedDate() {
		return _corpMembershipRequest.getModifiedDate();
	}

	/**
	* Sets the modified date of this corp membership request.
	*
	* @param modifiedDate the modified date of this corp membership request
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_corpMembershipRequest.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the corp entry ID of this corp membership request.
	*
	* @return the corp entry ID of this corp membership request
	*/
	public long getCorpEntryId() {
		return _corpMembershipRequest.getCorpEntryId();
	}

	/**
	* Sets the corp entry ID of this corp membership request.
	*
	* @param corpEntryId the corp entry ID of this corp membership request
	*/
	public void setCorpEntryId(long corpEntryId) {
		_corpMembershipRequest.setCorpEntryId(corpEntryId);
	}

	/**
	* Returns the key of this corp membership request.
	*
	* @return the key of this corp membership request
	*/
	public java.lang.String getKey() {
		return _corpMembershipRequest.getKey();
	}

	/**
	* Sets the key of this corp membership request.
	*
	* @param key the key of this corp membership request
	*/
	public void setKey(java.lang.String key) {
		_corpMembershipRequest.setKey(key);
	}

	/**
	* Returns the email address of this corp membership request.
	*
	* @return the email address of this corp membership request
	*/
	public java.lang.String getEmailAddress() {
		return _corpMembershipRequest.getEmailAddress();
	}

	/**
	* Sets the email address of this corp membership request.
	*
	* @param emailAddress the email address of this corp membership request
	*/
	public void setEmailAddress(java.lang.String emailAddress) {
		_corpMembershipRequest.setEmailAddress(emailAddress);
	}

	/**
	* Returns the status of this corp membership request.
	*
	* @return the status of this corp membership request
	*/
	public int getStatus() {
		return _corpMembershipRequest.getStatus();
	}

	/**
	* Sets the status of this corp membership request.
	*
	* @param status the status of this corp membership request
	*/
	public void setStatus(int status) {
		_corpMembershipRequest.setStatus(status);
	}

	public boolean isNew() {
		return _corpMembershipRequest.isNew();
	}

	public void setNew(boolean n) {
		_corpMembershipRequest.setNew(n);
	}

	public boolean isCachedModel() {
		return _corpMembershipRequest.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_corpMembershipRequest.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _corpMembershipRequest.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _corpMembershipRequest.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_corpMembershipRequest.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _corpMembershipRequest.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_corpMembershipRequest.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CorpMembershipRequestWrapper((CorpMembershipRequest)_corpMembershipRequest.clone());
	}

	public int compareTo(
		com.liferay.osb.model.CorpMembershipRequest corpMembershipRequest) {
		return _corpMembershipRequest.compareTo(corpMembershipRequest);
	}

	@Override
	public int hashCode() {
		return _corpMembershipRequest.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.CorpMembershipRequest> toCacheModel() {
		return _corpMembershipRequest.toCacheModel();
	}

	public com.liferay.osb.model.CorpMembershipRequest toEscapedModel() {
		return new CorpMembershipRequestWrapper(_corpMembershipRequest.toEscapedModel());
	}

	public com.liferay.osb.model.CorpMembershipRequest toUnescapedModel() {
		return new CorpMembershipRequestWrapper(_corpMembershipRequest.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _corpMembershipRequest.toString();
	}

	public java.lang.String toXmlString() {
		return _corpMembershipRequest.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpMembershipRequest.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CorpMembershipRequestWrapper)) {
			return false;
		}

		CorpMembershipRequestWrapper corpMembershipRequestWrapper = (CorpMembershipRequestWrapper)obj;

		if (Validator.equals(_corpMembershipRequest,
					corpMembershipRequestWrapper._corpMembershipRequest)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public CorpMembershipRequest getWrappedCorpMembershipRequest() {
		return _corpMembershipRequest;
	}

	public CorpMembershipRequest getWrappedModel() {
		return _corpMembershipRequest;
	}

	public void resetOriginalValues() {
		_corpMembershipRequest.resetOriginalValues();
	}

	private CorpMembershipRequest _corpMembershipRequest;
}