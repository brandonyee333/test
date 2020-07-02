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