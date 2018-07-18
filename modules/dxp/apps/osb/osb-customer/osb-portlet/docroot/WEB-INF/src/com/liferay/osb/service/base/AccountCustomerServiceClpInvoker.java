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
		_methodName214 = "getOSGiServiceIdentifier";

		_methodParameterTypes214 = new String[] {  };

		_methodName219 = "getCorpProjectAccountCustomerUsers";

		_methodParameterTypes219 = new String[] { "java.lang.String" };

		_methodName220 = "getCorpProjectAccountCustomerUUIDs";

		_methodParameterTypes220 = new String[] { "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName214.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes214, parameterTypes)) {
			return AccountCustomerServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName219.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes219, parameterTypes)) {
			return AccountCustomerServiceUtil.getCorpProjectAccountCustomerUsers((java.lang.String)arguments[0]);
		}

		if (_methodName220.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes220, parameterTypes)) {
			return AccountCustomerServiceUtil.getCorpProjectAccountCustomerUUIDs((java.lang.String)arguments[0]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName214;
	private String[] _methodParameterTypes214;
	private String _methodName219;
	private String[] _methodParameterTypes219;
	private String _methodName220;
	private String[] _methodParameterTypes220;
}