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
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
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

			_userSynchronizer.sync(user, accountEntry.getName(), tags);
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	public void remove(AccountCustomer accountCustomer) throws PortalException {
		try {
			List<AccountCustomer> accountCustomers =
				AccountCustomerLocalServiceUtil.getAccountCustomers(
					accountCustomer.getAccountEntryId(),
					AccountCustomerConstants.ROLE_DEVELOPER);

			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());

			List<ZendeskTicket> zendeskTickets =
				_zendeskTicketWebService.getRequesterTickets(zendeskUserId);

			if (!zendeskTickets.isEmpty()) {
				if (accountCustomers.isEmpty()) {
					throw new AccountCustomerRemovalException();
				}

				AccountCustomer newAccountCustomer = accountCustomers.get(0);

				long newZendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
					newAccountCustomer.getUserId());

				for (ZendeskTicket zendeskTicket : zendeskTickets) {
					zendeskTicket.setRequesterId(newZendeskUserId);
				}

				_asyncZendeskTicketWebService.updateTickets(zendeskTickets);
			}

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountCustomer.getAccountEntryId());

			_zendeskUserWebService.deleteZendeskUserOrganizationMemberships(
				zendeskUserId, new long[] {zendeskOrganizationId});

			Set<String> tags = getDeleteAccountCustomerTags(
				accountCustomer, zendeskOrganizationId);

			_zendeskUserWebService.deleteZendeskUserTags(zendeskUserId, tags);
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	public void update(AccountCustomer accountCustomer) throws PortalException {
		try {
			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountCustomer.getAccountEntryId());
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());

			Set<String> addTags = new HashSet<>();
			Set<String> removeTags = new HashSet<>();

			if (accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_WATCHER) {

				addTags.add(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));

				if (AccountEntryLocalServiceUtil.getUserAccountEntriesCount(
						accountCustomer.getUserId()) == 1) {

					removeTags.add(ZendeskTagConstants.OSB_CUSTOMER);
				}
			}
			else {
				addTags.add(ZendeskTagConstants.OSB_CUSTOMER);

				if (AccountEntryLocalServiceUtil.hasValidSupportAccountEntry(
						accountCustomer.getUserId())) {

					addTags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
				}

				removeTags.add(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
			}

			_zendeskUserWebService.addZendeskUserTags(zendeskUserId, addTags);

			_zendeskUserWebService.deleteZendeskUserTags(
				zendeskUserId, removeTags);
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	protected Set<String> getAddAccountCustomerTags(
			AccountCustomer accountCustomer)
		throws PortalException {

		Set<String> tags = new HashSet<>();

		if (accountCustomer.getRole() ==
				AccountCustomerConstants.ROLE_WATCHER) {

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountCustomer.getAccountEntryId());

			tags.add(ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
		}
		else {
			tags.add(ZendeskTagConstants.OSB_CUSTOMER);
		}

		if (AccountEntryLocalServiceUtil.hasValidSupportAccountEntry(
				accountCustomer.getUserId())) {

			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
		}

		return tags;
	}

	protected Set<String> getDeleteAccountCustomerTags(
		AccountCustomer accountCustomer, long zendeskOrganizationId) {

		Set<String> tags = new HashSet<>();

		int accountEntriesCount =
			AccountEntryLocalServiceUtil.getUserAccountEntriesCount(
				accountCustomer.getUserId());

		if (accountEntriesCount == 0) {
			tags.add(ZendeskTagConstants.OSB_CUSTOMER);
			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
			tags.add(ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
		}
		else {
			if (accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_WATCHER) {

				tags.add(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
			}
			else {
				if (!AccountEntryLocalServiceUtil.hasValidSupportAccountEntry(
						accountCustomer.getUserId())) {

					tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
				}
			}
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

	@Reference(target = "(async=true)")
	private ZendeskTicketWebService _asyncZendeskTicketWebService;

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