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