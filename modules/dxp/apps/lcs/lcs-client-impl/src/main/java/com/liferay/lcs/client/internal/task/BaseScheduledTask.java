/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.Message;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public abstract class BaseScheduledTask
	extends BaseTask implements ScheduledTask {

	@Override
	public void run() {
		if (_clusterMasterExecutor.isEnabled() &&
			(getScope() == Scope.CLUSTER)) {

			if (!_clusterMasterExecutor.isMaster()) {
				if (_log.isDebugEnabled()) {
					StringBundler sb = new StringBundler(4);

					sb.append("Aborting ");
					sb.append(getClass());
					sb.append(". It is a task with cluster scope, and this ");
					sb.append("node is not master.");

					_log.debug(sb.toString());
				}

				return;
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Executing cluster scoped task " + getClass() +
						" on master node");
			}
		}

		super.run();
	}

	public void setLCSGatewayService(LCSGatewayClient lcsGatewayClient) {
		_lcsGatewayClient = lcsGatewayClient;
	}

	public void setLCSKeyAdvisor(LCSKeyAdvisor lcsKeyAdvisor) {
		_lcsKeyAdvisor = lcsKeyAdvisor;
	}

	protected abstract void doRun() throws Exception;

	protected String getKey() {
		return _lcsKeyAdvisor.getKey();
	}

	protected void sendMessage(Message message) {
		try {
			_lcsGatewayClient.sendMessage(message);
		}
		catch (Exception e) {
			_log.error("Unable to send message", e);
		}
	}

	protected void setClusterMasterExecutor(
		ClusterMasterExecutor clusterMasterExecutor) {

		_clusterMasterExecutor = clusterMasterExecutor;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseScheduledTask.class);

	private ClusterMasterExecutor _clusterMasterExecutor;
	private LCSGatewayClient _lcsGatewayClient;
	private LCSKeyAdvisor _lcsKeyAdvisor;

}