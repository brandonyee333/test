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

import com.liferay.osb.service.OrderEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class OrderEntryLocalServiceClpInvoker {
	public OrderEntryLocalServiceClpInvoker() {
		_methodName0 = "addOrderEntry";

		_methodParameterTypes0 = new String[] { "com.liferay.osb.model.OrderEntry" };

		_methodName1 = "createOrderEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteOrderEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteOrderEntry";

		_methodParameterTypes3 = new String[] { "com.liferay.osb.model.OrderEntry" };

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

		_methodName9 = "fetchOrderEntry";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getOrderEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getOrderEntries";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getOrderEntriesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateOrderEntry";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.OrderEntry"
			};

		_methodName15 = "updateOrderEntry";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.OrderEntry", "boolean"
			};

		_methodName686 = "getBeanIdentifier";

		_methodParameterTypes686 = new String[] {  };

		_methodName687 = "setBeanIdentifier";

		_methodParameterTypes687 = new String[] { "java.lang.String" };

		_methodName692 = "addOrderEntriesWithWorkflow";

		_methodParameterTypes692 = new String[] {
				"java.lang.String", "com.liferay.osb.model.AccountEntry",
				"com.liferay.osb.model.CorpProject",
				"com.liferay.osb.model.PartnerEntry",
				"com.liferay.portal.model.Address",
				"com.liferay.osb.model.AccountWorker", "java.util.List",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName693 = "addOrderEntry";

		_methodParameterTypes693 = new String[] {
				"long", "long", "java.lang.String", "int", "int", "int",
				"boolean", "int", "int", "int", "int", "java.lang.String",
				"java.util.List"
			};

		_methodName694 = "deleteOrderEntry";

		_methodParameterTypes694 = new String[] { "long" };

		_methodName695 = "deleteOrderEntry";

		_methodParameterTypes695 = new String[] {
				"com.liferay.osb.model.OrderEntry"
			};

		_methodName696 = "getAccountEntryOrderEntries";

		_methodParameterTypes696 = new String[] { "long" };

		_methodName697 = "getOrderEntry";

		_methodParameterTypes697 = new String[] { "java.lang.String" };

		_methodName698 = "renewOrderEntry";

		_methodParameterTypes698 = new String[] { "long", "long", "int" };

		_methodName699 = "search";

		_methodParameterTypes699 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "java.lang.String", "int[][]", "int", "int",
				"int", "int", "int", "int", "java.lang.Boolean", "int", "int",
				"int", "int", "int", "int", "java.util.LinkedHashMap", "boolean",
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName700 = "search";

		_methodParameterTypes700 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName701 = "searchCount";

		_methodParameterTypes701 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "java.lang.String", "int[][]", "int", "int",
				"int", "int", "int", "int", "java.lang.Boolean", "int", "int",
				"int", "int", "int", "int", "java.util.LinkedHashMap", "boolean"
			};

		_methodName702 = "searchCount";

		_methodParameterTypes702 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName703 = "updateOrderEntry";

		_methodParameterTypes703 = new String[] {
				"long", "long", "long", "java.lang.String", "int", "int", "int",
				"boolean", "int", "int", "int", "java.lang.String",
				"java.util.List"
			};

		_methodName704 = "updateStatus";

		_methodParameterTypes704 = new String[] {
				"long", "long", "int",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return OrderEntryLocalServiceUtil.addOrderEntry((com.liferay.osb.model.OrderEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return OrderEntryLocalServiceUtil.createOrderEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return OrderEntryLocalServiceUtil.deleteOrderEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return OrderEntryLocalServiceUtil.deleteOrderEntry((com.liferay.osb.model.OrderEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return OrderEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return OrderEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return OrderEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return OrderEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return OrderEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return OrderEntryLocalServiceUtil.fetchOrderEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getOrderEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getOrderEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getOrderEntriesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return OrderEntryLocalServiceUtil.updateOrderEntry((com.liferay.osb.model.OrderEntry)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return OrderEntryLocalServiceUtil.updateOrderEntry((com.liferay.osb.model.OrderEntry)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			OrderEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return OrderEntryLocalServiceUtil.addOrderEntriesWithWorkflow((java.lang.String)arguments[0],
				(com.liferay.osb.model.AccountEntry)arguments[1],
				(com.liferay.osb.model.CorpProject)arguments[2],
				(com.liferay.osb.model.PartnerEntry)arguments[3],
				(com.liferay.portal.model.Address)arguments[4],
				(com.liferay.osb.model.AccountWorker)arguments[5],
				(java.util.List<com.liferay.osb.model.OrderEntry>)arguments[6],
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			return OrderEntryLocalServiceUtil.addOrderEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Boolean)arguments[6]).booleanValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				(java.lang.String)arguments[11],
				(java.util.List<com.liferay.osb.model.OfferingEntry>)arguments[12]);
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return OrderEntryLocalServiceUtil.deleteOrderEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			return OrderEntryLocalServiceUtil.deleteOrderEntry((com.liferay.osb.model.OrderEntry)arguments[0]);
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getAccountEntryOrderEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getOrderEntry((java.lang.String)arguments[0]);
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			return OrderEntryLocalServiceUtil.renewOrderEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			return OrderEntryLocalServiceUtil.search((java.lang.Long)arguments[0],
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
				(java.lang.Long)arguments[14], (java.lang.String)arguments[15],
				(int[])arguments[16], ((Integer)arguments[17]).intValue(),
				((Integer)arguments[18]).intValue(),
				((Integer)arguments[19]).intValue(),
				((Integer)arguments[20]).intValue(),
				((Integer)arguments[21]).intValue(),
				((Integer)arguments[22]).intValue(),
				(java.lang.Boolean)arguments[23],
				((Integer)arguments[24]).intValue(),
				((Integer)arguments[25]).intValue(),
				((Integer)arguments[26]).intValue(),
				((Integer)arguments[27]).intValue(),
				((Integer)arguments[28]).intValue(),
				((Integer)arguments[29]).intValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[30],
				((Boolean)arguments[31]).booleanValue(),
				((Integer)arguments[32]).intValue(),
				((Integer)arguments[33]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[34]);
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			return OrderEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return OrderEntryLocalServiceUtil.searchCount((java.lang.Long)arguments[0],
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
				(java.lang.Long)arguments[14], (java.lang.String)arguments[15],
				(int[])arguments[16], ((Integer)arguments[17]).intValue(),
				((Integer)arguments[18]).intValue(),
				((Integer)arguments[19]).intValue(),
				((Integer)arguments[20]).intValue(),
				((Integer)arguments[21]).intValue(),
				((Integer)arguments[22]).intValue(),
				(java.lang.Boolean)arguments[23],
				((Integer)arguments[24]).intValue(),
				((Integer)arguments[25]).intValue(),
				((Integer)arguments[26]).intValue(),
				((Integer)arguments[27]).intValue(),
				((Integer)arguments[28]).intValue(),
				((Integer)arguments[29]).intValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[30],
				((Boolean)arguments[31]).booleanValue());
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return OrderEntryLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return OrderEntryLocalServiceUtil.updateOrderEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Boolean)arguments[7]).booleanValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				(java.lang.String)arguments[11],
				(java.util.List<com.liferay.osb.model.OfferingEntry>)arguments[12]);
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return OrderEntryLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
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
	private String _methodName686;
	private String[] _methodParameterTypes686;
	private String _methodName687;
	private String[] _methodParameterTypes687;
	private String _methodName692;
	private String[] _methodParameterTypes692;
	private String _methodName693;
	private String[] _methodParameterTypes693;
	private String _methodName694;
	private String[] _methodParameterTypes694;
	private String _methodName695;
	private String[] _methodParameterTypes695;
	private String _methodName696;
	private String[] _methodParameterTypes696;
	private String _methodName697;
	private String[] _methodParameterTypes697;
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
}