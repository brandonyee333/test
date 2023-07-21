/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Timothy Bell
 */
@Component(immediate = true, service = LoopConsumer.class)
public class LoopConsumer extends DefaultConsumer {

	public LoopConsumer() {
		super(null);
	}

	public LoopConsumer(Channel channel) {
		super(channel);
	}

	@Override
	public void handleDelivery(
			String consumerTag, Envelope envelope,
			AMQP.BasicProperties properties, byte[] body)
		throws IOException {

		String message = new String(body, StringPool.UTF8);

		JSONObject jsonObject = null;

		try {
			Channel channel = getChannel();

			jsonObject = _jsonFactory.createJSONObject(message);

			String[] routingKey = StringUtil.split(
				envelope.getRoutingKey(), StringPool.PERIOD);

			jsonObject.put("apiName", routingKey[1]);

			String entityName = routingKey[0];

			jsonObject.put("entityName", entityName);

			boolean messageSent = LoopUtil.sendSyncMessage(
				jsonObject, entityName);

			if (messageSent) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Successfully processed RabbitMQ message: " +
							jsonObject.toString());
				}

				channel.basicAck(envelope.getDeliveryTag(), false);
			}
			else {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Failed processing RabbitMQ message: " +
							jsonObject.toString());
				}

				channel.basicNack(envelope.getDeliveryTag(), false, true);
			}
		}
		catch (Exception e) {
			StringBundler sb = new StringBundler(4);

			sb.append("Error processing RabbitMQ message: ");
			sb.append(jsonObject.toString());
			sb.append(StringPool.NEW_LINE);
			sb.append(e.getMessage());

			_log.error(sb.toString());

			LoopEmailNotificationUtil.sendExceptionEmail(
				e.getMessage(), jsonObject);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(LoopConsumer.class);

	@Reference
	private JSONFactory _jsonFactory;

}