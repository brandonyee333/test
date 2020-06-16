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

package com.liferay.osb.customer.zendesk.documentation.sync.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ZendeskCategory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskCategory
 * @generated
 */
@ProviderType
public class ZendeskCategoryCacheModel implements CacheModel<ZendeskCategory>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZendeskCategoryCacheModel)) {
			return false;
		}

		ZendeskCategoryCacheModel zendeskCategoryCacheModel = (ZendeskCategoryCacheModel)obj;

		if (zendeskCategoryId == zendeskCategoryCacheModel.zendeskCategoryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, zendeskCategoryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{zendeskCategoryId=");
		sb.append(zendeskCategoryId);
		sb.append(", documentationKey=");
		sb.append(documentationKey);
		sb.append(", documentationOriginalURL=");
		sb.append(documentationOriginalURL);
		sb.append(", articleLabels=");
		sb.append(articleLabels);
		sb.append(", remoteId=");
		sb.append(remoteId);
		sb.append(", remoteUserSegmentId=");
		sb.append(remoteUserSegmentId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ZendeskCategory toEntityModel() {
		ZendeskCategoryImpl zendeskCategoryImpl = new ZendeskCategoryImpl();

		zendeskCategoryImpl.setZendeskCategoryId(zendeskCategoryId);

		if (documentationKey == null) {
			zendeskCategoryImpl.setDocumentationKey(StringPool.BLANK);
		}
		else {
			zendeskCategoryImpl.setDocumentationKey(documentationKey);
		}

		if (documentationOriginalURL == null) {
			zendeskCategoryImpl.setDocumentationOriginalURL(StringPool.BLANK);
		}
		else {
			zendeskCategoryImpl.setDocumentationOriginalURL(documentationOriginalURL);
		}

		if (articleLabels == null) {
			zendeskCategoryImpl.setArticleLabels(StringPool.BLANK);
		}
		else {
			zendeskCategoryImpl.setArticleLabels(articleLabels);
		}

		zendeskCategoryImpl.setRemoteId(remoteId);
		zendeskCategoryImpl.setRemoteUserSegmentId(remoteUserSegmentId);

		zendeskCategoryImpl.resetOriginalValues();

		return zendeskCategoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		zendeskCategoryId = objectInput.readLong();
		documentationKey = objectInput.readUTF();
		documentationOriginalURL = objectInput.readUTF();
		articleLabels = objectInput.readUTF();

		remoteId = objectInput.readLong();

		remoteUserSegmentId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(zendeskCategoryId);

		if (documentationKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(documentationKey);
		}

		if (documentationOriginalURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(documentationOriginalURL);
		}

		if (articleLabels == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(articleLabels);
		}

		objectOutput.writeLong(remoteId);

		objectOutput.writeLong(remoteUserSegmentId);
	}

	public long zendeskCategoryId;
	public String documentationKey;
	public String documentationOriginalURL;
	public String articleLabels;
	public long remoteId;
	public long remoteUserSegmentId;
}