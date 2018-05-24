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

package com.liferay.osb.service.impl;

import com.liferay.osb.rabbitmq.ProvisioningCreateRabbitMQConsumer;
import com.liferay.osb.rabbitmq.ProvisioningUpdateRabbitMQConsumer;
import com.liferay.osb.rabbitmq.RabbitMQConsumer;
import com.liferay.osb.service.base.RabbitMQMessageProcessorLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Kyle Bischof
 */
public class RabbitMQMessageProcessorLocalServiceImpl
	extends RabbitMQMessageProcessorLocalServiceBaseImpl {

	public void processCreateMessage(JSONObject jsonObject)
		throws PortalException {

		RabbitMQConsumer rabbitMQConsumer =
			new ProvisioningCreateRabbitMQConsumer();

		rabbitMQConsumer.parse(
			"dossiera.provisioning.create", jsonObject.toString(), null);
	}

	public void processUpdateMessage(JSONObject jsonObject) {
		RabbitMQConsumer rabbitMQConsumer =
			new ProvisioningUpdateRabbitMQConsumer();

		rabbitMQConsumer.parse(
			"dossiera.provisioning.update", jsonObject.toString(), null);
	}

}