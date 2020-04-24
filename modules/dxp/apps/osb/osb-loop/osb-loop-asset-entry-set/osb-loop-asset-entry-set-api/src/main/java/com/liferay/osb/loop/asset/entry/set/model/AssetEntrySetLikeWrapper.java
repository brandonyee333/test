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

package com.liferay.osb.loop.asset.entry.set.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AssetEntrySetLike}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLike
 * @generated
 */
@ProviderType
public class AssetEntrySetLikeWrapper implements AssetEntrySetLike,
	ModelWrapper<AssetEntrySetLike> {
	public AssetEntrySetLikeWrapper(AssetEntrySetLike assetEntrySetLike) {
		_assetEntrySetLike = assetEntrySetLike;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetEntrySetLike.class;
	}

	@Override
	public String getModelClassName() {
		return AssetEntrySetLike.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetEntrySetId", getAssetEntrySetId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetEntrySetId = (Long)attributes.get("assetEntrySetId");

		if (assetEntrySetId != null) {
			setAssetEntrySetId(assetEntrySetId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AssetEntrySetLikeWrapper((AssetEntrySetLike)_assetEntrySetLike.clone());
	}

	@Override
	public int compareTo(AssetEntrySetLike assetEntrySetLike) {
		return _assetEntrySetLike.compareTo(assetEntrySetLike);
	}

	/**
	* Returns the asset entry set ID of this asset entry set like.
	*
	* @return the asset entry set ID of this asset entry set like
	*/
	@Override
	public long getAssetEntrySetId() {
		return _assetEntrySetLike.getAssetEntrySetId();
	}

	/**
	* Returns the fully qualified class name of this asset entry set like.
	*
	* @return the fully qualified class name of this asset entry set like
	*/
	@Override
	public java.lang.String getClassName() {
		return _assetEntrySetLike.getClassName();
	}

	/**
	* Returns the class name ID of this asset entry set like.
	*
	* @return the class name ID of this asset entry set like
	*/
	@Override
	public long getClassNameId() {
		return _assetEntrySetLike.getClassNameId();
	}

	/**
	* Returns the class pk of this asset entry set like.
	*
	* @return the class pk of this asset entry set like
	*/
	@Override
	public long getClassPK() {
		return _assetEntrySetLike.getClassPK();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _assetEntrySetLike.getExpandoBridge();
	}

	/**
	* Returns the primary key of this asset entry set like.
	*
	* @return the primary key of this asset entry set like
	*/
	@Override
	public com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetLikePK getPrimaryKey() {
		return _assetEntrySetLike.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetEntrySetLike.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _assetEntrySetLike.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _assetEntrySetLike.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _assetEntrySetLike.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _assetEntrySetLike.isNew();
	}

	@Override
	public void persist() {
		_assetEntrySetLike.persist();
	}

	/**
	* Sets the asset entry set ID of this asset entry set like.
	*
	* @param assetEntrySetId the asset entry set ID of this asset entry set like
	*/
	@Override
	public void setAssetEntrySetId(long assetEntrySetId) {
		_assetEntrySetLike.setAssetEntrySetId(assetEntrySetId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetEntrySetLike.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_assetEntrySetLike.setClassName(className);
	}

	/**
	* Sets the class name ID of this asset entry set like.
	*
	* @param classNameId the class name ID of this asset entry set like
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_assetEntrySetLike.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this asset entry set like.
	*
	* @param classPK the class pk of this asset entry set like
	*/
	@Override
	public void setClassPK(long classPK) {
		_assetEntrySetLike.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_assetEntrySetLike.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_assetEntrySetLike.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_assetEntrySetLike.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_assetEntrySetLike.setNew(n);
	}

	/**
	* Sets the primary key of this asset entry set like.
	*
	* @param primaryKey the primary key of this asset entry set like
	*/
	@Override
	public void setPrimaryKey(
		com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetLikePK primaryKey) {
		_assetEntrySetLike.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_assetEntrySetLike.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AssetEntrySetLike> toCacheModel() {
		return _assetEntrySetLike.toCacheModel();
	}

	@Override
	public AssetEntrySetLike toEscapedModel() {
		return new AssetEntrySetLikeWrapper(_assetEntrySetLike.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetEntrySetLike.toString();
	}

	@Override
	public AssetEntrySetLike toUnescapedModel() {
		return new AssetEntrySetLikeWrapper(_assetEntrySetLike.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _assetEntrySetLike.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntrySetLikeWrapper)) {
			return false;
		}

		AssetEntrySetLikeWrapper assetEntrySetLikeWrapper = (AssetEntrySetLikeWrapper)obj;

		if (Objects.equals(_assetEntrySetLike,
					assetEntrySetLikeWrapper._assetEntrySetLike)) {
			return true;
		}

		return false;
	}

	@Override
	public AssetEntrySetLike getWrappedModel() {
		return _assetEntrySetLike;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _assetEntrySetLike.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _assetEntrySetLike.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_assetEntrySetLike.resetOriginalValues();
	}

	private final AssetEntrySetLike _assetEntrySetLike;
}