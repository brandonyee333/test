/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.resiliency.spi.agent.SPIAgentRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 * @author Brian Myunghun Kim
 */
public class SharedSessionServletRequest extends HttpServletRequestWrapper {

	public SharedSessionServletRequest(
		HttpServletRequest request, boolean shared) {

		super(request);

		_shared = shared;

		_portalSession = request.getSession();
	}

	@Override
	public HttpSession getSession() {
		return getSession(true);
	}

	@Override
	public HttpSession getSession(boolean create) {
		if (create) {
			checkPortalSession();
		}

		if (_shared) {
			return _portalSession;
		}

		HttpSession portletSession = super.getSession(create);

		if ((portletSession != null) && (portletSession != _portalSession)) {
			SPIAgentRequest.populatePortletSessionAttributes(
				this, portletSession);

			return getSharedSessionWrapper(_portalSession, portletSession);
		}

		return portletSession;
	}

	public HttpSession getSharedSession() {
		return _portalSession;
	}

	protected void checkPortalSession() {
		try {
			_portalSession.isNew();
		}
		catch (IllegalStateException ise) {
			_portalSession = super.getSession(true);
		}
	}

	protected HttpSession getSharedSessionWrapper(
		HttpSession portalSession, HttpSession portletSession) {

		if (ServerDetector.isJetty()) {
			return new JettySharedSessionWrapper(portalSession, portletSession);
		}

		return new SharedSessionWrapper(portalSession, portletSession);
	}

	private HttpSession _portalSession;
	private final boolean _shared;

}