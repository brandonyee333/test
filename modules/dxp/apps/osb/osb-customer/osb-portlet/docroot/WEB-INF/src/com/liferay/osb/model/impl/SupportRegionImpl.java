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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.service.SupportTeamLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;
import java.util.TimeZone;

/**
 * @author Brent Krone-Schmidt
 */
public class SupportRegionImpl extends SupportRegionBaseImpl {

	public SupportRegionImpl() {
	}

	public User getManagerUser() throws SystemException {
		return UserLocalServiceUtil.fetchUser(getManagerUserId());
	}

	public List<SupportTeam> getSupportTeams() throws SystemException {
		return SupportTeamLocalServiceUtil.getSupportRegionSupportTeams(
			getSupportRegionId());
	}

	public TimeZone getTimeZone() {
		String timeZoneId = getTimeZoneId();

		if (Validator.isNull(timeZoneId)) {
			return TimeZone.getTimeZone("UTC");
		}

		return TimeZone.getTimeZone(timeZoneId);
	}

}