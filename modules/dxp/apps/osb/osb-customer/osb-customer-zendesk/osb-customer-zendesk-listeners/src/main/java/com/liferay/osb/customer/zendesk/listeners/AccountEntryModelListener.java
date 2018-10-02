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

package com.liferay.osb.customer.zendesk.listeners;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.listeners.util.AccountCustomerUtil;
import com.liferay.osb.customer.zendesk.listeners.util.PartnerWorkerUtil;
import com.liferay.osb.customer.zendesk.listeners.util.ZendeskModelListenerUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class AccountEntryModelListener extends BaseModelListener<AccountEntry> {

	@Override
	public void onAfterCreate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			if (!ZendeskModelListenerUtil.hasActiveSupportOffering(
					accountEntry)) {

				return;
			}

			createOrUpdateZendeskOrganization(accountEntry);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterUpdate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			long classNameId = ClassNameLocalServiceUtil.getClassNameId(
				AccountEntry.class);

			boolean externalIdMappers =
				ExternalIdMapperLocalServiceUtil.hasExternalIdMappers(
					classNameId, accountEntry.getAccountEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK);

			if (!externalIdMappers) {
				if (!ZendeskModelListenerUtil.hasActiveSupportOffering(
						accountEntry)) {

					return;
				}
			}

			createOrUpdateZendeskOrganization(accountEntry);

			addAccountCustomers(accountEntry);

			if (accountEntry.isPartnerManagedSupport()) {
				addPartnerWorkers(accountEntry);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onBeforeUpdate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			long classNameId = ClassNameLocalServiceUtil.getClassNameId(
				AccountEntry.class);

			boolean externalIdMappers =
				ExternalIdMapperLocalServiceUtil.hasExternalIdMappers(
					classNameId, accountEntry.getAccountEntryId(),
					ExternalIdMapperConstants.TYPE_ZENDESK);

			if (!externalIdMappers) {
				if (!ZendeskModelListenerUtil.hasActiveSupportOffering(
						accountEntry)) {

					return;
				}
			}

			AccountEntry oldAccountEntry =
				AccountEntryLocalServiceUtil.getAccountEntry(
					accountEntry.getAccountEntryId());

			if (oldAccountEntry.getStatus() != accountEntry.getStatus()) {
				if (accountEntry.getStatus() ==
						WorkflowConstants.STATUS_APPROVED) {

					updateAccountCustomers(accountEntry, true);
				}
				else {
					updateAccountCustomers(accountEntry, false);
				}
			}

			if (oldAccountEntry.isPartnerManagedSupport() &&
				!accountEntry.isPartnerManagedSupport()) {

				removePartnerWorkers(accountEntry);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void addAccountCustomers(AccountEntry accountEntry)
		throws PortalException {

		for (AccountCustomer accountCustomer :
				accountEntry.getAccountCustomers()) {

			_accountCustomerUtil.addAccountCustomer(accountCustomer);
		}
	}

	protected void addPartnerWorkers(AccountEntry accountEntry)
		throws PortalException {

		List<PartnerWorker> partnerWorkers =
			PartnerWorkerLocalServiceUtil.getPartnerWorkers(
				accountEntry.getPartnerEntryId());

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountEntry.getAccountEntryId());

		for (PartnerWorker partnerWorker : partnerWorkers) {
			if (partnerWorker.getRole() !=
					PartnerWorkerConstants.ROLE_WATCHER) {

				_partnerWorkerUtil.addPartnerWorker(
					partnerWorker, new long[] {zendeskOrganizationId});
			}
		}
	}

	protected void createOrUpdateZendeskOrganization(AccountEntry accountEntry)
		throws PortalException {

		String partnerEntryCode = StringPool.BLANK;

		PartnerEntry partnerEntry = accountEntry.getPartnerEntry();

		if (partnerEntry != null) {
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
			String.valueOf(accountEntry.getAccountEntryId()),
			accountEntry.getName(),
			String.valueOf(accountEntry.getPartnerManagedSupport()),
			partnerEntryCode, supportLevelLabel, accountEntry.getStatusLabel(),
			AccountEntryConstants.getLanguageLabel(languageIds[0]),
			supportRegion.getName(),
			AccountEntryConstants.getTierLabel(accountEntry.getTier()),
			getTags(accountEntry));
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

	protected boolean hasActiveSupportOffering(
		AccountCustomer accountCustomer, AccountEntry accountEntry) {

		List<AccountEntry> accountEntries =
			AccountEntryLocalServiceUtil.getUserAccountEntries(
				accountCustomer.getUserId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (AccountEntry curAccountEntry : accountEntries) {
			if (curAccountEntry.equals(accountEntry)) {
				continue;
			}

			if (curAccountEntry.hasActiveSupportOffering()) {
				return true;
			}
		}

		return false;
	}

	protected void removePartnerWorkers(AccountEntry accountEntry)
		throws PortalException {

		List<PartnerWorker> partnerWorkers =
			PartnerWorkerLocalServiceUtil.getPartnerWorkers(
				accountEntry.getPartnerEntryId());

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(
				accountEntry.getAccountEntryId());

		for (PartnerWorker partnerWorker : partnerWorkers) {
			if (partnerWorker.getRole() !=
					PartnerWorkerConstants.ROLE_WATCHER) {

				_partnerWorkerUtil.removePartnerWorker(
					partnerWorker, new long[] {zendeskOrganizationId});
			}
		}
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	protected void updateAccountCustomers(
			AccountEntry accountEntry, boolean approved)
		throws Exception {

		Set<String> tags = new HashSet<>();

		tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);

		for (AccountCustomer accountCustomer :
				accountEntry.getAccountCustomers()) {

			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());

			if (approved) {
				_zendeskUserWebService.addZendeskUserTags(zendeskUserId, tags);
			}
			else {
				if (!hasActiveSupportOffering(accountCustomer, accountEntry)) {
					_zendeskUserWebService.deleteZendeskUserTags(
						zendeskUserId, tags);
				}
			}
		}
	}

	@Reference
	private AccountCustomerUtil _accountCustomerUtil;

	@Reference
	private PartnerWorkerUtil _partnerWorkerUtil;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference(target = "(async=true)")
	private ZendeskOrganizationWebService _zendeskOrganizationWebService;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _zendeskUserWebService;

}