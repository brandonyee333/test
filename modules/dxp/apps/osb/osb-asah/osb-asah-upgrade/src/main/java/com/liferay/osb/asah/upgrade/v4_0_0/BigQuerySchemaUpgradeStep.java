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
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class BigQuerySchemaUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {

		// Functions

		_bigQuerySchemaManager.createFunction(
			ProjectIdThreadLocal.getProjectId(), "acquisitionChannel");

		// Tables

		_bigQuerySchemaManager.dropTable(
			ProjectIdThreadLocal.getProjectId(), "expandovalue");
		_bigQuerySchemaManager.dropTable(
			ProjectIdThreadLocal.getProjectId(), "individual");

		_bigQuerySchemaManager.createTable(
			ProjectIdThreadLocal.getProjectId(), "expandovalue");
		_bigQuerySchemaManager.createTable(
			ProjectIdThreadLocal.getProjectId(), "identityactivitysummary");
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

		_updateTableFields(
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
				).build(),
				Field.newBuilder(
					"experimentId", LegacySQLTypeName.INTEGER
				).setMode(
					Field.Mode.NULLABLE
				).build()),
			"event");

		_updateTableFields(
			Arrays.asList(
				Field.newBuilder(
					"ctaClicks", LegacySQLTypeName.INTEGER
				).setMode(
					Field.Mode.NULLABLE
				).build(),
				Field.newBuilder(
					"reads", LegacySQLTypeName.INTEGER
				).setMode(
					Field.Mode.NULLABLE
				).build(),
				Field.newBuilder(
					"variantId", LegacySQLTypeName.STRING
				).setMode(
					Field.Mode.NULLABLE
				).build()),
			"pagedaily");

		_updateTableFields(
			Arrays.asList(
				Field.newBuilder(
					"createDate", LegacySQLTypeName.TIMESTAMP
				).setMode(
					Field.Mode.NULLABLE
				).build()),
			"organization");

		_updateTableFields(
			Collections.singletonList(
				Field.newBuilder(
					"urls", LegacySQLTypeName.STRING
				).setMode(
					Field.Mode.REPEATED
				).build()),
			"session");

		_updateTableFields(
			Arrays.asList(
				Field.newBuilder(
					"individualId", LegacySQLTypeName.STRING
				).setMode(
					Field.Mode.NULLABLE
				).build(),
				Field.newBuilder(
					"uuid", LegacySQLTypeName.STRING
				).setMode(
					Field.Mode.NULLABLE
				).build()),
			"user");

		// Views

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
			_log.info("BigQuery successfully upgraded to schema 4.0.0");
		}
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

		fields.addAll(newFields);

		Set<String> newFieldNames = SetUtil.map(newFields, Field::getName);

		Table table = _bigQuery.getTable(
			ProjectIdThreadLocal.getProjectId(), tableName);

		for (Field field : _getTableFields(table)) {
			if (newFieldNames.contains(field.getName())) {
				continue;
			}

			fields.add(field);
		}

		Table.Builder builder = table.toBuilder();

		builder = builder.setDefinition(
			StandardTableDefinition.of(Schema.of(fields)));

		table = builder.build();

		table.update();
	}

	private static final Log _log = LogFactory.getLog(
		BigQuerySchemaUpgradeStep.class);

	private BigQuery _bigQuery;

	@Autowired
	private BigQuerySchemaManager _bigQuerySchemaManager;

}