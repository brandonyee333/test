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

import com.liferay.osb.model.LCSSubscriptionEntry;

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
 * The cache model class for representing LCSSubscriptionEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LCSSubscriptionEntry
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryCacheModel implements CacheModel<LCSSubscriptionEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSSubscriptionEntryCacheModel)) {
			return false;
		}

		LCSSubscriptionEntryCacheModel lcsSubscriptionEntryCacheModel = (LCSSubscriptionEntryCacheModel)obj;

		if (lcsSubscriptionEntryId == lcsSubscriptionEntryCacheModel.lcsSubscriptionEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lcsSubscriptionEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{lcsSubscriptionEntryId=");
		sb.append(lcsSubscriptionEntryId);
		sb.append(", lcsProjectId=");
		sb.append(lcsProjectId);
		sb.append(", product=");
		sb.append(product);
		sb.append(", productVersion=");
		sb.append(productVersion);
		sb.append(", type=");
		sb.append(type);
		sb.append(", platform=");
		sb.append(platform);
		sb.append(", platformVersion=");
		sb.append(platformVersion);
		sb.append(", serversAllowed=");
		sb.append(serversAllowed);
		sb.append(", serversUsed=");
		sb.append(serversUsed);
		sb.append(", instanceSize=");
		sb.append(instanceSize);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", supportStartDate=");
		sb.append(supportStartDate);
		sb.append(", supportEndDate=");
		sb.append(supportEndDate);
		sb.append(", actualPrice=");
		sb.append(actualPrice);
		sb.append(", currencyCode=");
		sb.append(currencyCode);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LCSSubscriptionEntry toEntityModel() {
		LCSSubscriptionEntryImpl lcsSubscriptionEntryImpl = new LCSSubscriptionEntryImpl();

		lcsSubscriptionEntryImpl.setLcsSubscriptionEntryId(lcsSubscriptionEntryId);
		lcsSubscriptionEntryImpl.setLcsProjectId(lcsProjectId);

		if (product == null) {
			lcsSubscriptionEntryImpl.setProduct(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setProduct(product);
		}

		lcsSubscriptionEntryImpl.setProductVersion(productVersion);

		if (type == null) {
			lcsSubscriptionEntryImpl.setType(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setType(type);
		}

		if (platform == null) {
			lcsSubscriptionEntryImpl.setPlatform(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setPlatform(platform);
		}

		if (platformVersion == null) {
			lcsSubscriptionEntryImpl.setPlatformVersion(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setPlatformVersion(platformVersion);
		}

		lcsSubscriptionEntryImpl.setServersAllowed(serversAllowed);
		lcsSubscriptionEntryImpl.setServersUsed(serversUsed);
		lcsSubscriptionEntryImpl.setInstanceSize(instanceSize);

		if (startDate == Long.MIN_VALUE) {
			lcsSubscriptionEntryImpl.setStartDate(null);
		}
		else {
			lcsSubscriptionEntryImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			lcsSubscriptionEntryImpl.setEndDate(null);
		}
		else {
			lcsSubscriptionEntryImpl.setEndDate(new Date(endDate));
		}

		if (supportStartDate == Long.MIN_VALUE) {
			lcsSubscriptionEntryImpl.setSupportStartDate(null);
		}
		else {
			lcsSubscriptionEntryImpl.setSupportStartDate(new Date(
					supportStartDate));
		}

		if (supportEndDate == Long.MIN_VALUE) {
			lcsSubscriptionEntryImpl.setSupportEndDate(null);
		}
		else {
			lcsSubscriptionEntryImpl.setSupportEndDate(new Date(supportEndDate));
		}

		lcsSubscriptionEntryImpl.setActualPrice(actualPrice);

		if (currencyCode == null) {
			lcsSubscriptionEntryImpl.setCurrencyCode(StringPool.BLANK);
		}
		else {
			lcsSubscriptionEntryImpl.setCurrencyCode(currencyCode);
		}

		lcsSubscriptionEntryImpl.setActive(active);

		lcsSubscriptionEntryImpl.resetOriginalValues();

		return lcsSubscriptionEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lcsSubscriptionEntryId = objectInput.readLong();

		lcsProjectId = objectInput.readLong();
		product = objectInput.readUTF();

		productVersion = objectInput.readInt();
		type = objectInput.readUTF();
		platform = objectInput.readUTF();
		platformVersion = objectInput.readUTF();

		serversAllowed = objectInput.readInt();

		serversUsed = objectInput.readInt();

		instanceSize = objectInput.readInt();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		supportStartDate = objectInput.readLong();
		supportEndDate = objectInput.readLong();

		actualPrice = objectInput.readDouble();
		currencyCode = objectInput.readUTF();

		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lcsSubscriptionEntryId);

		objectOutput.writeLong(lcsProjectId);

		if (product == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(product);
		}

		objectOutput.writeInt(productVersion);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

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

		objectOutput.writeInt(serversAllowed);

		objectOutput.writeInt(serversUsed);

		objectOutput.writeInt(instanceSize);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);
		objectOutput.writeLong(supportStartDate);
		objectOutput.writeLong(supportEndDate);

		objectOutput.writeDouble(actualPrice);

		if (currencyCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(currencyCode);
		}

		objectOutput.writeBoolean(active);
	}

	public long lcsSubscriptionEntryId;
	public long lcsProjectId;
	public String product;
	public int productVersion;
	public String type;
	public String platform;
	public String platformVersion;
	public int serversAllowed;
	public int serversUsed;
	public int instanceSize;
	public long startDate;
	public long endDate;
	public long supportStartDate;
	public long supportEndDate;
	public double actualPrice;
	public String currencyCode;
	public boolean active;
}