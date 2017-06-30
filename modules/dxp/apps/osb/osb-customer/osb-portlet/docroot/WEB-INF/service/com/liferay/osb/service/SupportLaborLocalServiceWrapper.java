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
 * This class is a wrapper for {@link SupportLaborLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportLaborLocalService
 * @generated
 */
public class SupportLaborLocalServiceWrapper implements SupportLaborLocalService,
	ServiceWrapper<SupportLaborLocalService> {
	public SupportLaborLocalServiceWrapper(
		SupportLaborLocalService supportLaborLocalService) {
		_supportLaborLocalService = supportLaborLocalService;
	}

	/**
	* Adds the support labor to the database. Also notifies the appropriate model listeners.
	*
	* @param supportLabor the support labor
	* @return the support labor that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportLabor addSupportLabor(
		com.liferay.osb.model.SupportLabor supportLabor)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.addSupportLabor(supportLabor);
	}

	/**
	* Creates a new support labor with the primary key. Does not add the support labor to the database.
	*
	* @param supportLaborId the primary key for the new support labor
	* @return the new support labor
	*/
	public com.liferay.osb.model.SupportLabor createSupportLabor(
		long supportLaborId) {
		return _supportLaborLocalService.createSupportLabor(supportLaborId);
	}

	/**
	* Deletes the support labor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportLaborId the primary key of the support labor
	* @return the support labor that was removed
	* @throws PortalException if a support labor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportLabor deleteSupportLabor(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.deleteSupportLabor(supportLaborId);
	}

	/**
	* Deletes the support labor from the database. Also notifies the appropriate model listeners.
	*
	* @param supportLabor the support labor
	* @return the support labor that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportLabor deleteSupportLabor(
		com.liferay.osb.model.SupportLabor supportLabor)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.deleteSupportLabor(supportLabor);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportLaborLocalService.dynamicQuery();
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
		return _supportLaborLocalService.dynamicQuery(dynamicQuery);
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
		return _supportLaborLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _supportLaborLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _supportLaborLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.SupportLabor fetchSupportLabor(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.fetchSupportLabor(supportLaborId);
	}

	/**
	* Returns the support labor with the primary key.
	*
	* @param supportLaborId the primary key of the support labor
	* @return the support labor
	* @throws PortalException if a support labor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportLabor getSupportLabor(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.getSupportLabor(supportLaborId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the support labors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support labors
	* @param end the upper bound of the range of support labors (not inclusive)
	* @return the range of support labors
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportLabor> getSupportLabors(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.getSupportLabors(start, end);
	}

	/**
	* Returns the number of support labors.
	*
	* @return the number of support labors
	* @throws SystemException if a system exception occurred
	*/
	public int getSupportLaborsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.getSupportLaborsCount();
	}

	/**
	* Updates the support labor in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportLabor the support labor
	* @return the support labor that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportLabor updateSupportLabor(
		com.liferay.osb.model.SupportLabor supportLabor)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.updateSupportLabor(supportLabor);
	}

	/**
	* Updates the support labor in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportLabor the support labor
	* @param merge whether to merge the support labor with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the support labor that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportLabor updateSupportLabor(
		com.liferay.osb.model.SupportLabor supportLabor, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.updateSupportLabor(supportLabor, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _supportLaborLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_supportLaborLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportLaborLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.SupportLabor addSupportLabor(
		java.lang.String name, java.lang.String description,
		java.lang.String timeZoneId, int sunOpen, int sunClose, int monOpen,
		int monClose, int tueOpen, int tueClose, int wedOpen, int wedClose,
		int thuOpen, int thuClose, int friOpen, int friClose, int satOpen,
		int satClose)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.addSupportLabor(name, description,
			timeZoneId, sunOpen, sunClose, monOpen, monClose, tueOpen,
			tueClose, wedOpen, wedClose, thuOpen, thuClose, friOpen, friClose,
			satOpen, satClose);
	}

	public void addSupportWorkers(long[] supportWorkerIds, long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_supportLaborLocalService.addSupportWorkers(supportWorkerIds,
			supportLaborId);
	}

	public boolean hasSupportWorker(long supportWorkerId, long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.hasSupportWorker(supportWorkerId,
			supportLaborId);
	}

	public void removeSupportWorkers(long[] supportWorkerIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_supportLaborLocalService.removeSupportWorkers(supportWorkerIds);
	}

	public com.liferay.osb.model.SupportLabor updateSupportLabor(
		long supportLaborId, java.lang.String name,
		java.lang.String description, java.lang.String timeZoneId, int sunOpen,
		int sunClose, int monOpen, int monClose, int tueOpen, int tueClose,
		int wedOpen, int wedClose, int thuOpen, int thuClose, int friOpen,
		int friClose, int satOpen, int satClose)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportLaborLocalService.updateSupportLabor(supportLaborId,
			name, description, timeZoneId, sunOpen, sunClose, monOpen,
			monClose, tueOpen, tueClose, wedOpen, wedClose, thuOpen, thuClose,
			friOpen, friClose, satOpen, satClose);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SupportLaborLocalService getWrappedSupportLaborLocalService() {
		return _supportLaborLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSupportLaborLocalService(
		SupportLaborLocalService supportLaborLocalService) {
		_supportLaborLocalService = supportLaborLocalService;
	}

	public SupportLaborLocalService getWrappedService() {
		return _supportLaborLocalService;
	}

	public void setWrappedService(
		SupportLaborLocalService supportLaborLocalService) {
		_supportLaborLocalService = supportLaborLocalService;
	}

	private SupportLaborLocalService _supportLaborLocalService;
}