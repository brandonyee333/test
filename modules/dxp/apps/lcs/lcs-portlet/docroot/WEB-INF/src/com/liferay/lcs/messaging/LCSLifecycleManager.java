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

package com.liferay.lcs.messaging;

import com.liferay.lcs.advisor.UptimeMonitoringAdvisor;
import com.liferay.lcs.sigar.SigarNativeLoader;
import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.OSDetector;

import java.util.concurrent.Future;

/**
 * @author Igor Beslic
 */
public class LCSLifecycleManager extends HotDeployMessageListener {

	public LCSLifecycleManager(
		TaskSchedulerService taskSchedulerService,
		UptimeMonitoringAdvisor uptimeMonitoringAdvisor) {

		super("lcs-portlet");

		_taskSchedulerService = taskSchedulerService;
		_uptimeMonitoringAdvisor = uptimeMonitoringAdvisor;
	}

	@Override
	protected void onDeploy(Message message) throws Exception {
		if (!OSDetector.isAIX()) {
			SigarNativeLoader.load();
		}

		try {
			_uptimeMonitoringAdvisor.init();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		_taskSchedulerService.schedule();

		if (_log.isInfoEnabled()) {
			_log.info(
				"LCS portlet " + PortletPropsValues.LCS_CLIENT_VERSION +
					" deployed");
		}
	}

	@Override
	protected void onUndeploy(Message message) throws Exception {
		Future<?> future = _taskSchedulerService.submitSignOffTask(true);

		while (!future.isDone()) {
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException ie) {
				if (_log.isWarnEnabled()) {
					_log.warn("Interrupted while waiting for SignOff task");
				}
				else if (_log.isDebugEnabled()) {
					_log.debug(
						"Interrupted while waiting for SignOff task", ie);
				}
			}
		}

		LCSUtil.processLCSPortletState(LCSPortletState.PLUGIN_ABSENT);

		if (!OSDetector.isAIX()) {
			SigarNativeLoader.unload();
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"LCS portlet " + PortletPropsValues.LCS_CLIENT_VERSION +
					" undeployed");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSLifecycleManager.class);

	private final TaskSchedulerService _taskSchedulerService;
	private final UptimeMonitoringAdvisor _uptimeMonitoringAdvisor;

}