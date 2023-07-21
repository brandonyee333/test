/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.management.MBeanServerService;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;

/**
 * @author Riccardo Ferrari
 */
public interface ServerMetricsTask extends Task {

	public void afterPropertiesSet();

	public boolean isCurrentThreadsMetricsEnabled();

	public boolean isJDBCConnectionPoolsMetricsEnabled();

	public void setLCSGatewayService(LCSGatewayClient lcsGatewayClient);

	public void setLCSKeyAdvisor(LCSKeyAdvisor lcsKeyAdvisor);

	public void setMBeanServerService(MBeanServerService mBeanServerService);

}