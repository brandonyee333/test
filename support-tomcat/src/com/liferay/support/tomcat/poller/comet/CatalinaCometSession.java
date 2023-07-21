/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.support.tomcat.poller.comet;

import com.liferay.portal.kernel.poller.comet.BaseCometSession;

import org.apache.catalina.comet.CometEvent;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
 */
@Deprecated
public class CatalinaCometSession extends BaseCometSession {

	public CatalinaCometSession(CometEvent cometEvent) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getAttribute(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setAttribute(String name, Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void doClose() {
		throw new UnsupportedOperationException();
	}

}