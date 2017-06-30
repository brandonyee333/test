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

import com.liferay.osb.service.TicketCannedResponseServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TicketCannedResponseServiceClpInvoker {
	public TicketCannedResponseServiceClpInvoker() {
		_methodName664 = "getBeanIdentifier";

		_methodParameterTypes664 = new String[] {  };

		_methodName665 = "setBeanIdentifier";

		_methodParameterTypes665 = new String[] { "java.lang.String" };

		_methodName670 = "incrementUseCount";

		_methodParameterTypes670 = new String[] { "long" };

		_methodName671 = "search";

		_methodParameterTypes671 = new String[] { "java.lang.String", "int", "int" };

		_methodName672 = "search";

		_methodParameterTypes672 = new String[] {
				"java.lang.String", "java.lang.String", "boolean", "int", "int"
			};

		_methodName673 = "searchCount";

		_methodParameterTypes673 = new String[] { "java.lang.String" };

		_methodName674 = "searchCount";

		_methodParameterTypes674 = new String[] {
				"java.lang.String", "java.lang.String", "boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName664.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes664, parameterTypes)) {
			return TicketCannedResponseServiceUtil.getBeanIdentifier();
		}

		if (_methodName665.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes665, parameterTypes)) {
			TicketCannedResponseServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName670.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes670, parameterTypes)) {
			TicketCannedResponseServiceUtil.incrementUseCount(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName671.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes671, parameterTypes)) {
			return TicketCannedResponseServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName672.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes672, parameterTypes)) {
			return TicketCannedResponseServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName673.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes673, parameterTypes)) {
			return TicketCannedResponseServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName674.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes674, parameterTypes)) {
			return TicketCannedResponseServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue());
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
	private String _methodName672;
	private String[] _methodParameterTypes672;
	private String _methodName673;
	private String[] _methodParameterTypes673;
	private String _methodName674;
	private String[] _methodParameterTypes674;
}