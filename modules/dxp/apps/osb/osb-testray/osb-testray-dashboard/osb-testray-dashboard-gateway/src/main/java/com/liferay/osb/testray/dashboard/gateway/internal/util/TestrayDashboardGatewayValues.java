/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.dashboard.gateway.internal.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Ethan Bustad
 */
public class TestrayDashboardGatewayValues {

	public static final int API_CACHE_TIMEOUT_SECONDS = GetterUtil.getInteger(
		TestrayDashboardGatewayUtil.get("api.cache.timeout.seconds"));

	public static final String[] AVAILABLE_APIS = StringUtil.split(
		TestrayDashboardGatewayUtil.get("available.apis"));

	public static final String JENKINS_USER_NAME =
		TestrayDashboardGatewayUtil.get("jenkins.user.name");

	public static final String JENKINS_USER_TOKEN =
		TestrayDashboardGatewayUtil.get("jenkins.user.token");

}