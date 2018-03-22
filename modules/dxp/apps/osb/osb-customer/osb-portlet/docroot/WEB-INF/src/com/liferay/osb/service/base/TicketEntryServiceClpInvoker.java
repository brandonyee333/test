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

import com.liferay.osb.service.TicketEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketEntryServiceClpInvoker {
	public TicketEntryServiceClpInvoker() {
		_methodName388 = "getOSGiServiceIdentifier";

		_methodParameterTypes388 = new String[] {  };

		_methodName393 = "addTicketEntry";

		_methodParameterTypes393 = new String[] {
				"long", "long", "long", "java.lang.String", "long",
				"java.lang.String", "java.lang.String", "int", "int", "int",
				"int", "int", "int", "java.util.Map", "java.util.List"
			};

		_methodName394 = "closeTicketEntry";

		_methodParameterTypes394 = new String[] {
				"long", "int", "java.lang.String"
			};

		_methodName395 = "escalateTicketEntry";

		_methodParameterTypes395 = new String[] { "long" };

		_methodName396 = "forwardTicketEntry";

		_methodParameterTypes396 = new String[] { "long", "java.lang.String" };

		_methodName397 = "getTicketEntry";

		_methodParameterTypes397 = new String[] { "long" };

		_methodName398 = "getTicketEntry";

		_methodParameterTypes398 = new String[] { "long", "long" };

		_methodName399 = "search";

		_methodParameterTypes399 = new String[] {
				"long", "long", "java.lang.String", "int[][]",
				"java.lang.Boolean", "java.util.Date", "java.util.Date",
				"java.lang.String", "int[][]", "int[][]", "int[][]", "long[][]",
				"long[][]", "long[][]", "long[][]", "long[][]", "int[][]",
				"int[][]", "java.util.Date", "java.util.Date", "java.util.Date",
				"java.util.Date", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName400 = "search";

		_methodParameterTypes400 = new String[] {
				"long", "long", "java.lang.String", "java.util.LinkedHashMap",
				"int", "int", "com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName401 = "search";

		_methodParameterTypes401 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName402 = "search";

		_methodParameterTypes402 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName403 = "searchCount";

		_methodParameterTypes403 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean"
			};

		_methodName404 = "searchCount";

		_methodParameterTypes404 = new String[] { "java.lang.String" };

		_methodName405 = "updatePendingTypes";

		_methodParameterTypes405 = new String[] { "long", "int[][]" };

		_methodName406 = "updateTicketEntry";

		_methodParameterTypes406 = new String[] {
				"long", "long", "long", "long", "int", "int", "int", "int",
				"int"
			};

		_methodName407 = "updateTicketEntry";

		_methodParameterTypes407 = new String[] {
				"long", "long", "long", "long", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "boolean",
				"java.util.Map", "int[][]", "java.util.List",
				"com.liferay.portal.kernel.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName388.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes388, parameterTypes)) {
			return TicketEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName393.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes393, parameterTypes)) {
			return TicketEntryServiceUtil.addTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				(java.util.Map<java.lang.Long, java.lang.String>)arguments[13],
				(java.util.List<com.liferay.osb.model.TicketAttachment>)arguments[14]);
		}

		if (_methodName394.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes394, parameterTypes)) {
			TicketEntryServiceUtil.closeTicketEntry(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName395.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes395, parameterTypes)) {
			TicketEntryServiceUtil.escalateTicketEntry(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName396.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes396, parameterTypes)) {
			return TicketEntryServiceUtil.forwardTicketEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName397.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes397, parameterTypes)) {
			return TicketEntryServiceUtil.getTicketEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName398.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes398, parameterTypes)) {
			return TicketEntryServiceUtil.getTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName399.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes399, parameterTypes)) {
			return TicketEntryServiceUtil.search(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (int[])arguments[3],
				(java.lang.Boolean)arguments[4], (java.util.Date)arguments[5],
				(java.util.Date)arguments[6], (java.lang.String)arguments[7],
				(int[])arguments[8], (int[])arguments[9], (int[])arguments[10],
				(long[])arguments[11], (long[])arguments[12],
				(long[])arguments[13], (long[])arguments[14],
				(long[])arguments[15], (int[])arguments[16],
				(int[])arguments[17], (java.util.Date)arguments[18],
				(java.util.Date)arguments[19], (java.util.Date)arguments[20],
				(java.util.Date)arguments[21],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[22],
				((Boolean)arguments[23]).booleanValue(),
				((Integer)arguments[24]).intValue(),
				((Integer)arguments[25]).intValue(),
				(com.liferay.portal.kernel.search.Sort[])arguments[26]);
		}

		if (_methodName400.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes400, parameterTypes)) {
			return TicketEntryServiceUtil.search(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.search.Sort[])arguments[6]);
		}

		if (_methodName401.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes401, parameterTypes)) {
			return TicketEntryServiceUtil.search(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (int[])arguments[2],
				(java.lang.Boolean)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(java.lang.String)arguments[10],
				(java.lang.String)arguments[11],
				(java.lang.String)arguments[12], (int[])arguments[13],
				(int[])arguments[14], (int[])arguments[15],
				(int[])arguments[16], (long[])arguments[17],
				(long[])arguments[18], (long[])arguments[19],
				(long[])arguments[20], (long[])arguments[21],
				(int[])arguments[22], (int[])arguments[23],
				((Integer)arguments[24]).intValue(),
				((Integer)arguments[25]).intValue(),
				((Integer)arguments[26]).intValue(),
				((Integer)arguments[27]).intValue(),
				((Integer)arguments[28]).intValue(),
				((Integer)arguments[29]).intValue(),
				((Integer)arguments[30]).intValue(),
				((Integer)arguments[31]).intValue(),
				((Integer)arguments[32]).intValue(),
				((Integer)arguments[33]).intValue(),
				((Integer)arguments[34]).intValue(),
				((Integer)arguments[35]).intValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[36],
				((Boolean)arguments[37]).booleanValue(),
				((Integer)arguments[38]).intValue(),
				((Integer)arguments[39]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[40]);
		}

		if (_methodName402.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes402, parameterTypes)) {
			return TicketEntryServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName403.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes403, parameterTypes)) {
			return TicketEntryServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (int[])arguments[2],
				(java.lang.Boolean)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(java.lang.String)arguments[10],
				(java.lang.String)arguments[11],
				(java.lang.String)arguments[12], (int[])arguments[13],
				(int[])arguments[14], (int[])arguments[15],
				(int[])arguments[16], (long[])arguments[17],
				(long[])arguments[18], (long[])arguments[19],
				(long[])arguments[20], (long[])arguments[21],
				(int[])arguments[22], (int[])arguments[23],
				((Integer)arguments[24]).intValue(),
				((Integer)arguments[25]).intValue(),
				((Integer)arguments[26]).intValue(),
				((Integer)arguments[27]).intValue(),
				((Integer)arguments[28]).intValue(),
				((Integer)arguments[29]).intValue(),
				((Integer)arguments[30]).intValue(),
				((Integer)arguments[31]).intValue(),
				((Integer)arguments[32]).intValue(),
				((Integer)arguments[33]).intValue(),
				((Integer)arguments[34]).intValue(),
				((Integer)arguments[35]).intValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[36],
				((Boolean)arguments[37]).booleanValue());
		}

		if (_methodName404.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes404, parameterTypes)) {
			return TicketEntryServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName405.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes405, parameterTypes)) {
			return TicketEntryServiceUtil.updatePendingTypes(((Long)arguments[0]).longValue(),
				(int[])arguments[1]);
		}

		if (_methodName406.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes406, parameterTypes)) {
			return TicketEntryServiceUtil.updateTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue());
		}

		if (_methodName407.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes407, parameterTypes)) {
			return TicketEntryServiceUtil.updateTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				((Integer)arguments[14]).intValue(),
				(java.lang.String)arguments[15],
				((Integer)arguments[16]).intValue(),
				((Integer)arguments[17]).intValue(),
				((Integer)arguments[18]).intValue(),
				((Integer)arguments[19]).intValue(),
				((Integer)arguments[20]).intValue(),
				((Integer)arguments[21]).intValue(),
				((Boolean)arguments[22]).booleanValue(),
				(java.util.Map<java.lang.Long, java.lang.String>)arguments[23],
				(int[])arguments[24],
				(java.util.List<com.liferay.osb.model.TicketAttachment>)arguments[25],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[26]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName388;
	private String[] _methodParameterTypes388;
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
	private String _methodName401;
	private String[] _methodParameterTypes401;
	private String _methodName402;
	private String[] _methodParameterTypes402;
	private String _methodName403;
	private String[] _methodParameterTypes403;
	private String _methodName404;
	private String[] _methodParameterTypes404;
	private String _methodName405;
	private String[] _methodParameterTypes405;
	private String _methodName406;
	private String[] _methodParameterTypes406;
	private String _methodName407;
	private String[] _methodParameterTypes407;
}