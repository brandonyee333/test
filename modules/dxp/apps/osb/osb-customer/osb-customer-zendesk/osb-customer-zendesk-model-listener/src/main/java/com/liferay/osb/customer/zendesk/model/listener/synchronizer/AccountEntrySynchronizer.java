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
import com.liferay.osb.customer.zendesk.constants.ZendeskLocales;
import com.liferay.osb.customer.zendesk.constants.ZendeskTicketConstants;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.model.listener.util.ZendeskModelListenerUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.QueryFactory;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.osb.exception.RequiredAccountEntryException;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = AccountEntrySynchronizer.class)
public class AccountEntrySynchronizer {

	public void addAccountCustomers(AccountEntry accountEntry)
		throws PortalException {

		for (AccountCustomer accountCustomer :
				accountEntry.getAccountCustomers()) {

			_accountCustomerSynchronizer.add(accountCustomer);

			if (accountEntry.getActiveTicketSupport()) {
				_accountCustomerSynchronizer.addOrganizationSubscription(
					accountCustomer);
			}
		}
	}

	public void addPartnerManagedSupport(AccountEntry accountEntry)
		throws PortalException {

		List<PartnerWorker> partnerWorkers =
			PartnerWorkerLocalServiceUtil.getPartnerWorkers(
				accountEntry.getPartnerEntryId());

		for (PartnerWorker partnerWorker : partnerWorkers) {
			_partnerWorkerSynchronizer.add(
				accountEntry.getAccountEntryId(), partnerWorker);
		}
	}

	public void closeZendeskTickets(AccountEntry accountEntry)
		throws PortalException {

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

	public void remove(AccountEntry accountEntry) throws PortalException {
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

		removeAccountCustomers(accountEntry);

		if (accountEntry.isPartnerManagedSupport()) {
			removePartnerManagedSupport(accountEntry);
		}

		_asyncZendeskOrganizationWebService.deleteZendeskOrganization(
			zendeskOrganizationId);
	}

	public void removeAccountCustomers(AccountEntry accountEntry)
		throws PortalException {

		for (AccountCustomer accountCustomer :
				accountEntry.getAccountCustomers()) {

			_accountCustomerSynchronizer.remove(accountCustomer);
		}
	}

	public void removeObsoleteTags(AccountEntry accountEntry)
		throws PortalException {

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountEntry.getAccountEntryId());

		Set<String> removeTags = new HashSet<>();

		removeTags.add(
			ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));

		for (AccountCustomer accountCustomer :
				accountEntry.getAccountCustomers()) {

			_userSynchronizer.removeObsoleteTags(
				accountCustomer.getUserId(), null, removeTags);
		}
	}

	public void removePartnerManagedSupport(AccountEntry accountEntry)
		throws PortalException {

		List<PartnerWorker> partnerWorkers =
			PartnerWorkerLocalServiceUtil.getPartnerWorkers(
				accountEntry.getPartnerEntryId());

		for (PartnerWorker partnerWorker : partnerWorkers) {
			_partnerWorkerSynchronizer.remove(
				accountEntry.getAccountEntryId(), partnerWorker);
		}
	}

	public void update(AccountEntry accountEntry) throws PortalException {
		long classNameId = _classNameLocalService.getClassNameId(
			AccountEntry.class);

		boolean externalIdMappers =
			ExternalIdMapperLocalServiceUtil.hasExternalIdMappers(
				classNameId, accountEntry.getAccountEntryId(),
				ExternalIdMapperConstants.TYPE_ZENDESK);

		String countryName = StringPool.BLANK;

		Address address = accountEntry.getAddress();

		if (address != null) {
			Country country = address.getCountry();

			countryName = country.getName(LocaleUtil.ENGLISH);
		}

		String jiraProjectKey = StringPool.BLANK;
		String partnerEntryCode = StringPool.BLANK;

		PartnerEntry partnerEntry = accountEntry.getPartnerEntry();

		if (partnerEntry != null) {
			jiraProjectKey = partnerEntry.getJiraProjectKey();
			partnerEntryCode = partnerEntry.getCode();
		}

		String supportLevelLabel = StringPool.BLANK;

		SupportResponse supportResponse =
			SupportResponseLocalServiceUtil.fetchSupportResponse(
				accountEntry.getHighestSupportResponseId());

		if (supportResponse != null) {
			supportLevelLabel = supportResponse.getSupportLevelLabel();
		}

		String[] languageIds = accountEntry.getLanguageIds();

		long[] supportRegionIds = accountEntry.getSupportRegionIds();

		SupportRegion supportRegion =
			SupportRegionLocalServiceUtil.getSupportRegion(supportRegionIds[0]);

		_zendeskOrganizationWebService.createOrUpdateZendeskOrganization(
			accountEntry.getCode(), countryName,
			ZendeskModelListenerUtil.convertAddressToString(address),
			String.valueOf(accountEntry.getAccountEntryId()),
			accountEntry.getName(), accountEntry.getNotes(),
			String.valueOf(accountEntry.getPartnerManagedSupport()),
			jiraProjectKey, partnerEntryCode, supportLevelLabel,
			accountEntry.getStatusLabel(),
			AccountEntryConstants.getLanguageLabel(languageIds[0]),
			supportRegion.getName(),
			AccountEntryConstants.getTierLabel(accountEntry.getTier()),
			getTags(accountEntry));

		if (!externalIdMappers) {
			_asyncZendeskUserWebService.createOrUpdateZendeskUser(
				null, getDefaultUserEmail(accountEntry.getAccountEntryId()),
				ZendeskLocales.US, accountEntry.getCode(),
				accountEntry.getName(), null);
		}
	}

	protected String getDefaultUserEmail(long accountEntryId) {
		return "no-reply@" + String.valueOf(accountEntryId) + ".com.broken";
	}

	protected Set<String> getTags(AccountEntry accountEntry)
		throws PortalException {

		Set<String> tags = new HashSet<>();

		List<OfferingEntry> offeringEntries = accountEntry.getOfferingEntries();

		for (OfferingEntry offeringEntry : offeringEntries) {
			if ((offeringEntry.getStatus() !=
					OfferingEntryConstants.STATUS_ACTIVE) ||
				!offeringEntry.isSupportTickets()) {

				continue;
			}

			ProductEntry productEntry = offeringEntry.getProductEntry();

			long classNameId = _classNameLocalService.getClassNameId(
				ProductEntry.class);

			List<ExternalIdMapper> externalIdMappers =
				ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
					classNameId, productEntry.getProductEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK);

			if (!externalIdMappers.isEmpty()) {
				ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

				tags.add(externalIdMapper.getExternalId());
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

	@Reference
	private AccountCustomerSynchronizer _accountCustomerSynchronizer;

	@Reference(target = "(async=true)")
	private ZendeskOrganizationWebService _asyncZendeskOrganizationWebService;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _asyncZendeskUserWebService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private PartnerWorkerSynchronizer _partnerWorkerSynchronizer;

	@Reference
	private QueryFactory _queryFactory;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskOrganizationWebService _zendeskOrganizationWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}