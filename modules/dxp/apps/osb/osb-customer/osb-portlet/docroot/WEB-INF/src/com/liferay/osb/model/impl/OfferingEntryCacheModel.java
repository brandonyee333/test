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

package com.liferay.osb.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.OfferingEntry;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OfferingEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntry
 * @generated
 */
@ProviderType
public class OfferingEntryCacheModel implements CacheModel<OfferingEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfferingEntryCacheModel)) {
			return false;
		}

		OfferingEntryCacheModel offeringEntryCacheModel = (OfferingEntryCacheModel)obj;

		if (offeringEntryId == offeringEntryCacheModel.offeringEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, offeringEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{offeringEntryId=");
		sb.append(offeringEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", orderEntryId=");
		sb.append(orderEntryId);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", supportResponseId=");
		sb.append(supportResponseId);
		sb.append(", productDescription=");
		sb.append(productDescription);
		sb.append(", type=");
		sb.append(type);
		sb.append(", version=");
		sb.append(version);
		sb.append(", platform=");
		sb.append(platform);
		sb.append(", platformVersion=");
		sb.append(platformVersion);
		sb.append(", licenses=");
		sb.append(licenses);
		sb.append(", licenseLifetime=");
		sb.append(licenseLifetime);
		sb.append(", maxConcurrentUsers=");
		sb.append(maxConcurrentUsers);
		sb.append(", maxUsers=");
		sb.append(maxUsers);
		sb.append(", supportTickets=");
		sb.append(supportTickets);
		sb.append(", supportLifetime=");
		sb.append(supportLifetime);
		sb.append(", supportEndDate=");
		sb.append(supportEndDate);
		sb.append(", sizing=");
		sb.append(sizing);
		sb.append(", quantity=");
		sb.append(quantity);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OfferingEntry toEntityModel() {
		OfferingEntryImpl offeringEntryImpl = new OfferingEntryImpl();

		offeringEntryImpl.setOfferingEntryId(offeringEntryId);
		offeringEntryImpl.setUserId(userId);

		if (userName == null) {
			offeringEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			offeringEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			offeringEntryImpl.setCreateDate(null);
		}
		else {
			offeringEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			offeringEntryImpl.setModifiedDate(null);
		}
		else {
			offeringEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		offeringEntryImpl.setAccountEntryId(accountEntryId);
		offeringEntryImpl.setOrderEntryId(orderEntryId);
		offeringEntryImpl.setProductEntryId(productEntryId);
		offeringEntryImpl.setSupportResponseId(supportResponseId);

		if (productDescription == null) {
			offeringEntryImpl.setProductDescription(StringPool.BLANK);
		}
		else {
			offeringEntryImpl.setProductDescription(productDescription);
		}

		offeringEntryImpl.setType(type);
		offeringEntryImpl.setVersion(version);

		if (platform == null) {
			offeringEntryImpl.setPlatform(StringPool.BLANK);
		}
		else {
			offeringEntryImpl.setPlatform(platform);
		}

		if (platformVersion == null) {
			offeringEntryImpl.setPlatformVersion(StringPool.BLANK);
		}
		else {
			offeringEntryImpl.setPlatformVersion(platformVersion);
		}

		offeringEntryImpl.setLicenses(licenses);
		offeringEntryImpl.setLicenseLifetime(licenseLifetime);
		offeringEntryImpl.setMaxConcurrentUsers(maxConcurrentUsers);
		offeringEntryImpl.setMaxUsers(maxUsers);
		offeringEntryImpl.setSupportTickets(supportTickets);
		offeringEntryImpl.setSupportLifetime(supportLifetime);

		if (supportEndDate == Long.MIN_VALUE) {
			offeringEntryImpl.setSupportEndDate(null);
		}
		else {
			offeringEntryImpl.setSupportEndDate(new Date(supportEndDate));
		}

		offeringEntryImpl.setSizing(sizing);
		offeringEntryImpl.setQuantity(quantity);
		offeringEntryImpl.setStatus(status);

		offeringEntryImpl.resetOriginalValues();

		return offeringEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		offeringEntryId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		orderEntryId = objectInput.readLong();

		productEntryId = objectInput.readLong();

		supportResponseId = objectInput.readLong();
		productDescription = objectInput.readUTF();

		type = objectInput.readInt();

		version = objectInput.readInt();
		platform = objectInput.readUTF();
		platformVersion = objectInput.readUTF();

		licenses = objectInput.readBoolean();

		licenseLifetime = objectInput.readLong();

		maxConcurrentUsers = objectInput.readLong();

		maxUsers = objectInput.readLong();

		supportTickets = objectInput.readBoolean();

		supportLifetime = objectInput.readLong();
		supportEndDate = objectInput.readLong();

		sizing = objectInput.readInt();

		quantity = objectInput.readInt();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(offeringEntryId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(orderEntryId);

		objectOutput.writeLong(productEntryId);

		objectOutput.writeLong(supportResponseId);

		if (productDescription == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(productDescription);
		}

		objectOutput.writeInt(type);

		objectOutput.writeInt(version);

		if (platform == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(platform);
		}

		if (platformVersion == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(platformVersion);
		}

		objectOutput.writeBoolean(licenses);

		objectOutput.writeLong(licenseLifetime);

		objectOutput.writeLong(maxConcurrentUsers);

		objectOutput.writeLong(maxUsers);

		objectOutput.writeBoolean(supportTickets);

		objectOutput.writeLong(supportLifetime);
		objectOutput.writeLong(supportEndDate);

		objectOutput.writeInt(sizing);

		objectOutput.writeInt(quantity);

		objectOutput.writeInt(status);
	}

	public long offeringEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountEntryId;
	public long orderEntryId;
	public long productEntryId;
	public long supportResponseId;
	public String productDescription;
	public int type;
	public int version;
	public String platform;
	public String platformVersion;
	public boolean licenses;
	public long licenseLifetime;
	public long maxConcurrentUsers;
	public long maxUsers;
	public boolean supportTickets;
	public long supportLifetime;
	public long supportEndDate;
	public int sizing;
	public int quantity;
	public int status;
}