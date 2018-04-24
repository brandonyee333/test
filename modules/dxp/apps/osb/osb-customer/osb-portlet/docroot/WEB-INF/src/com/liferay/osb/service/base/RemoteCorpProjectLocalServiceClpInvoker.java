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

import com.liferay.osb.service.RemoteCorpProjectLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class RemoteCorpProjectLocalServiceClpInvoker {
	public RemoteCorpProjectLocalServiceClpInvoker() {
		_methodName262 = "getOSGiServiceIdentifier";

		_methodParameterTypes262 = new String[] {  };

		_methodName265 = "addCorpProject";

		_methodParameterTypes265 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName266 = "addCorpProjectUsers";

		_methodParameterTypes266 = new String[] { "long", "long[][]" };

		_methodName267 = "addCorpProjectUsers";

		_methodParameterTypes267 = new String[] { "java.lang.String", "long[][]" };

		_methodName268 = "addUserCorpProjectRoles";

		_methodParameterTypes268 = new String[] { "long", "long[][]", "long" };

		_methodName269 = "addUserCorpProjectRoles";

		_methodParameterTypes269 = new String[] {
				"java.lang.String", "long[][]", "long"
			};

		_methodName270 = "deleteCorpProject";

		_methodParameterTypes270 = new String[] { "long" };

		_methodName271 = "updateCorpProject";

		_methodParameterTypes271 = new String[] { "long", "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName262.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes262, parameterTypes)) {
			return RemoteCorpProjectLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName265.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes265, parameterTypes)) {
			return RemoteCorpProjectLocalServiceUtil.addCorpProject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);
		}

		if (_methodName266.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes266, parameterTypes)) {
			RemoteCorpProjectLocalServiceUtil.addCorpProjectUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName267.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes267, parameterTypes)) {
			RemoteCorpProjectLocalServiceUtil.addCorpProjectUsers((java.lang.String)arguments[0],
				(long[])arguments[1]);

			return null;
		}

		if (_methodName268.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes268, parameterTypes)) {
			RemoteCorpProjectLocalServiceUtil.addUserCorpProjectRoles(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName269.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes269, parameterTypes)) {
			RemoteCorpProjectLocalServiceUtil.addUserCorpProjectRoles((java.lang.String)arguments[0],
				(long[])arguments[1], ((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName270.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes270, parameterTypes)) {
			RemoteCorpProjectLocalServiceUtil.deleteCorpProject(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName271.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes271, parameterTypes)) {
			RemoteCorpProjectLocalServiceUtil.updateCorpProject(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

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
	private String _methodName270;
	private String[] _methodParameterTypes270;
	private String _methodName271;
	private String[] _methodParameterTypes271;
}