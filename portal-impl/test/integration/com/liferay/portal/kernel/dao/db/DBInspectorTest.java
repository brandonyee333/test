/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.db;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import org.eclipse.core.runtime.Assert;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alberto Chaparro
 */
public class DBInspectorTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_connection = DataAccess.getConnection();

		_dbInspector = new DBInspector(_connection);
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		DataAccess.cleanUp(_connection);
	}

	@Test
	public void testHasColumn() throws Exception {
		Assert.isTrue(
			_dbInspector.hasColumn(
				_TABLE_NAME_EXISTING, _COLUMN_NAME_EXISTING));
	}

	@Test
	public void testHasColumnLowerCase() throws Exception {
		DatabaseMetaData databaseMetaData = _connection.getMetaData();

		Assume.assumeTrue(databaseMetaData.storesLowerCaseIdentifiers());

		Assert.isTrue(
			_dbInspector.hasColumn(
				_TABLE_NAME_EXISTING,
				StringUtil.toLowerCase(_COLUMN_NAME_EXISTING)));
	}

	@Test
	public void testHasColumnNonexisting() throws Exception {
		Assert.isTrue(
			!_dbInspector.hasColumn(
				_TABLE_NAME_EXISTING, _COLUMN_NAME_NONEXISTING));
	}

	@Test
	public void testHasColumnUpperCase() throws Exception {
		DatabaseMetaData databaseMetaData = _connection.getMetaData();

		Assume.assumeTrue(databaseMetaData.storesUpperCaseIdentifiers());

		Assert.isTrue(
			_dbInspector.hasColumn(
				_TABLE_NAME_EXISTING,
				StringUtil.toUpperCase(_COLUMN_NAME_EXISTING)));
	}

	@Test
	public void testHasTable() throws Exception {
		Assert.isTrue(_dbInspector.hasTable(_TABLE_NAME_EXISTING));
	}

	@Test
	public void testHasTableLowerCase() throws Exception {
		DatabaseMetaData databaseMetaData = _connection.getMetaData();

		Assume.assumeTrue(databaseMetaData.storesLowerCaseIdentifiers());

		Assert.isTrue(
			_dbInspector.hasTable(
				StringUtil.toLowerCase(_TABLE_NAME_EXISTING)));
	}

	@Test
	public void testHasTableNonexisting() throws Exception {
		Assert.isTrue(!_dbInspector.hasTable(_TABLE_NAME_NONEXISTING));
	}

	@Test
	public void testHasTableUpperCase() throws Exception {
		DatabaseMetaData databaseMetaData = _connection.getMetaData();

		Assume.assumeTrue(databaseMetaData.storesUpperCaseIdentifiers());

		Assert.isTrue(
			_dbInspector.hasTable(
				StringUtil.toUpperCase(_TABLE_NAME_EXISTING)));
	}

	private static final String _COLUMN_NAME_EXISTING = "releaseId";

	private static final String _COLUMN_NAME_NONEXISTING = "nonexistingColumn";

	private static final String _TABLE_NAME_EXISTING = "Release_";

	private static final String _TABLE_NAME_NONEXISTING = "NonexistingTable";

	private static Connection _connection;
	private static DBInspector _dbInspector;

}