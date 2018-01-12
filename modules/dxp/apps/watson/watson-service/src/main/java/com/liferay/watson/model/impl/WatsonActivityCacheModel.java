/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.watson.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.watson.model.WatsonActivity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WatsonActivity in entity cache.
 *
 * @author Steven Smith
 * @see WatsonActivity
 * @generated
 */
@ProviderType
public class WatsonActivityCacheModel implements CacheModel<WatsonActivity>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonActivityCacheModel)) {
			return false;
		}

		WatsonActivityCacheModel watsonActivityCacheModel = (WatsonActivityCacheModel)obj;

		if (watsonActivityId == watsonActivityCacheModel.watsonActivityId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, watsonActivityId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{watsonActivityId=");
		sb.append(watsonActivityId);
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
	public WatsonActivity toEntityModel() {
		WatsonActivityImpl watsonActivityImpl = new WatsonActivityImpl();

		watsonActivityImpl.setWatsonActivityId(watsonActivityId);
		watsonActivityImpl.setGroupId(groupId);
		watsonActivityImpl.setCompanyId(companyId);
		watsonActivityImpl.setUserId(userId);

		if (userName == null) {
			watsonActivityImpl.setUserName("");
		}
		else {
			watsonActivityImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			watsonActivityImpl.setCreateDate(null);
		}
		else {
			watsonActivityImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			watsonActivityImpl.setModifiedDate(null);
		}
		else {
			watsonActivityImpl.setModifiedDate(new Date(modifiedDate));
		}

		watsonActivityImpl.setTypeWatsonListTypeId(typeWatsonListTypeId);
		watsonActivityImpl.setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
		watsonActivityImpl.setWatsonIncidentId(watsonIncidentId);

		if (narrative == null) {
			watsonActivityImpl.setNarrative("");
		}
		else {
			watsonActivityImpl.setNarrative(narrative);
		}

		if (reportDate == Long.MIN_VALUE) {
			watsonActivityImpl.setReportDate(null);
		}
		else {
			watsonActivityImpl.setReportDate(new Date(reportDate));
		}

		if (startDate == Long.MIN_VALUE) {
			watsonActivityImpl.setStartDate(null);
		}
		else {
			watsonActivityImpl.setStartDate(new Date(startDate));
		}

		watsonActivityImpl.setStatus(status);

		watsonActivityImpl.resetOriginalValues();

		return watsonActivityImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		watsonActivityId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

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
		objectOutput.writeLong(watsonActivityId);

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

	public long watsonActivityId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long typeWatsonListTypeId;
	public long subtypeWatsonListTypeId;
	public long watsonIncidentId;
	public String narrative;
	public long reportDate;
	public long startDate;
	public int status;
}