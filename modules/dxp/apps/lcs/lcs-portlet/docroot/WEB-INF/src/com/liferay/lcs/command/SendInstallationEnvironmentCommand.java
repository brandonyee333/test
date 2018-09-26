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

import com.liferay.lcs.advisor.InstallationEnvironmentAdvisor;
import com.liferay.lcs.advisor.InstallationEnvironmentAdvisorFactory;
import com.liferay.lcs.messaging.SendInstallationEnvironmentCommandMessage;
import com.liferay.lcs.messaging.SendInstallationEnvironmentResponseMessage;
import com.liferay.lcs.platform.gateway.LCSGatewayService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public class SendInstallationEnvironmentCommand
	implements Command<SendInstallationEnvironmentCommandMessage> {

	public SendInstallationEnvironmentCommand(
		LCSGatewayService lcsGatewayService) {

		_lcsGatewayService = lcsGatewayService;
	}

	@Override
	public void execute(
		SendInstallationEnvironmentCommandMessage
			sendInstallationEnvironmentCommandMessage) {

		if (_log.isTraceEnabled()) {
			_log.trace("Executing send installation environment command");
		}

		SendInstallationEnvironmentResponseMessage
			sendInstallationEnvironmentResponseMessage =
				_getSendInstallationEnvironmentResponseMessage(
					sendInstallationEnvironmentCommandMessage);

		try {
			_lcsGatewayService.sendMessage(
				sendInstallationEnvironmentResponseMessage);
		}
		catch (Exception e) {
			_log.error("Unable to send installation environment", e);
		}
	}

	private SendInstallationEnvironmentResponseMessage
		_getSendInstallationEnvironmentResponseMessage(
			SendInstallationEnvironmentCommandMessage
				sendInstallationEnvironmentCommandMessage) {

		SendInstallationEnvironmentResponseMessage
			sendInstallationEnvironmentResponseMessage =
				new SendInstallationEnvironmentResponseMessage();

		sendInstallationEnvironmentResponseMessage.setCreateTime(
			System.currentTimeMillis());

		InstallationEnvironmentAdvisor installationEnvironmentAdvisor =
			InstallationEnvironmentAdvisorFactory.getInstance();

		sendInstallationEnvironmentResponseMessage.setHardwareMetadata(
			installationEnvironmentAdvisor.getHardwareMetadata());

		sendInstallationEnvironmentResponseMessage.setKey(
			sendInstallationEnvironmentCommandMessage.getKey());
		sendInstallationEnvironmentResponseMessage.setSoftwareMetadata(
			installationEnvironmentAdvisor.getSoftwareMetadata());

		return sendInstallationEnvironmentResponseMessage;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SendInstallationEnvironmentCommand.class);

	private final LCSGatewayService _lcsGatewayService;

}