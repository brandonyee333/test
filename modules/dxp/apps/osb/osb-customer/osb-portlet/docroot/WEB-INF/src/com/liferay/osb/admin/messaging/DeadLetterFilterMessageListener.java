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

package com.liferay.osb.admin.messaging;

import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletPreferences;

/* TODO update rabbitMQ integration
import com.liferay.osb.rabbitmq.DeadLetterFilterRabbitMQConsumer;
import com.liferay.rabbitmq.consumer.RabbitMQConsumer;
import com.liferay.rabbitmq.service.ConsumerManagerLocalServiceUtil;
*/

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