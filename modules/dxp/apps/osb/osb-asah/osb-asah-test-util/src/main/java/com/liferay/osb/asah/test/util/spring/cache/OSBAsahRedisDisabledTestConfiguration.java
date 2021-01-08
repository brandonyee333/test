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

package com.liferay.osb.asah.test.util.spring.cache;

import org.mockito.Mockito;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import redis.clients.jedis.JedisPool;

/**
 * @author Rachael Koestartyo
 */
@TestConfiguration
public class OSBAsahRedisDisabledTestConfiguration {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		return Mockito.mock(JedisConnectionFactory.class);
	}

	@Bean
	@Primary
	public JedisPool jedisPool() {
		return Mockito.mock(JedisPool.class);
	}

	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer() {
		return Mockito.mock(RedisMessageListenerContainer.class);
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate() {
		return Mockito.mock(RedisTemplate.class);
	}

}