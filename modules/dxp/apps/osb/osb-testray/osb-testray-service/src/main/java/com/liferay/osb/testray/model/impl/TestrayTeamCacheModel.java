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

import com.liferay.osb.testray.model.TestrayTeam;

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
 * The cache model class for representing TestrayTeam in entity cache.
 *
 * @author Ethan Bustad
 * @see TestrayTeam
 * @generated
 */
@ProviderType
public class TestrayTeamCacheModel implements CacheModel<TestrayTeam>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayTeamCacheModel)) {
			return false;
		}

		TestrayTeamCacheModel testrayTeamCacheModel = (TestrayTeamCacheModel)obj;

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
			testrayTeamImpl.setUserName(StringPool.BLANK);
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
			testrayTeamImpl.setName(StringPool.BLANK);
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
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(testrayTeamId);

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