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

import com.liferay.watson.model.WatsonActivityAudit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonActivityAudit in entity cache.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonActivityAuditCacheModel implements CacheModel<WatsonActivityAudit>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonActivityAuditCacheModel)) {
			return false;
		}

		WatsonActivityAuditCacheModel watsonActivityAuditCacheModel = (WatsonActivityAuditCacheModel)obj;

		if (watsonActivityAuditId == watsonActivityAuditCacheModel.watsonActivityAuditId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonActivityAuditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{watsonActivityAuditId=");
		sb.append(watsonActivityAuditId);
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
		sb.append(", watsonActivityId=");
		sb.append(watsonActivityId);
		sb.append(", typeWatsonListTypeId=");
		sb.append(typeWatsonListTypeId);
		sb.append(", subtypeWatsonListTypeId=");
		sb.append(subtypeWatsonListTypeId);
		sb.append(", watsonIncidentId=");
		sb.append(watsonIncidentId);
		sb.append(", narrative=");
		sb.append(narrative);
		sb.append(", reportDate=");
		sb.append(reportDate);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WatsonActivityAudit toEntityModel() {
		WatsonActivityAuditImpl watsonActivityAuditImpl = new WatsonActivityAuditImpl();

		watsonActivityAuditImpl.setWatsonActivityAuditId(watsonActivityAuditId);
		watsonActivityAuditImpl.setGroupId(groupId);
		watsonActivityAuditImpl.setCompanyId(companyId);
		watsonActivityAuditImpl.setUserId(userId);

		if (userName == null) {
			watsonActivityAuditImpl.setUserName("");
		}
		else {
			watsonActivityAuditImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonActivityAuditImpl.setCreateDate(null);
		}
		else {
			watsonActivityAuditImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonActivityAuditImpl.setModifiedDate(null);
		}
		else {
			watsonActivityAuditImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonActivityAuditImpl.setWatsonActivityId(watsonActivityId);
		watsonActivityAuditImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonActivityAuditImpl.setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
		watsonActivityAuditImpl.setWatsonIncidentId(watsonIncidentId);

		if (narrative == null) {
			watsonActivityAuditImpl.setNarrative("");
		}
		else {
			watsonActivityAuditImpl.setNarrative(narrative);
		}

		if (reportDate == Long.MIN_VALUE) {
			watsonActivityAuditImpl.setReportDate(null);
		}
		else {
			watsonActivityAuditImpl.setReportDate(new Date(reportDate));
		}

		if (startDate == Long.MIN_VALUE) {
			watsonActivityAuditImpl.setStartDate(null);
		}
		else {
			watsonActivityAuditImpl.setStartDate(new Date(startDate));
		}

		watsonActivityAuditImpl.setStatus(status);

		watsonActivityAuditImpl.resetOriginalValues();

		return watsonActivityAuditImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonActivityAuditId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		watsonActivityId = objectInput.readLong();

		typeWatsonListTypeId = objectInput.readLong();

		subtypeWatsonListTypeId = objectInput.readLong();

		watsonIncidentId = objectInput.readLong();
		narrative = objectInput.readUTF();
		reportDate = objectInput.readLong();
		startDate = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(watsonActivityAuditId);

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

		objectOutput.writeLong(watsonActivityId);

		objectOutput.writeLong(typeWatsonListTypeId);

		objectOutput.writeLong(subtypeWatsonListTypeId);

		objectOutput.writeLong(watsonIncidentId);

		if (narrative == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(narrative);
		}

		objectOutput.writeLong(reportDate);
		objectOutput.writeLong(startDate);

		objectOutput.writeInt(status);
	}

	public long watsonActivityAuditId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long watsonActivityId;
	public long typeWatsonListTypeId;
	public long subtypeWatsonListTypeId;
	public long watsonIncidentId;
	public String narrative;
	public long reportDate;
	public long startDate;
	public int status;
}