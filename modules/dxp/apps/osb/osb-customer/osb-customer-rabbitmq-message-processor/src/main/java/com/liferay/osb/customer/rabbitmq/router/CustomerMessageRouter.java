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

package com.liferay.osb.customer.rabbitmq.router;

import com.liferay.osb.customer.rabbitmq.connector.router.BaseMessageRouter;
import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouter;
import com.liferay.osb.customer.rabbitmq.processor.AccountContactAssignedMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.AccountContactRoleAssignedMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.AccountContactRoleUnassignedMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.AccountContactUnassignedMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.AccountCreateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.AccountDeleteMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.AccountUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectMessageAddMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectMessageDeleteMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectMessageUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.OrganizationAssignmentMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.OrganizationUnassignmentMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.OrganizationUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.ProvisioningCreateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.ProvisioningUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.RoleAssignmentMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.RoleUnassignmentMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.RoleUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.UserDeleteMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.UserUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.ZendeskOrganizationCreateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.ZendeskUserCreateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.ZendeskUserDeleteMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.ZendeskUserIdentityCreateMessageProcessor;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "queue=is_customer_queue",
	service = MessageRouter.class
)
public class CustomerMessageRouter extends BaseMessageRouter {

	@Reference(unbind = "-")
	protected void setAccountContactAssignedMessageProcessor(
		AccountContactAssignedMessageProcessor
			accountContactAssignedMessageProcessor,
		Map<String, Object> properties) {

		addRoute(accountContactAssignedMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountContactRoleAssignedMessageProcessor(
		AccountContactRoleAssignedMessageProcessor
			accountContactRoleAssignedMessageProcessor,
		Map<String, Object> properties) {

		addRoute(accountContactRoleAssignedMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountContactRoleUnassignedMessageProcessor(
		AccountContactRoleUnassignedMessageProcessor
			accountContactRoleUnassignedMessageProcessor,
		Map<String, Object> properties) {

		addRoute(accountContactRoleUnassignedMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountContactUnassignedMessageProcessor(
		AccountContactUnassignedMessageProcessor
			accountContactUnassignedMessageProcessor,
		Map<String, Object> properties) {

		addRoute(accountContactUnassignedMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountCreateMessageProcessor(
		AccountCreateMessageProcessor accountCreateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(accountCreateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountDeleteMessageProcessor(
		AccountDeleteMessageProcessor accountDeleteMessageProcessor,
		Map<String, Object> properties) {

		addRoute(accountDeleteMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountUpdateMessageProcessor(
		AccountUpdateMessageProcessor accountUpdateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(accountUpdateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setCorpProjectMessageAddMessageProcessor(
		CorpProjectMessageAddMessageProcessor
			corpProjectMessageAddMessageProcessor,
		Map<String, Object> properties) {

		addRoute(corpProjectMessageAddMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setCorpProjectMessageDeleteMessageProcessor(
		CorpProjectMessageDeleteMessageProcessor
			corpProjectMessageDeleteMessageProcessor,
		Map<String, Object> properties) {

		addRoute(corpProjectMessageDeleteMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setCorpProjectMessageUpdateMessageProcessor(
		CorpProjectMessageUpdateMessageProcessor
			corpProjectMessageUpdateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(corpProjectMessageUpdateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setOrganizationAssignmentMessageProcessor(
		OrganizationAssignmentMessageProcessor
			organizationAssignmentMessageProcessor,
		Map<String, Object> properties) {

		addRoute(organizationAssignmentMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setOrganizationUnassignmentMessageProcessor(
		OrganizationUnassignmentMessageProcessor
			organizationUnassignmentMessageProcessor,
		Map<String, Object> properties) {

		addRoute(organizationUnassignmentMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setOrganizationUpdateMessageProcessor(
		OrganizationUpdateMessageProcessor organizationUpdateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(organizationUpdateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setProvisioningCreateMessageProcessor(
		ProvisioningCreateMessageProcessor provisioningCreateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(provisioningCreateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setProvisioningUpdateMessageProcessor(
		ProvisioningUpdateMessageProcessor provisioningUpdateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(provisioningUpdateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setRoleAssignmentMessageProcessor(
		RoleAssignmentMessageProcessor roleAssignmentMessageProcessor,
		Map<String, Object> properties) {

		addRoute(roleAssignmentMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setRoleUnassignmentMessageProcessor(
		RoleUnassignmentMessageProcessor roleUnassignmentMessageProcessor,
		Map<String, Object> properties) {

		addRoute(roleUnassignmentMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setRoleUpdateMessageProcessor(
		RoleUpdateMessageProcessor roleUpdateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(roleUpdateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setUserDeleteMessageProcessor(
		UserDeleteMessageProcessor userDeleteMessageProcessor,
		Map<String, Object> properties) {

		addRoute(userDeleteMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setUserUpdateMessageProcessor(
		UserUpdateMessageProcessor userUpdateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(userUpdateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskOrganizationCreateMessageProcessor(
		ZendeskOrganizationCreateMessageProcessor
			zendeskOrganizationCreateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(zendeskOrganizationCreateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskUserCreateMessageProcessor(
		ZendeskUserCreateMessageProcessor zendeskUserCreateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(zendeskUserCreateMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskUserDeleteMessageProcessor(
		ZendeskUserDeleteMessageProcessor zendeskUserDeleteMessageProcessor,
		Map<String, Object> properties) {

		addRoute(zendeskUserDeleteMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskUserIdentityCreateMessageProcessor(
		ZendeskUserIdentityCreateMessageProcessor
			zendeskUserIdentityCreateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(zendeskUserIdentityCreateMessageProcessor, properties);
	}

}