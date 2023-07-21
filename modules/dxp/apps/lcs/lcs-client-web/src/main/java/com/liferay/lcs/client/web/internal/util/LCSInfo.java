/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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