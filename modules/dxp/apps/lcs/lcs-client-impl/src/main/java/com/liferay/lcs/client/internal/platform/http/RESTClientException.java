/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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