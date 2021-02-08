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

package com.liferay.osb.asah.common.util;

import java.net.URI;
import java.net.URISyntaxException;

import java.nio.charset.StandardCharsets;

import org.springframework.web.util.UriUtils;

/**
 * @author André Miranda
 */
public class URLUtil {

	public static String decode(String url) {
		return UriUtils.decode(url, StandardCharsets.UTF_8);
	}

	public static URI toURI(String url) throws URISyntaxException {
		return new URI(UriUtils.encodePath(url, StandardCharsets.UTF_8));
	}

}