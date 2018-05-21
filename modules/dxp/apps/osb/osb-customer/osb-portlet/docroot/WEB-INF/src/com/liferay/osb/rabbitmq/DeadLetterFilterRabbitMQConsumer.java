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

package com.liferay.osb.rabbitmq;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/* TODO update rabbitMQ integration
import com.liferay.rabbitmq.consumer.RabbitMQConsumer;
*/

/**
 * @author Amos Fong
 */
public class DeadLetterFilterRabbitMQConsumer {

	// implements RabbitMQConsumer {

	public DeadLetterFilterRabbitMQConsumer(String script) {
		_script = script;
	}

	/* TODO update rabbitMQ integration
	public int parse(
		String routingKey, String message, Map<String, Object> properties) {

		JSONObject jsonObject = null;

		try {
			jsonObject = JSONFactoryUtil.createJSONObject(m essage);
		}
		catch (JSONException jsone) {
			if (_log.isWarnEnabled()) {
				_log.warn("Improper message: " + message);
			}

			return RESPONSE_ACK;
		}

		try {
			Map<String, Object> inputObjects = new HashMap<>();

			inputObjects.put("messageJSONObject", jsonObject);
			inputObjects.put("properties", properties);
			inputObjects.put("routingKey", routingKey);

			Set<String> outputNames = new HashSet<>();

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

	private final String _script;

}