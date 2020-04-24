/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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