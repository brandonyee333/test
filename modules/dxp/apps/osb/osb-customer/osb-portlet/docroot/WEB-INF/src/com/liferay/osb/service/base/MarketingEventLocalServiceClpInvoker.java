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

import com.liferay.osb.service.MarketingEventLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class MarketingEventLocalServiceClpInvoker {
	public MarketingEventLocalServiceClpInvoker() {
		_methodName0 = "addMarketingEvent";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.MarketingEvent"
			};

		_methodName1 = "createMarketingEvent";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteMarketingEvent";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteMarketingEvent";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.MarketingEvent"
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

		_methodName9 = "fetchMarketingEvent";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getMarketingEvent";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getMarketingEvents";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getMarketingEventsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateMarketingEvent";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.MarketingEvent"
			};

		_methodName15 = "updateMarketingEvent";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.MarketingEvent", "boolean"
			};

		_methodName686 = "getBeanIdentifier";

		_methodParameterTypes686 = new String[] {  };

		_methodName687 = "setBeanIdentifier";

		_methodParameterTypes687 = new String[] { "java.lang.String" };

		_methodName692 = "addMarketingEvent";

		_methodParameterTypes692 = new String[] {
				"long", "int", "java.lang.String", "java.util.Map",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.util.Map", "long", "long", "java.lang.String",
				"java.lang.String", "int", "int", "int", "int", "int", "int",
				"int", "int", "int", "int", "boolean", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "int", "boolean", "int",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName693 = "deleteMarketingEvent";

		_methodParameterTypes693 = new String[] { "long" };

		_methodName694 = "deleteMarketingEvent";

		_methodParameterTypes694 = new String[] {
				"com.liferay.osb.model.MarketingEvent"
			};

		_methodName695 = "getMarketingEvents";

		_methodParameterTypes695 = new String[] {
				"int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName696 = "getMarketingEvents";

		_methodParameterTypes696 = new String[] {
				"int[][]", "int[][]", "long[][]", "int[][]", "boolean",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName697 = "search";

		_methodParameterTypes697 = new String[] {
				"long", "java.lang.String", "java.lang.String", "int[][]",
				"int[][]", "java.util.Date", "java.util.Date", "boolean", "int",
				"int", "com.liferay.portal.kernel.search.Sort"
			};

		_methodName698 = "updateMarketingEvent";

		_methodParameterTypes698 = new String[] {
				"long", "int", "java.lang.String", "java.util.Map",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.util.Map", "long", "long", "java.lang.String",
				"java.lang.String", "int", "int", "int", "int", "int", "int",
				"int", "int", "int", "int", "boolean", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "int", "boolean", "int",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return MarketingEventLocalServiceUtil.addMarketingEvent((com.liferay.osb.model.MarketingEvent)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return MarketingEventLocalServiceUtil.createMarketingEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return MarketingEventLocalServiceUtil.deleteMarketingEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return MarketingEventLocalServiceUtil.deleteMarketingEvent((com.liferay.osb.model.MarketingEvent)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return MarketingEventLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return MarketingEventLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return MarketingEventLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return MarketingEventLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return MarketingEventLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return MarketingEventLocalServiceUtil.fetchMarketingEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return MarketingEventLocalServiceUtil.getMarketingEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return MarketingEventLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return MarketingEventLocalServiceUtil.getMarketingEvents(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return MarketingEventLocalServiceUtil.getMarketingEventsCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return MarketingEventLocalServiceUtil.updateMarketingEvent((com.liferay.osb.model.MarketingEvent)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return MarketingEventLocalServiceUtil.updateMarketingEvent((com.liferay.osb.model.MarketingEvent)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return MarketingEventLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			MarketingEventLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return MarketingEventLocalServiceUtil.addMarketingEvent(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[7],
				((Long)arguments[8]).longValue(),
				((Long)arguments[9]).longValue(),
				(java.lang.String)arguments[10],
				(java.lang.String)arguments[11],
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				((Integer)arguments[14]).intValue(),
				((Integer)arguments[15]).intValue(),
				((Integer)arguments[16]).intValue(),
				((Integer)arguments[17]).intValue(),
				((Integer)arguments[18]).intValue(),
				((Integer)arguments[19]).intValue(),
				((Integer)arguments[20]).intValue(),
				((Integer)arguments[21]).intValue(),
				((Boolean)arguments[22]).booleanValue(),
				(java.lang.String)arguments[23],
				(java.lang.String)arguments[24],
				(java.lang.String)arguments[25],
				(java.lang.String)arguments[26],
				(java.lang.String)arguments[27],
				((Long)arguments[28]).longValue(),
				((Long)arguments[29]).longValue(),
				((Integer)arguments[30]).intValue(),
				((Boolean)arguments[31]).booleanValue(),
				((Integer)arguments[32]).intValue(),
				(java.lang.String)arguments[33],
				(com.liferay.portal.service.ServiceContext)arguments[34]);
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			return MarketingEventLocalServiceUtil.deleteMarketingEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return MarketingEventLocalServiceUtil.deleteMarketingEvent((com.liferay.osb.model.MarketingEvent)arguments[0]);
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			return MarketingEventLocalServiceUtil.getMarketingEvents(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return MarketingEventLocalServiceUtil.getMarketingEvents((int[])arguments[0],
				(int[])arguments[1], (long[])arguments[2], (int[])arguments[3],
				((Boolean)arguments[4]).booleanValue(),
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7]);
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			return MarketingEventLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(int[])arguments[3], (int[])arguments[4],
				(java.util.Date)arguments[5], (java.util.Date)arguments[6],
				((Boolean)arguments[7]).booleanValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(com.liferay.portal.kernel.search.Sort)arguments[10]);
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			return MarketingEventLocalServiceUtil.updateMarketingEvent(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[7],
				((Long)arguments[8]).longValue(),
				((Long)arguments[9]).longValue(),
				(java.lang.String)arguments[10],
				(java.lang.String)arguments[11],
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				((Integer)arguments[14]).intValue(),
				((Integer)arguments[15]).intValue(),
				((Integer)arguments[16]).intValue(),
				((Integer)arguments[17]).intValue(),
				((Integer)arguments[18]).intValue(),
				((Integer)arguments[19]).intValue(),
				((Integer)arguments[20]).intValue(),
				((Integer)arguments[21]).intValue(),
				((Boolean)arguments[22]).booleanValue(),
				(java.lang.String)arguments[23],
				(java.lang.String)arguments[24],
				(java.lang.String)arguments[25],
				(java.lang.String)arguments[26],
				(java.lang.String)arguments[27],
				((Long)arguments[28]).longValue(),
				((Long)arguments[29]).longValue(),
				((Integer)arguments[30]).intValue(),
				((Boolean)arguments[31]).booleanValue(),
				((Integer)arguments[32]).intValue(),
				(java.lang.String)arguments[33],
				(com.liferay.portal.service.ServiceContext)arguments[34]);
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
}