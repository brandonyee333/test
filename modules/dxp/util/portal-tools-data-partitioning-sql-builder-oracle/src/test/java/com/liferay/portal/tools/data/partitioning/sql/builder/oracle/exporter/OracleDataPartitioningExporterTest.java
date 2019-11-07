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

package com.liferay.portal.tools.data.partitioning.sql.builder.oracle.exporter;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Manuel de la Peña
 */
public class OracleDataPartitioningExporterTest {

	@Test
	public void testExportWithoutOracleHome() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(
			"The environment variable ORACLE_HOME is not set");

		Map<String, String> environment = new HashMap<>();

		environment.put("ORACLE_HOME", "");

		_oracleDataPartitioningExporter.setEnvironment(environment);

		_oracleDataPartitioningExporter.export(null);
	}

	@Test
	public void testExportWithoutTnsAdmin() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(
			"The environment variable TNS_ADMIN is not set");

		Map<String, String> environment = new HashMap<>();

		environment.put("ORACLE_HOME", "/path/to/oracle/home");
		environment.put("TNS_ADMIN", "");

		_oracleDataPartitioningExporter.setEnvironment(environment);

		_oracleDataPartitioningExporter.export(null);
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private final OracleDataPartitioningExporter
		_oracleDataPartitioningExporter = new OracleDataPartitioningExporter();

}