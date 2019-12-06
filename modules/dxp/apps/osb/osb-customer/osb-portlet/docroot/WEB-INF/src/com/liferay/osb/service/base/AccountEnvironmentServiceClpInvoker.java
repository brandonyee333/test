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
		_methodName204 = "getOSGiServiceIdentifier";

		_methodParameterTypes204 = new String[] {  };

		_methodName209 = "addAccountEnvironment";

		_methodParameterTypes209 = new String[] {
				"long", "long", "java.lang.String", "int", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.util.List", "java.util.List"
			};

		_methodName210 = "deleteAccountEnvironment";

		_methodParameterTypes210 = new String[] { "long" };

		_methodName211 = "getAccountEnvironment";

		_methodParameterTypes211 = new String[] { "long" };

		_methodName212 = "getAccountEnvironments";

		_methodParameterTypes212 = new String[] { "long" };

		_methodName213 = "getAccountEnvironmentsMap";

		_methodParameterTypes213 = new String[] { "long" };

		_methodName214 = "updateAccountEnvironment";

		_methodParameterTypes214 = new String[] {
				"long", "long", "java.lang.String", "int", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.util.List", "java.util.List"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName204.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes204, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName209.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes209, parameterTypes)) {
			return AccountEnvironmentServiceUtil.addAccountEnvironment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				(java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				(java.lang.String)arguments[12],
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[13],
				(java.util.List<java.lang.Integer>)arguments[14]);
		}

		if (_methodName210.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes210, parameterTypes)) {
			return AccountEnvironmentServiceUtil.deleteAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName211.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes211, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName212.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes212, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getAccountEnvironments(((Long)arguments[0]).longValue());
		}

		if (_methodName213.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes213, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getAccountEnvironmentsMap(((Long)arguments[0]).longValue());
		}

		if (_methodName214.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes214, parameterTypes)) {
			return AccountEnvironmentServiceUtil.updateAccountEnvironment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				(java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				(java.lang.String)arguments[12],
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[13],
				(java.util.List<java.lang.Integer>)arguments[14]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName204;
	private String[] _methodParameterTypes204;
	private String _methodName209;
	private String[] _methodParameterTypes209;
	private String _methodName210;
	private String[] _methodParameterTypes210;
	private String _methodName211;
	private String[] _methodParameterTypes211;
	private String _methodName212;
	private String[] _methodParameterTypes212;
	private String _methodName213;
	private String[] _methodParameterTypes213;
	private String _methodName214;
	private String[] _methodParameterTypes214;
}