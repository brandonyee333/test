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

package com.liferay.watson.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.watson.model.WatsonResourceAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonResourceAudit in entity cache.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonResourceAuditCacheModel implements CacheModel<WatsonResourceAudit>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonResourceAuditCacheModel)) {
			return false;
		}

		WatsonResourceAuditCacheModel watsonResourceAuditCacheModel = (WatsonResourceAuditCacheModel)obj;

		if (watsonResourceAuditId == watsonResourceAuditCacheModel.watsonResourceAuditId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonResourceAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{watsonResourceAuditId=");
		sb.append(watsonResourceAuditId);
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
		sb.append(", originalWatsonResourceId=");
		sb.append(originalWatsonResourceId);
		sb.append(", typeWatsonListTypeId=");
		sb.append(typeWatsonListTypeId);
		sb.append(", watsonIncidentId=");
		sb.append(watsonIncidentId);
		sb.append(", watsonResourceId=");
		sb.append(watsonResourceId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", imagePayload=");
		sb.append(imagePayload);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonResourceAudit toEntityModel() {
		WatsonResourceAuditImpl watsonResourceAuditImpl = new WatsonResourceAuditImpl();

		watsonResourceAuditImpl.setWatsonResourceAuditId(watsonResourceAuditId);
		watsonResourceAuditImpl.setGroupId(groupId);
		watsonResourceAuditImpl.setCompanyId(companyId);
		watsonResourceAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonResourceAuditImpl.setUserName("");
		}
		else {
			watsonResourceAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonResourceAuditImpl.setCreateDate(null);
		}
		else {
			watsonResourceAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonResourceAuditImpl.setModifiedDate(null);
		}
		else {
			watsonResourceAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonResourceAuditImpl.setOriginalWatsonResourceId(originalWatsonResourceId);
		watsonResourceAuditImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonResourceAuditImpl.setWatsonIncidentId(watsonIncidentId);
		watsonResourceAuditImpl.setWatsonResourceId(watsonResourceId);

		if (name == null) {
			watsonResourceAuditImpl.setName("");
		}
		else {
			watsonResourceAuditImpl.setName(name);
		}

		if (description == null) {
			watsonResourceAuditImpl.setDescription("");
		}
		else {
			watsonResourceAuditImpl.setDescription(description);
		}

		if (imagePayload == null) {
			watsonResourceAuditImpl.setImagePayload("");
		}
		else {
			watsonResourceAuditImpl.setImagePayload(imagePayload);
		}

		watsonResourceAuditImpl.setStatus(status);

		watsonResourceAuditImpl.resetOriginalValues();

		return watsonResourceAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonResourceAuditId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		originalWatsonResourceId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();

		watsonIncidentId = objectInput.readLong();

		watsonResourceId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		imagePayload = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonResourceAuditId);

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

		objectOutput.writeLong(originalWatsonResourceId);

		objectOutput.writeLong(typeWatsonListTypeId);

		objectOutput.writeLong(watsonIncidentId);

		objectOutput.writeLong(watsonResourceId);

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

		if (imagePayload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imagePayload);
		}

		objectOutput.writeInt(status);
	}

	public long watsonResourceAuditId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long originalWatsonResourceId;
	public long typeWatsonListTypeId;
	public long watsonIncidentId;
	public long watsonResourceId;
	public String name;
	public String description;
	public String imagePayload;
	public int status;
}