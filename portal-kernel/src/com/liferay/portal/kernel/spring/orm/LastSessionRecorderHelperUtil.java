/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.spring.orm;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Shuyang Zhou
 */
public class LastSessionRecorderHelperUtil {

	public static void syncLastSessionState() {
		_lastSessionRecorderHelper.syncLastSessionState();
	}

	public void setLastSessionRecorderHelper(
		LastSessionRecorderHelper lastSessionRecorderHelper) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_lastSessionRecorderHelper = lastSessionRecorderHelper;
	}

	private static LastSessionRecorderHelper _lastSessionRecorderHelper;

}