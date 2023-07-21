/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Connor McKay
 */
public class PersistedModelLocalServiceRegistryUtil {

	public static PersistedModelLocalService getPersistedModelLocalService(
		String className) {

		return getPersistedModelLocalServiceRegistry().
			getPersistedModelLocalService(className);
	}

	public static PersistedModelLocalServiceRegistry
		getPersistedModelLocalServiceRegistry() {

		return _persistedModelLocalServiceRegistry;
	}

	public static List<PersistedModelLocalService>
		getPersistedModelLocalServices() {

		return getPersistedModelLocalServiceRegistry().
			getPersistedModelLocalServices();
	}

	public static boolean isPermissionedModelLocalService(String className) {
		return getPersistedModelLocalServiceRegistry().
			isPermissionedModelLocalService(className);
	}

	public static void register(
		String className,
		PersistedModelLocalService persistedModelLocalService) {

		getPersistedModelLocalServiceRegistry().register(
			className, persistedModelLocalService);
	}

	public static void unregister(String className) {
		getPersistedModelLocalServiceRegistry().unregister(className);
	}

	public void setPersistedModelLocalServiceRegistry(
		PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_persistedModelLocalServiceRegistry =
			persistedModelLocalServiceRegistry;
	}

	private static PersistedModelLocalServiceRegistry
		_persistedModelLocalServiceRegistry;

}