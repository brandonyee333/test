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
		_methodName162 = "getOSGiServiceIdentifier";

		_methodParameterTypes162 = new String[] {  };

		_methodName165 = "addOrganizationUsers";

		_methodParameterTypes165 = new String[] { "long", "long[][]" };

		_methodName166 = "addRoleUsers";

		_methodParameterTypes166 = new String[] { "long", "long[][]" };

		_methodName167 = "deleteRoleUser";

		_methodParameterTypes167 = new String[] { "long", "long" };

		_methodName168 = "fetchUserByEmailAddress";

		_methodParameterTypes168 = new String[] { "java.lang.String" };

		_methodName169 = "getUserByUuid";

		_methodParameterTypes169 = new String[] { "java.lang.String" };

		_methodName170 = "synchronize";

		_methodParameterTypes170 = new String[] { "long" };

		_methodName171 = "translate";

		_methodParameterTypes171 = new String[] {
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName172 = "unsetOrganizationUsers";

		_methodParameterTypes172 = new String[] { "long", "long[][]" };

		_methodName173 = "updateUserExpandoValue";

		_methodParameterTypes173 = new String[] {
				"long", "java.lang.String", "java.lang.Object"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName162.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes162, parameterTypes)) {
			return RemoteUserLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName165.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes165, parameterTypes)) {
			RemoteUserLocalServiceUtil.addOrganizationUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName166.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes166, parameterTypes)) {
			RemoteUserLocalServiceUtil.addRoleUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName167.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes167, parameterTypes)) {
			RemoteUserLocalServiceUtil.deleteRoleUser(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName168.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes168, parameterTypes)) {
			return RemoteUserLocalServiceUtil.fetchUserByEmailAddress((java.lang.String)arguments[0]);
		}

		if (_methodName169.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes169, parameterTypes)) {
			return RemoteUserLocalServiceUtil.getUserByUuid((java.lang.String)arguments[0]);
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

		if (_methodName173.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes173, parameterTypes)) {
			RemoteUserLocalServiceUtil.updateUserExpandoValue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.Object)arguments[2]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName162;
	private String[] _methodParameterTypes162;
	private String _methodName165;
	private String[] _methodParameterTypes165;
	private String _methodName166;
	private String[] _methodParameterTypes166;
	private String _methodName167;
	private String[] _methodParameterTypes167;
	private String _methodName168;
	private String[] _methodParameterTypes168;
	private String _methodName169;
	private String[] _methodParameterTypes169;
	private String _methodName170;
	private String[] _methodParameterTypes170;
	private String _methodName171;
	private String[] _methodParameterTypes171;
	private String _methodName172;
	private String[] _methodParameterTypes172;
	private String _methodName173;
	private String[] _methodParameterTypes173;
}