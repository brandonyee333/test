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
 * This class is a wrapper for {@link LCSSubscriptionEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LCSSubscriptionEntryLocalService
 * @generated
 */
public class LCSSubscriptionEntryLocalServiceWrapper
	implements LCSSubscriptionEntryLocalService,
		ServiceWrapper<LCSSubscriptionEntryLocalService> {
	public LCSSubscriptionEntryLocalServiceWrapper(
		LCSSubscriptionEntryLocalService lcsSubscriptionEntryLocalService) {
		_lcsSubscriptionEntryLocalService = lcsSubscriptionEntryLocalService;
	}

	/**
	* Adds the l c s subscription entry to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntry the l c s subscription entry
	* @return the l c s subscription entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LCSSubscriptionEntry addLCSSubscriptionEntry(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lcsSubscriptionEntryLocalService.addLCSSubscriptionEntry(lcsSubscriptionEntry);
	}

	/**
	* Creates a new l c s subscription entry with the primary key. Does not add the l c s subscription entry to the database.
	*
	* @param lcsSubscriptionEntryId the primary key for the new l c s subscription entry
	* @return the new l c s subscription entry
	*/
	public com.liferay.osb.model.LCSSubscriptionEntry createLCSSubscriptionEntry(
		long lcsSubscriptionEntryId) {
		return _lcsSubscriptionEntryLocalService.createLCSSubscriptionEntry(lcsSubscriptionEntryId);
	}

	/**
	* Deletes the l c s subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry that was removed
	* @throws PortalException if a l c s subscription entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LCSSubscriptionEntry deleteLCSSubscriptionEntry(
		long lcsSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _lcsSubscriptionEntryLocalService.deleteLCSSubscriptionEntry(lcsSubscriptionEntryId);
	}

	/**
	* Deletes the l c s subscription entry from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntry the l c s subscription entry
	* @return the l c s subscription entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LCSSubscriptionEntry deleteLCSSubscriptionEntry(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lcsSubscriptionEntryLocalService.deleteLCSSubscriptionEntry(lcsSubscriptionEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsSubscriptionEntryLocalService.dynamicQuery();
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
		return _lcsSubscriptionEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _lcsSubscriptionEntryLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
		return _lcsSubscriptionEntryLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _lcsSubscriptionEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.LCSSubscriptionEntry fetchLCSSubscriptionEntry(
		long lcsSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lcsSubscriptionEntryLocalService.fetchLCSSubscriptionEntry(lcsSubscriptionEntryId);
	}

	/**
	* Returns the l c s subscription entry with the primary key.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry
	* @throws PortalException if a l c s subscription entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LCSSubscriptionEntry getLCSSubscriptionEntry(
		long lcsSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _lcsSubscriptionEntryLocalService.getLCSSubscriptionEntry(lcsSubscriptionEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _lcsSubscriptionEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the l c s subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @return the range of l c s subscription entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.LCSSubscriptionEntry> getLCSSubscriptionEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lcsSubscriptionEntryLocalService.getLCSSubscriptionEntries(start,
			end);
	}

	/**
	* Returns the number of l c s subscription entries.
	*
	* @return the number of l c s subscription entries
	* @throws SystemException if a system exception occurred
	*/
	public int getLCSSubscriptionEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lcsSubscriptionEntryLocalService.getLCSSubscriptionEntriesCount();
	}

	/**
	* Updates the l c s subscription entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntry the l c s subscription entry
	* @return the l c s subscription entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LCSSubscriptionEntry updateLCSSubscriptionEntry(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lcsSubscriptionEntryLocalService.updateLCSSubscriptionEntry(lcsSubscriptionEntry);
	}

	/**
	* Updates the l c s subscription entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntry the l c s subscription entry
	* @param merge whether to merge the l c s subscription entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the l c s subscription entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.LCSSubscriptionEntry updateLCSSubscriptionEntry(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _lcsSubscriptionEntryLocalService.updateLCSSubscriptionEntry(lcsSubscriptionEntry,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _lcsSubscriptionEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_lcsSubscriptionEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _lcsSubscriptionEntryLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public java.util.List<com.liferay.osb.model.LCSSubscriptionEntry> getLCSSubscriptionEntries(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _lcsSubscriptionEntryLocalService.getLCSSubscriptionEntries(corpProjectId);
	}

	public void syncToLCS(long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_lcsSubscriptionEntryLocalService.syncToLCS(corpProjectId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public LCSSubscriptionEntryLocalService getWrappedLCSSubscriptionEntryLocalService() {
		return _lcsSubscriptionEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedLCSSubscriptionEntryLocalService(
		LCSSubscriptionEntryLocalService lcsSubscriptionEntryLocalService) {
		_lcsSubscriptionEntryLocalService = lcsSubscriptionEntryLocalService;
	}

	public LCSSubscriptionEntryLocalService getWrappedService() {
		return _lcsSubscriptionEntryLocalService;
	}

	public void setWrappedService(
		LCSSubscriptionEntryLocalService lcsSubscriptionEntryLocalService) {
		_lcsSubscriptionEntryLocalService = lcsSubscriptionEntryLocalService;
	}

	private LCSSubscriptionEntryLocalService _lcsSubscriptionEntryLocalService;
}