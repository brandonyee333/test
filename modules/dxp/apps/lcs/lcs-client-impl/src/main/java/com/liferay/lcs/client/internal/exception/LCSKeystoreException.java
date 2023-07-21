/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.exception;

/**
 * @author Igor Beslic
 */
public class LCSKeystoreException extends RuntimeException {

	public LCSKeystoreException() {
	}

	public LCSKeystoreException(String msg) {
		super(msg);
	}

	public LCSKeystoreException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public LCSKeystoreException(Throwable cause) {
		super(cause);
	}

}