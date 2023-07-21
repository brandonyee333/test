/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.dao.jdbc;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.security.pacl.PACLPolicy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.Statement;

/**
 * @author Brian Wing Shun Chan
 */
public class PACLStatementHandler implements InvocationHandler {

	public PACLStatementHandler(Statement statement, PACLPolicy paclPolicy) {
		_statement = statement;
		_paclPolicy = paclPolicy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		try {
			String methodName = method.getName();

			if (methodName.equals("addBatch") || methodName.equals("execute") ||
				methodName.equals("executeQuery") ||
				methodName.equals("executeUpdate")) {

				if (ArrayUtil.isNotEmpty(arguments)) {
					String sql = (String)arguments[0];

					if (!_paclPolicy.hasSQL(sql)) {
						throw new SecurityException(
							"Attempted to execute unapproved SQL " + sql);
					}
				}
			}
			else if (methodName.equals("equals")) {
				if (proxy == arguments[0]) {
					return true;
				}

				return false;
			}
			else if (methodName.equals("hashCode")) {
				return System.identityHashCode(proxy);
			}

			return method.invoke(_statement, arguments);
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	private final PACLPolicy _paclPolicy;
	private final Statement _statement;

}