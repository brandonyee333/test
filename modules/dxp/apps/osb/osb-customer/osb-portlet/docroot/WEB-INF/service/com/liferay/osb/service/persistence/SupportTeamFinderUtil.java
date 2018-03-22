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
public class SupportTeamFinderUtil {
	public static int countByKeywords(java.lang.String keywords) {
		return getFinder().countByKeywords(keywords);
	}

	public static int countByN_T(java.lang.String name, java.lang.Integer type,
		boolean andOperator) {
		return getFinder().countByN_T(name, type, andOperator);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> findByKeywords(
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder().findByKeywords(keywords, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> findByN_T(
		java.lang.String name, java.lang.Integer type, boolean andOperator,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder().findByN_T(name, type, andOperator, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> findByU_R(
		long userId, int role) {
		return getFinder().findByU_R(userId, role);
	}

	public static SupportTeamFinder getFinder() {
		if (_finder == null) {
			_finder = (SupportTeamFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SupportTeamFinder.class.getName());

			ReferenceRegistry.registerReference(SupportTeamFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SupportTeamFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SupportTeamFinderUtil.class,
			"_finder");
	}

	private static SupportTeamFinder _finder;
}