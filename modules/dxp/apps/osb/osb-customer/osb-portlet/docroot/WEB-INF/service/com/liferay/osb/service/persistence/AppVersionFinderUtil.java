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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class AppVersionFinderUtil {
	public static int countByAEI_S(long appEntryId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByAEI_S(appEntryId, statuses);
	}

	public static java.util.List<com.liferay.osb.model.AppVersion> findByAEI_S(
		long appEntryId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByAEI_S(appEntryId, statuses, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.AppVersion> findByAEI_C_S(
		long appEntryId, int compatibility, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByAEI_C_S(appEntryId, compatibility, status, start, end);
	}

	public static AppVersionFinder getFinder() {
		if (_finder == null) {
			_finder = (AppVersionFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AppVersionFinder.class.getName());

			ReferenceRegistry.registerReference(AppVersionFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AppVersionFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AppVersionFinderUtil.class,
			"_finder");
	}

	private static AppVersionFinder _finder;
}