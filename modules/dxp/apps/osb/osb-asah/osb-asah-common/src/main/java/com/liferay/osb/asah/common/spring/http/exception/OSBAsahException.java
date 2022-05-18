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

package com.liferay.osb.asah.common.spring.http.exception;

import org.apache.commons.lang3.StringUtils;

import org.springframework.http.HttpStatus;

/**
 * @author Marcellus Tavares
 */
public class OSBAsahException extends RuntimeException {

	public OSBAsahException(HttpStatus httpStatus, String message) {
		this(httpStatus, message, null);
	}

	public OSBAsahException(
		HttpStatus httpStatus, String message, String messageKey,
		Throwable throwable) {

		super(message, throwable);

		_httpStatus = httpStatus;
		_messageKey = messageKey;
	}

	public OSBAsahException(
		HttpStatus httpStatus, String message, Throwable throwable) {

		this(
			httpStatus, message,
			StringUtils.replaceAll(StringUtils.lowerCase(message), "\\s+", "-"),
			throwable);
	}

	public HttpStatus getHttpStatus() {
		return _httpStatus;
	}

	public String getMessageKey() {
		return _messageKey;
	}

	private final HttpStatus _httpStatus;
	private final String _messageKey;

}