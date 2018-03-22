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
 * Provides a wrapper for {@link HolidayCalendarRelLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarRelLocalService
 * @generated
 */
@ProviderType
public class HolidayCalendarRelLocalServiceWrapper
	implements HolidayCalendarRelLocalService,
		ServiceWrapper<HolidayCalendarRelLocalService> {
	public HolidayCalendarRelLocalServiceWrapper(
		HolidayCalendarRelLocalService holidayCalendarRelLocalService) {
		_holidayCalendarRelLocalService = holidayCalendarRelLocalService;
	}

	@Override
	public boolean hasHolidayCalendarRel(long holidayCalendarId, long userId) {
		return _holidayCalendarRelLocalService.hasHolidayCalendarRel(holidayCalendarId,
			userId);
	}

	/**
	* Adds the holiday calendar rel to the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRel the holiday calendar rel
	* @return the holiday calendar rel that was added
	*/
	@Override
	public com.liferay.osb.model.HolidayCalendarRel addHolidayCalendarRel(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel) {
		return _holidayCalendarRelLocalService.addHolidayCalendarRel(holidayCalendarRel);
	}

	/**
	* Creates a new holiday calendar rel with the primary key. Does not add the holiday calendar rel to the database.
	*
	* @param holidayCalendarRelId the primary key for the new holiday calendar rel
	* @return the new holiday calendar rel
	*/
	@Override
	public com.liferay.osb.model.HolidayCalendarRel createHolidayCalendarRel(
		long holidayCalendarRelId) {
		return _holidayCalendarRelLocalService.createHolidayCalendarRel(holidayCalendarRelId);
	}

	/**
	* Deletes the holiday calendar rel from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRel the holiday calendar rel
	* @return the holiday calendar rel that was removed
	*/
	@Override
	public com.liferay.osb.model.HolidayCalendarRel deleteHolidayCalendarRel(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel) {
		return _holidayCalendarRelLocalService.deleteHolidayCalendarRel(holidayCalendarRel);
	}

	/**
	* Deletes the holiday calendar rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRelId the primary key of the holiday calendar rel
	* @return the holiday calendar rel that was removed
	* @throws PortalException if a holiday calendar rel with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.HolidayCalendarRel deleteHolidayCalendarRel(
		long holidayCalendarRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayCalendarRelLocalService.deleteHolidayCalendarRel(holidayCalendarRelId);
	}

	@Override
	public com.liferay.osb.model.HolidayCalendarRel fetchHolidayCalendarRel(
		long holidayCalendarRelId) {
		return _holidayCalendarRelLocalService.fetchHolidayCalendarRel(holidayCalendarRelId);
	}

	@Override
	public com.liferay.osb.model.HolidayCalendarRel getHolidayCalendarRel(
		long holidayCalendarId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayCalendarRelLocalService.getHolidayCalendarRel(holidayCalendarId,
			userId);
	}

	/**
	* Returns the holiday calendar rel with the primary key.
	*
	* @param holidayCalendarRelId the primary key of the holiday calendar rel
	* @return the holiday calendar rel
	* @throws PortalException if a holiday calendar rel with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.HolidayCalendarRel getHolidayCalendarRel(
		long holidayCalendarRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayCalendarRelLocalService.getHolidayCalendarRel(holidayCalendarRelId);
	}

	/**
	* Updates the holiday calendar rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRel the holiday calendar rel
	* @return the holiday calendar rel that was updated
	*/
	@Override
	public com.liferay.osb.model.HolidayCalendarRel updateHolidayCalendarRel(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel) {
		return _holidayCalendarRelLocalService.updateHolidayCalendarRel(holidayCalendarRel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _holidayCalendarRelLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _holidayCalendarRelLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _holidayCalendarRelLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayCalendarRelLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _holidayCalendarRelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of holiday calendar rels.
	*
	* @return the number of holiday calendar rels
	*/
	@Override
	public int getHolidayCalendarRelsCount() {
		return _holidayCalendarRelLocalService.getHolidayCalendarRelsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _holidayCalendarRelLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _holidayCalendarRelLocalService.getOSGiServiceIdentifier();
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
		return _holidayCalendarRelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _holidayCalendarRelLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _holidayCalendarRelLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the holiday calendar rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.HolidayCalendarRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @return the range of holiday calendar rels
	*/
	@Override
	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> getHolidayCalendarRels(
		int start, int end) {
		return _holidayCalendarRelLocalService.getHolidayCalendarRels(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> getHolidayCalendarRels(
		long holidayCalendarId) {
		return _holidayCalendarRelLocalService.getHolidayCalendarRels(holidayCalendarId);
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
		return _holidayCalendarRelLocalService.dynamicQueryCount(dynamicQuery);
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
		return _holidayCalendarRelLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void addUsers(long holidayCalendarId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_holidayCalendarRelLocalService.addUsers(holidayCalendarId, userIds);
	}

	@Override
	public void deleteHolidayCalendarRels(long holidayCalendarId) {
		_holidayCalendarRelLocalService.deleteHolidayCalendarRels(holidayCalendarId);
	}

	@Override
	public void deleteHolidayCalendarRels(long holidayCalendarId, long[] userIds) {
		_holidayCalendarRelLocalService.deleteHolidayCalendarRels(holidayCalendarId,
			userIds);
	}

	@Override
	public HolidayCalendarRelLocalService getWrappedService() {
		return _holidayCalendarRelLocalService;
	}

	@Override
	public void setWrappedService(
		HolidayCalendarRelLocalService holidayCalendarRelLocalService) {
		_holidayCalendarRelLocalService = holidayCalendarRelLocalService;
	}

	private HolidayCalendarRelLocalService _holidayCalendarRelLocalService;
}