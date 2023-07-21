/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.service.impl;

import com.liferay.osb.email.blacklist.exception.BounceEntryDateException;
import com.liferay.osb.email.blacklist.exception.BounceEntryEmailAddressException;
import com.liferay.osb.email.blacklist.internal.configuration.EmailBlacklistServiceConfigurationUtil;
import com.liferay.osb.email.blacklist.model.BounceEntry;
import com.liferay.osb.email.blacklist.service.base.BounceEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Jamie Sammons
 */
public class BounceEntryLocalServiceImpl
	extends BounceEntryLocalServiceBaseImpl {

	@Override
	public BounceEntry addBounceEntry(
			String emailAddress, Date bounceDate, String bounceType,
			String bounceSubtype)
		throws PortalException {

		validate(emailAddress, bounceDate);

		if (!isBounceWithinDateLimit(bounceDate)) {
			return null;
		}

		long bounceEntryId = counterLocalService.increment();

		BounceEntry bounceEntry = bounceEntryPersistence.create(bounceEntryId);

		bounceEntry.setEmailAddress(emailAddress);
		bounceEntry.setBounceDate(bounceDate);
		bounceEntry.setBounceType(bounceType);
		bounceEntry.setBounceSubtype(bounceSubtype);

		bounceEntry = bounceEntryPersistence.update(bounceEntry);

		checkBounceLimit(emailAddress);

		return bounceEntry;
	}

	@Override
	public List<BounceEntry> getBounceEntries(
		Date bounceDateLT, int start, int end,
		OrderByComparator<BounceEntry> obc) {

		return bounceEntryPersistence.findByLtBounceDate(
			bounceDateLT, start, end, obc);
	}

	protected void checkBounceLimit(String emailAddress)
		throws PortalException {

		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.add(
			Calendar.DATE,
			-1 *
				EmailBlacklistServiceConfigurationUtil.
					getBounceLimitWithinDays());

		int bounceCount = bounceEntryPersistence.countByEA_GtBD(
			emailAddress, CalendarUtil.getGTDate(calendar));

		if (bounceCount >
				EmailBlacklistServiceConfigurationUtil.getBounceLimit()) {

			blacklistEntryLocalService.addBlacklistEntry(emailAddress);
		}
	}

	protected boolean isBounceWithinDateLimit(Date bounceDate) {
		int daysSinceBounce = DateUtil.getDaysBetween(bounceDate, new Date());

		if (daysSinceBounce <=
				EmailBlacklistServiceConfigurationUtil.
					getBounceLimitWithinDays()) {

			return true;
		}

		return false;
	}

	protected void validate(String emailAddress, Date bounceDate)
		throws PortalException {

		if (!Validator.isEmailAddress(emailAddress)) {
			throw new BounceEntryEmailAddressException();
		}

		if (bounceDate == null) {
			throw new BounceEntryDateException();
		}
	}

}