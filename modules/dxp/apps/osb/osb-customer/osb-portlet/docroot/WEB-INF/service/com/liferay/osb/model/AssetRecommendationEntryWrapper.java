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
 * This class is a wrapper for {@link AssetRecommendationEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetRecommendationEntry
 * @generated
 */
public class AssetRecommendationEntryWrapper implements AssetRecommendationEntry,
	ModelWrapper<AssetRecommendationEntry> {
	public AssetRecommendationEntryWrapper(
		AssetRecommendationEntry assetRecommendationEntry) {
		_assetRecommendationEntry = assetRecommendationEntry;
	}

	public Class<?> getModelClass() {
		return AssetRecommendationEntry.class;
	}

	public String getModelClassName() {
		return AssetRecommendationEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetRecommendationEntryId",
			getAssetRecommendationEntryId());
		attributes.put("assetRecommendationSetId", getAssetRecommendationSetId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("viewedAlsoPurchasedCount", getViewedAlsoPurchasedCount());
		attributes.put("purchasedAlsoPurchasedCount",
			getPurchasedAlsoPurchasedCount());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetRecommendationEntryId = (Long)attributes.get(
				"assetRecommendationEntryId");

		if (assetRecommendationEntryId != null) {
			setAssetRecommendationEntryId(assetRecommendationEntryId);
		}

		Long assetRecommendationSetId = (Long)attributes.get(
				"assetRecommendationSetId");

		if (assetRecommendationSetId != null) {
			setAssetRecommendationSetId(assetRecommendationSetId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer viewedAlsoPurchasedCount = (Integer)attributes.get(
				"viewedAlsoPurchasedCount");

		if (viewedAlsoPurchasedCount != null) {
			setViewedAlsoPurchasedCount(viewedAlsoPurchasedCount);
		}

		Integer purchasedAlsoPurchasedCount = (Integer)attributes.get(
				"purchasedAlsoPurchasedCount");

		if (purchasedAlsoPurchasedCount != null) {
			setPurchasedAlsoPurchasedCount(purchasedAlsoPurchasedCount);
		}
	}

	/**
	* Returns the primary key of this asset recommendation entry.
	*
	* @return the primary key of this asset recommendation entry
	*/
	public long getPrimaryKey() {
		return _assetRecommendationEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset recommendation entry.
	*
	* @param primaryKey the primary key of this asset recommendation entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetRecommendationEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset recommendation entry ID of this asset recommendation entry.
	*
	* @return the asset recommendation entry ID of this asset recommendation entry
	*/
	public long getAssetRecommendationEntryId() {
		return _assetRecommendationEntry.getAssetRecommendationEntryId();
	}

	/**
	* Sets the asset recommendation entry ID of this asset recommendation entry.
	*
	* @param assetRecommendationEntryId the asset recommendation entry ID of this asset recommendation entry
	*/
	public void setAssetRecommendationEntryId(long assetRecommendationEntryId) {
		_assetRecommendationEntry.setAssetRecommendationEntryId(assetRecommendationEntryId);
	}

	/**
	* Returns the asset recommendation set ID of this asset recommendation entry.
	*
	* @return the asset recommendation set ID of this asset recommendation entry
	*/
	public long getAssetRecommendationSetId() {
		return _assetRecommendationEntry.getAssetRecommendationSetId();
	}

	/**
	* Sets the asset recommendation set ID of this asset recommendation entry.
	*
	* @param assetRecommendationSetId the asset recommendation set ID of this asset recommendation entry
	*/
	public void setAssetRecommendationSetId(long assetRecommendationSetId) {
		_assetRecommendationEntry.setAssetRecommendationSetId(assetRecommendationSetId);
	}

	/**
	* Returns the fully qualified class name of this asset recommendation entry.
	*
	* @return the fully qualified class name of this asset recommendation entry
	*/
	public java.lang.String getClassName() {
		return _assetRecommendationEntry.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_assetRecommendationEntry.setClassName(className);
	}

	/**
	* Returns the class name ID of this asset recommendation entry.
	*
	* @return the class name ID of this asset recommendation entry
	*/
	public long getClassNameId() {
		return _assetRecommendationEntry.getClassNameId();
	}

	/**
	* Sets the class name ID of this asset recommendation entry.
	*
	* @param classNameId the class name ID of this asset recommendation entry
	*/
	public void setClassNameId(long classNameId) {
		_assetRecommendationEntry.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this asset recommendation entry.
	*
	* @return the class p k of this asset recommendation entry
	*/
	public long getClassPK() {
		return _assetRecommendationEntry.getClassPK();
	}

	/**
	* Sets the class p k of this asset recommendation entry.
	*
	* @param classPK the class p k of this asset recommendation entry
	*/
	public void setClassPK(long classPK) {
		_assetRecommendationEntry.setClassPK(classPK);
	}

	/**
	* Returns the viewed also purchased count of this asset recommendation entry.
	*
	* @return the viewed also purchased count of this asset recommendation entry
	*/
	public int getViewedAlsoPurchasedCount() {
		return _assetRecommendationEntry.getViewedAlsoPurchasedCount();
	}

	/**
	* Sets the viewed also purchased count of this asset recommendation entry.
	*
	* @param viewedAlsoPurchasedCount the viewed also purchased count of this asset recommendation entry
	*/
	public void setViewedAlsoPurchasedCount(int viewedAlsoPurchasedCount) {
		_assetRecommendationEntry.setViewedAlsoPurchasedCount(viewedAlsoPurchasedCount);
	}

	/**
	* Returns the purchased also purchased count of this asset recommendation entry.
	*
	* @return the purchased also purchased count of this asset recommendation entry
	*/
	public int getPurchasedAlsoPurchasedCount() {
		return _assetRecommendationEntry.getPurchasedAlsoPurchasedCount();
	}

	/**
	* Sets the purchased also purchased count of this asset recommendation entry.
	*
	* @param purchasedAlsoPurchasedCount the purchased also purchased count of this asset recommendation entry
	*/
	public void setPurchasedAlsoPurchasedCount(int purchasedAlsoPurchasedCount) {
		_assetRecommendationEntry.setPurchasedAlsoPurchasedCount(purchasedAlsoPurchasedCount);
	}

	public boolean isNew() {
		return _assetRecommendationEntry.isNew();
	}

	public void setNew(boolean n) {
		_assetRecommendationEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetRecommendationEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetRecommendationEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetRecommendationEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetRecommendationEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetRecommendationEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetRecommendationEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetRecommendationEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetRecommendationEntryWrapper((AssetRecommendationEntry)_assetRecommendationEntry.clone());
	}

	public int compareTo(
		com.liferay.osb.model.AssetRecommendationEntry assetRecommendationEntry) {
		return _assetRecommendationEntry.compareTo(assetRecommendationEntry);
	}

	@Override
	public int hashCode() {
		return _assetRecommendationEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetRecommendationEntry> toCacheModel() {
		return _assetRecommendationEntry.toCacheModel();
	}

	public com.liferay.osb.model.AssetRecommendationEntry toEscapedModel() {
		return new AssetRecommendationEntryWrapper(_assetRecommendationEntry.toEscapedModel());
	}

	public com.liferay.osb.model.AssetRecommendationEntry toUnescapedModel() {
		return new AssetRecommendationEntryWrapper(_assetRecommendationEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetRecommendationEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _assetRecommendationEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetRecommendationEntry.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetRecommendationEntryWrapper)) {
			return false;
		}

		AssetRecommendationEntryWrapper assetRecommendationEntryWrapper = (AssetRecommendationEntryWrapper)obj;

		if (Validator.equals(_assetRecommendationEntry,
					assetRecommendationEntryWrapper._assetRecommendationEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetRecommendationEntry getWrappedAssetRecommendationEntry() {
		return _assetRecommendationEntry;
	}

	public AssetRecommendationEntry getWrappedModel() {
		return _assetRecommendationEntry;
	}

	public void resetOriginalValues() {
		_assetRecommendationEntry.resetOriginalValues();
	}

	private AssetRecommendationEntry _assetRecommendationEntry;
}