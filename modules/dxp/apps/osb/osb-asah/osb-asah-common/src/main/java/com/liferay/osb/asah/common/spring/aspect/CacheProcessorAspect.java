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

import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.spring.annotation.CacheEvict;
import com.liferay.osb.asah.common.spring.annotation.Cacheable;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.lang.reflect.Method;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

import org.json.JSONObject;

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

		if (_cacheManager == null) {
			return proceedingJoinPoint.proceed();
		}

		Object returnObject = null;

		MethodSignature methodSignature =
			(MethodSignature)proceedingJoinPoint.getSignature();

		Method method = methodSignature.getMethod();

		Map<String, Object> parameters = _getParameters(proceedingJoinPoint);

		if (method.getReturnType() == JSONObject.class) {
			String cacheName = _getCacheName(parameters);

			Object key = _getKey(parameters);

			Cache.ValueWrapper valueWrapper = _get(cacheName, key);

			if (valueWrapper != null) {
				return new JSONObject((String)valueWrapper.get());
			}

			returnObject = proceedingJoinPoint.proceed();

			if (returnObject != null) {
				_put(cacheName, key, returnObject.toString());
			}
		}
		else {
			if (_skipCache(parameters)) {
				return proceedingJoinPoint.proceed();
			}

			Cacheable cacheable = method.getAnnotation(Cacheable.class);

			Object[] arguments = proceedingJoinPoint.getArgs();

			Object key = _keyGenerator.generate(
				proceedingJoinPoint.getTarget(), method, arguments);

			String cacheName = cacheable.cacheName();

			if (StringUtils.isBlank(cacheName)) {
				cacheName = method.getName();
			}

			Cache.ValueWrapper valueWrapper = _get(cacheName, key);

			if (valueWrapper != null) {
				return valueWrapper.get();
			}

			returnObject = proceedingJoinPoint.proceed();

			_put(cacheName, key, returnObject);
		}

		return returnObject;
	}

	@AfterReturning(
		returning = "returnObject",
		value = "@annotation(com.liferay.osb.asah.common.spring.annotation.CacheEvict)"
	)
	public void processCacheEvict(JoinPoint joinPoint, Object returnObject)
		throws Exception {

		if (_cacheManager == null) {
			return;
		}

		MethodSignature methodSignature =
			(MethodSignature)joinPoint.getSignature();

		Method method = methodSignature.getMethod();

		CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);

		if (cacheEvict.evictAll()) {
			_evictAll();

			return;
		}

		if (!ArrayUtils.isEmpty(cacheEvict.value())) {
			if (cacheEvict.allProjects()) {
				ProjectIdThreadLocal.forProjects(
					_projectDog.getProjects(),
					() -> _clear(cacheEvict.value()));
			}
			else {
				_clear(cacheEvict.value());
			}

			return;
		}

		if ((returnObject == null) || !(boolean)returnObject) {
			return;
		}

		Map<String, Object> parameters = _getParameters(joinPoint);

		String cacheName = _getCacheName(parameters);

		if (cacheName == null) {
			return;
		}

		Object key = _getKey(parameters);

		if (key == null) {
			return;
		}

		_evict(cacheName, key);
	}

	@AfterReturning(
		returning = "returnObject",
		value = "@annotation(com.liferay.osb.asah.common.spring.annotation.CachePut)"
	)
	public void processCachePut(JoinPoint joinPoint, Object returnObject) {
		if (_cacheManager == null) {
			return;
		}

		if (!(returnObject instanceof JSONObject)) {
			return;
		}

		Map<String, Object> parameters = _getParameters(joinPoint);

		String cacheName = _getCacheName(parameters);

		if (cacheName == null) {
			return;
		}

		Object key = _getKey(parameters);

		if (key == null) {
			return;
		}

		_put(cacheName, key, String.valueOf(returnObject));
	}

	private void _clear(String[] cacheNames) {
		for (String cacheName : cacheNames) {
			Cache cache = _getCache(cacheName);

			if (cache == null) {
				return;
			}

			cache.clear();

			if (_log.isInfoEnabled()) {
				_log.info("Cache cleared: " + cache.getName());
			}
		}
	}

	private void _evict(String cacheName, Object key) {
		Cache cache = _getCache(cacheName);

		if (cache == null) {
			return;
		}

		cache.evict(key);
	}

	private void _evictAll() {
		for (String cacheName : _cacheManager.getCacheNames()) {
			if (!cacheName.startsWith(
					ProjectIdThreadLocal.getProjectId() + "#")) {

				continue;
			}

			Cache cache = _cacheManager.getCache(cacheName);

			if (cache != null) {
				cache.clear();
			}
		}
	}

	private Cache.ValueWrapper _get(String cacheName, Object cacheKey) {
		Cache cache = _getCache(cacheName);

		if (cache == null) {
			return null;
		}

		return cache.get(cacheKey);
	}

	private Cache _getCache(String cacheName) {
		return _cacheManager.getCache(
			ProjectIdThreadLocal.getProjectId() + "#" + cacheName);
	}

	private String _getCacheName(Map<String, Object> parameters) {
		return (String)parameters.get("collectionName");
	}

	private Object _getKey(Map<String, Object> parameters) {
		String key = (String)parameters.get("id");

		if (key == null) {
			JSONObject jsonObject = (JSONObject)parameters.get("jsonObject");

			if (jsonObject == null) {
				return null;
			}

			key = jsonObject.getString("id");
		}

		return key;
	}

	private Map<String, Object> _getParameters(JoinPoint joinPoint) {
		Map<String, Object> parameters = new HashMap<>();

		CodeSignature codeSignature = (CodeSignature)joinPoint.getSignature();

		String[] parameterNames = codeSignature.getParameterNames();

		Object[] arguments = joinPoint.getArgs();

		for (int i = 0; i < parameterNames.length; i++) {
			parameters.put(parameterNames[i], arguments[i]);
		}

		return parameters;
	}

	private void _put(String cacheName, Object key, Object value) {
		Cache cache = _getCache(cacheName);

		if (cache == null) {
			return;
		}

		cache.put(key, value);
	}

	private boolean _skipCache(Map<String, Object> parameters) {
		Boolean includeToday = (Boolean)parameters.get("includeToday");

		if ((includeToday != null) && includeToday) {
			return true;
		}

		String query = (String)parameters.get("query");

		if (query == null) {
			return false;
		}

		Map<String, Object> variables = (Map<String, Object>)parameters.get(
			"variables");

		if (variables == null) {
			return true;
		}

		if (!query.startsWith("{pagesCount") &&
			!query.startsWith("query CustomAssetsList") &&
			!query.startsWith("query IndividualMetrics")) {

			JSONObject variablesJSONObject = new JSONObject(variables);

			if (variablesJSONObject.has("rangeEnd")) {
				LocalDate currentLocalDate = LocalDate.now();
				LocalDate rangeEndLocalDate = LocalDate.parse(
					variablesJSONObject.getString("rangeEnd"));

				return currentLocalDate.isEqual(rangeEndLocalDate);
			}

			if (variablesJSONObject.optInt("rangeKey") > 0) {
				return false;
			}
		}

		return true;
	}

	private static final Log _log = LogFactory.getLog(
		CacheProcessorAspect.class);

	@Autowired(required = false)
	private CacheManager _cacheManager;

	@Autowired
	private KeyGenerator _keyGenerator;

	@Autowired
	private ProjectDog _projectDog;

}