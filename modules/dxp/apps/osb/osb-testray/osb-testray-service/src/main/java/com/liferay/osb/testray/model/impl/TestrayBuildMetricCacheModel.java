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

import com.liferay.osb.testray.model.TestrayBuildMetric;

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
 * The cache model class for representing TestrayBuildMetric in entity cache.
 *
 * @author Ethan Bustad
 * @see TestrayBuildMetric
 * @generated
 */
@ProviderType
public class TestrayBuildMetricCacheModel implements CacheModel<TestrayBuildMetric>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayBuildMetricCacheModel)) {
			return false;
		}

		TestrayBuildMetricCacheModel testrayBuildMetricCacheModel = (TestrayBuildMetricCacheModel)obj;

		if (testrayBuildMetricId == testrayBuildMetricCacheModel.testrayBuildMetricId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayBuildMetricId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{testrayBuildMetricId=");
		sb.append(testrayBuildMetricId);
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
		sb.append(", testrayCaseTypeId=");
		sb.append(testrayCaseTypeId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", count=");
		sb.append(count);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayBuildMetric toEntityModel() {
		TestrayBuildMetricImpl testrayBuildMetricImpl = new TestrayBuildMetricImpl();

		testrayBuildMetricImpl.setTestrayBuildMetricId(testrayBuildMetricId);
		testrayBuildMetricImpl.setGroupId(groupId);
		testrayBuildMetricImpl.setCompanyId(companyId);
		testrayBuildMetricImpl.setUserId(userId);

		if (userName == null) {
			testrayBuildMetricImpl.setUserName(StringPool.BLANK);
		}
		else {
			testrayBuildMetricImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayBuildMetricImpl.setCreateDate(null);
		}
		else {
			testrayBuildMetricImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayBuildMetricImpl.setModifiedDate(null);
		}
		else {
			testrayBuildMetricImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayBuildMetricImpl.setTestrayBuildId(testrayBuildId);
		testrayBuildMetricImpl.setTestrayCaseTypeId(testrayCaseTypeId);
		testrayBuildMetricImpl.setStatus(status);
		testrayBuildMetricImpl.setCount(count);

		testrayBuildMetricImpl.resetOriginalValues();

		return testrayBuildMetricImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayBuildMetricId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		testrayBuildId = objectInput.readLong();

		testrayCaseTypeId = objectInput.readLong();

		status = objectInput.readInt();

		count = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(testrayBuildMetricId);

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

		objectOutput.writeLong(testrayBuildId);

		objectOutput.writeLong(testrayCaseTypeId);

		objectOutput.writeInt(status);

		objectOutput.writeLong(count);
	}

	public long testrayBuildMetricId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayBuildId;
	public long testrayCaseTypeId;
	public int status;
	public long count;
}