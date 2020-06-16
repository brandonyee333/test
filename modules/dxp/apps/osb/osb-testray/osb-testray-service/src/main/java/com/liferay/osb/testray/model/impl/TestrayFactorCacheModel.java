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

import com.liferay.osb.testray.model.TestrayFactor;

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
 * The cache model class for representing TestrayFactor in entity cache.
 *
 * @author Ethan Bustad
 * @see TestrayFactor
 * @generated
 */
@ProviderType
public class TestrayFactorCacheModel implements CacheModel<TestrayFactor>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayFactorCacheModel)) {
			return false;
		}

		TestrayFactorCacheModel testrayFactorCacheModel = (TestrayFactorCacheModel)obj;

		if (testrayFactorId == testrayFactorCacheModel.testrayFactorId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayFactorId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{testrayFactorId=");
		sb.append(testrayFactorId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", testrayFactorCategoryId=");
		sb.append(testrayFactorCategoryId);
		sb.append(", testrayFactorCategoryName=");
		sb.append(testrayFactorCategoryName);
		sb.append(", testrayFactorOptionId=");
		sb.append(testrayFactorOptionId);
		sb.append(", testrayFactorOptionName=");
		sb.append(testrayFactorOptionName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayFactor toEntityModel() {
		TestrayFactorImpl testrayFactorImpl = new TestrayFactorImpl();

		testrayFactorImpl.setTestrayFactorId(testrayFactorId);
		testrayFactorImpl.setGroupId(groupId);
		testrayFactorImpl.setCompanyId(companyId);
		testrayFactorImpl.setUserId(userId);

		if (userName == null) {
			testrayFactorImpl.setUserName(StringPool.BLANK);
		}
		else {
			testrayFactorImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayFactorImpl.setCreateDate(null);
		}
		else {
			testrayFactorImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayFactorImpl.setModifiedDate(null);
		}
		else {
			testrayFactorImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayFactorImpl.setClassNameId(classNameId);
		testrayFactorImpl.setClassPK(classPK);
		testrayFactorImpl.setTestrayFactorCategoryId(testrayFactorCategoryId);

		if (testrayFactorCategoryName == null) {
			testrayFactorImpl.setTestrayFactorCategoryName(StringPool.BLANK);
		}
		else {
			testrayFactorImpl.setTestrayFactorCategoryName(testrayFactorCategoryName);
		}

		testrayFactorImpl.setTestrayFactorOptionId(testrayFactorOptionId);

		if (testrayFactorOptionName == null) {
			testrayFactorImpl.setTestrayFactorOptionName(StringPool.BLANK);
		}
		else {
			testrayFactorImpl.setTestrayFactorOptionName(testrayFactorOptionName);
		}

		testrayFactorImpl.resetOriginalValues();

		return testrayFactorImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayFactorId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		testrayFactorCategoryId = objectInput.readLong();
		testrayFactorCategoryName = objectInput.readUTF();

		testrayFactorOptionId = objectInput.readLong();
		testrayFactorOptionName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(testrayFactorId);

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

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(testrayFactorCategoryId);

		if (testrayFactorCategoryName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(testrayFactorCategoryName);
		}

		objectOutput.writeLong(testrayFactorOptionId);

		if (testrayFactorOptionName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(testrayFactorOptionName);
		}
	}

	public long testrayFactorId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public long testrayFactorCategoryId;
	public String testrayFactorCategoryName;
	public long testrayFactorOptionId;
	public String testrayFactorOptionName;
}