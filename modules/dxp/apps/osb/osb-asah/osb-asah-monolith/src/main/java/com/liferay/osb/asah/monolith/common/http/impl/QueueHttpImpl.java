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

package com.liferay.osb.asah.monolith.common.http.impl;

import com.liferay.osb.asah.common.http.QueueHttp;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
@Primary
public class QueueHttpImpl implements QueueHttp {

	@Override
	public String getMessages(String queueName) {
		return getMessages(queueName, 50);
	}

	public String getMessages(String queueName, long total) {
		Queue<JSONObject> queue = _queues.get(queueName);

		if (queue == null) {
			throw new IllegalArgumentException(
				"There is no queue with name " + queueName);
		}

		JSONArray messagesJSONArray = new JSONArray();

		for (int i = 0; (i < total) && !queue.isEmpty(); i++) {
			messagesJSONArray.put(queue.poll());
		}

		return JSONUtil.put(
			"messages", messagesJSONArray
		).toString();
	}

	public int getMessagesCount(String queueName) {
		Queue<JSONObject> queue = _queues.get(queueName);

		if (queue == null) {
			return 0;
		}

		return queue.size();
	}

	public void initializeQueue() {
	}

	public void pushMessage(String message, String queueName) {
		Queue<JSONObject> queue = _queues.computeIfAbsent(
			queueName, key -> new ConcurrentLinkedQueue<>());

		JSONObject messageJSONObject = JSONUtil.put(
			"message", message
		).put(
			"sentAt", System.currentTimeMillis()
		);

		queue.add(messageJSONObject);
	}

	private final Map<String, Queue<JSONObject>> _queues =
		new ConcurrentHashMap<>();

}