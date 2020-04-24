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
 * Provides a wrapper for {@link LoopStreamLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopStreamLocalService
 * @generated
 */
@ProviderType
public class LoopStreamLocalServiceWrapper implements LoopStreamLocalService,
	ServiceWrapper<LoopStreamLocalService> {
	public LoopStreamLocalServiceWrapper(
		LoopStreamLocalService loopStreamLocalService) {
		_loopStreamLocalService = loopStreamLocalService;
	}

	/**
	* Adds the loop stream to the database. Also notifies the appropriate model listeners.
	*
	* @param loopStream the loop stream
	* @return the loop stream that was added
	*/
	@Override
	public com.liferay.osb.loop.model.LoopStream addLoopStream(
		com.liferay.osb.loop.model.LoopStream loopStream) {
		return _loopStreamLocalService.addLoopStream(loopStream);
	}

	/**
	* Creates a new loop stream with the primary key. Does not add the loop stream to the database.
	*
	* @param loopStreamId the primary key for the new loop stream
	* @return the new loop stream
	*/
	@Override
	public com.liferay.osb.loop.model.LoopStream createLoopStream(
		long loopStreamId) {
		return _loopStreamLocalService.createLoopStream(loopStreamId);
	}

	/**
	* Deletes the loop stream with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopStreamId the primary key of the loop stream
	* @return the loop stream that was removed
	* @throws PortalException if a loop stream with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopStream deleteLoopStream(
		long loopStreamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopStreamLocalService.deleteLoopStream(loopStreamId);
	}

	/**
	* Deletes the loop stream from the database. Also notifies the appropriate model listeners.
	*
	* @param loopStream the loop stream
	* @return the loop stream that was removed
	*/
	@Override
	public com.liferay.osb.loop.model.LoopStream deleteLoopStream(
		com.liferay.osb.loop.model.LoopStream loopStream) {
		return _loopStreamLocalService.deleteLoopStream(loopStream);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopStreamLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopStreamLocalService.dynamicQuery();
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
		return _loopStreamLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopStreamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopStreamLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopStreamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopStreamLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _loopStreamLocalService.dynamicQueryCount(dynamicQuery);
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
		return _loopStreamLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopStream fetchLoopStream(
		long loopStreamId) {
		return _loopStreamLocalService.fetchLoopStream(loopStreamId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _loopStreamLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _loopStreamLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the loop stream with the primary key.
	*
	* @param loopStreamId the primary key of the loop stream
	* @return the loop stream
	* @throws PortalException if a loop stream with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopStream getLoopStream(
		long loopStreamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopStreamLocalService.getLoopStream(loopStreamId);
	}

	/**
	* Returns a range of all the loop streams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopStreamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop streams
	* @param end the upper bound of the range of loop streams (not inclusive)
	* @return the range of loop streams
	*/
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopStream> getLoopStreams(
		int start, int end) {
		return _loopStreamLocalService.getLoopStreams(start, end);
	}

	/**
	* Returns the number of loop streams.
	*
	* @return the number of loop streams
	*/
	@Override
	public int getLoopStreamsCount() {
		return _loopStreamLocalService.getLoopStreamsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopStreamLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopStreamLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the loop stream in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopStream the loop stream
	* @return the loop stream that was updated
	*/
	@Override
	public com.liferay.osb.loop.model.LoopStream updateLoopStream(
		com.liferay.osb.loop.model.LoopStream loopStream) {
		return _loopStreamLocalService.updateLoopStream(loopStream);
	}

	@Override
	public LoopStreamLocalService getWrappedService() {
		return _loopStreamLocalService;
	}

	@Override
	public void setWrappedService(LoopStreamLocalService loopStreamLocalService) {
		_loopStreamLocalService = loopStreamLocalService;
	}

	private LoopStreamLocalService _loopStreamLocalService;
}