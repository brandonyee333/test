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

import com.liferay.osb.service.SecurityPatchServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class SecurityPatchServiceClpInvoker {
	public SecurityPatchServiceClpInvoker() {
		_methodName674 = "getBeanIdentifier";

		_methodParameterTypes674 = new String[] {  };

		_methodName675 = "setBeanIdentifier";

		_methodParameterTypes675 = new String[] { "java.lang.String" };

		_methodName680 = "getSecurityPatch";

		_methodParameterTypes680 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName674.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes674, parameterTypes)) {
			return SecurityPatchServiceUtil.getBeanIdentifier();
		}

		if (_methodName675.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes675, parameterTypes)) {
			SecurityPatchServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName680.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes680, parameterTypes)) {
			return SecurityPatchServiceUtil.getSecurityPatch(((Long)arguments[0]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName674;
	private String[] _methodParameterTypes674;
	private String _methodName675;
	private String[] _methodParameterTypes675;
	private String _methodName680;
	private String[] _methodParameterTypes680;
}