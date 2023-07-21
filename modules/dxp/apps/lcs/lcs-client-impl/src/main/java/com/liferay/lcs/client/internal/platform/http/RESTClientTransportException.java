/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.platform.http;

/**
 * @author Ivica Cardic
 */
public class RESTClientTransportException extends RESTClientException {

	public RESTClientTransportException(String message) {
		super(message);
	}

	public RESTClientTransportException(String message, int status) {
		super(message, status);
	}

	public RESTClientTransportException(String message, Throwable cause) {
		super(message, cause);
	}

	public RESTClientTransportException(Throwable cause) {
		super(cause);
	}

	public static class AuthenticationFailure
		extends RESTClientTransportException {

		public AuthenticationFailure(String message) {
			super(message);
		}

		public AuthenticationFailure(String message, Throwable cause) {
			super(message, cause);
		}

		public AuthenticationFailure(Throwable cause) {
			super(cause);
		}

	}

	public static class CommunicationFailure
		extends RESTClientTransportException {

		public CommunicationFailure(String message, int status) {
			super(message, status);
		}

		public CommunicationFailure(String message, Throwable cause) {
			super(message, cause);
		}

		public CommunicationFailure(Throwable cause) {
			super(cause);
		}

	}

	public static class SigningFailure extends RESTClientTransportException {

		public SigningFailure(String message, int status) {
			super(message, status);
		}

		public SigningFailure(String message, Throwable cause) {
			super(message, cause);
		}

		public SigningFailure(Throwable cause) {
			super(cause);
		}

	}

}