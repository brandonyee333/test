/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

import java.io.InputStream;
import java.io.OutputStream;

import java.nio.channels.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class StreamUtil {

	public static void cleanUp(Channel channel) {
		try {
			if (channel != null) {
				channel.close();
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage(), e);
			}
		}
	}

	public static void cleanUp(InputStream inputStream) {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage(), e);
			}
		}
	}

	public static void cleanUp(OutputStream outputStream) {
		try {
			if (outputStream != null) {
				outputStream.flush();
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage(), e);
			}
		}

		try {
			if (outputStream != null) {
				outputStream.close();
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage(), e);
			}
		}
	}

	private static final Logger _log = LoggerFactory.getLogger(
		StreamUtil.class);

}