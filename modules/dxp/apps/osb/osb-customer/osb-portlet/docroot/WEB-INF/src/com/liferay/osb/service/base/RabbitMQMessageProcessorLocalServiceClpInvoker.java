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
		_methodName142 = "getOSGiServiceIdentifier";

		_methodParameterTypes142 = new String[] {  };

		_methodName145 = "processCreateMessage";

		_methodParameterTypes145 = new String[] {
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName146 = "processUpdateMessage";

		_methodParameterTypes146 = new String[] {
				"com.liferay.portal.kernel.json.JSONObject"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName142.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes142, parameterTypes)) {
			return RabbitMQMessageProcessorLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName145.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes145, parameterTypes)) {
			RabbitMQMessageProcessorLocalServiceUtil.processCreateMessage((com.liferay.portal.kernel.json.JSONObject)arguments[0]);

			return null;
		}

		if (_methodName146.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes146, parameterTypes)) {
			RabbitMQMessageProcessorLocalServiceUtil.processUpdateMessage((com.liferay.portal.kernel.json.JSONObject)arguments[0]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName142;
	private String[] _methodParameterTypes142;
	private String _methodName145;
	private String[] _methodParameterTypes145;
	private String _methodName146;
	private String[] _methodParameterTypes146;
}