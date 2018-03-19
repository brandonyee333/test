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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.service.ExternalIdMapperLocalService;
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
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the external ID mapper local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.osb.service.impl.ExternalIdMapperLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.impl.ExternalIdMapperLocalServiceImpl
 * @see com.liferay.osb.service.ExternalIdMapperLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class ExternalIdMapperLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements ExternalIdMapperLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.osb.service.ExternalIdMapperLocalServiceUtil} to access the external ID mapper local service.
	 */

	/**
	 * Adds the external ID mapper to the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapper the external ID mapper
	 * @return the external ID mapper that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ExternalIdMapper addExternalIdMapper(
		ExternalIdMapper externalIdMapper) {
		externalIdMapper.setNew(true);

		return externalIdMapperPersistence.update(externalIdMapper);
	}

	/**
	 * Creates a new external ID mapper with the primary key. Does not add the external ID mapper to the database.
	 *
	 * @param externalIdMapperId the primary key for the new external ID mapper
	 * @return the new external ID mapper
	 */
	@Override
	public ExternalIdMapper createExternalIdMapper(long externalIdMapperId) {
		return externalIdMapperPersistence.create(externalIdMapperId);
	}

	/**
	 * Deletes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper that was removed
	 * @throws PortalException if a external ID mapper with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ExternalIdMapper deleteExternalIdMapper(long externalIdMapperId)
		throws PortalException {
		return externalIdMapperPersistence.remove(externalIdMapperId);
	}

	/**
	 * Deletes the external ID mapper from the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapper the external ID mapper
	 * @return the external ID mapper that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ExternalIdMapper deleteExternalIdMapper(
		ExternalIdMapper externalIdMapper) {
		return externalIdMapperPersistence.remove(externalIdMapper);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(ExternalIdMapper.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return externalIdMapperPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return externalIdMapperPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return externalIdMapperPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return externalIdMapperPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return externalIdMapperPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public ExternalIdMapper fetchExternalIdMapper(long externalIdMapperId) {
		return externalIdMapperPersistence.fetchByPrimaryKey(externalIdMapperId);
	}

	/**
	 * Returns the external ID mapper with the primary key.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper
	 * @throws PortalException if a external ID mapper with the primary key could not be found
	 */
	@Override
	public ExternalIdMapper getExternalIdMapper(long externalIdMapperId)
		throws PortalException {
		return externalIdMapperPersistence.findByPrimaryKey(externalIdMapperId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(externalIdMapperLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ExternalIdMapper.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("externalIdMapperId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(externalIdMapperLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(ExternalIdMapper.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"externalIdMapperId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(externalIdMapperLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ExternalIdMapper.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("externalIdMapperId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return externalIdMapperLocalService.deleteExternalIdMapper((ExternalIdMapper)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return externalIdMapperPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the external ID mappers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> getExternalIdMappers(int start, int end) {
		return externalIdMapperPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of external ID mappers.
	 *
	 * @return the number of external ID mappers
	 */
	@Override
	public int getExternalIdMappersCount() {
		return externalIdMapperPersistence.countAll();
	}

	/**
	 * Updates the external ID mapper in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapper the external ID mapper
	 * @return the external ID mapper that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ExternalIdMapper updateExternalIdMapper(
		ExternalIdMapper externalIdMapper) {
		return externalIdMapperPersistence.update(externalIdMapper);
	}

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
		PersistedModelLocalServiceRegistryUtil.register("com.liferay.osb.model.ExternalIdMapper",
			externalIdMapperLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.osb.model.ExternalIdMapper");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ExternalIdMapperLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ExternalIdMapper.class;
	}

	protected String getModelClassName() {
		return ExternalIdMapper.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = externalIdMapperPersistence.getDataSource();

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
	@BeanReference(type = AccountAttachmentPersistence.class)
	protected AccountAttachmentPersistence accountAttachmentPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountCallLocalService.class)
	protected com.liferay.osb.service.AccountCallLocalService accountCallLocalService;
	@BeanReference(type = AccountCallPersistence.class)
	protected AccountCallPersistence accountCallPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountCustomerLocalService.class)
	protected com.liferay.osb.service.AccountCustomerLocalService accountCustomerLocalService;
	@BeanReference(type = AccountCustomerPersistence.class)
	protected AccountCustomerPersistence accountCustomerPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountEntryLocalService.class)
	protected com.liferay.osb.service.AccountEntryLocalService accountEntryLocalService;
	@BeanReference(type = AccountEntryPersistence.class)
	protected AccountEntryPersistence accountEntryPersistence;
	@BeanReference(type = AccountEntryFinder.class)
	protected AccountEntryFinder accountEntryFinder;
	@BeanReference(type = com.liferay.osb.service.AccountEntryLanguageLocalService.class)
	protected com.liferay.osb.service.AccountEntryLanguageLocalService accountEntryLanguageLocalService;
	@BeanReference(type = AccountEntryLanguagePersistence.class)
	protected AccountEntryLanguagePersistence accountEntryLanguagePersistence;
	@BeanReference(type = com.liferay.osb.service.AccountEnvironmentLocalService.class)
	protected com.liferay.osb.service.AccountEnvironmentLocalService accountEnvironmentLocalService;
	@BeanReference(type = AccountEnvironmentPersistence.class)
	protected AccountEnvironmentPersistence accountEnvironmentPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountEnvironmentAttachmentLocalService.class)
	protected com.liferay.osb.service.AccountEnvironmentAttachmentLocalService accountEnvironmentAttachmentLocalService;
	@BeanReference(type = AccountEnvironmentAttachmentPersistence.class)
	protected AccountEnvironmentAttachmentPersistence accountEnvironmentAttachmentPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountInformationLocalService.class)
	protected com.liferay.osb.service.AccountInformationLocalService accountInformationLocalService;
	@BeanReference(type = AccountInformationPersistence.class)
	protected AccountInformationPersistence accountInformationPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountLinkLocalService.class)
	protected com.liferay.osb.service.AccountLinkLocalService accountLinkLocalService;
	@BeanReference(type = AccountLinkPersistence.class)
	protected AccountLinkPersistence accountLinkPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountProjectLocalService.class)
	protected com.liferay.osb.service.AccountProjectLocalService accountProjectLocalService;
	@BeanReference(type = AccountProjectPersistence.class)
	protected AccountProjectPersistence accountProjectPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountWorkerLocalService.class)
	protected com.liferay.osb.service.AccountWorkerLocalService accountWorkerLocalService;
	@BeanReference(type = AccountWorkerPersistence.class)
	protected AccountWorkerPersistence accountWorkerPersistence;
	@BeanReference(type = com.liferay.osb.service.AuditActionLocalService.class)
	protected com.liferay.osb.service.AuditActionLocalService auditActionLocalService;
	@BeanReference(type = AuditActionPersistence.class)
	protected AuditActionPersistence auditActionPersistence;
	@BeanReference(type = com.liferay.osb.service.AuditEntryLocalService.class)
	protected com.liferay.osb.service.AuditEntryLocalService auditEntryLocalService;
	@BeanReference(type = AuditEntryPersistence.class)
	protected AuditEntryPersistence auditEntryPersistence;
	@BeanReference(type = com.liferay.osb.service.CorpProjectLocalService.class)
	protected com.liferay.osb.service.CorpProjectLocalService corpProjectLocalService;
	@BeanReference(type = CorpProjectPersistence.class)
	protected CorpProjectPersistence corpProjectPersistence;
	@BeanReference(type = com.liferay.osb.service.CorpProjectMessageLocalService.class)
	protected com.liferay.osb.service.CorpProjectMessageLocalService corpProjectMessageLocalService;
	@BeanReference(type = CorpProjectMessagePersistence.class)
	protected CorpProjectMessagePersistence corpProjectMessagePersistence;
	@BeanReference(type = ExternalIdMapperLocalService.class)
	protected ExternalIdMapperLocalService externalIdMapperLocalService;
	@BeanReference(type = ExternalIdMapperPersistence.class)
	protected ExternalIdMapperPersistence externalIdMapperPersistence;
	@BeanReference(type = com.liferay.osb.service.FeedbackEntryLocalService.class)
	protected com.liferay.osb.service.FeedbackEntryLocalService feedbackEntryLocalService;
	@BeanReference(type = FeedbackEntryPersistence.class)
	protected FeedbackEntryPersistence feedbackEntryPersistence;
	@BeanReference(type = com.liferay.osb.service.HolidayCalendarLocalService.class)
	protected com.liferay.osb.service.HolidayCalendarLocalService holidayCalendarLocalService;
	@BeanReference(type = HolidayCalendarPersistence.class)
	protected HolidayCalendarPersistence holidayCalendarPersistence;
	@BeanReference(type = com.liferay.osb.service.HolidayCalendarRelLocalService.class)
	protected com.liferay.osb.service.HolidayCalendarRelLocalService holidayCalendarRelLocalService;
	@BeanReference(type = HolidayCalendarRelPersistence.class)
	protected HolidayCalendarRelPersistence holidayCalendarRelPersistence;
	@BeanReference(type = com.liferay.osb.service.HolidayEntryLocalService.class)
	protected com.liferay.osb.service.HolidayEntryLocalService holidayEntryLocalService;
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
	@BeanReference(type = LicenseEntryPersistence.class)
	protected LicenseEntryPersistence licenseEntryPersistence;
	@BeanReference(type = com.liferay.osb.service.LicenseKeyLocalService.class)
	protected com.liferay.osb.service.LicenseKeyLocalService licenseKeyLocalService;
	@BeanReference(type = LicenseKeyPersistence.class)
	protected LicenseKeyPersistence licenseKeyPersistence;
	@BeanReference(type = LicenseKeyFinder.class)
	protected LicenseKeyFinder licenseKeyFinder;
	@BeanReference(type = com.liferay.osb.service.LicenseKeySetLocalService.class)
	protected com.liferay.osb.service.LicenseKeySetLocalService licenseKeySetLocalService;
	@BeanReference(type = LicenseKeySetPersistence.class)
	protected LicenseKeySetPersistence licenseKeySetPersistence;
	@BeanReference(type = com.liferay.osb.service.OfferingBundleLocalService.class)
	protected com.liferay.osb.service.OfferingBundleLocalService offeringBundleLocalService;
	@BeanReference(type = OfferingBundlePersistence.class)
	protected OfferingBundlePersistence offeringBundlePersistence;
	@BeanReference(type = com.liferay.osb.service.OfferingDefinitionLocalService.class)
	protected com.liferay.osb.service.OfferingDefinitionLocalService offeringDefinitionLocalService;
	@BeanReference(type = OfferingDefinitionPersistence.class)
	protected OfferingDefinitionPersistence offeringDefinitionPersistence;
	@BeanReference(type = com.liferay.osb.service.OfferingEntryLocalService.class)
	protected com.liferay.osb.service.OfferingEntryLocalService offeringEntryLocalService;
	@BeanReference(type = OfferingEntryPersistence.class)
	protected OfferingEntryPersistence offeringEntryPersistence;
	@BeanReference(type = OfferingEntryFinder.class)
	protected OfferingEntryFinder offeringEntryFinder;
	@BeanReference(type = com.liferay.osb.service.OrderEntryLocalService.class)
	protected com.liferay.osb.service.OrderEntryLocalService orderEntryLocalService;
	@BeanReference(type = OrderEntryPersistence.class)
	protected OrderEntryPersistence orderEntryPersistence;
	@BeanReference(type = OrderEntryFinder.class)
	protected OrderEntryFinder orderEntryFinder;
	@BeanReference(type = com.liferay.osb.service.PartnerEntryLocalService.class)
	protected com.liferay.osb.service.PartnerEntryLocalService partnerEntryLocalService;
	@BeanReference(type = PartnerEntryPersistence.class)
	protected PartnerEntryPersistence partnerEntryPersistence;
	@BeanReference(type = PartnerEntryFinder.class)
	protected PartnerEntryFinder partnerEntryFinder;
	@BeanReference(type = com.liferay.osb.service.PartnerWorkerLocalService.class)
	protected com.liferay.osb.service.PartnerWorkerLocalService partnerWorkerLocalService;
	@BeanReference(type = PartnerWorkerPersistence.class)
	protected PartnerWorkerPersistence partnerWorkerPersistence;
	@BeanReference(type = com.liferay.osb.service.ProductEntryLocalService.class)
	protected com.liferay.osb.service.ProductEntryLocalService productEntryLocalService;
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
	@BeanReference(type = SearchFilterPersistence.class)
	protected SearchFilterPersistence searchFilterPersistence;
	@BeanReference(type = com.liferay.osb.service.SecurityPatchLocalService.class)
	protected com.liferay.osb.service.SecurityPatchLocalService securityPatchLocalService;
	@BeanReference(type = SecurityPatchPersistence.class)
	protected SecurityPatchPersistence securityPatchPersistence;
	@BeanReference(type = com.liferay.osb.service.SupportLaborLocalService.class)
	protected com.liferay.osb.service.SupportLaborLocalService supportLaborLocalService;
	@BeanReference(type = SupportLaborPersistence.class)
	protected SupportLaborPersistence supportLaborPersistence;
	@BeanReference(type = com.liferay.osb.service.SupportRegionLocalService.class)
	protected com.liferay.osb.service.SupportRegionLocalService supportRegionLocalService;
	@BeanReference(type = SupportRegionPersistence.class)
	protected SupportRegionPersistence supportRegionPersistence;
	@BeanReference(type = com.liferay.osb.service.SupportResponseLocalService.class)
	protected com.liferay.osb.service.SupportResponseLocalService supportResponseLocalService;
	@BeanReference(type = SupportResponsePersistence.class)
	protected SupportResponsePersistence supportResponsePersistence;
	@BeanReference(type = SupportResponseFinder.class)
	protected SupportResponseFinder supportResponseFinder;
	@BeanReference(type = com.liferay.osb.service.SupportTeamLocalService.class)
	protected com.liferay.osb.service.SupportTeamLocalService supportTeamLocalService;
	@BeanReference(type = SupportTeamPersistence.class)
	protected SupportTeamPersistence supportTeamPersistence;
	@BeanReference(type = SupportTeamFinder.class)
	protected SupportTeamFinder supportTeamFinder;
	@BeanReference(type = com.liferay.osb.service.SupportTeamLanguageLocalService.class)
	protected com.liferay.osb.service.SupportTeamLanguageLocalService supportTeamLanguageLocalService;
	@BeanReference(type = SupportTeamLanguagePersistence.class)
	protected SupportTeamLanguagePersistence supportTeamLanguagePersistence;
	@BeanReference(type = com.liferay.osb.service.SupportWorkerLocalService.class)
	protected com.liferay.osb.service.SupportWorkerLocalService supportWorkerLocalService;
	@BeanReference(type = SupportWorkerPersistence.class)
	protected SupportWorkerPersistence supportWorkerPersistence;
	@BeanReference(type = SupportWorkerFinder.class)
	protected SupportWorkerFinder supportWorkerFinder;
	@BeanReference(type = com.liferay.osb.service.SupportWorkerAccountTierLocalService.class)
	protected com.liferay.osb.service.SupportWorkerAccountTierLocalService supportWorkerAccountTierLocalService;
	@BeanReference(type = SupportWorkerAccountTierPersistence.class)
	protected SupportWorkerAccountTierPersistence supportWorkerAccountTierPersistence;
	@BeanReference(type = com.liferay.osb.service.SupportWorkerComponentLocalService.class)
	protected com.liferay.osb.service.SupportWorkerComponentLocalService supportWorkerComponentLocalService;
	@BeanReference(type = SupportWorkerComponentPersistence.class)
	protected SupportWorkerComponentPersistence supportWorkerComponentPersistence;
	@BeanReference(type = com.liferay.osb.service.SupportWorkerSeverityLocalService.class)
	protected com.liferay.osb.service.SupportWorkerSeverityLocalService supportWorkerSeverityLocalService;
	@BeanReference(type = SupportWorkerSeverityPersistence.class)
	protected SupportWorkerSeverityPersistence supportWorkerSeverityPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketAttachmentLocalService.class)
	protected com.liferay.osb.service.TicketAttachmentLocalService ticketAttachmentLocalService;
	@BeanReference(type = TicketAttachmentPersistence.class)
	protected TicketAttachmentPersistence ticketAttachmentPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketCallLocalService.class)
	protected com.liferay.osb.service.TicketCallLocalService ticketCallLocalService;
	@BeanReference(type = TicketCallPersistence.class)
	protected TicketCallPersistence ticketCallPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketCommentLocalService.class)
	protected com.liferay.osb.service.TicketCommentLocalService ticketCommentLocalService;
	@BeanReference(type = TicketCommentPersistence.class)
	protected TicketCommentPersistence ticketCommentPersistence;
	@BeanReference(type = TicketCommentFinder.class)
	protected TicketCommentFinder ticketCommentFinder;
	@BeanReference(type = com.liferay.osb.service.TicketEntryLocalService.class)
	protected com.liferay.osb.service.TicketEntryLocalService ticketEntryLocalService;
	@BeanReference(type = TicketEntryPersistence.class)
	protected TicketEntryPersistence ticketEntryPersistence;
	@BeanReference(type = TicketEntryFinder.class)
	protected TicketEntryFinder ticketEntryFinder;
	@BeanReference(type = com.liferay.osb.service.TicketFeedbackLocalService.class)
	protected com.liferay.osb.service.TicketFeedbackLocalService ticketFeedbackLocalService;
	@BeanReference(type = TicketFeedbackPersistence.class)
	protected TicketFeedbackPersistence ticketFeedbackPersistence;
	@BeanReference(type = TicketFeedbackFinder.class)
	protected TicketFeedbackFinder ticketFeedbackFinder;
	@BeanReference(type = com.liferay.osb.service.TicketFlagLocalService.class)
	protected com.liferay.osb.service.TicketFlagLocalService ticketFlagLocalService;
	@BeanReference(type = TicketFlagPersistence.class)
	protected TicketFlagPersistence ticketFlagPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketInformationLocalService.class)
	protected com.liferay.osb.service.TicketInformationLocalService ticketInformationLocalService;
	@BeanReference(type = TicketInformationPersistence.class)
	protected TicketInformationPersistence ticketInformationPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketLinkLocalService.class)
	protected com.liferay.osb.service.TicketLinkLocalService ticketLinkLocalService;
	@BeanReference(type = TicketLinkPersistence.class)
	protected TicketLinkPersistence ticketLinkPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketSolutionLocalService.class)
	protected com.liferay.osb.service.TicketSolutionLocalService ticketSolutionLocalService;
	@BeanReference(type = TicketSolutionPersistence.class)
	protected TicketSolutionPersistence ticketSolutionPersistence;
	@BeanReference(type = com.liferay.osb.service.TicketWorkerLocalService.class)
	protected com.liferay.osb.service.TicketWorkerLocalService ticketWorkerLocalService;
	@BeanReference(type = TicketWorkerPersistence.class)
	protected TicketWorkerPersistence ticketWorkerPersistence;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}