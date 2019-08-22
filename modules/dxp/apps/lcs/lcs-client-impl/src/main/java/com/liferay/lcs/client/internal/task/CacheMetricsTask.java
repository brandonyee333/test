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

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.management.MBeanServerService;
import com.liferay.lcs.client.internal.management.ObjectNameKeyPropertyMapKeyStrategy;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.CacheMetricsMessage;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.ObjectName;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(
	property = "lcs.client.scheduled.task.name=com.liferay.lcs.task.CacheMetricsTask",
	service = ScheduledTask.class
)
public class CacheMetricsTask extends BaseScheduledTask {

	@Activate
	public void activate() {
		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		_multiVMObjectName = lcsConfiguration.cacheMetricsMultiVMObjectName();
		_singleVMObjectName = lcsConfiguration.cacheMetricsSingleVMObjectName();

		setClusterMasterExecutor(_clusterMasterExecutor);
		setLCSGatewayService(_lcsGatewayClient);
		setLCSKeyAdvisor(_lcsKeyAdvisor);
	}

	@Override
	public Scope getScope() {
		return Scope.NODE;
	}

	protected void doRun() throws Exception {
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
			new ObjectName(_multiVMObjectName), null);

		return _mBeanServerService.getObjectNamesAttributes(
			objectNames,
			new String[] {"CacheHits", "CacheMisses", "ObjectCount"},
			new ObjectNameKeyPropertyMapKeyStrategy("name"));
	}

	protected Map<String, Map<String, Object>> getLiferaySingleVMMetrics()
		throws Exception {

		Set<ObjectName> objectNames = _mBeanServerService.getObjectNames(
			new ObjectName(_singleVMObjectName), null);

		return _mBeanServerService.getObjectNamesAttributes(
			objectNames,
			new String[] {"CacheHits", "CacheMisses", "ObjectCount"},
			new ObjectNameKeyPropertyMapKeyStrategy("name"));
	}

	@Reference
	private ClusterMasterExecutor _clusterMasterExecutor;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

	@Reference
	private MBeanServerService _mBeanServerService;

	private String _multiVMObjectName;
	private String _singleVMObjectName;

}