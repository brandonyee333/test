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
public class BlogsEntryFinderUtil {

	public static int countByOrganizationId(
		long organizationId, java.util.Date displayDate,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.blogs.kernel.model.BlogsEntry> queryDefinition) {

		return getFinder().countByOrganizationId(
			organizationId, displayDate, queryDefinition);
	}

	public static int countByOrganizationIds(
		java.util.List<Long> organizationIds, java.util.Date displayDate,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.blogs.kernel.model.BlogsEntry> queryDefinition) {

		return getFinder().countByOrganizationIds(
			organizationIds, displayDate, queryDefinition);
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>
		findByGroupIds(
			long companyId, long groupId, java.util.Date displayDate,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.blogs.kernel.model.BlogsEntry> queryDefinition) {

		return getFinder().findByGroupIds(
			companyId, groupId, displayDate, queryDefinition);
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>
		findByOrganizationId(
			long organizationId, java.util.Date displayDate,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.blogs.kernel.model.BlogsEntry> queryDefinition) {

		return getFinder().findByOrganizationId(
			organizationId, displayDate, queryDefinition);
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>
		findByOrganizationIds(
			java.util.List<Long> organizationIds, java.util.Date displayDate,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.blogs.kernel.model.BlogsEntry> queryDefinition) {

		return getFinder().findByOrganizationIds(
			organizationIds, displayDate, queryDefinition);
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>
		findByNoAssets() {

		return getFinder().findByNoAssets();
	}

	public static BlogsEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (BlogsEntryFinder)PortalBeanLocatorUtil.locate(
				BlogsEntryFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(BlogsEntryFinder finder) {
		_finder = finder;
	}

	private static BlogsEntryFinder _finder;

}