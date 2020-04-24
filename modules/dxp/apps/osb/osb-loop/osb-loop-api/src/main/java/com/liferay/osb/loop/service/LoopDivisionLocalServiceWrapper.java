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

package com.liferay.osb.loop.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LoopDivisionLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopDivisionLocalService
 * @generated
 */
@ProviderType
public class LoopDivisionLocalServiceWrapper implements LoopDivisionLocalService,
	ServiceWrapper<LoopDivisionLocalService> {
	public LoopDivisionLocalServiceWrapper(
		LoopDivisionLocalService loopDivisionLocalService) {
		_loopDivisionLocalService = loopDivisionLocalService;
	}

	/**
	* Adds the loop division to the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivision the loop division
	* @return the loop division that was added
	*/
	@Override
	public com.liferay.osb.loop.model.LoopDivision addLoopDivision(
		com.liferay.osb.loop.model.LoopDivision loopDivision) {
		return _loopDivisionLocalService.addLoopDivision(loopDivision);
	}

	/**
	* Creates a new loop division with the primary key. Does not add the loop division to the database.
	*
	* @param loopDivisionId the primary key for the new loop division
	* @return the new loop division
	*/
	@Override
	public com.liferay.osb.loop.model.LoopDivision createLoopDivision(
		long loopDivisionId) {
		return _loopDivisionLocalService.createLoopDivision(loopDivisionId);
	}

	/**
	* Deletes the loop division with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivisionId the primary key of the loop division
	* @return the loop division that was removed
	* @throws PortalException if a loop division with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopDivision deleteLoopDivision(
		long loopDivisionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopDivisionLocalService.deleteLoopDivision(loopDivisionId);
	}

	/**
	* Deletes the loop division from the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivision the loop division
	* @return the loop division that was removed
	*/
	@Override
	public com.liferay.osb.loop.model.LoopDivision deleteLoopDivision(
		com.liferay.osb.loop.model.LoopDivision loopDivision) {
		return _loopDivisionLocalService.deleteLoopDivision(loopDivision);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopDivisionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopDivisionLocalService.dynamicQuery();
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
		return _loopDivisionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopDivisionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopDivisionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _loopDivisionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _loopDivisionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopDivision fetchLoopDivision(
		long loopDivisionId) {
		return _loopDivisionLocalService.fetchLoopDivision(loopDivisionId);
	}

	@Override
	public com.liferay.osb.loop.model.LoopDivision fetchRootLoopDivision(
		long companyId) {
		return _loopDivisionLocalService.fetchRootLoopDivision(companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _loopDivisionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _loopDivisionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the loop division with the primary key.
	*
	* @param loopDivisionId the primary key of the loop division
	* @return the loop division
	* @throws PortalException if a loop division with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopDivision getLoopDivision(
		long loopDivisionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopDivisionLocalService.getLoopDivision(loopDivisionId);
	}

	/**
	* Returns a range of all the loop divisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @return the range of loop divisions
	*/
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopDivision> getLoopDivisions(
		int start, int end) {
		return _loopDivisionLocalService.getLoopDivisions(start, end);
	}

	/**
	* Returns the number of loop divisions.
	*
	* @return the number of loop divisions
	*/
	@Override
	public int getLoopDivisionsCount() {
		return _loopDivisionLocalService.getLoopDivisionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopDivisionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopDivisionLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public String getRootLoopDivisionName(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopDivisionLocalService.getRootLoopDivisionName(companyId);
	}

	/**
	* Updates the loop division in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopDivision the loop division
	* @return the loop division that was updated
	*/
	@Override
	public com.liferay.osb.loop.model.LoopDivision updateLoopDivision(
		com.liferay.osb.loop.model.LoopDivision loopDivision) {
		return _loopDivisionLocalService.updateLoopDivision(loopDivision);
	}

	@Override
	public LoopDivisionLocalService getWrappedService() {
		return _loopDivisionLocalService;
	}

	@Override
	public void setWrappedService(
		LoopDivisionLocalService loopDivisionLocalService) {
		_loopDivisionLocalService = loopDivisionLocalService;
	}

	private LoopDivisionLocalService _loopDivisionLocalService;
}