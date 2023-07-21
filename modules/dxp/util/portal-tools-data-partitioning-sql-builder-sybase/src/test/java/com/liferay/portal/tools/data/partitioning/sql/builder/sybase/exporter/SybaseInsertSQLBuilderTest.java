/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.sybase.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.InsertSQLBuilder;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class SybaseInsertSQLBuilderTest {

	@Test
	public void testBuildFieldFieldDate() throws Exception {
		String field = _insertSQLBuilder.buildField(new Date(0L));

		Assert.assertEquals("01 01 1970 12:00:000", field);
	}

	@Test
	public void testBuildFieldFieldDouble() throws Exception {
		String field = _insertSQLBuilder.buildField(Double.valueOf(99.99));

		Assert.assertEquals("99.99", field);
	}

	@Test
	public void testBuildFieldFieldFloat() throws Exception {
		String field = _insertSQLBuilder.buildField(Float.valueOf(1));

		Assert.assertEquals("1.0", field);
	}

	@Test
	public void testBuildFieldFieldInteger() throws Exception {
		String field = _insertSQLBuilder.buildField(Integer.valueOf(1));

		Assert.assertEquals("1", field);
	}

	@Test
	public void testBuildFieldFieldNull() throws Exception {
		String field = _insertSQLBuilder.buildField(null);

		Assert.assertEquals("'null'", field);
	}

	@Test
	public void testBuildFieldFieldString() throws Exception {
		String field = _insertSQLBuilder.buildField(new String("1"));

		Assert.assertEquals("'1'", field);
	}

	@Test
	public void testBuildFieldFieldStringShouldWithQuotes() throws Exception {
		String field = _insertSQLBuilder.buildField(new String("'1'"));

		Assert.assertEquals("'''1'''", field);
	}

	@Test
	public void testBuildFieldFieldTimestamp() throws Exception {
		String field = _insertSQLBuilder.buildField(new Timestamp(0L));

		Assert.assertEquals("01 01 1970 12:00:000", field);
	}

	@Test
	public void testBuildInsert() {
		String[] fields = {"a", "b", "c", "d"};

		Assert.assertEquals(
			"insert into Foo values (a, b, c, d)\nGO\n",
			_insertSQLBuilder.buildInsert("Foo", fields));
	}

	@Test
	public void testBuildInsertWithMultilineFieldValues() {
		String[] fields = {"a;\n", "b", "c;\n", "d"};

		Assert.assertEquals(
			"insert into Foo values (a;\n, b, c;\n, d)\nGO\n",
			_insertSQLBuilder.buildInsert("Foo", fields));
	}

	private final InsertSQLBuilder _insertSQLBuilder =
		new SybaseInsertSQLBuilder();

}