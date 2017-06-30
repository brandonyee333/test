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
 * This class is a wrapper for {@link HolidayCalendarRelLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HolidayCalendarRelLocalService
 * @generated
 */
public class HolidayCalendarRelLocalServiceWrapper
	implements HolidayCalendarRelLocalService,
		ServiceWrapper<HolidayCalendarRelLocalService> {
	public HolidayCalendarRelLocalServiceWrapper(
		HolidayCalendarRelLocalService holidayCalendarRelLocalService) {
		_holidayCalendarRelLocalService = holidayCalendarRelLocalService;
	}

	/**
	* Adds the holiday calendar rel to the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRel the holiday calendar rel
	* @return the holiday calendar rel that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel addHolidayCalendarRel(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.addHolidayCalendarRel(holidayCalendarRel);
	}

	/**
	* Creates a new holiday calendar rel with the primary key. Does not add the holiday calendar rel to the database.
	*
	* @param holidayCalendarRelId the primary key for the new holiday calendar rel
	* @return the new holiday calendar rel
	*/
	public com.liferay.osb.model.HolidayCalendarRel createHolidayCalendarRel(
		long holidayCalendarRelId) {
		return _holidayCalendarRelLocalService.createHolidayCalendarRel(holidayCalendarRelId);
	}

	/**
	* Deletes the holiday calendar rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRelId the primary key of the holiday calendar rel
	* @return the holiday calendar rel that was removed
	* @throws PortalException if a holiday calendar rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel deleteHolidayCalendarRel(
		long holidayCalendarRelId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.deleteHolidayCalendarRel(holidayCalendarRelId);
	}

	/**
	* Deletes the holiday calendar rel from the database. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRel the holiday calendar rel
	* @return the holiday calendar rel that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel deleteHolidayCalendarRel(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.deleteHolidayCalendarRel(holidayCalendarRel);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _holidayCalendarRelLocalService.dynamicQuery();
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
		return _holidayCalendarRelLocalService.dynamicQuery(dynamicQuery);
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
		return _holidayCalendarRelLocalService.dynamicQuery(dynamicQuery,
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
		return _holidayCalendarRelLocalService.dynamicQuery(dynamicQuery,
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
		return _holidayCalendarRelLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.HolidayCalendarRel fetchHolidayCalendarRel(
		long holidayCalendarRelId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.fetchHolidayCalendarRel(holidayCalendarRelId);
	}

	/**
	* Returns the holiday calendar rel with the primary key.
	*
	* @param holidayCalendarRelId the primary key of the holiday calendar rel
	* @return the holiday calendar rel
	* @throws PortalException if a holiday calendar rel with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel getHolidayCalendarRel(
		long holidayCalendarRelId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.getHolidayCalendarRel(holidayCalendarRelId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the holiday calendar rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of holiday calendar rels
	* @param end the upper bound of the range of holiday calendar rels (not inclusive)
	* @return the range of holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> getHolidayCalendarRels(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.getHolidayCalendarRels(start, end);
	}

	/**
	* Returns the number of holiday calendar rels.
	*
	* @return the number of holiday calendar rels
	* @throws SystemException if a system exception occurred
	*/
	public int getHolidayCalendarRelsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.getHolidayCalendarRelsCount();
	}

	/**
	* Updates the holiday calendar rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRel the holiday calendar rel
	* @return the holiday calendar rel that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel updateHolidayCalendarRel(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.updateHolidayCalendarRel(holidayCalendarRel);
	}

	/**
	* Updates the holiday calendar rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param holidayCalendarRel the holiday calendar rel
	* @param merge whether to merge the holiday calendar rel with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the holiday calendar rel that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.HolidayCalendarRel updateHolidayCalendarRel(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.updateHolidayCalendarRel(holidayCalendarRel,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _holidayCalendarRelLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_holidayCalendarRelLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _holidayCalendarRelLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public void addUsers(long holidayCalendarId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_holidayCalendarRelLocalService.addUsers(holidayCalendarId, userIds);
	}

	public void deleteHolidayCalendarRels(long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_holidayCalendarRelLocalService.deleteHolidayCalendarRels(holidayCalendarId);
	}

	public void deleteHolidayCalendarRels(long holidayCalendarId, long[] userIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_holidayCalendarRelLocalService.deleteHolidayCalendarRels(holidayCalendarId,
			userIds);
	}

	public com.liferay.osb.model.HolidayCalendarRel getHolidayCalendarRel(
		long holidayCalendarId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.getHolidayCalendarRel(holidayCalendarId,
			userId);
	}

	public java.util.List<com.liferay.osb.model.HolidayCalendarRel> getHolidayCalendarRels(
		long holidayCalendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.getHolidayCalendarRels(holidayCalendarId);
	}

	public boolean hasHolidayCalendarRel(long holidayCalendarId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _holidayCalendarRelLocalService.hasHolidayCalendarRel(holidayCalendarId,
			userId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public HolidayCalendarRelLocalService getWrappedHolidayCalendarRelLocalService() {
		return _holidayCalendarRelLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedHolidayCalendarRelLocalService(
		HolidayCalendarRelLocalService holidayCalendarRelLocalService) {
		_holidayCalendarRelLocalService = holidayCalendarRelLocalService;
	}

	public HolidayCalendarRelLocalService getWrappedService() {
		return _holidayCalendarRelLocalService;
	}

	public void setWrappedService(
		HolidayCalendarRelLocalService holidayCalendarRelLocalService) {
		_holidayCalendarRelLocalService = holidayCalendarRelLocalService;
	}

	private HolidayCalendarRelLocalService _holidayCalendarRelLocalService;
}