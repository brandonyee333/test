/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.constants;

import com.liferay.portal.kernel.util.Time;

/**
 * @author Igor Beslic
 */
public class LCSClientConstants {

	public static final long COMMAND_MESSAGE_CHECK_INTERVAL = 20 * Time.SECOND;

	public static final long COMMAND_QUEUE_CHECK_INTERVAL = 330;

	public static final long HEARTBEAT_INTERVAL = Time.MINUTE;

	public static final int LCS_CLIENT_BUILD_NUMBER = 700;

	public static final String LCS_CLIENT_VERSION = "7.0.0";

}