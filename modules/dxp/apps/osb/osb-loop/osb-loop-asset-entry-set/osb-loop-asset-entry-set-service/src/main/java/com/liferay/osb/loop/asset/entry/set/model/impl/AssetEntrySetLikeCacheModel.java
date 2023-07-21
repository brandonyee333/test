/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.model.impl;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetLikePK;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AssetEntrySetLike in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetEntrySetLikeCacheModel
	implements CacheModel<AssetEntrySetLike>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AssetEntrySetLikeCacheModel)) {
			return false;
		}

		AssetEntrySetLikeCacheModel assetEntrySetLikeCacheModel =
			(AssetEntrySetLikeCacheModel)object;

		if (assetEntrySetLikePK.equals(
				assetEntrySetLikeCacheModel.assetEntrySetLikePK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, assetEntrySetLikePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{assetEntrySetId=");
		sb.append(assetEntrySetId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AssetEntrySetLike toEntityModel() {
		AssetEntrySetLikeImpl assetEntrySetLikeImpl =
			new AssetEntrySetLikeImpl();

		assetEntrySetLikeImpl.setAssetEntrySetId(assetEntrySetId);
		assetEntrySetLikeImpl.setClassNameId(classNameId);
		assetEntrySetLikeImpl.setClassPK(classPK);

		assetEntrySetLikeImpl.resetOriginalValues();

		return assetEntrySetLikeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		assetEntrySetId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		assetEntrySetLikePK = new AssetEntrySetLikePK(
			assetEntrySetId, classNameId, classPK);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(assetEntrySetId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);
	}

	public long assetEntrySetId;
	public long classNameId;
	public long classPK;
	public transient AssetEntrySetLikePK assetEntrySetLikePK;

}