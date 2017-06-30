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
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * @author Amos Fong
 */
public class PartnerWorkerImpl extends PartnerWorkerBaseImpl {

	public PartnerWorkerImpl() {
	}

	public String getNotificationsLabel() {
		return PartnerWorkerConstants.getNotificationsLabel(getNotifications());
	}

	public PartnerEntry getPartnerEntry()
		throws PortalException, SystemException {

		return PartnerEntryLocalServiceUtil.getPartnerEntry(
			getPartnerEntryId());
	}

	public String getRoleLabel() {
		return PartnerWorkerConstants.getRoleLabel(getRole());
	}

	public boolean isActive() throws PortalException, SystemException {
		try {
			User user = UserLocalServiceUtil.getUser(getUserId());

			if (user.isActive()) {
				return true;
			}
		}
		catch (NoSuchUserException nsue) {
		}

		return false;
	}

}