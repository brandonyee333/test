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
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutorUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public abstract class BaseScheduledTask implements ScheduledTask {

	@Override
	public void run() {
		if (!_lcsGatewayClient.isAvailable()) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Aborting " + getClass() +
						". LCS gateway is not available.");
			}

			return;
		}

		if (ClusterMasterExecutorUtil.isEnabled()) {
			if (getScope() == Scope.CLUSTER) {
				if (!ClusterMasterExecutorUtil.isMaster()) {
					if (_log.isDebugEnabled()) {
						StringBundler sb = new StringBundler(4);

						sb.append("Aborting ");
						sb.append(getClass());
						sb.append(". It is a task with cluster scope, and ");
						sb.append("this node is not master.");

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
		}

		try {
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
			_log.error(e, e);
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

	protected void sendMessage(Message message) throws PortalException {
		if (!_lcsGatewayClient.isAvailable()) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Aborting message sending. LCS gateway is not available.");
			}

			return;
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Sending message: " + message);
		}

		try {
			_lcsGatewayClient.sendMessage(message);
		}
		catch (Exception e) {
			_log.error("Unable to send message", e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseScheduledTask.class);

	private LCSGatewayClient _lcsGatewayClient;
	private LCSKeyAdvisor _lcsKeyAdvisor;

}