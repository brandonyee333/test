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

import com.liferay.osb.testray.model.TestrayRequirement;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestrayRequirement in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class TestrayRequirementCacheModel
	implements CacheModel<TestrayRequirement>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayRequirementCacheModel)) {
			return false;
		}

		TestrayRequirementCacheModel testrayRequirementCacheModel =
			(TestrayRequirementCacheModel)obj;

		if (testrayRequirementId ==
				testrayRequirementCacheModel.testrayRequirementId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testrayRequirementId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{testrayRequirementId=");
		sb.append(testrayRequirementId);
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
		sb.append(", testrayComponentId=");
		sb.append(testrayComponentId);
		sb.append(", testrayProjectId=");
		sb.append(testrayProjectId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", summary=");
		sb.append(summary);
		sb.append(", components=");
		sb.append(components);
		sb.append(", linkTitle=");
		sb.append(linkTitle);
		sb.append(", linkURL=");
		sb.append(linkURL);
		sb.append(", description=");
		sb.append(description);
		sb.append(", descriptionType=");
		sb.append(descriptionType);
		sb.append(", goals=");
		sb.append(goals);
		sb.append(", goalsType=");
		sb.append(goalsType);
		sb.append(", variations=");
		sb.append(variations);
		sb.append(", variationsType=");
		sb.append(variationsType);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestrayRequirement toEntityModel() {
		TestrayRequirementImpl testrayRequirementImpl =
			new TestrayRequirementImpl();

		testrayRequirementImpl.setTestrayRequirementId(testrayRequirementId);
		testrayRequirementImpl.setGroupId(groupId);
		testrayRequirementImpl.setCompanyId(companyId);
		testrayRequirementImpl.setUserId(userId);

		if (userName == null) {
			testrayRequirementImpl.setUserName("");
		}
		else {
			testrayRequirementImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			testrayRequirementImpl.setCreateDate(null);
		}
		else {
			testrayRequirementImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testrayRequirementImpl.setModifiedDate(null);
		}
		else {
			testrayRequirementImpl.setModifiedDate(new Date(modifiedDate));
		}

		testrayRequirementImpl.setTestrayComponentId(testrayComponentId);
		testrayRequirementImpl.setTestrayProjectId(testrayProjectId);

		if (key == null) {
			testrayRequirementImpl.setKey("");
		}
		else {
			testrayRequirementImpl.setKey(key);
		}

		if (summary == null) {
			testrayRequirementImpl.setSummary("");
		}
		else {
			testrayRequirementImpl.setSummary(summary);
		}

		if (components == null) {
			testrayRequirementImpl.setComponents("");
		}
		else {
			testrayRequirementImpl.setComponents(components);
		}

		if (linkTitle == null) {
			testrayRequirementImpl.setLinkTitle("");
		}
		else {
			testrayRequirementImpl.setLinkTitle(linkTitle);
		}

		if (linkURL == null) {
			testrayRequirementImpl.setLinkURL("");
		}
		else {
			testrayRequirementImpl.setLinkURL(linkURL);
		}

		if (description == null) {
			testrayRequirementImpl.setDescription("");
		}
		else {
			testrayRequirementImpl.setDescription(description);
		}

		if (descriptionType == null) {
			testrayRequirementImpl.setDescriptionType("");
		}
		else {
			testrayRequirementImpl.setDescriptionType(descriptionType);
		}

		if (goals == null) {
			testrayRequirementImpl.setGoals("");
		}
		else {
			testrayRequirementImpl.setGoals(goals);
		}

		if (goalsType == null) {
			testrayRequirementImpl.setGoalsType("");
		}
		else {
			testrayRequirementImpl.setGoalsType(goalsType);
		}

		if (variations == null) {
			testrayRequirementImpl.setVariations("");
		}
		else {
			testrayRequirementImpl.setVariations(variations);
		}

		if (variationsType == null) {
			testrayRequirementImpl.setVariationsType("");
		}
		else {
			testrayRequirementImpl.setVariationsType(variationsType);
		}

		testrayRequirementImpl.resetOriginalValues();

		return testrayRequirementImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		testrayRequirementId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		testrayComponentId = objectInput.readLong();

		testrayProjectId = objectInput.readLong();
		key = objectInput.readUTF();
		summary = objectInput.readUTF();
		components = objectInput.readUTF();
		linkTitle = objectInput.readUTF();
		linkURL = objectInput.readUTF();
		description = objectInput.readUTF();
		descriptionType = objectInput.readUTF();
		goals = objectInput.readUTF();
		goalsType = objectInput.readUTF();
		variations = objectInput.readUTF();
		variationsType = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(testrayRequirementId);

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

		objectOutput.writeLong(testrayComponentId);

		objectOutput.writeLong(testrayProjectId);

		if (key == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(key);
		}

		if (summary == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(summary);
		}

		if (components == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(components);
		}

		if (linkTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(linkTitle);
		}

		if (linkURL == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(linkURL);
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

		if (goals == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(goals);
		}

		if (goalsType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(goalsType);
		}

		if (variations == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(variations);
		}

		if (variationsType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(variationsType);
		}
	}

	public long testrayRequirementId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long testrayComponentId;
	public long testrayProjectId;
	public String key;
	public String summary;
	public String components;
	public String linkTitle;
	public String linkURL;
	public String description;
	public String descriptionType;
	public String goals;
	public String goalsType;
	public String variations;
	public String variationsType;

}