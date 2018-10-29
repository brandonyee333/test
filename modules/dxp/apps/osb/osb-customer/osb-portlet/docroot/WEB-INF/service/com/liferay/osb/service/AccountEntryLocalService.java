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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.PartnerEntry;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for AccountEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLocalServiceUtil
 * @see com.liferay.osb.service.base.AccountEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountEntryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AccountEntryLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEntryLocalServiceUtil} to access the account entry local service. Add custom service methods to {@link com.liferay.osb.service.impl.AccountEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSupportRegionAccountEntries(long supportRegionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasValidLicenseAccountEntry(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasValidSupportAccountEntry(long userId);

	/**
	* Adds the account entry to the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @return the account entry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AccountEntry addAccountEntry(AccountEntry accountEntry);

	public AccountEntry addAccountEntry(long userId,
		java.lang.String corpProjectUuid, java.lang.String dossieraAccountKey,
		java.lang.String corpEntryName, java.lang.String name,
		java.lang.String code, int type, int industry, long partnerEntryId,
		boolean partnerManagedSupport, int tier, int maxCustomers,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String[] languageIds, long[] supportRegionIds,
		java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId, java.lang.String ewsaDossieraProjectKey)
		throws PortalException;

	public AccountEntry addAccountEntryWithWorkflow(
		java.lang.String salesforceOpportunityKey, AccountEntry accountEntry,
		CorpProject corpProject, PartnerEntry partnerEntry, Address address,
		AccountWorker accountWorker, List<OrderEntry> orderEntries,
		List<User> users, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Creates a new account entry with the primary key. Does not add the account entry to the database.
	*
	* @param accountEntryId the primary key for the new account entry
	* @return the new account entry
	*/
	public AccountEntry createAccountEntry(long accountEntryId);

	/**
	* Deletes the account entry from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @return the account entry that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public AccountEntry deleteAccountEntry(AccountEntry accountEntry);

	/**
	* Deletes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry that was removed
	* @throws PortalException if a account entry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public AccountEntry deleteAccountEntry(long accountEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry fetchAccountEntry(long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry fetchAnalyticsCloudBasicAccountEntry(
		java.lang.String dossieraAccountKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry fetchCorpProjectAccountEntry(
		java.lang.String corpProjectUuid);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry fetchCorpProjectAccountEntry(long corpProjectId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry fetchUserTrialAccountEntry(long userId);

	/**
	* Returns the account entry with the primary key.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry
	* @throws PortalException if a account entry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry getAccountEntry(long accountEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry getAccountEntryByCode(java.lang.String code)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry getAccountEntryByName(java.lang.String name)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry getCorpProjectAccountEntry(long corpProjectId)
		throws PortalException;

	/**
	* Updates the account entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @return the account entry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AccountEntry updateAccountEntry(AccountEntry accountEntry);

	public AccountEntry updateAccountEntry(long userId, long accountEntryId,
		java.lang.String corpProjectUuid, java.lang.String dossieraAccountKey,
		java.lang.String corpEntryName, java.lang.String name,
		java.lang.String code, int type, int industry, long partnerEntryId,
		boolean partnerManagedSupport, int tier, int maxCustomers,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String[] languageIds, long[] supportRegionIds,
		long addressId, java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId, java.lang.String ewsaDossieraProjectKey)
		throws PortalException;

	public AccountEntry updateInstructions(long userId, long accountEntryId,
		java.lang.String instructions) throws PortalException;

	public AccountEntry updateStatus(long userId, long accountEntryId,
		int status, ServiceContext serviceContext) throws PortalException;

	public AccountEntry updateStatus(long userId, long accountEntryId,
		java.lang.String salesforceOpportunityKey, int status,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of account entries.
	*
	* @return the number of account entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAccountEntriesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSupportRegionAccountEntriesCount(long supportRegionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserAccountEntriesCount(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.Long createUserId, int createDateGTDay,
		int createDateGTMonth, int createDateGTYear, int createDateLTDay,
		int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear, java.lang.String name,
		java.lang.String code, int[] industries,
		java.lang.Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String partnerEntryCode, java.lang.String street,
		java.lang.Long countryId, java.lang.Long regionId,
		java.lang.String city, java.lang.String zip,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns a range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of account entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getAccountEntries(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getAccountEntries(int[] notTypes, int[] statuses,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getAccountEntries(int[] statuses, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getAccountEntries(
		java.lang.String dossieraAccountKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getAccountEntries(
		java.lang.String dossieraAccountKey, int type);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getActiveAccountEntries(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getPartnerAccountEntries(long partnerEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getRedirectAccountEntries(long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getSecurityPatchAccountEntries(
		java.lang.String portletId,
		LinkedHashMap<java.lang.String, java.lang.Object> params);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getUserAccountEntries(long userId, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<java.lang.Long> getUserAccountEntryIds(long userId, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getUserActiveAccountEntries(long userId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> search(java.lang.Long createUserId,
		int createDateGTDay, int createDateGTMonth, int createDateGTYear,
		int createDateLTDay, int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear, java.lang.String name,
		java.lang.String code, int[] industries,
		java.lang.Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String partnerEntryCode, java.lang.String street,
		java.lang.Long countryId, java.lang.Long regionId,
		java.lang.String city, java.lang.String zip,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> search(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> search(java.lang.String name,
		java.lang.String code);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Returns the supportRegionIds of the support regions associated with the account entry.
	*
	* @param accountEntryId the accountEntryId of the account entry
	* @return long[] the supportRegionIds of support regions associated with the account entry
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getSupportRegionPrimaryKeys(long accountEntryId);

	public void addAnalyticsCloudBasicAccountEntry(
		java.lang.String dossieraAccountKey, java.lang.String corpEntryName,
		java.lang.String accountEntryName, Date supportEndDate)
		throws PortalException;

	public void addSupportRegionAccountEntries(long supportRegionId,
		List<AccountEntry> accountEntries);

	public void addSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds);

	public void addSupportRegionAccountEntry(long supportRegionId,
		AccountEntry accountEntry);

	public void addSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId);

	public void addTrialAccountEntry(long userId) throws java.lang.Exception;

	public void addWorkflowTask(java.lang.String salesforceOpportunityKey,
		AccountEntry accountEntry, ServiceContext serviceContext)
		throws PortalException;

	public void assignOwnership(java.lang.String corpProjectUuid, long userId)
		throws PortalException;

	public void auditAccountEntries() throws PortalException;

	public void auditAccountEntry(long userId, long accountEntryId)
		throws PortalException;

	public void clearSupportRegionAccountEntries(long supportRegionId);

	public void deleteSupportRegionAccountEntries(long supportRegionId,
		List<AccountEntry> accountEntries);

	public void deleteSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds);

	public void deleteSupportRegionAccountEntry(long supportRegionId,
		AccountEntry accountEntry);

	public void deleteSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId);

	public void recalculateHighestSupportResponse(long accountEntryId)
		throws PortalException;

	public void setSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds);

	public void updateAccountEntryWithWorkflow(
		java.lang.String salesforceOpportunityKey, AccountEntry accountEntry,
		PartnerEntry partnerEntry, AccountWorker accountWorker,
		Address address, List<OrderEntry> orderEntries, List<User> users,
		ServiceContext serviceContext) throws PortalException;

	public void updateLastAuditDate(long userId, long accountEntryId,
		java.lang.String auditLabel, java.lang.String auditValue)
		throws PortalException;

	public void updateStatus(long accountEntryId) throws PortalException;

	public void validate(AccountEntry accountEntry) throws PortalException;
}