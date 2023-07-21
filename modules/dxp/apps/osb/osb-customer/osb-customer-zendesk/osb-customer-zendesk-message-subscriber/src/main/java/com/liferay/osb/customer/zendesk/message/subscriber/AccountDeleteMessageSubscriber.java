/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.message.subscriber;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.zendesk.synchronizer.AccountSynchronizer;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "topic.pattern=koroneiki.account.delete",
	service = AccountDeleteMessageSubscriber.class
)
public class AccountDeleteMessageSubscriber
	extends BaseMessageSubscriber implements MessageSubscriber {

	@Override
	protected void doReceive(JSONObject jsonObject) throws Exception {
		Account account = AccountSerDes.toDTO(jsonObject.getString("account"));

		AccountEntry accountEntry =
			_accountEntryLocalService.fetchKoroneikiAccountEntry(
				account.getKey());

		if (accountEntry == null) {
			return;
		}

		_accountSynchronizer.remove(account, accountEntry);

		_accountEntryLocalService.deleteAccountEntry(
			accountEntry.getAccountEntryId());
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountSynchronizer _accountSynchronizer;

}