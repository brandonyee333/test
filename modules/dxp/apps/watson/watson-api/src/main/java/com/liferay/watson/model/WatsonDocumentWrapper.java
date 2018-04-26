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

package com.liferay.watson.model;

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
 * This class is a wrapper for {@link WatsonDocument}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonDocument
 * @generated
 */
@ProviderType
public class WatsonDocumentWrapper implements WatsonDocument,
	ModelWrapper<WatsonDocument> {
	public WatsonDocumentWrapper(WatsonDocument watsonDocument) {
		_watsonDocument = watsonDocument;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonDocument.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonDocument.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonDocumentId", getWatsonDocumentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentTypeWatsonListTypeId",
			getParentTypeWatsonListTypeId());
		attributes.put("subtypeWatsonListTypeId", getSubtypeWatsonListTypeId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("watsonChildId", getWatsonChildId());
		attributes.put("originalDocument", isOriginalDocument());
		attributes.put("receivedDate", getReceivedDate());
		attributes.put("imagePayload", getImagePayload());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonDocumentId = (Long)attributes.get("watsonDocumentId");

		if (watsonDocumentId != null) {
			setWatsonDocumentId(watsonDocumentId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		Long parentTypeWatsonListTypeId = (Long)attributes.get(
				"parentTypeWatsonListTypeId");

		if (parentTypeWatsonListTypeId != null) {
			setParentTypeWatsonListTypeId(parentTypeWatsonListTypeId);
		}

		Long subtypeWatsonListTypeId = (Long)attributes.get(
				"subtypeWatsonListTypeId");

		if (subtypeWatsonListTypeId != null) {
			setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get("typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long watsonChildId = (Long)attributes.get("watsonChildId");

		if (watsonChildId != null) {
			setWatsonChildId(watsonChildId);
		}

		Boolean originalDocument = (Boolean)attributes.get("originalDocument");

		if (originalDocument != null) {
			setOriginalDocument(originalDocument);
		}

		Date receivedDate = (Date)attributes.get("receivedDate");

		if (receivedDate != null) {
			setReceivedDate(receivedDate);
		}

		String imagePayload = (String)attributes.get("imagePayload");

		if (imagePayload != null) {
			setImagePayload(imagePayload);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WatsonDocumentWrapper((WatsonDocument)_watsonDocument.clone());
	}

	@Override
	public int compareTo(WatsonDocument watsonDocument) {
		return _watsonDocument.compareTo(watsonDocument);
	}

	/**
	* Returns the company ID of this watson document.
	*
	* @return the company ID of this watson document
	*/
	@Override
	public long getCompanyId() {
		return _watsonDocument.getCompanyId();
	}

	/**
	* Returns the create date of this watson document.
	*
	* @return the create date of this watson document
	*/
	@Override
	public Date getCreateDate() {
		return _watsonDocument.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonDocument.getExpandoBridge();
	}

	/**
	* Returns the group ID of this watson document.
	*
	* @return the group ID of this watson document
	*/
	@Override
	public long getGroupId() {
		return _watsonDocument.getGroupId();
	}

	/**
	* Returns the image payload of this watson document.
	*
	* @return the image payload of this watson document
	*/
	@Override
	public java.lang.String getImagePayload() {
		return _watsonDocument.getImagePayload();
	}

	/**
	* Returns the modified date of this watson document.
	*
	* @return the modified date of this watson document
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonDocument.getModifiedDate();
	}

	/**
	* Returns the original document of this watson document.
	*
	* @return the original document of this watson document
	*/
	@Override
	public boolean getOriginalDocument() {
		return _watsonDocument.getOriginalDocument();
	}

	/**
	* Returns the parent type watson list type ID of this watson document.
	*
	* @return the parent type watson list type ID of this watson document
	*/
	@Override
	public long getParentTypeWatsonListTypeId() {
		return _watsonDocument.getParentTypeWatsonListTypeId();
	}

	/**
	* Returns the primary key of this watson document.
	*
	* @return the primary key of this watson document
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonDocument.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonDocument.getPrimaryKeyObj();
	}

	/**
	* Returns the received date of this watson document.
	*
	* @return the received date of this watson document
	*/
	@Override
	public Date getReceivedDate() {
		return _watsonDocument.getReceivedDate();
	}

	/**
	* Returns the status of this watson document.
	*
	* @return the status of this watson document
	*/
	@Override
	public int getStatus() {
		return _watsonDocument.getStatus();
	}

	/**
	* Returns the subtype watson list type ID of this watson document.
	*
	* @return the subtype watson list type ID of this watson document
	*/
	@Override
	public long getSubtypeWatsonListTypeId() {
		return _watsonDocument.getSubtypeWatsonListTypeId();
	}

	/**
	* Returns the type watson list type ID of this watson document.
	*
	* @return the type watson list type ID of this watson document
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonDocument.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson document.
	*
	* @return the user ID of this watson document
	*/
	@Override
	public long getUserId() {
		return _watsonDocument.getUserId();
	}

	/**
	* Returns the user name of this watson document.
	*
	* @return the user name of this watson document
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonDocument.getUserName();
	}

	/**
	* Returns the user uuid of this watson document.
	*
	* @return the user uuid of this watson document
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonDocument.getUserUuid();
	}

	/**
	* Returns the watson child ID of this watson document.
	*
	* @return the watson child ID of this watson document
	*/
	@Override
	public long getWatsonChildId() {
		return _watsonDocument.getWatsonChildId();
	}

	/**
	* Returns the watson document ID of this watson document.
	*
	* @return the watson document ID of this watson document
	*/
	@Override
	public long getWatsonDocumentId() {
		return _watsonDocument.getWatsonDocumentId();
	}

	@Override
	public int hashCode() {
		return _watsonDocument.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonDocument.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonDocument.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonDocument.isNew();
	}

	/**
	* Returns <code>true</code> if this watson document is original document.
	*
	* @return <code>true</code> if this watson document is original document; <code>false</code> otherwise
	*/
	@Override
	public boolean isOriginalDocument() {
		return _watsonDocument.isOriginalDocument();
	}

	@Override
	public void persist() {
		_watsonDocument.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonDocument.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson document.
	*
	* @param companyId the company ID of this watson document
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonDocument.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson document.
	*
	* @param createDate the create date of this watson document
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonDocument.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonDocument.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonDocument.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonDocument.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this watson document.
	*
	* @param groupId the group ID of this watson document
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonDocument.setGroupId(groupId);
	}

	/**
	* Sets the image payload of this watson document.
	*
	* @param imagePayload the image payload of this watson document
	*/
	@Override
	public void setImagePayload(java.lang.String imagePayload) {
		_watsonDocument.setImagePayload(imagePayload);
	}

	/**
	* Sets the modified date of this watson document.
	*
	* @param modifiedDate the modified date of this watson document
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonDocument.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonDocument.setNew(n);
	}

	/**
	* Sets whether this watson document is original document.
	*
	* @param originalDocument the original document of this watson document
	*/
	@Override
	public void setOriginalDocument(boolean originalDocument) {
		_watsonDocument.setOriginalDocument(originalDocument);
	}

	/**
	* Sets the parent type watson list type ID of this watson document.
	*
	* @param parentTypeWatsonListTypeId the parent type watson list type ID of this watson document
	*/
	@Override
	public void setParentTypeWatsonListTypeId(long parentTypeWatsonListTypeId) {
		_watsonDocument.setParentTypeWatsonListTypeId(parentTypeWatsonListTypeId);
	}

	/**
	* Sets the primary key of this watson document.
	*
	* @param primaryKey the primary key of this watson document
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonDocument.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonDocument.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the received date of this watson document.
	*
	* @param receivedDate the received date of this watson document
	*/
	@Override
	public void setReceivedDate(Date receivedDate) {
		_watsonDocument.setReceivedDate(receivedDate);
	}

	/**
	* Sets the status of this watson document.
	*
	* @param status the status of this watson document
	*/
	@Override
	public void setStatus(int status) {
		_watsonDocument.setStatus(status);
	}

	/**
	* Sets the subtype watson list type ID of this watson document.
	*
	* @param subtypeWatsonListTypeId the subtype watson list type ID of this watson document
	*/
	@Override
	public void setSubtypeWatsonListTypeId(long subtypeWatsonListTypeId) {
		_watsonDocument.setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
	}

	/**
	* Sets the type watson list type ID of this watson document.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson document
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonDocument.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson document.
	*
	* @param userId the user ID of this watson document
	*/
	@Override
	public void setUserId(long userId) {
		_watsonDocument.setUserId(userId);
	}

	/**
	* Sets the user name of this watson document.
	*
	* @param userName the user name of this watson document
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonDocument.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson document.
	*
	* @param userUuid the user uuid of this watson document
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonDocument.setUserUuid(userUuid);
	}

	/**
	* Sets the watson child ID of this watson document.
	*
	* @param watsonChildId the watson child ID of this watson document
	*/
	@Override
	public void setWatsonChildId(long watsonChildId) {
		_watsonDocument.setWatsonChildId(watsonChildId);
	}

	/**
	* Sets the watson document ID of this watson document.
	*
	* @param watsonDocumentId the watson document ID of this watson document
	*/
	@Override
	public void setWatsonDocumentId(long watsonDocumentId) {
		_watsonDocument.setWatsonDocumentId(watsonDocumentId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonDocument> toCacheModel() {
		return _watsonDocument.toCacheModel();
	}

	@Override
	public WatsonDocument toEscapedModel() {
		return new WatsonDocumentWrapper(_watsonDocument.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonDocument.toString();
	}

	@Override
	public WatsonDocument toUnescapedModel() {
		return new WatsonDocumentWrapper(_watsonDocument.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonDocument.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonDocumentWrapper)) {
			return false;
		}

		WatsonDocumentWrapper watsonDocumentWrapper = (WatsonDocumentWrapper)obj;

		if (Objects.equals(_watsonDocument,
					watsonDocumentWrapper._watsonDocument)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonDocument getWrappedModel() {
		return _watsonDocument;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonDocument.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonDocument.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonDocument.resetOriginalValues();
	}

	private final WatsonDocument _watsonDocument;
}