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

package com.liferay.lcs.util;

import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSConnectionManagerUtil {

	public static LCSConnectionManager getLCSConnectionManager() {
		return _lcsConnectionManager;
	}

	public static Map<String, String> getLCSConnectionMetadata() {
		return getLCSConnectionManager().getLCSConnectionMetadata();
	}

	public static boolean isLCSGatewayAvailable() {
		return getLCSConnectionManager().isLCSGatewayAvailable();
	}

	public static boolean isReady() {
		return getLCSConnectionManager().isReady();
	}

	public void setLCSConnectionManager(
		LCSConnectionManager lcsConnectionManager) {

		_lcsConnectionManager = lcsConnectionManager;
	}

	private static LCSConnectionManager _lcsConnectionManager;

}