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
 * Provides a wrapper for {@link HolidayCalendarLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.osb.model.HolidayCalendar addHolidayCalendar(
		com.liferay.osb.model.HolidayCalendar holidayCalendar) {
		return _holidayCalendarLocalService.addHolidayCalendar(holidayCalendar);
	}

	@Override
	public com.liferay.osb.model.HolidayCalendar addHolidayCalendar(
		java.lang.String name, java.lang.String description,
		java.util.List<com.liferay.osb.model.HolidayEntry> holidayEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayCalendarLocalService.addHolidayCalendar(name,
			description, holidayEntries);
	}

	/**
	* Creates a new holiday calendar with the primary key. Does not add the holiday calendar to the database.
	*
	* @param holidayCalendarId the primary key for the new holiday calendar
	* @return the new holiday calendar
	*/
	@Override
	public com.liferay.osb.model.HolidayCalendar createHolidayCalendar(
		long holidayCalendarId) {
		return _holidayCalendarLocalService.createHolidayCalendar(holidayCalendarId);
	}

	/**
	* Deletes the holiday calendar from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendar the holiday calendar
	* @return the holiday calendar that was removed
	*/
	@Override
	public com.liferay.osb.model.HolidayCalendar deleteHolidayCalendar(
		com.liferay.osb.model.HolidayCalendar holidayCalendar) {
		return _holidayCalendarLocalService.deleteHolidayCalendar(holidayCalendar);
	}

	/**
	* Deletes the holiday calendar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar that was removed
	* @throws PortalException if a holiday calendar with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.HolidayCalendar deleteHolidayCalendar(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayCalendarLocalService.deleteHolidayCalendar(holidayCalendarId);
	}

	@Override
	public com.liferay.osb.model.HolidayCalendar fetchHolidayCalendar(
		long holidayCalendarId) {
		return _holidayCalendarLocalService.fetchHolidayCalendar(holidayCalendarId);
	}

	/**
	* Returns the holiday calendar with the primary key.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar
	* @throws PortalException if a holiday calendar with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.HolidayCalendar getHolidayCalendar(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayCalendarLocalService.getHolidayCalendar(holidayCalendarId);
	}

	/**
	* Updates the holiday calendar in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendar the holiday calendar
	* @return the holiday calendar that was updated
	*/
	@Override
	public com.liferay.osb.model.HolidayCalendar updateHolidayCalendar(
		com.liferay.osb.model.HolidayCalendar holidayCalendar) {
		return _holidayCalendarLocalService.updateHolidayCalendar(holidayCalendar);
	}

	@Override
	public com.liferay.osb.model.HolidayCalendar updateHolidayCalendar(
		long holidayCalendarId, java.lang.String name,
		java.lang.String description,
		java.util.List<com.liferay.osb.model.HolidayEntry> holidayEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayCalendarLocalService.updateHolidayCalendar(holidayCalendarId,
			name, description, holidayEntries);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _holidayCalendarLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _holidayCalendarLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _holidayCalendarLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayCalendarLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayCalendarLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of holiday calendars.
	*
	* @return the number of holiday calendars
	*/
	@Override
	public int getHolidayCalendarsCount() {
		return _holidayCalendarLocalService.getHolidayCalendarsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _holidayCalendarLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _holidayCalendarLocalService.getOSGiServiceIdentifier();
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
		return _holidayCalendarLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayCalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _holidayCalendarLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayCalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _holidayCalendarLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the holiday calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayCalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendars
	* @param end the upper bound of the range of holiday calendars (not inclusive)
	* @return the range of holiday calendars
	*/
	@Override
	public java.util.List<com.liferay.osb.model.HolidayCalendar> getHolidayCalendars(
		int start, int end) {
		return _holidayCalendarLocalService.getHolidayCalendars(start, end);
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
		return _holidayCalendarLocalService.dynamicQueryCount(dynamicQuery);
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
		return _holidayCalendarLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public HolidayCalendarLocalService getWrappedService() {
		return _holidayCalendarLocalService;
	}

	@Override
	public void setWrappedService(
		HolidayCalendarLocalService holidayCalendarLocalService) {
		_holidayCalendarLocalService = holidayCalendarLocalService;
	}

	private HolidayCalendarLocalService _holidayCalendarLocalService;
}