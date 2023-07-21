/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.support.tomcat.poller.comet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.comet.CometEvent;
import org.apache.catalina.comet.CometProcessor;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
 */
@Deprecated
public class CatalinaCometProcessor
	extends HttpServlet implements CometProcessor {

	@Override
	public void destroy() {
		throw new UnsupportedOperationException();
	}

	public void event(CometEvent cometEvent) throws ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		throw new UnsupportedOperationException();
	}

	protected void closeConnection(
			CometEvent cometEvent, HttpServletRequest request,
			HttpSession session)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	protected void doEvent(CometEvent cometEvent) throws Exception {
		throw new UnsupportedOperationException();
	}

	protected void readData(
			CometEvent cometEvent, HttpServletRequest request,
			HttpSession session)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	protected void startCometHandler(
			CometEvent cometEvent, HttpServletRequest request,
			HttpSession session)
		throws Exception {

		throw new UnsupportedOperationException();
	}

}