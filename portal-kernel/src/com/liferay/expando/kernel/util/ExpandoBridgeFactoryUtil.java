/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.expando.kernel.util;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class ExpandoBridgeFactoryUtil {

	public static ExpandoBridge getExpandoBridge(
		long companyId, String className) {

		PortalRuntimePermission.checkExpandoBridge(className);

		return getExpandoBridgeFactory().getExpandoBridge(companyId, className);
	}

	public static ExpandoBridge getExpandoBridge(
		long companyId, String className, long classPK) {

		PortalRuntimePermission.checkExpandoBridge(className);

		return getExpandoBridgeFactory().getExpandoBridge(
			companyId, className, classPK);
	}

	public static ExpandoBridgeFactory getExpandoBridgeFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			ExpandoBridgeFactoryUtil.class);

		return _expandoBridgeFactory;
	}

	public void setExpandoBridgeFactory(
		ExpandoBridgeFactory expandoBridgeFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_expandoBridgeFactory = expandoBridgeFactory;
	}

	private static ExpandoBridgeFactory _expandoBridgeFactory;

}