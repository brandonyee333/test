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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.UserProfileFirstNameException;
import com.liferay.osb.UserProfileLastNameException;
import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.model.UserProfile;
import com.liferay.osb.service.base.UserProfileLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class UserProfileLocalServiceImpl
	extends UserProfileLocalServiceBaseImpl {

	public UserProfile addUserProfile(
			long userId, String emailAddress, String firstName, String lastName,
			String legalEntityName)
		throws PortalException, SystemException {

		validate(userId, firstName, lastName);

		long userProfileId = counterLocalService.increment();

		UserProfile newUserProfile = userProfilePersistence.create(
			userProfileId);

		newUserProfile.setUserId(userId);
		newUserProfile.setEmailAddress(emailAddress);
		newUserProfile.setFirstName(firstName);
		newUserProfile.setLastName(lastName);
		newUserProfile.setLegalEntityName(legalEntityName);

		List<UserProfile> userProfiles = getUserProfiles(userId);

		for (UserProfile oldUserProfile : userProfiles) {
			if (AdminUtil.equalsByUserProfile(oldUserProfile, newUserProfile)) {
				return oldUserProfile;
			}
		}

		return userProfilePersistence.update(newUserProfile, false);
	}

	public List<UserProfile> getUserProfiles(long userId)
		throws PortalException, SystemException {

		return userProfilePersistence.findByUserId(userId);
	}

	public int getUserProfilesCount(long userId)
		throws PortalException, SystemException {

		return userProfilePersistence.countByUserId(userId);
	}

	protected void validate(long userId, String firstName, String lastName)
		throws PortalException, SystemException {

		userPersistence.findByPrimaryKey(userId);

		if (Validator.isNull(firstName)) {
			throw new UserProfileFirstNameException();
		}

		if (Validator.isNull(lastName)) {
			throw new UserProfileLastNameException();
		}
	}

}