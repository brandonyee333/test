/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.platform.http;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class RESTClientInvocationException extends RESTClientException {

	public RESTClientInvocationException(String message) {
		super(message);
	}

	public RESTClientInvocationException(String message, int status) {
		super(message, status);
	}

	public RESTClientInvocationException(
		String message, int status, Throwable cause) {

		super(message, status, cause);
	}

	public RESTClientInvocationException(String message, Throwable cause) {
		super(message, cause);
	}

	public RESTClientInvocationException(Throwable cause) {
		super(cause);
	}

}