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
 * This class is a wrapper for {@link HolidayCalendarLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HolidayCalendarLocalService
 * @generated
 */
public class HolidayCalendarLocalServiceWrapper
	implements HolidayCalendarLocalService,
		ServiceWrapper<HolidayCalendarLocalService> {
	public HolidayCalendarLocalServiceWrapper(
		HolidayCalendarLocalService holidayCalendarLocalService) {
		_holidayCalendarLocalService = holidayCalendarLocalService;
	}

	/**
	* Adds the holiday calendar to the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendar the holiday calendar
	* @return the holiday calendar that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendar addHolidayCalendar(
		com.liferay.osb.model.HolidayCalendar holidayCalendar)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarLocalService.addHolidayCalendar(holidayCalendar);
	}

	/**
	* Creates a new holiday calendar with the primary key. Does not add the holiday calendar to the database.
	*
	* @param holidayCalendarId the primary key for the new holiday calendar
	* @return the new holiday calendar
	*/
	public com.liferay.osb.model.HolidayCalendar createHolidayCalendar(
		long holidayCalendarId) {
		return _holidayCalendarLocalService.createHolidayCalendar(holidayCalendarId);
	}

	/**
	* Deletes the holiday calendar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar that was removed
	* @throws PortalException if a holiday calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendar deleteHolidayCalendar(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarLocalService.deleteHolidayCalendar(holidayCalendarId);
	}

	/**
	* Deletes the holiday calendar from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendar the holiday calendar
	* @return the holiday calendar that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendar deleteHolidayCalendar(
		com.liferay.osb.model.HolidayCalendar holidayCalendar)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarLocalService.deleteHolidayCalendar(holidayCalendar);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _holidayCalendarLocalService.dynamicQuery();
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
		return _holidayCalendarLocalService.dynamicQuery(dynamicQuery);
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
		return _holidayCalendarLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
		return _holidayCalendarLocalService.dynamicQuery(dynamicQuery, start,
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
		return _holidayCalendarLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.HolidayCalendar fetchHolidayCalendar(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarLocalService.fetchHolidayCalendar(holidayCalendarId);
	}

	/**
	* Returns the holiday calendar with the primary key.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar
	* @throws PortalException if a holiday calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendar getHolidayCalendar(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarLocalService.getHolidayCalendar(holidayCalendarId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the holiday calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendars
	* @param end the upper bound of the range of holiday calendars (not inclusive)
	* @return the range of holiday calendars
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendar> getHolidayCalendars(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarLocalService.getHolidayCalendars(start, end);
	}

	/**
	* Returns the number of holiday calendars.
	*
	* @return the number of holiday calendars
	* @throws SystemException if a system exception occurred
	*/
	public int getHolidayCalendarsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarLocalService.getHolidayCalendarsCount();
	}

	/**
	* Updates the holiday calendar in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendar the holiday calendar
	* @return the holiday calendar that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendar updateHolidayCalendar(
		com.liferay.osb.model.HolidayCalendar holidayCalendar)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarLocalService.updateHolidayCalendar(holidayCalendar);
	}

	/**
	* Updates the holiday calendar in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendar the holiday calendar
	* @param merge whether to merge the holiday calendar with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the holiday calendar that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendar updateHolidayCalendar(
		com.liferay.osb.model.HolidayCalendar holidayCalendar, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarLocalService.updateHolidayCalendar(holidayCalendar,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _holidayCalendarLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_holidayCalendarLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _holidayCalendarLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.HolidayCalendar addHolidayCalendar(
		java.lang.String name, java.lang.String description,
		java.util.List<com.liferay.osb.model.HolidayEntry> holidayEntries)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarLocalService.addHolidayCalendar(name,
			description, holidayEntries);
	}

	public com.liferay.osb.model.HolidayCalendar updateHolidayCalendar(
		long holidayCalendarId, java.lang.String name,
		java.lang.String description,
		java.util.List<com.liferay.osb.model.HolidayEntry> holidayEntries)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarLocalService.updateHolidayCalendar(holidayCalendarId,
			name, description, holidayEntries);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public HolidayCalendarLocalService getWrappedHolidayCalendarLocalService() {
		return _holidayCalendarLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedHolidayCalendarLocalService(
		HolidayCalendarLocalService holidayCalendarLocalService) {
		_holidayCalendarLocalService = holidayCalendarLocalService;
	}

	public HolidayCalendarLocalService getWrappedService() {
		return _holidayCalendarLocalService;
	}

	public void setWrappedService(
		HolidayCalendarLocalService holidayCalendarLocalService) {
		_holidayCalendarLocalService = holidayCalendarLocalService;
	}

	private HolidayCalendarLocalService _holidayCalendarLocalService;
}