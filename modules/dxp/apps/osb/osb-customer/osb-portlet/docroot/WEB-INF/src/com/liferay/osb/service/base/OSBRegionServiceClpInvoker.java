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

import com.liferay.osb.service.OSBRegionServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class OSBRegionServiceClpInvoker {
	public OSBRegionServiceClpInvoker() {
		_methodName672 = "getBeanIdentifier";

		_methodParameterTypes672 = new String[] {  };

		_methodName673 = "setBeanIdentifier";

		_methodParameterTypes673 = new String[] { "java.lang.String" };

		_methodName676 = "addRegion";

		_methodParameterTypes676 = new String[] {
				"long", "java.lang.String", "java.lang.String", "boolean"
			};

		_methodName677 = "deleteRegion";

		_methodParameterTypes677 = new String[] { "long" };

		_methodName678 = "updateRegion";

		_methodParameterTypes678 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName672.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes672, parameterTypes)) {
			return OSBRegionServiceUtil.getBeanIdentifier();
		}

		if (_methodName673.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes673, parameterTypes)) {
			OSBRegionServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName676.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes676, parameterTypes)) {
			return OSBRegionServiceUtil.addRegion(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName677.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes677, parameterTypes)) {
			return OSBRegionServiceUtil.deleteRegion(((Long)arguments[0]).longValue());
		}

		if (_methodName678.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes678, parameterTypes)) {
			return OSBRegionServiceUtil.updateRegion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName672;
	private String[] _methodParameterTypes672;
	private String _methodName673;
	private String[] _methodParameterTypes673;
	private String _methodName676;
	private String[] _methodParameterTypes676;
	private String _methodName677;
	private String[] _methodParameterTypes677;
	private String _methodName678;
	private String[] _methodParameterTypes678;
}