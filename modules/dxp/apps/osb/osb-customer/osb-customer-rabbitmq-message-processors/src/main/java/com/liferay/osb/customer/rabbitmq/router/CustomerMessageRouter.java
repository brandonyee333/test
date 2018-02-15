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

package com.liferay.osb.customer.rabbitmq.router;

import com.liferay.osb.customer.rabbitmq.connector.router.BaseMessageRouter;
import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouter;
import com.liferay.osb.customer.rabbitmq.processors.OrganizationUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processors.UserUpdateMessageProcessor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.customer.rabbitmq.configuration.RabbitMQConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	property = {"queue=is_customer_queue"}, service = MessageRouter.class
)
public class CustomerMessageRouter extends BaseMessageRouter {

	@Reference(unbind = "-")
	protected void setOrganizationUpdateMessageProcessor(
		OrganizationUpdateMessageProcessor organizationUpdateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(organizationUpdateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setUserUpdateMessageProcessor(
		UserUpdateMessageProcessor userUpdateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(userUpdateMessageProcessor, properties);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CustomerMessageRouter.class);

}