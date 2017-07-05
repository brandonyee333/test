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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for AccountEntry. This utility wraps
 * {@link com.liferay.osb.service.impl.AccountEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLocalService
 * @see com.liferay.osb.service.base.AccountEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class AccountEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AccountEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasSupportRegionAccountEntries(long supportRegionId) {
		return getService().hasSupportRegionAccountEntries(supportRegionId);
	}

	public static boolean hasSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId) {
		return getService()
				   .hasSupportRegionAccountEntry(supportRegionId, accountEntryId);
	}

	public static boolean hasSupportTeamAccountEntries(long supportTeamId) {
		return getService().hasSupportTeamAccountEntries(supportTeamId);
	}

	public static boolean hasSupportTeamAccountEntry(long supportTeamId,
		long accountEntryId) {
		return getService()
				   .hasSupportTeamAccountEntry(supportTeamId, accountEntryId);
	}

	/**
	* Adds the account entry to the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @return the account entry that was added
	*/
	public static com.liferay.osb.model.AccountEntry addAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry) {
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
	* Deletes the account entry from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @return the account entry that was removed
	*/
	public static com.liferay.osb.model.AccountEntry deleteAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry) {
		return getService().deleteAccountEntry(accountEntry);
	}

	/**
	* Deletes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry that was removed
	* @throws PortalException if a account entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.AccountEntry deleteAccountEntry(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAccountEntry(accountEntryId);
	}

	public static com.liferay.osb.model.AccountEntry fetchAccountEntry(
		long accountEntryId) {
		return getService().fetchAccountEntry(accountEntryId);
	}

	/**
	* Returns the account entry with the primary key.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry
	* @throws PortalException if a account entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.AccountEntry getAccountEntry(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAccountEntry(accountEntryId);
	}

	/**
	* Updates the account entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEntry the account entry
	* @return the account entry that was updated
	*/
	public static com.liferay.osb.model.AccountEntry updateAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry) {
		return getService().updateAccountEntry(accountEntry);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of account entries.
	*
	* @return the number of account entries
	*/
	public static int getAccountEntriesCount() {
		return getService().getAccountEntriesCount();
	}

	public static int getSupportRegionAccountEntriesCount(long supportRegionId) {
		return getService().getSupportRegionAccountEntriesCount(supportRegionId);
	}

	public static int getSupportTeamAccountEntriesCount(long supportTeamId) {
		return getService().getSupportTeamAccountEntriesCount(supportTeamId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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
	public static java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		int start, int end) {
		return getService().getAccountEntries(start, end);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId) {
		return getService().getSupportRegionAccountEntries(supportRegionId);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId, int start, int end) {
		return getService()
				   .getSupportRegionAccountEntries(supportRegionId, start, end);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.AccountEntry> orderByComparator) {
		return getService()
				   .getSupportRegionAccountEntries(supportRegionId, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getSupportTeamAccountEntries(
		long supportTeamId) {
		return getService().getSupportTeamAccountEntries(supportTeamId);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getSupportTeamAccountEntries(
		long supportTeamId, int start, int end) {
		return getService()
				   .getSupportTeamAccountEntries(supportTeamId, start, end);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getSupportTeamAccountEntries(
		long supportTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.AccountEntry> orderByComparator) {
		return getService()
				   .getSupportTeamAccountEntries(supportTeamId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Returns the supportRegionIds of the support regions associated with the account entry.
	*
	* @param accountEntryId the accountEntryId of the account entry
	* @return long[] the supportRegionIds of support regions associated with the account entry
	*/
	public static long[] getSupportRegionPrimaryKeys(long accountEntryId) {
		return getService().getSupportRegionPrimaryKeys(accountEntryId);
	}

	/**
	* Returns the supportTeamIds of the support teams associated with the account entry.
	*
	* @param accountEntryId the accountEntryId of the account entry
	* @return long[] the supportTeamIds of support teams associated with the account entry
	*/
	public static long[] getSupportTeamPrimaryKeys(long accountEntryId) {
		return getService().getSupportTeamPrimaryKeys(accountEntryId);
	}

	public static void addSupportRegionAccountEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries) {
		getService()
			.addSupportRegionAccountEntries(supportRegionId, accountEntries);
	}

	public static void addSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds) {
		getService()
			.addSupportRegionAccountEntries(supportRegionId, accountEntryIds);
	}

	public static void addSupportRegionAccountEntry(long supportRegionId,
		com.liferay.osb.model.AccountEntry accountEntry) {
		getService().addSupportRegionAccountEntry(supportRegionId, accountEntry);
	}

	public static void addSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId) {
		getService()
			.addSupportRegionAccountEntry(supportRegionId, accountEntryId);
	}

	public static void addSupportTeamAccountEntries(long supportTeamId,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries) {
		getService().addSupportTeamAccountEntries(supportTeamId, accountEntries);
	}

	public static void addSupportTeamAccountEntries(long supportTeamId,
		long[] accountEntryIds) {
		getService().addSupportTeamAccountEntries(supportTeamId, accountEntryIds);
	}

	public static void addSupportTeamAccountEntry(long supportTeamId,
		com.liferay.osb.model.AccountEntry accountEntry) {
		getService().addSupportTeamAccountEntry(supportTeamId, accountEntry);
	}

	public static void addSupportTeamAccountEntry(long supportTeamId,
		long accountEntryId) {
		getService().addSupportTeamAccountEntry(supportTeamId, accountEntryId);
	}

	public static void clearSupportRegionAccountEntries(long supportRegionId) {
		getService().clearSupportRegionAccountEntries(supportRegionId);
	}

	public static void clearSupportTeamAccountEntries(long supportTeamId) {
		getService().clearSupportTeamAccountEntries(supportTeamId);
	}

	public static void deleteSupportRegionAccountEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries) {
		getService()
			.deleteSupportRegionAccountEntries(supportRegionId, accountEntries);
	}

	public static void deleteSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds) {
		getService()
			.deleteSupportRegionAccountEntries(supportRegionId, accountEntryIds);
	}

	public static void deleteSupportRegionAccountEntry(long supportRegionId,
		com.liferay.osb.model.AccountEntry accountEntry) {
		getService()
			.deleteSupportRegionAccountEntry(supportRegionId, accountEntry);
	}

	public static void deleteSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId) {
		getService()
			.deleteSupportRegionAccountEntry(supportRegionId, accountEntryId);
	}

	public static void deleteSupportTeamAccountEntries(long supportTeamId,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries) {
		getService()
			.deleteSupportTeamAccountEntries(supportTeamId, accountEntries);
	}

	public static void deleteSupportTeamAccountEntries(long supportTeamId,
		long[] accountEntryIds) {
		getService()
			.deleteSupportTeamAccountEntries(supportTeamId, accountEntryIds);
	}

	public static void deleteSupportTeamAccountEntry(long supportTeamId,
		com.liferay.osb.model.AccountEntry accountEntry) {
		getService().deleteSupportTeamAccountEntry(supportTeamId, accountEntry);
	}

	public static void deleteSupportTeamAccountEntry(long supportTeamId,
		long accountEntryId) {
		getService().deleteSupportTeamAccountEntry(supportTeamId, accountEntryId);
	}

	public static void setSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds) {
		getService()
			.setSupportRegionAccountEntries(supportRegionId, accountEntryIds);
	}

	public static void setSupportTeamAccountEntries(long supportTeamId,
		long[] accountEntryIds) {
		getService().setSupportTeamAccountEntries(supportTeamId, accountEntryIds);
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

	private static AccountEntryLocalService _service;
}