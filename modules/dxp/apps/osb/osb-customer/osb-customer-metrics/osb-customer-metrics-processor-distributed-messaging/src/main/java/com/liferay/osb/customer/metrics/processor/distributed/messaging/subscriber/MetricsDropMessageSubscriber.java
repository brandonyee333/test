/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.processor.distributed.messaging.subscriber;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=metrics.drop",
	service = MetricsDropMessageSubscriber.class
)
public class MetricsDropMessageSubscriber extends BaseMessageSubscriber {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		String schema = jsonObject.getString("schema");

		JSONObject modelJSONObject = jsonObject.getJSONObject("model");

		String modelName = modelJSONObject.getString("name");

		runSQL(schema, "truncate table " + getTableName(modelName, schema));

		JSONArray mappingsJSONArray = jsonObject.getJSONArray("mappings");

		if (mappingsJSONArray != null) {
			for (int i = 0; i < mappingsJSONArray.length(); i++) {
				JSONObject mappingJSONObject = mappingsJSONArray.getJSONObject(
					i);

				String mappingName = mappingJSONObject.getString("name");

				runSQL(
					schema,
					"truncate table " +
						getMappingTableName(modelName, mappingName));
			}
		}
	}

}