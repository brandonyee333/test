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

package com.liferay.osb.customer.zendesk.model.listener.synchronizer;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = AccountCustomerSynchronizer.class)
public class AccountCustomerSynchronizer {

	/*
	TODO
	public void add(AccountCustomer accountCustomer) throws PortalException {
		User user = _userLocalService.getUser(accountCustomer.getUserId());

		AccountEntry accountEntry = accountCustomer.getAccountEntry();

		_userSynchronizer.update(user, accountEntry.getName());
	}

	public void addOrganizationSubscription(AccountCustomer accountCustomer)
		throws PortalException {

		long accountEntryId = accountCustomer.getAccountEntryId();

		if (accountEntryId > 0) {
			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(accountEntryId);
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());

			if ((zendeskOrganizationId > 0) && (zendeskUserId > 0)) {
				_zendeskUserWebService.
					createZendeskUserOrganizationSubscription(
						zendeskUserId, zendeskOrganizationId);
			}
		}
	}

	public void remove(AccountCustomer accountCustomer) throws PortalException {
		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountCustomer.getAccountEntryId());
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			accountCustomer.getUserId());

		if ((zendeskOrganizationId > 0) && (zendeskUserId > 0)) {
			_zendeskOrganizationMembershipWebService.
				deleteOrganizationMemberships(
					zendeskUserId, new long[] {zendeskOrganizationId});

			_userSynchronizer.updateTags(accountCustomer.getUserId());
		}
	}

	public void update(AccountCustomer accountCustomer) throws PortalException {
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			accountCustomer.getUserId());

		if (zendeskUserId > 0) {
			_userSynchronizer.updateTags(accountCustomer.getUserId());
		}
	}

	@Reference
	private UserLocalService _userLocalService;

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

	*/

}