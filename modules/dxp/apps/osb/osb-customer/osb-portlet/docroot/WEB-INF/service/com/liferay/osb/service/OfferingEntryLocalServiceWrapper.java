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
 * Provides a wrapper for {@link OfferingEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntryLocalService
 * @generated
 */
@ProviderType
public class OfferingEntryLocalServiceWrapper
	implements OfferingEntryLocalService,
		ServiceWrapper<OfferingEntryLocalService> {
	public OfferingEntryLocalServiceWrapper(
		OfferingEntryLocalService offeringEntryLocalService) {
		_offeringEntryLocalService = offeringEntryLocalService;
	}

	@Override
	public boolean hasActiveSupportOfferingEntry(long accountEntryId) {
		return _offeringEntryLocalService.hasActiveSupportOfferingEntry(accountEntryId);
	}

	@Override
	public boolean hasActiveTrialOfferingEntry(long userId) {
		return _offeringEntryLocalService.hasActiveTrialOfferingEntry(userId);
	}

	/**
	* Adds the offering entry to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was added
	*/
	@Override
	public com.liferay.osb.model.OfferingEntry addOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry) {
		return _offeringEntryLocalService.addOfferingEntry(offeringEntry);
	}

	@Override
	public com.liferay.osb.model.OfferingEntry addOfferingEntry(long userId,
		long accountEntryId, long orderEntryId, long productEntryId,
		long supportResponseId, java.lang.String productDescription, int type,
		int version, boolean licenses, long licenseLifetime,
		long maxConcurrentUsers, long maxUsers, boolean supportTickets,
		long supportLifetime, int sizing, int quantity, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntryLocalService.addOfferingEntry(userId,
			accountEntryId, orderEntryId, productEntryId, supportResponseId,
			productDescription, type, version, licenses, licenseLifetime,
			maxConcurrentUsers, maxUsers, supportTickets, supportLifetime,
			sizing, quantity, status);
	}

	/**
	* Creates a new offering entry with the primary key. Does not add the offering entry to the database.
	*
	* @param offeringEntryId the primary key for the new offering entry
	* @return the new offering entry
	*/
	@Override
	public com.liferay.osb.model.OfferingEntry createOfferingEntry(
		long offeringEntryId) {
		return _offeringEntryLocalService.createOfferingEntry(offeringEntryId);
	}

	/**
	* Deletes the offering entry from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.osb.model.OfferingEntry deleteOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntryLocalService.deleteOfferingEntry(offeringEntry);
	}

	/**
	* Deletes the offering entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry that was removed
	* @throws PortalException if a offering entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.OfferingEntry deleteOfferingEntry(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntryLocalService.deleteOfferingEntry(offeringEntryId);
	}

	@Override
	public com.liferay.osb.model.OfferingEntry fetchOfferingEntry(
		long offeringEntryId) {
		return _offeringEntryLocalService.fetchOfferingEntry(offeringEntryId);
	}

	/**
	* Returns the offering entry with the primary key.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry
	* @throws PortalException if a offering entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.OfferingEntry getOfferingEntry(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntryLocalService.getOfferingEntry(offeringEntryId);
	}

	/**
	* Updates the offering entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was updated
	*/
	@Override
	public com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry) {
		return _offeringEntryLocalService.updateOfferingEntry(offeringEntry);
	}

	@Override
	public com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		long userId, long offeringEntryId, long accountEntryId,
		long orderEntryId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, int type, int version,
		boolean licenses, long licenseLifetime, long maxConcurrentUsers,
		long maxUsers, boolean supportTickets, long supportLifetime,
		int sizing, int quantity)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntryLocalService.updateOfferingEntry(userId,
			offeringEntryId, accountEntryId, orderEntryId, productEntryId,
			supportResponseId, productDescription, type, version, licenses,
			licenseLifetime, maxConcurrentUsers, maxUsers, supportTickets,
			supportLifetime, sizing, quantity);
	}

	@Override
	public com.liferay.osb.model.OfferingEntry updateStatus(long userId,
		long offeringEntryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntryLocalService.updateStatus(userId, offeringEntryId,
			status);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _offeringEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _offeringEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _offeringEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getAccountEntryOfferingEntriesCount(long accountEntryId) {
		return _offeringEntryLocalService.getAccountEntryOfferingEntriesCount(accountEntryId);
	}

	/**
	* Returns the number of offering entries.
	*
	* @return the number of offering entries
	*/
	@Override
	public int getOfferingEntriesCount() {
		return _offeringEntryLocalService.getOfferingEntriesCount();
	}

	@Override
	public int searchCount(long userId, long accountEntryId, int[] types,
		int[] statuses, int supportEndDateGTDay, int supportEndDateGTMonth,
		int supportEndDateGTYear, int supportEndDateLTDay,
		int supportEndDateLTMonth, int supportEndDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch) {
		return _offeringEntryLocalService.searchCount(userId, accountEntryId,
			types, statuses, supportEndDateGTDay, supportEndDateGTMonth,
			supportEndDateGTYear, supportEndDateLTDay, supportEndDateLTMonth,
			supportEndDateLTYear, params, andSearch);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _offeringEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _offeringEntryLocalService.getOSGiServiceIdentifier();
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
		return _offeringEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _offeringEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _offeringEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingEntry> getAccountEntryOfferingEntries(
		long accountEntryId) {
		return _offeringEntryLocalService.getAccountEntryOfferingEntries(accountEntryId);
	}

	/**
	* Returns a range of all the offering entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of offering entries
	*/
	@Override
	public java.util.List<com.liferay.osb.model.OfferingEntry> getOfferingEntries(
		int start, int end) {
		return _offeringEntryLocalService.getOfferingEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingEntry> getOrderEntryOfferingEntries(
		long orderEntryId) {
		return _offeringEntryLocalService.getOrderEntryOfferingEntries(orderEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingEntry> search(
		long userId, long accountEntryId, int[] types, int[] statuses,
		int supportEndDateGTDay, int supportEndDateGTMonth,
		int supportEndDateGTYear, int supportEndDateLTDay,
		int supportEndDateLTMonth, int supportEndDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _offeringEntryLocalService.search(userId, accountEntryId, types,
			statuses, supportEndDateGTDay, supportEndDateGTMonth,
			supportEndDateGTYear, supportEndDateLTDay, supportEndDateLTMonth,
			supportEndDateLTYear, params, andSearch, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OfferingEntry> search(
		long userId, long accountEntryId, long[] productEntryIds, int[] types,
		int[] statuses, java.util.Date supportEndDateGT,
		java.util.Date supportEndDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _offeringEntryLocalService.search(userId, accountEntryId,
			productEntryIds, types, statuses, supportEndDateGT,
			supportEndDateLT, params, andSearch, start, end, obc);
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
		return _offeringEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _offeringEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void checkOfferingEntries() throws java.lang.Exception {
		_offeringEntryLocalService.checkOfferingEntries();
	}

	@Override
	public OfferingEntryLocalService getWrappedService() {
		return _offeringEntryLocalService;
	}

	@Override
	public void setWrappedService(
		OfferingEntryLocalService offeringEntryLocalService) {
		_offeringEntryLocalService = offeringEntryLocalService;
	}

	private OfferingEntryLocalService _offeringEntryLocalService;
}