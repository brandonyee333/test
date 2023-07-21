/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

/**
 * @author Shinn Lok
 */
public class ReleaseInfo {

	public static final int getBuildNumber() {
		return _BUILD_NUMBER;
	}

	public static final int getFeatureSet() {
		return _FEATURE_SET;
	}

	public static final String getVersion() {
		return _VERSION;
	}

	private static final String _BUILD = "3411";

	private static final int _BUILD_NUMBER = Integer.parseInt(_BUILD);

	private static final int _FEATURE_SET = 1;

	private static final String _VERSION = "3.4.11";

}