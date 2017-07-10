/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.rabbitmq;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.util.GetterUtil;
/* TODO update rabbitMQ integration
import com.liferay.rabbitmq.consumer.RabbitMQConsumer;
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class DeadLetterFilterRabbitMQConsumer { // implements RabbitMQConsumer {

	public DeadLetterFilterRabbitMQConsumer(String script) {
		_script = script;
	}
/* TODO update rabbitMQ integration
	public int parse(
		String routingKey, String message, Map<String, Object> properties) {

		JSONObject jsonObject = null;

		try {
			jsonObject = JSONFactoryUtil.createJSONObject(m	essage);
		}
		catch (JSONException jsone) {
			if (_log.isWarnEnabled()) {
				_log.warn("Improper message: " + message);
			}

			return RESPONSE_ACK;
		}

		try {
			Map<String, Object> inputObjects = new HashMap<String, Object>();

			inputObjects.put("messageJSONObject", jsonObject);
			inputObjects.put("properties", properties);
			inputObjects.put("routingKey", routingKey);

			Set<String> outputNames = new HashSet<String>();

			outputNames.add("response");

			Map<String, Object> output = ScriptingUtil.eval(
				null, inputObjects, outputNames, "groovy", _script);

			int response = GetterUtil.getInteger(output.get("response"));

			if (ArrayUtil.contains(RESPONSES, response)) {
				return response;
			}

			_log.error("Invalid response " + response);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return RESPONSE_REPUBLISH;
	}
*/
	private static Log _log = LogFactoryUtil.getLog(
		DeadLetterFilterRabbitMQConsumer.class);

	private static String _script;

}