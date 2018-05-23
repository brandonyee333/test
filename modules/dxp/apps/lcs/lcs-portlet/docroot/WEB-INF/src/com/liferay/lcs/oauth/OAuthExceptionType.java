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

package com.liferay.lcs.oauth;

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