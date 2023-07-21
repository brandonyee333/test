/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.kernel.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MBCategoryFinderUtil {

	public static int countC_ByS_G_U_P(
		long groupId, long userId, long[] parentCategoryIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBCategory>
				queryDefinition) {

		return getFinder().countC_ByS_G_U_P(
			groupId, userId, parentCategoryIds, queryDefinition);
	}

	public static int countC_T_ByG_C(
		long groupId, long categoryId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<?> queryDefinition) {

		return getFinder().countC_T_ByG_C(groupId, categoryId, queryDefinition);
	}

	public static int filterCountC_ByS_G_U_P(
		long groupId, long userId, long[] parentCategoryIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBCategory>
				queryDefinition) {

		return getFinder().filterCountC_ByS_G_U_P(
			groupId, userId, parentCategoryIds, queryDefinition);
	}

	public static int filterCountC_T_ByG_C(
		long groupId, long categoryId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<?> queryDefinition) {

		return getFinder().filterCountC_T_ByG_C(
			groupId, categoryId, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBCategory>
			filterFindC_ByS_G_U_P(
				long groupId, long userId, long[] parentCategoryIds,
				com.liferay.portal.kernel.dao.orm.QueryDefinition
					<com.liferay.message.boards.kernel.model.MBCategory>
						queryDefinition) {

		return getFinder().filterFindC_ByS_G_U_P(
			groupId, userId, parentCategoryIds, queryDefinition);
	}

	public static java.util.List<Object> filterFindC_T_ByG_C(
		long groupId, long categoryId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<?> queryDefinition) {

		return getFinder().filterFindC_T_ByG_C(
			groupId, categoryId, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBCategory> findC_ByS_G_U_P(
			long groupId, long userId, long[] parentCategoryIds,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBCategory>
					queryDefinition) {

		return getFinder().findC_ByS_G_U_P(
			groupId, userId, parentCategoryIds, queryDefinition);
	}

	public static java.util.List<Object> findC_T_ByG_C(
		long groupId, long categoryId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<?> queryDefinition) {

		return getFinder().findC_T_ByG_C(groupId, categoryId, queryDefinition);
	}

	public static MBCategoryFinder getFinder() {
		if (_finder == null) {
			_finder = (MBCategoryFinder)PortalBeanLocatorUtil.locate(
				MBCategoryFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(MBCategoryFinder finder) {
		_finder = finder;
	}

	private static MBCategoryFinder _finder;

}