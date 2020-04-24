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
@ProviderType
public class TestrayAssignmentCacheModel
	implements CacheModel<TestrayAssignment>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayAssignmentCacheModel)) {
			return false;
		}

		TestrayAssignmentCacheModel testrayAssignmentCacheModel =
			(TestrayAssignmentCacheModel)obj;

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