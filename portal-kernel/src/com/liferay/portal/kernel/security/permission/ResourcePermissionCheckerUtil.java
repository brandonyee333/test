/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.permission;

import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

/**
 * @author Roberto Díaz
 */
public class ResourcePermissionCheckerUtil {

	public static Boolean containsResourcePermission(
		PermissionChecker permissionChecker, String className, long classPK,
		String actionId) {

		ResourcePermissionChecker resourcePermissionChecker =
			_serviceTrackerMap.getService(className);

		if (resourcePermissionChecker == null) {
			return null;
		}

		Boolean resource = resourcePermissionChecker.checkResource(
			permissionChecker, classPK, actionId);

		if (resource != null) {
			return resource.booleanValue();
		}

		return null;
	}

	private static final ServiceTrackerMap<String, ResourcePermissionChecker>
		_serviceTrackerMap = ServiceTrackerCollections.openSingleValueMap(
			ResourcePermissionChecker.class, "resource.name");

}