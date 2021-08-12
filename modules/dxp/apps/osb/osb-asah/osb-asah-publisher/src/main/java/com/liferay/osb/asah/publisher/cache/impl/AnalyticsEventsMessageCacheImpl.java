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

package com.liferay.osb.asah.publisher.cache.impl;

import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.publisher.cache.AnalyticsEventsMessageCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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

		try (Jedis jedis = _jedisPool.getResource()) {
			if (jedis.sadd(ProjectIdThreadLocal.getProjectId(), id) == 0) {
				return false;
			}

			return true;
		}
	}

	@Override
	public void remove(String id) {
		if (id == null) {
			return;
		}

		try (Jedis jedis = _jedisPool.getResource()) {
			jedis.srem(ProjectIdThreadLocal.getProjectId(), id);
		}
	}

	@Autowired
	private JedisPool _jedisPool;

}