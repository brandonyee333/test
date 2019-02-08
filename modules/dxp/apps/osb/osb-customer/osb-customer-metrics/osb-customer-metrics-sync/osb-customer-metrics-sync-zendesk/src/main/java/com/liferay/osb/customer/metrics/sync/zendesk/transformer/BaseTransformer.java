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

package com.liferay.osb.customer.metrics.sync.zendesk.transformer;

import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Kyle Bischof
 */
public abstract class BaseTransformer implements MessageProcessor {

	@Override
	public void process(
		String routingKey, String message, Map<String, Object> properties) {

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				message.trim());

			doProcess(jsonObject);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected JSONObject buildMetricsJSONObject(
		String modelName, Map<String, String> columnMap) {

		JSONObject valuesJSONObject = JSONFactoryUtil.createJSONObject();

		for (Map.Entry<String, String> entry : columnMap.entrySet()) {
			valuesJSONObject.put(entry.getKey(), entry.getValue());
		}

		JSONObject modelJSONObject = JSONFactoryUtil.createJSONObject();

		modelJSONObject.put("name", modelName);
		modelJSONObject.put("values", valuesJSONObject);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("model", modelJSONObject);

		return jsonObject;
	}

	protected abstract void doProcess(JSONObject jsonObject) throws Exception;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseTransformer.class);

}