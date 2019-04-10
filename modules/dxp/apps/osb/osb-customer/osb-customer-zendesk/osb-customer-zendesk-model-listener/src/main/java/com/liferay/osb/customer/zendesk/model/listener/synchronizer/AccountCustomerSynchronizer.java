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

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.model.listener.exception.AccountCustomerRemovalException;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationMembershipWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = AccountCustomerSynchronizer.class)
public class AccountCustomerSynchronizer {

	public void add(AccountCustomer accountCustomer) throws PortalException {
		User user = _userLocalService.getUser(accountCustomer.getUserId());

		AccountEntry accountEntry = accountCustomer.getAccountEntry();

		Set<String> tags = getTags(accountCustomer);

		_userSynchronizer.update(user, accountEntry.getName(), tags);
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

	public void reassignTickets(AccountCustomer accountCustomer)
		throws PortalException {

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountCustomer.getAccountEntryId());
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			accountCustomer.getUserId());

		reassignTickets(
			accountCustomer.getUserId(), accountCustomer.getAccountEntryId(),
			zendeskOrganizationId, zendeskUserId);
	}

	public void reassignTickets(
			long userId, long accountEntryId, long zendeskOrganizationId,
			long zendeskUserId)
		throws PortalException {

		if ((zendeskOrganizationId > 0) && (zendeskUserId > 0)) {
			Set<String> criteria = new HashSet<>();

			criteria.add("organization:" + zendeskOrganizationId);
			criteria.add("requester:" + zendeskUserId);
			criteria.add("status<closed");

			List<ZendeskTicket> zendeskTickets =
				_zendeskTicketWebService.getZendeskTickets(criteria);

			if (!zendeskTickets.isEmpty()) {
				List<AccountCustomer> accountCustomers =
					AccountCustomerLocalServiceUtil.getAccountCustomers(
						accountEntryId,
						AccountCustomerConstants.ROLE_DEVELOPER);

				accountCustomers = ListUtil.copy(accountCustomers);

				Iterator<AccountCustomer> iterator =
					accountCustomers.iterator();

				while (iterator.hasNext()) {
					AccountCustomer accountCustomer = iterator.next();

					if (accountCustomer.getUserId() == userId) {
						iterator.remove();
					}
				}

				if (accountCustomers.isEmpty()) {
					throw new AccountCustomerRemovalException();
				}

				AccountCustomer newAccountCustomer = accountCustomers.get(0);

				long newZendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
					newAccountCustomer.getUserId());

				for (ZendeskTicket zendeskTicket : zendeskTickets) {
					zendeskTicket.setRequesterId(newZendeskUserId);
					zendeskTicket.setZendeskOrganizationId(
						zendeskOrganizationId);
				}

				_zendeskTicketWebService.updateZendeskTickets(zendeskTickets);
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

			removeTags(accountCustomer, zendeskOrganizationId);
		}
	}

	public void sync(ZendeskUser zendeskUser, AccountCustomer accountCustomer)
		throws PortalException {

		User user = _userLocalService.getUser(accountCustomer.getUserId());

		_userSynchronizer.sync(zendeskUser, user, getTags(accountCustomer));
	}

	public void update(AccountCustomer accountCustomer) throws PortalException {
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			accountCustomer.getUserId());

		if (zendeskUserId > 0) {
			Set<String> tags = getTags(accountCustomer);

			_zendeskUserWebService.addZendeskUserTags(zendeskUserId, tags);

			Set<String> removeTags = new HashSet<>();

			if (accountCustomer.getRole() !=
					AccountCustomerConstants.ROLE_WATCHER) {

				long zendeskOrganizationId =
					_zendeskMapperUtil.fetchZendeskOrganizationId(
						accountCustomer.getAccountEntryId());

				if (zendeskOrganizationId > 0) {
					removeTags.add(
						ZendeskTagConstants.getWatcherTag(
							zendeskOrganizationId));
				}
			}

			_userSynchronizer.removeObsoleteTags(
				accountCustomer.getUserId(), null, removeTags);
		}
	}

	protected Set<String> getTags(AccountCustomer accountCustomer)
		throws PortalException {

		AccountEntry accountEntry = accountCustomer.getAccountEntry();

		Set<String> tags = new HashSet<>();

		if (accountCustomer.getRole() ==
				AccountCustomerConstants.ROLE_WATCHER) {

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountCustomer.getAccountEntryId());

			if (zendeskOrganizationId > 0) {
				tags.add(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
			}
		}
		else {
			if (accountEntry.getActiveTicketSupport()) {
				tags.add(ZendeskTagConstants.OSB_CUSTOMER);
			}
		}

		if (accountEntry.getActiveSupport()) {
			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
		}

		return tags;
	}

	protected void removeTags(
			AccountCustomer accountCustomer, long zendeskOrganizationId)
		throws PortalException {

		Set<String> removeTags = new HashSet<>();

		if (accountCustomer.getRole() ==
				AccountCustomerConstants.ROLE_WATCHER) {

			removeTags.add(
				ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
		}

		_userSynchronizer.removeObsoleteTags(
			accountCustomer.getUserId(), null, removeTags);
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
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

}