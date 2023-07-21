/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.dao.jdbc;

import com.liferay.portal.dao.jdbc.util.DataSourceWrapper;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.security.lang.DoPrivilegedFactory;
import com.liferay.portal.security.pacl.PACLPolicy;
import com.liferay.portal.security.pacl.PACLUtil;

import java.security.AccessController;
import java.security.PrivilegedAction;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 */
public class PACLDataSource extends DataSourceWrapper {

	public PACLDataSource(DataSource dataSource) {
		super(dataSource);

		_dataSource = dataSource;
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = _dataSource.getConnection();

		PACLPolicy paclPolicy = PACLUtil.getPACLPolicy();

		if (paclPolicy == null) {
			return connection;
		}

		connection = DoPrivilegedFactory.wrap(connection);
		paclPolicy = DoPrivilegedFactory.wrap(paclPolicy);

		return AccessController.doPrivileged(
			new ConnectionPrivilegedAction(connection, paclPolicy));
	}

	private final DataSource _dataSource;

	private static class ConnectionPrivilegedAction
		implements PrivilegedAction<Connection> {

		public ConnectionPrivilegedAction(
			Connection connection, PACLPolicy paclPolicy) {

			_connection = connection;
			_paclPolicy = paclPolicy;
		}

		@Override
		public Connection run() {
			return (Connection)ProxyUtil.newProxyInstance(
				_paclPolicy.getClassLoader(), new Class<?>[] {Connection.class},
				new PACLConnectionHandler(_connection, _paclPolicy));
		}

		private final Connection _connection;
		private final PACLPolicy _paclPolicy;

	}

}