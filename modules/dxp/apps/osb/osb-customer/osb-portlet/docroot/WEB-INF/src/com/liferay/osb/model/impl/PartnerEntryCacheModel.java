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

import com.liferay.osb.model.PartnerEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PartnerEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntry
 * @generated
 */
public class PartnerEntryCacheModel implements CacheModel<PartnerEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{partnerEntryId=");
		sb.append(partnerEntryId);
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
		sb.append(", parentPartnerEntryId=");
		sb.append(parentPartnerEntryId);
		sb.append(", dossieraAccountKey=");
		sb.append(dossieraAccountKey);
		sb.append(", code=");
		sb.append(code);
		sb.append(", notes=");
		sb.append(notes);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	public PartnerEntry toEntityModel() {
		PartnerEntryImpl partnerEntryImpl = new PartnerEntryImpl();

		partnerEntryImpl.setPartnerEntryId(partnerEntryId);
		partnerEntryImpl.setUserId(userId);

		if (userName == null) {
			partnerEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			partnerEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			partnerEntryImpl.setCreateDate(null);
		}
		else {
			partnerEntryImpl.setCreateDate(new Date(createDate));
		}

		partnerEntryImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			partnerEntryImpl.setModifiedUserName(StringPool.BLANK);
		}
		else {
			partnerEntryImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			partnerEntryImpl.setModifiedDate(null);
		}
		else {
			partnerEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		partnerEntryImpl.setParentPartnerEntryId(parentPartnerEntryId);

		if (dossieraAccountKey == null) {
			partnerEntryImpl.setDossieraAccountKey(StringPool.BLANK);
		}
		else {
			partnerEntryImpl.setDossieraAccountKey(dossieraAccountKey);
		}

		if (code == null) {
			partnerEntryImpl.setCode(StringPool.BLANK);
		}
		else {
			partnerEntryImpl.setCode(code);
		}

		if (notes == null) {
			partnerEntryImpl.setNotes(StringPool.BLANK);
		}
		else {
			partnerEntryImpl.setNotes(notes);
		}

		partnerEntryImpl.setStatus(status);

		partnerEntryImpl.resetOriginalValues();

		return partnerEntryImpl;
	}

	public long partnerEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public long parentPartnerEntryId;
	public String dossieraAccountKey;
	public String code;
	public String notes;
	public int status;
}