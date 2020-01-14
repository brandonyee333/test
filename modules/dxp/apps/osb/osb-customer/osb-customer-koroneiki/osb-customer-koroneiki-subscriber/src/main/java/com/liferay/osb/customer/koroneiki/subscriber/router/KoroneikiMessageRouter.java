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

import com.liferay.osb.customer.koroneiki.subscriber.AccountContactAssignedMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.AccountContactRoleAssignedMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.AccountContactRoleUnassignedMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.AccountContactUnassignedMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.AccountCreateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.AccountDeleteMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.AccountTeamRoleAssignedMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.AccountTeamRoleUnassignedMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.AccountUpdateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.ProductCreateMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.ProductDeleteMessageSubscriber;
import com.liferay.osb.customer.koroneiki.subscriber.ProductUpdateMessageSubscriber;
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
	protected void setAccountContactAssignedMessageSubscriber(
		AccountContactAssignedMessageSubscriber
			accountContactAssignedMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(accountContactAssignedMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountContactRoleAssignedMessageSubscriber(
		AccountContactRoleAssignedMessageSubscriber
			accountContactRoleAssignedMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(accountContactRoleAssignedMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountContactRoleUnassignedMessageSubscriber(
		AccountContactRoleUnassignedMessageSubscriber
			accountContactRoleUnassignedMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(accountContactRoleUnassignedMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountContactUnassignedMessageSubscriber(
		AccountContactUnassignedMessageSubscriber
			accountContactUnassignedMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(accountContactUnassignedMessageSubscriber, properties);
	}

	@Reference(unbind = "-")
	protected void setAccountCreateMessageSubscriber(
		AccountCreateMessageSubscriber accountCreateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(accountCreateMessageSubscriber, properties);
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
	protected void setProductUpdateMessageSubscriber(
		ProductUpdateMessageSubscriber productUpdateMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(productUpdateMessageSubscriber, properties);
	}

}