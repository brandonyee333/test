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

package com.liferay.osb.customer.zendesk.connector.rabbitmq.listener;

import com.liferay.osb.customer.rabbitmq.connector.publisher.MessagePublisher;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.util.ZendeskModelListenerUtil;
import com.liferay.osb.customer.zendesk.model.ZendeskOrganization;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.IOException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

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

			ZendeskOrganization zendeskOrganization = getZendeskOrganization(
				accountEntry, null);

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.organization.create",
				zendeskOrganization.toJSONObject());
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

			Set<String> tags = getTags(accountEntry);

			ZendeskOrganization zendeskOrganization = getZendeskOrganization(
				accountEntry, tags);

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.organization.create.or.update",
				zendeskOrganization.toJSONObject());
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onBeforeUpdate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			AccountEntry oldAccountEntry =
				AccountEntryLocalServiceUtil.getAccountEntry(
					accountEntry.getAccountEntryId());

			if (oldAccountEntry.getStatus() == accountEntry.getStatus()) {
				return;
			}

			if (accountEntry.getStatus() == WorkflowConstants.STATUS_APPROVED) {
				updateAccountCustomers(accountEntry, true);
			}
			else {
				updateAccountCustomers(accountEntry, false);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
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
				productEntry.getName());

			tags.add(zendeskTag);
		}

		return tags;
	}

	protected ZendeskOrganization getZendeskOrganization(
			AccountEntry accountEntry, Set<String> tags)
		throws PortalException {

		ZendeskOrganization zendeskOrganization = new ZendeskOrganization();

		zendeskOrganization.setExternalId(
			String.valueOf(accountEntry.getAccountEntryId()));

		zendeskOrganization.setName(accountEntry.getName());
		zendeskOrganization.setPartnerFirstLineSupport(
			String.valueOf(accountEntry.getPartnerManagedSupport()));

		String partnerEntryCode = StringPool.BLANK;

		PartnerEntry partnerEntry = accountEntry.getPartnerEntry();

		if (partnerEntry != null) {
			partnerEntryCode = partnerEntry.getCode();
		}

		zendeskOrganization.setPartnerOrganization(partnerEntryCode);
		zendeskOrganization.setSharedComments(Boolean.TRUE.toString());
		zendeskOrganization.setSharedTickets(Boolean.TRUE.toString());

		String supportLevelLabel = StringPool.BLANK;

		SupportResponse supportResponse =
			SupportResponseLocalServiceUtil.fetchSupportResponse(
				accountEntry.getHighestSupportResponseId());

		if (supportResponse != null) {
			supportLevelLabel = supportResponse.getSupportLevelLabel();
		}

		zendeskOrganization.setSLA(supportLevelLabel);
		zendeskOrganization.setStatus(accountEntry.getStatusLabel());

		String[] languageIds = accountEntry.getLanguageIds();

		zendeskOrganization.setSupportLanguage(
			AccountEntryConstants.getLanguageLabel(languageIds[0]));

		long[] supportRegionIds = accountEntry.getSupportRegionIds();

		SupportRegion supportRegion =
			SupportRegionLocalServiceUtil.getSupportRegion(supportRegionIds[0]);

		zendeskOrganization.setSupportRegion(supportRegion.getName());

		zendeskOrganization.setTags(tags);

		zendeskOrganization.setTier(
			AccountEntryConstants.getTierLabel(accountEntry.getTier()));

		return zendeskOrganization;
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

			if (accountEntry.hasActiveSupportOffering()) {
				return true;
			}
		}

		return false;
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
		throws IOException, PortalException, TimeoutException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);

		List<AccountCustomer> accountCustomers =
			accountEntry.getAccountCustomers();

		for (AccountCustomer accountCustomer : accountCustomers) {
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());

			JSONObject tagsJSONObject =
				ZendeskModelListenerUtil.getTagsJSONObject(
					jsonArray, "users", zendeskUserId);

			if (approved) {
				_messagePublisher.sendMessage(
					ZendeskConnectorConfigurationValues.
						RABBITMQ_MESSAGE_EXCHANGE_NAME,
					"zendesk.service.tag.add", tagsJSONObject);
			}
			else {
				if (!hasActiveSupportOffering(accountCustomer, accountEntry)) {
					_messagePublisher.sendMessage(
						ZendeskConnectorConfigurationValues.
							RABBITMQ_MESSAGE_EXCHANGE_NAME,
						"zendesk.service.tag.delete", tagsJSONObject);
				}
			}
		}
	}

	@Reference
	private MessagePublisher _messagePublisher;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

}