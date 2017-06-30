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

package com.liferay.osb.service;

import com.liferay.osb.model.AccountAttachmentClp;
import com.liferay.osb.model.AccountCallClp;
import com.liferay.osb.model.AccountCustomerClp;
import com.liferay.osb.model.AccountEntryClp;
import com.liferay.osb.model.AccountEntryLanguageClp;
import com.liferay.osb.model.AccountEnvironmentAttachmentClp;
import com.liferay.osb.model.AccountEnvironmentClp;
import com.liferay.osb.model.AccountInformationClp;
import com.liferay.osb.model.AccountLinkClp;
import com.liferay.osb.model.AccountProjectClp;
import com.liferay.osb.model.AccountWorkerClp;
import com.liferay.osb.model.AppAuditClp;
import com.liferay.osb.model.AppEntryClp;
import com.liferay.osb.model.AppEntryRelClp;
import com.liferay.osb.model.AppFlagClp;
import com.liferay.osb.model.AppPackageClp;
import com.liferay.osb.model.AppPackagePluginClp;
import com.liferay.osb.model.AppPricingClp;
import com.liferay.osb.model.AppPricingItemClp;
import com.liferay.osb.model.AppVersionClp;
import com.liferay.osb.model.AssetAttachmentClp;
import com.liferay.osb.model.AssetAuditClp;
import com.liferay.osb.model.AssetLicenseClp;
import com.liferay.osb.model.AssetListAssetEntryClp;
import com.liferay.osb.model.AssetListClp;
import com.liferay.osb.model.AssetReceiptClp;
import com.liferay.osb.model.AssetReceiptLicenseClp;
import com.liferay.osb.model.AssetReceiptRedeemTokenClp;
import com.liferay.osb.model.AssetReceiptSupportClp;
import com.liferay.osb.model.AssetRecommendationEntryClp;
import com.liferay.osb.model.AssetRecommendationSetClp;
import com.liferay.osb.model.AssetStatsDayClp;
import com.liferay.osb.model.AssetStatsMonthClp;
import com.liferay.osb.model.AssetStatsWeekClp;
import com.liferay.osb.model.AuditActionClp;
import com.liferay.osb.model.AuditEntryClp;
import com.liferay.osb.model.ContractAuditClp;
import com.liferay.osb.model.ContractEntryClp;
import com.liferay.osb.model.CorpEntryClp;
import com.liferay.osb.model.CorpGroupClp;
import com.liferay.osb.model.CorpMembershipRequestClp;
import com.liferay.osb.model.CorpProjectClp;
import com.liferay.osb.model.CorpProjectMessageClp;
import com.liferay.osb.model.CountryAppPricingClp;
import com.liferay.osb.model.CurrencyEntryClp;
import com.liferay.osb.model.DeveloperEntryClp;
import com.liferay.osb.model.ExternalIdMapperClp;
import com.liferay.osb.model.FeedbackEntryClp;
import com.liferay.osb.model.HolidayCalendarClp;
import com.liferay.osb.model.HolidayCalendarRelClp;
import com.liferay.osb.model.HolidayEntryClp;
import com.liferay.osb.model.LCSSubscriptionEntryClp;
import com.liferay.osb.model.LicenseEntryClp;
import com.liferay.osb.model.LicenseKeyClp;
import com.liferay.osb.model.LicenseKeySetClp;
import com.liferay.osb.model.MarketingEventClp;
import com.liferay.osb.model.OfferingBundleClp;
import com.liferay.osb.model.OfferingDefinitionClp;
import com.liferay.osb.model.OfferingEntryClp;
import com.liferay.osb.model.OrderEntryClp;
import com.liferay.osb.model.PartnerEntryClp;
import com.liferay.osb.model.PartnerWorkerClp;
import com.liferay.osb.model.PortalReleaseClp;
import com.liferay.osb.model.ProductEntryClp;
import com.liferay.osb.model.SearchFilterClp;
import com.liferay.osb.model.SecurityPatchClp;
import com.liferay.osb.model.SupportLaborClp;
import com.liferay.osb.model.SupportRegionClp;
import com.liferay.osb.model.SupportResponseClp;
import com.liferay.osb.model.SupportTeamClp;
import com.liferay.osb.model.SupportTeamLanguageClp;
import com.liferay.osb.model.SupportWorkerAccountTierClp;
import com.liferay.osb.model.SupportWorkerClp;
import com.liferay.osb.model.SupportWorkerComponentClp;
import com.liferay.osb.model.SupportWorkerSeverityClp;
import com.liferay.osb.model.TicketAttachmentClp;
import com.liferay.osb.model.TicketCallClp;
import com.liferay.osb.model.TicketCannedResponseClp;
import com.liferay.osb.model.TicketCommentClp;
import com.liferay.osb.model.TicketEntryClp;
import com.liferay.osb.model.TicketFeedbackClp;
import com.liferay.osb.model.TicketFlagClp;
import com.liferay.osb.model.TicketInformationClp;
import com.liferay.osb.model.TicketLinkClp;
import com.liferay.osb.model.TicketSolutionClp;
import com.liferay.osb.model.TicketWorkerClp;
import com.liferay.osb.model.TrainingCertificateClp;
import com.liferay.osb.model.TrainingCertificateTemplateClp;
import com.liferay.osb.model.TrainingCourseClp;
import com.liferay.osb.model.TrainingCustomerClp;
import com.liferay.osb.model.TrainingEventClp;
import com.liferay.osb.model.TrainingExamClp;
import com.liferay.osb.model.TrainingExamResultClp;
import com.liferay.osb.model.TrainingExamResultItemClp;
import com.liferay.osb.model.TrainingExamResultSectionClp;
import com.liferay.osb.model.TrainingImportLogClp;
import com.liferay.osb.model.TrainingLinkedUserClp;
import com.liferay.osb.model.TrainingLocationClp;
import com.liferay.osb.model.TrainingWorkerClp;
import com.liferay.osb.model.UserProfileClp;
import com.liferay.osb.model.UserProfileHistoryClp;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"osb-portlet-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to locate deployment context from portlet properties");
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"osb-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"Unable to locate deployment context from portal properties");
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "osb-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(AccountAttachmentClp.class.getName())) {
			return translateInputAccountAttachment(oldModel);
		}

		if (oldModelClassName.equals(AccountCallClp.class.getName())) {
			return translateInputAccountCall(oldModel);
		}

		if (oldModelClassName.equals(AccountCustomerClp.class.getName())) {
			return translateInputAccountCustomer(oldModel);
		}

		if (oldModelClassName.equals(AccountEntryClp.class.getName())) {
			return translateInputAccountEntry(oldModel);
		}

		if (oldModelClassName.equals(AccountEntryLanguageClp.class.getName())) {
			return translateInputAccountEntryLanguage(oldModel);
		}

		if (oldModelClassName.equals(AccountEnvironmentClp.class.getName())) {
			return translateInputAccountEnvironment(oldModel);
		}

		if (oldModelClassName.equals(
					AccountEnvironmentAttachmentClp.class.getName())) {
			return translateInputAccountEnvironmentAttachment(oldModel);
		}

		if (oldModelClassName.equals(AccountInformationClp.class.getName())) {
			return translateInputAccountInformation(oldModel);
		}

		if (oldModelClassName.equals(AccountLinkClp.class.getName())) {
			return translateInputAccountLink(oldModel);
		}

		if (oldModelClassName.equals(AccountProjectClp.class.getName())) {
			return translateInputAccountProject(oldModel);
		}

		if (oldModelClassName.equals(AccountWorkerClp.class.getName())) {
			return translateInputAccountWorker(oldModel);
		}

		if (oldModelClassName.equals(AppAuditClp.class.getName())) {
			return translateInputAppAudit(oldModel);
		}

		if (oldModelClassName.equals(AppEntryClp.class.getName())) {
			return translateInputAppEntry(oldModel);
		}

		if (oldModelClassName.equals(AppEntryRelClp.class.getName())) {
			return translateInputAppEntryRel(oldModel);
		}

		if (oldModelClassName.equals(AppFlagClp.class.getName())) {
			return translateInputAppFlag(oldModel);
		}

		if (oldModelClassName.equals(AppPackageClp.class.getName())) {
			return translateInputAppPackage(oldModel);
		}

		if (oldModelClassName.equals(AppPackagePluginClp.class.getName())) {
			return translateInputAppPackagePlugin(oldModel);
		}

		if (oldModelClassName.equals(AppPricingClp.class.getName())) {
			return translateInputAppPricing(oldModel);
		}

		if (oldModelClassName.equals(AppPricingItemClp.class.getName())) {
			return translateInputAppPricingItem(oldModel);
		}

		if (oldModelClassName.equals(AppVersionClp.class.getName())) {
			return translateInputAppVersion(oldModel);
		}

		if (oldModelClassName.equals(AssetAttachmentClp.class.getName())) {
			return translateInputAssetAttachment(oldModel);
		}

		if (oldModelClassName.equals(AssetAuditClp.class.getName())) {
			return translateInputAssetAudit(oldModel);
		}

		if (oldModelClassName.equals(AssetLicenseClp.class.getName())) {
			return translateInputAssetLicense(oldModel);
		}

		if (oldModelClassName.equals(AssetListClp.class.getName())) {
			return translateInputAssetList(oldModel);
		}

		if (oldModelClassName.equals(AssetListAssetEntryClp.class.getName())) {
			return translateInputAssetListAssetEntry(oldModel);
		}

		if (oldModelClassName.equals(AssetReceiptClp.class.getName())) {
			return translateInputAssetReceipt(oldModel);
		}

		if (oldModelClassName.equals(AssetReceiptLicenseClp.class.getName())) {
			return translateInputAssetReceiptLicense(oldModel);
		}

		if (oldModelClassName.equals(AssetReceiptRedeemTokenClp.class.getName())) {
			return translateInputAssetReceiptRedeemToken(oldModel);
		}

		if (oldModelClassName.equals(AssetReceiptSupportClp.class.getName())) {
			return translateInputAssetReceiptSupport(oldModel);
		}

		if (oldModelClassName.equals(
					AssetRecommendationEntryClp.class.getName())) {
			return translateInputAssetRecommendationEntry(oldModel);
		}

		if (oldModelClassName.equals(AssetRecommendationSetClp.class.getName())) {
			return translateInputAssetRecommendationSet(oldModel);
		}

		if (oldModelClassName.equals(AssetStatsDayClp.class.getName())) {
			return translateInputAssetStatsDay(oldModel);
		}

		if (oldModelClassName.equals(AssetStatsMonthClp.class.getName())) {
			return translateInputAssetStatsMonth(oldModel);
		}

		if (oldModelClassName.equals(AssetStatsWeekClp.class.getName())) {
			return translateInputAssetStatsWeek(oldModel);
		}

		if (oldModelClassName.equals(AuditActionClp.class.getName())) {
			return translateInputAuditAction(oldModel);
		}

		if (oldModelClassName.equals(AuditEntryClp.class.getName())) {
			return translateInputAuditEntry(oldModel);
		}

		if (oldModelClassName.equals(ContractAuditClp.class.getName())) {
			return translateInputContractAudit(oldModel);
		}

		if (oldModelClassName.equals(ContractEntryClp.class.getName())) {
			return translateInputContractEntry(oldModel);
		}

		if (oldModelClassName.equals(CorpEntryClp.class.getName())) {
			return translateInputCorpEntry(oldModel);
		}

		if (oldModelClassName.equals(CorpGroupClp.class.getName())) {
			return translateInputCorpGroup(oldModel);
		}

		if (oldModelClassName.equals(CorpMembershipRequestClp.class.getName())) {
			return translateInputCorpMembershipRequest(oldModel);
		}

		if (oldModelClassName.equals(CorpProjectClp.class.getName())) {
			return translateInputCorpProject(oldModel);
		}

		if (oldModelClassName.equals(CorpProjectMessageClp.class.getName())) {
			return translateInputCorpProjectMessage(oldModel);
		}

		if (oldModelClassName.equals(CountryAppPricingClp.class.getName())) {
			return translateInputCountryAppPricing(oldModel);
		}

		if (oldModelClassName.equals(CurrencyEntryClp.class.getName())) {
			return translateInputCurrencyEntry(oldModel);
		}

		if (oldModelClassName.equals(DeveloperEntryClp.class.getName())) {
			return translateInputDeveloperEntry(oldModel);
		}

		if (oldModelClassName.equals(ExternalIdMapperClp.class.getName())) {
			return translateInputExternalIdMapper(oldModel);
		}

		if (oldModelClassName.equals(FeedbackEntryClp.class.getName())) {
			return translateInputFeedbackEntry(oldModel);
		}

		if (oldModelClassName.equals(HolidayCalendarClp.class.getName())) {
			return translateInputHolidayCalendar(oldModel);
		}

		if (oldModelClassName.equals(HolidayCalendarRelClp.class.getName())) {
			return translateInputHolidayCalendarRel(oldModel);
		}

		if (oldModelClassName.equals(HolidayEntryClp.class.getName())) {
			return translateInputHolidayEntry(oldModel);
		}

		if (oldModelClassName.equals(LCSSubscriptionEntryClp.class.getName())) {
			return translateInputLCSSubscriptionEntry(oldModel);
		}

		if (oldModelClassName.equals(LicenseEntryClp.class.getName())) {
			return translateInputLicenseEntry(oldModel);
		}

		if (oldModelClassName.equals(LicenseKeyClp.class.getName())) {
			return translateInputLicenseKey(oldModel);
		}

		if (oldModelClassName.equals(LicenseKeySetClp.class.getName())) {
			return translateInputLicenseKeySet(oldModel);
		}

		if (oldModelClassName.equals(MarketingEventClp.class.getName())) {
			return translateInputMarketingEvent(oldModel);
		}

		if (oldModelClassName.equals(OfferingBundleClp.class.getName())) {
			return translateInputOfferingBundle(oldModel);
		}

		if (oldModelClassName.equals(OfferingDefinitionClp.class.getName())) {
			return translateInputOfferingDefinition(oldModel);
		}

		if (oldModelClassName.equals(OfferingEntryClp.class.getName())) {
			return translateInputOfferingEntry(oldModel);
		}

		if (oldModelClassName.equals(OrderEntryClp.class.getName())) {
			return translateInputOrderEntry(oldModel);
		}

		if (oldModelClassName.equals(PartnerEntryClp.class.getName())) {
			return translateInputPartnerEntry(oldModel);
		}

		if (oldModelClassName.equals(PartnerWorkerClp.class.getName())) {
			return translateInputPartnerWorker(oldModel);
		}

		if (oldModelClassName.equals(PortalReleaseClp.class.getName())) {
			return translateInputPortalRelease(oldModel);
		}

		if (oldModelClassName.equals(ProductEntryClp.class.getName())) {
			return translateInputProductEntry(oldModel);
		}

		if (oldModelClassName.equals(SearchFilterClp.class.getName())) {
			return translateInputSearchFilter(oldModel);
		}

		if (oldModelClassName.equals(SecurityPatchClp.class.getName())) {
			return translateInputSecurityPatch(oldModel);
		}

		if (oldModelClassName.equals(SupportLaborClp.class.getName())) {
			return translateInputSupportLabor(oldModel);
		}

		if (oldModelClassName.equals(SupportRegionClp.class.getName())) {
			return translateInputSupportRegion(oldModel);
		}

		if (oldModelClassName.equals(SupportResponseClp.class.getName())) {
			return translateInputSupportResponse(oldModel);
		}

		if (oldModelClassName.equals(SupportTeamClp.class.getName())) {
			return translateInputSupportTeam(oldModel);
		}

		if (oldModelClassName.equals(SupportTeamLanguageClp.class.getName())) {
			return translateInputSupportTeamLanguage(oldModel);
		}

		if (oldModelClassName.equals(SupportWorkerClp.class.getName())) {
			return translateInputSupportWorker(oldModel);
		}

		if (oldModelClassName.equals(
					SupportWorkerAccountTierClp.class.getName())) {
			return translateInputSupportWorkerAccountTier(oldModel);
		}

		if (oldModelClassName.equals(SupportWorkerComponentClp.class.getName())) {
			return translateInputSupportWorkerComponent(oldModel);
		}

		if (oldModelClassName.equals(SupportWorkerSeverityClp.class.getName())) {
			return translateInputSupportWorkerSeverity(oldModel);
		}

		if (oldModelClassName.equals(TicketAttachmentClp.class.getName())) {
			return translateInputTicketAttachment(oldModel);
		}

		if (oldModelClassName.equals(TicketCallClp.class.getName())) {
			return translateInputTicketCall(oldModel);
		}

		if (oldModelClassName.equals(TicketCannedResponseClp.class.getName())) {
			return translateInputTicketCannedResponse(oldModel);
		}

		if (oldModelClassName.equals(TicketCommentClp.class.getName())) {
			return translateInputTicketComment(oldModel);
		}

		if (oldModelClassName.equals(TicketEntryClp.class.getName())) {
			return translateInputTicketEntry(oldModel);
		}

		if (oldModelClassName.equals(TicketFeedbackClp.class.getName())) {
			return translateInputTicketFeedback(oldModel);
		}

		if (oldModelClassName.equals(TicketFlagClp.class.getName())) {
			return translateInputTicketFlag(oldModel);
		}

		if (oldModelClassName.equals(TicketInformationClp.class.getName())) {
			return translateInputTicketInformation(oldModel);
		}

		if (oldModelClassName.equals(TicketLinkClp.class.getName())) {
			return translateInputTicketLink(oldModel);
		}

		if (oldModelClassName.equals(TicketSolutionClp.class.getName())) {
			return translateInputTicketSolution(oldModel);
		}

		if (oldModelClassName.equals(TicketWorkerClp.class.getName())) {
			return translateInputTicketWorker(oldModel);
		}

		if (oldModelClassName.equals(TrainingCertificateClp.class.getName())) {
			return translateInputTrainingCertificate(oldModel);
		}

		if (oldModelClassName.equals(
					TrainingCertificateTemplateClp.class.getName())) {
			return translateInputTrainingCertificateTemplate(oldModel);
		}

		if (oldModelClassName.equals(TrainingCourseClp.class.getName())) {
			return translateInputTrainingCourse(oldModel);
		}

		if (oldModelClassName.equals(TrainingCustomerClp.class.getName())) {
			return translateInputTrainingCustomer(oldModel);
		}

		if (oldModelClassName.equals(TrainingEventClp.class.getName())) {
			return translateInputTrainingEvent(oldModel);
		}

		if (oldModelClassName.equals(TrainingExamClp.class.getName())) {
			return translateInputTrainingExam(oldModel);
		}

		if (oldModelClassName.equals(TrainingExamResultClp.class.getName())) {
			return translateInputTrainingExamResult(oldModel);
		}

		if (oldModelClassName.equals(TrainingExamResultItemClp.class.getName())) {
			return translateInputTrainingExamResultItem(oldModel);
		}

		if (oldModelClassName.equals(
					TrainingExamResultSectionClp.class.getName())) {
			return translateInputTrainingExamResultSection(oldModel);
		}

		if (oldModelClassName.equals(TrainingImportLogClp.class.getName())) {
			return translateInputTrainingImportLog(oldModel);
		}

		if (oldModelClassName.equals(TrainingLinkedUserClp.class.getName())) {
			return translateInputTrainingLinkedUser(oldModel);
		}

		if (oldModelClassName.equals(TrainingLocationClp.class.getName())) {
			return translateInputTrainingLocation(oldModel);
		}

		if (oldModelClassName.equals(TrainingWorkerClp.class.getName())) {
			return translateInputTrainingWorker(oldModel);
		}

		if (oldModelClassName.equals(UserProfileClp.class.getName())) {
			return translateInputUserProfile(oldModel);
		}

		if (oldModelClassName.equals(UserProfileHistoryClp.class.getName())) {
			return translateInputUserProfileHistory(oldModel);
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInputAccountAttachment(BaseModel<?> oldModel) {
		AccountAttachmentClp oldClpModel = (AccountAttachmentClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountAttachmentRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountCall(BaseModel<?> oldModel) {
		AccountCallClp oldClpModel = (AccountCallClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountCallRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountCustomer(BaseModel<?> oldModel) {
		AccountCustomerClp oldClpModel = (AccountCustomerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountCustomerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountEntry(BaseModel<?> oldModel) {
		AccountEntryClp oldClpModel = (AccountEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountEntryLanguage(
		BaseModel<?> oldModel) {
		AccountEntryLanguageClp oldClpModel = (AccountEntryLanguageClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountEntryLanguageRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountEnvironment(BaseModel<?> oldModel) {
		AccountEnvironmentClp oldClpModel = (AccountEnvironmentClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountEnvironmentRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountEnvironmentAttachment(
		BaseModel<?> oldModel) {
		AccountEnvironmentAttachmentClp oldClpModel = (AccountEnvironmentAttachmentClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountEnvironmentAttachmentRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountInformation(BaseModel<?> oldModel) {
		AccountInformationClp oldClpModel = (AccountInformationClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountInformationRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountLink(BaseModel<?> oldModel) {
		AccountLinkClp oldClpModel = (AccountLinkClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountLinkRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountProject(BaseModel<?> oldModel) {
		AccountProjectClp oldClpModel = (AccountProjectClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountProjectRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountWorker(BaseModel<?> oldModel) {
		AccountWorkerClp oldClpModel = (AccountWorkerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountWorkerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAppAudit(BaseModel<?> oldModel) {
		AppAuditClp oldClpModel = (AppAuditClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAppAuditRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAppEntry(BaseModel<?> oldModel) {
		AppEntryClp oldClpModel = (AppEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAppEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAppEntryRel(BaseModel<?> oldModel) {
		AppEntryRelClp oldClpModel = (AppEntryRelClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAppEntryRelRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAppFlag(BaseModel<?> oldModel) {
		AppFlagClp oldClpModel = (AppFlagClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAppFlagRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAppPackage(BaseModel<?> oldModel) {
		AppPackageClp oldClpModel = (AppPackageClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAppPackageRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAppPackagePlugin(BaseModel<?> oldModel) {
		AppPackagePluginClp oldClpModel = (AppPackagePluginClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAppPackagePluginRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAppPricing(BaseModel<?> oldModel) {
		AppPricingClp oldClpModel = (AppPricingClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAppPricingRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAppPricingItem(BaseModel<?> oldModel) {
		AppPricingItemClp oldClpModel = (AppPricingItemClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAppPricingItemRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAppVersion(BaseModel<?> oldModel) {
		AppVersionClp oldClpModel = (AppVersionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAppVersionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetAttachment(BaseModel<?> oldModel) {
		AssetAttachmentClp oldClpModel = (AssetAttachmentClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetAttachmentRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetAudit(BaseModel<?> oldModel) {
		AssetAuditClp oldClpModel = (AssetAuditClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetAuditRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetLicense(BaseModel<?> oldModel) {
		AssetLicenseClp oldClpModel = (AssetLicenseClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetLicenseRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetList(BaseModel<?> oldModel) {
		AssetListClp oldClpModel = (AssetListClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetListRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetListAssetEntry(
		BaseModel<?> oldModel) {
		AssetListAssetEntryClp oldClpModel = (AssetListAssetEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetListAssetEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetReceipt(BaseModel<?> oldModel) {
		AssetReceiptClp oldClpModel = (AssetReceiptClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetReceiptRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetReceiptLicense(
		BaseModel<?> oldModel) {
		AssetReceiptLicenseClp oldClpModel = (AssetReceiptLicenseClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetReceiptLicenseRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetReceiptRedeemToken(
		BaseModel<?> oldModel) {
		AssetReceiptRedeemTokenClp oldClpModel = (AssetReceiptRedeemTokenClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetReceiptRedeemTokenRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetReceiptSupport(
		BaseModel<?> oldModel) {
		AssetReceiptSupportClp oldClpModel = (AssetReceiptSupportClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetReceiptSupportRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetRecommendationEntry(
		BaseModel<?> oldModel) {
		AssetRecommendationEntryClp oldClpModel = (AssetRecommendationEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetRecommendationEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetRecommendationSet(
		BaseModel<?> oldModel) {
		AssetRecommendationSetClp oldClpModel = (AssetRecommendationSetClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetRecommendationSetRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetStatsDay(BaseModel<?> oldModel) {
		AssetStatsDayClp oldClpModel = (AssetStatsDayClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetStatsDayRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetStatsMonth(BaseModel<?> oldModel) {
		AssetStatsMonthClp oldClpModel = (AssetStatsMonthClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetStatsMonthRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAssetStatsWeek(BaseModel<?> oldModel) {
		AssetStatsWeekClp oldClpModel = (AssetStatsWeekClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAssetStatsWeekRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAuditAction(BaseModel<?> oldModel) {
		AuditActionClp oldClpModel = (AuditActionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAuditActionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAuditEntry(BaseModel<?> oldModel) {
		AuditEntryClp oldClpModel = (AuditEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAuditEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputContractAudit(BaseModel<?> oldModel) {
		ContractAuditClp oldClpModel = (ContractAuditClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getContractAuditRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputContractEntry(BaseModel<?> oldModel) {
		ContractEntryClp oldClpModel = (ContractEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getContractEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputCorpEntry(BaseModel<?> oldModel) {
		CorpEntryClp oldClpModel = (CorpEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getCorpEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputCorpGroup(BaseModel<?> oldModel) {
		CorpGroupClp oldClpModel = (CorpGroupClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getCorpGroupRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputCorpMembershipRequest(
		BaseModel<?> oldModel) {
		CorpMembershipRequestClp oldClpModel = (CorpMembershipRequestClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getCorpMembershipRequestRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputCorpProject(BaseModel<?> oldModel) {
		CorpProjectClp oldClpModel = (CorpProjectClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getCorpProjectRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputCorpProjectMessage(BaseModel<?> oldModel) {
		CorpProjectMessageClp oldClpModel = (CorpProjectMessageClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getCorpProjectMessageRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputCountryAppPricing(BaseModel<?> oldModel) {
		CountryAppPricingClp oldClpModel = (CountryAppPricingClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getCountryAppPricingRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputCurrencyEntry(BaseModel<?> oldModel) {
		CurrencyEntryClp oldClpModel = (CurrencyEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getCurrencyEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputDeveloperEntry(BaseModel<?> oldModel) {
		DeveloperEntryClp oldClpModel = (DeveloperEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getDeveloperEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputExternalIdMapper(BaseModel<?> oldModel) {
		ExternalIdMapperClp oldClpModel = (ExternalIdMapperClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getExternalIdMapperRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputFeedbackEntry(BaseModel<?> oldModel) {
		FeedbackEntryClp oldClpModel = (FeedbackEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getFeedbackEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputHolidayCalendar(BaseModel<?> oldModel) {
		HolidayCalendarClp oldClpModel = (HolidayCalendarClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getHolidayCalendarRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputHolidayCalendarRel(BaseModel<?> oldModel) {
		HolidayCalendarRelClp oldClpModel = (HolidayCalendarRelClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getHolidayCalendarRelRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputHolidayEntry(BaseModel<?> oldModel) {
		HolidayEntryClp oldClpModel = (HolidayEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getHolidayEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputLCSSubscriptionEntry(
		BaseModel<?> oldModel) {
		LCSSubscriptionEntryClp oldClpModel = (LCSSubscriptionEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getLCSSubscriptionEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputLicenseEntry(BaseModel<?> oldModel) {
		LicenseEntryClp oldClpModel = (LicenseEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getLicenseEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputLicenseKey(BaseModel<?> oldModel) {
		LicenseKeyClp oldClpModel = (LicenseKeyClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getLicenseKeyRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputLicenseKeySet(BaseModel<?> oldModel) {
		LicenseKeySetClp oldClpModel = (LicenseKeySetClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getLicenseKeySetRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputMarketingEvent(BaseModel<?> oldModel) {
		MarketingEventClp oldClpModel = (MarketingEventClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getMarketingEventRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputOfferingBundle(BaseModel<?> oldModel) {
		OfferingBundleClp oldClpModel = (OfferingBundleClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getOfferingBundleRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputOfferingDefinition(BaseModel<?> oldModel) {
		OfferingDefinitionClp oldClpModel = (OfferingDefinitionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getOfferingDefinitionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputOfferingEntry(BaseModel<?> oldModel) {
		OfferingEntryClp oldClpModel = (OfferingEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getOfferingEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputOrderEntry(BaseModel<?> oldModel) {
		OrderEntryClp oldClpModel = (OrderEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getOrderEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputPartnerEntry(BaseModel<?> oldModel) {
		PartnerEntryClp oldClpModel = (PartnerEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPartnerEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputPartnerWorker(BaseModel<?> oldModel) {
		PartnerWorkerClp oldClpModel = (PartnerWorkerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPartnerWorkerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputPortalRelease(BaseModel<?> oldModel) {
		PortalReleaseClp oldClpModel = (PortalReleaseClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPortalReleaseRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputProductEntry(BaseModel<?> oldModel) {
		ProductEntryClp oldClpModel = (ProductEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getProductEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSearchFilter(BaseModel<?> oldModel) {
		SearchFilterClp oldClpModel = (SearchFilterClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSearchFilterRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSecurityPatch(BaseModel<?> oldModel) {
		SecurityPatchClp oldClpModel = (SecurityPatchClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSecurityPatchRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSupportLabor(BaseModel<?> oldModel) {
		SupportLaborClp oldClpModel = (SupportLaborClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSupportLaborRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSupportRegion(BaseModel<?> oldModel) {
		SupportRegionClp oldClpModel = (SupportRegionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSupportRegionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSupportResponse(BaseModel<?> oldModel) {
		SupportResponseClp oldClpModel = (SupportResponseClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSupportResponseRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSupportTeam(BaseModel<?> oldModel) {
		SupportTeamClp oldClpModel = (SupportTeamClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSupportTeamRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSupportTeamLanguage(
		BaseModel<?> oldModel) {
		SupportTeamLanguageClp oldClpModel = (SupportTeamLanguageClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSupportTeamLanguageRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSupportWorker(BaseModel<?> oldModel) {
		SupportWorkerClp oldClpModel = (SupportWorkerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSupportWorkerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSupportWorkerAccountTier(
		BaseModel<?> oldModel) {
		SupportWorkerAccountTierClp oldClpModel = (SupportWorkerAccountTierClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSupportWorkerAccountTierRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSupportWorkerComponent(
		BaseModel<?> oldModel) {
		SupportWorkerComponentClp oldClpModel = (SupportWorkerComponentClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSupportWorkerComponentRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSupportWorkerSeverity(
		BaseModel<?> oldModel) {
		SupportWorkerSeverityClp oldClpModel = (SupportWorkerSeverityClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSupportWorkerSeverityRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTicketAttachment(BaseModel<?> oldModel) {
		TicketAttachmentClp oldClpModel = (TicketAttachmentClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTicketAttachmentRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTicketCall(BaseModel<?> oldModel) {
		TicketCallClp oldClpModel = (TicketCallClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTicketCallRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTicketCannedResponse(
		BaseModel<?> oldModel) {
		TicketCannedResponseClp oldClpModel = (TicketCannedResponseClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTicketCannedResponseRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTicketComment(BaseModel<?> oldModel) {
		TicketCommentClp oldClpModel = (TicketCommentClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTicketCommentRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTicketEntry(BaseModel<?> oldModel) {
		TicketEntryClp oldClpModel = (TicketEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTicketEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTicketFeedback(BaseModel<?> oldModel) {
		TicketFeedbackClp oldClpModel = (TicketFeedbackClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTicketFeedbackRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTicketFlag(BaseModel<?> oldModel) {
		TicketFlagClp oldClpModel = (TicketFlagClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTicketFlagRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTicketInformation(BaseModel<?> oldModel) {
		TicketInformationClp oldClpModel = (TicketInformationClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTicketInformationRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTicketLink(BaseModel<?> oldModel) {
		TicketLinkClp oldClpModel = (TicketLinkClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTicketLinkRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTicketSolution(BaseModel<?> oldModel) {
		TicketSolutionClp oldClpModel = (TicketSolutionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTicketSolutionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTicketWorker(BaseModel<?> oldModel) {
		TicketWorkerClp oldClpModel = (TicketWorkerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTicketWorkerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingCertificate(
		BaseModel<?> oldModel) {
		TrainingCertificateClp oldClpModel = (TrainingCertificateClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingCertificateRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingCertificateTemplate(
		BaseModel<?> oldModel) {
		TrainingCertificateTemplateClp oldClpModel = (TrainingCertificateTemplateClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingCertificateTemplateRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingCourse(BaseModel<?> oldModel) {
		TrainingCourseClp oldClpModel = (TrainingCourseClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingCourseRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingCustomer(BaseModel<?> oldModel) {
		TrainingCustomerClp oldClpModel = (TrainingCustomerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingCustomerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingEvent(BaseModel<?> oldModel) {
		TrainingEventClp oldClpModel = (TrainingEventClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingEventRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingExam(BaseModel<?> oldModel) {
		TrainingExamClp oldClpModel = (TrainingExamClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingExamRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingExamResult(BaseModel<?> oldModel) {
		TrainingExamResultClp oldClpModel = (TrainingExamResultClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingExamResultRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingExamResultItem(
		BaseModel<?> oldModel) {
		TrainingExamResultItemClp oldClpModel = (TrainingExamResultItemClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingExamResultItemRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingExamResultSection(
		BaseModel<?> oldModel) {
		TrainingExamResultSectionClp oldClpModel = (TrainingExamResultSectionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingExamResultSectionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingImportLog(BaseModel<?> oldModel) {
		TrainingImportLogClp oldClpModel = (TrainingImportLogClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingImportLogRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingLinkedUser(BaseModel<?> oldModel) {
		TrainingLinkedUserClp oldClpModel = (TrainingLinkedUserClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingLinkedUserRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingLocation(BaseModel<?> oldModel) {
		TrainingLocationClp oldClpModel = (TrainingLocationClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingLocationRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTrainingWorker(BaseModel<?> oldModel) {
		TrainingWorkerClp oldClpModel = (TrainingWorkerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTrainingWorkerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputUserProfile(BaseModel<?> oldModel) {
		UserProfileClp oldClpModel = (UserProfileClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getUserProfileRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputUserProfileHistory(BaseModel<?> oldModel) {
		UserProfileHistoryClp oldClpModel = (UserProfileHistoryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getUserProfileHistoryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountAttachmentImpl")) {
			return translateOutputAccountAttachment(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountCallImpl")) {
			return translateOutputAccountCall(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountCustomerImpl")) {
			return translateOutputAccountCustomer(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountEntryImpl")) {
			return translateOutputAccountEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountEntryLanguageImpl")) {
			return translateOutputAccountEntryLanguage(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountEnvironmentImpl")) {
			return translateOutputAccountEnvironment(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountEnvironmentAttachmentImpl")) {
			return translateOutputAccountEnvironmentAttachment(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountInformationImpl")) {
			return translateOutputAccountInformation(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountLinkImpl")) {
			return translateOutputAccountLink(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountProjectImpl")) {
			return translateOutputAccountProject(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountWorkerImpl")) {
			return translateOutputAccountWorker(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.osb.model.impl.AppAuditImpl")) {
			return translateOutputAppAudit(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.osb.model.impl.AppEntryImpl")) {
			return translateOutputAppEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AppEntryRelImpl")) {
			return translateOutputAppEntryRel(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.osb.model.impl.AppFlagImpl")) {
			return translateOutputAppFlag(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AppPackageImpl")) {
			return translateOutputAppPackage(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AppPackagePluginImpl")) {
			return translateOutputAppPackagePlugin(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AppPricingImpl")) {
			return translateOutputAppPricing(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AppPricingItemImpl")) {
			return translateOutputAppPricingItem(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AppVersionImpl")) {
			return translateOutputAppVersion(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetAttachmentImpl")) {
			return translateOutputAssetAttachment(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetAuditImpl")) {
			return translateOutputAssetAudit(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetLicenseImpl")) {
			return translateOutputAssetLicense(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.osb.model.impl.AssetListImpl")) {
			return translateOutputAssetList(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetListAssetEntryImpl")) {
			return translateOutputAssetListAssetEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetReceiptImpl")) {
			return translateOutputAssetReceipt(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetReceiptLicenseImpl")) {
			return translateOutputAssetReceiptLicense(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetReceiptRedeemTokenImpl")) {
			return translateOutputAssetReceiptRedeemToken(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetReceiptSupportImpl")) {
			return translateOutputAssetReceiptSupport(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetRecommendationEntryImpl")) {
			return translateOutputAssetRecommendationEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetRecommendationSetImpl")) {
			return translateOutputAssetRecommendationSet(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetStatsDayImpl")) {
			return translateOutputAssetStatsDay(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetStatsMonthImpl")) {
			return translateOutputAssetStatsMonth(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AssetStatsWeekImpl")) {
			return translateOutputAssetStatsWeek(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AuditActionImpl")) {
			return translateOutputAuditAction(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AuditEntryImpl")) {
			return translateOutputAuditEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.ContractAuditImpl")) {
			return translateOutputContractAudit(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.ContractEntryImpl")) {
			return translateOutputContractEntry(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.osb.model.impl.CorpEntryImpl")) {
			return translateOutputCorpEntry(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.osb.model.impl.CorpGroupImpl")) {
			return translateOutputCorpGroup(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.CorpMembershipRequestImpl")) {
			return translateOutputCorpMembershipRequest(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.CorpProjectImpl")) {
			return translateOutputCorpProject(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.CorpProjectMessageImpl")) {
			return translateOutputCorpProjectMessage(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.CountryAppPricingImpl")) {
			return translateOutputCountryAppPricing(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.CurrencyEntryImpl")) {
			return translateOutputCurrencyEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.DeveloperEntryImpl")) {
			return translateOutputDeveloperEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.ExternalIdMapperImpl")) {
			return translateOutputExternalIdMapper(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.FeedbackEntryImpl")) {
			return translateOutputFeedbackEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.HolidayCalendarImpl")) {
			return translateOutputHolidayCalendar(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.HolidayCalendarRelImpl")) {
			return translateOutputHolidayCalendarRel(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.HolidayEntryImpl")) {
			return translateOutputHolidayEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.LCSSubscriptionEntryImpl")) {
			return translateOutputLCSSubscriptionEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.LicenseEntryImpl")) {
			return translateOutputLicenseEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.LicenseKeyImpl")) {
			return translateOutputLicenseKey(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.LicenseKeySetImpl")) {
			return translateOutputLicenseKeySet(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.MarketingEventImpl")) {
			return translateOutputMarketingEvent(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.OfferingBundleImpl")) {
			return translateOutputOfferingBundle(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.OfferingDefinitionImpl")) {
			return translateOutputOfferingDefinition(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.OfferingEntryImpl")) {
			return translateOutputOfferingEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.OrderEntryImpl")) {
			return translateOutputOrderEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.PartnerEntryImpl")) {
			return translateOutputPartnerEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.PartnerWorkerImpl")) {
			return translateOutputPartnerWorker(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.PortalReleaseImpl")) {
			return translateOutputPortalRelease(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.ProductEntryImpl")) {
			return translateOutputProductEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SearchFilterImpl")) {
			return translateOutputSearchFilter(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SecurityPatchImpl")) {
			return translateOutputSecurityPatch(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportLaborImpl")) {
			return translateOutputSupportLabor(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportRegionImpl")) {
			return translateOutputSupportRegion(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportResponseImpl")) {
			return translateOutputSupportResponse(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportTeamImpl")) {
			return translateOutputSupportTeam(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportTeamLanguageImpl")) {
			return translateOutputSupportTeamLanguage(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportWorkerImpl")) {
			return translateOutputSupportWorker(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportWorkerAccountTierImpl")) {
			return translateOutputSupportWorkerAccountTier(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportWorkerComponentImpl")) {
			return translateOutputSupportWorkerComponent(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportWorkerSeverityImpl")) {
			return translateOutputSupportWorkerSeverity(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketAttachmentImpl")) {
			return translateOutputTicketAttachment(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketCallImpl")) {
			return translateOutputTicketCall(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketCannedResponseImpl")) {
			return translateOutputTicketCannedResponse(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketCommentImpl")) {
			return translateOutputTicketComment(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketEntryImpl")) {
			return translateOutputTicketEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketFeedbackImpl")) {
			return translateOutputTicketFeedback(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketFlagImpl")) {
			return translateOutputTicketFlag(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketInformationImpl")) {
			return translateOutputTicketInformation(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketLinkImpl")) {
			return translateOutputTicketLink(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketSolutionImpl")) {
			return translateOutputTicketSolution(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketWorkerImpl")) {
			return translateOutputTicketWorker(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingCertificateImpl")) {
			return translateOutputTrainingCertificate(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingCertificateTemplateImpl")) {
			return translateOutputTrainingCertificateTemplate(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingCourseImpl")) {
			return translateOutputTrainingCourse(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingCustomerImpl")) {
			return translateOutputTrainingCustomer(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingEventImpl")) {
			return translateOutputTrainingEvent(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingExamImpl")) {
			return translateOutputTrainingExam(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingExamResultImpl")) {
			return translateOutputTrainingExamResult(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingExamResultItemImpl")) {
			return translateOutputTrainingExamResultItem(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingExamResultSectionImpl")) {
			return translateOutputTrainingExamResultSection(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingImportLogImpl")) {
			return translateOutputTrainingImportLog(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingLinkedUserImpl")) {
			return translateOutputTrainingLinkedUser(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingLocationImpl")) {
			return translateOutputTrainingLocation(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TrainingWorkerImpl")) {
			return translateOutputTrainingWorker(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.UserProfileImpl")) {
			return translateOutputUserProfile(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.UserProfileHistoryImpl")) {
			return translateOutputUserProfileHistory(oldModel);
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Throwable translateThrowable(Throwable throwable) {
		if (_useReflectionToTranslateThrowable) {
			try {
				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

				objectOutputStream.writeObject(throwable);

				objectOutputStream.flush();
				objectOutputStream.close();

				UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
						0, unsyncByteArrayOutputStream.size());

				Thread currentThread = Thread.currentThread();

				ClassLoader contextClassLoader = currentThread.getContextClassLoader();

				ObjectInputStream objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
						contextClassLoader);

				throwable = (Throwable)objectInputStream.readObject();

				objectInputStream.close();

				return throwable;
			}
			catch (ClassNotFoundException cnfe) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (SecurityException se) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (Throwable throwable2) {
				_log.error(throwable2, throwable2);

				return throwable2;
			}
		}

		Class<?> clazz = throwable.getClass();

		String className = clazz.getName();

		if (className.equals("com.liferay.osb.AccountAttachmentSizeException")) {
			return new com.liferay.osb.AccountAttachmentSizeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountCallDateException")) {
			return new com.liferay.osb.AccountCallDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountCallLengthException")) {
			return new com.liferay.osb.AccountCallLengthException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountCallSummaryException")) {
			return new com.liferay.osb.AccountCallSummaryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountCallTypeException")) {
			return new com.liferay.osb.AccountCallTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountEntryCodeException")) {
			return new com.liferay.osb.AccountEntryCodeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountEntryCorpProjectException")) {
			return new com.liferay.osb.AccountEntryCorpProjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountEntryIndustryException")) {
			return new com.liferay.osb.AccountEntryIndustryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountEntryLanguageIdException")) {
			return new com.liferay.osb.AccountEntryLanguageIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AccountEntryMaximumCustomersException")) {
			return new com.liferay.osb.AccountEntryMaximumCustomersException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountEntryNameException")) {
			return new com.liferay.osb.AccountEntryNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AccountEntryPartnerEntryException")) {
			return new com.liferay.osb.AccountEntryPartnerEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AccountEntrySupportRegionException")) {
			return new com.liferay.osb.AccountEntrySupportRegionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AccountEnvironmentAttachmentException")) {
			return new com.liferay.osb.AccountEnvironmentAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AccountEnvironmentAttachmentSizeException")) {
			return new com.liferay.osb.AccountEnvironmentAttachmentSizeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountEnvironmentEnvASException")) {
			return new com.liferay.osb.AccountEnvironmentEnvASException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountEnvironmentEnvDBException")) {
			return new com.liferay.osb.AccountEnvironmentEnvDBException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AccountEnvironmentEnvLFRException")) {
			return new com.liferay.osb.AccountEnvironmentEnvLFRException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountEnvironmentEnvOSException")) {
			return new com.liferay.osb.AccountEnvironmentEnvOSException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountEnvironmentNameException")) {
			return new com.liferay.osb.AccountEnvironmentNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountLinkURLException")) {
			return new com.liferay.osb.AccountLinkURLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AccountProjectNameException")) {
			return new com.liferay.osb.AccountProjectNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AccountWorkerPartnerRoleException")) {
			return new com.liferay.osb.AccountWorkerPartnerRoleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AddressCompanyNameException")) {
			return new com.liferay.osb.AddressCompanyNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryCategoryException")) {
			return new com.liferay.osb.AppEntryCategoryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryChangeLogException")) {
			return new com.liferay.osb.AppEntryChangeLogException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryDemoWebsiteException")) {
			return new com.liferay.osb.AppEntryDemoWebsiteException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryDescriptionException")) {
			return new com.liferay.osb.AppEntryDescriptionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AppEntryDocumentationWebsiteException")) {
			return new com.liferay.osb.AppEntryDocumentationWebsiteException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryIconException")) {
			return new com.liferay.osb.AppEntryIconException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryLicenseLifetimeException")) {
			return new com.liferay.osb.AppEntryLicenseLifetimeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryLicenseSupportException")) {
			return new com.liferay.osb.AppEntryLicenseSupportException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryLicenseTypeException")) {
			return new com.liferay.osb.AppEntryLicenseTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryOrderURLException")) {
			return new com.liferay.osb.AppEntryOrderURLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryPACLException")) {
			return new com.liferay.osb.AppEntryPACLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryPurchaseException")) {
			return new com.liferay.osb.AppEntryPurchaseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AppEntryReferenceWebsiteException")) {
			return new com.liferay.osb.AppEntryReferenceWebsiteException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryReleaseTypeException")) {
			return new com.liferay.osb.AppEntryReleaseTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AppEntrySourceCodeWebsiteException")) {
			return new com.liferay.osb.AppEntrySourceCodeWebsiteException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryStatusException")) {
			return new com.liferay.osb.AppEntryStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntrySupportWebsiteException")) {
			return new com.liferay.osb.AppEntrySupportWebsiteException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryTitleException")) {
			return new com.liferay.osb.AppEntryTitleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryVersionException")) {
			return new com.liferay.osb.AppEntryVersionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppEntryWebsiteException")) {
			return new com.liferay.osb.AppEntryWebsiteException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPackageException")) {
			return new com.liferay.osb.AppPackageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPackageLicenseException")) {
			return new com.liferay.osb.AppPackageLicenseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AppPackageMarketplaceSupportException")) {
			return new com.liferay.osb.AppPackageMarketplaceSupportException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPackagePluginBundleException")) {
			return new com.liferay.osb.AppPackagePluginBundleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AppPackagePluginBundleSymbolicNameException")) {
			return new com.liferay.osb.AppPackagePluginBundleSymbolicNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AppPackagePluginDeploymentContextException")) {
			return new com.liferay.osb.AppPackagePluginDeploymentContextException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPackagePluginFileException")) {
			return new com.liferay.osb.AppPackagePluginFileException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AppPackagePluginFileNameException")) {
			return new com.liferay.osb.AppPackagePluginFileNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AppPackagePluginFileNameLengthException")) {
			return new com.liferay.osb.AppPackagePluginFileNameLengthException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPackagePluginNameException")) {
			return new com.liferay.osb.AppPackagePluginNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPackagePluginPACLException")) {
			return new com.liferay.osb.AppPackagePluginPACLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AppPackagePluginPluginPackageException")) {
			return new com.liferay.osb.AppPackagePluginPluginPackageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPackagePluginRelengException")) {
			return new com.liferay.osb.AppPackagePluginRelengException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPackagePluginTypeException")) {
			return new com.liferay.osb.AppPackagePluginTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPackagePluginVersionException")) {
			return new com.liferay.osb.AppPackagePluginVersionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPackagePluginXMLException")) {
			return new com.liferay.osb.AppPackagePluginXMLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPricingCountryException")) {
			return new com.liferay.osb.AppPricingCountryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AppPricingItemCurrencyEntryException")) {
			return new com.liferay.osb.AppPricingItemCurrencyEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPricingItemPriceException")) {
			return new com.liferay.osb.AppPricingItemPriceException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPricingNameException")) {
			return new com.liferay.osb.AppPricingNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPricingPriceException")) {
			return new com.liferay.osb.AppPricingPriceException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppPricingRankException")) {
			return new com.liferay.osb.AppPricingRankException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AppVersionWorkflowException")) {
			return new com.liferay.osb.AppVersionWorkflowException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AssetAttachmentFileNameException")) {
			return new com.liferay.osb.AssetAttachmentFileNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AssetAttachmentSizeException")) {
			return new com.liferay.osb.AssetAttachmentSizeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AssetEntryReviewLengthException")) {
			return new com.liferay.osb.AssetEntryReviewLengthException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AssetLicenseClassNameException")) {
			return new com.liferay.osb.AssetLicenseClassNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AssetLicenseLicenseTypeException")) {
			return new com.liferay.osb.AssetLicenseLicenseTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AssetLicenseLicenseTypeAllotmentException")) {
			return new com.liferay.osb.AssetLicenseLicenseTypeAllotmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AssetLicenseLifetimeException")) {
			return new com.liferay.osb.AssetLicenseLifetimeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AssetLicenseStatusException")) {
			return new com.liferay.osb.AssetLicenseStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AssetLicenseUsageTypeException")) {
			return new com.liferay.osb.AssetLicenseUsageTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AssetReceiptCorpProjectException")) {
			return new com.liferay.osb.AssetReceiptCorpProjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AssetReceiptEmailAddressException")) {
			return new com.liferay.osb.AssetReceiptEmailAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AssetReceiptLegalEntityNameException")) {
			return new com.liferay.osb.AssetReceiptLegalEntityNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AssetReceiptLicenseEndDateException")) {
			return new com.liferay.osb.AssetReceiptLicenseEndDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AssetReceiptLicenseStartDateException")) {
			return new com.liferay.osb.AssetReceiptLicenseStartDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AssetReceiptOwnerClassNameException")) {
			return new com.liferay.osb.AssetReceiptOwnerClassNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AssetReceiptPurchaseException")) {
			return new com.liferay.osb.AssetReceiptPurchaseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.AssetReceiptRedeemTokenEmailAddressException")) {
			return new com.liferay.osb.AssetReceiptRedeemTokenEmailAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AwayMessageDateException")) {
			return new com.liferay.osb.AwayMessageDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.AwayMessageMessageException")) {
			return new com.liferay.osb.AwayMessageMessageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.ContractAuditAcceptanceException")) {
			return new com.liferay.osb.ContractAuditAcceptanceException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.ContractEntryContentException")) {
			return new com.liferay.osb.ContractEntryContentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpEntryAdminException")) {
			return new com.liferay.osb.CorpEntryAdminException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.CorpEntryContactEmailAddressException")) {
			return new com.liferay.osb.CorpEntryContactEmailAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpEntryDescriptionException")) {
			return new com.liferay.osb.CorpEntryDescriptionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpEntryFaxNumberException")) {
			return new com.liferay.osb.CorpEntryFaxNumberException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpEntryLogoException")) {
			return new com.liferay.osb.CorpEntryLogoException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpEntryMembershipException")) {
			return new com.liferay.osb.CorpEntryMembershipException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpEntryNameException")) {
			return new com.liferay.osb.CorpEntryNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.CorpEntryProfileEmailAddressException")) {
			return new com.liferay.osb.CorpEntryProfileEmailAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpEntryRoleException")) {
			return new com.liferay.osb.CorpEntryRoleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpEntryTypeException")) {
			return new com.liferay.osb.CorpEntryTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpGroupDescriptionException")) {
			return new com.liferay.osb.CorpGroupDescriptionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpGroupEmailAddressException")) {
			return new com.liferay.osb.CorpGroupEmailAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpGroupLogoException")) {
			return new com.liferay.osb.CorpGroupLogoException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpGroupNameException")) {
			return new com.liferay.osb.CorpGroupNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpProjectAdminException")) {
			return new com.liferay.osb.CorpProjectAdminException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpProjectMembershipException")) {
			return new com.liferay.osb.CorpProjectMembershipException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.CorpProjectMessageContentException")) {
			return new com.liferay.osb.CorpProjectMessageContentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.CorpProjectMessageSeverityLevelException")) {
			return new com.liferay.osb.CorpProjectMessageSeverityLevelException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpProjectMessageTitleException")) {
			return new com.liferay.osb.CorpProjectMessageTitleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpProjectMessageTypeException")) {
			return new com.liferay.osb.CorpProjectMessageTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpProjectNameException")) {
			return new com.liferay.osb.CorpProjectNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CorpProjectRoleException")) {
			return new com.liferay.osb.CorpProjectRoleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.CorpProjectSalesforceProjectKeyException")) {
			return new com.liferay.osb.CorpProjectSalesforceProjectKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CurrencyEntryCodeException")) {
			return new com.liferay.osb.CurrencyEntryCodeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.CurrencyEntryCountryException")) {
			return new com.liferay.osb.CurrencyEntryCountryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryDomainNameException")) {
			return new com.liferay.osb.DeveloperEntryDomainNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryDomainStatusException")) {
			return new com.liferay.osb.DeveloperEntryDomainStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryDossieraAccountKeyException")) {
			return new com.liferay.osb.DeveloperEntryDossieraAccountKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryEmailAddressException")) {
			return new com.liferay.osb.DeveloperEntryEmailAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryFatcaWithholdingPercentageException")) {
			return new com.liferay.osb.DeveloperEntryFatcaWithholdingPercentageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DeveloperEntryFaxNumberException")) {
			return new com.liferay.osb.DeveloperEntryFaxNumberException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DeveloperEntryNameException")) {
			return new com.liferay.osb.DeveloperEntryNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryPaymentAccountException")) {
			return new com.liferay.osb.DeveloperEntryPaymentAccountException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryPaymentEmailAddressException")) {
			return new com.liferay.osb.DeveloperEntryPaymentEmailAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryPhoneNumberException")) {
			return new com.liferay.osb.DeveloperEntryPhoneNumberException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryProfileDescriptionException")) {
			return new com.liferay.osb.DeveloperEntryProfileDescriptionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryProfileEmailAddressException")) {
			return new com.liferay.osb.DeveloperEntryProfileEmailAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryProfileLogoException")) {
			return new com.liferay.osb.DeveloperEntryProfileLogoException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryProfileWebsiteException")) {
			return new com.liferay.osb.DeveloperEntryProfileWebsiteException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DeveloperEntryStatusException")) {
			return new com.liferay.osb.DeveloperEntryStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntrySubscriptionExpirationDateException")) {
			return new com.liferay.osb.DeveloperEntrySubscriptionExpirationDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntrySubscriptionStatusException")) {
			return new com.liferay.osb.DeveloperEntrySubscriptionStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryTaxDocumentException")) {
			return new com.liferay.osb.DeveloperEntryTaxDocumentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DeveloperEntryTaxDocumentFileNameException")) {
			return new com.liferay.osb.DeveloperEntryTaxDocumentFileNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateAccountAttachmentException")) {
			return new com.liferay.osb.DuplicateAccountAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateAccountEntryException")) {
			return new com.liferay.osb.DuplicateAccountEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateAccountEnvironmentException")) {
			return new com.liferay.osb.DuplicateAccountEnvironmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateAccountEnvironmentAttachmentException")) {
			return new com.liferay.osb.DuplicateAccountEnvironmentAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateAppEntryException")) {
			return new com.liferay.osb.DuplicateAppEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateAppEntryRelException")) {
			return new com.liferay.osb.DuplicateAppEntryRelException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateAppPackageException")) {
			return new com.liferay.osb.DuplicateAppPackageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateAppPackagePluginException")) {
			return new com.liferay.osb.DuplicateAppPackagePluginException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateAppPackagePluginOwnerException")) {
			return new com.liferay.osb.DuplicateAppPackagePluginOwnerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateAppPackageSrcException")) {
			return new com.liferay.osb.DuplicateAppPackageSrcException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateAssetAttachmentFileNameException")) {
			return new com.liferay.osb.DuplicateAssetAttachmentFileNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateAssetReceiptException")) {
			return new com.liferay.osb.DuplicateAssetReceiptException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateCorpProjectException")) {
			return new com.liferay.osb.DuplicateCorpProjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateCurrencyEntryException")) {
			return new com.liferay.osb.DuplicateCurrencyEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateDeveloperEntryException")) {
			return new com.liferay.osb.DuplicateDeveloperEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateDeveloperEntryDossieraAccountKeyException")) {
			return new com.liferay.osb.DuplicateDeveloperEntryDossieraAccountKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateHolidayCalendarException")) {
			return new com.liferay.osb.DuplicateHolidayCalendarException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateHolidayEntryException")) {
			return new com.liferay.osb.DuplicateHolidayEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateHostNameException")) {
			return new com.liferay.osb.DuplicateHostNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateIPAddressException")) {
			return new com.liferay.osb.DuplicateIPAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateMACAddressException")) {
			return new com.liferay.osb.DuplicateMACAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateOfferingBundleException")) {
			return new com.liferay.osb.DuplicateOfferingBundleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateOfferingDefinitionException")) {
			return new com.liferay.osb.DuplicateOfferingDefinitionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicatePartnerEntryCodeException")) {
			return new com.liferay.osb.DuplicatePartnerEntryCodeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicatePartnerEntryDossieraAccountKeyException")) {
			return new com.liferay.osb.DuplicatePartnerEntryDossieraAccountKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateProductEntryException")) {
			return new com.liferay.osb.DuplicateProductEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateSupportLaborException")) {
			return new com.liferay.osb.DuplicateSupportLaborException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateSupportRegionException")) {
			return new com.liferay.osb.DuplicateSupportRegionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateSupportResponseException")) {
			return new com.liferay.osb.DuplicateSupportResponseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateSupportTeamException")) {
			return new com.liferay.osb.DuplicateSupportTeamException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateSupportWorkerException")) {
			return new com.liferay.osb.DuplicateSupportWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateTicketAttachmentException")) {
			return new com.liferay.osb.DuplicateTicketAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateTicketCannedResponseException")) {
			return new com.liferay.osb.DuplicateTicketCannedResponseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.DuplicateTicketFeedbackException")) {
			return new com.liferay.osb.DuplicateTicketFeedbackException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateTrainingExamResultException")) {
			return new com.liferay.osb.DuplicateTrainingExamResultException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.DuplicateTrainingLinkedUserException")) {
			return new com.liferay.osb.DuplicateTrainingLinkedUserException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.ExpiredAppPricingItemException")) {
			return new com.liferay.osb.ExpiredAppPricingItemException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.FileRepositoryNotAvailableException")) {
			return new com.liferay.osb.FileRepositoryNotAvailableException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.HolidayCalendarNameException")) {
			return new com.liferay.osb.HolidayCalendarNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.HolidayEntryDateException")) {
			return new com.liferay.osb.HolidayEntryDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.HolidayEntryHolidayCalendarException")) {
			return new com.liferay.osb.HolidayEntryHolidayCalendarException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.HolidayEntryStartDateLaterThanEndDateException")) {
			return new com.liferay.osb.HolidayEntryStartDateLaterThanEndDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseEntryNameException")) {
			return new com.liferay.osb.LicenseEntryNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.LicenseEntryPortalVersionException")) {
			return new com.liferay.osb.LicenseEntryPortalVersionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyActiveException")) {
			return new com.liferay.osb.LicenseKeyActiveException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyDescriptionException")) {
			return new com.liferay.osb.LicenseKeyDescriptionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyExportException")) {
			return new com.liferay.osb.LicenseKeyExportException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyHostNameException")) {
			return new com.liferay.osb.LicenseKeyHostNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyIPAddressException")) {
			return new com.liferay.osb.LicenseKeyIPAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyMACAddressException")) {
			return new com.liferay.osb.LicenseKeyMACAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyMaxServersException")) {
			return new com.liferay.osb.LicenseKeyMaxServersException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyOwnerException")) {
			return new com.liferay.osb.LicenseKeyOwnerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyProductEntryException")) {
			return new com.liferay.osb.LicenseKeyProductEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.LicenseKeyProductVersionException")) {
			return new com.liferay.osb.LicenseKeyProductVersionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyRegistrationException")) {
			return new com.liferay.osb.LicenseKeyRegistrationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyRenewException")) {
			return new com.liferay.osb.LicenseKeyRenewException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyServerIdException")) {
			return new com.liferay.osb.LicenseKeyServerIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyServerInfoException")) {
			return new com.liferay.osb.LicenseKeyServerInfoException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeySetNameException")) {
			return new com.liferay.osb.LicenseKeySetNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeySingleUseException")) {
			return new com.liferay.osb.LicenseKeySingleUseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.LicenseKeyVersionException")) {
			return new com.liferay.osb.LicenseKeyVersionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.MarketingEventEndDateException")) {
			return new com.liferay.osb.MarketingEventEndDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.MarketingEventHostedByException")) {
			return new com.liferay.osb.MarketingEventHostedByException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.MarketingEventHostedByURLException")) {
			return new com.liferay.osb.MarketingEventHostedByURLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.MarketingEventRegistrationURLException")) {
			return new com.liferay.osb.MarketingEventRegistrationURLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.MarketingEventStartDateException")) {
			return new com.liferay.osb.MarketingEventStartDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.MarketingEventTitleException")) {
			return new com.liferay.osb.MarketingEventTitleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.MarketingEventTitleURLException")) {
			return new com.liferay.osb.MarketingEventTitleURLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.MarketingEventVideoTitleException")) {
			return new com.liferay.osb.MarketingEventVideoTitleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.MaximumAssetLicenseException")) {
			return new com.liferay.osb.MaximumAssetLicenseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.MaximumDraftTicketCommentException")) {
			return new com.liferay.osb.MaximumDraftTicketCommentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.MaximumLicenseKeyException")) {
			return new com.liferay.osb.MaximumLicenseKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.MaximumTicketEntryException")) {
			return new com.liferay.osb.MaximumTicketEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.MinimumAssetLicenseException")) {
			return new com.liferay.osb.MinimumAssetLicenseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.OSBPrincipalException")) {
			return new com.liferay.osb.OSBPrincipalException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.OfferingBundleNameException")) {
			return new com.liferay.osb.OfferingBundleNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.OfferingEntryQuantityException")) {
			return new com.liferay.osb.OfferingEntryQuantityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.OfferingEntrySizingException")) {
			return new com.liferay.osb.OfferingEntrySizingException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.OfferingEntryStatusException")) {
			return new com.liferay.osb.OfferingEntryStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.OfferingEntrySupportExpiredException")) {
			return new com.liferay.osb.OfferingEntrySupportExpiredException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.OrderEntryActualStartDateException")) {
			return new com.liferay.osb.OrderEntryActualStartDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.OrderEntryStartDateException")) {
			return new com.liferay.osb.OrderEntryStartDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.PartnerEntryCodeException")) {
			return new com.liferay.osb.PartnerEntryCodeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.PartnerEntryParentPartnerEntryException")) {
			return new com.liferay.osb.PartnerEntryParentPartnerEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.PortalReleaseBuildNumberException")) {
			return new com.liferay.osb.PortalReleaseBuildNumberException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.PortalReleaseNameException")) {
			return new com.liferay.osb.PortalReleaseNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.ProductEntryEnvironmentException")) {
			return new com.liferay.osb.ProductEntryEnvironmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.ProductEntryNameException")) {
			return new com.liferay.osb.ProductEntryNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredAccountEntryException")) {
			return new com.liferay.osb.RequiredAccountEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredAppManualQADirException")) {
			return new com.liferay.osb.RequiredAppManualQADirException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredAppPackageException")) {
			return new com.liferay.osb.RequiredAppPackageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.RequiredAppPackagePluginException")) {
			return new com.liferay.osb.RequiredAppPackagePluginException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredAppPricingItemException")) {
			return new com.liferay.osb.RequiredAppPricingItemException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredAssetLicenseException")) {
			return new com.liferay.osb.RequiredAssetLicenseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredCorpProjectException")) {
			return new com.liferay.osb.RequiredCorpProjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredCotermException")) {
			return new com.liferay.osb.RequiredCotermException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.RequiredEndUserEmailAddressException")) {
			return new com.liferay.osb.RequiredEndUserEmailAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.RequiredEndUserFirstNameException")) {
			return new com.liferay.osb.RequiredEndUserFirstNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredEndUserLastNameException")) {
			return new com.liferay.osb.RequiredEndUserLastNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.RequiredEndUserPhoneNumberException")) {
			return new com.liferay.osb.RequiredEndUserPhoneNumberException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredLicenseEntryException")) {
			return new com.liferay.osb.RequiredLicenseEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.RequiredLiferayDeploymentContextException")) {
			return new com.liferay.osb.RequiredLiferayDeploymentContextException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.RequiredOfferingDefinitionException")) {
			return new com.liferay.osb.RequiredOfferingDefinitionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredOfferingEntryException")) {
			return new com.liferay.osb.RequiredOfferingEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredPartnerEntryException")) {
			return new com.liferay.osb.RequiredPartnerEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredPartnerWorkerException")) {
			return new com.liferay.osb.RequiredPartnerWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredProductEntryException")) {
			return new com.liferay.osb.RequiredProductEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredQuantityException")) {
			return new com.liferay.osb.RequiredQuantityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredRenewalException")) {
			return new com.liferay.osb.RequiredRenewalException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.RequiredResourcesImporterException")) {
			return new com.liferay.osb.RequiredResourcesImporterException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredSupportRegionException")) {
			return new com.liferay.osb.RequiredSupportRegionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredSupportResponseException")) {
			return new com.liferay.osb.RequiredSupportResponseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredSupportTeamException")) {
			return new com.liferay.osb.RequiredSupportTeamException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredTaxException")) {
			return new com.liferay.osb.RequiredTaxException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredTicketWorkerException")) {
			return new com.liferay.osb.RequiredTicketWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.RequiredTrainingCertificateTemplateException")) {
			return new com.liferay.osb.RequiredTrainingCertificateTemplateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredTrainingCourseException")) {
			return new com.liferay.osb.RequiredTrainingCourseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RequiredTrainingEventException")) {
			return new com.liferay.osb.RequiredTrainingEventException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.RequiredTrainingExamResultEmailAddressException")) {
			return new com.liferay.osb.RequiredTrainingExamResultEmailAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.RequiredTrainingLocationException")) {
			return new com.liferay.osb.RequiredTrainingLocationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RestrictedCountryException")) {
			return new com.liferay.osb.RestrictedCountryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.RestrictedLiferayDeploymentContextException")) {
			return new com.liferay.osb.RestrictedLiferayDeploymentContextException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.RestrictedLiferayEmailAddressException")) {
			return new com.liferay.osb.RestrictedLiferayEmailAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.RestrictedReviewException")) {
			return new com.liferay.osb.RestrictedReviewException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.SalesforceAddressException")) {
			return new com.liferay.osb.SalesforceAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.SearchFilterNameException")) {
			return new com.liferay.osb.SearchFilterNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.SecurityPatchEnvLFRException")) {
			return new com.liferay.osb.SecurityPatchEnvLFRException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.SupportLaborHourException")) {
			return new com.liferay.osb.SupportLaborHourException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.SupportLaborNameException")) {
			return new com.liferay.osb.SupportLaborNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.SupportRegionNameException")) {
			return new com.liferay.osb.SupportRegionNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.SupportResponseNameException")) {
			return new com.liferay.osb.SupportResponseNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.SupportResponseSupportLevelException")) {
			return new com.liferay.osb.SupportResponseSupportLevelException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.SupportTeamContainsNoSupportWorkersException")) {
			return new com.liferay.osb.SupportTeamContainsNoSupportWorkersException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.SupportTeamLocationException")) {
			return new com.liferay.osb.SupportTeamLocationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.SupportTeamNameException")) {
			return new com.liferay.osb.SupportTeamNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.SupportTeamSupportLaborException")) {
			return new com.liferay.osb.SupportTeamSupportLaborException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.SupportWorkerMaxWorkException")) {
			return new com.liferay.osb.SupportWorkerMaxWorkException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TicketAttachmentAvailableFileRepositoryIdsException")) {
			return new com.liferay.osb.TicketAttachmentAvailableFileRepositoryIdsException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TicketAttachmentVisibilityException")) {
			return new com.liferay.osb.TicketAttachmentVisibilityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketCallCustomerNameException")) {
			return new com.liferay.osb.TicketCallCustomerNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketCallDateException")) {
			return new com.liferay.osb.TicketCallDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketCallLengthException")) {
			return new com.liferay.osb.TicketCallLengthException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketCallTypeException")) {
			return new com.liferay.osb.TicketCallTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TicketCannedResponseContentException")) {
			return new com.liferay.osb.TicketCannedResponseContentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TicketCannedResponseNameException")) {
			return new com.liferay.osb.TicketCannedResponseNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketCommentBodyException")) {
			return new com.liferay.osb.TicketCommentBodyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TicketCommentPendingTypeException")) {
			return new com.liferay.osb.TicketCommentPendingTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketCommentVisibilityException")) {
			return new com.liferay.osb.TicketCommentVisibilityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntryAssigneeException")) {
			return new com.liferay.osb.TicketEntryAssigneeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TicketEntryAttachmentSizeException")) {
			return new com.liferay.osb.TicketEntryAttachmentSizeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntryClosedException")) {
			return new com.liferay.osb.TicketEntryClosedException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntryComponentException")) {
			return new com.liferay.osb.TicketEntryComponentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntryDescriptionException")) {
			return new com.liferay.osb.TicketEntryDescriptionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntryDueDateException")) {
			return new com.liferay.osb.TicketEntryDueDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntryForwardingException")) {
			return new com.liferay.osb.TicketEntryForwardingException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TicketEntryForwardingFieldException")) {
			return new com.liferay.osb.TicketEntryForwardingFieldException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntryLanguageIdException")) {
			return new com.liferay.osb.TicketEntryLanguageIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntryResolutionException")) {
			return new com.liferay.osb.TicketEntryResolutionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntrySeverityException")) {
			return new com.liferay.osb.TicketEntrySeverityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntryStatusException")) {
			return new com.liferay.osb.TicketEntryStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntrySubcomponentException")) {
			return new com.liferay.osb.TicketEntrySubcomponentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntrySubjectException")) {
			return new com.liferay.osb.TicketEntrySubjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntrySystemStatusException")) {
			return new com.liferay.osb.TicketEntrySystemStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketEntryWeightException")) {
			return new com.liferay.osb.TicketEntryWeightException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketFeedbackAnswerException")) {
			return new com.liferay.osb.TicketFeedbackAnswerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketFeedbackRatingException")) {
			return new com.liferay.osb.TicketFeedbackRatingException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketFeedbackSatisfiedException")) {
			return new com.liferay.osb.TicketFeedbackSatisfiedException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketFlagTypeException")) {
			return new com.liferay.osb.TicketFlagTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketInformationException")) {
			return new com.liferay.osb.TicketInformationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketLinkTypeException")) {
			return new com.liferay.osb.TicketLinkTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketLinkURLException")) {
			return new com.liferay.osb.TicketLinkURLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketLinkVisibilityException")) {
			return new com.liferay.osb.TicketLinkVisibilityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketSolutionBodyException")) {
			return new com.liferay.osb.TicketSolutionBodyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketSolutionStatusException")) {
			return new com.liferay.osb.TicketSolutionStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TicketSolutionStatusMessageException")) {
			return new com.liferay.osb.TicketSolutionStatusMessageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TicketSolutionSummaryException")) {
			return new com.liferay.osb.TicketSolutionSummaryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TrainingCertificateSurveyStatusException")) {
			return new com.liferay.osb.TrainingCertificateSurveyStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TrainingCertificateTemplateNameException")) {
			return new com.liferay.osb.TrainingCertificateTemplateNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TrainingCourseNameException")) {
			return new com.liferay.osb.TrainingCourseNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TrainingCustomerCertificateException")) {
			return new com.liferay.osb.TrainingCustomerCertificateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TrainingCustomerStatusException")) {
			return new com.liferay.osb.TrainingCustomerStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TrainingEventEndDateException")) {
			return new com.liferay.osb.TrainingEventEndDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TrainingEventStartDateException")) {
			return new com.liferay.osb.TrainingEventStartDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TrainingExamResultStatusException")) {
			return new com.liferay.osb.TrainingExamResultStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.TrainingLinkedUserPrimaryUserIdException")) {
			return new com.liferay.osb.TrainingLinkedUserPrimaryUserIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TrainingLocationNameException")) {
			return new com.liferay.osb.TrainingLocationNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.TrialLicenseException")) {
			return new com.liferay.osb.TrialLicenseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.UnverifiedUserException")) {
			return new com.liferay.osb.UnverifiedUserException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.UserProfileFirstNameException")) {
			return new com.liferay.osb.UserProfileFirstNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.UserProfileHistoryFirstNameException")) {
			return new com.liferay.osb.UserProfileHistoryFirstNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.UserProfileHistoryLastNameException")) {
			return new com.liferay.osb.UserProfileHistoryLastNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.UserProfileLastNameException")) {
			return new com.liferay.osb.UserProfileLastNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAccountAttachmentException")) {
			return new com.liferay.osb.NoSuchAccountAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAccountCallException")) {
			return new com.liferay.osb.NoSuchAccountCallException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAccountCustomerException")) {
			return new com.liferay.osb.NoSuchAccountCustomerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAccountEntryException")) {
			return new com.liferay.osb.NoSuchAccountEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchAccountEntryLanguageException")) {
			return new com.liferay.osb.NoSuchAccountEntryLanguageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchAccountEnvironmentException")) {
			return new com.liferay.osb.NoSuchAccountEnvironmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchAccountEnvironmentAttachmentException")) {
			return new com.liferay.osb.NoSuchAccountEnvironmentAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchAccountInformationException")) {
			return new com.liferay.osb.NoSuchAccountInformationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAccountLinkException")) {
			return new com.liferay.osb.NoSuchAccountLinkException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAccountProjectException")) {
			return new com.liferay.osb.NoSuchAccountProjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAccountWorkerException")) {
			return new com.liferay.osb.NoSuchAccountWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAppAuditException")) {
			return new com.liferay.osb.NoSuchAppAuditException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAppEntryException")) {
			return new com.liferay.osb.NoSuchAppEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAppEntryRelException")) {
			return new com.liferay.osb.NoSuchAppEntryRelException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAppFlagException")) {
			return new com.liferay.osb.NoSuchAppFlagException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAppPackageException")) {
			return new com.liferay.osb.NoSuchAppPackageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAppPackagePluginException")) {
			return new com.liferay.osb.NoSuchAppPackagePluginException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAppPricingException")) {
			return new com.liferay.osb.NoSuchAppPricingException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAppPricingItemException")) {
			return new com.liferay.osb.NoSuchAppPricingItemException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAppVersionException")) {
			return new com.liferay.osb.NoSuchAppVersionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAssetAttachmentException")) {
			return new com.liferay.osb.NoSuchAssetAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAssetAuditException")) {
			return new com.liferay.osb.NoSuchAssetAuditException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAssetLicenseException")) {
			return new com.liferay.osb.NoSuchAssetLicenseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAssetListException")) {
			return new com.liferay.osb.NoSuchAssetListException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchAssetListAssetEntryException")) {
			return new com.liferay.osb.NoSuchAssetListAssetEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAssetReceiptException")) {
			return new com.liferay.osb.NoSuchAssetReceiptException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchAssetReceiptLicenseException")) {
			return new com.liferay.osb.NoSuchAssetReceiptLicenseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchAssetReceiptRedeemTokenException")) {
			return new com.liferay.osb.NoSuchAssetReceiptRedeemTokenException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchAssetReceiptSupportException")) {
			return new com.liferay.osb.NoSuchAssetReceiptSupportException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchAssetRecommendationEntryException")) {
			return new com.liferay.osb.NoSuchAssetRecommendationEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchAssetRecommendationSetException")) {
			return new com.liferay.osb.NoSuchAssetRecommendationSetException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAssetStatsDayException")) {
			return new com.liferay.osb.NoSuchAssetStatsDayException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAssetStatsMonthException")) {
			return new com.liferay.osb.NoSuchAssetStatsMonthException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAssetStatsWeekException")) {
			return new com.liferay.osb.NoSuchAssetStatsWeekException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAuditActionException")) {
			return new com.liferay.osb.NoSuchAuditActionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchAuditEntryException")) {
			return new com.liferay.osb.NoSuchAuditEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchContractAuditException")) {
			return new com.liferay.osb.NoSuchContractAuditException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchContractEntryException")) {
			return new com.liferay.osb.NoSuchContractEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchCorpEntryException")) {
			return new com.liferay.osb.NoSuchCorpEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchCorpGroupException")) {
			return new com.liferay.osb.NoSuchCorpGroupException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchCorpMembershipRequestException")) {
			return new com.liferay.osb.NoSuchCorpMembershipRequestException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchCorpProjectException")) {
			return new com.liferay.osb.NoSuchCorpProjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchCorpProjectMessageException")) {
			return new com.liferay.osb.NoSuchCorpProjectMessageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchCountryAppPricingException")) {
			return new com.liferay.osb.NoSuchCountryAppPricingException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchCurrencyEntryException")) {
			return new com.liferay.osb.NoSuchCurrencyEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchDeveloperEntryException")) {
			return new com.liferay.osb.NoSuchDeveloperEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchExternalIdMapperException")) {
			return new com.liferay.osb.NoSuchExternalIdMapperException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchFeedbackEntryException")) {
			return new com.liferay.osb.NoSuchFeedbackEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchHolidayCalendarException")) {
			return new com.liferay.osb.NoSuchHolidayCalendarException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchHolidayCalendarRelException")) {
			return new com.liferay.osb.NoSuchHolidayCalendarRelException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchHolidayEntryException")) {
			return new com.liferay.osb.NoSuchHolidayEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchLCSSubscriptionEntryException")) {
			return new com.liferay.osb.NoSuchLCSSubscriptionEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchLicenseEntryException")) {
			return new com.liferay.osb.NoSuchLicenseEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchLicenseKeyException")) {
			return new com.liferay.osb.NoSuchLicenseKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchLicenseKeySetException")) {
			return new com.liferay.osb.NoSuchLicenseKeySetException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchMarketingEventException")) {
			return new com.liferay.osb.NoSuchMarketingEventException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchOfferingBundleException")) {
			return new com.liferay.osb.NoSuchOfferingBundleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchOfferingDefinitionException")) {
			return new com.liferay.osb.NoSuchOfferingDefinitionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchOfferingEntryException")) {
			return new com.liferay.osb.NoSuchOfferingEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchOrderEntryException")) {
			return new com.liferay.osb.NoSuchOrderEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchPartnerEntryException")) {
			return new com.liferay.osb.NoSuchPartnerEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchPartnerWorkerException")) {
			return new com.liferay.osb.NoSuchPartnerWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchPortalReleaseException")) {
			return new com.liferay.osb.NoSuchPortalReleaseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchProductEntryException")) {
			return new com.liferay.osb.NoSuchProductEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchSearchFilterException")) {
			return new com.liferay.osb.NoSuchSearchFilterException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchSecurityPatchException")) {
			return new com.liferay.osb.NoSuchSecurityPatchException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchSupportLaborException")) {
			return new com.liferay.osb.NoSuchSupportLaborException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchSupportRegionException")) {
			return new com.liferay.osb.NoSuchSupportRegionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchSupportResponseException")) {
			return new com.liferay.osb.NoSuchSupportResponseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchSupportTeamException")) {
			return new com.liferay.osb.NoSuchSupportTeamException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchSupportTeamLanguageException")) {
			return new com.liferay.osb.NoSuchSupportTeamLanguageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchSupportWorkerException")) {
			return new com.liferay.osb.NoSuchSupportWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchSupportWorkerAccountTierException")) {
			return new com.liferay.osb.NoSuchSupportWorkerAccountTierException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchSupportWorkerComponentException")) {
			return new com.liferay.osb.NoSuchSupportWorkerComponentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchSupportWorkerSeverityException")) {
			return new com.liferay.osb.NoSuchSupportWorkerSeverityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTicketAttachmentException")) {
			return new com.liferay.osb.NoSuchTicketAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTicketCallException")) {
			return new com.liferay.osb.NoSuchTicketCallException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchTicketCannedResponseException")) {
			return new com.liferay.osb.NoSuchTicketCannedResponseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTicketCommentException")) {
			return new com.liferay.osb.NoSuchTicketCommentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTicketEntryException")) {
			return new com.liferay.osb.NoSuchTicketEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTicketFeedbackException")) {
			return new com.liferay.osb.NoSuchTicketFeedbackException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTicketFlagException")) {
			return new com.liferay.osb.NoSuchTicketFlagException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTicketInformationException")) {
			return new com.liferay.osb.NoSuchTicketInformationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTicketLinkException")) {
			return new com.liferay.osb.NoSuchTicketLinkException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTicketSolutionException")) {
			return new com.liferay.osb.NoSuchTicketSolutionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTicketWorkerException")) {
			return new com.liferay.osb.NoSuchTicketWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchTrainingCertificateException")) {
			return new com.liferay.osb.NoSuchTrainingCertificateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchTrainingCertificateTemplateException")) {
			return new com.liferay.osb.NoSuchTrainingCertificateTemplateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTrainingCourseException")) {
			return new com.liferay.osb.NoSuchTrainingCourseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTrainingCustomerException")) {
			return new com.liferay.osb.NoSuchTrainingCustomerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTrainingEventException")) {
			return new com.liferay.osb.NoSuchTrainingEventException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTrainingExamException")) {
			return new com.liferay.osb.NoSuchTrainingExamException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchTrainingExamResultException")) {
			return new com.liferay.osb.NoSuchTrainingExamResultException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchTrainingExamResultItemException")) {
			return new com.liferay.osb.NoSuchTrainingExamResultItemException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchTrainingExamResultSectionException")) {
			return new com.liferay.osb.NoSuchTrainingExamResultSectionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTrainingImportLogException")) {
			return new com.liferay.osb.NoSuchTrainingImportLogException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchTrainingLinkedUserException")) {
			return new com.liferay.osb.NoSuchTrainingLinkedUserException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTrainingLocationException")) {
			return new com.liferay.osb.NoSuchTrainingLocationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchTrainingWorkerException")) {
			return new com.liferay.osb.NoSuchTrainingWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.NoSuchUserProfileException")) {
			return new com.liferay.osb.NoSuchUserProfileException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.NoSuchUserProfileHistoryException")) {
			return new com.liferay.osb.NoSuchUserProfileHistoryException(throwable.getMessage(),
				throwable.getCause());
		}

		return throwable;
	}

	public static Object translateOutputAccountAttachment(BaseModel<?> oldModel) {
		AccountAttachmentClp newModel = new AccountAttachmentClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountAttachmentRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountCall(BaseModel<?> oldModel) {
		AccountCallClp newModel = new AccountCallClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountCallRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountCustomer(BaseModel<?> oldModel) {
		AccountCustomerClp newModel = new AccountCustomerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountCustomerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountEntry(BaseModel<?> oldModel) {
		AccountEntryClp newModel = new AccountEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountEntryLanguage(
		BaseModel<?> oldModel) {
		AccountEntryLanguageClp newModel = new AccountEntryLanguageClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountEntryLanguageRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountEnvironment(
		BaseModel<?> oldModel) {
		AccountEnvironmentClp newModel = new AccountEnvironmentClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountEnvironmentRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountEnvironmentAttachment(
		BaseModel<?> oldModel) {
		AccountEnvironmentAttachmentClp newModel = new AccountEnvironmentAttachmentClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountEnvironmentAttachmentRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountInformation(
		BaseModel<?> oldModel) {
		AccountInformationClp newModel = new AccountInformationClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountInformationRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountLink(BaseModel<?> oldModel) {
		AccountLinkClp newModel = new AccountLinkClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountLinkRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountProject(BaseModel<?> oldModel) {
		AccountProjectClp newModel = new AccountProjectClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountProjectRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountWorker(BaseModel<?> oldModel) {
		AccountWorkerClp newModel = new AccountWorkerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountWorkerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAppAudit(BaseModel<?> oldModel) {
		AppAuditClp newModel = new AppAuditClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAppAuditRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAppEntry(BaseModel<?> oldModel) {
		AppEntryClp newModel = new AppEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAppEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAppEntryRel(BaseModel<?> oldModel) {
		AppEntryRelClp newModel = new AppEntryRelClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAppEntryRelRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAppFlag(BaseModel<?> oldModel) {
		AppFlagClp newModel = new AppFlagClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAppFlagRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAppPackage(BaseModel<?> oldModel) {
		AppPackageClp newModel = new AppPackageClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAppPackageRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAppPackagePlugin(BaseModel<?> oldModel) {
		AppPackagePluginClp newModel = new AppPackagePluginClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAppPackagePluginRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAppPricing(BaseModel<?> oldModel) {
		AppPricingClp newModel = new AppPricingClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAppPricingRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAppPricingItem(BaseModel<?> oldModel) {
		AppPricingItemClp newModel = new AppPricingItemClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAppPricingItemRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAppVersion(BaseModel<?> oldModel) {
		AppVersionClp newModel = new AppVersionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAppVersionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetAttachment(BaseModel<?> oldModel) {
		AssetAttachmentClp newModel = new AssetAttachmentClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetAttachmentRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetAudit(BaseModel<?> oldModel) {
		AssetAuditClp newModel = new AssetAuditClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetAuditRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetLicense(BaseModel<?> oldModel) {
		AssetLicenseClp newModel = new AssetLicenseClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetLicenseRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetList(BaseModel<?> oldModel) {
		AssetListClp newModel = new AssetListClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetListRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetListAssetEntry(
		BaseModel<?> oldModel) {
		AssetListAssetEntryClp newModel = new AssetListAssetEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetListAssetEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetReceipt(BaseModel<?> oldModel) {
		AssetReceiptClp newModel = new AssetReceiptClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetReceiptRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetReceiptLicense(
		BaseModel<?> oldModel) {
		AssetReceiptLicenseClp newModel = new AssetReceiptLicenseClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetReceiptLicenseRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetReceiptRedeemToken(
		BaseModel<?> oldModel) {
		AssetReceiptRedeemTokenClp newModel = new AssetReceiptRedeemTokenClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetReceiptRedeemTokenRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetReceiptSupport(
		BaseModel<?> oldModel) {
		AssetReceiptSupportClp newModel = new AssetReceiptSupportClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetReceiptSupportRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetRecommendationEntry(
		BaseModel<?> oldModel) {
		AssetRecommendationEntryClp newModel = new AssetRecommendationEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetRecommendationEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetRecommendationSet(
		BaseModel<?> oldModel) {
		AssetRecommendationSetClp newModel = new AssetRecommendationSetClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetRecommendationSetRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetStatsDay(BaseModel<?> oldModel) {
		AssetStatsDayClp newModel = new AssetStatsDayClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetStatsDayRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetStatsMonth(BaseModel<?> oldModel) {
		AssetStatsMonthClp newModel = new AssetStatsMonthClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetStatsMonthRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAssetStatsWeek(BaseModel<?> oldModel) {
		AssetStatsWeekClp newModel = new AssetStatsWeekClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAssetStatsWeekRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAuditAction(BaseModel<?> oldModel) {
		AuditActionClp newModel = new AuditActionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAuditActionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAuditEntry(BaseModel<?> oldModel) {
		AuditEntryClp newModel = new AuditEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAuditEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputContractAudit(BaseModel<?> oldModel) {
		ContractAuditClp newModel = new ContractAuditClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setContractAuditRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputContractEntry(BaseModel<?> oldModel) {
		ContractEntryClp newModel = new ContractEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setContractEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputCorpEntry(BaseModel<?> oldModel) {
		CorpEntryClp newModel = new CorpEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setCorpEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputCorpGroup(BaseModel<?> oldModel) {
		CorpGroupClp newModel = new CorpGroupClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setCorpGroupRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputCorpMembershipRequest(
		BaseModel<?> oldModel) {
		CorpMembershipRequestClp newModel = new CorpMembershipRequestClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setCorpMembershipRequestRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputCorpProject(BaseModel<?> oldModel) {
		CorpProjectClp newModel = new CorpProjectClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setCorpProjectRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputCorpProjectMessage(
		BaseModel<?> oldModel) {
		CorpProjectMessageClp newModel = new CorpProjectMessageClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setCorpProjectMessageRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputCountryAppPricing(BaseModel<?> oldModel) {
		CountryAppPricingClp newModel = new CountryAppPricingClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setCountryAppPricingRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputCurrencyEntry(BaseModel<?> oldModel) {
		CurrencyEntryClp newModel = new CurrencyEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setCurrencyEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputDeveloperEntry(BaseModel<?> oldModel) {
		DeveloperEntryClp newModel = new DeveloperEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setDeveloperEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputExternalIdMapper(BaseModel<?> oldModel) {
		ExternalIdMapperClp newModel = new ExternalIdMapperClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setExternalIdMapperRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputFeedbackEntry(BaseModel<?> oldModel) {
		FeedbackEntryClp newModel = new FeedbackEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setFeedbackEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputHolidayCalendar(BaseModel<?> oldModel) {
		HolidayCalendarClp newModel = new HolidayCalendarClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setHolidayCalendarRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputHolidayCalendarRel(
		BaseModel<?> oldModel) {
		HolidayCalendarRelClp newModel = new HolidayCalendarRelClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setHolidayCalendarRelRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputHolidayEntry(BaseModel<?> oldModel) {
		HolidayEntryClp newModel = new HolidayEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setHolidayEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputLCSSubscriptionEntry(
		BaseModel<?> oldModel) {
		LCSSubscriptionEntryClp newModel = new LCSSubscriptionEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setLCSSubscriptionEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputLicenseEntry(BaseModel<?> oldModel) {
		LicenseEntryClp newModel = new LicenseEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setLicenseEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputLicenseKey(BaseModel<?> oldModel) {
		LicenseKeyClp newModel = new LicenseKeyClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setLicenseKeyRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputLicenseKeySet(BaseModel<?> oldModel) {
		LicenseKeySetClp newModel = new LicenseKeySetClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setLicenseKeySetRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputMarketingEvent(BaseModel<?> oldModel) {
		MarketingEventClp newModel = new MarketingEventClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setMarketingEventRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputOfferingBundle(BaseModel<?> oldModel) {
		OfferingBundleClp newModel = new OfferingBundleClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setOfferingBundleRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputOfferingDefinition(
		BaseModel<?> oldModel) {
		OfferingDefinitionClp newModel = new OfferingDefinitionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setOfferingDefinitionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputOfferingEntry(BaseModel<?> oldModel) {
		OfferingEntryClp newModel = new OfferingEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setOfferingEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputOrderEntry(BaseModel<?> oldModel) {
		OrderEntryClp newModel = new OrderEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setOrderEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputPartnerEntry(BaseModel<?> oldModel) {
		PartnerEntryClp newModel = new PartnerEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPartnerEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputPartnerWorker(BaseModel<?> oldModel) {
		PartnerWorkerClp newModel = new PartnerWorkerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPartnerWorkerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputPortalRelease(BaseModel<?> oldModel) {
		PortalReleaseClp newModel = new PortalReleaseClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPortalReleaseRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputProductEntry(BaseModel<?> oldModel) {
		ProductEntryClp newModel = new ProductEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setProductEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSearchFilter(BaseModel<?> oldModel) {
		SearchFilterClp newModel = new SearchFilterClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSearchFilterRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSecurityPatch(BaseModel<?> oldModel) {
		SecurityPatchClp newModel = new SecurityPatchClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSecurityPatchRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSupportLabor(BaseModel<?> oldModel) {
		SupportLaborClp newModel = new SupportLaborClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSupportLaborRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSupportRegion(BaseModel<?> oldModel) {
		SupportRegionClp newModel = new SupportRegionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSupportRegionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSupportResponse(BaseModel<?> oldModel) {
		SupportResponseClp newModel = new SupportResponseClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSupportResponseRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSupportTeam(BaseModel<?> oldModel) {
		SupportTeamClp newModel = new SupportTeamClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSupportTeamRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSupportTeamLanguage(
		BaseModel<?> oldModel) {
		SupportTeamLanguageClp newModel = new SupportTeamLanguageClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSupportTeamLanguageRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSupportWorker(BaseModel<?> oldModel) {
		SupportWorkerClp newModel = new SupportWorkerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSupportWorkerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSupportWorkerAccountTier(
		BaseModel<?> oldModel) {
		SupportWorkerAccountTierClp newModel = new SupportWorkerAccountTierClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSupportWorkerAccountTierRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSupportWorkerComponent(
		BaseModel<?> oldModel) {
		SupportWorkerComponentClp newModel = new SupportWorkerComponentClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSupportWorkerComponentRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSupportWorkerSeverity(
		BaseModel<?> oldModel) {
		SupportWorkerSeverityClp newModel = new SupportWorkerSeverityClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSupportWorkerSeverityRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTicketAttachment(BaseModel<?> oldModel) {
		TicketAttachmentClp newModel = new TicketAttachmentClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTicketAttachmentRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTicketCall(BaseModel<?> oldModel) {
		TicketCallClp newModel = new TicketCallClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTicketCallRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTicketCannedResponse(
		BaseModel<?> oldModel) {
		TicketCannedResponseClp newModel = new TicketCannedResponseClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTicketCannedResponseRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTicketComment(BaseModel<?> oldModel) {
		TicketCommentClp newModel = new TicketCommentClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTicketCommentRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTicketEntry(BaseModel<?> oldModel) {
		TicketEntryClp newModel = new TicketEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTicketEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTicketFeedback(BaseModel<?> oldModel) {
		TicketFeedbackClp newModel = new TicketFeedbackClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTicketFeedbackRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTicketFlag(BaseModel<?> oldModel) {
		TicketFlagClp newModel = new TicketFlagClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTicketFlagRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTicketInformation(BaseModel<?> oldModel) {
		TicketInformationClp newModel = new TicketInformationClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTicketInformationRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTicketLink(BaseModel<?> oldModel) {
		TicketLinkClp newModel = new TicketLinkClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTicketLinkRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTicketSolution(BaseModel<?> oldModel) {
		TicketSolutionClp newModel = new TicketSolutionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTicketSolutionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTicketWorker(BaseModel<?> oldModel) {
		TicketWorkerClp newModel = new TicketWorkerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTicketWorkerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingCertificate(
		BaseModel<?> oldModel) {
		TrainingCertificateClp newModel = new TrainingCertificateClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingCertificateRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingCertificateTemplate(
		BaseModel<?> oldModel) {
		TrainingCertificateTemplateClp newModel = new TrainingCertificateTemplateClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingCertificateTemplateRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingCourse(BaseModel<?> oldModel) {
		TrainingCourseClp newModel = new TrainingCourseClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingCourseRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingCustomer(BaseModel<?> oldModel) {
		TrainingCustomerClp newModel = new TrainingCustomerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingCustomerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingEvent(BaseModel<?> oldModel) {
		TrainingEventClp newModel = new TrainingEventClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingEventRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingExam(BaseModel<?> oldModel) {
		TrainingExamClp newModel = new TrainingExamClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingExamRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingExamResult(
		BaseModel<?> oldModel) {
		TrainingExamResultClp newModel = new TrainingExamResultClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingExamResultRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingExamResultItem(
		BaseModel<?> oldModel) {
		TrainingExamResultItemClp newModel = new TrainingExamResultItemClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingExamResultItemRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingExamResultSection(
		BaseModel<?> oldModel) {
		TrainingExamResultSectionClp newModel = new TrainingExamResultSectionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingExamResultSectionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingImportLog(BaseModel<?> oldModel) {
		TrainingImportLogClp newModel = new TrainingImportLogClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingImportLogRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingLinkedUser(
		BaseModel<?> oldModel) {
		TrainingLinkedUserClp newModel = new TrainingLinkedUserClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingLinkedUserRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingLocation(BaseModel<?> oldModel) {
		TrainingLocationClp newModel = new TrainingLocationClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingLocationRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTrainingWorker(BaseModel<?> oldModel) {
		TrainingWorkerClp newModel = new TrainingWorkerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTrainingWorkerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputUserProfile(BaseModel<?> oldModel) {
		UserProfileClp newModel = new UserProfileClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setUserProfileRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputUserProfileHistory(
		BaseModel<?> oldModel) {
		UserProfileHistoryClp newModel = new UserProfileHistoryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setUserProfileHistoryRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}