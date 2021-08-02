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

import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Inácio Nery
 */
public class OSBAsahCacheManager implements CacheManager {

	public OSBAsahCacheManager(RedisTemplate<Object, Object> redisTemplate) {
		_redisTemplate = redisTemplate;
	}

	public com.github.benmanes.caffeine.cache.Cache<Object, Object>
		caffeineCache() {

		Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder();

		caffeineBuilder.expireAfterAccess(5, TimeUnit.SECONDS);

		return caffeineBuilder.build();
	}

	public void clearCaffeineCache(String name, Object key) {
		Cache cache = _caches.get(name);

		if (cache == null) {
			return;
		}

		OSBAsahCache osbAsahCache = (OSBAsahCache)cache;

		osbAsahCache.clearCaffeineCache(key);
	}

	@Override
	public Cache getCache(String name) {
		Cache cache = _caches.get(name);

		if (cache == null) {
			cache = new OSBAsahCache(caffeineCache(), name, _redisTemplate);

			if (_log.isDebugEnabled()) {
				_log.debug("Created OSBAsahCache instance " + name);
			}

			Cache oldCache = _caches.putIfAbsent(name, cache);

			if (oldCache != null) {
				cache = oldCache;
			}
		}

		return cache;
	}

	@Override
	public Collection<String> getCacheNames() {
		return Collections.unmodifiableSet(_caches.keySet());
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahCacheManager.class);

	private final ConcurrentMap<String, Cache> _caches =
		new ConcurrentHashMap<>();
	private final RedisTemplate<Object, Object> _redisTemplate;

}