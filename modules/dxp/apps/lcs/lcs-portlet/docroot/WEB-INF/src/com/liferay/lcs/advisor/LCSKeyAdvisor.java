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
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSKeyAdvisor implements LCSKeyAdvisor {

	@Override
	public void clearCache() {
		_key = null;
	}

	@Override
	public synchronized String getKey() {
		if (_key != null) {
			return _key;
		}

		_key = _readKey();

		return _key;
	}

	public synchronized void updateKey(String key) {
		try {
			File lcsServerIdFile = new File(
				_LICENSE_REPOSITORY_DIR + "/server/lcsServerId");

			FileUtil.write(lcsServerIdFile, key.getBytes());

			_key = key;
		}
		catch (IOException ioe) {
			throw new InitializationException.FileSystemAccessException(
				_LICENSE_REPOSITORY_DIR + "/server/lcsServerId", ioe);
		}
	}

	protected String getHostName(String lcsServerIdPropertiesString) {
		Matcher matcher = _hostNamePattern.matcher(lcsServerIdPropertiesString);

		if (matcher.find()) {
			return matcher.group(1);
		}

		return StringPool.BLANK;
	}

	private String _readKey() {
		byte[] lcsServerIdBytes = null;

		try {
			File lcsServerIdFile = new File(
				_LICENSE_REPOSITORY_DIR + "/server/lcsServerId");

			if (lcsServerIdFile.exists()) {
				lcsServerIdBytes = FileUtil.getBytes(lcsServerIdFile);

				return Arrays.toString(lcsServerIdBytes);
			}

			return null;
		}
		catch (IOException ioe) {
			throw new InitializationException.FileSystemAccessException(
				PropsKeys.LIFERAY_HOME + "/data/license/server/lcsServerId",
				ioe);
		}
	}

	private static final String _LICENSE_REPOSITORY_DIR =
		PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/data/license";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSKeyAdvisor.class);

	private static final Pattern _hostNamePattern = Pattern.compile(
		"hostName=(.*)(\\s?)");

	private String _key;

}