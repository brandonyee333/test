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

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ZendeskSection in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ZendeskSectionCacheModel
	implements CacheModel<ZendeskSection>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZendeskSectionCacheModel)) {
			return false;
		}

		ZendeskSectionCacheModel zendeskSectionCacheModel =
			(ZendeskSectionCacheModel)obj;

		if (zendeskSectionId == zendeskSectionCacheModel.zendeskSectionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, zendeskSectionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{zendeskSectionId=");
		sb.append(zendeskSectionId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", zendeskCategoryId=");
		sb.append(zendeskCategoryId);
		sb.append(", documentationKey=");
		sb.append(documentationKey);
		sb.append(", remoteId=");
		sb.append(remoteId);
		sb.append(", remoteHtmlURL=");
		sb.append(remoteHtmlURL);
		sb.append(", remoteName=");
		sb.append(remoteName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ZendeskSection toEntityModel() {
		ZendeskSectionImpl zendeskSectionImpl = new ZendeskSectionImpl();

		zendeskSectionImpl.setZendeskSectionId(zendeskSectionId);

		if (modifiedDate == Long.MIN_VALUE) {
			zendeskSectionImpl.setModifiedDate(null);
		}
		else {
			zendeskSectionImpl.setModifiedDate(new Date(modifiedDate));
		}

		zendeskSectionImpl.setZendeskCategoryId(zendeskCategoryId);

		if (documentationKey == null) {
			zendeskSectionImpl.setDocumentationKey("");
		}
		else {
			zendeskSectionImpl.setDocumentationKey(documentationKey);
		}

		zendeskSectionImpl.setRemoteId(remoteId);

		if (remoteHtmlURL == null) {
			zendeskSectionImpl.setRemoteHtmlURL("");
		}
		else {
			zendeskSectionImpl.setRemoteHtmlURL(remoteHtmlURL);
		}

		if (remoteName == null) {
			zendeskSectionImpl.setRemoteName("");
		}
		else {
			zendeskSectionImpl.setRemoteName(remoteName);
		}

		zendeskSectionImpl.resetOriginalValues();

		return zendeskSectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		zendeskSectionId = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		zendeskCategoryId = objectInput.readLong();
		documentationKey = objectInput.readUTF();

		remoteId = objectInput.readLong();
		remoteHtmlURL = objectInput.readUTF();
		remoteName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(zendeskSectionId);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(zendeskCategoryId);

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

		if (remoteName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(remoteName);
		}
	}

	public long zendeskSectionId;
	public long modifiedDate;
	public long zendeskCategoryId;
	public String documentationKey;
	public long remoteId;
	public String remoteHtmlURL;
	public String remoteName;

}