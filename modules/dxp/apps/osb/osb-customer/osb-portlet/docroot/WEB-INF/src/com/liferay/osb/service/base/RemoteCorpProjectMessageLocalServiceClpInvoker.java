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

import com.liferay.osb.service.RemoteCorpProjectMessageLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class RemoteCorpProjectMessageLocalServiceClpInvoker {
	public RemoteCorpProjectMessageLocalServiceClpInvoker() {
		_methodName254 = "getOSGiServiceIdentifier";

		_methodParameterTypes254 = new String[] {  };

		_methodName257 = "addCorpProjectMessage";

		_methodParameterTypes257 = new String[] {
				"long", "long", "int", "int", "java.lang.String",
				"java.lang.String", "boolean", "boolean", "boolean"
			};

		_methodName258 = "deleteCorpProjectMessage";

		_methodParameterTypes258 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName254.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes254, parameterTypes)) {
			return RemoteCorpProjectMessageLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName257.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes257, parameterTypes)) {
			return RemoteCorpProjectMessageLocalServiceUtil.addCorpProjectMessage(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue(),
				((Boolean)arguments[7]).booleanValue(),
				((Boolean)arguments[8]).booleanValue());
		}

		if (_methodName258.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes258, parameterTypes)) {
			RemoteCorpProjectMessageLocalServiceUtil.deleteCorpProjectMessage(((Long)arguments[0]).longValue());

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName254;
	private String[] _methodParameterTypes254;
	private String _methodName257;
	private String[] _methodParameterTypes257;
	private String _methodName258;
	private String[] _methodParameterTypes258;
}