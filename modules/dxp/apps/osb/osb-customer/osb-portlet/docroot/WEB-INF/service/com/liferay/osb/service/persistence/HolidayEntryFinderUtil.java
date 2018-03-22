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

package com.liferay.osb.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class HolidayEntryFinderUtil {
	public static java.util.List<com.liferay.osb.model.HolidayEntry> findByU_RY(
		long userId, boolean repeatYearly) {
		return getFinder().findByU_RY(userId, repeatYearly);
	}

	public static java.util.List<com.liferay.osb.model.HolidayEntry> findByU_RY_SD_ED(
		long userId, java.lang.Boolean repeatYearly, java.util.Date startDate,
		java.util.Date endDate) {
		return getFinder()
				   .findByU_RY_SD_ED(userId, repeatYearly, startDate, endDate);
	}

	public static HolidayEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (HolidayEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					HolidayEntryFinder.class.getName());

			ReferenceRegistry.registerReference(HolidayEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(HolidayEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(HolidayEntryFinderUtil.class,
			"_finder");
	}

	private static HolidayEntryFinder _finder;
}