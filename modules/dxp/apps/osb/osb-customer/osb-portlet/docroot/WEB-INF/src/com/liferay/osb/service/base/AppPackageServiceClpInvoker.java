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

import com.liferay.osb.service.AppPackageServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AppPackageServiceClpInvoker {
	public AppPackageServiceClpInvoker() {
		_methodName664 = "getBeanIdentifier";

		_methodParameterTypes664 = new String[] {  };

		_methodName665 = "setBeanIdentifier";

		_methodParameterTypes665 = new String[] { "java.lang.String" };

		_methodName670 = "addAppPackage";

		_methodParameterTypes670 = new String[] { "long", "long", "int", "boolean" };

		_methodName671 = "addAppPackageSrc";

		_methodParameterTypes671 = new String[] {
				"long", "java.lang.String", "java.io.File"
			};

		_methodName672 = "deleteAppPackage";

		_methodParameterTypes672 = new String[] { "long" };

		_methodName673 = "deleteAppPackageSrc";

		_methodParameterTypes673 = new String[] { "long" };

		_methodName674 = "fetchAppPackage";

		_methodParameterTypes674 = new String[] { "long", "int" };

		_methodName675 = "getAppPackage";

		_methodParameterTypes675 = new String[] { "long" };

		_methodName676 = "updateAppPackage";

		_methodParameterTypes676 = new String[] { "long", "boolean" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName664.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes664, parameterTypes)) {
			return AppPackageServiceUtil.getBeanIdentifier();
		}

		if (_methodName665.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes665, parameterTypes)) {
			AppPackageServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName670.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes670, parameterTypes)) {
			return AppPackageServiceUtil.addAppPackage(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName671.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes671, parameterTypes)) {
			AppPackageServiceUtil.addAppPackageSrc(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.io.File)arguments[2]);

			return null;
		}

		if (_methodName672.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes672, parameterTypes)) {
			return AppPackageServiceUtil.deleteAppPackage(((Long)arguments[0]).longValue());
		}

		if (_methodName673.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes673, parameterTypes)) {
			AppPackageServiceUtil.deleteAppPackageSrc(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName674.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes674, parameterTypes)) {
			return AppPackageServiceUtil.fetchAppPackage(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName675.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes675, parameterTypes)) {
			return AppPackageServiceUtil.getAppPackage(((Long)arguments[0]).longValue());
		}

		if (_methodName676.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes676, parameterTypes)) {
			return AppPackageServiceUtil.updateAppPackage(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName664;
	private String[] _methodParameterTypes664;
	private String _methodName665;
	private String[] _methodParameterTypes665;
	private String _methodName670;
	private String[] _methodParameterTypes670;
	private String _methodName671;
	private String[] _methodParameterTypes671;
	private String _methodName672;
	private String[] _methodParameterTypes672;
	private String _methodName673;
	private String[] _methodParameterTypes673;
	private String _methodName674;
	private String[] _methodParameterTypes674;
	private String _methodName675;
	private String[] _methodParameterTypes675;
	private String _methodName676;
	private String[] _methodParameterTypes676;
}