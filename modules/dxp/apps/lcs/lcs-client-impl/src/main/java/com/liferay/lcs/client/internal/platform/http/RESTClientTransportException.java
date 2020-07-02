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