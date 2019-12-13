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

import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.CorpProjectLocalService;
import com.liferay.osb.service.persistence.AccountAttachmentPersistence;
import com.liferay.osb.service.persistence.AccountCustomerFinder;
import com.liferay.osb.service.persistence.AccountCustomerPersistence;
import com.liferay.osb.service.persistence.AccountEntryFinder;
import com.liferay.osb.service.persistence.AccountEntryLanguagePersistence;
import com.liferay.osb.service.persistence.AccountEntryPersistence;
import com.liferay.osb.service.persistence.AccountEnvironmentAttachmentPersistence;
import com.liferay.osb.service.persistence.AccountEnvironmentPersistence;
import com.liferay.osb.service.persistence.AccountInformationPersistence;
import com.liferay.osb.service.persistence.AccountProjectPersistence;
import com.liferay.osb.service.persistence.AccountWorkerPersistence;
import com.liferay.osb.service.persistence.AuditEntryPersistence;
import com.liferay.osb.service.persistence.CorpProjectMessagePersistence;
import com.liferay.osb.service.persistence.CorpProjectPersistence;
import com.liferay.osb.service.persistence.ExternalIdMapperPersistence;
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
import com.liferay.osb.service.persistence.SupportRegionPersistence;
import com.liferay.osb.service.persistence.SupportResponseFinder;
import com.liferay.osb.service.persistence.SupportResponsePersistence;

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
import com.liferay.portal.kernel.service.persistence.OrganizationPersistence;
import com.liferay.portal.kernel.service.persistence.RolePersistence;
import com.liferay.portal.kernel.service.persistence.UserGroupRolePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the corp project local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.osb.service.impl.CorpProjectLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.impl.CorpProjectLocalServiceImpl
 * @see com.liferay.osb.service.CorpProjectLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class CorpProjectLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements CorpProjectLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.osb.service.CorpProjectLocalServiceUtil} to access the corp project local service.
	 */

	/**
	 * Adds the corp project to the database. Also notifies the appropriate model listeners.
	 *
	 * @param corpProject the corp project
	 * @return the corp project that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CorpProject addCorpProject(CorpProject corpProject) {
		corpProject.setNew(true);

		return corpProjectPersistence.update(corpProject);
	}

	/**
	 * Creates a new corp project with the primary key. Does not add the corp project to the database.
	 *
	 * @param corpProjectId the primary key for the new corp project
	 * @return the new corp project
	 */
	@Override
	public CorpProject createCorpProject(long corpProjectId) {
		return corpProjectPersistence.create(corpProjectId);
	}

	/**
	 * Deletes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param corpProjectId the primary key of the corp project
	 * @return the corp project that was removed
	 * @throws PortalException if a corp project with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CorpProject deleteCorpProject(long corpProjectId)
		throws PortalException {
		return corpProjectPersistence.remove(corpProjectId);
	}

	/**
	 * Deletes the corp project from the database. Also notifies the appropriate model listeners.
	 *
	 * @param corpProject the corp project
	 * @return the corp project that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CorpProject deleteCorpProject(CorpProject corpProject)
		throws PortalException {
		return corpProjectPersistence.remove(corpProject);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(CorpProject.class,
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
		return corpProjectPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return corpProjectPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return corpProjectPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return corpProjectPersistence.countWithDynamicQuery(dynamicQuery);
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
		return corpProjectPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public CorpProject fetchCorpProject(long corpProjectId) {
		return corpProjectPersistence.fetchByPrimaryKey(corpProjectId);
	}

	/**
	 * Returns the corp project with the primary key.
	 *
	 * @param corpProjectId the primary key of the corp project
	 * @return the corp project
	 * @throws PortalException if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject getCorpProject(long corpProjectId)
		throws PortalException {
		return corpProjectPersistence.findByPrimaryKey(corpProjectId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(corpProjectLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CorpProject.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("corpProjectId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(corpProjectLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CorpProject.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"corpProjectId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(corpProjectLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CorpProject.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("corpProjectId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return corpProjectLocalService.deleteCorpProject((CorpProject)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return corpProjectPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the corp projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @return the range of corp projects
	 */
	@Override
	public List<CorpProject> getCorpProjects(int start, int end) {
		return corpProjectPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of corp projects.
	 *
	 * @return the number of corp projects
	 */
	@Override
	public int getCorpProjectsCount() {
		return corpProjectPersistence.countAll();
	}

	/**
	 * Updates the corp project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param corpProject the corp project
	 * @return the corp project that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CorpProject updateCorpProject(CorpProject corpProject) {
		return corpProjectPersistence.update(corpProject);
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
	 * Returns the account customer finder.
	 *
	 * @return the account customer finder
	 */
	public AccountCustomerFinder getAccountCustomerFinder() {
		return accountCustomerFinder;
	}

	/**
	 * Sets the account customer finder.
	 *
	 * @param accountCustomerFinder the account customer finder
	 */
	public void setAccountCustomerFinder(
		AccountCustomerFinder accountCustomerFinder) {
		this.accountCustomerFinder = accountCustomerFinder;
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
	 * Returns the rabbit mq message processor local service.
	 *
	 * @return the rabbit mq message processor local service
	 */
	public com.liferay.osb.service.RabbitMQMessageProcessorLocalService getRabbitMQMessageProcessorLocalService() {
		return rabbitMQMessageProcessorLocalService;
	}

	/**
	 * Sets the rabbit mq message processor local service.
	 *
	 * @param rabbitMQMessageProcessorLocalService the rabbit mq message processor local service
	 */
	public void setRabbitMQMessageProcessorLocalService(
		com.liferay.osb.service.RabbitMQMessageProcessorLocalService rabbitMQMessageProcessorLocalService) {
		this.rabbitMQMessageProcessorLocalService = rabbitMQMessageProcessorLocalService;
	}

	/**
	 * Returns the remote corp entry local service.
	 *
	 * @return the remote corp entry local service
	 */
	public com.liferay.osb.service.RemoteCorpEntryLocalService getRemoteCorpEntryLocalService() {
		return remoteCorpEntryLocalService;
	}

	/**
	 * Sets the remote corp entry local service.
	 *
	 * @param remoteCorpEntryLocalService the remote corp entry local service
	 */
	public void setRemoteCorpEntryLocalService(
		com.liferay.osb.service.RemoteCorpEntryLocalService remoteCorpEntryLocalService) {
		this.remoteCorpEntryLocalService = remoteCorpEntryLocalService;
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
	 * Returns the organization local service.
	 *
	 * @return the organization local service
	 */
	public com.liferay.portal.kernel.service.OrganizationLocalService getOrganizationLocalService() {
		return organizationLocalService;
	}

	/**
	 * Sets the organization local service.
	 *
	 * @param organizationLocalService the organization local service
	 */
	public void setOrganizationLocalService(
		com.liferay.portal.kernel.service.OrganizationLocalService organizationLocalService) {
		this.organizationLocalService = organizationLocalService;
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
	 * Returns the role local service.
	 *
	 * @return the role local service
	 */
	public com.liferay.portal.kernel.service.RoleLocalService getRoleLocalService() {
		return roleLocalService;
	}

	/**
	 * Sets the role local service.
	 *
	 * @param roleLocalService the role local service
	 */
	public void setRoleLocalService(
		com.liferay.portal.kernel.service.RoleLocalService roleLocalService) {
		this.roleLocalService = roleLocalService;
	}

	/**
	 * Returns the role persistence.
	 *
	 * @return the role persistence
	 */
	public RolePersistence getRolePersistence() {
		return rolePersistence;
	}

	/**
	 * Sets the role persistence.
	 *
	 * @param rolePersistence the role persistence
	 */
	public void setRolePersistence(RolePersistence rolePersistence) {
		this.rolePersistence = rolePersistence;
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

	/**
	 * Returns the user group role local service.
	 *
	 * @return the user group role local service
	 */
	public com.liferay.portal.kernel.service.UserGroupRoleLocalService getUserGroupRoleLocalService() {
		return userGroupRoleLocalService;
	}

	/**
	 * Sets the user group role local service.
	 *
	 * @param userGroupRoleLocalService the user group role local service
	 */
	public void setUserGroupRoleLocalService(
		com.liferay.portal.kernel.service.UserGroupRoleLocalService userGroupRoleLocalService) {
		this.userGroupRoleLocalService = userGroupRoleLocalService;
	}

	/**
	 * Returns the user group role persistence.
	 *
	 * @return the user group role persistence
	 */
	public UserGroupRolePersistence getUserGroupRolePersistence() {
		return userGroupRolePersistence;
	}

	/**
	 * Sets the user group role persistence.
	 *
	 * @param userGroupRolePersistence the user group role persistence
	 */
	public void setUserGroupRolePersistence(
		UserGroupRolePersistence userGroupRolePersistence) {
		this.userGroupRolePersistence = userGroupRolePersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.osb.model.CorpProject",
			corpProjectLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.osb.model.CorpProject");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CorpProjectLocalService.class.getName();
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
		return CorpProject.class;
	}

	protected String getModelClassName() {
		return CorpProject.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = corpProjectPersistence.getDataSource();

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
	@BeanReference(type = com.liferay.osb.service.AccountCustomerLocalService.class)
	protected com.liferay.osb.service.AccountCustomerLocalService accountCustomerLocalService;
	@BeanReference(type = AccountCustomerPersistence.class)
	protected AccountCustomerPersistence accountCustomerPersistence;
	@BeanReference(type = AccountCustomerFinder.class)
	protected AccountCustomerFinder accountCustomerFinder;
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
	@BeanReference(type = com.liferay.osb.service.AccountProjectLocalService.class)
	protected com.liferay.osb.service.AccountProjectLocalService accountProjectLocalService;
	@BeanReference(type = AccountProjectPersistence.class)
	protected AccountProjectPersistence accountProjectPersistence;
	@BeanReference(type = com.liferay.osb.service.AccountWorkerLocalService.class)
	protected com.liferay.osb.service.AccountWorkerLocalService accountWorkerLocalService;
	@BeanReference(type = AccountWorkerPersistence.class)
	protected AccountWorkerPersistence accountWorkerPersistence;
	@BeanReference(type = com.liferay.osb.service.AuditEntryLocalService.class)
	protected com.liferay.osb.service.AuditEntryLocalService auditEntryLocalService;
	@BeanReference(type = AuditEntryPersistence.class)
	protected AuditEntryPersistence auditEntryPersistence;
	@BeanReference(type = CorpProjectLocalService.class)
	protected CorpProjectLocalService corpProjectLocalService;
	@BeanReference(type = CorpProjectPersistence.class)
	protected CorpProjectPersistence corpProjectPersistence;
	@BeanReference(type = com.liferay.osb.service.CorpProjectMessageLocalService.class)
	protected com.liferay.osb.service.CorpProjectMessageLocalService corpProjectMessageLocalService;
	@BeanReference(type = CorpProjectMessagePersistence.class)
	protected CorpProjectMessagePersistence corpProjectMessagePersistence;
	@BeanReference(type = com.liferay.osb.service.ExternalIdMapperLocalService.class)
	protected com.liferay.osb.service.ExternalIdMapperLocalService externalIdMapperLocalService;
	@BeanReference(type = ExternalIdMapperPersistence.class)
	protected ExternalIdMapperPersistence externalIdMapperPersistence;
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
	@BeanReference(type = com.liferay.osb.service.RabbitMQMessageProcessorLocalService.class)
	protected com.liferay.osb.service.RabbitMQMessageProcessorLocalService rabbitMQMessageProcessorLocalService;
	@BeanReference(type = com.liferay.osb.service.RemoteCorpEntryLocalService.class)
	protected com.liferay.osb.service.RemoteCorpEntryLocalService remoteCorpEntryLocalService;
	@BeanReference(type = com.liferay.osb.service.RemoteCorpProjectLocalService.class)
	protected com.liferay.osb.service.RemoteCorpProjectLocalService remoteCorpProjectLocalService;
	@BeanReference(type = com.liferay.osb.service.RemoteCorpProjectMessageLocalService.class)
	protected com.liferay.osb.service.RemoteCorpProjectMessageLocalService remoteCorpProjectMessageLocalService;
	@BeanReference(type = com.liferay.osb.service.RemoteUserLocalService.class)
	protected com.liferay.osb.service.RemoteUserLocalService remoteUserLocalService;
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
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.OrganizationLocalService.class)
	protected com.liferay.portal.kernel.service.OrganizationLocalService organizationLocalService;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.RoleLocalService.class)
	protected com.liferay.portal.kernel.service.RoleLocalService roleLocalService;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.UserGroupRoleLocalService.class)
	protected com.liferay.portal.kernel.service.UserGroupRoleLocalService userGroupRoleLocalService;
	@BeanReference(type = UserGroupRolePersistence.class)
	protected UserGroupRolePersistence userGroupRolePersistence;
	private ClassLoader _classLoader;
	private CorpProjectLocalServiceClpInvoker _clpInvoker = new CorpProjectLocalServiceClpInvoker();
}