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
		_methodName366 = "getOSGiServiceIdentifier";

		_methodParameterTypes366 = new String[] {  };

		_methodName371 = "addAccountLinks";

		_methodParameterTypes371 = new String[] { "long", "java.lang.String[][]" };

		_methodName372 = "deleteAccountLink";

		_methodParameterTypes372 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName366.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes366, parameterTypes)) {
			return AccountLinkServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName371.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes371, parameterTypes)) {
			AccountLinkServiceUtil.addAccountLinks(((Long)arguments[0]).longValue(),
				(java.lang.String[])arguments[1]);

			return null;
		}

		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			return AccountLinkServiceUtil.deleteAccountLink(((Long)arguments[0]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName366;
	private String[] _methodParameterTypes366;
	private String _methodName371;
	private String[] _methodParameterTypes371;
	private String _methodName372;
	private String[] _methodParameterTypes372;
}