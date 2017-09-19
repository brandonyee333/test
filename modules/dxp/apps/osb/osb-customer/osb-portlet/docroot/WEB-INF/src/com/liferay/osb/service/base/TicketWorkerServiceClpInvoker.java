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
		_methodName366 = "getOSGiServiceIdentifier";

		_methodParameterTypes366 = new String[] {  };

		_methodName371 = "addTicketWorkers";

		_methodParameterTypes371 = new String[] {
				"long[][]", "long", "long[][]", "long[][]", "int[][]", "long"
			};

		_methodName372 = "deleteTicketWorkers";

		_methodParameterTypes372 = new String[] { "long[][]", "long", "long" };

		_methodName373 = "updateTicketWorkers";

		_methodParameterTypes373 = new String[] {
				"long[][]", "int[][]", "long[][]", "long", "long[][]",
				"long[][]", "long"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName366.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes366, parameterTypes)) {
			return TicketWorkerServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName371.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes371, parameterTypes)) {
			return TicketWorkerServiceUtil.addTicketWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue(), (long[])arguments[2],
				(long[])arguments[3], (int[])arguments[4],
				((Long)arguments[5]).longValue());
		}

		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			TicketWorkerServiceUtil.deleteTicketWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName373.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes373, parameterTypes)) {
			return TicketWorkerServiceUtil.updateTicketWorkers((long[])arguments[0],
				(int[])arguments[1], (long[])arguments[2],
				((Long)arguments[3]).longValue(), (long[])arguments[4],
				(long[])arguments[5], ((Long)arguments[6]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName366;
	private String[] _methodParameterTypes366;
	private String _methodName371;
	private String[] _methodParameterTypes371;
	private String _methodName372;
	private String[] _methodParameterTypes372;
	private String _methodName373;
	private String[] _methodParameterTypes373;
}