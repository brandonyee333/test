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
 * Provides a wrapper for {@link LoopJobTitleLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopJobTitleLocalService
 * @generated
 */
@ProviderType
public class LoopJobTitleLocalServiceWrapper implements LoopJobTitleLocalService,
	ServiceWrapper<LoopJobTitleLocalService> {
	public LoopJobTitleLocalServiceWrapper(
		LoopJobTitleLocalService loopJobTitleLocalService) {
		_loopJobTitleLocalService = loopJobTitleLocalService;
	}

	/**
	* Adds the loop job title to the database. Also notifies the appropriate model listeners.
	*
	* @param loopJobTitle the loop job title
	* @return the loop job title that was added
	*/
	@Override
	public com.liferay.osb.loop.model.LoopJobTitle addLoopJobTitle(
		com.liferay.osb.loop.model.LoopJobTitle loopJobTitle) {
		return _loopJobTitleLocalService.addLoopJobTitle(loopJobTitle);
	}

	/**
	* Creates a new loop job title with the primary key. Does not add the loop job title to the database.
	*
	* @param loopJobTitleId the primary key for the new loop job title
	* @return the new loop job title
	*/
	@Override
	public com.liferay.osb.loop.model.LoopJobTitle createLoopJobTitle(
		long loopJobTitleId) {
		return _loopJobTitleLocalService.createLoopJobTitle(loopJobTitleId);
	}

	/**
	* Deletes the loop job title with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopJobTitleId the primary key of the loop job title
	* @return the loop job title that was removed
	* @throws PortalException if a loop job title with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopJobTitle deleteLoopJobTitle(
		long loopJobTitleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopJobTitleLocalService.deleteLoopJobTitle(loopJobTitleId);
	}

	/**
	* Deletes the loop job title from the database. Also notifies the appropriate model listeners.
	*
	* @param loopJobTitle the loop job title
	* @return the loop job title that was removed
	*/
	@Override
	public com.liferay.osb.loop.model.LoopJobTitle deleteLoopJobTitle(
		com.liferay.osb.loop.model.LoopJobTitle loopJobTitle) {
		return _loopJobTitleLocalService.deleteLoopJobTitle(loopJobTitle);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopJobTitleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopJobTitleLocalService.dynamicQuery();
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
		return _loopJobTitleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopJobTitleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopJobTitleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopJobTitleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopJobTitleLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _loopJobTitleLocalService.dynamicQueryCount(dynamicQuery);
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
		return _loopJobTitleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopJobTitle fetchLoopJobTitle(
		long loopJobTitleId) {
		return _loopJobTitleLocalService.fetchLoopJobTitle(loopJobTitleId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _loopJobTitleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _loopJobTitleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the loop job title with the primary key.
	*
	* @param loopJobTitleId the primary key of the loop job title
	* @return the loop job title
	* @throws PortalException if a loop job title with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopJobTitle getLoopJobTitle(
		long loopJobTitleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopJobTitleLocalService.getLoopJobTitle(loopJobTitleId);
	}

	/**
	* Returns a range of all the loop job titles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopJobTitleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop job titles
	* @param end the upper bound of the range of loop job titles (not inclusive)
	* @return the range of loop job titles
	*/
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopJobTitle> getLoopJobTitles(
		int start, int end) {
		return _loopJobTitleLocalService.getLoopJobTitles(start, end);
	}

	/**
	* Returns the number of loop job titles.
	*
	* @return the number of loop job titles
	*/
	@Override
	public int getLoopJobTitlesCount() {
		return _loopJobTitleLocalService.getLoopJobTitlesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopJobTitleLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopJobTitleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the loop job title in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopJobTitle the loop job title
	* @return the loop job title that was updated
	*/
	@Override
	public com.liferay.osb.loop.model.LoopJobTitle updateLoopJobTitle(
		com.liferay.osb.loop.model.LoopJobTitle loopJobTitle) {
		return _loopJobTitleLocalService.updateLoopJobTitle(loopJobTitle);
	}

	@Override
	public LoopJobTitleLocalService getWrappedService() {
		return _loopJobTitleLocalService;
	}

	@Override
	public void setWrappedService(
		LoopJobTitleLocalService loopJobTitleLocalService) {
		_loopJobTitleLocalService = loopJobTitleLocalService;
	}

	private LoopJobTitleLocalService _loopJobTitleLocalService;
}