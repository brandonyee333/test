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

import com.liferay.osb.model.LicenseKey;

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
 * The cache model class for representing LicenseKey in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKey
 * @generated
 */
@ProviderType
public class LicenseKeyCacheModel implements CacheModel<LicenseKey>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LicenseKeyCacheModel)) {
			return false;
		}

		LicenseKeyCacheModel licenseKeyCacheModel = (LicenseKeyCacheModel)obj;

		if (licenseKeyId == licenseKeyCacheModel.licenseKeyId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, licenseKeyId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(81);

		sb.append("{licenseKeyId=");
		sb.append(licenseKeyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedUserId=");
		sb.append(modifiedUserId);
		sb.append(", modifiedUserName=");
		sb.append(modifiedUserName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", licenseKeySetId=");
		sb.append(licenseKeySetId);
		sb.append(", assetReceiptLicenseId=");
		sb.append(assetReceiptLicenseId);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", orderEntryId=");
		sb.append(orderEntryId);
		sb.append(", offeringEntryId=");
		sb.append(offeringEntryId);
		sb.append(", licenseEntryId=");
		sb.append(licenseEntryId);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", supportResponseId=");
		sb.append(supportResponseId);
		sb.append(", accountEntryName=");
		sb.append(accountEntryName);
		sb.append(", licenseEntryName=");
		sb.append(licenseEntryName);
		sb.append(", licenseEntryType=");
		sb.append(licenseEntryType);
		sb.append(", licenseVersion=");
		sb.append(licenseVersion);
		sb.append(", productEntryName=");
		sb.append(productEntryName);
		sb.append(", productId=");
		sb.append(productId);
		sb.append(", productVersion=");
		sb.append(productVersion);
		sb.append(", productVersionLabel=");
		sb.append(productVersionLabel);
		sb.append(", clusterId=");
		sb.append(clusterId);
		sb.append(", owner=");
		sb.append(owner);
		sb.append(", maxServers=");
		sb.append(maxServers);
		sb.append(", maxConcurrentUsers=");
		sb.append(maxConcurrentUsers);
		sb.append(", maxUsers=");
		sb.append(maxUsers);
		sb.append(", maxHttpSessions=");
		sb.append(maxHttpSessions);
		sb.append(", description=");
		sb.append(description);
		sb.append(", hostName=");
		sb.append(hostName);
		sb.append(", ipAddresses=");
		sb.append(ipAddresses);
		sb.append(", macAddresses=");
		sb.append(macAddresses);
		sb.append(", serverId=");
		sb.append(serverId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", additionalInfo=");
		sb.append(additionalInfo);
		sb.append(", complimentary=");
		sb.append(complimentary);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LicenseKey toEntityModel() {
		LicenseKeyImpl licenseKeyImpl = new LicenseKeyImpl();

		licenseKeyImpl.setLicenseKeyId(licenseKeyId);
		licenseKeyImpl.setUserId(userId);

		if (userName == null) {
			licenseKeyImpl.setUserName(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			licenseKeyImpl.setCreateDate(null);
		}
		else {
			licenseKeyImpl.setCreateDate(new Date(createDate));
		}

		licenseKeyImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			licenseKeyImpl.setModifiedUserName(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			licenseKeyImpl.setModifiedDate(null);
		}
		else {
			licenseKeyImpl.setModifiedDate(new Date(modifiedDate));
		}

		licenseKeyImpl.setLicenseKeySetId(licenseKeySetId);
		licenseKeyImpl.setAssetReceiptLicenseId(assetReceiptLicenseId);
		licenseKeyImpl.setAccountEntryId(accountEntryId);
		licenseKeyImpl.setOrderEntryId(orderEntryId);
		licenseKeyImpl.setOfferingEntryId(offeringEntryId);
		licenseKeyImpl.setLicenseEntryId(licenseEntryId);
		licenseKeyImpl.setProductEntryId(productEntryId);
		licenseKeyImpl.setSupportResponseId(supportResponseId);

		if (accountEntryName == null) {
			licenseKeyImpl.setAccountEntryName(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setAccountEntryName(accountEntryName);
		}

		if (licenseEntryName == null) {
			licenseKeyImpl.setLicenseEntryName(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setLicenseEntryName(licenseEntryName);
		}

		if (licenseEntryType == null) {
			licenseKeyImpl.setLicenseEntryType(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setLicenseEntryType(licenseEntryType);
		}

		licenseKeyImpl.setLicenseVersion(licenseVersion);

		if (productEntryName == null) {
			licenseKeyImpl.setProductEntryName(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setProductEntryName(productEntryName);
		}

		if (productId == null) {
			licenseKeyImpl.setProductId(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setProductId(productId);
		}

		licenseKeyImpl.setProductVersion(productVersion);

		if (productVersionLabel == null) {
			licenseKeyImpl.setProductVersionLabel(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setProductVersionLabel(productVersionLabel);
		}

		licenseKeyImpl.setClusterId(clusterId);

		if (owner == null) {
			licenseKeyImpl.setOwner(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setOwner(owner);
		}

		licenseKeyImpl.setMaxServers(maxServers);
		licenseKeyImpl.setMaxConcurrentUsers(maxConcurrentUsers);
		licenseKeyImpl.setMaxUsers(maxUsers);
		licenseKeyImpl.setMaxHttpSessions(maxHttpSessions);

		if (description == null) {
			licenseKeyImpl.setDescription(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setDescription(description);
		}

		if (hostName == null) {
			licenseKeyImpl.setHostName(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setHostName(hostName);
		}

		if (ipAddresses == null) {
			licenseKeyImpl.setIpAddresses(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setIpAddresses(ipAddresses);
		}

		if (macAddresses == null) {
			licenseKeyImpl.setMacAddresses(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setMacAddresses(macAddresses);
		}

		if (serverId == null) {
			licenseKeyImpl.setServerId(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setServerId(serverId);
		}

		if (key == null) {
			licenseKeyImpl.setKey(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setKey(key);
		}

		if (startDate == Long.MIN_VALUE) {
			licenseKeyImpl.setStartDate(null);
		}
		else {
			licenseKeyImpl.setStartDate(new Date(startDate));
		}

		if (expirationDate == Long.MIN_VALUE) {
			licenseKeyImpl.setExpirationDate(null);
		}
		else {
			licenseKeyImpl.setExpirationDate(new Date(expirationDate));
		}

		if (additionalInfo == null) {
			licenseKeyImpl.setAdditionalInfo(StringPool.BLANK);
		}
		else {
			licenseKeyImpl.setAdditionalInfo(additionalInfo);
		}

		licenseKeyImpl.setComplimentary(complimentary);
		licenseKeyImpl.setActive(active);

		licenseKeyImpl.resetOriginalValues();

		return licenseKeyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		licenseKeyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		modifiedUserId = objectInput.readLong();
		modifiedUserName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		licenseKeySetId = objectInput.readLong();

		assetReceiptLicenseId = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		orderEntryId = objectInput.readLong();

		offeringEntryId = objectInput.readLong();

		licenseEntryId = objectInput.readLong();

		productEntryId = objectInput.readLong();

		supportResponseId = objectInput.readLong();
		accountEntryName = objectInput.readUTF();
		licenseEntryName = objectInput.readUTF();
		licenseEntryType = objectInput.readUTF();

		licenseVersion = objectInput.readInt();
		productEntryName = objectInput.readUTF();
		productId = objectInput.readUTF();

		productVersion = objectInput.readInt();
		productVersionLabel = objectInput.readUTF();

		clusterId = objectInput.readLong();
		owner = objectInput.readUTF();

		maxServers = objectInput.readInt();

		maxConcurrentUsers = objectInput.readLong();

		maxUsers = objectInput.readLong();

		maxHttpSessions = objectInput.readInt();
		description = objectInput.readUTF();
		hostName = objectInput.readUTF();
		ipAddresses = objectInput.readUTF();
		macAddresses = objectInput.readUTF();
		serverId = objectInput.readUTF();
		key = objectInput.readUTF();
		startDate = objectInput.readLong();
		expirationDate = objectInput.readLong();
		additionalInfo = objectInput.readUTF();

		complimentary = objectInput.readBoolean();

		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(licenseKeyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(modifiedUserId);

		if (modifiedUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedUserName);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(licenseKeySetId);

		objectOutput.writeLong(assetReceiptLicenseId);

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(orderEntryId);

		objectOutput.writeLong(offeringEntryId);

		objectOutput.writeLong(licenseEntryId);

		objectOutput.writeLong(productEntryId);

		objectOutput.writeLong(supportResponseId);

		if (accountEntryName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountEntryName);
		}

		if (licenseEntryName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(licenseEntryName);
		}

		if (licenseEntryType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(licenseEntryType);
		}

		objectOutput.writeInt(licenseVersion);

		if (productEntryName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(productEntryName);
		}

		if (productId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(productId);
		}

		objectOutput.writeInt(productVersion);

		if (productVersionLabel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(productVersionLabel);
		}

		objectOutput.writeLong(clusterId);

		if (owner == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(owner);
		}

		objectOutput.writeInt(maxServers);

		objectOutput.writeLong(maxConcurrentUsers);

		objectOutput.writeLong(maxUsers);

		objectOutput.writeInt(maxHttpSessions);

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (hostName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(hostName);
		}

		if (ipAddresses == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ipAddresses);
		}

		if (macAddresses == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(macAddresses);
		}

		if (serverId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(serverId);
		}

		if (key == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(key);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(expirationDate);

		if (additionalInfo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(additionalInfo);
		}

		objectOutput.writeBoolean(complimentary);

		objectOutput.writeBoolean(active);
	}

	public long licenseKeyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public long licenseKeySetId;
	public long assetReceiptLicenseId;
	public long accountEntryId;
	public long orderEntryId;
	public long offeringEntryId;
	public long licenseEntryId;
	public long productEntryId;
	public long supportResponseId;
	public String accountEntryName;
	public String licenseEntryName;
	public String licenseEntryType;
	public int licenseVersion;
	public String productEntryName;
	public String productId;
	public int productVersion;
	public String productVersionLabel;
	public long clusterId;
	public String owner;
	public int maxServers;
	public long maxConcurrentUsers;
	public long maxUsers;
	public int maxHttpSessions;
	public String description;
	public String hostName;
	public String ipAddresses;
	public String macAddresses;
	public String serverId;
	public String key;
	public long startDate;
	public long expirationDate;
	public String additionalInfo;
	public boolean complimentary;
	public boolean active;
}