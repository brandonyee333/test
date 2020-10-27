/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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