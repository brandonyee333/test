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

import com.liferay.osb.service.TicketLinkServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TicketLinkServiceClpInvoker {
	public TicketLinkServiceClpInvoker() {
		_methodName670 = "getBeanIdentifier";

		_methodParameterTypes670 = new String[] {  };

		_methodName671 = "setBeanIdentifier";

		_methodParameterTypes671 = new String[] { "java.lang.String" };

		_methodName676 = "addTicketLink";

		_methodParameterTypes676 = new String[] {
				"long", "long", "long", "java.lang.String[][]",
				"java.lang.Integer[][]", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName677 = "deleteTicketLink";

		_methodParameterTypes677 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName670.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes670, parameterTypes)) {
			return TicketLinkServiceUtil.getBeanIdentifier();
		}

		if (_methodName671.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes671, parameterTypes)) {
			TicketLinkServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName676.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes676, parameterTypes)) {
			return TicketLinkServiceUtil.addTicketLink(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String[])arguments[3],
				(java.lang.Integer[])arguments[4],
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName677.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes677, parameterTypes)) {
			TicketLinkServiceUtil.deleteTicketLink(((Long)arguments[0]).longValue());

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
}