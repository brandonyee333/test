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

package com.liferay.osb.asah.common.spring.aspect;

import com.liferay.osb.asah.common.spring.annotation.Cacheable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Aspect
@Component
public class CacheProcessorAspect {

	@Around(
		"@annotation(com.liferay.osb.asah.common.spring.annotation.Cacheable)"
	)
	public Object processCacheable(ProceedingJoinPoint proceedingJoinPoint)
		throws Throwable {

		if ((_cacheManager == null) || _skipCache(proceedingJoinPoint)) {
			return proceedingJoinPoint.proceed();
		}

		MethodSignature methodSignature =
			(MethodSignature)proceedingJoinPoint.getSignature();

		Method method = methodSignature.getMethod();

		Cacheable cacheable = method.getAnnotation(Cacheable.class);

		Object[] arguments = proceedingJoinPoint.getArgs();

		Object key = _keyGenerator.generate(
			proceedingJoinPoint.getTarget(), method, arguments);

		Cache.ValueWrapper cacheValue = _get(cacheable.cacheName(), key);

		if (cacheValue != null) {
			return cacheValue.get();
		}

		Object resultObject = proceedingJoinPoint.proceed();

		_put(cacheable.cacheName(), key, resultObject);

		return resultObject;
	}

	private Cache.ValueWrapper _get(String cacheName, Object key) {
		Cache cache = _cacheManager.getCache(cacheName);

		if (cache == null) {
			return null;
		}

		return cache.get(key);
	}

	private Map<String, Object> _getParameters(JoinPoint joinPoint) {
		Map<String, Object> parameters = new HashMap<>();

		CodeSignature signature = (CodeSignature)joinPoint.getSignature();

		String[] parameterNames = signature.getParameterNames();
		Object[] arguments = joinPoint.getArgs();

		for (int i = 0; i < parameterNames.length; i++) {
			parameters.put(parameterNames[i], arguments[i]);
		}

		return parameters;
	}

	private void _put(String cacheName, Object key, Object value) {
		Cache cache = _cacheManager.getCache(cacheName);

		if (cache == null) {
			return;
		}

		cache.put(key, value);
	}

	private boolean _skipCache(JoinPoint joinPoint) {
		Map<String, Object> parameters = _getParameters(joinPoint);

		Boolean includeToday = (Boolean)parameters.get("includeToday");

		if ((includeToday != null) && includeToday) {
			return true;
		}

		return false;
	}

	@Autowired(required = false)
	private CacheManager _cacheManager;

	@Autowired
	private KeyGenerator _keyGenerator;

}