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

import com.liferay.osb.testray.model.TestrayCase;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayCase in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class TestrayCaseCacheModel
	implements CacheModel<TestrayCase>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayCaseCacheModel)) {
			return false;
		}

		TestrayCaseCacheModel testrayCaseCacheModel =
			(TestrayCaseCacheModel)obj;

		if (testrayCaseId == testrayCaseCacheModel.testrayCaseId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayCaseId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{testrayCaseId=");
		sb.append(testrayCaseId);
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
		sb.append(", testrayCaseTypeId=");
		sb.append(testrayCaseTypeId);
		sb.append(", testrayComponentId=");
		sb.append(testrayComponentId);
		sb.append(", testrayProjectId=");
		sb.append(testrayProjectId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", descriptionType=");
		sb.append(descriptionType);
		sb.append(", originationKey=");
		sb.append(originationKey);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", estimatedDuration=");
		sb.append(estimatedDuration);
		sb.append(", steps=");
		sb.append(steps);
		sb.append(", stepsType=");
		sb.append(stepsType);
		sb.append(", caseNumber=");
		sb.append(caseNumber);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayCase toEntityModel() {
		TestrayCaseImpl testrayCaseImpl = new TestrayCaseImpl();

		testrayCaseImpl.setTestrayCaseId(testrayCaseId);
		testrayCaseImpl.setGroupId(groupId);
		testrayCaseImpl.setCompanyId(companyId);
		testrayCaseImpl.setUserId(userId);

		if (userName == null) {
			testrayCaseImpl.setUserName("");
		}
		else {
			testrayCaseImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayCaseImpl.setCreateDate(null);
		}
		else {
			testrayCaseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayCaseImpl.setModifiedDate(null);
		}
		else {
			testrayCaseImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayCaseImpl.setTestrayCaseTypeId(testrayCaseTypeId);
		testrayCaseImpl.setTestrayComponentId(testrayComponentId);
		testrayCaseImpl.setTestrayProjectId(testrayProjectId);

		if (name == null) {
			testrayCaseImpl.setName("");
		}
		else {
			testrayCaseImpl.setName(name);
		}

		if (description == null) {
			testrayCaseImpl.setDescription("");
		}
		else {
			testrayCaseImpl.setDescription(description);
		}

		if (descriptionType == null) {
			testrayCaseImpl.setDescriptionType("");
		}
		else {
			testrayCaseImpl.setDescriptionType(descriptionType);
		}

		testrayCaseImpl.setOriginationKey(originationKey);
		testrayCaseImpl.setPriority(priority);
		testrayCaseImpl.setEstimatedDuration(estimatedDuration);

		if (steps == null) {
			testrayCaseImpl.setSteps("");
		}
		else {
			testrayCaseImpl.setSteps(steps);
		}

		if (stepsType == null) {
			testrayCaseImpl.setStepsType("");
		}
		else {
			testrayCaseImpl.setStepsType(stepsType);
		}

		testrayCaseImpl.setCaseNumber(caseNumber);

		testrayCaseImpl.resetOriginalValues();

		return testrayCaseImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayCaseId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		testrayCaseTypeId = objectInput.readLong();

		testrayComponentId = objectInput.readLong();

		testrayProjectId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		descriptionType = objectInput.readUTF();

		originationKey = objectInput.readLong();

		priority = objectInput.readInt();

		estimatedDuration = objectInput.readInt();
		steps = objectInput.readUTF();
		stepsType = objectInput.readUTF();

		caseNumber = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayCaseId);

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

		objectOutput.writeLong(testrayCaseTypeId);

		objectOutput.writeLong(testrayComponentId);

		objectOutput.writeLong(testrayProjectId);

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

		if (descriptionType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(descriptionType);
		}

		objectOutput.writeLong(originationKey);

		objectOutput.writeInt(priority);

		objectOutput.writeInt(estimatedDuration);

		if (steps == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(steps);
		}

		if (stepsType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stepsType);
		}

		objectOutput.writeLong(caseNumber);
	}

	public long testrayCaseId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayCaseTypeId;
	public long testrayComponentId;
	public long testrayProjectId;
	public String name;
	public String description;
	public String descriptionType;
	public long originationKey;
	public int priority;
	public int estimatedDuration;
	public String steps;
	public String stepsType;
	public long caseNumber;

}