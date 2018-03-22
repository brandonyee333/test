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

import com.liferay.osb.model.OfferingDefinition;

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
 * The cache model class for representing OfferingDefinition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingDefinition
 * @generated
 */
@ProviderType
public class OfferingDefinitionCacheModel implements CacheModel<OfferingDefinition>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfferingDefinitionCacheModel)) {
			return false;
		}

		OfferingDefinitionCacheModel offeringDefinitionCacheModel = (OfferingDefinitionCacheModel)obj;

		if (offeringDefinitionId == offeringDefinitionCacheModel.offeringDefinitionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, offeringDefinitionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{offeringDefinitionId=");
		sb.append(offeringDefinitionId);
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
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", supportResponseId=");
		sb.append(supportResponseId);
		sb.append(", productDescription=");
		sb.append(productDescription);
		sb.append(", licenses=");
		sb.append(licenses);
		sb.append(", unlimitedLicenses=");
		sb.append(unlimitedLicenses);
		sb.append(", maxConcurrentUsers=");
		sb.append(maxConcurrentUsers);
		sb.append(", maxUsers=");
		sb.append(maxUsers);
		sb.append(", supportTickets=");
		sb.append(supportTickets);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OfferingDefinition toEntityModel() {
		OfferingDefinitionImpl offeringDefinitionImpl = new OfferingDefinitionImpl();

		offeringDefinitionImpl.setOfferingDefinitionId(offeringDefinitionId);
		offeringDefinitionImpl.setCompanyId(companyId);
		offeringDefinitionImpl.setUserId(userId);

		if (userName == null) {
			offeringDefinitionImpl.setUserName(StringPool.BLANK);
		}
		else {
			offeringDefinitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			offeringDefinitionImpl.setCreateDate(null);
		}
		else {
			offeringDefinitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			offeringDefinitionImpl.setModifiedDate(null);
		}
		else {
			offeringDefinitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		offeringDefinitionImpl.setProductEntryId(productEntryId);
		offeringDefinitionImpl.setSupportResponseId(supportResponseId);

		if (productDescription == null) {
			offeringDefinitionImpl.setProductDescription(StringPool.BLANK);
		}
		else {
			offeringDefinitionImpl.setProductDescription(productDescription);
		}

		offeringDefinitionImpl.setLicenses(licenses);
		offeringDefinitionImpl.setUnlimitedLicenses(unlimitedLicenses);
		offeringDefinitionImpl.setMaxConcurrentUsers(maxConcurrentUsers);
		offeringDefinitionImpl.setMaxUsers(maxUsers);
		offeringDefinitionImpl.setSupportTickets(supportTickets);

		offeringDefinitionImpl.resetOriginalValues();

		return offeringDefinitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		offeringDefinitionId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		productEntryId = objectInput.readLong();

		supportResponseId = objectInput.readLong();
		productDescription = objectInput.readUTF();

		licenses = objectInput.readBoolean();

		unlimitedLicenses = objectInput.readBoolean();

		maxConcurrentUsers = objectInput.readLong();

		maxUsers = objectInput.readLong();

		supportTickets = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(offeringDefinitionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(productEntryId);

		objectOutput.writeLong(supportResponseId);

		if (productDescription == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(productDescription);
		}

		objectOutput.writeBoolean(licenses);

		objectOutput.writeBoolean(unlimitedLicenses);

		objectOutput.writeLong(maxConcurrentUsers);

		objectOutput.writeLong(maxUsers);

		objectOutput.writeBoolean(supportTickets);
	}

	public long offeringDefinitionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long productEntryId;
	public long supportResponseId;
	public String productDescription;
	public boolean licenses;
	public boolean unlimitedLicenses;
	public long maxConcurrentUsers;
	public long maxUsers;
	public boolean supportTickets;
}