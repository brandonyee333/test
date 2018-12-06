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

package com.liferay.osb.customer.zendesk.listeners.synchronizer;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.listeners.exception.AccountCustomerRemovalException;
import com.liferay.osb.customer.zendesk.listeners.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.HashSet;
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
		try {
			User user = _userLocalService.getUser(accountCustomer.getUserId());

			AccountEntry accountEntry = accountCustomer.getAccountEntry();

			Set<String> tags = getAddAccountCustomerTags(accountCustomer);

			_userSynchronizer.sync(
				user, accountEntry.getAccountEntryId(), accountEntry.getName(),
				tags);
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	public void addOrganizationSubscription(AccountCustomer accountCustomer)
		throws PortalException {

		long accountEntryId = accountCustomer.getAccountEntryId();

		if (accountEntryId > 0) {
			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(accountEntryId);
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());

			_zendeskUserWebService.createZendeskUserOrganizationSubscription(
				zendeskUserId, zendeskOrganizationId);
		}
	}

	public void reassignTickets(AccountCustomer accountCustomer)
		throws PortalException {

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			accountCustomer.getUserId());
		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountCustomer.getAccountEntryId());

		Set<String> criteria = new HashSet<>();

		criteria.add("organization:" + zendeskOrganizationId);
		criteria.add("requester:" + zendeskUserId);
		criteria.add("status<closed");

		List<ZendeskTicket> zendeskTickets =
			_zendeskTicketWebService.getZendeskTickets(criteria);

		if (!zendeskTickets.isEmpty()) {
			List<AccountCustomer> accountCustomers =
				AccountCustomerLocalServiceUtil.getAccountCustomers(
					accountCustomer.getAccountEntryId(),
					AccountCustomerConstants.ROLE_DEVELOPER);

			if (accountCustomers.isEmpty()) {
				throw new AccountCustomerRemovalException();
			}

			AccountCustomer newAccountCustomer = accountCustomers.get(0);

			long newZendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				newAccountCustomer.getUserId());

			for (ZendeskTicket zendeskTicket : zendeskTickets) {
				zendeskTicket.setRequesterId(newZendeskUserId);
			}

			_zendeskTicketWebService.updateZendeskTickets(zendeskTickets);
		}
	}

	public void remove(AccountCustomer accountCustomer) throws PortalException {
		try {
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());
			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountCustomer.getAccountEntryId());

			_zendeskUserWebService.deleteZendeskUserOrganizationMemberships(
				zendeskUserId, new long[] {zendeskOrganizationId});

			removeTags(accountCustomer, zendeskOrganizationId);
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	public void removeTags(
			AccountCustomer accountCustomer, long zendeskOrganizationId)
		throws PortalException {

		if (accountCustomer.getRole() ==
				AccountCustomerConstants.ROLE_WATCHER) {

			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());

			Set<String> tags = new HashSet<>();

			tags.add(ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));

			_zendeskUserWebService.deleteZendeskUserTags(zendeskUserId, tags);
		}

		_userSynchronizer.removeObsoleteTags(accountCustomer.getUserId());
	}

	public void update(AccountCustomer accountCustomer) throws PortalException {
		try {
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());

			Set<String> addTags = getAddAccountCustomerTags(accountCustomer);

			_zendeskUserWebService.addZendeskUserTags(zendeskUserId, addTags);

			if (accountCustomer.getRole() !=
					AccountCustomerConstants.ROLE_WATCHER) {

				long zendeskOrganizationId =
					_zendeskMapperUtil.fetchZendeskOrganizationId(
						accountCustomer.getAccountEntryId());

				Set<String> removeTags = new HashSet<>();

				removeTags.add(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));

				_zendeskUserWebService.deleteZendeskUserTags(
					zendeskUserId, removeTags);
			}

			_userSynchronizer.removeObsoleteTags(accountCustomer.getUserId());
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	protected Set<String> getAddAccountCustomerTags(
			AccountCustomer accountCustomer)
		throws PortalException {

		AccountEntry accountEntry = accountCustomer.getAccountEntry();

		Set<String> tags = new HashSet<>();

		if (accountCustomer.getRole() ==
				AccountCustomerConstants.ROLE_WATCHER) {

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountCustomer.getAccountEntryId());

			tags.add(ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
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

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountCustomerSynchronizer.class);

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _zendeskUserWebService;

}