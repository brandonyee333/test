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
 * Provides a wrapper for {@link LoopPersonLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopPersonLocalService
 * @generated
 */
@ProviderType
public class LoopPersonLocalServiceWrapper implements LoopPersonLocalService,
	ServiceWrapper<LoopPersonLocalService> {
	public LoopPersonLocalServiceWrapper(
		LoopPersonLocalService loopPersonLocalService) {
		_loopPersonLocalService = loopPersonLocalService;
	}

	@Override
	public com.liferay.osb.loop.model.LoopPerson addLoopPerson(long userId,
		long personUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopPersonLocalService.addLoopPerson(userId, personUserId);
	}

	/**
	* Adds the loop person to the database. Also notifies the appropriate model listeners.
	*
	* @param loopPerson the loop person
	* @return the loop person that was added
	*/
	@Override
	public com.liferay.osb.loop.model.LoopPerson addLoopPerson(
		com.liferay.osb.loop.model.LoopPerson loopPerson) {
		return _loopPersonLocalService.addLoopPerson(loopPerson);
	}

	/**
	* Creates a new loop person with the primary key. Does not add the loop person to the database.
	*
	* @param loopPersonId the primary key for the new loop person
	* @return the new loop person
	*/
	@Override
	public com.liferay.osb.loop.model.LoopPerson createLoopPerson(
		long loopPersonId) {
		return _loopPersonLocalService.createLoopPerson(loopPersonId);
	}

	/**
	* Deletes the loop person with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopPersonId the primary key of the loop person
	* @return the loop person that was removed
	* @throws PortalException if a loop person with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopPerson deleteLoopPerson(
		long loopPersonId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopPersonLocalService.deleteLoopPerson(loopPersonId);
	}

	/**
	* Deletes the loop person from the database. Also notifies the appropriate model listeners.
	*
	* @param loopPerson the loop person
	* @return the loop person that was removed
	*/
	@Override
	public com.liferay.osb.loop.model.LoopPerson deleteLoopPerson(
		com.liferay.osb.loop.model.LoopPerson loopPerson) {
		return _loopPersonLocalService.deleteLoopPerson(loopPerson);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopPersonLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopPersonLocalService.dynamicQuery();
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
		return _loopPersonLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopPersonLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopPersonLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _loopPersonLocalService.dynamicQueryCount(dynamicQuery);
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
		return _loopPersonLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopPerson fetchLoopPerson(
		long loopPersonId) {
		return _loopPersonLocalService.fetchLoopPerson(loopPersonId);
	}

	@Override
	public com.liferay.osb.loop.model.LoopPerson fetchLoopPersonByPersonUserId(
		long personUserId) {
		return _loopPersonLocalService.fetchLoopPersonByPersonUserId(personUserId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _loopPersonLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _loopPersonLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the loop person with the primary key.
	*
	* @param loopPersonId the primary key of the loop person
	* @return the loop person
	* @throws PortalException if a loop person with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopPerson getLoopPerson(
		long loopPersonId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopPersonLocalService.getLoopPerson(loopPersonId);
	}

	@Override
	public com.liferay.osb.loop.model.LoopPerson getLoopPersonByPersonUserId(
		long personUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopPersonLocalService.getLoopPersonByPersonUserId(personUserId);
	}

	/**
	* Returns a range of all the loop persons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopPersonModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop persons
	* @param end the upper bound of the range of loop persons (not inclusive)
	* @return the range of loop persons
	*/
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopPerson> getLoopPersons(
		int start, int end) {
		return _loopPersonLocalService.getLoopPersons(start, end);
	}

	/**
	* Returns the number of loop persons.
	*
	* @return the number of loop persons
	*/
	@Override
	public int getLoopPersonsCount() {
		return _loopPersonLocalService.getLoopPersonsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopPersonLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopPersonLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasLoopPerson(long personUserId) {
		return _loopPersonLocalService.hasLoopPerson(personUserId);
	}

	/**
	* Updates the loop person in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopPerson the loop person
	* @return the loop person that was updated
	*/
	@Override
	public com.liferay.osb.loop.model.LoopPerson updateLoopPerson(
		com.liferay.osb.loop.model.LoopPerson loopPerson) {
		return _loopPersonLocalService.updateLoopPerson(loopPerson);
	}

	@Override
	public LoopPersonLocalService getWrappedService() {
		return _loopPersonLocalService;
	}

	@Override
	public void setWrappedService(LoopPersonLocalService loopPersonLocalService) {
		_loopPersonLocalService = loopPersonLocalService;
	}

	private LoopPersonLocalService _loopPersonLocalService;
}