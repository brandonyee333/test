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

import com.liferay.osb.testray.model.TestrayFactorOption;

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
 * The cache model class for representing TestrayFactorOption in entity cache.
 *
 * @author Ethan Bustad
 * @see TestrayFactorOption
 * @generated
 */
@ProviderType
public class TestrayFactorOptionCacheModel implements CacheModel<TestrayFactorOption>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayFactorOptionCacheModel)) {
			return false;
		}

		TestrayFactorOptionCacheModel testrayFactorOptionCacheModel = (TestrayFactorOptionCacheModel)obj;

		if (testrayFactorOptionId == testrayFactorOptionCacheModel.testrayFactorOptionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayFactorOptionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{testrayFactorOptionId=");
		sb.append(testrayFactorOptionId);
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
		sb.append(", testrayFactorCategoryId=");
		sb.append(testrayFactorCategoryId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayFactorOption toEntityModel() {
		TestrayFactorOptionImpl testrayFactorOptionImpl = new TestrayFactorOptionImpl();

		testrayFactorOptionImpl.setTestrayFactorOptionId(testrayFactorOptionId);
		testrayFactorOptionImpl.setGroupId(groupId);
		testrayFactorOptionImpl.setCompanyId(companyId);
		testrayFactorOptionImpl.setUserId(userId);

		if (userName == null) {
			testrayFactorOptionImpl.setUserName(StringPool.BLANK);
		}
		else {
			testrayFactorOptionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayFactorOptionImpl.setCreateDate(null);
		}
		else {
			testrayFactorOptionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayFactorOptionImpl.setModifiedDate(null);
		}
		else {
			testrayFactorOptionImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayFactorOptionImpl.setTestrayFactorCategoryId(testrayFactorCategoryId);

		if (name == null) {
			testrayFactorOptionImpl.setName(StringPool.BLANK);
		}
		else {
			testrayFactorOptionImpl.setName(name);
		}

		testrayFactorOptionImpl.resetOriginalValues();

		return testrayFactorOptionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayFactorOptionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		testrayFactorCategoryId = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(testrayFactorOptionId);

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

		objectOutput.writeLong(testrayFactorCategoryId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long testrayFactorOptionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayFactorCategoryId;
	public String name;
}