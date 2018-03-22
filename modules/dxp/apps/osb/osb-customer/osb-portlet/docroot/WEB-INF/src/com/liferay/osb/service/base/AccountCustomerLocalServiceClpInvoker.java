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

import com.liferay.osb.service.AccountCustomerLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountCustomerLocalServiceClpInvoker {
	public AccountCustomerLocalServiceClpInvoker() {
		_methodName0 = "addAccountCustomer";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.AccountCustomer"
			};

		_methodName1 = "createAccountCustomer";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAccountCustomer";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAccountCustomer";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.AccountCustomer"
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

		_methodName10 = "fetchAccountCustomer";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getAccountCustomer";

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

		_methodName17 = "getAccountCustomers";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getAccountCustomersCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateAccountCustomer";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.AccountCustomer"
			};

		_methodName282 = "getOSGiServiceIdentifier";

		_methodParameterTypes282 = new String[] {  };

		_methodName287 = "addAccountCustomer";

		_methodParameterTypes287 = new String[] {
				"long", "long", "long", "int", "int"
			};

		_methodName288 = "addAccountCustomer";

		_methodParameterTypes288 = new String[] {
				"long", "java.lang.String", "long", "int", "int"
			};

		_methodName289 = "deleteAccountCustomer";

		_methodParameterTypes289 = new String[] {
				"long", "com.liferay.osb.model.AccountCustomer"
			};

		_methodName290 = "deleteAccountCustomer";

		_methodParameterTypes290 = new String[] { "long", "long" };

		_methodName291 = "deleteAccountCustomers";

		_methodParameterTypes291 = new String[] { "long" };

		_methodName292 = "deleteAccountEntryAccountCustomers";

		_methodParameterTypes292 = new String[] { "long" };

		_methodName293 = "fetchAccountCustomer";

		_methodParameterTypes293 = new String[] { "long", "long" };

		_methodName294 = "getAccountCustomer";

		_methodParameterTypes294 = new String[] { "long", "long" };

		_methodName295 = "getAccountCustomers";

		_methodParameterTypes295 = new String[] { "long" };

		_methodName296 = "getAccountCustomers";

		_methodParameterTypes296 = new String[] { "long", "int" };

		_methodName297 = "getUserAccountCustomers";

		_methodParameterTypes297 = new String[] { "long" };

		_methodName298 = "getUserAccountCustomers";

		_methodParameterTypes298 = new String[] { "long", "int[][]" };

		_methodName299 = "hasAccountCustomer";

		_methodParameterTypes299 = new String[] { "long", "long" };

		_methodName300 = "toggleNotifications";

		_methodParameterTypes300 = new String[] { "long" };

		_methodName301 = "updateAccountCustomer";

		_methodParameterTypes301 = new String[] { "long", "long", "int", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.addAccountCustomer((com.liferay.osb.model.AccountCustomer)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.createAccountCustomer(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.deleteAccountCustomer(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.deleteAccountCustomer((com.liferay.osb.model.AccountCustomer)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.fetchAccountCustomer(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getAccountCustomer(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getAccountCustomers(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getAccountCustomersCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.updateAccountCustomer((com.liferay.osb.model.AccountCustomer)arguments[0]);
		}

		if (_methodName282.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes282, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.addAccountCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName288.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes288, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.addAccountCustomer(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName289.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes289, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.deleteAccountCustomer(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.AccountCustomer)arguments[1]);
		}

		if (_methodName290.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes290, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.deleteAccountCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName291.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes291, parameterTypes)) {
			AccountCustomerLocalServiceUtil.deleteAccountCustomers(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName292.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes292, parameterTypes)) {
			AccountCustomerLocalServiceUtil.deleteAccountEntryAccountCustomers(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName293.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes293, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.fetchAccountCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName294.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes294, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getAccountCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName295.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes295, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getAccountCustomers(((Long)arguments[0]).longValue());
		}

		if (_methodName296.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes296, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getAccountCustomers(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName297.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes297, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getUserAccountCustomers(((Long)arguments[0]).longValue());
		}

		if (_methodName298.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes298, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getUserAccountCustomers(((Long)arguments[0]).longValue(),
				(int[])arguments[1]);
		}

		if (_methodName299.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes299, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.hasAccountCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName300.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes300, parameterTypes)) {
			AccountCustomerLocalServiceUtil.toggleNotifications(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName301.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes301, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.updateAccountCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
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
	private String _methodName298;
	private String[] _methodParameterTypes298;
	private String _methodName299;
	private String[] _methodParameterTypes299;
	private String _methodName300;
	private String[] _methodParameterTypes300;
	private String _methodName301;
	private String[] _methodParameterTypes301;
}