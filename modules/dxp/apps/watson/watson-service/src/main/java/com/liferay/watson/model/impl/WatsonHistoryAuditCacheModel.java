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
import com.liferay.watson.model.WatsonHistoryAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonHistoryAudit in entity cache.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonHistoryAuditCacheModel
	implements CacheModel<WatsonHistoryAudit>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonHistoryAuditCacheModel)) {
			return false;
		}

		WatsonHistoryAuditCacheModel watsonHistoryAuditCacheModel =
			(WatsonHistoryAuditCacheModel)obj;

		if (watsonHistoryAuditId ==
				watsonHistoryAuditCacheModel.watsonHistoryAuditId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonHistoryAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{watsonHistoryAuditId=");
		sb.append(watsonHistoryAuditId);
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
		sb.append(", watsonHistoryId=");
		sb.append(watsonHistoryId);
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
	public WatsonHistoryAudit toEntityModel() {
		WatsonHistoryAuditImpl watsonHistoryAuditImpl =
			new WatsonHistoryAuditImpl();

		watsonHistoryAuditImpl.setWatsonHistoryAuditId(watsonHistoryAuditId);
		watsonHistoryAuditImpl.setGroupId(groupId);
		watsonHistoryAuditImpl.setCompanyId(companyId);
		watsonHistoryAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonHistoryAuditImpl.setUserName("");
		}
		else {
			watsonHistoryAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonHistoryAuditImpl.setCreateDate(null);
		}
		else {
			watsonHistoryAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonHistoryAuditImpl.setModifiedDate(null);
		}
		else {
			watsonHistoryAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonHistoryAuditImpl.setWatsonHistoryId(watsonHistoryId);
		watsonHistoryAuditImpl.setWatsonParentId(watsonParentId);
		watsonHistoryAuditImpl.setClassNameId(classNameId);
		watsonHistoryAuditImpl.setClassPK(classPK);
		watsonHistoryAuditImpl.setType(type);
		watsonHistoryAuditImpl.setStatus(status);

		watsonHistoryAuditImpl.resetOriginalValues();

		return watsonHistoryAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonHistoryAuditId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		watsonHistoryId = objectInput.readLong();

		watsonParentId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		type = objectInput.readInt();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(watsonHistoryAuditId);

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

		objectOutput.writeLong(watsonHistoryId);

		objectOutput.writeLong(watsonParentId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeInt(type);

		objectOutput.writeInt(status);
	}

	public long watsonHistoryAuditId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long watsonHistoryId;
	public long watsonParentId;
	public long classNameId;
	public long classPK;
	public int type;
	public int status;

}