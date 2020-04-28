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

import com.liferay.osb.testray.model.TestrayComponent;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayComponent in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class TestrayComponentCacheModel
	implements CacheModel<TestrayComponent>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayComponentCacheModel)) {
			return false;
		}

		TestrayComponentCacheModel testrayComponentCacheModel =
			(TestrayComponentCacheModel)obj;

		if (testrayComponentId ==
				testrayComponentCacheModel.testrayComponentId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayComponentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{testrayComponentId=");
		sb.append(testrayComponentId);
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
		sb.append(", testrayTeamId=");
		sb.append(testrayTeamId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", originationKey=");
		sb.append(originationKey);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayComponent toEntityModel() {
		TestrayComponentImpl testrayComponentImpl = new TestrayComponentImpl();

		testrayComponentImpl.setTestrayComponentId(testrayComponentId);
		testrayComponentImpl.setGroupId(groupId);
		testrayComponentImpl.setCompanyId(companyId);
		testrayComponentImpl.setUserId(userId);

		if (userName == null) {
			testrayComponentImpl.setUserName("");
		}
		else {
			testrayComponentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayComponentImpl.setCreateDate(null);
		}
		else {
			testrayComponentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayComponentImpl.setModifiedDate(null);
		}
		else {
			testrayComponentImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayComponentImpl.setTestrayProjectId(testrayProjectId);
		testrayComponentImpl.setTestrayTeamId(testrayTeamId);

		if (name == null) {
			testrayComponentImpl.setName("");
		}
		else {
			testrayComponentImpl.setName(name);
		}

		testrayComponentImpl.setOriginationKey(originationKey);

		testrayComponentImpl.resetOriginalValues();

		return testrayComponentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayComponentId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		testrayProjectId = objectInput.readLong();

		testrayTeamId = objectInput.readLong();
		name = objectInput.readUTF();

		originationKey = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayComponentId);

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

		objectOutput.writeLong(testrayTeamId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeLong(originationKey);
	}

	public long testrayComponentId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayProjectId;
	public long testrayTeamId;
	public String name;
	public long originationKey;

}