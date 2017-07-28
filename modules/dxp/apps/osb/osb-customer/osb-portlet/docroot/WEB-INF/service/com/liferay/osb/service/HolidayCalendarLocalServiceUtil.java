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
 * Provides the local service utility for HolidayCalendar. This utility wraps
 * {@link com.liferay.osb.service.impl.HolidayCalendarLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarLocalService
 * @see com.liferay.osb.service.base.HolidayCalendarLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.HolidayCalendarLocalServiceImpl
 * @generated
 */
@ProviderType
public class HolidayCalendarLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.HolidayCalendarLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the holiday calendar to the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendar the holiday calendar
	* @return the holiday calendar that was added
	*/
	public static com.liferay.osb.model.HolidayCalendar addHolidayCalendar(
		com.liferay.osb.model.HolidayCalendar holidayCalendar) {
		return getService().addHolidayCalendar(holidayCalendar);
	}

	public static com.liferay.osb.model.HolidayCalendar addHolidayCalendar(
		java.lang.String name, java.lang.String description,
		java.util.List<com.liferay.osb.model.HolidayEntry> holidayEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addHolidayCalendar(name, description, holidayEntries);
	}

	/**
	* Creates a new holiday calendar with the primary key. Does not add the holiday calendar to the database.
	*
	* @param holidayCalendarId the primary key for the new holiday calendar
	* @return the new holiday calendar
	*/
	public static com.liferay.osb.model.HolidayCalendar createHolidayCalendar(
		long holidayCalendarId) {
		return getService().createHolidayCalendar(holidayCalendarId);
	}

	/**
	* Deletes the holiday calendar from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendar the holiday calendar
	* @return the holiday calendar that was removed
	*/
	public static com.liferay.osb.model.HolidayCalendar deleteHolidayCalendar(
		com.liferay.osb.model.HolidayCalendar holidayCalendar) {
		return getService().deleteHolidayCalendar(holidayCalendar);
	}

	/**
	* Deletes the holiday calendar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar that was removed
	* @throws PortalException if a holiday calendar with the primary key could not be found
	*/
	public static com.liferay.osb.model.HolidayCalendar deleteHolidayCalendar(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteHolidayCalendar(holidayCalendarId);
	}

	public static com.liferay.osb.model.HolidayCalendar fetchHolidayCalendar(
		long holidayCalendarId) {
		return getService().fetchHolidayCalendar(holidayCalendarId);
	}

	/**
	* Returns the holiday calendar with the primary key.
	*
	* @param holidayCalendarId the primary key of the holiday calendar
	* @return the holiday calendar
	* @throws PortalException if a holiday calendar with the primary key could not be found
	*/
	public static com.liferay.osb.model.HolidayCalendar getHolidayCalendar(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getHolidayCalendar(holidayCalendarId);
	}

	/**
	* Updates the holiday calendar in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendar the holiday calendar
	* @return the holiday calendar that was updated
	*/
	public static com.liferay.osb.model.HolidayCalendar updateHolidayCalendar(
		com.liferay.osb.model.HolidayCalendar holidayCalendar) {
		return getService().updateHolidayCalendar(holidayCalendar);
	}

	public static com.liferay.osb.model.HolidayCalendar updateHolidayCalendar(
		long holidayCalendarId, java.lang.String name,
		java.lang.String description,
		java.util.List<com.liferay.osb.model.HolidayEntry> holidayEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateHolidayCalendar(holidayCalendarId, name, description,
			holidayEntries);
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
	* Returns the number of holiday calendars.
	*
	* @return the number of holiday calendars
	*/
	public static int getHolidayCalendarsCount() {
		return getService().getHolidayCalendarsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayCalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayCalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<com.liferay.osb.model.HolidayCalendar> getHolidayCalendars(
		int start, int end) {
		return getService().getHolidayCalendars(start, end);
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

	public static void clearService() {
		_service = null;
	}

	public static HolidayCalendarLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					HolidayCalendarLocalService.class.getName());

			if (invokableLocalService instanceof HolidayCalendarLocalService) {
				_service = (HolidayCalendarLocalService)invokableLocalService;
			}
			else {
				_service = new HolidayCalendarLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(HolidayCalendarLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static HolidayCalendarLocalService _service;
}