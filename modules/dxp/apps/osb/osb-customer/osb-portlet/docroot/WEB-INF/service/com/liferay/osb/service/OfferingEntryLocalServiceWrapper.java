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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OfferingEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OfferingEntryLocalService
 * @generated
 */
public class OfferingEntryLocalServiceWrapper
	implements OfferingEntryLocalService,
		ServiceWrapper<OfferingEntryLocalService> {
	public OfferingEntryLocalServiceWrapper(
		OfferingEntryLocalService offeringEntryLocalService) {
		_offeringEntryLocalService = offeringEntryLocalService;
	}

	/**
	* Adds the offering entry to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry addOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.addOfferingEntry(offeringEntry);
	}

	/**
	* Creates a new offering entry with the primary key. Does not add the offering entry to the database.
	*
	* @param offeringEntryId the primary key for the new offering entry
	* @return the new offering entry
	*/
	public com.liferay.osb.model.OfferingEntry createOfferingEntry(
		long offeringEntryId) {
		return _offeringEntryLocalService.createOfferingEntry(offeringEntryId);
	}

	/**
	* Deletes the offering entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry that was removed
	* @throws PortalException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry deleteOfferingEntry(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.deleteOfferingEntry(offeringEntryId);
	}

	/**
	* Deletes the offering entry from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry deleteOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.deleteOfferingEntry(offeringEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _offeringEntryLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.OfferingEntry fetchOfferingEntry(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.fetchOfferingEntry(offeringEntryId);
	}

	/**
	* Returns the offering entry with the primary key.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry
	* @throws PortalException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry getOfferingEntry(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.getOfferingEntry(offeringEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the offering entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of offering entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OfferingEntry> getOfferingEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.getOfferingEntries(start, end);
	}

	/**
	* Returns the number of offering entries.
	*
	* @return the number of offering entries
	* @throws SystemException if a system exception occurred
	*/
	public int getOfferingEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.getOfferingEntriesCount();
	}

	/**
	* Updates the offering entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.updateOfferingEntry(offeringEntry);
	}

	/**
	* Updates the offering entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @param merge whether to merge the offering entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the offering entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.updateOfferingEntry(offeringEntry,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _offeringEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_offeringEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _offeringEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.OfferingEntry addOfferingEntry(long userId,
		long accountEntryId, long orderEntryId, long productEntryId,
		long supportResponseId, java.lang.String productDescription, int type,
		int version, boolean licenses, long licenseLifetime,
		long maxConcurrentUsers, long maxUsers, boolean supportTickets,
		long supportLifetime, int sizing, int quantity, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.addOfferingEntry(userId,
			accountEntryId, orderEntryId, productEntryId, supportResponseId,
			productDescription, type, version, licenses, licenseLifetime,
			maxConcurrentUsers, maxUsers, supportTickets, supportLifetime,
			sizing, quantity, status);
	}

	public void checkOfferingEntries() throws java.lang.Exception {
		_offeringEntryLocalService.checkOfferingEntries();
	}

	public java.util.List<com.liferay.osb.model.OfferingEntry> getAccountEntryOfferingEntries(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.getAccountEntryOfferingEntries(accountEntryId);
	}

	public int getAccountEntryOfferingEntriesCount(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.getAccountEntryOfferingEntriesCount(accountEntryId);
	}

	public java.util.List<com.liferay.osb.model.OfferingEntry> getOrderEntryOfferingEntries(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.getOrderEntryOfferingEntries(orderEntryId);
	}

	public boolean hasActiveTrialOfferingEntry(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.hasActiveTrialOfferingEntry(userId);
	}

	public java.util.List<com.liferay.osb.model.OfferingEntry> search(
		long userId, long accountEntryId, int[] types, int[] statuses,
		int supportEndDateGTDay, int supportEndDateGTMonth,
		int supportEndDateGTYear, int supportEndDateLTDay,
		int supportEndDateLTMonth, int supportEndDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.search(userId, accountEntryId, types,
			statuses, supportEndDateGTDay, supportEndDateGTMonth,
			supportEndDateGTYear, supportEndDateLTDay, supportEndDateLTMonth,
			supportEndDateLTYear, params, andSearch, start, end, obc);
	}

	public int searchCount(long userId, long accountEntryId, int[] types,
		int[] statuses, int supportEndDateGTDay, int supportEndDateGTMonth,
		int supportEndDateGTYear, int supportEndDateLTDay,
		int supportEndDateLTMonth, int supportEndDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.searchCount(userId, accountEntryId,
			types, statuses, supportEndDateGTDay, supportEndDateGTMonth,
			supportEndDateGTYear, supportEndDateLTDay, supportEndDateLTMonth,
			supportEndDateLTYear, params, andSearch);
	}

	public com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		long userId, long offeringEntryId, long accountEntryId,
		long orderEntryId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, int type, int version,
		boolean licenses, long licenseLifetime, long maxConcurrentUsers,
		long maxUsers, boolean supportTickets, long supportLifetime,
		int sizing, int quantity)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.updateOfferingEntry(userId,
			offeringEntryId, accountEntryId, orderEntryId, productEntryId,
			supportResponseId, productDescription, type, version, licenses,
			licenseLifetime, maxConcurrentUsers, maxUsers, supportTickets,
			supportLifetime, sizing, quantity);
	}

	public com.liferay.osb.model.OfferingEntry updateStatus(long userId,
		long offeringEntryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringEntryLocalService.updateStatus(userId, offeringEntryId,
			status);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public OfferingEntryLocalService getWrappedOfferingEntryLocalService() {
		return _offeringEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedOfferingEntryLocalService(
		OfferingEntryLocalService offeringEntryLocalService) {
		_offeringEntryLocalService = offeringEntryLocalService;
	}

	public OfferingEntryLocalService getWrappedService() {
		return _offeringEntryLocalService;
	}

	public void setWrappedService(
		OfferingEntryLocalService offeringEntryLocalService) {
		_offeringEntryLocalService = offeringEntryLocalService;
	}

	private OfferingEntryLocalService _offeringEntryLocalService;
}