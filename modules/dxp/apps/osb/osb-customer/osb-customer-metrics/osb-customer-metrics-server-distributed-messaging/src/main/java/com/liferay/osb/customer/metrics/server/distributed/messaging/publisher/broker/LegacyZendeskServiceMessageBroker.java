/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.server.distributed.messaging.publisher.broker;

import com.liferay.osb.customer.metrics.server.distributed.messaging.rabbitmq.connector.LegacyConnection;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.Connection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.broker.BaseMessageBroker;

import java.io.IOException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"exchange=is_metrics_zendesk_exchange",
		"publishing.topic.pattern=zendesk.service.*"
	},
	service = LegacyZendeskServiceMessageBroker.class
)
public class LegacyZendeskServiceMessageBroker extends BaseMessageBroker {

	@Override
	public void publish(String topic, List<Message> messages)
		throws IOException {
	}

	@Override
	protected Connection getConnection() {
		return _legacyConnection;
	}

	@Reference
	private LegacyConnection _legacyConnection;

}