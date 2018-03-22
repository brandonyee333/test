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

import com.liferay.osb.service.SupportWorkerLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class SupportWorkerLocalServiceClpInvoker {
	public SupportWorkerLocalServiceClpInvoker() {
		_methodName0 = "addSupportWorker";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.SupportWorker"
			};

		_methodName1 = "createSupportWorker";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSupportWorker";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSupportWorker";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.SupportWorker"
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

		_methodName10 = "fetchSupportWorker";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getSupportWorker";

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

		_methodName17 = "getSupportWorkers";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getSupportWorkersCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateSupportWorker";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.SupportWorker"
			};

		_methodName274 = "getOSGiServiceIdentifier";

		_methodParameterTypes274 = new String[] {  };

		_methodName279 = "addSupportWorkers";

		_methodParameterTypes279 = new String[] {
				"long[][]", "long", "double[][]", "int[][]", "int[][]",
				"int[][]"
			};

		_methodName280 = "clockInOut";

		_methodParameterTypes280 = new String[] { "long" };

		_methodName281 = "decreaseAssignedWork";

		_methodParameterTypes281 = new String[] { "long", "double" };

		_methodName282 = "decreaseTicketEntryAssignedWork";

		_methodParameterTypes282 = new String[] { "long", "double" };

		_methodName283 = "deleteSupportWorkers";

		_methodParameterTypes283 = new String[] { "long" };

		_methodName284 = "deleteSupportWorkers";

		_methodParameterTypes284 = new String[] { "long[][]", "long" };

		_methodName285 = "getAssignedWork";

		_methodParameterTypes285 = new String[] { "long" };

		_methodName286 = "getAvailableSupportWorker";

		_methodParameterTypes286 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName287 = "getLongestOpenSupportWorker";

		_methodParameterTypes287 = new String[] {
				"java.util.List", "com.liferay.osb.model.TicketEntry"
			};

		_methodName288 = "getMostAvailableSupportWorker";

		_methodParameterTypes288 = new String[] {
				"com.liferay.osb.model.TicketEntry", "java.util.LinkedHashMap"
			};

		_methodName289 = "getNextOpenSupportWorker";

		_methodParameterTypes289 = new String[] {
				"java.util.List", "com.liferay.osb.model.TicketEntry"
			};

		_methodName290 = "getSupportWorker";

		_methodParameterTypes290 = new String[] { "long", "long" };

		_methodName291 = "getSupportWorkersBySupportLaborId";

		_methodParameterTypes291 = new String[] { "long" };

		_methodName292 = "getSupportWorkersBySupportRegionId";

		_methodParameterTypes292 = new String[] { "long" };

		_methodName293 = "getSupportWorkersCountBySupportLaborId";

		_methodParameterTypes293 = new String[] { "long" };

		_methodName294 = "getTeamSupportWorkers";

		_methodParameterTypes294 = new String[] { "long" };

		_methodName295 = "getUserSupportTeamManagers";

		_methodParameterTypes295 = new String[] { "long", "java.lang.Integer" };

		_methodName296 = "getUserSupportWorkers";

		_methodParameterTypes296 = new String[] { "long" };

		_methodName297 = "hasSupportWorker";

		_methodParameterTypes297 = new String[] { "long", "int" };

		_methodName298 = "hasSupportWorker";

		_methodParameterTypes298 = new String[] {
				"long", "int", "long", "java.lang.Integer"
			};

		_methodName299 = "hasSupportWorker";

		_methodParameterTypes299 = new String[] { "long", "long" };

		_methodName300 = "hasSupportWorkerRole";

		_methodParameterTypes300 = new String[] { "long", "int" };

		_methodName301 = "increaseAssignedWork";

		_methodParameterTypes301 = new String[] { "long", "double" };

		_methodName302 = "increaseTicketEntryAssignedWork";

		_methodParameterTypes302 = new String[] { "long", "double" };

		_methodName303 = "isClockedIn";

		_methodParameterTypes303 = new String[] { "long" };

		_methodName304 = "isManagerOfWorker";

		_methodParameterTypes304 = new String[] { "long", "long" };

		_methodName305 = "recalculateUtilization";

		_methodParameterTypes305 = new String[] {  };

		_methodName306 = "search";

		_methodParameterTypes306 = new String[] {
				"java.lang.Boolean", "int", "java.util.LinkedHashMap"
			};

		_methodName307 = "search";

		_methodParameterTypes307 = new String[] {
				"long", "java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName308 = "search";

		_methodParameterTypes308 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName309 = "searchCount";

		_methodParameterTypes309 = new String[] { "long", "java.lang.String" };

		_methodName310 = "searchCount";

		_methodParameterTypes310 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName311 = "updateSupportWorker";

		_methodParameterTypes311 = new String[] {
				"long", "long", "boolean", "double", "int", "int", "int"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.addSupportWorker((com.liferay.osb.model.SupportWorker)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.createSupportWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.deleteSupportWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.deleteSupportWorker((com.liferay.osb.model.SupportWorker)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.fetchSupportWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorkers(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorkersCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.updateSupportWorker((com.liferay.osb.model.SupportWorker)arguments[0]);
		}

		if (_methodName274.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes274, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName279.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes279, parameterTypes)) {
			SupportWorkerLocalServiceUtil.addSupportWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue(), (double[])arguments[2],
				(int[])arguments[3], (int[])arguments[4], (int[])arguments[5]);

			return null;
		}

		if (_methodName280.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes280, parameterTypes)) {
			SupportWorkerLocalServiceUtil.clockInOut(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName281.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes281, parameterTypes)) {
			SupportWorkerLocalServiceUtil.decreaseAssignedWork(((Long)arguments[0]).longValue(),
				((Double)arguments[1]).doubleValue());

			return null;
		}

		if (_methodName282.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes282, parameterTypes)) {
			SupportWorkerLocalServiceUtil.decreaseTicketEntryAssignedWork(((Long)arguments[0]).longValue(),
				((Double)arguments[1]).doubleValue());

			return null;
		}

		if (_methodName283.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes283, parameterTypes)) {
			SupportWorkerLocalServiceUtil.deleteSupportWorkers(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName284.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes284, parameterTypes)) {
			SupportWorkerLocalServiceUtil.deleteSupportWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName285.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes285, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getAssignedWork(((Long)arguments[0]).longValue());
		}

		if (_methodName286.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes286, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getAvailableSupportWorker((com.liferay.osb.model.TicketEntry)arguments[0]);
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getLongestOpenSupportWorker((java.util.List<com.liferay.osb.model.SupportWorker>)arguments[0],
				(com.liferay.osb.model.TicketEntry)arguments[1]);
		}

		if (_methodName288.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes288, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getMostAvailableSupportWorker((com.liferay.osb.model.TicketEntry)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName289.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes289, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getNextOpenSupportWorker((java.util.List<com.liferay.osb.model.SupportWorker>)arguments[0],
				(com.liferay.osb.model.TicketEntry)arguments[1]);
		}

		if (_methodName290.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes290, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName291.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes291, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorkersBySupportLaborId(((Long)arguments[0]).longValue());
		}

		if (_methodName292.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes292, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorkersBySupportRegionId(((Long)arguments[0]).longValue());
		}

		if (_methodName293.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes293, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorkersCountBySupportLaborId(((Long)arguments[0]).longValue());
		}

		if (_methodName294.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes294, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getTeamSupportWorkers(((Long)arguments[0]).longValue());
		}

		if (_methodName295.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes295, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getUserSupportTeamManagers(((Long)arguments[0]).longValue(),
				(java.lang.Integer)arguments[1]);
		}

		if (_methodName296.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes296, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getUserSupportWorkers(((Long)arguments[0]).longValue());
		}

		if (_methodName297.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes297, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.hasSupportWorker(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName298.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes298, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.hasSupportWorker(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.Integer)arguments[3]);
		}

		if (_methodName299.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes299, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.hasSupportWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName300.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes300, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.hasSupportWorkerRole(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName301.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes301, parameterTypes)) {
			SupportWorkerLocalServiceUtil.increaseAssignedWork(((Long)arguments[0]).longValue(),
				((Double)arguments[1]).doubleValue());

			return null;
		}

		if (_methodName302.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes302, parameterTypes)) {
			SupportWorkerLocalServiceUtil.increaseTicketEntryAssignedWork(((Long)arguments[0]).longValue(),
				((Double)arguments[1]).doubleValue());

			return null;
		}

		if (_methodName303.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes303, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.isClockedIn(((Long)arguments[0]).longValue());
		}

		if (_methodName304.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes304, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.isManagerOfWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName305.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes305, parameterTypes)) {
			SupportWorkerLocalServiceUtil.recalculateUtilization();

			return null;
		}

		if (_methodName306.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes306, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.search((java.lang.Boolean)arguments[0],
				((Integer)arguments[1]).intValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[2]);
		}

		if (_methodName307.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes307, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName308.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes308, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				((Boolean)arguments[7]).booleanValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[10]);
		}

		if (_methodName309.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes309, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName310.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes310, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName311.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes311, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.updateSupportWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Double)arguments[3]).doubleValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue());
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
	private String _methodName298;
	private String[] _methodParameterTypes298;
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
}