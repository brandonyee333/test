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

import com.liferay.lcs.client.advisor.ClusterNodeAdvisor;
import com.liferay.lcs.client.exception.CompressionException;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.platform.gateway.LCSGatewayException;
import com.liferay.lcs.messaging.ClusterHealthMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(
	property = "lcs.client.scheduled.task.name=com.liferay.lcs.task.ClusterHealthTask",
	service = Task.class
)
public class ClusterHealthTask extends BaseTask {

	@Override
	public TaskType getTaskType() {
		return TaskType.MANAGEABLE;
	}

	@Activate
	protected void activate() {
		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	@Override
	protected void doRun() throws CompressionException, LCSGatewayException {
		ClusterHealthMessage clusterHealthMessage = new ClusterHealthMessage();

		clusterHealthMessage.setCreateTime(System.currentTimeMillis());
		clusterHealthMessage.setKey(_lcsKeyAdvisor.getKey());

		clusterHealthMessage.setSiblingKeys(
			_clusterNodeAdvisor.getClusterNodeKeys());

		_lcsGatewayClient.sendMessage(clusterHealthMessage);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClusterHealthTask.class);

	@Reference
	private ClusterNodeAdvisor _clusterNodeAdvisor;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

}