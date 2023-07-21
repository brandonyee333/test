/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.webserver;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 * @since  6.1, replaced com.liferay.portal.kernel.servlet.ImageServletTokenUtil
 */
public class WebServerServletTokenUtil {

	public static String getToken(long imageId) {
		return getWebServerServletToken().getToken(imageId);
	}

	public static WebServerServletToken getWebServerServletToken() {
		PortalRuntimePermission.checkGetBeanProperty(
			WebServerServletTokenUtil.class);

		return _webServerServletToken;
	}

	public static void resetToken(long imageId) {
		getWebServerServletToken().resetToken(imageId);
	}

	public void setWebServerServletToken(
		WebServerServletToken webServerServletToken) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_webServerServletToken = webServerServletToken;
	}

	private static WebServerServletToken _webServerServletToken;

}