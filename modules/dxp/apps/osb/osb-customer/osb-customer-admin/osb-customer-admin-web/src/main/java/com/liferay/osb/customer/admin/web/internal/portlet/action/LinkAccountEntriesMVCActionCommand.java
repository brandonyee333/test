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

package com.liferay.osb.customer.admin.web.internal.portlet.action;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.web.internal.constants.CustomerAdminPortletKeys;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + CustomerAdminPortletKeys.ADMIN,
		"mvc.command.name=linkAccountEntries"
	},
	service = MVCActionCommand.class
)
public class LinkAccountEntriesMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		List<AccountEntry> accountEntries =
			_accountEntryLocalService.getAccountEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountEntry accountEntry : accountEntries) {
			List<Account> accounts = _accountWebService.search(
				"code eq '" + accountEntry.getCode() + "'", 1, 1000, null);

			if (accounts.isEmpty()) {
				_log.error(
					"No account was found for " + accountEntry.getCode());

				continue;
			}

			if (accounts.size() > 1) {
				_log.error(
					"There are multiple accounts for " +
						accountEntry.getCode());

				continue;
			}

			Account account = accounts.get(0);

			accountEntry.setKoroneikiAccountKey(account.getKey());

			_accountEntryLocalService.updateAccountEntry(accountEntry);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LinkAccountEntriesMVCActionCommand.class);

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountWebService _accountWebService;

}