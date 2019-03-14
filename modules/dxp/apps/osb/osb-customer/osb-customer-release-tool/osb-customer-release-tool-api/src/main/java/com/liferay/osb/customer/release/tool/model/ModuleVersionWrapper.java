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

package com.liferay.osb.customer.release.tool.model;

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
 * This class is a wrapper for {@link ModuleVersion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModuleVersion
 * @generated
 */
@ProviderType
public class ModuleVersionWrapper
	implements ModuleVersion, ModelWrapper<ModuleVersion> {

	public ModuleVersionWrapper(ModuleVersion moduleVersion) {
		_moduleVersion = moduleVersion;
	}

	@Override
	public Class<?> getModelClass() {
		return ModuleVersion.class;
	}

	@Override
	public String getModelClassName() {
		return ModuleVersion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("moduleVersionId", getModuleVersionId());
		attributes.put("releaseAssetCategoryId", getReleaseAssetCategoryId());
		attributes.put("group", getGroup());
		attributes.put("name", getName());
		attributes.put("version", getVersion());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long moduleVersionId = (Long)attributes.get("moduleVersionId");

		if (moduleVersionId != null) {
			setModuleVersionId(moduleVersionId);
		}

		Long releaseAssetCategoryId = (Long)attributes.get(
			"releaseAssetCategoryId");

		if (releaseAssetCategoryId != null) {
			setReleaseAssetCategoryId(releaseAssetCategoryId);
		}

		String group = (String)attributes.get("group");

		if (group != null) {
			setGroup(group);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}
	}

	@Override
	public Object clone() {
		return new ModuleVersionWrapper((ModuleVersion)_moduleVersion.clone());
	}

	@Override
	public int compareTo(ModuleVersion moduleVersion) {
		return _moduleVersion.compareTo(moduleVersion);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _moduleVersion.getExpandoBridge();
	}

	/**
	 * Returns the group of this module version.
	 *
	 * @return the group of this module version
	 */
	@Override
	public String getGroup() {
		return _moduleVersion.getGroup();
	}

	/**
	 * Returns the module version ID of this module version.
	 *
	 * @return the module version ID of this module version
	 */
	@Override
	public long getModuleVersionId() {
		return _moduleVersion.getModuleVersionId();
	}

	/**
	 * Returns the name of this module version.
	 *
	 * @return the name of this module version
	 */
	@Override
	public String getName() {
		return _moduleVersion.getName();
	}

	/**
	 * Returns the primary key of this module version.
	 *
	 * @return the primary key of this module version
	 */
	@Override
	public long getPrimaryKey() {
		return _moduleVersion.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _moduleVersion.getPrimaryKeyObj();
	}

	/**
	 * Returns the release asset category ID of this module version.
	 *
	 * @return the release asset category ID of this module version
	 */
	@Override
	public long getReleaseAssetCategoryId() {
		return _moduleVersion.getReleaseAssetCategoryId();
	}

	/**
	 * Returns the version of this module version.
	 *
	 * @return the version of this module version
	 */
	@Override
	public String getVersion() {
		return _moduleVersion.getVersion();
	}

	@Override
	public int hashCode() {
		return _moduleVersion.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _moduleVersion.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _moduleVersion.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _moduleVersion.isNew();
	}

	@Override
	public void persist() {
		_moduleVersion.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_moduleVersion.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_moduleVersion.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_moduleVersion.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_moduleVersion.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group of this module version.
	 *
	 * @param group the group of this module version
	 */
	@Override
	public void setGroup(String group) {
		_moduleVersion.setGroup(group);
	}

	/**
	 * Sets the module version ID of this module version.
	 *
	 * @param moduleVersionId the module version ID of this module version
	 */
	@Override
	public void setModuleVersionId(long moduleVersionId) {
		_moduleVersion.setModuleVersionId(moduleVersionId);
	}

	/**
	 * Sets the name of this module version.
	 *
	 * @param name the name of this module version
	 */
	@Override
	public void setName(String name) {
		_moduleVersion.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_moduleVersion.setNew(n);
	}

	/**
	 * Sets the primary key of this module version.
	 *
	 * @param primaryKey the primary key of this module version
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_moduleVersion.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_moduleVersion.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the release asset category ID of this module version.
	 *
	 * @param releaseAssetCategoryId the release asset category ID of this module version
	 */
	@Override
	public void setReleaseAssetCategoryId(long releaseAssetCategoryId) {
		_moduleVersion.setReleaseAssetCategoryId(releaseAssetCategoryId);
	}

	/**
	 * Sets the version of this module version.
	 *
	 * @param version the version of this module version
	 */
	@Override
	public void setVersion(String version) {
		_moduleVersion.setVersion(version);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ModuleVersion>
		toCacheModel() {

		return _moduleVersion.toCacheModel();
	}

	@Override
	public ModuleVersion toEscapedModel() {
		return new ModuleVersionWrapper(_moduleVersion.toEscapedModel());
	}

	@Override
	public String toString() {
		return _moduleVersion.toString();
	}

	@Override
	public ModuleVersion toUnescapedModel() {
		return new ModuleVersionWrapper(_moduleVersion.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _moduleVersion.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ModuleVersionWrapper)) {
			return false;
		}

		ModuleVersionWrapper moduleVersionWrapper = (ModuleVersionWrapper)obj;

		if (Objects.equals(
				_moduleVersion, moduleVersionWrapper._moduleVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public ModuleVersion getWrappedModel() {
		return _moduleVersion;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _moduleVersion.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _moduleVersion.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_moduleVersion.resetOriginalValues();
	}

	private final ModuleVersion _moduleVersion;

}