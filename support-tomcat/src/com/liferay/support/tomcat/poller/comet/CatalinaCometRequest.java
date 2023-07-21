/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.support.tomcat.poller.comet;

import com.liferay.portal.kernel.poller.comet.BaseCometRequest;

import java.util.Enumeration;
import java.util.Map;

import org.apache.catalina.comet.CometEvent;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
 */
@Deprecated
public class CatalinaCometRequest extends BaseCometRequest {

	public CatalinaCometRequest(CometEvent cometEvent) {
		super(null);

		throw new UnsupportedOperationException();
	}

	@Override
	public String getParameter(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<String> getParameterNames() {
		throw new UnsupportedOperationException();
	}

}