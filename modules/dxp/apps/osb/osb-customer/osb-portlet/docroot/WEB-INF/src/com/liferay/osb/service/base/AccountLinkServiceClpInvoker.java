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

import com.liferay.osb.service.AccountLinkServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountLinkServiceClpInvoker {
	public AccountLinkServiceClpInvoker() {
		_methodName358 = "getOSGiServiceIdentifier";

		_methodParameterTypes358 = new String[] {  };

		_methodName363 = "addAccountLinks";

		_methodParameterTypes363 = new String[] { "long", "java.lang.String[][]" };

		_methodName364 = "deleteAccountLink";

		_methodParameterTypes364 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName358.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes358, parameterTypes)) {
			return AccountLinkServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName363.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes363, parameterTypes)) {
			AccountLinkServiceUtil.addAccountLinks(((Long)arguments[0]).longValue(),
				(java.lang.String[])arguments[1]);

			return null;
		}

		if (_methodName364.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
			return AccountLinkServiceUtil.deleteAccountLink(((Long)arguments[0]).longValue());
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