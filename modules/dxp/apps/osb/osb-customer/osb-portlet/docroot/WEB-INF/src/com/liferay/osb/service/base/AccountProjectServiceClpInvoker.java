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

import com.liferay.osb.service.AccountProjectServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountProjectServiceClpInvoker {
	public AccountProjectServiceClpInvoker() {
		_methodName202 = "getOSGiServiceIdentifier";

		_methodParameterTypes202 = new String[] {  };

		_methodName207 = "deleteAccountProject";

		_methodParameterTypes207 = new String[] { "long" };

		_methodName208 = "updateAccountProject";

		_methodParameterTypes208 = new String[] {
				"long", "long", "java.lang.String", "java.util.Map"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName202.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes202, parameterTypes)) {
			return AccountProjectServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName207.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes207, parameterTypes)) {
			return AccountProjectServiceUtil.deleteAccountProject(((Long)arguments[0]).longValue());
		}

		if (_methodName208.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes208, parameterTypes)) {
			return AccountProjectServiceUtil.updateAccountProject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.util.Map<java.lang.Integer, java.lang.String>)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName202;
	private String[] _methodParameterTypes202;
	private String _methodName207;
	private String[] _methodParameterTypes207;
	private String _methodName208;
	private String[] _methodParameterTypes208;
}