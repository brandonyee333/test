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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "routing.key=metrics.remove",
	service = MetricsRemoveMessageProcessor.class
)
public class MetricsRemoveMessageProcessor extends BaseMessageProcessor {

	protected String buildSql(
		String table, List<String> columns, List<String> values) {

		StringBundler sb = new StringBundler(3 + ((6 * columns.size()) - 1));

		sb.append("delete from ");
		sb.append(table);
		sb.append(" where ");

		for (int i = 0; i < columns.size(); i++) {
			if (i > 0) {
				sb.append(" and ");
			}

			sb.append(columns.get(i));
			sb.append(" = ");
			sb.append(StringPool.APOSTROPHE);
			sb.append(values.get(i));
			sb.append(StringPool.APOSTROPHE);
		}

		return sb.toString();
	}

	protected void doProcess(JSONObject jsonObject) throws Exception {
		JSONObject tableJSONObject = jsonObject.getJSONObject("table");

		JSONArray tableNamesJSONArray = tableJSONObject.names();

		String table = tableNamesJSONArray.getString(0);

		List<String> columns = new ArrayList<>();
		List<String> values = new ArrayList<>();

		JSONObject modelJSONObject = tableJSONObject.getJSONObject(table);

		Iterator<String> keysIterator = modelJSONObject.keys();

		while (keysIterator.hasNext()) {
			String key = keysIterator.next();

			columns.add(key);
			values.add(modelJSONObject.getString(key));
		}

		String sql = buildSql("OSB_Metrics" + table, columns, values);

		runSQL(sql);

		JSONArray mappingJSONArray = jsonObject.getJSONArray("mapping");

		if (mappingJSONArray != null) {
			for (int i = 0; i < mappingJSONArray.length(); i++) {
				String mappingTable = (String)mappingJSONArray.get(i);

				String mappingTableName = getMappingTableName(
					table, mappingTable);

				sql = buildSql(mappingTableName, columns, values);

				runSQL(sql);
			}
		}
	}

}