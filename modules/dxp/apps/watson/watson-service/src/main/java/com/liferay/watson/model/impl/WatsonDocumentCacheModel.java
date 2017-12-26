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

import com.liferay.watson.model.WatsonDocument;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonDocument in entity cache.
 *
 * @author Steven Smith
 * @see WatsonDocument
 * @generated
 */
@ProviderType
public class WatsonDocumentCacheModel implements CacheModel<WatsonDocument>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonDocumentCacheModel)) {
			return false;
		}

		WatsonDocumentCacheModel watsonDocumentCacheModel = (WatsonDocumentCacheModel)obj;

		if (watsonDocumentId == watsonDocumentCacheModel.watsonDocumentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonDocumentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{watsonDocumentId=");
		sb.append(watsonDocumentId);
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
	public WatsonDocument toEntityModel() {
		WatsonDocumentImpl watsonDocumentImpl = new WatsonDocumentImpl();

		watsonDocumentImpl.setWatsonDocumentId(watsonDocumentId);
		watsonDocumentImpl.setGroupId(groupId);
		watsonDocumentImpl.setCompanyId(companyId);
		watsonDocumentImpl.setUserId(userId);

		if (userName == null) {
			watsonDocumentImpl.setUserName("");
		}
		else {
			watsonDocumentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonDocumentImpl.setCreateDate(null);
		}
		else {
			watsonDocumentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonDocumentImpl.setModifiedDate(null);
		}
		else {
			watsonDocumentImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonDocumentImpl.setParentTypeWatsonListTypeId(parentTypeWatsonListTypeId);
		watsonDocumentImpl.setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
		watsonDocumentImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonDocumentImpl.setWatsonChildId(watsonChildId);
		watsonDocumentImpl.setOriginalDocument(originalDocument);

		if (receivedDate == Long.MIN_VALUE) {
			watsonDocumentImpl.setReceivedDate(null);
		}
		else {
			watsonDocumentImpl.setReceivedDate(new Date(receivedDate));
		}

		if (imagePayload == null) {
			watsonDocumentImpl.setImagePayload("");
		}
		else {
			watsonDocumentImpl.setImagePayload(imagePayload);
		}

		watsonDocumentImpl.setStatus(status);

		watsonDocumentImpl.resetOriginalValues();

		return watsonDocumentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonDocumentId = objectInput.readLong();

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

		originalDocument = objectInput.readBoolean();
		receivedDate = objectInput.readLong();
		imagePayload = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonDocumentId);

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

	public long watsonDocumentId;
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
	public boolean originalDocument;
	public long receivedDate;
	public String imagePayload;
	public int status;
}