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

import com.liferay.osb.service.TicketWorkerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TicketWorkerServiceClpInvoker {
	public TicketWorkerServiceClpInvoker() {
		_methodName664 = "getBeanIdentifier";

		_methodParameterTypes664 = new String[] {  };

		_methodName665 = "setBeanIdentifier";

		_methodParameterTypes665 = new String[] { "java.lang.String" };

		_methodName670 = "addTicketWorkers";

		_methodParameterTypes670 = new String[] {
				"long[][]", "long", "long[][]", "long[][]", "int[][]", "long"
			};

		_methodName671 = "deleteTicketWorkers";

		_methodParameterTypes671 = new String[] { "long[][]", "long", "long" };

		_methodName672 = "updateTicketWorkers";

		_methodParameterTypes672 = new String[] {
				"long[][]", "int[][]", "long[][]", "long", "long[][]",
				"long[][]", "long"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName664.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes664, parameterTypes)) {
			return TicketWorkerServiceUtil.getBeanIdentifier();
		}

		if (_methodName665.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes665, parameterTypes)) {
			TicketWorkerServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName670.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes670, parameterTypes)) {
			return TicketWorkerServiceUtil.addTicketWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue(), (long[])arguments[2],
				(long[])arguments[3], (int[])arguments[4],
				((Long)arguments[5]).longValue());
		}

		if (_methodName671.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes671, parameterTypes)) {
			TicketWorkerServiceUtil.deleteTicketWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName672.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes672, parameterTypes)) {
			return TicketWorkerServiceUtil.updateTicketWorkers((long[])arguments[0],
				(int[])arguments[1], (long[])arguments[2],
				((Long)arguments[3]).longValue(), (long[])arguments[4],
				(long[])arguments[5], ((Long)arguments[6]).longValue());
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
}