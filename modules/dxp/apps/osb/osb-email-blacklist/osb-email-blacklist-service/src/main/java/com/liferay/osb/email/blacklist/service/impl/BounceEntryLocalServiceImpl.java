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

		bounceEntryPersistence.update(bounceEntry);

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