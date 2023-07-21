/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.proxy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import oasis.names.tc.wsrp.v1.bind.WSRP_v1_PortletManagement_Binding_SOAPStub;
import oasis.names.tc.wsrp.v1.intf.WSRP_v1_PortletManagement_PortType;

/**
 * @author Michael Young
 */
public class PortletManagementServiceHandler implements InvocationHandler {

	public PortletManagementServiceHandler(
		WSRP_v1_PortletManagement_Binding_SOAPStub portletManagementService) {

		_portletManagementService = portletManagementService;
	}

	public Object doInvoke(Object proxy, Method method, Object[] args)
		throws Exception {

		System.out.println(_portletManagementService.getClass());

		return null;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {

		try {
			return doInvoke(proxy, method, args);
		}
		catch (Throwable t) {
			_log.error(t, t);

			throw t;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletManagementServiceHandler.class);

	private final WSRP_v1_PortletManagement_PortType _portletManagementService;

}