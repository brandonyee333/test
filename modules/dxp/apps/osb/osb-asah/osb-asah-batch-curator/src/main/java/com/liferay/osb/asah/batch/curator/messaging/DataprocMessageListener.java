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

package com.liferay.osb.asah.batch.curator.messaging;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.messaging.MessageListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class DataprocMessageListener implements MessageListener {

	@Override
	public void onMessage(String message) {
		try {
			JSONObject jsonObject = new JSONObject(message);

			List<DataprocMessageProcessor> dataprocMessageProcessors =
				_dataprocMessageProcessors.get(
					jsonObject.optString("applicationId"));

			if (dataprocMessageProcessors == null) {
				_log.error(
					"There are no message processors registered to handle " +
						message);

				return;
			}

			dataprocMessageProcessors.forEach(
				dataprocMessageProcessor -> dataprocMessageProcessor.process(
					jsonObject.getJSONObject("message")));
		}
		catch (Exception exception) {
			_log.error(
				"An error occurred while processing message " + message,
				exception);
		}
	}

	@PostConstruct
	private void _init() {
		_messageBus.registerMessageListener(Channel.DATAPROC, this);

		if (_messageProcessors != null) {
			Stream<DataprocMessageProcessor> stream =
				_messageProcessors.stream();

			_dataprocMessageProcessors = stream.collect(
				Collectors.groupingBy(
					DataprocMessageProcessor::getApplicationId));
		}
	}

	private static final Log _log = LogFactory.getLog(
		DataprocMessageListener.class);

	private Map<String, List<DataprocMessageProcessor>>
		_dataprocMessageProcessors = new HashMap<>();

	@Autowired
	private MessageBus _messageBus;

	@Autowired(required = false)
	private List<DataprocMessageProcessor> _messageProcessors;

}