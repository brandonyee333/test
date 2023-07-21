/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl;

import java.net.URL;

import java.util.Objects;

/**
 * @author Raymond Augé
 */
public class URLWrapper {

	public URLWrapper(URL url) {
		_url = url;

		_urlString = _url.toString();

		_hashCode = _urlString.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof URLWrapper)) {
			return false;
		}

		URLWrapper urlWrapper = (URLWrapper)obj;

		if ((_url == urlWrapper._url) ||
			Objects.equals(_urlString, urlWrapper._urlString)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return _hashCode;
	}

	private final int _hashCode;
	private URL _url;
	private final String _urlString;

}