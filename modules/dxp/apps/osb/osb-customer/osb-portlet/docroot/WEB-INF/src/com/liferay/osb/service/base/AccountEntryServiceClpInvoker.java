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
		_methodName384 = "getOSGiServiceIdentifier";

		_methodParameterTypes384 = new String[] {  };

		_methodName389 = "fetchCorpProjectAccountEntry";

		_methodParameterTypes389 = new String[] { "long" };

		_methodName390 = "getAccountEntry";

		_methodParameterTypes390 = new String[] { "long" };

		_methodName391 = "getAccountEntryByCode";

		_methodParameterTypes391 = new String[] { "java.lang.String" };

		_methodName392 = "getSecurityPatchAccountEntries";

		_methodParameterTypes392 = new String[] { "java.lang.String" };

		_methodName393 = "search";

		_methodParameterTypes393 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int[][]",
				"java.lang.Boolean", "int[][]", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName394 = "search";

		_methodParameterTypes394 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName395 = "search";

		_methodParameterTypes395 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName396 = "searchCount";

		_methodParameterTypes396 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int[][]",
				"java.lang.Boolean", "int[][]", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.LinkedHashMap", "boolean"
			};

		_methodName397 = "searchCount";

		_methodParameterTypes397 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName398 = "updateAccountEntry";

		_methodParameterTypes398 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "int", "int", "long", "boolean", "int",
				"int", "java.lang.String", "java.lang.String",
				"java.lang.String[][]", "long[][]", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "java.lang.String"
			};

		_methodName399 = "updateInstructions";

		_methodParameterTypes399 = new String[] { "long", "java.lang.String" };

		_methodName400 = "updateTier";

		_methodParameterTypes400 = new String[] { "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName384.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes384, parameterTypes)) {
			return AccountEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName389.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes389, parameterTypes)) {
			return AccountEntryServiceUtil.fetchCorpProjectAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName390.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes390, parameterTypes)) {
			return AccountEntryServiceUtil.getAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName391.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes391, parameterTypes)) {
			return AccountEntryServiceUtil.getAccountEntryByCode((java.lang.String)arguments[0]);
		}

		if (_methodName392.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes392, parameterTypes)) {
			return AccountEntryServiceUtil.getSecurityPatchAccountEntries((java.lang.String)arguments[0]);
		}

		if (_methodName393.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes393, parameterTypes)) {
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

		if (_methodName394.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes394, parameterTypes)) {
			return AccountEntryServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName395.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes395, parameterTypes)) {
			return AccountEntryServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName396.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes396, parameterTypes)) {
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

		if (_methodName397.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes397, parameterTypes)) {
			return AccountEntryServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName398.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes398, parameterTypes)) {
			return AccountEntryServiceUtil.updateAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4],
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

		if (_methodName399.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes399, parameterTypes)) {
			return AccountEntryServiceUtil.updateInstructions(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName400.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes400, parameterTypes)) {
			return AccountEntryServiceUtil.updateTier(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName384;
	private String[] _methodParameterTypes384;
	private String _methodName389;
	private String[] _methodParameterTypes389;
	private String _methodName390;
	private String[] _methodParameterTypes390;
	private String _methodName391;
	private String[] _methodParameterTypes391;
	private String _methodName392;
	private String[] _methodParameterTypes392;
	private String _methodName393;
	private String[] _methodParameterTypes393;
	private String _methodName394;
	private String[] _methodParameterTypes394;
	private String _methodName395;
	private String[] _methodParameterTypes395;
	private String _methodName396;
	private String[] _methodParameterTypes396;
	private String _methodName397;
	private String[] _methodParameterTypes397;
	private String _methodName398;
	private String[] _methodParameterTypes398;
	private String _methodName399;
	private String[] _methodParameterTypes399;
	private String _methodName400;
	private String[] _methodParameterTypes400;
}