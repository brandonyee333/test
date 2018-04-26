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
		_methodName214 = "getOSGiServiceIdentifier";

		_methodParameterTypes214 = new String[] {  };

		_methodName219 = "getPartnerEntry";

		_methodParameterTypes219 = new String[] { "long" };

		_methodName220 = "search";

		_methodParameterTypes220 = new String[] {
				"java.lang.String", "int[][]", "java.util.LinkedHashMap",
				"boolean", "int", "int"
			};

		_methodName221 = "search";

		_methodParameterTypes221 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int"
			};

		_methodName222 = "searchCount";

		_methodParameterTypes222 = new String[] { "java.lang.String" };

		_methodName223 = "searchCount";

		_methodParameterTypes223 = new String[] {
				"java.lang.String", "int[][]", "java.util.LinkedHashMap",
				"boolean"
			};

		_methodName224 = "searchCount";

		_methodParameterTypes224 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName214.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes214, parameterTypes)) {
			return PartnerEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName219.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes219, parameterTypes)) {
			return PartnerEntryServiceUtil.getPartnerEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName220.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes220, parameterTypes)) {
			return PartnerEntryServiceUtil.search((java.lang.String)arguments[0],
				(int[])arguments[1],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue());
		}

		if (_methodName221.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes221, parameterTypes)) {
			return PartnerEntryServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName222.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes222, parameterTypes)) {
			return PartnerEntryServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName223.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes223, parameterTypes)) {
			return PartnerEntryServiceUtil.searchCount((java.lang.String)arguments[0],
				(int[])arguments[1],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName224.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes224, parameterTypes)) {
			return PartnerEntryServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName214;
	private String[] _methodParameterTypes214;
	private String _methodName219;
	private String[] _methodParameterTypes219;
	private String _methodName220;
	private String[] _methodParameterTypes220;
	private String _methodName221;
	private String[] _methodParameterTypes221;
	private String _methodName222;
	private String[] _methodParameterTypes222;
	private String _methodName223;
	private String[] _methodParameterTypes223;
	private String _methodName224;
	private String[] _methodParameterTypes224;
}