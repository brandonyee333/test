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

import com.liferay.osb.model.AccountEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AccountEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntry
 * @generated
 */
public class AccountEntryCacheModel implements CacheModel<AccountEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(57);

		sb.append("{accountEntryId=");
		sb.append(accountEntryId);
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
		sb.append(", corpProjectId=");
		sb.append(corpProjectId);
		sb.append(", corpEntryName=");
		sb.append(corpEntryName);
		sb.append(", name=");
		sb.append(name);
		sb.append(", code=");
		sb.append(code);
		sb.append(", redirectAccountEntryId=");
		sb.append(redirectAccountEntryId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", industry=");
		sb.append(industry);
		sb.append(", countryId=");
		sb.append(countryId);
		sb.append(", partnerEntryId=");
		sb.append(partnerEntryId);
		sb.append(", partnerManagedSupport=");
		sb.append(partnerManagedSupport);
		sb.append(", tier=");
		sb.append(tier);
		sb.append(", maxCustomers=");
		sb.append(maxCustomers);
		sb.append(", instructions=");
		sb.append(instructions);
		sb.append(", notes=");
		sb.append(notes);
		sb.append(", highestSupportResponseId=");
		sb.append(highestSupportResponseId);
		sb.append(", lastAuditDate=");
		sb.append(lastAuditDate);
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

	public AccountEntry toEntityModel() {
		AccountEntryImpl accountEntryImpl = new AccountEntryImpl();

		accountEntryImpl.setAccountEntryId(accountEntryId);
		accountEntryImpl.setUserId(userId);

		if (userName == null) {
			accountEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			accountEntryImpl.setCreateDate(null);
		}
		else {
			accountEntryImpl.setCreateDate(new Date(createDate));
		}

		accountEntryImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			accountEntryImpl.setModifiedUserName(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountEntryImpl.setModifiedDate(null);
		}
		else {
			accountEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		accountEntryImpl.setCorpProjectId(corpProjectId);

		if (corpEntryName == null) {
			accountEntryImpl.setCorpEntryName(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setCorpEntryName(corpEntryName);
		}

		if (name == null) {
			accountEntryImpl.setName(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setName(name);
		}

		if (code == null) {
			accountEntryImpl.setCode(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setCode(code);
		}

		accountEntryImpl.setRedirectAccountEntryId(redirectAccountEntryId);
		accountEntryImpl.setType(type);
		accountEntryImpl.setIndustry(industry);
		accountEntryImpl.setCountryId(countryId);
		accountEntryImpl.setPartnerEntryId(partnerEntryId);
		accountEntryImpl.setPartnerManagedSupport(partnerManagedSupport);
		accountEntryImpl.setTier(tier);
		accountEntryImpl.setMaxCustomers(maxCustomers);

		if (instructions == null) {
			accountEntryImpl.setInstructions(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setInstructions(instructions);
		}

		if (notes == null) {
			accountEntryImpl.setNotes(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setNotes(notes);
		}

		accountEntryImpl.setHighestSupportResponseId(highestSupportResponseId);

		if (lastAuditDate == Long.MIN_VALUE) {
			accountEntryImpl.setLastAuditDate(null);
		}
		else {
			accountEntryImpl.setLastAuditDate(new Date(lastAuditDate));
		}

		accountEntryImpl.setStatus(status);
		accountEntryImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			accountEntryImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			accountEntryImpl.setStatusDate(null);
		}
		else {
			accountEntryImpl.setStatusDate(new Date(statusDate));
		}

		if (statusMessage == null) {
			accountEntryImpl.setStatusMessage(StringPool.BLANK);
		}
		else {
			accountEntryImpl.setStatusMessage(statusMessage);
		}

		accountEntryImpl.resetOriginalValues();

		return accountEntryImpl;
	}

	public long accountEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public long corpProjectId;
	public String corpEntryName;
	public String name;
	public String code;
	public long redirectAccountEntryId;
	public int type;
	public int industry;
	public long countryId;
	public long partnerEntryId;
	public boolean partnerManagedSupport;
	public int tier;
	public int maxCustomers;
	public String instructions;
	public String notes;
	public long highestSupportResponseId;
	public long lastAuditDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String statusMessage;
}