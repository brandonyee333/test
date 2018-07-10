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
		_methodName160 = "getOSGiServiceIdentifier";

		_methodParameterTypes160 = new String[] {  };

		_methodName163 = "addOrganizationUsers";

		_methodParameterTypes163 = new String[] { "long", "long[][]" };

		_methodName164 = "addRoleUsers";

		_methodParameterTypes164 = new String[] { "long", "long[][]" };

		_methodName165 = "deleteRoleUser";

		_methodParameterTypes165 = new String[] { "long", "long" };

		_methodName166 = "fetchUserByEmailAddress";

		_methodParameterTypes166 = new String[] { "java.lang.String" };

		_methodName167 = "getUserByUuid";

		_methodParameterTypes167 = new String[] { "java.lang.String" };

		_methodName168 = "updateUserExpandoValue";

		_methodParameterTypes168 = new String[] {
				"long", "java.lang.String", "java.lang.Object"
			};

		_methodName170 = "synchronize";

		_methodParameterTypes170 = new String[] { "long" };

		_methodName171 = "translate";

		_methodParameterTypes171 = new String[] {
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName172 = "unsetOrganizationUsers";

		_methodParameterTypes172 = new String[] { "long", "long[][]" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName160.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes160, parameterTypes)) {
			return RemoteUserLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName163.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes163, parameterTypes)) {
			RemoteUserLocalServiceUtil.addOrganizationUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName164.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes164, parameterTypes)) {
			RemoteUserLocalServiceUtil.addRoleUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName165.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes165, parameterTypes)) {
			RemoteUserLocalServiceUtil.deleteRoleUser(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName166.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes166, parameterTypes)) {
			return RemoteUserLocalServiceUtil.fetchUserByEmailAddress((java.lang.String)arguments[0]);
		}

		if (_methodName167.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes167, parameterTypes)) {
			return RemoteUserLocalServiceUtil.getUserByUuid((java.lang.String)arguments[0]);
		}

		if (_methodName168.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes168, parameterTypes)) {
			RemoteUserLocalServiceUtil.updateUserExpandoValue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.Object)arguments[2]);

			return null;
		}

		if (_methodName170.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes170, parameterTypes)) {
			RemoteUserLocalServiceUtil.synchronize(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName171.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes171, parameterTypes)) {
			return RemoteUserLocalServiceUtil.translate((com.liferay.portal.kernel.json.JSONObject)arguments[0]);
		}

		if (_methodName172.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes172, parameterTypes)) {
			RemoteUserLocalServiceUtil.unsetOrganizationUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName160;
	private String[] _methodParameterTypes160;
	private String _methodName163;
	private String[] _methodParameterTypes163;
	private String _methodName164;
	private String[] _methodParameterTypes164;
	private String _methodName165;
	private String[] _methodParameterTypes165;
	private String _methodName166;
	private String[] _methodParameterTypes166;
	private String _methodName167;
	private String[] _methodParameterTypes167;
	private String _methodName168;
	private String[] _methodParameterTypes168;
	private String _methodName170;
	private String[] _methodParameterTypes170;
	private String _methodName171;
	private String[] _methodParameterTypes171;
	private String _methodName172;
	private String[] _methodParameterTypes172;
}