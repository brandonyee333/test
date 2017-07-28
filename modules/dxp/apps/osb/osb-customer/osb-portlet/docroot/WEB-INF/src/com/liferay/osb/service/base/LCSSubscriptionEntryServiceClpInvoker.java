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

import com.liferay.osb.service.LCSSubscriptionEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryServiceClpInvoker {
	public LCSSubscriptionEntryServiceClpInvoker() {
		_methodName372 = "getOSGiServiceIdentifier";

		_methodParameterTypes372 = new String[] {  };

		_methodName377 = "getLCSSubscriptionEntries";

		_methodParameterTypes377 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			return LCSSubscriptionEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName377.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes377, parameterTypes)) {
			return LCSSubscriptionEntryServiceUtil.getLCSSubscriptionEntries(((Long)arguments[0]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName372;
	private String[] _methodParameterTypes372;
	private String _methodName377;
	private String[] _methodParameterTypes377;
}