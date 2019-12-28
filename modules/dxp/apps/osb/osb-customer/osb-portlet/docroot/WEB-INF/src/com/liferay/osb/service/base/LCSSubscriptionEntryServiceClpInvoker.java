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
		_methodName56 = "getOSGiServiceIdentifier";

		_methodParameterTypes56 = new String[] {  };

		_methodName61 = "getLCSSubscriptionEntries";

		_methodParameterTypes61 = new String[] { "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return LCSSubscriptionEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return LCSSubscriptionEntryServiceUtil.getLCSSubscriptionEntries((java.lang.String)arguments[0]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName61;
	private String[] _methodParameterTypes61;
}