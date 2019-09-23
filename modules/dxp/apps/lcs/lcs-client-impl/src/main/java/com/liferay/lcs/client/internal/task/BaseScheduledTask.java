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
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.Message;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public abstract class BaseScheduledTask implements ScheduledTask {

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

		try {
			if (_log.isTraceEnabled()) {
				_log.trace("Running task " + getClass());
			}

			long startTimeMillis = System.currentTimeMillis();

			doRun();

			if (_log.isDebugEnabled()) {
				long taskExecutionMillis =
					System.currentTimeMillis() - startTimeMillis;

				StringBundler sb = new StringBundler(5);

				sb.append("Executed LCS task ");
				sb.append(getClass());
				sb.append(" in ");
				sb.append(taskExecutionMillis);
				sb.append("ms");

				_log.debug(sb.toString());
			}
		}
		catch (Exception e) {
			String errorMessage = "Unable to run task";

			if (e instanceof JSONWebServiceException) {
				_log.error(errorMessage);
			}
			else {
				_log.error(errorMessage, e);
			}
		}
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