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

package com.liferay.osb.asah.dataflow.emulator.util;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.util.InMemoryResource;

/**
 * @author Marcos Martins
 */
public class DatabaseUtil {

	public static void createTables(DataSource dataSource, String projectId) {
		if (_log.isInfoEnabled()) {
			_log.info("Initializing Ingestion Schema");
		}

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new InMemoryResource(
					"CREATE SCHEMA IF NOT EXISTS " + projectId)),
			dataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new ClassPathResource("bq_tables.sql")),
			dataSource);
	}

	private static final Log _log = LogFactory.getLog(DatabaseUtil.class);

}