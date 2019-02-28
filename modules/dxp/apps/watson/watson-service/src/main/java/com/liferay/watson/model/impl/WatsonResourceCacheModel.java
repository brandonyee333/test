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
import com.liferay.watson.model.WatsonResource;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonResource in entity cache.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonResourceCacheModel
	implements CacheModel<WatsonResource>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonResourceCacheModel)) {
			return false;
		}

		WatsonResourceCacheModel watsonResourceCacheModel =
			(WatsonResourceCacheModel)obj;

		if (watsonResourceId == watsonResourceCacheModel.watsonResourceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonResourceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{watsonResourceId=");
		sb.append(watsonResourceId);
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
	public WatsonResource toEntityModel() {
		WatsonResourceImpl watsonResourceImpl = new WatsonResourceImpl();

		watsonResourceImpl.setWatsonResourceId(watsonResourceId);
		watsonResourceImpl.setGroupId(groupId);
		watsonResourceImpl.setCompanyId(companyId);
		watsonResourceImpl.setUserId(userId);

		if (userName == null) {
			watsonResourceImpl.setUserName("");
		}
		else {
			watsonResourceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonResourceImpl.setCreateDate(null);
		}
		else {
			watsonResourceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonResourceImpl.setModifiedDate(null);
		}
		else {
			watsonResourceImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonResourceImpl.setOriginalWatsonResourceId(
			originalWatsonResourceId);
		watsonResourceImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonResourceImpl.setWatsonIncidentId(watsonIncidentId);

		if (name == null) {
			watsonResourceImpl.setName("");
		}
		else {
			watsonResourceImpl.setName(name);
		}

		if (description == null) {
			watsonResourceImpl.setDescription("");
		}
		else {
			watsonResourceImpl.setDescription(description);
		}

		if (imagePayload == null) {
			watsonResourceImpl.setImagePayload("");
		}
		else {
			watsonResourceImpl.setImagePayload(imagePayload);
		}

		watsonResourceImpl.setStatus(status);

		watsonResourceImpl.resetOriginalValues();

		return watsonResourceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonResourceId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		originalWatsonResourceId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();

		watsonIncidentId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		imagePayload = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(watsonResourceId);

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

	public long watsonResourceId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long originalWatsonResourceId;
	public long typeWatsonListTypeId;
	public long watsonIncidentId;
	public String name;
	public String description;
	public String imagePayload;
	public int status;

}