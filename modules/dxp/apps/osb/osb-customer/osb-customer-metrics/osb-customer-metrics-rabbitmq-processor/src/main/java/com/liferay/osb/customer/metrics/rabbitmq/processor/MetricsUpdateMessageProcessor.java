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

package com.liferay.osb.customer.metrics.rabbitmq.processor;

import com.liferay.osb.customer.metrics.rabbitmq.configuration.MetricsProcessorConfigurationValues;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.NoSuchClassNameException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "routing.key=metrics.update",
	service = MetricsUpdateMessageProcessor.class
)
public class MetricsUpdateMessageProcessor extends BaseMessageProcessor {

	protected String buildSql(String tableName, Map<String, Object> columnMap) {
		StringBundler sb = new StringBundler(6);

		sb.append("replace into ");
		sb.append(tableName);
		sb.append(StringPool.SPACE);

		StringBundler columnNamesSB = new StringBundler(2 * columnMap.size());
		StringBundler columnValueSB = new StringBundler(4 * columnMap.size());

		columnNamesSB.append(StringPool.OPEN_PARENTHESIS);

		columnValueSB.append(StringPool.OPEN_PARENTHESIS);

		Set<Entry<String, Object>> entrySet = columnMap.entrySet();

		Iterator<Entry<String, Object>> iterator = entrySet.iterator();

		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();

			columnNamesSB.append(entry.getKey());

			columnValueSB.append(StringPool.QUESTION);

			if (iterator.hasNext()) {
				columnNamesSB.append(StringPool.COMMA);

				columnValueSB.append(StringPool.COMMA);
			}
		}

		columnNamesSB.append(StringPool.CLOSE_PARENTHESIS);

		columnValueSB.append(StringPool.CLOSE_PARENTHESIS);

		sb.append(columnNamesSB.toString());
		sb.append(" values ");
		sb.append(columnValueSB.toString());

		return sb.toString();
	}

	protected void doProcess(JSONObject jsonObject) throws Exception {
		String schema = jsonObject.getString("schema");

		JSONObject modelJSONObject = jsonObject.getJSONObject("model");

		String modelName = modelJSONObject.getString("name");

		updateTable(schema, modelJSONObject, modelName);

		JSONArray mappingsJSONArray = jsonObject.getJSONArray("mappings");

		if (mappingsJSONArray != null) {
			updateMappingTables(schema, mappingsJSONArray, modelName);
		}
	}

	protected JSONObject reconcile(JSONObject jsonObject) throws Exception {
		Iterator<String> iterator = jsonObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			String lowercaseKey = StringUtil.toLowerCase(key);

			if (lowercaseKey.contains("classname")) {
				String classNameValue = null;

				String prefix = key.substring(0, key.length() - 6);

				if (key.equals("className")) {
					classNameValue = jsonObject.getString(key);

					prefix = key.substring(0, key.length() - 4);
				}
				else {
					JSONObject valueJSONObject = jsonObject.getJSONObject(key);

					classNameValue = valueJSONObject.getString("value");

					String classNameId = _getClassNameId(classNameValue);

					if (classNameId != null) {
						jsonObject.put(key, classNameId);
					}
					else {
						throw new NoSuchClassNameException(
							"{value=" + classNameValue + "}");
					}
				}

				if (classNameValue.equals(User.class.getName())) {
					Object value = jsonObject.get(prefix + "PK");

					if (value instanceof JSONObject) {
						JSONObject columnJSONObject = (JSONObject)value;

						String uuid = columnJSONObject.getString("uuid_");

						String classPK = getColumnValue(
							"select userId from OSB_MetricsUser where uuid_ " +
								"= '" + uuid + "'");

						if (classPK != null) {
							jsonObject.put(prefix + "PK", classPK);
						}
						else {
							throw new NoSuchUserException(
								"{uuid=" + uuid + "}");
						}
					}
				}
			}
			else if (lowercaseKey.contains("userid")) {
				Object value = jsonObject.get(key);

				if (value instanceof JSONObject) {
					JSONObject columnJSONObject = (JSONObject)value;

					String uuid = columnJSONObject.getString("uuid_");

					String userId = getColumnValue(
						"select userId from OSB_MetricsUser where uuid_ = '" +
							uuid + "'");

					if (userId != null) {
						jsonObject.put(key, userId);
					}
					else {
						throw new NoSuchUserException("{uuid=" + uuid + "}");
					}
				}
			}
		}

		return jsonObject;
	}

	protected void runSQL(
			String schema, String sql, Map<String, Object> columnMap)
		throws Exception {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			Context initialContext = new InitialContext();

			String actualSchema = getSchema(schema);

			DataSource dataSource = (DataSource)initialContext.lookup(
				JDBC_PATH + actualSchema);

			connection = dataSource.getConnection();

			if (isSchemaEmojiSupported(actualSchema)) {
				try (PreparedStatement ps2 = connection.prepareStatement(
						UTF8MB4_QUERY)) {

					ps2.executeUpdate();
				}
			}

			ps = connection.prepareStatement(sql);

			Set<Entry<String, Object>> entrySet = columnMap.entrySet();

			Iterator<Entry<String, Object>> iterator = entrySet.iterator();

			int i = 1;

			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();

				Object columnValue = entry.getValue();

				ps.setObject(i, columnValue);

				i++;
			}

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(connection, ps);
		}
	}

	protected void updateMappingTables(
			String schema, JSONArray jsonArray, String modelName)
		throws Exception {

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject mappingJSONObject = jsonArray.getJSONObject(i);

			String mappingName = mappingJSONObject.getString("name");

			JSONArray mappingValuesJSONArray = mappingJSONObject.getJSONArray(
				"values");

			for (int j = 0; j < mappingValuesJSONArray.length(); j++) {
				JSONObject mappingValuesJSONObject = reconcile(
					mappingValuesJSONArray.getJSONObject(j));

				Map<String, Object> columnMap = getColumnMap(
					mappingValuesJSONObject);

				String sql = buildSql(
					getMappingTableName(modelName, mappingName), columnMap);

				runSQL(schema, sql, columnMap);
			}
		}
	}

	protected void updateTable(
			String schema, JSONObject jsonObject, String modelName)
		throws Exception {

		JSONObject valuesJSONObject = reconcile(
			jsonObject.getJSONObject("values"));

		Map<String, Object> columnMap = getColumnMap(valuesJSONObject);

		String sql = buildSql(getTableName(modelName, schema), columnMap);

		runSQL(schema, sql, columnMap);
	}

	private String _getClassNameId(String value) throws Exception {
		String classNameId = getColumnValue(
			"select classNameId from OSB_MetricsClassName where value = '" +
				value + "'");

		if (classNameId == null) {
			String[] classNameMappings =
				MetricsProcessorConfigurationValues.CLASSNAME_MAPPINGS;

			for (String mapping : classNameMappings) {
				if (mapping.contains(value)) {
					int pos = mapping.indexOf(StringPool.COLON) + 1;

					return _getClassNameId(mapping.substring(pos));
				}
			}

			if (value.contains(".kernel")) {
				value = value.replace(".kernel", StringPool.BLANK);

				classNameId = _getClassNameId(value);
			}
		}

		return classNameId;
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

}