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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		String modelName, Map<String, Object> columnMap) {

		JSONObject valuesJSONObject = JSONFactoryUtil.createJSONObject();

		for (Map.Entry<String, Object> entry : columnMap.entrySet()) {
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

	protected Map<String, Object> insertColumnMapValue(
			Map<String, Object> columnMap, JSONObject jsonObject, String key,
			Object value)
		throws ParseException {

		if (value instanceof JSONArray || value instanceof JSONObject) {
			columnMap.put(key, String.valueOf(value));
		}
		else {
			if (_isDate(key) && !jsonObject.isNull(key)) {
				value = _formatDate(String.valueOf(value));
			}

			columnMap.put(key, value);
		}

		return columnMap;
	}

	private String _formatDate(String value) throws ParseException {
		SimpleDateFormat sourceFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
		SimpleDateFormat targetFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

		return targetFormat.format(sourceFormat.parse(value));
	}

	private boolean _isDate(String key) {
		if (ArrayUtil.contains(_DATE_FIELDS, key)) {
			return true;
		}

		return false;
	}

	private static final String[] _DATE_FIELDS = {
		"created_at", "deleted_at", "edited_at", "last_login_at", "updated_at"
	};

	private static final Log _log = LogFactoryUtil.getLog(
		BaseTransformer.class);

}