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
public class AppFlagFinderUtil {
	public static int countByDeveloperEntryId(long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByDeveloperEntryId(developerEntryId);
	}

	public static AppFlagFinder getFinder() {
		if (_finder == null) {
			_finder = (AppFlagFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AppFlagFinder.class.getName());

			ReferenceRegistry.registerReference(AppFlagFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AppFlagFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AppFlagFinderUtil.class, "_finder");
	}

	private static AppFlagFinder _finder;
}