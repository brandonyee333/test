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

import java.net.MalformedURLException;
import java.net.URL;

import java.time.Duration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisPoolingClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Inácio Nery
 * @author Pedro Queiroz
 * @author Shinn Lok
 */
@Configuration
@EnableCaching
public class OSBAsahCachingConfigurerSupport extends CachingConfigurerSupport {

	@Bean
	@Override
	public CacheResolver cacheResolver() {
		if (_cacheEnabled) {
			return new OSBAsahCacheResolver(osbAsahCacheManager());
		}

		return new OSBAsahCacheResolver(new NoOpCacheManager());
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		try {
			URL url = new URL(ServiceConstants.URL_REDIS);

			JedisClientConfigurationBuilder jedisClientConfigurationBuilder =
				JedisClientConfiguration.builder();

			jedisClientConfigurationBuilder.connectTimeout(
				Duration.ofSeconds(10));
			jedisClientConfigurationBuilder.readTimeout(Duration.ofSeconds(10));

			JedisPoolingClientConfigurationBuilder
				jedisPoolingClientConfigurationBuilder =
					jedisClientConfigurationBuilder.usePooling();

			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

			jedisPoolConfig.setMaxTotal(16);
			jedisPoolConfig.setMaxIdle(16);

			jedisPoolingClientConfigurationBuilder.poolConfig(jedisPoolConfig);

			return new JedisConnectionFactory(
				new RedisStandaloneConfiguration(url.getHost(), url.getPort()),
				jedisClientConfigurationBuilder.build());
		}
		catch (MalformedURLException malformedURLException) {
			throw new IllegalArgumentException(malformedURLException);
		}
	}

	@Bean
	public JedisPool jedisPool() {
		JedisConnectionFactory jedisConnectionFactory =
			jedisConnectionFactory();

		return new JedisPool(
			jedisConnectionFactory.getHostName(),
			jedisConnectionFactory.getPort());
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return (target, method, params) -> {
			StringBuilder sb = new StringBuilder();

			Class<?> clazz = target.getClass();

			sb.append(clazz.getSimpleName());

			sb.append("#");
			sb.append(method.getName());
			sb.append("#");
			sb.append(Arrays.deepHashCode(method.getParameterTypes()));
			sb.append("#");
			sb.append(Arrays.deepHashCode(params));

			return sb.toString();
		};
	}

	@Bean
	public OSBAsahCacheManager osbAsahCacheManager() {
		return new OSBAsahCacheManager(redisTemplate());
	}

	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer() {
		RedisMessageListenerContainer redisMessageListenerContainer =
			new RedisMessageListenerContainer();

		redisMessageListenerContainer.setConnectionFactory(
			jedisConnectionFactory());

		OSBAsahCacheMessageListener osbAsahCacheMessageListener =
			new OSBAsahCacheMessageListener(
				osbAsahCacheManager(), redisTemplate());

		redisMessageListenerContainer.addMessageListener(
			osbAsahCacheMessageListener,
			new ChannelTopic("cache:redis:caffeine:topic"));

		return redisMessageListenerContainer;
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate() {
		return new RedisTemplate<Object, Object>() {
			{
				setConnectionFactory(jedisConnectionFactory());
				setEnableTransactionSupport(false);
				setKeySerializer(StringRedisSerializer.UTF_8);
			}
		};
	}

	@Value("${osb.asah.cache.enabled:true}")
	private boolean _cacheEnabled;

}