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

package com.liferay.osb.customer.koroneiki.message.subscriber;

import com.liferay.osb.customer.admin.constants.AccountEntryConstants;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.subscription.util.DXPCloudStatusPageSubscriptionUtil;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=koroneiki.account.update",
	service = AccountUpdateMessageSubscriber.class
)
public class AccountUpdateMessageSubscriber
	extends BaseMessageSubscriber implements MessageSubscriber {

	@Override
	public void doReceive(Message message) throws Exception {
		JSONObject jsonObject = jsonFactory.createJSONObject(
			(String)message.getPayload());

		Account account = AccountSerDes.toDTO(jsonObject.getString("account"));

		AccountEntry accountEntry =
			accountEntryLocalService.fetchKoroneikiAccountEntry(
				account.getKey());

		List<ProductPurchase> productPurchases =
			accountReader.getProductPurchases(account.getKey());

		if (accountEntry == null) {
			if (!accountReader.isSyncAccount(productPurchases)) {
				return;
			}

			accountEntryLocalService.addAccountEntry(
				OSBCustomerConstants.USER_DEFAULT_USER_ID, account.getKey(),
				accountReader.getDossieraAccountKey(account.getExternalLinks()),
				accountReader.getCorpProjectUuid(account.getExternalLinks()),
				accountReader.getCorpProjectId(account.getExternalLinks()),
				account.getName(), account.getCode(), null,
				accountReader.getSupportEndDate(productPurchases),
				accountReader.getTicketSupportEndDate(productPurchases),
				accountReader.getStatus(account),
				new String[] {
					AccountEntryConstants.getLanguageId(account.getLanguage())
				});
		}
		else {
			accountEntryLocalService.updateAccountEntry(
				OSBCustomerConstants.USER_DEFAULT_USER_ID,
				accountEntry.getAccountEntryId(), account.getKey(),
				accountReader.getDossieraAccountKey(account.getExternalLinks()),
				accountReader.getCorpProjectUuid(account.getExternalLinks()),
				accountReader.getCorpProjectId(account.getExternalLinks()),
				account.getName(), account.getCode(),
				accountEntry.getInstructions(),
				accountReader.getSupportEndDate(productPurchases),
				accountReader.getTicketSupportEndDate(productPurchases),
				accountReader.getStatus(account),
				accountEntry.getLanguageIds());
		}

		_dxpCloudStatusPageSubscriptionUtil.syncAccount(account.getKey());
	}

	@Reference
	private DXPCloudStatusPageSubscriptionUtil
		_dxpCloudStatusPageSubscriptionUtil;

}