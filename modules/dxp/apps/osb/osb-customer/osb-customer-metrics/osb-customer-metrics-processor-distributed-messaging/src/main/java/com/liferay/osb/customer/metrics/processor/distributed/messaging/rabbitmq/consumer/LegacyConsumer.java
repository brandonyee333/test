/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.processor.distributed.messaging.rabbitmq.consumer;

import com.liferay.osb.customer.metrics.processor.distributed.messaging.rabbitmq.connector.LegacyConnection;
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
		"exchange=is_metrics_exchange", "queue=is_metrics_queue",
		"routing.key=metrics.drop", "routing.key=metrics.remove",
		"routing.key=metrics.update", "routing.key=metrics.upgrade"
	},
	service = LegacyConsumer.class
)
public class LegacyConsumer extends BaseConsumer {

	@Override
	protected Connection getConnection() {
		return _legacyConnection;
	}

	@Reference
	private LegacyConnection _legacyConnection;

}