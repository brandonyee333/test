/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.permission;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Raymond Augé
 * @see    InlineSQLHelper
 */
public class InlineSQLHelperUtil {

	public static InlineSQLHelper getInlineSQLHelper() {
		PortalRuntimePermission.checkGetBeanProperty(InlineSQLHelperUtil.class);

		return _inlineSQLPermission;
	}

	public static boolean isEnabled() {
		return getInlineSQLHelper().isEnabled();
	}

	public static boolean isEnabled(long groupId) {
		return getInlineSQLHelper().isEnabled(groupId);
	}

	public static boolean isEnabled(long companyId, long groupId) {
		return getInlineSQLHelper().isEnabled(companyId, groupId);
	}

	public static boolean isEnabled(long[] groupIds) {
		return getInlineSQLHelper().isEnabled(groupIds);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField) {

		return getInlineSQLHelper().replacePermissionCheck(
			sql, className, classPKField);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, long groupId) {

		return getInlineSQLHelper().replacePermissionCheck(
			sql, className, classPKField, groupId);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, long groupId,
		String bridgeJoin) {

		return getInlineSQLHelper().replacePermissionCheck(
			sql, className, classPKField, groupId, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, long[] groupIds) {

		return getInlineSQLHelper().replacePermissionCheck(
			sql, className, classPKField, groupIds);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, long[] groupIds,
		String bridgeJoin) {

		return getInlineSQLHelper().replacePermissionCheck(
			sql, className, classPKField, groupIds, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField) {

		return getInlineSQLHelper().replacePermissionCheck(
			sql, className, classPKField, userIdField);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long groupId) {

		return getInlineSQLHelper().replacePermissionCheck(
			sql, className, classPKField, userIdField, groupId);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long groupId, String bridgeJoin) {

		return getInlineSQLHelper().replacePermissionCheck(
			sql, className, classPKField, userIdField, groupId, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds) {

		return getInlineSQLHelper().replacePermissionCheck(
			sql, className, classPKField, userIdField, groupIds);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds, String bridgeJoin) {

		return getInlineSQLHelper().replacePermissionCheck(
			sql, className, classPKField, userIdField, groupIds, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		String bridgeJoin) {

		return getInlineSQLHelper().replacePermissionCheck(
			sql, className, classPKField, userIdField, bridgeJoin);
	}

	public static String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		String groupIdField, long[] groupIds, String bridgeJoin) {

		return getInlineSQLHelper().replacePermissionCheck(
			sql, className, classPKField, userIdField, groupIdField, groupIds,
			bridgeJoin);
	}

	public void setInlineSQLHelper(InlineSQLHelper inlineSQLPermission) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_inlineSQLPermission = inlineSQLPermission;
	}

	private static InlineSQLHelper _inlineSQLPermission;

}