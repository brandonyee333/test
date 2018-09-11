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

package com.liferay.lcs.task;

import com.liferay.lcs.management.MBeanServerService;
import com.liferay.lcs.management.ObjectNameKeyPropertyMapKeyStrategy;
import com.liferay.lcs.messaging.CacheMetricsMessage;
import com.liferay.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.ObjectName;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class CacheMetricsTask extends BaseScheduledTask {

	@Override
	public Scope getScope() {
		return Scope.NODE;
	}

	public void setmBeanServerService(MBeanServerService mBeanServerService) {
		_mBeanServerService = mBeanServerService;
	}

	protected void doRun() throws Exception {
		if (_log.isTraceEnabled()) {
			_log.trace("Running cache metrics task");
		}

		CacheMetricsMessage cacheMetricsMessage = new CacheMetricsMessage();

		cacheMetricsMessage.setCreateTime(System.currentTimeMillis());
		cacheMetricsMessage.setHibernateMetrics(getHibernateMetrics());
		cacheMetricsMessage.setKey(getKey());
		cacheMetricsMessage.setLiferayMultiVMMetrics(
			getLiferayMultiVMMetrics());
		cacheMetricsMessage.setLiferaySingleVMMetrics(
			getLiferaySingleVMMetrics());

		sendMessage(cacheMetricsMessage);
	}

	protected Map<String, Object> getHibernateMetrics() throws Exception {
		Map<String, Object> map = new HashMap<>();

		Object sessionFactory = PortalBeanLocatorUtil.locate(
			"liferayHibernateSessionFactory");

		Class<?> sessionFactoryClass = sessionFactory.getClass();

		Method getStatisticsMethod = sessionFactoryClass.getDeclaredMethod(
			"getStatistics");

		Object statistics = getStatisticsMethod.invoke(sessionFactory);

		Class<?> statisticsClass = statistics.getClass();

		Field queryCacheHitCountField = statisticsClass.getDeclaredField(
			"queryCacheHitCount");

		queryCacheHitCountField.setAccessible(true);

		AtomicLong queryCacheHitCountAtomicLong =
			(AtomicLong)queryCacheHitCountField.get(statistics);

		map.put("QueryCacheHitCount", queryCacheHitCountAtomicLong.get());

		Field queryCacheMissCountField = statisticsClass.getDeclaredField(
			"queryCacheMissCount");

		queryCacheMissCountField.setAccessible(true);

		AtomicLong queryCacheMissCountAtomicLong =
			(AtomicLong)queryCacheMissCountField.get(statistics);

		map.put("QueryCacheMissCount", queryCacheMissCountAtomicLong.get());

		Field queryExecutionCountField = statisticsClass.getDeclaredField(
			"queryExecutionCount");

		queryExecutionCountField.setAccessible(true);

		AtomicLong queryExecutionCountAtomicLong =
			(AtomicLong)queryExecutionCountField.get(statistics);

		map.put("QueryExecutionCount", queryExecutionCountAtomicLong.get());

		Field queryExecutionMaxTimeField = statisticsClass.getDeclaredField(
			"queryExecutionMaxTime");

		queryExecutionMaxTimeField.setAccessible(true);

		AtomicLong queryExecutionMaxTimeAtomicLong =
			(AtomicLong)queryExecutionMaxTimeField.get(statistics);

		map.put("QueryExecutionMaxTime", queryExecutionMaxTimeAtomicLong.get());

		return map;
	}

	protected Map<String, Map<String, Object>> getLiferayMultiVMMetrics()
		throws Exception {

		Set<ObjectName> objectNames = _mBeanServerService.getObjectNames(
			new ObjectName(
				PortletPropsValues.CACHE_METRICS_MULTI_VM_OBJECT_NAME),
			null);

		return _mBeanServerService.getObjectNamesAttributes(
			objectNames,
			new String[] {"CacheHits", "CacheMisses", "ObjectCount"},
			new ObjectNameKeyPropertyMapKeyStrategy("name"));
	}

	protected Map<String, Map<String, Object>> getLiferaySingleVMMetrics()
		throws Exception {

		Set<ObjectName> objectNames = _mBeanServerService.getObjectNames(
			new ObjectName(
				PortletPropsValues.CACHE_METRICS_SINGLE_VM_OBJECT_NAME),
			null);

		return _mBeanServerService.getObjectNamesAttributes(
			objectNames,
			new String[] {"CacheHits", "CacheMisses", "ObjectCount"},
			new ObjectNameKeyPropertyMapKeyStrategy("name"));
	}

	protected Object getPayload() throws Exception {
		Map<String, Object> payload = new HashMap<>();

		payload.put("hibernateMetrics", getHibernateMetrics());
		payload.put("liferayMultiVMMetrics", getLiferayMultiVMMetrics());
		payload.put("liferaySingleVMMetrics", getLiferaySingleVMMetrics());

		return payload;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CacheMetricsTask.class);

	private MBeanServerService _mBeanServerService;

}