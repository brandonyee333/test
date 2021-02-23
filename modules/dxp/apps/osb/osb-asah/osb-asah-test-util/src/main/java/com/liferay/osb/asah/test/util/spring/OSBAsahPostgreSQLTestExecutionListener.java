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

package com.liferay.osb.asah.test.util.spring;

import com.liferay.osb.asah.common.constants.ServiceConstants;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.Socket;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * @author Leslie Wong
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class OSBAsahPostgreSQLTestExecutionListener
	extends AbstractTestExecutionListener {

	public OSBAsahPostgreSQLTestExecutionListener() {
		if (!_isPostgreSQLUp()) {
			throw new IllegalStateException(
				"Integration test infrastructure is not up. Please run " +
					"\"docker-compose -f docker-compose.integration-test.yml " +
						"up -d\" from the root project directory.");
		}
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws SQLException {
		try (Connection connection = _dataSource.getConnection()) {
			DatabaseMetaData databaseMetaData = connection.getMetaData();

			ResultSet tables = databaseMetaData.getTables(
				null, connection.getSchema(), null, new String[] {"TABLE"});

			while (tables.next()) {
				PreparedStatement preparedStatement =
					connection.prepareStatement(
						"TRUNCATE TABLE " + tables.getString("TABLE_NAME") +
							" CASCADE");

				preparedStatement.execute();
			}

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("data.sql")),
				_dataSource);
		}
	}

	private boolean _isPostgreSQLUp() {
		return _pingHost(ServiceConstants.POSTGRESQL_SERVER_IP, 5432, 3000);
	}

	private boolean _pingHost(String hostname, int port, int timeout) {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(hostname, port), timeout);

			return true;
		}
		catch (IOException ioe) {
			return false;
		}
	}

	@Autowired
	private DataSource _dataSource;

}