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

import com.liferay.osb.service.TicketEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
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

		_methodName9 = "fetchTicketEntry";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getTicketEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getTicketEntries";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getTicketEntriesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateTicketEntry";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName15 = "updateTicketEntry";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.TicketEntry", "boolean"
			};

		_methodName708 = "getBeanIdentifier";

		_methodParameterTypes708 = new String[] {  };

		_methodName709 = "setBeanIdentifier";

		_methodParameterTypes709 = new String[] { "java.lang.String" };

		_methodName714 = "addTicketEntry";

		_methodParameterTypes714 = new String[] {
				"long", "long", "long", "java.lang.String", "long",
				"java.lang.String", "java.lang.String", "int", "int", "int",
				"int", "int", "int", "java.util.Map", "java.util.List"
			};

		_methodName715 = "checkInactiveTicketEntries";

		_methodParameterTypes715 = new String[] {  };

		_methodName716 = "checkOnHoldTicketEntries";

		_methodParameterTypes716 = new String[] {  };

		_methodName717 = "deleteTicketEntries";

		_methodParameterTypes717 = new String[] { "long" };

		_methodName718 = "deleteTicketEntry";

		_methodParameterTypes718 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName719 = "escalateTicketEntry";

		_methodParameterTypes719 = new String[] { "long", "long" };

		_methodName720 = "forwardTicketEntry";

		_methodParameterTypes720 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName721 = "getTicketEntries";

		_methodParameterTypes721 = new String[] { "java.util.Date", "int", "int" };

		_methodName722 = "getTicketEntries";

		_methodParameterTypes722 = new String[] {
				"com.liferay.portal.kernel.search.Hits"
			};

		_methodName723 = "getTicketEntries";

		_methodParameterTypes723 = new String[] { "long" };

		_methodName724 = "getTicketEntries";

		_methodParameterTypes724 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName725 = "getTicketEntriesCount";

		_methodParameterTypes725 = new String[] { "java.util.Date" };

		_methodName726 = "getTicketEntriesCount";

		_methodParameterTypes726 = new String[] { "long" };

		_methodName727 = "getTicketEntry";

		_methodParameterTypes727 = new String[] { "long", "long" };

		_methodName728 = "getTicketFeedbackTicketEntries";

		_methodParameterTypes728 = new String[] {
				"long", "int", "int", "int", "int"
			};

		_methodName729 = "getUserVisibilities";

		_methodParameterTypes729 = new String[] { "long", "long" };

		_methodName730 = "getValidTicketEntries";

		_methodParameterTypes730 = new String[] { "long" };

		_methodName731 = "getValidTicketEntriesCount";

		_methodParameterTypes731 = new String[] { "long" };

		_methodName732 = "hasParticipant";

		_methodParameterTypes732 = new String[] { "long", "long" };

		_methodName733 = "hasParticipant";

		_methodParameterTypes733 = new String[] {
				"long", "com.liferay.osb.model.TicketEntry"
			};

		_methodName734 = "hasVisibility";

		_methodParameterTypes734 = new String[] { "long", "long", "int" };

		_methodName735 = "reindexTicketEntry";

		_methodParameterTypes735 = new String[] { "long" };

		_methodName736 = "reindexTicketEntry";

		_methodParameterTypes736 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName737 = "search";

		_methodParameterTypes737 = new String[] {
				"long", "long", "long", "java.lang.String", "int[][]",
				"java.lang.Boolean", "java.util.Date", "java.util.Date",
				"java.lang.String", "int[][]", "int[][]", "int[][]", "long[][]",
				"long[][]", "long[][]", "long[][]", "long[][]", "int[][]",
				"int[][]", "java.util.Date", "java.util.Date", "java.util.Date",
				"java.util.Date", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName738 = "search";

		_methodParameterTypes738 = new String[] {
				"long", "long", "long", "java.lang.String",
				"java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName739 = "search";

		_methodParameterTypes739 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName740 = "search";

		_methodParameterTypes740 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName741 = "searchCount";

		_methodParameterTypes741 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean"
			};

		_methodName742 = "searchCount";

		_methodParameterTypes742 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName743 = "sendEmail";

		_methodParameterTypes743 = new String[] {
				"long", "long", "com.liferay.osb.model.TicketComment",
				"java.lang.String"
			};

		_methodName744 = "sendEmail";

		_methodParameterTypes744 = new String[] {
				"long", "com.liferay.osb.model.TicketEntry",
				"com.liferay.osb.model.TicketComment", "java.lang.String"
			};

		_methodName745 = "syncToJIRA";

		_methodParameterTypes745 = new String[] { "long" };

		_methodName746 = "updateCustomerModifiedDate";

		_methodParameterTypes746 = new String[] { "long", "long", "java.util.Date" };

		_methodName747 = "updatePendingTypes";

		_methodParameterTypes747 = new String[] { "long", "long", "int[][]" };

		_methodName748 = "updateTicketEntry";

		_methodParameterTypes748 = new String[] {
				"long", "long", "long", "long", "int", "int", "int", "int",
				"int"
			};

		_methodName749 = "updateTicketEntry";

		_methodParameterTypes749 = new String[] {
				"long", "long", "long", "long", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "boolean",
				"java.util.Map", "int[][]", "java.util.List",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName750 = "updateTicketId";

		_methodParameterTypes750 = new String[] { "long", "long" };

		_methodName751 = "updateWorkerModifiedDate";

		_methodParameterTypes751 = new String[] { "long", "java.util.Date" };
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
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TicketEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TicketEntryLocalServiceUtil.fetchTicketEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntriesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return TicketEntryLocalServiceUtil.updateTicketEntry((com.liferay.osb.model.TicketEntry)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TicketEntryLocalServiceUtil.updateTicketEntry((com.liferay.osb.model.TicketEntry)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			TicketEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName714.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes714, parameterTypes)) {
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

		if (_methodName715.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes715, parameterTypes)) {
			TicketEntryLocalServiceUtil.checkInactiveTicketEntries();

			return null;
		}

		if (_methodName716.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes716, parameterTypes)) {
			TicketEntryLocalServiceUtil.checkOnHoldTicketEntries();

			return null;
		}

		if (_methodName717.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes717, parameterTypes)) {
			TicketEntryLocalServiceUtil.deleteTicketEntries(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName718.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes718, parameterTypes)) {
			return TicketEntryLocalServiceUtil.deleteTicketEntry((com.liferay.osb.model.TicketEntry)arguments[0]);
		}

		if (_methodName719.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes719, parameterTypes)) {
			TicketEntryLocalServiceUtil.escalateTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName720.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes720, parameterTypes)) {
			return TicketEntryLocalServiceUtil.forwardTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName721.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes721, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntries((java.util.Date)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName722.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes722, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntries((com.liferay.portal.kernel.search.Hits)arguments[0]);
		}

		if (_methodName723.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes723, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName724.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes724, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName725.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes725, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntriesCount((java.util.Date)arguments[0]);
		}

		if (_methodName726.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes726, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName727.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes727, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName728.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes728, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getTicketFeedbackTicketEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName729.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes729, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getUserVisibilities(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName730.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes730, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getValidTicketEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName731.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes731, parameterTypes)) {
			return TicketEntryLocalServiceUtil.getValidTicketEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName732.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes732, parameterTypes)) {
			return TicketEntryLocalServiceUtil.hasParticipant(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName733.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes733, parameterTypes)) {
			return TicketEntryLocalServiceUtil.hasParticipant(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.TicketEntry)arguments[1]);
		}

		if (_methodName734.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes734, parameterTypes)) {
			return TicketEntryLocalServiceUtil.hasVisibility(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName735.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes735, parameterTypes)) {
			TicketEntryLocalServiceUtil.reindexTicketEntry(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName736.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes736, parameterTypes)) {
			TicketEntryLocalServiceUtil.reindexTicketEntry((com.liferay.osb.model.TicketEntry)arguments[0]);

			return null;
		}

		if (_methodName737.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes737, parameterTypes)) {
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

		if (_methodName738.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes738, parameterTypes)) {
			return TicketEntryLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(com.liferay.portal.kernel.search.Sort[])arguments[7]);
		}

		if (_methodName739.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes739, parameterTypes)) {
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

		if (_methodName740.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes740, parameterTypes)) {
			return TicketEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName741.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes741, parameterTypes)) {
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

		if (_methodName742.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes742, parameterTypes)) {
			return TicketEntryLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName743.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes743, parameterTypes)) {
			TicketEntryLocalServiceUtil.sendEmail(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.osb.model.TicketComment)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName744.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes744, parameterTypes)) {
			TicketEntryLocalServiceUtil.sendEmail(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.TicketEntry)arguments[1],
				(com.liferay.osb.model.TicketComment)arguments[2],
				(java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName745.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes745, parameterTypes)) {
			TicketEntryLocalServiceUtil.syncToJIRA(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName746.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes746, parameterTypes)) {
			return TicketEntryLocalServiceUtil.updateCustomerModifiedDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Date)arguments[2]);
		}

		if (_methodName747.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes747, parameterTypes)) {
			return TicketEntryLocalServiceUtil.updatePendingTypes(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (int[])arguments[2]);
		}

		if (_methodName748.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes748, parameterTypes)) {
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

		if (_methodName749.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes749, parameterTypes)) {
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
				(com.liferay.portal.service.ServiceContext)arguments[26]);
		}

		if (_methodName750.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes750, parameterTypes)) {
			return TicketEntryLocalServiceUtil.updateTicketId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName751.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes751, parameterTypes)) {
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
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName708;
	private String[] _methodParameterTypes708;
	private String _methodName709;
	private String[] _methodParameterTypes709;
	private String _methodName714;
	private String[] _methodParameterTypes714;
	private String _methodName715;
	private String[] _methodParameterTypes715;
	private String _methodName716;
	private String[] _methodParameterTypes716;
	private String _methodName717;
	private String[] _methodParameterTypes717;
	private String _methodName718;
	private String[] _methodParameterTypes718;
	private String _methodName719;
	private String[] _methodParameterTypes719;
	private String _methodName720;
	private String[] _methodParameterTypes720;
	private String _methodName721;
	private String[] _methodParameterTypes721;
	private String _methodName722;
	private String[] _methodParameterTypes722;
	private String _methodName723;
	private String[] _methodParameterTypes723;
	private String _methodName724;
	private String[] _methodParameterTypes724;
	private String _methodName725;
	private String[] _methodParameterTypes725;
	private String _methodName726;
	private String[] _methodParameterTypes726;
	private String _methodName727;
	private String[] _methodParameterTypes727;
	private String _methodName728;
	private String[] _methodParameterTypes728;
	private String _methodName729;
	private String[] _methodParameterTypes729;
	private String _methodName730;
	private String[] _methodParameterTypes730;
	private String _methodName731;
	private String[] _methodParameterTypes731;
	private String _methodName732;
	private String[] _methodParameterTypes732;
	private String _methodName733;
	private String[] _methodParameterTypes733;
	private String _methodName734;
	private String[] _methodParameterTypes734;
	private String _methodName735;
	private String[] _methodParameterTypes735;
	private String _methodName736;
	private String[] _methodParameterTypes736;
	private String _methodName737;
	private String[] _methodParameterTypes737;
	private String _methodName738;
	private String[] _methodParameterTypes738;
	private String _methodName739;
	private String[] _methodParameterTypes739;
	private String _methodName740;
	private String[] _methodParameterTypes740;
	private String _methodName741;
	private String[] _methodParameterTypes741;
	private String _methodName742;
	private String[] _methodParameterTypes742;
	private String _methodName743;
	private String[] _methodParameterTypes743;
	private String _methodName744;
	private String[] _methodParameterTypes744;
	private String _methodName745;
	private String[] _methodParameterTypes745;
	private String _methodName746;
	private String[] _methodParameterTypes746;
	private String _methodName747;
	private String[] _methodParameterTypes747;
	private String _methodName748;
	private String[] _methodParameterTypes748;
	private String _methodName749;
	private String[] _methodParameterTypes749;
	private String _methodName750;
	private String[] _methodParameterTypes750;
	private String _methodName751;
	private String[] _methodParameterTypes751;
}