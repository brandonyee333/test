/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.command.advisor.CommandAdvisor;
import com.liferay.lcs.client.internal.command.queue.CommandQueue;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(
	name = "com.liferay.lcs.client.internal.task.CommandMessageCheckTask",
	service = Task.class
)
public class CommandMessageCheckTask extends BaseTask {

	public CommandMessageCheckTask() {
		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	public CommandMessageCheckTask(
		CommandQueue commandQueue, LCSGatewayClient lcsGatewayClient,
		LCSKeyAdvisor lcsKeyAdvisor) {

		_commandQueue = commandQueue;
		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.ONLINE_REQUIRED;
	}

	@Activate
	protected void activate() {
		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	protected void doRun() throws Exception {
		String key = _lcsKeyAdvisor.getKey();

		if (_log.isTraceEnabled()) {
			_log.trace("Checking messages for " + key);
		}

		List<Message> messages = _lcsGatewayClient.getMessages(key);

		Iterator<Message> iterator = messages.iterator();

		while (iterator.hasNext()) {
			Message message = iterator.next();

			if (!(message instanceof CommandMessage)) {
				_log.error("Unknown message " + message);

				iterator.remove();

				continue;
			}

			if (!_commandAdvisor.isValid((CommandMessage)message)) {
				if (_log.isInfoEnabled()) {
					_log.info("Skip invalid command message " + message);
				}

				iterator.remove();
			}
		}

		_commandQueue.add(messages);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommandMessageCheckTask.class);

	@Reference
	private CommandAdvisor _commandAdvisor;

	@Reference
	private CommandQueue _commandQueue;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

}