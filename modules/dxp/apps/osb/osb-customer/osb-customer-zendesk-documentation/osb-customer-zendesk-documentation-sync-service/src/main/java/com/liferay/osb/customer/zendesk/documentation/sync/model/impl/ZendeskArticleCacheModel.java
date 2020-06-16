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

package com.liferay.osb.customer.zendesk.documentation.sync.model.impl;

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;
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
 * @generated
 */
public class ZendeskArticleCacheModel
	implements CacheModel<ZendeskArticle>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZendeskArticleCacheModel)) {
			return false;
		}

		ZendeskArticleCacheModel zendeskArticleCacheModel =
			(ZendeskArticleCacheModel)obj;

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
		StringBundler sb = new StringBundler(23);

		sb.append("{zendeskArticleId=");
		sb.append(zendeskArticleId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", zendeskCategoryId=");
		sb.append(zendeskCategoryId);
		sb.append(", zendeskSectionId=");
		sb.append(zendeskSectionId);
		sb.append(", documentationKey=");
		sb.append(documentationKey);
		sb.append(", documentationOriginalURL=");
		sb.append(documentationOriginalURL);
		sb.append(", previousArticleDocumentationKey=");
		sb.append(previousArticleDocumentationKey);
		sb.append(", nextArticleDocumentationKey=");
		sb.append(nextArticleDocumentationKey);
		sb.append(", remoteId=");
		sb.append(remoteId);
		sb.append(", remoteHtmlURL=");
		sb.append(remoteHtmlURL);
		sb.append(", remoteTitle=");
		sb.append(remoteTitle);
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

		zendeskArticleImpl.setZendeskCategoryId(zendeskCategoryId);
		zendeskArticleImpl.setZendeskSectionId(zendeskSectionId);

		if (documentationKey == null) {
			zendeskArticleImpl.setDocumentationKey("");
		}
		else {
			zendeskArticleImpl.setDocumentationKey(documentationKey);
		}

		if (documentationOriginalURL == null) {
			zendeskArticleImpl.setDocumentationOriginalURL("");
		}
		else {
			zendeskArticleImpl.setDocumentationOriginalURL(
				documentationOriginalURL);
		}

		if (previousArticleDocumentationKey == null) {
			zendeskArticleImpl.setPreviousArticleDocumentationKey("");
		}
		else {
			zendeskArticleImpl.setPreviousArticleDocumentationKey(
				previousArticleDocumentationKey);
		}

		if (nextArticleDocumentationKey == null) {
			zendeskArticleImpl.setNextArticleDocumentationKey("");
		}
		else {
			zendeskArticleImpl.setNextArticleDocumentationKey(
				nextArticleDocumentationKey);
		}

		zendeskArticleImpl.setRemoteId(remoteId);

		if (remoteHtmlURL == null) {
			zendeskArticleImpl.setRemoteHtmlURL("");
		}
		else {
			zendeskArticleImpl.setRemoteHtmlURL(remoteHtmlURL);
		}

		if (remoteTitle == null) {
			zendeskArticleImpl.setRemoteTitle("");
		}
		else {
			zendeskArticleImpl.setRemoteTitle(remoteTitle);
		}

		zendeskArticleImpl.resetOriginalValues();

		return zendeskArticleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		zendeskArticleId = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		zendeskCategoryId = objectInput.readLong();

		zendeskSectionId = objectInput.readLong();
		documentationKey = objectInput.readUTF();
		documentationOriginalURL = objectInput.readUTF();
		previousArticleDocumentationKey = objectInput.readUTF();
		nextArticleDocumentationKey = objectInput.readUTF();

		remoteId = objectInput.readLong();
		remoteHtmlURL = objectInput.readUTF();
		remoteTitle = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(zendeskArticleId);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(zendeskCategoryId);

		objectOutput.writeLong(zendeskSectionId);

		if (documentationKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(documentationKey);
		}

		if (documentationOriginalURL == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(documentationOriginalURL);
		}

		if (previousArticleDocumentationKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(previousArticleDocumentationKey);
		}

		if (nextArticleDocumentationKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(nextArticleDocumentationKey);
		}

		objectOutput.writeLong(remoteId);

		if (remoteHtmlURL == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(remoteHtmlURL);
		}

		if (remoteTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(remoteTitle);
		}
	}

	public long zendeskArticleId;
	public long modifiedDate;
	public long zendeskCategoryId;
	public long zendeskSectionId;
	public String documentationKey;
	public String documentationOriginalURL;
	public String previousArticleDocumentationKey;
	public String nextArticleDocumentationKey;
	public long remoteId;
	public String remoteHtmlURL;
	public String remoteTitle;

}