/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.distributed.messaging.subscriber.rabbitmq.consumer;

import com.liferay.osb.customer.distributed.messaging.connector.rabbitmq.LegacyConnection;
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
		"exchange=is_zendesk_exchange", "exclusive=true",
		"queue=is_zendesk_queue"
	},
	service = LegacyZendeskConsumer.class
)
public class LegacyZendeskConsumer extends BaseConsumer {

	@Override
	protected Connection getConnection() {
		return _legacyConnection;
	}

	@Reference
	private LegacyConnection _legacyConnection;

}