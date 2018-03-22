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
		_methodName358 = "getOSGiServiceIdentifier";

		_methodParameterTypes358 = new String[] {  };

		_methodName363 = "deleteAccountProject";

		_methodParameterTypes363 = new String[] { "long" };

		_methodName364 = "updateAccountProject";

		_methodParameterTypes364 = new String[] {
				"long", "long", "java.lang.String", "java.util.Map"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName358.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes358, parameterTypes)) {
			return AccountProjectServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName363.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes363, parameterTypes)) {
			return AccountProjectServiceUtil.deleteAccountProject(((Long)arguments[0]).longValue());
		}

		if (_methodName364.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
			return AccountProjectServiceUtil.updateAccountProject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.util.Map<java.lang.Integer, java.lang.String>)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName358;
	private String[] _methodParameterTypes358;
	private String _methodName363;
	private String[] _methodParameterTypes363;
	private String _methodName364;
	private String[] _methodParameterTypes364;
}