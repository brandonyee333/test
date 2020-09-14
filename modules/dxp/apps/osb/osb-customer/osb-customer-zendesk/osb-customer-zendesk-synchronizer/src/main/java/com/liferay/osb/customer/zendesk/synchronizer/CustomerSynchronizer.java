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

package com.liferay.osb.customer.zendesk.synchronizer;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
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

		_userSynchronizer.update(user, account.getName());

		if (accountEntry.isActiveTicketSupport()) {
			addOrganizationSubscription(account, accountEntry, user);
		}
	}

	public void remove(User user, AccountEntry accountEntry) throws Exception {
		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountEntry.getAccountEntryId());
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		if ((zendeskOrganizationId > 0) && (zendeskUserId > 0)) {
			_zendeskOrganizationMembershipWebService.
				deleteOrganizationMemberships(
					zendeskUserId, new long[] {zendeskOrganizationId});

			_userSynchronizer.updateTags(user);
		}
	}

	public void update(User user) throws Exception {
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

	@Reference(target = "(provider=web)")
	private UserIdentityProvider _userIdentityProvider;

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