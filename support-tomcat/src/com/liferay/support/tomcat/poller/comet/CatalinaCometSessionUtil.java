/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.support.tomcat.poller.comet;

import org.apache.catalina.comet.CometEvent;

/**
 * @author Edward Han
 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
 */
@Deprecated
public class CatalinaCometSessionUtil {

	public static String getSessionId(CometEvent cometEvent) {
		throw new UnsupportedOperationException();
	}

}