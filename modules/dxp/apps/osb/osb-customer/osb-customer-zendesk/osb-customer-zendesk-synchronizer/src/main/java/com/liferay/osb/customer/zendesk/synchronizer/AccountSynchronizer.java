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

import com.liferay.osb.customer.admin.constants.AccountEntryConstants;
import com.liferay.osb.customer.admin.constants.ExternalIdMapperConstants;
import com.liferay.osb.customer.admin.exception.RequiredAccountEntryException;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.service.ExternalIdMapperLocalService;
import com.liferay.osb.customer.admin.service.ProductEntryLocalService;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.customer.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.osb.customer.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.customer.zendesk.constants.ZendeskLocales;
import com.liferay.osb.customer.zendesk.constants.ZendeskTicketConstants;
import com.liferay.osb.customer.zendesk.exception.PartnerWorkerRemovalException;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.synchronizer.util.AddressUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationMembershipWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.QueryFactory;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = AccountSynchronizer.class)
public class AccountSynchronizer {

	public void addCustomers(Account account, AccountEntry accountEntry)
		throws Exception {

		List<Contact> contacts = _contactWebService.getAccountCustomerContacts(
			account.getKey(), 1, 1000);

		for (Contact contact : contacts) {
			User user = _userIdentityProvider.fetchUserByEmailAddress(
				contact.getEmailAddress());

			if (user == null) {
				continue;
			}

			for (ContactRole contactRole : contact.getContactRoles()) {
				if (ArrayUtil.contains(
						ContactRoleConstants.SUPPORT_CONTACT_ROLES,
						contactRole.getName())) {

					_customerSynchronizer.add(user, account, accountEntry);

					break;
				}
			}
		}
	}

	public void addFirstLineSupport(
			Account account, AccountEntry accountEntry, Team team)
		throws Exception {

		List<Contact> contacts = _contactWebService.getTeamContacts(
			team.getKey(), 1, 1000);

		for (Contact contact : contacts) {
			User user = _userIdentityProvider.fetchUserByEmailAddress(
				contact.getEmailAddress());

			if (user == null) {
				continue;
			}

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountEntry.getAccountEntryId());

			long zendeskUserId = _userSynchronizer.update(user, null);

			addOrganizationMemberships(
				zendeskUserId, new long[] {zendeskOrganizationId});
		}
	}

	public void closeZendeskTickets(
			Account account, AccountEntry accountEntry, User user)
		throws PortalException {

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountEntry.getAccountEntryId());

		Set<String> criteria = new HashSet<>();

		criteria.add("organization:" + zendeskOrganizationId);

		if (user != null) {
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				user.getUserId());

			criteria.add("requester:" + zendeskUserId);
		}

		criteria.add("status<" + ZendeskTicketConstants.STATUS_CLOSED);

		ZendeskUser zendeskUser = _zendeskUserWebService.getZendeskUserByEmail(
			getDefaultUserEmail(accountEntry.getAccountEntryId()));

		List<ZendeskTicket> zendeskTickets =
			_zendeskTicketWebService.getZendeskTickets(criteria);

		for (ZendeskTicket zendeskTicket : zendeskTickets) {
			zendeskTicket.setRequesterId(zendeskUser.getZendeskUserId());
			zendeskTicket.setStatus("closed");
		}

		if (!zendeskTickets.isEmpty()) {
			_zendeskTicketWebService.updateZendeskTickets(zendeskTickets);
		}
	}

	public void reassignTickets(AccountEntry accountEntry, Team team, User user)
		throws Exception {

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountEntry.getAccountEntryId());
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		if ((zendeskOrganizationId == 0) || (zendeskUserId == 0)) {
			return;
		}

		Set<String> criteria = new HashSet<>();

		criteria.add("organization:" + zendeskOrganizationId);
		criteria.add("requester:" + zendeskUserId);
		criteria.add("status<closed");

		List<ZendeskTicket> zendeskTickets =
			_zendeskTicketWebService.getZendeskTickets(criteria);

		if (zendeskTickets.isEmpty()) {
			return;
		}

		User newUser = null;

		List<Contact> contacts = _contactWebService.getTeamContacts(
			team.getKey(), 1, 1000);

		for (Contact contact : contacts) {
			String emailAddress = contact.getEmailAddress();

			if (emailAddress.equals(user.getEmailAddress())) {
				continue;
			}

			User curUser = _userIdentityProvider.fetchUserByEmailAddress(
				emailAddress);

			if (curUser == null) {
				continue;
			}

			newUser = curUser;
		}

		if (newUser == null) {
			throw new PartnerWorkerRemovalException();
		}

		long newZendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			newUser.getUserId());

		for (ZendeskTicket zendeskTicket : zendeskTickets) {
			zendeskTicket.setRequesterId(newZendeskUserId);
			zendeskTicket.setZendeskOrganizationId(zendeskOrganizationId);
		}

		_zendeskTicketWebService.updateZendeskTickets(zendeskTickets);
	}

	public void reassignTickets(
			String accountKey, AccountEntry accountEntry, User user)
		throws Exception {

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountEntry.getAccountEntryId());
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		if ((zendeskOrganizationId == 0) || (zendeskUserId == 0)) {
			return;
		}

		long newZendeskUserId = 0;

		Set<String> criteria = new HashSet<>();

		criteria.add("organization:" + zendeskOrganizationId);
		criteria.add("requester:" + zendeskUserId);
		criteria.add("status<closed");

		List<ZendeskTicket> zendeskTickets =
			_zendeskTicketWebService.getZendeskTickets(criteria);

		if (!zendeskTickets.isEmpty()) {
			ZendeskUser zendeskUser = null;

			Account account = _accountWebService.getAccountContactsContactRoles(
				accountKey);

			Contact[] contacts = account.getCustomerContacts();

			if (!ArrayUtil.isEmpty(contacts)) {
				for (Contact contact : contacts) {
					String emailAddress = contact.getEmailAddress();

					if (emailAddress.equals(user.getEmailAddress())) {
						continue;
					}

					ContactRole[] contactRoles = contact.getContactRoles();

					if (ArrayUtil.isEmpty(contactRoles)) {
						continue;
					}

					for (ContactRole contactRole : contactRoles) {
						String name = contactRole.getName();

						if (name.equals(
								ContactRoleConstants.NAME_SUPPORT_DEVELOPER)) {

							User curUser =
								_userIdentityProvider.fetchUserByEmailAddress(
									contact.getEmailAddress());

							newZendeskUserId =
								_zendeskMapperUtil.fetchZendeskUserId(
									curUser.getUserId());
						}
					}
				}
			}
			else {
				zendeskUser = _zendeskUserWebService.getZendeskUserByEmail(
					getDefaultUserEmail(accountEntry.getAccountEntryId()));

				newZendeskUserId = zendeskUser.getZendeskUserId();
			}

			for (ZendeskTicket zendeskTicket : zendeskTickets) {
				zendeskTicket.setRequesterId(newZendeskUserId);
				zendeskTicket.setZendeskOrganizationId(zendeskOrganizationId);
			}

			_zendeskTicketWebService.updateZendeskTickets(zendeskTickets);
		}
	}

	public void remove(Account account, AccountEntry accountEntry)
		throws Exception {

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountEntry.getAccountEntryId());

		if (zendeskOrganizationId <= 0) {
			return;
		}

		Set<String> criteria = new HashSet<>();

		criteria.add("organization:" + zendeskOrganizationId);

		List<ZendeskTicket> zendeskTickets =
			_zendeskTicketWebService.getZendeskTickets(criteria);

		if (!zendeskTickets.isEmpty()) {
			throw new RequiredAccountEntryException(
				"You cannot delete projects with tickets");
		}

		Query query = _queryFactory.createQuery();

		query.addCriterion("organization_id:" + zendeskOrganizationId);
		query.addCriterion("user:no-reply@*");
		query.setPage(1);

		SearchHits<ZendeskUser> searchHits =
			_zendeskUserWebService.getZendeskUsers(query);

		List<ZendeskUser> zendeskUsers = searchHits.getResults();

		for (ZendeskUser zendeskUser : zendeskUsers) {
			_asyncZendeskUserWebService.deleteZendeskUser(
				zendeskUser.getZendeskUserId());
		}

		removeCustomers(account, accountEntry);

		if (hasFirstLineSupport(account)) {
			removeFirstLineSupport(account, accountEntry);
		}

		_asyncZendeskOrganizationWebService.deleteZendeskOrganization(
			zendeskOrganizationId);
	}

	public void removeCustomers(Account account, AccountEntry accountEntry)
		throws Exception {

		List<Contact> contacts = _contactWebService.getAccountCustomerContacts(
			account.getKey(), 1, 1000);

		for (Contact contact : contacts) {
			User user = _userIdentityProvider.fetchUserByEmailAddress(
				contact.getEmailAddress());

			if (user == null) {
				continue;
			}

			_customerSynchronizer.remove(user, accountEntry);
		}
	}

	public void removeFirstLineSupport(
			Account account, AccountEntry accountEntry, Team team)
		throws Exception {

		List<Contact> contacts = _contactWebService.getTeamContacts(
			team.getKey(), 1, 1000);

		for (Contact contact : contacts) {
			User user = _userIdentityProvider.fetchUserByEmailAddress(
				contact.getEmailAddress());

			if (user == null) {
				continue;
			}

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountEntry.getAccountEntryId());
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				user.getUserId());

			if ((zendeskOrganizationId > 0) && (zendeskUserId > 0)) {
				removeOrganizationMemberships(
					zendeskUserId, new long[] {zendeskOrganizationId});
			}

			_userSynchronizer.update(user, null);
		}
	}

	public void update(Account account, AccountEntry accountEntry)
		throws Exception {

		List<ProductPurchase> productPurchases =
			_accountReader.getProductPurchases(account.getKey());

		boolean externalIdMappers =
			_externalIdMapperLocalService.hasExternalIdMappers(
				_classNameLocalService.getClassNameId(AccountEntry.class),
				accountEntry.getAccountEntryId(),
				ExternalIdMapperConstants.TYPE_ZENDESK);

		String address = StringPool.BLANK;
		String countryName = StringPool.BLANK;

		PostalAddress[] postalAddresses = account.getPostalAddresses();

		if (!ArrayUtil.isEmpty(postalAddresses)) {
			address = AddressUtil.convertAddressToString(postalAddresses[0]);
			countryName = postalAddresses[0].getAddressCountry();
		}

		boolean firstLineSupport = false;
		String partnerName = StringPool.BLANK;
		String partnerJiraProject = StringPool.BLANK;

		if (account.getAssignedTeams() != null) {
			for (Team team : account.getAssignedTeams()) {
				if (team.getTeamRoles() == null) {
					continue;
				}

				for (TeamRole teamRole : team.getTeamRoles()) {
					String name = teamRole.getName();

					if (name.equals(
							TeamRoleConstants.NAME_FIRST_LINE_SUPPORT)) {

						firstLineSupport = true;

						partnerName = team.getName();

						for (ExternalLink teamExternalLink :
								team.getExternalLinks()) {

							String externalLinkDomain =
								teamExternalLink.getDomain();

							if (externalLinkDomain.equals("jira")) {
								partnerJiraProject =
									teamExternalLink.getEntityId();

								break;
							}
						}

						break;
					}
				}

				if (firstLineSupport) {
					break;
				}
			}
		}

		String[] languageIds = accountEntry.getLanguageIds();

		_zendeskOrganizationWebService.createOrUpdateZendeskOrganization(
			account.getCode(), countryName, address,
			String.valueOf(accountEntry.getAccountEntryId()), account.getName(),
			accountEntry.getInstructions(), String.valueOf(firstLineSupport),
			partnerJiraProject, partnerName, getSupportLevel(productPurchases),
			getStatus(productPurchases),
			AccountEntryConstants.getLanguageLabel(languageIds[0]),
			account.getRegionAsString(), account.getTierAsString(),
			getTags(productPurchases));

		if (!externalIdMappers) {
			_asyncZendeskUserWebService.createOrUpdateZendeskUser(
				null, getDefaultUserEmail(accountEntry.getAccountEntryId()),
				ZendeskLocales.US, accountEntry.getCode(),
				accountEntry.getName(), null);
		}
	}

	protected void addOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws Exception {

		if (ArrayUtil.isEmpty(zendeskOrganizationIds)) {
			return;
		}

		_zendeskOrganizationMembershipWebService.createOrganizationMemberships(
			zendeskUserId, zendeskOrganizationIds);

		for (long zendeskOrganizationId : zendeskOrganizationIds) {
			_asyncZendeskUserWebService.
				createZendeskUserOrganizationSubscription(
					zendeskUserId, zendeskOrganizationId);
		}
	}

	protected String getDefaultUserEmail(long accountEntryId) {
		return "no-reply@" + String.valueOf(accountEntryId) + ".com.broken";
	}

	protected String getStatus(List<ProductPurchase> productPurchases) {
		String state = _accountReader.getSubscriptionState(productPurchases);

		if (state.equals(ProductPurchaseConstants.STATE_ACTIVE)) {
			return "Approved";
		}
		else if (state.equals(ProductPurchaseConstants.STATE_EXPIRED)) {
			return "Expired";
		}

		return "Closed";
	}

	protected String getSupportLevel(List<ProductPurchase> productPurchases)
		throws Exception {

		ProductPurchase productPurchase = _accountReader.getSLAProductPurchase(
			productPurchases);

		if (productPurchase != null) {
			Product product = productPurchase.getProduct();

			return StringUtil.removeSubstring(
				product.getName(), " Subscription");
		}

		return StringPool.BLANK;
	}

	protected Set<String> getTags(List<ProductPurchase> productPurchases)
		throws Exception {

		Set<String> tags = new HashSet<>();

		Date now = new Date();

		for (ProductPurchase productPurchase : productPurchases) {
			Map<String, String> properties = productPurchase.getProperties();

			if ((!productPurchase.getPerpetual() &&
				 now.after(productPurchase.getEndDate())) ||
				((properties != null) && (properties.get("Tickets") == null))) {

				continue;
			}

			ProductEntry productEntry =
				_productEntryLocalService.getProductEntryByKoroneikiKey(
					productPurchase.getProductKey());

			if (Validator.isNotNull(productEntry.getZendeskTag())) {
				tags.add(productEntry.getZendeskTag());
			}
		}

		return tags;
	}

	protected boolean hasFirstLineSupport(Account account) {
		if (account.getAssignedTeams() == null) {
			return false;
		}

		for (Team team : account.getAssignedTeams()) {
			if (team.getTeamRoles() == null) {
				continue;
			}

			for (TeamRole teamRole : team.getTeamRoles()) {
				String name = teamRole.getName();

				if (name.equals(TeamRoleConstants.NAME_FIRST_LINE_SUPPORT)) {
					return true;
				}
			}
		}

		return false;
	}

	protected void removeFirstLineSupport(
			Account account, AccountEntry accountEntry)
		throws Exception {

		if (account.getAssignedTeams() == null) {
			return;
		}

		for (Team team : account.getAssignedTeams()) {
			if (team.getTeamRoles() == null) {
				continue;
			}

			for (TeamRole teamRole : team.getTeamRoles()) {
				String name = teamRole.getName();

				if (name.equals(TeamRoleConstants.NAME_FIRST_LINE_SUPPORT)) {
					removeFirstLineSupport(account, accountEntry, team);
				}
			}
		}
	}

	protected void removeOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws Exception {

		if (ArrayUtil.isEmpty(zendeskOrganizationIds)) {
			return;
		}

		_zendeskOrganizationMembershipWebService.deleteOrganizationMemberships(
			zendeskUserId, zendeskOrganizationIds);
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference(target = "(async=true)")
	private ZendeskOrganizationWebService _asyncZendeskOrganizationWebService;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _asyncZendeskUserWebService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private CustomerSynchronizer _customerSynchronizer;

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	@Reference
	private QueryFactory _queryFactory;

	@Reference(target = "(provider=web)")
	private UserIdentityProvider _userIdentityProvider;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference(target = "(async=true)")
	private ZendeskOrganizationMembershipWebService
		_zendeskOrganizationMembershipWebService;

	@Reference
	private ZendeskOrganizationWebService _zendeskOrganizationWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}