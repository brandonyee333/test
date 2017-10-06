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
		_methodName356 = "getOSGiServiceIdentifier";

		_methodParameterTypes356 = new String[] {  };

		_methodName361 = "addAccountLinks";

		_methodParameterTypes361 = new String[] { "long", "java.lang.String[][]" };

		_methodName362 = "deleteAccountLink";

		_methodParameterTypes362 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName356.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes356, parameterTypes)) {
			return AccountLinkServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName361.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes361, parameterTypes)) {
			AccountLinkServiceUtil.addAccountLinks(((Long)arguments[0]).longValue(),
				(java.lang.String[])arguments[1]);

			return null;
		}

		if (_methodName362.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes362, parameterTypes)) {
			return AccountLinkServiceUtil.deleteAccountLink(((Long)arguments[0]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName356;
	private String[] _methodParameterTypes356;
	private String _methodName361;
	private String[] _methodParameterTypes361;
	private String _methodName362;
	private String[] _methodParameterTypes362;
}