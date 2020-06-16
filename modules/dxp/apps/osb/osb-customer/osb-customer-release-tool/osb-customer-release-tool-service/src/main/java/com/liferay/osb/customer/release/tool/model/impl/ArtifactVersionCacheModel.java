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

package com.liferay.osb.customer.release.tool.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.tool.model.ArtifactVersion;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ArtifactVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ArtifactVersion
 * @generated
 */
@ProviderType
public class ArtifactVersionCacheModel implements CacheModel<ArtifactVersion>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ArtifactVersionCacheModel)) {
			return false;
		}

		ArtifactVersionCacheModel artifactVersionCacheModel = (ArtifactVersionCacheModel)obj;

		if (artifactVersionId == artifactVersionCacheModel.artifactVersionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, artifactVersionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{artifactVersionId=");
		sb.append(artifactVersionId);
		sb.append(", releaseAssetCategoryId=");
		sb.append(releaseAssetCategoryId);
		sb.append(", owner=");
		sb.append(owner);
		sb.append(", repository=");
		sb.append(repository);
		sb.append(", group=");
		sb.append(group);
		sb.append(", name=");
		sb.append(name);
		sb.append(", version=");
		sb.append(version);
		sb.append(", packaging=");
		sb.append(packaging);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ArtifactVersion toEntityModel() {
		ArtifactVersionImpl artifactVersionImpl = new ArtifactVersionImpl();

		artifactVersionImpl.setArtifactVersionId(artifactVersionId);
		artifactVersionImpl.setReleaseAssetCategoryId(releaseAssetCategoryId);
		artifactVersionImpl.setOwner(owner);
		artifactVersionImpl.setRepository(repository);

		if (group == null) {
			artifactVersionImpl.setGroup(StringPool.BLANK);
		}
		else {
			artifactVersionImpl.setGroup(group);
		}

		if (name == null) {
			artifactVersionImpl.setName(StringPool.BLANK);
		}
		else {
			artifactVersionImpl.setName(name);
		}

		if (version == null) {
			artifactVersionImpl.setVersion(StringPool.BLANK);
		}
		else {
			artifactVersionImpl.setVersion(version);
		}

		if (packaging == null) {
			artifactVersionImpl.setPackaging(StringPool.BLANK);
		}
		else {
			artifactVersionImpl.setPackaging(packaging);
		}

		artifactVersionImpl.resetOriginalValues();

		return artifactVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		artifactVersionId = objectInput.readLong();

		releaseAssetCategoryId = objectInput.readLong();

		owner = objectInput.readInt();

		repository = objectInput.readInt();
		group = objectInput.readUTF();
		name = objectInput.readUTF();
		version = objectInput.readUTF();
		packaging = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(artifactVersionId);

		objectOutput.writeLong(releaseAssetCategoryId);

		objectOutput.writeInt(owner);

		objectOutput.writeInt(repository);

		if (group == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(group);
		}

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (version == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(version);
		}

		if (packaging == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(packaging);
		}
	}

	public long artifactVersionId;
	public long releaseAssetCategoryId;
	public int owner;
	public int repository;
	public String group;
	public String name;
	public String version;
	public String packaging;
}