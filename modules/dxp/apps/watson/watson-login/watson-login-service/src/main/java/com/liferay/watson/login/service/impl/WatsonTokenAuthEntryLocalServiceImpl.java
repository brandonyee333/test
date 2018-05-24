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
		User user, String token, String loginIP) {

		removeWatsonTokenAuthEntry(user);

		WatsonTokenAuthEntry watsonTokenAuthEntry =
			watsonTokenAuthEntryPersistence.create(
				counterLocalService.increment());

		watsonTokenAuthEntry.setCompanyId(user.getCompanyId());
		watsonTokenAuthEntry.setUserId(user.getUserId());
		watsonTokenAuthEntry.setUserName(user.getFullName());
		watsonTokenAuthEntry.setCreateDate(new Date());
		watsonTokenAuthEntry.setActive(false);
		watsonTokenAuthEntry.setLoginIP(loginIP);
		watsonTokenAuthEntry.setToken(token);
		watsonTokenAuthEntry.setExpirationDate(getNewExpirationDate());
		watsonTokenAuthEntry.setLoginDate(watsonTokenAuthEntry.getCreateDate());
		watsonTokenAuthEntry.setStatus(
			WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_PENDING);

		return watsonTokenAuthEntryPersistence.update(watsonTokenAuthEntry);
	}

	public void extendWatsonTokenAuthEntry(User user, String loginIP) {
		WatsonTokenAuthEntry watsonTokenAuthEntry = fetchWatsonTokenAuthEntry(
			user);

		if (Validator.isNotNull(watsonTokenAuthEntry) &&
			watsonTokenAuthEntry.isActive() &&
			!watsonTokenAuthEntry.isExpired() &&
			loginIP.equals(watsonTokenAuthEntry.getLoginIP())) {

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

	public int updateWatsonTokenAuthEntryStatus(User user, String loginIP) {
		WatsonTokenAuthEntry watsonTokenAuthEntry = fetchWatsonTokenAuthEntry(
			user);

		if (watsonTokenAuthEntry == null) {
			return WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_ERROR;
		}
		else if (watsonTokenAuthEntry.isExpired()) {
			watsonTokenAuthEntry.setStatus(
				WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_EXPIRED);

			watsonTokenAuthEntry = watsonTokenAuthEntryPersistence.update(
				watsonTokenAuthEntry);
		}
		else if (loginIP.equals(watsonTokenAuthEntry.getLoginIP()) &&
				 (watsonTokenAuthEntry.getStatus() ==
					 WatsonTokenAuthEntryConstants.
						 AUTHORIZATION_STATUS_INVALID_IP)) {

			watsonTokenAuthEntry.setActive(false);
			watsonTokenAuthEntry.setStatus(
				WatsonTokenAuthEntryConstants.
					AUTHORIZATION_STATUS_INVALID_IP_WARNING);

			watsonTokenAuthEntry = watsonTokenAuthEntryPersistence.update(
				watsonTokenAuthEntry);
		}
		else if (!loginIP.equals(watsonTokenAuthEntry.getLoginIP()) ||
				 (watsonTokenAuthEntry.getStatus() ==
					 WatsonTokenAuthEntryConstants.
						 AUTHORIZATION_STATUS_INVALID_IP)) {

			watsonTokenAuthEntry.setActive(false);
			watsonTokenAuthEntry.setStatus(
				WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_INVALID_IP);

			watsonTokenAuthEntry = watsonTokenAuthEntryPersistence.update(
				watsonTokenAuthEntry);
		}

		return watsonTokenAuthEntry.getStatus();
	}

	public int updateWatsonTokenAuthEntryStatus(
		User user, String token, String loginIP) {

		WatsonTokenAuthEntry watsonTokenAuthEntry = fetchWatsonTokenAuthEntry(
			user);

		if (Validator.isNull(token) || (watsonTokenAuthEntry == null)) {
			return WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_ERROR;
		}
		else if (watsonTokenAuthEntry.isExpired()) {
			watsonTokenAuthEntry.setStatus(
				WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_EXPIRED);

			watsonTokenAuthEntry = watsonTokenAuthEntryPersistence.update(
				watsonTokenAuthEntry);
		}
		else if (loginIP.equals(watsonTokenAuthEntry.getLoginIP()) &&
				 (watsonTokenAuthEntry.getStatus() ==
					 WatsonTokenAuthEntryConstants.
						 AUTHORIZATION_STATUS_INVALID_IP)) {

			watsonTokenAuthEntry.setActive(false);
			watsonTokenAuthEntry.setStatus(
				WatsonTokenAuthEntryConstants.
					AUTHORIZATION_STATUS_INVALID_IP_WARNING);

			watsonTokenAuthEntry = watsonTokenAuthEntryPersistence.update(
				watsonTokenAuthEntry);
		}
		else if (!loginIP.equals(watsonTokenAuthEntry.getLoginIP()) ||
				 (watsonTokenAuthEntry.getStatus() ==
					 WatsonTokenAuthEntryConstants.
						 AUTHORIZATION_STATUS_INVALID_IP)) {

			watsonTokenAuthEntry.setActive(false);
			watsonTokenAuthEntry.setStatus(
				WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_INVALID_IP);

			watsonTokenAuthEntry = watsonTokenAuthEntryPersistence.update(
				watsonTokenAuthEntry);
		}
		else if (token.equals(watsonTokenAuthEntry.getToken())) {
			watsonTokenAuthEntry.setActive(true);
			watsonTokenAuthEntry.setStatus(
				WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_APPROVED);

			watsonTokenAuthEntry = watsonTokenAuthEntryPersistence.update(
				watsonTokenAuthEntry);
		}
		else if (!token.equals(watsonTokenAuthEntry.getToken())) {
			watsonTokenAuthEntry.setStatus(
				WatsonTokenAuthEntryConstants.AUTHORIZATION_STATUS_INVALID);

			watsonTokenAuthEntry = watsonTokenAuthEntryPersistence.update(
				watsonTokenAuthEntry);
		}

		return watsonTokenAuthEntry.getStatus();
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