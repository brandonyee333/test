/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.jdbc.pool.metrics;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.zaxxer.hikari.HikariPoolMXBean;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author Mladen Cikara
 */
public class HikariConnectionPoolMetrics extends BaseConnectionPoolMetrics {

	public HikariConnectionPoolMetrics(Object dataSource) {
		_dataSource = dataSource;
	}

	@Override
	public int getNumActive() {
		if (!_initializationFailed && (_connectionPool == null)) {
			initializeConnectionPool();
		}

		if (_initializationFailed) {
			return -1;
		}

		return _connectionPool.getActiveConnections();
	}

	@Override
	public int getNumIdle() {
		if (!_initializationFailed && (_connectionPool == null)) {
			initializeConnectionPool();
		}

		if (_initializationFailed) {
			return -1;
		}

		return _connectionPool.getIdleConnections();
	}

	@Override
	protected Object getDataSource() {
		if (_initializationFailed) {
			return null;
		}

		return _dataSource;
	}

	@Override
	protected String getPoolName() {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			Class<?> clazz = contextClassLoader.loadClass(
				"com.zaxxer.hikari.HikariDataSource");

			Method method = clazz.getMethod("getPoolName");

			return (String)method.invoke(_dataSource);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage(), e);
			}
		}

		return null;
	}

	@Override
	protected void initializeConnectionPool() {
		if (getPoolName() == null) {
			_initializationFailed = true;

			return;
		}

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

		try {
			ObjectName objectName = new ObjectName(
				"com.zaxxer.hikari:type=Pool (" + getPoolName() + ")");

			_connectionPool = JMX.newMXBeanProxy(
				mBeanServer, objectName, HikariPoolMXBean.class);
		}
		catch (Exception e) {
			_initializationFailed = true;

			if (_log.isDebugEnabled()) {
				_log.debug(e.getMessage());
			}
		}

		super.initializeConnectionPool();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		HikariConnectionPoolMetrics.class);

	private HikariPoolMXBean _connectionPool;
	private final Object _dataSource;
	private boolean _initializationFailed;

}