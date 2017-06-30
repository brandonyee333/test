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

import com.liferay.osb.model.CorpGroup;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing CorpGroup in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CorpGroup
 * @generated
 */
public class CorpGroupCacheModel implements CacheModel<CorpGroup>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{corpGroupId=");
		sb.append(corpGroupId);
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
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", website=");
		sb.append(website);
		sb.append("}");

		return sb.toString();
	}

	public CorpGroup toEntityModel() {
		CorpGroupImpl corpGroupImpl = new CorpGroupImpl();

		corpGroupImpl.setCorpGroupId(corpGroupId);
		corpGroupImpl.setUserId(userId);

		if (userName == null) {
			corpGroupImpl.setUserName(StringPool.BLANK);
		}
		else {
			corpGroupImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			corpGroupImpl.setCreateDate(null);
		}
		else {
			corpGroupImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			corpGroupImpl.setModifiedDate(null);
		}
		else {
			corpGroupImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			corpGroupImpl.setName(StringPool.BLANK);
		}
		else {
			corpGroupImpl.setName(name);
		}

		if (description == null) {
			corpGroupImpl.setDescription(StringPool.BLANK);
		}
		else {
			corpGroupImpl.setDescription(description);
		}

		corpGroupImpl.setOrganizationId(organizationId);
		corpGroupImpl.setLogoId(logoId);

		if (emailAddress == null) {
			corpGroupImpl.setEmailAddress(StringPool.BLANK);
		}
		else {
			corpGroupImpl.setEmailAddress(emailAddress);
		}

		if (website == null) {
			corpGroupImpl.setWebsite(StringPool.BLANK);
		}
		else {
			corpGroupImpl.setWebsite(website);
		}

		corpGroupImpl.resetOriginalValues();

		return corpGroupImpl;
	}

	public long corpGroupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public long organizationId;
	public long logoId;
	public String emailAddress;
	public String website;
}