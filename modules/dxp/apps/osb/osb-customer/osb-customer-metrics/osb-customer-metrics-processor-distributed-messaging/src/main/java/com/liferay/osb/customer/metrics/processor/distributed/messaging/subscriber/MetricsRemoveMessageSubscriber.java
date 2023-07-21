/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.processor.distributed.messaging.subscriber;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=metrics.remove",
	service = MetricsRemoveMessageSubscriber.class
)
public class MetricsRemoveMessageSubscriber extends BaseMessageSubscriber {

	protected String buildSql(String tableName, Map<String, Object> columnMap) {
		StringBundler sb = new StringBundler(3 + ((6 * columnMap.size()) - 1));

		sb.append("delete from ");
		sb.append(tableName);
		sb.append(" where ");

		Set<Map.Entry<String, Object>> entrySet = columnMap.entrySet();

		Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();

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
		String schema = jsonObject.getString("schema");

		JSONObject modelJSONObject = jsonObject.getJSONObject("model");

		String modelName = modelJSONObject.getString("name");

		Map<String, Object> columnMap = getColumnMap(
			modelJSONObject.getJSONObject("values"));

		String sql = buildSql(getTableName(modelName, schema), columnMap);

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