/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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