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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author Inácio Nery
 */
public class OSBAsahCacheMessageListener implements MessageListener {

	public OSBAsahCacheMessageListener(
		OSBAsahCacheManager osbAsahCacheManager,
		RedisTemplate<Object, Object> redisTemplate) {

		_osbAsahCacheManager = osbAsahCacheManager;
		_redisTemplate = redisTemplate;
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		RedisSerializer<?> redisSerializer =
			_redisTemplate.getValueSerializer();

		OSBAsahCacheMessage osbAsahCacheMessage =
			(OSBAsahCacheMessage)redisSerializer.deserialize(message.getBody());

		if (osbAsahCacheMessage == null) {
			throw new IllegalStateException("OSBAsahCacheMessage is null");
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Clear Caffeine cache, the name is %s and the key is %s",
					osbAsahCacheMessage.getName(),
					osbAsahCacheMessage.getKey()));
		}

		_osbAsahCacheManager.clearCaffeineCache(
			osbAsahCacheMessage.getName(), osbAsahCacheMessage.getKey());
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahCacheMessageListener.class);

	private final OSBAsahCacheManager _osbAsahCacheManager;
	private final RedisTemplate<Object, Object> _redisTemplate;

}