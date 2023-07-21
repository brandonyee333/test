/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.exception;

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