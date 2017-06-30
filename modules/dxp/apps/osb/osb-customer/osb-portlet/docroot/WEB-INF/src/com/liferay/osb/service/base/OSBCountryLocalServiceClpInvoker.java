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

import com.liferay.osb.service.OSBCountryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class OSBCountryLocalServiceClpInvoker {
	public OSBCountryLocalServiceClpInvoker() {
		_methodName668 = "getBeanIdentifier";

		_methodParameterTypes668 = new String[] {  };

		_methodName669 = "setBeanIdentifier";

		_methodParameterTypes669 = new String[] { "java.lang.String" };

		_methodName672 = "addCountry";

		_methodParameterTypes672 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName673 = "deleteCountry";

		_methodParameterTypes673 = new String[] { "long" };

		_methodName674 = "getCountries";

		_methodParameterTypes674 = new String[] {  };

		_methodName675 = "getCountries";

		_methodParameterTypes675 = new String[] { "int", "int" };

		_methodName676 = "getCountriesCount";

		_methodParameterTypes676 = new String[] {  };

		_methodName677 = "getCountry";

		_methodParameterTypes677 = new String[] { "long" };

		_methodName678 = "updateCountry";

		_methodParameterTypes678 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName668.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes668, parameterTypes)) {
			return OSBCountryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName669.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes669, parameterTypes)) {
			OSBCountryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName672.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes672, parameterTypes)) {
			return OSBCountryLocalServiceUtil.addCountry((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName673.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes673, parameterTypes)) {
			return OSBCountryLocalServiceUtil.deleteCountry(((Long)arguments[0]).longValue());
		}

		if (_methodName674.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes674, parameterTypes)) {
			return OSBCountryLocalServiceUtil.getCountries();
		}

		if (_methodName675.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes675, parameterTypes)) {
			return OSBCountryLocalServiceUtil.getCountries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName676.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes676, parameterTypes)) {
			return OSBCountryLocalServiceUtil.getCountriesCount();
		}

		if (_methodName677.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes677, parameterTypes)) {
			return OSBCountryLocalServiceUtil.getCountry(((Long)arguments[0]).longValue());
		}

		if (_methodName678.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes678, parameterTypes)) {
			return OSBCountryLocalServiceUtil.updateCountry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName668;
	private String[] _methodParameterTypes668;
	private String _methodName669;
	private String[] _methodParameterTypes669;
	private String _methodName672;
	private String[] _methodParameterTypes672;
	private String _methodName673;
	private String[] _methodParameterTypes673;
	private String _methodName674;
	private String[] _methodParameterTypes674;
	private String _methodName675;
	private String[] _methodParameterTypes675;
	private String _methodName676;
	private String[] _methodParameterTypes676;
	private String _methodName677;
	private String[] _methodParameterTypes677;
	private String _methodName678;
	private String[] _methodParameterTypes678;
}