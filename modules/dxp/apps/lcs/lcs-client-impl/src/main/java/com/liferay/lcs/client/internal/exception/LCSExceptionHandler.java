/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.exception;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Ivica Cardic
 */
public class LCSExceptionHandler {

	public static void debug(String message, Throwable t) {
		if (_log.isDebugEnabled()) {
			_log.debug(message, t);
		}
	}

	public static void debug(Throwable t) {
		if (_log.isDebugEnabled()) {
			_log.debug(t);
		}
	}

	public static void error(String message, Throwable t) {
		_log.error(message, t);
	}

	public static void error(Throwable t) {
		_log.error(t);
	}

	public static Throwable getRootCause(Throwable throwable) {
		while (throwable.getCause() != null) {
			throwable = throwable.getCause();
		}

		return throwable;
	}

	public static void info(String message, Throwable t) {
		if (_log.isInfoEnabled()) {
			_log.info(message, t);
		}
	}

	public static void info(Throwable t) {
		if (_log.isInfoEnabled()) {
			_log.info(t);
		}
	}

	public static void warn(String message, Throwable t) {
		if (_log.isWarnEnabled()) {
			_log.warn(message, t);
		}
	}

	public static void warn(Throwable t) {
		if (_log.isWarnEnabled()) {
			_log.warn(t);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSExceptionHandler.class);

}