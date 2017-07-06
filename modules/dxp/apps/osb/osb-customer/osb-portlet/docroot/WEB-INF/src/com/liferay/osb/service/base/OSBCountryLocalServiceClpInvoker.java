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

import com.liferay.osb.service.OSBCountryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class OSBCountryLocalServiceClpInvoker {
	public OSBCountryLocalServiceClpInvoker() {
		_methodName268 = "getOSGiServiceIdentifier";

		_methodParameterTypes268 = new String[] {  };

		_methodName271 = "addCountry";

		_methodParameterTypes271 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName272 = "deleteCountry";

		_methodParameterTypes272 = new String[] { "long" };

		_methodName273 = "getCountries";

		_methodParameterTypes273 = new String[] {  };

		_methodName274 = "getCountries";

		_methodParameterTypes274 = new String[] { "int", "int" };

		_methodName275 = "getCountriesCount";

		_methodParameterTypes275 = new String[] {  };

		_methodName276 = "getCountry";

		_methodParameterTypes276 = new String[] { "long" };

		_methodName277 = "updateCountry";

		_methodParameterTypes277 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName268.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes268, parameterTypes)) {
			return OSBCountryLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName271.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes271, parameterTypes)) {
			return OSBCountryLocalServiceUtil.addCountry((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName272.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes272, parameterTypes)) {
			return OSBCountryLocalServiceUtil.deleteCountry(((Long)arguments[0]).longValue());
		}

		if (_methodName273.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes273, parameterTypes)) {
			return OSBCountryLocalServiceUtil.getCountries();
		}

		if (_methodName274.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes274, parameterTypes)) {
			return OSBCountryLocalServiceUtil.getCountries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName275.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes275, parameterTypes)) {
			return OSBCountryLocalServiceUtil.getCountriesCount();
		}

		if (_methodName276.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes276, parameterTypes)) {
			return OSBCountryLocalServiceUtil.getCountry(((Long)arguments[0]).longValue());
		}

		if (_methodName277.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes277, parameterTypes)) {
			return OSBCountryLocalServiceUtil.updateCountry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName268;
	private String[] _methodParameterTypes268;
	private String _methodName271;
	private String[] _methodParameterTypes271;
	private String _methodName272;
	private String[] _methodParameterTypes272;
	private String _methodName273;
	private String[] _methodParameterTypes273;
	private String _methodName274;
	private String[] _methodParameterTypes274;
	private String _methodName275;
	private String[] _methodParameterTypes275;
	private String _methodName276;
	private String[] _methodParameterTypes276;
	private String _methodName277;
	private String[] _methodParameterTypes277;
}