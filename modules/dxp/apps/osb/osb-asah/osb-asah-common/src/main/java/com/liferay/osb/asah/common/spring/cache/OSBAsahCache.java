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

import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Inácio Nery
 */
public class OSBAsahCache extends AbstractValueAdaptingCache {

	public OSBAsahCache(
		Cache caffeineCache, String name, Cache redisCache,
		RedisTemplate<Object, Object> redisTemplate) {

		super(false);

		_caffeineCache = caffeineCache;
		_name = name;
		_redisCache = redisCache;
		_redisTemplate = redisTemplate;
	}

	@Override
	public void clear() {
		_clearRedisCache(null);

		clearCaffeineCache(null);
	}

	@Override
	public void evict(Object key) {
		_clearRedisCache(key);

		clearCaffeineCache(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized <T> T get(Object key, Callable<T> callable) {
		Object value = lookup(key);

		if (value != null) {
			return (T)value;
		}

		try {
			value = callable.call();
		}
		catch (Throwable throwable) {
			throw new ValueRetrievalException(key, callable, throwable);
		}

		put(key, value);

		return (T)value;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public Object getNativeCache() {
		return this;
	}

	@Override
	public void put(Object key, Object value) {
		if (!super.isAllowNullValues() && (value == null)) {
			evict(key);

			return;
		}

		_put(key, value);
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		ValueWrapper valueWrapper = toValueWrapper(lookup(key));

		if (valueWrapper == null) {
			_put(key, value);
		}

		return valueWrapper;
	}

	protected void clearCaffeineCache(Object key) {
		if (key == null) {
			_caffeineCache.clear();

			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"Caffeine cache with name %s cleared", _name));
			}
		}
		else {
			_caffeineCache.evict(key);

			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"Caffeine cache with name %s and key %s cleared", _name,
						key));
			}
		}
	}

	@Override
	protected Object lookup(Object key) {
		ValueWrapper valueWrapper = _caffeineCache.get(key);

		if (valueWrapper != null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"Got value %s from Caffeine with name %s and key %s",
						valueWrapper.get(), _name, key));
			}

			return valueWrapper.get();
		}

		valueWrapper = _redisCache.get(key);

		if (valueWrapper != null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"Got value %s from Redis with name %s and key %s",
						valueWrapper.get(), _name, key));
			}

			_caffeineCache.put(key, valueWrapper.get());

			return valueWrapper.get();
		}

		return null;
	}

	private void _clearRedisCache(Object key) {
		if (key == null) {
			_redisCache.clear();

			_sendRedisMessage(new OSBAsahCacheMessage(_name, null));

			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format("Redis cache with name %s cleared", _name));
			}
		}
		else {
			_redisCache.evict(key);

			_sendRedisMessage(new OSBAsahCacheMessage(_name, key));

			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"Redis cache with name %s and key %s cleared", _name,
						key));
			}
		}
	}

	private void _put(Object key, Object value) {
		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Put cache with name %s, key %s and value %s", _name, key,
					value));
		}

		_redisCache.put(key, value);

		_sendRedisMessage(new OSBAsahCacheMessage(_name, key));

		_caffeineCache.put(key, value);
	}

	private void _sendRedisMessage(OSBAsahCacheMessage osbAsahCacheMessage) {
		_redisTemplate.convertAndSend(
			"cache:redis:caffeine:topic", osbAsahCacheMessage);
	}

	private static final Log _log = LogFactory.getLog(OSBAsahCache.class);

	private final Cache _caffeineCache;
	private final String _name;
	private final Cache _redisCache;
	private final RedisTemplate<Object, Object> _redisTemplate;

}