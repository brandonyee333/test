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
public class TrainingLocationFinderUtil {
	public static java.util.List<com.liferay.osb.model.TrainingLocation> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findAll(start, end, obc);
	}

	public static TrainingLocationFinder getFinder() {
		if (_finder == null) {
			_finder = (TrainingLocationFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingLocationFinder.class.getName());

			ReferenceRegistry.registerReference(TrainingLocationFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(TrainingLocationFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TrainingLocationFinderUtil.class,
			"_finder");
	}

	private static TrainingLocationFinder _finder;
}