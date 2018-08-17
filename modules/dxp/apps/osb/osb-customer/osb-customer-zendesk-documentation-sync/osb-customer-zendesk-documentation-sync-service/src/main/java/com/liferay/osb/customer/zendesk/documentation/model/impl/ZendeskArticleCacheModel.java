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

package com.liferay.osb.customer.zendesk.documentation.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticle;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ZendeskArticle in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticle
 * @generated
 */
@ProviderType
public class ZendeskArticleCacheModel implements CacheModel<ZendeskArticle>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZendeskArticleCacheModel)) {
			return false;
		}

		ZendeskArticleCacheModel zendeskArticleCacheModel = (ZendeskArticleCacheModel)obj;

		if (zendeskArticleId == zendeskArticleCacheModel.zendeskArticleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, zendeskArticleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{zendeskArticleId=");
		sb.append(zendeskArticleId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", zendeskSectionId=");
		sb.append(zendeskSectionId);
		sb.append(", documentationKey=");
		sb.append(documentationKey);
		sb.append(", remoteId=");
		sb.append(remoteId);
		sb.append(", remoteHtmlURL=");
		sb.append(remoteHtmlURL);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ZendeskArticle toEntityModel() {
		ZendeskArticleImpl zendeskArticleImpl = new ZendeskArticleImpl();

		zendeskArticleImpl.setZendeskArticleId(zendeskArticleId);

		if (modifiedDate == Long.MIN_VALUE) {
			zendeskArticleImpl.setModifiedDate(null);
		}
		else {
			zendeskArticleImpl.setModifiedDate(new Date(modifiedDate));
		}

		zendeskArticleImpl.setZendeskSectionId(zendeskSectionId);

		if (documentationKey == null) {
			zendeskArticleImpl.setDocumentationKey("");
		}
		else {
			zendeskArticleImpl.setDocumentationKey(documentationKey);
		}

		zendeskArticleImpl.setRemoteId(remoteId);

		if (remoteHtmlURL == null) {
			zendeskArticleImpl.setRemoteHtmlURL("");
		}
		else {
			zendeskArticleImpl.setRemoteHtmlURL(remoteHtmlURL);
		}

		zendeskArticleImpl.resetOriginalValues();

		return zendeskArticleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		zendeskArticleId = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		zendeskSectionId = objectInput.readLong();
		documentationKey = objectInput.readUTF();

		remoteId = objectInput.readLong();
		remoteHtmlURL = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(zendeskArticleId);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(zendeskSectionId);

		if (documentationKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(documentationKey);
		}

		objectOutput.writeLong(remoteId);

		if (remoteHtmlURL == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(remoteHtmlURL);
		}
	}

	public long zendeskArticleId;
	public long modifiedDate;
	public long zendeskSectionId;
	public String documentationKey;
	public long remoteId;
	public String remoteHtmlURL;
}