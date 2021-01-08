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

package com.liferay.osb.asah.common.spring.cache;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;

import java.net.URL;

import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import redis.clients.jedis.JedisPool;

/**
 * @author Pedro Queiroz
 * @author Shinn Lok
 */
@Configuration
@EnableCaching
@MonolithExclude
@Profile("!test")
public class OSBAsahRedisConfiguration {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() throws Exception {
		JedisConnectionFactory jedisConnectionFactory =
			new JedisConnectionFactory();

		URL url = new URL(ServiceConstants.URL_REDIS);

		jedisConnectionFactory.setHostName(url.getHost());
		jedisConnectionFactory.setPort(url.getPort());

		return jedisConnectionFactory;
	}

	@Bean
	public JedisPool jedisPool() throws Exception {
		JedisConnectionFactory jedisConnectionFactory =
			jedisConnectionFactory();

		return new JedisPool(
			jedisConnectionFactory.getHostName(),
			jedisConnectionFactory.getPort());
	}

	@Bean
	@Profile("!default")
	public HealthIndicator redisHealthIndicator() {
		return () -> null;
	}

	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer()
		throws Exception {

		RedisMessageListenerContainer redisMessageListenerContainer =
			new RedisMessageListenerContainer();

		redisMessageListenerContainer.setConnectionFactory(
			jedisConnectionFactory());

		return redisMessageListenerContainer;
	}

	@Bean
	public RedisSerializer<?> redisSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate() throws Exception {
		return new RedisTemplate<Object, Object>() {
			{
				setConnectionFactory(jedisConnectionFactory());
				setDefaultSerializer(redisSerializer());
				setEnableTransactionSupport(false);
			}
		};
	}

}