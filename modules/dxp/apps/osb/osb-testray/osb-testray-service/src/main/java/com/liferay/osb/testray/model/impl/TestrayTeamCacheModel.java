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

import com.liferay.osb.testray.model.TestrayTeam;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayTeam in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class TestrayTeamCacheModel
	implements CacheModel<TestrayTeam>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayTeamCacheModel)) {
			return false;
		}

		TestrayTeamCacheModel testrayTeamCacheModel =
			(TestrayTeamCacheModel)obj;

		if (testrayTeamId == testrayTeamCacheModel.testrayTeamId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayTeamId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{testrayTeamId=");
		sb.append(testrayTeamId);
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
		sb.append(", testrayProjectId=");
		sb.append(testrayProjectId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayTeam toEntityModel() {
		TestrayTeamImpl testrayTeamImpl = new TestrayTeamImpl();

		testrayTeamImpl.setTestrayTeamId(testrayTeamId);
		testrayTeamImpl.setGroupId(groupId);
		testrayTeamImpl.setCompanyId(companyId);
		testrayTeamImpl.setUserId(userId);

		if (userName == null) {
			testrayTeamImpl.setUserName("");
		}
		else {
			testrayTeamImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayTeamImpl.setCreateDate(null);
		}
		else {
			testrayTeamImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayTeamImpl.setModifiedDate(null);
		}
		else {
			testrayTeamImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayTeamImpl.setTestrayProjectId(testrayProjectId);

		if (name == null) {
			testrayTeamImpl.setName("");
		}
		else {
			testrayTeamImpl.setName(name);
		}

		testrayTeamImpl.resetOriginalValues();

		return testrayTeamImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayTeamId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		testrayProjectId = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayTeamId);

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

		objectOutput.writeLong(testrayProjectId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long testrayTeamId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayProjectId;
	public String name;

}