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

import com.liferay.osb.service.AppPackagePluginServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AppPackagePluginServiceClpInvoker {
	public AppPackagePluginServiceClpInvoker() {
		_methodName670 = "getBeanIdentifier";

		_methodParameterTypes670 = new String[] {  };

		_methodName671 = "setBeanIdentifier";

		_methodParameterTypes671 = new String[] { "java.lang.String" };

		_methodName676 = "addAppPackagePlugin";

		_methodParameterTypes676 = new String[] {
				"long", "java.lang.String", "java.io.File", "int", "boolean"
			};

		_methodName677 = "deleteAppPackagePlugin";

		_methodParameterTypes677 = new String[] { "long" };

		_methodName678 = "deleteInvalidAppPackagePlugins";

		_methodParameterTypes678 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName670.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes670, parameterTypes)) {
			return AppPackagePluginServiceUtil.getBeanIdentifier();
		}

		if (_methodName671.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes671, parameterTypes)) {
			AppPackagePluginServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName676.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes676, parameterTypes)) {
			return AppPackagePluginServiceUtil.addAppPackagePlugin(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.io.File)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Boolean)arguments[4]).booleanValue());
		}

		if (_methodName677.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes677, parameterTypes)) {
			return AppPackagePluginServiceUtil.deleteAppPackagePlugin(((Long)arguments[0]).longValue());
		}

		if (_methodName678.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes678, parameterTypes)) {
			AppPackagePluginServiceUtil.deleteInvalidAppPackagePlugins(((Long)arguments[0]).longValue());

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName670;
	private String[] _methodParameterTypes670;
	private String _methodName671;
	private String[] _methodParameterTypes671;
	private String _methodName676;
	private String[] _methodParameterTypes676;
	private String _methodName677;
	private String[] _methodParameterTypes677;
	private String _methodName678;
	private String[] _methodParameterTypes678;
}