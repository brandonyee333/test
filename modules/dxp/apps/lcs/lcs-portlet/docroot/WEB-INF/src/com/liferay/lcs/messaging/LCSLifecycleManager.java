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

import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Igor Beslic
 */
public class LCSLifecycleManager extends HotDeployMessageListener {

	public LCSLifecycleManager(TaskSchedulerService taskSchedulerService) {
		super("lcs-portlet");

		_taskSchedulerService = taskSchedulerService;
	}

	@Override
	protected void onDeploy(Message message) throws Exception {
		_taskSchedulerService.start();

		if (_log.isInfoEnabled()) {
			_log.info(
				"LCS client " + PortletPropsValues.LCS_CLIENT_VERSION +
					" deployed");
		}
	}

	@Override
	protected void onUndeploy(Message message) throws Exception {
		LCSUtil.processLCSPortletState(LCSPortletState.PLUGIN_ABSENT);

		if (_log.isWarnEnabled()) {
			_log.warn(
				"LCS client " + PortletPropsValues.LCS_CLIENT_VERSION +
					" undeployed");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSLifecycleManager.class);

	private final TaskSchedulerService _taskSchedulerService;

}