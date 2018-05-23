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

package com.liferay.lcs.messaging.echo.sample1.web.internal.messaging;

import com.liferay.lcs.messaging.bus.LCSMessageBusService;
import com.liferay.lcs.messaging.bus.LCSMessageListener;
import com.liferay.lcs.messaging.bus.LCSMessageListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

/**
 * @author Riccardo Ferrari
 */
public class EchoLCSMessageListener implements LCSMessageListener {

	public EchoLCSMessageListener(LCSMessageBusService lcsMessageBusService) {
		_lcsMessageBusService = lcsMessageBusService;
	}

	@Override
	public void receive(
			String destinationName, Map<String, String> metadata,
			String payload, String responseDestinationName)
		throws LCSMessageListenerException {

		if (_log.isInfoEnabled()) {
			_log.info("Received message for destination:" + destinationName);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Message payload: " + payload);
		}

		if (Validator.isNull(responseDestinationName)) {
			return;
		}

		StringBuilder sb = new StringBuilder(payload);

		sb.reverse();

		_lcsMessageBusService.sendMessage(
			responseDestinationName, sb.toString());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EchoLCSMessageListener.class);

	private final LCSMessageBusService _lcsMessageBusService;

}