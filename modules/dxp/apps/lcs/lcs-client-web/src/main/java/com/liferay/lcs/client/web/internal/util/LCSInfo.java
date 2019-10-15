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

package com.liferay.lcs.client.web.internal.util;

/**
 * @author Igor Beslic
 */
public class LCSInfo {

	public static final String LRDCOM_LCS_CLIENT_DOWNLOAD_URL =
		"https://web.liferay.com/marketplace/-/mp/application/71774947";

	public static final String LRDCOM_LCS_PRODUCT_PAGE_URL =
		"https://www.liferay.com/supporting-products/liferay-connected-" +
			"services";

	public static final String LRDCOM_SALES_EMAIL_ADDRESS = "sales@liferay.com";

	public static final String LRDCOM_SUPPORT_URL =
		"https://help.liferay.com/hc/en-us/requests/new";

	public static String getUserDocumentationUrl(int dxpMajorInfo) {
		if (dxpMajorInfo == 70) {
			return _LRDCOM_70_LCS_USER_DOCUMENTATION_URL;
		}
		else if (dxpMajorInfo == 71) {
			return _LRDCOM_71_LCS_USER_DOCUMENTATION_URL;
		}
		else if (dxpMajorInfo == 72) {
			return _LRDCOM_72_LCS_USER_DOCUMENTATION_URL;
		}

		return _LRDCOM_LCS_USER_DOCUMENTATION_URL;
	}

	private static final String _LRDCOM_70_LCS_USER_DOCUMENTATION_URL =
		"https://help.liferay.com/hc/en-us/sections/360002520631-Managing-" +
			"Liferay-DXP-with-Liferay-Connected-Services";

	private static final String _LRDCOM_71_LCS_USER_DOCUMENTATION_URL =
		"https://help.liferay.com/hc/en-us/sections/360002520711-Managing-" +
			"Liferay-DXP-with-Liferay-Connected-Services";

	private static final String _LRDCOM_72_LCS_USER_DOCUMENTATION_URL =
		"https://help.liferay.com/hc/en-us/sections/360004655891-Managing-" +
			"Liferay-DXP-with-Liferay-Connected-Services";

	private static final String _LRDCOM_LCS_USER_DOCUMENTATION_URL =
		"https://help.liferay.com/hc/en-us/articles/360018230712";

}