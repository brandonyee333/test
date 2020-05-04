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

import org.json.JSONObject;

import org.springframework.http.HttpStatus;

/**
 * @author Marcellus Tavares
 */
public class OSBAsahException extends RuntimeException {

	public OSBAsahException(HttpStatus httpStatus) {
		_httpStatus = httpStatus;
	}

	public OSBAsahException(HttpStatus httpStatus, String message) {
		super(message);

		_httpStatus = httpStatus;
	}

	public OSBAsahException(
		JSONObject debugInfoJSONObject, HttpStatus httpStatus, String message) {

		super(message);

		_debugInfoJSONObject = debugInfoJSONObject;
		_httpStatus = httpStatus;
	}

	public JSONObject getDebugInfoJSONObject() {
		return _debugInfoJSONObject;
	}

	public HttpStatus getHttpStatus() {
		return _httpStatus;
	}

	private JSONObject _debugInfoJSONObject;
	private final HttpStatus _httpStatus;

}