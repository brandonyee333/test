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

import com.liferay.osb.service.RabbitMQMessageProcessorLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class RabbitMQMessageProcessorLocalServiceClpInvoker {
	public RabbitMQMessageProcessorLocalServiceClpInvoker() {
		_methodName144 = "getOSGiServiceIdentifier";

		_methodParameterTypes144 = new String[] {  };

		_methodName147 = "processCreateMessage";

		_methodParameterTypes147 = new String[] {
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName148 = "processUpdateMessage";

		_methodParameterTypes148 = new String[] {
				"com.liferay.portal.kernel.json.JSONObject"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName144.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes144, parameterTypes)) {
			return RabbitMQMessageProcessorLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName147.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes147, parameterTypes)) {
			RabbitMQMessageProcessorLocalServiceUtil.processCreateMessage((com.liferay.portal.kernel.json.JSONObject)arguments[0]);

			return null;
		}

		if (_methodName148.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes148, parameterTypes)) {
			RabbitMQMessageProcessorLocalServiceUtil.processUpdateMessage((com.liferay.portal.kernel.json.JSONObject)arguments[0]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName144;
	private String[] _methodParameterTypes144;
	private String _methodName147;
	private String[] _methodParameterTypes147;
	private String _methodName148;
	private String[] _methodParameterTypes148;
}