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

import com.liferay.osb.service.CorpGroupServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class CorpGroupServiceClpInvoker {
	public CorpGroupServiceClpInvoker() {
		_methodName682 = "getBeanIdentifier";

		_methodParameterTypes682 = new String[] {  };

		_methodName683 = "setBeanIdentifier";

		_methodParameterTypes683 = new String[] { "java.lang.String" };

		_methodName688 = "addCorpGroup";

		_methodParameterTypes688 = new String[] {
				"java.lang.String", "java.util.Map", "java.io.File",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName689 = "deleteCorpGroup";

		_methodParameterTypes689 = new String[] { "long" };

		_methodName690 = "updateCorpGroup";

		_methodParameterTypes690 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.io.File",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName682.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes682, parameterTypes)) {
			return CorpGroupServiceUtil.getBeanIdentifier();
		}

		if (_methodName683.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes683, parameterTypes)) {
			CorpGroupServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName688.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes688, parameterTypes)) {
			return CorpGroupServiceUtil.addCorpGroup((java.lang.String)arguments[0],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.io.File)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName689.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes689, parameterTypes)) {
			return CorpGroupServiceUtil.deleteCorpGroup(((Long)arguments[0]).longValue());
		}

		if (_methodName690.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes690, parameterTypes)) {
			return CorpGroupServiceUtil.updateCorpGroup(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.io.File)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName682;
	private String[] _methodParameterTypes682;
	private String _methodName683;
	private String[] _methodParameterTypes683;
	private String _methodName688;
	private String[] _methodParameterTypes688;
	private String _methodName689;
	private String[] _methodParameterTypes689;
	private String _methodName690;
	private String[] _methodParameterTypes690;
}