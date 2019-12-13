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

package com.liferay.osb.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.AccountCustomerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountCustomerServiceClpInvoker {
	public AccountCustomerServiceClpInvoker() {
		_methodName210 = "getOSGiServiceIdentifier";

		_methodParameterTypes210 = new String[] {  };

		_methodName215 = "getCorpProjectAccountCustomerUsers";

		_methodParameterTypes215 = new String[] { "java.lang.String" };

		_methodName216 = "getCorpProjectAccountCustomerUUIDs";

		_methodParameterTypes216 = new String[] { "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName210.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes210, parameterTypes)) {
			return AccountCustomerServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName215.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes215, parameterTypes)) {
			return AccountCustomerServiceUtil.getCorpProjectAccountCustomerUsers((java.lang.String)arguments[0]);
		}

		if (_methodName216.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes216, parameterTypes)) {
			return AccountCustomerServiceUtil.getCorpProjectAccountCustomerUUIDs((java.lang.String)arguments[0]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName210;
	private String[] _methodParameterTypes210;
	private String _methodName215;
	private String[] _methodParameterTypes215;
	private String _methodName216;
	private String[] _methodParameterTypes216;
}