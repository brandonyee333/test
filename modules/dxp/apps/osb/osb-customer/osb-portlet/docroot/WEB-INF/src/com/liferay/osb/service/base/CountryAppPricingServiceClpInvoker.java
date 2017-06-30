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

import com.liferay.osb.service.CountryAppPricingServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class CountryAppPricingServiceClpInvoker {
	public CountryAppPricingServiceClpInvoker() {
		_methodName668 = "getBeanIdentifier";

		_methodParameterTypes668 = new String[] {  };

		_methodName669 = "setBeanIdentifier";

		_methodParameterTypes669 = new String[] { "java.lang.String" };

		_methodName674 = "deleteCountryAppPricings";

		_methodParameterTypes674 = new String[] { "long", "long[][]" };

		_methodName675 = "updateCountryAppPricings";

		_methodParameterTypes675 = new String[] { "long", "long", "long[][]" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName668.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes668, parameterTypes)) {
			return CountryAppPricingServiceUtil.getBeanIdentifier();
		}

		if (_methodName669.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes669, parameterTypes)) {
			CountryAppPricingServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName674.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes674, parameterTypes)) {
			CountryAppPricingServiceUtil.deleteCountryAppPricings(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName675.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes675, parameterTypes)) {
			return CountryAppPricingServiceUtil.updateCountryAppPricings(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (long[])arguments[2]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName668;
	private String[] _methodParameterTypes668;
	private String _methodName669;
	private String[] _methodParameterTypes669;
	private String _methodName674;
	private String[] _methodParameterTypes674;
	private String _methodName675;
	private String[] _methodParameterTypes675;
}