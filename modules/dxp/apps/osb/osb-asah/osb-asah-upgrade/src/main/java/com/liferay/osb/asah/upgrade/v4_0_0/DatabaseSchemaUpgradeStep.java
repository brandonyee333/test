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

import com.liferay.osb.asah.common.bigquery.BigQuerySchemaManager;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.postgresql.PostgreSQLSchemaManager;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
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
	public void upgrade(String version) {
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

			// BigQuery Functions

			_bigQuerySchemaManager.createFunction(
				ProjectIdThreadLocal.getProjectId(), "acquisitionChannel");

			// BigQuery Tables

			_bigQuerySchemaManager.dropTable(
				ProjectIdThreadLocal.getProjectId(), "expandovalue");
			_bigQuerySchemaManager.dropTable(
				ProjectIdThreadLocal.getProjectId(), "individual");
			_bigQuerySchemaManager.dropTable(
				ProjectIdThreadLocal.getProjectId(), "user");

			_bigQuerySchemaManager.createTable(
				ProjectIdThreadLocal.getProjectId(), "expandovalue");
			_bigQuerySchemaManager.createTable(
				ProjectIdThreadLocal.getProjectId(), "identityinterestpage");
			_bigQuerySchemaManager.createTable(
				ProjectIdThreadLocal.getProjectId(), "identityinterestscore");
			_bigQuerySchemaManager.createTable(
				ProjectIdThreadLocal.getProjectId(), "individual");
			_bigQuerySchemaManager.createTable(
				ProjectIdThreadLocal.getProjectId(), "membership");
			_bigQuerySchemaManager.createTable(
				ProjectIdThreadLocal.getProjectId(), "membershipchange");
			_bigQuerySchemaManager.createTable(
				ProjectIdThreadLocal.getProjectId(), "sessioninterestscore");
			_bigQuerySchemaManager.createTable(
				ProjectIdThreadLocal.getProjectId(), "user");

			_updateTableFields(
				Arrays.asList(
					Field.newBuilder(
						"status", LegacySQLTypeName.INTEGER
					).setMode(
						Field.Mode.NULLABLE
					).build(),
					Field.newBuilder(
						"type", LegacySQLTypeName.STRING
					).setMode(
						Field.Mode.NULLABLE
					).build()),
				"accountentry");

			_addTableFields(
				Arrays.asList(
					Field.newBuilder(
						"assetId", LegacySQLTypeName.STRING
					).setMode(
						Field.Mode.NULLABLE
					).build(),
					Field.newBuilder(
						"assetTitle", LegacySQLTypeName.STRING
					).setMode(
						Field.Mode.NULLABLE
					).build()),
				"event");

			_addTableFields(
				Arrays.asList(
					Field.newBuilder(
						"createDate", LegacySQLTypeName.TIMESTAMP
					).setMode(
						Field.Mode.NULLABLE
					).build()),
				"organization");

			_addTableFields(
				Collections.singletonList(
					Field.newBuilder(
						"urls", LegacySQLTypeName.STRING
					).setMode(
						Field.Mode.REPEATED
					).build()),
				"session");

			// BigQuery Views

			_bigQuerySchemaManager.createOrReplaceView(
				ProjectIdThreadLocal.getProjectId(), "asset");
			_bigQuerySchemaManager.createOrReplaceView(
				ProjectIdThreadLocal.getProjectId(), "bloghourly");
			_bigQuerySchemaManager.createOrReplaceView(
				ProjectIdThreadLocal.getProjectId(), "customassethourly");
			_bigQuerySchemaManager.createOrReplaceView(
				ProjectIdThreadLocal.getProjectId(), "documentlibraryhourly");
			_bigQuerySchemaManager.createOrReplaceView(
				ProjectIdThreadLocal.getProjectId(), "fieldmapping");
			_bigQuerySchemaManager.createOrReplaceView(
				ProjectIdThreadLocal.getProjectId(), "formhourly");
			_bigQuerySchemaManager.createOrReplaceView(
				ProjectIdThreadLocal.getProjectId(), "identityactivity");
			_bigQuerySchemaManager.createOrReplaceView(
				ProjectIdThreadLocal.getProjectId(), "journalhourly");
			_bigQuerySchemaManager.createOrReplaceView(
				ProjectIdThreadLocal.getProjectId(), "pagehourly");
			_bigQuerySchemaManager.createOrReplaceView(
				ProjectIdThreadLocal.getProjectId(), "pagereferrers");

			if (_log.isInfoEnabled()) {
				_log.info("Databases successfully upgraded to schema 4.0.0");
			}
		}
		catch (RuntimeException runtimeException) {
			throw runtimeException;
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

	private void _addTableFields(List<Field> newFields, String tableName) {
		Table table = _bigQuery.getTable(
			ProjectIdThreadLocal.getProjectId(), tableName);

		List<Field> fields = _getTableFields(table);

		for (Field newField : newFields) {
			fields.add(newField);
		}

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

	private void _updateTableFields(List<Field> newFields, String tableName) {
		List<Field> fields = new ArrayList<>();

		Table table = _bigQuery.getTable(
			ProjectIdThreadLocal.getProjectId(), tableName);

		for (Field field : _getTableFields(table)) {
			for (Field newField : newFields) {
				if (StringUtils.equals(newField.getName(), field.getName())) {
					fields.add(newField);

					continue;
				}

				fields.add(field);
			}
		}

		Table.Builder builder = table.toBuilder();

		builder = builder.setDefinition(
			StandardTableDefinition.of(Schema.of(fields)));

		table = builder.build();

		table.update();
	}

	private static final Log _log = LogFactory.getLog(
		DatabaseSchemaUpgradeStep.class);

	private BigQuery _bigQuery;

	@Autowired
	private BigQuerySchemaManager _bigQuerySchemaManager;

	@Autowired
	@Qualifier("postgreSQLDataSource")
	private DataSource _postgreSQLDataSource;

	@Autowired
	private PostgreSQLSchemaManager _postgreSQLSchemaManager;

}