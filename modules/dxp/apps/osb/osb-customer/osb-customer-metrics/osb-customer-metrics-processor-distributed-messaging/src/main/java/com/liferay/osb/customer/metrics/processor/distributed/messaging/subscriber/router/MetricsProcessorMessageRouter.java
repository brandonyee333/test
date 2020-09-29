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