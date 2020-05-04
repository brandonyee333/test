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

package com.liferay.osb.asah.common.upgrade.impl;

import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;
import com.liferay.osb.asah.common.upgrade.UpgradeState;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * @author André Miranda
 */
@Component
@MonolithExclude
@Profile("default")
public class UpgradeStateImpl implements UpgradeState {

	@Override
	public void awaitCompletion() {
		Jedis jedis = _jedisPool.getResource();

		jedis.psubscribe(
			new JedisPubSub() {

				@Override
				public void onPMessage(
					String pattern, String channel, String message) {

					punsubscribe();
				}

			},
			"__key*__:UPGRADE_STATE");
	}

	@Override
	public void complete() {
		Jedis jedis = _jedisPool.getResource();

		jedis.set("UPGRADE_STATE", "COMPLETE");
	}

	@Override
	public boolean isComplete() {
		Jedis jedis = _jedisPool.getResource();

		if (Objects.equals(jedis.get("UPGRADE_STATE"), "COMPLETE")) {
			return true;
		}

		return false;
	}

	@Autowired
	private JedisPool _jedisPool;

}