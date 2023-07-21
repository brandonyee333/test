/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.exception;

/**
 * @author Igor Beslic
 */
public class CompressionException extends Exception {

	public CompressionException() {
	}

	public CompressionException(String message) {
		super(message);
	}

	public CompressionException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompressionException(Throwable cause) {
		super(cause);
	}

}