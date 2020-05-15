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

package com.liferay.osb.asah.common.http.impl;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.http.QueueHttp;
import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Inácio Nery
 */
@Component
@MonolithExclude
public class QueueHttpImpl implements QueueHttp {

	@Override
	public String getMessages(String queueName) {
		return getMessages(queueName, 50);
	}

	@Override
	public String getMessages(String queueName, long total) {
		RestTemplate restTemplate = new RestTemplate();

		StringBuilder sb = new StringBuilder();

		sb.append(ServiceConstants.URL_QUEUE);
		sb.append("/messages/");
		sb.append(queueName);
		sb.append("?total=");
		sb.append(total);

		return restTemplate.getForObject(sb.toString(), String.class);
	}

	@Override
	public int getMessagesCount(String queueName) {
		RestTemplate restTemplate = new RestTemplate();

		String queueJSON = restTemplate.getForObject(
			ServiceConstants.URL_QUEUE + "/queues/" + queueName, String.class);

		JSONObject queueJSONObject = new JSONObject(queueJSON);

		return queueJSONObject.getInt("total");
	}

	@Override
	public void initializeQueue() {
		List<String> queueNames = _getQueueNames();

		if (!queueNames.contains(QUEUE_NAME_ACTIVE_INDIVIDUAL_IDS)) {
			_createQueue(QUEUE_NAME_ACTIVE_INDIVIDUAL_IDS);
		}

		if (!queueNames.contains(QUEUE_NAME_DXP_ENTITIES)) {
			_createQueue(QUEUE_NAME_DXP_ENTITIES);
		}

		if (!queueNames.contains(QUEUE_NAME_IDENTITY)) {
			_createQueue(QUEUE_NAME_IDENTITY);
		}
	}

	@Override
	public void pushMessage(String message, String queueName) {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.postForObject(
			ServiceConstants.URL_QUEUE + "/messages/" + queueName,
			new HashMap<String, Object>() {
				{
					put("delayBeforeRead", 0);
					put("message", message);
				}
			},
			String.class);
	}

	private void _createQueue(String queueName) {
		RestTemplate restTemplate = new RestTemplate();

		String responseJSON = restTemplate.postForObject(
			ServiceConstants.URL_QUEUE + "/queues",
			new HashMap<String, Object>() {
				{
					put("delayAfterRead", 30);
					put("delayBeforeRead", 0);
					put("maxSize", -1);
					put("name", queueName);
				}
			},
			String.class);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Response for creating queue " + queueName + ": " +
					responseJSON);
		}
	}

	private List<String> _getQueueNames() {
		List<String> queueNames = new ArrayList<>();

		RestTemplate restTemplate = new RestTemplate();

		JSONObject resultJSONObject = new JSONObject(
			restTemplate.getForObject(
				ServiceConstants.URL_QUEUE + "/queues", String.class));

		JSONArray queueNamesJSONArray = resultJSONObject.getJSONArray("queues");

		Iterator<Object> iterator = queueNamesJSONArray.iterator();

		while (iterator.hasNext()) {
			String queueName = String.valueOf(iterator.next());

			queueNames.add(queueName);
		}

		return queueNames;
	}

	private static final Log _log = LogFactory.getLog(QueueHttpImpl.class);

}