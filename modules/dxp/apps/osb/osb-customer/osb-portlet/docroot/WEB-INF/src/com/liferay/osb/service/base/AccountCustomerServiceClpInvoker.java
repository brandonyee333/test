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
		_methodName378 = "getOSGiServiceIdentifier";

		_methodParameterTypes378 = new String[] {  };

		_methodName383 = "addAccountCustomers";

		_methodParameterTypes383 = new String[] {
				"long[][]", "long", "int[][]", "int[][]"
			};

		_methodName384 = "deleteAccountCustomers";

		_methodParameterTypes384 = new String[] { "long[][]", "long" };

		_methodName385 = "getCorpProjectAccountCustomerUUIDs";

		_methodParameterTypes385 = new String[] { "long" };

		_methodName386 = "toggleNotifications";

		_methodParameterTypes386 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName378.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes378, parameterTypes)) {
			return AccountCustomerServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName383.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes383, parameterTypes)) {
			AccountCustomerServiceUtil.addAccountCustomers((long[])arguments[0],
				((Long)arguments[1]).longValue(), (int[])arguments[2],
				(int[])arguments[3]);

			return null;
		}

		if (_methodName384.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes384, parameterTypes)) {
			AccountCustomerServiceUtil.deleteAccountCustomers((long[])arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName385.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes385, parameterTypes)) {
			return AccountCustomerServiceUtil.getCorpProjectAccountCustomerUUIDs(((Long)arguments[0]).longValue());
		}

		if (_methodName386.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes386, parameterTypes)) {
			AccountCustomerServiceUtil.toggleNotifications(((Long)arguments[0]).longValue());

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName378;
	private String[] _methodParameterTypes378;
	private String _methodName383;
	private String[] _methodParameterTypes383;
	private String _methodName384;
	private String[] _methodParameterTypes384;
	private String _methodName385;
	private String[] _methodParameterTypes385;
	private String _methodName386;
	private String[] _methodParameterTypes386;
}