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

package com.liferay.watson.login.service.impl;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.watson.login.constants.WatsonTokenAuthEntryConstants;
import com.liferay.watson.login.model.WatsonTokenAuthEntry;
import com.liferay.watson.login.service.base.WatsonTokenAuthEntryLocalServiceBaseImpl;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Steven Smith
 */
public class WatsonTokenAuthEntryLocalServiceImpl
	extends WatsonTokenAuthEntryLocalServiceBaseImpl {

	public WatsonTokenAuthEntry addWatsonTokenAuthEntry(
		User user, String authToken, String latestLoginIP) {

		removeWatsonTokenAuthEntry(user);

		WatsonTokenAuthEntry watsonTokenAuthEntry =
			watsonTokenAuthEntryPersistence.create(
				counterLocalService.increment());

		watsonTokenAuthEntry.setCompanyId(user.getCompanyId());
		watsonTokenAuthEntry.setUserId(user.getUserId());
		watsonTokenAuthEntry.setUserName(user.getFullName());
		watsonTokenAuthEntry.setCreateDate(new Date());
		watsonTokenAuthEntry.setActive(false);
		watsonTokenAuthEntry.setLoginIP(latestLoginIP);
		watsonTokenAuthEntry.setToken(authToken);
		watsonTokenAuthEntry.setExpirationDate(getNewExpirationDate());
		watsonTokenAuthEntry.setLoginDate(watsonTokenAuthEntry.getCreateDate());
		watsonTokenAuthEntry.setStatus(
			WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_PENDING);

		return watsonTokenAuthEntryPersistence.update(watsonTokenAuthEntry);
	}

	public void extendWatsonTokenAuthEntry(User user, String lastRequestIP) {
		WatsonTokenAuthEntry watsonTokenAuthEntry = fetchWatsonTokenAuthEntry(
			user);

		if (Validator.isNotNull(watsonTokenAuthEntry) &&
			watsonTokenAuthEntry.isActive() &&
			!watsonTokenAuthEntry.isExpired() &&
			lastRequestIP.equals(watsonTokenAuthEntry.getLoginIP())) {

			watsonTokenAuthEntry.setExpirationDate(getNewExpirationDate());

			watsonTokenAuthEntryPersistence.update(watsonTokenAuthEntry);
		}
	}

	public WatsonTokenAuthEntry fetchWatsonTokenAuthEntry(User user) {
		WatsonTokenAuthEntry watsonTokenAuthEntry = null;

		if (user != null) {
			watsonTokenAuthEntry =
				watsonTokenAuthEntryPersistence.fetchByUserId(user.getUserId());
		}

		return watsonTokenAuthEntry;
	}

	public String getWatsonTokenAuthEntryStatus(
		User user, String latestLoginIP) {

		WatsonTokenAuthEntry watsonTokenAuthEntry = fetchWatsonTokenAuthEntry(
			user);

		if (watsonTokenAuthEntry != null) {
			if (watsonTokenAuthEntry.isExpired()) {
				watsonTokenAuthEntry.setStatus(
					WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_EXPIRED);

				watsonTokenAuthEntryPersistence.update(watsonTokenAuthEntry);
			}
			else if (latestLoginIP.equals(watsonTokenAuthEntry.getLoginIP()) &&
					 (watsonTokenAuthEntry.getStatus() ==
						 WatsonTokenAuthEntryConstants.
							 AUTHORIZATION_STATUS_INVALID_IP)) {

				watsonTokenAuthEntry.setActive(false);
				watsonTokenAuthEntry.setStatus(
					WatsonTokenAuthEntryConstants.
						AUTHORIZATION_STATUS_INVALID_IP_WARNING);

				watsonTokenAuthEntryPersistence.update(watsonTokenAuthEntry);
			}
			else if (!latestLoginIP.equals(watsonTokenAuthEntry.getLoginIP()) ||
					 (watsonTokenAuthEntry.getStatus() ==
						 WatsonTokenAuthEntryConstants.
							 AUTHORIZATION_STATUS_INVALID_IP)) {

				watsonTokenAuthEntry.setActive(false);
				watsonTokenAuthEntry.setStatus(
					WatsonTokenAuthEntryConstants.
						AUTHORIZATION_STATUS_INVALID_IP);

				watsonTokenAuthEntryPersistence.update(watsonTokenAuthEntry);
			}

			return WatsonTokenAuthEntryConstants.
				getWatsonTokenAuthEntryStatusLabel(watsonTokenAuthEntry.
					getStatus());
		}

		return null;
	}

	public boolean hasAuthenticatedSession(User user) {
		WatsonTokenAuthEntry watsonTokenAuthEntry = fetchWatsonTokenAuthEntry(
			user);

		if (Validator.isNotNull(watsonTokenAuthEntry) &&
			watsonTokenAuthEntry.isActive() &&
			!watsonTokenAuthEntry.isExpired() &&
			(watsonTokenAuthEntry.getStatus() ==
				WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_APPROVED)) {

			return true;
		}

		return false;
	}

	public void invalidateWatsonAuthToken(User user) {
		removeWatsonTokenAuthEntry(user);
	}

	public int verifyWatsonTokenAuthEntry(
		User user, String authToken, String latestLoginIP) {

		WatsonTokenAuthEntry watsonTokenAuthEntry = fetchWatsonTokenAuthEntry(
			user);

		if (Validator.isNotNull(authToken) && (watsonTokenAuthEntry != null)) {
			if (watsonTokenAuthEntry.isExpired()) {
				watsonTokenAuthEntry.setStatus(
					WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_EXPIRED);

				watsonTokenAuthEntryPersistence.update(watsonTokenAuthEntry);
			}
			else if (latestLoginIP.equals(watsonTokenAuthEntry.getLoginIP()) &&
					 (watsonTokenAuthEntry.getStatus() ==
						 WatsonTokenAuthEntryConstants.
							 AUTHORIZATION_STATUS_INVALID_IP)) {

				watsonTokenAuthEntry.setActive(false);
				watsonTokenAuthEntry.setStatus(
					WatsonTokenAuthEntryConstants.
						AUTHORIZATION_STATUS_INVALID_IP_WARNING);

				watsonTokenAuthEntryPersistence.update(watsonTokenAuthEntry);
			}
			else if (!latestLoginIP.equals(watsonTokenAuthEntry.getLoginIP()) ||
					 (watsonTokenAuthEntry.getStatus() ==
						 WatsonTokenAuthEntryConstants.
							 AUTHORIZATION_STATUS_INVALID_IP)) {

				watsonTokenAuthEntry.setActive(false);
				watsonTokenAuthEntry.setStatus(
					WatsonTokenAuthEntryConstants.
						AUTHORIZATION_STATUS_INVALID_IP);

				watsonTokenAuthEntryPersistence.update(watsonTokenAuthEntry);
			}
			else if (authToken.equals(watsonTokenAuthEntry.getToken())) {
				watsonTokenAuthEntry.setActive(true);

				watsonTokenAuthEntry.setStatus(
					WatsonTokenAuthEntryConstants.
						AUTHORIZATION_STATUS_APPROVED);

				watsonTokenAuthEntryPersistence.update(watsonTokenAuthEntry);
			}

			return watsonTokenAuthEntry.getStatus();
		}

		return WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_INVALID;
	}

	protected Date getNewExpirationDate() {
		Calendar expirationDate = CalendarFactoryUtil.getCalendar();

		expirationDate.setTime(new Date());

		expirationDate.add(Calendar.MINUTE, 30);

		return expirationDate.getTime();
	}

	protected void removeWatsonTokenAuthEntry(User user) {
		watsonTokenAuthEntryPersistence.removeByC_U(
			user.getCompanyId(), user.getUserId());
	}

}