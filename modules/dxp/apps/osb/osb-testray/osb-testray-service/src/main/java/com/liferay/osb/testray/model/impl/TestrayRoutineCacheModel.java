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

import com.liferay.osb.testray.model.TestrayRoutine;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayRoutine in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class TestrayRoutineCacheModel
	implements CacheModel<TestrayRoutine>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayRoutineCacheModel)) {
			return false;
		}

		TestrayRoutineCacheModel testrayRoutineCacheModel =
			(TestrayRoutineCacheModel)obj;

		if (testrayRoutineId == testrayRoutineCacheModel.testrayRoutineId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayRoutineId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{testrayRoutineId=");
		sb.append(testrayRoutineId);
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
		sb.append(", autoanalyze=");
		sb.append(autoanalyze);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayRoutine toEntityModel() {
		TestrayRoutineImpl testrayRoutineImpl = new TestrayRoutineImpl();

		testrayRoutineImpl.setTestrayRoutineId(testrayRoutineId);
		testrayRoutineImpl.setGroupId(groupId);
		testrayRoutineImpl.setCompanyId(companyId);
		testrayRoutineImpl.setUserId(userId);

		if (userName == null) {
			testrayRoutineImpl.setUserName("");
		}
		else {
			testrayRoutineImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayRoutineImpl.setCreateDate(null);
		}
		else {
			testrayRoutineImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayRoutineImpl.setModifiedDate(null);
		}
		else {
			testrayRoutineImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayRoutineImpl.setTestrayProjectId(testrayProjectId);

		if (name == null) {
			testrayRoutineImpl.setName("");
		}
		else {
			testrayRoutineImpl.setName(name);
		}

		testrayRoutineImpl.setAutoanalyze(autoanalyze);

		testrayRoutineImpl.resetOriginalValues();

		return testrayRoutineImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayRoutineId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		testrayProjectId = objectInput.readLong();
		name = objectInput.readUTF();

		autoanalyze = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayRoutineId);

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

		objectOutput.writeBoolean(autoanalyze);
	}

	public long testrayRoutineId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayProjectId;
	public String name;
	public boolean autoanalyze;

}