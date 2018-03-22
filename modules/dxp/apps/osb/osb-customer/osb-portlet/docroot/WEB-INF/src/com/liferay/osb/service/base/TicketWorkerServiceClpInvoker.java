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
		_methodName358 = "getOSGiServiceIdentifier";

		_methodParameterTypes358 = new String[] {  };

		_methodName363 = "addTicketWorkers";

		_methodParameterTypes363 = new String[] {
				"long[][]", "long", "long[][]", "long[][]", "int[][]", "long"
			};

		_methodName364 = "deleteTicketWorkers";

		_methodParameterTypes364 = new String[] { "long[][]", "long", "long" };

		_methodName365 = "updateTicketWorkers";

		_methodParameterTypes365 = new String[] {
				"long[][]", "int[][]", "long[][]", "long", "long[][]",
				"long[][]", "long"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName358.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes358, parameterTypes)) {
			return TicketWorkerServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName363.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes363, parameterTypes)) {
			return TicketWorkerServiceUtil.addTicketWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue(), (long[])arguments[2],
				(long[])arguments[3], (int[])arguments[4],
				((Long)arguments[5]).longValue());
		}

		if (_methodName364.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
			TicketWorkerServiceUtil.deleteTicketWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName365.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes365, parameterTypes)) {
			return TicketWorkerServiceUtil.updateTicketWorkers((long[])arguments[0],
				(int[])arguments[1], (long[])arguments[2],
				((Long)arguments[3]).longValue(), (long[])arguments[4],
				(long[])arguments[5], ((Long)arguments[6]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName358;
	private String[] _methodParameterTypes358;
	private String _methodName363;
	private String[] _methodParameterTypes363;
	private String _methodName364;
	private String[] _methodParameterTypes364;
	private String _methodName365;
	private String[] _methodParameterTypes365;
}