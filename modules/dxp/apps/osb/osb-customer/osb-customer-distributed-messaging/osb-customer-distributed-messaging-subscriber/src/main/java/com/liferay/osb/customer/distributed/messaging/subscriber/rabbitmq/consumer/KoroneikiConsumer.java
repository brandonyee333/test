/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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