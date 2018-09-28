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

package com.liferay.lcs.command;

import com.liferay.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.advisor.LCSKeyAdvisor;
import com.liferay.lcs.messaging.SignOffCommandMessage;
import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Igor Beslic
 */
public class SignOffCommand implements Command<SignOffCommandMessage> {

	public SignOffCommand(
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor,
		LCSKeyAdvisor lcsKeyAdvisor,
		TaskSchedulerService taskSchedulerService) {

		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_taskSchedulerService = taskSchedulerService;
	}

	@Override
	public void execute(SignOffCommandMessage signOffCommandMessage) {
		if (_log.isDebugEnabled()) {
			StringBundler sb = new StringBundler(5);

			sb.append("Command message: {deregister=");
			sb.append(signOffCommandMessage.isDeregister());
			sb.append(", invalidateToken=");
			sb.append(signOffCommandMessage.isInvalidateToken());
			sb.append("}");

			_log.debug(sb.toString());
		}

		if (signOffCommandMessage.isDeregister() ||
			signOffCommandMessage.isInvalidateToken()) {

			_lcsClusterEntryTokenAdvisor.deleteLCSCLusterEntryTokenFile();

			if (signOffCommandMessage.isDeregister()) {
				_lcsKeyAdvisor.clearCache();
			}
		}

		_taskSchedulerService.restart();

		if (_log.isDebugEnabled()) {
			_log.debug("Signed off server from LCS");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(SignOffCommand.class);

	private final LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private final LCSKeyAdvisor _lcsKeyAdvisor;
	private final TaskSchedulerService _taskSchedulerService;

}