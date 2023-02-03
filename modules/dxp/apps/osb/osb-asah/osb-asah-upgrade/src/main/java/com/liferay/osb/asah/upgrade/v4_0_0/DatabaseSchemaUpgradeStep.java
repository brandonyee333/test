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

package com.liferay.osb.asah.upgrade.v4_0_0;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Field;
import com.google.cloud.bigquery.LegacySQLTypeName;
import com.google.cloud.bigquery.Schema;
import com.google.cloud.bigquery.StandardTableDefinition;
import com.google.cloud.bigquery.Table;
import com.google.cloud.bigquery.TableDefinition;

import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.postgresql.PostgreSQLSchemaManager;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class DatabaseSchemaUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		try {

			// PostgreSQL

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("tables_4.0.0.sql")),
				_postgreSQLDataSource);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("constraints_4.0.0.sql")),
				_postgreSQLDataSource);

			// BigQuery

			Field field = Field.newBuilder(
				"urls", LegacySQLTypeName.STRING
			).setMode(
				Field.Mode.REPEATED
			).build();

			_addTableField(
				field, ProjectIdThreadLocal.getProjectId(), "session");

			if (_log.isInfoEnabled()) {
				_log.info("Databases successfully upgraded to schema 4.0.0");
			}
		}
		catch (Exception exception) {
			String projectId = ProjectIdThreadLocal.getProjectId();

			_log.error(
				String.format(
					"Failed to run SchemaUpgradeStep on %s. Attempting to " +
						"create project schema",
					projectId));

			_postgreSQLSchemaManager.createSchema(new Project(projectId));

			ProjectIdThreadLocal.setProjectId(projectId);
		}
	}

	private void _addTableField(
		Field field, String projectId, String tableName) {

		Table table = _bigQuery.getTable(projectId, tableName);

		List<Field> fields = _getTableFields(table);

		fields.add(field);

		Table.Builder builder = table.toBuilder();

		builder = builder.setDefinition(
			StandardTableDefinition.of(Schema.of(fields)));

		table = builder.build();

		table.update();
	}

	private List<Field> _getTableFields(Table table) {
		TableDefinition definition = table.getDefinition();

		Schema currentSchema = definition.getSchema();

		return new ArrayList<>(currentSchema.getFields());
	}

	@PostConstruct
	private void _init() {
		BigQueryOptions bigQueryOptions = BigQueryOptions.getDefaultInstance();

		_bigQuery = bigQueryOptions.getService();
	}

	private static final Log _log = LogFactory.getLog(
		DatabaseSchemaUpgradeStep.class);

	private BigQuery _bigQuery;

	@Autowired
	@Qualifier("postgreSQLDataSource")
	private DataSource _postgreSQLDataSource;

	@Autowired
	private PostgreSQLSchemaManager _postgreSQLSchemaManager;

}