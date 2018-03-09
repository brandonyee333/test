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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.CorpProjectMessage;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CorpProjectMessage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectMessage
 * @generated
 */
@ProviderType
public class CorpProjectMessageCacheModel implements CacheModel<CorpProjectMessage>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CorpProjectMessageCacheModel)) {
			return false;
		}

		CorpProjectMessageCacheModel corpProjectMessageCacheModel = (CorpProjectMessageCacheModel)obj;

		if (corpProjectMessageId == corpProjectMessageCacheModel.corpProjectMessageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, corpProjectMessageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", corpProjectMessageId=");
		sb.append(corpProjectMessageId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", corpProjectId=");
		sb.append(corpProjectId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", severityLevel=");
		sb.append(severityLevel);
		sb.append(", title=");
		sb.append(title);
		sb.append(", content=");
		sb.append(content);
		sb.append(", displayCP=");
		sb.append(displayCP);
		sb.append(", displayLCS=");
		sb.append(displayLCS);
		sb.append(", displayLESA=");
		sb.append(displayLESA);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CorpProjectMessage toEntityModel() {
		CorpProjectMessageImpl corpProjectMessageImpl = new CorpProjectMessageImpl();

		if (uuid == null) {
			corpProjectMessageImpl.setUuid("");
		}
		else {
			corpProjectMessageImpl.setUuid(uuid);
		}

		corpProjectMessageImpl.setCorpProjectMessageId(corpProjectMessageId);
		corpProjectMessageImpl.setUserId(userId);

		if (userName == null) {
			corpProjectMessageImpl.setUserName("");
		}
		else {
			corpProjectMessageImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			corpProjectMessageImpl.setCreateDate(null);
		}
		else {
			corpProjectMessageImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			corpProjectMessageImpl.setModifiedDate(null);
		}
		else {
			corpProjectMessageImpl.setModifiedDate(new Date(modifiedDate));
		}

		corpProjectMessageImpl.setCorpProjectId(corpProjectId);
		corpProjectMessageImpl.setType(type);
		corpProjectMessageImpl.setSeverityLevel(severityLevel);

		if (title == null) {
			corpProjectMessageImpl.setTitle("");
		}
		else {
			corpProjectMessageImpl.setTitle(title);
		}

		if (content == null) {
			corpProjectMessageImpl.setContent("");
		}
		else {
			corpProjectMessageImpl.setContent(content);
		}

		corpProjectMessageImpl.setDisplayCP(displayCP);
		corpProjectMessageImpl.setDisplayLCS(displayLCS);
		corpProjectMessageImpl.setDisplayLESA(displayLESA);

		corpProjectMessageImpl.resetOriginalValues();

		return corpProjectMessageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		corpProjectMessageId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		corpProjectId = objectInput.readLong();

		type = objectInput.readInt();

		severityLevel = objectInput.readInt();
		title = objectInput.readUTF();
		content = objectInput.readUTF();

		displayCP = objectInput.readBoolean();

		displayLCS = objectInput.readBoolean();

		displayLESA = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(corpProjectMessageId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(corpProjectId);

		objectOutput.writeInt(type);

		objectOutput.writeInt(severityLevel);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeBoolean(displayCP);

		objectOutput.writeBoolean(displayLCS);

		objectOutput.writeBoolean(displayLESA);
	}

	public String uuid;
	public long corpProjectMessageId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long corpProjectId;
	public int type;
	public int severityLevel;
	public String title;
	public String content;
	public boolean displayCP;
	public boolean displayLCS;
	public boolean displayLESA;
}