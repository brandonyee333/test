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

package com.liferay.osb.loop.asset.entry.set.model.impl;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AssetEntrySet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetEntrySetCacheModel
	implements CacheModel<AssetEntrySet>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntrySetCacheModel)) {
			return false;
		}

		AssetEntrySetCacheModel assetEntrySetCacheModel =
			(AssetEntrySetCacheModel)obj;

		if (assetEntrySetId == assetEntrySetCacheModel.assetEntrySetId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, assetEntrySetId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{assetEntrySetId=");
		sb.append(assetEntrySetId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createTime=");
		sb.append(createTime);
		sb.append(", modifiedTime=");
		sb.append(modifiedTime);
		sb.append(", assetEntryId=");
		sb.append(assetEntryId);
		sb.append(", parentAssetEntrySetId=");
		sb.append(parentAssetEntrySetId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", creatorClassNameId=");
		sb.append(creatorClassNameId);
		sb.append(", creatorClassPK=");
		sb.append(creatorClassPK);
		sb.append(", creatorName=");
		sb.append(creatorName);
		sb.append(", assetEntrySetLikesCount=");
		sb.append(assetEntrySetLikesCount);
		sb.append(", childAssetEntrySetsCount=");
		sb.append(childAssetEntrySetsCount);
		sb.append(", level=");
		sb.append(level);
		sb.append(", payload=");
		sb.append(payload);
		sb.append(", privateAssetEntrySet=");
		sb.append(privateAssetEntrySet);
		sb.append(", stickyTime=");
		sb.append(stickyTime);
		sb.append(", title=");
		sb.append(title);
		sb.append(", type=");
		sb.append(type);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AssetEntrySet toEntityModel() {
		AssetEntrySetImpl assetEntrySetImpl = new AssetEntrySetImpl();

		assetEntrySetImpl.setAssetEntrySetId(assetEntrySetId);
		assetEntrySetImpl.setCompanyId(companyId);
		assetEntrySetImpl.setUserId(userId);
		assetEntrySetImpl.setCreateTime(createTime);
		assetEntrySetImpl.setModifiedTime(modifiedTime);
		assetEntrySetImpl.setAssetEntryId(assetEntryId);
		assetEntrySetImpl.setParentAssetEntrySetId(parentAssetEntrySetId);
		assetEntrySetImpl.setClassNameId(classNameId);
		assetEntrySetImpl.setClassPK(classPK);
		assetEntrySetImpl.setCreatorClassNameId(creatorClassNameId);
		assetEntrySetImpl.setCreatorClassPK(creatorClassPK);

		if (creatorName == null) {
			assetEntrySetImpl.setCreatorName("");
		}
		else {
			assetEntrySetImpl.setCreatorName(creatorName);
		}

		assetEntrySetImpl.setAssetEntrySetLikesCount(assetEntrySetLikesCount);
		assetEntrySetImpl.setChildAssetEntrySetsCount(childAssetEntrySetsCount);
		assetEntrySetImpl.setLevel(level);

		if (payload == null) {
			assetEntrySetImpl.setPayload("");
		}
		else {
			assetEntrySetImpl.setPayload(payload);
		}

		assetEntrySetImpl.setPrivateAssetEntrySet(privateAssetEntrySet);
		assetEntrySetImpl.setStickyTime(stickyTime);

		if (title == null) {
			assetEntrySetImpl.setTitle("");
		}
		else {
			assetEntrySetImpl.setTitle(title);
		}

		assetEntrySetImpl.setType(type);
		assetEntrySetImpl.setStatus(status);

		assetEntrySetImpl.resetOriginalValues();

		return assetEntrySetImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		assetEntrySetId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();

		createTime = objectInput.readLong();

		modifiedTime = objectInput.readLong();

		assetEntryId = objectInput.readLong();

		parentAssetEntrySetId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		creatorClassNameId = objectInput.readLong();

		creatorClassPK = objectInput.readLong();
		creatorName = objectInput.readUTF();

		assetEntrySetLikesCount = objectInput.readInt();

		childAssetEntrySetsCount = objectInput.readInt();

		level = objectInput.readInt();
		payload = objectInput.readUTF();

		privateAssetEntrySet = objectInput.readBoolean();

		stickyTime = objectInput.readLong();
		title = objectInput.readUTF();

		type = objectInput.readInt();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(assetEntrySetId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(createTime);

		objectOutput.writeLong(modifiedTime);

		objectOutput.writeLong(assetEntryId);

		objectOutput.writeLong(parentAssetEntrySetId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(creatorClassNameId);

		objectOutput.writeLong(creatorClassPK);

		if (creatorName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(creatorName);
		}

		objectOutput.writeInt(assetEntrySetLikesCount);

		objectOutput.writeInt(childAssetEntrySetsCount);

		objectOutput.writeInt(level);

		if (payload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(payload);
		}

		objectOutput.writeBoolean(privateAssetEntrySet);

		objectOutput.writeLong(stickyTime);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeInt(type);

		objectOutput.writeInt(status);
	}

	public long assetEntrySetId;
	public long companyId;
	public long userId;
	public long createTime;
	public long modifiedTime;
	public long assetEntryId;
	public long parentAssetEntrySetId;
	public long classNameId;
	public long classPK;
	public long creatorClassNameId;
	public long creatorClassPK;
	public String creatorName;
	public int assetEntrySetLikesCount;
	public int childAssetEntrySetsCount;
	public int level;
	public String payload;
	public boolean privateAssetEntrySet;
	public long stickyTime;
	public String title;
	public int type;
	public int status;

}