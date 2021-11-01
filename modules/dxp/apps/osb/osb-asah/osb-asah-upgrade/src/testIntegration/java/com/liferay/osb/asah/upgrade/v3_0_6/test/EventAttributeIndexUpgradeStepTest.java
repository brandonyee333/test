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

package com.liferay.osb.asah.upgrade.v3_0_6.test;

import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v3_0_6.EventAttributeIndexUpgradeStep;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcos Martins
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class EventAttributeIndexUpgradeStepTest {

	@Test
	public void testUpgrade() throws Exception {
		try (Connection connection = _postgreSQLDataSource.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				statement.execute(
					"DROP INDEX IF EXISTS IX_EVENTATTRIBUTE_EADIED");

				_assertIndexExists(false, statement);

				_eventIndexUpgradeStep.upgrade("");

				_assertIndexExists(true, statement);
			}
		}
	}

	private void _assertIndexExists(boolean exists, Statement statement)
		throws Exception {

		try (ResultSet resultSet = statement.executeQuery(
				"SELECT COUNT(*) FROM pg_class, pg_index WHERE pg_class.oid " +
					"= pg_index.indexrelid AND pg_class.relname ILIKE " +
						"'IX_EVENTATTRIBUTE_EADIED'")) {

			resultSet.next();

			if (exists) {
				Assert.assertEquals(1, resultSet.getInt(1));

				return;
			}

			Assert.assertEquals(0, resultSet.getInt(1));
		}
	}

	@Autowired
	private EventAttributeIndexUpgradeStep _eventIndexUpgradeStep;

	@Autowired
	@Qualifier("postgreSQLDataSource")
	private DataSource _postgreSQLDataSource;

}