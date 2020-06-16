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

import com.liferay.osb.testray.model.TestrayTask;

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
 * The cache model class for representing TestrayTask in entity cache.
 *
 * @author Ethan Bustad
 * @see TestrayTask
 * @generated
 */
@ProviderType
public class TestrayTaskCacheModel implements CacheModel<TestrayTask>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayTaskCacheModel)) {
			return false;
		}

		TestrayTaskCacheModel testrayTaskCacheModel = (TestrayTaskCacheModel)obj;

		if (testrayTaskId == testrayTaskCacheModel.testrayTaskId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayTaskId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{testrayTaskId=");
		sb.append(testrayTaskId);
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
		sb.append(", testrayBuildId=");
		sb.append(testrayBuildId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", statusUpdateDate=");
		sb.append(statusUpdateDate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayTask toEntityModel() {
		TestrayTaskImpl testrayTaskImpl = new TestrayTaskImpl();

		testrayTaskImpl.setTestrayTaskId(testrayTaskId);
		testrayTaskImpl.setGroupId(groupId);
		testrayTaskImpl.setCompanyId(companyId);
		testrayTaskImpl.setUserId(userId);

		if (userName == null) {
			testrayTaskImpl.setUserName(StringPool.BLANK);
		}
		else {
			testrayTaskImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayTaskImpl.setCreateDate(null);
		}
		else {
			testrayTaskImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayTaskImpl.setModifiedDate(null);
		}
		else {
			testrayTaskImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayTaskImpl.setTestrayBuildId(testrayBuildId);

		if (name == null) {
			testrayTaskImpl.setName(StringPool.BLANK);
		}
		else {
			testrayTaskImpl.setName(name);
		}

		if (statusUpdateDate == Long.MIN_VALUE) {
			testrayTaskImpl.setStatusUpdateDate(null);
		}
		else {
			testrayTaskImpl.setStatusUpdateDate(new Date(statusUpdateDate));
		}

		testrayTaskImpl.setStatus(status);

		testrayTaskImpl.resetOriginalValues();

		return testrayTaskImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayTaskId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		testrayBuildId = objectInput.readLong();
		name = objectInput.readUTF();
		statusUpdateDate = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(testrayTaskId);

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

		objectOutput.writeLong(testrayBuildId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(statusUpdateDate);

		objectOutput.writeInt(status);
	}

	public long testrayTaskId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayBuildId;
	public String name;
	public long statusUpdateDate;
	public int status;
}