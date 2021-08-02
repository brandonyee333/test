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

import com.github.benmanes.caffeine.cache.Cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author Inácio Nery
 */
public class OSBAsahCache extends AbstractValueAdaptingCache {

	public OSBAsahCache(
		Cache<Object, Object> caffeineCache, String name,
		RedisTemplate<Object, Object> redisTemplate) {

		super(false);

		_caffeineCache = caffeineCache;
		_name = name;
		_redisTemplate = redisTemplate;
	}

	@Override
	public void clear() {
		clearRedisCache(null);

		clearCaffeineCache(null);
	}

	public void clearCaffeineCache(Object key) {
		if (key == null) {
			_caffeineCache.invalidateAll();

			if (_log.isDebugEnabled()) {
				_log.debug("Caffeine cache cleared");
			}
		}
		else {
			_caffeineCache.invalidate(key);

			if (_log.isDebugEnabled()) {
				_log.debug(String.format("Caffeine cache key %s cleared", key));
			}
		}
	}

	public void clearRedisCache(Object key) {
		if (key == null) {
			Set<Object> keys = _redisTemplate.keys(_name.concat(":*"));

			if (keys != null) {
				_redisTemplate.delete(keys);
			}

			_push(new OSBAsahCacheMessage(_name, null));

			if (_log.isDebugEnabled()) {
				_log.debug("Redis cache cleared");
			}
		}
		else {
			_redisTemplate.delete(_getRedisKey(key));

			_push(new OSBAsahCacheMessage(_name, key));

			if (_log.isDebugEnabled()) {
				_log.debug(String.format("Redis cache key %s cleared", key));
			}
		}
	}

	@Override
	public void evict(Object key) {
		clearRedisCache(key);

		clearCaffeineCache(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Object key, Callable<T> callable) {
		Object value = lookup(key);

		if (value != null) {
			return (T)value;
		}

		ReentrantLock reentrantLock = _reentrantLocks.computeIfAbsent(
			key.toString(),
			newKey -> {
				if (_log.isTraceEnabled()) {
					_log.trace("Created lock for cache key " + newKey);
				}

				return new ReentrantLock();
			});

		try {
			reentrantLock.lock();

			value = lookup(key);

			if (value != null) {
				return (T)value;
			}

			value = callable.call();

			put(key, toStoreValue(value));

			return (T)value;
		}
		catch (Exception exception) {
			throw new ValueRetrievalException(
				key, callable, exception.getCause());
		}
		finally {
			reentrantLock.unlock();
		}
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

		ValueOperations<Object, Object> valueOperations =
			_redisTemplate.opsForValue();

		Object storeValue = toStoreValue(value);

		valueOperations.set(_getRedisKey(key), storeValue);

		_push(new OSBAsahCacheMessage(_name, key));

		_caffeineCache.put(key, storeValue);
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		Object oldValue = null;

		synchronized (key) {
			Object redisKey = _getRedisKey(key);

			ValueOperations<Object, Object> valueOperations =
				_redisTemplate.opsForValue();

			oldValue = valueOperations.get(redisKey);

			if (oldValue == null) {
				Object storeValue = toStoreValue(value);

				valueOperations.set(redisKey, storeValue);

				_push(new OSBAsahCacheMessage(_name, key));

				_caffeineCache.put(key, storeValue);
			}
		}

		return toValueWrapper(oldValue);
	}

	@Override
	protected Object lookup(Object key) {
		Object value = _caffeineCache.getIfPresent(key);

		if (value != null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Got value from Caffeine with cache key " + key);
			}

			return value;
		}

		Object redisKey = _getRedisKey(key);

		ValueOperations<Object, Object> valueOperations =
			_redisTemplate.opsForValue();

		value = valueOperations.get(redisKey);

		if (value != null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Got value from Redis with cache key " + key);
			}

			_caffeineCache.put(key, toStoreValue(value));
		}

		return value;
	}

	private Object _getRedisKey(Object key) {
		return _name.concat(
			":"
		).concat(
			key.toString()
		);
	}

	private void _push(OSBAsahCacheMessage osbAsahCacheMessage) {
		_redisTemplate.convertAndSend(
			"cache:redis:caffeine:topic", osbAsahCacheMessage);
	}

	private static final Log _log = LogFactory.getLog(OSBAsahCache.class);

	private final Cache<Object, Object> _caffeineCache;
	private final String _name;
	private final RedisTemplate<Object, Object> _redisTemplate;
	private final Map<String, ReentrantLock> _reentrantLocks =
		new ConcurrentHashMap<>();

}