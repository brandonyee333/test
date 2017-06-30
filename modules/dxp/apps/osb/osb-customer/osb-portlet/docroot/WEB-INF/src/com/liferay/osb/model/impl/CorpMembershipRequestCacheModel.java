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

import com.liferay.osb.model.CorpMembershipRequest;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing CorpMembershipRequest in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CorpMembershipRequest
 * @generated
 */
public class CorpMembershipRequestCacheModel implements CacheModel<CorpMembershipRequest>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{corpMembershipRequestId=");
		sb.append(corpMembershipRequestId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", corpEntryId=");
		sb.append(corpEntryId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	public CorpMembershipRequest toEntityModel() {
		CorpMembershipRequestImpl corpMembershipRequestImpl = new CorpMembershipRequestImpl();

		corpMembershipRequestImpl.setCorpMembershipRequestId(corpMembershipRequestId);
		corpMembershipRequestImpl.setUserId(userId);

		if (userName == null) {
			corpMembershipRequestImpl.setUserName(StringPool.BLANK);
		}
		else {
			corpMembershipRequestImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			corpMembershipRequestImpl.setCreateDate(null);
		}
		else {
			corpMembershipRequestImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			corpMembershipRequestImpl.setModifiedDate(null);
		}
		else {
			corpMembershipRequestImpl.setModifiedDate(new Date(modifiedDate));
		}

		corpMembershipRequestImpl.setCorpEntryId(corpEntryId);

		if (key == null) {
			corpMembershipRequestImpl.setKey(StringPool.BLANK);
		}
		else {
			corpMembershipRequestImpl.setKey(key);
		}

		if (emailAddress == null) {
			corpMembershipRequestImpl.setEmailAddress(StringPool.BLANK);
		}
		else {
			corpMembershipRequestImpl.setEmailAddress(emailAddress);
		}

		corpMembershipRequestImpl.setStatus(status);

		corpMembershipRequestImpl.resetOriginalValues();

		return corpMembershipRequestImpl;
	}

	public long corpMembershipRequestId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long corpEntryId;
	public String key;
	public String emailAddress;
	public int status;
}