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

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.osb.model.AuditActionClp;
import com.liferay.osb.model.AuditEntryClp;
import com.liferay.osb.model.CorpProjectClp;
import com.liferay.osb.model.CorpProjectMessageClp;
import com.liferay.osb.model.ExternalIdMapperClp;
import com.liferay.osb.model.FeedbackEntryClp;
import com.liferay.osb.model.HolidayCalendarClp;
import com.liferay.osb.model.HolidayCalendarRelClp;
import com.liferay.osb.model.HolidayEntryClp;
import com.liferay.osb.model.LCSSubscriptionEntryClp;
import com.liferay.osb.model.LicenseEntryClp;
import com.liferay.osb.model.LicenseKeyClp;
import com.liferay.osb.model.LicenseKeySetClp;
import com.liferay.osb.model.OfferingBundleClp;
import com.liferay.osb.model.OfferingDefinitionClp;
import com.liferay.osb.model.OfferingEntryClp;
import com.liferay.osb.model.OrderEntryClp;
import com.liferay.osb.model.PartnerEntryClp;
import com.liferay.osb.model.PartnerWorkerClp;
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
import com.liferay.osb.model.TicketCommentClp;
import com.liferay.osb.model.TicketEntryClp;
import com.liferay.osb.model.TicketFeedbackClp;
import com.liferay.osb.model.TicketFlagClp;
import com.liferay.osb.model.TicketInformationClp;
import com.liferay.osb.model.TicketLinkClp;
import com.liferay.osb.model.TicketSolutionClp;
import com.liferay.osb.model.TicketWorkerClp;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
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

		if (oldModelClassName.equals(AuditActionClp.class.getName())) {
			return translateInputAuditAction(oldModel);
		}

		if (oldModelClassName.equals(AuditEntryClp.class.getName())) {
			return translateInputAuditEntry(oldModel);
		}

		if (oldModelClassName.equals(CorpProjectClp.class.getName())) {
			return translateInputCorpProject(oldModel);
		}

		if (oldModelClassName.equals(CorpProjectMessageClp.class.getName())) {
			return translateInputCorpProjectMessage(oldModel);
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
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountCallImpl")) {
			return translateOutputAccountCall(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountCustomerImpl")) {
			return translateOutputAccountCustomer(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountEntryImpl")) {
			return translateOutputAccountEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountEntryLanguageImpl")) {
			return translateOutputAccountEntryLanguage(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountEnvironmentImpl")) {
			return translateOutputAccountEnvironment(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountEnvironmentAttachmentImpl")) {
			return translateOutputAccountEnvironmentAttachment(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountInformationImpl")) {
			return translateOutputAccountInformation(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountLinkImpl")) {
			return translateOutputAccountLink(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountProjectImpl")) {
			return translateOutputAccountProject(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AccountWorkerImpl")) {
			return translateOutputAccountWorker(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AuditActionImpl")) {
			return translateOutputAuditAction(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.AuditEntryImpl")) {
			return translateOutputAuditEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.CorpProjectImpl")) {
			return translateOutputCorpProject(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.CorpProjectMessageImpl")) {
			return translateOutputCorpProjectMessage(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.ExternalIdMapperImpl")) {
			return translateOutputExternalIdMapper(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.FeedbackEntryImpl")) {
			return translateOutputFeedbackEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.HolidayCalendarImpl")) {
			return translateOutputHolidayCalendar(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.HolidayCalendarRelImpl")) {
			return translateOutputHolidayCalendarRel(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.HolidayEntryImpl")) {
			return translateOutputHolidayEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.LCSSubscriptionEntryImpl")) {
			return translateOutputLCSSubscriptionEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.LicenseEntryImpl")) {
			return translateOutputLicenseEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.LicenseKeyImpl")) {
			return translateOutputLicenseKey(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.LicenseKeySetImpl")) {
			return translateOutputLicenseKeySet(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.OfferingBundleImpl")) {
			return translateOutputOfferingBundle(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.OfferingDefinitionImpl")) {
			return translateOutputOfferingDefinition(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.OfferingEntryImpl")) {
			return translateOutputOfferingEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.OrderEntryImpl")) {
			return translateOutputOrderEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.PartnerEntryImpl")) {
			return translateOutputPartnerEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.PartnerWorkerImpl")) {
			return translateOutputPartnerWorker(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.ProductEntryImpl")) {
			return translateOutputProductEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SearchFilterImpl")) {
			return translateOutputSearchFilter(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SecurityPatchImpl")) {
			return translateOutputSecurityPatch(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportLaborImpl")) {
			return translateOutputSupportLabor(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportRegionImpl")) {
			return translateOutputSupportRegion(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportResponseImpl")) {
			return translateOutputSupportResponse(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportTeamImpl")) {
			return translateOutputSupportTeam(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportTeamLanguageImpl")) {
			return translateOutputSupportTeamLanguage(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportWorkerImpl")) {
			return translateOutputSupportWorker(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportWorkerAccountTierImpl")) {
			return translateOutputSupportWorkerAccountTier(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportWorkerComponentImpl")) {
			return translateOutputSupportWorkerComponent(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.SupportWorkerSeverityImpl")) {
			return translateOutputSupportWorkerSeverity(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketAttachmentImpl")) {
			return translateOutputTicketAttachment(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketCallImpl")) {
			return translateOutputTicketCall(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketCommentImpl")) {
			return translateOutputTicketComment(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketEntryImpl")) {
			return translateOutputTicketEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketFeedbackImpl")) {
			return translateOutputTicketFeedback(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketFlagImpl")) {
			return translateOutputTicketFlag(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketInformationImpl")) {
			return translateOutputTicketInformation(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketLinkImpl")) {
			return translateOutputTicketLink(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketSolutionImpl")) {
			return translateOutputTicketSolution(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.TicketWorkerImpl")) {
			return translateOutputTicketWorker(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
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
			ObjectInputStream objectInputStream = null;
			ObjectOutputStream objectOutputStream = null;

			try {
				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
				objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

				objectOutputStream.writeObject(throwable);

				objectOutputStream.flush();

				UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
						0, unsyncByteArrayOutputStream.size());

				Thread currentThread = Thread.currentThread();

				ClassLoader contextClassLoader = currentThread.getContextClassLoader();

				objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
						contextClassLoader);

				throwable = (Throwable)objectInputStream.readObject();

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
			finally {
				if (objectOutputStream != null) {
					try {
						objectOutputStream.close();
					}
					catch (Throwable throwable2) {
						_log.error(throwable2, throwable2);

						return throwable2;
					}
				}

				if (objectInputStream != null) {
					try {
						objectInputStream.close();
					}
					catch (Throwable throwable2) {
						_log.error(throwable2, throwable2);

						return throwable2;
					}
				}
			}
		}

		Class<?> clazz = throwable.getClass();

		String className = clazz.getName();

		if (className.equals(
					"com.liferay.osb.exception.AccountAttachmentSizeException")) {
			return new com.liferay.osb.exception.AccountAttachmentSizeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountCallDateException")) {
			return new com.liferay.osb.exception.AccountCallDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountCallLengthException")) {
			return new com.liferay.osb.exception.AccountCallLengthException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountCallSummaryException")) {
			return new com.liferay.osb.exception.AccountCallSummaryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountCallTypeException")) {
			return new com.liferay.osb.exception.AccountCallTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEntryCodeException")) {
			return new com.liferay.osb.exception.AccountEntryCodeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEntryCorpProjectException")) {
			return new com.liferay.osb.exception.AccountEntryCorpProjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEntryIndustryException")) {
			return new com.liferay.osb.exception.AccountEntryIndustryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEntryLanguageIdException")) {
			return new com.liferay.osb.exception.AccountEntryLanguageIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEntryMaximumCustomersException")) {
			return new com.liferay.osb.exception.AccountEntryMaximumCustomersException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEntryNameException")) {
			return new com.liferay.osb.exception.AccountEntryNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEntryPartnerEntryException")) {
			return new com.liferay.osb.exception.AccountEntryPartnerEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEntrySupportRegionException")) {
			return new com.liferay.osb.exception.AccountEntrySupportRegionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEnvironmentAttachmentException")) {
			return new com.liferay.osb.exception.AccountEnvironmentAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEnvironmentAttachmentSizeException")) {
			return new com.liferay.osb.exception.AccountEnvironmentAttachmentSizeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEnvironmentEnvASException")) {
			return new com.liferay.osb.exception.AccountEnvironmentEnvASException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEnvironmentEnvDBException")) {
			return new com.liferay.osb.exception.AccountEnvironmentEnvDBException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEnvironmentEnvLFRException")) {
			return new com.liferay.osb.exception.AccountEnvironmentEnvLFRException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEnvironmentEnvOSException")) {
			return new com.liferay.osb.exception.AccountEnvironmentEnvOSException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountEnvironmentNameException")) {
			return new com.liferay.osb.exception.AccountEnvironmentNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountLinkURLException")) {
			return new com.liferay.osb.exception.AccountLinkURLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AccountProjectNameException")) {
			return new com.liferay.osb.exception.AccountProjectNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AwayMessageDateException")) {
			return new com.liferay.osb.exception.AwayMessageDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.AwayMessageMessageException")) {
			return new com.liferay.osb.exception.AwayMessageMessageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateAccountAttachmentException")) {
			return new com.liferay.osb.exception.DuplicateAccountAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateAccountCustomerException")) {
			return new com.liferay.osb.exception.DuplicateAccountCustomerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateAccountEntryException")) {
			return new com.liferay.osb.exception.DuplicateAccountEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateAccountEnvironmentException")) {
			return new com.liferay.osb.exception.DuplicateAccountEnvironmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateAccountEnvironmentAttachmentException")) {
			return new com.liferay.osb.exception.DuplicateAccountEnvironmentAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateHolidayEntryException")) {
			return new com.liferay.osb.exception.DuplicateHolidayEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateHostNameException")) {
			return new com.liferay.osb.exception.DuplicateHostNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateIPAddressException")) {
			return new com.liferay.osb.exception.DuplicateIPAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateMACAddressException")) {
			return new com.liferay.osb.exception.DuplicateMACAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateOfferingBundleException")) {
			return new com.liferay.osb.exception.DuplicateOfferingBundleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateOfferingDefinitionException")) {
			return new com.liferay.osb.exception.DuplicateOfferingDefinitionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicatePartnerEntryCodeException")) {
			return new com.liferay.osb.exception.DuplicatePartnerEntryCodeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicatePartnerEntryDossieraAccountKeyException")) {
			return new com.liferay.osb.exception.DuplicatePartnerEntryDossieraAccountKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateProductEntryException")) {
			return new com.liferay.osb.exception.DuplicateProductEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateSupportRegionException")) {
			return new com.liferay.osb.exception.DuplicateSupportRegionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateSupportResponseException")) {
			return new com.liferay.osb.exception.DuplicateSupportResponseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateSupportTeamException")) {
			return new com.liferay.osb.exception.DuplicateSupportTeamException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateTicketAttachmentException")) {
			return new com.liferay.osb.exception.DuplicateTicketAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateTicketFeedbackException")) {
			return new com.liferay.osb.exception.DuplicateTicketFeedbackException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.FileRepositoryNotAvailableException")) {
			return new com.liferay.osb.exception.FileRepositoryNotAvailableException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.HolidayCalendarNameException")) {
			return new com.liferay.osb.exception.HolidayCalendarNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.HolidayEntryDateException")) {
			return new com.liferay.osb.exception.HolidayEntryDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.HolidayEntryStartDateLaterThanEndDateException")) {
			return new com.liferay.osb.exception.HolidayEntryStartDateLaterThanEndDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseEntryNameException")) {
			return new com.liferay.osb.exception.LicenseEntryNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseEntryPortalVersionException")) {
			return new com.liferay.osb.exception.LicenseEntryPortalVersionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyActiveException")) {
			return new com.liferay.osb.exception.LicenseKeyActiveException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyDescriptionException")) {
			return new com.liferay.osb.exception.LicenseKeyDescriptionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyHostNameException")) {
			return new com.liferay.osb.exception.LicenseKeyHostNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyIPAddressException")) {
			return new com.liferay.osb.exception.LicenseKeyIPAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyMACAddressException")) {
			return new com.liferay.osb.exception.LicenseKeyMACAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyMaxServersException")) {
			return new com.liferay.osb.exception.LicenseKeyMaxServersException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyOwnerException")) {
			return new com.liferay.osb.exception.LicenseKeyOwnerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyProductEntryException")) {
			return new com.liferay.osb.exception.LicenseKeyProductEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyProductVersionException")) {
			return new com.liferay.osb.exception.LicenseKeyProductVersionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyRegistrationException")) {
			return new com.liferay.osb.exception.LicenseKeyRegistrationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyRenewException")) {
			return new com.liferay.osb.exception.LicenseKeyRenewException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyServerIdException")) {
			return new com.liferay.osb.exception.LicenseKeyServerIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyServerInfoException")) {
			return new com.liferay.osb.exception.LicenseKeyServerInfoException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeySetNameException")) {
			return new com.liferay.osb.exception.LicenseKeySetNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeySingleUseException")) {
			return new com.liferay.osb.exception.LicenseKeySingleUseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.MaximumDraftTicketCommentException")) {
			return new com.liferay.osb.exception.MaximumDraftTicketCommentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.MaximumLicenseKeyException")) {
			return new com.liferay.osb.exception.MaximumLicenseKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.MaximumTicketEntryException")) {
			return new com.liferay.osb.exception.MaximumTicketEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.exception.OSBPrincipalException")) {
			return new com.liferay.osb.exception.OSBPrincipalException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OfferingBundleNameException")) {
			return new com.liferay.osb.exception.OfferingBundleNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OfferingEntryQuantityException")) {
			return new com.liferay.osb.exception.OfferingEntryQuantityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OfferingEntrySizingException")) {
			return new com.liferay.osb.exception.OfferingEntrySizingException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OfferingEntryStatusException")) {
			return new com.liferay.osb.exception.OfferingEntryStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OfferingEntrySupportExpiredException")) {
			return new com.liferay.osb.exception.OfferingEntrySupportExpiredException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OrderEntryActualStartDateException")) {
			return new com.liferay.osb.exception.OrderEntryActualStartDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OrderEntryStartDateException")) {
			return new com.liferay.osb.exception.OrderEntryStartDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.PartnerEntryCodeException")) {
			return new com.liferay.osb.exception.PartnerEntryCodeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.PartnerEntryParentPartnerEntryException")) {
			return new com.liferay.osb.exception.PartnerEntryParentPartnerEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.ProductEntryEnvironmentException")) {
			return new com.liferay.osb.exception.ProductEntryEnvironmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.ProductEntryNameException")) {
			return new com.liferay.osb.exception.ProductEntryNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.exception.RemoteServiceException")) {
			return new com.liferay.osb.exception.RemoteServiceException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredAccountEntryException")) {
			return new com.liferay.osb.exception.RequiredAccountEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredOfferingDefinitionException")) {
			return new com.liferay.osb.exception.RequiredOfferingDefinitionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredOfferingEntryException")) {
			return new com.liferay.osb.exception.RequiredOfferingEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredPartnerEntryException")) {
			return new com.liferay.osb.exception.RequiredPartnerEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredProductEntryException")) {
			return new com.liferay.osb.exception.RequiredProductEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredSupportRegionException")) {
			return new com.liferay.osb.exception.RequiredSupportRegionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredSupportResponseException")) {
			return new com.liferay.osb.exception.RequiredSupportResponseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredSupportTeamException")) {
			return new com.liferay.osb.exception.RequiredSupportTeamException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredTicketWorkerException")) {
			return new com.liferay.osb.exception.RequiredTicketWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.SearchFilterNameException")) {
			return new com.liferay.osb.exception.SearchFilterNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.SupportLaborHourException")) {
			return new com.liferay.osb.exception.SupportLaborHourException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.SupportLaborNameException")) {
			return new com.liferay.osb.exception.SupportLaborNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.SupportRegionNameException")) {
			return new com.liferay.osb.exception.SupportRegionNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.SupportResponseNameException")) {
			return new com.liferay.osb.exception.SupportResponseNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.SupportResponseSupportLevelException")) {
			return new com.liferay.osb.exception.SupportResponseSupportLevelException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.SupportTeamLocationException")) {
			return new com.liferay.osb.exception.SupportTeamLocationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.SupportTeamNameException")) {
			return new com.liferay.osb.exception.SupportTeamNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.SupportTeamSupportLaborException")) {
			return new com.liferay.osb.exception.SupportTeamSupportLaborException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.SupportWorkerMaxWorkException")) {
			return new com.liferay.osb.exception.SupportWorkerMaxWorkException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketAttachmentAvailableFileRepositoryIdsException")) {
			return new com.liferay.osb.exception.TicketAttachmentAvailableFileRepositoryIdsException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketAttachmentVisibilityException")) {
			return new com.liferay.osb.exception.TicketAttachmentVisibilityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketCallCustomerNameException")) {
			return new com.liferay.osb.exception.TicketCallCustomerNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketCallDateException")) {
			return new com.liferay.osb.exception.TicketCallDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketCallLengthException")) {
			return new com.liferay.osb.exception.TicketCallLengthException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketCallTypeException")) {
			return new com.liferay.osb.exception.TicketCallTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketCommentBodyException")) {
			return new com.liferay.osb.exception.TicketCommentBodyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketCommentPendingTypeException")) {
			return new com.liferay.osb.exception.TicketCommentPendingTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketCommentVisibilityException")) {
			return new com.liferay.osb.exception.TicketCommentVisibilityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntryAssigneeException")) {
			return new com.liferay.osb.exception.TicketEntryAssigneeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntryAttachmentSizeException")) {
			return new com.liferay.osb.exception.TicketEntryAttachmentSizeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntryClosedException")) {
			return new com.liferay.osb.exception.TicketEntryClosedException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntryComponentException")) {
			return new com.liferay.osb.exception.TicketEntryComponentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntryDescriptionException")) {
			return new com.liferay.osb.exception.TicketEntryDescriptionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntryDueDateException")) {
			return new com.liferay.osb.exception.TicketEntryDueDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntryForwardingException")) {
			return new com.liferay.osb.exception.TicketEntryForwardingException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntryForwardingFieldException")) {
			return new com.liferay.osb.exception.TicketEntryForwardingFieldException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntryLanguageIdException")) {
			return new com.liferay.osb.exception.TicketEntryLanguageIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntryResolutionException")) {
			return new com.liferay.osb.exception.TicketEntryResolutionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntrySeverityException")) {
			return new com.liferay.osb.exception.TicketEntrySeverityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntryStatusException")) {
			return new com.liferay.osb.exception.TicketEntryStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntrySubcomponentException")) {
			return new com.liferay.osb.exception.TicketEntrySubcomponentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntrySubjectException")) {
			return new com.liferay.osb.exception.TicketEntrySubjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntrySystemStatusException")) {
			return new com.liferay.osb.exception.TicketEntrySystemStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketEntryWeightException")) {
			return new com.liferay.osb.exception.TicketEntryWeightException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketFeedbackAnswerException")) {
			return new com.liferay.osb.exception.TicketFeedbackAnswerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketFeedbackRatingException")) {
			return new com.liferay.osb.exception.TicketFeedbackRatingException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketFeedbackSatisfiedException")) {
			return new com.liferay.osb.exception.TicketFeedbackSatisfiedException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketFlagTypeException")) {
			return new com.liferay.osb.exception.TicketFlagTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketInformationException")) {
			return new com.liferay.osb.exception.TicketInformationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketLinkTypeException")) {
			return new com.liferay.osb.exception.TicketLinkTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.exception.TicketLinkURLException")) {
			return new com.liferay.osb.exception.TicketLinkURLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketLinkVisibilityException")) {
			return new com.liferay.osb.exception.TicketLinkVisibilityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketSolutionBodyException")) {
			return new com.liferay.osb.exception.TicketSolutionBodyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketSolutionStatusException")) {
			return new com.liferay.osb.exception.TicketSolutionStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketSolutionStatusMessageException")) {
			return new com.liferay.osb.exception.TicketSolutionStatusMessageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.TicketSolutionSummaryException")) {
			return new com.liferay.osb.exception.TicketSolutionSummaryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountAttachmentException")) {
			return new com.liferay.osb.exception.NoSuchAccountAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountCallException")) {
			return new com.liferay.osb.exception.NoSuchAccountCallException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountCustomerException")) {
			return new com.liferay.osb.exception.NoSuchAccountCustomerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountEntryException")) {
			return new com.liferay.osb.exception.NoSuchAccountEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountEntryLanguageException")) {
			return new com.liferay.osb.exception.NoSuchAccountEntryLanguageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountEnvironmentException")) {
			return new com.liferay.osb.exception.NoSuchAccountEnvironmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountEnvironmentAttachmentException")) {
			return new com.liferay.osb.exception.NoSuchAccountEnvironmentAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountInformationException")) {
			return new com.liferay.osb.exception.NoSuchAccountInformationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountLinkException")) {
			return new com.liferay.osb.exception.NoSuchAccountLinkException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountProjectException")) {
			return new com.liferay.osb.exception.NoSuchAccountProjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountWorkerException")) {
			return new com.liferay.osb.exception.NoSuchAccountWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAuditActionException")) {
			return new com.liferay.osb.exception.NoSuchAuditActionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAuditEntryException")) {
			return new com.liferay.osb.exception.NoSuchAuditEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchCorpProjectException")) {
			return new com.liferay.osb.exception.NoSuchCorpProjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchCorpProjectMessageException")) {
			return new com.liferay.osb.exception.NoSuchCorpProjectMessageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchExternalIdMapperException")) {
			return new com.liferay.osb.exception.NoSuchExternalIdMapperException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchFeedbackEntryException")) {
			return new com.liferay.osb.exception.NoSuchFeedbackEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchHolidayCalendarException")) {
			return new com.liferay.osb.exception.NoSuchHolidayCalendarException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchHolidayCalendarRelException")) {
			return new com.liferay.osb.exception.NoSuchHolidayCalendarRelException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchHolidayEntryException")) {
			return new com.liferay.osb.exception.NoSuchHolidayEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchLCSSubscriptionEntryException")) {
			return new com.liferay.osb.exception.NoSuchLCSSubscriptionEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchLicenseEntryException")) {
			return new com.liferay.osb.exception.NoSuchLicenseEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchLicenseKeyException")) {
			return new com.liferay.osb.exception.NoSuchLicenseKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchLicenseKeySetException")) {
			return new com.liferay.osb.exception.NoSuchLicenseKeySetException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchOfferingBundleException")) {
			return new com.liferay.osb.exception.NoSuchOfferingBundleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchOfferingDefinitionException")) {
			return new com.liferay.osb.exception.NoSuchOfferingDefinitionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchOfferingEntryException")) {
			return new com.liferay.osb.exception.NoSuchOfferingEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchOrderEntryException")) {
			return new com.liferay.osb.exception.NoSuchOrderEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchPartnerEntryException")) {
			return new com.liferay.osb.exception.NoSuchPartnerEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchPartnerWorkerException")) {
			return new com.liferay.osb.exception.NoSuchPartnerWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchProductEntryException")) {
			return new com.liferay.osb.exception.NoSuchProductEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchSearchFilterException")) {
			return new com.liferay.osb.exception.NoSuchSearchFilterException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchSecurityPatchException")) {
			return new com.liferay.osb.exception.NoSuchSecurityPatchException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchSupportLaborException")) {
			return new com.liferay.osb.exception.NoSuchSupportLaborException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchSupportRegionException")) {
			return new com.liferay.osb.exception.NoSuchSupportRegionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchSupportResponseException")) {
			return new com.liferay.osb.exception.NoSuchSupportResponseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchSupportTeamException")) {
			return new com.liferay.osb.exception.NoSuchSupportTeamException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchSupportTeamLanguageException")) {
			return new com.liferay.osb.exception.NoSuchSupportTeamLanguageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchSupportWorkerException")) {
			return new com.liferay.osb.exception.NoSuchSupportWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchSupportWorkerAccountTierException")) {
			return new com.liferay.osb.exception.NoSuchSupportWorkerAccountTierException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchSupportWorkerComponentException")) {
			return new com.liferay.osb.exception.NoSuchSupportWorkerComponentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchSupportWorkerSeverityException")) {
			return new com.liferay.osb.exception.NoSuchSupportWorkerSeverityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchTicketAttachmentException")) {
			return new com.liferay.osb.exception.NoSuchTicketAttachmentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchTicketCallException")) {
			return new com.liferay.osb.exception.NoSuchTicketCallException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchTicketCommentException")) {
			return new com.liferay.osb.exception.NoSuchTicketCommentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchTicketEntryException")) {
			return new com.liferay.osb.exception.NoSuchTicketEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchTicketFeedbackException")) {
			return new com.liferay.osb.exception.NoSuchTicketFeedbackException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchTicketFlagException")) {
			return new com.liferay.osb.exception.NoSuchTicketFlagException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchTicketInformationException")) {
			return new com.liferay.osb.exception.NoSuchTicketInformationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchTicketLinkException")) {
			return new com.liferay.osb.exception.NoSuchTicketLinkException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchTicketSolutionException")) {
			return new com.liferay.osb.exception.NoSuchTicketSolutionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchTicketWorkerException")) {
			return new com.liferay.osb.exception.NoSuchTicketWorkerException(throwable.getMessage(),
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

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}