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

import com.liferay.osb.testray.model.TestrayFactorOption;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayFactorOption in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class TestrayFactorOptionCacheModel
	implements CacheModel<TestrayFactorOption>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayFactorOptionCacheModel)) {
			return false;
		}

		TestrayFactorOptionCacheModel testrayFactorOptionCacheModel =
			(TestrayFactorOptionCacheModel)obj;

		if (testrayFactorOptionId ==
				testrayFactorOptionCacheModel.testrayFactorOptionId) {

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
		TestrayFactorOptionImpl testrayFactorOptionImpl =
			new TestrayFactorOptionImpl();

		testrayFactorOptionImpl.setTestrayFactorOptionId(testrayFactorOptionId);
		testrayFactorOptionImpl.setGroupId(groupId);
		testrayFactorOptionImpl.setCompanyId(companyId);
		testrayFactorOptionImpl.setUserId(userId);

		if (userName == null) {
			testrayFactorOptionImpl.setUserName("");
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

		testrayFactorOptionImpl.setTestrayFactorCategoryId(
			testrayFactorCategoryId);

		if (name == null) {
			testrayFactorOptionImpl.setName("");
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayFactorOptionId);

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

		objectOutput.writeLong(testrayFactorCategoryId);

		if (name == null) {
			objectOutput.writeUTF("");
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