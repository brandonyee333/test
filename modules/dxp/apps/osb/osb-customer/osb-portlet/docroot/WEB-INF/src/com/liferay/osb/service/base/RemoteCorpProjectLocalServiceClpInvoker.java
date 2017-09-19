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

import com.liferay.osb.service.RemoteCorpProjectLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class RemoteCorpProjectLocalServiceClpInvoker {
	public RemoteCorpProjectLocalServiceClpInvoker() {
		_methodName260 = "getOSGiServiceIdentifier";

		_methodParameterTypes260 = new String[] {  };

		_methodName263 = "deleteCorpProject";

		_methodParameterTypes263 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName260.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes260, parameterTypes)) {
			return RemoteCorpProjectLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName263.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes263, parameterTypes)) {
			return RemoteCorpProjectLocalServiceUtil.deleteCorpProject(((Long)arguments[0]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName260;
	private String[] _methodParameterTypes260;
	private String _methodName263;
	private String[] _methodParameterTypes263;
}