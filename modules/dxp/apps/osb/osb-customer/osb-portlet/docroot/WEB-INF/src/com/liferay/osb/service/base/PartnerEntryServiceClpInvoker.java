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
		_methodName378 = "getOSGiServiceIdentifier";

		_methodParameterTypes378 = new String[] {  };

		_methodName383 = "getPartnerEntry";

		_methodParameterTypes383 = new String[] { "long" };

		_methodName384 = "search";

		_methodParameterTypes384 = new String[] {
				"java.lang.String", "int[][]", "java.util.LinkedHashMap",
				"boolean", "int", "int"
			};

		_methodName385 = "search";

		_methodParameterTypes385 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int"
			};

		_methodName386 = "searchCount";

		_methodParameterTypes386 = new String[] { "java.lang.String" };

		_methodName387 = "searchCount";

		_methodParameterTypes387 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName388 = "searchCount";

		_methodParameterTypes388 = new String[] {
				"java.lang.String", "int[][]", "java.util.LinkedHashMap",
				"boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName378.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes378, parameterTypes)) {
			return PartnerEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName383.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes383, parameterTypes)) {
			return PartnerEntryServiceUtil.getPartnerEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName384.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes384, parameterTypes)) {
			return PartnerEntryServiceUtil.search((java.lang.String)arguments[0],
				(int[])arguments[1],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue());
		}

		if (_methodName385.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes385, parameterTypes)) {
			return PartnerEntryServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName386.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes386, parameterTypes)) {
			return PartnerEntryServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName387.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes387, parameterTypes)) {
			return PartnerEntryServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName388.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes388, parameterTypes)) {
			return PartnerEntryServiceUtil.searchCount((java.lang.String)arguments[0],
				(int[])arguments[1],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName378;
	private String[] _methodParameterTypes378;
	private String _methodName383;
	private String[] _methodParameterTypes383;
	private String _methodName384;
	private String[] _methodParameterTypes384;
	private String _methodName385;
	private String[] _methodParameterTypes385;
	private String _methodName386;
	private String[] _methodParameterTypes386;
	private String _methodName387;
	private String[] _methodParameterTypes387;
	private String _methodName388;
	private String[] _methodParameterTypes388;
}