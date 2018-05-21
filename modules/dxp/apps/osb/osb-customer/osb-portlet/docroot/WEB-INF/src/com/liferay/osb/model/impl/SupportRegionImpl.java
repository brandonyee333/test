/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.model.impl;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.TimeZone;

/**
 * @author Brent Krone-Schmidt
 */
public class SupportRegionImpl extends SupportRegionBaseImpl {

	public SupportRegionImpl() {
	}

	public User getManagerUser() {
		return UserLocalServiceUtil.fetchUser(getManagerUserId());
	}

	public TimeZone getTimeZone() {
		String timeZoneId = getTimeZoneId();

		if (Validator.isNull(timeZoneId)) {
			return TimeZone.getTimeZone("UTC");
		}

		return TimeZone.getTimeZone(timeZoneId);
	}

}