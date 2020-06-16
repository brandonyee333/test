/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.loop.asset.sharing.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AssetSharingEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetSharingEntry
 * @generated
 */
public class AssetSharingEntryWrapper
	implements AssetSharingEntry, ModelWrapper<AssetSharingEntry> {

	public AssetSharingEntryWrapper(AssetSharingEntry assetSharingEntry) {
		_assetSharingEntry = assetSharingEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetSharingEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AssetSharingEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("sharedToClassNameId", getSharedToClassNameId());
		attributes.put("sharedToClassPK", getSharedToClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long sharedToClassNameId = (Long)attributes.get("sharedToClassNameId");

		if (sharedToClassNameId != null) {
			setSharedToClassNameId(sharedToClassNameId);
		}

		Long sharedToClassPK = (Long)attributes.get("sharedToClassPK");

		if (sharedToClassPK != null) {
			setSharedToClassPK(sharedToClassPK);
		}
	}

	@Override
	public Object clone() {
		return new AssetSharingEntryWrapper(
			(AssetSharingEntry)_assetSharingEntry.clone());
	}

	@Override
	public int compareTo(AssetSharingEntry assetSharingEntry) {
		return _assetSharingEntry.compareTo(assetSharingEntry);
	}

	/**
	 * Returns the fully qualified class name of this asset sharing entry.
	 *
	 * @return the fully qualified class name of this asset sharing entry
	 */
	@Override
	public String getClassName() {
		return _assetSharingEntry.getClassName();
	}

	/**
	 * Returns the class name ID of this asset sharing entry.
	 *
	 * @return the class name ID of this asset sharing entry
	 */
	@Override
	public long getClassNameId() {
		return _assetSharingEntry.getClassNameId();
	}

	/**
	 * Returns the class pk of this asset sharing entry.
	 *
	 * @return the class pk of this asset sharing entry
	 */
	@Override
	public long getClassPK() {
		return _assetSharingEntry.getClassPK();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _assetSharingEntry.getExpandoBridge();
	}

	/**
	 * Returns the primary key of this asset sharing entry.
	 *
	 * @return the primary key of this asset sharing entry
	 */
	@Override
	public
		com.liferay.osb.loop.asset.sharing.service.persistence.
			AssetSharingEntryPK getPrimaryKey() {

		return _assetSharingEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetSharingEntry.getPrimaryKeyObj();
	}

	/**
	 * Returns the shared to class name ID of this asset sharing entry.
	 *
	 * @return the shared to class name ID of this asset sharing entry
	 */
	@Override
	public long getSharedToClassNameId() {
		return _assetSharingEntry.getSharedToClassNameId();
	}

	/**
	 * Returns the shared to class pk of this asset sharing entry.
	 *
	 * @return the shared to class pk of this asset sharing entry
	 */
	@Override
	public long getSharedToClassPK() {
		return _assetSharingEntry.getSharedToClassPK();
	}

	@Override
	public int hashCode() {
		return _assetSharingEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _assetSharingEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _assetSharingEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _assetSharingEntry.isNew();
	}

	@Override
	public void persist() {
		_assetSharingEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetSharingEntry.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_assetSharingEntry.setClassName(className);
	}

	/**
	 * Sets the class name ID of this asset sharing entry.
	 *
	 * @param classNameId the class name ID of this asset sharing entry
	 */
	@Override
	public void setClassNameId(long classNameId) {
		_assetSharingEntry.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this asset sharing entry.
	 *
	 * @param classPK the class pk of this asset sharing entry
	 */
	@Override
	public void setClassPK(long classPK) {
		_assetSharingEntry.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_assetSharingEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_assetSharingEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_assetSharingEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_assetSharingEntry.setNew(n);
	}

	/**
	 * Sets the primary key of this asset sharing entry.
	 *
	 * @param primaryKey the primary key of this asset sharing entry
	 */
	@Override
	public void setPrimaryKey(
		com.liferay.osb.loop.asset.sharing.service.persistence.
			AssetSharingEntryPK primaryKey) {

		_assetSharingEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_assetSharingEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the shared to class name ID of this asset sharing entry.
	 *
	 * @param sharedToClassNameId the shared to class name ID of this asset sharing entry
	 */
	@Override
	public void setSharedToClassNameId(long sharedToClassNameId) {
		_assetSharingEntry.setSharedToClassNameId(sharedToClassNameId);
	}

	/**
	 * Sets the shared to class pk of this asset sharing entry.
	 *
	 * @param sharedToClassPK the shared to class pk of this asset sharing entry
	 */
	@Override
	public void setSharedToClassPK(long sharedToClassPK) {
		_assetSharingEntry.setSharedToClassPK(sharedToClassPK);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AssetSharingEntry>
		toCacheModel() {

		return _assetSharingEntry.toCacheModel();
	}

	@Override
	public AssetSharingEntry toEscapedModel() {
		return new AssetSharingEntryWrapper(
			_assetSharingEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _assetSharingEntry.toString();
	}

	@Override
	public AssetSharingEntry toUnescapedModel() {
		return new AssetSharingEntryWrapper(
			_assetSharingEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _assetSharingEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetSharingEntryWrapper)) {
			return false;
		}

		AssetSharingEntryWrapper assetSharingEntryWrapper =
			(AssetSharingEntryWrapper)obj;

		if (Objects.equals(
				_assetSharingEntry,
				assetSharingEntryWrapper._assetSharingEntry)) {

			return true;
		}

		return false;
	}

	@Override
	public AssetSharingEntry getWrappedModel() {
		return _assetSharingEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _assetSharingEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _assetSharingEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_assetSharingEntry.resetOriginalValues();
	}

	private final AssetSharingEntry _assetSharingEntry;

}