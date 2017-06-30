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
public class CorpEntryFinderUtil {
	public static int countByName(java.lang.String name,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByName(name, params, andOperator);
	}

	public static java.util.List<com.liferay.osb.model.CorpEntry> findByName(
		java.lang.String name,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByName(name, params, andOperator, start, end, obc);
	}

	public static CorpEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (CorpEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					CorpEntryFinder.class.getName());

			ReferenceRegistry.registerReference(CorpEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(CorpEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(CorpEntryFinderUtil.class, "_finder");
	}

	private static CorpEntryFinder _finder;
}