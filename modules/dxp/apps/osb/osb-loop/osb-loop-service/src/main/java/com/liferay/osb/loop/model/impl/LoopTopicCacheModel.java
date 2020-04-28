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

package com.liferay.osb.loop.model.impl;

import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LoopTopic in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopTopicCacheModel
	implements CacheModel<LoopTopic>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopTopicCacheModel)) {
			return false;
		}

		LoopTopicCacheModel loopTopicCacheModel = (LoopTopicCacheModel)obj;

		if (loopTopicId == loopTopicCacheModel.loopTopicId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopTopicId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{loopTopicId=");
		sb.append(loopTopicId);
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
		sb.append(", parentLoopTopicId=");
		sb.append(parentLoopTopicId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", imagePayload=");
		sb.append(imagePayload);
		sb.append(", mergeTime=");
		sb.append(mergeTime);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopTopic toEntityModel() {
		LoopTopicImpl loopTopicImpl = new LoopTopicImpl();

		loopTopicImpl.setLoopTopicId(loopTopicId);
		loopTopicImpl.setCompanyId(companyId);
		loopTopicImpl.setUserId(userId);

		if (userName == null) {
			loopTopicImpl.setUserName("");
		}
		else {
			loopTopicImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			loopTopicImpl.setCreateDate(null);
		}
		else {
			loopTopicImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			loopTopicImpl.setModifiedDate(null);
		}
		else {
			loopTopicImpl.setModifiedDate(new Date(modifiedDate));
		}

		loopTopicImpl.setParentLoopTopicId(parentLoopTopicId);

		if (name == null) {
			loopTopicImpl.setName("");
		}
		else {
			loopTopicImpl.setName(name);
		}

		if (description == null) {
			loopTopicImpl.setDescription("");
		}
		else {
			loopTopicImpl.setDescription(description);
		}

		if (imagePayload == null) {
			loopTopicImpl.setImagePayload("");
		}
		else {
			loopTopicImpl.setImagePayload(imagePayload);
		}

		loopTopicImpl.setMergeTime(mergeTime);

		loopTopicImpl.resetOriginalValues();

		return loopTopicImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopTopicId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		parentLoopTopicId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		imagePayload = objectInput.readUTF();

		mergeTime = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(loopTopicId);

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

		objectOutput.writeLong(parentLoopTopicId);

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

		if (imagePayload == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imagePayload);
		}

		objectOutput.writeLong(mergeTime);
	}

	public long loopTopicId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long parentLoopTopicId;
	public String name;
	public String description;
	public String imagePayload;
	public long mergeTime;

}