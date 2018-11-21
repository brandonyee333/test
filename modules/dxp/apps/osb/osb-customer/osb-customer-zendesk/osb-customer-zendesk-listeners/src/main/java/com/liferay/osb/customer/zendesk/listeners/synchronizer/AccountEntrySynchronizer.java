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
import com.liferay.osb.customer.zendesk.listeners.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.listeners.util.ZendeskModelListenerUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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

	public void add(AccountEntry accountEntry) throws PortalException {
		try {
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
				SupportRegionLocalServiceUtil.getSupportRegion(
					supportRegionIds[0]);

			_zendeskOrganizationWebService.createOrUpdateZendeskOrganization(
				accountEntry.getCode(),
				ZendeskModelListenerUtil.convertAddressToString(
					accountEntry.getAddress()),
				String.valueOf(accountEntry.getAccountEntryId()),
				accountEntry.getName(), accountEntry.getNotes(),
				String.valueOf(accountEntry.getPartnerManagedSupport()),
				jiraProjectKey, partnerEntryCode, supportLevelLabel,
				accountEntry.getStatusLabel(),
				AccountEntryConstants.getLanguageLabel(languageIds[0]),
				supportRegion.getName(),
				AccountEntryConstants.getTierLabel(accountEntry.getTier()),
				getTags(accountEntry));
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	public void addAccountCustomers(AccountEntry accountEntry)
		throws PortalException {

		try {
			for (AccountCustomer accountCustomer :
					accountEntry.getAccountCustomers()) {

				_accountCustomerSynchronizer.add(accountCustomer);
			}
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	public void addPartnerManagedSupport(AccountEntry accountEntry)
		throws PortalException {

		try {
			List<PartnerWorker> partnerWorkers =
				PartnerWorkerLocalServiceUtil.getPartnerWorkers(
					accountEntry.getPartnerEntryId());

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountEntry.getAccountEntryId());

			for (PartnerWorker partnerWorker : partnerWorkers) {
				if (partnerWorker.getRole() ==
						PartnerWorkerConstants.ROLE_WATCHER) {

					continue;
				}

				User user = _userLocalService.getUser(
					partnerWorker.getUserId());

				Set<String> tags = new HashSet<>();

				tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
				tags.add(ZendeskTagConstants.OSB_PARTNER);

				long zendeskUserId = _userSynchronizer.sync(
					user, 0, null, tags);

				_zendeskUserWebService.createZendeskUserOrganizationMemberships(
					zendeskUserId, new long[] {zendeskOrganizationId});

				_zendeskUserWebService.
					createZendeskUserOrganizationSubscription(
						zendeskUserId, zendeskOrganizationId);
			}
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	public void removeObsoleteTags(AccountEntry accountEntry)
		throws PortalException {

		try {
			for (AccountCustomer accountCustomer :
					accountEntry.getAccountCustomers()) {

				_userSynchronizer.removeObsoleteTags(
					accountCustomer.getUserId());
			}
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	public void removePartnerManagedSupport(AccountEntry accountEntry)
		throws PortalException {

		try {
			List<PartnerWorker> partnerWorkers =
				PartnerWorkerLocalServiceUtil.getPartnerWorkers(
					accountEntry.getPartnerEntryId());

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountEntry.getAccountEntryId());

			for (PartnerWorker partnerWorker : partnerWorkers) {
				if (partnerWorker.getRole() ==
						PartnerWorkerConstants.ROLE_WATCHER) {

					continue;
				}

				long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
					partnerWorker.getUserId());

				_zendeskUserWebService.deleteZendeskUserOrganizationMemberships(
					zendeskUserId, new long[] {zendeskOrganizationId});

				_userSynchronizer.removeObsoleteTags(partnerWorker.getUserId());
			}
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
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

			String zendeskTag = ZendeskModelListenerUtil.convertToTag(
				productEntry);

			if (Validator.isNotNull(zendeskTag)) {
				tags.add(zendeskTag);
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
		AccountEntrySynchronizer.class);

	@Reference
	private AccountCustomerSynchronizer _accountCustomerSynchronizer;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskOrganizationWebService _zendeskOrganizationWebService;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _zendeskUserWebService;

}