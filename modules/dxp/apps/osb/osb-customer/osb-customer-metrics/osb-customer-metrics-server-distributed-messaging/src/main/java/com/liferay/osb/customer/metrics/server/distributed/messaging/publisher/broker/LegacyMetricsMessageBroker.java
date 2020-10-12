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

package com.liferay.osb.customer.metrics.server.distributed.messaging.publisher.broker;

import com.liferay.osb.customer.metrics.server.distributed.messaging.rabbitmq.connector.LegacyConnection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.Connection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.broker.BaseMessageBroker;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"exchange=is_metrics_exchange", "publishing.topic.pattern=metrics.*"
	},
	service = LegacyMetricsMessageBroker.class
)
public class LegacyMetricsMessageBroker extends BaseMessageBroker {

	@Override
	protected Connection getConnection() {
		return _legacyConnection;
	}

	@Reference
	private LegacyConnection _legacyConnection;

}