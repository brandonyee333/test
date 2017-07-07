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

import com.liferay.osb.service.OSBRegionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class OSBRegionLocalServiceClpInvoker {
	public OSBRegionLocalServiceClpInvoker() {
		_methodName266 = "getOSGiServiceIdentifier";

		_methodParameterTypes266 = new String[] {  };

		_methodName269 = "addRegion";

		_methodParameterTypes269 = new String[] {
				"long", "java.lang.String", "java.lang.String", "boolean"
			};

		_methodName270 = "deleteRegion";

		_methodParameterTypes270 = new String[] { "long" };

		_methodName271 = "fetchRegion";

		_methodParameterTypes271 = new String[] { "long", "java.lang.String" };

		_methodName272 = "getRegion";

		_methodParameterTypes272 = new String[] { "long" };

		_methodName273 = "getRegions";

		_methodParameterTypes273 = new String[] { "long", "int", "int" };

		_methodName274 = "getRegionsCount";

		_methodParameterTypes274 = new String[] { "long" };

		_methodName275 = "updateRegion";

		_methodParameterTypes275 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName266.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes266, parameterTypes)) {
			return OSBRegionLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName269.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes269, parameterTypes)) {
			return OSBRegionLocalServiceUtil.addRegion(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName270.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes270, parameterTypes)) {
			return OSBRegionLocalServiceUtil.deleteRegion(((Long)arguments[0]).longValue());
		}

		if (_methodName271.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes271, parameterTypes)) {
			return OSBRegionLocalServiceUtil.fetchRegion(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName272.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes272, parameterTypes)) {
			return OSBRegionLocalServiceUtil.getRegion(((Long)arguments[0]).longValue());
		}

		if (_methodName273.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes273, parameterTypes)) {
			return OSBRegionLocalServiceUtil.getRegions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName274.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes274, parameterTypes)) {
			return OSBRegionLocalServiceUtil.getRegionsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName275.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes275, parameterTypes)) {
			return OSBRegionLocalServiceUtil.updateRegion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName266;
	private String[] _methodParameterTypes266;
	private String _methodName269;
	private String[] _methodParameterTypes269;
	private String _methodName270;
	private String[] _methodParameterTypes270;
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
}