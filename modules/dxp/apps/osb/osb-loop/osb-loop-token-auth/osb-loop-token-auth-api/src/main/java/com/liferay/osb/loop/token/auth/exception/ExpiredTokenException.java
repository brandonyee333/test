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

package com.liferay.osb.loop.token.auth.exception;

import com.liferay.portal.kernel.security.auth.AuthException;

/**
 * @author Bruno Farache
 */
public class ExpiredTokenException extends AuthException {

	public ExpiredTokenException() {
	}

	public ExpiredTokenException(String msg) {
		super(msg);
	}

	public ExpiredTokenException(String token, Throwable cause) {
		super("Token " + token + " is expired", cause);

		_token = token;
	}

	public ExpiredTokenException(Throwable cause) {
		super(cause);
	}

	public String getToken() {
		return _token;
	}

	private String _token;

}