/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.kernel.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BlogsStatsUserFinderUtil {

	public static int countByOrganizationId(long organizationId) {
		return getFinder().countByOrganizationId(organizationId);
	}

	public static int countByOrganizationIds(
		java.util.List<Long> organizationIds) {

		return getFinder().countByOrganizationIds(organizationIds);
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsStatsUser>
		findByGroupIds(long companyId, long groupId, int start, int end) {

		return getFinder().findByGroupIds(companyId, groupId, start, end);
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsStatsUser>
		findByOrganizationId(
			long organizationId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.blogs.kernel.model.BlogsStatsUser> obc) {

		return getFinder().findByOrganizationId(
			organizationId, start, end, obc);
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsStatsUser>
		findByOrganizationIds(
			java.util.List<Long> organizationIds, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.blogs.kernel.model.BlogsStatsUser> obc) {

		return getFinder().findByOrganizationIds(
			organizationIds, start, end, obc);
	}

	public static BlogsStatsUserFinder getFinder() {
		if (_finder == null) {
			_finder = (BlogsStatsUserFinder)PortalBeanLocatorUtil.locate(
				BlogsStatsUserFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(BlogsStatsUserFinder finder) {
		_finder = finder;
	}

	private static BlogsStatsUserFinder _finder;

}