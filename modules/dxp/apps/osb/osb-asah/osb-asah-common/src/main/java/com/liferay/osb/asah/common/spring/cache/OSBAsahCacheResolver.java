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

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

/**
 * @author Inácio Nery
 */
public class OSBAsahCacheResolver implements CacheResolver {

	public OSBAsahCacheResolver(CacheManager cacheManager) {
		_cacheManager = cacheManager;
	}

	@Override
	public Collection<Cache> resolveCaches(
		CacheOperationInvocationContext<?> cacheOperationInvocationContext) {

		Collection<Cache> caches = new ArrayList<>();

		Object target = cacheOperationInvocationContext.getTarget();

		Class<?> clazz = target.getClass();

		caches.add(_cacheManager.getCache(clazz.getName()));

		return caches;
	}

	private final CacheManager _cacheManager;

}