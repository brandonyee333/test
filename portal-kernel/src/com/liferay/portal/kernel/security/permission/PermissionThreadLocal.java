/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.permission;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class PermissionThreadLocal {

	public static PermissionChecker getPermissionChecker() {
		return _permissionChecker.get();
	}

	public static boolean isAddResource() {
		return _addResource.get();
	}

	public static boolean isFlushResourceBlockEnabled(
		long companyId, long groupId, String name) {

		Set<String> set = _flushResourceBlockEnabled.get();

		StringBundler sb = new StringBundler(5);

		sb.append(companyId);
		sb.append(StringPool.UNDERLINE);
		sb.append(groupId);
		sb.append(StringPool.UNDERLINE);
		sb.append(name);

		return !set.contains(sb.toString());
	}

	public static boolean isFlushResourcePermissionEnabled(
		String resourceName, String primKey) {

		Set<String> set = _flushResourcePermissionEnabled.get();

		return !set.contains(resourceName + StringPool.UNDERLINE + primKey);
	}

	public static void setAddResource(boolean addResource) {
		_addResource.set(addResource);
	}

	public static void setFlushResourceBlockEnabled(
		long companyId, long groupId, String name, boolean enabled) {

		Set<String> set = _flushResourceBlockEnabled.get();

		StringBundler sb = new StringBundler(5);

		sb.append(companyId);
		sb.append(StringPool.UNDERLINE);
		sb.append(groupId);
		sb.append(StringPool.UNDERLINE);
		sb.append(name);

		if (enabled) {
			set.remove(sb.toString());
		}
		else {
			set.add(sb.toString());
		}
	}

	public static void setFlushResourcePermissionEnabled(
		String resourceName, String primKey, boolean enabled) {

		Set<String> set = _flushResourcePermissionEnabled.get();

		if (enabled) {
			set.remove(resourceName + StringPool.UNDERLINE + primKey);
		}
		else {
			set.add(resourceName + StringPool.UNDERLINE + primKey);
		}
	}

	public static void setPermissionChecker(
		PermissionChecker permissionChecker) {

		_permissionChecker.set(permissionChecker);
	}

	private static final ThreadLocal<Boolean> _addResource =
		new CentralizedThreadLocal<>(
			PermissionThreadLocal.class + "._addResource", () -> Boolean.TRUE);
	private static final ThreadLocal<Set<String>> _flushResourceBlockEnabled =
		new CentralizedThreadLocal<>(
			PermissionThreadLocal.class + "._flushResourceBlockEnabled",
			HashSet::new);
	private static final ThreadLocal<Set<String>>
		_flushResourcePermissionEnabled = new CentralizedThreadLocal<>(
			PermissionThreadLocal.class + "._flushResourcePermissionEnabled",
			HashSet::new);
	private static final ThreadLocal<PermissionChecker> _permissionChecker =
		new CentralizedThreadLocal<>(
			PermissionThreadLocal.class + "._permissionChecker", null,
			Function.identity(), true);

}