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
		_methodName154 = "getOSGiServiceIdentifier";

		_methodParameterTypes154 = new String[] {  };

		_methodName157 = "addCorpProject";

		_methodParameterTypes157 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName158 = "addCorpProjectUsers";

		_methodParameterTypes158 = new String[] { "long", "long[][]" };

		_methodName159 = "addCorpProjectUsers";

		_methodParameterTypes159 = new String[] { "java.lang.String", "long[][]" };

		_methodName160 = "addUserCorpProjectRoles";

		_methodParameterTypes160 = new String[] { "long", "long[][]", "long" };

		_methodName161 = "addUserCorpProjectRoles";

		_methodParameterTypes161 = new String[] {
				"java.lang.String", "long[][]", "long"
			};

		_methodName162 = "deleteCorpProject";

		_methodParameterTypes162 = new String[] { "long" };

		_methodName163 = "updateCorpProject";

		_methodParameterTypes163 = new String[] { "long", "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName154.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes154, parameterTypes)) {
			return RemoteCorpProjectLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName157.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes157, parameterTypes)) {
			return RemoteCorpProjectLocalServiceUtil.addCorpProject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);
		}

		if (_methodName158.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes158, parameterTypes)) {
			RemoteCorpProjectLocalServiceUtil.addCorpProjectUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName159.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes159, parameterTypes)) {
			RemoteCorpProjectLocalServiceUtil.addCorpProjectUsers((java.lang.String)arguments[0],
				(long[])arguments[1]);

			return null;
		}

		if (_methodName160.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes160, parameterTypes)) {
			RemoteCorpProjectLocalServiceUtil.addUserCorpProjectRoles(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName161.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes161, parameterTypes)) {
			RemoteCorpProjectLocalServiceUtil.addUserCorpProjectRoles((java.lang.String)arguments[0],
				(long[])arguments[1], ((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName162.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes162, parameterTypes)) {
			RemoteCorpProjectLocalServiceUtil.deleteCorpProject(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName163.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes163, parameterTypes)) {
			RemoteCorpProjectLocalServiceUtil.updateCorpProject(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName154;
	private String[] _methodParameterTypes154;
	private String _methodName157;
	private String[] _methodParameterTypes157;
	private String _methodName158;
	private String[] _methodParameterTypes158;
	private String _methodName159;
	private String[] _methodParameterTypes159;
	private String _methodName160;
	private String[] _methodParameterTypes160;
	private String _methodName161;
	private String[] _methodParameterTypes161;
	private String _methodName162;
	private String[] _methodParameterTypes162;
	private String _methodName163;
	private String[] _methodParameterTypes163;
}