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
		_methodName404 = "getOSGiServiceIdentifier";

		_methodParameterTypes404 = new String[] {  };

		_methodName409 = "addTicketEntry";

		_methodParameterTypes409 = new String[] {
				"long", "long", "long", "java.lang.String", "long",
				"java.lang.String", "java.lang.String", "int", "int", "int",
				"int", "int", "int", "java.util.Map", "java.util.List"
			};

		_methodName410 = "closeTicketEntry";

		_methodParameterTypes410 = new String[] {
				"long", "int", "java.lang.String"
			};

		_methodName411 = "escalateTicketEntry";

		_methodParameterTypes411 = new String[] { "long" };

		_methodName412 = "forwardTicketEntry";

		_methodParameterTypes412 = new String[] { "long", "java.lang.String" };

		_methodName413 = "getTicketEntries";

		_methodParameterTypes413 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName414 = "getTicketEntriesCount";

		_methodParameterTypes414 = new String[] { "long" };

		_methodName415 = "getTicketEntry";

		_methodParameterTypes415 = new String[] { "long" };

		_methodName416 = "getTicketEntry";

		_methodParameterTypes416 = new String[] { "long", "long" };

		_methodName417 = "search";

		_methodParameterTypes417 = new String[] {
				"long", "long", "java.lang.String", "int[][]",
				"java.lang.Boolean", "java.util.Date", "java.util.Date",
				"java.lang.String", "int[][]", "int[][]", "int[][]", "long[][]",
				"long[][]", "long[][]", "long[][]", "long[][]", "int[][]",
				"int[][]", "java.util.Date", "java.util.Date", "java.util.Date",
				"java.util.Date", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName418 = "search";

		_methodParameterTypes418 = new String[] {
				"long", "long", "java.lang.String", "java.util.LinkedHashMap",
				"int", "int", "com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName419 = "search";

		_methodParameterTypes419 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName420 = "search";

		_methodParameterTypes420 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName421 = "searchCount";

		_methodParameterTypes421 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean"
			};

		_methodName422 = "searchCount";

		_methodParameterTypes422 = new String[] { "java.lang.String" };

		_methodName423 = "updatePendingTypes";

		_methodParameterTypes423 = new String[] { "long", "int[][]" };

		_methodName424 = "updateTicketEntry";

		_methodParameterTypes424 = new String[] {
				"long", "long", "long", "long", "int", "int", "int", "int",
				"int"
			};

		_methodName425 = "updateTicketEntry";

		_methodParameterTypes425 = new String[] {
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
		if (_methodName404.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes404, parameterTypes)) {
			return TicketEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName409.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes409, parameterTypes)) {
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

		if (_methodName410.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes410, parameterTypes)) {
			TicketEntryServiceUtil.closeTicketEntry(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName411.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes411, parameterTypes)) {
			TicketEntryServiceUtil.escalateTicketEntry(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName412.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes412, parameterTypes)) {
			return TicketEntryServiceUtil.forwardTicketEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName413.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes413, parameterTypes)) {
			return TicketEntryServiceUtil.getTicketEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName414.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes414, parameterTypes)) {
			return TicketEntryServiceUtil.getTicketEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName415.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes415, parameterTypes)) {
			return TicketEntryServiceUtil.getTicketEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName416.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes416, parameterTypes)) {
			return TicketEntryServiceUtil.getTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName417.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes417, parameterTypes)) {
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

		if (_methodName418.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes418, parameterTypes)) {
			return TicketEntryServiceUtil.search(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.search.Sort[])arguments[6]);
		}

		if (_methodName419.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes419, parameterTypes)) {
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

		if (_methodName420.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes420, parameterTypes)) {
			return TicketEntryServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName421.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes421, parameterTypes)) {
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

		if (_methodName422.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes422, parameterTypes)) {
			return TicketEntryServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName423.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes423, parameterTypes)) {
			return TicketEntryServiceUtil.updatePendingTypes(((Long)arguments[0]).longValue(),
				(int[])arguments[1]);
		}

		if (_methodName424.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes424, parameterTypes)) {
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

		if (_methodName425.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes425, parameterTypes)) {
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

	private String _methodName404;
	private String[] _methodParameterTypes404;
	private String _methodName409;
	private String[] _methodParameterTypes409;
	private String _methodName410;
	private String[] _methodParameterTypes410;
	private String _methodName411;
	private String[] _methodParameterTypes411;
	private String _methodName412;
	private String[] _methodParameterTypes412;
	private String _methodName413;
	private String[] _methodParameterTypes413;
	private String _methodName414;
	private String[] _methodParameterTypes414;
	private String _methodName415;
	private String[] _methodParameterTypes415;
	private String _methodName416;
	private String[] _methodParameterTypes416;
	private String _methodName417;
	private String[] _methodParameterTypes417;
	private String _methodName418;
	private String[] _methodParameterTypes418;
	private String _methodName419;
	private String[] _methodParameterTypes419;
	private String _methodName420;
	private String[] _methodParameterTypes420;
	private String _methodName421;
	private String[] _methodParameterTypes421;
	private String _methodName422;
	private String[] _methodParameterTypes422;
	private String _methodName423;
	private String[] _methodParameterTypes423;
	private String _methodName424;
	private String[] _methodParameterTypes424;
	private String _methodName425;
	private String[] _methodParameterTypes425;
}