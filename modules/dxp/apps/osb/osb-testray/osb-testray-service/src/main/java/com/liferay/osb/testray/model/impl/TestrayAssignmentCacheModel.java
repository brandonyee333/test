/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.model.impl;

import com.liferay.osb.testray.model.TestrayAssignment;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayAssignment in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class TestrayAssignmentCacheModel
	implements CacheModel<TestrayAssignment>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TestrayAssignmentCacheModel)) {
			return false;
		}

		TestrayAssignmentCacheModel testrayAssignmentCacheModel =
			(TestrayAssignmentCacheModel)object;

		if (testrayAssignmentId ==
				testrayAssignmentCacheModel.testrayAssignmentId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayAssignmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{testrayAssignmentId=");
		sb.append(testrayAssignmentId);
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
		sb.append(", assignedUserId=");
		sb.append(assignedUserId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayAssignment toEntityModel() {
		TestrayAssignmentImpl testrayAssignmentImpl =
			new TestrayAssignmentImpl();

		testrayAssignmentImpl.setTestrayAssignmentId(testrayAssignmentId);
		testrayAssignmentImpl.setGroupId(groupId);
		testrayAssignmentImpl.setCompanyId(companyId);
		testrayAssignmentImpl.setUserId(userId);

		if (userName == null) {
			testrayAssignmentImpl.setUserName("");
		}
		else {
			testrayAssignmentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayAssignmentImpl.setCreateDate(null);
		}
		else {
			testrayAssignmentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayAssignmentImpl.setModifiedDate(null);
		}
		else {
			testrayAssignmentImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayAssignmentImpl.setAssignedUserId(assignedUserId);
		testrayAssignmentImpl.setClassNameId(classNameId);
		testrayAssignmentImpl.setClassPK(classPK);

		testrayAssignmentImpl.resetOriginalValues();

		return testrayAssignmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayAssignmentId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		assignedUserId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayAssignmentId);

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

		objectOutput.writeLong(assignedUserId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);
	}

	public long testrayAssignmentId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long assignedUserId;
	public long classNameId;
	public long classPK;

}