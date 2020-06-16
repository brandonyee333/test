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

import com.liferay.osb.testray.model.TestrayComponent;

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
 * The cache model class for representing TestrayComponent in entity cache.
 *
 * @author Ethan Bustad
 * @see TestrayComponent
 * @generated
 */
@ProviderType
public class TestrayComponentCacheModel implements CacheModel<TestrayComponent>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayComponentCacheModel)) {
			return false;
		}

		TestrayComponentCacheModel testrayComponentCacheModel = (TestrayComponentCacheModel)obj;

		if (testrayComponentId == testrayComponentCacheModel.testrayComponentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayComponentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{testrayComponentId=");
		sb.append(testrayComponentId);
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
		sb.append(", testrayTeamId=");
		sb.append(testrayTeamId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", originationKey=");
		sb.append(originationKey);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayComponent toEntityModel() {
		TestrayComponentImpl testrayComponentImpl = new TestrayComponentImpl();

		testrayComponentImpl.setTestrayComponentId(testrayComponentId);
		testrayComponentImpl.setGroupId(groupId);
		testrayComponentImpl.setCompanyId(companyId);
		testrayComponentImpl.setUserId(userId);

		if (userName == null) {
			testrayComponentImpl.setUserName(StringPool.BLANK);
		}
		else {
			testrayComponentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayComponentImpl.setCreateDate(null);
		}
		else {
			testrayComponentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayComponentImpl.setModifiedDate(null);
		}
		else {
			testrayComponentImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayComponentImpl.setTestrayProjectId(testrayProjectId);
		testrayComponentImpl.setTestrayTeamId(testrayTeamId);

		if (name == null) {
			testrayComponentImpl.setName(StringPool.BLANK);
		}
		else {
			testrayComponentImpl.setName(name);
		}

		testrayComponentImpl.setOriginationKey(originationKey);

		testrayComponentImpl.resetOriginalValues();

		return testrayComponentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayComponentId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		testrayProjectId = objectInput.readLong();

		testrayTeamId = objectInput.readLong();
		name = objectInput.readUTF();

		originationKey = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(testrayComponentId);

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

		objectOutput.writeLong(testrayTeamId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(originationKey);
	}

	public long testrayComponentId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayProjectId;
	public long testrayTeamId;
	public String name;
	public long originationKey;
}