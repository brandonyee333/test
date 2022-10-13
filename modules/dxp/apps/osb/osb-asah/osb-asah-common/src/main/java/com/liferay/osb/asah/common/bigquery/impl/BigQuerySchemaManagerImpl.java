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

package com.liferay.osb.asah.common.bigquery.impl;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryException;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Clustering;
import com.google.cloud.bigquery.Dataset;
import com.google.cloud.bigquery.DatasetId;
import com.google.cloud.bigquery.DatasetInfo;
import com.google.cloud.bigquery.Field;
import com.google.cloud.bigquery.FieldList;
import com.google.cloud.bigquery.LegacySQLTypeName;
import com.google.cloud.bigquery.Schema;
import com.google.cloud.bigquery.StandardSQLTypeName;
import com.google.cloud.bigquery.StandardTableDefinition;
import com.google.cloud.bigquery.Table;
import com.google.cloud.bigquery.TableDefinition;
import com.google.cloud.bigquery.TableId;
import com.google.cloud.bigquery.TableInfo;
import com.google.cloud.bigquery.TimePartitioning;
import com.google.cloud.bigquery.ViewDefinition;

import com.liferay.osb.asah.common.bigquery.BigQuerySchemaManager;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.io.InputStream;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class BigQuerySchemaManagerImpl implements BigQuerySchemaManager {

	@Override
	public void createSchema(Project project) {
		try {
			Dataset dataset = _createDataset(project);

			for (String tableName : _tablesJSONObject.keySet()) {
				_createTable(
					dataset.getDatasetId(),
					_tablesJSONObject.getJSONObject(tableName), tableName);
			}

			for (String viewName : _viewsJSONObject.keySet()) {
				JSONObject jsonObject = _viewsJSONObject.getJSONObject(
					viewName);

				_createView(
					dataset.getDatasetId(),
					_readFile("/bigquery/" + jsonObject.getString("path")),
					viewName);
			}
		}
		catch (Exception exception) {
			_log.error(
				"Unable to create schema for project " + project.getId(),
				exception);
		}
	}

	@Override
	public void deleteSchema(String projectId) {
		try {
			boolean success = _bigQuery.delete(
				DatasetId.of(_googleProjectId, projectId),
				BigQuery.DatasetDeleteOption.deleteContents());

			if (_log.isInfoEnabled()) {
				if (success) {
					_log.info(
						String.format(
							"Schema for project %s deleted successfully",
							projectId));
				}
			}
			else {
				_log.info(
					String.format(
						"Schema for project %s was not found" + projectId));
			}
		}
		catch (BigQueryException bigQueryException) {
			_log.error(
				"Unable to delete schema for project " + projectId,
				bigQueryException);
		}
	}

	private Clustering _buildClustering(JSONArray clusteringFieldsJSONArray) {
		Clustering.Builder builder = Clustering.newBuilder();

		return builder.setFields(
			JSONUtil.toStringList(clusteringFieldsJSONArray)
		).build();
	}

	private Field _buildField(JSONObject fieldJSONObject) {
		Field.Builder builder = null;

		if (Objects.equals(fieldJSONObject.getString("type"), "RECORD")) {
			builder = Field.newBuilder(
				fieldJSONObject.getString("name"), LegacySQLTypeName.RECORD,
				_buildFieldList(fieldJSONObject.getJSONArray("fields")));
		}
		else {
			builder = Field.newBuilder(
				fieldJSONObject.getString("name"),
				StandardSQLTypeName.valueOf(fieldJSONObject.getString("type")));
		}

		if (fieldJSONObject.has("mode")) {
			return builder.setMode(
				Field.Mode.valueOf(fieldJSONObject.getString("mode"))
			).build();
		}

		return builder.setMode(
			Field.Mode.NULLABLE
		).build();
	}

	private FieldList _buildFieldList(JSONArray fieldsJSONArray) {
		List<Field> fields = new ArrayList<>();

		for (int i = 0; i < fieldsJSONArray.length(); i++) {
			JSONObject fieldJSONObject = fieldsJSONArray.getJSONObject(i);

			fields.add(_buildField(fieldJSONObject));
		}

		return FieldList.of(fields);
	}

	private Schema _buildSchema(JSONArray fieldsJSONArray) {
		try {
			return Schema.of(
				JSONUtil.toList(fieldsJSONArray, this::_buildField));
		}
		catch (Exception exception) {
			throw new IllegalStateException(
				"Unable to build schema", exception);
		}
	}

	private TableDefinition _buildTableDefinition(JSONObject tableJSONObject) {
		StandardTableDefinition.Builder builder =
			StandardTableDefinition.newBuilder();

		if (tableJSONObject.has("clusteringFields")) {
			builder.setClustering(
				_buildClustering(
					tableJSONObject.getJSONArray("clusteringFields")));
		}

		if (tableJSONObject.has("timePartitioning")) {
			builder.setTimePartitioning(
				_buildTimePartitioning(
					tableJSONObject.getJSONObject("timePartitioning")));
		}

		return builder.setLocation(
			_location
		).setSchema(
			_buildSchema(tableJSONObject.getJSONArray("fields"))
		).build();
	}

	private TimePartitioning _buildTimePartitioning(
		JSONObject timePartitioningJSONObject) {

		TimePartitioning.Builder builder = TimePartitioning.newBuilder(
			TimePartitioning.Type.valueOf(
				timePartitioningJSONObject.getString("type")));

		return builder.setField(
			timePartitioningJSONObject.getString("field")
		).build();
	}

	private Dataset _createDataset(Project project) {
		DatasetInfo.Builder builder = DatasetInfo.newBuilder(project.getId());

		builder = builder.setLocation(_location);

		Dataset dataset = _bigQuery.create(builder.build());

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Dataset %s created successfully", project.getId()));
		}

		return dataset;
	}

	private Table _createTable(
		DatasetId datasetId, JSONObject tablesJSONObject, String tableName) {

		TableId tableId = TableId.of(datasetId.getDataset(), tableName);

		TableInfo.Builder builder = TableInfo.newBuilder(
			tableId, _buildTableDefinition(tablesJSONObject));

		Table table = _bigQuery.create(builder.build());

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Table %s.%s created successfully", datasetId, tableName));
		}

		return table;
	}

	private Table _createView(
		DatasetId datasetId, String query, String viewName) {

		TableId tableId = TableId.of(datasetId.getDataset(), viewName);

		ViewDefinition viewDefinition = ViewDefinition.newBuilder(
			StringUtils.replace(query, "$[AC_PROJECT_ID]", _getProjectId())
		).setUseLegacySql(
			false
		).build();

		Table table = _bigQuery.create(TableInfo.of(tableId, viewDefinition));

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"View %s.%s created successfully", datasetId, viewName));
		}

		return table;
	}

	private String _getProjectId() {
		return _googleProjectId + "." + ProjectIdThreadLocal.getProjectId();
	}

	@PostConstruct
	private void _init() {
		BigQueryOptions bigQueryOptions = BigQueryOptions.getDefaultInstance();

		_bigQuery = bigQueryOptions.getService();
		_googleProjectId = bigQueryOptions.getProjectId();

		_tablesJSONObject = new JSONObject(_readFile("/bigquery_tables.json"));
		_viewsJSONObject = new JSONObject(_readFile("/bigquery_views.json"));
	}

	private String _readFile(String filePath) {
		try {
			Class<?> clazz = getClass();

			InputStream inputStream = clazz.getResourceAsStream(filePath);

			return IOUtils.toString(inputStream, Charset.defaultCharset());
		}
		catch (Exception exception) {
			throw new IllegalStateException(exception);
		}
	}

	private static final Log _log = LogFactory.getLog(
		BigQuerySchemaManagerImpl.class);

	private BigQuery _bigQuery;
	private String _googleProjectId;

	@Value("${gcloud.compute.region}")
	private String _location;

	private JSONObject _tablesJSONObject;
	private JSONObject _viewsJSONObject;

}