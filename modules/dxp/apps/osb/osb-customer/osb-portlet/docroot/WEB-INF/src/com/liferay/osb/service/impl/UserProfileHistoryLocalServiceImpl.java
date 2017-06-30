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
import com.liferay.osb.UserProfileHistoryFirstNameException;
import com.liferay.osb.UserProfileHistoryLastNameException;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.service.base.UserProfileHistoryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class UserProfileHistoryLocalServiceImpl
	extends UserProfileHistoryLocalServiceBaseImpl {

	public UserProfileHistory addUserProfileHistory(
			long userId, long classNameId, long classPK, String emailAddress,
			String firstName, String lastName, String legalEntityName)
		throws PortalException, SystemException {

		validate(userId, firstName, lastName);

		long userProfileHistoryId = counterLocalService.increment();

		UserProfileHistory userProfileHistory =
			userProfileHistoryPersistence.create(userProfileHistoryId);

		userProfileHistory.setUserId(userId);
		userProfileHistory.setCreateDate(new Date());
		userProfileHistory.setClassNameId(classNameId);
		userProfileHistory.setClassPK(classPK);
		userProfileHistory.setEmailAddress(emailAddress);
		userProfileHistory.setFirstName(firstName);
		userProfileHistory.setLastName(lastName);
		userProfileHistory.setLegalEntityName(legalEntityName);

		return userProfileHistoryPersistence.update(userProfileHistory, false);
	}

	public List<UserProfileHistory> getClassUserProfileHistories(
			long classNameId, long classPK)
		throws SystemException {

		return userProfileHistoryPersistence.findByC_C(classNameId, classPK);
	}

	public List<UserProfileHistory> getUserUserProfileHistories(
			long userId, long classNameId)
		throws SystemException {

		return userProfileHistoryPersistence.findByU_C(userId, classNameId);
	}

	protected void validate(long userId, String firstName, String lastName)
		throws PortalException, SystemException {

		userPersistence.findByPrimaryKey(userId);

		if (Validator.isNull(firstName)) {
			throw new UserProfileHistoryFirstNameException();
		}

		if (Validator.isNull(lastName)) {
			throw new UserProfileHistoryLastNameException();
		}
	}

}