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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.testray.model.TestrayFactorCategory;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayFactorCategory in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class TestrayFactorCategoryCacheModel
	implements CacheModel<TestrayFactorCategory>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayFactorCategoryCacheModel)) {
			return false;
		}

		TestrayFactorCategoryCacheModel testrayFactorCategoryCacheModel =
			(TestrayFactorCategoryCacheModel)obj;

		if (testrayFactorCategoryId ==
				testrayFactorCategoryCacheModel.testrayFactorCategoryId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayFactorCategoryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{testrayFactorCategoryId=");
		sb.append(testrayFactorCategoryId);
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
	public TestrayFactorCategory toEntityModel() {
		TestrayFactorCategoryImpl testrayFactorCategoryImpl =
			new TestrayFactorCategoryImpl();

		testrayFactorCategoryImpl.setTestrayFactorCategoryId(
			testrayFactorCategoryId);
		testrayFactorCategoryImpl.setGroupId(groupId);
		testrayFactorCategoryImpl.setCompanyId(companyId);
		testrayFactorCategoryImpl.setUserId(userId);

		if (userName == null) {
			testrayFactorCategoryImpl.setUserName("");
		}
		else {
			testrayFactorCategoryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayFactorCategoryImpl.setCreateDate(null);
		}
		else {
			testrayFactorCategoryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayFactorCategoryImpl.setModifiedDate(null);
		}
		else {
			testrayFactorCategoryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			testrayFactorCategoryImpl.setName("");
		}
		else {
			testrayFactorCategoryImpl.setName(name);
		}

		testrayFactorCategoryImpl.resetOriginalValues();

		return testrayFactorCategoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayFactorCategoryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayFactorCategoryId);

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
	}

	public long testrayFactorCategoryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;

}