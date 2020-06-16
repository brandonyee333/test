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

package com.liferay.osb.community.meetup.model.impl;

import com.liferay.osb.community.meetup.model.MeetupGroup;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MeetupGroup in entity cache.
 *
 * @author Jamie Sammons
 * @generated
 */
public class MeetupGroupCacheModel
	implements CacheModel<MeetupGroup>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MeetupGroupCacheModel)) {
			return false;
		}

		MeetupGroupCacheModel meetupGroupCacheModel =
			(MeetupGroupCacheModel)obj;

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
			meetupGroupImpl.setName("");
		}
		else {
			meetupGroupImpl.setName(name);
		}

		if (city == null) {
			meetupGroupImpl.setCity("");
		}
		else {
			meetupGroupImpl.setCity(city);
		}

		meetupGroupImpl.setMemberCount(memberCount);

		if (description == null) {
			meetupGroupImpl.setDescription("");
		}
		else {
			meetupGroupImpl.setDescription(description);
		}

		if (url == null) {
			meetupGroupImpl.setUrl("");
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(meetupGroupId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (city == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(city);
		}

		objectOutput.writeInt(memberCount);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (url == null) {
			objectOutput.writeUTF("");
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