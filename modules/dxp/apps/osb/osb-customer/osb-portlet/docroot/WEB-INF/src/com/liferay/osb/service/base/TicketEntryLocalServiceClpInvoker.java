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

import com.liferay.osb.service.TicketEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketEntryLocalServiceClpInvoker {
	public TicketEntryLocalServiceClpInvoker() {
		_methodName0 = "addTicketEntry";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName1 = "createTicketEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTicketEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTicketEntry";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchTicketEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getTicketEntry";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getActionableDynamicQuery";

		_methodParameterTypes12 = new String[] {  };

		_methodName13 = "getIndexableActionableDynamicQuery";

		_methodParameterTypes13 = new String[] {  };

		_methodName15 = "deletePersistedModel";

		_methodParameterTypes15 = new String[] {
				"com.liferay.portal.kernel.model.PersistedModel"
			};

		_methodName16 = "getPersistedModel";

		_methodParameterTypes16 = new String[] { "java.io.Serializable" };

		_methodName17 = "getTicketEntries";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getTicketEntriesCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateTicketEntry";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName294 = "getOSGiServiceIdentifier";

		_methodParameterTypes294 = new String[] {  };

		_methodName299 = "addTicketEntry";

		_methodParameterTypes299 = new String[] {
				"long", "long", "long", "java.lang.String", "long",
				"java.lang.String", "java.lang.String", "int", "int", "int",
				"int", "int", "int", "java.util.Map", "java.util.List"
			};

		_methodName300 = "checkInactiveTicketEntries";

		_methodParameterTypes300 = new String[] {  };

		_methodName301 = "checkOnHoldTicketEntries";

		_methodParameterTypes301 = new String[] {  };

		_methodName302 = "deleteTicketEntries";

		_methodParameterTypes302 = new String[] { "long" };

		_methodName303 = "deleteTicketEntry";

		_methodParameterTypes303 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName304 = "escalateTicketEntry";

		_methodParameterTypes304 = new String[] { "long", "long" };

		_methodName305 = "forwardTicketEntry";

		_methodParameterTypes305 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName306 = "getTicketEntries";

		_methodParameterTypes306 = new String[] { "java.util.Date", "int", "int" };

		_methodName307 = "getTicketEntries";

		_methodParameterTypes307 = new String[] {
				"com.liferay.portal.kernel.search.Hits"
			};

		_methodName308 = "getTicketEntriesCount";

		_methodParameterTypes308 = new String[] { "java.util.Date" };

		_methodName309 = "getTicketEntry";

		_methodParameterTypes309 = new String[] { "long", "long" };

		_methodName310 = "getTicketFeedbackTicketEntries";

		_methodParameterTypes310 = new String[] {
				"long", "int", "int", "int", "int"
			};

		_methodName311 = "getValidTicketEntriesCount";

		_methodParameterTypes311 = new String[] { "long" };

		_methodName312 = "hasParticipant";

		_methodParameterTypes312 = new String[] { "long", "long" };

		_methodName313 = "reindexTicketEntry";

		_methodParameterTypes313 = new String[] { "long" };

		_methodName314 = "reindexTicketEntry";

		_methodParameterTypes314 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName315 = "search";

		_methodParameterTypes315 = new String[] {
				"long", "long", "long", "java.lang.String", "int[][]",
				"java.lang.Boolean", "java.util.Date", "java.util.Date",
				"java.lang.String", "int[][]", "int[][]", "int[][]", "long[][]",
				"long[][]", "long[][]", "long[][]", "long[][]", "int[][]",
				"int[][]", "java.util.Date", "java.util.Date", "java.util.Date",
				"java.util.Date", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName316 = "search";

		_methodParameterTypes316 = new String[] {
				"long", "long", "long", "java.lang.String",
				"java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName317 = "search";

		_methodParameterTypes317 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName318 = "search";

		_methodParameterTypes318 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName319 = "searchCount";

		_methodParameterTypes319 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean"
			};

		_methodName320 = "searchCount";

		_methodParameterTypes320 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName321 = "sendEmail";

		_methodParameterTypes321 = new String[] {
				"long", "long", "com.liferay.osb.model.TicketComment",
				"java.lang.String"
			};

		_methodName322 = "sendEmail";

		_methodParameterTypes322 = new String[] {
				"long", "com.liferay.osb.model.TicketEntry",
				"com.liferay.osb.model.TicketComment", "java.lang.String"
			};

		_methodName323 = "syncToJIRA";

		_methodParameterTypes323 = new String[] { "long" };

		_methodName324 = "updateCustomerModifiedDate";

		_methodParameterTypes324 = new String[] { "long", "long", "java.util.Date" };

		_methodName325 = "updatePendingTypes";

		_methodParameterTypes325 = new String[] { "long", "long", "int[][]" };

		_methodName326 = "updateTicketEntry";

		_methodParameterTypes326 = new String[] {
				"long", "long", "long", "long", "int", "int", "int", "int",
				"int"
			};

		_methodName327 = "updateTicketEntry";

		_methodParameterTypes327 = new String[] {
				"long", "long", "long", "long", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "boolean",
				"java.util.Map", "int[][]", "java.util.List",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName328 = "updateWorkerModifiedDate";

		_methodParameterTypes328 = new String[] { "long", "java.util.Date" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return TicketEntryLocalServiceUtil.addTicketEntry((com.liferay.osb.model.TicketEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return TicketEntryLocalServiceUtil.createTicketEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return TicketEntryLocalServiceUtil.deleteTicketEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return TicketEntryLocalServiceUtil.deleteTicketEntry((com.liferay.osb.model.TicketEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return TicketEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return TicketEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return TicketEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return TicketEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TicketEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TicketEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TicketEntryLocalServiceUtil.fetchTicketEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TicketEntryLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntriesCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return TicketEntryLocalServiceUtil.updateTicketEntry((com.liferay.osb.model.TicketEntry)arguments[0]);
		}

		if (_methodName294.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes294, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName299.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes299, parameterTypes)) {
			return TicketEntryLocalServiceUtil.addTicketEntry(((Long)arguments[0]).longValue(),
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

		if (_methodName300.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes300, parameterTypes)) {
			TicketEntryLocalServiceUtil.checkInactiveTicketEntries();

			return null;
		}

		if (_methodName301.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes301, parameterTypes)) {
			TicketEntryLocalServiceUtil.checkOnHoldTicketEntries();

			return null;
		}

		if (_methodName302.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes302, parameterTypes)) {
			TicketEntryLocalServiceUtil.deleteTicketEntries(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName303.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes303, parameterTypes)) {
			return TicketEntryLocalServiceUtil.deleteTicketEntry((com.liferay.osb.model.TicketEntry)arguments[0]);
		}

		if (_methodName304.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes304, parameterTypes)) {
			TicketEntryLocalServiceUtil.escalateTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName305.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes305, parameterTypes)) {
			return TicketEntryLocalServiceUtil.forwardTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName306.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes306, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntries((java.util.Date)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName307.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes307, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntries((com.liferay.portal.kernel.search.Hits)arguments[0]);
		}

		if (_methodName308.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes308, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntriesCount((java.util.Date)arguments[0]);
		}

		if (_methodName309.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes309, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName310.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes310, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketFeedbackTicketEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName311.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes311, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getValidTicketEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName312.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes312, parameterTypes)) {
			return TicketEntryLocalServiceUtil.hasParticipant(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName313.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes313, parameterTypes)) {
			TicketEntryLocalServiceUtil.reindexTicketEntry(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName314.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes314, parameterTypes)) {
			TicketEntryLocalServiceUtil.reindexTicketEntry((com.liferay.osb.model.TicketEntry)arguments[0]);

			return null;
		}

		if (_methodName315.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes315, parameterTypes)) {
			return TicketEntryLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (int[])arguments[4],
				(java.lang.Boolean)arguments[5], (java.util.Date)arguments[6],
				(java.util.Date)arguments[7], (java.lang.String)arguments[8],
				(int[])arguments[9], (int[])arguments[10],
				(int[])arguments[11], (long[])arguments[12],
				(long[])arguments[13], (long[])arguments[14],
				(long[])arguments[15], (long[])arguments[16],
				(int[])arguments[17], (int[])arguments[18],
				(java.util.Date)arguments[19], (java.util.Date)arguments[20],
				(java.util.Date)arguments[21], (java.util.Date)arguments[22],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[23],
				((Boolean)arguments[24]).booleanValue(),
				((Integer)arguments[25]).intValue(),
				((Integer)arguments[26]).intValue(),
				(com.liferay.portal.kernel.search.Sort[])arguments[27]);
		}

		if (_methodName316.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes316, parameterTypes)) {
			return TicketEntryLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(com.liferay.portal.kernel.search.Sort[])arguments[7]);
		}

		if (_methodName317.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes317, parameterTypes)) {
			return TicketEntryLocalServiceUtil.search(((Long)arguments[0]).longValue(),
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

		if (_methodName318.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes318, parameterTypes)) {
			return TicketEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName319.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes319, parameterTypes)) {
			return TicketEntryLocalServiceUtil.searchCount(((Long)arguments[0]).longValue(),
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

		if (_methodName320.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes320, parameterTypes)) {
			return TicketEntryLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName321.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes321, parameterTypes)) {
			TicketEntryLocalServiceUtil.sendEmail(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.osb.model.TicketComment)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName322.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes322, parameterTypes)) {
			TicketEntryLocalServiceUtil.sendEmail(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.TicketEntry)arguments[1],
				(com.liferay.osb.model.TicketComment)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName323.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes323, parameterTypes)) {
			TicketEntryLocalServiceUtil.syncToJIRA(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName324.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes324, parameterTypes)) {
			return TicketEntryLocalServiceUtil.updateCustomerModifiedDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Date)arguments[2]);
		}

		if (_methodName325.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes325, parameterTypes)) {
			return TicketEntryLocalServiceUtil.updatePendingTypes(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (int[])arguments[2]);
		}

		if (_methodName326.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes326, parameterTypes)) {
			return TicketEntryLocalServiceUtil.updateTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue());
		}

		if (_methodName327.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes327, parameterTypes)) {
			return TicketEntryLocalServiceUtil.updateTicketEntry(((Long)arguments[0]).longValue(),
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

		if (_methodName328.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes328, parameterTypes)) {
			return TicketEntryLocalServiceUtil.updateWorkerModifiedDate(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName18;
	private String[] _methodParameterTypes18;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName294;
	private String[] _methodParameterTypes294;
	private String _methodName299;
	private String[] _methodParameterTypes299;
	private String _methodName300;
	private String[] _methodParameterTypes300;
	private String _methodName301;
	private String[] _methodParameterTypes301;
	private String _methodName302;
	private String[] _methodParameterTypes302;
	private String _methodName303;
	private String[] _methodParameterTypes303;
	private String _methodName304;
	private String[] _methodParameterTypes304;
	private String _methodName305;
	private String[] _methodParameterTypes305;
	private String _methodName306;
	private String[] _methodParameterTypes306;
	private String _methodName307;
	private String[] _methodParameterTypes307;
	private String _methodName308;
	private String[] _methodParameterTypes308;
	private String _methodName309;
	private String[] _methodParameterTypes309;
	private String _methodName310;
	private String[] _methodParameterTypes310;
	private String _methodName311;
	private String[] _methodParameterTypes311;
	private String _methodName312;
	private String[] _methodParameterTypes312;
	private String _methodName313;
	private String[] _methodParameterTypes313;
	private String _methodName314;
	private String[] _methodParameterTypes314;
	private String _methodName315;
	private String[] _methodParameterTypes315;
	private String _methodName316;
	private String[] _methodParameterTypes316;
	private String _methodName317;
	private String[] _methodParameterTypes317;
	private String _methodName318;
	private String[] _methodParameterTypes318;
	private String _methodName319;
	private String[] _methodParameterTypes319;
	private String _methodName320;
	private String[] _methodParameterTypes320;
	private String _methodName321;
	private String[] _methodParameterTypes321;
	private String _methodName322;
	private String[] _methodParameterTypes322;
	private String _methodName323;
	private String[] _methodParameterTypes323;
	private String _methodName324;
	private String[] _methodParameterTypes324;
	private String _methodName325;
	private String[] _methodParameterTypes325;
	private String _methodName326;
	private String[] _methodParameterTypes326;
	private String _methodName327;
	private String[] _methodParameterTypes327;
	private String _methodName328;
	private String[] _methodParameterTypes328;
}