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

import com.liferay.osb.testray.model.TestrayProject;

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
 * The cache model class for representing TestrayProject in entity cache.
 *
 * @author Ethan Bustad
 * @see TestrayProject
 * @generated
 */
@ProviderType
public class TestrayProjectCacheModel implements CacheModel<TestrayProject>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayProjectCacheModel)) {
			return false;
		}

		TestrayProjectCacheModel testrayProjectCacheModel = (TestrayProjectCacheModel)obj;

		if (testrayProjectId == testrayProjectCacheModel.testrayProjectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayProjectId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{testrayProjectId=");
		sb.append(testrayProjectId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayProject toEntityModel() {
		TestrayProjectImpl testrayProjectImpl = new TestrayProjectImpl();

		testrayProjectImpl.setTestrayProjectId(testrayProjectId);
		testrayProjectImpl.setGroupId(groupId);
		testrayProjectImpl.setCompanyId(companyId);
		testrayProjectImpl.setUserId(userId);

		if (userName == null) {
			testrayProjectImpl.setUserName(StringPool.BLANK);
		}
		else {
			testrayProjectImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayProjectImpl.setCreateDate(null);
		}
		else {
			testrayProjectImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayProjectImpl.setModifiedDate(null);
		}
		else {
			testrayProjectImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			testrayProjectImpl.setName(StringPool.BLANK);
		}
		else {
			testrayProjectImpl.setName(name);
		}

		if (description == null) {
			testrayProjectImpl.setDescription(StringPool.BLANK);
		}
		else {
			testrayProjectImpl.setDescription(description);
		}

		testrayProjectImpl.resetOriginalValues();

		return testrayProjectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayProjectId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(testrayProjectId);

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

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public long testrayProjectId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
}