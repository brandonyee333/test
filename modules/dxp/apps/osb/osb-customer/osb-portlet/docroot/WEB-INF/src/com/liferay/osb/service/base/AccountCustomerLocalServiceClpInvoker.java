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

import com.liferay.osb.service.AccountCustomerLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
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

		_methodName9 = "fetchAccountCustomer";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getAccountCustomer";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getAccountCustomers";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getAccountCustomersCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateAccountCustomer";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.AccountCustomer"
			};

		_methodName15 = "updateAccountCustomer";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.AccountCustomer", "boolean"
			};

		_methodName686 = "getBeanIdentifier";

		_methodParameterTypes686 = new String[] {  };

		_methodName687 = "setBeanIdentifier";

		_methodParameterTypes687 = new String[] { "java.lang.String" };

		_methodName692 = "addAccountCustomers";

		_methodParameterTypes692 = new String[] {
				"long", "long[][]", "long", "int[][]", "int[][]"
			};

		_methodName693 = "deleteAccountCustomer";

		_methodParameterTypes693 = new String[] {
				"long", "com.liferay.osb.model.AccountCustomer"
			};

		_methodName694 = "deleteAccountCustomers";

		_methodParameterTypes694 = new String[] { "long" };

		_methodName695 = "deleteAccountCustomers";

		_methodParameterTypes695 = new String[] { "long", "long[][]", "long" };

		_methodName696 = "deleteAccountEntryAccountCustomers";

		_methodParameterTypes696 = new String[] { "long" };

		_methodName697 = "fetchAccountCustomer";

		_methodParameterTypes697 = new String[] { "long", "long" };

		_methodName698 = "getAccountCustomer";

		_methodParameterTypes698 = new String[] { "long", "long" };

		_methodName699 = "getAccountCustomers";

		_methodParameterTypes699 = new String[] { "long" };

		_methodName700 = "getAccountCustomers";

		_methodParameterTypes700 = new String[] { "long", "int" };

		_methodName701 = "getUserAccountCustomers";

		_methodParameterTypes701 = new String[] { "long" };

		_methodName702 = "getUserAccountCustomers";

		_methodParameterTypes702 = new String[] { "long", "int[][]" };

		_methodName703 = "hasAccountCustomer";

		_methodParameterTypes703 = new String[] { "long", "long" };

		_methodName704 = "toggleNotifications";

		_methodParameterTypes704 = new String[] { "long" };
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
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.fetchAccountCustomer(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getAccountCustomer(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getAccountCustomers(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getAccountCustomersCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.updateAccountCustomer((com.liferay.osb.model.AccountCustomer)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.updateAccountCustomer((com.liferay.osb.model.AccountCustomer)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			AccountCustomerLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			AccountCustomerLocalServiceUtil.addAccountCustomers(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue(),
				(int[])arguments[3], (int[])arguments[4]);

			return null;
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.deleteAccountCustomer(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.AccountCustomer)arguments[1]);
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			AccountCustomerLocalServiceUtil.deleteAccountCustomers(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			AccountCustomerLocalServiceUtil.deleteAccountCustomers(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			AccountCustomerLocalServiceUtil.deleteAccountEntryAccountCustomers(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.fetchAccountCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getAccountCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getAccountCustomers(((Long)arguments[0]).longValue());
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getAccountCustomers(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getUserAccountCustomers(((Long)arguments[0]).longValue());
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.getUserAccountCustomers(((Long)arguments[0]).longValue(),
				(int[])arguments[1]);
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return AccountCustomerLocalServiceUtil.hasAccountCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			AccountCustomerLocalServiceUtil.toggleNotifications(((Long)arguments[0]).longValue());

			return null;
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
	private String _methodName699;
	private String[] _methodParameterTypes699;
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
}