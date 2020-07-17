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
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.zendesk.synchronizer.util.AccountUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationMembershipWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = CustomerSynchronizer.class)
public class CustomerSynchronizer {

	public void add(Account account, Contact contact) throws Exception {
		User user = _userIdentityProvider.fetchUserByEmailAddress(
			contact.getEmailAddress());

		_userSynchronizer.update(user, account.getName());

		if (_accountUtil.hasActiveTicketSupport(account)) {
			AccountEntry accountEntry =
				_accountEntryLocalService.getKoroneikiAccountEntry(
					account.getKey());

			addOrganizationSubscription(
				account, contact, accountEntry.getAccountEntryId());
		}
	}

	public void remove(Contact contact, long accountEntryId) throws Exception {
		User user = _userIdentityProvider.fetchUserByEmailAddress(
			contact.getEmailAddress());

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(accountEntryId);
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		if ((zendeskOrganizationId > 0) && (zendeskUserId > 0)) {
			_zendeskOrganizationMembershipWebService.
				deleteOrganizationMemberships(
					zendeskUserId, new long[] {zendeskOrganizationId});

			_userSynchronizer.updateTags(user);
		}
	}

	public void update(Contact contact) throws Exception {
		User user = _userIdentityProvider.fetchUserByEmailAddress(
			contact.getEmailAddress());

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		if (zendeskUserId > 0) {
			_userSynchronizer.updateTags(user);
		}
	}

	protected void addOrganizationSubscription(
			Account account, Contact contact, long accountEntryId)
		throws Exception {

		User user = _userIdentityProvider.fetchUserByEmailAddress(
			contact.getEmailAddress());

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(accountEntryId);
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		if ((zendeskOrganizationId > 0) && (zendeskUserId > 0)) {
			_zendeskUserWebService.createZendeskUserOrganizationSubscription(
				zendeskUserId, zendeskOrganizationId);
		}
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountUtil _accountUtil;

	@Reference(target = "(provider=okta)")
	private UserIdentityProvider _userIdentityProvider;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference(target = "(async=true)")
	private ZendeskOrganizationMembershipWebService
		_zendeskOrganizationMembershipWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _zendeskUserWebService;

}