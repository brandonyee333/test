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
		_methodName232 = "getOSGiServiceIdentifier";

		_methodParameterTypes232 = new String[] {  };

		_methodName237 = "deleteAccountEntry";

		_methodParameterTypes237 = new String[] { "long" };

		_methodName238 = "fetchCorpProjectAccountEntry";

		_methodParameterTypes238 = new String[] { "java.lang.String" };

		_methodName239 = "fetchCorpProjectIdAccountEntry";

		_methodParameterTypes239 = new String[] { "long" };

		_methodName240 = "getAccountEntries";

		_methodParameterTypes240 = new String[] { "java.lang.String", "long[][]" };

		_methodName241 = "getAccountEntry";

		_methodParameterTypes241 = new String[] { "long" };

		_methodName242 = "getAccountEntryByCode";

		_methodParameterTypes242 = new String[] { "java.lang.String" };

		_methodName243 = "getCorpProjectAccountEntry";

		_methodParameterTypes243 = new String[] { "java.lang.String" };

		_methodName244 = "search";

		_methodParameterTypes244 = new String[] {
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

		_methodName245 = "searchCount";

		_methodParameterTypes245 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "int[][]", "java.lang.Boolean", "int[][]",
				"int[][]", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.Long",
				"java.lang.Long", "java.lang.String", "java.lang.String",
				"java.util.LinkedHashMap", "boolean"
			};

		_methodName246 = "updateInstructions";

		_methodParameterTypes246 = new String[] { "long", "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName232.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes232, parameterTypes)) {
			return AccountEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName237.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes237, parameterTypes)) {
			return AccountEntryServiceUtil.deleteAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName238.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes238, parameterTypes)) {
			return AccountEntryServiceUtil.fetchCorpProjectAccountEntry((java.lang.String)arguments[0]);
		}

		if (_methodName239.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes239, parameterTypes)) {
			return AccountEntryServiceUtil.fetchCorpProjectIdAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName240.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes240, parameterTypes)) {
			return AccountEntryServiceUtil.getAccountEntries((java.lang.String)arguments[0],
				(long[])arguments[1]);
		}

		if (_methodName241.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes241, parameterTypes)) {
			return AccountEntryServiceUtil.getAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName242.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes242, parameterTypes)) {
			return AccountEntryServiceUtil.getAccountEntryByCode((java.lang.String)arguments[0]);
		}

		if (_methodName243.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes243, parameterTypes)) {
			return AccountEntryServiceUtil.getCorpProjectAccountEntry((java.lang.String)arguments[0]);
		}

		if (_methodName244.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes244, parameterTypes)) {
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

		if (_methodName245.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes245, parameterTypes)) {
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

		if (_methodName246.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes246, parameterTypes)) {
			return AccountEntryServiceUtil.updateInstructions(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName232;
	private String[] _methodParameterTypes232;
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
	private String _methodName244;
	private String[] _methodParameterTypes244;
	private String _methodName245;
	private String[] _methodParameterTypes245;
	private String _methodName246;
	private String[] _methodParameterTypes246;
}