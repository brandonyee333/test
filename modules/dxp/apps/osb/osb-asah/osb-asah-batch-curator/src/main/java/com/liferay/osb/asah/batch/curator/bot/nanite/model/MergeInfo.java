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

package com.liferay.osb.asah.batch.curator.bot.nanite.model;

import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

/**
 * @author Rachael Koestartyo
 */
public abstract class MergeInfo implements Serializable {

	public MergeInfo(String projectId) {
		_projectId = projectId;
	}

	public abstract List<String> getInsertFields();

	public abstract Map<String, String> getJoinFields();

	public String getMergeStatement(String previousRunDateString) {
		Map<String, String> mergeQueryValues = new HashMap<>();

		mergeQueryValues.put(
			"insertSQL", _buildInsertStatement(getInsertFields()));
		mergeQueryValues.put(
			"joinConditions", _buildJoinConditions(getJoinFields()));
		mergeQueryValues.put("replicaTable", getReplicaTable());
		mergeQueryValues.put("replicaTableAlias", _REPLICA_TABLE_ALIAS);
		mergeQueryValues.put("stagingTableAlias", _STAGING_TABLE_ALIAS);
		mergeQueryValues.put(
			"stagingTableSQL", getStagingTableSQL(previousRunDateString));
		mergeQueryValues.put(
			"updateSQL", _buildUpdateStatement(getUpdateFields()));

		return StringSubstitutor.replace(
			_templateProperties.getProperty("merge"), mergeQueryValues, "{",
			"}");
	}

	public String getProjectId() {
		return _projectId;
	}

	public abstract String getReplicaTable();

	public abstract String getStagingTableSQL(String previousRunDateString);

	public abstract List<String> getUpdateFields();

	protected String buildSelectExpandoColumnIds() {
		return getQueryTemplate("unnestExpandoFields");
	}

	protected String buildSelectFields(Map<String, String> fieldNames) {
		List<String> fields = new ArrayList<>();

		for (Map.Entry<String, String> entry : fieldNames.entrySet()) {
			fields.add(_selectField(entry.getKey(), entry.getValue()));
		}

		return String.join(", ", fields);
	}

	protected String buildStagingTableSQL(
		String previousRunDateString, String selectFields, String type) {

		Map<String, String> mergeQueryValues = new HashMap<>();

		mergeQueryValues.put("previousRunDateString", previousRunDateString);
		mergeQueryValues.put("selectFields", selectFields);
		mergeQueryValues.put("sha256HexId", getQueryTemplate("sha256HexId"));
		mergeQueryValues.put("stagingTable", getProjectId() + ".dxpentity");
		mergeQueryValues.put("type", type);

		return StringSubstitutor.replace(
			getQueryTemplate("staging"), mergeQueryValues, "{", "}");
	}

	protected String getQueryTemplate(String key) {
		return _templateProperties.getProperty(key);
	}

	private String _buildInsertStatement(List<String> insertFields) {
		Stream<String> stream = insertFields.stream();

		return String.format(
			_templateProperties.getProperty("insert"),
			String.join(", ", _joinFields(insertFields)),
			String.join(
				", ",
				stream.map(
					fieldName -> {
						String[] fields = StringUtils.split(fieldName, ".");

						if (fields.length == 2) {
							return String.format(
								"%s.%s", _STAGING_TABLE_ALIAS, fields[1]);
						}

						return String.format(
							"%s.%s", _STAGING_TABLE_ALIAS, fields[0]);
					}
				).collect(
					Collectors.toList()
				)));
	}

	private String _buildJoinConditions(Map<String, String> joinFields) {
		List<String> conditions = new ArrayList<>();

		joinFields.forEach(
			(key, value) -> conditions.add(
				String.format(
					"%s.%s = %s.%s", _STAGING_TABLE_ALIAS, key,
					_REPLICA_TABLE_ALIAS, value)));

		return String.join(" AND ", conditions);
	}

	private String _buildUpdateStatement(List<String> updateFields) {
		Stream<String> stream = updateFields.stream();

		return String.format(
			_templateProperties.getProperty("update"),
			String.join(
				", ",
				stream.map(
					fieldName -> {
						String[] fields = StringUtils.split(fieldName, ".");

						if (fields.length == 2) {
							return String.format(
								"%s.%s = %s.%s", _REPLICA_TABLE_ALIAS,
								fields[0], _STAGING_TABLE_ALIAS, fields[1]);
						}

						return String.format(
							"%s.%s = %s.%s", _REPLICA_TABLE_ALIAS, fields[0],
							_STAGING_TABLE_ALIAS, fields[0]);
					}
				).collect(
					Collectors.toList()
				)));
	}

	@PostConstruct
	private void _init() {
		_templateProperties = _loadTemplateProperties();
	}

	private String _joinFields(List<String> joinFields) {
		List<String> quotedFields = new ArrayList<>();

		for (String joinField : joinFields) {
			String[] fields = StringUtils.split(joinField, ".");

			quotedFields.add("`" + fields[0] + "`");
		}

		return String.join(",", quotedFields);
	}

	private Properties _loadTemplateProperties() {
		Class<?> clazz = getClass();

		try (InputStream inputStream = clazz.getResourceAsStream(
				"bq-dxp-entities-merge-sql.xml")) {

			Properties properties = new Properties();

			properties.load(inputStream);

			return properties;
		}
		catch (Exception exception) {
			throw new IllegalStateException(exception);
		}
	}

	private String _selectField(String fieldName, String fieldType) {
		String fieldAlias = null;

		String[] fieldNames = StringUtils.split(fieldName, ".");

		if (fieldNames.length == 2) {
			fieldName = fieldNames[0];
			fieldAlias = fieldNames[1];
		}

		if (fieldAlias == null) {
			fieldAlias = fieldName;
		}

		String finalFieldAlias = fieldAlias;
		String finalFieldName = fieldName;

		if (fieldType.equalsIgnoreCase("REPEATED")) {
			return StringSubstitutor.replace(
				getQueryTemplate("unnestJSONArrayFields"),
				new HashMap<String, String>() {
					{
						put("fieldAlias", finalFieldAlias);
						put("fieldName", finalFieldName);
					}
				},
				"{", "}");
		}

		return StringSubstitutor.replace(
			getQueryTemplate("unnestFields"),
			new HashMap<String, String>() {
				{
					put("fieldAlias", finalFieldAlias);
					put("fieldName", finalFieldName);
					put("fieldType", fieldType);
				}
			},
			"{", "}");
	}

	private static final String _REPLICA_TABLE_ALIAS = "replica";

	private static final String _STAGING_TABLE_ALIAS = "staging";

	private final String _projectId;
	private Properties _templateProperties;

}