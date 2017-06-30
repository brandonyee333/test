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

import com.liferay.osb.service.PartnerEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class PartnerEntryServiceClpInvoker {
	public PartnerEntryServiceClpInvoker() {
		_methodName676 = "getBeanIdentifier";

		_methodParameterTypes676 = new String[] {  };

		_methodName677 = "setBeanIdentifier";

		_methodParameterTypes677 = new String[] { "java.lang.String" };

		_methodName682 = "getPartnerEntry";

		_methodParameterTypes682 = new String[] { "long" };

		_methodName683 = "search";

		_methodParameterTypes683 = new String[] {
				"java.lang.String", "int[][]", "java.util.LinkedHashMap",
				"boolean", "int", "int"
			};

		_methodName684 = "search";

		_methodParameterTypes684 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int"
			};

		_methodName685 = "searchCount";

		_methodParameterTypes685 = new String[] { "java.lang.String" };

		_methodName686 = "searchCount";

		_methodParameterTypes686 = new String[] {
				"java.lang.String", "int[][]", "java.util.LinkedHashMap",
				"boolean"
			};

		_methodName687 = "searchCount";

		_methodParameterTypes687 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName676.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes676, parameterTypes)) {
			return PartnerEntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName677.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes677, parameterTypes)) {
			PartnerEntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName682.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes682, parameterTypes)) {
			return PartnerEntryServiceUtil.getPartnerEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName683.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes683, parameterTypes)) {
			return PartnerEntryServiceUtil.search((java.lang.String)arguments[0],
				(int[])arguments[1],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue());
		}

		if (_methodName684.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes684, parameterTypes)) {
			return PartnerEntryServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName685.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes685, parameterTypes)) {
			return PartnerEntryServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return PartnerEntryServiceUtil.searchCount((java.lang.String)arguments[0],
				(int[])arguments[1],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			return PartnerEntryServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName676;
	private String[] _methodParameterTypes676;
	private String _methodName677;
	private String[] _methodParameterTypes677;
	private String _methodName682;
	private String[] _methodParameterTypes682;
	private String _methodName683;
	private String[] _methodParameterTypes683;
	private String _methodName684;
	private String[] _methodParameterTypes684;
	private String _methodName685;
	private String[] _methodParameterTypes685;
	private String _methodName686;
	private String[] _methodParameterTypes686;
	private String _methodName687;
	private String[] _methodParameterTypes687;
}