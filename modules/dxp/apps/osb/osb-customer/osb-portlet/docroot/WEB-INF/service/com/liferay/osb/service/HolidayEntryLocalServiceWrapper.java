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
 * Provides a wrapper for {@link HolidayEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HolidayEntryLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.osb.model.HolidayEntry addHolidayEntry(
		com.liferay.osb.model.HolidayEntry holidayEntry) {
		return _holidayEntryLocalService.addHolidayEntry(holidayEntry);
	}

	@Override
	public com.liferay.osb.model.HolidayEntry addHolidayEntry(
		long holidayCalendarId, java.lang.String name,
		java.lang.String description, java.util.Date startDate,
		java.util.Date endDate, boolean repeatYearly)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayEntryLocalService.addHolidayEntry(holidayCalendarId,
			name, description, startDate, endDate, repeatYearly);
	}

	/**
	* Creates a new holiday entry with the primary key. Does not add the holiday entry to the database.
	*
	* @param holidayEntryId the primary key for the new holiday entry
	* @return the new holiday entry
	*/
	@Override
	public com.liferay.osb.model.HolidayEntry createHolidayEntry(
		long holidayEntryId) {
		return _holidayEntryLocalService.createHolidayEntry(holidayEntryId);
	}

	/**
	* Deletes the holiday entry from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayEntry the holiday entry
	* @return the holiday entry that was removed
	*/
	@Override
	public com.liferay.osb.model.HolidayEntry deleteHolidayEntry(
		com.liferay.osb.model.HolidayEntry holidayEntry) {
		return _holidayEntryLocalService.deleteHolidayEntry(holidayEntry);
	}

	/**
	* Deletes the holiday entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayEntryId the primary key of the holiday entry
	* @return the holiday entry that was removed
	* @throws PortalException if a holiday entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.HolidayEntry deleteHolidayEntry(
		long holidayEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayEntryLocalService.deleteHolidayEntry(holidayEntryId);
	}

	@Override
	public com.liferay.osb.model.HolidayEntry fetchHolidayEntry(
		long holidayEntryId) {
		return _holidayEntryLocalService.fetchHolidayEntry(holidayEntryId);
	}

	/**
	* Returns the holiday entry with the primary key.
	*
	* @param holidayEntryId the primary key of the holiday entry
	* @return the holiday entry
	* @throws PortalException if a holiday entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.HolidayEntry getHolidayEntry(
		long holidayEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayEntryLocalService.getHolidayEntry(holidayEntryId);
	}

	/**
	* Updates the holiday entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holidayEntry the holiday entry
	* @return the holiday entry that was updated
	*/
	@Override
	public com.liferay.osb.model.HolidayEntry updateHolidayEntry(
		com.liferay.osb.model.HolidayEntry holidayEntry) {
		return _holidayEntryLocalService.updateHolidayEntry(holidayEntry);
	}

	@Override
	public com.liferay.osb.model.HolidayEntry updateHolidayEntry(
		long holidayEntryId, long holidayCalendarId, java.lang.String name,
		java.lang.String description, java.util.Date startDate,
		java.util.Date endDate, boolean repeatYearly)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayEntryLocalService.updateHolidayEntry(holidayEntryId,
			holidayCalendarId, name, description, startDate, endDate,
			repeatYearly);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _holidayEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _holidayEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _holidayEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of holiday entries.
	*
	* @return the number of holiday entries
	*/
	@Override
	public int getHolidayEntriesCount() {
		return _holidayEntryLocalService.getHolidayEntriesCount();
	}

	@Override
	public int getHolidayEntriesCount(long userId, java.util.Date date) {
		return _holidayEntryLocalService.getHolidayEntriesCount(userId, date);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _holidayEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _holidayEntryLocalService.getOSGiServiceIdentifier();
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
		return _holidayEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _holidayEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _holidayEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the holiday entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday entries
	* @param end the upper bound of the range of holiday entries (not inclusive)
	* @return the range of holiday entries
	*/
	@Override
	public java.util.List<com.liferay.osb.model.HolidayEntry> getHolidayEntries(
		int start, int end) {
		return _holidayEntryLocalService.getHolidayEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.HolidayEntry> getHolidayEntries(
		long holidayCalendarId) {
		return _holidayEntryLocalService.getHolidayEntries(holidayCalendarId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.HolidayEntry> getHolidayEntriesBetween(
		long userId, java.util.Date startDate, java.util.Date endDate,
		java.util.TimeZone timeZone) {
		return _holidayEntryLocalService.getHolidayEntriesBetween(userId,
			startDate, endDate, timeZone);
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
		return _holidayEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _holidayEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public HolidayEntryLocalService getWrappedService() {
		return _holidayEntryLocalService;
	}

	@Override
	public void setWrappedService(
		HolidayEntryLocalService holidayEntryLocalService) {
		_holidayEntryLocalService = holidayEntryLocalService;
	}

	private HolidayEntryLocalService _holidayEntryLocalService;
}