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

import com.liferay.osb.service.AccountEnvironmentServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountEnvironmentServiceClpInvoker {
	public AccountEnvironmentServiceClpInvoker() {
		_methodName208 = "getOSGiServiceIdentifier";

		_methodParameterTypes208 = new String[] {  };

		_methodName213 = "addAccountEnvironment";

		_methodParameterTypes213 = new String[] {
				"long", "long", "java.lang.String", "int", "java.lang.String",
				"int", "int", "int", "int", "java.util.List", "java.util.List"
			};

		_methodName214 = "deleteAccountEnvironment";

		_methodParameterTypes214 = new String[] { "long" };

		_methodName215 = "getAccountEnvironment";

		_methodParameterTypes215 = new String[] { "long" };

		_methodName216 = "getAccountEnvironments";

		_methodParameterTypes216 = new String[] { "long" };

		_methodName217 = "getAccountEnvironmentsMap";

		_methodParameterTypes217 = new String[] { "long" };

		_methodName218 = "updateAccountEnvironment";

		_methodParameterTypes218 = new String[] {
				"long", "long", "java.lang.String", "int", "java.lang.String",
				"int", "int", "int", "int", "java.util.List", "java.util.List"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName208.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes208, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName213.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes213, parameterTypes)) {
			return AccountEnvironmentServiceUtil.addAccountEnvironment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				(java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[9],
				(java.util.List<java.lang.Integer>)arguments[10]);
		}

		if (_methodName214.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes214, parameterTypes)) {
			return AccountEnvironmentServiceUtil.deleteAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName215.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes215, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName216.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes216, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getAccountEnvironments(((Long)arguments[0]).longValue());
		}

		if (_methodName217.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes217, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getAccountEnvironmentsMap(((Long)arguments[0]).longValue());
		}

		if (_methodName218.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes218, parameterTypes)) {
			return AccountEnvironmentServiceUtil.updateAccountEnvironment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				(java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[9],
				(java.util.List<java.lang.Integer>)arguments[10]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName208;
	private String[] _methodParameterTypes208;
	private String _methodName213;
	private String[] _methodParameterTypes213;
	private String _methodName214;
	private String[] _methodParameterTypes214;
	private String _methodName215;
	private String[] _methodParameterTypes215;
	private String _methodName216;
	private String[] _methodParameterTypes216;
	private String _methodName217;
	private String[] _methodParameterTypes217;
	private String _methodName218;
	private String[] _methodParameterTypes218;
}