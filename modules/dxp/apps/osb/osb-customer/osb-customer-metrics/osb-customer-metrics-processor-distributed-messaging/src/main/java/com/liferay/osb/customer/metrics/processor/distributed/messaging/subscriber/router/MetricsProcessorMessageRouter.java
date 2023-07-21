/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.processor.distributed.messaging.subscriber.router;

import com.liferay.osb.customer.metrics.processor.distributed.messaging.subscriber.MetricsDropMessageSubscriber;
import com.liferay.osb.customer.metrics.processor.distributed.messaging.subscriber.MetricsRemoveMessageSubscriber;
import com.liferay.osb.customer.metrics.processor.distributed.messaging.subscriber.MetricsUpdateMessageSubscriber;
import com.liferay.osb.customer.metrics.processor.distributed.messaging.subscriber.MetricsUpgradeMessageSubscriber;
import com.liferay.osb.distributed.messaging.subscribing.router.BaseMessageRouter;
import com.liferay.osb.distributed.messaging.subscribing.router.MessageRouter;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MessageRouter.class)
public class MetricsProcessorMessageRouter extends BaseMessageRouter {

	@Reference(unbind = "-")
	protected void setMetricsDropMessageSubscriber(
		MetricsDropMessageSubscriber metricsDropMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(metricsDropMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setMetricsRemoveMessageSubscriber(
		MetricsRemoveMessageSubscriber metricsRemoveMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(metricsRemoveMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setMetricsUpdateMessageSubscriber(
		MetricsUpdateMessageSubscriber metricsUpdateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(metricsUpdateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setMetricsUpgradeMessageSubscriber(
		MetricsUpgradeMessageSubscriber metricsUpgradeMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(metricsUpgradeMessageSubscriber, properties);
	}

}