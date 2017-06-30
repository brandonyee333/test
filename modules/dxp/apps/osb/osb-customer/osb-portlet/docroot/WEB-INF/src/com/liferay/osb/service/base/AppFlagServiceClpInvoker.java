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

import com.liferay.osb.service.AppFlagServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AppFlagServiceClpInvoker {
	public AppFlagServiceClpInvoker() {
		_methodName664 = "getBeanIdentifier";

		_methodParameterTypes664 = new String[] {  };

		_methodName665 = "setBeanIdentifier";

		_methodParameterTypes665 = new String[] { "java.lang.String" };

		_methodName670 = "addAppFlag";

		_methodParameterTypes670 = new String[] { "long", "long", "int" };

		_methodName671 = "deleteAppFlag";

		_methodParameterTypes671 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName664.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes664, parameterTypes)) {
			return AppFlagServiceUtil.getBeanIdentifier();
		}

		if (_methodName665.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes665, parameterTypes)) {
			AppFlagServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName670.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes670, parameterTypes)) {
			return AppFlagServiceUtil.addAppFlag(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName671.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes671, parameterTypes)) {
			return AppFlagServiceUtil.deleteAppFlag(((Long)arguments[0]).longValue());
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
}