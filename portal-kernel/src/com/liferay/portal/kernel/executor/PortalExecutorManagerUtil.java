/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.executor;

import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.security.pacl.PACLConstants;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author     Shuyang Zhou
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class PortalExecutorManagerUtil {

	public static ThreadPoolExecutor getPortalExecutor(String name) {
		PortalRuntimePermission.checkThreadPoolExecutor(name);

		return getPortalExecutorManager().getPortalExecutor(name);
	}

	public static ThreadPoolExecutor getPortalExecutor(
		String name, boolean createIfAbsent) {

		PortalRuntimePermission.checkThreadPoolExecutor(name);

		return getPortalExecutorManager().getPortalExecutor(
			name, createIfAbsent);
	}

	public static PortalExecutorManager getPortalExecutorManager() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortalExecutorManagerUtil.class);

		return _portalExecutorManager;
	}

	public static ThreadPoolExecutor registerPortalExecutor(
		String name, ThreadPoolExecutor threadPoolExecutor) {

		PortalRuntimePermission.checkThreadPoolExecutor(name);

		return getPortalExecutorManager().registerPortalExecutor(
			name, threadPoolExecutor);
	}

	public static void shutdown() {
		PortalRuntimePermission.checkThreadPoolExecutor(
			PACLConstants.PORTAL_RUNTIME_PERMISSION_THREAD_POOL_ALL_EXECUTORS);

		_portalExecutorManager.shutdown();
	}

	public static void shutdown(boolean interrupt) {
		PortalRuntimePermission.checkThreadPoolExecutor(
			PACLConstants.PORTAL_RUNTIME_PERMISSION_THREAD_POOL_ALL_EXECUTORS);

		_portalExecutorManager.shutdown(interrupt);
	}

	private PortalExecutorManagerUtil() {
	}

	private static volatile PortalExecutorManager _portalExecutorManager =
		ServiceProxyFactory.newServiceTrackedInstance(
			PortalExecutorManager.class, PortalExecutorManagerUtil.class,
			"_portalExecutorManager", true);

}