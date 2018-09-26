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

package com.liferay.lcs.task;

import com.liferay.lcs.advisor.LCSKeyAdvisor;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.service.LCSGatewayService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public abstract class BaseScheduledTask implements ScheduledTask {

	@Override
	public void run() {
		if (!_lcsGatewayService.isAvailable()) {
			if (_log.isDebugEnabled()) {
				_log.debug(getClass() + " waiting for LCS connection manager");
			}

			return;
		}

		try {
			doRun();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void setLCSGatewayService(LCSGatewayService lcsGatewayService) {
		_lcsGatewayService = lcsGatewayService;
	}

	public void setLCSKeyAdvisor(LCSKeyAdvisor lcsKeyAdvisor) {
		_lcsKeyAdvisor = lcsKeyAdvisor;
	}

	protected abstract void doRun() throws Exception;

	protected String getKey() {
		return _lcsKeyAdvisor.getKey();
	}

	protected void sendMessage(Message message) throws PortalException {
		if (_log.isTraceEnabled()) {
			_log.trace("Sending message: " + message);
		}

		try {
			_lcsGatewayService.sendMessage(message);
		}
		catch (Exception e) {
			_log.error("Unable to send message", e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseScheduledTask.class);

	private LCSGatewayService _lcsGatewayService;
	private LCSKeyAdvisor _lcsKeyAdvisor;

}