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

import com.liferay.osb.customer.admin.model.LicenseEntry;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LicenseEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseEntryCacheModel
	implements CacheModel<LicenseEntry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LicenseEntryCacheModel)) {
			return false;
		}

		LicenseEntryCacheModel licenseEntryCacheModel =
			(LicenseEntryCacheModel)obj;

		if (licenseEntryId == licenseEntryCacheModel.licenseEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, licenseEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{licenseEntryId=");
		sb.append(licenseEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", type=");
		sb.append(type);
		sb.append(", portalVersionMin=");
		sb.append(portalVersionMin);
		sb.append(", portalVersionMax=");
		sb.append(portalVersionMax);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LicenseEntry toEntityModel() {
		LicenseEntryImpl licenseEntryImpl = new LicenseEntryImpl();

		licenseEntryImpl.setLicenseEntryId(licenseEntryId);
		licenseEntryImpl.setUserId(userId);

		if (userName == null) {
			licenseEntryImpl.setUserName("");
		}
		else {
			licenseEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			licenseEntryImpl.setCreateDate(null);
		}
		else {
			licenseEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			licenseEntryImpl.setModifiedDate(null);
		}
		else {
			licenseEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		licenseEntryImpl.setProductEntryId(productEntryId);

		if (name == null) {
			licenseEntryImpl.setName("");
		}
		else {
			licenseEntryImpl.setName(name);
		}

		if (type == null) {
			licenseEntryImpl.setType("");
		}
		else {
			licenseEntryImpl.setType(type);
		}

		licenseEntryImpl.setPortalVersionMin(portalVersionMin);
		licenseEntryImpl.setPortalVersionMax(portalVersionMax);

		licenseEntryImpl.resetOriginalValues();

		return licenseEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		licenseEntryId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		productEntryId = objectInput.readLong();
		name = objectInput.readUTF();
		type = objectInput.readUTF();

		portalVersionMin = objectInput.readInt();

		portalVersionMax = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(licenseEntryId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(productEntryId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeInt(portalVersionMin);

		objectOutput.writeInt(portalVersionMax);
	}

	public long licenseEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long productEntryId;
	public String name;
	public String type;
	public int portalVersionMin;
	public int portalVersionMax;

}