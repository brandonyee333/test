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
import com.liferay.lcs.messaging.SignOffCommandMessage;
import com.liferay.lcs.messaging.SignOffReasonCode;
import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public class SignOffCommand implements Command<SignOffCommandMessage> {

	public SignOffCommand(
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor,
		TaskSchedulerService taskSchedulerService) {

		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;
		_taskSchedulerService = taskSchedulerService;
	}

	@Override
	public void execute(SignOffCommandMessage signOffCommandMessage) {
		SignOffReasonCode signOffReasonCode = _getSignOffReasonCode(
			signOffCommandMessage);

		if (_log.isDebugEnabled()) {
			_log.debug("Executing sign off command " + signOffCommandMessage);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Sign off reason " + signOffReasonCode);
		}

		if ((signOffReasonCode == SignOffReasonCode.INVALIDATE_TOKEN) ||
			(signOffReasonCode == SignOffReasonCode.UNREGISTER)) {

			_lcsClusterEntryTokenAdvisor.deleteLCSCLusterEntryTokenFile();
		}

		_taskSchedulerService.restart();

		if (_log.isDebugEnabled()) {
			_log.debug("Signed off server from LCS");
		}
	}

	private SignOffReasonCode _getSignOffReasonCode(
		SignOffCommandMessage signOffCommandMessage) {

		for (SignOffReasonCode signOffReasonCode : SignOffReasonCode.values()) {
			if (signOffCommandMessage.getReasonCode() ==
					signOffReasonCode.getCode()) {

				return signOffReasonCode;
			}
		}

		return SignOffReasonCode.UNDEFINED;
	}

	private static final Log _log = LogFactoryUtil.getLog(SignOffCommand.class);

	private final LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private final TaskSchedulerService _taskSchedulerService;

}