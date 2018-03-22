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

import com.liferay.osb.service.RemoteUserLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class RemoteUserLocalServiceClpInvoker {
	public RemoteUserLocalServiceClpInvoker() {
		_methodName262 = "getOSGiServiceIdentifier";

		_methodParameterTypes262 = new String[] {  };

		_methodName265 = "addOrganizationUsers";

		_methodParameterTypes265 = new String[] { "long", "long[][]" };

		_methodName266 = "addRoleUsers";

		_methodParameterTypes266 = new String[] { "long", "long[][]" };

		_methodName267 = "deleteRoleUser";

		_methodParameterTypes267 = new String[] { "long", "long" };

		_methodName268 = "fetchUserByEmailAddress";

		_methodParameterTypes268 = new String[] { "java.lang.String" };

		_methodName269 = "unsetOrganizationUsers";

		_methodParameterTypes269 = new String[] { "long", "long[][]" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName262.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes262, parameterTypes)) {
			return RemoteUserLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName265.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes265, parameterTypes)) {
			RemoteUserLocalServiceUtil.addOrganizationUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName266.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes266, parameterTypes)) {
			RemoteUserLocalServiceUtil.addRoleUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName267.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes267, parameterTypes)) {
			RemoteUserLocalServiceUtil.deleteRoleUser(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName268.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes268, parameterTypes)) {
			return RemoteUserLocalServiceUtil.fetchUserByEmailAddress((java.lang.String)arguments[0]);
		}

		if (_methodName269.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes269, parameterTypes)) {
			RemoteUserLocalServiceUtil.unsetOrganizationUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName262;
	private String[] _methodParameterTypes262;
	private String _methodName265;
	private String[] _methodParameterTypes265;
	private String _methodName266;
	private String[] _methodParameterTypes266;
	private String _methodName267;
	private String[] _methodParameterTypes267;
	private String _methodName268;
	private String[] _methodParameterTypes268;
	private String _methodName269;
	private String[] _methodParameterTypes269;
}