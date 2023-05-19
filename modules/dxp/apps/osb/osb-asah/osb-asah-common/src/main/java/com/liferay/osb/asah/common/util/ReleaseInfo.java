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

package com.liferay.osb.asah.common.util;

/**
 * @author Shinn Lok
 */
public class ReleaseInfo {

	public static int getSchemaVersion() {
		return _SCHEMA_VERSION;
	}

	public static String getVersion() {
		return _VERSION;
	}

	private static final int _SCHEMA_VERSION = 14;

	private static final String _VERSION = "3.6.1";

}