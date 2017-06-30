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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the account entry local service. This utility wraps {@link com.liferay.osb.service.impl.AccountEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLocalService
 * @see com.liferay.osb.service.base.AccountEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountEntryLocalServiceImpl
 * @generated
 */
public class AccountEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AccountEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the account entry to the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @return the account entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry addAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAccountEntry(accountEntry);
	}

	/**
	* Creates a new account entry with the primary key. Does not add the account entry to the database.
	*
	* @param accountEntryId the primary key for the new account entry
	* @return the new account entry
	*/
	public static com.liferay.osb.model.AccountEntry createAccountEntry(
		long accountEntryId) {
		return getService().createAccountEntry(accountEntryId);
	}

	/**
	* Deletes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry that was removed
	* @throws PortalException if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry deleteAccountEntry(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAccountEntry(accountEntryId);
	}

	/**
	* Deletes the account entry from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @return the account entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry deleteAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAccountEntry(accountEntry);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.AccountEntry fetchAccountEntry(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAccountEntry(accountEntryId);
	}

	/**
	* Returns the account entry with the primary key.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry
	* @throws PortalException if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry getAccountEntry(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntry(accountEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntries(start, end);
	}

	/**
	* Returns the number of account entries.
	*
	* @return the number of account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getAccountEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntriesCount();
	}

	/**
	* Updates the account entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @return the account entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry updateAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAccountEntry(accountEntry);
	}

	/**
	* Updates the account entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @param merge whether to merge the account entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry updateAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAccountEntry(accountEntry, merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addSupportRegionAccountEntry(supportRegionId, accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegionAccountEntry(long supportRegionId,
		com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addSupportRegionAccountEntry(supportRegionId, accountEntry);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addSupportRegionAccountEntries(supportRegionId, accountEntryIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegionAccountEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.AccountEntry> AccountEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addSupportRegionAccountEntries(supportRegionId, AccountEntries);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearSupportRegionAccountEntries(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearSupportRegionAccountEntries(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportRegionAccountEntry(supportRegionId, accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportRegionAccountEntry(long supportRegionId,
		com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportRegionAccountEntry(supportRegionId, accountEntry);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportRegionAccountEntries(supportRegionId, accountEntryIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportRegionAccountEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.AccountEntry> AccountEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportRegionAccountEntries(supportRegionId, AccountEntries);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportRegionAccountEntries(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSupportRegionAccountEntries(supportRegionId, start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSupportRegionAccountEntries(supportRegionId, start, end,
			orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportRegionAccountEntriesCount(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportRegionAccountEntriesCount(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasSupportRegionAccountEntry(supportRegionId, accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasSupportRegionAccountEntries(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasSupportRegionAccountEntries(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setSupportRegionAccountEntries(supportRegionId, accountEntryIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeamAccountEntry(long supportTeamId,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addSupportTeamAccountEntry(supportTeamId, accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeamAccountEntry(long supportTeamId,
		com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addSupportTeamAccountEntry(supportTeamId, accountEntry);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeamAccountEntries(long supportTeamId,
		long[] accountEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addSupportTeamAccountEntries(supportTeamId, accountEntryIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeamAccountEntries(long supportTeamId,
		java.util.List<com.liferay.osb.model.AccountEntry> AccountEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addSupportTeamAccountEntries(supportTeamId, AccountEntries);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearSupportTeamAccountEntries(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearSupportTeamAccountEntries(supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportTeamAccountEntry(long supportTeamId,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSupportTeamAccountEntry(supportTeamId, accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportTeamAccountEntry(long supportTeamId,
		com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSupportTeamAccountEntry(supportTeamId, accountEntry);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportTeamAccountEntries(long supportTeamId,
		long[] accountEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportTeamAccountEntries(supportTeamId, accountEntryIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportTeamAccountEntries(long supportTeamId,
		java.util.List<com.liferay.osb.model.AccountEntry> AccountEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportTeamAccountEntries(supportTeamId, AccountEntries);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> getSupportTeamAccountEntries(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportTeamAccountEntries(supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> getSupportTeamAccountEntries(
		long supportTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSupportTeamAccountEntries(supportTeamId, start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> getSupportTeamAccountEntries(
		long supportTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSupportTeamAccountEntries(supportTeamId, start, end,
			orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportTeamAccountEntriesCount(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportTeamAccountEntriesCount(supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasSupportTeamAccountEntry(long supportTeamId,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasSupportTeamAccountEntry(supportTeamId, accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasSupportTeamAccountEntries(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasSupportTeamAccountEntries(supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportTeamAccountEntries(long supportTeamId,
		long[] accountEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().setSupportTeamAccountEntries(supportTeamId, accountEntryIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.AccountEntry addAccountEntry(
		long userId, long corpProjectId, java.lang.String corpEntryName,
		java.lang.String name, java.lang.String code, int type, int industry,
		long partnerEntryId, boolean partnerManagedSupport, int tier,
		int maxCustomers, java.lang.String instructions,
		java.lang.String notes, java.lang.String[] languageIds,
		long[] supportRegionIds, java.lang.String street1,
		java.lang.String street2, java.lang.String street3,
		java.lang.String city, java.lang.String zip, long regionId,
		long countryId, java.lang.String ewsaDossieraProjectKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAccountEntry(userId, corpProjectId, corpEntryName, name,
			code, type, industry, partnerEntryId, partnerManagedSupport, tier,
			maxCustomers, instructions, notes, languageIds, supportRegionIds,
			street1, street2, street3, city, zip, regionId, countryId,
			ewsaDossieraProjectKey);
	}

	public static com.liferay.osb.model.AccountEntry addAccountEntryWithWorkflow(
		java.lang.String salesforceOpportunityKey,
		com.liferay.osb.model.AccountEntry accountEntry,
		com.liferay.osb.model.CorpProject corpProject,
		com.liferay.osb.model.PartnerEntry partnerEntry,
		com.liferay.portal.model.Address address,
		com.liferay.osb.model.AccountWorker accountWorker,
		java.util.List<com.liferay.osb.model.OrderEntry> orderEntries,
		java.util.ArrayList<com.liferay.portal.model.User> users,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAccountEntryWithWorkflow(salesforceOpportunityKey,
			accountEntry, corpProject, partnerEntry, address, accountWorker,
			orderEntries, users, serviceContext);
	}

	public static void addTrialAccountEntry(long userId, long trialLicenseKeyId)
		throws java.lang.Exception {
		getService().addTrialAccountEntry(userId, trialLicenseKeyId);
	}

	public static void auditAccountEntries()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().auditAccountEntries();
	}

	public static void auditAccountEntry(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().auditAccountEntry(userId, accountEntryId);
	}

	public static com.liferay.osb.model.AccountEntry fetchCorpProjectAccountEntry(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchCorpProjectAccountEntry(corpProjectId);
	}

	public static com.liferay.osb.model.AccountEntry fetchUserTrialAccountEntry(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchUserTrialAccountEntry(userId);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntries(statuses, start, end);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		int[] notTypes, int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntries(notTypes, statuses, start, end);
	}

	public static com.liferay.osb.model.AccountEntry getAccountEntryByCode(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntryByCode(code);
	}

	public static com.liferay.osb.model.AccountEntry getAccountEntryByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntryByName(name);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getActiveAccountEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getActiveAccountEntries(start, end);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getPartnerAccountEntries(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerAccountEntries(partnerEntryId);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getRedirectAccountEntries(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getRedirectAccountEntries(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getSecurityPatchAccountEntries(
		java.lang.String portletId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSecurityPatchAccountEntries(portletId, params);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getUserAccountEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserAccountEntries(userId, start, end);
	}

	public static int getUserAccountEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserAccountEntriesCount(userId);
	}

	public static java.util.List<java.lang.Long> getUserAccountEntryIds(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserAccountEntryIds(userId, start, end);
	}

	public static java.util.List<java.lang.String> getUserAccountEntryNames(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserAccountEntryNames(userId);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getUserActiveAccountEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserActiveAccountEntries(userId, start, end);
	}

	public static boolean hasValidLicenseAccountEntry(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasValidLicenseAccountEntry(userId);
	}

	public static boolean hasValidSupportAccountEntry(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasValidSupportAccountEntry(userId);
	}

	public static void recalculateHighestSupportResponse(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().recalculateHighestSupportResponse(accountEntryId);
	}

	public static void reindexAccountEntry(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().reindexAccountEntry(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> search(
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
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(createUserId, createDateGTDay, createDateGTMonth,
			createDateGTYear, createDateLTDay, createDateLTMonth,
			createDateLTYear, modifiedUserId, modifiedDateGTDay,
			modifiedDateGTMonth, modifiedDateGTYear, modifiedDateLTDay,
			modifiedDateLTMonth, modifiedDateLTYear, name, code, industries,
			partnerManagedSupport, tiers, statuses, instructions, notes,
			partnerEntryCode, street, countryId, regionId, city, zip, params,
			andOperator, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(keywords, params, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> search(
		java.lang.String name, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(name, code);
	}

	public static int searchCount(java.lang.Long createUserId,
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
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(createUserId, createDateGTDay,
			createDateGTMonth, createDateGTYear, createDateLTDay,
			createDateLTMonth, createDateLTYear, modifiedUserId,
			modifiedDateGTDay, modifiedDateGTMonth, modifiedDateGTYear,
			modifiedDateLTDay, modifiedDateLTMonth, modifiedDateLTYear, name,
			code, industries, partnerManagedSupport, tiers, statuses,
			instructions, notes, partnerEntryCode, street, countryId, regionId,
			city, zip, params, andOperator);
	}

	public static int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(keywords, params);
	}

	public static com.liferay.osb.model.AccountEntry updateAccountEntry(
		long userId, long accountEntryId, long corpProjectId,
		java.lang.String corpEntryName, java.lang.String name,
		java.lang.String code, int type, int industry, long partnerEntryId,
		boolean partnerManagedSupport, int tier, int maxCustomers,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String[] languageIds, long[] supportRegionIds,
		long addressId, java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId, java.lang.String ewsaDossieraProjectKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAccountEntry(userId, accountEntryId, corpProjectId,
			corpEntryName, name, code, type, industry, partnerEntryId,
			partnerManagedSupport, tier, maxCustomers, instructions, notes,
			languageIds, supportRegionIds, addressId, street1, street2,
			street3, city, zip, regionId, countryId, ewsaDossieraProjectKey);
	}

	public static void updateAccountEntryWithWorkflow(
		java.lang.String salesforceOpportunityKey,
		com.liferay.osb.model.AccountEntry accountEntry,
		com.liferay.osb.model.PartnerEntry partnerEntry,
		com.liferay.portal.model.Address address,
		java.util.List<com.liferay.osb.model.OrderEntry> orderEntries,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateAccountEntryWithWorkflow(salesforceOpportunityKey,
			accountEntry, partnerEntry, address, orderEntries, serviceContext);
	}

	public static com.liferay.osb.model.AccountEntry updateCorpProject(
		long accountEntryId, long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCorpProject(accountEntryId, corpProjectId);
	}

	public static com.liferay.osb.model.AccountEntry updateInstructions(
		long userId, long accountEntryId, java.lang.String instructions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateInstructions(userId, accountEntryId, instructions);
	}

	public static void updateLastAuditDate(long userId, long accountEntryId,
		java.lang.String auditLabel, java.lang.String auditValue)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateLastAuditDate(userId, accountEntryId, auditLabel, auditValue);
	}

	public static void updateStatus(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateStatus(accountEntryId);
	}

	public static com.liferay.osb.model.AccountEntry updateStatus(long userId,
		long accountEntryId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, accountEntryId, status, serviceContext);
	}

	public static com.liferay.osb.model.AccountEntry updateStatus(long userId,
		long accountEntryId, java.lang.String salesforceOpportunityKey,
		int status, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, accountEntryId,
			salesforceOpportunityKey, status, serviceContext);
	}

	public static com.liferay.osb.model.AccountEntry updateTier(long userId,
		long accountEntryId, int tier)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTier(userId, accountEntryId, tier);
	}

	public static void validate(com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().validate(accountEntry);
	}

	public static void clearService() {
		_service = null;
	}

	public static AccountEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AccountEntryLocalService.class.getName());

			if (invokableLocalService instanceof AccountEntryLocalService) {
				_service = (AccountEntryLocalService)invokableLocalService;
			}
			else {
				_service = new AccountEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AccountEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AccountEntryLocalService service) {
	}

	private static AccountEntryLocalService _service;
}