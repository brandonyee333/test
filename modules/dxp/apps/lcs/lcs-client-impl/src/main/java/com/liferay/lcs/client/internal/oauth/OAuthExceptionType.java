/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.oauth;

/**
 * @author Igor Beslic
 */
public enum OAuthExceptionType {

	CONSUMER_KEY_REFUSED("oAuthConsumerKeyRefused"),
	TIMESTAMP_REFUSED("oAuthTimestampRefused"),
	TOKEN_REJECTED("oAuthTokenRejected");

	public String getKey() {
		return _key;
	}

	private OAuthExceptionType(String key) {
		_key = key;
	}

	private final String _key;

}