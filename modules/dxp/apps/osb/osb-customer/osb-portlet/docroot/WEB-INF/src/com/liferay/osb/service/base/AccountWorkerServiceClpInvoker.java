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

import com.liferay.osb.service.AccountWorkerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountWorkerServiceClpInvoker {
	public AccountWorkerServiceClpInvoker() {
		_methodName366 = "getOSGiServiceIdentifier";

		_methodParameterTypes366 = new String[] {  };

		_methodName371 = "addAccountWorkers";

		_methodParameterTypes371 = new String[] {
				"long[][]", "long", "int[][]", "int[][]"
			};

		_methodName372 = "deleteAccountWorkers";

		_methodParameterTypes372 = new String[] { "long[][]", "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName366.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes366, parameterTypes)) {
			return AccountWorkerServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName371.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes371, parameterTypes)) {
			AccountWorkerServiceUtil.addAccountWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue(), (int[])arguments[2],
				(int[])arguments[3]);

			return null;
		}

		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			AccountWorkerServiceUtil.deleteAccountWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName366;
	private String[] _methodParameterTypes366;
	private String _methodName371;
	private String[] _methodParameterTypes371;
	private String _methodName372;
	private String[] _methodParameterTypes372;
}