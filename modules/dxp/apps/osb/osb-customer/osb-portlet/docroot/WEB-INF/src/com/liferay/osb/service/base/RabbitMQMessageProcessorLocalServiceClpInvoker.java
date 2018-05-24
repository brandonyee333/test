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
		_methodName148 = "getOSGiServiceIdentifier";

		_methodParameterTypes148 = new String[] {  };

		_methodName151 = "processCreateMessage";

		_methodParameterTypes151 = new String[] {
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName152 = "processUpdateMessage";

		_methodParameterTypes152 = new String[] {
				"com.liferay.portal.kernel.json.JSONObject"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName148.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes148, parameterTypes)) {
			return RabbitMQMessageProcessorLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName151.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes151, parameterTypes)) {
			RabbitMQMessageProcessorLocalServiceUtil.processCreateMessage((com.liferay.portal.kernel.json.JSONObject)arguments[0]);

			return null;
		}

		if (_methodName152.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes152, parameterTypes)) {
			RabbitMQMessageProcessorLocalServiceUtil.processUpdateMessage((com.liferay.portal.kernel.json.JSONObject)arguments[0]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName148;
	private String[] _methodParameterTypes148;
	private String _methodName151;
	private String[] _methodParameterTypes151;
	private String _methodName152;
	private String[] _methodParameterTypes152;
}