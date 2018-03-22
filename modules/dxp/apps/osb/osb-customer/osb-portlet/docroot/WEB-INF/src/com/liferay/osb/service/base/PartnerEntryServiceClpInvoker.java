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

import com.liferay.osb.service.PartnerEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class PartnerEntryServiceClpInvoker {
	public PartnerEntryServiceClpInvoker() {
		_methodName370 = "getOSGiServiceIdentifier";

		_methodParameterTypes370 = new String[] {  };

		_methodName375 = "getPartnerEntry";

		_methodParameterTypes375 = new String[] { "long" };

		_methodName376 = "search";

		_methodParameterTypes376 = new String[] {
				"java.lang.String", "int[][]", "java.util.LinkedHashMap",
				"boolean", "int", "int"
			};

		_methodName377 = "search";

		_methodParameterTypes377 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int"
			};

		_methodName378 = "searchCount";

		_methodParameterTypes378 = new String[] { "java.lang.String" };

		_methodName379 = "searchCount";

		_methodParameterTypes379 = new String[] {
				"java.lang.String", "int[][]", "java.util.LinkedHashMap",
				"boolean"
			};

		_methodName380 = "searchCount";

		_methodParameterTypes380 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName370.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes370, parameterTypes)) {
			return PartnerEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName375.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes375, parameterTypes)) {
			return PartnerEntryServiceUtil.getPartnerEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName376.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes376, parameterTypes)) {
			return PartnerEntryServiceUtil.search((java.lang.String)arguments[0],
				(int[])arguments[1],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue());
		}

		if (_methodName377.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes377, parameterTypes)) {
			return PartnerEntryServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName378.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes378, parameterTypes)) {
			return PartnerEntryServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName379.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes379, parameterTypes)) {
			return PartnerEntryServiceUtil.searchCount((java.lang.String)arguments[0],
				(int[])arguments[1],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName380.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes380, parameterTypes)) {
			return PartnerEntryServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName370;
	private String[] _methodParameterTypes370;
	private String _methodName375;
	private String[] _methodParameterTypes375;
	private String _methodName376;
	private String[] _methodParameterTypes376;
	private String _methodName377;
	private String[] _methodParameterTypes377;
	private String _methodName378;
	private String[] _methodParameterTypes378;
	private String _methodName379;
	private String[] _methodParameterTypes379;
	private String _methodName380;
	private String[] _methodParameterTypes380;
}