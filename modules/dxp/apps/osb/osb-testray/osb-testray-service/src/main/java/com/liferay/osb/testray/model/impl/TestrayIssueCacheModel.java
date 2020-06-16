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

import com.liferay.osb.testray.model.TestrayIssue;

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
 * The cache model class for representing TestrayIssue in entity cache.
 *
 * @author Ethan Bustad
 * @see TestrayIssue
 * @generated
 */
@ProviderType
public class TestrayIssueCacheModel implements CacheModel<TestrayIssue>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayIssueCacheModel)) {
			return false;
		}

		TestrayIssueCacheModel testrayIssueCacheModel = (TestrayIssueCacheModel)obj;

		if (testrayIssueId == testrayIssueCacheModel.testrayIssueId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayIssueId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{testrayIssueId=");
		sb.append(testrayIssueId);
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
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayIssue toEntityModel() {
		TestrayIssueImpl testrayIssueImpl = new TestrayIssueImpl();

		testrayIssueImpl.setTestrayIssueId(testrayIssueId);
		testrayIssueImpl.setGroupId(groupId);
		testrayIssueImpl.setCompanyId(companyId);
		testrayIssueImpl.setUserId(userId);

		if (userName == null) {
			testrayIssueImpl.setUserName(StringPool.BLANK);
		}
		else {
			testrayIssueImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayIssueImpl.setCreateDate(null);
		}
		else {
			testrayIssueImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayIssueImpl.setModifiedDate(null);
		}
		else {
			testrayIssueImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			testrayIssueImpl.setName(StringPool.BLANK);
		}
		else {
			testrayIssueImpl.setName(name);
		}

		testrayIssueImpl.resetOriginalValues();

		return testrayIssueImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayIssueId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(testrayIssueId);

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
	}

	public long testrayIssueId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
}