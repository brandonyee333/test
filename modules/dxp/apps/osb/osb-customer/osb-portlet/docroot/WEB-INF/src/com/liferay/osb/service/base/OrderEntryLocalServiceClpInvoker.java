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

import com.liferay.osb.service.OrderEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
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

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchOrderEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getOrderEntry";

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

		_methodName17 = "getOrderEntries";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getOrderEntriesCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateOrderEntry";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.OrderEntry"
			};

		_methodName278 = "getOSGiServiceIdentifier";

		_methodParameterTypes278 = new String[] {  };

		_methodName283 = "addOrderEntriesWithWorkflow";

		_methodParameterTypes283 = new String[] {
				"java.lang.String", "com.liferay.osb.model.AccountEntry",
				"com.liferay.osb.model.CorpProject",
				"com.liferay.osb.model.PartnerEntry",
				"com.liferay.portal.kernel.model.Address",
				"com.liferay.osb.model.AccountWorker", "java.util.List",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName284 = "addOrderEntry";

		_methodParameterTypes284 = new String[] {
				"long", "long", "java.lang.String", "int", "int", "int",
				"boolean", "int", "int", "int", "int", "java.lang.String",
				"java.util.List"
			};

		_methodName285 = "deleteOrderEntry";

		_methodParameterTypes285 = new String[] { "long" };

		_methodName286 = "deleteOrderEntry";

		_methodParameterTypes286 = new String[] {
				"com.liferay.osb.model.OrderEntry"
			};

		_methodName287 = "getAccountEntryOrderEntries";

		_methodParameterTypes287 = new String[] { "long" };

		_methodName288 = "getOrderEntry";

		_methodParameterTypes288 = new String[] { "java.lang.String" };

		_methodName289 = "renewOrderEntry";

		_methodParameterTypes289 = new String[] { "long", "long", "int" };

		_methodName290 = "search";

		_methodParameterTypes290 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "java.lang.String", "int[][]", "int", "int",
				"int", "int", "int", "int", "java.lang.Boolean", "int", "int",
				"int", "int", "int", "int", "java.util.LinkedHashMap", "boolean",
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName291 = "search";

		_methodParameterTypes291 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName292 = "searchCount";

		_methodParameterTypes292 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "java.lang.String", "int[][]", "int", "int",
				"int", "int", "int", "int", "java.lang.Boolean", "int", "int",
				"int", "int", "int", "int", "java.util.LinkedHashMap", "boolean"
			};

		_methodName293 = "searchCount";

		_methodParameterTypes293 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName294 = "updateOrderEntry";

		_methodParameterTypes294 = new String[] {
				"long", "long", "long", "java.lang.String", "int", "int", "int",
				"boolean", "int", "int", "int", "java.lang.String",
				"java.util.List"
			};

		_methodName295 = "updateStatus";

		_methodParameterTypes295 = new String[] {
				"long", "long", "int",
				"com.liferay.portal.kernel.service.ServiceContext"
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
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return OrderEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return OrderEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return OrderEntryLocalServiceUtil.fetchOrderEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getOrderEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return OrderEntryLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getOrderEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getOrderEntriesCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return OrderEntryLocalServiceUtil.updateOrderEntry((com.liferay.osb.model.OrderEntry)arguments[0]);
		}

		if (_methodName278.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes278, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName283.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes283, parameterTypes)) {
			return OrderEntryLocalServiceUtil.addOrderEntriesWithWorkflow((java.lang.String)arguments[0],
				(com.liferay.osb.model.AccountEntry)arguments[1],
				(com.liferay.osb.model.CorpProject)arguments[2],
				(com.liferay.osb.model.PartnerEntry)arguments[3],
				(com.liferay.portal.kernel.model.Address)arguments[4],
				(com.liferay.osb.model.AccountWorker)arguments[5],
				(java.util.List<com.liferay.osb.model.OrderEntry>)arguments[6],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[7]);
		}

		if (_methodName284.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes284, parameterTypes)) {
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

		if (_methodName285.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes285, parameterTypes)) {
			return OrderEntryLocalServiceUtil.deleteOrderEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName286.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes286, parameterTypes)) {
			return OrderEntryLocalServiceUtil.deleteOrderEntry((com.liferay.osb.model.OrderEntry)arguments[0]);
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getAccountEntryOrderEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName288.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes288, parameterTypes)) {
			return OrderEntryLocalServiceUtil.getOrderEntry((java.lang.String)arguments[0]);
		}

		if (_methodName289.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes289, parameterTypes)) {
			return OrderEntryLocalServiceUtil.renewOrderEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName290.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes290, parameterTypes)) {
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

		if (_methodName291.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes291, parameterTypes)) {
			return OrderEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName292.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes292, parameterTypes)) {
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

		if (_methodName293.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes293, parameterTypes)) {
			return OrderEntryLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName294.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes294, parameterTypes)) {
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

		if (_methodName295.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes295, parameterTypes)) {
			return OrderEntryLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[3]);
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
	private String _methodName278;
	private String[] _methodParameterTypes278;
	private String _methodName283;
	private String[] _methodParameterTypes283;
	private String _methodName284;
	private String[] _methodParameterTypes284;
	private String _methodName285;
	private String[] _methodParameterTypes285;
	private String _methodName286;
	private String[] _methodParameterTypes286;
	private String _methodName287;
	private String[] _methodParameterTypes287;
	private String _methodName288;
	private String[] _methodParameterTypes288;
	private String _methodName289;
	private String[] _methodParameterTypes289;
	private String _methodName290;
	private String[] _methodParameterTypes290;
	private String _methodName291;
	private String[] _methodParameterTypes291;
	private String _methodName292;
	private String[] _methodParameterTypes292;
	private String _methodName293;
	private String[] _methodParameterTypes293;
	private String _methodName294;
	private String[] _methodParameterTypes294;
	private String _methodName295;
	private String[] _methodParameterTypes295;
}