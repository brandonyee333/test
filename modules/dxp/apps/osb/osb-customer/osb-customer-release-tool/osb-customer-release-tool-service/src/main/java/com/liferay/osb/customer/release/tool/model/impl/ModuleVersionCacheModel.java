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

import com.liferay.osb.customer.release.tool.model.ModuleVersion;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ModuleVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ModuleVersionCacheModel
	implements CacheModel<ModuleVersion>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ModuleVersionCacheModel)) {
			return false;
		}

		ModuleVersionCacheModel moduleVersionCacheModel =
			(ModuleVersionCacheModel)obj;

		if (moduleVersionId == moduleVersionCacheModel.moduleVersionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, moduleVersionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{moduleVersionId=");
		sb.append(moduleVersionId);
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
	public ModuleVersion toEntityModel() {
		ModuleVersionImpl moduleVersionImpl = new ModuleVersionImpl();

		moduleVersionImpl.setModuleVersionId(moduleVersionId);
		moduleVersionImpl.setReleaseAssetCategoryId(releaseAssetCategoryId);

		if (group == null) {
			moduleVersionImpl.setGroup("");
		}
		else {
			moduleVersionImpl.setGroup(group);
		}

		if (name == null) {
			moduleVersionImpl.setName("");
		}
		else {
			moduleVersionImpl.setName(name);
		}

		if (version == null) {
			moduleVersionImpl.setVersion("");
		}
		else {
			moduleVersionImpl.setVersion(version);
		}

		moduleVersionImpl.resetOriginalValues();

		return moduleVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		moduleVersionId = objectInput.readLong();

		releaseAssetCategoryId = objectInput.readLong();
		group = objectInput.readUTF();
		name = objectInput.readUTF();
		version = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(moduleVersionId);

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

	public long moduleVersionId;
	public long releaseAssetCategoryId;
	public String group;
	public String name;
	public String version;

}