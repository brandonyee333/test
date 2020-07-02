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

package com.liferay.lcs.client.internal.platform.http;

/**
 * @author Ivica Cardic
 */
public class RESTClientException extends Exception {

	public RESTClientException(String message) {
		super(message);
	}

	public RESTClientException(String message, int status) {
		super(message);

		_status = status;
	}

	public RESTClientException(String message, int status, Throwable cause) {
		super(message, cause);

		_status = status;
	}

	public RESTClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public RESTClientException(Throwable cause) {
		super(cause);
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	@Override
	public String toString() {
		String message = super.getMessage();

		if ((message != null) && (message.length() > 0)) {
			return message;
		}

		return "Server returned status " + _status;
	}

	private int _status;

}