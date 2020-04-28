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

import com.liferay.osb.testray.model.TestrayProductVersion;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayProductVersion in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class TestrayProductVersionCacheModel
	implements CacheModel<TestrayProductVersion>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayProductVersionCacheModel)) {
			return false;
		}

		TestrayProductVersionCacheModel testrayProductVersionCacheModel =
			(TestrayProductVersionCacheModel)obj;

		if (testrayProductVersionId ==
				testrayProductVersionCacheModel.testrayProductVersionId) {

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
		TestrayProductVersionImpl testrayProductVersionImpl =
			new TestrayProductVersionImpl();

		testrayProductVersionImpl.setTestrayProductVersionId(
			testrayProductVersionId);
		testrayProductVersionImpl.setGroupId(groupId);
		testrayProductVersionImpl.setCompanyId(companyId);
		testrayProductVersionImpl.setUserId(userId);

		if (userName == null) {
			testrayProductVersionImpl.setUserName("");
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
			testrayProductVersionImpl.setName("");
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayProductVersionId);

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