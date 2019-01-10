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

import com.liferay.portal.kernel.exception.NoSuchClassNameException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

	protected String buildSql(String tableName, Map<String, String> columnMap) {
		StringBundler sb = new StringBundler(6);

		sb.append("replace into ");
		sb.append(tableName);
		sb.append(StringPool.SPACE);

		StringBundler columnNamesSB = new StringBundler(2 * columnMap.size());
		StringBundler columnValueSB = new StringBundler(4 * columnMap.size());

		columnNamesSB.append(StringPool.OPEN_PARENTHESIS);

		columnValueSB.append(StringPool.OPEN_PARENTHESIS);

		Set<Entry<String, String>> entrySet = columnMap.entrySet();

		Iterator<Entry<String, String>> iterator = entrySet.iterator();

		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();

			columnNamesSB.append(entry.getKey());

			columnValueSB.append(StringPool.APOSTROPHE);
			columnValueSB.append(entry.getValue());
			columnValueSB.append(StringPool.APOSTROPHE);

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
		JSONObject modelJSONObject = jsonObject.getJSONObject("model");

		String modelName = modelJSONObject.getString("name");

		updateTable(modelJSONObject, modelName);

		JSONArray mappingsJSONArray = jsonObject.getJSONArray("mappings");

		if (mappingsJSONArray != null) {
			updateMappingTables(mappingsJSONArray, modelName);
		}
	}

	protected JSONObject reconcile(JSONObject jsonObject) throws Exception {
		if (jsonObject.has("classNameId")) {
			ClassName className = _classNameLocalService.getClassName(
				jsonObject.getLong("classNameId"));

			String classNameId = getColumnValue(
				"select classNameId from OSB_MetricsClassName where value = '" +
					className.getValue() + "'");

			if (classNameId != null) {
				jsonObject.put("classNameId", classNameId);
			}
			else {
				throw new NoSuchClassNameException();
			}
		}

		if (jsonObject.has("classPK")) {
			long classNameId = _classNameLocalService.getClassNameId(
				User.class.getName());

			if (classNameId == jsonObject.getLong("classNameId")) {
				JSONObject columnJSONObject = jsonObject.getJSONObject(
					"classPK");

				String classPK = getColumnValue(
					"select userId from OSB_MetricsUser where uuid_ = '" +
						columnJSONObject.getString("uuid_") + "'");

				if (classPK != null) {
					jsonObject.put("classPK", classPK);
				}
				else {
					throw new NoSuchUserException();
				}
			}
		}

		if (jsonObject.has("userId")) {
			JSONObject columnJSONObject = jsonObject.getJSONObject("userId");

			String userId = getColumnValue(
				"select userId from OSB_MetricsUser where uuid_ = '" +
					columnJSONObject.getString("uuid_") + "'");

			if (userId != null) {
				jsonObject.put("userId", userId);
			}
			else {
				throw new NoSuchUserException();
			}
		}

		return jsonObject;
	}

	protected void updateMappingTables(JSONArray jsonArray, String modelName)
		throws Exception {

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject mappingJSONObject = jsonArray.getJSONObject(i);

			String mappingName = mappingJSONObject.getString("name");

			JSONArray mappingValuesJSONArray = mappingJSONObject.getJSONArray(
				"values");

			for (int j = 0; j < mappingValuesJSONArray.length(); j++) {
				JSONObject mappingValuesJSONObject = reconcile(
					mappingValuesJSONArray.getJSONObject(j));

				String sql = buildSql(
					getMappingTableName(modelName, mappingName),
					getColumnMap(mappingValuesJSONObject));

				runSQL(sql);
			}
		}
	}

	protected void updateTable(JSONObject jsonObject, String modelName)
		throws Exception {

		JSONObject valuesJSONObject = reconcile(
			jsonObject.getJSONObject("values"));

		String sql = buildSql(
			getTableName(modelName), getColumnMap(valuesJSONObject));

		runSQL(sql);
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

}