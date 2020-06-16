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