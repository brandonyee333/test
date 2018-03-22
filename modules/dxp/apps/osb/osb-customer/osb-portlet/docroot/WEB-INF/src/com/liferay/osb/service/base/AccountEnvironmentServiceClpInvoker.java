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
		_methodName364 = "getOSGiServiceIdentifier";

		_methodParameterTypes364 = new String[] {  };

		_methodName369 = "addAccountEnvironment";

		_methodParameterTypes369 = new String[] {
				"long", "long", "java.lang.String", "int", "java.lang.String",
				"int", "int", "int", "int", "java.util.List", "java.util.List"
			};

		_methodName370 = "deleteAccountEnvironment";

		_methodParameterTypes370 = new String[] { "long" };

		_methodName371 = "getAccountEnvironment";

		_methodParameterTypes371 = new String[] { "long" };

		_methodName372 = "getAccountEnvironments";

		_methodParameterTypes372 = new String[] { "long" };

		_methodName373 = "getAccountEnvironmentsMap";

		_methodParameterTypes373 = new String[] { "long" };

		_methodName374 = "updateAccountEnvironment";

		_methodParameterTypes374 = new String[] {
				"long", "long", "java.lang.String", "int", "java.lang.String",
				"int", "int", "int", "int", "java.util.List", "java.util.List"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName364.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName369.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes369, parameterTypes)) {
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

		if (_methodName370.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes370, parameterTypes)) {
			return AccountEnvironmentServiceUtil.deleteAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName371.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes371, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getAccountEnvironments(((Long)arguments[0]).longValue());
		}

		if (_methodName373.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes373, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getAccountEnvironmentsMap(((Long)arguments[0]).longValue());
		}

		if (_methodName374.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes374, parameterTypes)) {
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

	private String _methodName364;
	private String[] _methodParameterTypes364;
	private String _methodName369;
	private String[] _methodParameterTypes369;
	private String _methodName370;
	private String[] _methodParameterTypes370;
	private String _methodName371;
	private String[] _methodParameterTypes371;
	private String _methodName372;
	private String[] _methodParameterTypes372;
	private String _methodName373;
	private String[] _methodParameterTypes373;
	private String _methodName374;
	private String[] _methodParameterTypes374;
}