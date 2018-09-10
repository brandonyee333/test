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

package com.liferay.lcs.advisor;

import com.liferay.lcs.exception.InitializationException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.File;
import java.io.IOException;

import java.util.Arrays;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSKeyAdvisor {

	public void clearCache() {
		_key = null;
	}

	public synchronized String getKey() {
		if (_key != null) {
			return _key;
		}

		_readKey();

		return _key;
	}

	public synchronized void updateKey(String key) {
		try {
			File lcsServerIdFile = new File(_LCS_KEY_FILE_PATH);

			FileUtil.write(lcsServerIdFile, key.getBytes());

			_key = key;

			if (_log.isInfoEnabled()) {
				_log.info("LCS key updated to value " + _key);
			}
		}
		catch (IOException ioe) {
			throw new InitializationException.FileSystemAccessException(
				_LCS_KEY_FILE_PATH, ioe);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private void _readKey() {
		byte[] lcsServerIdBytes = null;

		try {
			File lcsServerIdFile = new File(_LCS_KEY_FILE_PATH);

			if (!lcsServerIdFile.exists()) {
				if (_log.isDebugEnabled()) {
					_log.debug("LCS key file doesn't exist");
				}

				return;
			}

			lcsServerIdBytes = FileUtil.getBytes(lcsServerIdFile);

			_key = Arrays.toString(lcsServerIdBytes);

			if (_log.isDebugEnabled()) {
				_log.debug("LCS key detected and red from file system " + _key);
			}
		}
		catch (IOException ioe) {
			throw new InitializationException.FileSystemAccessException(
				_LCS_KEY_FILE_PATH, ioe);
		}
	}

	private static final String _LCS_KEY_FILE_PATH =
		PropsUtil.get(PropsKeys.LIFERAY_HOME) +
			"/data/license/server/lcsServerId";

	private static final Log _log = LogFactoryUtil.getLog(LCSKeyAdvisor.class);

	private String _key;

}