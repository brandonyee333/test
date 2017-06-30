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

import com.liferay.osb.service.TicketEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TicketEntryServiceClpInvoker {
	public TicketEntryServiceClpInvoker() {
		_methodName692 = "getBeanIdentifier";

		_methodParameterTypes692 = new String[] {  };

		_methodName693 = "setBeanIdentifier";

		_methodParameterTypes693 = new String[] { "java.lang.String" };

		_methodName698 = "addTicketEntry";

		_methodParameterTypes698 = new String[] {
				"long", "long", "long", "java.lang.String", "long",
				"java.lang.String", "java.lang.String", "int", "int", "int",
				"int", "int", "int", "java.util.Map", "java.util.List"
			};

		_methodName699 = "closeTicketEntry";

		_methodParameterTypes699 = new String[] {
				"long", "int", "java.lang.String"
			};

		_methodName700 = "escalateTicketEntry";

		_methodParameterTypes700 = new String[] { "long" };

		_methodName701 = "forwardTicketEntry";

		_methodParameterTypes701 = new String[] { "long", "java.lang.String" };

		_methodName702 = "getTicketEntries";

		_methodParameterTypes702 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName703 = "getTicketEntriesCount";

		_methodParameterTypes703 = new String[] { "long" };

		_methodName704 = "getTicketEntry";

		_methodParameterTypes704 = new String[] { "long" };

		_methodName705 = "getTicketEntry";

		_methodParameterTypes705 = new String[] { "long", "long" };

		_methodName706 = "search";

		_methodParameterTypes706 = new String[] {
				"long", "long", "java.lang.String", "int[][]",
				"java.lang.Boolean", "java.util.Date", "java.util.Date",
				"java.lang.String", "int[][]", "int[][]", "int[][]", "long[][]",
				"long[][]", "long[][]", "long[][]", "long[][]", "int[][]",
				"int[][]", "java.util.Date", "java.util.Date", "java.util.Date",
				"java.util.Date", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName707 = "search";

		_methodParameterTypes707 = new String[] {
				"long", "long", "java.lang.String", "java.util.LinkedHashMap",
				"int", "int", "com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName708 = "search";

		_methodParameterTypes708 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName709 = "search";

		_methodParameterTypes709 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName710 = "searchCount";

		_methodParameterTypes710 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean"
			};

		_methodName711 = "searchCount";

		_methodParameterTypes711 = new String[] { "java.lang.String" };

		_methodName712 = "updatePendingTypes";

		_methodParameterTypes712 = new String[] { "long", "int[][]" };

		_methodName713 = "updateTicketEntry";

		_methodParameterTypes713 = new String[] {
				"long", "long", "long", "long", "int", "int", "int", "int",
				"int"
			};

		_methodName714 = "updateTicketEntry";

		_methodParameterTypes714 = new String[] {
				"long", "long", "long", "long", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "boolean",
				"java.util.Map", "int[][]", "java.util.List",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return TicketEntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			TicketEntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
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

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			TicketEntryServiceUtil.closeTicketEntry(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			TicketEntryServiceUtil.escalateTicketEntry(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return TicketEntryServiceUtil.forwardTicketEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return TicketEntryServiceUtil.getTicketEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return TicketEntryServiceUtil.getTicketEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return TicketEntryServiceUtil.getTicketEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return TicketEntryServiceUtil.getTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName706.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes706, parameterTypes)) {
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

		if (_methodName707.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes707, parameterTypes)) {
			return TicketEntryServiceUtil.search(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.search.Sort[])arguments[6]);
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
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

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			return TicketEntryServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName710.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes710, parameterTypes)) {
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

		if (_methodName711.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes711, parameterTypes)) {
			return TicketEntryServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName712.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes712, parameterTypes)) {
			return TicketEntryServiceUtil.updatePendingTypes(((Long)arguments[0]).longValue(),
				(int[])arguments[1]);
		}

		if (_methodName713.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes713, parameterTypes)) {
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

		if (_methodName714.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes714, parameterTypes)) {
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
				(com.liferay.portal.service.ServiceContext)arguments[26]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName692;
	private String[] _methodParameterTypes692;
	private String _methodName693;
	private String[] _methodParameterTypes693;
	private String _methodName698;
	private String[] _methodParameterTypes698;
	private String _methodName699;
	private String[] _methodParameterTypes699;
	private String _methodName700;
	private String[] _methodParameterTypes700;
	private String _methodName701;
	private String[] _methodParameterTypes701;
	private String _methodName702;
	private String[] _methodParameterTypes702;
	private String _methodName703;
	private String[] _methodParameterTypes703;
	private String _methodName704;
	private String[] _methodParameterTypes704;
	private String _methodName705;
	private String[] _methodParameterTypes705;
	private String _methodName706;
	private String[] _methodParameterTypes706;
	private String _methodName707;
	private String[] _methodParameterTypes707;
	private String _methodName708;
	private String[] _methodParameterTypes708;
	private String _methodName709;
	private String[] _methodParameterTypes709;
	private String _methodName710;
	private String[] _methodParameterTypes710;
	private String _methodName711;
	private String[] _methodParameterTypes711;
	private String _methodName712;
	private String[] _methodParameterTypes712;
	private String _methodName713;
	private String[] _methodParameterTypes713;
	private String _methodName714;
	private String[] _methodParameterTypes714;
}