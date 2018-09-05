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
		_methodName234 = "getOSGiServiceIdentifier";

		_methodParameterTypes234 = new String[] {  };

		_methodName239 = "fetchCorpProjectAccountEntry";

		_methodParameterTypes239 = new String[] { "java.lang.String" };

		_methodName240 = "getAccountEntry";

		_methodParameterTypes240 = new String[] { "long" };

		_methodName241 = "getAccountEntryByCode";

		_methodParameterTypes241 = new String[] { "java.lang.String" };

		_methodName242 = "getSecurityPatchAccountEntries";

		_methodParameterTypes242 = new String[] { "java.lang.String" };

		_methodName243 = "search";

		_methodParameterTypes243 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int[][]",
				"java.lang.Boolean", "int[][]", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName244 = "searchCount";

		_methodParameterTypes244 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int[][]",
				"java.lang.Boolean", "int[][]", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.LinkedHashMap", "boolean"
			};

		_methodName245 = "updateInstructions";

		_methodParameterTypes245 = new String[] { "long", "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName234.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes234, parameterTypes)) {
			return AccountEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName239.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes239, parameterTypes)) {
			return AccountEntryServiceUtil.fetchCorpProjectAccountEntry((java.lang.String)arguments[0]);
		}

		if (_methodName240.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes240, parameterTypes)) {
			return AccountEntryServiceUtil.getAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName241.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes241, parameterTypes)) {
			return AccountEntryServiceUtil.getAccountEntryByCode((java.lang.String)arguments[0]);
		}

		if (_methodName242.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes242, parameterTypes)) {
			return AccountEntryServiceUtil.getSecurityPatchAccountEntries((java.lang.String)arguments[0]);
		}

		if (_methodName243.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes243, parameterTypes)) {
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

		if (_methodName244.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes244, parameterTypes)) {
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

		if (_methodName245.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes245, parameterTypes)) {
			return AccountEntryServiceUtil.updateInstructions(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName234;
	private String[] _methodParameterTypes234;
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
}