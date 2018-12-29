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
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectAddMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectAssignedMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectDeleteMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectMessageAddMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectMessageDeleteMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectMessageUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectRoleAssignedMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectRoleUnassignedMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectUnassignedMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.CorpProjectUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.OrganizationAssignmentMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.OrganizationUnassignmentMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.OrganizationUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.ProvisioningCreateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.ProvisioningTrialCreateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.ProvisioningUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.RoleAssignmentMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.RoleUnassignmentMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.RoleUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.UserDeleteMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.UserUpdateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.ZendeskOrganizationCreateMessageProcessor;
import com.liferay.osb.customer.rabbitmq.processor.ZendeskUserCreateMessageProcessor;
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
	protected void setCorpProjectAddMessageProcessor(
		CorpProjectAddMessageProcessor corpProjectAddMessageProcessor,
		Map<String, Object> properties) {

		addRoute(corpProjectAddMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setCorpProjectAssignedMessageProcessor(
		CorpProjectAssignedMessageProcessor corpProjectAssignedMessageProcessor,
		Map<String, Object> properties) {

		addRoute(corpProjectAssignedMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setCorpProjectDeleteMessageProcessor(
		CorpProjectDeleteMessageProcessor corpProjectDeleteMessageProcessor,
		Map<String, Object> properties) {

		addRoute(corpProjectDeleteMessageProcessor, properties);
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
	protected void setCorpProjectRoleAssignedMessageProcessor(
		CorpProjectRoleAssignedMessageProcessor
			corpProjectRoleAssignedMessageProcessor,
		Map<String, Object> properties) {

		addRoute(corpProjectRoleAssignedMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setCorpProjectRoleUnassignedMessageProcessor(
		CorpProjectRoleUnassignedMessageProcessor
			corpProjectRoleUnassignedMessageProcessor,
		Map<String, Object> properties) {

		addRoute(corpProjectRoleUnassignedMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setCorpProjectUnassignedMessageProcessor(
		CorpProjectUnassignedMessageProcessor
			corpProjectUnassignedMessageProcessor,
		Map<String, Object> properties) {

		addRoute(corpProjectUnassignedMessageProcessor, properties);
	}

	@Reference(unbind = "-")
	protected void setCorpProjectUpdateMessageProcessor(
		CorpProjectUpdateMessageProcessor corpProjectUpdateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(corpProjectUpdateMessageProcessor, properties);
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
	protected void setProvisioningTrialCreateMessageProcessor(
		ProvisioningTrialCreateMessageProcessor
			provisioningTrialCreateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(provisioningTrialCreateMessageProcessor, properties);
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
	protected void setZendeskUserIdentityCreateMessageProcessor(
		ZendeskUserIdentityCreateMessageProcessor
			zendeskUserIdentityCreateMessageProcessor,
		Map<String, Object> properties) {

		addRoute(zendeskUserIdentityCreateMessageProcessor, properties);
	}

}