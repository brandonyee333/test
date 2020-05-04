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

package com.liferay.osb.asah.test.util.queue.http;

import com.liferay.osb.asah.common.http.QueueHttp;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author Marcellus Tavares
 */
@TestConfiguration
public class CerebroQueueHttpTestConfiguration {

	@Bean
	@Primary
	public QueueHttp cerebroQueueHttp() {
		return new MockQueueHttp();
	}

	private static final class MockQueueHttp implements QueueHttp {

		@Override
		public String getMessages(String queueName) {
			List<JSONObject> messageJSONObjects = new ArrayList<>();

			while (!_messagesQueue.isEmpty()) {
				messageJSONObjects.add(_messagesQueue.poll());
			}

			return JSONUtil.put(
				"messages", new JSONArray(messageJSONObjects)
			).toString();
		}

		@Override
		public String getMessages(String queueName, long total) {
			List<JSONObject> messageJSONObjects = new ArrayList<>();

			int count = 0;

			while ((count < total) && !_messagesQueue.isEmpty()) {
				messageJSONObjects.add(_messagesQueue.poll());

				count++;
			}

			return JSONUtil.put(
				"messages", new JSONArray(messageJSONObjects)
			).toString();
		}

		@Override
		public int getMessagesCount(String queueName) {
			return _messagesQueue.size();
		}

		@Override
		public void initializeQueue() {
		}

		@Override
		public void pushMessage(String message, String queueName) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("message", message);
			jsonObject.put("sentAt", System.currentTimeMillis());

			_messagesQueue.add(jsonObject);
		}

		private final Queue<JSONObject> _messagesQueue = new LinkedList<>();

	}

}