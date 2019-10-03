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

package com.liferay.lcs.client.internal.lifecycle;

import com.liferay.lcs.client.constants.LCSClientConstants;
import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.event.LCSEventListener;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.task.TaskDefinition;
import com.liferay.lcs.client.internal.task.advisor.TaskAdvisor;
import com.liferay.lcs.client.internal.task.executor.LCSTaskExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import java.util.HashSet;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSModuleLifecycle.class)
public class LCSModuleLifecycle implements LCSEventListener {

	public LCSModuleLifecycle() {
	}

	public LCSModuleLifecycle(
		LCSTaskExecutor lcsTaskExecutor, TaskAdvisor taskAdvisor,
		LCSEventManager lcsEventManager) {

		_lcsTaskExecutor = lcsTaskExecutor;
		_taskAdvisor = taskAdvisor;
		_lcsEventManager = lcsEventManager;
	}

	@Override
	public void onLCSEvent(LCSEvent lcsEvent) {
		if (lcsEvent == LCSEvent.HANDSHAKE_SUCCESS) {
			_startOnlineTasks();
		}
		else if (lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS) {
			_startHandShakeTask(false);
		}
		else if ((lcsEvent == LCSEvent.HANDSHAKE_FAILED) ||
				 (lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED) ||
				 (lcsEvent ==
					 LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED) ||
				 (lcsEvent ==
					 LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH) ||
				 (lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID) ||
				 (lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED) ||
				 (lcsEvent ==
					 LCSEvent.
						 LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS) ||
				 (lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MISSING) ||
				 (lcsEvent ==
					 LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MULTIPLE_TOKENS) ||
				 (lcsEvent == LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED) ||
				 (lcsEvent == LCSEvent.LCS_GATEWAY_UNAVAILABLE)) {

			_startLCSClusterEntryTokenCheckTask(true);
		}
	}

	@Activate
	protected void activate() {
		_subscribeToLCSEvents();

		_startUptimeTask();

		_startLCSClusterEntryTokenCheckTask(false);

		if (_log.isInfoEnabled()) {
			_log.info(_LCS_CLIENT_FULL_NAME + " activated");
		}
	}

	@Deactivate
	protected void deactivate() {
		_lcsEventManager.unsubscribe(this);

		if (_log.isWarnEnabled()) {
			_log.warn(_LCS_CLIENT_FULL_NAME + " deactivated");
		}
	}

	private void _startHandShakeTask(boolean delayed) {
		_lcsTaskExecutor.submit(
			_taskAdvisor.getHandshakeTaskDefinition(delayed));
	}

	private void _startLCSClusterEntryTokenCheckTask(boolean delayed) {
		_lcsTaskExecutor.submit(
			_taskAdvisor.getLCSClusterEntryTokenCheckTaskDefinition(delayed));
	}

	private void _startOnlineTasks() {
		Set<TaskDefinition> taskDefinitions = new HashSet<>();

		taskDefinitions.add(_taskAdvisor.getHeartBeatTaskDefinition());
		taskDefinitions.add(
			_taskAdvisor.getCommandMessageCheckTaskDefinition());
		taskDefinitions.add(_taskAdvisor.getCommandQueueCheckTaskDefinition());

		_lcsTaskExecutor.process(taskDefinitions);
	}

	private void _startUptimeTask() {
		_lcsTaskExecutor.submit(_taskAdvisor.getUptimeTaskDefinition());
	}

	private void _subscribeToLCSEvents() {
		_lcsEventManager.subscribe(LCSEvent.HANDSHAKE_FAILED, this);
		_lcsEventManager.subscribe(LCSEvent.HANDSHAKE_SUCCESS, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MISSING, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MULTIPLE_TOKENS, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED, this);
		_lcsEventManager.subscribe(LCSEvent.LCS_GATEWAY_UNAVAILABLE, this);
	}

	private static final String _LCS_CLIENT_FULL_NAME =
		"LCS Client " + LCSClientConstants.LCS_CLIENT_VERSION;

	private static final Log _log = LogFactoryUtil.getLog(
		LCSModuleLifecycle.class);

	@Reference
	private LCSEventManager _lcsEventManager;

	@Reference
	private LCSTaskExecutor _lcsTaskExecutor;

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

	@Reference
	private TaskAdvisor _taskAdvisor;

}