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

package com.liferay.watson.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.watson.model.WatsonHistory;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonHistory in entity cache.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonHistoryCacheModel implements CacheModel<WatsonHistory>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonHistoryCacheModel)) {
			return false;
		}

		WatsonHistoryCacheModel watsonHistoryCacheModel = (WatsonHistoryCacheModel)obj;

		if (watsonHistoryId == watsonHistoryCacheModel.watsonHistoryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonHistoryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{watsonHistoryId=");
		sb.append(watsonHistoryId);
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
		sb.append(", watsonParentId=");
		sb.append(watsonParentId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonHistory toEntityModel() {
		WatsonHistoryImpl watsonHistoryImpl = new WatsonHistoryImpl();

		watsonHistoryImpl.setWatsonHistoryId(watsonHistoryId);
		watsonHistoryImpl.setGroupId(groupId);
		watsonHistoryImpl.setCompanyId(companyId);
		watsonHistoryImpl.setUserId(userId);

		if (userName == null) {
			watsonHistoryImpl.setUserName("");
		}
		else {
			watsonHistoryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonHistoryImpl.setCreateDate(null);
		}
		else {
			watsonHistoryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonHistoryImpl.setModifiedDate(null);
		}
		else {
			watsonHistoryImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonHistoryImpl.setWatsonParentId(watsonParentId);
		watsonHistoryImpl.setClassNameId(classNameId);
		watsonHistoryImpl.setClassPK(classPK);
		watsonHistoryImpl.setType(type);
		watsonHistoryImpl.setStatus(status);

		watsonHistoryImpl.resetOriginalValues();

		return watsonHistoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonHistoryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		watsonParentId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		type = objectInput.readInt();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonHistoryId);

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

		objectOutput.writeLong(watsonParentId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeInt(type);

		objectOutput.writeInt(status);
	}

	public long watsonHistoryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long watsonParentId;
	public long classNameId;
	public long classPK;
	public int type;
	public int status;
}