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

import com.liferay.osb.service.AccountInformationServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountInformationServiceClpInvoker {
	public AccountInformationServiceClpInvoker() {
		_methodName358 = "getOSGiServiceIdentifier";

		_methodParameterTypes358 = new String[] {  };

		_methodName363 = "updateAccountInformation";

		_methodParameterTypes363 = new String[] { "long", "long", "java.util.Map" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName358.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes358, parameterTypes)) {
			return AccountInformationServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName363.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes363, parameterTypes)) {
			return AccountInformationServiceUtil.updateAccountInformation(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Map<java.lang.Integer, java.lang.String>)arguments[2]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName358;
	private String[] _methodParameterTypes358;
	private String _methodName363;
	private String[] _methodParameterTypes363;
}