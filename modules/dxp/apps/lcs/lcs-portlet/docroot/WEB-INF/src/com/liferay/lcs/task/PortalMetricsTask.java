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

import com.liferay.lcs.messaging.MetricsMessage;
import com.liferay.lcs.metrics.PortalMetricsAggregator;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public class PortalMetricsTask extends BaseScheduledTask {

	@Override
	public Type getType() {
		return Type.LOCAL;
	}

	public void setPortalMetricsAggregator(
		PortalMetricsAggregator portalMetricsAggregator) {

		_portalMetricsAggregator = portalMetricsAggregator;
	}

	@Override
	protected void doRun() throws Exception {
		if (_log.isTraceEnabled()) {
			_log.trace("Running portal metrics task");
		}

		if (_portalMetricsAggregator.isEmpty()) {
			if (_log.isDebugEnabled()) {
				_log.debug("No portal metrics to send");
			}

			return;
		}

		MetricsMessage metricsMessage = new MetricsMessage();

		metricsMessage.setCreateTime(System.currentTimeMillis());
		metricsMessage.setKey(getKey());
		metricsMessage.setMetricsType(MetricsMessage.METRICS_TYPE_PORTAL);

		metricsMessage.setPayload(_portalMetricsAggregator.pop());

		sendMessage(metricsMessage);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortalMetricsTask.class);

	private PortalMetricsAggregator _portalMetricsAggregator;

}