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

package com.liferay.lcs.exception;

/**
 * @author Ivica Cardic
 */
public class InitializationException extends RuntimeException {

	public InitializationException(String message) {
		super(message);
	}

	public InitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InitializationException(Throwable cause) {
		super(cause);
	}

	public static class FileSystemAccessException
		extends InitializationException {

		public FileSystemAccessException(Exception e) {
			super(e);
		}

		public FileSystemAccessException(String path, Exception e) {
			super("Unable to access path " + path, e);
		}

	}

	public static class KeyStoreException extends InitializationException {

		public KeyStoreException(Exception e) {
			super("Unable to access key store", e);
		}

	}

}