/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.messaging;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.AccountAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountAttachmentServiceUtil;
import com.liferay.osb.service.AccountCallLocalServiceUtil;
import com.liferay.osb.service.AccountCallServiceUtil;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountCustomerServiceUtil;
import com.liferay.osb.service.AccountEntryLanguageLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLanguageServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AccountEntryServiceUtil;
import com.liferay.osb.service.AccountEnvironmentAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentAttachmentServiceUtil;
import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentServiceUtil;
import com.liferay.osb.service.AccountInformationLocalServiceUtil;
import com.liferay.osb.service.AccountInformationServiceUtil;
import com.liferay.osb.service.AccountLinkLocalServiceUtil;
import com.liferay.osb.service.AccountLinkServiceUtil;
import com.liferay.osb.service.AccountProjectLocalServiceUtil;
import com.liferay.osb.service.AccountProjectServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerServiceUtil;
import com.liferay.osb.service.AuditActionLocalServiceUtil;
import com.liferay.osb.service.AuditActionServiceUtil;
import com.liferay.osb.service.AuditEntryLocalServiceUtil;
import com.liferay.osb.service.AuditEntryServiceUtil;
import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.service.CorpProjectMessageLocalServiceUtil;
import com.liferay.osb.service.CorpProjectMessageServiceUtil;
import com.liferay.osb.service.CorpProjectServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.FeedbackEntryLocalServiceUtil;
import com.liferay.osb.service.FeedbackEntryServiceUtil;
import com.liferay.osb.service.HolidayCalendarLocalServiceUtil;
import com.liferay.osb.service.HolidayCalendarRelLocalServiceUtil;
import com.liferay.osb.service.HolidayCalendarRelServiceUtil;
import com.liferay.osb.service.HolidayCalendarServiceUtil;
import com.liferay.osb.service.HolidayEntryLocalServiceUtil;
import com.liferay.osb.service.HolidayEntryServiceUtil;
import com.liferay.osb.service.LCSSubscriptionEntryLocalServiceUtil;
import com.liferay.osb.service.LicenseEntryLocalServiceUtil;
import com.liferay.osb.service.LicenseEntryServiceUtil;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyServiceUtil;
import com.liferay.osb.service.LicenseKeySetLocalServiceUtil;
import com.liferay.osb.service.LicenseKeySetServiceUtil;
import com.liferay.osb.service.OfferingBundleLocalServiceUtil;
import com.liferay.osb.service.OfferingBundleServiceUtil;
import com.liferay.osb.service.OfferingDefinitionLocalServiceUtil;
import com.liferay.osb.service.OfferingDefinitionServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryServiceUtil;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerEntryServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.ProductEntryServiceUtil;
import com.liferay.osb.service.RemoteCorpProjectLocalServiceUtil;
import com.liferay.osb.service.RemoteCorpProjectMessageLocalServiceUtil;
import com.liferay.osb.service.RemoteUserLocalServiceUtil;
import com.liferay.osb.service.SearchFilterLocalServiceUtil;
import com.liferay.osb.service.SearchFilterServiceUtil;
import com.liferay.osb.service.SecurityPatchLocalServiceUtil;
import com.liferay.osb.service.SecurityPatchServiceUtil;
import com.liferay.osb.service.SupportLaborLocalServiceUtil;
import com.liferay.osb.service.SupportLaborServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.service.SupportRegionServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.osb.service.SupportResponseServiceUtil;
import com.liferay.osb.service.SupportTeamLanguageLocalServiceUtil;
import com.liferay.osb.service.SupportTeamLanguageServiceUtil;
import com.liferay.osb.service.SupportTeamLocalServiceUtil;
import com.liferay.osb.service.SupportTeamServiceUtil;
import com.liferay.osb.service.SupportWorkerAccountTierLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerAccountTierServiceUtil;
import com.liferay.osb.service.SupportWorkerComponentLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerComponentServiceUtil;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerServiceUtil;
import com.liferay.osb.service.SupportWorkerSeverityLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerSeverityServiceUtil;
import com.liferay.osb.service.TicketAttachmentLocalServiceUtil;
import com.liferay.osb.service.TicketAttachmentServiceUtil;
import com.liferay.osb.service.TicketCallLocalServiceUtil;
import com.liferay.osb.service.TicketCallServiceUtil;
import com.liferay.osb.service.TicketCommentLocalServiceUtil;
import com.liferay.osb.service.TicketCommentServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.service.TicketEntryServiceUtil;
import com.liferay.osb.service.TicketFeedbackLocalServiceUtil;
import com.liferay.osb.service.TicketFeedbackServiceUtil;
import com.liferay.osb.service.TicketFlagLocalServiceUtil;
import com.liferay.osb.service.TicketFlagServiceUtil;
import com.liferay.osb.service.TicketInformationLocalServiceUtil;
import com.liferay.osb.service.TicketInformationServiceUtil;
import com.liferay.osb.service.TicketLinkLocalServiceUtil;
import com.liferay.osb.service.TicketLinkServiceUtil;
import com.liferay.osb.service.TicketSolutionLocalServiceUtil;
import com.liferay.osb.service.TicketSolutionServiceUtil;
import com.liferay.osb.service.TicketWorkerLocalServiceUtil;
import com.liferay.osb.service.TicketWorkerServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class ClpMessageListener extends BaseMessageListener {
	public static String getServletContextName() {
		return ClpSerializer.getServletContextName();
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (command.equals("undeploy") &&
				servletContextName.equals(getServletContextName())) {
			AccountAttachmentLocalServiceUtil.clearService();

			AccountAttachmentServiceUtil.clearService();
			AccountCallLocalServiceUtil.clearService();

			AccountCallServiceUtil.clearService();
			AccountCustomerLocalServiceUtil.clearService();

			AccountCustomerServiceUtil.clearService();
			AccountEntryLocalServiceUtil.clearService();

			AccountEntryServiceUtil.clearService();
			AccountEntryLanguageLocalServiceUtil.clearService();

			AccountEntryLanguageServiceUtil.clearService();
			AccountEnvironmentLocalServiceUtil.clearService();

			AccountEnvironmentServiceUtil.clearService();
			AccountEnvironmentAttachmentLocalServiceUtil.clearService();

			AccountEnvironmentAttachmentServiceUtil.clearService();
			AccountInformationLocalServiceUtil.clearService();

			AccountInformationServiceUtil.clearService();
			AccountLinkLocalServiceUtil.clearService();

			AccountLinkServiceUtil.clearService();
			AccountProjectLocalServiceUtil.clearService();

			AccountProjectServiceUtil.clearService();
			AccountWorkerLocalServiceUtil.clearService();

			AccountWorkerServiceUtil.clearService();
			AuditActionLocalServiceUtil.clearService();

			AuditActionServiceUtil.clearService();
			AuditEntryLocalServiceUtil.clearService();

			AuditEntryServiceUtil.clearService();
			CorpProjectLocalServiceUtil.clearService();

			CorpProjectServiceUtil.clearService();
			CorpProjectMessageLocalServiceUtil.clearService();

			CorpProjectMessageServiceUtil.clearService();
			ExternalIdMapperLocalServiceUtil.clearService();

			FeedbackEntryLocalServiceUtil.clearService();

			FeedbackEntryServiceUtil.clearService();
			HolidayCalendarLocalServiceUtil.clearService();

			HolidayCalendarServiceUtil.clearService();
			HolidayCalendarRelLocalServiceUtil.clearService();

			HolidayCalendarRelServiceUtil.clearService();
			HolidayEntryLocalServiceUtil.clearService();

			HolidayEntryServiceUtil.clearService();
			LCSSubscriptionEntryLocalServiceUtil.clearService();

			LicenseEntryLocalServiceUtil.clearService();

			LicenseEntryServiceUtil.clearService();
			LicenseKeyLocalServiceUtil.clearService();

			LicenseKeyServiceUtil.clearService();
			LicenseKeySetLocalServiceUtil.clearService();

			LicenseKeySetServiceUtil.clearService();
			OfferingBundleLocalServiceUtil.clearService();

			OfferingBundleServiceUtil.clearService();
			OfferingDefinitionLocalServiceUtil.clearService();

			OfferingDefinitionServiceUtil.clearService();
			OfferingEntryLocalServiceUtil.clearService();

			OfferingEntryServiceUtil.clearService();
			OrderEntryLocalServiceUtil.clearService();

			OrderEntryServiceUtil.clearService();
			PartnerEntryLocalServiceUtil.clearService();

			PartnerEntryServiceUtil.clearService();
			PartnerWorkerLocalServiceUtil.clearService();

			PartnerWorkerServiceUtil.clearService();
			ProductEntryLocalServiceUtil.clearService();

			ProductEntryServiceUtil.clearService();
			RemoteCorpProjectLocalServiceUtil.clearService();

			RemoteCorpProjectMessageLocalServiceUtil.clearService();

			RemoteUserLocalServiceUtil.clearService();

			SearchFilterLocalServiceUtil.clearService();

			SearchFilterServiceUtil.clearService();
			SecurityPatchLocalServiceUtil.clearService();

			SecurityPatchServiceUtil.clearService();
			SupportLaborLocalServiceUtil.clearService();

			SupportLaborServiceUtil.clearService();
			SupportRegionLocalServiceUtil.clearService();

			SupportRegionServiceUtil.clearService();
			SupportResponseLocalServiceUtil.clearService();

			SupportResponseServiceUtil.clearService();
			SupportTeamLocalServiceUtil.clearService();

			SupportTeamServiceUtil.clearService();
			SupportTeamLanguageLocalServiceUtil.clearService();

			SupportTeamLanguageServiceUtil.clearService();
			SupportWorkerLocalServiceUtil.clearService();

			SupportWorkerServiceUtil.clearService();
			SupportWorkerAccountTierLocalServiceUtil.clearService();

			SupportWorkerAccountTierServiceUtil.clearService();
			SupportWorkerComponentLocalServiceUtil.clearService();

			SupportWorkerComponentServiceUtil.clearService();
			SupportWorkerSeverityLocalServiceUtil.clearService();

			SupportWorkerSeverityServiceUtil.clearService();
			TicketAttachmentLocalServiceUtil.clearService();

			TicketAttachmentServiceUtil.clearService();
			TicketCallLocalServiceUtil.clearService();

			TicketCallServiceUtil.clearService();
			TicketCommentLocalServiceUtil.clearService();

			TicketCommentServiceUtil.clearService();
			TicketEntryLocalServiceUtil.clearService();

			TicketEntryServiceUtil.clearService();
			TicketFeedbackLocalServiceUtil.clearService();

			TicketFeedbackServiceUtil.clearService();
			TicketFlagLocalServiceUtil.clearService();

			TicketFlagServiceUtil.clearService();
			TicketInformationLocalServiceUtil.clearService();

			TicketInformationServiceUtil.clearService();
			TicketLinkLocalServiceUtil.clearService();

			TicketLinkServiceUtil.clearService();
			TicketSolutionLocalServiceUtil.clearService();

			TicketSolutionServiceUtil.clearService();
			TicketWorkerLocalServiceUtil.clearService();

			TicketWorkerServiceUtil.clearService();
		}
	}
}