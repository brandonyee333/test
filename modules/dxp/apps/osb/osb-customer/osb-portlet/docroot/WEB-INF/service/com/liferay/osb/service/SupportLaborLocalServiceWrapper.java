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
 * Provides a wrapper for {@link SupportLaborLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SupportLaborLocalService
 * @generated
 */
@ProviderType
public class SupportLaborLocalServiceWrapper implements SupportLaborLocalService,
	ServiceWrapper<SupportLaborLocalService> {
	public SupportLaborLocalServiceWrapper(
		SupportLaborLocalService supportLaborLocalService) {
		_supportLaborLocalService = supportLaborLocalService;
	}

	@Override
	public boolean hasSupportWorker(long supportWorkerId, long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportLaborLocalService.hasSupportWorker(supportWorkerId,
			supportLaborId);
	}

	/**
	* Adds the support labor to the database. Also notifies the appropriate model listeners.
	*
	* @param supportLabor the support labor
	* @return the support labor that was added
	*/
	@Override
	public com.liferay.osb.model.SupportLabor addSupportLabor(
		com.liferay.osb.model.SupportLabor supportLabor) {
		return _supportLaborLocalService.addSupportLabor(supportLabor);
	}

	@Override
	public com.liferay.osb.model.SupportLabor addSupportLabor(
		java.lang.String name, java.lang.String description,
		java.lang.String timeZoneId, int sunOpen, int sunClose, int monOpen,
		int monClose, int tueOpen, int tueClose, int wedOpen, int wedClose,
		int thuOpen, int thuClose, int friOpen, int friClose, int satOpen,
		int satClose)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportLaborLocalService.addSupportLabor(name, description,
			timeZoneId, sunOpen, sunClose, monOpen, monClose, tueOpen,
			tueClose, wedOpen, wedClose, thuOpen, thuClose, friOpen, friClose,
			satOpen, satClose);
	}

	/**
	* Creates a new support labor with the primary key. Does not add the support labor to the database.
	*
	* @param supportLaborId the primary key for the new support labor
	* @return the new support labor
	*/
	@Override
	public com.liferay.osb.model.SupportLabor createSupportLabor(
		long supportLaborId) {
		return _supportLaborLocalService.createSupportLabor(supportLaborId);
	}

	/**
	* Deletes the support labor from the database. Also notifies the appropriate model listeners.
	*
	* @param supportLabor the support labor
	* @return the support labor that was removed
	*/
	@Override
	public com.liferay.osb.model.SupportLabor deleteSupportLabor(
		com.liferay.osb.model.SupportLabor supportLabor) {
		return _supportLaborLocalService.deleteSupportLabor(supportLabor);
	}

	/**
	* Deletes the support labor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportLaborId the primary key of the support labor
	* @return the support labor that was removed
	* @throws PortalException if a support labor with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportLabor deleteSupportLabor(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportLaborLocalService.deleteSupportLabor(supportLaborId);
	}

	@Override
	public com.liferay.osb.model.SupportLabor fetchSupportLabor(
		long supportLaborId) {
		return _supportLaborLocalService.fetchSupportLabor(supportLaborId);
	}

	/**
	* Returns the support labor with the primary key.
	*
	* @param supportLaborId the primary key of the support labor
	* @return the support labor
	* @throws PortalException if a support labor with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportLabor getSupportLabor(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportLaborLocalService.getSupportLabor(supportLaborId);
	}

	/**
	* Updates the support labor in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportLabor the support labor
	* @return the support labor that was updated
	*/
	@Override
	public com.liferay.osb.model.SupportLabor updateSupportLabor(
		com.liferay.osb.model.SupportLabor supportLabor) {
		return _supportLaborLocalService.updateSupportLabor(supportLabor);
	}

	@Override
	public com.liferay.osb.model.SupportLabor updateSupportLabor(
		long supportLaborId, java.lang.String name,
		java.lang.String description, java.lang.String timeZoneId, int sunOpen,
		int sunClose, int monOpen, int monClose, int tueOpen, int tueClose,
		int wedOpen, int wedClose, int thuOpen, int thuClose, int friOpen,
		int friClose, int satOpen, int satClose)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportLaborLocalService.updateSupportLabor(supportLaborId,
			name, description, timeZoneId, sunOpen, sunClose, monOpen,
			monClose, tueOpen, tueClose, wedOpen, wedClose, thuOpen, thuClose,
			friOpen, friClose, satOpen, satClose);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _supportLaborLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportLaborLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _supportLaborLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportLaborLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportLaborLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of support labors.
	*
	* @return the number of support labors
	*/
	@Override
	public int getSupportLaborsCount() {
		return _supportLaborLocalService.getSupportLaborsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportLaborLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _supportLaborLocalService.getOSGiServiceIdentifier();
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
		return _supportLaborLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportLaborLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportLaborLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the support labors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support labors
	* @param end the upper bound of the range of support labors (not inclusive)
	* @return the range of support labors
	*/
	@Override
	public java.util.List<com.liferay.osb.model.SupportLabor> getSupportLabors(
		int start, int end) {
		return _supportLaborLocalService.getSupportLabors(start, end);
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
		return _supportLaborLocalService.dynamicQueryCount(dynamicQuery);
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
		return _supportLaborLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void addSupportWorkers(long[] supportWorkerIds, long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_supportLaborLocalService.addSupportWorkers(supportWorkerIds,
			supportLaborId);
	}

	@Override
	public void removeSupportWorkers(long[] supportWorkerIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_supportLaborLocalService.removeSupportWorkers(supportWorkerIds);
	}

	@Override
	public SupportLaborLocalService getWrappedService() {
		return _supportLaborLocalService;
	}

	@Override
	public void setWrappedService(
		SupportLaborLocalService supportLaborLocalService) {
		_supportLaborLocalService = supportLaborLocalService;
	}

	private SupportLaborLocalService _supportLaborLocalService;
}