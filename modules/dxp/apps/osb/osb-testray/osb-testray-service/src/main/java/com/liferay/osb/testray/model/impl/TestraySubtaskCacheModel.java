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

package com.liferay.osb.testray.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.model.TestraySubtask;

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
 * The cache model class for representing TestraySubtask in entity cache.
 *
 * @author Ethan Bustad
 * @see TestraySubtask
 * @generated
 */
@ProviderType
public class TestraySubtaskCacheModel implements CacheModel<TestraySubtask>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestraySubtaskCacheModel)) {
			return false;
		}

		TestraySubtaskCacheModel testraySubtaskCacheModel = (TestraySubtaskCacheModel)obj;

		if (testraySubtaskId == testraySubtaskCacheModel.testraySubtaskId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testraySubtaskId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{testraySubtaskId=");
		sb.append(testraySubtaskId);
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
		sb.append(", commentMBMessageId=");
		sb.append(commentMBMessageId);
		sb.append(", mergedToTestraySubtaskId=");
		sb.append(mergedToTestraySubtaskId);
		sb.append(", splitFromTestraySubtaskId=");
		sb.append(splitFromTestraySubtaskId);
		sb.append(", testrayTaskId=");
		sb.append(testrayTaskId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", score=");
		sb.append(score);
		sb.append(", statusUpdateDate=");
		sb.append(statusUpdateDate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestraySubtask toEntityModel() {
		TestraySubtaskImpl testraySubtaskImpl = new TestraySubtaskImpl();

		testraySubtaskImpl.setTestraySubtaskId(testraySubtaskId);
		testraySubtaskImpl.setGroupId(groupId);
		testraySubtaskImpl.setCompanyId(companyId);
		testraySubtaskImpl.setUserId(userId);

		if (userName == null) {
			testraySubtaskImpl.setUserName(StringPool.BLANK);
		}
		else {
			testraySubtaskImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testraySubtaskImpl.setCreateDate(null);
		}
		else {
			testraySubtaskImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testraySubtaskImpl.setModifiedDate(null);
		}
		else {
			testraySubtaskImpl.setModifiedDate(new Date(modifiedDate));
		}

		testraySubtaskImpl.setCommentMBMessageId(commentMBMessageId);
		testraySubtaskImpl.setMergedToTestraySubtaskId(mergedToTestraySubtaskId);
		testraySubtaskImpl.setSplitFromTestraySubtaskId(splitFromTestraySubtaskId);
		testraySubtaskImpl.setTestrayTaskId(testrayTaskId);

		if (name == null) {
			testraySubtaskImpl.setName(StringPool.BLANK);
		}
		else {
			testraySubtaskImpl.setName(name);
		}

		testraySubtaskImpl.setScore(score);

		if (statusUpdateDate == Long.MIN_VALUE) {
			testraySubtaskImpl.setStatusUpdateDate(null);
		}
		else {
			testraySubtaskImpl.setStatusUpdateDate(new Date(statusUpdateDate));
		}

		testraySubtaskImpl.setStatus(status);

		testraySubtaskImpl.resetOriginalValues();

		return testraySubtaskImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testraySubtaskId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commentMBMessageId = objectInput.readLong();

		mergedToTestraySubtaskId = objectInput.readLong();

		splitFromTestraySubtaskId = objectInput.readLong();

		testrayTaskId = objectInput.readLong();
		name = objectInput.readUTF();

		score = objectInput.readInt();
		statusUpdateDate = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(testraySubtaskId);

		objectOutput.writeLong(groupId);

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

		objectOutput.writeLong(commentMBMessageId);

		objectOutput.writeLong(mergedToTestraySubtaskId);

		objectOutput.writeLong(splitFromTestraySubtaskId);

		objectOutput.writeLong(testrayTaskId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeInt(score);
		objectOutput.writeLong(statusUpdateDate);

		objectOutput.writeInt(status);
	}

	public long testraySubtaskId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commentMBMessageId;
	public long mergedToTestraySubtaskId;
	public long splitFromTestraySubtaskId;
	public long testrayTaskId;
	public String name;
	public int score;
	public long statusUpdateDate;
	public int status;
}