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

import com.liferay.osb.service.AccountWorkerLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountWorkerLocalServiceClpInvoker {
	public AccountWorkerLocalServiceClpInvoker() {
		_methodName0 = "addAccountWorker";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.AccountWorker"
			};

		_methodName1 = "createAccountWorker";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAccountWorker";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAccountWorker";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.AccountWorker"
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

		_methodName10 = "fetchAccountWorker";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getAccountWorker";

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

		_methodName17 = "getAccountWorkers";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getAccountWorkersCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateAccountWorker";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.AccountWorker"
			};

		_methodName274 = "getOSGiServiceIdentifier";

		_methodParameterTypes274 = new String[] {  };

		_methodName279 = "addAccountWorkers";

		_methodParameterTypes279 = new String[] {
				"long", "long[][]", "long", "int[][]", "int[][]"
			};

		_methodName280 = "deleteAccountEntryAccountWorkers";

		_methodParameterTypes280 = new String[] { "long" };

		_methodName281 = "deleteAccountWorkers";

		_methodParameterTypes281 = new String[] { "long" };

		_methodName282 = "deleteAccountWorkers";

		_methodParameterTypes282 = new String[] { "long", "long[][]", "long" };

		_methodName283 = "getAccountWorker";

		_methodParameterTypes283 = new String[] { "long", "long" };

		_methodName284 = "getAccountWorkers";

		_methodParameterTypes284 = new String[] { "long" };

		_methodName285 = "getAccountWorkers";

		_methodParameterTypes285 = new String[] { "long", "int" };

		_methodName286 = "getUserAccountWorkers";

		_methodParameterTypes286 = new String[] { "long" };

		_methodName287 = "hasAccountWorker";

		_methodParameterTypes287 = new String[] { "long", "long" };

		_methodName288 = "hasAccountWorkerRole";

		_methodParameterTypes288 = new String[] { "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.addAccountWorker((com.liferay.osb.model.AccountWorker)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.createAccountWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.deleteAccountWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.deleteAccountWorker((com.liferay.osb.model.AccountWorker)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.fetchAccountWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.getAccountWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.getAccountWorkers(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.getAccountWorkersCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.updateAccountWorker((com.liferay.osb.model.AccountWorker)arguments[0]);
		}

		if (_methodName274.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes274, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName279.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes279, parameterTypes)) {
			AccountWorkerLocalServiceUtil.addAccountWorkers(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue(),
				(int[])arguments[3], (int[])arguments[4]);

			return null;
		}

		if (_methodName280.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes280, parameterTypes)) {
			AccountWorkerLocalServiceUtil.deleteAccountEntryAccountWorkers(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName281.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes281, parameterTypes)) {
			AccountWorkerLocalServiceUtil.deleteAccountWorkers(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName282.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes282, parameterTypes)) {
			AccountWorkerLocalServiceUtil.deleteAccountWorkers(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName283.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes283, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.getAccountWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName284.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes284, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.getAccountWorkers(((Long)arguments[0]).longValue());
		}

		if (_methodName285.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes285, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.getAccountWorkers(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName286.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes286, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.getUserAccountWorkers(((Long)arguments[0]).longValue());
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.hasAccountWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName288.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes288, parameterTypes)) {
			return AccountWorkerLocalServiceUtil.hasAccountWorkerRole(((Long)arguments[0]).longValue(),
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
}