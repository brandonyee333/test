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

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

	protected String buildSql(
		String table, Map<String, String> columnValuePair) {

		StringBundler sb = new StringBundler(6);
		StringBundler columnSB = new StringBundler(2 * columnValuePair.size());
		StringBundler valueSB = new StringBundler(4 * columnValuePair.size());

		sb.append("replace into ");
		sb.append(table);
		sb.append(StringPool.SPACE);

		columnSB.append(StringPool.OPEN_PARENTHESIS);

		valueSB.append(StringPool.OPEN_PARENTHESIS);

		for (Map.Entry<String, String> entrySet : columnValuePair.entrySet()) {
			columnSB.append(entrySet.getKey());

			valueSB.append(StringPool.APOSTROPHE);
			valueSB.append(entrySet.getValue());
			valueSB.append(StringPool.APOSTROPHE);

			if (columnSB.index() < (columnSB.capacity() - 1)) {
				columnSB.append(StringPool.COMMA);

				valueSB.append(StringPool.COMMA);
			}
		}

		columnSB.append(StringPool.CLOSE_PARENTHESIS);

		valueSB.append(StringPool.CLOSE_PARENTHESIS);

		sb.append(columnSB.toString());
		sb.append(" values ");
		sb.append(valueSB.toString());

		return sb.toString();
	}

	protected void doProcess(JSONObject jsonObject) throws Exception {
		JSONObject tableJSONObject = jsonObject.getJSONObject("table");

		JSONArray tableNamesJSONArray = tableJSONObject.names();

		String table = tableNamesJSONArray.getString(0);

		updateTable(tableJSONObject, table);

		JSONObject mappingJSONObject = jsonObject.getJSONObject("mapping");

		if (mappingJSONObject != null) {
			updateMappingTables(mappingJSONObject, table);
		}
	}

	protected Map<String, String> getColumnValuePairs(JSONObject jsonObject) {
		Map<String, String> columnValuePairs = new HashMap<>();

		Iterator<String> keysIterator = jsonObject.keys();

		while (keysIterator.hasNext())	 {
			String column = keysIterator.next();

			String value = jsonObject.getString(column);

			columnValuePairs.put(column, value);
		}

		return columnValuePairs;
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

	protected void updateMappingTables(JSONObject jsonObject, String table)
		throws Exception {

		Iterator<String> keysIterator = jsonObject.keys();

		while (keysIterator.hasNext()) {
			String key = keysIterator.next();

			JSONArray mappingTableJSONArray = jsonObject.getJSONArray(key);

			for (int i = 0; i < mappingTableJSONArray.length(); i++) {
				JSONObject mappingTableJSONObject = reconcile(
					mappingTableJSONArray.getJSONObject(i));

				String sql = buildSql(
					getMappingTableName(table, key),
					getColumnValuePairs(mappingTableJSONObject));

				runSQL(sql);
			}
		}
	}

	protected void updateTable(JSONObject jsonObject, String table)
		throws Exception {

		JSONObject tableJSONObject = reconcile(jsonObject.getJSONObject(table));

		String sql = buildSql(
			"OSB_Metrics" + table, getColumnValuePairs(tableJSONObject));

		runSQL(sql);
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

}