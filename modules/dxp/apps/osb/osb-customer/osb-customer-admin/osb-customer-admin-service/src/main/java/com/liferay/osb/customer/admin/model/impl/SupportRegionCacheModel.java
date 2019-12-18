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

package com.liferay.osb.customer.admin.model.impl;

import com.liferay.osb.customer.admin.model.SupportRegion;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SupportRegion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SupportRegionCacheModel
	implements CacheModel<SupportRegion>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportRegionCacheModel)) {
			return false;
		}

		SupportRegionCacheModel supportRegionCacheModel =
			(SupportRegionCacheModel)obj;

		if (supportRegionId == supportRegionCacheModel.supportRegionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, supportRegionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{supportRegionId=");
		sb.append(supportRegionId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", timeZoneId=");
		sb.append(timeZoneId);
		sb.append(", managerUserId=");
		sb.append(managerUserId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SupportRegion toEntityModel() {
		SupportRegionImpl supportRegionImpl = new SupportRegionImpl();

		supportRegionImpl.setSupportRegionId(supportRegionId);
		supportRegionImpl.setCompanyId(companyId);
		supportRegionImpl.setUserId(userId);

		if (userName == null) {
			supportRegionImpl.setUserName("");
		}
		else {
			supportRegionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			supportRegionImpl.setCreateDate(null);
		}
		else {
			supportRegionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			supportRegionImpl.setModifiedDate(null);
		}
		else {
			supportRegionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			supportRegionImpl.setName("");
		}
		else {
			supportRegionImpl.setName(name);
		}

		if (description == null) {
			supportRegionImpl.setDescription("");
		}
		else {
			supportRegionImpl.setDescription(description);
		}

		if (timeZoneId == null) {
			supportRegionImpl.setTimeZoneId("");
		}
		else {
			supportRegionImpl.setTimeZoneId(timeZoneId);
		}

		supportRegionImpl.setManagerUserId(managerUserId);

		supportRegionImpl.resetOriginalValues();

		return supportRegionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		supportRegionId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		timeZoneId = objectInput.readUTF();

		managerUserId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(supportRegionId);

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

		if (timeZoneId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(timeZoneId);
		}

		objectOutput.writeLong(managerUserId);
	}

	public long supportRegionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public String timeZoneId;
	public long managerUserId;

}