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

package com.liferay.osb.testray.dashboard.web.internal.util;

import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Ethan Bustad
 */
public class TestrayDashboardWebValues {

	public static final String[] AUTO_LOGIN_IPS = StringUtil.split(
		TestrayDashboardWebUtil.get("auto.login.ips"));

	public static final String AUTO_LOGIN_USER_EMAIL_ADDRESS =
		TestrayDashboardWebUtil.get("auto.login.user.email.address");

}