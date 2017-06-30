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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AssetAttachment;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AssetAttachment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetAttachment
 * @generated
 */
public class AssetAttachmentCacheModel implements CacheModel<AssetAttachment>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{assetAttachmentId=");
		sb.append(assetAttachmentId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", type=");
		sb.append(type);
		sb.append(", rank=");
		sb.append(rank);
		sb.append("}");

		return sb.toString();
	}

	public AssetAttachment toEntityModel() {
		AssetAttachmentImpl assetAttachmentImpl = new AssetAttachmentImpl();

		assetAttachmentImpl.setAssetAttachmentId(assetAttachmentId);
		assetAttachmentImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			assetAttachmentImpl.setCreateDate(null);
		}
		else {
			assetAttachmentImpl.setCreateDate(new Date(createDate));
		}

		assetAttachmentImpl.setClassNameId(classNameId);
		assetAttachmentImpl.setClassPK(classPK);

		if (fileName == null) {
			assetAttachmentImpl.setFileName(StringPool.BLANK);
		}
		else {
			assetAttachmentImpl.setFileName(fileName);
		}

		assetAttachmentImpl.setType(type);
		assetAttachmentImpl.setRank(rank);

		assetAttachmentImpl.resetOriginalValues();

		return assetAttachmentImpl;
	}

	public long assetAttachmentId;
	public long userId;
	public long createDate;
	public long classNameId;
	public long classPK;
	public String fileName;
	public int type;
	public int rank;
}