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

package com.liferay.osb.customer.distributed.messaging.subscriber.router;

import com.liferay.osb.customer.koroneiki.message.subscriber.AccountContactRoleMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.AccountTeamRoleAssignedMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.AccountTeamRoleUnassignedMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.AccountUpdateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.EntitlementCreateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.EntitlementDeleteMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.ProductCreateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.ProductDeleteMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.ProductPurchaseCreateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.ProductPurchaseDeleteMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.ProductPurchaseUpdateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.ProductUpdateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.TeamContactRoleMessageSubscriber;
import com.liferay.osb.customer.koroneiki.message.subscriber.TeamUpdateMessageSubscriber;
import com.liferay.osb.customer.legacy.message.subscriber.OrganizationAssignmentMessageSubscriber;
import com.liferay.osb.customer.legacy.message.subscriber.OrganizationUnassignmentMessageSubscriber;
import com.liferay.osb.customer.legacy.message.subscriber.OrganizationUpdateMessageSubscriber;
import com.liferay.osb.customer.legacy.message.subscriber.RoleAssignmentMessageSubscriber;
import com.liferay.osb.customer.legacy.message.subscriber.RoleUnassignmentMessageSubscriber;
import com.liferay.osb.customer.legacy.message.subscriber.RoleUpdateMessageSubscriber;
import com.liferay.osb.customer.legacy.message.subscriber.UserDeleteMessageSubscriber;
import com.liferay.osb.customer.legacy.message.subscriber.UserUpdateMessageSubscriber;
import com.liferay.osb.customer.legacy.message.subscriber.ZendeskOrganizationCreateMessageSubscriber;
import com.liferay.osb.customer.legacy.message.subscriber.ZendeskUserCreateMessageSubscriber;
import com.liferay.osb.customer.legacy.message.subscriber.ZendeskUserDeleteMessageSubscriber;
import com.liferay.osb.customer.legacy.message.subscriber.ZendeskUserIdentityCreateMessageSubscriber;
import com.liferay.osb.customer.zendesk.message.subscriber.AccountDeleteMessageSubscriber;
import com.liferay.osb.customer.zendesk.message.subscriber.DossieraMessageSubscriber;
import com.liferay.osb.customer.zendesk.message.subscriber.ZendeskWebServiceMessageSubscriber;
import com.liferay.osb.distributed.messaging.subscribing.router.BaseMessageRouter;
import com.liferay.osb.distributed.messaging.subscribing.router.MessageRouter;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MessageRouter.class)
public class CustomerMessageRouter extends BaseMessageRouter {

	@Reference(unbind = "-")
	protected void setAccountContactRoleMessageSubscriber(
		AccountContactRoleMessageSubscriber accountContactRoleMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(accountContactRoleMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountDeleteMessageSubscriber(
		AccountDeleteMessageSubscriber accountDeleteMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(accountDeleteMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountTeamRoleAssignedMessageSubscriber(
		AccountTeamRoleAssignedMessageSubscriber
			accountTeamRoleAssignedMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(accountTeamRoleAssignedMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountTeamRoleUnassignedMessageSubscriber(
		AccountTeamRoleUnassignedMessageSubscriber
			accountTeamRoleUnassignedMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(accountTeamRoleUnassignedMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountUpdateMessageSubscriber(
		AccountUpdateMessageSubscriber accountUpdateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(accountUpdateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setDossieraMessageSubscriber(
		DossieraMessageSubscriber dossieraMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(dossieraMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setEntitlementCreateMessageSubscriber(
		EntitlementCreateMessageSubscriber entitlementCreateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(entitlementCreateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setEntitlementDeleteMessageSubscriber(
		EntitlementDeleteMessageSubscriber entitlementDeleteMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(entitlementDeleteMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setOrganizationAssignmentMessageSubscriber(
		OrganizationAssignmentMessageSubscriber
			organizationAssignmentMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(organizationAssignmentMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setOrganizationUnassignmentMessageSubscriber(
		OrganizationUnassignmentMessageSubscriber
			organizationUnassignmentMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(organizationUnassignmentMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setOrganizationUpdateMessageSubscriber(
		OrganizationUpdateMessageSubscriber organizationUpdateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(organizationUpdateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setProductCreateMessageSubscriber(
		ProductCreateMessageSubscriber productCreateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(productCreateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setProductDeleteMessageSubscriber(
		ProductDeleteMessageSubscriber productDeleteMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(productDeleteMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setProductPurchaseCreateMessageSubscriber(
		ProductPurchaseCreateMessageSubscriber
			productPurchaseCreateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(productPurchaseCreateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setProductPurchaseDeleteMessageSubscriber(
		ProductPurchaseDeleteMessageSubscriber
			productPurchaseDeleteMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(productPurchaseDeleteMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setProductPurchaseUpdateMessageSubscriber(
		ProductPurchaseUpdateMessageSubscriber
			productPurchaseUpdateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(productPurchaseUpdateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setProductUpdateMessageSubscriber(
		ProductUpdateMessageSubscriber productUpdateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(productUpdateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setRoleAssignmentMessageSubscriber(
		RoleAssignmentMessageSubscriber roleAssignmentMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(roleAssignmentMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setRoleUnassignmentMessageSubscriber(
		RoleUnassignmentMessageSubscriber roleUnassignmentMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(roleUnassignmentMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setRoleUpdateMessageSubscriber(
		RoleUpdateMessageSubscriber roleUpdateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(roleUpdateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setTeamContactRoleMessageSubscriber(
		TeamContactRoleMessageSubscriber teamContactRoleMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(teamContactRoleMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setTeamUpdateMessageSubscriber(
		TeamUpdateMessageSubscriber teamUpdateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(teamUpdateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setUserDeleteMessageSubscriber(
		UserDeleteMessageSubscriber userDeleteMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(userDeleteMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setUserUpdateMessageSubscriber(
		UserUpdateMessageSubscriber userUpdateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(userUpdateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskOrganizationCreateMessageSubscriber(
		ZendeskOrganizationCreateMessageSubscriber
			zendeskOrganizationCreateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(zendeskOrganizationCreateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskUserCreateMessageSubscriber(
		ZendeskUserCreateMessageSubscriber zendeskUserCreateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(zendeskUserCreateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskUserDeleteMessageSubscriber(
		ZendeskUserDeleteMessageSubscriber zendeskUserDeleteMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(zendeskUserDeleteMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskUserIdentityCreateMessageSubscriber(
		ZendeskUserIdentityCreateMessageSubscriber
			zendeskUserIdentityCreateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(zendeskUserIdentityCreateMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskWebServiceMessageSubscriber(
		ZendeskWebServiceMessageSubscriber zendeskWebServiceMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(zendeskWebServiceMessageSubscriber, properties);
	}

}