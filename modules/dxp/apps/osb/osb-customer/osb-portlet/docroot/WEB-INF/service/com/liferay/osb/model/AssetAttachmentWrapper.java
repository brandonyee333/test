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
 * This class is a wrapper for {@link AssetAttachment}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetAttachment
 * @generated
 */
public class AssetAttachmentWrapper implements AssetAttachment,
	ModelWrapper<AssetAttachment> {
	public AssetAttachmentWrapper(AssetAttachment assetAttachment) {
		_assetAttachment = assetAttachment;
	}

	public Class<?> getModelClass() {
		return AssetAttachment.class;
	}

	public String getModelClassName() {
		return AssetAttachment.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetAttachmentId", getAssetAttachmentId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("fileName", getFileName());
		attributes.put("type", getType());
		attributes.put("rank", getRank());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetAttachmentId = (Long)attributes.get("assetAttachmentId");

		if (assetAttachmentId != null) {
			setAssetAttachmentId(assetAttachmentId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer rank = (Integer)attributes.get("rank");

		if (rank != null) {
			setRank(rank);
		}
	}

	/**
	* Returns the primary key of this asset attachment.
	*
	* @return the primary key of this asset attachment
	*/
	public long getPrimaryKey() {
		return _assetAttachment.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset attachment.
	*
	* @param primaryKey the primary key of this asset attachment
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetAttachment.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset attachment ID of this asset attachment.
	*
	* @return the asset attachment ID of this asset attachment
	*/
	public long getAssetAttachmentId() {
		return _assetAttachment.getAssetAttachmentId();
	}

	/**
	* Sets the asset attachment ID of this asset attachment.
	*
	* @param assetAttachmentId the asset attachment ID of this asset attachment
	*/
	public void setAssetAttachmentId(long assetAttachmentId) {
		_assetAttachment.setAssetAttachmentId(assetAttachmentId);
	}

	/**
	* Returns the user ID of this asset attachment.
	*
	* @return the user ID of this asset attachment
	*/
	public long getUserId() {
		return _assetAttachment.getUserId();
	}

	/**
	* Sets the user ID of this asset attachment.
	*
	* @param userId the user ID of this asset attachment
	*/
	public void setUserId(long userId) {
		_assetAttachment.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset attachment.
	*
	* @return the user uuid of this asset attachment
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachment.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset attachment.
	*
	* @param userUuid the user uuid of this asset attachment
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_assetAttachment.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this asset attachment.
	*
	* @return the create date of this asset attachment
	*/
	public java.util.Date getCreateDate() {
		return _assetAttachment.getCreateDate();
	}

	/**
	* Sets the create date of this asset attachment.
	*
	* @param createDate the create date of this asset attachment
	*/
	public void setCreateDate(java.util.Date createDate) {
		_assetAttachment.setCreateDate(createDate);
	}

	/**
	* Returns the fully qualified class name of this asset attachment.
	*
	* @return the fully qualified class name of this asset attachment
	*/
	public java.lang.String getClassName() {
		return _assetAttachment.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_assetAttachment.setClassName(className);
	}

	/**
	* Returns the class name ID of this asset attachment.
	*
	* @return the class name ID of this asset attachment
	*/
	public long getClassNameId() {
		return _assetAttachment.getClassNameId();
	}

	/**
	* Sets the class name ID of this asset attachment.
	*
	* @param classNameId the class name ID of this asset attachment
	*/
	public void setClassNameId(long classNameId) {
		_assetAttachment.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this asset attachment.
	*
	* @return the class p k of this asset attachment
	*/
	public long getClassPK() {
		return _assetAttachment.getClassPK();
	}

	/**
	* Sets the class p k of this asset attachment.
	*
	* @param classPK the class p k of this asset attachment
	*/
	public void setClassPK(long classPK) {
		_assetAttachment.setClassPK(classPK);
	}

	/**
	* Returns the file name of this asset attachment.
	*
	* @return the file name of this asset attachment
	*/
	public java.lang.String getFileName() {
		return _assetAttachment.getFileName();
	}

	/**
	* Sets the file name of this asset attachment.
	*
	* @param fileName the file name of this asset attachment
	*/
	public void setFileName(java.lang.String fileName) {
		_assetAttachment.setFileName(fileName);
	}

	/**
	* Returns the type of this asset attachment.
	*
	* @return the type of this asset attachment
	*/
	public int getType() {
		return _assetAttachment.getType();
	}

	/**
	* Sets the type of this asset attachment.
	*
	* @param type the type of this asset attachment
	*/
	public void setType(int type) {
		_assetAttachment.setType(type);
	}

	/**
	* Returns the rank of this asset attachment.
	*
	* @return the rank of this asset attachment
	*/
	public int getRank() {
		return _assetAttachment.getRank();
	}

	/**
	* Sets the rank of this asset attachment.
	*
	* @param rank the rank of this asset attachment
	*/
	public void setRank(int rank) {
		_assetAttachment.setRank(rank);
	}

	public boolean isNew() {
		return _assetAttachment.isNew();
	}

	public void setNew(boolean n) {
		_assetAttachment.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetAttachment.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetAttachment.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetAttachment.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetAttachment.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetAttachment.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetAttachment.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetAttachment.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetAttachmentWrapper((AssetAttachment)_assetAttachment.clone());
	}

	public int compareTo(com.liferay.osb.model.AssetAttachment assetAttachment) {
		return _assetAttachment.compareTo(assetAttachment);
	}

	@Override
	public int hashCode() {
		return _assetAttachment.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetAttachment> toCacheModel() {
		return _assetAttachment.toCacheModel();
	}

	public com.liferay.osb.model.AssetAttachment toEscapedModel() {
		return new AssetAttachmentWrapper(_assetAttachment.toEscapedModel());
	}

	public com.liferay.osb.model.AssetAttachment toUnescapedModel() {
		return new AssetAttachmentWrapper(_assetAttachment.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetAttachment.toString();
	}

	public java.lang.String toXmlString() {
		return _assetAttachment.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetAttachment.persist();
	}

	public java.lang.String getDir() {
		return _assetAttachment.getDir();
	}

	public java.io.InputStream getFileAsStream()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachment.getFileAsStream();
	}

	public java.lang.String getPath() {
		return _assetAttachment.getPath();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetAttachmentWrapper)) {
			return false;
		}

		AssetAttachmentWrapper assetAttachmentWrapper = (AssetAttachmentWrapper)obj;

		if (Validator.equals(_assetAttachment,
					assetAttachmentWrapper._assetAttachment)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetAttachment getWrappedAssetAttachment() {
		return _assetAttachment;
	}

	public AssetAttachment getWrappedModel() {
		return _assetAttachment;
	}

	public void resetOriginalValues() {
		_assetAttachment.resetOriginalValues();
	}

	private AssetAttachment _assetAttachment;
}