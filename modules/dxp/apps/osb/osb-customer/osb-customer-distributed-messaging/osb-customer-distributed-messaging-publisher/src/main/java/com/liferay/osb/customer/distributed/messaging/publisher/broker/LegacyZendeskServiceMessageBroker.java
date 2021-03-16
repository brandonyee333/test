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

package com.liferay.osb.customer.distributed.messaging.publisher.broker;

import com.liferay.osb.customer.distributed.messaging.connector.rabbitmq.LegacyConnection;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.Connection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.broker.BaseMessageBroker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

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
		"exchange=is_zendesk_exchange",
		"publishing.topic.pattern=zendesk.service"
	},
	service = LegacyZendeskServiceMessageBroker.class
)
public class LegacyZendeskServiceMessageBroker extends BaseMessageBroker {

	@Override
	public void publish(String topic, List<Message> messages)
		throws IOException {
	}

	@Override
	public void publish(String topic, Message message) throws IOException {
		try {
			super.publish(topic, message);
		}
		catch (Exception exception) {
			_log.error(
				"Unable to publish message: " + message.toString(), exception);
		}
	}

	@Override
	protected Connection getConnection() {
		return _legacyConnection;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LegacyZendeskServiceMessageBroker.class);

	@Reference
	private LegacyConnection _legacyConnection;

}