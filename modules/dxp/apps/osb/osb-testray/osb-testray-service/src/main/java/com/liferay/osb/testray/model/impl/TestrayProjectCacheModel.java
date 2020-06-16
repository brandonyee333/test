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

import com.liferay.osb.testray.model.TestrayProject;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayProject in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class TestrayProjectCacheModel
	implements CacheModel<TestrayProject>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayProjectCacheModel)) {
			return false;
		}

		TestrayProjectCacheModel testrayProjectCacheModel =
			(TestrayProjectCacheModel)obj;

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
			testrayProjectImpl.setUserName("");
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
			testrayProjectImpl.setName("");
		}
		else {
			testrayProjectImpl.setName(name);
		}

		if (description == null) {
			testrayProjectImpl.setDescription("");
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayProjectId);

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

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
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