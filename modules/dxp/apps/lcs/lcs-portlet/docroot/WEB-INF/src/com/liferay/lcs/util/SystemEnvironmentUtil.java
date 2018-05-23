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

package com.liferay.lcs.util;

import com.liferay.portal.kernel.util.Validator;

/**
 * @author Ivica Cardic
 */
public class SystemEnvironmentUtil {

	public static final String OSB_LCS_GATEWAY_WEB_HOST_NAME =
		"OSB_LCS_GATEWAY_WEB_HOST_NAME";

	public static final String OSB_LCS_GATEWAY_WEB_HOST_PORT =
		"OSB_LCS_GATEWAY_WEB_HOST_PORT";

	public static final String OSB_LCS_GATEWAY_WEB_PROTOCOL =
		"OSB_LCS_GATEWAY_WEB_PROTOCOL";

	public static final String OSB_LCS_PORTLET_HOST_NAME =
		"OSB_LCS_PORTLET_HOST_NAME";

	public static final String OSB_LCS_PORTLET_HOST_PORT =
		"OSB_LCS_PORTLET_HOST_PORT";

	public static final String OSB_LCS_PORTLET_OAUTH_CONSUMER_KEY =
		"OSB_LCS_PORTLET_OAUTH_CONSUMER_KEY";

	public static final String OSB_LCS_PORTLET_OAUTH_CONSUMER_SECRET =
		"OSB_LCS_PORTLET_OAUTH_CONSUMER_SECRET";

	public static final String OSB_LCS_PORTLET_PROTOCOL =
		"OSB_LCS_PORTLET_PROTOCOL";

	public static String getValue(String name, String defaultValue) {
		String value = System.getenv(name);

		if (Validator.isNull(value)) {
			return defaultValue;
		}

		return value;
	}

}