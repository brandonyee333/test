/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.command.advisor;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.constants.LCSClientConstants;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.ErrorResponseMessage;
import com.liferay.lcs.messaging.security.DigitalSignature;
import com.liferay.lcs.messaging.security.DigitalSignatureFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = CommandAdvisor.class)
public class CommandAdvisor {

	public CommandAdvisor() {
		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	public CommandAdvisor(
		DigitalSignature digitalSignature,
		LCSConfigurationProvider lcsConfigurationProvider,
		LCSGatewayClient lcsGatewayClient, LCSKeyAdvisor lcsKeyAdvisor) {

		_digitalSignature = digitalSignature;
		_lcsConfigurationProvider = lcsConfigurationProvider;
		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	public boolean isValid(CommandMessage commandMessage) {
		if (_log.isTraceEnabled()) {
			_log.trace("Verifying digital signature");
		}

		try {
			if (_digitalSignature.verifyMessage(
					LCSClientConstants.LCS_CLIENT_BUILD_NUMBER,
					commandMessage)) {

				return true;
			}

			sendErrorResponseMessage(
				commandMessage, "Digital signature invalid");
		}
		catch (Exception e) {
			_log.error("Unable to perform digital signature check", e);

			sendErrorResponseMessage(
				commandMessage, "Unable to verify digital signature");
		}

		return false;
	}

	@Activate
	protected void activate() {
		_initDigitalSignature(_lcsConfigurationProvider.getLCSConfiguration());

		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	protected void sendErrorResponseMessage(
		CommandMessage commandMessage, String errorMessage) {

		ErrorResponseMessage errorResponseMessage = new ErrorResponseMessage();

		errorResponseMessage.setCorrelationId(
			commandMessage.getCorrelationId());
		errorResponseMessage.setCreateTime(System.currentTimeMillis());
		errorResponseMessage.setErrorMessage(errorMessage);
		errorResponseMessage.setKey(commandMessage.getKey());

		try {
			if (_log.isTraceEnabled()) {
				_log.trace("Sending response message");
			}

			_lcsGatewayClient.sendMessage(errorResponseMessage);
		}
		catch (Exception e) {
			_log.error("Unable to send error response message to LCS", e);
		}
	}

	private void _initDigitalSignature(LCSConfiguration lcsConfiguration) {
		Map<String, Object> dictionary = new HashMap<String, Object>() {
			{
				put("keyName", lcsConfiguration.digitalSignatureKeyName());
				put(
					"keyStorePath",
					lcsConfiguration.digitalSignatureKeyStorePath());
				put(
					"keyStoreType",
					lcsConfiguration.digitalSignatureKeyStoreType());
				put(
					"signingAlgorithm",
					lcsConfiguration.digitalSignatureSigningAlgorithm());
			}
		};

		_digitalSignature = _digitalSignatureFactory.getInstance(dictionary);
	}

	private static final Log _log = LogFactoryUtil.getLog(CommandAdvisor.class);

	private DigitalSignature _digitalSignature;

	@Reference
	private DigitalSignatureFactory _digitalSignatureFactory;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

}