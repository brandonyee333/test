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

package com.liferay.osb.asah.monolith.cache.impl;

import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.publisher.cache.AnalyticsEventsMessageCache;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
public class AnalyticsEventsMessageCacheImpl
	implements AnalyticsEventsMessageCache {

	@Override
	public boolean add(String id) {
		if (id == null) {
			return true;
		}

		Queue<String> queue = _getQueue();

		if (queue.contains(id)) {
			return false;
		}

		queue.add(id);

		return true;
	}

	public boolean contains(String id) {
		Queue<String> queue = _getQueue();

		return queue.contains(id);
	}

	@Override
	public void remove(String id) {
		if (id == null) {
			return;
		}

		Queue<String> queue = _getQueue();

		queue.remove(id);
	}

	private Queue<String> _getQueue() {
		Queue<String> queue = _queues.get(ProjectIdThreadLocal.getProjectId());

		if (queue == null) {
			queue = new CircularFifoQueue<>(1000000);

			_queues.put(ProjectIdThreadLocal.getProjectId(), queue);
		}

		return queue;
	}

	private final Map<String, Queue<String>> _queues =
		new ConcurrentHashMap<>();

}