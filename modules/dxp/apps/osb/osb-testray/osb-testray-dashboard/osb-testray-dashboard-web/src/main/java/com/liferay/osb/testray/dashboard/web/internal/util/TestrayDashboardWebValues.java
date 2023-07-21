/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.dashboard.web.internal.util;

import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Ethan Bustad
 */
public class TestrayDashboardWebValues {

	public static final String[] AUTO_LOGIN_IPS = StringUtil.split(
		TestrayDashboardWebUtil.get("osb.testray.auto.login.ips"));

	public static final String AUTO_LOGIN_USER_EMAIL_ADDRESS =
		"dashboard.user@liferay.com";

}