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

package com.liferay.osb.community.doc.project.model.impl;

import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DocProject in entity cache.
 *
 * @author Ryan Park
 * @generated
 */
public class DocProjectCacheModel
	implements CacheModel<DocProject>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocProjectCacheModel)) {
			return false;
		}

		DocProjectCacheModel docProjectCacheModel = (DocProjectCacheModel)obj;

		if (docProjectId == docProjectCacheModel.docProjectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, docProjectId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", docProjectId=");
		sb.append(docProjectId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", iconFileName=");
		sb.append(iconFileName);
		sb.append(", unlisted=");
		sb.append(unlisted);
		sb.append(", type=");
		sb.append(type);
		sb.append(", typeSettings=");
		sb.append(typeSettings);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DocProject toEntityModel() {
		DocProjectImpl docProjectImpl = new DocProjectImpl();

		if (uuid == null) {
			docProjectImpl.setUuid("");
		}
		else {
			docProjectImpl.setUuid(uuid);
		}

		docProjectImpl.setDocProjectId(docProjectId);
		docProjectImpl.setGroupId(groupId);
		docProjectImpl.setCompanyId(companyId);
		docProjectImpl.setUserId(userId);

		if (userName == null) {
			docProjectImpl.setUserName("");
		}
		else {
			docProjectImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			docProjectImpl.setCreateDate(null);
		}
		else {
			docProjectImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			docProjectImpl.setModifiedDate(null);
		}
		else {
			docProjectImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			docProjectImpl.setName("");
		}
		else {
			docProjectImpl.setName(name);
		}

		if (description == null) {
			docProjectImpl.setDescription("");
		}
		else {
			docProjectImpl.setDescription(description);
		}

		if (iconFileName == null) {
			docProjectImpl.setIconFileName("");
		}
		else {
			docProjectImpl.setIconFileName(iconFileName);
		}

		docProjectImpl.setUnlisted(unlisted);

		if (type == null) {
			docProjectImpl.setType("");
		}
		else {
			docProjectImpl.setType(type);
		}

		if (typeSettings == null) {
			docProjectImpl.setTypeSettings("");
		}
		else {
			docProjectImpl.setTypeSettings(typeSettings);
		}

		docProjectImpl.setStatus(status);

		docProjectImpl.resetOriginalValues();

		return docProjectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		docProjectId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		iconFileName = objectInput.readUTF();

		unlisted = objectInput.readBoolean();
		type = objectInput.readUTF();
		typeSettings = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(docProjectId);

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

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (iconFileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(iconFileName);
		}

		objectOutput.writeBoolean(unlisted);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (typeSettings == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(typeSettings);
		}

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long docProjectId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public String iconFileName;
	public boolean unlisted;
	public String type;
	public String typeSettings;
	public int status;

}