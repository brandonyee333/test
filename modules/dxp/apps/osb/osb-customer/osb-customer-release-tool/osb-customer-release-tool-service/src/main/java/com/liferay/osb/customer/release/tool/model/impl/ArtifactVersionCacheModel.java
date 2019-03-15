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

package com.liferay.osb.customer.release.tool.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.tool.model.ArtifactVersion;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ArtifactVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ArtifactVersionCacheModel
	implements CacheModel<ArtifactVersion>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ArtifactVersionCacheModel)) {
			return false;
		}

		ArtifactVersionCacheModel artifactVersionCacheModel =
			(ArtifactVersionCacheModel)obj;

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
		StringBundler sb = new StringBundler(11);

		sb.append("{artifactVersionId=");
		sb.append(artifactVersionId);
		sb.append(", releaseAssetCategoryId=");
		sb.append(releaseAssetCategoryId);
		sb.append(", group=");
		sb.append(group);
		sb.append(", name=");
		sb.append(name);
		sb.append(", version=");
		sb.append(version);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ArtifactVersion toEntityModel() {
		ArtifactVersionImpl artifactVersionImpl = new ArtifactVersionImpl();

		artifactVersionImpl.setArtifactVersionId(artifactVersionId);
		artifactVersionImpl.setReleaseAssetCategoryId(releaseAssetCategoryId);

		if (group == null) {
			artifactVersionImpl.setGroup("");
		}
		else {
			artifactVersionImpl.setGroup(group);
		}

		if (name == null) {
			artifactVersionImpl.setName("");
		}
		else {
			artifactVersionImpl.setName(name);
		}

		if (version == null) {
			artifactVersionImpl.setVersion("");
		}
		else {
			artifactVersionImpl.setVersion(version);
		}

		artifactVersionImpl.resetOriginalValues();

		return artifactVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		artifactVersionId = objectInput.readLong();

		releaseAssetCategoryId = objectInput.readLong();
		group = objectInput.readUTF();
		name = objectInput.readUTF();
		version = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(artifactVersionId);

		objectOutput.writeLong(releaseAssetCategoryId);

		if (group == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(group);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (version == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(version);
		}
	}

	public long artifactVersionId;
	public long releaseAssetCategoryId;
	public String group;
	public String name;
	public String version;

}