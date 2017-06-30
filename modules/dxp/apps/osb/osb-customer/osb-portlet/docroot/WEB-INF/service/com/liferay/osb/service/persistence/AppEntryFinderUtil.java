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
public class AppEntryFinderUtil {
	public static int countByTitle(java.lang.String title,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByTitle(title, params, andOperator);
	}

	public static int countByC_P_S(int compatibility, boolean prepackaged,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByC_P_S(compatibility, prepackaged, status);
	}

	public static java.util.List<com.liferay.osb.model.AppEntry> findByTitle(
		java.lang.String title,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByTitle(title, params, andOperator, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.AppEntry> findByC_P_S(
		int compatibility, boolean prepackaged, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByC_P_S(compatibility, prepackaged, status);
	}

	public static AppEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (AppEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AppEntryFinder.class.getName());

			ReferenceRegistry.registerReference(AppEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AppEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AppEntryFinderUtil.class, "_finder");
	}

	private static AppEntryFinder _finder;
}