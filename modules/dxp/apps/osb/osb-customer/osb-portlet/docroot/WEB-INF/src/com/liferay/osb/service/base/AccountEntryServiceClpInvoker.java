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

import com.liferay.osb.service.AccountEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountEntryServiceClpInvoker {
	public AccountEntryServiceClpInvoker() {
		_methodName230 = "getOSGiServiceIdentifier";

		_methodParameterTypes230 = new String[] {  };

		_methodName235 = "deleteAccountEntry";

		_methodParameterTypes235 = new String[] { "long" };

		_methodName236 = "fetchCorpProjectAccountEntry";

		_methodParameterTypes236 = new String[] { "java.lang.String" };

		_methodName237 = "getAccountEntries";

		_methodParameterTypes237 = new String[] { "java.lang.String", "long[][]" };

		_methodName238 = "getAccountEntry";

		_methodParameterTypes238 = new String[] { "long" };

		_methodName239 = "getAccountEntryByCode";

		_methodParameterTypes239 = new String[] { "java.lang.String" };

		_methodName240 = "getCorpProjectAccountEntry";

		_methodParameterTypes240 = new String[] { "java.lang.String" };

		_methodName241 = "search";

		_methodParameterTypes241 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "int[][]", "java.lang.Boolean", "int[][]",
				"int[][]", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.Long",
				"java.lang.Long", "java.lang.String", "java.lang.String",
				"java.util.LinkedHashMap", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName242 = "searchCount";

		_methodParameterTypes242 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "int[][]", "java.lang.Boolean", "int[][]",
				"int[][]", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.Long",
				"java.lang.Long", "java.lang.String", "java.lang.String",
				"java.util.LinkedHashMap", "boolean"
			};

		_methodName243 = "updateInstructions";

		_methodParameterTypes243 = new String[] { "long", "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName230.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes230, parameterTypes)) {
			return AccountEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName235.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes235, parameterTypes)) {
			return AccountEntryServiceUtil.deleteAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName236.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes236, parameterTypes)) {
			return AccountEntryServiceUtil.fetchCorpProjectAccountEntry((java.lang.String)arguments[0]);
		}

		if (_methodName237.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes237, parameterTypes)) {
			return AccountEntryServiceUtil.getAccountEntries((java.lang.String)arguments[0],
				(long[])arguments[1]);
		}

		if (_methodName238.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes238, parameterTypes)) {
			return AccountEntryServiceUtil.getAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName239.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes239, parameterTypes)) {
			return AccountEntryServiceUtil.getAccountEntryByCode((java.lang.String)arguments[0]);
		}

		if (_methodName240.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes240, parameterTypes)) {
			return AccountEntryServiceUtil.getCorpProjectAccountEntry((java.lang.String)arguments[0]);
		}

		if (_methodName241.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes241, parameterTypes)) {
			return AccountEntryServiceUtil.search((java.lang.Long)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(java.lang.Long)arguments[7],
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(java.lang.String)arguments[17], (int[])arguments[18],
				(java.lang.Boolean)arguments[19], (int[])arguments[20],
				(int[])arguments[21], (java.lang.String)arguments[22],
				(java.lang.String)arguments[23],
				(java.lang.String)arguments[24],
				(java.lang.String)arguments[25], (java.lang.Long)arguments[26],
				(java.lang.Long)arguments[27], (java.lang.String)arguments[28],
				(java.lang.String)arguments[29],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[30],
				((Boolean)arguments[31]).booleanValue(),
				((Integer)arguments[32]).intValue(),
				((Integer)arguments[33]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[34]);
		}

		if (_methodName242.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes242, parameterTypes)) {
			return AccountEntryServiceUtil.searchCount((java.lang.Long)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(java.lang.Long)arguments[7],
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(java.lang.String)arguments[17], (int[])arguments[18],
				(java.lang.Boolean)arguments[19], (int[])arguments[20],
				(int[])arguments[21], (java.lang.String)arguments[22],
				(java.lang.String)arguments[23],
				(java.lang.String)arguments[24],
				(java.lang.String)arguments[25], (java.lang.Long)arguments[26],
				(java.lang.Long)arguments[27], (java.lang.String)arguments[28],
				(java.lang.String)arguments[29],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[30],
				((Boolean)arguments[31]).booleanValue());
		}

		if (_methodName243.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes243, parameterTypes)) {
			return AccountEntryServiceUtil.updateInstructions(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName230;
	private String[] _methodParameterTypes230;
	private String _methodName235;
	private String[] _methodParameterTypes235;
	private String _methodName236;
	private String[] _methodParameterTypes236;
	private String _methodName237;
	private String[] _methodParameterTypes237;
	private String _methodName238;
	private String[] _methodParameterTypes238;
	private String _methodName239;
	private String[] _methodParameterTypes239;
	private String _methodName240;
	private String[] _methodParameterTypes240;
	private String _methodName241;
	private String[] _methodParameterTypes241;
	private String _methodName242;
	private String[] _methodParameterTypes242;
	private String _methodName243;
	private String[] _methodParameterTypes243;
}