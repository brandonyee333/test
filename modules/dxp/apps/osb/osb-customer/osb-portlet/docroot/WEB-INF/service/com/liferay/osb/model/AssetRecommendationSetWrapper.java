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
 * This class is a wrapper for {@link AssetRecommendationSet}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetRecommendationSet
 * @generated
 */
public class AssetRecommendationSetWrapper implements AssetRecommendationSet,
	ModelWrapper<AssetRecommendationSet> {
	public AssetRecommendationSetWrapper(
		AssetRecommendationSet assetRecommendationSet) {
		_assetRecommendationSet = assetRecommendationSet;
	}

	public Class<?> getModelClass() {
		return AssetRecommendationSet.class;
	}

	public String getModelClassName() {
		return AssetRecommendationSet.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetRecommendationSetId", getAssetRecommendationSetId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
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
	}

	/**
	* Returns the primary key of this asset recommendation set.
	*
	* @return the primary key of this asset recommendation set
	*/
	public long getPrimaryKey() {
		return _assetRecommendationSet.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset recommendation set.
	*
	* @param primaryKey the primary key of this asset recommendation set
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetRecommendationSet.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset recommendation set ID of this asset recommendation set.
	*
	* @return the asset recommendation set ID of this asset recommendation set
	*/
	public long getAssetRecommendationSetId() {
		return _assetRecommendationSet.getAssetRecommendationSetId();
	}

	/**
	* Sets the asset recommendation set ID of this asset recommendation set.
	*
	* @param assetRecommendationSetId the asset recommendation set ID of this asset recommendation set
	*/
	public void setAssetRecommendationSetId(long assetRecommendationSetId) {
		_assetRecommendationSet.setAssetRecommendationSetId(assetRecommendationSetId);
	}

	/**
	* Returns the fully qualified class name of this asset recommendation set.
	*
	* @return the fully qualified class name of this asset recommendation set
	*/
	public java.lang.String getClassName() {
		return _assetRecommendationSet.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_assetRecommendationSet.setClassName(className);
	}

	/**
	* Returns the class name ID of this asset recommendation set.
	*
	* @return the class name ID of this asset recommendation set
	*/
	public long getClassNameId() {
		return _assetRecommendationSet.getClassNameId();
	}

	/**
	* Sets the class name ID of this asset recommendation set.
	*
	* @param classNameId the class name ID of this asset recommendation set
	*/
	public void setClassNameId(long classNameId) {
		_assetRecommendationSet.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this asset recommendation set.
	*
	* @return the class p k of this asset recommendation set
	*/
	public long getClassPK() {
		return _assetRecommendationSet.getClassPK();
	}

	/**
	* Sets the class p k of this asset recommendation set.
	*
	* @param classPK the class p k of this asset recommendation set
	*/
	public void setClassPK(long classPK) {
		_assetRecommendationSet.setClassPK(classPK);
	}

	public boolean isNew() {
		return _assetRecommendationSet.isNew();
	}

	public void setNew(boolean n) {
		_assetRecommendationSet.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetRecommendationSet.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetRecommendationSet.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetRecommendationSet.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetRecommendationSet.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetRecommendationSet.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetRecommendationSet.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetRecommendationSet.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetRecommendationSetWrapper((AssetRecommendationSet)_assetRecommendationSet.clone());
	}

	public int compareTo(
		com.liferay.osb.model.AssetRecommendationSet assetRecommendationSet) {
		return _assetRecommendationSet.compareTo(assetRecommendationSet);
	}

	@Override
	public int hashCode() {
		return _assetRecommendationSet.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetRecommendationSet> toCacheModel() {
		return _assetRecommendationSet.toCacheModel();
	}

	public com.liferay.osb.model.AssetRecommendationSet toEscapedModel() {
		return new AssetRecommendationSetWrapper(_assetRecommendationSet.toEscapedModel());
	}

	public com.liferay.osb.model.AssetRecommendationSet toUnescapedModel() {
		return new AssetRecommendationSetWrapper(_assetRecommendationSet.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetRecommendationSet.toString();
	}

	public java.lang.String toXmlString() {
		return _assetRecommendationSet.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetRecommendationSet.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetRecommendationSetWrapper)) {
			return false;
		}

		AssetRecommendationSetWrapper assetRecommendationSetWrapper = (AssetRecommendationSetWrapper)obj;

		if (Validator.equals(_assetRecommendationSet,
					assetRecommendationSetWrapper._assetRecommendationSet)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetRecommendationSet getWrappedAssetRecommendationSet() {
		return _assetRecommendationSet;
	}

	public AssetRecommendationSet getWrappedModel() {
		return _assetRecommendationSet;
	}

	public void resetOriginalValues() {
		_assetRecommendationSet.resetOriginalValues();
	}

	private AssetRecommendationSet _assetRecommendationSet;
}