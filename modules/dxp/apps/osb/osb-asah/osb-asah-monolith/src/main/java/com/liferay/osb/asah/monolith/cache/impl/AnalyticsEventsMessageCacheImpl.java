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

import com.liferay.osb.asah.publisher.cache.AnalyticsEventsMessageCache;

import java.util.Queue;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
public class AnalyticsEventsMessageCacheImpl
	implements AnalyticsEventsMessageCache {

	@Override
	public void add(String id) {
		if (id == null) {
			return;
		}

		_queue.add(id);
	}

	@Override
	public boolean has(String id) {
		if (id == null) {
			return false;
		}

		return _queue.contains(id);
	}

	private final Queue<String> _queue = new CircularFifoQueue<>(1000000);

}