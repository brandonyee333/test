/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.proxy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import oasis.names.tc.wsrp.v1.bind.WSRP_v1_Registration_Binding_SOAPStub;
import oasis.names.tc.wsrp.v1.intf.WSRP_v1_Registration_PortType;
import oasis.names.tc.wsrp.v1.types.RegistrationContext;
import oasis.names.tc.wsrp.v1.types.RegistrationData;
import oasis.names.tc.wsrp.v2.types.Register;

/**
 * @author Michael Young
 */
public class RegistrationServiceHandler implements InvocationHandler {

	public RegistrationServiceHandler(
		WSRP_v1_Registration_Binding_SOAPStub registrationDescriptionService) {

		_registrationDescriptionService = registrationDescriptionService;
	}

	public Object doInvoke(Object proxy, Method method, Object[] args)
		throws Exception {

		Register register = (Register)args[0];

		Object v1Bean = TypeConvertorUtil.convert(
			register.getRegistrationData(), 2);

		RegistrationData registrationData = (RegistrationData)v1Bean;

		RegistrationContext registrationContext =
			_registrationDescriptionService.register(registrationData);

		return TypeConvertorUtil.convert(registrationContext, 1);
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
		RegistrationServiceHandler.class);

	private final WSRP_v1_Registration_PortType _registrationDescriptionService;

}