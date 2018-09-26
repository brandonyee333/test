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

package com.liferay.lcs.messaging;

import com.liferay.lcs.metrics.PortalMetricsAggregator;
import com.liferay.lcs.platform.gateway.LCSGatewayService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.monitoring.DataSample;

import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class PortalMetricsMessageListener implements MessageListener {

	public PortalMetricsMessageListener(
		LCSGatewayService lcsGatewayService,
		PortalMetricsAggregator portalMetricsAggregator) {

		_lcsGatewayService = lcsGatewayService;
		_portalMetricsAggregator = portalMetricsAggregator;
	}

	public void destroy() {
	}

	@Override
	public void receive(Message message) {
		if (!_lcsGatewayService.isAvailable()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Waiting for LCS connection manager");
			}

			return;
		}

		if (message.getPayload() instanceof DataSample) {
			_portalMetricsAggregator.push((DataSample)message.getPayload());
		}
		else {
			_portalMetricsAggregator.push(
				(List<DataSample>)message.getPayload());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortalMetricsMessageListener.class);

	private final LCSGatewayService _lcsGatewayService;
	private final PortalMetricsAggregator _portalMetricsAggregator;

}