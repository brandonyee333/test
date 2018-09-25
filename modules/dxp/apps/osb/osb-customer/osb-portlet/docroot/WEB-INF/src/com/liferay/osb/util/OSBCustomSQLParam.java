/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.util;

import com.liferay.portal.kernel.dao.orm.CustomSQLParam;
import com.liferay.portal.kernel.dao.orm.QueryPos;

/**
 * @author Alan Zhang
 */
public class OSBCustomSQLParam extends CustomSQLParam {

	public OSBCustomSQLParam(String key, String sql, Object value) {
		super(sql, value);

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
		if (_key.equals("usersDossieraAccountAccountCustomers")) {
			Object[] valueArray = (Object[])_value;

			String dossieraAccountKey = (String)valueArray[0];
			Integer type = (Integer)valueArray[1];

			qPos.add(dossieraAccountKey);
			qPos.add(type);
		}
		else {
			super.process(qPos);
		}
	}

	private final String _key;
	private final String _sql;
	private final Object _value;

}