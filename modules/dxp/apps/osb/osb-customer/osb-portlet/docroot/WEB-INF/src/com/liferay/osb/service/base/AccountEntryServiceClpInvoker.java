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
		_methodName228 = "getOSGiServiceIdentifier";

		_methodParameterTypes228 = new String[] {  };

		_methodName233 = "fetchCorpProjectAccountEntry";

		_methodParameterTypes233 = new String[] { "long" };

		_methodName234 = "getAccountEntry";

		_methodParameterTypes234 = new String[] { "long" };

		_methodName235 = "getAccountEntryByCode";

		_methodParameterTypes235 = new String[] { "java.lang.String" };

		_methodName236 = "getSecurityPatchAccountEntries";

		_methodParameterTypes236 = new String[] { "java.lang.String" };

		_methodName237 = "search";

		_methodParameterTypes237 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int[][]",
				"java.lang.Boolean", "int[][]", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName238 = "search";

		_methodParameterTypes238 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName239 = "search";

		_methodParameterTypes239 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName240 = "searchCount";

		_methodParameterTypes240 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int[][]",
				"java.lang.Boolean", "int[][]", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.LinkedHashMap", "boolean"
			};

		_methodName241 = "searchCount";

		_methodParameterTypes241 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName242 = "updateAccountEntry";

		_methodParameterTypes242 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "int", "int", "long",
				"boolean", "int", "int", "java.lang.String", "java.lang.String",
				"java.lang.String[][]", "long[][]", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "java.lang.String"
			};

		_methodName243 = "updateInstructions";

		_methodParameterTypes243 = new String[] { "long", "java.lang.String" };

		_methodName244 = "updateTier";

		_methodParameterTypes244 = new String[] { "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName228.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes228, parameterTypes)) {
			return AccountEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName233.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes233, parameterTypes)) {
			return AccountEntryServiceUtil.fetchCorpProjectAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName234.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes234, parameterTypes)) {
			return AccountEntryServiceUtil.getAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName235.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes235, parameterTypes)) {
			return AccountEntryServiceUtil.getAccountEntryByCode((java.lang.String)arguments[0]);
		}

		if (_methodName236.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes236, parameterTypes)) {
			return AccountEntryServiceUtil.getSecurityPatchAccountEntries((java.lang.String)arguments[0]);
		}

		if (_methodName237.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes237, parameterTypes)) {
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
				(java.lang.String)arguments[15], (int[])arguments[16],
				(java.lang.Boolean)arguments[17], (int[])arguments[18],
				(int[])arguments[19], (java.lang.String)arguments[20],
				(java.lang.String)arguments[21],
				(java.lang.String)arguments[22],
				(java.lang.String)arguments[23], (java.lang.Long)arguments[24],
				(java.lang.Long)arguments[25], (java.lang.String)arguments[26],
				(java.lang.String)arguments[27],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[28],
				((Boolean)arguments[29]).booleanValue(),
				((Integer)arguments[30]).intValue(),
				((Integer)arguments[31]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[32]);
		}

		if (_methodName238.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes238, parameterTypes)) {
			return AccountEntryServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName239.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes239, parameterTypes)) {
			return AccountEntryServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName240.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes240, parameterTypes)) {
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
				(java.lang.String)arguments[15], (int[])arguments[16],
				(java.lang.Boolean)arguments[17], (int[])arguments[18],
				(int[])arguments[19], (java.lang.String)arguments[20],
				(java.lang.String)arguments[21],
				(java.lang.String)arguments[22],
				(java.lang.String)arguments[23], (java.lang.Long)arguments[24],
				(java.lang.Long)arguments[25], (java.lang.String)arguments[26],
				(java.lang.String)arguments[27],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[28],
				((Boolean)arguments[29]).booleanValue());
		}

		if (_methodName241.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes241, parameterTypes)) {
			return AccountEntryServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName242.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes242, parameterTypes)) {
			return AccountEntryServiceUtil.updateAccountEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Long)arguments[7]).longValue(),
				((Boolean)arguments[8]).booleanValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				(java.lang.String)arguments[11],
				(java.lang.String)arguments[12],
				(java.lang.String[])arguments[13], (long[])arguments[14],
				((Long)arguments[15]).longValue(),
				(java.lang.String)arguments[16],
				(java.lang.String)arguments[17],
				(java.lang.String)arguments[18],
				(java.lang.String)arguments[19],
				(java.lang.String)arguments[20],
				((Long)arguments[21]).longValue(),
				((Long)arguments[22]).longValue(),
				(java.lang.String)arguments[23]);
		}

		if (_methodName243.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes243, parameterTypes)) {
			return AccountEntryServiceUtil.updateInstructions(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName244.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes244, parameterTypes)) {
			return AccountEntryServiceUtil.updateTier(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName228;
	private String[] _methodParameterTypes228;
	private String _methodName233;
	private String[] _methodParameterTypes233;
	private String _methodName234;
	private String[] _methodParameterTypes234;
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
	private String _methodName244;
	private String[] _methodParameterTypes244;
}