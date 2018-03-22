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

import com.liferay.osb.service.TicketWorkerLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketWorkerLocalServiceClpInvoker {
	public TicketWorkerLocalServiceClpInvoker() {
		_methodName0 = "addTicketWorker";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.TicketWorker"
			};

		_methodName1 = "createTicketWorker";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTicketWorker";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTicketWorker";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.TicketWorker"
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

		_methodName10 = "fetchTicketWorker";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getTicketWorker";

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

		_methodName17 = "getTicketWorkers";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getTicketWorkersCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateTicketWorker";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.TicketWorker"
			};

		_methodName274 = "getOSGiServiceIdentifier";

		_methodParameterTypes274 = new String[] {  };

		_methodName279 = "addTicketWorkers";

		_methodParameterTypes279 = new String[] {
				"long", "long[][]", "long", "long[][]", "long[][]", "int[][]",
				"long"
			};

		_methodName280 = "deleteTicketWorkers";

		_methodParameterTypes280 = new String[] { "long" };

		_methodName281 = "deleteTicketWorkers";

		_methodParameterTypes281 = new String[] {
				"long", "long[][]", "long", "long"
			};

		_methodName282 = "fetchLatestTicketWorker";

		_methodParameterTypes282 = new String[] { "long" };

		_methodName283 = "fetchPrimaryTicketWorker";

		_methodParameterTypes283 = new String[] { "long" };

		_methodName284 = "fetchTicketWorker";

		_methodParameterTypes284 = new String[] { "long", "long" };

		_methodName285 = "getTicketWorker";

		_methodParameterTypes285 = new String[] { "long", "long" };

		_methodName286 = "getTicketWorkers";

		_methodParameterTypes286 = new String[] { "long" };

		_methodName287 = "getTicketWorkers";

		_methodParameterTypes287 = new String[] { "long", "long" };

		_methodName288 = "getUserTicketWorkersCount";

		_methodParameterTypes288 = new String[] { "long" };

		_methodName289 = "hasTicketWorker";

		_methodParameterTypes289 = new String[] { "long", "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.addTicketWorker((com.liferay.osb.model.TicketWorker)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.createTicketWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.deleteTicketWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.deleteTicketWorker((com.liferay.osb.model.TicketWorker)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.fetchTicketWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.getTicketWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.getTicketWorkers(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.getTicketWorkersCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.updateTicketWorker((com.liferay.osb.model.TicketWorker)arguments[0]);
		}

		if (_methodName274.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes274, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName279.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes279, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.addTicketWorkers(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue(),
				(long[])arguments[3], (long[])arguments[4],
				(int[])arguments[5], ((Long)arguments[6]).longValue());
		}

		if (_methodName280.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes280, parameterTypes)) {
			TicketWorkerLocalServiceUtil.deleteTicketWorkers(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName281.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes281, parameterTypes)) {
			TicketWorkerLocalServiceUtil.deleteTicketWorkers(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue());

			return null;
		}

		if (_methodName282.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes282, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.fetchLatestTicketWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName283.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes283, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.fetchPrimaryTicketWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName284.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes284, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.fetchTicketWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName285.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes285, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.getTicketWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName286.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes286, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.getTicketWorkers(((Long)arguments[0]).longValue());
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.getTicketWorkers(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName288.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes288, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.getUserTicketWorkersCount(((Long)arguments[0]).longValue());
		}

		if (_methodName289.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes289, parameterTypes)) {
			return TicketWorkerLocalServiceUtil.hasTicketWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
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
	private String _methodName274;
	private String[] _methodParameterTypes274;
	private String _methodName279;
	private String[] _methodParameterTypes279;
	private String _methodName280;
	private String[] _methodParameterTypes280;
	private String _methodName281;
	private String[] _methodParameterTypes281;
	private String _methodName282;
	private String[] _methodParameterTypes282;
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
}