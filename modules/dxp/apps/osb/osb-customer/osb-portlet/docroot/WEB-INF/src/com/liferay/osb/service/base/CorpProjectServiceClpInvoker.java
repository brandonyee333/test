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

import com.liferay.osb.service.CorpProjectServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CorpProjectServiceClpInvoker {
	public CorpProjectServiceClpInvoker() {
		_methodName216 = "getOSGiServiceIdentifier";

		_methodParameterTypes216 = new String[] {  };

		_methodName221 = "addCorpProject";

		_methodParameterTypes221 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName222 = "addUserCorpProjectRoles";

		_methodParameterTypes222 = new String[] { "long", "long", "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName216.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes216, parameterTypes)) {
			return CorpProjectServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName221.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes221, parameterTypes)) {
			return CorpProjectServiceUtil.addCorpProject((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3],
				((Long)arguments[4]).longValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[5]);
		}

		if (_methodName222.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes222, parameterTypes)) {
			CorpProjectServiceUtil.addUserCorpProjectRoles(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName216;
	private String[] _methodParameterTypes216;
	private String _methodName221;
	private String[] _methodParameterTypes221;
	private String _methodName222;
	private String[] _methodParameterTypes222;
}