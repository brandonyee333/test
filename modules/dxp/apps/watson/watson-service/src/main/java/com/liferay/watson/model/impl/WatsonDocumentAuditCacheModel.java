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

package com.liferay.watson.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.watson.model.WatsonDocumentAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonDocumentAudit in entity cache.
 *
 * @author Steven Smith
 * @see WatsonDocumentAudit
 * @generated
 */
@ProviderType
public class WatsonDocumentAuditCacheModel implements CacheModel<WatsonDocumentAudit>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonDocumentAuditCacheModel)) {
			return false;
		}

		WatsonDocumentAuditCacheModel watsonDocumentAuditCacheModel = (WatsonDocumentAuditCacheModel)obj;

		if (watsonDocumentAuditId == watsonDocumentAuditCacheModel.watsonDocumentAuditId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonDocumentAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{watsonDocumentAuditId=");
		sb.append(watsonDocumentAuditId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", parentTypeWatsonListTypeId=");
		sb.append(parentTypeWatsonListTypeId);
		sb.append(", subtypeWatsonListTypeId=");
		sb.append(subtypeWatsonListTypeId);
		sb.append(", typeWatsonListTypeId=");
		sb.append(typeWatsonListTypeId);
		sb.append(", watsonChildId=");
		sb.append(watsonChildId);
		sb.append(", watsonDocumentId=");
		sb.append(watsonDocumentId);
		sb.append(", originalDocument=");
		sb.append(originalDocument);
		sb.append(", receivedDate=");
		sb.append(receivedDate);
		sb.append(", imagePayload=");
		sb.append(imagePayload);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonDocumentAudit toEntityModel() {
		WatsonDocumentAuditImpl watsonDocumentAuditImpl = new WatsonDocumentAuditImpl();

		watsonDocumentAuditImpl.setWatsonDocumentAuditId(watsonDocumentAuditId);
		watsonDocumentAuditImpl.setGroupId(groupId);
		watsonDocumentAuditImpl.setCompanyId(companyId);
		watsonDocumentAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonDocumentAuditImpl.setUserName("");
		}
		else {
			watsonDocumentAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonDocumentAuditImpl.setCreateDate(null);
		}
		else {
			watsonDocumentAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonDocumentAuditImpl.setModifiedDate(null);
		}
		else {
			watsonDocumentAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonDocumentAuditImpl.setParentTypeWatsonListTypeId(parentTypeWatsonListTypeId);
		watsonDocumentAuditImpl.setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
		watsonDocumentAuditImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonDocumentAuditImpl.setWatsonChildId(watsonChildId);
		watsonDocumentAuditImpl.setWatsonDocumentId(watsonDocumentId);
		watsonDocumentAuditImpl.setOriginalDocument(originalDocument);

		if (receivedDate == Long.MIN_VALUE) {
			watsonDocumentAuditImpl.setReceivedDate(null);
		}
		else {
			watsonDocumentAuditImpl.setReceivedDate(new Date(receivedDate));
		}

		if (imagePayload == null) {
			watsonDocumentAuditImpl.setImagePayload("");
		}
		else {
			watsonDocumentAuditImpl.setImagePayload(imagePayload);
		}

		watsonDocumentAuditImpl.setStatus(status);

		watsonDocumentAuditImpl.resetOriginalValues();

		return watsonDocumentAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonDocumentAuditId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		parentTypeWatsonListTypeId = objectInput.readLong();

		subtypeWatsonListTypeId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();

		watsonChildId = objectInput.readLong();

		watsonDocumentId = objectInput.readLong();

		originalDocument = objectInput.readBoolean();
		receivedDate = objectInput.readLong();
		imagePayload = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonDocumentAuditId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(parentTypeWatsonListTypeId);

		objectOutput.writeLong(subtypeWatsonListTypeId);

		objectOutput.writeLong(typeWatsonListTypeId);

		objectOutput.writeLong(watsonChildId);

		objectOutput.writeLong(watsonDocumentId);

		objectOutput.writeBoolean(originalDocument);
		objectOutput.writeLong(receivedDate);

		if (imagePayload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imagePayload);
		}

		objectOutput.writeInt(status);
	}

	public long watsonDocumentAuditId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long parentTypeWatsonListTypeId;
	public long subtypeWatsonListTypeId;
	public long typeWatsonListTypeId;
	public long watsonChildId;
	public long watsonDocumentId;
	public boolean originalDocument;
	public long receivedDate;
	public String imagePayload;
	public int status;
}