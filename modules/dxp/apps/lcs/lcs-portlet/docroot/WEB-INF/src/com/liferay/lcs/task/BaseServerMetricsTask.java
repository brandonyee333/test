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

import com.liferay.lcs.advisor.LCSKeyAdvisor;
import com.liferay.lcs.management.MBeanServerService;
import com.liferay.lcs.messaging.ServerMetricsMessage;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.portal.kernel.dao.jdbc.pool.metrics.ConnectionPoolMetrics;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.ObjectName;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author Riccardo Ferrari
 * @author Mladen Cikara
 */
public abstract class BaseServerMetricsTask implements ServerMetricsTask {

	public void afterPropertiesSet() {
		try {
			Bundle bundle = FrameworkUtil.getBundle(getClass());

			BundleContext bundleContext = bundle.getBundleContext();

			Collection<ServiceReference<ConnectionPoolMetrics>>
				serviceReferences = bundleContext.getServiceReferences(
					ConnectionPoolMetrics.class, null);

			for (ServiceReference<ConnectionPoolMetrics> serviceReference :
					serviceReferences) {

				_connectionPoolMetrics.add(
					bundleContext.getService(serviceReference));
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("JDBC connection pools metrics is not available");
			}

			_currentThreadsMetricsEnabled = false;

			_log.error(e, e);
		}

		try {
			setUpCurrentThreadsObjectNames();

			Map<String, Map<String, Object>> currentThreadsMetrics =
				getCurrentThreadsMetrics();

			if (currentThreadsMetrics.isEmpty()) {
				_currentThreadsMetricsEnabled = false;

				if (_log.isWarnEnabled()) {
					_log.warn("Current threads metrics is not available");
				}
			}
		}
		catch (Exception e) {
			if (e instanceof AttributeNotFoundException ||
				e instanceof InstanceNotFoundException) {

				_currentThreadsMetricsEnabled = false;

				if (_log.isWarnEnabled()) {
					_log.warn("Current threads metrics is not available");
				}
			}
			else {
				_log.error(e, e);
			}
		}

		try {
			_properties = PropsUtil.getProperties("jdbc.default.", true);

			String jndiName = _properties.getProperty("jndi.name");

			if (Validator.isNotNull(jndiName)) {
				setUpJNDIJDBCConnectionPoolsObjectNames();
			}

			Map<String, Map<String, Object>> jdbcConnectionPoolsMetrics =
				getJDBCConnectionPoolsMetrics();

			if (jdbcConnectionPoolsMetrics.isEmpty()) {
				_jdbcConnectionPoolsMetricsEnabled = false;

				if (_log.isWarnEnabled()) {
					_log.warn("JDBC connection pools metrics is not available");
				}
			}
		}
		catch (Exception e) {
			if (e instanceof AttributeNotFoundException ||
				e instanceof InstanceNotFoundException) {

				_jdbcConnectionPoolsMetricsEnabled = false;

				if (_log.isWarnEnabled()) {
					_log.warn("JDBC connection pools metrics is not available");
				}
			}
			else {
				_log.error(e, e);
			}
		}
	}

	public Set<ObjectName> getCurrentThreadsObjectNames() {
		return _currentThreadsObjectNames;
	}

	public Set<ObjectName> getJNDIJDBCConnectionPoolsObjectNames() {
		return _jndiJDBCConnectionPoolsObjectNames;
	}

	@Override
	public Scope getScope() {
		return Scope.NODE;
	}

	@Override
	public boolean isCurrentThreadsMetricsEnabled() {
		return _currentThreadsMetricsEnabled;
	}

	@Override
	public boolean isJDBCConnectionPoolsMetricsEnabled() {
		return _jdbcConnectionPoolsMetricsEnabled;
	}

	@Override
	public void run() {
		try {
			doRun();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void setCurrentThreadsObjectNames(
		Set<ObjectName> currentThreadsObjectNames) {

		_currentThreadsObjectNames = currentThreadsObjectNames;
	}

	public void setJNDIJDBCConnectionPoolsObjectNames(
		Set<ObjectName> jndiJDBCConnectionPoolsObjectNames) {

		_jndiJDBCConnectionPoolsObjectNames =
			jndiJDBCConnectionPoolsObjectNames;
	}

	@Override
	public void setLCSGatewayService(LCSGatewayClient lcsGatewayClient) {
		this.lcsGatewayClient = lcsGatewayClient;
	}

	@Override
	public void setLCSKeyAdvisor(LCSKeyAdvisor lcsKeyAdvisor) {
		this.lcsKeyAdvisor = lcsKeyAdvisor;
	}

	@Override
	public void setMBeanServerService(MBeanServerService mBeanServerService) {
		this.mBeanServerService = mBeanServerService;
	}

	protected void doRun() throws Exception {
		if (_log.isTraceEnabled()) {
			_log.trace("Running " + getClass());
		}

		if (!lcsGatewayClient.isAvailable()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Waiting for LCS connection manager");
			}

			return;
		}

		ServerMetricsMessage serverMetricsMessage = new ServerMetricsMessage();

		serverMetricsMessage.setCreateTime(System.currentTimeMillis());
		serverMetricsMessage.setCurrentThreadsMetrics(
			getCurrentThreadsMetrics());
		serverMetricsMessage.setKey(lcsKeyAdvisor.getKey());
		serverMetricsMessage.setJDBCConnectionPoolsMetrics(
			getJDBCConnectionPoolsMetrics());

		lcsGatewayClient.sendMessage(serverMetricsMessage);
	}

	protected abstract Map<String, Map<String, Object>>
			getCurrentThreadsMetrics()
		throws Exception;

	protected Map<String, Map<String, Object>> getJDBCConnectionPoolsMetrics()
		throws Exception {

		Map<String, Map<String, Object>> jdbcConnectionPoolsMetrics;

		String jndiName = _properties.getProperty("jndi.name");

		if (Validator.isNotNull(jndiName)) {
			jdbcConnectionPoolsMetrics = getJNDIJDBCConnectionPoolsMetrics();
		}
		else {
			jdbcConnectionPoolsMetrics = new HashMap<>();

			for (ConnectionPoolMetrics connectionPoolMetric :
					_connectionPoolMetrics) {

				Map<String, Object> values = new HashMap<>();

				values.put("numActive", connectionPoolMetric.getNumActive());
				values.put("numIdle", connectionPoolMetric.getNumIdle());

				jdbcConnectionPoolsMetrics.put(
					connectionPoolMetric.getConnectionPoolName(), values);
			}
		}

		return jdbcConnectionPoolsMetrics;
	}

	protected abstract Map<String, Map<String, Object>>
			getJNDIJDBCConnectionPoolsMetrics()
		throws Exception;

	protected Map<String, Object> getPayload() throws Exception {
		Map<String, Object> payload = new HashMap<>();

		if (isCurrentThreadsMetricsEnabled()) {
			payload.put("currentThreadsMetrics", getCurrentThreadsMetrics());
		}

		if (isJDBCConnectionPoolsMetricsEnabled()) {
			payload.put(
				"jdbcConnectionPoolsMetrics", getJDBCConnectionPoolsMetrics());
		}

		return payload;
	}

	protected abstract void setUpCurrentThreadsObjectNames() throws Exception;

	protected abstract void setUpJNDIJDBCConnectionPoolsObjectNames()
		throws Exception;

	protected LCSGatewayClient lcsGatewayClient;
	protected LCSKeyAdvisor lcsKeyAdvisor;
	protected MBeanServerService mBeanServerService;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseServerMetricsTask.class);

	private final Collection<ConnectionPoolMetrics> _connectionPoolMetrics =
		new ArrayList<>();
	private boolean _currentThreadsMetricsEnabled = true;
	private Set<ObjectName> _currentThreadsObjectNames = Collections.emptySet();
	private boolean _jdbcConnectionPoolsMetricsEnabled = true;
	private Set<ObjectName> _jndiJDBCConnectionPoolsObjectNames =
		Collections.emptySet();
	private Properties _properties;

}