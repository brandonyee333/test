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

import com.liferay.osb.model.OfferingDefinition;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing OfferingDefinition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingDefinition
 * @generated
 */
public class OfferingDefinitionCacheModel implements CacheModel<OfferingDefinition>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{offeringDefinitionId=");
		sb.append(offeringDefinitionId);
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

	public OfferingDefinition toEntityModel() {
		OfferingDefinitionImpl offeringDefinitionImpl = new OfferingDefinitionImpl();

		offeringDefinitionImpl.setOfferingDefinitionId(offeringDefinitionId);
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

	public long offeringDefinitionId;
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