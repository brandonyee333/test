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

import com.liferay.osb.testray.model.TestrayRoutine;

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
 * The cache model class for representing TestrayRoutine in entity cache.
 *
 * @author Ethan Bustad
 * @see TestrayRoutine
 * @generated
 */
@ProviderType
public class TestrayRoutineCacheModel implements CacheModel<TestrayRoutine>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayRoutineCacheModel)) {
			return false;
		}

		TestrayRoutineCacheModel testrayRoutineCacheModel = (TestrayRoutineCacheModel)obj;

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
			testrayRoutineImpl.setUserName(StringPool.BLANK);
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
			testrayRoutineImpl.setName(StringPool.BLANK);
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
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(testrayRoutineId);

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

		objectOutput.writeLong(testrayProjectId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
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