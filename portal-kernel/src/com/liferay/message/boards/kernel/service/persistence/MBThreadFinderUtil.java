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
public class MBThreadFinderUtil {

	public static int countByG_U(
		long groupId, long userId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBThread>
				queryDefinition) {

		return getFinder().countByG_U(groupId, userId, queryDefinition);
	}

	public static int countByG_C(
		long groupId, long categoryId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBThread>
				queryDefinition) {

		return getFinder().countByG_C(groupId, categoryId, queryDefinition);
	}

	public static int countByG_U_C(
		long groupId, long userId, long[] categoryIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBThread>
				queryDefinition) {

		return getFinder().countByG_U_C(
			groupId, userId, categoryIds, queryDefinition);
	}

	public static int countByG_U_LPD(
		long groupId, long userId, java.util.Date lastPostDate,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBThread>
				queryDefinition) {

		return getFinder().countByG_U_LPD(
			groupId, userId, lastPostDate, queryDefinition);
	}

	public static int countByG_U_A(
		long groupId, long userId, boolean anonymous,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBThread>
				queryDefinition) {

		return getFinder().countByG_U_A(
			groupId, userId, anonymous, queryDefinition);
	}

	public static int countByS_G_U(
		long groupId, long userId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBThread>
				queryDefinition) {

		return getFinder().countByS_G_U(groupId, userId, queryDefinition);
	}

	public static int countByG_U_C_A(
		long groupId, long userId, long[] categoryIds, boolean anonymous,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBThread>
				queryDefinition) {

		return getFinder().countByG_U_C_A(
			groupId, userId, categoryIds, anonymous, queryDefinition);
	}

	public static int countByG_U_LPD_A(
		long groupId, long userId, java.util.Date lastPostDate,
		boolean includeAnonymous,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBThread>
				queryDefinition) {

		return getFinder().countByG_U_LPD_A(
			groupId, userId, lastPostDate, includeAnonymous, queryDefinition);
	}

	public static int countByS_G_U_C(
		long groupId, long userId, long[] categoryIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBThread>
				queryDefinition) {

		return getFinder().countByS_G_U_C(
			groupId, userId, categoryIds, queryDefinition);
	}

	public static int filterCountByG_C(long groupId, long categoryId) {
		return getFinder().filterCountByG_C(groupId, categoryId);
	}

	public static int filterCountByG_C(
		long groupId, long categoryId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBThread>
				queryDefinition) {

		return getFinder().filterCountByG_C(
			groupId, categoryId, queryDefinition);
	}

	public static int filterCountByS_G_U_C(
		long groupId, long userId, long[] categoryIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition
			<com.liferay.message.boards.kernel.model.MBThread>
				queryDefinition) {

		return getFinder().filterCountByS_G_U_C(
			groupId, userId, categoryIds, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> filterFindByG_C(
			long groupId, long categoryId, int start, int end) {

		return getFinder().filterFindByG_C(groupId, categoryId, start, end);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> filterFindByG_C(
			long groupId, long categoryId,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBThread>
					queryDefinition) {

		return getFinder().filterFindByG_C(
			groupId, categoryId, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> filterFindByS_G_U_C(
			long groupId, long userId, long[] categoryIds,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBThread>
					queryDefinition) {

		return getFinder().filterFindByS_G_U_C(
			groupId, userId, categoryIds, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> findByNoAssets() {

		return getFinder().findByNoAssets();
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> findByG_U(
			long groupId, long userId,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBThread>
					queryDefinition) {

		return getFinder().findByG_U(groupId, userId, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> findByG_C(
			long groupId, long categoryId,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBThread>
					queryDefinition) {

		return getFinder().findByG_C(groupId, categoryId, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> findByG_U_C(
			long groupId, long userId, long[] categoryIds,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBThread>
					queryDefinition) {

		return getFinder().findByG_U_C(
			groupId, userId, categoryIds, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> findByG_U_LPD(
			long groupId, long userId, java.util.Date lastPostDate,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBThread>
					queryDefinition) {

		return getFinder().findByG_U_LPD(
			groupId, userId, lastPostDate, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> findByG_U_A(
			long groupId, long userId, boolean anonymous,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBThread>
					queryDefinition) {

		return getFinder().findByG_U_A(
			groupId, userId, anonymous, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> findByS_G_U(
			long groupId, long userId,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBThread>
					queryDefinition) {

		return getFinder().findByS_G_U(groupId, userId, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> findByG_U_C_A(
			long groupId, long userId, long[] categoryIds, boolean anonymous,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBThread>
					queryDefinition) {

		return getFinder().findByG_U_C_A(
			groupId, userId, categoryIds, anonymous, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> findByG_U_LPD_A(
			long groupId, long userId, java.util.Date lastPostDate,
			boolean includeAnonymous,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBThread>
					queryDefinition) {

		return getFinder().findByG_U_LPD_A(
			groupId, userId, lastPostDate, includeAnonymous, queryDefinition);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBThread> findByS_G_U_C(
			long groupId, long userId, long[] categoryIds,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBThread>
					queryDefinition) {

		return getFinder().findByS_G_U_C(
			groupId, userId, categoryIds, queryDefinition);
	}

	public static MBThreadFinder getFinder() {
		if (_finder == null) {
			_finder = (MBThreadFinder)PortalBeanLocatorUtil.locate(
				MBThreadFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(MBThreadFinder finder) {
		_finder = finder;
	}

	private static MBThreadFinder _finder;

}