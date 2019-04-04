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

import com.liferay.lcs.client.internal.metrics.PortalMetricsAggregator;
import com.liferay.lcs.messaging.PortalMetricsMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = ScheduledTask.class)
public class PortalMetricsTask extends BaseScheduledTask {

	@Override
	public Scope getScope() {
		return Scope.NODE;
	}

	@Override
	protected void doRun() throws Exception {
		if (_portalMetricsAggregator.isEmpty()) {
			if (_log.isDebugEnabled()) {
				_log.debug("No portal metrics to send");
			}

			return;
		}

		PortalMetricsMessage portalMetricsMessage = new PortalMetricsMessage();

		portalMetricsMessage.setCreateTime(System.currentTimeMillis());
		portalMetricsMessage.setKey(getKey());

		List<Map<String, Object>>[] performanceMetrics =
			_portalMetricsAggregator.pop();

		portalMetricsMessage.setLayoutPerformanceMetricsList(
			performanceMetrics[0]);
		portalMetricsMessage.setPortletPerformanceMetricsList(
			performanceMetrics[1]);

		sendMessage(portalMetricsMessage);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortalMetricsTask.class);

	@Reference
	private PortalMetricsAggregator _portalMetricsAggregator;

}