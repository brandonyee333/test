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
import com.liferay.portal.kernel.util.StringPool;

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
 * @see WatsonResource
 * @generated
 */
@ProviderType
public class WatsonResourceCacheModel implements CacheModel<WatsonResource>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonResourceCacheModel)) {
			return false;
		}

		WatsonResourceCacheModel watsonResourceCacheModel = (WatsonResourceCacheModel)obj;

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
		StringBundler sb = new StringBundler(27);

		sb.append("{watsonResourceId=");
		sb.append(watsonResourceId);
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
		watsonResourceImpl.setCompanyId(companyId);
		watsonResourceImpl.setUserId(userId);

		if (userName == null) {
			watsonResourceImpl.setUserName(StringPool.BLANK);
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

		watsonResourceImpl.setOriginalWatsonResourceId(originalWatsonResourceId);
		watsonResourceImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonResourceImpl.setWatsonIncidentId(watsonIncidentId);

		if (name == null) {
			watsonResourceImpl.setName(StringPool.BLANK);
		}
		else {
			watsonResourceImpl.setName(name);
		}

		if (description == null) {
			watsonResourceImpl.setDescription(StringPool.BLANK);
		}
		else {
			watsonResourceImpl.setDescription(description);
		}

		if (imagePayload == null) {
			watsonResourceImpl.setImagePayload(StringPool.BLANK);
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
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonResourceId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
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
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (imagePayload == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(imagePayload);
		}

		objectOutput.writeInt(status);
	}

	public long watsonResourceId;
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