/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.servlet;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.types.RegistrationContext;
import oasis.names.tc.wsrp.v2.types.SessionContext;

/**
 * @author Michael Young
 */
public class ServiceHolder {

	public WSRP_v2_Markup_PortType getMarkupService() {
		return _markupService;
	}

	public RegistrationContext getRegistrationContext() {
		return _registrationContext;
	}

	public SessionContext getSessionContext() {
		return _sessionContext;
	}

	public void setMarkupService(WSRP_v2_Markup_PortType markupService) {
		_markupService = markupService;
	}

	public void setRegistrationContext(
		RegistrationContext registrationContext) {

		_registrationContext = registrationContext;
	}

	public void setSessionContext(SessionContext sessionContext) {
		_sessionContext = sessionContext;
	}

	private WSRP_v2_Markup_PortType _markupService;
	private RegistrationContext _registrationContext;
	private SessionContext _sessionContext;

}