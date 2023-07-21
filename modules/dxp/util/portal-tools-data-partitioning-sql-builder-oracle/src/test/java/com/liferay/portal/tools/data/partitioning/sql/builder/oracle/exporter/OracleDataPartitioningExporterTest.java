/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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