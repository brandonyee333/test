/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.kernel.util.Validator;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.types.ReleaseSessions;
import oasis.names.tc.wsrp.v2.types.SessionContext;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael Young
 * @author Peter Fellwock
 */
@Component(immediate = true, service = HttpSessionListener.class)
public class WSRPSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();

		Enumeration<String> enu = session.getAttributeNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			Object value = session.getAttribute(name);

			if (value instanceof TransientValue) {
				TransientValue<?> transientValue = (TransientValue<?>)value;

				releaseSessions(transientValue.getValue());
			}
		}
	}

	protected void releaseSessions(Object value) {
		if ((value == null) || !(value instanceof ServiceHolder)) {
			return;
		}

		ServiceHolder serviceHolder = (ServiceHolder)value;

		SessionContext sessionContext = serviceHolder.getSessionContext();

		if ((sessionContext == null) ||
			Validator.isNull(sessionContext.getSessionID())) {

			return;
		}

		WSRP_v2_Markup_PortType markupService =
			serviceHolder.getMarkupService();

		ReleaseSessions releaseSessions = new ReleaseSessions();

		releaseSessions.setRegistrationContext(
			serviceHolder.getRegistrationContext());

		String[] sessionIDs = {sessionContext.getSessionID()};

		releaseSessions.setSessionIDs(sessionIDs);

		try {
			markupService.releaseSessions(releaseSessions);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage());
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WSRPSessionListener.class);

}