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