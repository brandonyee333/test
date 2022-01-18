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

package com.liferay.osb.asah.publisher.cache;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

/**
 * @author Inácio Nery
 */
@Component
public class AnalyticsEventsMessageCache {

	public boolean add(String id) {
		if (id == null) {
			return true;
		}

		try (Jedis jedis = _jedisPool.getResource()) {
			String status = jedis.set(
				ProjectIdThreadLocal.getProjectId() + "#" + id, "0",
				_setParams);

			if (status == null) {
				return false;
			}

			return true;
		}
	}

	public void remove(String id) {
		if (id == null) {
			return;
		}

		try (Jedis jedis = _jedisPool.getResource()) {
			jedis.del(ProjectIdThreadLocal.getProjectId() + "#" + id);
		}
	}

	@PostConstruct
	private void _init() {
		_setParams.nx();

		_setParams.px(DateUtil.DAY * 7);
	}

	@Autowired
	private JedisPool _jedisPool;

	private final SetParams _setParams = SetParams.setParams();

}