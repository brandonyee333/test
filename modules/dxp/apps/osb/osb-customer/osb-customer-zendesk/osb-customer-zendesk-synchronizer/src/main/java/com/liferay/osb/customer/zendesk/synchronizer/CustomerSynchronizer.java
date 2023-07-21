/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.synchronizer;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationMembershipWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = CustomerSynchronizer.class)
public class CustomerSynchronizer {

	public void add(User user, Account account, AccountEntry accountEntry)
		throws Exception {

		if (!ZendeskSynchronizerThreadLocal.isEnabled()) {
			return;
		}

		if (accountEntry != null) {
			_userSynchronizer.update(user, account.getName());

			addOrganizationSubscription(account, accountEntry, user);
		}
		else {
			_userSynchronizer.update(user, null);
		}
	}

	public void remove(User user, AccountEntry accountEntry) throws Exception {
		if (!ZendeskSynchronizerThreadLocal.isEnabled()) {
			return;
		}

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		if (zendeskUserId <= 0) {
			return;
		}

		if (accountEntry != null) {
			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountEntry.getAccountEntryId());

			if (zendeskOrganizationId > 0) {
				_zendeskOrganizationMembershipWebService.
					deleteOrganizationMemberships(
						zendeskUserId, new long[] {zendeskOrganizationId});
			}
		}

		_userSynchronizer.update(user, null);
	}

	public void update(User user) throws Exception {
		if (!ZendeskSynchronizerThreadLocal.isEnabled()) {
			return;
		}

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		if (zendeskUserId > 0) {
			_userSynchronizer.updateTags(user);
		}
	}

	protected void addOrganizationSubscription(
			Account account, AccountEntry accountEntry, User user)
		throws Exception {

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountEntry.getAccountEntryId());
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		if ((zendeskOrganizationId > 0) && (zendeskUserId > 0)) {
			_zendeskUserWebService.createZendeskUserOrganizationSubscription(
				zendeskUserId, zendeskOrganizationId);
		}
	}

	@Reference
	private AccountReader _accountReader;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference(target = "(async=true)")
	private ZendeskOrganizationMembershipWebService
		_zendeskOrganizationMembershipWebService;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _zendeskUserWebService;

}