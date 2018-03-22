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

import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class PartnerWorkerLocalServiceClpInvoker {
	public PartnerWorkerLocalServiceClpInvoker() {
		_methodName0 = "addPartnerWorker";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.PartnerWorker"
			};

		_methodName1 = "createPartnerWorker";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deletePartnerWorker";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deletePartnerWorker";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.PartnerWorker"
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

		_methodName10 = "fetchPartnerWorker";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPartnerWorker";

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

		_methodName17 = "getPartnerWorkers";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getPartnerWorkersCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updatePartnerWorker";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.PartnerWorker"
			};

		_methodName282 = "getOSGiServiceIdentifier";

		_methodParameterTypes282 = new String[] {  };

		_methodName287 = "addPartnerWorkers";

		_methodParameterTypes287 = new String[] {
				"long[][]", "long", "int[][]", "int[][]"
			};

		_methodName288 = "deletePartnerWorkers";

		_methodParameterTypes288 = new String[] { "long" };

		_methodName289 = "deletePartnerWorkers";

		_methodParameterTypes289 = new String[] { "long[][]", "long" };

		_methodName290 = "fetchPartnerWorker";

		_methodParameterTypes290 = new String[] { "long", "long" };

		_methodName291 = "getPartnerWorker";

		_methodParameterTypes291 = new String[] { "long", "long" };

		_methodName292 = "getPartnerWorkers";

		_methodParameterTypes292 = new String[] { "long" };

		_methodName293 = "getPartnerWorkers";

		_methodParameterTypes293 = new String[] { "long", "int" };

		_methodName294 = "getUserPartnerWorkers";

		_methodParameterTypes294 = new String[] { "long" };

		_methodName295 = "hasPartnerWorker";

		_methodParameterTypes295 = new String[] { "long" };

		_methodName296 = "hasPartnerWorker";

		_methodParameterTypes296 = new String[] { "long", "long" };

		_methodName297 = "hasPartnerWorkerRole";

		_methodParameterTypes297 = new String[] { "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.addPartnerWorker((com.liferay.osb.model.PartnerWorker)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.createPartnerWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.deletePartnerWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.deletePartnerWorker((com.liferay.osb.model.PartnerWorker)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.fetchPartnerWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.getPartnerWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.getPartnerWorkers(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.getPartnerWorkersCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.updatePartnerWorker((com.liferay.osb.model.PartnerWorker)arguments[0]);
		}

		if (_methodName282.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes282, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
			PartnerWorkerLocalServiceUtil.addPartnerWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue(), (int[])arguments[2],
				(int[])arguments[3]);

			return null;
		}

		if (_methodName288.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes288, parameterTypes)) {
			PartnerWorkerLocalServiceUtil.deletePartnerWorkers(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName289.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes289, parameterTypes)) {
			PartnerWorkerLocalServiceUtil.deletePartnerWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName290.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes290, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.fetchPartnerWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName291.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes291, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.getPartnerWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName292.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes292, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.getPartnerWorkers(((Long)arguments[0]).longValue());
		}

		if (_methodName293.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes293, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.getPartnerWorkers(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName294.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes294, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(((Long)arguments[0]).longValue());
		}

		if (_methodName295.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes295, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.hasPartnerWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName296.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes296, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.hasPartnerWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName297.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes297, parameterTypes)) {
			return PartnerWorkerLocalServiceUtil.hasPartnerWorkerRole(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
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