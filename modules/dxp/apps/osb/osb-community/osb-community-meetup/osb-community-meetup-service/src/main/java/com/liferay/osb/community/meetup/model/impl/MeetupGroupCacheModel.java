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

package com.liferay.osb.community.meetup.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.meetup.model.MeetupGroup;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MeetupGroup in entity cache.
 *
 * @author Jamie Sammons
 * @see MeetupGroup
 * @generated
 */
@ProviderType
public class MeetupGroupCacheModel implements CacheModel<MeetupGroup>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MeetupGroupCacheModel)) {
			return false;
		}

		MeetupGroupCacheModel meetupGroupCacheModel = (MeetupGroupCacheModel)obj;

		if (meetupGroupId == meetupGroupCacheModel.meetupGroupId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, meetupGroupId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{meetupGroupId=");
		sb.append(meetupGroupId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", city=");
		sb.append(city);
		sb.append(", memberCount=");
		sb.append(memberCount);
		sb.append(", description=");
		sb.append(description);
		sb.append(", url=");
		sb.append(url);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MeetupGroup toEntityModel() {
		MeetupGroupImpl meetupGroupImpl = new MeetupGroupImpl();

		meetupGroupImpl.setMeetupGroupId(meetupGroupId);
		meetupGroupImpl.setGroupId(groupId);
		meetupGroupImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			meetupGroupImpl.setCreateDate(null);
		}
		else {
			meetupGroupImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			meetupGroupImpl.setModifiedDate(null);
		}
		else {
			meetupGroupImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			meetupGroupImpl.setName(StringPool.BLANK);
		}
		else {
			meetupGroupImpl.setName(name);
		}

		if (city == null) {
			meetupGroupImpl.setCity(StringPool.BLANK);
		}
		else {
			meetupGroupImpl.setCity(city);
		}

		meetupGroupImpl.setMemberCount(memberCount);

		if (description == null) {
			meetupGroupImpl.setDescription(StringPool.BLANK);
		}
		else {
			meetupGroupImpl.setDescription(description);
		}

		if (url == null) {
			meetupGroupImpl.setUrl(StringPool.BLANK);
		}
		else {
			meetupGroupImpl.setUrl(url);
		}

		meetupGroupImpl.resetOriginalValues();

		return meetupGroupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		meetupGroupId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		city = objectInput.readUTF();

		memberCount = objectInput.readInt();
		description = objectInput.readUTF();
		url = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(meetupGroupId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (city == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(city);
		}

		objectOutput.writeInt(memberCount);

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}
	}

	public long meetupGroupId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String city;
	public int memberCount;
	public String description;
	public String url;
}