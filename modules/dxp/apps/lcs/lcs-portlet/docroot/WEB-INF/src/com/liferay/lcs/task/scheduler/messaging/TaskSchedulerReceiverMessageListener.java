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

package com.liferay.lcs.task.scheduler.messaging;

import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.task.ScheduledTask;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Riccardo Ferrari
 */
public class TaskSchedulerReceiverMessageListener extends BaseMessageListener {

	public TaskSchedulerReceiverMessageListener(
		LCSGatewayClient lcsGatewayClient) {

		_lcsGatewayClient = lcsGatewayClient;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		Map<String, String> schedulerContext =
			(Map<String, String>)message.getPayload();

		String taskName = schedulerContext.get("taskName");

		try {
			if (!_runningTaskNames.contains(taskName)) {
				if (!_lcsGatewayClient.isAvailable()) {
					if (_log.isDebugEnabled()) {
						_log.debug("Waiting for LCS gateway handshake");
					}

					return;
				}

				if (_log.isDebugEnabled()) {
					_log.debug(
						"LCS scheduler received trigger for task " + taskName);
				}

				BeanLocator beanLocator = PortletBeanLocatorUtil.getBeanLocator(
					"lcs-portlet");

				ScheduledTask scheduledTask = (ScheduledTask)beanLocator.locate(
					taskName);

				_runningTaskNames.add(taskName);

				long startTimeMillis = System.currentTimeMillis();

				scheduledTask.run();

				if (_log.isDebugEnabled()) {
					long taskExecutionMillis =
						System.currentTimeMillis() - startTimeMillis;

					_log.debug(
						"Executed LCS task " + taskName + " in " +
							taskExecutionMillis + "ms");
				}

				_runningTaskNames.remove(taskName);
			}
			else {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Skipping message because task " + taskName +
							" is still running");
				}

				return;
			}
		}
		catch (Exception e) {
			_runningTaskNames.remove(taskName);

			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TaskSchedulerReceiverMessageListener.class);

	private static final Set<String> _runningTaskNames =
		Collections.newSetFromMap(new ConcurrentHashMap<>());

	private final LCSGatewayClient _lcsGatewayClient;

}