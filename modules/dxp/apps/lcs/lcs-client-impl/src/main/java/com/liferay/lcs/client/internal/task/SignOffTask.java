/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.messaging.SignOffCommandMessage;
import com.liferay.lcs.messaging.SignOffReasonCode;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class SignOffTask extends BaseTask {

	public SignOffTask(
		LCSEventManager lcsEventManager,
		SignOffCommandMessage signOffCommandMessage) {

		_lcsEventManager = lcsEventManager;
		_signOffCommandMessage = signOffCommandMessage;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void doRun() {
		SignOffReasonCode signOffReasonCode = _getSignOffReasonCode();

		if (_log.isInfoEnabled()) {
			_log.info(
				"Signing server out of LCS with reason code:" +
					signOffReasonCode);

			if (_log.isTraceEnabled()) {
				_log.trace(
					"Sign out command message: " + _signOffCommandMessage);
			}
		}

		if (signOffReasonCode == SignOffReasonCode.INVALIDATE_TOKEN) {
			_lcsEventManager.publish(
				LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED);
		}
		else if (signOffReasonCode == SignOffReasonCode.UNREGISTER) {
			_lcsEventManager.publish(LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED);
		}
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.MANAGEABLE;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private SignOffReasonCode _getSignOffReasonCode() {
		for (SignOffReasonCode signOffReasonCode : SignOffReasonCode.values()) {
			if (_signOffCommandMessage.getReasonCode() ==
					signOffReasonCode.getCode()) {

				return signOffReasonCode;
			}
		}

		return SignOffReasonCode.UNDEFINED;
	}

	private static final Log _log = LogFactoryUtil.getLog(SignOffTask.class);

	private final LCSEventManager _lcsEventManager;
	private final SignOffCommandMessage _signOffCommandMessage;

}