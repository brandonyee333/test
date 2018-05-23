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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Ivica Cardic
 */
public class ProxyHostUtil {

	public static String getProxyAuthType() {
		String proxyAuthType = PortletPropsValues.PROXY_AUTH_TYPE;

		if (Validator.isNull(proxyAuthType)) {
			proxyAuthType = System.getProperty("http.proxyAuthType");
		}

		return proxyAuthType;
	}

	public static String getProxyDomain() {
		String proxyDomain = PortletPropsValues.PROXY_DOMAIN;

		if (Validator.isNull(proxyDomain)) {
			proxyDomain = System.getProperty("http.proxyDomain");
		}

		return proxyDomain;
	}

	public static String getProxyHostLogin() {
		String proxyHostLogin = PortletPropsValues.PROXY_HOST_LOGIN;

		if (Validator.isNull(proxyHostLogin)) {
			proxyHostLogin = System.getProperty("http.proxyUser");
		}

		return proxyHostLogin;
	}

	public static String getProxyHostName() {
		String proxyHostName = PortletPropsValues.PROXY_HOST_NAME;

		if (Validator.isNull(proxyHostName)) {
			proxyHostName = System.getProperty("http.proxyHost");
		}

		return proxyHostName;
	}

	public static String getProxyHostPassword() {
		String proxyHostPassword = PortletPropsValues.PROXY_HOST_PASSWORD;

		if (Validator.isNull(proxyHostPassword)) {
			proxyHostPassword = System.getProperty("http.proxyPassword");
		}

		return proxyHostPassword;
	}

	public static int getProxyHostPort() {
		int proxyHostPort = PortletPropsValues.PROXY_HOST_PORT;

		if (proxyHostPort == 0) {
			proxyHostPort = GetterUtil.getInteger(
				System.getProperty("http.proxyPort"));
		}

		return proxyHostPort;
	}

	public static String getProxyWorkstation() {
		String proxyWorkstation = PortletPropsValues.PROXY_WORKSTATION;

		if (Validator.isNull(proxyWorkstation)) {
			proxyWorkstation = System.getProperty("http.proxyWorkstation");
		}

		return proxyWorkstation;
	}

}