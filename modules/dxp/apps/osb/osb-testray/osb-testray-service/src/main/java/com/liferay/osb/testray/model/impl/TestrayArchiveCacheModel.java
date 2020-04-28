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

import com.liferay.osb.testray.model.TestrayArchive;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayArchive in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class TestrayArchiveCacheModel
	implements CacheModel<TestrayArchive>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayArchiveCacheModel)) {
			return false;
		}

		TestrayArchiveCacheModel testrayArchiveCacheModel =
			(TestrayArchiveCacheModel)obj;

		if (testrayArchiveId == testrayArchiveCacheModel.testrayArchiveId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayArchiveId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{testrayArchiveId=");
		sb.append(testrayArchiveId);
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

		return sb.toString();
	}

	@Override
	public TestrayArchive toEntityModel() {
		TestrayArchiveImpl testrayArchiveImpl = new TestrayArchiveImpl();

		testrayArchiveImpl.setTestrayArchiveId(testrayArchiveId);
		testrayArchiveImpl.setGroupId(groupId);
		testrayArchiveImpl.setCompanyId(companyId);
		testrayArchiveImpl.setUserId(userId);

		if (userName == null) {
			testrayArchiveImpl.setUserName("");
		}
		else {
			testrayArchiveImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayArchiveImpl.setCreateDate(null);
		}
		else {
			testrayArchiveImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayArchiveImpl.setModifiedDate(null);
		}
		else {
			testrayArchiveImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayArchiveImpl.setClassNameId(classNameId);
		testrayArchiveImpl.setClassPK(classPK);

		testrayArchiveImpl.resetOriginalValues();

		return testrayArchiveImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayArchiveId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayArchiveId);

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

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);
	}

	public long testrayArchiveId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;

}