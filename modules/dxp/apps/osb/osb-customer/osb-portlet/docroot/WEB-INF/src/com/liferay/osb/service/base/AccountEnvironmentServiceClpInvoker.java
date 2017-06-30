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

import com.liferay.osb.service.AccountEnvironmentServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AccountEnvironmentServiceClpInvoker {
	public AccountEnvironmentServiceClpInvoker() {
		_methodName668 = "getBeanIdentifier";

		_methodParameterTypes668 = new String[] {  };

		_methodName669 = "setBeanIdentifier";

		_methodParameterTypes669 = new String[] { "java.lang.String" };

		_methodName674 = "addAccountEnvironment";

		_methodParameterTypes674 = new String[] {
				"long", "long", "java.lang.String", "int", "java.lang.String",
				"int", "int", "int", "int", "java.util.List", "java.util.List"
			};

		_methodName675 = "deleteAccountEnvironment";

		_methodParameterTypes675 = new String[] { "long" };

		_methodName676 = "getAccountEnvironment";

		_methodParameterTypes676 = new String[] { "long" };

		_methodName677 = "getAccountEnvironments";

		_methodParameterTypes677 = new String[] { "long" };

		_methodName678 = "getAccountEnvironmentsMap";

		_methodParameterTypes678 = new String[] { "long" };

		_methodName679 = "updateAccountEnvironment";

		_methodParameterTypes679 = new String[] {
				"long", "long", "java.lang.String", "int", "java.lang.String",
				"int", "int", "int", "int", "java.util.List", "java.util.List"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName668.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes668, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getBeanIdentifier();
		}

		if (_methodName669.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes669, parameterTypes)) {
			AccountEnvironmentServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName674.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes674, parameterTypes)) {
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

		if (_methodName675.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes675, parameterTypes)) {
			return AccountEnvironmentServiceUtil.deleteAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName676.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes676, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName677.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes677, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getAccountEnvironments(((Long)arguments[0]).longValue());
		}

		if (_methodName678.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes678, parameterTypes)) {
			return AccountEnvironmentServiceUtil.getAccountEnvironmentsMap(((Long)arguments[0]).longValue());
		}

		if (_methodName679.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes679, parameterTypes)) {
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

	private String _methodName668;
	private String[] _methodParameterTypes668;
	private String _methodName669;
	private String[] _methodParameterTypes669;
	private String _methodName674;
	private String[] _methodParameterTypes674;
	private String _methodName675;
	private String[] _methodParameterTypes675;
	private String _methodName676;
	private String[] _methodParameterTypes676;
	private String _methodName677;
	private String[] _methodParameterTypes677;
	private String _methodName678;
	private String[] _methodParameterTypes678;
	private String _methodName679;
	private String[] _methodParameterTypes679;
}