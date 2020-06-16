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
public class ArtifactVersionWrapper implements ArtifactVersion,
	ModelWrapper<ArtifactVersion> {
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
		attributes.put("owner", getOwner());
		attributes.put("repository", getRepository());
		attributes.put("group", getGroup());
		attributes.put("name", getName());
		attributes.put("version", getVersion());
		attributes.put("packaging", getPackaging());

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

		Integer owner = (Integer)attributes.get("owner");

		if (owner != null) {
			setOwner(owner);
		}

		Integer repository = (Integer)attributes.get("repository");

		if (repository != null) {
			setRepository(repository);
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

		String packaging = (String)attributes.get("packaging");

		if (packaging != null) {
			setPackaging(packaging);
		}
	}

	@Override
	public ArtifactVersion toEscapedModel() {
		return new ArtifactVersionWrapper(_artifactVersion.toEscapedModel());
	}

	@Override
	public ArtifactVersion toUnescapedModel() {
		return new ArtifactVersionWrapper(_artifactVersion.toUnescapedModel());
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
	public ExpandoBridge getExpandoBridge() {
		return _artifactVersion.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ArtifactVersion> toCacheModel() {
		return _artifactVersion.toCacheModel();
	}

	@Override
	public int compareTo(ArtifactVersion artifactVersion) {
		return _artifactVersion.compareTo(artifactVersion);
	}

	/**
	* Returns the owner of this artifact version.
	*
	* @return the owner of this artifact version
	*/
	@Override
	public int getOwner() {
		return _artifactVersion.getOwner();
	}

	/**
	* Returns the repository of this artifact version.
	*
	* @return the repository of this artifact version
	*/
	@Override
	public int getRepository() {
		return _artifactVersion.getRepository();
	}

	@Override
	public int hashCode() {
		return _artifactVersion.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _artifactVersion.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ArtifactVersionWrapper((ArtifactVersion)_artifactVersion.clone());
	}

	/**
	* Returns the group of this artifact version.
	*
	* @return the group of this artifact version
	*/
	@Override
	public java.lang.String getGroup() {
		return _artifactVersion.getGroup();
	}

	/**
	* Returns the name of this artifact version.
	*
	* @return the name of this artifact version
	*/
	@Override
	public java.lang.String getName() {
		return _artifactVersion.getName();
	}

	/**
	* Returns the packaging of this artifact version.
	*
	* @return the packaging of this artifact version
	*/
	@Override
	public java.lang.String getPackaging() {
		return _artifactVersion.getPackaging();
	}

	/**
	* Returns the version of this artifact version.
	*
	* @return the version of this artifact version
	*/
	@Override
	public java.lang.String getVersion() {
		return _artifactVersion.getVersion();
	}

	@Override
	public java.lang.String toString() {
		return _artifactVersion.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _artifactVersion.toXmlString();
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

	/**
	* Returns the primary key of this artifact version.
	*
	* @return the primary key of this artifact version
	*/
	@Override
	public long getPrimaryKey() {
		return _artifactVersion.getPrimaryKey();
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
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_artifactVersion.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_artifactVersion.setExpandoBridgeAttributes(baseModel);
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
	public void setGroup(java.lang.String group) {
		_artifactVersion.setGroup(group);
	}

	/**
	* Sets the name of this artifact version.
	*
	* @param name the name of this artifact version
	*/
	@Override
	public void setName(java.lang.String name) {
		_artifactVersion.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_artifactVersion.setNew(n);
	}

	/**
	* Sets the owner of this artifact version.
	*
	* @param owner the owner of this artifact version
	*/
	@Override
	public void setOwner(int owner) {
		_artifactVersion.setOwner(owner);
	}

	/**
	* Sets the packaging of this artifact version.
	*
	* @param packaging the packaging of this artifact version
	*/
	@Override
	public void setPackaging(java.lang.String packaging) {
		_artifactVersion.setPackaging(packaging);
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
	* Sets the repository of this artifact version.
	*
	* @param repository the repository of this artifact version
	*/
	@Override
	public void setRepository(int repository) {
		_artifactVersion.setRepository(repository);
	}

	/**
	* Sets the version of this artifact version.
	*
	* @param version the version of this artifact version
	*/
	@Override
	public void setVersion(java.lang.String version) {
		_artifactVersion.setVersion(version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ArtifactVersionWrapper)) {
			return false;
		}

		ArtifactVersionWrapper artifactVersionWrapper = (ArtifactVersionWrapper)obj;

		if (Objects.equals(_artifactVersion,
					artifactVersionWrapper._artifactVersion)) {
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