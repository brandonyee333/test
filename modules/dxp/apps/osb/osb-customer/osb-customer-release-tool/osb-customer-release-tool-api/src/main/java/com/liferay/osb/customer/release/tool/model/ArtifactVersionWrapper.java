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
 * This class is a wrapper for {@link ArtifactVersion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ArtifactVersion
 * @generated
 */
@ProviderType
public class ArtifactVersionWrapper
	implements ArtifactVersion, ModelWrapper<ArtifactVersion> {

	public ArtifactVersionWrapper(ArtifactVersion artifactVersion) {
		_artifactVersion = artifactVersion;
	}

	@Override
	public Class<?> getModelClass() {
		return ArtifactVersion.class;
	}

	@Override
	public String getModelClassName() {
		return ArtifactVersion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("artifactVersionId", getArtifactVersionId());
		attributes.put("releaseAssetCategoryId", getReleaseAssetCategoryId());
		attributes.put("group", getGroup());
		attributes.put("name", getName());
		attributes.put("version", getVersion());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long artifactVersionId = (Long)attributes.get("artifactVersionId");

		if (artifactVersionId != null) {
			setArtifactVersionId(artifactVersionId);
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
		return new ArtifactVersionWrapper(
			(ArtifactVersion)_artifactVersion.clone());
	}

	@Override
	public int compareTo(ArtifactVersion artifactVersion) {
		return _artifactVersion.compareTo(artifactVersion);
	}

	/**
	 * Returns the artifact version ID of this artifact version.
	 *
	 * @return the artifact version ID of this artifact version
	 */
	@Override
	public long getArtifactVersionId() {
		return _artifactVersion.getArtifactVersionId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _artifactVersion.getExpandoBridge();
	}

	/**
	 * Returns the group of this artifact version.
	 *
	 * @return the group of this artifact version
	 */
	@Override
	public String getGroup() {
		return _artifactVersion.getGroup();
	}

	/**
	 * Returns the name of this artifact version.
	 *
	 * @return the name of this artifact version
	 */
	@Override
	public String getName() {
		return _artifactVersion.getName();
	}

	/**
	 * Returns the primary key of this artifact version.
	 *
	 * @return the primary key of this artifact version
	 */
	@Override
	public long getPrimaryKey() {
		return _artifactVersion.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _artifactVersion.getPrimaryKeyObj();
	}

	/**
	 * Returns the release asset category ID of this artifact version.
	 *
	 * @return the release asset category ID of this artifact version
	 */
	@Override
	public long getReleaseAssetCategoryId() {
		return _artifactVersion.getReleaseAssetCategoryId();
	}

	/**
	 * Returns the version of this artifact version.
	 *
	 * @return the version of this artifact version
	 */
	@Override
	public String getVersion() {
		return _artifactVersion.getVersion();
	}

	@Override
	public int hashCode() {
		return _artifactVersion.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _artifactVersion.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _artifactVersion.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _artifactVersion.isNew();
	}

	@Override
	public void persist() {
		_artifactVersion.persist();
	}

	/**
	 * Sets the artifact version ID of this artifact version.
	 *
	 * @param artifactVersionId the artifact version ID of this artifact version
	 */
	@Override
	public void setArtifactVersionId(long artifactVersionId) {
		_artifactVersion.setArtifactVersionId(artifactVersionId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_artifactVersion.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_artifactVersion.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_artifactVersion.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_artifactVersion.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group of this artifact version.
	 *
	 * @param group the group of this artifact version
	 */
	@Override
	public void setGroup(String group) {
		_artifactVersion.setGroup(group);
	}

	/**
	 * Sets the name of this artifact version.
	 *
	 * @param name the name of this artifact version
	 */
	@Override
	public void setName(String name) {
		_artifactVersion.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_artifactVersion.setNew(n);
	}

	/**
	 * Sets the primary key of this artifact version.
	 *
	 * @param primaryKey the primary key of this artifact version
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_artifactVersion.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_artifactVersion.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the release asset category ID of this artifact version.
	 *
	 * @param releaseAssetCategoryId the release asset category ID of this artifact version
	 */
	@Override
	public void setReleaseAssetCategoryId(long releaseAssetCategoryId) {
		_artifactVersion.setReleaseAssetCategoryId(releaseAssetCategoryId);
	}

	/**
	 * Sets the version of this artifact version.
	 *
	 * @param version the version of this artifact version
	 */
	@Override
	public void setVersion(String version) {
		_artifactVersion.setVersion(version);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ArtifactVersion>
		toCacheModel() {

		return _artifactVersion.toCacheModel();
	}

	@Override
	public ArtifactVersion toEscapedModel() {
		return new ArtifactVersionWrapper(_artifactVersion.toEscapedModel());
	}

	@Override
	public String toString() {
		return _artifactVersion.toString();
	}

	@Override
	public ArtifactVersion toUnescapedModel() {
		return new ArtifactVersionWrapper(_artifactVersion.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _artifactVersion.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ArtifactVersionWrapper)) {
			return false;
		}

		ArtifactVersionWrapper artifactVersionWrapper =
			(ArtifactVersionWrapper)obj;

		if (Objects.equals(
				_artifactVersion, artifactVersionWrapper._artifactVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public ArtifactVersion getWrappedModel() {
		return _artifactVersion;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _artifactVersion.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _artifactVersion.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_artifactVersion.resetOriginalValues();
	}

	private final ArtifactVersion _artifactVersion;

}