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

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.exception.CompressionException;
import com.liferay.lcs.client.internal.advisor.DefaultInstallationEnvironmentAdvisor;
import com.liferay.lcs.client.internal.advisor.InstallationEnvironmentAdvisor;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.platform.gateway.LCSGatewayException;
import com.liferay.lcs.messaging.SendInstallationEnvironmentCommandMessage;
import com.liferay.lcs.messaging.SendInstallationEnvironmentResponseMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public class SendInstallationEnvironmentTask extends BaseTask {

	public SendInstallationEnvironmentTask(
		LCSGatewayClient lcsGatewayClient,
		SendInstallationEnvironmentCommandMessage
			sendInstallationEnvironmentCommandMessage) {

		_lcsGatewayClient = lcsGatewayClient;
		_sendInstallationEnvironmentCommandMessage =
			sendInstallationEnvironmentCommandMessage;

		_installationEnvironmentAdvisor =
			new DefaultInstallationEnvironmentAdvisor();

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void doRun() throws CompressionException, LCSGatewayException {
		if (_log.isTraceEnabled()) {
			_log.trace("Executing send installation environment command");
		}

		SendInstallationEnvironmentResponseMessage
			sendInstallationEnvironmentResponseMessage =
				_getSendInstallationEnvironmentResponseMessage(
					_sendInstallationEnvironmentCommandMessage.getKey());

		_lcsGatewayClient.sendMessage(
			sendInstallationEnvironmentResponseMessage);
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.COMMAND;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private SendInstallationEnvironmentResponseMessage
		_getSendInstallationEnvironmentResponseMessage(String key) {

		SendInstallationEnvironmentResponseMessage
			sendInstallationEnvironmentResponseMessage =
				new SendInstallationEnvironmentResponseMessage();

		sendInstallationEnvironmentResponseMessage.setCreateTime(
			System.currentTimeMillis());

		sendInstallationEnvironmentResponseMessage.setHardwareMetadata(
			_installationEnvironmentAdvisor.getHardwareMetadata());

		sendInstallationEnvironmentResponseMessage.setKey(key);
		sendInstallationEnvironmentResponseMessage.setSoftwareMetadata(
			_installationEnvironmentAdvisor.getSoftwareMetadata());

		return sendInstallationEnvironmentResponseMessage;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SendInstallationEnvironmentTask.class);

	private final InstallationEnvironmentAdvisor
		_installationEnvironmentAdvisor;
	private final LCSGatewayClient _lcsGatewayClient;
	private final SendInstallationEnvironmentCommandMessage
		_sendInstallationEnvironmentCommandMessage;

}