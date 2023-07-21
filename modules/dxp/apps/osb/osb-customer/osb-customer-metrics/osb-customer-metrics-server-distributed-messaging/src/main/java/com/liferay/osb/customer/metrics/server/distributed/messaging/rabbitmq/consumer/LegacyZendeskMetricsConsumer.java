/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.server.distributed.messaging.rabbitmq.consumer;

import com.liferay.osb.customer.metrics.server.distributed.messaging.rabbitmq.connector.LegacyConnection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.Connection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.consumer.BaseConsumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"exchange=is_entity_exchange", "queue=is_zendesk_metrics_queue"
	},
	service = LegacyZendeskMetricsConsumer.class
)
public class LegacyZendeskMetricsConsumer extends BaseConsumer {

	@Override
	protected Connection getConnection() {
		return _legacyConnection;
	}

	@Reference
	private LegacyConnection _legacyConnection;

}