/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.exception;

/**
 * @author Matija Petanjek
 */
public class LCSHandshakeException extends RuntimeException {

	public LCSHandshakeException() {
	}

	public LCSHandshakeException(String msg) {
		super(msg);
	}

	public LCSHandshakeException(String msg, int errorCode) {
		super(msg);

		_errorCode = errorCode;
	}

	public LCSHandshakeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public LCSHandshakeException(Throwable cause) {
		super(cause);
	}

	public int getErrorCode() {
		return _errorCode;
	}

	@Override
	public String getMessage() {
		if (_errorCode != -1) {
			return super.getMessage() + ", errorCode is " + _errorCode;
		}

		return super.getMessage();
	}

	private int _errorCode = -1;

}