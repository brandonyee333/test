/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.social.networking.model.MeetupsRegistration;
import com.liferay.social.networking.service.base.MeetupsRegistrationLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetupsRegistrationLocalServiceImpl
	extends MeetupsRegistrationLocalServiceBaseImpl {

	@Override
	public MeetupsRegistration getMeetupsRegistration(
			long userId, long meetupsEntryId)
		throws PortalException {

		return meetupsRegistrationPersistence.findByU_ME(
			userId, meetupsEntryId);
	}

	@Override
	public List<MeetupsRegistration> getMeetupsRegistrations(
		long meetupsEntryId, int status, int start, int end) {

		return meetupsRegistrationPersistence.findByME_S(
			meetupsEntryId, status, start, end);
	}

	@Override
	public int getMeetupsRegistrationsCount(long meetupsEntryId, int status) {
		return meetupsRegistrationPersistence.countByME_S(
			meetupsEntryId, status);
	}

	@Override
	public MeetupsRegistration updateMeetupsRegistration(
			long userId, long meetupsEntryId, int status, String comments)
		throws PortalException {

		User user = userLocalService.getUserById(userId);

		MeetupsRegistration meetupsRegistration =
			meetupsRegistrationPersistence.fetchByU_ME(userId, meetupsEntryId);

		if (meetupsRegistration == null) {
			long meetupsRegistrationId = counterLocalService.increment();

			meetupsRegistration = meetupsRegistrationPersistence.create(
				meetupsRegistrationId);

			meetupsRegistration.setCompanyId(user.getCompanyId());
			meetupsRegistration.setUserId(user.getUserId());
			meetupsRegistration.setUserName(user.getFullName());
			meetupsRegistration.setMeetupsEntryId(meetupsEntryId);
		}

		meetupsRegistration.setComments(comments);
		meetupsRegistration.setStatus(status);

		return meetupsRegistrationPersistence.update(meetupsRegistration);
	}

}