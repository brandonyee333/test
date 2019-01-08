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

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "routing.key=metrics.drop",
	service = MetricsDropMessageProcessor.class
)
public class MetricsDropMessageProcessor extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		JSONObject tableJSONObject = jsonObject.getJSONObject("table");

		String table = tableJSONObject.getString("name");

		runSQL("truncate table OSB_Metrics" + table);

		JSONObject mappingJSONObject = jsonObject.getJSONObject("mapping");

		if (mappingJSONObject != null) {
			JSONArray mappingNamesJSONArray = mappingJSONObject.getJSONArray(
				"names");

			for (int i = 0; i < mappingNamesJSONArray.length(); i++) {
				String mappingTable = (String)mappingNamesJSONArray.get(i);

				runSQL(
					"truncate table " +
						getMappingTableName(table, mappingTable));
			}
		}
	}

}