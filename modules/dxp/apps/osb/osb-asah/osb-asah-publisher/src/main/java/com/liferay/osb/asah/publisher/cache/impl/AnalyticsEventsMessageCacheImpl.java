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

import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;
import com.liferay.osb.asah.publisher.cache.AnalyticsEventsMessageCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Shinn Lok
 */
@Component
@MonolithExclude
public class AnalyticsEventsMessageCacheImpl
	implements AnalyticsEventsMessageCache {

	@Override
	public void add(String id) {
		if (id == null) {
			return;
		}

		try (Jedis jedis = _jedisPool.getResource()) {
			jedis.set(id, "", "nx", "ex", 86400);
		}
	}

	@Override
	public boolean has(String id) {
		if (id == null) {
			return false;
		}

		try (Jedis jedis = _jedisPool.getResource()) {
			return jedis.exists(id);
		}
	}

	@Autowired
	private JedisPool _jedisPool;

}