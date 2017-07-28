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

package com.liferay.osb.util;

import com.liferay.portal.kernel.dao.orm.CustomSQLParam;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.util.dao.orm.CustomSQLUtil;

/**
 * @author Alan Zhang
 */
public class OSBCustomSQLParam extends CustomSQLParam {

	public OSBCustomSQLParam(String key, String sql, Object value) {
		super(sql, value);

		if (key.equals("usersAccountCustomerPartnerWorker")) {
			Object[] valueArray = (Object[])value;

			sql = CustomSQLUtil.replaceKeywords(
				sql, "OSB_AccountCustomer.role", true, (int[])valueArray[1]);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "OSB_PartnerWorker.role", true, (int[])valueArray[3]);
		}
		else if (key.equals("usersAccountCustomers")) {
			Object[] valueArray = (Object[])value;

			sql = CustomSQLUtil.replaceKeywords(
				sql, "OSB_AccountCustomer.role", true, (int[])valueArray[1]);
		}

		_key = key;
		_sql = sql;
		_value = value;
	}

	@Override
	public String getSQL() {
		return _sql;
	}

	@Override
	public void process(QueryPos qPos) {
		if (_key.equals("usersAccountCustomerPartnerWorker")) {
			Object[] valueArray = (Object[])_value;

			Long accountEntryId = (Long)valueArray[0];
			int[] accountCustomerRoles = (int[])valueArray[1];

			Long partnerEntryId = (Long)valueArray[2];
			int[] partnerWorkerRoles = (int[])valueArray[3];

			qPos.add(accountEntryId);

			for (int role : accountCustomerRoles) {
				qPos.add(role);
			}

			qPos.add(partnerEntryId);

			for (int role : partnerWorkerRoles) {
				qPos.add(role);
			}
		}
		else if (_key.equals("usersAccountCustomers")) {
			Object[] valueArray = (Object[])_value;

			Long accountEntryId = (Long)valueArray[0];
			int[] roles = (int[])valueArray[1];

			qPos.add(accountEntryId);

			for (int role : roles) {
				qPos.add(role);
			}
		}
		else {
			super.process(qPos);
		}
	}

	private final String _key;
	private final String _sql;
	private final Object _value;

}