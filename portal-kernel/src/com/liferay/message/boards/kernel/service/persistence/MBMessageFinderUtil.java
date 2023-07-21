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
public class MBMessageFinderUtil {

	public static int countByC_T(java.util.Date createDate, long threadId) {
		return getFinder().countByC_T(createDate, threadId);
	}

	public static int countByG_U_C_S(
		long groupId, long userId, long[] categoryIds, int status) {

		return getFinder().countByG_U_C_S(groupId, userId, categoryIds, status);
	}

	public static int countByG_U_C_A_S(
		long groupId, long userId, long[] categoryIds, boolean anonymous,
		int status) {

		return getFinder().countByG_U_C_A_S(
			groupId, userId, categoryIds, anonymous, status);
	}

	public static int filterCountByG_U_C_S(
		long groupId, long userId, long[] categoryIds, int status) {

		return getFinder().filterCountByG_U_C_S(
			groupId, userId, categoryIds, status);
	}

	public static int filterCountByG_U_C_A_S(
		long groupId, long userId, long[] categoryIds, boolean anonymous,
		int status) {

		return getFinder().filterCountByG_U_C_A_S(
			groupId, userId, categoryIds, anonymous, status);
	}

	public static int filterCountByG_U_MD_C_S(
		long groupId, long userId, java.util.Date modifiedDate,
		long[] categoryIds, int status) {

		return getFinder().filterCountByG_U_MD_C_S(
			groupId, userId, modifiedDate, categoryIds, status);
	}

	public static int filterCountByG_U_MD_C_A_S(
		long groupId, long userId, java.util.Date modifiedDate,
		long[] categoryIds, boolean anonymous, int status) {

		return getFinder().filterCountByG_U_MD_C_A_S(
			groupId, userId, modifiedDate, categoryIds, anonymous, status);
	}

	public static java.util.List<Long> filterFindByG_U_C_S(
		long groupId, long userId, long[] categoryIds, int status, int start,
		int end) {

		return getFinder().filterFindByG_U_C_S(
			groupId, userId, categoryIds, status, start, end);
	}

	public static java.util.List<Long> filterFindByG_U_C_A_S(
		long groupId, long userId, long[] categoryIds, boolean anonymous,
		int status, int start, int end) {

		return getFinder().filterFindByG_U_C_A_S(
			groupId, userId, categoryIds, anonymous, status, start, end);
	}

	public static java.util.List<Long> filterFindByG_U_MD_C_S(
		long groupId, long userId, java.util.Date modifiedDate,
		long[] categoryIds, int status, int start, int end) {

		return getFinder().filterFindByG_U_MD_C_S(
			groupId, userId, modifiedDate, categoryIds, status, start, end);
	}

	public static java.util.List<Long> filterFindByG_U_MD_C_A_S(
		long groupId, long userId, java.util.Date modifiedDate,
		long[] categoryIds, boolean anonymous, int status, int start, int end) {

		return getFinder().filterFindByG_U_MD_C_A_S(
			groupId, userId, modifiedDate, categoryIds, anonymous, status,
			start, end);
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBMessage> findByNoAssets() {

		return getFinder().findByNoAssets();
	}

	public static java.util.List
		<com.liferay.message.boards.kernel.model.MBMessage> findByThreadId(
			long threadId,
			com.liferay.portal.kernel.dao.orm.QueryDefinition
				<com.liferay.message.boards.kernel.model.MBMessage>
					queryDefinition) {

		return getFinder().findByThreadId(threadId, queryDefinition);
	}

	public static java.util.List<Long> findByG_U_C_S(
		long groupId, long userId, long[] categoryIds, int status, int start,
		int end) {

		return getFinder().findByG_U_C_S(
			groupId, userId, categoryIds, status, start, end);
	}

	public static java.util.List<Long> findByG_U_C_A_S(
		long groupId, long userId, long[] categoryIds, boolean anonymous,
		int status, int start, int end) {

		return getFinder().findByG_U_C_A_S(
			groupId, userId, categoryIds, anonymous, status, start, end);
	}

	public static MBMessageFinder getFinder() {
		if (_finder == null) {
			_finder = (MBMessageFinder)PortalBeanLocatorUtil.locate(
				MBMessageFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(MBMessageFinder finder) {
		_finder = finder;
	}

	private static MBMessageFinder _finder;

}