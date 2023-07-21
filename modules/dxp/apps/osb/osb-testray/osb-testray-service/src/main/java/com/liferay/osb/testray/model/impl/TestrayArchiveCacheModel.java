/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TestrayArchiveCacheModel)) {
			return false;
		}

		TestrayArchiveCacheModel testrayArchiveCacheModel =
			(TestrayArchiveCacheModel)object;

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