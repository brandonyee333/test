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

package com.liferay.osb.testray.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.model.TestrayTask;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayTask in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class TestrayTaskCacheModel
	implements CacheModel<TestrayTask>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayTaskCacheModel)) {
			return false;
		}

		TestrayTaskCacheModel testrayTaskCacheModel =
			(TestrayTaskCacheModel)obj;

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
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusUpdateDate=");
		sb.append(statusUpdateDate);
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
			testrayTaskImpl.setUserName("");
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
			testrayTaskImpl.setName("");
		}
		else {
			testrayTaskImpl.setName(name);
		}

		testrayTaskImpl.setStatus(status);

		if (statusUpdateDate == Long.MIN_VALUE) {
			testrayTaskImpl.setStatusUpdateDate(null);
		}
		else {
			testrayTaskImpl.setStatusUpdateDate(new Date(statusUpdateDate));
		}

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

		status = objectInput.readInt();
		statusUpdateDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayTaskId);

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

		objectOutput.writeLong(testrayBuildId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeInt(status);
		objectOutput.writeLong(statusUpdateDate);
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
	public int status;
	public long statusUpdateDate;

}