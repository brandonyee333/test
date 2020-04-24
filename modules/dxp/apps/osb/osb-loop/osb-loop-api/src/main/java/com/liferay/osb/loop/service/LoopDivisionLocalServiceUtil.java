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
 * Provides the local service utility for LoopDivision. This utility wraps
 * {@link com.liferay.osb.loop.service.impl.LoopDivisionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see LoopDivisionLocalService
 * @see com.liferay.osb.loop.service.base.LoopDivisionLocalServiceBaseImpl
 * @see com.liferay.osb.loop.service.impl.LoopDivisionLocalServiceImpl
 * @generated
 */
@ProviderType
public class LoopDivisionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.loop.service.impl.LoopDivisionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the loop division to the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivision the loop division
	* @return the loop division that was added
	*/
	public static com.liferay.osb.loop.model.LoopDivision addLoopDivision(
		com.liferay.osb.loop.model.LoopDivision loopDivision) {
		return getService().addLoopDivision(loopDivision);
	}

	/**
	* Creates a new loop division with the primary key. Does not add the loop division to the database.
	*
	* @param loopDivisionId the primary key for the new loop division
	* @return the new loop division
	*/
	public static com.liferay.osb.loop.model.LoopDivision createLoopDivision(
		long loopDivisionId) {
		return getService().createLoopDivision(loopDivisionId);
	}

	/**
	* Deletes the loop division with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivisionId the primary key of the loop division
	* @return the loop division that was removed
	* @throws PortalException if a loop division with the primary key could not be found
	*/
	public static com.liferay.osb.loop.model.LoopDivision deleteLoopDivision(
		long loopDivisionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLoopDivision(loopDivisionId);
	}

	/**
	* Deletes the loop division from the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivision the loop division
	* @return the loop division that was removed
	*/
	public static com.liferay.osb.loop.model.LoopDivision deleteLoopDivision(
		com.liferay.osb.loop.model.LoopDivision loopDivision) {
		return getService().deleteLoopDivision(loopDivision);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.loop.model.LoopDivision fetchLoopDivision(
		long loopDivisionId) {
		return getService().fetchLoopDivision(loopDivisionId);
	}

	public static com.liferay.osb.loop.model.LoopDivision fetchRootLoopDivision(
		long companyId) {
		return getService().fetchRootLoopDivision(companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the loop division with the primary key.
	*
	* @param loopDivisionId the primary key of the loop division
	* @return the loop division
	* @throws PortalException if a loop division with the primary key could not be found
	*/
	public static com.liferay.osb.loop.model.LoopDivision getLoopDivision(
		long loopDivisionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLoopDivision(loopDivisionId);
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
	public static java.util.List<com.liferay.osb.loop.model.LoopDivision> getLoopDivisions(
		int start, int end) {
		return getService().getLoopDivisions(start, end);
	}

	/**
	* Returns the number of loop divisions.
	*
	* @return the number of loop divisions
	*/
	public static int getLoopDivisionsCount() {
		return getService().getLoopDivisionsCount();
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

	public static String getRootLoopDivisionName(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRootLoopDivisionName(companyId);
	}

	/**
	* Updates the loop division in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopDivision the loop division
	* @return the loop division that was updated
	*/
	public static com.liferay.osb.loop.model.LoopDivision updateLoopDivision(
		com.liferay.osb.loop.model.LoopDivision loopDivision) {
		return getService().updateLoopDivision(loopDivision);
	}

	public static LoopDivisionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopDivisionLocalService, LoopDivisionLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopDivisionLocalService.class);

		ServiceTracker<LoopDivisionLocalService, LoopDivisionLocalService> serviceTracker =
			new ServiceTracker<LoopDivisionLocalService, LoopDivisionLocalService>(bundle.getBundleContext(),
				LoopDivisionLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}