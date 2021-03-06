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

package com.liferay.osb.customer.distributed.messaging.subscriber.rabbitmq.consumer;

import com.liferay.osb.customer.distributed.messaging.connector.rabbitmq.KoroneikiConnection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.Connection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.consumer.BaseConsumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = {
		"exchange=koroneiki_exchange", "queue=is_koroneiki_customer_queue",
		"exclusive=true", "routing.key=koroneiki.account.contactrole.assigned",
		"routing.key=koroneiki.account.contactrole.unassigned",
		"routing.key=koroneiki.account.create",
		"routing.key=koroneiki.account.delete",
		"routing.key=koroneiki.account.teamrole.assigned",
		"routing.key=koroneiki.account.teamrole.unassigned",
		"routing.key=koroneiki.account.update",
		"routing.key=koroneiki.entitlement.create",
		"routing.key=koroneiki.entitlement.delete",
		"routing.key=koroneiki.product.create",
		"routing.key=koroneiki.product.delete",
		"routing.key=koroneiki.product.update",
		"routing.key=koroneiki.productpurchase.create",
		"routing.key=koroneiki.productpurchase.delete",
		"routing.key=koroneiki.productpurchase.update",
		"routing.key=koroneiki.team.contactrole.assigned",
		"routing.key=koroneiki.team.contactrole.unassigned",
		"routing.key=koroneiki.team.update"
	},
	service = KoroneikiConsumer.class
)
public class KoroneikiConsumer extends BaseConsumer {

	@Override
	protected Connection getConnection() {
		return _koroneikiConnection;
	}

	@Reference
	private KoroneikiConnection _koroneikiConnection;

}