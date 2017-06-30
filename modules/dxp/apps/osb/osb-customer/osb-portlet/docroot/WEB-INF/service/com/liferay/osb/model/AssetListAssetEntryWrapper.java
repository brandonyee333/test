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
 * This class is a wrapper for {@link AssetListAssetEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetListAssetEntry
 * @generated
 */
public class AssetListAssetEntryWrapper implements AssetListAssetEntry,
	ModelWrapper<AssetListAssetEntry> {
	public AssetListAssetEntryWrapper(AssetListAssetEntry assetListAssetEntry) {
		_assetListAssetEntry = assetListAssetEntry;
	}

	public Class<?> getModelClass() {
		return AssetListAssetEntry.class;
	}

	public String getModelClassName() {
		return AssetListAssetEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetListAssetEntryId", getAssetListAssetEntryId());
		attributes.put("assetListId", getAssetListId());
		attributes.put("assetEntryId", getAssetEntryId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetListAssetEntryId = (Long)attributes.get(
				"assetListAssetEntryId");

		if (assetListAssetEntryId != null) {
			setAssetListAssetEntryId(assetListAssetEntryId);
		}

		Long assetListId = (Long)attributes.get("assetListId");

		if (assetListId != null) {
			setAssetListId(assetListId);
		}

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}
	}

	/**
	* Returns the primary key of this asset list asset entry.
	*
	* @return the primary key of this asset list asset entry
	*/
	public long getPrimaryKey() {
		return _assetListAssetEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset list asset entry.
	*
	* @param primaryKey the primary key of this asset list asset entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetListAssetEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset list asset entry ID of this asset list asset entry.
	*
	* @return the asset list asset entry ID of this asset list asset entry
	*/
	public long getAssetListAssetEntryId() {
		return _assetListAssetEntry.getAssetListAssetEntryId();
	}

	/**
	* Sets the asset list asset entry ID of this asset list asset entry.
	*
	* @param assetListAssetEntryId the asset list asset entry ID of this asset list asset entry
	*/
	public void setAssetListAssetEntryId(long assetListAssetEntryId) {
		_assetListAssetEntry.setAssetListAssetEntryId(assetListAssetEntryId);
	}

	/**
	* Returns the asset list ID of this asset list asset entry.
	*
	* @return the asset list ID of this asset list asset entry
	*/
	public long getAssetListId() {
		return _assetListAssetEntry.getAssetListId();
	}

	/**
	* Sets the asset list ID of this asset list asset entry.
	*
	* @param assetListId the asset list ID of this asset list asset entry
	*/
	public void setAssetListId(long assetListId) {
		_assetListAssetEntry.setAssetListId(assetListId);
	}

	/**
	* Returns the asset entry ID of this asset list asset entry.
	*
	* @return the asset entry ID of this asset list asset entry
	*/
	public long getAssetEntryId() {
		return _assetListAssetEntry.getAssetEntryId();
	}

	/**
	* Sets the asset entry ID of this asset list asset entry.
	*
	* @param assetEntryId the asset entry ID of this asset list asset entry
	*/
	public void setAssetEntryId(long assetEntryId) {
		_assetListAssetEntry.setAssetEntryId(assetEntryId);
	}

	public boolean isNew() {
		return _assetListAssetEntry.isNew();
	}

	public void setNew(boolean n) {
		_assetListAssetEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetListAssetEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetListAssetEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetListAssetEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetListAssetEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetListAssetEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetListAssetEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetListAssetEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetListAssetEntryWrapper((AssetListAssetEntry)_assetListAssetEntry.clone());
	}

	public int compareTo(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry) {
		return _assetListAssetEntry.compareTo(assetListAssetEntry);
	}

	@Override
	public int hashCode() {
		return _assetListAssetEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetListAssetEntry> toCacheModel() {
		return _assetListAssetEntry.toCacheModel();
	}

	public com.liferay.osb.model.AssetListAssetEntry toEscapedModel() {
		return new AssetListAssetEntryWrapper(_assetListAssetEntry.toEscapedModel());
	}

	public com.liferay.osb.model.AssetListAssetEntry toUnescapedModel() {
		return new AssetListAssetEntryWrapper(_assetListAssetEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetListAssetEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _assetListAssetEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetListAssetEntry.persist();
	}

	public com.liferay.portlet.asset.model.AssetEntry getAssetEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetListAssetEntry.getAssetEntry();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetListAssetEntryWrapper)) {
			return false;
		}

		AssetListAssetEntryWrapper assetListAssetEntryWrapper = (AssetListAssetEntryWrapper)obj;

		if (Validator.equals(_assetListAssetEntry,
					assetListAssetEntryWrapper._assetListAssetEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetListAssetEntry getWrappedAssetListAssetEntry() {
		return _assetListAssetEntry;
	}

	public AssetListAssetEntry getWrappedModel() {
		return _assetListAssetEntry;
	}

	public void resetOriginalValues() {
		_assetListAssetEntry.resetOriginalValues();
	}

	private AssetListAssetEntry _assetListAssetEntry;
}