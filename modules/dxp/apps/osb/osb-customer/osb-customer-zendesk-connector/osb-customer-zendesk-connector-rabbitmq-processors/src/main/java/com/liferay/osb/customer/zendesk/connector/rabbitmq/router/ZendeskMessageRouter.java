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

package com.liferay.osb.customer.zendesk.connector.rabbitmq.router;

import com.liferay.osb.customer.rabbitmq.connector.router.BaseMessageRouter;
import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouter;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.processors.ZendeskOrganizationCreateMessageProcessor;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.processors.ZendeskOrganizationCreateOrUpdateMessageProcessor;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.processors.ZendeskOrganizationMembershipBulkCreateMessageProcessor;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.processors.ZendeskOrganizationMembershipBulkDeleteMessageProcessor;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.processors.ZendeskTagAddMessageProcessor;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.processors.ZendeskTagDeleteMessageProcessor;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.processors.ZendeskUserCreateOrUpdateMessageProcessor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "queue=is_zendesk_queue",
	service = MessageRouter.class
)
public class ZendeskMessageRouter extends BaseMessageRouter {

	@Reference(unbind = "-")
	protected void setZendeskOrganizationCreateMessageProcessor(
		ZendeskOrganizationCreateMessageProcessor
			zendeskOrganizationCreateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(zendeskOrganizationCreateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskOrganizationCreateOrUpdateMessageProcessor(
		ZendeskOrganizationCreateOrUpdateMessageProcessor
			zendeskOrganizationCreateOrUpdateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(zendeskOrganizationCreateOrUpdateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskOrganizationMembershipBulkCreateMessageProcessor(
		ZendeskOrganizationMembershipBulkCreateMessageProcessor
			zendeskOrganizationMembershipBulkCreateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(
			zendeskOrganizationMembershipBulkCreateMessageProcessor,
			properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskOrganizationMembershipBulkDeleteMessageProcessor(
		ZendeskOrganizationMembershipBulkDeleteMessageProcessor
			zendeskOrganizationMembershipBulkDeleteMessageProcessor,
		Map<String, Object> properties) {

		addRoute(
			zendeskOrganizationMembershipBulkDeleteMessageProcessor,
			properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskTagAddMessageProcessor(
		ZendeskTagAddMessageProcessor zendeskTagAddMessageProcessor,
		Map<String, Object> properties) {

		addRoute(zendeskTagAddMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskTagDeleteMessageProcessor(
		ZendeskTagDeleteMessageProcessor zendeskTagDeleteMessageProcessor,
		Map<String, Object> properties) {

		addRoute(zendeskTagDeleteMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskUserCreateOrUpdateMessageProcessor(
		ZendeskUserCreateOrUpdateMessageProcessor
			zendeskUserCreateOrUpdateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(zendeskUserCreateOrUpdateMessageProcessor, properties);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskMessageRouter.class);

}