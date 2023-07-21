/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.support.tomcat.poller.comet;

import com.liferay.portal.kernel.poller.comet.CometException;
import com.liferay.portal.kernel.poller.comet.CometResponse;

import org.apache.catalina.comet.CometEvent;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
 */
@Deprecated
public class CatalinaCometResponse implements CometResponse {

	public CatalinaCometResponse(CometEvent cometEvent) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void close() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isOpen() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void writeData(byte[] data) throws CometException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void writeData(String data) throws CometException {
		throw new UnsupportedOperationException();
	}

}