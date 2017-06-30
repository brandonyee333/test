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

package com.liferay.osb.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.service.AccountAttachmentLocalService;
import com.liferay.osb.service.AccountAttachmentService;
import com.liferay.osb.service.AccountCallLocalService;
import com.liferay.osb.service.AccountCallService;
import com.liferay.osb.service.AccountCustomerLocalService;
import com.liferay.osb.service.AccountCustomerService;
import com.liferay.osb.service.AccountEntryLanguageLocalService;
import com.liferay.osb.service.AccountEntryLanguageService;
import com.liferay.osb.service.AccountEntryLocalService;
import com.liferay.osb.service.AccountEntryService;
import com.liferay.osb.service.AccountEnvironmentAttachmentLocalService;
import com.liferay.osb.service.AccountEnvironmentAttachmentService;
import com.liferay.osb.service.AccountEnvironmentLocalService;
import com.liferay.osb.service.AccountEnvironmentService;
import com.liferay.osb.service.AccountInformationLocalService;
import com.liferay.osb.service.AccountInformationService;
import com.liferay.osb.service.AccountLinkLocalService;
import com.liferay.osb.service.AccountLinkService;
import com.liferay.osb.service.AccountProjectLocalService;
import com.liferay.osb.service.AccountProjectService;
import com.liferay.osb.service.AccountWorkerLocalService;
import com.liferay.osb.service.AccountWorkerService;
import com.liferay.osb.service.AppAuditLocalService;
import com.liferay.osb.service.AppEntryLocalService;
import com.liferay.osb.service.AppEntryRelLocalService;
import com.liferay.osb.service.AppEntryRelService;
import com.liferay.osb.service.AppEntryService;
import com.liferay.osb.service.AppFlagLocalService;
import com.liferay.osb.service.AppFlagService;
import com.liferay.osb.service.AppPackageLocalService;
import com.liferay.osb.service.AppPackagePluginLocalService;
import com.liferay.osb.service.AppPackagePluginService;
import com.liferay.osb.service.AppPackageService;
import com.liferay.osb.service.AppPricingItemLocalService;
import com.liferay.osb.service.AppPricingItemService;
import com.liferay.osb.service.AppPricingLocalService;
import com.liferay.osb.service.AppPricingService;
import com.liferay.osb.service.AppVersionLocalService;
import com.liferay.osb.service.AppVersionService;
import com.liferay.osb.service.AssetAttachmentLocalService;
import com.liferay.osb.service.AssetAttachmentService;
import com.liferay.osb.service.AssetAuditLocalService;
import com.liferay.osb.service.AssetAuditService;
import com.liferay.osb.service.AssetLicenseLocalService;
import com.liferay.osb.service.AssetListAssetEntryLocalService;
import com.liferay.osb.service.AssetListLocalService;
import com.liferay.osb.service.AssetReceiptLicenseLocalService;
import com.liferay.osb.service.AssetReceiptLicenseService;
import com.liferay.osb.service.AssetReceiptLocalService;
import com.liferay.osb.service.AssetReceiptRedeemTokenLocalService;
import com.liferay.osb.service.AssetReceiptRedeemTokenService;
import com.liferay.osb.service.AssetReceiptService;
import com.liferay.osb.service.AssetReceiptSupportLocalService;
import com.liferay.osb.service.AssetRecommendationEntryLocalService;
import com.liferay.osb.service.AssetRecommendationEntryService;
import com.liferay.osb.service.AssetRecommendationSetLocalService;
import com.liferay.osb.service.AssetStatsDayLocalService;
import com.liferay.osb.service.AssetStatsMonthLocalService;
import com.liferay.osb.service.AssetStatsWeekLocalService;
import com.liferay.osb.service.AuditActionLocalService;
import com.liferay.osb.service.AuditActionService;
import com.liferay.osb.service.AuditEntryLocalService;
import com.liferay.osb.service.AuditEntryService;
import com.liferay.osb.service.ContractAuditLocalService;
import com.liferay.osb.service.ContractAuditService;
import com.liferay.osb.service.ContractEntryLocalService;
import com.liferay.osb.service.ContractEntryService;
import com.liferay.osb.service.CorpEntryLocalService;
import com.liferay.osb.service.CorpEntryService;
import com.liferay.osb.service.CorpGroupLocalService;
import com.liferay.osb.service.CorpGroupService;
import com.liferay.osb.service.CorpMembershipRequestLocalService;
import com.liferay.osb.service.CorpMembershipRequestService;
import com.liferay.osb.service.CorpProjectLocalService;
import com.liferay.osb.service.CorpProjectMessageLocalService;
import com.liferay.osb.service.CorpProjectMessageService;
import com.liferay.osb.service.CorpProjectService;
import com.liferay.osb.service.CountryAppPricingLocalService;
import com.liferay.osb.service.CountryAppPricingService;
import com.liferay.osb.service.CurrencyEntryLocalService;
import com.liferay.osb.service.CurrencyEntryService;
import com.liferay.osb.service.DeveloperEntryLocalService;
import com.liferay.osb.service.DeveloperEntryService;
import com.liferay.osb.service.ExternalIdMapperLocalService;
import com.liferay.osb.service.FeedbackEntryLocalService;
import com.liferay.osb.service.FeedbackEntryService;
import com.liferay.osb.service.HolidayCalendarLocalService;
import com.liferay.osb.service.HolidayCalendarRelLocalService;
import com.liferay.osb.service.HolidayCalendarRelService;
import com.liferay.osb.service.HolidayCalendarService;
import com.liferay.osb.service.HolidayEntryLocalService;
import com.liferay.osb.service.HolidayEntryService;
import com.liferay.osb.service.LCSSubscriptionEntryLocalService;
import com.liferay.osb.service.LCSSubscriptionEntryService;
import com.liferay.osb.service.LicenseEntryLocalService;
import com.liferay.osb.service.LicenseEntryService;
import com.liferay.osb.service.LicenseKeyLocalService;
import com.liferay.osb.service.LicenseKeyService;
import com.liferay.osb.service.LicenseKeySetLocalService;
import com.liferay.osb.service.LicenseKeySetService;
import com.liferay.osb.service.MarketingEventLocalService;
import com.liferay.osb.service.OSBCountryLocalService;
import com.liferay.osb.service.OSBCountryService;
import com.liferay.osb.service.OSBRegionLocalService;
import com.liferay.osb.service.OSBRegionService;
import com.liferay.osb.service.OfferingBundleLocalService;
import com.liferay.osb.service.OfferingBundleService;
import com.liferay.osb.service.OfferingDefinitionLocalService;
import com.liferay.osb.service.OfferingDefinitionService;
import com.liferay.osb.service.OfferingEntryLocalService;
import com.liferay.osb.service.OfferingEntryService;
import com.liferay.osb.service.OrderEntryLocalService;
import com.liferay.osb.service.OrderEntryService;
import com.liferay.osb.service.PartnerEntryLocalService;
import com.liferay.osb.service.PartnerEntryService;
import com.liferay.osb.service.PartnerWorkerLocalService;
import com.liferay.osb.service.PartnerWorkerService;
import com.liferay.osb.service.PortalReleaseLocalService;
import com.liferay.osb.service.ProductEntryLocalService;
import com.liferay.osb.service.ProductEntryService;
import com.liferay.osb.service.SearchFilterLocalService;
import com.liferay.osb.service.SearchFilterService;
import com.liferay.osb.service.SecurityPatchLocalService;
import com.liferay.osb.service.SecurityPatchService;
import com.liferay.osb.service.SupportLaborLocalService;
import com.liferay.osb.service.SupportLaborService;
import com.liferay.osb.service.SupportRegionLocalService;
import com.liferay.osb.service.SupportRegionService;
import com.liferay.osb.service.SupportResponseLocalService;
import com.liferay.osb.service.SupportResponseService;
import com.liferay.osb.service.SupportTeamLanguageLocalService;
import com.liferay.osb.service.SupportTeamLanguageService;
import com.liferay.osb.service.SupportTeamLocalService;
import com.liferay.osb.service.SupportTeamService;
import com.liferay.osb.service.SupportWorkerAccountTierLocalService;
import com.liferay.osb.service.SupportWorkerAccountTierService;
import com.liferay.osb.service.SupportWorkerComponentLocalService;
import com.liferay.osb.service.SupportWorkerComponentService;
import com.liferay.osb.service.SupportWorkerLocalService;
import com.liferay.osb.service.SupportWorkerService;
import com.liferay.osb.service.SupportWorkerSeverityLocalService;
import com.liferay.osb.service.SupportWorkerSeverityService;
import com.liferay.osb.service.TicketAttachmentLocalService;
import com.liferay.osb.service.TicketAttachmentService;
import com.liferay.osb.service.TicketCallLocalService;
import com.liferay.osb.service.TicketCallService;
import com.liferay.osb.service.TicketCannedResponseLocalService;
import com.liferay.osb.service.TicketCannedResponseService;
import com.liferay.osb.service.TicketCommentLocalService;
import com.liferay.osb.service.TicketCommentService;
import com.liferay.osb.service.TicketEntryLocalService;
import com.liferay.osb.service.TicketEntryService;
import com.liferay.osb.service.TicketFeedbackLocalService;
import com.liferay.osb.service.TicketFeedbackService;
import com.liferay.osb.service.TicketFlagLocalService;
import com.liferay.osb.service.TicketFlagService;
import com.liferay.osb.service.TicketInformationLocalService;
import com.liferay.osb.service.TicketInformationService;
import com.liferay.osb.service.TicketLinkLocalService;
import com.liferay.osb.service.TicketLinkService;
import com.liferay.osb.service.TicketSolutionLocalService;
import com.liferay.osb.service.TicketSolutionService;
import com.liferay.osb.service.TicketWorkerLocalService;
import com.liferay.osb.service.TicketWorkerService;
import com.liferay.osb.service.TrainingCertificateLocalService;
import com.liferay.osb.service.TrainingCertificateService;
import com.liferay.osb.service.TrainingCertificateTemplateLocalService;
import com.liferay.osb.service.TrainingCertificateTemplateService;
import com.liferay.osb.service.TrainingCourseLocalService;
import com.liferay.osb.service.TrainingCourseService;
import com.liferay.osb.service.TrainingCustomerLocalService;
import com.liferay.osb.service.TrainingCustomerService;
import com.liferay.osb.service.TrainingEventLocalService;
import com.liferay.osb.service.TrainingEventService;
import com.liferay.osb.service.TrainingExamLocalService;
import com.liferay.osb.service.TrainingExamResultItemLocalService;
import com.liferay.osb.service.TrainingExamResultItemService;
import com.liferay.osb.service.TrainingExamResultLocalService;
import com.liferay.osb.service.TrainingExamResultSectionLocalService;
import com.liferay.osb.service.TrainingExamResultSectionService;
import com.liferay.osb.service.TrainingExamResultService;
import com.liferay.osb.service.TrainingExamService;
import com.liferay.osb.service.TrainingImportLogLocalService;
import com.liferay.osb.service.TrainingLinkedUserLocalService;
import com.liferay.osb.service.TrainingLocationLocalService;
import com.liferay.osb.service.TrainingLocationService;
import com.liferay.osb.service.TrainingWorkerLocalService;
import com.liferay.osb.service.UserProfileHistoryLocalService;
import com.liferay.osb.service.UserProfileHistoryService;
import com.liferay.osb.service.UserProfileLocalService;
import com.liferay.osb.service.persistence.AccountAttachmentPersistence;
import com.liferay.osb.service.persistence.AccountCallPersistence;
import com.liferay.osb.service.persistence.AccountCustomerPersistence;
import com.liferay.osb.service.persistence.AccountEntryFinder;
import com.liferay.osb.service.persistence.AccountEntryLanguagePersistence;
import com.liferay.osb.service.persistence.AccountEntryPersistence;
import com.liferay.osb.service.persistence.AccountEnvironmentAttachmentPersistence;
import com.liferay.osb.service.persistence.AccountEnvironmentPersistence;
import com.liferay.osb.service.persistence.AccountInformationPersistence;
import com.liferay.osb.service.persistence.AccountLinkPersistence;
import com.liferay.osb.service.persistence.AccountProjectPersistence;
import com.liferay.osb.service.persistence.AccountWorkerPersistence;
import com.liferay.osb.service.persistence.AppAuditPersistence;
import com.liferay.osb.service.persistence.AppEntryFinder;
import com.liferay.osb.service.persistence.AppEntryPersistence;
import com.liferay.osb.service.persistence.AppEntryRelPersistence;
import com.liferay.osb.service.persistence.AppFlagFinder;
import com.liferay.osb.service.persistence.AppFlagPersistence;
import com.liferay.osb.service.persistence.AppPackageFinder;
import com.liferay.osb.service.persistence.AppPackagePersistence;
import com.liferay.osb.service.persistence.AppPackagePluginPersistence;
import com.liferay.osb.service.persistence.AppPricingItemFinder;
import com.liferay.osb.service.persistence.AppPricingItemPersistence;
import com.liferay.osb.service.persistence.AppPricingPersistence;
import com.liferay.osb.service.persistence.AppVersionFinder;
import com.liferay.osb.service.persistence.AppVersionPersistence;
import com.liferay.osb.service.persistence.AssetAttachmentPersistence;
import com.liferay.osb.service.persistence.AssetAuditFinder;
import com.liferay.osb.service.persistence.AssetAuditPersistence;
import com.liferay.osb.service.persistence.AssetLicensePersistence;
import com.liferay.osb.service.persistence.AssetListAssetEntryPersistence;
import com.liferay.osb.service.persistence.AssetListFinder;
import com.liferay.osb.service.persistence.AssetListPersistence;
import com.liferay.osb.service.persistence.AssetReceiptFinder;
import com.liferay.osb.service.persistence.AssetReceiptLicenseFinder;
import com.liferay.osb.service.persistence.AssetReceiptLicensePersistence;
import com.liferay.osb.service.persistence.AssetReceiptPersistence;
import com.liferay.osb.service.persistence.AssetReceiptRedeemTokenPersistence;
import com.liferay.osb.service.persistence.AssetReceiptSupportPersistence;
import com.liferay.osb.service.persistence.AssetRecommendationEntryPersistence;
import com.liferay.osb.service.persistence.AssetRecommendationSetPersistence;
import com.liferay.osb.service.persistence.AssetStatsDayPersistence;
import com.liferay.osb.service.persistence.AssetStatsMonthPersistence;
import com.liferay.osb.service.persistence.AssetStatsWeekPersistence;
import com.liferay.osb.service.persistence.AuditActionPersistence;
import com.liferay.osb.service.persistence.AuditEntryPersistence;
import com.liferay.osb.service.persistence.ContractAuditPersistence;
import com.liferay.osb.service.persistence.ContractEntryPersistence;
import com.liferay.osb.service.persistence.CorpEntryFinder;
import com.liferay.osb.service.persistence.CorpEntryPersistence;
import com.liferay.osb.service.persistence.CorpGroupPersistence;
import com.liferay.osb.service.persistence.CorpMembershipRequestPersistence;
import com.liferay.osb.service.persistence.CorpProjectMessagePersistence;
import com.liferay.osb.service.persistence.CorpProjectPersistence;
import com.liferay.osb.service.persistence.CountryAppPricingPersistence;
import com.liferay.osb.service.persistence.CurrencyEntryPersistence;
import com.liferay.osb.service.persistence.DeveloperEntryPersistence;
import com.liferay.osb.service.persistence.ExternalIdMapperPersistence;
import com.liferay.osb.service.persistence.FeedbackEntryPersistence;
import com.liferay.osb.service.persistence.HolidayCalendarPersistence;
import com.liferay.osb.service.persistence.HolidayCalendarRelPersistence;
import com.liferay.osb.service.persistence.HolidayEntryFinder;
import com.liferay.osb.service.persistence.HolidayEntryPersistence;
import com.liferay.osb.service.persistence.LCSSubscriptionEntryPersistence;
import com.liferay.osb.service.persistence.LicenseEntryPersistence;
import com.liferay.osb.service.persistence.LicenseKeyFinder;
import com.liferay.osb.service.persistence.LicenseKeyPersistence;
import com.liferay.osb.service.persistence.LicenseKeySetPersistence;
import com.liferay.osb.service.persistence.MarketingEventPersistence;
import com.liferay.osb.service.persistence.OfferingBundlePersistence;
import com.liferay.osb.service.persistence.OfferingDefinitionPersistence;
import com.liferay.osb.service.persistence.OfferingEntryFinder;
import com.liferay.osb.service.persistence.OfferingEntryPersistence;
import com.liferay.osb.service.persistence.OrderEntryFinder;
import com.liferay.osb.service.persistence.OrderEntryPersistence;
import com.liferay.osb.service.persistence.PartnerEntryFinder;
import com.liferay.osb.service.persistence.PartnerEntryPersistence;
import com.liferay.osb.service.persistence.PartnerWorkerPersistence;
import com.liferay.osb.service.persistence.PortalReleasePersistence;
import com.liferay.osb.service.persistence.ProductEntryFinder;
import com.liferay.osb.service.persistence.ProductEntryPersistence;
import com.liferay.osb.service.persistence.SearchFilterPersistence;
import com.liferay.osb.service.persistence.SecurityPatchPersistence;
import com.liferay.osb.service.persistence.SupportLaborPersistence;
import com.liferay.osb.service.persistence.SupportRegionPersistence;
import com.liferay.osb.service.persistence.SupportResponseFinder;
import com.liferay.osb.service.persistence.SupportResponsePersistence;
import com.liferay.osb.service.persistence.SupportTeamFinder;
import com.liferay.osb.service.persistence.SupportTeamLanguagePersistence;
import com.liferay.osb.service.persistence.SupportTeamPersistence;
import com.liferay.osb.service.persistence.SupportWorkerAccountTierPersistence;
import com.liferay.osb.service.persistence.SupportWorkerComponentPersistence;
import com.liferay.osb.service.persistence.SupportWorkerFinder;
import com.liferay.osb.service.persistence.SupportWorkerPersistence;
import com.liferay.osb.service.persistence.SupportWorkerSeverityPersistence;
import com.liferay.osb.service.persistence.TicketAttachmentPersistence;
import com.liferay.osb.service.persistence.TicketCallPersistence;
import com.liferay.osb.service.persistence.TicketCannedResponseFinder;
import com.liferay.osb.service.persistence.TicketCannedResponsePersistence;
import com.liferay.osb.service.persistence.TicketCommentFinder;
import com.liferay.osb.service.persistence.TicketCommentPersistence;
import com.liferay.osb.service.persistence.TicketEntryFinder;
import com.liferay.osb.service.persistence.TicketEntryPersistence;
import com.liferay.osb.service.persistence.TicketFeedbackFinder;
import com.liferay.osb.service.persistence.TicketFeedbackPersistence;
import com.liferay.osb.service.persistence.TicketFlagPersistence;
import com.liferay.osb.service.persistence.TicketInformationPersistence;
import com.liferay.osb.service.persistence.TicketLinkPersistence;
import com.liferay.osb.service.persistence.TicketSolutionPersistence;
import com.liferay.osb.service.persistence.TicketWorkerPersistence;
import com.liferay.osb.service.persistence.TrainingCertificatePersistence;
import com.liferay.osb.service.persistence.TrainingCertificateTemplatePersistence;
import com.liferay.osb.service.persistence.TrainingCoursePersistence;
import com.liferay.osb.service.persistence.TrainingCustomerFinder;
import com.liferay.osb.service.persistence.TrainingCustomerPersistence;
import com.liferay.osb.service.persistence.TrainingEventFinder;
import com.liferay.osb.service.persistence.TrainingEventPersistence;
import com.liferay.osb.service.persistence.TrainingExamPersistence;
import com.liferay.osb.service.persistence.TrainingExamResultFinder;
import com.liferay.osb.service.persistence.TrainingExamResultItemFinder;
import com.liferay.osb.service.persistence.TrainingExamResultItemPersistence;
import com.liferay.osb.service.persistence.TrainingExamResultPersistence;
import com.liferay.osb.service.persistence.TrainingExamResultSectionFinder;
import com.liferay.osb.service.persistence.TrainingExamResultSectionPersistence;
import com.liferay.osb.service.persistence.TrainingImportLogPersistence;
import com.liferay.osb.service.persistence.TrainingLinkedUserPersistence;
import com.liferay.osb.service.persistence.TrainingLocationFinder;
import com.liferay.osb.service.persistence.TrainingLocationPersistence;
import com.liferay.osb.service.persistence.TrainingWorkerPersistence;
import com.liferay.osb.service.persistence.UserProfileHistoryPersistence;
import com.liferay.osb.service.persistence.UserProfilePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.ClassNameLocalService;
import com.liferay.portal.service.ClassNameService;
import com.liferay.portal.service.OrganizationLocalService;
import com.liferay.portal.service.OrganizationService;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import javax.sql.DataSource;

/**
 * The base implementation of the partner worker remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.osb.service.impl.PartnerWorkerServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.impl.PartnerWorkerServiceImpl
 * @see com.liferay.osb.service.PartnerWorkerServiceUtil
 * @generated
 */
public abstract class PartnerWorkerServiceBaseImpl extends BaseServiceImpl
	implements PartnerWorkerService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.osb.service.PartnerWorkerServiceUtil} to access the partner worker remote service.
	 */

	/**
	 * Returns the account attachment local service.
	 *
	 * @return the account attachment local service
	 */
	public AccountAttachmentLocalService getAccountAttachmentLocalService() {
		return accountAttachmentLocalService;
	}

	/**
	 * Sets the account attachment local service.
	 *
	 * @param accountAttachmentLocalService the account attachment local service
	 */
	public void setAccountAttachmentLocalService(
		AccountAttachmentLocalService accountAttachmentLocalService) {
		this.accountAttachmentLocalService = accountAttachmentLocalService;
	}

	/**
	 * Returns the account attachment remote service.
	 *
	 * @return the account attachment remote service
	 */
	public AccountAttachmentService getAccountAttachmentService() {
		return accountAttachmentService;
	}

	/**
	 * Sets the account attachment remote service.
	 *
	 * @param accountAttachmentService the account attachment remote service
	 */
	public void setAccountAttachmentService(
		AccountAttachmentService accountAttachmentService) {
		this.accountAttachmentService = accountAttachmentService;
	}

	/**
	 * Returns the account attachment persistence.
	 *
	 * @return the account attachment persistence
	 */
	public AccountAttachmentPersistence getAccountAttachmentPersistence() {
		return accountAttachmentPersistence;
	}

	/**
	 * Sets the account attachment persistence.
	 *
	 * @param accountAttachmentPersistence the account attachment persistence
	 */
	public void setAccountAttachmentPersistence(
		AccountAttachmentPersistence accountAttachmentPersistence) {
		this.accountAttachmentPersistence = accountAttachmentPersistence;
	}

	/**
	 * Returns the account call local service.
	 *
	 * @return the account call local service
	 */
	public AccountCallLocalService getAccountCallLocalService() {
		return accountCallLocalService;
	}

	/**
	 * Sets the account call local service.
	 *
	 * @param accountCallLocalService the account call local service
	 */
	public void setAccountCallLocalService(
		AccountCallLocalService accountCallLocalService) {
		this.accountCallLocalService = accountCallLocalService;
	}

	/**
	 * Returns the account call remote service.
	 *
	 * @return the account call remote service
	 */
	public AccountCallService getAccountCallService() {
		return accountCallService;
	}

	/**
	 * Sets the account call remote service.
	 *
	 * @param accountCallService the account call remote service
	 */
	public void setAccountCallService(AccountCallService accountCallService) {
		this.accountCallService = accountCallService;
	}

	/**
	 * Returns the account call persistence.
	 *
	 * @return the account call persistence
	 */
	public AccountCallPersistence getAccountCallPersistence() {
		return accountCallPersistence;
	}

	/**
	 * Sets the account call persistence.
	 *
	 * @param accountCallPersistence the account call persistence
	 */
	public void setAccountCallPersistence(
		AccountCallPersistence accountCallPersistence) {
		this.accountCallPersistence = accountCallPersistence;
	}

	/**
	 * Returns the account customer local service.
	 *
	 * @return the account customer local service
	 */
	public AccountCustomerLocalService getAccountCustomerLocalService() {
		return accountCustomerLocalService;
	}

	/**
	 * Sets the account customer local service.
	 *
	 * @param accountCustomerLocalService the account customer local service
	 */
	public void setAccountCustomerLocalService(
		AccountCustomerLocalService accountCustomerLocalService) {
		this.accountCustomerLocalService = accountCustomerLocalService;
	}

	/**
	 * Returns the account customer remote service.
	 *
	 * @return the account customer remote service
	 */
	public AccountCustomerService getAccountCustomerService() {
		return accountCustomerService;
	}

	/**
	 * Sets the account customer remote service.
	 *
	 * @param accountCustomerService the account customer remote service
	 */
	public void setAccountCustomerService(
		AccountCustomerService accountCustomerService) {
		this.accountCustomerService = accountCustomerService;
	}

	/**
	 * Returns the account customer persistence.
	 *
	 * @return the account customer persistence
	 */
	public AccountCustomerPersistence getAccountCustomerPersistence() {
		return accountCustomerPersistence;
	}

	/**
	 * Sets the account customer persistence.
	 *
	 * @param accountCustomerPersistence the account customer persistence
	 */
	public void setAccountCustomerPersistence(
		AccountCustomerPersistence accountCustomerPersistence) {
		this.accountCustomerPersistence = accountCustomerPersistence;
	}

	/**
	 * Returns the account entry local service.
	 *
	 * @return the account entry local service
	 */
	public AccountEntryLocalService getAccountEntryLocalService() {
		return accountEntryLocalService;
	}

	/**
	 * Sets the account entry local service.
	 *
	 * @param accountEntryLocalService the account entry local service
	 */
	public void setAccountEntryLocalService(
		AccountEntryLocalService accountEntryLocalService) {
		this.accountEntryLocalService = accountEntryLocalService;
	}

	/**
	 * Returns the account entry remote service.
	 *
	 * @return the account entry remote service
	 */
	public AccountEntryService getAccountEntryService() {
		return accountEntryService;
	}

	/**
	 * Sets the account entry remote service.
	 *
	 * @param accountEntryService the account entry remote service
	 */
	public void setAccountEntryService(AccountEntryService accountEntryService) {
		this.accountEntryService = accountEntryService;
	}

	/**
	 * Returns the account entry persistence.
	 *
	 * @return the account entry persistence
	 */
	public AccountEntryPersistence getAccountEntryPersistence() {
		return accountEntryPersistence;
	}

	/**
	 * Sets the account entry persistence.
	 *
	 * @param accountEntryPersistence the account entry persistence
	 */
	public void setAccountEntryPersistence(
		AccountEntryPersistence accountEntryPersistence) {
		this.accountEntryPersistence = accountEntryPersistence;
	}

	/**
	 * Returns the account entry finder.
	 *
	 * @return the account entry finder
	 */
	public AccountEntryFinder getAccountEntryFinder() {
		return accountEntryFinder;
	}

	/**
	 * Sets the account entry finder.
	 *
	 * @param accountEntryFinder the account entry finder
	 */
	public void setAccountEntryFinder(AccountEntryFinder accountEntryFinder) {
		this.accountEntryFinder = accountEntryFinder;
	}

	/**
	 * Returns the account entry language local service.
	 *
	 * @return the account entry language local service
	 */
	public AccountEntryLanguageLocalService getAccountEntryLanguageLocalService() {
		return accountEntryLanguageLocalService;
	}

	/**
	 * Sets the account entry language local service.
	 *
	 * @param accountEntryLanguageLocalService the account entry language local service
	 */
	public void setAccountEntryLanguageLocalService(
		AccountEntryLanguageLocalService accountEntryLanguageLocalService) {
		this.accountEntryLanguageLocalService = accountEntryLanguageLocalService;
	}

	/**
	 * Returns the account entry language remote service.
	 *
	 * @return the account entry language remote service
	 */
	public AccountEntryLanguageService getAccountEntryLanguageService() {
		return accountEntryLanguageService;
	}

	/**
	 * Sets the account entry language remote service.
	 *
	 * @param accountEntryLanguageService the account entry language remote service
	 */
	public void setAccountEntryLanguageService(
		AccountEntryLanguageService accountEntryLanguageService) {
		this.accountEntryLanguageService = accountEntryLanguageService;
	}

	/**
	 * Returns the account entry language persistence.
	 *
	 * @return the account entry language persistence
	 */
	public AccountEntryLanguagePersistence getAccountEntryLanguagePersistence() {
		return accountEntryLanguagePersistence;
	}

	/**
	 * Sets the account entry language persistence.
	 *
	 * @param accountEntryLanguagePersistence the account entry language persistence
	 */
	public void setAccountEntryLanguagePersistence(
		AccountEntryLanguagePersistence accountEntryLanguagePersistence) {
		this.accountEntryLanguagePersistence = accountEntryLanguagePersistence;
	}

	/**
	 * Returns the account environment local service.
	 *
	 * @return the account environment local service
	 */
	public AccountEnvironmentLocalService getAccountEnvironmentLocalService() {
		return accountEnvironmentLocalService;
	}

	/**
	 * Sets the account environment local service.
	 *
	 * @param accountEnvironmentLocalService the account environment local service
	 */
	public void setAccountEnvironmentLocalService(
		AccountEnvironmentLocalService accountEnvironmentLocalService) {
		this.accountEnvironmentLocalService = accountEnvironmentLocalService;
	}

	/**
	 * Returns the account environment remote service.
	 *
	 * @return the account environment remote service
	 */
	public AccountEnvironmentService getAccountEnvironmentService() {
		return accountEnvironmentService;
	}

	/**
	 * Sets the account environment remote service.
	 *
	 * @param accountEnvironmentService the account environment remote service
	 */
	public void setAccountEnvironmentService(
		AccountEnvironmentService accountEnvironmentService) {
		this.accountEnvironmentService = accountEnvironmentService;
	}

	/**
	 * Returns the account environment persistence.
	 *
	 * @return the account environment persistence
	 */
	public AccountEnvironmentPersistence getAccountEnvironmentPersistence() {
		return accountEnvironmentPersistence;
	}

	/**
	 * Sets the account environment persistence.
	 *
	 * @param accountEnvironmentPersistence the account environment persistence
	 */
	public void setAccountEnvironmentPersistence(
		AccountEnvironmentPersistence accountEnvironmentPersistence) {
		this.accountEnvironmentPersistence = accountEnvironmentPersistence;
	}

	/**
	 * Returns the account environment attachment local service.
	 *
	 * @return the account environment attachment local service
	 */
	public AccountEnvironmentAttachmentLocalService getAccountEnvironmentAttachmentLocalService() {
		return accountEnvironmentAttachmentLocalService;
	}

	/**
	 * Sets the account environment attachment local service.
	 *
	 * @param accountEnvironmentAttachmentLocalService the account environment attachment local service
	 */
	public void setAccountEnvironmentAttachmentLocalService(
		AccountEnvironmentAttachmentLocalService accountEnvironmentAttachmentLocalService) {
		this.accountEnvironmentAttachmentLocalService = accountEnvironmentAttachmentLocalService;
	}

	/**
	 * Returns the account environment attachment remote service.
	 *
	 * @return the account environment attachment remote service
	 */
	public AccountEnvironmentAttachmentService getAccountEnvironmentAttachmentService() {
		return accountEnvironmentAttachmentService;
	}

	/**
	 * Sets the account environment attachment remote service.
	 *
	 * @param accountEnvironmentAttachmentService the account environment attachment remote service
	 */
	public void setAccountEnvironmentAttachmentService(
		AccountEnvironmentAttachmentService accountEnvironmentAttachmentService) {
		this.accountEnvironmentAttachmentService = accountEnvironmentAttachmentService;
	}

	/**
	 * Returns the account environment attachment persistence.
	 *
	 * @return the account environment attachment persistence
	 */
	public AccountEnvironmentAttachmentPersistence getAccountEnvironmentAttachmentPersistence() {
		return accountEnvironmentAttachmentPersistence;
	}

	/**
	 * Sets the account environment attachment persistence.
	 *
	 * @param accountEnvironmentAttachmentPersistence the account environment attachment persistence
	 */
	public void setAccountEnvironmentAttachmentPersistence(
		AccountEnvironmentAttachmentPersistence accountEnvironmentAttachmentPersistence) {
		this.accountEnvironmentAttachmentPersistence = accountEnvironmentAttachmentPersistence;
	}

	/**
	 * Returns the account information local service.
	 *
	 * @return the account information local service
	 */
	public AccountInformationLocalService getAccountInformationLocalService() {
		return accountInformationLocalService;
	}

	/**
	 * Sets the account information local service.
	 *
	 * @param accountInformationLocalService the account information local service
	 */
	public void setAccountInformationLocalService(
		AccountInformationLocalService accountInformationLocalService) {
		this.accountInformationLocalService = accountInformationLocalService;
	}

	/**
	 * Returns the account information remote service.
	 *
	 * @return the account information remote service
	 */
	public AccountInformationService getAccountInformationService() {
		return accountInformationService;
	}

	/**
	 * Sets the account information remote service.
	 *
	 * @param accountInformationService the account information remote service
	 */
	public void setAccountInformationService(
		AccountInformationService accountInformationService) {
		this.accountInformationService = accountInformationService;
	}

	/**
	 * Returns the account information persistence.
	 *
	 * @return the account information persistence
	 */
	public AccountInformationPersistence getAccountInformationPersistence() {
		return accountInformationPersistence;
	}

	/**
	 * Sets the account information persistence.
	 *
	 * @param accountInformationPersistence the account information persistence
	 */
	public void setAccountInformationPersistence(
		AccountInformationPersistence accountInformationPersistence) {
		this.accountInformationPersistence = accountInformationPersistence;
	}

	/**
	 * Returns the account link local service.
	 *
	 * @return the account link local service
	 */
	public AccountLinkLocalService getAccountLinkLocalService() {
		return accountLinkLocalService;
	}

	/**
	 * Sets the account link local service.
	 *
	 * @param accountLinkLocalService the account link local service
	 */
	public void setAccountLinkLocalService(
		AccountLinkLocalService accountLinkLocalService) {
		this.accountLinkLocalService = accountLinkLocalService;
	}

	/**
	 * Returns the account link remote service.
	 *
	 * @return the account link remote service
	 */
	public AccountLinkService getAccountLinkService() {
		return accountLinkService;
	}

	/**
	 * Sets the account link remote service.
	 *
	 * @param accountLinkService the account link remote service
	 */
	public void setAccountLinkService(AccountLinkService accountLinkService) {
		this.accountLinkService = accountLinkService;
	}

	/**
	 * Returns the account link persistence.
	 *
	 * @return the account link persistence
	 */
	public AccountLinkPersistence getAccountLinkPersistence() {
		return accountLinkPersistence;
	}

	/**
	 * Sets the account link persistence.
	 *
	 * @param accountLinkPersistence the account link persistence
	 */
	public void setAccountLinkPersistence(
		AccountLinkPersistence accountLinkPersistence) {
		this.accountLinkPersistence = accountLinkPersistence;
	}

	/**
	 * Returns the account project local service.
	 *
	 * @return the account project local service
	 */
	public AccountProjectLocalService getAccountProjectLocalService() {
		return accountProjectLocalService;
	}

	/**
	 * Sets the account project local service.
	 *
	 * @param accountProjectLocalService the account project local service
	 */
	public void setAccountProjectLocalService(
		AccountProjectLocalService accountProjectLocalService) {
		this.accountProjectLocalService = accountProjectLocalService;
	}

	/**
	 * Returns the account project remote service.
	 *
	 * @return the account project remote service
	 */
	public AccountProjectService getAccountProjectService() {
		return accountProjectService;
	}

	/**
	 * Sets the account project remote service.
	 *
	 * @param accountProjectService the account project remote service
	 */
	public void setAccountProjectService(
		AccountProjectService accountProjectService) {
		this.accountProjectService = accountProjectService;
	}

	/**
	 * Returns the account project persistence.
	 *
	 * @return the account project persistence
	 */
	public AccountProjectPersistence getAccountProjectPersistence() {
		return accountProjectPersistence;
	}

	/**
	 * Sets the account project persistence.
	 *
	 * @param accountProjectPersistence the account project persistence
	 */
	public void setAccountProjectPersistence(
		AccountProjectPersistence accountProjectPersistence) {
		this.accountProjectPersistence = accountProjectPersistence;
	}

	/**
	 * Returns the account worker local service.
	 *
	 * @return the account worker local service
	 */
	public AccountWorkerLocalService getAccountWorkerLocalService() {
		return accountWorkerLocalService;
	}

	/**
	 * Sets the account worker local service.
	 *
	 * @param accountWorkerLocalService the account worker local service
	 */
	public void setAccountWorkerLocalService(
		AccountWorkerLocalService accountWorkerLocalService) {
		this.accountWorkerLocalService = accountWorkerLocalService;
	}

	/**
	 * Returns the account worker remote service.
	 *
	 * @return the account worker remote service
	 */
	public AccountWorkerService getAccountWorkerService() {
		return accountWorkerService;
	}

	/**
	 * Sets the account worker remote service.
	 *
	 * @param accountWorkerService the account worker remote service
	 */
	public void setAccountWorkerService(
		AccountWorkerService accountWorkerService) {
		this.accountWorkerService = accountWorkerService;
	}

	/**
	 * Returns the account worker persistence.
	 *
	 * @return the account worker persistence
	 */
	public AccountWorkerPersistence getAccountWorkerPersistence() {
		return accountWorkerPersistence;
	}

	/**
	 * Sets the account worker persistence.
	 *
	 * @param accountWorkerPersistence the account worker persistence
	 */
	public void setAccountWorkerPersistence(
		AccountWorkerPersistence accountWorkerPersistence) {
		this.accountWorkerPersistence = accountWorkerPersistence;
	}

	/**
	 * Returns the app audit local service.
	 *
	 * @return the app audit local service
	 */
	public AppAuditLocalService getAppAuditLocalService() {
		return appAuditLocalService;
	}

	/**
	 * Sets the app audit local service.
	 *
	 * @param appAuditLocalService the app audit local service
	 */
	public void setAppAuditLocalService(
		AppAuditLocalService appAuditLocalService) {
		this.appAuditLocalService = appAuditLocalService;
	}

	/**
	 * Returns the app audit persistence.
	 *
	 * @return the app audit persistence
	 */
	public AppAuditPersistence getAppAuditPersistence() {
		return appAuditPersistence;
	}

	/**
	 * Sets the app audit persistence.
	 *
	 * @param appAuditPersistence the app audit persistence
	 */
	public void setAppAuditPersistence(AppAuditPersistence appAuditPersistence) {
		this.appAuditPersistence = appAuditPersistence;
	}

	/**
	 * Returns the app entry local service.
	 *
	 * @return the app entry local service
	 */
	public AppEntryLocalService getAppEntryLocalService() {
		return appEntryLocalService;
	}

	/**
	 * Sets the app entry local service.
	 *
	 * @param appEntryLocalService the app entry local service
	 */
	public void setAppEntryLocalService(
		AppEntryLocalService appEntryLocalService) {
		this.appEntryLocalService = appEntryLocalService;
	}

	/**
	 * Returns the app entry remote service.
	 *
	 * @return the app entry remote service
	 */
	public AppEntryService getAppEntryService() {
		return appEntryService;
	}

	/**
	 * Sets the app entry remote service.
	 *
	 * @param appEntryService the app entry remote service
	 */
	public void setAppEntryService(AppEntryService appEntryService) {
		this.appEntryService = appEntryService;
	}

	/**
	 * Returns the app entry persistence.
	 *
	 * @return the app entry persistence
	 */
	public AppEntryPersistence getAppEntryPersistence() {
		return appEntryPersistence;
	}

	/**
	 * Sets the app entry persistence.
	 *
	 * @param appEntryPersistence the app entry persistence
	 */
	public void setAppEntryPersistence(AppEntryPersistence appEntryPersistence) {
		this.appEntryPersistence = appEntryPersistence;
	}

	/**
	 * Returns the app entry finder.
	 *
	 * @return the app entry finder
	 */
	public AppEntryFinder getAppEntryFinder() {
		return appEntryFinder;
	}

	/**
	 * Sets the app entry finder.
	 *
	 * @param appEntryFinder the app entry finder
	 */
	public void setAppEntryFinder(AppEntryFinder appEntryFinder) {
		this.appEntryFinder = appEntryFinder;
	}

	/**
	 * Returns the app entry rel local service.
	 *
	 * @return the app entry rel local service
	 */
	public AppEntryRelLocalService getAppEntryRelLocalService() {
		return appEntryRelLocalService;
	}

	/**
	 * Sets the app entry rel local service.
	 *
	 * @param appEntryRelLocalService the app entry rel local service
	 */
	public void setAppEntryRelLocalService(
		AppEntryRelLocalService appEntryRelLocalService) {
		this.appEntryRelLocalService = appEntryRelLocalService;
	}

	/**
	 * Returns the app entry rel remote service.
	 *
	 * @return the app entry rel remote service
	 */
	public AppEntryRelService getAppEntryRelService() {
		return appEntryRelService;
	}

	/**
	 * Sets the app entry rel remote service.
	 *
	 * @param appEntryRelService the app entry rel remote service
	 */
	public void setAppEntryRelService(AppEntryRelService appEntryRelService) {
		this.appEntryRelService = appEntryRelService;
	}

	/**
	 * Returns the app entry rel persistence.
	 *
	 * @return the app entry rel persistence
	 */
	public AppEntryRelPersistence getAppEntryRelPersistence() {
		return appEntryRelPersistence;
	}

	/**
	 * Sets the app entry rel persistence.
	 *
	 * @param appEntryRelPersistence the app entry rel persistence
	 */
	public void setAppEntryRelPersistence(
		AppEntryRelPersistence appEntryRelPersistence) {
		this.appEntryRelPersistence = appEntryRelPersistence;
	}

	/**
	 * Returns the app flag local service.
	 *
	 * @return the app flag local service
	 */
	public AppFlagLocalService getAppFlagLocalService() {
		return appFlagLocalService;
	}

	/**
	 * Sets the app flag local service.
	 *
	 * @param appFlagLocalService the app flag local service
	 */
	public void setAppFlagLocalService(AppFlagLocalService appFlagLocalService) {
		this.appFlagLocalService = appFlagLocalService;
	}

	/**
	 * Returns the app flag remote service.
	 *
	 * @return the app flag remote service
	 */
	public AppFlagService getAppFlagService() {
		return appFlagService;
	}

	/**
	 * Sets the app flag remote service.
	 *
	 * @param appFlagService the app flag remote service
	 */
	public void setAppFlagService(AppFlagService appFlagService) {
		this.appFlagService = appFlagService;
	}

	/**
	 * Returns the app flag persistence.
	 *
	 * @return the app flag persistence
	 */
	public AppFlagPersistence getAppFlagPersistence() {
		return appFlagPersistence;
	}

	/**
	 * Sets the app flag persistence.
	 *
	 * @param appFlagPersistence the app flag persistence
	 */
	public void setAppFlagPersistence(AppFlagPersistence appFlagPersistence) {
		this.appFlagPersistence = appFlagPersistence;
	}

	/**
	 * Returns the app flag finder.
	 *
	 * @return the app flag finder
	 */
	public AppFlagFinder getAppFlagFinder() {
		return appFlagFinder;
	}

	/**
	 * Sets the app flag finder.
	 *
	 * @param appFlagFinder the app flag finder
	 */
	public void setAppFlagFinder(AppFlagFinder appFlagFinder) {
		this.appFlagFinder = appFlagFinder;
	}

	/**
	 * Returns the app package local service.
	 *
	 * @return the app package local service
	 */
	public AppPackageLocalService getAppPackageLocalService() {
		return appPackageLocalService;
	}

	/**
	 * Sets the app package local service.
	 *
	 * @param appPackageLocalService the app package local service
	 */
	public void setAppPackageLocalService(
		AppPackageLocalService appPackageLocalService) {
		this.appPackageLocalService = appPackageLocalService;
	}

	/**
	 * Returns the app package remote service.
	 *
	 * @return the app package remote service
	 */
	public AppPackageService getAppPackageService() {
		return appPackageService;
	}

	/**
	 * Sets the app package remote service.
	 *
	 * @param appPackageService the app package remote service
	 */
	public void setAppPackageService(AppPackageService appPackageService) {
		this.appPackageService = appPackageService;
	}

	/**
	 * Returns the app package persistence.
	 *
	 * @return the app package persistence
	 */
	public AppPackagePersistence getAppPackagePersistence() {
		return appPackagePersistence;
	}

	/**
	 * Sets the app package persistence.
	 *
	 * @param appPackagePersistence the app package persistence
	 */
	public void setAppPackagePersistence(
		AppPackagePersistence appPackagePersistence) {
		this.appPackagePersistence = appPackagePersistence;
	}

	/**
	 * Returns the app package finder.
	 *
	 * @return the app package finder
	 */
	public AppPackageFinder getAppPackageFinder() {
		return appPackageFinder;
	}

	/**
	 * Sets the app package finder.
	 *
	 * @param appPackageFinder the app package finder
	 */
	public void setAppPackageFinder(AppPackageFinder appPackageFinder) {
		this.appPackageFinder = appPackageFinder;
	}

	/**
	 * Returns the app package plugin local service.
	 *
	 * @return the app package plugin local service
	 */
	public AppPackagePluginLocalService getAppPackagePluginLocalService() {
		return appPackagePluginLocalService;
	}

	/**
	 * Sets the app package plugin local service.
	 *
	 * @param appPackagePluginLocalService the app package plugin local service
	 */
	public void setAppPackagePluginLocalService(
		AppPackagePluginLocalService appPackagePluginLocalService) {
		this.appPackagePluginLocalService = appPackagePluginLocalService;
	}

	/**
	 * Returns the app package plugin remote service.
	 *
	 * @return the app package plugin remote service
	 */
	public AppPackagePluginService getAppPackagePluginService() {
		return appPackagePluginService;
	}

	/**
	 * Sets the app package plugin remote service.
	 *
	 * @param appPackagePluginService the app package plugin remote service
	 */
	public void setAppPackagePluginService(
		AppPackagePluginService appPackagePluginService) {
		this.appPackagePluginService = appPackagePluginService;
	}

	/**
	 * Returns the app package plugin persistence.
	 *
	 * @return the app package plugin persistence
	 */
	public AppPackagePluginPersistence getAppPackagePluginPersistence() {
		return appPackagePluginPersistence;
	}

	/**
	 * Sets the app package plugin persistence.
	 *
	 * @param appPackagePluginPersistence the app package plugin persistence
	 */
	public void setAppPackagePluginPersistence(
		AppPackagePluginPersistence appPackagePluginPersistence) {
		this.appPackagePluginPersistence = appPackagePluginPersistence;
	}

	/**
	 * Returns the app pricing local service.
	 *
	 * @return the app pricing local service
	 */
	public AppPricingLocalService getAppPricingLocalService() {
		return appPricingLocalService;
	}

	/**
	 * Sets the app pricing local service.
	 *
	 * @param appPricingLocalService the app pricing local service
	 */
	public void setAppPricingLocalService(
		AppPricingLocalService appPricingLocalService) {
		this.appPricingLocalService = appPricingLocalService;
	}

	/**
	 * Returns the app pricing remote service.
	 *
	 * @return the app pricing remote service
	 */
	public AppPricingService getAppPricingService() {
		return appPricingService;
	}

	/**
	 * Sets the app pricing remote service.
	 *
	 * @param appPricingService the app pricing remote service
	 */
	public void setAppPricingService(AppPricingService appPricingService) {
		this.appPricingService = appPricingService;
	}

	/**
	 * Returns the app pricing persistence.
	 *
	 * @return the app pricing persistence
	 */
	public AppPricingPersistence getAppPricingPersistence() {
		return appPricingPersistence;
	}

	/**
	 * Sets the app pricing persistence.
	 *
	 * @param appPricingPersistence the app pricing persistence
	 */
	public void setAppPricingPersistence(
		AppPricingPersistence appPricingPersistence) {
		this.appPricingPersistence = appPricingPersistence;
	}

	/**
	 * Returns the app pricing item local service.
	 *
	 * @return the app pricing item local service
	 */
	public AppPricingItemLocalService getAppPricingItemLocalService() {
		return appPricingItemLocalService;
	}

	/**
	 * Sets the app pricing item local service.
	 *
	 * @param appPricingItemLocalService the app pricing item local service
	 */
	public void setAppPricingItemLocalService(
		AppPricingItemLocalService appPricingItemLocalService) {
		this.appPricingItemLocalService = appPricingItemLocalService;
	}

	/**
	 * Returns the app pricing item remote service.
	 *
	 * @return the app pricing item remote service
	 */
	public AppPricingItemService getAppPricingItemService() {
		return appPricingItemService;
	}

	/**
	 * Sets the app pricing item remote service.
	 *
	 * @param appPricingItemService the app pricing item remote service
	 */
	public void setAppPricingItemService(
		AppPricingItemService appPricingItemService) {
		this.appPricingItemService = appPricingItemService;
	}

	/**
	 * Returns the app pricing item persistence.
	 *
	 * @return the app pricing item persistence
	 */
	public AppPricingItemPersistence getAppPricingItemPersistence() {
		return appPricingItemPersistence;
	}

	/**
	 * Sets the app pricing item persistence.
	 *
	 * @param appPricingItemPersistence the app pricing item persistence
	 */
	public void setAppPricingItemPersistence(
		AppPricingItemPersistence appPricingItemPersistence) {
		this.appPricingItemPersistence = appPricingItemPersistence;
	}

	/**
	 * Returns the app pricing item finder.
	 *
	 * @return the app pricing item finder
	 */
	public AppPricingItemFinder getAppPricingItemFinder() {
		return appPricingItemFinder;
	}

	/**
	 * Sets the app pricing item finder.
	 *
	 * @param appPricingItemFinder the app pricing item finder
	 */
	public void setAppPricingItemFinder(
		AppPricingItemFinder appPricingItemFinder) {
		this.appPricingItemFinder = appPricingItemFinder;
	}

	/**
	 * Returns the app version local service.
	 *
	 * @return the app version local service
	 */
	public AppVersionLocalService getAppVersionLocalService() {
		return appVersionLocalService;
	}

	/**
	 * Sets the app version local service.
	 *
	 * @param appVersionLocalService the app version local service
	 */
	public void setAppVersionLocalService(
		AppVersionLocalService appVersionLocalService) {
		this.appVersionLocalService = appVersionLocalService;
	}

	/**
	 * Returns the app version remote service.
	 *
	 * @return the app version remote service
	 */
	public AppVersionService getAppVersionService() {
		return appVersionService;
	}

	/**
	 * Sets the app version remote service.
	 *
	 * @param appVersionService the app version remote service
	 */
	public void setAppVersionService(AppVersionService appVersionService) {
		this.appVersionService = appVersionService;
	}

	/**
	 * Returns the app version persistence.
	 *
	 * @return the app version persistence
	 */
	public AppVersionPersistence getAppVersionPersistence() {
		return appVersionPersistence;
	}

	/**
	 * Sets the app version persistence.
	 *
	 * @param appVersionPersistence the app version persistence
	 */
	public void setAppVersionPersistence(
		AppVersionPersistence appVersionPersistence) {
		this.appVersionPersistence = appVersionPersistence;
	}

	/**
	 * Returns the app version finder.
	 *
	 * @return the app version finder
	 */
	public AppVersionFinder getAppVersionFinder() {
		return appVersionFinder;
	}

	/**
	 * Sets the app version finder.
	 *
	 * @param appVersionFinder the app version finder
	 */
	public void setAppVersionFinder(AppVersionFinder appVersionFinder) {
		this.appVersionFinder = appVersionFinder;
	}

	/**
	 * Returns the asset attachment local service.
	 *
	 * @return the asset attachment local service
	 */
	public AssetAttachmentLocalService getAssetAttachmentLocalService() {
		return assetAttachmentLocalService;
	}

	/**
	 * Sets the asset attachment local service.
	 *
	 * @param assetAttachmentLocalService the asset attachment local service
	 */
	public void setAssetAttachmentLocalService(
		AssetAttachmentLocalService assetAttachmentLocalService) {
		this.assetAttachmentLocalService = assetAttachmentLocalService;
	}

	/**
	 * Returns the asset attachment remote service.
	 *
	 * @return the asset attachment remote service
	 */
	public AssetAttachmentService getAssetAttachmentService() {
		return assetAttachmentService;
	}

	/**
	 * Sets the asset attachment remote service.
	 *
	 * @param assetAttachmentService the asset attachment remote service
	 */
	public void setAssetAttachmentService(
		AssetAttachmentService assetAttachmentService) {
		this.assetAttachmentService = assetAttachmentService;
	}

	/**
	 * Returns the asset attachment persistence.
	 *
	 * @return the asset attachment persistence
	 */
	public AssetAttachmentPersistence getAssetAttachmentPersistence() {
		return assetAttachmentPersistence;
	}

	/**
	 * Sets the asset attachment persistence.
	 *
	 * @param assetAttachmentPersistence the asset attachment persistence
	 */
	public void setAssetAttachmentPersistence(
		AssetAttachmentPersistence assetAttachmentPersistence) {
		this.assetAttachmentPersistence = assetAttachmentPersistence;
	}

	/**
	 * Returns the asset audit local service.
	 *
	 * @return the asset audit local service
	 */
	public AssetAuditLocalService getAssetAuditLocalService() {
		return assetAuditLocalService;
	}

	/**
	 * Sets the asset audit local service.
	 *
	 * @param assetAuditLocalService the asset audit local service
	 */
	public void setAssetAuditLocalService(
		AssetAuditLocalService assetAuditLocalService) {
		this.assetAuditLocalService = assetAuditLocalService;
	}

	/**
	 * Returns the asset audit remote service.
	 *
	 * @return the asset audit remote service
	 */
	public AssetAuditService getAssetAuditService() {
		return assetAuditService;
	}

	/**
	 * Sets the asset audit remote service.
	 *
	 * @param assetAuditService the asset audit remote service
	 */
	public void setAssetAuditService(AssetAuditService assetAuditService) {
		this.assetAuditService = assetAuditService;
	}

	/**
	 * Returns the asset audit persistence.
	 *
	 * @return the asset audit persistence
	 */
	public AssetAuditPersistence getAssetAuditPersistence() {
		return assetAuditPersistence;
	}

	/**
	 * Sets the asset audit persistence.
	 *
	 * @param assetAuditPersistence the asset audit persistence
	 */
	public void setAssetAuditPersistence(
		AssetAuditPersistence assetAuditPersistence) {
		this.assetAuditPersistence = assetAuditPersistence;
	}

	/**
	 * Returns the asset audit finder.
	 *
	 * @return the asset audit finder
	 */
	public AssetAuditFinder getAssetAuditFinder() {
		return assetAuditFinder;
	}

	/**
	 * Sets the asset audit finder.
	 *
	 * @param assetAuditFinder the asset audit finder
	 */
	public void setAssetAuditFinder(AssetAuditFinder assetAuditFinder) {
		this.assetAuditFinder = assetAuditFinder;
	}

	/**
	 * Returns the asset license local service.
	 *
	 * @return the asset license local service
	 */
	public AssetLicenseLocalService getAssetLicenseLocalService() {
		return assetLicenseLocalService;
	}

	/**
	 * Sets the asset license local service.
	 *
	 * @param assetLicenseLocalService the asset license local service
	 */
	public void setAssetLicenseLocalService(
		AssetLicenseLocalService assetLicenseLocalService) {
		this.assetLicenseLocalService = assetLicenseLocalService;
	}

	/**
	 * Returns the asset license persistence.
	 *
	 * @return the asset license persistence
	 */
	public AssetLicensePersistence getAssetLicensePersistence() {
		return assetLicensePersistence;
	}

	/**
	 * Sets the asset license persistence.
	 *
	 * @param assetLicensePersistence the asset license persistence
	 */
	public void setAssetLicensePersistence(
		AssetLicensePersistence assetLicensePersistence) {
		this.assetLicensePersistence = assetLicensePersistence;
	}

	/**
	 * Returns the asset list local service.
	 *
	 * @return the asset list local service
	 */
	public AssetListLocalService getAssetListLocalService() {
		return assetListLocalService;
	}

	/**
	 * Sets the asset list local service.
	 *
	 * @param assetListLocalService the asset list local service
	 */
	public void setAssetListLocalService(
		AssetListLocalService assetListLocalService) {
		this.assetListLocalService = assetListLocalService;
	}

	/**
	 * Returns the asset list persistence.
	 *
	 * @return the asset list persistence
	 */
	public AssetListPersistence getAssetListPersistence() {
		return assetListPersistence;
	}

	/**
	 * Sets the asset list persistence.
	 *
	 * @param assetListPersistence the asset list persistence
	 */
	public void setAssetListPersistence(
		AssetListPersistence assetListPersistence) {
		this.assetListPersistence = assetListPersistence;
	}

	/**
	 * Returns the asset list finder.
	 *
	 * @return the asset list finder
	 */
	public AssetListFinder getAssetListFinder() {
		return assetListFinder;
	}

	/**
	 * Sets the asset list finder.
	 *
	 * @param assetListFinder the asset list finder
	 */
	public void setAssetListFinder(AssetListFinder assetListFinder) {
		this.assetListFinder = assetListFinder;
	}

	/**
	 * Returns the asset list asset entry local service.
	 *
	 * @return the asset list asset entry local service
	 */
	public AssetListAssetEntryLocalService getAssetListAssetEntryLocalService() {
		return assetListAssetEntryLocalService;
	}

	/**
	 * Sets the asset list asset entry local service.
	 *
	 * @param assetListAssetEntryLocalService the asset list asset entry local service
	 */
	public void setAssetListAssetEntryLocalService(
		AssetListAssetEntryLocalService assetListAssetEntryLocalService) {
		this.assetListAssetEntryLocalService = assetListAssetEntryLocalService;
	}

	/**
	 * Returns the asset list asset entry persistence.
	 *
	 * @return the asset list asset entry persistence
	 */
	public AssetListAssetEntryPersistence getAssetListAssetEntryPersistence() {
		return assetListAssetEntryPersistence;
	}

	/**
	 * Sets the asset list asset entry persistence.
	 *
	 * @param assetListAssetEntryPersistence the asset list asset entry persistence
	 */
	public void setAssetListAssetEntryPersistence(
		AssetListAssetEntryPersistence assetListAssetEntryPersistence) {
		this.assetListAssetEntryPersistence = assetListAssetEntryPersistence;
	}

	/**
	 * Returns the asset receipt local service.
	 *
	 * @return the asset receipt local service
	 */
	public AssetReceiptLocalService getAssetReceiptLocalService() {
		return assetReceiptLocalService;
	}

	/**
	 * Sets the asset receipt local service.
	 *
	 * @param assetReceiptLocalService the asset receipt local service
	 */
	public void setAssetReceiptLocalService(
		AssetReceiptLocalService assetReceiptLocalService) {
		this.assetReceiptLocalService = assetReceiptLocalService;
	}

	/**
	 * Returns the asset receipt remote service.
	 *
	 * @return the asset receipt remote service
	 */
	public AssetReceiptService getAssetReceiptService() {
		return assetReceiptService;
	}

	/**
	 * Sets the asset receipt remote service.
	 *
	 * @param assetReceiptService the asset receipt remote service
	 */
	public void setAssetReceiptService(AssetReceiptService assetReceiptService) {
		this.assetReceiptService = assetReceiptService;
	}

	/**
	 * Returns the asset receipt persistence.
	 *
	 * @return the asset receipt persistence
	 */
	public AssetReceiptPersistence getAssetReceiptPersistence() {
		return assetReceiptPersistence;
	}

	/**
	 * Sets the asset receipt persistence.
	 *
	 * @param assetReceiptPersistence the asset receipt persistence
	 */
	public void setAssetReceiptPersistence(
		AssetReceiptPersistence assetReceiptPersistence) {
		this.assetReceiptPersistence = assetReceiptPersistence;
	}

	/**
	 * Returns the asset receipt finder.
	 *
	 * @return the asset receipt finder
	 */
	public AssetReceiptFinder getAssetReceiptFinder() {
		return assetReceiptFinder;
	}

	/**
	 * Sets the asset receipt finder.
	 *
	 * @param assetReceiptFinder the asset receipt finder
	 */
	public void setAssetReceiptFinder(AssetReceiptFinder assetReceiptFinder) {
		this.assetReceiptFinder = assetReceiptFinder;
	}

	/**
	 * Returns the asset receipt license local service.
	 *
	 * @return the asset receipt license local service
	 */
	public AssetReceiptLicenseLocalService getAssetReceiptLicenseLocalService() {
		return assetReceiptLicenseLocalService;
	}

	/**
	 * Sets the asset receipt license local service.
	 *
	 * @param assetReceiptLicenseLocalService the asset receipt license local service
	 */
	public void setAssetReceiptLicenseLocalService(
		AssetReceiptLicenseLocalService assetReceiptLicenseLocalService) {
		this.assetReceiptLicenseLocalService = assetReceiptLicenseLocalService;
	}

	/**
	 * Returns the asset receipt license remote service.
	 *
	 * @return the asset receipt license remote service
	 */
	public AssetReceiptLicenseService getAssetReceiptLicenseService() {
		return assetReceiptLicenseService;
	}

	/**
	 * Sets the asset receipt license remote service.
	 *
	 * @param assetReceiptLicenseService the asset receipt license remote service
	 */
	public void setAssetReceiptLicenseService(
		AssetReceiptLicenseService assetReceiptLicenseService) {
		this.assetReceiptLicenseService = assetReceiptLicenseService;
	}

	/**
	 * Returns the asset receipt license persistence.
	 *
	 * @return the asset receipt license persistence
	 */
	public AssetReceiptLicensePersistence getAssetReceiptLicensePersistence() {
		return assetReceiptLicensePersistence;
	}

	/**
	 * Sets the asset receipt license persistence.
	 *
	 * @param assetReceiptLicensePersistence the asset receipt license persistence
	 */
	public void setAssetReceiptLicensePersistence(
		AssetReceiptLicensePersistence assetReceiptLicensePersistence) {
		this.assetReceiptLicensePersistence = assetReceiptLicensePersistence;
	}

	/**
	 * Returns the asset receipt license finder.
	 *
	 * @return the asset receipt license finder
	 */
	public AssetReceiptLicenseFinder getAssetReceiptLicenseFinder() {
		return assetReceiptLicenseFinder;
	}

	/**
	 * Sets the asset receipt license finder.
	 *
	 * @param assetReceiptLicenseFinder the asset receipt license finder
	 */
	public void setAssetReceiptLicenseFinder(
		AssetReceiptLicenseFinder assetReceiptLicenseFinder) {
		this.assetReceiptLicenseFinder = assetReceiptLicenseFinder;
	}

	/**
	 * Returns the asset receipt redeem token local service.
	 *
	 * @return the asset receipt redeem token local service
	 */
	public AssetReceiptRedeemTokenLocalService getAssetReceiptRedeemTokenLocalService() {
		return assetReceiptRedeemTokenLocalService;
	}

	/**
	 * Sets the asset receipt redeem token local service.
	 *
	 * @param assetReceiptRedeemTokenLocalService the asset receipt redeem token local service
	 */
	public void setAssetReceiptRedeemTokenLocalService(
		AssetReceiptRedeemTokenLocalService assetReceiptRedeemTokenLocalService) {
		this.assetReceiptRedeemTokenLocalService = assetReceiptRedeemTokenLocalService;
	}

	/**
	 * Returns the asset receipt redeem token remote service.
	 *
	 * @return the asset receipt redeem token remote service
	 */
	public AssetReceiptRedeemTokenService getAssetReceiptRedeemTokenService() {
		return assetReceiptRedeemTokenService;
	}

	/**
	 * Sets the asset receipt redeem token remote service.
	 *
	 * @param assetReceiptRedeemTokenService the asset receipt redeem token remote service
	 */
	public void setAssetReceiptRedeemTokenService(
		AssetReceiptRedeemTokenService assetReceiptRedeemTokenService) {
		this.assetReceiptRedeemTokenService = assetReceiptRedeemTokenService;
	}

	/**
	 * Returns the asset receipt redeem token persistence.
	 *
	 * @return the asset receipt redeem token persistence
	 */
	public AssetReceiptRedeemTokenPersistence getAssetReceiptRedeemTokenPersistence() {
		return assetReceiptRedeemTokenPersistence;
	}

	/**
	 * Sets the asset receipt redeem token persistence.
	 *
	 * @param assetReceiptRedeemTokenPersistence the asset receipt redeem token persistence
	 */
	public void setAssetReceiptRedeemTokenPersistence(
		AssetReceiptRedeemTokenPersistence assetReceiptRedeemTokenPersistence) {
		this.assetReceiptRedeemTokenPersistence = assetReceiptRedeemTokenPersistence;
	}

	/**
	 * Returns the asset receipt support local service.
	 *
	 * @return the asset receipt support local service
	 */
	public AssetReceiptSupportLocalService getAssetReceiptSupportLocalService() {
		return assetReceiptSupportLocalService;
	}

	/**
	 * Sets the asset receipt support local service.
	 *
	 * @param assetReceiptSupportLocalService the asset receipt support local service
	 */
	public void setAssetReceiptSupportLocalService(
		AssetReceiptSupportLocalService assetReceiptSupportLocalService) {
		this.assetReceiptSupportLocalService = assetReceiptSupportLocalService;
	}

	/**
	 * Returns the asset receipt support persistence.
	 *
	 * @return the asset receipt support persistence
	 */
	public AssetReceiptSupportPersistence getAssetReceiptSupportPersistence() {
		return assetReceiptSupportPersistence;
	}

	/**
	 * Sets the asset receipt support persistence.
	 *
	 * @param assetReceiptSupportPersistence the asset receipt support persistence
	 */
	public void setAssetReceiptSupportPersistence(
		AssetReceiptSupportPersistence assetReceiptSupportPersistence) {
		this.assetReceiptSupportPersistence = assetReceiptSupportPersistence;
	}

	/**
	 * Returns the asset recommendation entry local service.
	 *
	 * @return the asset recommendation entry local service
	 */
	public AssetRecommendationEntryLocalService getAssetRecommendationEntryLocalService() {
		return assetRecommendationEntryLocalService;
	}

	/**
	 * Sets the asset recommendation entry local service.
	 *
	 * @param assetRecommendationEntryLocalService the asset recommendation entry local service
	 */
	public void setAssetRecommendationEntryLocalService(
		AssetRecommendationEntryLocalService assetRecommendationEntryLocalService) {
		this.assetRecommendationEntryLocalService = assetRecommendationEntryLocalService;
	}

	/**
	 * Returns the asset recommendation entry remote service.
	 *
	 * @return the asset recommendation entry remote service
	 */
	public AssetRecommendationEntryService getAssetRecommendationEntryService() {
		return assetRecommendationEntryService;
	}

	/**
	 * Sets the asset recommendation entry remote service.
	 *
	 * @param assetRecommendationEntryService the asset recommendation entry remote service
	 */
	public void setAssetRecommendationEntryService(
		AssetRecommendationEntryService assetRecommendationEntryService) {
		this.assetRecommendationEntryService = assetRecommendationEntryService;
	}

	/**
	 * Returns the asset recommendation entry persistence.
	 *
	 * @return the asset recommendation entry persistence
	 */
	public AssetRecommendationEntryPersistence getAssetRecommendationEntryPersistence() {
		return assetRecommendationEntryPersistence;
	}

	/**
	 * Sets the asset recommendation entry persistence.
	 *
	 * @param assetRecommendationEntryPersistence the asset recommendation entry persistence
	 */
	public void setAssetRecommendationEntryPersistence(
		AssetRecommendationEntryPersistence assetRecommendationEntryPersistence) {
		this.assetRecommendationEntryPersistence = assetRecommendationEntryPersistence;
	}

	/**
	 * Returns the asset recommendation set local service.
	 *
	 * @return the asset recommendation set local service
	 */
	public AssetRecommendationSetLocalService getAssetRecommendationSetLocalService() {
		return assetRecommendationSetLocalService;
	}

	/**
	 * Sets the asset recommendation set local service.
	 *
	 * @param assetRecommendationSetLocalService the asset recommendation set local service
	 */
	public void setAssetRecommendationSetLocalService(
		AssetRecommendationSetLocalService assetRecommendationSetLocalService) {
		this.assetRecommendationSetLocalService = assetRecommendationSetLocalService;
	}

	/**
	 * Returns the asset recommendation set persistence.
	 *
	 * @return the asset recommendation set persistence
	 */
	public AssetRecommendationSetPersistence getAssetRecommendationSetPersistence() {
		return assetRecommendationSetPersistence;
	}

	/**
	 * Sets the asset recommendation set persistence.
	 *
	 * @param assetRecommendationSetPersistence the asset recommendation set persistence
	 */
	public void setAssetRecommendationSetPersistence(
		AssetRecommendationSetPersistence assetRecommendationSetPersistence) {
		this.assetRecommendationSetPersistence = assetRecommendationSetPersistence;
	}

	/**
	 * Returns the asset stats day local service.
	 *
	 * @return the asset stats day local service
	 */
	public AssetStatsDayLocalService getAssetStatsDayLocalService() {
		return assetStatsDayLocalService;
	}

	/**
	 * Sets the asset stats day local service.
	 *
	 * @param assetStatsDayLocalService the asset stats day local service
	 */
	public void setAssetStatsDayLocalService(
		AssetStatsDayLocalService assetStatsDayLocalService) {
		this.assetStatsDayLocalService = assetStatsDayLocalService;
	}

	/**
	 * Returns the asset stats day persistence.
	 *
	 * @return the asset stats day persistence
	 */
	public AssetStatsDayPersistence getAssetStatsDayPersistence() {
		return assetStatsDayPersistence;
	}

	/**
	 * Sets the asset stats day persistence.
	 *
	 * @param assetStatsDayPersistence the asset stats day persistence
	 */
	public void setAssetStatsDayPersistence(
		AssetStatsDayPersistence assetStatsDayPersistence) {
		this.assetStatsDayPersistence = assetStatsDayPersistence;
	}

	/**
	 * Returns the asset stats month local service.
	 *
	 * @return the asset stats month local service
	 */
	public AssetStatsMonthLocalService getAssetStatsMonthLocalService() {
		return assetStatsMonthLocalService;
	}

	/**
	 * Sets the asset stats month local service.
	 *
	 * @param assetStatsMonthLocalService the asset stats month local service
	 */
	public void setAssetStatsMonthLocalService(
		AssetStatsMonthLocalService assetStatsMonthLocalService) {
		this.assetStatsMonthLocalService = assetStatsMonthLocalService;
	}

	/**
	 * Returns the asset stats month persistence.
	 *
	 * @return the asset stats month persistence
	 */
	public AssetStatsMonthPersistence getAssetStatsMonthPersistence() {
		return assetStatsMonthPersistence;
	}

	/**
	 * Sets the asset stats month persistence.
	 *
	 * @param assetStatsMonthPersistence the asset stats month persistence
	 */
	public void setAssetStatsMonthPersistence(
		AssetStatsMonthPersistence assetStatsMonthPersistence) {
		this.assetStatsMonthPersistence = assetStatsMonthPersistence;
	}

	/**
	 * Returns the asset stats week local service.
	 *
	 * @return the asset stats week local service
	 */
	public AssetStatsWeekLocalService getAssetStatsWeekLocalService() {
		return assetStatsWeekLocalService;
	}

	/**
	 * Sets the asset stats week local service.
	 *
	 * @param assetStatsWeekLocalService the asset stats week local service
	 */
	public void setAssetStatsWeekLocalService(
		AssetStatsWeekLocalService assetStatsWeekLocalService) {
		this.assetStatsWeekLocalService = assetStatsWeekLocalService;
	}

	/**
	 * Returns the asset stats week persistence.
	 *
	 * @return the asset stats week persistence
	 */
	public AssetStatsWeekPersistence getAssetStatsWeekPersistence() {
		return assetStatsWeekPersistence;
	}

	/**
	 * Sets the asset stats week persistence.
	 *
	 * @param assetStatsWeekPersistence the asset stats week persistence
	 */
	public void setAssetStatsWeekPersistence(
		AssetStatsWeekPersistence assetStatsWeekPersistence) {
		this.assetStatsWeekPersistence = assetStatsWeekPersistence;
	}

	/**
	 * Returns the audit action local service.
	 *
	 * @return the audit action local service
	 */
	public AuditActionLocalService getAuditActionLocalService() {
		return auditActionLocalService;
	}

	/**
	 * Sets the audit action local service.
	 *
	 * @param auditActionLocalService the audit action local service
	 */
	public void setAuditActionLocalService(
		AuditActionLocalService auditActionLocalService) {
		this.auditActionLocalService = auditActionLocalService;
	}

	/**
	 * Returns the audit action remote service.
	 *
	 * @return the audit action remote service
	 */
	public AuditActionService getAuditActionService() {
		return auditActionService;
	}

	/**
	 * Sets the audit action remote service.
	 *
	 * @param auditActionService the audit action remote service
	 */
	public void setAuditActionService(AuditActionService auditActionService) {
		this.auditActionService = auditActionService;
	}

	/**
	 * Returns the audit action persistence.
	 *
	 * @return the audit action persistence
	 */
	public AuditActionPersistence getAuditActionPersistence() {
		return auditActionPersistence;
	}

	/**
	 * Sets the audit action persistence.
	 *
	 * @param auditActionPersistence the audit action persistence
	 */
	public void setAuditActionPersistence(
		AuditActionPersistence auditActionPersistence) {
		this.auditActionPersistence = auditActionPersistence;
	}

	/**
	 * Returns the audit entry local service.
	 *
	 * @return the audit entry local service
	 */
	public AuditEntryLocalService getAuditEntryLocalService() {
		return auditEntryLocalService;
	}

	/**
	 * Sets the audit entry local service.
	 *
	 * @param auditEntryLocalService the audit entry local service
	 */
	public void setAuditEntryLocalService(
		AuditEntryLocalService auditEntryLocalService) {
		this.auditEntryLocalService = auditEntryLocalService;
	}

	/**
	 * Returns the audit entry remote service.
	 *
	 * @return the audit entry remote service
	 */
	public AuditEntryService getAuditEntryService() {
		return auditEntryService;
	}

	/**
	 * Sets the audit entry remote service.
	 *
	 * @param auditEntryService the audit entry remote service
	 */
	public void setAuditEntryService(AuditEntryService auditEntryService) {
		this.auditEntryService = auditEntryService;
	}

	/**
	 * Returns the audit entry persistence.
	 *
	 * @return the audit entry persistence
	 */
	public AuditEntryPersistence getAuditEntryPersistence() {
		return auditEntryPersistence;
	}

	/**
	 * Sets the audit entry persistence.
	 *
	 * @param auditEntryPersistence the audit entry persistence
	 */
	public void setAuditEntryPersistence(
		AuditEntryPersistence auditEntryPersistence) {
		this.auditEntryPersistence = auditEntryPersistence;
	}

	/**
	 * Returns the contract audit local service.
	 *
	 * @return the contract audit local service
	 */
	public ContractAuditLocalService getContractAuditLocalService() {
		return contractAuditLocalService;
	}

	/**
	 * Sets the contract audit local service.
	 *
	 * @param contractAuditLocalService the contract audit local service
	 */
	public void setContractAuditLocalService(
		ContractAuditLocalService contractAuditLocalService) {
		this.contractAuditLocalService = contractAuditLocalService;
	}

	/**
	 * Returns the contract audit remote service.
	 *
	 * @return the contract audit remote service
	 */
	public ContractAuditService getContractAuditService() {
		return contractAuditService;
	}

	/**
	 * Sets the contract audit remote service.
	 *
	 * @param contractAuditService the contract audit remote service
	 */
	public void setContractAuditService(
		ContractAuditService contractAuditService) {
		this.contractAuditService = contractAuditService;
	}

	/**
	 * Returns the contract audit persistence.
	 *
	 * @return the contract audit persistence
	 */
	public ContractAuditPersistence getContractAuditPersistence() {
		return contractAuditPersistence;
	}

	/**
	 * Sets the contract audit persistence.
	 *
	 * @param contractAuditPersistence the contract audit persistence
	 */
	public void setContractAuditPersistence(
		ContractAuditPersistence contractAuditPersistence) {
		this.contractAuditPersistence = contractAuditPersistence;
	}

	/**
	 * Returns the contract entry local service.
	 *
	 * @return the contract entry local service
	 */
	public ContractEntryLocalService getContractEntryLocalService() {
		return contractEntryLocalService;
	}

	/**
	 * Sets the contract entry local service.
	 *
	 * @param contractEntryLocalService the contract entry local service
	 */
	public void setContractEntryLocalService(
		ContractEntryLocalService contractEntryLocalService) {
		this.contractEntryLocalService = contractEntryLocalService;
	}

	/**
	 * Returns the contract entry remote service.
	 *
	 * @return the contract entry remote service
	 */
	public ContractEntryService getContractEntryService() {
		return contractEntryService;
	}

	/**
	 * Sets the contract entry remote service.
	 *
	 * @param contractEntryService the contract entry remote service
	 */
	public void setContractEntryService(
		ContractEntryService contractEntryService) {
		this.contractEntryService = contractEntryService;
	}

	/**
	 * Returns the contract entry persistence.
	 *
	 * @return the contract entry persistence
	 */
	public ContractEntryPersistence getContractEntryPersistence() {
		return contractEntryPersistence;
	}

	/**
	 * Sets the contract entry persistence.
	 *
	 * @param contractEntryPersistence the contract entry persistence
	 */
	public void setContractEntryPersistence(
		ContractEntryPersistence contractEntryPersistence) {
		this.contractEntryPersistence = contractEntryPersistence;
	}

	/**
	 * Returns the corp entry local service.
	 *
	 * @return the corp entry local service
	 */
	public CorpEntryLocalService getCorpEntryLocalService() {
		return corpEntryLocalService;
	}

	/**
	 * Sets the corp entry local service.
	 *
	 * @param corpEntryLocalService the corp entry local service
	 */
	public void setCorpEntryLocalService(
		CorpEntryLocalService corpEntryLocalService) {
		this.corpEntryLocalService = corpEntryLocalService;
	}

	/**
	 * Returns the corp entry remote service.
	 *
	 * @return the corp entry remote service
	 */
	public CorpEntryService getCorpEntryService() {
		return corpEntryService;
	}

	/**
	 * Sets the corp entry remote service.
	 *
	 * @param corpEntryService the corp entry remote service
	 */
	public void setCorpEntryService(CorpEntryService corpEntryService) {
		this.corpEntryService = corpEntryService;
	}

	/**
	 * Returns the corp entry persistence.
	 *
	 * @return the corp entry persistence
	 */
	public CorpEntryPersistence getCorpEntryPersistence() {
		return corpEntryPersistence;
	}

	/**
	 * Sets the corp entry persistence.
	 *
	 * @param corpEntryPersistence the corp entry persistence
	 */
	public void setCorpEntryPersistence(
		CorpEntryPersistence corpEntryPersistence) {
		this.corpEntryPersistence = corpEntryPersistence;
	}

	/**
	 * Returns the corp entry finder.
	 *
	 * @return the corp entry finder
	 */
	public CorpEntryFinder getCorpEntryFinder() {
		return corpEntryFinder;
	}

	/**
	 * Sets the corp entry finder.
	 *
	 * @param corpEntryFinder the corp entry finder
	 */
	public void setCorpEntryFinder(CorpEntryFinder corpEntryFinder) {
		this.corpEntryFinder = corpEntryFinder;
	}

	/**
	 * Returns the corp group local service.
	 *
	 * @return the corp group local service
	 */
	public CorpGroupLocalService getCorpGroupLocalService() {
		return corpGroupLocalService;
	}

	/**
	 * Sets the corp group local service.
	 *
	 * @param corpGroupLocalService the corp group local service
	 */
	public void setCorpGroupLocalService(
		CorpGroupLocalService corpGroupLocalService) {
		this.corpGroupLocalService = corpGroupLocalService;
	}

	/**
	 * Returns the corp group remote service.
	 *
	 * @return the corp group remote service
	 */
	public CorpGroupService getCorpGroupService() {
		return corpGroupService;
	}

	/**
	 * Sets the corp group remote service.
	 *
	 * @param corpGroupService the corp group remote service
	 */
	public void setCorpGroupService(CorpGroupService corpGroupService) {
		this.corpGroupService = corpGroupService;
	}

	/**
	 * Returns the corp group persistence.
	 *
	 * @return the corp group persistence
	 */
	public CorpGroupPersistence getCorpGroupPersistence() {
		return corpGroupPersistence;
	}

	/**
	 * Sets the corp group persistence.
	 *
	 * @param corpGroupPersistence the corp group persistence
	 */
	public void setCorpGroupPersistence(
		CorpGroupPersistence corpGroupPersistence) {
		this.corpGroupPersistence = corpGroupPersistence;
	}

	/**
	 * Returns the corp membership request local service.
	 *
	 * @return the corp membership request local service
	 */
	public CorpMembershipRequestLocalService getCorpMembershipRequestLocalService() {
		return corpMembershipRequestLocalService;
	}

	/**
	 * Sets the corp membership request local service.
	 *
	 * @param corpMembershipRequestLocalService the corp membership request local service
	 */
	public void setCorpMembershipRequestLocalService(
		CorpMembershipRequestLocalService corpMembershipRequestLocalService) {
		this.corpMembershipRequestLocalService = corpMembershipRequestLocalService;
	}

	/**
	 * Returns the corp membership request remote service.
	 *
	 * @return the corp membership request remote service
	 */
	public CorpMembershipRequestService getCorpMembershipRequestService() {
		return corpMembershipRequestService;
	}

	/**
	 * Sets the corp membership request remote service.
	 *
	 * @param corpMembershipRequestService the corp membership request remote service
	 */
	public void setCorpMembershipRequestService(
		CorpMembershipRequestService corpMembershipRequestService) {
		this.corpMembershipRequestService = corpMembershipRequestService;
	}

	/**
	 * Returns the corp membership request persistence.
	 *
	 * @return the corp membership request persistence
	 */
	public CorpMembershipRequestPersistence getCorpMembershipRequestPersistence() {
		return corpMembershipRequestPersistence;
	}

	/**
	 * Sets the corp membership request persistence.
	 *
	 * @param corpMembershipRequestPersistence the corp membership request persistence
	 */
	public void setCorpMembershipRequestPersistence(
		CorpMembershipRequestPersistence corpMembershipRequestPersistence) {
		this.corpMembershipRequestPersistence = corpMembershipRequestPersistence;
	}

	/**
	 * Returns the corp project local service.
	 *
	 * @return the corp project local service
	 */
	public CorpProjectLocalService getCorpProjectLocalService() {
		return corpProjectLocalService;
	}

	/**
	 * Sets the corp project local service.
	 *
	 * @param corpProjectLocalService the corp project local service
	 */
	public void setCorpProjectLocalService(
		CorpProjectLocalService corpProjectLocalService) {
		this.corpProjectLocalService = corpProjectLocalService;
	}

	/**
	 * Returns the corp project remote service.
	 *
	 * @return the corp project remote service
	 */
	public CorpProjectService getCorpProjectService() {
		return corpProjectService;
	}

	/**
	 * Sets the corp project remote service.
	 *
	 * @param corpProjectService the corp project remote service
	 */
	public void setCorpProjectService(CorpProjectService corpProjectService) {
		this.corpProjectService = corpProjectService;
	}

	/**
	 * Returns the corp project persistence.
	 *
	 * @return the corp project persistence
	 */
	public CorpProjectPersistence getCorpProjectPersistence() {
		return corpProjectPersistence;
	}

	/**
	 * Sets the corp project persistence.
	 *
	 * @param corpProjectPersistence the corp project persistence
	 */
	public void setCorpProjectPersistence(
		CorpProjectPersistence corpProjectPersistence) {
		this.corpProjectPersistence = corpProjectPersistence;
	}

	/**
	 * Returns the corp project message local service.
	 *
	 * @return the corp project message local service
	 */
	public CorpProjectMessageLocalService getCorpProjectMessageLocalService() {
		return corpProjectMessageLocalService;
	}

	/**
	 * Sets the corp project message local service.
	 *
	 * @param corpProjectMessageLocalService the corp project message local service
	 */
	public void setCorpProjectMessageLocalService(
		CorpProjectMessageLocalService corpProjectMessageLocalService) {
		this.corpProjectMessageLocalService = corpProjectMessageLocalService;
	}

	/**
	 * Returns the corp project message remote service.
	 *
	 * @return the corp project message remote service
	 */
	public CorpProjectMessageService getCorpProjectMessageService() {
		return corpProjectMessageService;
	}

	/**
	 * Sets the corp project message remote service.
	 *
	 * @param corpProjectMessageService the corp project message remote service
	 */
	public void setCorpProjectMessageService(
		CorpProjectMessageService corpProjectMessageService) {
		this.corpProjectMessageService = corpProjectMessageService;
	}

	/**
	 * Returns the corp project message persistence.
	 *
	 * @return the corp project message persistence
	 */
	public CorpProjectMessagePersistence getCorpProjectMessagePersistence() {
		return corpProjectMessagePersistence;
	}

	/**
	 * Sets the corp project message persistence.
	 *
	 * @param corpProjectMessagePersistence the corp project message persistence
	 */
	public void setCorpProjectMessagePersistence(
		CorpProjectMessagePersistence corpProjectMessagePersistence) {
		this.corpProjectMessagePersistence = corpProjectMessagePersistence;
	}

	/**
	 * Returns the country app pricing local service.
	 *
	 * @return the country app pricing local service
	 */
	public CountryAppPricingLocalService getCountryAppPricingLocalService() {
		return countryAppPricingLocalService;
	}

	/**
	 * Sets the country app pricing local service.
	 *
	 * @param countryAppPricingLocalService the country app pricing local service
	 */
	public void setCountryAppPricingLocalService(
		CountryAppPricingLocalService countryAppPricingLocalService) {
		this.countryAppPricingLocalService = countryAppPricingLocalService;
	}

	/**
	 * Returns the country app pricing remote service.
	 *
	 * @return the country app pricing remote service
	 */
	public CountryAppPricingService getCountryAppPricingService() {
		return countryAppPricingService;
	}

	/**
	 * Sets the country app pricing remote service.
	 *
	 * @param countryAppPricingService the country app pricing remote service
	 */
	public void setCountryAppPricingService(
		CountryAppPricingService countryAppPricingService) {
		this.countryAppPricingService = countryAppPricingService;
	}

	/**
	 * Returns the country app pricing persistence.
	 *
	 * @return the country app pricing persistence
	 */
	public CountryAppPricingPersistence getCountryAppPricingPersistence() {
		return countryAppPricingPersistence;
	}

	/**
	 * Sets the country app pricing persistence.
	 *
	 * @param countryAppPricingPersistence the country app pricing persistence
	 */
	public void setCountryAppPricingPersistence(
		CountryAppPricingPersistence countryAppPricingPersistence) {
		this.countryAppPricingPersistence = countryAppPricingPersistence;
	}

	/**
	 * Returns the currency entry local service.
	 *
	 * @return the currency entry local service
	 */
	public CurrencyEntryLocalService getCurrencyEntryLocalService() {
		return currencyEntryLocalService;
	}

	/**
	 * Sets the currency entry local service.
	 *
	 * @param currencyEntryLocalService the currency entry local service
	 */
	public void setCurrencyEntryLocalService(
		CurrencyEntryLocalService currencyEntryLocalService) {
		this.currencyEntryLocalService = currencyEntryLocalService;
	}

	/**
	 * Returns the currency entry remote service.
	 *
	 * @return the currency entry remote service
	 */
	public CurrencyEntryService getCurrencyEntryService() {
		return currencyEntryService;
	}

	/**
	 * Sets the currency entry remote service.
	 *
	 * @param currencyEntryService the currency entry remote service
	 */
	public void setCurrencyEntryService(
		CurrencyEntryService currencyEntryService) {
		this.currencyEntryService = currencyEntryService;
	}

	/**
	 * Returns the currency entry persistence.
	 *
	 * @return the currency entry persistence
	 */
	public CurrencyEntryPersistence getCurrencyEntryPersistence() {
		return currencyEntryPersistence;
	}

	/**
	 * Sets the currency entry persistence.
	 *
	 * @param currencyEntryPersistence the currency entry persistence
	 */
	public void setCurrencyEntryPersistence(
		CurrencyEntryPersistence currencyEntryPersistence) {
		this.currencyEntryPersistence = currencyEntryPersistence;
	}

	/**
	 * Returns the developer entry local service.
	 *
	 * @return the developer entry local service
	 */
	public DeveloperEntryLocalService getDeveloperEntryLocalService() {
		return developerEntryLocalService;
	}

	/**
	 * Sets the developer entry local service.
	 *
	 * @param developerEntryLocalService the developer entry local service
	 */
	public void setDeveloperEntryLocalService(
		DeveloperEntryLocalService developerEntryLocalService) {
		this.developerEntryLocalService = developerEntryLocalService;
	}

	/**
	 * Returns the developer entry remote service.
	 *
	 * @return the developer entry remote service
	 */
	public DeveloperEntryService getDeveloperEntryService() {
		return developerEntryService;
	}

	/**
	 * Sets the developer entry remote service.
	 *
	 * @param developerEntryService the developer entry remote service
	 */
	public void setDeveloperEntryService(
		DeveloperEntryService developerEntryService) {
		this.developerEntryService = developerEntryService;
	}

	/**
	 * Returns the developer entry persistence.
	 *
	 * @return the developer entry persistence
	 */
	public DeveloperEntryPersistence getDeveloperEntryPersistence() {
		return developerEntryPersistence;
	}

	/**
	 * Sets the developer entry persistence.
	 *
	 * @param developerEntryPersistence the developer entry persistence
	 */
	public void setDeveloperEntryPersistence(
		DeveloperEntryPersistence developerEntryPersistence) {
		this.developerEntryPersistence = developerEntryPersistence;
	}

	/**
	 * Returns the external ID mapper local service.
	 *
	 * @return the external ID mapper local service
	 */
	public ExternalIdMapperLocalService getExternalIdMapperLocalService() {
		return externalIdMapperLocalService;
	}

	/**
	 * Sets the external ID mapper local service.
	 *
	 * @param externalIdMapperLocalService the external ID mapper local service
	 */
	public void setExternalIdMapperLocalService(
		ExternalIdMapperLocalService externalIdMapperLocalService) {
		this.externalIdMapperLocalService = externalIdMapperLocalService;
	}

	/**
	 * Returns the external ID mapper persistence.
	 *
	 * @return the external ID mapper persistence
	 */
	public ExternalIdMapperPersistence getExternalIdMapperPersistence() {
		return externalIdMapperPersistence;
	}

	/**
	 * Sets the external ID mapper persistence.
	 *
	 * @param externalIdMapperPersistence the external ID mapper persistence
	 */
	public void setExternalIdMapperPersistence(
		ExternalIdMapperPersistence externalIdMapperPersistence) {
		this.externalIdMapperPersistence = externalIdMapperPersistence;
	}

	/**
	 * Returns the feedback entry local service.
	 *
	 * @return the feedback entry local service
	 */
	public FeedbackEntryLocalService getFeedbackEntryLocalService() {
		return feedbackEntryLocalService;
	}

	/**
	 * Sets the feedback entry local service.
	 *
	 * @param feedbackEntryLocalService the feedback entry local service
	 */
	public void setFeedbackEntryLocalService(
		FeedbackEntryLocalService feedbackEntryLocalService) {
		this.feedbackEntryLocalService = feedbackEntryLocalService;
	}

	/**
	 * Returns the feedback entry remote service.
	 *
	 * @return the feedback entry remote service
	 */
	public FeedbackEntryService getFeedbackEntryService() {
		return feedbackEntryService;
	}

	/**
	 * Sets the feedback entry remote service.
	 *
	 * @param feedbackEntryService the feedback entry remote service
	 */
	public void setFeedbackEntryService(
		FeedbackEntryService feedbackEntryService) {
		this.feedbackEntryService = feedbackEntryService;
	}

	/**
	 * Returns the feedback entry persistence.
	 *
	 * @return the feedback entry persistence
	 */
	public FeedbackEntryPersistence getFeedbackEntryPersistence() {
		return feedbackEntryPersistence;
	}

	/**
	 * Sets the feedback entry persistence.
	 *
	 * @param feedbackEntryPersistence the feedback entry persistence
	 */
	public void setFeedbackEntryPersistence(
		FeedbackEntryPersistence feedbackEntryPersistence) {
		this.feedbackEntryPersistence = feedbackEntryPersistence;
	}

	/**
	 * Returns the holiday calendar local service.
	 *
	 * @return the holiday calendar local service
	 */
	public HolidayCalendarLocalService getHolidayCalendarLocalService() {
		return holidayCalendarLocalService;
	}

	/**
	 * Sets the holiday calendar local service.
	 *
	 * @param holidayCalendarLocalService the holiday calendar local service
	 */
	public void setHolidayCalendarLocalService(
		HolidayCalendarLocalService holidayCalendarLocalService) {
		this.holidayCalendarLocalService = holidayCalendarLocalService;
	}

	/**
	 * Returns the holiday calendar remote service.
	 *
	 * @return the holiday calendar remote service
	 */
	public HolidayCalendarService getHolidayCalendarService() {
		return holidayCalendarService;
	}

	/**
	 * Sets the holiday calendar remote service.
	 *
	 * @param holidayCalendarService the holiday calendar remote service
	 */
	public void setHolidayCalendarService(
		HolidayCalendarService holidayCalendarService) {
		this.holidayCalendarService = holidayCalendarService;
	}

	/**
	 * Returns the holiday calendar persistence.
	 *
	 * @return the holiday calendar persistence
	 */
	public HolidayCalendarPersistence getHolidayCalendarPersistence() {
		return holidayCalendarPersistence;
	}

	/**
	 * Sets the holiday calendar persistence.
	 *
	 * @param holidayCalendarPersistence the holiday calendar persistence
	 */
	public void setHolidayCalendarPersistence(
		HolidayCalendarPersistence holidayCalendarPersistence) {
		this.holidayCalendarPersistence = holidayCalendarPersistence;
	}

	/**
	 * Returns the holiday calendar rel local service.
	 *
	 * @return the holiday calendar rel local service
	 */
	public HolidayCalendarRelLocalService getHolidayCalendarRelLocalService() {
		return holidayCalendarRelLocalService;
	}

	/**
	 * Sets the holiday calendar rel local service.
	 *
	 * @param holidayCalendarRelLocalService the holiday calendar rel local service
	 */
	public void setHolidayCalendarRelLocalService(
		HolidayCalendarRelLocalService holidayCalendarRelLocalService) {
		this.holidayCalendarRelLocalService = holidayCalendarRelLocalService;
	}

	/**
	 * Returns the holiday calendar rel remote service.
	 *
	 * @return the holiday calendar rel remote service
	 */
	public HolidayCalendarRelService getHolidayCalendarRelService() {
		return holidayCalendarRelService;
	}

	/**
	 * Sets the holiday calendar rel remote service.
	 *
	 * @param holidayCalendarRelService the holiday calendar rel remote service
	 */
	public void setHolidayCalendarRelService(
		HolidayCalendarRelService holidayCalendarRelService) {
		this.holidayCalendarRelService = holidayCalendarRelService;
	}

	/**
	 * Returns the holiday calendar rel persistence.
	 *
	 * @return the holiday calendar rel persistence
	 */
	public HolidayCalendarRelPersistence getHolidayCalendarRelPersistence() {
		return holidayCalendarRelPersistence;
	}

	/**
	 * Sets the holiday calendar rel persistence.
	 *
	 * @param holidayCalendarRelPersistence the holiday calendar rel persistence
	 */
	public void setHolidayCalendarRelPersistence(
		HolidayCalendarRelPersistence holidayCalendarRelPersistence) {
		this.holidayCalendarRelPersistence = holidayCalendarRelPersistence;
	}

	/**
	 * Returns the holiday entry local service.
	 *
	 * @return the holiday entry local service
	 */
	public HolidayEntryLocalService getHolidayEntryLocalService() {
		return holidayEntryLocalService;
	}

	/**
	 * Sets the holiday entry local service.
	 *
	 * @param holidayEntryLocalService the holiday entry local service
	 */
	public void setHolidayEntryLocalService(
		HolidayEntryLocalService holidayEntryLocalService) {
		this.holidayEntryLocalService = holidayEntryLocalService;
	}

	/**
	 * Returns the holiday entry remote service.
	 *
	 * @return the holiday entry remote service
	 */
	public HolidayEntryService getHolidayEntryService() {
		return holidayEntryService;
	}

	/**
	 * Sets the holiday entry remote service.
	 *
	 * @param holidayEntryService the holiday entry remote service
	 */
	public void setHolidayEntryService(HolidayEntryService holidayEntryService) {
		this.holidayEntryService = holidayEntryService;
	}

	/**
	 * Returns the holiday entry persistence.
	 *
	 * @return the holiday entry persistence
	 */
	public HolidayEntryPersistence getHolidayEntryPersistence() {
		return holidayEntryPersistence;
	}

	/**
	 * Sets the holiday entry persistence.
	 *
	 * @param holidayEntryPersistence the holiday entry persistence
	 */
	public void setHolidayEntryPersistence(
		HolidayEntryPersistence holidayEntryPersistence) {
		this.holidayEntryPersistence = holidayEntryPersistence;
	}

	/**
	 * Returns the holiday entry finder.
	 *
	 * @return the holiday entry finder
	 */
	public HolidayEntryFinder getHolidayEntryFinder() {
		return holidayEntryFinder;
	}

	/**
	 * Sets the holiday entry finder.
	 *
	 * @param holidayEntryFinder the holiday entry finder
	 */
	public void setHolidayEntryFinder(HolidayEntryFinder holidayEntryFinder) {
		this.holidayEntryFinder = holidayEntryFinder;
	}

	/**
	 * Returns the l c s subscription entry local service.
	 *
	 * @return the l c s subscription entry local service
	 */
	public LCSSubscriptionEntryLocalService getLCSSubscriptionEntryLocalService() {
		return lcsSubscriptionEntryLocalService;
	}

	/**
	 * Sets the l c s subscription entry local service.
	 *
	 * @param lcsSubscriptionEntryLocalService the l c s subscription entry local service
	 */
	public void setLCSSubscriptionEntryLocalService(
		LCSSubscriptionEntryLocalService lcsSubscriptionEntryLocalService) {
		this.lcsSubscriptionEntryLocalService = lcsSubscriptionEntryLocalService;
	}

	/**
	 * Returns the l c s subscription entry remote service.
	 *
	 * @return the l c s subscription entry remote service
	 */
	public LCSSubscriptionEntryService getLCSSubscriptionEntryService() {
		return lcsSubscriptionEntryService;
	}

	/**
	 * Sets the l c s subscription entry remote service.
	 *
	 * @param lcsSubscriptionEntryService the l c s subscription entry remote service
	 */
	public void setLCSSubscriptionEntryService(
		LCSSubscriptionEntryService lcsSubscriptionEntryService) {
		this.lcsSubscriptionEntryService = lcsSubscriptionEntryService;
	}

	/**
	 * Returns the l c s subscription entry persistence.
	 *
	 * @return the l c s subscription entry persistence
	 */
	public LCSSubscriptionEntryPersistence getLCSSubscriptionEntryPersistence() {
		return lcsSubscriptionEntryPersistence;
	}

	/**
	 * Sets the l c s subscription entry persistence.
	 *
	 * @param lcsSubscriptionEntryPersistence the l c s subscription entry persistence
	 */
	public void setLCSSubscriptionEntryPersistence(
		LCSSubscriptionEntryPersistence lcsSubscriptionEntryPersistence) {
		this.lcsSubscriptionEntryPersistence = lcsSubscriptionEntryPersistence;
	}

	/**
	 * Returns the license entry local service.
	 *
	 * @return the license entry local service
	 */
	public LicenseEntryLocalService getLicenseEntryLocalService() {
		return licenseEntryLocalService;
	}

	/**
	 * Sets the license entry local service.
	 *
	 * @param licenseEntryLocalService the license entry local service
	 */
	public void setLicenseEntryLocalService(
		LicenseEntryLocalService licenseEntryLocalService) {
		this.licenseEntryLocalService = licenseEntryLocalService;
	}

	/**
	 * Returns the license entry remote service.
	 *
	 * @return the license entry remote service
	 */
	public LicenseEntryService getLicenseEntryService() {
		return licenseEntryService;
	}

	/**
	 * Sets the license entry remote service.
	 *
	 * @param licenseEntryService the license entry remote service
	 */
	public void setLicenseEntryService(LicenseEntryService licenseEntryService) {
		this.licenseEntryService = licenseEntryService;
	}

	/**
	 * Returns the license entry persistence.
	 *
	 * @return the license entry persistence
	 */
	public LicenseEntryPersistence getLicenseEntryPersistence() {
		return licenseEntryPersistence;
	}

	/**
	 * Sets the license entry persistence.
	 *
	 * @param licenseEntryPersistence the license entry persistence
	 */
	public void setLicenseEntryPersistence(
		LicenseEntryPersistence licenseEntryPersistence) {
		this.licenseEntryPersistence = licenseEntryPersistence;
	}

	/**
	 * Returns the license key local service.
	 *
	 * @return the license key local service
	 */
	public LicenseKeyLocalService getLicenseKeyLocalService() {
		return licenseKeyLocalService;
	}

	/**
	 * Sets the license key local service.
	 *
	 * @param licenseKeyLocalService the license key local service
	 */
	public void setLicenseKeyLocalService(
		LicenseKeyLocalService licenseKeyLocalService) {
		this.licenseKeyLocalService = licenseKeyLocalService;
	}

	/**
	 * Returns the license key remote service.
	 *
	 * @return the license key remote service
	 */
	public LicenseKeyService getLicenseKeyService() {
		return licenseKeyService;
	}

	/**
	 * Sets the license key remote service.
	 *
	 * @param licenseKeyService the license key remote service
	 */
	public void setLicenseKeyService(LicenseKeyService licenseKeyService) {
		this.licenseKeyService = licenseKeyService;
	}

	/**
	 * Returns the license key persistence.
	 *
	 * @return the license key persistence
	 */
	public LicenseKeyPersistence getLicenseKeyPersistence() {
		return licenseKeyPersistence;
	}

	/**
	 * Sets the license key persistence.
	 *
	 * @param licenseKeyPersistence the license key persistence
	 */
	public void setLicenseKeyPersistence(
		LicenseKeyPersistence licenseKeyPersistence) {
		this.licenseKeyPersistence = licenseKeyPersistence;
	}

	/**
	 * Returns the license key finder.
	 *
	 * @return the license key finder
	 */
	public LicenseKeyFinder getLicenseKeyFinder() {
		return licenseKeyFinder;
	}

	/**
	 * Sets the license key finder.
	 *
	 * @param licenseKeyFinder the license key finder
	 */
	public void setLicenseKeyFinder(LicenseKeyFinder licenseKeyFinder) {
		this.licenseKeyFinder = licenseKeyFinder;
	}

	/**
	 * Returns the license key set local service.
	 *
	 * @return the license key set local service
	 */
	public LicenseKeySetLocalService getLicenseKeySetLocalService() {
		return licenseKeySetLocalService;
	}

	/**
	 * Sets the license key set local service.
	 *
	 * @param licenseKeySetLocalService the license key set local service
	 */
	public void setLicenseKeySetLocalService(
		LicenseKeySetLocalService licenseKeySetLocalService) {
		this.licenseKeySetLocalService = licenseKeySetLocalService;
	}

	/**
	 * Returns the license key set remote service.
	 *
	 * @return the license key set remote service
	 */
	public LicenseKeySetService getLicenseKeySetService() {
		return licenseKeySetService;
	}

	/**
	 * Sets the license key set remote service.
	 *
	 * @param licenseKeySetService the license key set remote service
	 */
	public void setLicenseKeySetService(
		LicenseKeySetService licenseKeySetService) {
		this.licenseKeySetService = licenseKeySetService;
	}

	/**
	 * Returns the license key set persistence.
	 *
	 * @return the license key set persistence
	 */
	public LicenseKeySetPersistence getLicenseKeySetPersistence() {
		return licenseKeySetPersistence;
	}

	/**
	 * Sets the license key set persistence.
	 *
	 * @param licenseKeySetPersistence the license key set persistence
	 */
	public void setLicenseKeySetPersistence(
		LicenseKeySetPersistence licenseKeySetPersistence) {
		this.licenseKeySetPersistence = licenseKeySetPersistence;
	}

	/**
	 * Returns the marketing event local service.
	 *
	 * @return the marketing event local service
	 */
	public MarketingEventLocalService getMarketingEventLocalService() {
		return marketingEventLocalService;
	}

	/**
	 * Sets the marketing event local service.
	 *
	 * @param marketingEventLocalService the marketing event local service
	 */
	public void setMarketingEventLocalService(
		MarketingEventLocalService marketingEventLocalService) {
		this.marketingEventLocalService = marketingEventLocalService;
	}

	/**
	 * Returns the marketing event persistence.
	 *
	 * @return the marketing event persistence
	 */
	public MarketingEventPersistence getMarketingEventPersistence() {
		return marketingEventPersistence;
	}

	/**
	 * Sets the marketing event persistence.
	 *
	 * @param marketingEventPersistence the marketing event persistence
	 */
	public void setMarketingEventPersistence(
		MarketingEventPersistence marketingEventPersistence) {
		this.marketingEventPersistence = marketingEventPersistence;
	}

	/**
	 * Returns the offering bundle local service.
	 *
	 * @return the offering bundle local service
	 */
	public OfferingBundleLocalService getOfferingBundleLocalService() {
		return offeringBundleLocalService;
	}

	/**
	 * Sets the offering bundle local service.
	 *
	 * @param offeringBundleLocalService the offering bundle local service
	 */
	public void setOfferingBundleLocalService(
		OfferingBundleLocalService offeringBundleLocalService) {
		this.offeringBundleLocalService = offeringBundleLocalService;
	}

	/**
	 * Returns the offering bundle remote service.
	 *
	 * @return the offering bundle remote service
	 */
	public OfferingBundleService getOfferingBundleService() {
		return offeringBundleService;
	}

	/**
	 * Sets the offering bundle remote service.
	 *
	 * @param offeringBundleService the offering bundle remote service
	 */
	public void setOfferingBundleService(
		OfferingBundleService offeringBundleService) {
		this.offeringBundleService = offeringBundleService;
	}

	/**
	 * Returns the offering bundle persistence.
	 *
	 * @return the offering bundle persistence
	 */
	public OfferingBundlePersistence getOfferingBundlePersistence() {
		return offeringBundlePersistence;
	}

	/**
	 * Sets the offering bundle persistence.
	 *
	 * @param offeringBundlePersistence the offering bundle persistence
	 */
	public void setOfferingBundlePersistence(
		OfferingBundlePersistence offeringBundlePersistence) {
		this.offeringBundlePersistence = offeringBundlePersistence;
	}

	/**
	 * Returns the offering definition local service.
	 *
	 * @return the offering definition local service
	 */
	public OfferingDefinitionLocalService getOfferingDefinitionLocalService() {
		return offeringDefinitionLocalService;
	}

	/**
	 * Sets the offering definition local service.
	 *
	 * @param offeringDefinitionLocalService the offering definition local service
	 */
	public void setOfferingDefinitionLocalService(
		OfferingDefinitionLocalService offeringDefinitionLocalService) {
		this.offeringDefinitionLocalService = offeringDefinitionLocalService;
	}

	/**
	 * Returns the offering definition remote service.
	 *
	 * @return the offering definition remote service
	 */
	public OfferingDefinitionService getOfferingDefinitionService() {
		return offeringDefinitionService;
	}

	/**
	 * Sets the offering definition remote service.
	 *
	 * @param offeringDefinitionService the offering definition remote service
	 */
	public void setOfferingDefinitionService(
		OfferingDefinitionService offeringDefinitionService) {
		this.offeringDefinitionService = offeringDefinitionService;
	}

	/**
	 * Returns the offering definition persistence.
	 *
	 * @return the offering definition persistence
	 */
	public OfferingDefinitionPersistence getOfferingDefinitionPersistence() {
		return offeringDefinitionPersistence;
	}

	/**
	 * Sets the offering definition persistence.
	 *
	 * @param offeringDefinitionPersistence the offering definition persistence
	 */
	public void setOfferingDefinitionPersistence(
		OfferingDefinitionPersistence offeringDefinitionPersistence) {
		this.offeringDefinitionPersistence = offeringDefinitionPersistence;
	}

	/**
	 * Returns the offering entry local service.
	 *
	 * @return the offering entry local service
	 */
	public OfferingEntryLocalService getOfferingEntryLocalService() {
		return offeringEntryLocalService;
	}

	/**
	 * Sets the offering entry local service.
	 *
	 * @param offeringEntryLocalService the offering entry local service
	 */
	public void setOfferingEntryLocalService(
		OfferingEntryLocalService offeringEntryLocalService) {
		this.offeringEntryLocalService = offeringEntryLocalService;
	}

	/**
	 * Returns the offering entry remote service.
	 *
	 * @return the offering entry remote service
	 */
	public OfferingEntryService getOfferingEntryService() {
		return offeringEntryService;
	}

	/**
	 * Sets the offering entry remote service.
	 *
	 * @param offeringEntryService the offering entry remote service
	 */
	public void setOfferingEntryService(
		OfferingEntryService offeringEntryService) {
		this.offeringEntryService = offeringEntryService;
	}

	/**
	 * Returns the offering entry persistence.
	 *
	 * @return the offering entry persistence
	 */
	public OfferingEntryPersistence getOfferingEntryPersistence() {
		return offeringEntryPersistence;
	}

	/**
	 * Sets the offering entry persistence.
	 *
	 * @param offeringEntryPersistence the offering entry persistence
	 */
	public void setOfferingEntryPersistence(
		OfferingEntryPersistence offeringEntryPersistence) {
		this.offeringEntryPersistence = offeringEntryPersistence;
	}

	/**
	 * Returns the offering entry finder.
	 *
	 * @return the offering entry finder
	 */
	public OfferingEntryFinder getOfferingEntryFinder() {
		return offeringEntryFinder;
	}

	/**
	 * Sets the offering entry finder.
	 *
	 * @param offeringEntryFinder the offering entry finder
	 */
	public void setOfferingEntryFinder(OfferingEntryFinder offeringEntryFinder) {
		this.offeringEntryFinder = offeringEntryFinder;
	}

	/**
	 * Returns the order entry local service.
	 *
	 * @return the order entry local service
	 */
	public OrderEntryLocalService getOrderEntryLocalService() {
		return orderEntryLocalService;
	}

	/**
	 * Sets the order entry local service.
	 *
	 * @param orderEntryLocalService the order entry local service
	 */
	public void setOrderEntryLocalService(
		OrderEntryLocalService orderEntryLocalService) {
		this.orderEntryLocalService = orderEntryLocalService;
	}

	/**
	 * Returns the order entry remote service.
	 *
	 * @return the order entry remote service
	 */
	public OrderEntryService getOrderEntryService() {
		return orderEntryService;
	}

	/**
	 * Sets the order entry remote service.
	 *
	 * @param orderEntryService the order entry remote service
	 */
	public void setOrderEntryService(OrderEntryService orderEntryService) {
		this.orderEntryService = orderEntryService;
	}

	/**
	 * Returns the order entry persistence.
	 *
	 * @return the order entry persistence
	 */
	public OrderEntryPersistence getOrderEntryPersistence() {
		return orderEntryPersistence;
	}

	/**
	 * Sets the order entry persistence.
	 *
	 * @param orderEntryPersistence the order entry persistence
	 */
	public void setOrderEntryPersistence(
		OrderEntryPersistence orderEntryPersistence) {
		this.orderEntryPersistence = orderEntryPersistence;
	}

	/**
	 * Returns the order entry finder.
	 *
	 * @return the order entry finder
	 */
	public OrderEntryFinder getOrderEntryFinder() {
		return orderEntryFinder;
	}

	/**
	 * Sets the order entry finder.
	 *
	 * @param orderEntryFinder the order entry finder
	 */
	public void setOrderEntryFinder(OrderEntryFinder orderEntryFinder) {
		this.orderEntryFinder = orderEntryFinder;
	}

	/**
	 * Returns the o s b country local service.
	 *
	 * @return the o s b country local service
	 */
	public OSBCountryLocalService getOSBCountryLocalService() {
		return osbCountryLocalService;
	}

	/**
	 * Sets the o s b country local service.
	 *
	 * @param osbCountryLocalService the o s b country local service
	 */
	public void setOSBCountryLocalService(
		OSBCountryLocalService osbCountryLocalService) {
		this.osbCountryLocalService = osbCountryLocalService;
	}

	/**
	 * Returns the o s b country remote service.
	 *
	 * @return the o s b country remote service
	 */
	public OSBCountryService getOSBCountryService() {
		return osbCountryService;
	}

	/**
	 * Sets the o s b country remote service.
	 *
	 * @param osbCountryService the o s b country remote service
	 */
	public void setOSBCountryService(OSBCountryService osbCountryService) {
		this.osbCountryService = osbCountryService;
	}

	/**
	 * Returns the o s b region local service.
	 *
	 * @return the o s b region local service
	 */
	public OSBRegionLocalService getOSBRegionLocalService() {
		return osbRegionLocalService;
	}

	/**
	 * Sets the o s b region local service.
	 *
	 * @param osbRegionLocalService the o s b region local service
	 */
	public void setOSBRegionLocalService(
		OSBRegionLocalService osbRegionLocalService) {
		this.osbRegionLocalService = osbRegionLocalService;
	}

	/**
	 * Returns the o s b region remote service.
	 *
	 * @return the o s b region remote service
	 */
	public OSBRegionService getOSBRegionService() {
		return osbRegionService;
	}

	/**
	 * Sets the o s b region remote service.
	 *
	 * @param osbRegionService the o s b region remote service
	 */
	public void setOSBRegionService(OSBRegionService osbRegionService) {
		this.osbRegionService = osbRegionService;
	}

	/**
	 * Returns the partner entry local service.
	 *
	 * @return the partner entry local service
	 */
	public PartnerEntryLocalService getPartnerEntryLocalService() {
		return partnerEntryLocalService;
	}

	/**
	 * Sets the partner entry local service.
	 *
	 * @param partnerEntryLocalService the partner entry local service
	 */
	public void setPartnerEntryLocalService(
		PartnerEntryLocalService partnerEntryLocalService) {
		this.partnerEntryLocalService = partnerEntryLocalService;
	}

	/**
	 * Returns the partner entry remote service.
	 *
	 * @return the partner entry remote service
	 */
	public PartnerEntryService getPartnerEntryService() {
		return partnerEntryService;
	}

	/**
	 * Sets the partner entry remote service.
	 *
	 * @param partnerEntryService the partner entry remote service
	 */
	public void setPartnerEntryService(PartnerEntryService partnerEntryService) {
		this.partnerEntryService = partnerEntryService;
	}

	/**
	 * Returns the partner entry persistence.
	 *
	 * @return the partner entry persistence
	 */
	public PartnerEntryPersistence getPartnerEntryPersistence() {
		return partnerEntryPersistence;
	}

	/**
	 * Sets the partner entry persistence.
	 *
	 * @param partnerEntryPersistence the partner entry persistence
	 */
	public void setPartnerEntryPersistence(
		PartnerEntryPersistence partnerEntryPersistence) {
		this.partnerEntryPersistence = partnerEntryPersistence;
	}

	/**
	 * Returns the partner entry finder.
	 *
	 * @return the partner entry finder
	 */
	public PartnerEntryFinder getPartnerEntryFinder() {
		return partnerEntryFinder;
	}

	/**
	 * Sets the partner entry finder.
	 *
	 * @param partnerEntryFinder the partner entry finder
	 */
	public void setPartnerEntryFinder(PartnerEntryFinder partnerEntryFinder) {
		this.partnerEntryFinder = partnerEntryFinder;
	}

	/**
	 * Returns the partner worker local service.
	 *
	 * @return the partner worker local service
	 */
	public PartnerWorkerLocalService getPartnerWorkerLocalService() {
		return partnerWorkerLocalService;
	}

	/**
	 * Sets the partner worker local service.
	 *
	 * @param partnerWorkerLocalService the partner worker local service
	 */
	public void setPartnerWorkerLocalService(
		PartnerWorkerLocalService partnerWorkerLocalService) {
		this.partnerWorkerLocalService = partnerWorkerLocalService;
	}

	/**
	 * Returns the partner worker remote service.
	 *
	 * @return the partner worker remote service
	 */
	public PartnerWorkerService getPartnerWorkerService() {
		return partnerWorkerService;
	}

	/**
	 * Sets the partner worker remote service.
	 *
	 * @param partnerWorkerService the partner worker remote service
	 */
	public void setPartnerWorkerService(
		PartnerWorkerService partnerWorkerService) {
		this.partnerWorkerService = partnerWorkerService;
	}

	/**
	 * Returns the partner worker persistence.
	 *
	 * @return the partner worker persistence
	 */
	public PartnerWorkerPersistence getPartnerWorkerPersistence() {
		return partnerWorkerPersistence;
	}

	/**
	 * Sets the partner worker persistence.
	 *
	 * @param partnerWorkerPersistence the partner worker persistence
	 */
	public void setPartnerWorkerPersistence(
		PartnerWorkerPersistence partnerWorkerPersistence) {
		this.partnerWorkerPersistence = partnerWorkerPersistence;
	}

	/**
	 * Returns the portal release local service.
	 *
	 * @return the portal release local service
	 */
	public PortalReleaseLocalService getPortalReleaseLocalService() {
		return portalReleaseLocalService;
	}

	/**
	 * Sets the portal release local service.
	 *
	 * @param portalReleaseLocalService the portal release local service
	 */
	public void setPortalReleaseLocalService(
		PortalReleaseLocalService portalReleaseLocalService) {
		this.portalReleaseLocalService = portalReleaseLocalService;
	}

	/**
	 * Returns the portal release persistence.
	 *
	 * @return the portal release persistence
	 */
	public PortalReleasePersistence getPortalReleasePersistence() {
		return portalReleasePersistence;
	}

	/**
	 * Sets the portal release persistence.
	 *
	 * @param portalReleasePersistence the portal release persistence
	 */
	public void setPortalReleasePersistence(
		PortalReleasePersistence portalReleasePersistence) {
		this.portalReleasePersistence = portalReleasePersistence;
	}

	/**
	 * Returns the product entry local service.
	 *
	 * @return the product entry local service
	 */
	public ProductEntryLocalService getProductEntryLocalService() {
		return productEntryLocalService;
	}

	/**
	 * Sets the product entry local service.
	 *
	 * @param productEntryLocalService the product entry local service
	 */
	public void setProductEntryLocalService(
		ProductEntryLocalService productEntryLocalService) {
		this.productEntryLocalService = productEntryLocalService;
	}

	/**
	 * Returns the product entry remote service.
	 *
	 * @return the product entry remote service
	 */
	public ProductEntryService getProductEntryService() {
		return productEntryService;
	}

	/**
	 * Sets the product entry remote service.
	 *
	 * @param productEntryService the product entry remote service
	 */
	public void setProductEntryService(ProductEntryService productEntryService) {
		this.productEntryService = productEntryService;
	}

	/**
	 * Returns the product entry persistence.
	 *
	 * @return the product entry persistence
	 */
	public ProductEntryPersistence getProductEntryPersistence() {
		return productEntryPersistence;
	}

	/**
	 * Sets the product entry persistence.
	 *
	 * @param productEntryPersistence the product entry persistence
	 */
	public void setProductEntryPersistence(
		ProductEntryPersistence productEntryPersistence) {
		this.productEntryPersistence = productEntryPersistence;
	}

	/**
	 * Returns the product entry finder.
	 *
	 * @return the product entry finder
	 */
	public ProductEntryFinder getProductEntryFinder() {
		return productEntryFinder;
	}

	/**
	 * Sets the product entry finder.
	 *
	 * @param productEntryFinder the product entry finder
	 */
	public void setProductEntryFinder(ProductEntryFinder productEntryFinder) {
		this.productEntryFinder = productEntryFinder;
	}

	/**
	 * Returns the search filter local service.
	 *
	 * @return the search filter local service
	 */
	public SearchFilterLocalService getSearchFilterLocalService() {
		return searchFilterLocalService;
	}

	/**
	 * Sets the search filter local service.
	 *
	 * @param searchFilterLocalService the search filter local service
	 */
	public void setSearchFilterLocalService(
		SearchFilterLocalService searchFilterLocalService) {
		this.searchFilterLocalService = searchFilterLocalService;
	}

	/**
	 * Returns the search filter remote service.
	 *
	 * @return the search filter remote service
	 */
	public SearchFilterService getSearchFilterService() {
		return searchFilterService;
	}

	/**
	 * Sets the search filter remote service.
	 *
	 * @param searchFilterService the search filter remote service
	 */
	public void setSearchFilterService(SearchFilterService searchFilterService) {
		this.searchFilterService = searchFilterService;
	}

	/**
	 * Returns the search filter persistence.
	 *
	 * @return the search filter persistence
	 */
	public SearchFilterPersistence getSearchFilterPersistence() {
		return searchFilterPersistence;
	}

	/**
	 * Sets the search filter persistence.
	 *
	 * @param searchFilterPersistence the search filter persistence
	 */
	public void setSearchFilterPersistence(
		SearchFilterPersistence searchFilterPersistence) {
		this.searchFilterPersistence = searchFilterPersistence;
	}

	/**
	 * Returns the security patch local service.
	 *
	 * @return the security patch local service
	 */
	public SecurityPatchLocalService getSecurityPatchLocalService() {
		return securityPatchLocalService;
	}

	/**
	 * Sets the security patch local service.
	 *
	 * @param securityPatchLocalService the security patch local service
	 */
	public void setSecurityPatchLocalService(
		SecurityPatchLocalService securityPatchLocalService) {
		this.securityPatchLocalService = securityPatchLocalService;
	}

	/**
	 * Returns the security patch remote service.
	 *
	 * @return the security patch remote service
	 */
	public SecurityPatchService getSecurityPatchService() {
		return securityPatchService;
	}

	/**
	 * Sets the security patch remote service.
	 *
	 * @param securityPatchService the security patch remote service
	 */
	public void setSecurityPatchService(
		SecurityPatchService securityPatchService) {
		this.securityPatchService = securityPatchService;
	}

	/**
	 * Returns the security patch persistence.
	 *
	 * @return the security patch persistence
	 */
	public SecurityPatchPersistence getSecurityPatchPersistence() {
		return securityPatchPersistence;
	}

	/**
	 * Sets the security patch persistence.
	 *
	 * @param securityPatchPersistence the security patch persistence
	 */
	public void setSecurityPatchPersistence(
		SecurityPatchPersistence securityPatchPersistence) {
		this.securityPatchPersistence = securityPatchPersistence;
	}

	/**
	 * Returns the support labor local service.
	 *
	 * @return the support labor local service
	 */
	public SupportLaborLocalService getSupportLaborLocalService() {
		return supportLaborLocalService;
	}

	/**
	 * Sets the support labor local service.
	 *
	 * @param supportLaborLocalService the support labor local service
	 */
	public void setSupportLaborLocalService(
		SupportLaborLocalService supportLaborLocalService) {
		this.supportLaborLocalService = supportLaborLocalService;
	}

	/**
	 * Returns the support labor remote service.
	 *
	 * @return the support labor remote service
	 */
	public SupportLaborService getSupportLaborService() {
		return supportLaborService;
	}

	/**
	 * Sets the support labor remote service.
	 *
	 * @param supportLaborService the support labor remote service
	 */
	public void setSupportLaborService(SupportLaborService supportLaborService) {
		this.supportLaborService = supportLaborService;
	}

	/**
	 * Returns the support labor persistence.
	 *
	 * @return the support labor persistence
	 */
	public SupportLaborPersistence getSupportLaborPersistence() {
		return supportLaborPersistence;
	}

	/**
	 * Sets the support labor persistence.
	 *
	 * @param supportLaborPersistence the support labor persistence
	 */
	public void setSupportLaborPersistence(
		SupportLaborPersistence supportLaborPersistence) {
		this.supportLaborPersistence = supportLaborPersistence;
	}

	/**
	 * Returns the support region local service.
	 *
	 * @return the support region local service
	 */
	public SupportRegionLocalService getSupportRegionLocalService() {
		return supportRegionLocalService;
	}

	/**
	 * Sets the support region local service.
	 *
	 * @param supportRegionLocalService the support region local service
	 */
	public void setSupportRegionLocalService(
		SupportRegionLocalService supportRegionLocalService) {
		this.supportRegionLocalService = supportRegionLocalService;
	}

	/**
	 * Returns the support region remote service.
	 *
	 * @return the support region remote service
	 */
	public SupportRegionService getSupportRegionService() {
		return supportRegionService;
	}

	/**
	 * Sets the support region remote service.
	 *
	 * @param supportRegionService the support region remote service
	 */
	public void setSupportRegionService(
		SupportRegionService supportRegionService) {
		this.supportRegionService = supportRegionService;
	}

	/**
	 * Returns the support region persistence.
	 *
	 * @return the support region persistence
	 */
	public SupportRegionPersistence getSupportRegionPersistence() {
		return supportRegionPersistence;
	}

	/**
	 * Sets the support region persistence.
	 *
	 * @param supportRegionPersistence the support region persistence
	 */
	public void setSupportRegionPersistence(
		SupportRegionPersistence supportRegionPersistence) {
		this.supportRegionPersistence = supportRegionPersistence;
	}

	/**
	 * Returns the support response local service.
	 *
	 * @return the support response local service
	 */
	public SupportResponseLocalService getSupportResponseLocalService() {
		return supportResponseLocalService;
	}

	/**
	 * Sets the support response local service.
	 *
	 * @param supportResponseLocalService the support response local service
	 */
	public void setSupportResponseLocalService(
		SupportResponseLocalService supportResponseLocalService) {
		this.supportResponseLocalService = supportResponseLocalService;
	}

	/**
	 * Returns the support response remote service.
	 *
	 * @return the support response remote service
	 */
	public SupportResponseService getSupportResponseService() {
		return supportResponseService;
	}

	/**
	 * Sets the support response remote service.
	 *
	 * @param supportResponseService the support response remote service
	 */
	public void setSupportResponseService(
		SupportResponseService supportResponseService) {
		this.supportResponseService = supportResponseService;
	}

	/**
	 * Returns the support response persistence.
	 *
	 * @return the support response persistence
	 */
	public SupportResponsePersistence getSupportResponsePersistence() {
		return supportResponsePersistence;
	}

	/**
	 * Sets the support response persistence.
	 *
	 * @param supportResponsePersistence the support response persistence
	 */
	public void setSupportResponsePersistence(
		SupportResponsePersistence supportResponsePersistence) {
		this.supportResponsePersistence = supportResponsePersistence;
	}

	/**
	 * Returns the support response finder.
	 *
	 * @return the support response finder
	 */
	public SupportResponseFinder getSupportResponseFinder() {
		return supportResponseFinder;
	}

	/**
	 * Sets the support response finder.
	 *
	 * @param supportResponseFinder the support response finder
	 */
	public void setSupportResponseFinder(
		SupportResponseFinder supportResponseFinder) {
		this.supportResponseFinder = supportResponseFinder;
	}

	/**
	 * Returns the support team local service.
	 *
	 * @return the support team local service
	 */
	public SupportTeamLocalService getSupportTeamLocalService() {
		return supportTeamLocalService;
	}

	/**
	 * Sets the support team local service.
	 *
	 * @param supportTeamLocalService the support team local service
	 */
	public void setSupportTeamLocalService(
		SupportTeamLocalService supportTeamLocalService) {
		this.supportTeamLocalService = supportTeamLocalService;
	}

	/**
	 * Returns the support team remote service.
	 *
	 * @return the support team remote service
	 */
	public SupportTeamService getSupportTeamService() {
		return supportTeamService;
	}

	/**
	 * Sets the support team remote service.
	 *
	 * @param supportTeamService the support team remote service
	 */
	public void setSupportTeamService(SupportTeamService supportTeamService) {
		this.supportTeamService = supportTeamService;
	}

	/**
	 * Returns the support team persistence.
	 *
	 * @return the support team persistence
	 */
	public SupportTeamPersistence getSupportTeamPersistence() {
		return supportTeamPersistence;
	}

	/**
	 * Sets the support team persistence.
	 *
	 * @param supportTeamPersistence the support team persistence
	 */
	public void setSupportTeamPersistence(
		SupportTeamPersistence supportTeamPersistence) {
		this.supportTeamPersistence = supportTeamPersistence;
	}

	/**
	 * Returns the support team finder.
	 *
	 * @return the support team finder
	 */
	public SupportTeamFinder getSupportTeamFinder() {
		return supportTeamFinder;
	}

	/**
	 * Sets the support team finder.
	 *
	 * @param supportTeamFinder the support team finder
	 */
	public void setSupportTeamFinder(SupportTeamFinder supportTeamFinder) {
		this.supportTeamFinder = supportTeamFinder;
	}

	/**
	 * Returns the support team language local service.
	 *
	 * @return the support team language local service
	 */
	public SupportTeamLanguageLocalService getSupportTeamLanguageLocalService() {
		return supportTeamLanguageLocalService;
	}

	/**
	 * Sets the support team language local service.
	 *
	 * @param supportTeamLanguageLocalService the support team language local service
	 */
	public void setSupportTeamLanguageLocalService(
		SupportTeamLanguageLocalService supportTeamLanguageLocalService) {
		this.supportTeamLanguageLocalService = supportTeamLanguageLocalService;
	}

	/**
	 * Returns the support team language remote service.
	 *
	 * @return the support team language remote service
	 */
	public SupportTeamLanguageService getSupportTeamLanguageService() {
		return supportTeamLanguageService;
	}

	/**
	 * Sets the support team language remote service.
	 *
	 * @param supportTeamLanguageService the support team language remote service
	 */
	public void setSupportTeamLanguageService(
		SupportTeamLanguageService supportTeamLanguageService) {
		this.supportTeamLanguageService = supportTeamLanguageService;
	}

	/**
	 * Returns the support team language persistence.
	 *
	 * @return the support team language persistence
	 */
	public SupportTeamLanguagePersistence getSupportTeamLanguagePersistence() {
		return supportTeamLanguagePersistence;
	}

	/**
	 * Sets the support team language persistence.
	 *
	 * @param supportTeamLanguagePersistence the support team language persistence
	 */
	public void setSupportTeamLanguagePersistence(
		SupportTeamLanguagePersistence supportTeamLanguagePersistence) {
		this.supportTeamLanguagePersistence = supportTeamLanguagePersistence;
	}

	/**
	 * Returns the support worker local service.
	 *
	 * @return the support worker local service
	 */
	public SupportWorkerLocalService getSupportWorkerLocalService() {
		return supportWorkerLocalService;
	}

	/**
	 * Sets the support worker local service.
	 *
	 * @param supportWorkerLocalService the support worker local service
	 */
	public void setSupportWorkerLocalService(
		SupportWorkerLocalService supportWorkerLocalService) {
		this.supportWorkerLocalService = supportWorkerLocalService;
	}

	/**
	 * Returns the support worker remote service.
	 *
	 * @return the support worker remote service
	 */
	public SupportWorkerService getSupportWorkerService() {
		return supportWorkerService;
	}

	/**
	 * Sets the support worker remote service.
	 *
	 * @param supportWorkerService the support worker remote service
	 */
	public void setSupportWorkerService(
		SupportWorkerService supportWorkerService) {
		this.supportWorkerService = supportWorkerService;
	}

	/**
	 * Returns the support worker persistence.
	 *
	 * @return the support worker persistence
	 */
	public SupportWorkerPersistence getSupportWorkerPersistence() {
		return supportWorkerPersistence;
	}

	/**
	 * Sets the support worker persistence.
	 *
	 * @param supportWorkerPersistence the support worker persistence
	 */
	public void setSupportWorkerPersistence(
		SupportWorkerPersistence supportWorkerPersistence) {
		this.supportWorkerPersistence = supportWorkerPersistence;
	}

	/**
	 * Returns the support worker finder.
	 *
	 * @return the support worker finder
	 */
	public SupportWorkerFinder getSupportWorkerFinder() {
		return supportWorkerFinder;
	}

	/**
	 * Sets the support worker finder.
	 *
	 * @param supportWorkerFinder the support worker finder
	 */
	public void setSupportWorkerFinder(SupportWorkerFinder supportWorkerFinder) {
		this.supportWorkerFinder = supportWorkerFinder;
	}

	/**
	 * Returns the support worker account tier local service.
	 *
	 * @return the support worker account tier local service
	 */
	public SupportWorkerAccountTierLocalService getSupportWorkerAccountTierLocalService() {
		return supportWorkerAccountTierLocalService;
	}

	/**
	 * Sets the support worker account tier local service.
	 *
	 * @param supportWorkerAccountTierLocalService the support worker account tier local service
	 */
	public void setSupportWorkerAccountTierLocalService(
		SupportWorkerAccountTierLocalService supportWorkerAccountTierLocalService) {
		this.supportWorkerAccountTierLocalService = supportWorkerAccountTierLocalService;
	}

	/**
	 * Returns the support worker account tier remote service.
	 *
	 * @return the support worker account tier remote service
	 */
	public SupportWorkerAccountTierService getSupportWorkerAccountTierService() {
		return supportWorkerAccountTierService;
	}

	/**
	 * Sets the support worker account tier remote service.
	 *
	 * @param supportWorkerAccountTierService the support worker account tier remote service
	 */
	public void setSupportWorkerAccountTierService(
		SupportWorkerAccountTierService supportWorkerAccountTierService) {
		this.supportWorkerAccountTierService = supportWorkerAccountTierService;
	}

	/**
	 * Returns the support worker account tier persistence.
	 *
	 * @return the support worker account tier persistence
	 */
	public SupportWorkerAccountTierPersistence getSupportWorkerAccountTierPersistence() {
		return supportWorkerAccountTierPersistence;
	}

	/**
	 * Sets the support worker account tier persistence.
	 *
	 * @param supportWorkerAccountTierPersistence the support worker account tier persistence
	 */
	public void setSupportWorkerAccountTierPersistence(
		SupportWorkerAccountTierPersistence supportWorkerAccountTierPersistence) {
		this.supportWorkerAccountTierPersistence = supportWorkerAccountTierPersistence;
	}

	/**
	 * Returns the support worker component local service.
	 *
	 * @return the support worker component local service
	 */
	public SupportWorkerComponentLocalService getSupportWorkerComponentLocalService() {
		return supportWorkerComponentLocalService;
	}

	/**
	 * Sets the support worker component local service.
	 *
	 * @param supportWorkerComponentLocalService the support worker component local service
	 */
	public void setSupportWorkerComponentLocalService(
		SupportWorkerComponentLocalService supportWorkerComponentLocalService) {
		this.supportWorkerComponentLocalService = supportWorkerComponentLocalService;
	}

	/**
	 * Returns the support worker component remote service.
	 *
	 * @return the support worker component remote service
	 */
	public SupportWorkerComponentService getSupportWorkerComponentService() {
		return supportWorkerComponentService;
	}

	/**
	 * Sets the support worker component remote service.
	 *
	 * @param supportWorkerComponentService the support worker component remote service
	 */
	public void setSupportWorkerComponentService(
		SupportWorkerComponentService supportWorkerComponentService) {
		this.supportWorkerComponentService = supportWorkerComponentService;
	}

	/**
	 * Returns the support worker component persistence.
	 *
	 * @return the support worker component persistence
	 */
	public SupportWorkerComponentPersistence getSupportWorkerComponentPersistence() {
		return supportWorkerComponentPersistence;
	}

	/**
	 * Sets the support worker component persistence.
	 *
	 * @param supportWorkerComponentPersistence the support worker component persistence
	 */
	public void setSupportWorkerComponentPersistence(
		SupportWorkerComponentPersistence supportWorkerComponentPersistence) {
		this.supportWorkerComponentPersistence = supportWorkerComponentPersistence;
	}

	/**
	 * Returns the support worker severity local service.
	 *
	 * @return the support worker severity local service
	 */
	public SupportWorkerSeverityLocalService getSupportWorkerSeverityLocalService() {
		return supportWorkerSeverityLocalService;
	}

	/**
	 * Sets the support worker severity local service.
	 *
	 * @param supportWorkerSeverityLocalService the support worker severity local service
	 */
	public void setSupportWorkerSeverityLocalService(
		SupportWorkerSeverityLocalService supportWorkerSeverityLocalService) {
		this.supportWorkerSeverityLocalService = supportWorkerSeverityLocalService;
	}

	/**
	 * Returns the support worker severity remote service.
	 *
	 * @return the support worker severity remote service
	 */
	public SupportWorkerSeverityService getSupportWorkerSeverityService() {
		return supportWorkerSeverityService;
	}

	/**
	 * Sets the support worker severity remote service.
	 *
	 * @param supportWorkerSeverityService the support worker severity remote service
	 */
	public void setSupportWorkerSeverityService(
		SupportWorkerSeverityService supportWorkerSeverityService) {
		this.supportWorkerSeverityService = supportWorkerSeverityService;
	}

	/**
	 * Returns the support worker severity persistence.
	 *
	 * @return the support worker severity persistence
	 */
	public SupportWorkerSeverityPersistence getSupportWorkerSeverityPersistence() {
		return supportWorkerSeverityPersistence;
	}

	/**
	 * Sets the support worker severity persistence.
	 *
	 * @param supportWorkerSeverityPersistence the support worker severity persistence
	 */
	public void setSupportWorkerSeverityPersistence(
		SupportWorkerSeverityPersistence supportWorkerSeverityPersistence) {
		this.supportWorkerSeverityPersistence = supportWorkerSeverityPersistence;
	}

	/**
	 * Returns the ticket attachment local service.
	 *
	 * @return the ticket attachment local service
	 */
	public TicketAttachmentLocalService getTicketAttachmentLocalService() {
		return ticketAttachmentLocalService;
	}

	/**
	 * Sets the ticket attachment local service.
	 *
	 * @param ticketAttachmentLocalService the ticket attachment local service
	 */
	public void setTicketAttachmentLocalService(
		TicketAttachmentLocalService ticketAttachmentLocalService) {
		this.ticketAttachmentLocalService = ticketAttachmentLocalService;
	}

	/**
	 * Returns the ticket attachment remote service.
	 *
	 * @return the ticket attachment remote service
	 */
	public TicketAttachmentService getTicketAttachmentService() {
		return ticketAttachmentService;
	}

	/**
	 * Sets the ticket attachment remote service.
	 *
	 * @param ticketAttachmentService the ticket attachment remote service
	 */
	public void setTicketAttachmentService(
		TicketAttachmentService ticketAttachmentService) {
		this.ticketAttachmentService = ticketAttachmentService;
	}

	/**
	 * Returns the ticket attachment persistence.
	 *
	 * @return the ticket attachment persistence
	 */
	public TicketAttachmentPersistence getTicketAttachmentPersistence() {
		return ticketAttachmentPersistence;
	}

	/**
	 * Sets the ticket attachment persistence.
	 *
	 * @param ticketAttachmentPersistence the ticket attachment persistence
	 */
	public void setTicketAttachmentPersistence(
		TicketAttachmentPersistence ticketAttachmentPersistence) {
		this.ticketAttachmentPersistence = ticketAttachmentPersistence;
	}

	/**
	 * Returns the ticket call local service.
	 *
	 * @return the ticket call local service
	 */
	public TicketCallLocalService getTicketCallLocalService() {
		return ticketCallLocalService;
	}

	/**
	 * Sets the ticket call local service.
	 *
	 * @param ticketCallLocalService the ticket call local service
	 */
	public void setTicketCallLocalService(
		TicketCallLocalService ticketCallLocalService) {
		this.ticketCallLocalService = ticketCallLocalService;
	}

	/**
	 * Returns the ticket call remote service.
	 *
	 * @return the ticket call remote service
	 */
	public TicketCallService getTicketCallService() {
		return ticketCallService;
	}

	/**
	 * Sets the ticket call remote service.
	 *
	 * @param ticketCallService the ticket call remote service
	 */
	public void setTicketCallService(TicketCallService ticketCallService) {
		this.ticketCallService = ticketCallService;
	}

	/**
	 * Returns the ticket call persistence.
	 *
	 * @return the ticket call persistence
	 */
	public TicketCallPersistence getTicketCallPersistence() {
		return ticketCallPersistence;
	}

	/**
	 * Sets the ticket call persistence.
	 *
	 * @param ticketCallPersistence the ticket call persistence
	 */
	public void setTicketCallPersistence(
		TicketCallPersistence ticketCallPersistence) {
		this.ticketCallPersistence = ticketCallPersistence;
	}

	/**
	 * Returns the ticket canned response local service.
	 *
	 * @return the ticket canned response local service
	 */
	public TicketCannedResponseLocalService getTicketCannedResponseLocalService() {
		return ticketCannedResponseLocalService;
	}

	/**
	 * Sets the ticket canned response local service.
	 *
	 * @param ticketCannedResponseLocalService the ticket canned response local service
	 */
	public void setTicketCannedResponseLocalService(
		TicketCannedResponseLocalService ticketCannedResponseLocalService) {
		this.ticketCannedResponseLocalService = ticketCannedResponseLocalService;
	}

	/**
	 * Returns the ticket canned response remote service.
	 *
	 * @return the ticket canned response remote service
	 */
	public TicketCannedResponseService getTicketCannedResponseService() {
		return ticketCannedResponseService;
	}

	/**
	 * Sets the ticket canned response remote service.
	 *
	 * @param ticketCannedResponseService the ticket canned response remote service
	 */
	public void setTicketCannedResponseService(
		TicketCannedResponseService ticketCannedResponseService) {
		this.ticketCannedResponseService = ticketCannedResponseService;
	}

	/**
	 * Returns the ticket canned response persistence.
	 *
	 * @return the ticket canned response persistence
	 */
	public TicketCannedResponsePersistence getTicketCannedResponsePersistence() {
		return ticketCannedResponsePersistence;
	}

	/**
	 * Sets the ticket canned response persistence.
	 *
	 * @param ticketCannedResponsePersistence the ticket canned response persistence
	 */
	public void setTicketCannedResponsePersistence(
		TicketCannedResponsePersistence ticketCannedResponsePersistence) {
		this.ticketCannedResponsePersistence = ticketCannedResponsePersistence;
	}

	/**
	 * Returns the ticket canned response finder.
	 *
	 * @return the ticket canned response finder
	 */
	public TicketCannedResponseFinder getTicketCannedResponseFinder() {
		return ticketCannedResponseFinder;
	}

	/**
	 * Sets the ticket canned response finder.
	 *
	 * @param ticketCannedResponseFinder the ticket canned response finder
	 */
	public void setTicketCannedResponseFinder(
		TicketCannedResponseFinder ticketCannedResponseFinder) {
		this.ticketCannedResponseFinder = ticketCannedResponseFinder;
	}

	/**
	 * Returns the ticket comment local service.
	 *
	 * @return the ticket comment local service
	 */
	public TicketCommentLocalService getTicketCommentLocalService() {
		return ticketCommentLocalService;
	}

	/**
	 * Sets the ticket comment local service.
	 *
	 * @param ticketCommentLocalService the ticket comment local service
	 */
	public void setTicketCommentLocalService(
		TicketCommentLocalService ticketCommentLocalService) {
		this.ticketCommentLocalService = ticketCommentLocalService;
	}

	/**
	 * Returns the ticket comment remote service.
	 *
	 * @return the ticket comment remote service
	 */
	public TicketCommentService getTicketCommentService() {
		return ticketCommentService;
	}

	/**
	 * Sets the ticket comment remote service.
	 *
	 * @param ticketCommentService the ticket comment remote service
	 */
	public void setTicketCommentService(
		TicketCommentService ticketCommentService) {
		this.ticketCommentService = ticketCommentService;
	}

	/**
	 * Returns the ticket comment persistence.
	 *
	 * @return the ticket comment persistence
	 */
	public TicketCommentPersistence getTicketCommentPersistence() {
		return ticketCommentPersistence;
	}

	/**
	 * Sets the ticket comment persistence.
	 *
	 * @param ticketCommentPersistence the ticket comment persistence
	 */
	public void setTicketCommentPersistence(
		TicketCommentPersistence ticketCommentPersistence) {
		this.ticketCommentPersistence = ticketCommentPersistence;
	}

	/**
	 * Returns the ticket comment finder.
	 *
	 * @return the ticket comment finder
	 */
	public TicketCommentFinder getTicketCommentFinder() {
		return ticketCommentFinder;
	}

	/**
	 * Sets the ticket comment finder.
	 *
	 * @param ticketCommentFinder the ticket comment finder
	 */
	public void setTicketCommentFinder(TicketCommentFinder ticketCommentFinder) {
		this.ticketCommentFinder = ticketCommentFinder;
	}

	/**
	 * Returns the ticket entry local service.
	 *
	 * @return the ticket entry local service
	 */
	public TicketEntryLocalService getTicketEntryLocalService() {
		return ticketEntryLocalService;
	}

	/**
	 * Sets the ticket entry local service.
	 *
	 * @param ticketEntryLocalService the ticket entry local service
	 */
	public void setTicketEntryLocalService(
		TicketEntryLocalService ticketEntryLocalService) {
		this.ticketEntryLocalService = ticketEntryLocalService;
	}

	/**
	 * Returns the ticket entry remote service.
	 *
	 * @return the ticket entry remote service
	 */
	public TicketEntryService getTicketEntryService() {
		return ticketEntryService;
	}

	/**
	 * Sets the ticket entry remote service.
	 *
	 * @param ticketEntryService the ticket entry remote service
	 */
	public void setTicketEntryService(TicketEntryService ticketEntryService) {
		this.ticketEntryService = ticketEntryService;
	}

	/**
	 * Returns the ticket entry persistence.
	 *
	 * @return the ticket entry persistence
	 */
	public TicketEntryPersistence getTicketEntryPersistence() {
		return ticketEntryPersistence;
	}

	/**
	 * Sets the ticket entry persistence.
	 *
	 * @param ticketEntryPersistence the ticket entry persistence
	 */
	public void setTicketEntryPersistence(
		TicketEntryPersistence ticketEntryPersistence) {
		this.ticketEntryPersistence = ticketEntryPersistence;
	}

	/**
	 * Returns the ticket entry finder.
	 *
	 * @return the ticket entry finder
	 */
	public TicketEntryFinder getTicketEntryFinder() {
		return ticketEntryFinder;
	}

	/**
	 * Sets the ticket entry finder.
	 *
	 * @param ticketEntryFinder the ticket entry finder
	 */
	public void setTicketEntryFinder(TicketEntryFinder ticketEntryFinder) {
		this.ticketEntryFinder = ticketEntryFinder;
	}

	/**
	 * Returns the ticket feedback local service.
	 *
	 * @return the ticket feedback local service
	 */
	public TicketFeedbackLocalService getTicketFeedbackLocalService() {
		return ticketFeedbackLocalService;
	}

	/**
	 * Sets the ticket feedback local service.
	 *
	 * @param ticketFeedbackLocalService the ticket feedback local service
	 */
	public void setTicketFeedbackLocalService(
		TicketFeedbackLocalService ticketFeedbackLocalService) {
		this.ticketFeedbackLocalService = ticketFeedbackLocalService;
	}

	/**
	 * Returns the ticket feedback remote service.
	 *
	 * @return the ticket feedback remote service
	 */
	public TicketFeedbackService getTicketFeedbackService() {
		return ticketFeedbackService;
	}

	/**
	 * Sets the ticket feedback remote service.
	 *
	 * @param ticketFeedbackService the ticket feedback remote service
	 */
	public void setTicketFeedbackService(
		TicketFeedbackService ticketFeedbackService) {
		this.ticketFeedbackService = ticketFeedbackService;
	}

	/**
	 * Returns the ticket feedback persistence.
	 *
	 * @return the ticket feedback persistence
	 */
	public TicketFeedbackPersistence getTicketFeedbackPersistence() {
		return ticketFeedbackPersistence;
	}

	/**
	 * Sets the ticket feedback persistence.
	 *
	 * @param ticketFeedbackPersistence the ticket feedback persistence
	 */
	public void setTicketFeedbackPersistence(
		TicketFeedbackPersistence ticketFeedbackPersistence) {
		this.ticketFeedbackPersistence = ticketFeedbackPersistence;
	}

	/**
	 * Returns the ticket feedback finder.
	 *
	 * @return the ticket feedback finder
	 */
	public TicketFeedbackFinder getTicketFeedbackFinder() {
		return ticketFeedbackFinder;
	}

	/**
	 * Sets the ticket feedback finder.
	 *
	 * @param ticketFeedbackFinder the ticket feedback finder
	 */
	public void setTicketFeedbackFinder(
		TicketFeedbackFinder ticketFeedbackFinder) {
		this.ticketFeedbackFinder = ticketFeedbackFinder;
	}

	/**
	 * Returns the ticket flag local service.
	 *
	 * @return the ticket flag local service
	 */
	public TicketFlagLocalService getTicketFlagLocalService() {
		return ticketFlagLocalService;
	}

	/**
	 * Sets the ticket flag local service.
	 *
	 * @param ticketFlagLocalService the ticket flag local service
	 */
	public void setTicketFlagLocalService(
		TicketFlagLocalService ticketFlagLocalService) {
		this.ticketFlagLocalService = ticketFlagLocalService;
	}

	/**
	 * Returns the ticket flag remote service.
	 *
	 * @return the ticket flag remote service
	 */
	public TicketFlagService getTicketFlagService() {
		return ticketFlagService;
	}

	/**
	 * Sets the ticket flag remote service.
	 *
	 * @param ticketFlagService the ticket flag remote service
	 */
	public void setTicketFlagService(TicketFlagService ticketFlagService) {
		this.ticketFlagService = ticketFlagService;
	}

	/**
	 * Returns the ticket flag persistence.
	 *
	 * @return the ticket flag persistence
	 */
	public TicketFlagPersistence getTicketFlagPersistence() {
		return ticketFlagPersistence;
	}

	/**
	 * Sets the ticket flag persistence.
	 *
	 * @param ticketFlagPersistence the ticket flag persistence
	 */
	public void setTicketFlagPersistence(
		TicketFlagPersistence ticketFlagPersistence) {
		this.ticketFlagPersistence = ticketFlagPersistence;
	}

	/**
	 * Returns the ticket information local service.
	 *
	 * @return the ticket information local service
	 */
	public TicketInformationLocalService getTicketInformationLocalService() {
		return ticketInformationLocalService;
	}

	/**
	 * Sets the ticket information local service.
	 *
	 * @param ticketInformationLocalService the ticket information local service
	 */
	public void setTicketInformationLocalService(
		TicketInformationLocalService ticketInformationLocalService) {
		this.ticketInformationLocalService = ticketInformationLocalService;
	}

	/**
	 * Returns the ticket information remote service.
	 *
	 * @return the ticket information remote service
	 */
	public TicketInformationService getTicketInformationService() {
		return ticketInformationService;
	}

	/**
	 * Sets the ticket information remote service.
	 *
	 * @param ticketInformationService the ticket information remote service
	 */
	public void setTicketInformationService(
		TicketInformationService ticketInformationService) {
		this.ticketInformationService = ticketInformationService;
	}

	/**
	 * Returns the ticket information persistence.
	 *
	 * @return the ticket information persistence
	 */
	public TicketInformationPersistence getTicketInformationPersistence() {
		return ticketInformationPersistence;
	}

	/**
	 * Sets the ticket information persistence.
	 *
	 * @param ticketInformationPersistence the ticket information persistence
	 */
	public void setTicketInformationPersistence(
		TicketInformationPersistence ticketInformationPersistence) {
		this.ticketInformationPersistence = ticketInformationPersistence;
	}

	/**
	 * Returns the ticket link local service.
	 *
	 * @return the ticket link local service
	 */
	public TicketLinkLocalService getTicketLinkLocalService() {
		return ticketLinkLocalService;
	}

	/**
	 * Sets the ticket link local service.
	 *
	 * @param ticketLinkLocalService the ticket link local service
	 */
	public void setTicketLinkLocalService(
		TicketLinkLocalService ticketLinkLocalService) {
		this.ticketLinkLocalService = ticketLinkLocalService;
	}

	/**
	 * Returns the ticket link remote service.
	 *
	 * @return the ticket link remote service
	 */
	public TicketLinkService getTicketLinkService() {
		return ticketLinkService;
	}

	/**
	 * Sets the ticket link remote service.
	 *
	 * @param ticketLinkService the ticket link remote service
	 */
	public void setTicketLinkService(TicketLinkService ticketLinkService) {
		this.ticketLinkService = ticketLinkService;
	}

	/**
	 * Returns the ticket link persistence.
	 *
	 * @return the ticket link persistence
	 */
	public TicketLinkPersistence getTicketLinkPersistence() {
		return ticketLinkPersistence;
	}

	/**
	 * Sets the ticket link persistence.
	 *
	 * @param ticketLinkPersistence the ticket link persistence
	 */
	public void setTicketLinkPersistence(
		TicketLinkPersistence ticketLinkPersistence) {
		this.ticketLinkPersistence = ticketLinkPersistence;
	}

	/**
	 * Returns the ticket solution local service.
	 *
	 * @return the ticket solution local service
	 */
	public TicketSolutionLocalService getTicketSolutionLocalService() {
		return ticketSolutionLocalService;
	}

	/**
	 * Sets the ticket solution local service.
	 *
	 * @param ticketSolutionLocalService the ticket solution local service
	 */
	public void setTicketSolutionLocalService(
		TicketSolutionLocalService ticketSolutionLocalService) {
		this.ticketSolutionLocalService = ticketSolutionLocalService;
	}

	/**
	 * Returns the ticket solution remote service.
	 *
	 * @return the ticket solution remote service
	 */
	public TicketSolutionService getTicketSolutionService() {
		return ticketSolutionService;
	}

	/**
	 * Sets the ticket solution remote service.
	 *
	 * @param ticketSolutionService the ticket solution remote service
	 */
	public void setTicketSolutionService(
		TicketSolutionService ticketSolutionService) {
		this.ticketSolutionService = ticketSolutionService;
	}

	/**
	 * Returns the ticket solution persistence.
	 *
	 * @return the ticket solution persistence
	 */
	public TicketSolutionPersistence getTicketSolutionPersistence() {
		return ticketSolutionPersistence;
	}

	/**
	 * Sets the ticket solution persistence.
	 *
	 * @param ticketSolutionPersistence the ticket solution persistence
	 */
	public void setTicketSolutionPersistence(
		TicketSolutionPersistence ticketSolutionPersistence) {
		this.ticketSolutionPersistence = ticketSolutionPersistence;
	}

	/**
	 * Returns the ticket worker local service.
	 *
	 * @return the ticket worker local service
	 */
	public TicketWorkerLocalService getTicketWorkerLocalService() {
		return ticketWorkerLocalService;
	}

	/**
	 * Sets the ticket worker local service.
	 *
	 * @param ticketWorkerLocalService the ticket worker local service
	 */
	public void setTicketWorkerLocalService(
		TicketWorkerLocalService ticketWorkerLocalService) {
		this.ticketWorkerLocalService = ticketWorkerLocalService;
	}

	/**
	 * Returns the ticket worker remote service.
	 *
	 * @return the ticket worker remote service
	 */
	public TicketWorkerService getTicketWorkerService() {
		return ticketWorkerService;
	}

	/**
	 * Sets the ticket worker remote service.
	 *
	 * @param ticketWorkerService the ticket worker remote service
	 */
	public void setTicketWorkerService(TicketWorkerService ticketWorkerService) {
		this.ticketWorkerService = ticketWorkerService;
	}

	/**
	 * Returns the ticket worker persistence.
	 *
	 * @return the ticket worker persistence
	 */
	public TicketWorkerPersistence getTicketWorkerPersistence() {
		return ticketWorkerPersistence;
	}

	/**
	 * Sets the ticket worker persistence.
	 *
	 * @param ticketWorkerPersistence the ticket worker persistence
	 */
	public void setTicketWorkerPersistence(
		TicketWorkerPersistence ticketWorkerPersistence) {
		this.ticketWorkerPersistence = ticketWorkerPersistence;
	}

	/**
	 * Returns the training certificate local service.
	 *
	 * @return the training certificate local service
	 */
	public TrainingCertificateLocalService getTrainingCertificateLocalService() {
		return trainingCertificateLocalService;
	}

	/**
	 * Sets the training certificate local service.
	 *
	 * @param trainingCertificateLocalService the training certificate local service
	 */
	public void setTrainingCertificateLocalService(
		TrainingCertificateLocalService trainingCertificateLocalService) {
		this.trainingCertificateLocalService = trainingCertificateLocalService;
	}

	/**
	 * Returns the training certificate remote service.
	 *
	 * @return the training certificate remote service
	 */
	public TrainingCertificateService getTrainingCertificateService() {
		return trainingCertificateService;
	}

	/**
	 * Sets the training certificate remote service.
	 *
	 * @param trainingCertificateService the training certificate remote service
	 */
	public void setTrainingCertificateService(
		TrainingCertificateService trainingCertificateService) {
		this.trainingCertificateService = trainingCertificateService;
	}

	/**
	 * Returns the training certificate persistence.
	 *
	 * @return the training certificate persistence
	 */
	public TrainingCertificatePersistence getTrainingCertificatePersistence() {
		return trainingCertificatePersistence;
	}

	/**
	 * Sets the training certificate persistence.
	 *
	 * @param trainingCertificatePersistence the training certificate persistence
	 */
	public void setTrainingCertificatePersistence(
		TrainingCertificatePersistence trainingCertificatePersistence) {
		this.trainingCertificatePersistence = trainingCertificatePersistence;
	}

	/**
	 * Returns the training certificate template local service.
	 *
	 * @return the training certificate template local service
	 */
	public TrainingCertificateTemplateLocalService getTrainingCertificateTemplateLocalService() {
		return trainingCertificateTemplateLocalService;
	}

	/**
	 * Sets the training certificate template local service.
	 *
	 * @param trainingCertificateTemplateLocalService the training certificate template local service
	 */
	public void setTrainingCertificateTemplateLocalService(
		TrainingCertificateTemplateLocalService trainingCertificateTemplateLocalService) {
		this.trainingCertificateTemplateLocalService = trainingCertificateTemplateLocalService;
	}

	/**
	 * Returns the training certificate template remote service.
	 *
	 * @return the training certificate template remote service
	 */
	public TrainingCertificateTemplateService getTrainingCertificateTemplateService() {
		return trainingCertificateTemplateService;
	}

	/**
	 * Sets the training certificate template remote service.
	 *
	 * @param trainingCertificateTemplateService the training certificate template remote service
	 */
	public void setTrainingCertificateTemplateService(
		TrainingCertificateTemplateService trainingCertificateTemplateService) {
		this.trainingCertificateTemplateService = trainingCertificateTemplateService;
	}

	/**
	 * Returns the training certificate template persistence.
	 *
	 * @return the training certificate template persistence
	 */
	public TrainingCertificateTemplatePersistence getTrainingCertificateTemplatePersistence() {
		return trainingCertificateTemplatePersistence;
	}

	/**
	 * Sets the training certificate template persistence.
	 *
	 * @param trainingCertificateTemplatePersistence the training certificate template persistence
	 */
	public void setTrainingCertificateTemplatePersistence(
		TrainingCertificateTemplatePersistence trainingCertificateTemplatePersistence) {
		this.trainingCertificateTemplatePersistence = trainingCertificateTemplatePersistence;
	}

	/**
	 * Returns the training course local service.
	 *
	 * @return the training course local service
	 */
	public TrainingCourseLocalService getTrainingCourseLocalService() {
		return trainingCourseLocalService;
	}

	/**
	 * Sets the training course local service.
	 *
	 * @param trainingCourseLocalService the training course local service
	 */
	public void setTrainingCourseLocalService(
		TrainingCourseLocalService trainingCourseLocalService) {
		this.trainingCourseLocalService = trainingCourseLocalService;
	}

	/**
	 * Returns the training course remote service.
	 *
	 * @return the training course remote service
	 */
	public TrainingCourseService getTrainingCourseService() {
		return trainingCourseService;
	}

	/**
	 * Sets the training course remote service.
	 *
	 * @param trainingCourseService the training course remote service
	 */
	public void setTrainingCourseService(
		TrainingCourseService trainingCourseService) {
		this.trainingCourseService = trainingCourseService;
	}

	/**
	 * Returns the training course persistence.
	 *
	 * @return the training course persistence
	 */
	public TrainingCoursePersistence getTrainingCoursePersistence() {
		return trainingCoursePersistence;
	}

	/**
	 * Sets the training course persistence.
	 *
	 * @param trainingCoursePersistence the training course persistence
	 */
	public void setTrainingCoursePersistence(
		TrainingCoursePersistence trainingCoursePersistence) {
		this.trainingCoursePersistence = trainingCoursePersistence;
	}

	/**
	 * Returns the training customer local service.
	 *
	 * @return the training customer local service
	 */
	public TrainingCustomerLocalService getTrainingCustomerLocalService() {
		return trainingCustomerLocalService;
	}

	/**
	 * Sets the training customer local service.
	 *
	 * @param trainingCustomerLocalService the training customer local service
	 */
	public void setTrainingCustomerLocalService(
		TrainingCustomerLocalService trainingCustomerLocalService) {
		this.trainingCustomerLocalService = trainingCustomerLocalService;
	}

	/**
	 * Returns the training customer remote service.
	 *
	 * @return the training customer remote service
	 */
	public TrainingCustomerService getTrainingCustomerService() {
		return trainingCustomerService;
	}

	/**
	 * Sets the training customer remote service.
	 *
	 * @param trainingCustomerService the training customer remote service
	 */
	public void setTrainingCustomerService(
		TrainingCustomerService trainingCustomerService) {
		this.trainingCustomerService = trainingCustomerService;
	}

	/**
	 * Returns the training customer persistence.
	 *
	 * @return the training customer persistence
	 */
	public TrainingCustomerPersistence getTrainingCustomerPersistence() {
		return trainingCustomerPersistence;
	}

	/**
	 * Sets the training customer persistence.
	 *
	 * @param trainingCustomerPersistence the training customer persistence
	 */
	public void setTrainingCustomerPersistence(
		TrainingCustomerPersistence trainingCustomerPersistence) {
		this.trainingCustomerPersistence = trainingCustomerPersistence;
	}

	/**
	 * Returns the training customer finder.
	 *
	 * @return the training customer finder
	 */
	public TrainingCustomerFinder getTrainingCustomerFinder() {
		return trainingCustomerFinder;
	}

	/**
	 * Sets the training customer finder.
	 *
	 * @param trainingCustomerFinder the training customer finder
	 */
	public void setTrainingCustomerFinder(
		TrainingCustomerFinder trainingCustomerFinder) {
		this.trainingCustomerFinder = trainingCustomerFinder;
	}

	/**
	 * Returns the training event local service.
	 *
	 * @return the training event local service
	 */
	public TrainingEventLocalService getTrainingEventLocalService() {
		return trainingEventLocalService;
	}

	/**
	 * Sets the training event local service.
	 *
	 * @param trainingEventLocalService the training event local service
	 */
	public void setTrainingEventLocalService(
		TrainingEventLocalService trainingEventLocalService) {
		this.trainingEventLocalService = trainingEventLocalService;
	}

	/**
	 * Returns the training event remote service.
	 *
	 * @return the training event remote service
	 */
	public TrainingEventService getTrainingEventService() {
		return trainingEventService;
	}

	/**
	 * Sets the training event remote service.
	 *
	 * @param trainingEventService the training event remote service
	 */
	public void setTrainingEventService(
		TrainingEventService trainingEventService) {
		this.trainingEventService = trainingEventService;
	}

	/**
	 * Returns the training event persistence.
	 *
	 * @return the training event persistence
	 */
	public TrainingEventPersistence getTrainingEventPersistence() {
		return trainingEventPersistence;
	}

	/**
	 * Sets the training event persistence.
	 *
	 * @param trainingEventPersistence the training event persistence
	 */
	public void setTrainingEventPersistence(
		TrainingEventPersistence trainingEventPersistence) {
		this.trainingEventPersistence = trainingEventPersistence;
	}

	/**
	 * Returns the training event finder.
	 *
	 * @return the training event finder
	 */
	public TrainingEventFinder getTrainingEventFinder() {
		return trainingEventFinder;
	}

	/**
	 * Sets the training event finder.
	 *
	 * @param trainingEventFinder the training event finder
	 */
	public void setTrainingEventFinder(TrainingEventFinder trainingEventFinder) {
		this.trainingEventFinder = trainingEventFinder;
	}

	/**
	 * Returns the training exam local service.
	 *
	 * @return the training exam local service
	 */
	public TrainingExamLocalService getTrainingExamLocalService() {
		return trainingExamLocalService;
	}

	/**
	 * Sets the training exam local service.
	 *
	 * @param trainingExamLocalService the training exam local service
	 */
	public void setTrainingExamLocalService(
		TrainingExamLocalService trainingExamLocalService) {
		this.trainingExamLocalService = trainingExamLocalService;
	}

	/**
	 * Returns the training exam remote service.
	 *
	 * @return the training exam remote service
	 */
	public TrainingExamService getTrainingExamService() {
		return trainingExamService;
	}

	/**
	 * Sets the training exam remote service.
	 *
	 * @param trainingExamService the training exam remote service
	 */
	public void setTrainingExamService(TrainingExamService trainingExamService) {
		this.trainingExamService = trainingExamService;
	}

	/**
	 * Returns the training exam persistence.
	 *
	 * @return the training exam persistence
	 */
	public TrainingExamPersistence getTrainingExamPersistence() {
		return trainingExamPersistence;
	}

	/**
	 * Sets the training exam persistence.
	 *
	 * @param trainingExamPersistence the training exam persistence
	 */
	public void setTrainingExamPersistence(
		TrainingExamPersistence trainingExamPersistence) {
		this.trainingExamPersistence = trainingExamPersistence;
	}

	/**
	 * Returns the training exam result local service.
	 *
	 * @return the training exam result local service
	 */
	public TrainingExamResultLocalService getTrainingExamResultLocalService() {
		return trainingExamResultLocalService;
	}

	/**
	 * Sets the training exam result local service.
	 *
	 * @param trainingExamResultLocalService the training exam result local service
	 */
	public void setTrainingExamResultLocalService(
		TrainingExamResultLocalService trainingExamResultLocalService) {
		this.trainingExamResultLocalService = trainingExamResultLocalService;
	}

	/**
	 * Returns the training exam result remote service.
	 *
	 * @return the training exam result remote service
	 */
	public TrainingExamResultService getTrainingExamResultService() {
		return trainingExamResultService;
	}

	/**
	 * Sets the training exam result remote service.
	 *
	 * @param trainingExamResultService the training exam result remote service
	 */
	public void setTrainingExamResultService(
		TrainingExamResultService trainingExamResultService) {
		this.trainingExamResultService = trainingExamResultService;
	}

	/**
	 * Returns the training exam result persistence.
	 *
	 * @return the training exam result persistence
	 */
	public TrainingExamResultPersistence getTrainingExamResultPersistence() {
		return trainingExamResultPersistence;
	}

	/**
	 * Sets the training exam result persistence.
	 *
	 * @param trainingExamResultPersistence the training exam result persistence
	 */
	public void setTrainingExamResultPersistence(
		TrainingExamResultPersistence trainingExamResultPersistence) {
		this.trainingExamResultPersistence = trainingExamResultPersistence;
	}

	/**
	 * Returns the training exam result finder.
	 *
	 * @return the training exam result finder
	 */
	public TrainingExamResultFinder getTrainingExamResultFinder() {
		return trainingExamResultFinder;
	}

	/**
	 * Sets the training exam result finder.
	 *
	 * @param trainingExamResultFinder the training exam result finder
	 */
	public void setTrainingExamResultFinder(
		TrainingExamResultFinder trainingExamResultFinder) {
		this.trainingExamResultFinder = trainingExamResultFinder;
	}

	/**
	 * Returns the training exam result item local service.
	 *
	 * @return the training exam result item local service
	 */
	public TrainingExamResultItemLocalService getTrainingExamResultItemLocalService() {
		return trainingExamResultItemLocalService;
	}

	/**
	 * Sets the training exam result item local service.
	 *
	 * @param trainingExamResultItemLocalService the training exam result item local service
	 */
	public void setTrainingExamResultItemLocalService(
		TrainingExamResultItemLocalService trainingExamResultItemLocalService) {
		this.trainingExamResultItemLocalService = trainingExamResultItemLocalService;
	}

	/**
	 * Returns the training exam result item remote service.
	 *
	 * @return the training exam result item remote service
	 */
	public TrainingExamResultItemService getTrainingExamResultItemService() {
		return trainingExamResultItemService;
	}

	/**
	 * Sets the training exam result item remote service.
	 *
	 * @param trainingExamResultItemService the training exam result item remote service
	 */
	public void setTrainingExamResultItemService(
		TrainingExamResultItemService trainingExamResultItemService) {
		this.trainingExamResultItemService = trainingExamResultItemService;
	}

	/**
	 * Returns the training exam result item persistence.
	 *
	 * @return the training exam result item persistence
	 */
	public TrainingExamResultItemPersistence getTrainingExamResultItemPersistence() {
		return trainingExamResultItemPersistence;
	}

	/**
	 * Sets the training exam result item persistence.
	 *
	 * @param trainingExamResultItemPersistence the training exam result item persistence
	 */
	public void setTrainingExamResultItemPersistence(
		TrainingExamResultItemPersistence trainingExamResultItemPersistence) {
		this.trainingExamResultItemPersistence = trainingExamResultItemPersistence;
	}

	/**
	 * Returns the training exam result item finder.
	 *
	 * @return the training exam result item finder
	 */
	public TrainingExamResultItemFinder getTrainingExamResultItemFinder() {
		return trainingExamResultItemFinder;
	}

	/**
	 * Sets the training exam result item finder.
	 *
	 * @param trainingExamResultItemFinder the training exam result item finder
	 */
	public void setTrainingExamResultItemFinder(
		TrainingExamResultItemFinder trainingExamResultItemFinder) {
		this.trainingExamResultItemFinder = trainingExamResultItemFinder;
	}

	/**
	 * Returns the training exam result section local service.
	 *
	 * @return the training exam result section local service
	 */
	public TrainingExamResultSectionLocalService getTrainingExamResultSectionLocalService() {
		return trainingExamResultSectionLocalService;
	}

	/**
	 * Sets the training exam result section local service.
	 *
	 * @param trainingExamResultSectionLocalService the training exam result section local service
	 */
	public void setTrainingExamResultSectionLocalService(
		TrainingExamResultSectionLocalService trainingExamResultSectionLocalService) {
		this.trainingExamResultSectionLocalService = trainingExamResultSectionLocalService;
	}

	/**
	 * Returns the training exam result section remote service.
	 *
	 * @return the training exam result section remote service
	 */
	public TrainingExamResultSectionService getTrainingExamResultSectionService() {
		return trainingExamResultSectionService;
	}

	/**
	 * Sets the training exam result section remote service.
	 *
	 * @param trainingExamResultSectionService the training exam result section remote service
	 */
	public void setTrainingExamResultSectionService(
		TrainingExamResultSectionService trainingExamResultSectionService) {
		this.trainingExamResultSectionService = trainingExamResultSectionService;
	}

	/**
	 * Returns the training exam result section persistence.
	 *
	 * @return the training exam result section persistence
	 */
	public TrainingExamResultSectionPersistence getTrainingExamResultSectionPersistence() {
		return trainingExamResultSectionPersistence;
	}

	/**
	 * Sets the training exam result section persistence.
	 *
	 * @param trainingExamResultSectionPersistence the training exam result section persistence
	 */
	public void setTrainingExamResultSectionPersistence(
		TrainingExamResultSectionPersistence trainingExamResultSectionPersistence) {
		this.trainingExamResultSectionPersistence = trainingExamResultSectionPersistence;
	}

	/**
	 * Returns the training exam result section finder.
	 *
	 * @return the training exam result section finder
	 */
	public TrainingExamResultSectionFinder getTrainingExamResultSectionFinder() {
		return trainingExamResultSectionFinder;
	}

	/**
	 * Sets the training exam result section finder.
	 *
	 * @param trainingExamResultSectionFinder the training exam result section finder
	 */
	public void setTrainingExamResultSectionFinder(
		TrainingExamResultSectionFinder trainingExamResultSectionFinder) {
		this.trainingExamResultSectionFinder = trainingExamResultSectionFinder;
	}

	/**
	 * Returns the training import log local service.
	 *
	 * @return the training import log local service
	 */
	public TrainingImportLogLocalService getTrainingImportLogLocalService() {
		return trainingImportLogLocalService;
	}

	/**
	 * Sets the training import log local service.
	 *
	 * @param trainingImportLogLocalService the training import log local service
	 */
	public void setTrainingImportLogLocalService(
		TrainingImportLogLocalService trainingImportLogLocalService) {
		this.trainingImportLogLocalService = trainingImportLogLocalService;
	}

	/**
	 * Returns the training import log persistence.
	 *
	 * @return the training import log persistence
	 */
	public TrainingImportLogPersistence getTrainingImportLogPersistence() {
		return trainingImportLogPersistence;
	}

	/**
	 * Sets the training import log persistence.
	 *
	 * @param trainingImportLogPersistence the training import log persistence
	 */
	public void setTrainingImportLogPersistence(
		TrainingImportLogPersistence trainingImportLogPersistence) {
		this.trainingImportLogPersistence = trainingImportLogPersistence;
	}

	/**
	 * Returns the training linked user local service.
	 *
	 * @return the training linked user local service
	 */
	public TrainingLinkedUserLocalService getTrainingLinkedUserLocalService() {
		return trainingLinkedUserLocalService;
	}

	/**
	 * Sets the training linked user local service.
	 *
	 * @param trainingLinkedUserLocalService the training linked user local service
	 */
	public void setTrainingLinkedUserLocalService(
		TrainingLinkedUserLocalService trainingLinkedUserLocalService) {
		this.trainingLinkedUserLocalService = trainingLinkedUserLocalService;
	}

	/**
	 * Returns the training linked user persistence.
	 *
	 * @return the training linked user persistence
	 */
	public TrainingLinkedUserPersistence getTrainingLinkedUserPersistence() {
		return trainingLinkedUserPersistence;
	}

	/**
	 * Sets the training linked user persistence.
	 *
	 * @param trainingLinkedUserPersistence the training linked user persistence
	 */
	public void setTrainingLinkedUserPersistence(
		TrainingLinkedUserPersistence trainingLinkedUserPersistence) {
		this.trainingLinkedUserPersistence = trainingLinkedUserPersistence;
	}

	/**
	 * Returns the training location local service.
	 *
	 * @return the training location local service
	 */
	public TrainingLocationLocalService getTrainingLocationLocalService() {
		return trainingLocationLocalService;
	}

	/**
	 * Sets the training location local service.
	 *
	 * @param trainingLocationLocalService the training location local service
	 */
	public void setTrainingLocationLocalService(
		TrainingLocationLocalService trainingLocationLocalService) {
		this.trainingLocationLocalService = trainingLocationLocalService;
	}

	/**
	 * Returns the training location remote service.
	 *
	 * @return the training location remote service
	 */
	public TrainingLocationService getTrainingLocationService() {
		return trainingLocationService;
	}

	/**
	 * Sets the training location remote service.
	 *
	 * @param trainingLocationService the training location remote service
	 */
	public void setTrainingLocationService(
		TrainingLocationService trainingLocationService) {
		this.trainingLocationService = trainingLocationService;
	}

	/**
	 * Returns the training location persistence.
	 *
	 * @return the training location persistence
	 */
	public TrainingLocationPersistence getTrainingLocationPersistence() {
		return trainingLocationPersistence;
	}

	/**
	 * Sets the training location persistence.
	 *
	 * @param trainingLocationPersistence the training location persistence
	 */
	public void setTrainingLocationPersistence(
		TrainingLocationPersistence trainingLocationPersistence) {
		this.trainingLocationPersistence = trainingLocationPersistence;
	}

	/**
	 * Returns the training location finder.
	 *
	 * @return the training location finder
	 */
	public TrainingLocationFinder getTrainingLocationFinder() {
		return trainingLocationFinder;
	}

	/**
	 * Sets the training location finder.
	 *
	 * @param trainingLocationFinder the training location finder
	 */
	public void setTrainingLocationFinder(
		TrainingLocationFinder trainingLocationFinder) {
		this.trainingLocationFinder = trainingLocationFinder;
	}

	/**
	 * Returns the training worker local service.
	 *
	 * @return the training worker local service
	 */
	public TrainingWorkerLocalService getTrainingWorkerLocalService() {
		return trainingWorkerLocalService;
	}

	/**
	 * Sets the training worker local service.
	 *
	 * @param trainingWorkerLocalService the training worker local service
	 */
	public void setTrainingWorkerLocalService(
		TrainingWorkerLocalService trainingWorkerLocalService) {
		this.trainingWorkerLocalService = trainingWorkerLocalService;
	}

	/**
	 * Returns the training worker persistence.
	 *
	 * @return the training worker persistence
	 */
	public TrainingWorkerPersistence getTrainingWorkerPersistence() {
		return trainingWorkerPersistence;
	}

	/**
	 * Sets the training worker persistence.
	 *
	 * @param trainingWorkerPersistence the training worker persistence
	 */
	public void setTrainingWorkerPersistence(
		TrainingWorkerPersistence trainingWorkerPersistence) {
		this.trainingWorkerPersistence = trainingWorkerPersistence;
	}

	/**
	 * Returns the user profile local service.
	 *
	 * @return the user profile local service
	 */
	public UserProfileLocalService getUserProfileLocalService() {
		return userProfileLocalService;
	}

	/**
	 * Sets the user profile local service.
	 *
	 * @param userProfileLocalService the user profile local service
	 */
	public void setUserProfileLocalService(
		UserProfileLocalService userProfileLocalService) {
		this.userProfileLocalService = userProfileLocalService;
	}

	/**
	 * Returns the user profile persistence.
	 *
	 * @return the user profile persistence
	 */
	public UserProfilePersistence getUserProfilePersistence() {
		return userProfilePersistence;
	}

	/**
	 * Sets the user profile persistence.
	 *
	 * @param userProfilePersistence the user profile persistence
	 */
	public void setUserProfilePersistence(
		UserProfilePersistence userProfilePersistence) {
		this.userProfilePersistence = userProfilePersistence;
	}

	/**
	 * Returns the user profile history local service.
	 *
	 * @return the user profile history local service
	 */
	public UserProfileHistoryLocalService getUserProfileHistoryLocalService() {
		return userProfileHistoryLocalService;
	}

	/**
	 * Sets the user profile history local service.
	 *
	 * @param userProfileHistoryLocalService the user profile history local service
	 */
	public void setUserProfileHistoryLocalService(
		UserProfileHistoryLocalService userProfileHistoryLocalService) {
		this.userProfileHistoryLocalService = userProfileHistoryLocalService;
	}

	/**
	 * Returns the user profile history remote service.
	 *
	 * @return the user profile history remote service
	 */
	public UserProfileHistoryService getUserProfileHistoryService() {
		return userProfileHistoryService;
	}

	/**
	 * Sets the user profile history remote service.
	 *
	 * @param userProfileHistoryService the user profile history remote service
	 */
	public void setUserProfileHistoryService(
		UserProfileHistoryService userProfileHistoryService) {
		this.userProfileHistoryService = userProfileHistoryService;
	}

	/**
	 * Returns the user profile history persistence.
	 *
	 * @return the user profile history persistence
	 */
	public UserProfileHistoryPersistence getUserProfileHistoryPersistence() {
		return userProfileHistoryPersistence;
	}

	/**
	 * Sets the user profile history persistence.
	 *
	 * @param userProfileHistoryPersistence the user profile history persistence
	 */
	public void setUserProfileHistoryPersistence(
		UserProfileHistoryPersistence userProfileHistoryPersistence) {
		this.userProfileHistoryPersistence = userProfileHistoryPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(ClassNameService classNameService) {
		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the organization local service.
	 *
	 * @return the organization local service
	 */
	public OrganizationLocalService getOrganizationLocalService() {
		return organizationLocalService;
	}

	/**
	 * Sets the organization local service.
	 *
	 * @param organizationLocalService the organization local service
	 */
	public void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {
		this.organizationLocalService = organizationLocalService;
	}

	/**
	 * Returns the organization remote service.
	 *
	 * @return the organization remote service
	 */
	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	/**
	 * Sets the organization remote service.
	 *
	 * @param organizationService the organization remote service
	 */
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	/**
	 * Returns the organization persistence.
	 *
	 * @return the organization persistence
	 */
	public OrganizationPersistence getOrganizationPersistence() {
		return organizationPersistence;
	}

	/**
	 * Sets the organization persistence.
	 *
	 * @param organizationPersistence the organization persistence
	 */
	public void setOrganizationPersistence(
		OrganizationPersistence organizationPersistence) {
		this.organizationPersistence = organizationPersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();
	}

	public void destroy() {
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return PartnerWorker.class;
	}

	protected String getModelClassName() {
		return PartnerWorker.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = partnerWorkerPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = AccountAttachmentLocalService.class)
	protected AccountAttachmentLocalService accountAttachmentLocalService;
	@BeanReference(type = AccountAttachmentService.class)
	protected AccountAttachmentService accountAttachmentService;
	@BeanReference(type = AccountAttachmentPersistence.class)
	protected AccountAttachmentPersistence accountAttachmentPersistence;
	@BeanReference(type = AccountCallLocalService.class)
	protected AccountCallLocalService accountCallLocalService;
	@BeanReference(type = AccountCallService.class)
	protected AccountCallService accountCallService;
	@BeanReference(type = AccountCallPersistence.class)
	protected AccountCallPersistence accountCallPersistence;
	@BeanReference(type = AccountCustomerLocalService.class)
	protected AccountCustomerLocalService accountCustomerLocalService;
	@BeanReference(type = AccountCustomerService.class)
	protected AccountCustomerService accountCustomerService;
	@BeanReference(type = AccountCustomerPersistence.class)
	protected AccountCustomerPersistence accountCustomerPersistence;
	@BeanReference(type = AccountEntryLocalService.class)
	protected AccountEntryLocalService accountEntryLocalService;
	@BeanReference(type = AccountEntryService.class)
	protected AccountEntryService accountEntryService;
	@BeanReference(type = AccountEntryPersistence.class)
	protected AccountEntryPersistence accountEntryPersistence;
	@BeanReference(type = AccountEntryFinder.class)
	protected AccountEntryFinder accountEntryFinder;
	@BeanReference(type = AccountEntryLanguageLocalService.class)
	protected AccountEntryLanguageLocalService accountEntryLanguageLocalService;
	@BeanReference(type = AccountEntryLanguageService.class)
	protected AccountEntryLanguageService accountEntryLanguageService;
	@BeanReference(type = AccountEntryLanguagePersistence.class)
	protected AccountEntryLanguagePersistence accountEntryLanguagePersistence;
	@BeanReference(type = AccountEnvironmentLocalService.class)
	protected AccountEnvironmentLocalService accountEnvironmentLocalService;
	@BeanReference(type = AccountEnvironmentService.class)
	protected AccountEnvironmentService accountEnvironmentService;
	@BeanReference(type = AccountEnvironmentPersistence.class)
	protected AccountEnvironmentPersistence accountEnvironmentPersistence;
	@BeanReference(type = AccountEnvironmentAttachmentLocalService.class)
	protected AccountEnvironmentAttachmentLocalService accountEnvironmentAttachmentLocalService;
	@BeanReference(type = AccountEnvironmentAttachmentService.class)
	protected AccountEnvironmentAttachmentService accountEnvironmentAttachmentService;
	@BeanReference(type = AccountEnvironmentAttachmentPersistence.class)
	protected AccountEnvironmentAttachmentPersistence accountEnvironmentAttachmentPersistence;
	@BeanReference(type = AccountInformationLocalService.class)
	protected AccountInformationLocalService accountInformationLocalService;
	@BeanReference(type = AccountInformationService.class)
	protected AccountInformationService accountInformationService;
	@BeanReference(type = AccountInformationPersistence.class)
	protected AccountInformationPersistence accountInformationPersistence;
	@BeanReference(type = AccountLinkLocalService.class)
	protected AccountLinkLocalService accountLinkLocalService;
	@BeanReference(type = AccountLinkService.class)
	protected AccountLinkService accountLinkService;
	@BeanReference(type = AccountLinkPersistence.class)
	protected AccountLinkPersistence accountLinkPersistence;
	@BeanReference(type = AccountProjectLocalService.class)
	protected AccountProjectLocalService accountProjectLocalService;
	@BeanReference(type = AccountProjectService.class)
	protected AccountProjectService accountProjectService;
	@BeanReference(type = AccountProjectPersistence.class)
	protected AccountProjectPersistence accountProjectPersistence;
	@BeanReference(type = AccountWorkerLocalService.class)
	protected AccountWorkerLocalService accountWorkerLocalService;
	@BeanReference(type = AccountWorkerService.class)
	protected AccountWorkerService accountWorkerService;
	@BeanReference(type = AccountWorkerPersistence.class)
	protected AccountWorkerPersistence accountWorkerPersistence;
	@BeanReference(type = AppAuditLocalService.class)
	protected AppAuditLocalService appAuditLocalService;
	@BeanReference(type = AppAuditPersistence.class)
	protected AppAuditPersistence appAuditPersistence;
	@BeanReference(type = AppEntryLocalService.class)
	protected AppEntryLocalService appEntryLocalService;
	@BeanReference(type = AppEntryService.class)
	protected AppEntryService appEntryService;
	@BeanReference(type = AppEntryPersistence.class)
	protected AppEntryPersistence appEntryPersistence;
	@BeanReference(type = AppEntryFinder.class)
	protected AppEntryFinder appEntryFinder;
	@BeanReference(type = AppEntryRelLocalService.class)
	protected AppEntryRelLocalService appEntryRelLocalService;
	@BeanReference(type = AppEntryRelService.class)
	protected AppEntryRelService appEntryRelService;
	@BeanReference(type = AppEntryRelPersistence.class)
	protected AppEntryRelPersistence appEntryRelPersistence;
	@BeanReference(type = AppFlagLocalService.class)
	protected AppFlagLocalService appFlagLocalService;
	@BeanReference(type = AppFlagService.class)
	protected AppFlagService appFlagService;
	@BeanReference(type = AppFlagPersistence.class)
	protected AppFlagPersistence appFlagPersistence;
	@BeanReference(type = AppFlagFinder.class)
	protected AppFlagFinder appFlagFinder;
	@BeanReference(type = AppPackageLocalService.class)
	protected AppPackageLocalService appPackageLocalService;
	@BeanReference(type = AppPackageService.class)
	protected AppPackageService appPackageService;
	@BeanReference(type = AppPackagePersistence.class)
	protected AppPackagePersistence appPackagePersistence;
	@BeanReference(type = AppPackageFinder.class)
	protected AppPackageFinder appPackageFinder;
	@BeanReference(type = AppPackagePluginLocalService.class)
	protected AppPackagePluginLocalService appPackagePluginLocalService;
	@BeanReference(type = AppPackagePluginService.class)
	protected AppPackagePluginService appPackagePluginService;
	@BeanReference(type = AppPackagePluginPersistence.class)
	protected AppPackagePluginPersistence appPackagePluginPersistence;
	@BeanReference(type = AppPricingLocalService.class)
	protected AppPricingLocalService appPricingLocalService;
	@BeanReference(type = AppPricingService.class)
	protected AppPricingService appPricingService;
	@BeanReference(type = AppPricingPersistence.class)
	protected AppPricingPersistence appPricingPersistence;
	@BeanReference(type = AppPricingItemLocalService.class)
	protected AppPricingItemLocalService appPricingItemLocalService;
	@BeanReference(type = AppPricingItemService.class)
	protected AppPricingItemService appPricingItemService;
	@BeanReference(type = AppPricingItemPersistence.class)
	protected AppPricingItemPersistence appPricingItemPersistence;
	@BeanReference(type = AppPricingItemFinder.class)
	protected AppPricingItemFinder appPricingItemFinder;
	@BeanReference(type = AppVersionLocalService.class)
	protected AppVersionLocalService appVersionLocalService;
	@BeanReference(type = AppVersionService.class)
	protected AppVersionService appVersionService;
	@BeanReference(type = AppVersionPersistence.class)
	protected AppVersionPersistence appVersionPersistence;
	@BeanReference(type = AppVersionFinder.class)
	protected AppVersionFinder appVersionFinder;
	@BeanReference(type = AssetAttachmentLocalService.class)
	protected AssetAttachmentLocalService assetAttachmentLocalService;
	@BeanReference(type = AssetAttachmentService.class)
	protected AssetAttachmentService assetAttachmentService;
	@BeanReference(type = AssetAttachmentPersistence.class)
	protected AssetAttachmentPersistence assetAttachmentPersistence;
	@BeanReference(type = AssetAuditLocalService.class)
	protected AssetAuditLocalService assetAuditLocalService;
	@BeanReference(type = AssetAuditService.class)
	protected AssetAuditService assetAuditService;
	@BeanReference(type = AssetAuditPersistence.class)
	protected AssetAuditPersistence assetAuditPersistence;
	@BeanReference(type = AssetAuditFinder.class)
	protected AssetAuditFinder assetAuditFinder;
	@BeanReference(type = AssetLicenseLocalService.class)
	protected AssetLicenseLocalService assetLicenseLocalService;
	@BeanReference(type = AssetLicensePersistence.class)
	protected AssetLicensePersistence assetLicensePersistence;
	@BeanReference(type = AssetListLocalService.class)
	protected AssetListLocalService assetListLocalService;
	@BeanReference(type = AssetListPersistence.class)
	protected AssetListPersistence assetListPersistence;
	@BeanReference(type = AssetListFinder.class)
	protected AssetListFinder assetListFinder;
	@BeanReference(type = AssetListAssetEntryLocalService.class)
	protected AssetListAssetEntryLocalService assetListAssetEntryLocalService;
	@BeanReference(type = AssetListAssetEntryPersistence.class)
	protected AssetListAssetEntryPersistence assetListAssetEntryPersistence;
	@BeanReference(type = AssetReceiptLocalService.class)
	protected AssetReceiptLocalService assetReceiptLocalService;
	@BeanReference(type = AssetReceiptService.class)
	protected AssetReceiptService assetReceiptService;
	@BeanReference(type = AssetReceiptPersistence.class)
	protected AssetReceiptPersistence assetReceiptPersistence;
	@BeanReference(type = AssetReceiptFinder.class)
	protected AssetReceiptFinder assetReceiptFinder;
	@BeanReference(type = AssetReceiptLicenseLocalService.class)
	protected AssetReceiptLicenseLocalService assetReceiptLicenseLocalService;
	@BeanReference(type = AssetReceiptLicenseService.class)
	protected AssetReceiptLicenseService assetReceiptLicenseService;
	@BeanReference(type = AssetReceiptLicensePersistence.class)
	protected AssetReceiptLicensePersistence assetReceiptLicensePersistence;
	@BeanReference(type = AssetReceiptLicenseFinder.class)
	protected AssetReceiptLicenseFinder assetReceiptLicenseFinder;
	@BeanReference(type = AssetReceiptRedeemTokenLocalService.class)
	protected AssetReceiptRedeemTokenLocalService assetReceiptRedeemTokenLocalService;
	@BeanReference(type = AssetReceiptRedeemTokenService.class)
	protected AssetReceiptRedeemTokenService assetReceiptRedeemTokenService;
	@BeanReference(type = AssetReceiptRedeemTokenPersistence.class)
	protected AssetReceiptRedeemTokenPersistence assetReceiptRedeemTokenPersistence;
	@BeanReference(type = AssetReceiptSupportLocalService.class)
	protected AssetReceiptSupportLocalService assetReceiptSupportLocalService;
	@BeanReference(type = AssetReceiptSupportPersistence.class)
	protected AssetReceiptSupportPersistence assetReceiptSupportPersistence;
	@BeanReference(type = AssetRecommendationEntryLocalService.class)
	protected AssetRecommendationEntryLocalService assetRecommendationEntryLocalService;
	@BeanReference(type = AssetRecommendationEntryService.class)
	protected AssetRecommendationEntryService assetRecommendationEntryService;
	@BeanReference(type = AssetRecommendationEntryPersistence.class)
	protected AssetRecommendationEntryPersistence assetRecommendationEntryPersistence;
	@BeanReference(type = AssetRecommendationSetLocalService.class)
	protected AssetRecommendationSetLocalService assetRecommendationSetLocalService;
	@BeanReference(type = AssetRecommendationSetPersistence.class)
	protected AssetRecommendationSetPersistence assetRecommendationSetPersistence;
	@BeanReference(type = AssetStatsDayLocalService.class)
	protected AssetStatsDayLocalService assetStatsDayLocalService;
	@BeanReference(type = AssetStatsDayPersistence.class)
	protected AssetStatsDayPersistence assetStatsDayPersistence;
	@BeanReference(type = AssetStatsMonthLocalService.class)
	protected AssetStatsMonthLocalService assetStatsMonthLocalService;
	@BeanReference(type = AssetStatsMonthPersistence.class)
	protected AssetStatsMonthPersistence assetStatsMonthPersistence;
	@BeanReference(type = AssetStatsWeekLocalService.class)
	protected AssetStatsWeekLocalService assetStatsWeekLocalService;
	@BeanReference(type = AssetStatsWeekPersistence.class)
	protected AssetStatsWeekPersistence assetStatsWeekPersistence;
	@BeanReference(type = AuditActionLocalService.class)
	protected AuditActionLocalService auditActionLocalService;
	@BeanReference(type = AuditActionService.class)
	protected AuditActionService auditActionService;
	@BeanReference(type = AuditActionPersistence.class)
	protected AuditActionPersistence auditActionPersistence;
	@BeanReference(type = AuditEntryLocalService.class)
	protected AuditEntryLocalService auditEntryLocalService;
	@BeanReference(type = AuditEntryService.class)
	protected AuditEntryService auditEntryService;
	@BeanReference(type = AuditEntryPersistence.class)
	protected AuditEntryPersistence auditEntryPersistence;
	@BeanReference(type = ContractAuditLocalService.class)
	protected ContractAuditLocalService contractAuditLocalService;
	@BeanReference(type = ContractAuditService.class)
	protected ContractAuditService contractAuditService;
	@BeanReference(type = ContractAuditPersistence.class)
	protected ContractAuditPersistence contractAuditPersistence;
	@BeanReference(type = ContractEntryLocalService.class)
	protected ContractEntryLocalService contractEntryLocalService;
	@BeanReference(type = ContractEntryService.class)
	protected ContractEntryService contractEntryService;
	@BeanReference(type = ContractEntryPersistence.class)
	protected ContractEntryPersistence contractEntryPersistence;
	@BeanReference(type = CorpEntryLocalService.class)
	protected CorpEntryLocalService corpEntryLocalService;
	@BeanReference(type = CorpEntryService.class)
	protected CorpEntryService corpEntryService;
	@BeanReference(type = CorpEntryPersistence.class)
	protected CorpEntryPersistence corpEntryPersistence;
	@BeanReference(type = CorpEntryFinder.class)
	protected CorpEntryFinder corpEntryFinder;
	@BeanReference(type = CorpGroupLocalService.class)
	protected CorpGroupLocalService corpGroupLocalService;
	@BeanReference(type = CorpGroupService.class)
	protected CorpGroupService corpGroupService;
	@BeanReference(type = CorpGroupPersistence.class)
	protected CorpGroupPersistence corpGroupPersistence;
	@BeanReference(type = CorpMembershipRequestLocalService.class)
	protected CorpMembershipRequestLocalService corpMembershipRequestLocalService;
	@BeanReference(type = CorpMembershipRequestService.class)
	protected CorpMembershipRequestService corpMembershipRequestService;
	@BeanReference(type = CorpMembershipRequestPersistence.class)
	protected CorpMembershipRequestPersistence corpMembershipRequestPersistence;
	@BeanReference(type = CorpProjectLocalService.class)
	protected CorpProjectLocalService corpProjectLocalService;
	@BeanReference(type = CorpProjectService.class)
	protected CorpProjectService corpProjectService;
	@BeanReference(type = CorpProjectPersistence.class)
	protected CorpProjectPersistence corpProjectPersistence;
	@BeanReference(type = CorpProjectMessageLocalService.class)
	protected CorpProjectMessageLocalService corpProjectMessageLocalService;
	@BeanReference(type = CorpProjectMessageService.class)
	protected CorpProjectMessageService corpProjectMessageService;
	@BeanReference(type = CorpProjectMessagePersistence.class)
	protected CorpProjectMessagePersistence corpProjectMessagePersistence;
	@BeanReference(type = CountryAppPricingLocalService.class)
	protected CountryAppPricingLocalService countryAppPricingLocalService;
	@BeanReference(type = CountryAppPricingService.class)
	protected CountryAppPricingService countryAppPricingService;
	@BeanReference(type = CountryAppPricingPersistence.class)
	protected CountryAppPricingPersistence countryAppPricingPersistence;
	@BeanReference(type = CurrencyEntryLocalService.class)
	protected CurrencyEntryLocalService currencyEntryLocalService;
	@BeanReference(type = CurrencyEntryService.class)
	protected CurrencyEntryService currencyEntryService;
	@BeanReference(type = CurrencyEntryPersistence.class)
	protected CurrencyEntryPersistence currencyEntryPersistence;
	@BeanReference(type = DeveloperEntryLocalService.class)
	protected DeveloperEntryLocalService developerEntryLocalService;
	@BeanReference(type = DeveloperEntryService.class)
	protected DeveloperEntryService developerEntryService;
	@BeanReference(type = DeveloperEntryPersistence.class)
	protected DeveloperEntryPersistence developerEntryPersistence;
	@BeanReference(type = ExternalIdMapperLocalService.class)
	protected ExternalIdMapperLocalService externalIdMapperLocalService;
	@BeanReference(type = ExternalIdMapperPersistence.class)
	protected ExternalIdMapperPersistence externalIdMapperPersistence;
	@BeanReference(type = FeedbackEntryLocalService.class)
	protected FeedbackEntryLocalService feedbackEntryLocalService;
	@BeanReference(type = FeedbackEntryService.class)
	protected FeedbackEntryService feedbackEntryService;
	@BeanReference(type = FeedbackEntryPersistence.class)
	protected FeedbackEntryPersistence feedbackEntryPersistence;
	@BeanReference(type = HolidayCalendarLocalService.class)
	protected HolidayCalendarLocalService holidayCalendarLocalService;
	@BeanReference(type = HolidayCalendarService.class)
	protected HolidayCalendarService holidayCalendarService;
	@BeanReference(type = HolidayCalendarPersistence.class)
	protected HolidayCalendarPersistence holidayCalendarPersistence;
	@BeanReference(type = HolidayCalendarRelLocalService.class)
	protected HolidayCalendarRelLocalService holidayCalendarRelLocalService;
	@BeanReference(type = HolidayCalendarRelService.class)
	protected HolidayCalendarRelService holidayCalendarRelService;
	@BeanReference(type = HolidayCalendarRelPersistence.class)
	protected HolidayCalendarRelPersistence holidayCalendarRelPersistence;
	@BeanReference(type = HolidayEntryLocalService.class)
	protected HolidayEntryLocalService holidayEntryLocalService;
	@BeanReference(type = HolidayEntryService.class)
	protected HolidayEntryService holidayEntryService;
	@BeanReference(type = HolidayEntryPersistence.class)
	protected HolidayEntryPersistence holidayEntryPersistence;
	@BeanReference(type = HolidayEntryFinder.class)
	protected HolidayEntryFinder holidayEntryFinder;
	@BeanReference(type = LCSSubscriptionEntryLocalService.class)
	protected LCSSubscriptionEntryLocalService lcsSubscriptionEntryLocalService;
	@BeanReference(type = LCSSubscriptionEntryService.class)
	protected LCSSubscriptionEntryService lcsSubscriptionEntryService;
	@BeanReference(type = LCSSubscriptionEntryPersistence.class)
	protected LCSSubscriptionEntryPersistence lcsSubscriptionEntryPersistence;
	@BeanReference(type = LicenseEntryLocalService.class)
	protected LicenseEntryLocalService licenseEntryLocalService;
	@BeanReference(type = LicenseEntryService.class)
	protected LicenseEntryService licenseEntryService;
	@BeanReference(type = LicenseEntryPersistence.class)
	protected LicenseEntryPersistence licenseEntryPersistence;
	@BeanReference(type = LicenseKeyLocalService.class)
	protected LicenseKeyLocalService licenseKeyLocalService;
	@BeanReference(type = LicenseKeyService.class)
	protected LicenseKeyService licenseKeyService;
	@BeanReference(type = LicenseKeyPersistence.class)
	protected LicenseKeyPersistence licenseKeyPersistence;
	@BeanReference(type = LicenseKeyFinder.class)
	protected LicenseKeyFinder licenseKeyFinder;
	@BeanReference(type = LicenseKeySetLocalService.class)
	protected LicenseKeySetLocalService licenseKeySetLocalService;
	@BeanReference(type = LicenseKeySetService.class)
	protected LicenseKeySetService licenseKeySetService;
	@BeanReference(type = LicenseKeySetPersistence.class)
	protected LicenseKeySetPersistence licenseKeySetPersistence;
	@BeanReference(type = MarketingEventLocalService.class)
	protected MarketingEventLocalService marketingEventLocalService;
	@BeanReference(type = MarketingEventPersistence.class)
	protected MarketingEventPersistence marketingEventPersistence;
	@BeanReference(type = OfferingBundleLocalService.class)
	protected OfferingBundleLocalService offeringBundleLocalService;
	@BeanReference(type = OfferingBundleService.class)
	protected OfferingBundleService offeringBundleService;
	@BeanReference(type = OfferingBundlePersistence.class)
	protected OfferingBundlePersistence offeringBundlePersistence;
	@BeanReference(type = OfferingDefinitionLocalService.class)
	protected OfferingDefinitionLocalService offeringDefinitionLocalService;
	@BeanReference(type = OfferingDefinitionService.class)
	protected OfferingDefinitionService offeringDefinitionService;
	@BeanReference(type = OfferingDefinitionPersistence.class)
	protected OfferingDefinitionPersistence offeringDefinitionPersistence;
	@BeanReference(type = OfferingEntryLocalService.class)
	protected OfferingEntryLocalService offeringEntryLocalService;
	@BeanReference(type = OfferingEntryService.class)
	protected OfferingEntryService offeringEntryService;
	@BeanReference(type = OfferingEntryPersistence.class)
	protected OfferingEntryPersistence offeringEntryPersistence;
	@BeanReference(type = OfferingEntryFinder.class)
	protected OfferingEntryFinder offeringEntryFinder;
	@BeanReference(type = OrderEntryLocalService.class)
	protected OrderEntryLocalService orderEntryLocalService;
	@BeanReference(type = OrderEntryService.class)
	protected OrderEntryService orderEntryService;
	@BeanReference(type = OrderEntryPersistence.class)
	protected OrderEntryPersistence orderEntryPersistence;
	@BeanReference(type = OrderEntryFinder.class)
	protected OrderEntryFinder orderEntryFinder;
	@BeanReference(type = OSBCountryLocalService.class)
	protected OSBCountryLocalService osbCountryLocalService;
	@BeanReference(type = OSBCountryService.class)
	protected OSBCountryService osbCountryService;
	@BeanReference(type = OSBRegionLocalService.class)
	protected OSBRegionLocalService osbRegionLocalService;
	@BeanReference(type = OSBRegionService.class)
	protected OSBRegionService osbRegionService;
	@BeanReference(type = PartnerEntryLocalService.class)
	protected PartnerEntryLocalService partnerEntryLocalService;
	@BeanReference(type = PartnerEntryService.class)
	protected PartnerEntryService partnerEntryService;
	@BeanReference(type = PartnerEntryPersistence.class)
	protected PartnerEntryPersistence partnerEntryPersistence;
	@BeanReference(type = PartnerEntryFinder.class)
	protected PartnerEntryFinder partnerEntryFinder;
	@BeanReference(type = PartnerWorkerLocalService.class)
	protected PartnerWorkerLocalService partnerWorkerLocalService;
	@BeanReference(type = PartnerWorkerService.class)
	protected PartnerWorkerService partnerWorkerService;
	@BeanReference(type = PartnerWorkerPersistence.class)
	protected PartnerWorkerPersistence partnerWorkerPersistence;
	@BeanReference(type = PortalReleaseLocalService.class)
	protected PortalReleaseLocalService portalReleaseLocalService;
	@BeanReference(type = PortalReleasePersistence.class)
	protected PortalReleasePersistence portalReleasePersistence;
	@BeanReference(type = ProductEntryLocalService.class)
	protected ProductEntryLocalService productEntryLocalService;
	@BeanReference(type = ProductEntryService.class)
	protected ProductEntryService productEntryService;
	@BeanReference(type = ProductEntryPersistence.class)
	protected ProductEntryPersistence productEntryPersistence;
	@BeanReference(type = ProductEntryFinder.class)
	protected ProductEntryFinder productEntryFinder;
	@BeanReference(type = SearchFilterLocalService.class)
	protected SearchFilterLocalService searchFilterLocalService;
	@BeanReference(type = SearchFilterService.class)
	protected SearchFilterService searchFilterService;
	@BeanReference(type = SearchFilterPersistence.class)
	protected SearchFilterPersistence searchFilterPersistence;
	@BeanReference(type = SecurityPatchLocalService.class)
	protected SecurityPatchLocalService securityPatchLocalService;
	@BeanReference(type = SecurityPatchService.class)
	protected SecurityPatchService securityPatchService;
	@BeanReference(type = SecurityPatchPersistence.class)
	protected SecurityPatchPersistence securityPatchPersistence;
	@BeanReference(type = SupportLaborLocalService.class)
	protected SupportLaborLocalService supportLaborLocalService;
	@BeanReference(type = SupportLaborService.class)
	protected SupportLaborService supportLaborService;
	@BeanReference(type = SupportLaborPersistence.class)
	protected SupportLaborPersistence supportLaborPersistence;
	@BeanReference(type = SupportRegionLocalService.class)
	protected SupportRegionLocalService supportRegionLocalService;
	@BeanReference(type = SupportRegionService.class)
	protected SupportRegionService supportRegionService;
	@BeanReference(type = SupportRegionPersistence.class)
	protected SupportRegionPersistence supportRegionPersistence;
	@BeanReference(type = SupportResponseLocalService.class)
	protected SupportResponseLocalService supportResponseLocalService;
	@BeanReference(type = SupportResponseService.class)
	protected SupportResponseService supportResponseService;
	@BeanReference(type = SupportResponsePersistence.class)
	protected SupportResponsePersistence supportResponsePersistence;
	@BeanReference(type = SupportResponseFinder.class)
	protected SupportResponseFinder supportResponseFinder;
	@BeanReference(type = SupportTeamLocalService.class)
	protected SupportTeamLocalService supportTeamLocalService;
	@BeanReference(type = SupportTeamService.class)
	protected SupportTeamService supportTeamService;
	@BeanReference(type = SupportTeamPersistence.class)
	protected SupportTeamPersistence supportTeamPersistence;
	@BeanReference(type = SupportTeamFinder.class)
	protected SupportTeamFinder supportTeamFinder;
	@BeanReference(type = SupportTeamLanguageLocalService.class)
	protected SupportTeamLanguageLocalService supportTeamLanguageLocalService;
	@BeanReference(type = SupportTeamLanguageService.class)
	protected SupportTeamLanguageService supportTeamLanguageService;
	@BeanReference(type = SupportTeamLanguagePersistence.class)
	protected SupportTeamLanguagePersistence supportTeamLanguagePersistence;
	@BeanReference(type = SupportWorkerLocalService.class)
	protected SupportWorkerLocalService supportWorkerLocalService;
	@BeanReference(type = SupportWorkerService.class)
	protected SupportWorkerService supportWorkerService;
	@BeanReference(type = SupportWorkerPersistence.class)
	protected SupportWorkerPersistence supportWorkerPersistence;
	@BeanReference(type = SupportWorkerFinder.class)
	protected SupportWorkerFinder supportWorkerFinder;
	@BeanReference(type = SupportWorkerAccountTierLocalService.class)
	protected SupportWorkerAccountTierLocalService supportWorkerAccountTierLocalService;
	@BeanReference(type = SupportWorkerAccountTierService.class)
	protected SupportWorkerAccountTierService supportWorkerAccountTierService;
	@BeanReference(type = SupportWorkerAccountTierPersistence.class)
	protected SupportWorkerAccountTierPersistence supportWorkerAccountTierPersistence;
	@BeanReference(type = SupportWorkerComponentLocalService.class)
	protected SupportWorkerComponentLocalService supportWorkerComponentLocalService;
	@BeanReference(type = SupportWorkerComponentService.class)
	protected SupportWorkerComponentService supportWorkerComponentService;
	@BeanReference(type = SupportWorkerComponentPersistence.class)
	protected SupportWorkerComponentPersistence supportWorkerComponentPersistence;
	@BeanReference(type = SupportWorkerSeverityLocalService.class)
	protected SupportWorkerSeverityLocalService supportWorkerSeverityLocalService;
	@BeanReference(type = SupportWorkerSeverityService.class)
	protected SupportWorkerSeverityService supportWorkerSeverityService;
	@BeanReference(type = SupportWorkerSeverityPersistence.class)
	protected SupportWorkerSeverityPersistence supportWorkerSeverityPersistence;
	@BeanReference(type = TicketAttachmentLocalService.class)
	protected TicketAttachmentLocalService ticketAttachmentLocalService;
	@BeanReference(type = TicketAttachmentService.class)
	protected TicketAttachmentService ticketAttachmentService;
	@BeanReference(type = TicketAttachmentPersistence.class)
	protected TicketAttachmentPersistence ticketAttachmentPersistence;
	@BeanReference(type = TicketCallLocalService.class)
	protected TicketCallLocalService ticketCallLocalService;
	@BeanReference(type = TicketCallService.class)
	protected TicketCallService ticketCallService;
	@BeanReference(type = TicketCallPersistence.class)
	protected TicketCallPersistence ticketCallPersistence;
	@BeanReference(type = TicketCannedResponseLocalService.class)
	protected TicketCannedResponseLocalService ticketCannedResponseLocalService;
	@BeanReference(type = TicketCannedResponseService.class)
	protected TicketCannedResponseService ticketCannedResponseService;
	@BeanReference(type = TicketCannedResponsePersistence.class)
	protected TicketCannedResponsePersistence ticketCannedResponsePersistence;
	@BeanReference(type = TicketCannedResponseFinder.class)
	protected TicketCannedResponseFinder ticketCannedResponseFinder;
	@BeanReference(type = TicketCommentLocalService.class)
	protected TicketCommentLocalService ticketCommentLocalService;
	@BeanReference(type = TicketCommentService.class)
	protected TicketCommentService ticketCommentService;
	@BeanReference(type = TicketCommentPersistence.class)
	protected TicketCommentPersistence ticketCommentPersistence;
	@BeanReference(type = TicketCommentFinder.class)
	protected TicketCommentFinder ticketCommentFinder;
	@BeanReference(type = TicketEntryLocalService.class)
	protected TicketEntryLocalService ticketEntryLocalService;
	@BeanReference(type = TicketEntryService.class)
	protected TicketEntryService ticketEntryService;
	@BeanReference(type = TicketEntryPersistence.class)
	protected TicketEntryPersistence ticketEntryPersistence;
	@BeanReference(type = TicketEntryFinder.class)
	protected TicketEntryFinder ticketEntryFinder;
	@BeanReference(type = TicketFeedbackLocalService.class)
	protected TicketFeedbackLocalService ticketFeedbackLocalService;
	@BeanReference(type = TicketFeedbackService.class)
	protected TicketFeedbackService ticketFeedbackService;
	@BeanReference(type = TicketFeedbackPersistence.class)
	protected TicketFeedbackPersistence ticketFeedbackPersistence;
	@BeanReference(type = TicketFeedbackFinder.class)
	protected TicketFeedbackFinder ticketFeedbackFinder;
	@BeanReference(type = TicketFlagLocalService.class)
	protected TicketFlagLocalService ticketFlagLocalService;
	@BeanReference(type = TicketFlagService.class)
	protected TicketFlagService ticketFlagService;
	@BeanReference(type = TicketFlagPersistence.class)
	protected TicketFlagPersistence ticketFlagPersistence;
	@BeanReference(type = TicketInformationLocalService.class)
	protected TicketInformationLocalService ticketInformationLocalService;
	@BeanReference(type = TicketInformationService.class)
	protected TicketInformationService ticketInformationService;
	@BeanReference(type = TicketInformationPersistence.class)
	protected TicketInformationPersistence ticketInformationPersistence;
	@BeanReference(type = TicketLinkLocalService.class)
	protected TicketLinkLocalService ticketLinkLocalService;
	@BeanReference(type = TicketLinkService.class)
	protected TicketLinkService ticketLinkService;
	@BeanReference(type = TicketLinkPersistence.class)
	protected TicketLinkPersistence ticketLinkPersistence;
	@BeanReference(type = TicketSolutionLocalService.class)
	protected TicketSolutionLocalService ticketSolutionLocalService;
	@BeanReference(type = TicketSolutionService.class)
	protected TicketSolutionService ticketSolutionService;
	@BeanReference(type = TicketSolutionPersistence.class)
	protected TicketSolutionPersistence ticketSolutionPersistence;
	@BeanReference(type = TicketWorkerLocalService.class)
	protected TicketWorkerLocalService ticketWorkerLocalService;
	@BeanReference(type = TicketWorkerService.class)
	protected TicketWorkerService ticketWorkerService;
	@BeanReference(type = TicketWorkerPersistence.class)
	protected TicketWorkerPersistence ticketWorkerPersistence;
	@BeanReference(type = TrainingCertificateLocalService.class)
	protected TrainingCertificateLocalService trainingCertificateLocalService;
	@BeanReference(type = TrainingCertificateService.class)
	protected TrainingCertificateService trainingCertificateService;
	@BeanReference(type = TrainingCertificatePersistence.class)
	protected TrainingCertificatePersistence trainingCertificatePersistence;
	@BeanReference(type = TrainingCertificateTemplateLocalService.class)
	protected TrainingCertificateTemplateLocalService trainingCertificateTemplateLocalService;
	@BeanReference(type = TrainingCertificateTemplateService.class)
	protected TrainingCertificateTemplateService trainingCertificateTemplateService;
	@BeanReference(type = TrainingCertificateTemplatePersistence.class)
	protected TrainingCertificateTemplatePersistence trainingCertificateTemplatePersistence;
	@BeanReference(type = TrainingCourseLocalService.class)
	protected TrainingCourseLocalService trainingCourseLocalService;
	@BeanReference(type = TrainingCourseService.class)
	protected TrainingCourseService trainingCourseService;
	@BeanReference(type = TrainingCoursePersistence.class)
	protected TrainingCoursePersistence trainingCoursePersistence;
	@BeanReference(type = TrainingCustomerLocalService.class)
	protected TrainingCustomerLocalService trainingCustomerLocalService;
	@BeanReference(type = TrainingCustomerService.class)
	protected TrainingCustomerService trainingCustomerService;
	@BeanReference(type = TrainingCustomerPersistence.class)
	protected TrainingCustomerPersistence trainingCustomerPersistence;
	@BeanReference(type = TrainingCustomerFinder.class)
	protected TrainingCustomerFinder trainingCustomerFinder;
	@BeanReference(type = TrainingEventLocalService.class)
	protected TrainingEventLocalService trainingEventLocalService;
	@BeanReference(type = TrainingEventService.class)
	protected TrainingEventService trainingEventService;
	@BeanReference(type = TrainingEventPersistence.class)
	protected TrainingEventPersistence trainingEventPersistence;
	@BeanReference(type = TrainingEventFinder.class)
	protected TrainingEventFinder trainingEventFinder;
	@BeanReference(type = TrainingExamLocalService.class)
	protected TrainingExamLocalService trainingExamLocalService;
	@BeanReference(type = TrainingExamService.class)
	protected TrainingExamService trainingExamService;
	@BeanReference(type = TrainingExamPersistence.class)
	protected TrainingExamPersistence trainingExamPersistence;
	@BeanReference(type = TrainingExamResultLocalService.class)
	protected TrainingExamResultLocalService trainingExamResultLocalService;
	@BeanReference(type = TrainingExamResultService.class)
	protected TrainingExamResultService trainingExamResultService;
	@BeanReference(type = TrainingExamResultPersistence.class)
	protected TrainingExamResultPersistence trainingExamResultPersistence;
	@BeanReference(type = TrainingExamResultFinder.class)
	protected TrainingExamResultFinder trainingExamResultFinder;
	@BeanReference(type = TrainingExamResultItemLocalService.class)
	protected TrainingExamResultItemLocalService trainingExamResultItemLocalService;
	@BeanReference(type = TrainingExamResultItemService.class)
	protected TrainingExamResultItemService trainingExamResultItemService;
	@BeanReference(type = TrainingExamResultItemPersistence.class)
	protected TrainingExamResultItemPersistence trainingExamResultItemPersistence;
	@BeanReference(type = TrainingExamResultItemFinder.class)
	protected TrainingExamResultItemFinder trainingExamResultItemFinder;
	@BeanReference(type = TrainingExamResultSectionLocalService.class)
	protected TrainingExamResultSectionLocalService trainingExamResultSectionLocalService;
	@BeanReference(type = TrainingExamResultSectionService.class)
	protected TrainingExamResultSectionService trainingExamResultSectionService;
	@BeanReference(type = TrainingExamResultSectionPersistence.class)
	protected TrainingExamResultSectionPersistence trainingExamResultSectionPersistence;
	@BeanReference(type = TrainingExamResultSectionFinder.class)
	protected TrainingExamResultSectionFinder trainingExamResultSectionFinder;
	@BeanReference(type = TrainingImportLogLocalService.class)
	protected TrainingImportLogLocalService trainingImportLogLocalService;
	@BeanReference(type = TrainingImportLogPersistence.class)
	protected TrainingImportLogPersistence trainingImportLogPersistence;
	@BeanReference(type = TrainingLinkedUserLocalService.class)
	protected TrainingLinkedUserLocalService trainingLinkedUserLocalService;
	@BeanReference(type = TrainingLinkedUserPersistence.class)
	protected TrainingLinkedUserPersistence trainingLinkedUserPersistence;
	@BeanReference(type = TrainingLocationLocalService.class)
	protected TrainingLocationLocalService trainingLocationLocalService;
	@BeanReference(type = TrainingLocationService.class)
	protected TrainingLocationService trainingLocationService;
	@BeanReference(type = TrainingLocationPersistence.class)
	protected TrainingLocationPersistence trainingLocationPersistence;
	@BeanReference(type = TrainingLocationFinder.class)
	protected TrainingLocationFinder trainingLocationFinder;
	@BeanReference(type = TrainingWorkerLocalService.class)
	protected TrainingWorkerLocalService trainingWorkerLocalService;
	@BeanReference(type = TrainingWorkerPersistence.class)
	protected TrainingWorkerPersistence trainingWorkerPersistence;
	@BeanReference(type = UserProfileLocalService.class)
	protected UserProfileLocalService userProfileLocalService;
	@BeanReference(type = UserProfilePersistence.class)
	protected UserProfilePersistence userProfilePersistence;
	@BeanReference(type = UserProfileHistoryLocalService.class)
	protected UserProfileHistoryLocalService userProfileHistoryLocalService;
	@BeanReference(type = UserProfileHistoryService.class)
	protected UserProfileHistoryService userProfileHistoryService;
	@BeanReference(type = UserProfileHistoryPersistence.class)
	protected UserProfileHistoryPersistence userProfileHistoryPersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ClassNameLocalService.class)
	protected ClassNameLocalService classNameLocalService;
	@BeanReference(type = ClassNameService.class)
	protected ClassNameService classNameService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = OrganizationLocalService.class)
	protected OrganizationLocalService organizationLocalService;
	@BeanReference(type = OrganizationService.class)
	protected OrganizationService organizationService;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private PartnerWorkerServiceClpInvoker _clpInvoker = new PartnerWorkerServiceClpInvoker();
}