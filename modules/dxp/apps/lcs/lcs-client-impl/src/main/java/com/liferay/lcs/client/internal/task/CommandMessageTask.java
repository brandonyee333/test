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

import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.command.advisor.CommandAdvisor;
import com.liferay.lcs.client.internal.util.comparator.MessagePriorityComparator;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.Message;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(
	property = "lcs.client.scheduled.task.name=com.liferay.lcs.task.CommandMessageTask",
	service = ScheduledTask.class
)
public class CommandMessageTask extends BaseScheduledTask {

	public CommandMessageTask() {
		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	public CommandMessageTask(
		CommandAdvisor commandAdvisor, LCSGatewayClient lcsGatewayClient,
		LCSKeyAdvisor lcsKeyAdvisor) {

		_commandAdvisor = commandAdvisor;
		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;

		setLCSGatewayService(_lcsGatewayClient);
		setLCSKeyAdvisor(_lcsKeyAdvisor);

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public Scope getScope() {
		return Scope.NODE;
	}

	@Activate
	protected void activate() {
		setLCSGatewayService(_lcsGatewayClient);
		setLCSKeyAdvisor(_lcsKeyAdvisor);
	}

	protected void doRun() throws Exception {
		if (_log.isTraceEnabled()) {
			_log.trace("Running command message task");
		}

		if (!_lcsGatewayClient.isAvailable()) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Aborting command messages retrieving. LCS gateway is " +
						"not available.");
			}

			return;
		}

		String key = getKey();

		if (_log.isTraceEnabled()) {
			_log.trace("Checking messages for " + key);
		}

		List<Message> messages = _lcsGatewayClient.getMessages(key);

		Collections.sort(messages, new MessagePriorityComparator());

		_commandAdvisor.execute(messages);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommandMessageTask.class);

	@Reference
	private CommandAdvisor _commandAdvisor;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

}