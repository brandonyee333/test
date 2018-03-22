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

import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.service.AccountEnvironmentService;
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
import com.liferay.osb.service.persistence.AuditActionPersistence;
import com.liferay.osb.service.persistence.AuditEntryPersistence;
import com.liferay.osb.service.persistence.CorpProjectMessagePersistence;
import com.liferay.osb.service.persistence.CorpProjectPersistence;
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
import com.liferay.osb.service.persistence.OfferingBundlePersistence;
import com.liferay.osb.service.persistence.OfferingDefinitionPersistence;
import com.liferay.osb.service.persistence.OfferingEntryFinder;
import com.liferay.osb.service.persistence.OfferingEntryPersistence;
import com.liferay.osb.service.persistence.OrderEntryFinder;
import com.liferay.osb.service.persistence.OrderEntryPersistence;
import com.liferay.osb.service.persistence.PartnerEntryFinder;
import com.liferay.osb.service.persistence.PartnerEntryPersistence;
import com.liferay.osb.service.persistence.PartnerWorkerPersistence;
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

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.ListTypePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the account environment remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.osb.service.impl.AccountEnvironmentServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.impl.AccountEnvironmentServiceImpl
 * @see com.liferay.osb.service.AccountEnvironmentServiceUtil
 * @generated
 */
public abstract class AccountEnvironmentServiceBaseImpl extends BaseServiceImpl
	implements AccountEnvironmentService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.osb.service.AccountEnvironmentServiceUtil} to access the account environment remote service.
	 */

	/**
	 * Returns the account attachment local service.
	 *
	 * @return the account attachment local service
	 */
	public com.liferay.osb.service.AccountAttachmentLocalService getAccountAttachmentLocalService() {
		return accountAttachmentLocalService;
	}

	/**
	 * Sets the account attachment local service.
	 *
	 * @param accountAttachmentLocalService the account attachment local service
	 */
	public void setAccountAttachmentLocalService(
		com.liferay.osb.service.AccountAttachmentLocalService accountAttachmentLocalService) {
		this.accountAttachmentLocalService = accountAttachmentLocalService;
	}

	/**
	 * Returns the account attachment remote service.
	 *
	 * @return the account attachment remote service
	 */
	public com.liferay.osb.service.AccountAttachmentService getAccountAttachmentService() {
		return accountAttachmentService;
	}

	/**
	 * Sets the account attachment remote service.
	 *
	 * @param accountAttachmentService the account attachment remote service
	 */
	public void setAccountAttachmentService(
		com.liferay.osb.service.AccountAttachmentService accountAttachmentService) {
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
	public com.liferay.osb.service.AccountCallLocalService getAccountCallLocalService() {
		return accountCallLocalService;
	}

	/**
	 * Sets the account call local service.
	 *
	 * @param accountCallLocalService the account call local service
	 */
	public void setAccountCallLocalService(
		com.liferay.osb.service.AccountCallLocalService accountCallLocalService) {
		this.accountCallLocalService = accountCallLocalService;
	}

	/**
	 * Returns the account call remote service.
	 *
	 * @return the account call remote service
	 */
	public com.liferay.osb.service.AccountCallService getAccountCallService() {
		return accountCallService;
	}

	/**
	 * Sets the account call remote service.
	 *
	 * @param accountCallService the account call remote service
	 */
	public void setAccountCallService(
		com.liferay.osb.service.AccountCallService accountCallService) {
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
	public com.liferay.osb.service.AccountCustomerLocalService getAccountCustomerLocalService() {
		return accountCustomerLocalService;
	}

	/**
	 * Sets the account customer local service.
	 *
	 * @param accountCustomerLocalService the account customer local service
	 */
	public void setAccountCustomerLocalService(
		com.liferay.osb.service.AccountCustomerLocalService accountCustomerLocalService) {
		this.accountCustomerLocalService = accountCustomerLocalService;
	}

	/**
	 * Returns the account customer remote service.
	 *
	 * @return the account customer remote service
	 */
	public com.liferay.osb.service.AccountCustomerService getAccountCustomerService() {
		return accountCustomerService;
	}

	/**
	 * Sets the account customer remote service.
	 *
	 * @param accountCustomerService the account customer remote service
	 */
	public void setAccountCustomerService(
		com.liferay.osb.service.AccountCustomerService accountCustomerService) {
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
	public com.liferay.osb.service.AccountEntryLocalService getAccountEntryLocalService() {
		return accountEntryLocalService;
	}

	/**
	 * Sets the account entry local service.
	 *
	 * @param accountEntryLocalService the account entry local service
	 */
	public void setAccountEntryLocalService(
		com.liferay.osb.service.AccountEntryLocalService accountEntryLocalService) {
		this.accountEntryLocalService = accountEntryLocalService;
	}

	/**
	 * Returns the account entry remote service.
	 *
	 * @return the account entry remote service
	 */
	public com.liferay.osb.service.AccountEntryService getAccountEntryService() {
		return accountEntryService;
	}

	/**
	 * Sets the account entry remote service.
	 *
	 * @param accountEntryService the account entry remote service
	 */
	public void setAccountEntryService(
		com.liferay.osb.service.AccountEntryService accountEntryService) {
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
	public com.liferay.osb.service.AccountEntryLanguageLocalService getAccountEntryLanguageLocalService() {
		return accountEntryLanguageLocalService;
	}

	/**
	 * Sets the account entry language local service.
	 *
	 * @param accountEntryLanguageLocalService the account entry language local service
	 */
	public void setAccountEntryLanguageLocalService(
		com.liferay.osb.service.AccountEntryLanguageLocalService accountEntryLanguageLocalService) {
		this.accountEntryLanguageLocalService = accountEntryLanguageLocalService;
	}

	/**
	 * Returns the account entry language remote service.
	 *
	 * @return the account entry language remote service
	 */
	public com.liferay.osb.service.AccountEntryLanguageService getAccountEntryLanguageService() {
		return accountEntryLanguageService;
	}

	/**
	 * Sets the account entry language remote service.
	 *
	 * @param accountEntryLanguageService the account entry language remote service
	 */
	public void setAccountEntryLanguageService(
		com.liferay.osb.service.AccountEntryLanguageService accountEntryLanguageService) {
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
	public com.liferay.osb.service.AccountEnvironmentLocalService getAccountEnvironmentLocalService() {
		return accountEnvironmentLocalService;
	}

	/**
	 * Sets the account environment local service.
	 *
	 * @param accountEnvironmentLocalService the account environment local service
	 */
	public void setAccountEnvironmentLocalService(
		com.liferay.osb.service.AccountEnvironmentLocalService accountEnvironmentLocalService) {
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
	public com.liferay.osb.service.AccountEnvironmentAttachmentLocalService getAccountEnvironmentAttachmentLocalService() {
		return accountEnvironmentAttachmentLocalService;
	}

	/**
	 * Sets the account environment attachment local service.
	 *
	 * @param accountEnvironmentAttachmentLocalService the account environment attachment local service
	 */
	public void setAccountEnvironmentAttachmentLocalService(
		com.liferay.osb.service.AccountEnvironmentAttachmentLocalService accountEnvironmentAttachmentLocalService) {
		this.accountEnvironmentAttachmentLocalService = accountEnvironmentAttachmentLocalService;
	}

	/**
	 * Returns the account environment attachment remote service.
	 *
	 * @return the account environment attachment remote service
	 */
	public com.liferay.osb.service.AccountEnvironmentAttachmentService getAccountEnvironmentAttachmentService() {
		return accountEnvironmentAttachmentService;
	}

	/**
	 * Sets the account environment attachment remote service.
	 *
	 * @param accountEnvironmentAttachmentService the account environment attachment remote service
	 */
	public void setAccountEnvironmentAttachmentService(
		com.liferay.osb.service.AccountEnvironmentAttachmentService accountEnvironmentAttachmentService) {
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
	public com.liferay.osb.service.AccountInformationLocalService getAccountInformationLocalService() {
		return accountInformationLocalService;
	}

	/**
	 * Sets the account information local service.
	 *
	 * @param accountInformationLocalService the account information local service
	 */
	public void setAccountInformationLocalService(
		com.liferay.osb.service.AccountInformationLocalService accountInformationLocalService) {
		this.accountInformationLocalService = accountInformationLocalService;
	}

	/**
	 * Returns the account information remote service.
	 *
	 * @return the account information remote service
	 */
	public com.liferay.osb.service.AccountInformationService getAccountInformationService() {
		return accountInformationService;
	}

	/**
	 * Sets the account information remote service.
	 *
	 * @param accountInformationService the account information remote service
	 */
	public void setAccountInformationService(
		com.liferay.osb.service.AccountInformationService accountInformationService) {
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
	public com.liferay.osb.service.AccountLinkLocalService getAccountLinkLocalService() {
		return accountLinkLocalService;
	}

	/**
	 * Sets the account link local service.
	 *
	 * @param accountLinkLocalService the account link local service
	 */
	public void setAccountLinkLocalService(
		com.liferay.osb.service.AccountLinkLocalService accountLinkLocalService) {
		this.accountLinkLocalService = accountLinkLocalService;
	}

	/**
	 * Returns the account link remote service.
	 *
	 * @return the account link remote service
	 */
	public com.liferay.osb.service.AccountLinkService getAccountLinkService() {
		return accountLinkService;
	}

	/**
	 * Sets the account link remote service.
	 *
	 * @param accountLinkService the account link remote service
	 */
	public void setAccountLinkService(
		com.liferay.osb.service.AccountLinkService accountLinkService) {
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
	public com.liferay.osb.service.AccountProjectLocalService getAccountProjectLocalService() {
		return accountProjectLocalService;
	}

	/**
	 * Sets the account project local service.
	 *
	 * @param accountProjectLocalService the account project local service
	 */
	public void setAccountProjectLocalService(
		com.liferay.osb.service.AccountProjectLocalService accountProjectLocalService) {
		this.accountProjectLocalService = accountProjectLocalService;
	}

	/**
	 * Returns the account project remote service.
	 *
	 * @return the account project remote service
	 */
	public com.liferay.osb.service.AccountProjectService getAccountProjectService() {
		return accountProjectService;
	}

	/**
	 * Sets the account project remote service.
	 *
	 * @param accountProjectService the account project remote service
	 */
	public void setAccountProjectService(
		com.liferay.osb.service.AccountProjectService accountProjectService) {
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
	public com.liferay.osb.service.AccountWorkerLocalService getAccountWorkerLocalService() {
		return accountWorkerLocalService;
	}

	/**
	 * Sets the account worker local service.
	 *
	 * @param accountWorkerLocalService the account worker local service
	 */
	public void setAccountWorkerLocalService(
		com.liferay.osb.service.AccountWorkerLocalService accountWorkerLocalService) {
		this.accountWorkerLocalService = accountWorkerLocalService;
	}

	/**
	 * Returns the account worker remote service.
	 *
	 * @return the account worker remote service
	 */
	public com.liferay.osb.service.AccountWorkerService getAccountWorkerService() {
		return accountWorkerService;
	}

	/**
	 * Sets the account worker remote service.
	 *
	 * @param accountWorkerService the account worker remote service
	 */
	public void setAccountWorkerService(
		com.liferay.osb.service.AccountWorkerService accountWorkerService) {
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
	 * Returns the audit action local service.
	 *
	 * @return the audit action local service
	 */
	public com.liferay.osb.service.AuditActionLocalService getAuditActionLocalService() {
		return auditActionLocalService;
	}

	/**
	 * Sets the audit action local service.
	 *
	 * @param auditActionLocalService the audit action local service
	 */
	public void setAuditActionLocalService(
		com.liferay.osb.service.AuditActionLocalService auditActionLocalService) {
		this.auditActionLocalService = auditActionLocalService;
	}

	/**
	 * Returns the audit action remote service.
	 *
	 * @return the audit action remote service
	 */
	public com.liferay.osb.service.AuditActionService getAuditActionService() {
		return auditActionService;
	}

	/**
	 * Sets the audit action remote service.
	 *
	 * @param auditActionService the audit action remote service
	 */
	public void setAuditActionService(
		com.liferay.osb.service.AuditActionService auditActionService) {
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
	public com.liferay.osb.service.AuditEntryLocalService getAuditEntryLocalService() {
		return auditEntryLocalService;
	}

	/**
	 * Sets the audit entry local service.
	 *
	 * @param auditEntryLocalService the audit entry local service
	 */
	public void setAuditEntryLocalService(
		com.liferay.osb.service.AuditEntryLocalService auditEntryLocalService) {
		this.auditEntryLocalService = auditEntryLocalService;
	}

	/**
	 * Returns the audit entry remote service.
	 *
	 * @return the audit entry remote service
	 */
	public com.liferay.osb.service.AuditEntryService getAuditEntryService() {
		return auditEntryService;
	}

	/**
	 * Sets the audit entry remote service.
	 *
	 * @param auditEntryService the audit entry remote service
	 */
	public void setAuditEntryService(
		com.liferay.osb.service.AuditEntryService auditEntryService) {
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
	 * Returns the corp project local service.
	 *
	 * @return the corp project local service
	 */
	public com.liferay.osb.service.CorpProjectLocalService getCorpProjectLocalService() {
		return corpProjectLocalService;
	}

	/**
	 * Sets the corp project local service.
	 *
	 * @param corpProjectLocalService the corp project local service
	 */
	public void setCorpProjectLocalService(
		com.liferay.osb.service.CorpProjectLocalService corpProjectLocalService) {
		this.corpProjectLocalService = corpProjectLocalService;
	}

	/**
	 * Returns the corp project remote service.
	 *
	 * @return the corp project remote service
	 */
	public com.liferay.osb.service.CorpProjectService getCorpProjectService() {
		return corpProjectService;
	}

	/**
	 * Sets the corp project remote service.
	 *
	 * @param corpProjectService the corp project remote service
	 */
	public void setCorpProjectService(
		com.liferay.osb.service.CorpProjectService corpProjectService) {
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
	public com.liferay.osb.service.CorpProjectMessageLocalService getCorpProjectMessageLocalService() {
		return corpProjectMessageLocalService;
	}

	/**
	 * Sets the corp project message local service.
	 *
	 * @param corpProjectMessageLocalService the corp project message local service
	 */
	public void setCorpProjectMessageLocalService(
		com.liferay.osb.service.CorpProjectMessageLocalService corpProjectMessageLocalService) {
		this.corpProjectMessageLocalService = corpProjectMessageLocalService;
	}

	/**
	 * Returns the corp project message remote service.
	 *
	 * @return the corp project message remote service
	 */
	public com.liferay.osb.service.CorpProjectMessageService getCorpProjectMessageService() {
		return corpProjectMessageService;
	}

	/**
	 * Sets the corp project message remote service.
	 *
	 * @param corpProjectMessageService the corp project message remote service
	 */
	public void setCorpProjectMessageService(
		com.liferay.osb.service.CorpProjectMessageService corpProjectMessageService) {
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
	 * Returns the external ID mapper local service.
	 *
	 * @return the external ID mapper local service
	 */
	public com.liferay.osb.service.ExternalIdMapperLocalService getExternalIdMapperLocalService() {
		return externalIdMapperLocalService;
	}

	/**
	 * Sets the external ID mapper local service.
	 *
	 * @param externalIdMapperLocalService the external ID mapper local service
	 */
	public void setExternalIdMapperLocalService(
		com.liferay.osb.service.ExternalIdMapperLocalService externalIdMapperLocalService) {
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
	public com.liferay.osb.service.FeedbackEntryLocalService getFeedbackEntryLocalService() {
		return feedbackEntryLocalService;
	}

	/**
	 * Sets the feedback entry local service.
	 *
	 * @param feedbackEntryLocalService the feedback entry local service
	 */
	public void setFeedbackEntryLocalService(
		com.liferay.osb.service.FeedbackEntryLocalService feedbackEntryLocalService) {
		this.feedbackEntryLocalService = feedbackEntryLocalService;
	}

	/**
	 * Returns the feedback entry remote service.
	 *
	 * @return the feedback entry remote service
	 */
	public com.liferay.osb.service.FeedbackEntryService getFeedbackEntryService() {
		return feedbackEntryService;
	}

	/**
	 * Sets the feedback entry remote service.
	 *
	 * @param feedbackEntryService the feedback entry remote service
	 */
	public void setFeedbackEntryService(
		com.liferay.osb.service.FeedbackEntryService feedbackEntryService) {
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
	public com.liferay.osb.service.HolidayCalendarLocalService getHolidayCalendarLocalService() {
		return holidayCalendarLocalService;
	}

	/**
	 * Sets the holiday calendar local service.
	 *
	 * @param holidayCalendarLocalService the holiday calendar local service
	 */
	public void setHolidayCalendarLocalService(
		com.liferay.osb.service.HolidayCalendarLocalService holidayCalendarLocalService) {
		this.holidayCalendarLocalService = holidayCalendarLocalService;
	}

	/**
	 * Returns the holiday calendar remote service.
	 *
	 * @return the holiday calendar remote service
	 */
	public com.liferay.osb.service.HolidayCalendarService getHolidayCalendarService() {
		return holidayCalendarService;
	}

	/**
	 * Sets the holiday calendar remote service.
	 *
	 * @param holidayCalendarService the holiday calendar remote service
	 */
	public void setHolidayCalendarService(
		com.liferay.osb.service.HolidayCalendarService holidayCalendarService) {
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
	public com.liferay.osb.service.HolidayCalendarRelLocalService getHolidayCalendarRelLocalService() {
		return holidayCalendarRelLocalService;
	}

	/**
	 * Sets the holiday calendar rel local service.
	 *
	 * @param holidayCalendarRelLocalService the holiday calendar rel local service
	 */
	public void setHolidayCalendarRelLocalService(
		com.liferay.osb.service.HolidayCalendarRelLocalService holidayCalendarRelLocalService) {
		this.holidayCalendarRelLocalService = holidayCalendarRelLocalService;
	}

	/**
	 * Returns the holiday calendar rel remote service.
	 *
	 * @return the holiday calendar rel remote service
	 */
	public com.liferay.osb.service.HolidayCalendarRelService getHolidayCalendarRelService() {
		return holidayCalendarRelService;
	}

	/**
	 * Sets the holiday calendar rel remote service.
	 *
	 * @param holidayCalendarRelService the holiday calendar rel remote service
	 */
	public void setHolidayCalendarRelService(
		com.liferay.osb.service.HolidayCalendarRelService holidayCalendarRelService) {
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
	public com.liferay.osb.service.HolidayEntryLocalService getHolidayEntryLocalService() {
		return holidayEntryLocalService;
	}

	/**
	 * Sets the holiday entry local service.
	 *
	 * @param holidayEntryLocalService the holiday entry local service
	 */
	public void setHolidayEntryLocalService(
		com.liferay.osb.service.HolidayEntryLocalService holidayEntryLocalService) {
		this.holidayEntryLocalService = holidayEntryLocalService;
	}

	/**
	 * Returns the holiday entry remote service.
	 *
	 * @return the holiday entry remote service
	 */
	public com.liferay.osb.service.HolidayEntryService getHolidayEntryService() {
		return holidayEntryService;
	}

	/**
	 * Sets the holiday entry remote service.
	 *
	 * @param holidayEntryService the holiday entry remote service
	 */
	public void setHolidayEntryService(
		com.liferay.osb.service.HolidayEntryService holidayEntryService) {
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
	 * Returns the lcs subscription entry local service.
	 *
	 * @return the lcs subscription entry local service
	 */
	public com.liferay.osb.service.LCSSubscriptionEntryLocalService getLCSSubscriptionEntryLocalService() {
		return lcsSubscriptionEntryLocalService;
	}

	/**
	 * Sets the lcs subscription entry local service.
	 *
	 * @param lcsSubscriptionEntryLocalService the lcs subscription entry local service
	 */
	public void setLCSSubscriptionEntryLocalService(
		com.liferay.osb.service.LCSSubscriptionEntryLocalService lcsSubscriptionEntryLocalService) {
		this.lcsSubscriptionEntryLocalService = lcsSubscriptionEntryLocalService;
	}

	/**
	 * Returns the lcs subscription entry persistence.
	 *
	 * @return the lcs subscription entry persistence
	 */
	public LCSSubscriptionEntryPersistence getLCSSubscriptionEntryPersistence() {
		return lcsSubscriptionEntryPersistence;
	}

	/**
	 * Sets the lcs subscription entry persistence.
	 *
	 * @param lcsSubscriptionEntryPersistence the lcs subscription entry persistence
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
	public com.liferay.osb.service.LicenseEntryLocalService getLicenseEntryLocalService() {
		return licenseEntryLocalService;
	}

	/**
	 * Sets the license entry local service.
	 *
	 * @param licenseEntryLocalService the license entry local service
	 */
	public void setLicenseEntryLocalService(
		com.liferay.osb.service.LicenseEntryLocalService licenseEntryLocalService) {
		this.licenseEntryLocalService = licenseEntryLocalService;
	}

	/**
	 * Returns the license entry remote service.
	 *
	 * @return the license entry remote service
	 */
	public com.liferay.osb.service.LicenseEntryService getLicenseEntryService() {
		return licenseEntryService;
	}

	/**
	 * Sets the license entry remote service.
	 *
	 * @param licenseEntryService the license entry remote service
	 */
	public void setLicenseEntryService(
		com.liferay.osb.service.LicenseEntryService licenseEntryService) {
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
	public com.liferay.osb.service.LicenseKeyLocalService getLicenseKeyLocalService() {
		return licenseKeyLocalService;
	}

	/**
	 * Sets the license key local service.
	 *
	 * @param licenseKeyLocalService the license key local service
	 */
	public void setLicenseKeyLocalService(
		com.liferay.osb.service.LicenseKeyLocalService licenseKeyLocalService) {
		this.licenseKeyLocalService = licenseKeyLocalService;
	}

	/**
	 * Returns the license key remote service.
	 *
	 * @return the license key remote service
	 */
	public com.liferay.osb.service.LicenseKeyService getLicenseKeyService() {
		return licenseKeyService;
	}

	/**
	 * Sets the license key remote service.
	 *
	 * @param licenseKeyService the license key remote service
	 */
	public void setLicenseKeyService(
		com.liferay.osb.service.LicenseKeyService licenseKeyService) {
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
	public com.liferay.osb.service.LicenseKeySetLocalService getLicenseKeySetLocalService() {
		return licenseKeySetLocalService;
	}

	/**
	 * Sets the license key set local service.
	 *
	 * @param licenseKeySetLocalService the license key set local service
	 */
	public void setLicenseKeySetLocalService(
		com.liferay.osb.service.LicenseKeySetLocalService licenseKeySetLocalService) {
		this.licenseKeySetLocalService = licenseKeySetLocalService;
	}

	/**
	 * Returns the license key set remote service.
	 *
	 * @return the license key set remote service
	 */
	public com.liferay.osb.service.LicenseKeySetService getLicenseKeySetService() {
		return licenseKeySetService;
	}

	/**
	 * Sets the license key set remote service.
	 *
	 * @param licenseKeySetService the license key set remote service
	 */
	public void setLicenseKeySetService(
		com.liferay.osb.service.LicenseKeySetService licenseKeySetService) {
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
	 * Returns the offering bundle local service.
	 *
	 * @return the offering bundle local service
	 */
	public com.liferay.osb.service.OfferingBundleLocalService getOfferingBundleLocalService() {
		return offeringBundleLocalService;
	}

	/**
	 * Sets the offering bundle local service.
	 *
	 * @param offeringBundleLocalService the offering bundle local service
	 */
	public void setOfferingBundleLocalService(
		com.liferay.osb.service.OfferingBundleLocalService offeringBundleLocalService) {
		this.offeringBundleLocalService = offeringBundleLocalService;
	}

	/**
	 * Returns the offering bundle remote service.
	 *
	 * @return the offering bundle remote service
	 */
	public com.liferay.osb.service.OfferingBundleService getOfferingBundleService() {
		return offeringBundleService;
	}

	/**
	 * Sets the offering bundle remote service.
	 *
	 * @param offeringBundleService the offering bundle remote service
	 */
	public void setOfferingBundleService(
		com.liferay.osb.service.OfferingBundleService offeringBundleService) {
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
	public com.liferay.osb.service.OfferingDefinitionLocalService getOfferingDefinitionLocalService() {
		return offeringDefinitionLocalService;
	}

	/**
	 * Sets the offering definition local service.
	 *
	 * @param offeringDefinitionLocalService the offering definition local service
	 */
	public void setOfferingDefinitionLocalService(
		com.liferay.osb.service.OfferingDefinitionLocalService offeringDefinitionLocalService) {
		this.offeringDefinitionLocalService = offeringDefinitionLocalService;
	}

	/**
	 * Returns the offering definition remote service.
	 *
	 * @return the offering definition remote service
	 */
	public com.liferay.osb.service.OfferingDefinitionService getOfferingDefinitionService() {
		return offeringDefinitionService;
	}

	/**
	 * Sets the offering definition remote service.
	 *
	 * @param offeringDefinitionService the offering definition remote service
	 */
	public void setOfferingDefinitionService(
		com.liferay.osb.service.OfferingDefinitionService offeringDefinitionService) {
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
	public com.liferay.osb.service.OfferingEntryLocalService getOfferingEntryLocalService() {
		return offeringEntryLocalService;
	}

	/**
	 * Sets the offering entry local service.
	 *
	 * @param offeringEntryLocalService the offering entry local service
	 */
	public void setOfferingEntryLocalService(
		com.liferay.osb.service.OfferingEntryLocalService offeringEntryLocalService) {
		this.offeringEntryLocalService = offeringEntryLocalService;
	}

	/**
	 * Returns the offering entry remote service.
	 *
	 * @return the offering entry remote service
	 */
	public com.liferay.osb.service.OfferingEntryService getOfferingEntryService() {
		return offeringEntryService;
	}

	/**
	 * Sets the offering entry remote service.
	 *
	 * @param offeringEntryService the offering entry remote service
	 */
	public void setOfferingEntryService(
		com.liferay.osb.service.OfferingEntryService offeringEntryService) {
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
	public com.liferay.osb.service.OrderEntryLocalService getOrderEntryLocalService() {
		return orderEntryLocalService;
	}

	/**
	 * Sets the order entry local service.
	 *
	 * @param orderEntryLocalService the order entry local service
	 */
	public void setOrderEntryLocalService(
		com.liferay.osb.service.OrderEntryLocalService orderEntryLocalService) {
		this.orderEntryLocalService = orderEntryLocalService;
	}

	/**
	 * Returns the order entry remote service.
	 *
	 * @return the order entry remote service
	 */
	public com.liferay.osb.service.OrderEntryService getOrderEntryService() {
		return orderEntryService;
	}

	/**
	 * Sets the order entry remote service.
	 *
	 * @param orderEntryService the order entry remote service
	 */
	public void setOrderEntryService(
		com.liferay.osb.service.OrderEntryService orderEntryService) {
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
	 * Returns the partner entry local service.
	 *
	 * @return the partner entry local service
	 */
	public com.liferay.osb.service.PartnerEntryLocalService getPartnerEntryLocalService() {
		return partnerEntryLocalService;
	}

	/**
	 * Sets the partner entry local service.
	 *
	 * @param partnerEntryLocalService the partner entry local service
	 */
	public void setPartnerEntryLocalService(
		com.liferay.osb.service.PartnerEntryLocalService partnerEntryLocalService) {
		this.partnerEntryLocalService = partnerEntryLocalService;
	}

	/**
	 * Returns the partner entry remote service.
	 *
	 * @return the partner entry remote service
	 */
	public com.liferay.osb.service.PartnerEntryService getPartnerEntryService() {
		return partnerEntryService;
	}

	/**
	 * Sets the partner entry remote service.
	 *
	 * @param partnerEntryService the partner entry remote service
	 */
	public void setPartnerEntryService(
		com.liferay.osb.service.PartnerEntryService partnerEntryService) {
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
	public com.liferay.osb.service.PartnerWorkerLocalService getPartnerWorkerLocalService() {
		return partnerWorkerLocalService;
	}

	/**
	 * Sets the partner worker local service.
	 *
	 * @param partnerWorkerLocalService the partner worker local service
	 */
	public void setPartnerWorkerLocalService(
		com.liferay.osb.service.PartnerWorkerLocalService partnerWorkerLocalService) {
		this.partnerWorkerLocalService = partnerWorkerLocalService;
	}

	/**
	 * Returns the partner worker remote service.
	 *
	 * @return the partner worker remote service
	 */
	public com.liferay.osb.service.PartnerWorkerService getPartnerWorkerService() {
		return partnerWorkerService;
	}

	/**
	 * Sets the partner worker remote service.
	 *
	 * @param partnerWorkerService the partner worker remote service
	 */
	public void setPartnerWorkerService(
		com.liferay.osb.service.PartnerWorkerService partnerWorkerService) {
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
	 * Returns the product entry local service.
	 *
	 * @return the product entry local service
	 */
	public com.liferay.osb.service.ProductEntryLocalService getProductEntryLocalService() {
		return productEntryLocalService;
	}

	/**
	 * Sets the product entry local service.
	 *
	 * @param productEntryLocalService the product entry local service
	 */
	public void setProductEntryLocalService(
		com.liferay.osb.service.ProductEntryLocalService productEntryLocalService) {
		this.productEntryLocalService = productEntryLocalService;
	}

	/**
	 * Returns the product entry remote service.
	 *
	 * @return the product entry remote service
	 */
	public com.liferay.osb.service.ProductEntryService getProductEntryService() {
		return productEntryService;
	}

	/**
	 * Sets the product entry remote service.
	 *
	 * @param productEntryService the product entry remote service
	 */
	public void setProductEntryService(
		com.liferay.osb.service.ProductEntryService productEntryService) {
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
	 * Returns the remote corp project local service.
	 *
	 * @return the remote corp project local service
	 */
	public com.liferay.osb.service.RemoteCorpProjectLocalService getRemoteCorpProjectLocalService() {
		return remoteCorpProjectLocalService;
	}

	/**
	 * Sets the remote corp project local service.
	 *
	 * @param remoteCorpProjectLocalService the remote corp project local service
	 */
	public void setRemoteCorpProjectLocalService(
		com.liferay.osb.service.RemoteCorpProjectLocalService remoteCorpProjectLocalService) {
		this.remoteCorpProjectLocalService = remoteCorpProjectLocalService;
	}

	/**
	 * Returns the remote corp project message local service.
	 *
	 * @return the remote corp project message local service
	 */
	public com.liferay.osb.service.RemoteCorpProjectMessageLocalService getRemoteCorpProjectMessageLocalService() {
		return remoteCorpProjectMessageLocalService;
	}

	/**
	 * Sets the remote corp project message local service.
	 *
	 * @param remoteCorpProjectMessageLocalService the remote corp project message local service
	 */
	public void setRemoteCorpProjectMessageLocalService(
		com.liferay.osb.service.RemoteCorpProjectMessageLocalService remoteCorpProjectMessageLocalService) {
		this.remoteCorpProjectMessageLocalService = remoteCorpProjectMessageLocalService;
	}

	/**
	 * Returns the remote user local service.
	 *
	 * @return the remote user local service
	 */
	public com.liferay.osb.service.RemoteUserLocalService getRemoteUserLocalService() {
		return remoteUserLocalService;
	}

	/**
	 * Sets the remote user local service.
	 *
	 * @param remoteUserLocalService the remote user local service
	 */
	public void setRemoteUserLocalService(
		com.liferay.osb.service.RemoteUserLocalService remoteUserLocalService) {
		this.remoteUserLocalService = remoteUserLocalService;
	}

	/**
	 * Returns the search filter local service.
	 *
	 * @return the search filter local service
	 */
	public com.liferay.osb.service.SearchFilterLocalService getSearchFilterLocalService() {
		return searchFilterLocalService;
	}

	/**
	 * Sets the search filter local service.
	 *
	 * @param searchFilterLocalService the search filter local service
	 */
	public void setSearchFilterLocalService(
		com.liferay.osb.service.SearchFilterLocalService searchFilterLocalService) {
		this.searchFilterLocalService = searchFilterLocalService;
	}

	/**
	 * Returns the search filter remote service.
	 *
	 * @return the search filter remote service
	 */
	public com.liferay.osb.service.SearchFilterService getSearchFilterService() {
		return searchFilterService;
	}

	/**
	 * Sets the search filter remote service.
	 *
	 * @param searchFilterService the search filter remote service
	 */
	public void setSearchFilterService(
		com.liferay.osb.service.SearchFilterService searchFilterService) {
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
	public com.liferay.osb.service.SecurityPatchLocalService getSecurityPatchLocalService() {
		return securityPatchLocalService;
	}

	/**
	 * Sets the security patch local service.
	 *
	 * @param securityPatchLocalService the security patch local service
	 */
	public void setSecurityPatchLocalService(
		com.liferay.osb.service.SecurityPatchLocalService securityPatchLocalService) {
		this.securityPatchLocalService = securityPatchLocalService;
	}

	/**
	 * Returns the security patch remote service.
	 *
	 * @return the security patch remote service
	 */
	public com.liferay.osb.service.SecurityPatchService getSecurityPatchService() {
		return securityPatchService;
	}

	/**
	 * Sets the security patch remote service.
	 *
	 * @param securityPatchService the security patch remote service
	 */
	public void setSecurityPatchService(
		com.liferay.osb.service.SecurityPatchService securityPatchService) {
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
	public com.liferay.osb.service.SupportLaborLocalService getSupportLaborLocalService() {
		return supportLaborLocalService;
	}

	/**
	 * Sets the support labor local service.
	 *
	 * @param supportLaborLocalService the support labor local service
	 */
	public void setSupportLaborLocalService(
		com.liferay.osb.service.SupportLaborLocalService supportLaborLocalService) {
		this.supportLaborLocalService = supportLaborLocalService;
	}

	/**
	 * Returns the support labor remote service.
	 *
	 * @return the support labor remote service
	 */
	public com.liferay.osb.service.SupportLaborService getSupportLaborService() {
		return supportLaborService;
	}

	/**
	 * Sets the support labor remote service.
	 *
	 * @param supportLaborService the support labor remote service
	 */
	public void setSupportLaborService(
		com.liferay.osb.service.SupportLaborService supportLaborService) {
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
	public com.liferay.osb.service.SupportRegionLocalService getSupportRegionLocalService() {
		return supportRegionLocalService;
	}

	/**
	 * Sets the support region local service.
	 *
	 * @param supportRegionLocalService the support region local service
	 */
	public void setSupportRegionLocalService(
		com.liferay.osb.service.SupportRegionLocalService supportRegionLocalService) {
		this.supportRegionLocalService = supportRegionLocalService;
	}

	/**
	 * Returns the support region remote service.
	 *
	 * @return the support region remote service
	 */
	public com.liferay.osb.service.SupportRegionService getSupportRegionService() {
		return supportRegionService;
	}

	/**
	 * Sets the support region remote service.
	 *
	 * @param supportRegionService the support region remote service
	 */
	public void setSupportRegionService(
		com.liferay.osb.service.SupportRegionService supportRegionService) {
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
	public com.liferay.osb.service.SupportResponseLocalService getSupportResponseLocalService() {
		return supportResponseLocalService;
	}

	/**
	 * Sets the support response local service.
	 *
	 * @param supportResponseLocalService the support response local service
	 */
	public void setSupportResponseLocalService(
		com.liferay.osb.service.SupportResponseLocalService supportResponseLocalService) {
		this.supportResponseLocalService = supportResponseLocalService;
	}

	/**
	 * Returns the support response remote service.
	 *
	 * @return the support response remote service
	 */
	public com.liferay.osb.service.SupportResponseService getSupportResponseService() {
		return supportResponseService;
	}

	/**
	 * Sets the support response remote service.
	 *
	 * @param supportResponseService the support response remote service
	 */
	public void setSupportResponseService(
		com.liferay.osb.service.SupportResponseService supportResponseService) {
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
	public com.liferay.osb.service.SupportTeamLocalService getSupportTeamLocalService() {
		return supportTeamLocalService;
	}

	/**
	 * Sets the support team local service.
	 *
	 * @param supportTeamLocalService the support team local service
	 */
	public void setSupportTeamLocalService(
		com.liferay.osb.service.SupportTeamLocalService supportTeamLocalService) {
		this.supportTeamLocalService = supportTeamLocalService;
	}

	/**
	 * Returns the support team remote service.
	 *
	 * @return the support team remote service
	 */
	public com.liferay.osb.service.SupportTeamService getSupportTeamService() {
		return supportTeamService;
	}

	/**
	 * Sets the support team remote service.
	 *
	 * @param supportTeamService the support team remote service
	 */
	public void setSupportTeamService(
		com.liferay.osb.service.SupportTeamService supportTeamService) {
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
	public com.liferay.osb.service.SupportTeamLanguageLocalService getSupportTeamLanguageLocalService() {
		return supportTeamLanguageLocalService;
	}

	/**
	 * Sets the support team language local service.
	 *
	 * @param supportTeamLanguageLocalService the support team language local service
	 */
	public void setSupportTeamLanguageLocalService(
		com.liferay.osb.service.SupportTeamLanguageLocalService supportTeamLanguageLocalService) {
		this.supportTeamLanguageLocalService = supportTeamLanguageLocalService;
	}

	/**
	 * Returns the support team language remote service.
	 *
	 * @return the support team language remote service
	 */
	public com.liferay.osb.service.SupportTeamLanguageService getSupportTeamLanguageService() {
		return supportTeamLanguageService;
	}

	/**
	 * Sets the support team language remote service.
	 *
	 * @param supportTeamLanguageService the support team language remote service
	 */
	public void setSupportTeamLanguageService(
		com.liferay.osb.service.SupportTeamLanguageService supportTeamLanguageService) {
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
	public com.liferay.osb.service.SupportWorkerLocalService getSupportWorkerLocalService() {
		return supportWorkerLocalService;
	}

	/**
	 * Sets the support worker local service.
	 *
	 * @param supportWorkerLocalService the support worker local service
	 */
	public void setSupportWorkerLocalService(
		com.liferay.osb.service.SupportWorkerLocalService supportWorkerLocalService) {
		this.supportWorkerLocalService = supportWorkerLocalService;
	}

	/**
	 * Returns the support worker remote service.
	 *
	 * @return the support worker remote service
	 */
	public com.liferay.osb.service.SupportWorkerService getSupportWorkerService() {
		return supportWorkerService;
	}

	/**
	 * Sets the support worker remote service.
	 *
	 * @param supportWorkerService the support worker remote service
	 */
	public void setSupportWorkerService(
		com.liferay.osb.service.SupportWorkerService supportWorkerService) {
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
	public com.liferay.osb.service.SupportWorkerAccountTierLocalService getSupportWorkerAccountTierLocalService() {
		return supportWorkerAccountTierLocalService;
	}

	/**
	 * Sets the support worker account tier local service.
	 *
	 * @param supportWorkerAccountTierLocalService the support worker account tier local service
	 */
	public void setSupportWorkerAccountTierLocalService(
		com.liferay.osb.service.SupportWorkerAccountTierLocalService supportWorkerAccountTierLocalService) {
		this.supportWorkerAccountTierLocalService = supportWorkerAccountTierLocalService;
	}

	/**
	 * Returns the support worker account tier remote service.
	 *
	 * @return the support worker account tier remote service
	 */
	public com.liferay.osb.service.SupportWorkerAccountTierService getSupportWorkerAccountTierService() {
		return supportWorkerAccountTierService;
	}

	/**
	 * Sets the support worker account tier remote service.
	 *
	 * @param supportWorkerAccountTierService the support worker account tier remote service
	 */
	public void setSupportWorkerAccountTierService(
		com.liferay.osb.service.SupportWorkerAccountTierService supportWorkerAccountTierService) {
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
	public com.liferay.osb.service.SupportWorkerComponentLocalService getSupportWorkerComponentLocalService() {
		return supportWorkerComponentLocalService;
	}

	/**
	 * Sets the support worker component local service.
	 *
	 * @param supportWorkerComponentLocalService the support worker component local service
	 */
	public void setSupportWorkerComponentLocalService(
		com.liferay.osb.service.SupportWorkerComponentLocalService supportWorkerComponentLocalService) {
		this.supportWorkerComponentLocalService = supportWorkerComponentLocalService;
	}

	/**
	 * Returns the support worker component remote service.
	 *
	 * @return the support worker component remote service
	 */
	public com.liferay.osb.service.SupportWorkerComponentService getSupportWorkerComponentService() {
		return supportWorkerComponentService;
	}

	/**
	 * Sets the support worker component remote service.
	 *
	 * @param supportWorkerComponentService the support worker component remote service
	 */
	public void setSupportWorkerComponentService(
		com.liferay.osb.service.SupportWorkerComponentService supportWorkerComponentService) {
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
	public com.liferay.osb.service.SupportWorkerSeverityLocalService getSupportWorkerSeverityLocalService() {
		return supportWorkerSeverityLocalService;
	}

	/**
	 * Sets the support worker severity local service.
	 *
	 * @param supportWorkerSeverityLocalService the support worker severity local service
	 */
	public void setSupportWorkerSeverityLocalService(
		com.liferay.osb.service.SupportWorkerSeverityLocalService supportWorkerSeverityLocalService) {
		this.supportWorkerSeverityLocalService = supportWorkerSeverityLocalService;
	}

	/**
	 * Returns the support worker severity remote service.
	 *
	 * @return the support worker severity remote service
	 */
	public com.liferay.osb.service.SupportWorkerSeverityService getSupportWorkerSeverityService() {
		return supportWorkerSeverityService;
	}

	/**
	 * Sets the support worker severity remote service.
	 *
	 * @param supportWorkerSeverityService the support worker severity remote service
	 */
	public void setSupportWorkerSeverityService(
		com.liferay.osb.service.SupportWorkerSeverityService supportWorkerSeverityService) {
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
	public com.liferay.osb.service.TicketAttachmentLocalService getTicketAttachmentLocalService() {
		return ticketAttachmentLocalService;
	}

	/**
	 * Sets the ticket attachment local service.
	 *
	 * @param ticketAttachmentLocalService the ticket attachment local service
	 */
	public void setTicketAttachmentLocalService(
		com.liferay.osb.service.TicketAttachmentLocalService ticketAttachmentLocalService) {
		this.ticketAttachmentLocalService = ticketAttachmentLocalService;
	}

	/**
	 * Returns the ticket attachment remote service.
	 *
	 * @return the ticket attachment remote service
	 */
	public com.liferay.osb.service.TicketAttachmentService getTicketAttachmentService() {
		return ticketAttachmentService;
	}

	/**
	 * Sets the ticket attachment remote service.
	 *
	 * @param ticketAttachmentService the ticket attachment remote service
	 */
	public void setTicketAttachmentService(
		com.liferay.osb.service.TicketAttachmentService ticketAttachmentService) {
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
	public com.liferay.osb.service.TicketCallLocalService getTicketCallLocalService() {
		return ticketCallLocalService;
	}

	/**
	 * Sets the ticket call local service.
	 *
	 * @param ticketCallLocalService the ticket call local service
	 */
	public void setTicketCallLocalService(
		com.liferay.osb.service.TicketCallLocalService ticketCallLocalService) {
		this.ticketCallLocalService = ticketCallLocalService;
	}

	/**
	 * Returns the ticket call remote service.
	 *
	 * @return the ticket call remote service
	 */
	public com.liferay.osb.service.TicketCallService getTicketCallService() {
		return ticketCallService;
	}

	/**
	 * Sets the ticket call remote service.
	 *
	 * @param ticketCallService the ticket call remote service
	 */
	public void setTicketCallService(
		com.liferay.osb.service.TicketCallService ticketCallService) {
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
	 * Returns the ticket comment local service.
	 *
	 * @return the ticket comment local service
	 */
	public com.liferay.osb.service.TicketCommentLocalService getTicketCommentLocalService() {
		return ticketCommentLocalService;
	}

	/**
	 * Sets the ticket comment local service.
	 *
	 * @param ticketCommentLocalService the ticket comment local service
	 */
	public void setTicketCommentLocalService(
		com.liferay.osb.service.TicketCommentLocalService ticketCommentLocalService) {
		this.ticketCommentLocalService = ticketCommentLocalService;
	}

	/**
	 * Returns the ticket comment remote service.
	 *
	 * @return the ticket comment remote service
	 */
	public com.liferay.osb.service.TicketCommentService getTicketCommentService() {
		return ticketCommentService;
	}

	/**
	 * Sets the ticket comment remote service.
	 *
	 * @param ticketCommentService the ticket comment remote service
	 */
	public void setTicketCommentService(
		com.liferay.osb.service.TicketCommentService ticketCommentService) {
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
	public com.liferay.osb.service.TicketEntryLocalService getTicketEntryLocalService() {
		return ticketEntryLocalService;
	}

	/**
	 * Sets the ticket entry local service.
	 *
	 * @param ticketEntryLocalService the ticket entry local service
	 */
	public void setTicketEntryLocalService(
		com.liferay.osb.service.TicketEntryLocalService ticketEntryLocalService) {
		this.ticketEntryLocalService = ticketEntryLocalService;
	}

	/**
	 * Returns the ticket entry remote service.
	 *
	 * @return the ticket entry remote service
	 */
	public com.liferay.osb.service.TicketEntryService getTicketEntryService() {
		return ticketEntryService;
	}

	/**
	 * Sets the ticket entry remote service.
	 *
	 * @param ticketEntryService the ticket entry remote service
	 */
	public void setTicketEntryService(
		com.liferay.osb.service.TicketEntryService ticketEntryService) {
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
	public com.liferay.osb.service.TicketFeedbackLocalService getTicketFeedbackLocalService() {
		return ticketFeedbackLocalService;
	}

	/**
	 * Sets the ticket feedback local service.
	 *
	 * @param ticketFeedbackLocalService the ticket feedback local service
	 */
	public void setTicketFeedbackLocalService(
		com.liferay.osb.service.TicketFeedbackLocalService ticketFeedbackLocalService) {
		this.ticketFeedbackLocalService = ticketFeedbackLocalService;
	}

	/**
	 * Returns the ticket feedback remote service.
	 *
	 * @return the ticket feedback remote service
	 */
	public com.liferay.osb.service.TicketFeedbackService getTicketFeedbackService() {
		return ticketFeedbackService;
	}

	/**
	 * Sets the ticket feedback remote service.
	 *
	 * @param ticketFeedbackService the ticket feedback remote service
	 */
	public void setTicketFeedbackService(
		com.liferay.osb.service.TicketFeedbackService ticketFeedbackService) {
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
	public com.liferay.osb.service.TicketFlagLocalService getTicketFlagLocalService() {
		return ticketFlagLocalService;
	}

	/**
	 * Sets the ticket flag local service.
	 *
	 * @param ticketFlagLocalService the ticket flag local service
	 */
	public void setTicketFlagLocalService(
		com.liferay.osb.service.TicketFlagLocalService ticketFlagLocalService) {
		this.ticketFlagLocalService = ticketFlagLocalService;
	}

	/**
	 * Returns the ticket flag remote service.
	 *
	 * @return the ticket flag remote service
	 */
	public com.liferay.osb.service.TicketFlagService getTicketFlagService() {
		return ticketFlagService;
	}

	/**
	 * Sets the ticket flag remote service.
	 *
	 * @param ticketFlagService the ticket flag remote service
	 */
	public void setTicketFlagService(
		com.liferay.osb.service.TicketFlagService ticketFlagService) {
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
	public com.liferay.osb.service.TicketInformationLocalService getTicketInformationLocalService() {
		return ticketInformationLocalService;
	}

	/**
	 * Sets the ticket information local service.
	 *
	 * @param ticketInformationLocalService the ticket information local service
	 */
	public void setTicketInformationLocalService(
		com.liferay.osb.service.TicketInformationLocalService ticketInformationLocalService) {
		this.ticketInformationLocalService = ticketInformationLocalService;
	}

	/**
	 * Returns the ticket information remote service.
	 *
	 * @return the ticket information remote service
	 */
	public com.liferay.osb.service.TicketInformationService getTicketInformationService() {
		return ticketInformationService;
	}

	/**
	 * Sets the ticket information remote service.
	 *
	 * @param ticketInformationService the ticket information remote service
	 */
	public void setTicketInformationService(
		com.liferay.osb.service.TicketInformationService ticketInformationService) {
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
	public com.liferay.osb.service.TicketLinkLocalService getTicketLinkLocalService() {
		return ticketLinkLocalService;
	}

	/**
	 * Sets the ticket link local service.
	 *
	 * @param ticketLinkLocalService the ticket link local service
	 */
	public void setTicketLinkLocalService(
		com.liferay.osb.service.TicketLinkLocalService ticketLinkLocalService) {
		this.ticketLinkLocalService = ticketLinkLocalService;
	}

	/**
	 * Returns the ticket link remote service.
	 *
	 * @return the ticket link remote service
	 */
	public com.liferay.osb.service.TicketLinkService getTicketLinkService() {
		return ticketLinkService;
	}

	/**
	 * Sets the ticket link remote service.
	 *
	 * @param ticketLinkService the ticket link remote service
	 */
	public void setTicketLinkService(
		com.liferay.osb.service.TicketLinkService ticketLinkService) {
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
	public com.liferay.osb.service.TicketSolutionLocalService getTicketSolutionLocalService() {
		return ticketSolutionLocalService;
	}

	/**
	 * Sets the ticket solution local service.
	 *
	 * @param ticketSolutionLocalService the ticket solution local service
	 */
	public void setTicketSolutionLocalService(
		com.liferay.osb.service.TicketSolutionLocalService ticketSolutionLocalService) {
		this.ticketSolutionLocalService = ticketSolutionLocalService;
	}

	/**
	 * Returns the ticket solution remote service.
	 *
	 * @return the ticket solution remote service
	 */
	public com.liferay.osb.service.TicketSolutionService getTicketSolutionService() {
		return ticketSolutionService;
	}

	/**
	 * Sets the ticket solution remote service.
	 *
	 * @param ticketSolutionService the ticket solution remote service
	 */
	public void setTicketSolutionService(
		com.liferay.osb.service.TicketSolutionService ticketSolutionService) {
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
	public com.liferay.osb.service.TicketWorkerLocalService getTicketWorkerLocalService() {
		return ticketWorkerLocalService;
	}

	/**
	 * Sets the ticket worker local service.
	 *
	 * @param ticketWorkerLocalService the ticket worker local service
	 */
	public void setTicketWorkerLocalService(
		com.liferay.osb.service.TicketWorkerLocalService ticketWorkerLocalService) {
		this.ticketWorkerLocalService = ticketWorkerLocalService;
	}

	/**
	 * Returns the ticket worker remote service.
	 *
	 * @return the ticket worker remote service
	 */
	public com.liferay.osb.service.TicketWorkerService getTicketWorkerService() {
		return ticketWorkerService;
	}

	/**
	 * Sets the ticket worker remote service.
	 *
	 * @param ticketWorkerService the ticket worker remote service
	 */
	public void setTicketWorkerService(
		com.liferay.osb.service.TicketWorkerService ticketWorkerService) {
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
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {
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
	 * Returns the list type local service.
	 *
	 * @return the list type local service
	 */
	public com.liferay.portal.kernel.service.ListTypeLocalService getListTypeLocalService() {
		return listTypeLocalService;
	}

	/**
	 * Sets the list type local service.
	 *
	 * @param listTypeLocalService the list type local service
	 */
	public void setListTypeLocalService(
		com.liferay.portal.kernel.service.ListTypeLocalService listTypeLocalService) {
		this.listTypeLocalService = listTypeLocalService;
	}

	/**
	 * Returns the list type remote service.
	 *
	 * @return the list type remote service
	 */
	public com.liferay.portal.kernel.service.ListTypeService getListTypeService() {
		return listTypeService;
	}

	/**
	 * Sets the list type remote service.
	 *
	 * @param listTypeService the list type remote service
	 */
	public void setListTypeService(
		com.liferay.portal.kernel.service.ListTypeService listTypeService) {
		this.listTypeService = listTypeService;
	}

	/**
	 * Returns the list type persistence.
	 *
	 * @return the list type persistence
	 */
	public ListTypePersistence getListTypePersistence() {
		return listTypePersistence;
	}

	/**
	 * Sets the list type persistence.
	 *
	 * @param listTypePersistence the list type persistence
	 */
	public void setListTypePersistence(ListTypePersistence listTypePersistence) {
		this.listTypePersistence = listTypePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {
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
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AccountEnvironmentService.class.getName();
	}

	@Override
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
		return AccountEnvironment.class;
	}

	protected String getModelClassName() {
		return AccountEnvironment.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = accountEnvironmentPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.osb.service.AccountAttachmentLocalService.class)
	protected com.liferay.osb.service.AccountAttachmentLocalService accountAttachmentLocalService;
	@BeanReference(type = com.liferay.osb.service.AccountAttachmentService.class)
	protected com.liferay.osb.service.AccountAttachmentService accountAttachmentService;
	@BeanReference(type = AccountAttachmentPersistence.class)
	protected AccountAttachmentPersistence accountAttachmentPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountCallLocalService.class)
	protected com.liferay.osb.service.AccountCallLocalService accountCallLocalService;
	@BeanReference(type = com.liferay.osb.service.AccountCallService.class)
	protected com.liferay.osb.service.AccountCallService accountCallService;
	@BeanReference(type = AccountCallPersistence.class)
	protected AccountCallPersistence accountCallPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountCustomerLocalService.class)
	protected com.liferay.osb.service.AccountCustomerLocalService accountCustomerLocalService;
	@BeanReference(type = com.liferay.osb.service.AccountCustomerService.class)
	protected com.liferay.osb.service.AccountCustomerService accountCustomerService;
	@BeanReference(type = AccountCustomerPersistence.class)
	protected AccountCustomerPersistence accountCustomerPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountEntryLocalService.class)
	protected com.liferay.osb.service.AccountEntryLocalService accountEntryLocalService;
	@BeanReference(type = com.liferay.osb.service.AccountEntryService.class)
	protected com.liferay.osb.service.AccountEntryService accountEntryService;
	@BeanReference(type = AccountEntryPersistence.class)
	protected AccountEntryPersistence accountEntryPersistence;
	@BeanReference(type = AccountEntryFinder.class)
	protected AccountEntryFinder accountEntryFinder;
	@BeanReference(type = com.liferay.osb.service.AccountEntryLanguageLocalService.class)
	protected com.liferay.osb.service.AccountEntryLanguageLocalService accountEntryLanguageLocalService;
	@BeanReference(type = com.liferay.osb.service.AccountEntryLanguageService.class)
	protected com.liferay.osb.service.AccountEntryLanguageService accountEntryLanguageService;
	@BeanReference(type = AccountEntryLanguagePersistence.class)
	protected AccountEntryLanguagePersistence accountEntryLanguagePersistence;
	@BeanReference(type = com.liferay.osb.service.AccountEnvironmentLocalService.class)
	protected com.liferay.osb.service.AccountEnvironmentLocalService accountEnvironmentLocalService;
	@BeanReference(type = AccountEnvironmentService.class)
	protected AccountEnvironmentService accountEnvironmentService;
	@BeanReference(type = AccountEnvironmentPersistence.class)
	protected AccountEnvironmentPersistence accountEnvironmentPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountEnvironmentAttachmentLocalService.class)
	protected com.liferay.osb.service.AccountEnvironmentAttachmentLocalService accountEnvironmentAttachmentLocalService;
	@BeanReference(type = com.liferay.osb.service.AccountEnvironmentAttachmentService.class)
	protected com.liferay.osb.service.AccountEnvironmentAttachmentService accountEnvironmentAttachmentService;
	@BeanReference(type = AccountEnvironmentAttachmentPersistence.class)
	protected AccountEnvironmentAttachmentPersistence accountEnvironmentAttachmentPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountInformationLocalService.class)
	protected com.liferay.osb.service.AccountInformationLocalService accountInformationLocalService;
	@BeanReference(type = com.liferay.osb.service.AccountInformationService.class)
	protected com.liferay.osb.service.AccountInformationService accountInformationService;
	@BeanReference(type = AccountInformationPersistence.class)
	protected AccountInformationPersistence accountInformationPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountLinkLocalService.class)
	protected com.liferay.osb.service.AccountLinkLocalService accountLinkLocalService;
	@BeanReference(type = com.liferay.osb.service.AccountLinkService.class)
	protected com.liferay.osb.service.AccountLinkService accountLinkService;
	@BeanReference(type = AccountLinkPersistence.class)
	protected AccountLinkPersistence accountLinkPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountProjectLocalService.class)
	protected com.liferay.osb.service.AccountProjectLocalService accountProjectLocalService;
	@BeanReference(type = com.liferay.osb.service.AccountProjectService.class)
	protected com.liferay.osb.service.AccountProjectService accountProjectService;
	@BeanReference(type = AccountProjectPersistence.class)
	protected AccountProjectPersistence accountProjectPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountWorkerLocalService.class)
	protected com.liferay.osb.service.AccountWorkerLocalService accountWorkerLocalService;
	@BeanReference(type = com.liferay.osb.service.AccountWorkerService.class)
	protected com.liferay.osb.service.AccountWorkerService accountWorkerService;
	@BeanReference(type = AccountWorkerPersistence.class)
	protected AccountWorkerPersistence accountWorkerPersistence;
	@BeanReference(type = com.liferay.osb.service.AuditActionLocalService.class)
	protected com.liferay.osb.service.AuditActionLocalService auditActionLocalService;
	@BeanReference(type = com.liferay.osb.service.AuditActionService.class)
	protected com.liferay.osb.service.AuditActionService auditActionService;
	@BeanReference(type = AuditActionPersistence.class)
	protected AuditActionPersistence auditActionPersistence;
	@BeanReference(type = com.liferay.osb.service.AuditEntryLocalService.class)
	protected com.liferay.osb.service.AuditEntryLocalService auditEntryLocalService;
	@BeanReference(type = com.liferay.osb.service.AuditEntryService.class)
	protected com.liferay.osb.service.AuditEntryService auditEntryService;
	@BeanReference(type = AuditEntryPersistence.class)
	protected AuditEntryPersistence auditEntryPersistence;
	@BeanReference(type = com.liferay.osb.service.CorpProjectLocalService.class)
	protected com.liferay.osb.service.CorpProjectLocalService corpProjectLocalService;
	@BeanReference(type = com.liferay.osb.service.CorpProjectService.class)
	protected com.liferay.osb.service.CorpProjectService corpProjectService;
	@BeanReference(type = CorpProjectPersistence.class)
	protected CorpProjectPersistence corpProjectPersistence;
	@BeanReference(type = com.liferay.osb.service.CorpProjectMessageLocalService.class)
	protected com.liferay.osb.service.CorpProjectMessageLocalService corpProjectMessageLocalService;
	@BeanReference(type = com.liferay.osb.service.CorpProjectMessageService.class)
	protected com.liferay.osb.service.CorpProjectMessageService corpProjectMessageService;
	@BeanReference(type = CorpProjectMessagePersistence.class)
	protected CorpProjectMessagePersistence corpProjectMessagePersistence;
	@BeanReference(type = com.liferay.osb.service.ExternalIdMapperLocalService.class)
	protected com.liferay.osb.service.ExternalIdMapperLocalService externalIdMapperLocalService;
	@BeanReference(type = ExternalIdMapperPersistence.class)
	protected ExternalIdMapperPersistence externalIdMapperPersistence;
	@BeanReference(type = com.liferay.osb.service.FeedbackEntryLocalService.class)
	protected com.liferay.osb.service.FeedbackEntryLocalService feedbackEntryLocalService;
	@BeanReference(type = com.liferay.osb.service.FeedbackEntryService.class)
	protected com.liferay.osb.service.FeedbackEntryService feedbackEntryService;
	@BeanReference(type = FeedbackEntryPersistence.class)
	protected FeedbackEntryPersistence feedbackEntryPersistence;
	@BeanReference(type = com.liferay.osb.service.HolidayCalendarLocalService.class)
	protected com.liferay.osb.service.HolidayCalendarLocalService holidayCalendarLocalService;
	@BeanReference(type = com.liferay.osb.service.HolidayCalendarService.class)
	protected com.liferay.osb.service.HolidayCalendarService holidayCalendarService;
	@BeanReference(type = HolidayCalendarPersistence.class)
	protected HolidayCalendarPersistence holidayCalendarPersistence;
	@BeanReference(type = com.liferay.osb.service.HolidayCalendarRelLocalService.class)
	protected com.liferay.osb.service.HolidayCalendarRelLocalService holidayCalendarRelLocalService;
	@BeanReference(type = com.liferay.osb.service.HolidayCalendarRelService.class)
	protected com.liferay.osb.service.HolidayCalendarRelService holidayCalendarRelService;
	@BeanReference(type = HolidayCalendarRelPersistence.class)
	protected HolidayCalendarRelPersistence holidayCalendarRelPersistence;
	@BeanReference(type = com.liferay.osb.service.HolidayEntryLocalService.class)
	protected com.liferay.osb.service.HolidayEntryLocalService holidayEntryLocalService;
	@BeanReference(type = com.liferay.osb.service.HolidayEntryService.class)
	protected com.liferay.osb.service.HolidayEntryService holidayEntryService;
	@BeanReference(type = HolidayEntryPersistence.class)
	protected HolidayEntryPersistence holidayEntryPersistence;
	@BeanReference(type = HolidayEntryFinder.class)
	protected HolidayEntryFinder holidayEntryFinder;
	@BeanReference(type = com.liferay.osb.service.LCSSubscriptionEntryLocalService.class)
	protected com.liferay.osb.service.LCSSubscriptionEntryLocalService lcsSubscriptionEntryLocalService;
	@BeanReference(type = LCSSubscriptionEntryPersistence.class)
	protected LCSSubscriptionEntryPersistence lcsSubscriptionEntryPersistence;
	@BeanReference(type = com.liferay.osb.service.LicenseEntryLocalService.class)
	protected com.liferay.osb.service.LicenseEntryLocalService licenseEntryLocalService;
	@BeanReference(type = com.liferay.osb.service.LicenseEntryService.class)
	protected com.liferay.osb.service.LicenseEntryService licenseEntryService;
	@BeanReference(type = LicenseEntryPersistence.class)
	protected LicenseEntryPersistence licenseEntryPersistence;
	@BeanReference(type = com.liferay.osb.service.LicenseKeyLocalService.class)
	protected com.liferay.osb.service.LicenseKeyLocalService licenseKeyLocalService;
	@BeanReference(type = com.liferay.osb.service.LicenseKeyService.class)
	protected com.liferay.osb.service.LicenseKeyService licenseKeyService;
	@BeanReference(type = LicenseKeyPersistence.class)
	protected LicenseKeyPersistence licenseKeyPersistence;
	@BeanReference(type = LicenseKeyFinder.class)
	protected LicenseKeyFinder licenseKeyFinder;
	@BeanReference(type = com.liferay.osb.service.LicenseKeySetLocalService.class)
	protected com.liferay.osb.service.LicenseKeySetLocalService licenseKeySetLocalService;
	@BeanReference(type = com.liferay.osb.service.LicenseKeySetService.class)
	protected com.liferay.osb.service.LicenseKeySetService licenseKeySetService;
	@BeanReference(type = LicenseKeySetPersistence.class)
	protected LicenseKeySetPersistence licenseKeySetPersistence;
	@BeanReference(type = com.liferay.osb.service.OfferingBundleLocalService.class)
	protected com.liferay.osb.service.OfferingBundleLocalService offeringBundleLocalService;
	@BeanReference(type = com.liferay.osb.service.OfferingBundleService.class)
	protected com.liferay.osb.service.OfferingBundleService offeringBundleService;
	@BeanReference(type = OfferingBundlePersistence.class)
	protected OfferingBundlePersistence offeringBundlePersistence;
	@BeanReference(type = com.liferay.osb.service.OfferingDefinitionLocalService.class)
	protected com.liferay.osb.service.OfferingDefinitionLocalService offeringDefinitionLocalService;
	@BeanReference(type = com.liferay.osb.service.OfferingDefinitionService.class)
	protected com.liferay.osb.service.OfferingDefinitionService offeringDefinitionService;
	@BeanReference(type = OfferingDefinitionPersistence.class)
	protected OfferingDefinitionPersistence offeringDefinitionPersistence;
	@BeanReference(type = com.liferay.osb.service.OfferingEntryLocalService.class)
	protected com.liferay.osb.service.OfferingEntryLocalService offeringEntryLocalService;
	@BeanReference(type = com.liferay.osb.service.OfferingEntryService.class)
	protected com.liferay.osb.service.OfferingEntryService offeringEntryService;
	@BeanReference(type = OfferingEntryPersistence.class)
	protected OfferingEntryPersistence offeringEntryPersistence;
	@BeanReference(type = OfferingEntryFinder.class)
	protected OfferingEntryFinder offeringEntryFinder;
	@BeanReference(type = com.liferay.osb.service.OrderEntryLocalService.class)
	protected com.liferay.osb.service.OrderEntryLocalService orderEntryLocalService;
	@BeanReference(type = com.liferay.osb.service.OrderEntryService.class)
	protected com.liferay.osb.service.OrderEntryService orderEntryService;
	@BeanReference(type = OrderEntryPersistence.class)
	protected OrderEntryPersistence orderEntryPersistence;
	@BeanReference(type = OrderEntryFinder.class)
	protected OrderEntryFinder orderEntryFinder;
	@BeanReference(type = com.liferay.osb.service.PartnerEntryLocalService.class)
	protected com.liferay.osb.service.PartnerEntryLocalService partnerEntryLocalService;
	@BeanReference(type = com.liferay.osb.service.PartnerEntryService.class)
	protected com.liferay.osb.service.PartnerEntryService partnerEntryService;
	@BeanReference(type = PartnerEntryPersistence.class)
	protected PartnerEntryPersistence partnerEntryPersistence;
	@BeanReference(type = PartnerEntryFinder.class)
	protected PartnerEntryFinder partnerEntryFinder;
	@BeanReference(type = com.liferay.osb.service.PartnerWorkerLocalService.class)
	protected com.liferay.osb.service.PartnerWorkerLocalService partnerWorkerLocalService;
	@BeanReference(type = com.liferay.osb.service.PartnerWorkerService.class)
	protected com.liferay.osb.service.PartnerWorkerService partnerWorkerService;
	@BeanReference(type = PartnerWorkerPersistence.class)
	protected PartnerWorkerPersistence partnerWorkerPersistence;
	@BeanReference(type = com.liferay.osb.service.ProductEntryLocalService.class)
	protected com.liferay.osb.service.ProductEntryLocalService productEntryLocalService;
	@BeanReference(type = com.liferay.osb.service.ProductEntryService.class)
	protected com.liferay.osb.service.ProductEntryService productEntryService;
	@BeanReference(type = ProductEntryPersistence.class)
	protected ProductEntryPersistence productEntryPersistence;
	@BeanReference(type = ProductEntryFinder.class)
	protected ProductEntryFinder productEntryFinder;
	@BeanReference(type = com.liferay.osb.service.RemoteCorpProjectLocalService.class)
	protected com.liferay.osb.service.RemoteCorpProjectLocalService remoteCorpProjectLocalService;
	@BeanReference(type = com.liferay.osb.service.RemoteCorpProjectMessageLocalService.class)
	protected com.liferay.osb.service.RemoteCorpProjectMessageLocalService remoteCorpProjectMessageLocalService;
	@BeanReference(type = com.liferay.osb.service.RemoteUserLocalService.class)
	protected com.liferay.osb.service.RemoteUserLocalService remoteUserLocalService;
	@BeanReference(type = com.liferay.osb.service.SearchFilterLocalService.class)
	protected com.liferay.osb.service.SearchFilterLocalService searchFilterLocalService;
	@BeanReference(type = com.liferay.osb.service.SearchFilterService.class)
	protected com.liferay.osb.service.SearchFilterService searchFilterService;
	@BeanReference(type = SearchFilterPersistence.class)
	protected SearchFilterPersistence searchFilterPersistence;
	@BeanReference(type = com.liferay.osb.service.SecurityPatchLocalService.class)
	protected com.liferay.osb.service.SecurityPatchLocalService securityPatchLocalService;
	@BeanReference(type = com.liferay.osb.service.SecurityPatchService.class)
	protected com.liferay.osb.service.SecurityPatchService securityPatchService;
	@BeanReference(type = SecurityPatchPersistence.class)
	protected SecurityPatchPersistence securityPatchPersistence;
	@BeanReference(type = com.liferay.osb.service.SupportLaborLocalService.class)
	protected com.liferay.osb.service.SupportLaborLocalService supportLaborLocalService;
	@BeanReference(type = com.liferay.osb.service.SupportLaborService.class)
	protected com.liferay.osb.service.SupportLaborService supportLaborService;
	@BeanReference(type = SupportLaborPersistence.class)
	protected SupportLaborPersistence supportLaborPersistence;
	@BeanReference(type = com.liferay.osb.service.SupportRegionLocalService.class)
	protected com.liferay.osb.service.SupportRegionLocalService supportRegionLocalService;
	@BeanReference(type = com.liferay.osb.service.SupportRegionService.class)
	protected com.liferay.osb.service.SupportRegionService supportRegionService;
	@BeanReference(type = SupportRegionPersistence.class)
	protected SupportRegionPersistence supportRegionPersistence;
	@BeanReference(type = com.liferay.osb.service.SupportResponseLocalService.class)
	protected com.liferay.osb.service.SupportResponseLocalService supportResponseLocalService;
	@BeanReference(type = com.liferay.osb.service.SupportResponseService.class)
	protected com.liferay.osb.service.SupportResponseService supportResponseService;
	@BeanReference(type = SupportResponsePersistence.class)
	protected SupportResponsePersistence supportResponsePersistence;
	@BeanReference(type = SupportResponseFinder.class)
	protected SupportResponseFinder supportResponseFinder;
	@BeanReference(type = com.liferay.osb.service.SupportTeamLocalService.class)
	protected com.liferay.osb.service.SupportTeamLocalService supportTeamLocalService;
	@BeanReference(type = com.liferay.osb.service.SupportTeamService.class)
	protected com.liferay.osb.service.SupportTeamService supportTeamService;
	@BeanReference(type = SupportTeamPersistence.class)
	protected SupportTeamPersistence supportTeamPersistence;
	@BeanReference(type = SupportTeamFinder.class)
	protected SupportTeamFinder supportTeamFinder;
	@BeanReference(type = com.liferay.osb.service.SupportTeamLanguageLocalService.class)
	protected com.liferay.osb.service.SupportTeamLanguageLocalService supportTeamLanguageLocalService;
	@BeanReference(type = com.liferay.osb.service.SupportTeamLanguageService.class)
	protected com.liferay.osb.service.SupportTeamLanguageService supportTeamLanguageService;
	@BeanReference(type = SupportTeamLanguagePersistence.class)
	protected SupportTeamLanguagePersistence supportTeamLanguagePersistence;
	@BeanReference(type = com.liferay.osb.service.SupportWorkerLocalService.class)
	protected com.liferay.osb.service.SupportWorkerLocalService supportWorkerLocalService;
	@BeanReference(type = com.liferay.osb.service.SupportWorkerService.class)
	protected com.liferay.osb.service.SupportWorkerService supportWorkerService;
	@BeanReference(type = SupportWorkerPersistence.class)
	protected SupportWorkerPersistence supportWorkerPersistence;
	@BeanReference(type = SupportWorkerFinder.class)
	protected SupportWorkerFinder supportWorkerFinder;
	@BeanReference(type = com.liferay.osb.service.SupportWorkerAccountTierLocalService.class)
	protected com.liferay.osb.service.SupportWorkerAccountTierLocalService supportWorkerAccountTierLocalService;
	@BeanReference(type = com.liferay.osb.service.SupportWorkerAccountTierService.class)
	protected com.liferay.osb.service.SupportWorkerAccountTierService supportWorkerAccountTierService;
	@BeanReference(type = SupportWorkerAccountTierPersistence.class)
	protected SupportWorkerAccountTierPersistence supportWorkerAccountTierPersistence;
	@BeanReference(type = com.liferay.osb.service.SupportWorkerComponentLocalService.class)
	protected com.liferay.osb.service.SupportWorkerComponentLocalService supportWorkerComponentLocalService;
	@BeanReference(type = com.liferay.osb.service.SupportWorkerComponentService.class)
	protected com.liferay.osb.service.SupportWorkerComponentService supportWorkerComponentService;
	@BeanReference(type = SupportWorkerComponentPersistence.class)
	protected SupportWorkerComponentPersistence supportWorkerComponentPersistence;
	@BeanReference(type = com.liferay.osb.service.SupportWorkerSeverityLocalService.class)
	protected com.liferay.osb.service.SupportWorkerSeverityLocalService supportWorkerSeverityLocalService;
	@BeanReference(type = com.liferay.osb.service.SupportWorkerSeverityService.class)
	protected com.liferay.osb.service.SupportWorkerSeverityService supportWorkerSeverityService;
	@BeanReference(type = SupportWorkerSeverityPersistence.class)
	protected SupportWorkerSeverityPersistence supportWorkerSeverityPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketAttachmentLocalService.class)
	protected com.liferay.osb.service.TicketAttachmentLocalService ticketAttachmentLocalService;
	@BeanReference(type = com.liferay.osb.service.TicketAttachmentService.class)
	protected com.liferay.osb.service.TicketAttachmentService ticketAttachmentService;
	@BeanReference(type = TicketAttachmentPersistence.class)
	protected TicketAttachmentPersistence ticketAttachmentPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketCallLocalService.class)
	protected com.liferay.osb.service.TicketCallLocalService ticketCallLocalService;
	@BeanReference(type = com.liferay.osb.service.TicketCallService.class)
	protected com.liferay.osb.service.TicketCallService ticketCallService;
	@BeanReference(type = TicketCallPersistence.class)
	protected TicketCallPersistence ticketCallPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketCommentLocalService.class)
	protected com.liferay.osb.service.TicketCommentLocalService ticketCommentLocalService;
	@BeanReference(type = com.liferay.osb.service.TicketCommentService.class)
	protected com.liferay.osb.service.TicketCommentService ticketCommentService;
	@BeanReference(type = TicketCommentPersistence.class)
	protected TicketCommentPersistence ticketCommentPersistence;
	@BeanReference(type = TicketCommentFinder.class)
	protected TicketCommentFinder ticketCommentFinder;
	@BeanReference(type = com.liferay.osb.service.TicketEntryLocalService.class)
	protected com.liferay.osb.service.TicketEntryLocalService ticketEntryLocalService;
	@BeanReference(type = com.liferay.osb.service.TicketEntryService.class)
	protected com.liferay.osb.service.TicketEntryService ticketEntryService;
	@BeanReference(type = TicketEntryPersistence.class)
	protected TicketEntryPersistence ticketEntryPersistence;
	@BeanReference(type = TicketEntryFinder.class)
	protected TicketEntryFinder ticketEntryFinder;
	@BeanReference(type = com.liferay.osb.service.TicketFeedbackLocalService.class)
	protected com.liferay.osb.service.TicketFeedbackLocalService ticketFeedbackLocalService;
	@BeanReference(type = com.liferay.osb.service.TicketFeedbackService.class)
	protected com.liferay.osb.service.TicketFeedbackService ticketFeedbackService;
	@BeanReference(type = TicketFeedbackPersistence.class)
	protected TicketFeedbackPersistence ticketFeedbackPersistence;
	@BeanReference(type = TicketFeedbackFinder.class)
	protected TicketFeedbackFinder ticketFeedbackFinder;
	@BeanReference(type = com.liferay.osb.service.TicketFlagLocalService.class)
	protected com.liferay.osb.service.TicketFlagLocalService ticketFlagLocalService;
	@BeanReference(type = com.liferay.osb.service.TicketFlagService.class)
	protected com.liferay.osb.service.TicketFlagService ticketFlagService;
	@BeanReference(type = TicketFlagPersistence.class)
	protected TicketFlagPersistence ticketFlagPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketInformationLocalService.class)
	protected com.liferay.osb.service.TicketInformationLocalService ticketInformationLocalService;
	@BeanReference(type = com.liferay.osb.service.TicketInformationService.class)
	protected com.liferay.osb.service.TicketInformationService ticketInformationService;
	@BeanReference(type = TicketInformationPersistence.class)
	protected TicketInformationPersistence ticketInformationPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketLinkLocalService.class)
	protected com.liferay.osb.service.TicketLinkLocalService ticketLinkLocalService;
	@BeanReference(type = com.liferay.osb.service.TicketLinkService.class)
	protected com.liferay.osb.service.TicketLinkService ticketLinkService;
	@BeanReference(type = TicketLinkPersistence.class)
	protected TicketLinkPersistence ticketLinkPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketSolutionLocalService.class)
	protected com.liferay.osb.service.TicketSolutionLocalService ticketSolutionLocalService;
	@BeanReference(type = com.liferay.osb.service.TicketSolutionService.class)
	protected com.liferay.osb.service.TicketSolutionService ticketSolutionService;
	@BeanReference(type = TicketSolutionPersistence.class)
	protected TicketSolutionPersistence ticketSolutionPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketWorkerLocalService.class)
	protected com.liferay.osb.service.TicketWorkerLocalService ticketWorkerLocalService;
	@BeanReference(type = com.liferay.osb.service.TicketWorkerService.class)
	protected com.liferay.osb.service.TicketWorkerService ticketWorkerService;
	@BeanReference(type = TicketWorkerPersistence.class)
	protected TicketWorkerPersistence ticketWorkerPersistence;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.ClassNameService.class)
	protected com.liferay.portal.kernel.service.ClassNameService classNameService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.ListTypeLocalService.class)
	protected com.liferay.portal.kernel.service.ListTypeLocalService listTypeLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.ListTypeService.class)
	protected com.liferay.portal.kernel.service.ListTypeService listTypeService;
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private ClassLoader _classLoader;
	private AccountEnvironmentServiceClpInvoker _clpInvoker = new AccountEnvironmentServiceClpInvoker();
}