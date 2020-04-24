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
		else {
			return url;
		}
	}

}