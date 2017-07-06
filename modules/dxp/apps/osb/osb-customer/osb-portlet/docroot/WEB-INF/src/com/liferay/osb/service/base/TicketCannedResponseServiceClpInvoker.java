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

import com.liferay.osb.service.TicketCannedResponseServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketCannedResponseServiceClpInvoker {
	public TicketCannedResponseServiceClpInvoker() {
		_methodName380 = "getOSGiServiceIdentifier";

		_methodParameterTypes380 = new String[] {  };

		_methodName385 = "incrementUseCount";

		_methodParameterTypes385 = new String[] { "long" };

		_methodName386 = "search";

		_methodParameterTypes386 = new String[] { "java.lang.String", "int", "int" };

		_methodName387 = "search";

		_methodParameterTypes387 = new String[] {
				"java.lang.String", "java.lang.String", "boolean", "int", "int"
			};

		_methodName388 = "searchCount";

		_methodParameterTypes388 = new String[] { "java.lang.String" };

		_methodName389 = "searchCount";

		_methodParameterTypes389 = new String[] {
				"java.lang.String", "java.lang.String", "boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName380.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes380, parameterTypes)) {
			return TicketCannedResponseServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName385.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes385, parameterTypes)) {
			TicketCannedResponseServiceUtil.incrementUseCount(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName386.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes386, parameterTypes)) {
			return TicketCannedResponseServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName387.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes387, parameterTypes)) {
			return TicketCannedResponseServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName388.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes388, parameterTypes)) {
			return TicketCannedResponseServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName389.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes389, parameterTypes)) {
			return TicketCannedResponseServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName380;
	private String[] _methodParameterTypes380;
	private String _methodName385;
	private String[] _methodParameterTypes385;
	private String _methodName386;
	private String[] _methodParameterTypes386;
	private String _methodName387;
	private String[] _methodParameterTypes387;
	private String _methodName388;
	private String[] _methodParameterTypes388;
	private String _methodName389;
	private String[] _methodParameterTypes389;
}