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
 * Provides a wrapper for {@link LoopPersonRelLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopPersonRelLocalService
 * @generated
 */
@ProviderType
public class LoopPersonRelLocalServiceWrapper
	implements LoopPersonRelLocalService,
		ServiceWrapper<LoopPersonRelLocalService> {
	public LoopPersonRelLocalServiceWrapper(
		LoopPersonRelLocalService loopPersonRelLocalService) {
		_loopPersonRelLocalService = loopPersonRelLocalService;
	}

	/**
	* Adds the loop person rel to the database. Also notifies the appropriate model listeners.
	*
	* @param loopPersonRel the loop person rel
	* @return the loop person rel that was added
	*/
	@Override
	public com.liferay.osb.loop.model.LoopPersonRel addLoopPersonRel(
		com.liferay.osb.loop.model.LoopPersonRel loopPersonRel) {
		return _loopPersonRelLocalService.addLoopPersonRel(loopPersonRel);
	}

	/**
	* Creates a new loop person rel with the primary key. Does not add the loop person rel to the database.
	*
	* @param loopPersonRelId the primary key for the new loop person rel
	* @return the new loop person rel
	*/
	@Override
	public com.liferay.osb.loop.model.LoopPersonRel createLoopPersonRel(
		long loopPersonRelId) {
		return _loopPersonRelLocalService.createLoopPersonRel(loopPersonRelId);
	}

	/**
	* Deletes the loop person rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopPersonRelId the primary key of the loop person rel
	* @return the loop person rel that was removed
	* @throws PortalException if a loop person rel with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopPersonRel deleteLoopPersonRel(
		long loopPersonRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopPersonRelLocalService.deleteLoopPersonRel(loopPersonRelId);
	}

	/**
	* Deletes the loop person rel from the database. Also notifies the appropriate model listeners.
	*
	* @param loopPersonRel the loop person rel
	* @return the loop person rel that was removed
	*/
	@Override
	public com.liferay.osb.loop.model.LoopPersonRel deleteLoopPersonRel(
		com.liferay.osb.loop.model.LoopPersonRel loopPersonRel) {
		return _loopPersonRelLocalService.deleteLoopPersonRel(loopPersonRel);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopPersonRelLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopPersonRelLocalService.dynamicQuery();
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
		return _loopPersonRelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopPersonRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopPersonRelLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopPersonRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopPersonRelLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _loopPersonRelLocalService.dynamicQueryCount(dynamicQuery);
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
		return _loopPersonRelLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopPersonRel fetchLoopPersonRel(
		long loopPersonRelId) {
		return _loopPersonRelLocalService.fetchLoopPersonRel(loopPersonRelId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _loopPersonRelLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _loopPersonRelLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the loop person rel with the primary key.
	*
	* @param loopPersonRelId the primary key of the loop person rel
	* @return the loop person rel
	* @throws PortalException if a loop person rel with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopPersonRel getLoopPersonRel(
		long loopPersonRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopPersonRelLocalService.getLoopPersonRel(loopPersonRelId);
	}

	/**
	* Returns a range of all the loop person rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopPersonRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop person rels
	* @param end the upper bound of the range of loop person rels (not inclusive)
	* @return the range of loop person rels
	*/
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopPersonRel> getLoopPersonRels(
		int start, int end) {
		return _loopPersonRelLocalService.getLoopPersonRels(start, end);
	}

	/**
	* Returns the number of loop person rels.
	*
	* @return the number of loop person rels
	*/
	@Override
	public int getLoopPersonRelsCount() {
		return _loopPersonRelLocalService.getLoopPersonRelsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopPersonRelLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopPersonRelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the loop person rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopPersonRel the loop person rel
	* @return the loop person rel that was updated
	*/
	@Override
	public com.liferay.osb.loop.model.LoopPersonRel updateLoopPersonRel(
		com.liferay.osb.loop.model.LoopPersonRel loopPersonRel) {
		return _loopPersonRelLocalService.updateLoopPersonRel(loopPersonRel);
	}

	@Override
	public LoopPersonRelLocalService getWrappedService() {
		return _loopPersonRelLocalService;
	}

	@Override
	public void setWrappedService(
		LoopPersonRelLocalService loopPersonRelLocalService) {
		_loopPersonRelLocalService = loopPersonRelLocalService;
	}

	private LoopPersonRelLocalService _loopPersonRelLocalService;
}