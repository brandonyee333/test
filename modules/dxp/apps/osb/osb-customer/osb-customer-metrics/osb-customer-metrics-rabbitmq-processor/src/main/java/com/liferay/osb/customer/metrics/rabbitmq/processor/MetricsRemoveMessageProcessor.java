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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "routing.key=metrics.remove",
	service = MetricsRemoveMessageProcessor.class
)
public class MetricsRemoveMessageProcessor extends BaseMessageProcessor {

	protected String buildSql(String tableName, Map<String, Object> columnMap) {
		StringBundler sb = new StringBundler(3 + ((6 * columnMap.size()) - 1));

		sb.append("delete from ");
		sb.append(tableName);
		sb.append(" where ");

		Set<Entry<String, Object>> entrySet = columnMap.entrySet();

		Iterator<Entry<String, Object>> iterator = entrySet.iterator();

		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();

			sb.append(entry.getKey());

			sb.append(" = '");
			sb.append(entry.getValue());
			sb.append(StringPool.APOSTROPHE);

			if (iterator.hasNext()) {
				sb.append(" and ");
			}
		}

		return sb.toString();
	}

	protected void doProcess(JSONObject jsonObject) throws Exception {
		JSONObject modelJSONObject = jsonObject.getJSONObject("model");

		String modelName = modelJSONObject.getString("name");

		Map<String, Object> columnMap = getColumnMap(
			modelJSONObject.getJSONObject("values"));

		String schema = jsonObject.getString("schema");

		String sql = buildSql(getTableName(modelName), columnMap);

		runSQL(schema, sql);

		JSONArray mappingsJSONArray = jsonObject.getJSONArray("mappings");

		if (mappingsJSONArray != null) {
			for (int i = 0; i < mappingsJSONArray.length(); i++) {
				JSONObject mappingJSONObject = mappingsJSONArray.getJSONObject(
					i);

				String mappingName = mappingJSONObject.getString("name");

				String mappingTableName = getMappingTableName(
					modelName, mappingName);

				String mappingSql = buildSql(mappingTableName, columnMap);

				runSQL(schema, mappingSql);
			}
		}
	}

}