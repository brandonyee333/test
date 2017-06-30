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
import com.liferay.osb.service.AppAuditLocalServiceUtil;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppEntryRelLocalServiceUtil;
import com.liferay.osb.service.AppEntryRelServiceUtil;
import com.liferay.osb.service.AppEntryServiceUtil;
import com.liferay.osb.service.AppFlagLocalServiceUtil;
import com.liferay.osb.service.AppFlagServiceUtil;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.AppPackagePluginLocalServiceUtil;
import com.liferay.osb.service.AppPackagePluginServiceUtil;
import com.liferay.osb.service.AppPackageServiceUtil;
import com.liferay.osb.service.AppPricingItemLocalServiceUtil;
import com.liferay.osb.service.AppPricingItemServiceUtil;
import com.liferay.osb.service.AppPricingLocalServiceUtil;
import com.liferay.osb.service.AppPricingServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.AppVersionServiceUtil;
import com.liferay.osb.service.AssetAttachmentLocalServiceUtil;
import com.liferay.osb.service.AssetAttachmentServiceUtil;
import com.liferay.osb.service.AssetAuditLocalServiceUtil;
import com.liferay.osb.service.AssetAuditServiceUtil;
import com.liferay.osb.service.AssetLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetListAssetEntryLocalServiceUtil;
import com.liferay.osb.service.AssetListLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLicenseServiceUtil;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptRedeemTokenLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptRedeemTokenServiceUtil;
import com.liferay.osb.service.AssetReceiptServiceUtil;
import com.liferay.osb.service.AssetReceiptSupportLocalServiceUtil;
import com.liferay.osb.service.AssetRecommendationEntryLocalServiceUtil;
import com.liferay.osb.service.AssetRecommendationEntryServiceUtil;
import com.liferay.osb.service.AssetRecommendationSetLocalServiceUtil;
import com.liferay.osb.service.AssetStatsDayLocalServiceUtil;
import com.liferay.osb.service.AssetStatsMonthLocalServiceUtil;
import com.liferay.osb.service.AssetStatsWeekLocalServiceUtil;
import com.liferay.osb.service.AuditActionLocalServiceUtil;
import com.liferay.osb.service.AuditActionServiceUtil;
import com.liferay.osb.service.AuditEntryLocalServiceUtil;
import com.liferay.osb.service.AuditEntryServiceUtil;
import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.ContractAuditLocalServiceUtil;
import com.liferay.osb.service.ContractAuditServiceUtil;
import com.liferay.osb.service.ContractEntryLocalServiceUtil;
import com.liferay.osb.service.ContractEntryServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.CorpEntryServiceUtil;
import com.liferay.osb.service.CorpGroupLocalServiceUtil;
import com.liferay.osb.service.CorpGroupServiceUtil;
import com.liferay.osb.service.CorpMembershipRequestLocalServiceUtil;
import com.liferay.osb.service.CorpMembershipRequestServiceUtil;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.service.CorpProjectMessageLocalServiceUtil;
import com.liferay.osb.service.CorpProjectMessageServiceUtil;
import com.liferay.osb.service.CorpProjectServiceUtil;
import com.liferay.osb.service.CountryAppPricingLocalServiceUtil;
import com.liferay.osb.service.CountryAppPricingServiceUtil;
import com.liferay.osb.service.CurrencyEntryLocalServiceUtil;
import com.liferay.osb.service.CurrencyEntryServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryServiceUtil;
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
import com.liferay.osb.service.LCSSubscriptionEntryServiceUtil;
import com.liferay.osb.service.LicenseEntryLocalServiceUtil;
import com.liferay.osb.service.LicenseEntryServiceUtil;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyServiceUtil;
import com.liferay.osb.service.LicenseKeySetLocalServiceUtil;
import com.liferay.osb.service.LicenseKeySetServiceUtil;
import com.liferay.osb.service.MarketingEventLocalServiceUtil;
import com.liferay.osb.service.OSBCountryLocalServiceUtil;
import com.liferay.osb.service.OSBCountryServiceUtil;
import com.liferay.osb.service.OSBRegionLocalServiceUtil;
import com.liferay.osb.service.OSBRegionServiceUtil;
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
import com.liferay.osb.service.PortalReleaseLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.ProductEntryServiceUtil;
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
import com.liferay.osb.service.TicketCannedResponseLocalServiceUtil;
import com.liferay.osb.service.TicketCannedResponseServiceUtil;
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
import com.liferay.osb.service.TrainingCertificateLocalServiceUtil;
import com.liferay.osb.service.TrainingCertificateServiceUtil;
import com.liferay.osb.service.TrainingCertificateTemplateLocalServiceUtil;
import com.liferay.osb.service.TrainingCertificateTemplateServiceUtil;
import com.liferay.osb.service.TrainingCourseLocalServiceUtil;
import com.liferay.osb.service.TrainingCourseServiceUtil;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.TrainingCustomerServiceUtil;
import com.liferay.osb.service.TrainingEventLocalServiceUtil;
import com.liferay.osb.service.TrainingEventServiceUtil;
import com.liferay.osb.service.TrainingExamLocalServiceUtil;
import com.liferay.osb.service.TrainingExamResultItemLocalServiceUtil;
import com.liferay.osb.service.TrainingExamResultItemServiceUtil;
import com.liferay.osb.service.TrainingExamResultLocalServiceUtil;
import com.liferay.osb.service.TrainingExamResultSectionLocalServiceUtil;
import com.liferay.osb.service.TrainingExamResultSectionServiceUtil;
import com.liferay.osb.service.TrainingExamResultServiceUtil;
import com.liferay.osb.service.TrainingExamServiceUtil;
import com.liferay.osb.service.TrainingImportLogLocalServiceUtil;
import com.liferay.osb.service.TrainingLinkedUserLocalServiceUtil;
import com.liferay.osb.service.TrainingLocationLocalServiceUtil;
import com.liferay.osb.service.TrainingLocationServiceUtil;
import com.liferay.osb.service.TrainingWorkerLocalServiceUtil;
import com.liferay.osb.service.UserProfileHistoryLocalServiceUtil;
import com.liferay.osb.service.UserProfileHistoryServiceUtil;
import com.liferay.osb.service.UserProfileLocalServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Brian Wing Shun Chan
 */
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
			AppAuditLocalServiceUtil.clearService();

			AppEntryLocalServiceUtil.clearService();

			AppEntryServiceUtil.clearService();
			AppEntryRelLocalServiceUtil.clearService();

			AppEntryRelServiceUtil.clearService();
			AppFlagLocalServiceUtil.clearService();

			AppFlagServiceUtil.clearService();
			AppPackageLocalServiceUtil.clearService();

			AppPackageServiceUtil.clearService();
			AppPackagePluginLocalServiceUtil.clearService();

			AppPackagePluginServiceUtil.clearService();
			AppPricingLocalServiceUtil.clearService();

			AppPricingServiceUtil.clearService();
			AppPricingItemLocalServiceUtil.clearService();

			AppPricingItemServiceUtil.clearService();
			AppVersionLocalServiceUtil.clearService();

			AppVersionServiceUtil.clearService();
			AssetAttachmentLocalServiceUtil.clearService();

			AssetAttachmentServiceUtil.clearService();
			AssetAuditLocalServiceUtil.clearService();

			AssetAuditServiceUtil.clearService();
			AssetLicenseLocalServiceUtil.clearService();

			AssetListLocalServiceUtil.clearService();

			AssetListAssetEntryLocalServiceUtil.clearService();

			AssetReceiptLocalServiceUtil.clearService();

			AssetReceiptServiceUtil.clearService();
			AssetReceiptLicenseLocalServiceUtil.clearService();

			AssetReceiptLicenseServiceUtil.clearService();
			AssetReceiptRedeemTokenLocalServiceUtil.clearService();

			AssetReceiptRedeemTokenServiceUtil.clearService();
			AssetReceiptSupportLocalServiceUtil.clearService();

			AssetRecommendationEntryLocalServiceUtil.clearService();

			AssetRecommendationEntryServiceUtil.clearService();
			AssetRecommendationSetLocalServiceUtil.clearService();

			AssetStatsDayLocalServiceUtil.clearService();

			AssetStatsMonthLocalServiceUtil.clearService();

			AssetStatsWeekLocalServiceUtil.clearService();

			AuditActionLocalServiceUtil.clearService();

			AuditActionServiceUtil.clearService();
			AuditEntryLocalServiceUtil.clearService();

			AuditEntryServiceUtil.clearService();
			ContractAuditLocalServiceUtil.clearService();

			ContractAuditServiceUtil.clearService();
			ContractEntryLocalServiceUtil.clearService();

			ContractEntryServiceUtil.clearService();
			CorpEntryLocalServiceUtil.clearService();

			CorpEntryServiceUtil.clearService();
			CorpGroupLocalServiceUtil.clearService();

			CorpGroupServiceUtil.clearService();
			CorpMembershipRequestLocalServiceUtil.clearService();

			CorpMembershipRequestServiceUtil.clearService();
			CorpProjectLocalServiceUtil.clearService();

			CorpProjectServiceUtil.clearService();
			CorpProjectMessageLocalServiceUtil.clearService();

			CorpProjectMessageServiceUtil.clearService();
			CountryAppPricingLocalServiceUtil.clearService();

			CountryAppPricingServiceUtil.clearService();
			CurrencyEntryLocalServiceUtil.clearService();

			CurrencyEntryServiceUtil.clearService();
			DeveloperEntryLocalServiceUtil.clearService();

			DeveloperEntryServiceUtil.clearService();
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

			LCSSubscriptionEntryServiceUtil.clearService();
			LicenseEntryLocalServiceUtil.clearService();

			LicenseEntryServiceUtil.clearService();
			LicenseKeyLocalServiceUtil.clearService();

			LicenseKeyServiceUtil.clearService();
			LicenseKeySetLocalServiceUtil.clearService();

			LicenseKeySetServiceUtil.clearService();
			MarketingEventLocalServiceUtil.clearService();

			OfferingBundleLocalServiceUtil.clearService();

			OfferingBundleServiceUtil.clearService();
			OfferingDefinitionLocalServiceUtil.clearService();

			OfferingDefinitionServiceUtil.clearService();
			OfferingEntryLocalServiceUtil.clearService();

			OfferingEntryServiceUtil.clearService();
			OrderEntryLocalServiceUtil.clearService();

			OrderEntryServiceUtil.clearService();
			OSBCountryLocalServiceUtil.clearService();

			OSBCountryServiceUtil.clearService();
			OSBRegionLocalServiceUtil.clearService();

			OSBRegionServiceUtil.clearService();
			PartnerEntryLocalServiceUtil.clearService();

			PartnerEntryServiceUtil.clearService();
			PartnerWorkerLocalServiceUtil.clearService();

			PartnerWorkerServiceUtil.clearService();
			PortalReleaseLocalServiceUtil.clearService();

			ProductEntryLocalServiceUtil.clearService();

			ProductEntryServiceUtil.clearService();
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
			TicketCannedResponseLocalServiceUtil.clearService();

			TicketCannedResponseServiceUtil.clearService();
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
			TrainingCertificateLocalServiceUtil.clearService();

			TrainingCertificateServiceUtil.clearService();
			TrainingCertificateTemplateLocalServiceUtil.clearService();

			TrainingCertificateTemplateServiceUtil.clearService();
			TrainingCourseLocalServiceUtil.clearService();

			TrainingCourseServiceUtil.clearService();
			TrainingCustomerLocalServiceUtil.clearService();

			TrainingCustomerServiceUtil.clearService();
			TrainingEventLocalServiceUtil.clearService();

			TrainingEventServiceUtil.clearService();
			TrainingExamLocalServiceUtil.clearService();

			TrainingExamServiceUtil.clearService();
			TrainingExamResultLocalServiceUtil.clearService();

			TrainingExamResultServiceUtil.clearService();
			TrainingExamResultItemLocalServiceUtil.clearService();

			TrainingExamResultItemServiceUtil.clearService();
			TrainingExamResultSectionLocalServiceUtil.clearService();

			TrainingExamResultSectionServiceUtil.clearService();
			TrainingImportLogLocalServiceUtil.clearService();

			TrainingLinkedUserLocalServiceUtil.clearService();

			TrainingLocationLocalServiceUtil.clearService();

			TrainingLocationServiceUtil.clearService();
			TrainingWorkerLocalServiceUtil.clearService();

			UserProfileLocalServiceUtil.clearService();

			UserProfileHistoryLocalServiceUtil.clearService();

			UserProfileHistoryServiceUtil.clearService();
		}
	}
}