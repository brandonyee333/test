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
import com.liferay.lcs.platform.gateway.LCSGatewayService;

/**
 * @author Riccardo Ferrari
 */
public interface ServerMetricsTask extends ScheduledTask {

	public boolean isCurrentThreadsMetricsEnabled();

	public boolean isJDBCConnectionPoolsMetricsEnabled();

	public void setLCSGatewayService(LCSGatewayService lcsGatewayService);

	public void setLCSKeyAdvisor(LCSKeyAdvisor lcsKeyAdvisor);

	public void setMBeanServerService(MBeanServerService mBeanServerService);

}