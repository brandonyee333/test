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

import com.liferay.osb.service.TicketWorkerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketWorkerServiceClpInvoker {
	public TicketWorkerServiceClpInvoker() {
		_methodName380 = "getOSGiServiceIdentifier";

		_methodParameterTypes380 = new String[] {  };

		_methodName385 = "addTicketWorkers";

		_methodParameterTypes385 = new String[] {
				"long[][]", "long", "long[][]", "long[][]", "int[][]", "long"
			};

		_methodName386 = "deleteTicketWorkers";

		_methodParameterTypes386 = new String[] { "long[][]", "long", "long" };

		_methodName387 = "updateTicketWorkers";

		_methodParameterTypes387 = new String[] {
				"long[][]", "int[][]", "long[][]", "long", "long[][]",
				"long[][]", "long"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName380.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes380, parameterTypes)) {
			return TicketWorkerServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName385.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes385, parameterTypes)) {
			return TicketWorkerServiceUtil.addTicketWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue(), (long[])arguments[2],
				(long[])arguments[3], (int[])arguments[4],
				((Long)arguments[5]).longValue());
		}

		if (_methodName386.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes386, parameterTypes)) {
			TicketWorkerServiceUtil.deleteTicketWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName387.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes387, parameterTypes)) {
			return TicketWorkerServiceUtil.updateTicketWorkers((long[])arguments[0],
				(int[])arguments[1], (long[])arguments[2],
				((Long)arguments[3]).longValue(), (long[])arguments[4],
				(long[])arguments[5], ((Long)arguments[6]).longValue());
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
}