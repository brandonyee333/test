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

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ZendeskArticleAttachment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleAttachment
 * @generated
 */
@ProviderType
public class ZendeskArticleAttachmentCacheModel implements CacheModel<ZendeskArticleAttachment>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZendeskArticleAttachmentCacheModel)) {
			return false;
		}

		ZendeskArticleAttachmentCacheModel zendeskArticleAttachmentCacheModel = (ZendeskArticleAttachmentCacheModel)obj;

		if (zendeskArticleAttachmentId == zendeskArticleAttachmentCacheModel.zendeskArticleAttachmentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, zendeskArticleAttachmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{zendeskArticleAttachmentId=");
		sb.append(zendeskArticleAttachmentId);
		sb.append(", zendeskArticleId=");
		sb.append(zendeskArticleId);
		sb.append(", filePath=");
		sb.append(filePath);
		sb.append(", checksum=");
		sb.append(checksum);
		sb.append(", remoteId=");
		sb.append(remoteId);
		sb.append(", remoteContentURL=");
		sb.append(remoteContentURL);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ZendeskArticleAttachment toEntityModel() {
		ZendeskArticleAttachmentImpl zendeskArticleAttachmentImpl = new ZendeskArticleAttachmentImpl();

		zendeskArticleAttachmentImpl.setZendeskArticleAttachmentId(zendeskArticleAttachmentId);
		zendeskArticleAttachmentImpl.setZendeskArticleId(zendeskArticleId);

		if (filePath == null) {
			zendeskArticleAttachmentImpl.setFilePath(StringPool.BLANK);
		}
		else {
			zendeskArticleAttachmentImpl.setFilePath(filePath);
		}

		if (checksum == null) {
			zendeskArticleAttachmentImpl.setChecksum(StringPool.BLANK);
		}
		else {
			zendeskArticleAttachmentImpl.setChecksum(checksum);
		}

		zendeskArticleAttachmentImpl.setRemoteId(remoteId);

		if (remoteContentURL == null) {
			zendeskArticleAttachmentImpl.setRemoteContentURL(StringPool.BLANK);
		}
		else {
			zendeskArticleAttachmentImpl.setRemoteContentURL(remoteContentURL);
		}

		zendeskArticleAttachmentImpl.resetOriginalValues();

		return zendeskArticleAttachmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		zendeskArticleAttachmentId = objectInput.readLong();

		zendeskArticleId = objectInput.readLong();
		filePath = objectInput.readUTF();
		checksum = objectInput.readUTF();

		remoteId = objectInput.readLong();
		remoteContentURL = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(zendeskArticleAttachmentId);

		objectOutput.writeLong(zendeskArticleId);

		if (filePath == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(filePath);
		}

		if (checksum == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(checksum);
		}

		objectOutput.writeLong(remoteId);

		if (remoteContentURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(remoteContentURL);
		}
	}

	public long zendeskArticleAttachmentId;
	public long zendeskArticleId;
	public String filePath;
	public String checksum;
	public long remoteId;
	public String remoteContentURL;
}