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

package com.liferay.osb.customer.koroneiki.subscriber.router;

import com.liferay.osb.customer.koroneiki.subscriber.AccountContactRoleMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.AccountDeleteMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.AccountTeamRoleAssignedMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.AccountTeamRoleUnassignedMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.AccountUpdateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.EntitlementCreateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.EntitlementDeleteMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.ProductCreateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.ProductDeleteMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.ProductPurchaseCreateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.ProductPurchaseDeleteMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.ProductPurchaseUpdateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.ProductUpdateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.TeamUpdateMessageSubscriber;
import com.liferay.osb.distributed.messaging.subscribing.router.BaseMessageRouter;
import com.liferay.osb.distributed.messaging.subscribing.router.MessageRouter;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MessageRouter.class)
public class KoroneikiMessageRouter extends BaseMessageRouter {

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
	protected void setTeamUpdateMessageSubscriber(
		TeamUpdateMessageSubscriber teamUpdateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(teamUpdateMessageSubscriber, properties);
	}

}