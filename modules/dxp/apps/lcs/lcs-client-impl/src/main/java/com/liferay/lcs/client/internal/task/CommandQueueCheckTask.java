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
import com.liferay.lcs.client.internal.command.queue.CommandQueue;
import com.liferay.lcs.client.internal.util.comparator.MessagePriorityComparator;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.Message;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	property = "lcs.client.scheduled.task.name=com.liferay.lcs.task.CommandQueueCheckTask",
	service = ScheduledTask.class
)
public class CommandQueueCheckTask extends BaseScheduledTask {

	public CommandQueueCheckTask() {
		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	public CommandQueueCheckTask(
		ClusterMasterExecutor clusterMasterExecutor,
		CommandAdvisor commandAdvisor, CommandQueue commandQueue,
		LCSGatewayClient lcsGatewayClient, LCSKeyAdvisor lcsKeyAdvisor) {

		_clusterMasterExecutor = clusterMasterExecutor;
		_commandAdvisor = commandAdvisor;
		_commandQueue = commandQueue;
		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;

		_initBaseScheduledTask();

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
		_initBaseScheduledTask();
	}

	protected void doRun() {
		if (_log.isTraceEnabled()) {
			_log.trace("Checking command message queue");
		}

		List<Message> messages = new ArrayList<>();

		Message message = null;

		while ((message = _commandQueue.next()) != null) {
			messages.add(message);
		}

		if (messages.isEmpty()) {
			if (_log.isTraceEnabled()) {
				_log.trace("Command message queue empty");
			}

			return;
		}

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

	private void _initBaseScheduledTask() {
		setClusterMasterExecutor(_clusterMasterExecutor);
		setLCSGatewayService(_lcsGatewayClient);
		setLCSKeyAdvisor(_lcsKeyAdvisor);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommandQueueCheckTask.class);

	@Reference
	private ClusterMasterExecutor _clusterMasterExecutor;

	@Reference
	private CommandAdvisor _commandAdvisor;

	@Reference
	private CommandQueue _commandQueue;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

}