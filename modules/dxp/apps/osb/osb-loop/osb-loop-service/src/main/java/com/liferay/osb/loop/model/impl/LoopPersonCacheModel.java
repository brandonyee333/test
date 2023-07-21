/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.model.impl;

import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LoopPerson in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopPersonCacheModel
	implements CacheModel<LoopPerson>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LoopPersonCacheModel)) {
			return false;
		}

		LoopPersonCacheModel loopPersonCacheModel =
			(LoopPersonCacheModel)object;

		if (loopPersonId == loopPersonCacheModel.loopPersonId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopPersonId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{loopPersonId=");
		sb.append(loopPersonId);
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
		sb.append(", loopJobTitleId=");
		sb.append(loopJobTitleId);
		sb.append(", managerLoopPersonId=");
		sb.append(managerLoopPersonId);
		sb.append(", personUserId=");
		sb.append(personUserId);
		sb.append(", extraData=");
		sb.append(extraData);
		sb.append(", groupedUserNotificationEventsCount=");
		sb.append(groupedUserNotificationEventsCount);
		sb.append(", imagePayload=");
		sb.append(imagePayload);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopPerson toEntityModel() {
		LoopPersonImpl loopPersonImpl = new LoopPersonImpl();

		loopPersonImpl.setLoopPersonId(loopPersonId);
		loopPersonImpl.setCompanyId(companyId);
		loopPersonImpl.setUserId(userId);

		if (userName == null) {
			loopPersonImpl.setUserName("");
		}
		else {
			loopPersonImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			loopPersonImpl.setCreateDate(null);
		}
		else {
			loopPersonImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			loopPersonImpl.setModifiedDate(null);
		}
		else {
			loopPersonImpl.setModifiedDate(new Date(modifiedDate));
		}

		loopPersonImpl.setLoopJobTitleId(loopJobTitleId);
		loopPersonImpl.setManagerLoopPersonId(managerLoopPersonId);
		loopPersonImpl.setPersonUserId(personUserId);

		if (extraData == null) {
			loopPersonImpl.setExtraData("");
		}
		else {
			loopPersonImpl.setExtraData(extraData);
		}

		loopPersonImpl.setGroupedUserNotificationEventsCount(
			groupedUserNotificationEventsCount);

		if (imagePayload == null) {
			loopPersonImpl.setImagePayload("");
		}
		else {
			loopPersonImpl.setImagePayload(imagePayload);
		}

		loopPersonImpl.resetOriginalValues();

		return loopPersonImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopPersonId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		loopJobTitleId = objectInput.readLong();

		managerLoopPersonId = objectInput.readLong();

		personUserId = objectInput.readLong();
		extraData = objectInput.readUTF();

		groupedUserNotificationEventsCount = objectInput.readInt();
		imagePayload = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(loopPersonId);

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

		objectOutput.writeLong(loopJobTitleId);

		objectOutput.writeLong(managerLoopPersonId);

		objectOutput.writeLong(personUserId);

		if (extraData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(extraData);
		}

		objectOutput.writeInt(groupedUserNotificationEventsCount);

		if (imagePayload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imagePayload);
		}
	}

	public long loopPersonId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long loopJobTitleId;
	public long managerLoopPersonId;
	public long personUserId;
	public String extraData;
	public int groupedUserNotificationEventsCount;
	public String imagePayload;

}