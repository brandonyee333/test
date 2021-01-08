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

package com.liferay.osb.asah.backend.model;

import com.liferay.osb.asah.backend.constants.DataConstants;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Adolfo Pérez
 */
public class URL {

	public static URL any() {
		return _URL_ANY;
	}

	public static URL url(String url) {
		return new URL(url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof URL)) {
			return false;
		}

		URL url = (URL)obj;

		if (Objects.equals(_url, url._url)) {
			return true;
		}

		return false;
	}

	public String getURL() {
		return _url;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_url);
	}

	private URL(String url) {
		if (StringUtils.isEmpty(url)) {
			url = DataConstants.ANY;
		}

		_url = url;
	}

	private static final URL _URL_ANY = new URL(DataConstants.ANY);

	private final String _url;

}