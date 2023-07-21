/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.dao.jdbc.aop.DynamicDataSourceTargetSource;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;

import javax.mail.Session;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
@OSGiBeanProperties(service = InfrastructureUtil.class)
public class InfrastructureUtil {

	public static DataSource getDataSource() {
		PortalRuntimePermission.checkGetBeanProperty(
			InfrastructureUtil.class, "dataSource");

		return _dataSource;
	}

	public static DynamicDataSourceTargetSource
		getDynamicDataSourceTargetSource() {

		PortalRuntimePermission.checkGetBeanProperty(
			InfrastructureUtil.class, "dynamicDataSourceTargetSource");

		return _dynamicDataSourceTargetSource;
	}

	public static Session getMailSession() {
		PortalRuntimePermission.checkGetBeanProperty(
			InfrastructureUtil.class, "mailSession");

		return _mailSession;
	}

	public static Object getTransactionManager() {
		PortalRuntimePermission.checkGetBeanProperty(
			InfrastructureUtil.class, "transactionManager");

		return _transactionManager;
	}

	public void setDataSource(DataSource dataSource) {
		PortalRuntimePermission.checkSetBeanProperty(getClass(), "dataSource");

		_dataSource = dataSource;
	}

	public void setDynamicDataSourceTargetSource(
		DynamicDataSourceTargetSource dynamicDataSourceTargetSource) {

		PortalRuntimePermission.checkSetBeanProperty(
			getClass(), "dynamicDataSourceTargetSource");

		_dynamicDataSourceTargetSource = dynamicDataSourceTargetSource;
	}

	public void setMailSession(Session mailSession) {
		PortalRuntimePermission.checkSetBeanProperty(getClass(), "mailSession");

		_mailSession = mailSession;
	}

	public void setTransactionManager(Object transactionManager) {
		PortalRuntimePermission.checkSetBeanProperty(
			getClass(), "transactionManager");

		_transactionManager = transactionManager;
	}

	private static DataSource _dataSource;
	private static DynamicDataSourceTargetSource _dynamicDataSourceTargetSource;
	private static Session _mailSession;
	private static Object _transactionManager;

}