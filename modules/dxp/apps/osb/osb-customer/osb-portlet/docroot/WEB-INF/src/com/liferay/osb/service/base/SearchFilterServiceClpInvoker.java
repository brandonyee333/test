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

import com.liferay.osb.service.SearchFilterServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class SearchFilterServiceClpInvoker {
	public SearchFilterServiceClpInvoker() {
		_methodName364 = "getOSGiServiceIdentifier";

		_methodParameterTypes364 = new String[] {  };

		_methodName369 = "addSearchFilter";

		_methodParameterTypes369 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String", "int"
			};

		_methodName370 = "deleteSearchFilter";

		_methodParameterTypes370 = new String[] { "long" };

		_methodName371 = "getSearchFilter";

		_methodParameterTypes371 = new String[] { "long" };

		_methodName372 = "updateSearchFilter";

		_methodParameterTypes372 = new String[] {
				"long", "java.lang.String", "java.lang.String", "int"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName364.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
			return SearchFilterServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName369.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes369, parameterTypes)) {
			return SearchFilterServiceUtil.addSearchFilter(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue());
		}

		if (_methodName370.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes370, parameterTypes)) {
			SearchFilterServiceUtil.deleteSearchFilter(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName371.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes371, parameterTypes)) {
			return SearchFilterServiceUtil.getSearchFilter(((Long)arguments[0]).longValue());
		}

		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			return SearchFilterServiceUtil.updateSearchFilter(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName364;
	private String[] _methodParameterTypes364;
	private String _methodName369;
	private String[] _methodParameterTypes369;
	private String _methodName370;
	private String[] _methodParameterTypes370;
	private String _methodName371;
	private String[] _methodParameterTypes371;
	private String _methodName372;
	private String[] _methodParameterTypes372;
}