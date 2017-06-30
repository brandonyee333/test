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

import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AccountEnvironmentLocalServiceClpInvoker {
	public AccountEnvironmentLocalServiceClpInvoker() {
		_methodName0 = "addAccountEnvironment";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.AccountEnvironment"
			};

		_methodName1 = "createAccountEnvironment";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAccountEnvironment";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAccountEnvironment";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.AccountEnvironment"
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

		_methodName9 = "fetchAccountEnvironment";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getAccountEnvironment";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getAccountEnvironments";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getAccountEnvironmentsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateAccountEnvironment";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.AccountEnvironment"
			};

		_methodName15 = "updateAccountEnvironment";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.AccountEnvironment", "boolean"
			};

		_methodName684 = "getBeanIdentifier";

		_methodParameterTypes684 = new String[] {  };

		_methodName685 = "setBeanIdentifier";

		_methodParameterTypes685 = new String[] { "java.lang.String" };

		_methodName690 = "addAccountEnvironment";

		_methodParameterTypes690 = new String[] {
				"long", "long", "long", "java.lang.String", "int",
				"java.lang.String", "int", "int", "int", "int", "java.util.List",
				"java.util.List"
			};

		_methodName691 = "deleteAccountEnvironment";

		_methodParameterTypes691 = new String[] { "long" };

		_methodName692 = "fetchAccountEnvironment";

		_methodParameterTypes692 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName693 = "getAccountEnvironments";

		_methodParameterTypes693 = new String[] { "long" };

		_methodName694 = "getAccountEnvironments";

		_methodParameterTypes694 = new String[] { "long", "long" };

		_methodName695 = "getAccountEnvironmentsMap";

		_methodParameterTypes695 = new String[] { "long" };

		_methodName696 = "updateAccountEnvironment";

		_methodParameterTypes696 = new String[] {
				"long", "long", "long", "java.lang.String", "int",
				"java.lang.String", "int", "int", "int", "int", "java.util.List",
				"java.util.List"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.addAccountEnvironment((com.liferay.osb.model.AccountEnvironment)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.createAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.deleteAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.deleteAccountEnvironment((com.liferay.osb.model.AccountEnvironment)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.fetchAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.getAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.getAccountEnvironments(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.getAccountEnvironmentsCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.updateAccountEnvironment((com.liferay.osb.model.AccountEnvironment)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.updateAccountEnvironment((com.liferay.osb.model.AccountEnvironment)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName684.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes684, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName685.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes685, parameterTypes)) {
			AccountEnvironmentLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName690.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes690, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.addAccountEnvironment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				(java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[10],
				(java.util.List<java.lang.Integer>)arguments[11]);
		}

		if (_methodName691.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes691, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.deleteAccountEnvironment(((Long)arguments[0]).longValue());
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.fetchAccountEnvironment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.getAccountEnvironments(((Long)arguments[0]).longValue());
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.getAccountEnvironments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.getAccountEnvironmentsMap(((Long)arguments[0]).longValue());
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return AccountEnvironmentLocalServiceUtil.updateAccountEnvironment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				(java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[10],
				(java.util.List<java.lang.Integer>)arguments[11]);
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
	private String _methodName684;
	private String[] _methodParameterTypes684;
	private String _methodName685;
	private String[] _methodParameterTypes685;
	private String _methodName690;
	private String[] _methodParameterTypes690;
	private String _methodName691;
	private String[] _methodParameterTypes691;
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
}