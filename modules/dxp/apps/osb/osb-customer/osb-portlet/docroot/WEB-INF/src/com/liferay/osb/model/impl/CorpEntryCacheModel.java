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

import com.liferay.osb.model.CorpEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing CorpEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CorpEntry
 * @generated
 */
public class CorpEntryCacheModel implements CacheModel<CorpEntry>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{corpEntryId=");
		sb.append(corpEntryId);
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
		sb.append(", organizationId=");
		sb.append(organizationId);
		sb.append(", logoId=");
		sb.append(logoId);
		sb.append(", addressId=");
		sb.append(addressId);
		sb.append(", contactEmailAddress=");
		sb.append(contactEmailAddress);
		sb.append(", profileEmailAddress=");
		sb.append(profileEmailAddress);
		sb.append(", phoneNumber=");
		sb.append(phoneNumber);
		sb.append(", faxNumber=");
		sb.append(faxNumber);
		sb.append(", website=");
		sb.append(website);
		sb.append(", dossieraAccountKey=");
		sb.append(dossieraAccountKey);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", statusMessage=");
		sb.append(statusMessage);
		sb.append("}");

		return sb.toString();
	}

	public CorpEntry toEntityModel() {
		CorpEntryImpl corpEntryImpl = new CorpEntryImpl();

		corpEntryImpl.setCorpEntryId(corpEntryId);
		corpEntryImpl.setUserId(userId);

		if (userName == null) {
			corpEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			corpEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			corpEntryImpl.setCreateDate(null);
		}
		else {
			corpEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			corpEntryImpl.setModifiedDate(null);
		}
		else {
			corpEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			corpEntryImpl.setName(StringPool.BLANK);
		}
		else {
			corpEntryImpl.setName(name);
		}

		if (description == null) {
			corpEntryImpl.setDescription(StringPool.BLANK);
		}
		else {
			corpEntryImpl.setDescription(description);
		}

		corpEntryImpl.setOrganizationId(organizationId);
		corpEntryImpl.setLogoId(logoId);
		corpEntryImpl.setAddressId(addressId);

		if (contactEmailAddress == null) {
			corpEntryImpl.setContactEmailAddress(StringPool.BLANK);
		}
		else {
			corpEntryImpl.setContactEmailAddress(contactEmailAddress);
		}

		if (profileEmailAddress == null) {
			corpEntryImpl.setProfileEmailAddress(StringPool.BLANK);
		}
		else {
			corpEntryImpl.setProfileEmailAddress(profileEmailAddress);
		}

		if (phoneNumber == null) {
			corpEntryImpl.setPhoneNumber(StringPool.BLANK);
		}
		else {
			corpEntryImpl.setPhoneNumber(phoneNumber);
		}

		if (faxNumber == null) {
			corpEntryImpl.setFaxNumber(StringPool.BLANK);
		}
		else {
			corpEntryImpl.setFaxNumber(faxNumber);
		}

		if (website == null) {
			corpEntryImpl.setWebsite(StringPool.BLANK);
		}
		else {
			corpEntryImpl.setWebsite(website);
		}

		if (dossieraAccountKey == null) {
			corpEntryImpl.setDossieraAccountKey(StringPool.BLANK);
		}
		else {
			corpEntryImpl.setDossieraAccountKey(dossieraAccountKey);
		}

		corpEntryImpl.setStatus(status);
		corpEntryImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			corpEntryImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			corpEntryImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			corpEntryImpl.setStatusDate(null);
		}
		else {
			corpEntryImpl.setStatusDate(new Date(statusDate));
		}

		if (statusMessage == null) {
			corpEntryImpl.setStatusMessage(StringPool.BLANK);
		}
		else {
			corpEntryImpl.setStatusMessage(statusMessage);
		}

		corpEntryImpl.resetOriginalValues();

		return corpEntryImpl;
	}

	public long corpEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public long organizationId;
	public long logoId;
	public long addressId;
	public String contactEmailAddress;
	public String profileEmailAddress;
	public String phoneNumber;
	public String faxNumber;
	public String website;
	public String dossieraAccountKey;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String statusMessage;
}