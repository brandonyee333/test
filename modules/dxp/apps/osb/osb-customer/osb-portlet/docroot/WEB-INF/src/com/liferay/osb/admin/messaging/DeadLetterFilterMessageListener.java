/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.admin.messaging;

import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Validator;

/* TODO update rabbitMQ integration
import com.liferay.osb.rabbitmq.DeadLetterFilterRabbitMQConsumer;
import com.liferay.rabbitmq.consumer.RabbitMQConsumer;
import com.liferay.rabbitmq.service.ConsumerManagerLocalServiceUtil;
*/

import javax.portlet.PortletPreferences;

/**
 * @author Amos Fong
 */
public class DeadLetterFilterMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		if (Validator.isNull(
				PortletPropsValues.RABBITMQ_MESSAGE_DEAD_LETTER_QUEUE_NAME)) {

			return;
		}

		PortletPreferences portletPreferences =
			AdminUtil.getPortletPreferences();

		String deadLetterFilterScript = portletPreferences.getValue(
			"deadLetterFilterScript", null);

		if (Validator.isNull(deadLetterFilterScript)) {
			return;
		}

		/* TODO update rabbitMQ integration
		try {
			RabbitMQConsumer rabbitMQConsumer =
				new DeadLetterFilterRabbitMQConsumer(deadLetterFilterScript);

			ConsumerManagerLocalServiceUtil.consumeMessages(
				PortletPropsValues.RABBITMQ_MESSAGE_DEAD_LETTER_QUEUE_NAME, 0,
				rabbitMQConsumer);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		*/
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DeadLetterFilterMessageListener.class);

}