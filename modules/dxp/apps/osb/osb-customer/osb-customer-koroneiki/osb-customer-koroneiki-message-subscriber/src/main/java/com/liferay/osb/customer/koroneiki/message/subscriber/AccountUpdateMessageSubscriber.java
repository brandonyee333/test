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

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;

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

		if (accountEntry == null) {
			return;
		}

		accountEntryLocalService.updateAccountEntry(
			OSBCustomerConstants.USER_DEFAULT_USER_ID,
			accountEntry.getAccountEntryId(), account.getKey(),
			getDossieraAccountKey(account.getExternalLinks()),
			getCorpProjectUuid(account.getExternalLinks()),
			getCorpProjectId(account.getExternalLinks()), account.getName(),
			account.getCode(), accountEntry.getInstructions(),
			accountReader.getStatus(account), accountEntry.getLanguageIds());
	}

}