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
import com.liferay.osb.customer.zendesk.connector.rabbitmq.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.model.ZendeskOrganization;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.util.StringPool;

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
			if ((accountEntry.getStatus() !=
					WorkflowConstants.STATUS_APPROVED) ||
				!accountEntry.hasActiveSupportOffering()) {

				return;
			}

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
			zendeskOrganization.setSLA(StringPool.BLANK);
			zendeskOrganization.setStatus(accountEntry.getStatusLabel());

			String[] languageIds = accountEntry.getLanguageIds();

			zendeskOrganization.setSupportLanguage(
				AccountEntryConstants.getLanguageLabel(languageIds[0]));

			long[] supportRegionIds = accountEntry.getSupportRegionIds();

			SupportRegion supportRegion =
				SupportRegionLocalServiceUtil.getSupportRegion(
					supportRegionIds[0]);

			zendeskOrganization.setSupportRegion(supportRegion.getName());

			zendeskOrganization.setTier(
				AccountEntryConstants.getTierLabel(accountEntry.getTier()));

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.organization.add",
				zendeskOrganization.toJSONObject());
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference
	private MessagePublisher _messagePublisher;

}