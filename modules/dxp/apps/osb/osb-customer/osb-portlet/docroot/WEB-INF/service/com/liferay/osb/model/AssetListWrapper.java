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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AssetList}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetList
 * @generated
 */
public class AssetListWrapper implements AssetList, ModelWrapper<AssetList> {
	public AssetListWrapper(AssetList assetList) {
		_assetList = assetList;
	}

	public Class<?> getModelClass() {
		return AssetList.class;
	}

	public String getModelClassName() {
		return AssetList.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetListId", getAssetListId());
		attributes.put("assetCategoryId", getAssetCategoryId());
		attributes.put("type", getType());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetListId = (Long)attributes.get("assetListId");

		if (assetListId != null) {
			setAssetListId(assetListId);
		}

		Long assetCategoryId = (Long)attributes.get("assetCategoryId");

		if (assetCategoryId != null) {
			setAssetCategoryId(assetCategoryId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	/**
	* Returns the primary key of this asset list.
	*
	* @return the primary key of this asset list
	*/
	public long getPrimaryKey() {
		return _assetList.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset list.
	*
	* @param primaryKey the primary key of this asset list
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetList.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset list ID of this asset list.
	*
	* @return the asset list ID of this asset list
	*/
	public long getAssetListId() {
		return _assetList.getAssetListId();
	}

	/**
	* Sets the asset list ID of this asset list.
	*
	* @param assetListId the asset list ID of this asset list
	*/
	public void setAssetListId(long assetListId) {
		_assetList.setAssetListId(assetListId);
	}

	/**
	* Returns the asset category ID of this asset list.
	*
	* @return the asset category ID of this asset list
	*/
	public long getAssetCategoryId() {
		return _assetList.getAssetCategoryId();
	}

	/**
	* Sets the asset category ID of this asset list.
	*
	* @param assetCategoryId the asset category ID of this asset list
	*/
	public void setAssetCategoryId(long assetCategoryId) {
		_assetList.setAssetCategoryId(assetCategoryId);
	}

	/**
	* Returns the type of this asset list.
	*
	* @return the type of this asset list
	*/
	public int getType() {
		return _assetList.getType();
	}

	/**
	* Sets the type of this asset list.
	*
	* @param type the type of this asset list
	*/
	public void setType(int type) {
		_assetList.setType(type);
	}

	public boolean isNew() {
		return _assetList.isNew();
	}

	public void setNew(boolean n) {
		_assetList.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetList.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetList.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetList.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetList.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetList.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetList.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetList.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetListWrapper((AssetList)_assetList.clone());
	}

	public int compareTo(com.liferay.osb.model.AssetList assetList) {
		return _assetList.compareTo(assetList);
	}

	@Override
	public int hashCode() {
		return _assetList.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetList> toCacheModel() {
		return _assetList.toCacheModel();
	}

	public com.liferay.osb.model.AssetList toEscapedModel() {
		return new AssetListWrapper(_assetList.toEscapedModel());
	}

	public com.liferay.osb.model.AssetList toUnescapedModel() {
		return new AssetListWrapper(_assetList.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetList.toString();
	}

	public java.lang.String toXmlString() {
		return _assetList.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetList.persist();
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetEntry> getAssetEntries(
		boolean visible)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetList.getAssetEntries(visible);
	}

	public java.lang.String getTypeLabel() {
		return _assetList.getTypeLabel();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetListWrapper)) {
			return false;
		}

		AssetListWrapper assetListWrapper = (AssetListWrapper)obj;

		if (Validator.equals(_assetList, assetListWrapper._assetList)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetList getWrappedAssetList() {
		return _assetList;
	}

	public AssetList getWrappedModel() {
		return _assetList;
	}

	public void resetOriginalValues() {
		_assetList.resetOriginalValues();
	}

	private AssetList _assetList;
}