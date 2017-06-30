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

import com.liferay.osb.service.TicketFeedbackLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TicketFeedbackLocalServiceClpInvoker {
	public TicketFeedbackLocalServiceClpInvoker() {
		_methodName0 = "addTicketFeedback";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.TicketFeedback"
			};

		_methodName1 = "createTicketFeedback";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTicketFeedback";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTicketFeedback";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.TicketFeedback"
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

		_methodName9 = "fetchTicketFeedback";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getTicketFeedback";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getTicketFeedbacks";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getTicketFeedbacksCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateTicketFeedback";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.TicketFeedback"
			};

		_methodName15 = "updateTicketFeedback";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.TicketFeedback", "boolean"
			};

		_methodName694 = "getBeanIdentifier";

		_methodParameterTypes694 = new String[] {  };

		_methodName695 = "setBeanIdentifier";

		_methodParameterTypes695 = new String[] { "java.lang.String" };

		_methodName700 = "addTicketFeedback";

		_methodParameterTypes700 = new String[] { "long", "long", "int", "int" };

		_methodName701 = "fetchFirstOpenTicketFeedback";

		_methodParameterTypes701 = new String[] { "long", "long", "int" };

		_methodName702 = "getTicketFeedbacks";

		_methodParameterTypes702 = new String[] { "long", "int" };

		_methodName703 = "getTicketFeedbacks";

		_methodParameterTypes703 = new String[] { "long", "int", "int" };

		_methodName704 = "search";

		_methodParameterTypes704 = new String[] {
				"java.lang.String", "int", "int", "int", "int", "int", "int",
				"int", "int", "int", "int", "int", "int", "java.lang.Integer",
				"java.lang.String", "java.lang.Integer", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.util.LinkedHashMap", "boolean",
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName705 = "search";

		_methodParameterTypes705 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName706 = "searchCount";

		_methodParameterTypes706 = new String[] {
				"java.lang.String", "int", "int", "int", "int", "int", "int",
				"int", "int", "int", "int", "int", "int", "java.lang.Integer",
				"java.lang.String", "java.lang.Integer", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.util.LinkedHashMap", "boolean"
			};

		_methodName707 = "searchCount";

		_methodParameterTypes707 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName708 = "sendCustomerNotifications";

		_methodParameterTypes708 = new String[] {  };

		_methodName709 = "sendLiferayWorkerNotifications";

		_methodParameterTypes709 = new String[] {  };

		_methodName710 = "sendSupportTeamNotifications";

		_methodParameterTypes710 = new String[] {  };

		_methodName711 = "updateTicketFeedback";

		_methodParameterTypes711 = new String[] {
				"long", "long", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.addTicketFeedback((com.liferay.osb.model.TicketFeedback)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.createTicketFeedback(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.deleteTicketFeedback(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.deleteTicketFeedback((com.liferay.osb.model.TicketFeedback)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.fetchTicketFeedback(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getTicketFeedback(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getTicketFeedbacks(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getTicketFeedbacksCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.updateTicketFeedback((com.liferay.osb.model.TicketFeedback)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.updateTicketFeedback((com.liferay.osb.model.TicketFeedback)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			TicketFeedbackLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.addTicketFeedback(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.fetchFirstOpenTicketFeedback(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getTicketFeedbacks(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getTicketFeedbacks(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				(java.lang.Integer)arguments[13],
				(java.lang.String)arguments[14],
				(java.lang.Integer)arguments[15],
				(java.lang.Integer[])arguments[16],
				(java.lang.Integer[])arguments[17],
				(java.lang.Integer[])arguments[18],
				(java.lang.Integer[])arguments[19],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[20],
				((Boolean)arguments[21]).booleanValue(),
				((Integer)arguments[22]).intValue(),
				((Integer)arguments[23]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[24]);
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName706.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes706, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				(java.lang.Integer)arguments[13],
				(java.lang.String)arguments[14],
				(java.lang.Integer)arguments[15],
				(java.lang.Integer[])arguments[16],
				(java.lang.Integer[])arguments[17],
				(java.lang.Integer[])arguments[18],
				(java.lang.Integer[])arguments[19],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[20],
				((Boolean)arguments[21]).booleanValue());
		}

		if (_methodName707.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes707, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			TicketFeedbackLocalServiceUtil.sendCustomerNotifications();

			return null;
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			TicketFeedbackLocalServiceUtil.sendLiferayWorkerNotifications();

			return null;
		}

		if (_methodName710.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes710, parameterTypes)) {
			TicketFeedbackLocalServiceUtil.sendSupportTeamNotifications();

			return null;
		}

		if (_methodName711.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes711, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.updateTicketFeedback(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(java.lang.String)arguments[10]);
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
	private String _methodName694;
	private String[] _methodParameterTypes694;
	private String _methodName695;
	private String[] _methodParameterTypes695;
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
}