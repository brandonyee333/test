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

import com.liferay.osb.testray.model.TestrayProductVersion;

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
 * The cache model class for representing TestrayProductVersion in entity cache.
 *
 * @author Ethan Bustad
 * @see TestrayProductVersion
 * @generated
 */
@ProviderType
public class TestrayProductVersionCacheModel implements CacheModel<TestrayProductVersion>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayProductVersionCacheModel)) {
			return false;
		}

		TestrayProductVersionCacheModel testrayProductVersionCacheModel = (TestrayProductVersionCacheModel)obj;

		if (testrayProductVersionId == testrayProductVersionCacheModel.testrayProductVersionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayProductVersionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{testrayProductVersionId=");
		sb.append(testrayProductVersionId);
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
	public TestrayProductVersion toEntityModel() {
		TestrayProductVersionImpl testrayProductVersionImpl = new TestrayProductVersionImpl();

		testrayProductVersionImpl.setTestrayProductVersionId(testrayProductVersionId);
		testrayProductVersionImpl.setGroupId(groupId);
		testrayProductVersionImpl.setCompanyId(companyId);
		testrayProductVersionImpl.setUserId(userId);

		if (userName == null) {
			testrayProductVersionImpl.setUserName(StringPool.BLANK);
		}
		else {
			testrayProductVersionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayProductVersionImpl.setCreateDate(null);
		}
		else {
			testrayProductVersionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayProductVersionImpl.setModifiedDate(null);
		}
		else {
			testrayProductVersionImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayProductVersionImpl.setTestrayProjectId(testrayProjectId);

		if (name == null) {
			testrayProductVersionImpl.setName(StringPool.BLANK);
		}
		else {
			testrayProductVersionImpl.setName(name);
		}

		testrayProductVersionImpl.resetOriginalValues();

		return testrayProductVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayProductVersionId = objectInput.readLong();

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
		objectOutput.writeLong(testrayProductVersionId);

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

	public long testrayProductVersionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayProjectId;
	public String name;
}