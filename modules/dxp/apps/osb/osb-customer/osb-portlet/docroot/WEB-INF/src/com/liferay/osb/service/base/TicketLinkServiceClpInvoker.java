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

import com.liferay.osb.service.TicketLinkServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketLinkServiceClpInvoker {
	public TicketLinkServiceClpInvoker() {
		_methodName364 = "getOSGiServiceIdentifier";

		_methodParameterTypes364 = new String[] {  };

		_methodName369 = "addTicketLink";

		_methodParameterTypes369 = new String[] {
				"long", "long", "long", "java.lang.String[][]",
				"java.lang.Integer[][]", "int",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName370 = "deleteTicketLink";

		_methodParameterTypes370 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName364.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
			return TicketLinkServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName369.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes369, parameterTypes)) {
			return TicketLinkServiceUtil.addTicketLink(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String[])arguments[3],
				(java.lang.Integer[])arguments[4],
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[6]);
		}

		if (_methodName370.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes370, parameterTypes)) {
			TicketLinkServiceUtil.deleteTicketLink(((Long)arguments[0]).longValue());

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName364;
	private String[] _methodParameterTypes364;
	private String _methodName369;
	private String[] _methodParameterTypes369;
	private String _methodName370;
	private String[] _methodParameterTypes370;
}