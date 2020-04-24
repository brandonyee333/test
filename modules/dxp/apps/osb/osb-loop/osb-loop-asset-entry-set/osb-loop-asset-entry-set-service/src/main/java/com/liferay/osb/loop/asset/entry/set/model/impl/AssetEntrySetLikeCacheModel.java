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

package com.liferay.osb.loop.asset.entry.set.model.impl;

import aQute.bnd.annotation.ProviderType;

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
 * @see AssetEntrySetLike
 * @generated
 */
@ProviderType
public class AssetEntrySetLikeCacheModel implements CacheModel<AssetEntrySetLike>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntrySetLikeCacheModel)) {
			return false;
		}

		AssetEntrySetLikeCacheModel assetEntrySetLikeCacheModel = (AssetEntrySetLikeCacheModel)obj;

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
		AssetEntrySetLikeImpl assetEntrySetLikeImpl = new AssetEntrySetLikeImpl();

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

		assetEntrySetLikePK = new AssetEntrySetLikePK(assetEntrySetId,
				classNameId, classPK);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(assetEntrySetId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);
	}

	public long assetEntrySetId;
	public long classNameId;
	public long classPK;
	public transient AssetEntrySetLikePK assetEntrySetLikePK;
}