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

package com.liferay.portal.sqlchecker.impl;

import com.liferay.portal.kernel.util.StringBundler;

import java.io.IOException;

import java.net.URISyntaxException;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sampsa Sohlman
 */
public class SQLCheckerTest {

	@Test
	public void testExampleDM() throws Exception {
		verifyQueryFile("/sql-example-dm-ok.txt");
		verifyQueryFile("/sql-example-dm-error.txt");
	}

	@Test
	public void testExampleSocial() throws Exception {
		verifyQueryFile("/sql-example-social-ok.txt");
		verifyQueryFile("/sql-example-social-error.txt");
	}

	@Test
	public void testHibernateSelectWhere() throws Exception {
		verifyQueryFile("/sql-simple-hibernate-select-ok.txt");
	}

	@Test
	public void testInnerJoinSelect() throws Exception {
		verifyQueryFile("/sql-inner-join-select01-ok.txt");
		verifyQueryFile("/sql-inner-join-select02-ok.txt");
		verifyQueryFile("/sql-inner-join-select03-ok.txt");
		verifyQueryFile("/sql-inner-join-select-error.txt");
	}

	@Test
	public void testInnerJoinStange() throws Exception {
		verifyQueryFile("/sql-inner-join-select-stange-ok.txt");
	}

	@Test
	public void testRealworld() throws Exception {
		verifyQueryFile("/sql-realworld01-ok.txt");
	}

	@Test
	public void testSelectGroupBy() throws Exception {
		verifyQueryFile("/sql-select-group-by01-ok.txt");
		verifyQueryFile("/sql-select-group-by02-ok.txt");
		verifyQueryFile("/sql-select-group-by03-ok.txt");
		verifyQueryFile("/sql-select-group-by03-error.txt");
	}

	@Test
	public void testSelectOrderBy() throws Exception {
		verifyQueryFile("/sql-select-order-by01-ok.txt");
		verifyQueryFile("/sql-select-order-by02-ok.txt");
		verifyQueryFile("/sql-select-order-by03-ok.txt");
		verifyQueryFile("/sql-select-order-by-error.txt");
	}

	@Test
	public void testSimpleFunctionSelect() throws Exception {
		verifyQueryFile("/sql-simple-function-select-ok.txt");
		verifyQueryFile("/sql-simple-function-select1-ok.txt");
	}

	@Test
	public void testSimpleSelect() throws Exception {
		verifyQueryFile("/sql-simple-select01-ok.txt");
		verifyQueryFile("/sql-simple-select02-ok.txt");
	}

	@Test
	public void testSimpleSelectWhere() throws Exception {
		verifyQueryFile("/sql-simple-select-where-ok.txt");
		verifyQueryFile("/sql-simple-select-where-no-prefix-ok.txt");
	}

	@Test
	public void testSimpleSelectWhereIn() throws Exception {
		verifyQueryFile("/sql-simple-select-where-in-ok.txt");
	}

	@Test
	public void testStarSelectWhere() throws Exception {
		verifyQueryFile("/sql-simple-star-select-ok.txt");
	}

	@Test
	public void testUnionSelect() throws Exception {
		verifyQueryFile("/sql-union01-ok.txt");
		verifyQueryFile("/sql-union02-ok.txt");
	}

	protected String cleanEntities(String sql) {
		return sql.replaceAll("[\\{]|[\\}]", "");
	}

	protected String readSQL(String file)
		throws IOException, URISyntaxException {

		List<String> list = Files.readAllLines(
				Paths.get(this.getClass().getResource(file).toURI()),
				Charset.defaultCharset());
		StringBundler sb = new StringBundler(list.size()*2);

		for (String line : list) {
			sb.append(line);
			sb.append("\n");
		}

		return sb.toString();
	}

	protected void verifyQueryFile(String file) throws Exception {
		String sql = cleanEntities(readSQL(file));
		SQLChecker sqlChecker = new SQLCheckerImpl();

		try {
			sqlChecker.verifySelectSQL(sql);

			if (file.indexOf("-error.")>0) {
				Assert.fail(
					"sql-file " + file + " sql should be faulty : " + sql);
			}
		}
		catch (NoTableNameAtColumnSQLException ntbce ) {
			if (file.indexOf("-ok.")>0) {
				Assert.fail(ntbce.getMessage() + "\n" + sql);
			}
		}
	}

}