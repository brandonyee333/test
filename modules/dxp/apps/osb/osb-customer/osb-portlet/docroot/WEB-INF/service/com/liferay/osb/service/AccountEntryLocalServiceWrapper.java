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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLocalService
 * @generated
 */
@ProviderType
public class AccountEntryLocalServiceWrapper implements AccountEntryLocalService,
	ServiceWrapper<AccountEntryLocalService> {
	public AccountEntryLocalServiceWrapper(
		AccountEntryLocalService accountEntryLocalService) {
		_accountEntryLocalService = accountEntryLocalService;
	}

	@Override
	public boolean hasSupportRegionAccountEntries(long supportRegionId) {
		return _accountEntryLocalService.hasSupportRegionAccountEntries(supportRegionId);
	}

	@Override
	public boolean hasSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId) {
		return _accountEntryLocalService.hasSupportRegionAccountEntry(supportRegionId,
			accountEntryId);
	}

	@Override
	public boolean hasValidLicenseAccountEntry(long userId) {
		return _accountEntryLocalService.hasValidLicenseAccountEntry(userId);
	}

	@Override
	public boolean hasValidSupportAccountEntry(long userId) {
		return _accountEntryLocalService.hasValidSupportAccountEntry(userId);
	}

	/**
	* Adds the account entry to the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @return the account entry that was added
	*/
	@Override
	public com.liferay.osb.model.AccountEntry addAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry) {
		return _accountEntryLocalService.addAccountEntry(accountEntry);
	}

	@Override
	public com.liferay.osb.model.AccountEntry addAccountEntry(long userId,
		java.lang.String corpProjectUuid, java.lang.String corpEntryName,
		java.lang.String name, java.lang.String code, int type, int industry,
		long partnerEntryId, boolean partnerManagedSupport, int tier,
		int maxCustomers, java.lang.String instructions,
		java.lang.String notes, java.lang.String[] languageIds,
		long[] supportRegionIds, java.lang.String street1,
		java.lang.String street2, java.lang.String street3,
		java.lang.String city, java.lang.String zip, long regionId,
		long countryId, java.lang.String ewsaDossieraProjectKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLocalService.addAccountEntry(userId,
			corpProjectUuid, corpEntryName, name, code, type, industry,
			partnerEntryId, partnerManagedSupport, tier, maxCustomers,
			instructions, notes, languageIds, supportRegionIds, street1,
			street2, street3, city, zip, regionId, countryId,
			ewsaDossieraProjectKey);
	}

	@Override
	public com.liferay.osb.model.AccountEntry addAccountEntryWithWorkflow(
		java.lang.String salesforceOpportunityKey,
		com.liferay.osb.model.AccountEntry accountEntry,
		com.liferay.osb.model.CorpProject corpProject,
		com.liferay.osb.model.PartnerEntry partnerEntry,
		com.liferay.portal.kernel.model.Address address,
		com.liferay.osb.model.AccountWorker accountWorker,
		java.util.List<com.liferay.osb.model.OrderEntry> orderEntries,
		java.util.ArrayList<com.liferay.portal.kernel.model.User> users,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLocalService.addAccountEntryWithWorkflow(salesforceOpportunityKey,
			accountEntry, corpProject, partnerEntry, address, accountWorker,
			orderEntries, users, serviceContext);
	}

	/**
	* Creates a new account entry with the primary key. Does not add the account entry to the database.
	*
	* @param accountEntryId the primary key for the new account entry
	* @return the new account entry
	*/
	@Override
	public com.liferay.osb.model.AccountEntry createAccountEntry(
		long accountEntryId) {
		return _accountEntryLocalService.createAccountEntry(accountEntryId);
	}

	/**
	* Deletes the account entry from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @return the account entry that was removed
	*/
	@Override
	public com.liferay.osb.model.AccountEntry deleteAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry) {
		return _accountEntryLocalService.deleteAccountEntry(accountEntry);
	}

	/**
	* Deletes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry that was removed
	* @throws PortalException if a account entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountEntry deleteAccountEntry(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLocalService.deleteAccountEntry(accountEntryId);
	}

	@Override
	public com.liferay.osb.model.AccountEntry fetchAccountEntry(
		long accountEntryId) {
		return _accountEntryLocalService.fetchAccountEntry(accountEntryId);
	}

	@Override
	public com.liferay.osb.model.AccountEntry fetchCorpProjectAccountEntry(
		java.lang.String corpProjectUuid) {
		return _accountEntryLocalService.fetchCorpProjectAccountEntry(corpProjectUuid);
	}

	@Override
	public com.liferay.osb.model.AccountEntry fetchCorpProjectAccountEntry(
		long corpProjectId) {
		return _accountEntryLocalService.fetchCorpProjectAccountEntry(corpProjectId);
	}

	@Override
	public com.liferay.osb.model.AccountEntry fetchUserTrialAccountEntry(
		long userId) {
		return _accountEntryLocalService.fetchUserTrialAccountEntry(userId);
	}

	/**
	* Returns the account entry with the primary key.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry
	* @throws PortalException if a account entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountEntry getAccountEntry(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLocalService.getAccountEntry(accountEntryId);
	}

	@Override
	public com.liferay.osb.model.AccountEntry getAccountEntryByCode(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLocalService.getAccountEntryByCode(code);
	}

	@Override
	public com.liferay.osb.model.AccountEntry getAccountEntryByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLocalService.getAccountEntryByName(name);
	}

	/**
	* Updates the account entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @return the account entry that was updated
	*/
	@Override
	public com.liferay.osb.model.AccountEntry updateAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry) {
		return _accountEntryLocalService.updateAccountEntry(accountEntry);
	}

	@Override
	public com.liferay.osb.model.AccountEntry updateAccountEntry(long userId,
		long accountEntryId, java.lang.String corpProjectUuid,
		java.lang.String corpEntryName, java.lang.String name,
		java.lang.String code, int type, int industry, long partnerEntryId,
		boolean partnerManagedSupport, int tier, int maxCustomers,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String[] languageIds, long[] supportRegionIds,
		long addressId, java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId, java.lang.String ewsaDossieraProjectKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLocalService.updateAccountEntry(userId,
			accountEntryId, corpProjectUuid, corpEntryName, name, code, type,
			industry, partnerEntryId, partnerManagedSupport, tier,
			maxCustomers, instructions, notes, languageIds, supportRegionIds,
			addressId, street1, street2, street3, city, zip, regionId,
			countryId, ewsaDossieraProjectKey);
	}

	@Override
	public com.liferay.osb.model.AccountEntry updateStatus(long userId,
		long accountEntryId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLocalService.updateStatus(userId, accountEntryId,
			status, serviceContext);
	}

	@Override
	public com.liferay.osb.model.AccountEntry updateStatus(long userId,
		long accountEntryId, java.lang.String salesforceOpportunityKey,
		int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLocalService.updateStatus(userId, accountEntryId,
			salesforceOpportunityKey, status, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _accountEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _accountEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of account entries.
	*
	* @return the number of account entries
	*/
	@Override
	public int getAccountEntriesCount() {
		return _accountEntryLocalService.getAccountEntriesCount();
	}

	@Override
	public int getSupportRegionAccountEntriesCount(long supportRegionId) {
		return _accountEntryLocalService.getSupportRegionAccountEntriesCount(supportRegionId);
	}

	@Override
	public int getUserAccountEntriesCount(long userId) {
		return _accountEntryLocalService.getUserAccountEntriesCount(userId);
	}

	@Override
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
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return _accountEntryLocalService.searchCount(createUserId,
			createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear,
			modifiedUserId, modifiedDateGTDay, modifiedDateGTMonth,
			modifiedDateGTYear, modifiedDateLTDay, modifiedDateLTMonth,
			modifiedDateLTYear, name, code, industries, partnerManagedSupport,
			tiers, statuses, instructions, notes, partnerEntryCode, street,
			countryId, regionId, city, zip, params, andOperator);
	}

	@Override
	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return _accountEntryLocalService.searchCount(keywords, params);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accountEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _accountEntryLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _accountEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _accountEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

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
	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		int start, int end) {
		return _accountEntryLocalService.getAccountEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		int[] notTypes, int[] statuses, int start, int end) {
		return _accountEntryLocalService.getAccountEntries(notTypes, statuses,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		int[] statuses, int start, int end) {
		return _accountEntryLocalService.getAccountEntries(statuses, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getActiveAccountEntries(
		int start, int end) {
		return _accountEntryLocalService.getActiveAccountEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getPartnerAccountEntries(
		long partnerEntryId) {
		return _accountEntryLocalService.getPartnerAccountEntries(partnerEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getRedirectAccountEntries(
		long accountEntryId) {
		return _accountEntryLocalService.getRedirectAccountEntries(accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getSecurityPatchAccountEntries(
		java.lang.String portletId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return _accountEntryLocalService.getSecurityPatchAccountEntries(portletId,
			params);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId) {
		return _accountEntryLocalService.getSupportRegionAccountEntries(supportRegionId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId, int start, int end) {
		return _accountEntryLocalService.getSupportRegionAccountEntries(supportRegionId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.AccountEntry> orderByComparator) {
		return _accountEntryLocalService.getSupportRegionAccountEntries(supportRegionId,
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getUserAccountEntries(
		long userId, int start, int end) {
		return _accountEntryLocalService.getUserAccountEntries(userId, start,
			end);
	}

	@Override
	public java.util.List<java.lang.Long> getUserAccountEntryIds(long userId,
		int start, int end) {
		return _accountEntryLocalService.getUserAccountEntryIds(userId, start,
			end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getUserActiveAccountEntries(
		long userId, int start, int end) {
		return _accountEntryLocalService.getUserActiveAccountEntries(userId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> search(
		java.lang.Long createUserId, int createDateGTDay,
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
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _accountEntryLocalService.search(createUserId, createDateGTDay,
			createDateGTMonth, createDateGTYear, createDateLTDay,
			createDateLTMonth, createDateLTYear, modifiedUserId,
			modifiedDateGTDay, modifiedDateGTMonth, modifiedDateGTYear,
			modifiedDateLTDay, modifiedDateLTMonth, modifiedDateLTYear, name,
			code, industries, partnerManagedSupport, tiers, statuses,
			instructions, notes, partnerEntryCode, street, countryId, regionId,
			city, zip, params, andOperator, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _accountEntryLocalService.search(keywords, params, start, end,
			obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> search(
		java.lang.String name, java.lang.String code) {
		return _accountEntryLocalService.search(name, code);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _accountEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _accountEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Returns the supportRegionIds of the support regions associated with the account entry.
	*
	* @param accountEntryId the accountEntryId of the account entry
	* @return long[] the supportRegionIds of support regions associated with the account entry
	*/
	@Override
	public long[] getSupportRegionPrimaryKeys(long accountEntryId) {
		return _accountEntryLocalService.getSupportRegionPrimaryKeys(accountEntryId);
	}

	@Override
	public void addSupportRegionAccountEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries) {
		_accountEntryLocalService.addSupportRegionAccountEntries(supportRegionId,
			accountEntries);
	}

	@Override
	public void addSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds) {
		_accountEntryLocalService.addSupportRegionAccountEntries(supportRegionId,
			accountEntryIds);
	}

	@Override
	public void addSupportRegionAccountEntry(long supportRegionId,
		com.liferay.osb.model.AccountEntry accountEntry) {
		_accountEntryLocalService.addSupportRegionAccountEntry(supportRegionId,
			accountEntry);
	}

	@Override
	public void addSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId) {
		_accountEntryLocalService.addSupportRegionAccountEntry(supportRegionId,
			accountEntryId);
	}

	@Override
	public void addTrialAccountEntry(long userId) throws java.lang.Exception {
		_accountEntryLocalService.addTrialAccountEntry(userId);
	}

	@Override
	public void auditAccountEntries()
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountEntryLocalService.auditAccountEntries();
	}

	@Override
	public void auditAccountEntry(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountEntryLocalService.auditAccountEntry(userId, accountEntryId);
	}

	@Override
	public void clearSupportRegionAccountEntries(long supportRegionId) {
		_accountEntryLocalService.clearSupportRegionAccountEntries(supportRegionId);
	}

	@Override
	public void deleteSupportRegionAccountEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries) {
		_accountEntryLocalService.deleteSupportRegionAccountEntries(supportRegionId,
			accountEntries);
	}

	@Override
	public void deleteSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds) {
		_accountEntryLocalService.deleteSupportRegionAccountEntries(supportRegionId,
			accountEntryIds);
	}

	@Override
	public void deleteSupportRegionAccountEntry(long supportRegionId,
		com.liferay.osb.model.AccountEntry accountEntry) {
		_accountEntryLocalService.deleteSupportRegionAccountEntry(supportRegionId,
			accountEntry);
	}

	@Override
	public void deleteSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId) {
		_accountEntryLocalService.deleteSupportRegionAccountEntry(supportRegionId,
			accountEntryId);
	}

	@Override
	public void recalculateHighestSupportResponse(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountEntryLocalService.recalculateHighestSupportResponse(accountEntryId);
	}

	@Override
	public void setSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds) {
		_accountEntryLocalService.setSupportRegionAccountEntries(supportRegionId,
			accountEntryIds);
	}

	@Override
	public void updateAccountEntryWithWorkflow(
		java.lang.String salesforceOpportunityKey,
		com.liferay.osb.model.AccountEntry accountEntry,
		com.liferay.osb.model.PartnerEntry partnerEntry,
		com.liferay.osb.model.AccountWorker accountWorker,
		com.liferay.portal.kernel.model.Address address,
		java.util.List<com.liferay.osb.model.OrderEntry> orderEntries,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountEntryLocalService.updateAccountEntryWithWorkflow(salesforceOpportunityKey,
			accountEntry, partnerEntry, accountWorker, address, orderEntries,
			serviceContext);
	}

	@Override
	public void updateLastAuditDate(long userId, long accountEntryId,
		java.lang.String auditLabel, java.lang.String auditValue)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountEntryLocalService.updateLastAuditDate(userId, accountEntryId,
			auditLabel, auditValue);
	}

	@Override
	public void updateStatus(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountEntryLocalService.updateStatus(accountEntryId);
	}

	@Override
	public void validate(com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		_accountEntryLocalService.validate(accountEntry);
	}

	@Override
	public AccountEntryLocalService getWrappedService() {
		return _accountEntryLocalService;
	}

	@Override
	public void setWrappedService(
		AccountEntryLocalService accountEntryLocalService) {
		_accountEntryLocalService = accountEntryLocalService;
	}

	private AccountEntryLocalService _accountEntryLocalService;
}