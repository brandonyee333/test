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

package com.liferay.osb.customer.koroneiki.subscriber;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.zendesk.constants.ZendeskDestinationNames;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductPurchaseSerDes;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "topic.pattern=koroneiki.productpurchase.create",
	service = ProductPurchaseCreateMessageSubscriber.class
)
public class ProductPurchaseCreateMessageSubscriber
	extends BaseMessageSubscriber implements MessageSubscriber {

	@Override
	public void doReceive(Message message) throws Exception {
		JSONObject jsonObject = jsonFactory.createJSONObject(
			(String)message.getPayload());

		ProductPurchase productPurchase = ProductPurchaseSerDes.toDTO(
			jsonObject.getString("productPurchase"));

		AccountEntry accountEntry =
			_accountEntryLocalService.fetchKoroneikiAccountEntry(
				productPurchase.getAccountKey());

		if (accountEntry == null) {
			Account account = _accountWebService.getAccount(
				productPurchase.getAccountKey());

			if (!isSyncAccount(account)) {
				return;
			}

			_accountEntryLocalService.addAccountEntry(
				OSBCustomerConstants.USER_DEFAULT_USER_ID, account.getKey(),
				getDossieraAccountKey(account.getExternalLinks()),
				getCorpProjectUuid(account.getExternalLinks()),
				getCorpProjectId(account.getExternalLinks()), account.getName(),
				account.getCode(), null, getStatus(account.getStatusAsString()),
				null, new long[0]);
		}

		sendMessage(
			ZendeskDestinationNames.PRODUCTPURCHASE_SYNC,
			message.getDestinationName(), (String)message.getPayload());
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountWebService _accountWebService;

}