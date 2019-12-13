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
		_methodName168 = "getOSGiServiceIdentifier";

		_methodParameterTypes168 = new String[] {  };

		_methodName171 = "addOrganizationUsers";

		_methodParameterTypes171 = new String[] { "long", "long[][]" };

		_methodName172 = "addRoleUsers";

		_methodParameterTypes172 = new String[] { "long", "long[][]" };

		_methodName173 = "deleteRoleUser";

		_methodParameterTypes173 = new String[] { "long", "long" };

		_methodName174 = "fetchUserByEmailAddress";

		_methodParameterTypes174 = new String[] { "java.lang.String" };

		_methodName175 = "getUserByUuid";

		_methodParameterTypes175 = new String[] { "java.lang.String" };

		_methodName176 = "synchronize";

		_methodParameterTypes176 = new String[] { "long" };

		_methodName177 = "translate";

		_methodParameterTypes177 = new String[] {
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName178 = "unsetOrganizationUsers";

		_methodParameterTypes178 = new String[] { "long", "long[][]" };

		_methodName179 = "updateUserExpandoValue";

		_methodParameterTypes179 = new String[] {
				"long", "java.lang.String", "java.lang.Object"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName168.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes168, parameterTypes)) {
			return RemoteUserLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName171.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes171, parameterTypes)) {
			RemoteUserLocalServiceUtil.addOrganizationUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName172.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes172, parameterTypes)) {
			RemoteUserLocalServiceUtil.addRoleUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName173.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes173, parameterTypes)) {
			RemoteUserLocalServiceUtil.deleteRoleUser(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName174.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes174, parameterTypes)) {
			return RemoteUserLocalServiceUtil.fetchUserByEmailAddress((java.lang.String)arguments[0]);
		}

		if (_methodName175.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes175, parameterTypes)) {
			return RemoteUserLocalServiceUtil.getUserByUuid((java.lang.String)arguments[0]);
		}

		if (_methodName176.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes176, parameterTypes)) {
			RemoteUserLocalServiceUtil.synchronize(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName177.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes177, parameterTypes)) {
			return RemoteUserLocalServiceUtil.translate((com.liferay.portal.kernel.json.JSONObject)arguments[0]);
		}

		if (_methodName178.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes178, parameterTypes)) {
			RemoteUserLocalServiceUtil.unsetOrganizationUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName179.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes179, parameterTypes)) {
			RemoteUserLocalServiceUtil.updateUserExpandoValue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.Object)arguments[2]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName168;
	private String[] _methodParameterTypes168;
	private String _methodName171;
	private String[] _methodParameterTypes171;
	private String _methodName172;
	private String[] _methodParameterTypes172;
	private String _methodName173;
	private String[] _methodParameterTypes173;
	private String _methodName174;
	private String[] _methodParameterTypes174;
	private String _methodName175;
	private String[] _methodParameterTypes175;
	private String _methodName176;
	private String[] _methodParameterTypes176;
	private String _methodName177;
	private String[] _methodParameterTypes177;
	private String _methodName178;
	private String[] _methodParameterTypes178;
	private String _methodName179;
	private String[] _methodParameterTypes179;
}