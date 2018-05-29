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

import com.liferay.watson.model.WatsonRelationshipAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonRelationshipAudit in entity cache.
 *
 * @author Steven Smith
 * @see WatsonRelationshipAudit
 * @generated
 */
@ProviderType
public class WatsonRelationshipAuditCacheModel implements CacheModel<WatsonRelationshipAudit>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonRelationshipAuditCacheModel)) {
			return false;
		}

		WatsonRelationshipAuditCacheModel watsonRelationshipAuditCacheModel = (WatsonRelationshipAuditCacheModel)obj;

		if (watsonRelationshipAuditId == watsonRelationshipAuditCacheModel.watsonRelationshipAuditId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonRelationshipAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{watsonRelationshipAuditId=");
		sb.append(watsonRelationshipAuditId);
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
		sb.append(", watsonIncidentId=");
		sb.append(watsonIncidentId);
		sb.append(", watsonRelationshipId=");
		sb.append(watsonRelationshipId);
		sb.append(", typeWatsonListTypeId=");
		sb.append(typeWatsonListTypeId);
		sb.append(", classNameId1=");
		sb.append(classNameId1);
		sb.append(", classPK1=");
		sb.append(classPK1);
		sb.append(", classNameId2=");
		sb.append(classNameId2);
		sb.append(", classPK2=");
		sb.append(classPK2);
		sb.append(", description=");
		sb.append(description);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonRelationshipAudit toEntityModel() {
		WatsonRelationshipAuditImpl watsonRelationshipAuditImpl = new WatsonRelationshipAuditImpl();

		watsonRelationshipAuditImpl.setWatsonRelationshipAuditId(watsonRelationshipAuditId);
		watsonRelationshipAuditImpl.setGroupId(groupId);
		watsonRelationshipAuditImpl.setCompanyId(companyId);
		watsonRelationshipAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonRelationshipAuditImpl.setUserName("");
		}
		else {
			watsonRelationshipAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonRelationshipAuditImpl.setCreateDate(null);
		}
		else {
			watsonRelationshipAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonRelationshipAuditImpl.setModifiedDate(null);
		}
		else {
			watsonRelationshipAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonRelationshipAuditImpl.setWatsonIncidentId(watsonIncidentId);
		watsonRelationshipAuditImpl.setWatsonRelationshipId(watsonRelationshipId);
		watsonRelationshipAuditImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonRelationshipAuditImpl.setClassNameId1(classNameId1);
		watsonRelationshipAuditImpl.setClassPK1(classPK1);
		watsonRelationshipAuditImpl.setClassNameId2(classNameId2);
		watsonRelationshipAuditImpl.setClassPK2(classPK2);

		if (description == null) {
			watsonRelationshipAuditImpl.setDescription("");
		}
		else {
			watsonRelationshipAuditImpl.setDescription(description);
		}

		watsonRelationshipAuditImpl.setStatus(status);

		watsonRelationshipAuditImpl.resetOriginalValues();

		return watsonRelationshipAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonRelationshipAuditId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		watsonIncidentId = objectInput.readLong();

		watsonRelationshipId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();

		classNameId1 = objectInput.readLong();

		classPK1 = objectInput.readLong();

		classNameId2 = objectInput.readLong();

		classPK2 = objectInput.readLong();
		description = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonRelationshipAuditId);

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

		objectOutput.writeLong(watsonIncidentId);

		objectOutput.writeLong(watsonRelationshipId);

		objectOutput.writeLong(typeWatsonListTypeId);

		objectOutput.writeLong(classNameId1);

		objectOutput.writeLong(classPK1);

		objectOutput.writeLong(classNameId2);

		objectOutput.writeLong(classPK2);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(status);
	}

	public long watsonRelationshipAuditId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long watsonIncidentId;
	public long watsonRelationshipId;
	public long typeWatsonListTypeId;
	public long classNameId1;
	public long classPK1;
	public long classNameId2;
	public long classPK2;
	public String description;
	public int status;
}