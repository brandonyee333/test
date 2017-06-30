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

import com.liferay.osb.model.UserProfile;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing UserProfile in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserProfile
 * @generated
 */
public class UserProfileCacheModel implements CacheModel<UserProfile>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{userProfileId=");
		sb.append(userProfileId);
		sb.append(", userId=");
		sb.append(userId);
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

	public UserProfile toEntityModel() {
		UserProfileImpl userProfileImpl = new UserProfileImpl();

		userProfileImpl.setUserProfileId(userProfileId);
		userProfileImpl.setUserId(userId);

		if (emailAddress == null) {
			userProfileImpl.setEmailAddress(StringPool.BLANK);
		}
		else {
			userProfileImpl.setEmailAddress(emailAddress);
		}

		if (firstName == null) {
			userProfileImpl.setFirstName(StringPool.BLANK);
		}
		else {
			userProfileImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			userProfileImpl.setLastName(StringPool.BLANK);
		}
		else {
			userProfileImpl.setLastName(lastName);
		}

		if (legalEntityName == null) {
			userProfileImpl.setLegalEntityName(StringPool.BLANK);
		}
		else {
			userProfileImpl.setLegalEntityName(legalEntityName);
		}

		userProfileImpl.resetOriginalValues();

		return userProfileImpl;
	}

	public long userProfileId;
	public long userId;
	public String emailAddress;
	public String firstName;
	public String lastName;
	public String legalEntityName;
}