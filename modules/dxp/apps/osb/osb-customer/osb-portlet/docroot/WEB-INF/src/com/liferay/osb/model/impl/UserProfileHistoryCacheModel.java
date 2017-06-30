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

import com.liferay.osb.model.UserProfileHistory;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing UserProfileHistory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileHistory
 * @generated
 */
public class UserProfileHistoryCacheModel implements CacheModel<UserProfileHistory>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{userProfileHistoryId=");
		sb.append(userProfileHistoryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", legalEntityName=");
		sb.append(legalEntityName);
		sb.append("}");

		return sb.toString();
	}

	public UserProfileHistory toEntityModel() {
		UserProfileHistoryImpl userProfileHistoryImpl = new UserProfileHistoryImpl();

		userProfileHistoryImpl.setUserProfileHistoryId(userProfileHistoryId);
		userProfileHistoryImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			userProfileHistoryImpl.setCreateDate(null);
		}
		else {
			userProfileHistoryImpl.setCreateDate(new Date(createDate));
		}

		userProfileHistoryImpl.setClassNameId(classNameId);
		userProfileHistoryImpl.setClassPK(classPK);

		if (emailAddress == null) {
			userProfileHistoryImpl.setEmailAddress(StringPool.BLANK);
		}
		else {
			userProfileHistoryImpl.setEmailAddress(emailAddress);
		}

		if (firstName == null) {
			userProfileHistoryImpl.setFirstName(StringPool.BLANK);
		}
		else {
			userProfileHistoryImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			userProfileHistoryImpl.setLastName(StringPool.BLANK);
		}
		else {
			userProfileHistoryImpl.setLastName(lastName);
		}

		if (legalEntityName == null) {
			userProfileHistoryImpl.setLegalEntityName(StringPool.BLANK);
		}
		else {
			userProfileHistoryImpl.setLegalEntityName(legalEntityName);
		}

		userProfileHistoryImpl.resetOriginalValues();

		return userProfileHistoryImpl;
	}

	public long userProfileHistoryId;
	public long userId;
	public long createDate;
	public long classNameId;
	public long classPK;
	public String emailAddress;
	public String firstName;
	public String lastName;
	public String legalEntityName;
}