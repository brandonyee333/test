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

package com.liferay.lcs.client.internal.command;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.messaging.SignOffCommandMessage;
import com.liferay.lcs.messaging.SignOffReasonCode;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(service = SignOffCommand.class)
public class SignOffCommand implements Command<SignOffCommandMessage> {

	public SignOffCommand() {
		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void execute(SignOffCommandMessage signOffCommandMessage) {
		SignOffReasonCode signOffReasonCode = _getSignOffReasonCode(
			signOffCommandMessage);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Signing server out of LCS with reason code:" +
					signOffReasonCode);

			if (_log.isTraceEnabled()) {
				_log.trace(
					"Sign out command message: " + signOffCommandMessage);
			}
		}

		if (signOffReasonCode == SignOffReasonCode.INVALIDATE_TOKEN) {
			_lcsEventManager.publish(
				LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED);
		}
		else if (signOffReasonCode == SignOffReasonCode.UNREGISTER) {
			_lcsEventManager.publish(LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED);
		}
		else {
			_lcsEventManager.publish(LCSEvent.SIGNOFF_REQUESTED);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Signed server out of LCS");
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

	@Reference
	private LCSEventManager _lcsEventManager;

}