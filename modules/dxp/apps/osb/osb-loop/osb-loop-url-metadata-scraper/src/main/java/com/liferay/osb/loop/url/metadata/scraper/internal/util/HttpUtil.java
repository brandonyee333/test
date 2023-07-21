/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.url.metadata.scraper.internal.util;

/**
 * @author Evan Thibodeau
 */
public class HttpUtil {

	public static final String HTTP = "http";

	public static final String HTTP_WITH_SLASH = "http://";

	public static final String HTTPS_WITH_SLASH = "https://";

	public static final String PROTOCOL_DELIMITER = "://";

	public static final char SLASH = '/';

	public static String getDomain(String url) {
		if (Validator.isNull(url)) {
			return url;
		}

		url = removeProtocol(url);

		int pos = url.indexOf(SLASH);

		if (pos != -1) {
			return url.substring(0, pos);
		}

		return url;
	}

	public static String getProtocol(String url) {
		if (Validator.isNull(url)) {
			return url;
		}

		int pos = url.indexOf(PROTOCOL_DELIMITER);

		if (pos != -1) {
			return url.substring(0, pos);
		}

		return HTTP;
	}

	public static String removeProtocol(String url) {
		if (Validator.isNull(url)) {
			return url;
		}

		if (url.startsWith(HTTP_WITH_SLASH)) {
			return url.substring(HTTP_WITH_SLASH.length());
		}
		else if (url.startsWith(HTTPS_WITH_SLASH)) {
			return url.substring(HTTPS_WITH_SLASH.length());
		}

		return url;
	}

}