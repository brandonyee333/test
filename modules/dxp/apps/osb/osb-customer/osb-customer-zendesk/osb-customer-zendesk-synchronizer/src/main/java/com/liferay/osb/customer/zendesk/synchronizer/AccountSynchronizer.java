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

import com.liferay.osb.customer.admin.constants.ExternalIdMapperConstants;
import com.liferay.osb.customer.admin.exception.RequiredAccountEntryException;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.service.ExternalIdMapperLocalService;
import com.liferay.osb.customer.admin.service.ProductEntryLocalService;
import com.liferay.osb.customer.koroneiki.constants.ProductConstants;
import com.liferay.osb.customer.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.customer.zendesk.constants.ZendeskLocales;
import com.liferay.osb.customer.zendesk.constants.ZendeskTicketConstants;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.synchronizer.util.AddressUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
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
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringBundler;
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

	public void addAccountCustomers(Account account) throws Exception {
		List<Contact> contacts = _contactWebService.getAccountContacts(
			account.getKey(), 1, 1000);

		for (Contact contact : contacts) {
			for (ContactRole contactRole : contact.getContactRoles()) {
				String name = contactRole.getName();

				if (name.equals("Customer Developer") ||
					name.equals("Customer Manager")) {

					//_accountCustomerSynchronizer.add(accountCustomer);
				}
			}

			if (hasActiveTicketSupport(account)) {
				/*_accountCustomerSynchronizer.addOrganizationSubscription(
				 accountCustomer);*/
			}
		}
	}

	public void addPartnerManagedSupport(Account account, Team team)
		throws Exception {

		List<Contact> contacts = _contactWebService.getTeamContacts(
			team.getKey(), 1, 1000);

		for (Contact contact : contacts) {
			for (ContactRole contactRole : contact.getContactRoles()) {
				String contactRoleName = contactRole.getName();

				if (contactRoleName.equals("Partner Manager") ||
					contactRoleName.equals("Partner Member")) {

					/*_partnerWorkerSynchronizer.add(
						accountEntry.getAccountEntryId(), partnerWorker);*/
				}
			}
		}
	}

	public void closeZendeskTickets(Account account) throws PortalException {
		AccountEntry accountEntry =
			_accountEntryLocalService.fetchKoroneikiAccountEntry(
				account.getKey());

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountEntry.getAccountEntryId());

		Set<String> criteria = new HashSet<>();

		criteria.add("organization:" + zendeskOrganizationId);
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

	/*
	TODO
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

	*/

	public List<ProductPurchase> getProductPurchases(String accountKey)
		throws Exception {

		StringBundler sb = new StringBundler(3);

		sb.append("accountKey eq '");
		sb.append(accountKey);
		sb.append("' and state eq 'active'");

		return _productPurchaseWebService.search(sb.toString(), 1, 1000);
	}

	public boolean hasActiveSupport(Account account) throws Exception {
		Date now = new Date();

		List<ProductPurchase> productPurchases = getProductPurchases(
			account.getKey());

		for (ProductPurchase productPurchase : productPurchases) {
			Product product = productPurchase.getProduct();

			String name = product.getName();

			if (name.equals(ProductConstants.NAME_GOLD) ||
				name.equals(ProductConstants.NAME_LIMITED) ||
				name.equals(ProductConstants.NAME_PLATINUM) ||
				name.equals(ProductConstants.NAME_SILVER)) {

				if (productPurchase.getPerpetual()) {
					return true;
				}

				Date endDate = productPurchase.getEndDate();

				if (now.before(endDate)) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean hasActiveTicketSupport(Account account) throws Exception {
		List<ProductPurchase> productPurchases = getProductPurchases(
			account.getKey());

		for (ProductPurchase productPurchase : productPurchases) {
			Map<String, String> properties = productPurchase.getProperties();

			if (properties.get("Tickets") != null) {
				return true;
			}
		}

		return false;
	}

	public void remove(Account account) throws Exception {
		AccountEntry accountEntry =
			_accountEntryLocalService.fetchKoroneikiAccountEntry(
				account.getKey());

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

		removeAccountCustomers(account);

		if (partnerManagedSupport(account)) {
			removePartnerManagedSupport(account);
		}

		_asyncZendeskOrganizationWebService.deleteZendeskOrganization(
			zendeskOrganizationId);
	}

	public void removeAccountCustomers(Account account) throws Exception {
		List<Contact> contacts = _contactWebService.getAccountContacts(
			account.getKey(), 1, 1000);

		for (Contact contact : contacts) {
			//_accountCustomerSynchronizer.remove(accountCustomer);
		}
	}

	public void removePartnerManagedSupport(Account account, Team team)
		throws Exception {

		List<Contact> contacts = _contactWebService.getTeamContacts(
			team.getKey(), 1, 1000);

		for (Contact contact : contacts) {
			for (ContactRole contactRole : contact.getContactRoles()) {
				String contactRoleName = contactRole.getName();

				if (contactRoleName.equals("Partner Manager") ||
					contactRoleName.equals("Partner Member")) {

					/*_partnerWorkerSynchronizer.remove(
						accountEntry.getAccountEntryId(), partnerWorker);*/
				}
			}
		}
	}

	public void update(Account account) throws Exception {
		AccountEntry accountEntry =
			_accountEntryLocalService.fetchKoroneikiAccountEntry(
				account.getKey());

		long classNameId = _classNameLocalService.getClassNameId(
			AccountEntry.class);

		boolean externalIdMappers =
			_externalIdMapperLocalService.hasExternalIdMappers(
				classNameId, accountEntry.getAccountEntryId(),
				ExternalIdMapperConstants.TYPE_ZENDESK);

		String address = StringPool.BLANK;
		String countryName = StringPool.BLANK;

		PostalAddress[] postalAddresses = account.getPostalAddresses();

		if ((postalAddresses != null) && (postalAddresses.length > 0)) {
			address = AddressUtil.convertAddressToString(postalAddresses[0]);
			countryName = postalAddresses[0].getAddressCountry();
		}

		boolean partnerManagedSupport = false;
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

						partnerManagedSupport = true;

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

				if (partnerManagedSupport) {
					break;
				}
			}
		}

		// TODO

		String supportRegion = StringPool.BLANK;
		String supportLanguage = StringPool.BLANK;

		_zendeskOrganizationWebService.createOrUpdateZendeskOrganization(
			account.getCode(), countryName, address,
			String.valueOf(account.getKey()), account.getName(),
			account.getNotes(), String.valueOf(partnerManagedSupport),
			partnerJiraProject, partnerName, getSupportLevel(account.getKey()),
			account.getStatusAsString(), supportLanguage, supportRegion,
			account.getTierAsString(), getTags(account));

		if (!externalIdMappers) {
			_asyncZendeskUserWebService.createOrUpdateZendeskUser(
				null, getDefaultUserEmail(accountEntry.getAccountEntryId()),
				ZendeskLocales.US, accountEntry.getCode(),
				accountEntry.getName(), null);
		}
	}

	public void updateTags(Account account) throws Exception {
		List<Contact> contacts = _contactWebService.getAccountContacts(
			account.getKey(), 1, 1000);

		for (Contact contact : contacts) {
			//_userSynchronizer.updateTags(accountCustomer.getUserId());
		}
	}

	protected String getDefaultUserEmail(long accountEntryId) {
		return "no-reply@" + String.valueOf(accountEntryId) + ".com.broken";
	}

	protected String getSupportLevel(String accountKey) throws Exception {
		List<ProductPurchase> productPurchases = getProductPurchases(
			accountKey);

		for (ProductPurchase productPurchase : productPurchases) {
			Product product = productPurchase.getProduct();

			String name = product.getName();

			if (name.equals(ProductConstants.NAME_FLOATING) ||
				name.equals(ProductConstants.NAME_GOLD) ||
				name.equals(ProductConstants.NAME_LIMITED) ||
				name.equals(ProductConstants.NAME_PLATINUM) ||
				name.equals(ProductConstants.NAME_SILVER)) {

				return StringUtil.replace(
					name, " Subscription", StringPool.BLANK);
			}
		}

		return StringPool.BLANK;
	}

	protected Set<String> getTags(Account account) throws Exception {
		Set<String> tags = new HashSet<>();

		Date now = new Date();

		List<ProductPurchase> productPurchases = getProductPurchases(
			account.getKey());

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

			String tag = productEntry.getZendeskTag();

			if (Validator.isNotNull(tag)) {
				tags.add(tag);
			}
		}

		return tags;
	}

	protected boolean partnerManagedSupport(Account account) {
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

	protected void removePartnerManagedSupport(Account account)
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
					removePartnerManagedSupport(account, team);
				}
			}
		}
	}

	@Reference
	private AccountCustomerSynchronizer _accountCustomerSynchronizer;

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference(target = "(async=true)")
	private ZendeskOrganizationWebService _asyncZendeskOrganizationWebService;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _asyncZendeskUserWebService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	@Reference
	private QueryFactory _queryFactory;

	@Reference
	private UserLocalService _userLocalService;

	/*@Reference
	private UserSynchronizer _userSynchronizer;*/

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskOrganizationWebService _zendeskOrganizationWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}