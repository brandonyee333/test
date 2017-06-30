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
 * This class is a wrapper for {@link HolidayEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HolidayEntryLocalService
 * @generated
 */
public class HolidayEntryLocalServiceWrapper implements HolidayEntryLocalService,
	ServiceWrapper<HolidayEntryLocalService> {
	public HolidayEntryLocalServiceWrapper(
		HolidayEntryLocalService holidayEntryLocalService) {
		_holidayEntryLocalService = holidayEntryLocalService;
	}

	/**
	* Adds the holiday entry to the database. Also notifies the appropriate model listeners.
	*
	* @param holidayEntry the holiday entry
	* @return the holiday entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayEntry addHolidayEntry(
		com.liferay.osb.model.HolidayEntry holidayEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.addHolidayEntry(holidayEntry);
	}

	/**
	* Creates a new holiday entry with the primary key. Does not add the holiday entry to the database.
	*
	* @param holidayEntryId the primary key for the new holiday entry
	* @return the new holiday entry
	*/
	public com.liferay.osb.model.HolidayEntry createHolidayEntry(
		long holidayEntryId) {
		return _holidayEntryLocalService.createHolidayEntry(holidayEntryId);
	}

	/**
	* Deletes the holiday entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayEntryId the primary key of the holiday entry
	* @return the holiday entry that was removed
	* @throws PortalException if a holiday entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayEntry deleteHolidayEntry(
		long holidayEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.deleteHolidayEntry(holidayEntryId);
	}

	/**
	* Deletes the holiday entry from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayEntry the holiday entry
	* @return the holiday entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayEntry deleteHolidayEntry(
		com.liferay.osb.model.HolidayEntry holidayEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.deleteHolidayEntry(holidayEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _holidayEntryLocalService.dynamicQuery();
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
		return _holidayEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _holidayEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _holidayEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _holidayEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.HolidayEntry fetchHolidayEntry(
		long holidayEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.fetchHolidayEntry(holidayEntryId);
	}

	/**
	* Returns the holiday entry with the primary key.
	*
	* @param holidayEntryId the primary key of the holiday entry
	* @return the holiday entry
	* @throws PortalException if a holiday entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayEntry getHolidayEntry(
		long holidayEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.getHolidayEntry(holidayEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the holiday entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of holiday entries
	* @param end the upper bound of the range of holiday entries (not inclusive)
	* @return the range of holiday entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayEntry> getHolidayEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.getHolidayEntries(start, end);
	}

	/**
	* Returns the number of holiday entries.
	*
	* @return the number of holiday entries
	* @throws SystemException if a system exception occurred
	*/
	public int getHolidayEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.getHolidayEntriesCount();
	}

	/**
	* Updates the holiday entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holidayEntry the holiday entry
	* @return the holiday entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayEntry updateHolidayEntry(
		com.liferay.osb.model.HolidayEntry holidayEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.updateHolidayEntry(holidayEntry);
	}

	/**
	* Updates the holiday entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holidayEntry the holiday entry
	* @param merge whether to merge the holiday entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the holiday entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayEntry updateHolidayEntry(
		com.liferay.osb.model.HolidayEntry holidayEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.updateHolidayEntry(holidayEntry, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _holidayEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_holidayEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _holidayEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.HolidayEntry addHolidayEntry(
		long holidayCalendarId, java.lang.String name,
		java.lang.String description, java.util.Date startDate,
		java.util.Date endDate, boolean repeatYearly)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.addHolidayEntry(holidayCalendarId,
			name, description, startDate, endDate, repeatYearly);
	}

	public java.util.List<com.liferay.osb.model.HolidayEntry> getHolidayEntries(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.getHolidayEntries(holidayCalendarId);
	}

	public java.util.List<com.liferay.osb.model.HolidayEntry> getHolidayEntriesBetween(
		long userId, java.util.Date startDate, java.util.Date endDate,
		java.util.TimeZone timeZone)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.getHolidayEntriesBetween(userId,
			startDate, endDate, timeZone);
	}

	public int getHolidayEntriesCount(long userId, java.util.Date date)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.getHolidayEntriesCount(userId, date);
	}

	public com.liferay.osb.model.HolidayEntry updateHolidayEntry(
		long holidayEntryId, long holidayCalendarId, java.lang.String name,
		java.lang.String description, java.util.Date startDate,
		java.util.Date endDate, boolean repeatYearly)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayEntryLocalService.updateHolidayEntry(holidayEntryId,
			holidayCalendarId, name, description, startDate, endDate,
			repeatYearly);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public HolidayEntryLocalService getWrappedHolidayEntryLocalService() {
		return _holidayEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedHolidayEntryLocalService(
		HolidayEntryLocalService holidayEntryLocalService) {
		_holidayEntryLocalService = holidayEntryLocalService;
	}

	public HolidayEntryLocalService getWrappedService() {
		return _holidayEntryLocalService;
	}

	public void setWrappedService(
		HolidayEntryLocalService holidayEntryLocalService) {
		_holidayEntryLocalService = holidayEntryLocalService;
	}

	private HolidayEntryLocalService _holidayEntryLocalService;
}