/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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