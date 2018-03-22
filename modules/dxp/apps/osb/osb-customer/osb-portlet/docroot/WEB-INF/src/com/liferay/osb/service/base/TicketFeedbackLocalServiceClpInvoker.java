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

import com.liferay.osb.service.TicketFeedbackLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
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

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchTicketFeedback";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getTicketFeedback";

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

		_methodName17 = "getTicketFeedbacks";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getTicketFeedbacksCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateTicketFeedback";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.TicketFeedback"
			};

		_methodName282 = "getOSGiServiceIdentifier";

		_methodParameterTypes282 = new String[] {  };

		_methodName287 = "addTicketFeedback";

		_methodParameterTypes287 = new String[] { "long", "long", "int", "int" };

		_methodName288 = "fetchFirstOpenTicketFeedback";

		_methodParameterTypes288 = new String[] { "long", "long", "int" };

		_methodName289 = "getTicketFeedbacks";

		_methodParameterTypes289 = new String[] { "long", "int" };

		_methodName290 = "search";

		_methodParameterTypes290 = new String[] {
				"java.lang.String", "int", "int", "int", "int", "int", "int",
				"int", "int", "int", "int", "int", "int", "java.lang.Integer",
				"java.lang.String", "java.lang.Integer", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.util.LinkedHashMap", "boolean",
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName291 = "search";

		_methodParameterTypes291 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName292 = "searchCount";

		_methodParameterTypes292 = new String[] {
				"java.lang.String", "int", "int", "int", "int", "int", "int",
				"int", "int", "int", "int", "int", "int", "java.lang.Integer",
				"java.lang.String", "java.lang.Integer", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.util.LinkedHashMap", "boolean"
			};

		_methodName293 = "searchCount";

		_methodParameterTypes293 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName294 = "sendCustomerNotifications";

		_methodParameterTypes294 = new String[] {  };

		_methodName295 = "sendLiferayWorkerNotifications";

		_methodParameterTypes295 = new String[] {  };

		_methodName296 = "sendSupportTeamNotifications";

		_methodParameterTypes296 = new String[] {  };

		_methodName297 = "updateTicketFeedback";

		_methodParameterTypes297 = new String[] {
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
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.fetchTicketFeedback(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getTicketFeedback(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getTicketFeedbacks(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getTicketFeedbacksCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.updateTicketFeedback((com.liferay.osb.model.TicketFeedback)arguments[0]);
		}

		if (_methodName282.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes282, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.addTicketFeedback(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName288.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes288, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.fetchFirstOpenTicketFeedback(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName289.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes289, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.getTicketFeedbacks(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName290.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes290, parameterTypes)) {
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

		if (_methodName291.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes291, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName292.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes292, parameterTypes)) {
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

		if (_methodName293.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes293, parameterTypes)) {
			return TicketFeedbackLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName294.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes294, parameterTypes)) {
			TicketFeedbackLocalServiceUtil.sendCustomerNotifications();

			return null;
		}

		if (_methodName295.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes295, parameterTypes)) {
			TicketFeedbackLocalServiceUtil.sendLiferayWorkerNotifications();

			return null;
		}

		if (_methodName296.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes296, parameterTypes)) {
			TicketFeedbackLocalServiceUtil.sendSupportTeamNotifications();

			return null;
		}

		if (_methodName297.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes297, parameterTypes)) {
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
	private String _methodName282;
	private String[] _methodParameterTypes282;
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
	private String _methodName296;
	private String[] _methodParameterTypes296;
	private String _methodName297;
	private String[] _methodParameterTypes297;
}