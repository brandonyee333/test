/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.proxy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import oasis.names.tc.wsrp.v1.bind.WSRP_v1_ServiceDescription_Binding_SOAPStub;
import oasis.names.tc.wsrp.v1.intf.WSRP_v1_ServiceDescription_PortType;
import oasis.names.tc.wsrp.v1.types.GetServiceDescription;
import oasis.names.tc.wsrp.v1.types.ServiceDescription;

/**
 * @author Michael Young
 */
public class ServiceDescriptionServiceHandler implements InvocationHandler {

	public ServiceDescriptionServiceHandler(
		WSRP_v1_ServiceDescription_Binding_SOAPStub serviceDescriptionService) {

		_serviceDescriptionService = serviceDescriptionService;
	}

	public Object doInvoke(Object proxy, Method method, Object[] args)
		throws Exception {

		Object v1Bean = TypeConvertorUtil.convert(args[0], 2);

		GetServiceDescription getServiceDescription =
			(GetServiceDescription)v1Bean;

		ServiceDescription serviceDescription =
			_serviceDescriptionService.getServiceDescription(
				getServiceDescription);

		return TypeConvertorUtil.convert(serviceDescription, 1);
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
		ServiceDescriptionServiceHandler.class);

	private final WSRP_v1_ServiceDescription_PortType
		_serviceDescriptionService;

}