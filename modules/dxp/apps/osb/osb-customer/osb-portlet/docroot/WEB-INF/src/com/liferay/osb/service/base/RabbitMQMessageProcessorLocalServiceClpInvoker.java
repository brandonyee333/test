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
		_methodName146 = "getOSGiServiceIdentifier";

		_methodParameterTypes146 = new String[] {  };

		_methodName149 = "processCreateMessage";

		_methodParameterTypes149 = new String[] {
				"com.liferay.portal.kernel.json.JSONObject"
			};

		_methodName150 = "processUpdateMessage";

		_methodParameterTypes150 = new String[] {
				"com.liferay.portal.kernel.json.JSONObject"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName146.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes146, parameterTypes)) {
			return RabbitMQMessageProcessorLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName149.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes149, parameterTypes)) {
			RabbitMQMessageProcessorLocalServiceUtil.processCreateMessage((com.liferay.portal.kernel.json.JSONObject)arguments[0]);

			return null;
		}

		if (_methodName150.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes150, parameterTypes)) {
			RabbitMQMessageProcessorLocalServiceUtil.processUpdateMessage((com.liferay.portal.kernel.json.JSONObject)arguments[0]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName146;
	private String[] _methodParameterTypes146;
	private String _methodName149;
	private String[] _methodParameterTypes149;
	private String _methodName150;
	private String[] _methodParameterTypes150;
}