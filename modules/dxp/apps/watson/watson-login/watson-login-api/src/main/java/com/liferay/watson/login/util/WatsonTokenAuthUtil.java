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

package com.liferay.watson.login.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.watson.login.model.WatsonTokenAuthEntry;
import com.liferay.watson.login.service.WatsonTokenAuthEntryLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Steven Smith
 */
public class WatsonTokenAuthUtil {

	public static final int AUTHORIZATION_STATUS_APPROVED = 0;

	public static final int AUTHORIZATION_STATUS_EXPIRED = 2;

	public static final int AUTHORIZATION_STATUS_INVALID = 3;

	public static final String AUTHORIZATION_STATUS_LABEL_APPROVED = "approved";

	public static final String AUTHORIZATION_STATUS_LABEL_EXPIRED = "expired";

	public static final String AUTHORIZATION_STATUS_LABEL_INVALID = "invalid";

	public static final String AUTHORIZATION_STATUS_LABEL_PENDING = "pending";

	public static final int AUTHORIZATION_STATUS_PENDING = 1;

	public static WatsonTokenAuthEntry addDistinctWatsonTokenAuthEntry(
		User user, String authToken, Date expirationDate) {

		removeWatsonTokenAuthEntry(user);

		WatsonTokenAuthEntry watsonTokenAuthEntry =
			WatsonTokenAuthEntryLocalServiceUtil.createWatsonTokenAuthEntry(
				CounterLocalServiceUtil.increment());

		watsonTokenAuthEntry.setCompanyId(user.getCompanyId());
		watsonTokenAuthEntry.setUserId(user.getUserId());
		watsonTokenAuthEntry.setUserName(user.getFullName());
		watsonTokenAuthEntry.setCreateDate(new Date());
		watsonTokenAuthEntry.setExpirationDate(expirationDate);
		watsonTokenAuthEntry.setToken(authToken);
		watsonTokenAuthEntry.setLoginDate(watsonTokenAuthEntry.getCreateDate());

		return WatsonTokenAuthEntryLocalServiceUtil.updateWatsonTokenAuthEntry(
			watsonTokenAuthEntry);
	}

	public static void extendWatsonTokenAuth(User user) {
		if (Validator.isNotNull(user)) {
			WatsonTokenAuthEntry watsonTokenAuthEntry =
				fetchWatsonTokenAuthEntry(user);

			if (Validator.isNotNull(watsonTokenAuthEntry) &&
				watsonTokenAuthEntry.isActive() &&
				!isExpired(watsonTokenAuthEntry)) {

				watsonTokenAuthEntry.setExpirationDate(getNewExpirationDate());

				WatsonTokenAuthEntryLocalServiceUtil.updateWatsonTokenAuthEntry(
					watsonTokenAuthEntry);
			}
		}
	}

	public static Date getNewExpirationDate() {
		Date now = new Date();

		Calendar expirationDate = CalendarFactoryUtil.getCalendar();

		expirationDate.setTime(now);

		expirationDate.add(Calendar.MINUTE, 30);

		return expirationDate.getTime();
	}

	public static boolean hasAuthenticatedSession(User user) {
		WatsonTokenAuthEntry watsonTokenAuthEntry = fetchWatsonTokenAuthEntry(
			user);

		if (Validator.isNotNull(watsonTokenAuthEntry) &&
			watsonTokenAuthEntry.isActive() &&
			!isExpired(watsonTokenAuthEntry)) {

			return true;
		}

		return false;
	}

	public static boolean hasPendingToken(User user) {
		WatsonTokenAuthEntry watsonTokenAuthEntry = fetchWatsonTokenAuthEntry(
			user);

		if (Validator.isNotNull(watsonTokenAuthEntry) &&
			!watsonTokenAuthEntry.isActive() &&
			!isExpired(watsonTokenAuthEntry)) {

			return true;
		}

		return false;
	}

	public static String verifyWatsonTokenAuthEntry(
		User user, String authToken) {

		WatsonTokenAuthEntry watsonTokenAuthEntry = fetchWatsonTokenAuthEntry(
			user);

		if (Validator.isNotNull(authToken) && (watsonTokenAuthEntry != null)) {
			if (isExpired(watsonTokenAuthEntry)) {
				return AUTHORIZATION_STATUS_LABEL_EXPIRED;
			}
			else if (authToken.equals(watsonTokenAuthEntry.getToken())) {
				watsonTokenAuthEntry.setActive(true);

				WatsonTokenAuthEntryLocalServiceUtil.updateWatsonTokenAuthEntry(
					watsonTokenAuthEntry);

				return AUTHORIZATION_STATUS_LABEL_APPROVED;
			}
		}

		return AUTHORIZATION_STATUS_LABEL_INVALID;
	}

	protected static WatsonTokenAuthEntry fetchWatsonTokenAuthEntry(User user) {
		DynamicQuery dynamicQuery =
			WatsonTokenAuthEntryLocalServiceUtil.dynamicQuery();

		Object[] properties =
			{"userId", user.getUserId(), "companyId", user.getCompanyId()};

		for (int i = 0; i < properties.length; i += 2) {
			String propertyName = String.valueOf(properties[i]);

			Property property = PropertyFactoryUtil.forName(propertyName);

			Object propertyValue = properties[i + 1];

			dynamicQuery.add(property.eq(propertyValue));
		}

		List<WatsonTokenAuthEntry> watsonTokenAuthEntries =
			WatsonTokenAuthEntryLocalServiceUtil.dynamicQuery(dynamicQuery);

		if (!watsonTokenAuthEntries.isEmpty()) {
			return watsonTokenAuthEntries.get(0);
		}

		return null;
	}

	protected static boolean isExpired(
		WatsonTokenAuthEntry watsonTokenAuthEntry) {

		Date expirationDate = watsonTokenAuthEntry.getExpirationDate();

		if ((expirationDate != null) && expirationDate.before(new Date())) {
			return true;
		}
		else {
			return false;
		}
	}

	protected static void removeWatsonTokenAuthEntry(User user) {
		WatsonTokenAuthEntry watsonTokenAuthEntry = fetchWatsonTokenAuthEntry(
			user);

		if (watsonTokenAuthEntry != null) {
			WatsonTokenAuthEntryLocalServiceUtil.deleteWatsonTokenAuthEntry(
				watsonTokenAuthEntry);
		}
	}

}