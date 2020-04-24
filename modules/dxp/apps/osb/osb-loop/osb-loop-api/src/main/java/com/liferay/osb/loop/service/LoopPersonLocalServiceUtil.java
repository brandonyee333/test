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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LoopPerson. This utility wraps
 * {@link com.liferay.osb.loop.service.impl.LoopPersonLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see LoopPersonLocalService
 * @see com.liferay.osb.loop.service.base.LoopPersonLocalServiceBaseImpl
 * @see com.liferay.osb.loop.service.impl.LoopPersonLocalServiceImpl
 * @generated
 */
@ProviderType
public class LoopPersonLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.loop.service.impl.LoopPersonLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.loop.model.LoopPerson addLoopPerson(
		long userId, long personUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addLoopPerson(userId, personUserId);
	}

	/**
	* Adds the loop person to the database. Also notifies the appropriate model listeners.
	*
	* @param loopPerson the loop person
	* @return the loop person that was added
	*/
	public static com.liferay.osb.loop.model.LoopPerson addLoopPerson(
		com.liferay.osb.loop.model.LoopPerson loopPerson) {
		return getService().addLoopPerson(loopPerson);
	}

	/**
	* Creates a new loop person with the primary key. Does not add the loop person to the database.
	*
	* @param loopPersonId the primary key for the new loop person
	* @return the new loop person
	*/
	public static com.liferay.osb.loop.model.LoopPerson createLoopPerson(
		long loopPersonId) {
		return getService().createLoopPerson(loopPersonId);
	}

	/**
	* Deletes the loop person with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopPersonId the primary key of the loop person
	* @return the loop person that was removed
	* @throws PortalException if a loop person with the primary key could not be found
	*/
	public static com.liferay.osb.loop.model.LoopPerson deleteLoopPerson(
		long loopPersonId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLoopPerson(loopPersonId);
	}

	/**
	* Deletes the loop person from the database. Also notifies the appropriate model listeners.
	*
	* @param loopPerson the loop person
	* @return the loop person that was removed
	*/
	public static com.liferay.osb.loop.model.LoopPerson deleteLoopPerson(
		com.liferay.osb.loop.model.LoopPerson loopPerson) {
		return getService().deleteLoopPerson(loopPerson);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.osb.loop.model.LoopPerson fetchLoopPerson(
		long loopPersonId) {
		return getService().fetchLoopPerson(loopPersonId);
	}

	public static com.liferay.osb.loop.model.LoopPerson fetchLoopPersonByPersonUserId(
		long personUserId) {
		return getService().fetchLoopPersonByPersonUserId(personUserId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the loop person with the primary key.
	*
	* @param loopPersonId the primary key of the loop person
	* @return the loop person
	* @throws PortalException if a loop person with the primary key could not be found
	*/
	public static com.liferay.osb.loop.model.LoopPerson getLoopPerson(
		long loopPersonId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLoopPerson(loopPersonId);
	}

	public static com.liferay.osb.loop.model.LoopPerson getLoopPersonByPersonUserId(
		long personUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLoopPersonByPersonUserId(personUserId);
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
	public static java.util.List<com.liferay.osb.loop.model.LoopPerson> getLoopPersons(
		int start, int end) {
		return getService().getLoopPersons(start, end);
	}

	/**
	* Returns the number of loop persons.
	*
	* @return the number of loop persons
	*/
	public static int getLoopPersonsCount() {
		return getService().getLoopPersonsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static boolean hasLoopPerson(long personUserId) {
		return getService().hasLoopPerson(personUserId);
	}

	/**
	* Updates the loop person in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopPerson the loop person
	* @return the loop person that was updated
	*/
	public static com.liferay.osb.loop.model.LoopPerson updateLoopPerson(
		com.liferay.osb.loop.model.LoopPerson loopPerson) {
		return getService().updateLoopPerson(loopPerson);
	}

	public static LoopPersonLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopPersonLocalService, LoopPersonLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopPersonLocalService.class);

		ServiceTracker<LoopPersonLocalService, LoopPersonLocalService> serviceTracker =
			new ServiceTracker<LoopPersonLocalService, LoopPersonLocalService>(bundle.getBundleContext(),
				LoopPersonLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}