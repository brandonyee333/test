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
import com.google.cloud.bigquery.FieldList;
import com.google.cloud.bigquery.LegacySQLTypeName;
import com.google.cloud.bigquery.Schema;
import com.google.cloud.bigquery.StandardTableDefinition;
import com.google.cloud.bigquery.Table;
import com.google.cloud.bigquery.TableDefinition;

import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class DatabaseSchemaV400UpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		Field field = Field.newBuilder(
			"urls", LegacySQLTypeName.STRING
		).setMode(
			Field.Mode.REPEATED
		).build();

		_addTableColumn(field, ProjectIdThreadLocal.getProjectId(), "session");

		if (_log.isInfoEnabled()) {
			_log.info("Database successfully upgraded to schema 4.0.0");
		}
	}

	private void _addTableColumn(
		Field field, String projectId, String tableName) {

		Table table = _bigQuery.getTable(projectId, tableName);

		TableDefinition definition = table.getDefinition();

		Schema schema = definition.getSchema();

		FieldList fields = schema.getFields();

		List<Field> fieldsList = new ArrayList<>();

		fields.forEach(fieldsList::add);

		fieldsList.add(field);

		Schema newSchema = Schema.of(fieldsList);

		Table.Builder builder = table.toBuilder();

		builder = builder.setDefinition(StandardTableDefinition.of(newSchema));

		table = builder.build();

		table.update();
	}

	@PostConstruct
	private void _init() {
		BigQueryOptions bigQueryOptions = BigQueryOptions.getDefaultInstance();

		_bigQuery = bigQueryOptions.getService();
	}

	private static final Log _log = LogFactory.getLog(
		DatabaseSchemaV400UpgradeStep.class);

	private BigQuery _bigQuery;

}