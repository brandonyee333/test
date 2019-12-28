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
		_methodName62 = "getOSGiServiceIdentifier";

		_methodParameterTypes62 = new String[] {  };

		_methodName65 = "addOrganizationUsers";

		_methodParameterTypes65 = new String[] { "long", "long[][]" };

		_methodName66 = "addRoleUsers";

		_methodParameterTypes66 = new String[] { "long", "long[][]" };

		_methodName67 = "deleteRoleUser";

		_methodParameterTypes67 = new String[] { "long", "long" };

		_methodName68 = "fetchUserByEmailAddress";

		_methodParameterTypes68 = new String[] { "java.lang.String" };

		_methodName69 = "getUserByUuid";

		_methodParameterTypes69 = new String[] { "java.lang.String" };

		_methodName70 = "synchronize";

		_methodParameterTypes70 = new String[] { "long" };

		_methodName71 = "translate";

		_methodParameterTypes71 = new String[] {
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName72 = "unsetOrganizationUsers";

		_methodParameterTypes72 = new String[] { "long", "long[][]" };

		_methodName73 = "updateUserExpandoValue";

		_methodParameterTypes73 = new String[] {
				"long", "java.lang.String", "java.lang.Object"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return RemoteUserLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			RemoteUserLocalServiceUtil.addOrganizationUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			RemoteUserLocalServiceUtil.addRoleUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			RemoteUserLocalServiceUtil.deleteRoleUser(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return RemoteUserLocalServiceUtil.fetchUserByEmailAddress((java.lang.String)arguments[0]);
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return RemoteUserLocalServiceUtil.getUserByUuid((java.lang.String)arguments[0]);
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			RemoteUserLocalServiceUtil.synchronize(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return RemoteUserLocalServiceUtil.translate((com.liferay.portal.kernel.json.JSONObject)arguments[0]);
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			RemoteUserLocalServiceUtil.unsetOrganizationUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			RemoteUserLocalServiceUtil.updateUserExpandoValue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.Object)arguments[2]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName66;
	private String[] _methodParameterTypes66;
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName70;
	private String[] _methodParameterTypes70;
	private String _methodName71;
	private String[] _methodParameterTypes71;
	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
}