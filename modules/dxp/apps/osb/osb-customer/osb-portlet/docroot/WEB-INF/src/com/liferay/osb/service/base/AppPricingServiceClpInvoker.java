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

import com.liferay.osb.service.AppPricingServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AppPricingServiceClpInvoker {
	public AppPricingServiceClpInvoker() {
		_methodName664 = "getBeanIdentifier";

		_methodParameterTypes664 = new String[] {  };

		_methodName665 = "setBeanIdentifier";

		_methodParameterTypes665 = new String[] { "java.lang.String" };

		_methodName670 = "addAppPricing";

		_methodParameterTypes670 = new String[] { "long", "java.lang.String" };

		_methodName671 = "deleteAppPricing";

		_methodParameterTypes671 = new String[] { "long" };

		_methodName672 = "updateAppPricing";

		_methodParameterTypes672 = new String[] {
				"long", "java.lang.String", "long", "double", "double"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName664.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes664, parameterTypes)) {
			return AppPricingServiceUtil.getBeanIdentifier();
		}

		if (_methodName665.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes665, parameterTypes)) {
			AppPricingServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName670.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes670, parameterTypes)) {
			return AppPricingServiceUtil.addAppPricing(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName671.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes671, parameterTypes)) {
			return AppPricingServiceUtil.deleteAppPricing(((Long)arguments[0]).longValue());
		}

		if (_methodName672.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes672, parameterTypes)) {
			return AppPricingServiceUtil.updateAppPricing(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Double)arguments[3]).doubleValue(),
				((Double)arguments[4]).doubleValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName664;
	private String[] _methodParameterTypes664;
	private String _methodName665;
	private String[] _methodParameterTypes665;
	private String _methodName670;
	private String[] _methodParameterTypes670;
	private String _methodName671;
	private String[] _methodParameterTypes671;
	private String _methodName672;
	private String[] _methodParameterTypes672;
}