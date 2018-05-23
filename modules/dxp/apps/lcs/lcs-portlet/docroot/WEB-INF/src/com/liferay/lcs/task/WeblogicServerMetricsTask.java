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

import com.liferay.lcs.management.ObjectNameAttributeMapKeyStrategy;
import com.liferay.lcs.management.ObjectNameKeyPropertyMapKeyStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.management.ObjectName;

/**
 * @author Riccardo Ferrari
 */
public class WeblogicServerMetricsTask extends BaseServerMetricsTask {

	@Override
	protected Map<String, Map<String, Object>> getCurrentThreadsMetrics()
		throws Exception {

		return mBeanServerService.getObjectNamesAttributes(
			getCurrentThreadsObjectNames(),
			new String[] {
				"CompletedRequestCount", "ExecuteThreadIdleCount",
				"ExecuteThreadTotalCount", "HoggingThreadCount",
				"PendingUserRequestCount", "QueueLength",
				"SharedCapacityForWorkManagers", "StandbyThreadCount",
				"Suspended", "Throughput"
			},
			new ObjectNameAttributeMapKeyStrategy(
				mBeanServerService.getMBeanServer(), "Name"));
	}

	@Override
	protected Map<String, Map<String, Object>>
			getJNDIJDBCConnectionPoolsMetrics()
		throws Exception {

		return mBeanServerService.getObjectNamesAttributes(
			getJNDIJDBCConnectionPoolsObjectNames(),
			new String[] {
				"ActiveConnectionsAverageCount",
				"ActiveConnectionsCurrentCount", "ConnectionsTotalCount",
				"CurrCapacity", "CurrCapacityHighCount", "HighestNumAvailable",
				"LeakedConnectionCount", "WaitingForConnectionCurrentCount",
				"WaitingForConnectionFailureTotal",
				"WaitingForConnectionHighCount", "WaitingForConnectionTotal",
				"WaitSecondsHighCount"
			},
			new ObjectNameKeyPropertyMapKeyStrategy("Name"));
	}

	@Override
	protected void setUpCurrentThreadsObjectNames() throws Exception {
		ObjectName objectName = new ObjectName(
			"com.bea:Name=RuntimeService,Type=" +
				"weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");

		setCurrentThreadsObjectNames(
			mBeanServerService.getObjectNames(
				objectName,
				new ArrayList<String>(
					Arrays.asList("ServerRuntime", "ThreadPoolRuntime"))));
	}

	@Override
	protected void setUpJNDIJDBCConnectionPoolsObjectNames() throws Exception {
		ObjectName objectName = new ObjectName(
			"com.bea:Name=RuntimeService,Type=" +
				"weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");

		setJNDIJDBCConnectionPoolsObjectNames(
			mBeanServerService.getObjectNames(
				objectName,

				// Order matters

				new ArrayList<String>(
					Arrays.asList(
						"ServerRuntime", "JDBCServiceRuntime",
						"JDBCDataSourceRuntimeMBeans"))));
	}

}