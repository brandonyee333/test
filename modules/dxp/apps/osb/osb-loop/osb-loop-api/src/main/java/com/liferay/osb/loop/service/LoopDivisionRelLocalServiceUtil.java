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
 * Provides the local service utility for LoopDivisionRel. This utility wraps
 * {@link com.liferay.osb.loop.service.impl.LoopDivisionRelLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see LoopDivisionRelLocalService
 * @see com.liferay.osb.loop.service.base.LoopDivisionRelLocalServiceBaseImpl
 * @see com.liferay.osb.loop.service.impl.LoopDivisionRelLocalServiceImpl
 * @generated
 */
@ProviderType
public class LoopDivisionRelLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.loop.service.impl.LoopDivisionRelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the loop division rel to the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivisionRel the loop division rel
	* @return the loop division rel that was added
	*/
	public static com.liferay.osb.loop.model.LoopDivisionRel addLoopDivisionRel(
		com.liferay.osb.loop.model.LoopDivisionRel loopDivisionRel) {
		return getService().addLoopDivisionRel(loopDivisionRel);
	}

	/**
	* Creates a new loop division rel with the primary key. Does not add the loop division rel to the database.
	*
	* @param loopDivisionRelId the primary key for the new loop division rel
	* @return the new loop division rel
	*/
	public static com.liferay.osb.loop.model.LoopDivisionRel createLoopDivisionRel(
		long loopDivisionRelId) {
		return getService().createLoopDivisionRel(loopDivisionRelId);
	}

	/**
	* Deletes the loop division rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivisionRelId the primary key of the loop division rel
	* @return the loop division rel that was removed
	* @throws PortalException if a loop division rel with the primary key could not be found
	*/
	public static com.liferay.osb.loop.model.LoopDivisionRel deleteLoopDivisionRel(
		long loopDivisionRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLoopDivisionRel(loopDivisionRelId);
	}

	/**
	* Deletes the loop division rel from the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivisionRel the loop division rel
	* @return the loop division rel that was removed
	*/
	public static com.liferay.osb.loop.model.LoopDivisionRel deleteLoopDivisionRel(
		com.liferay.osb.loop.model.LoopDivisionRel loopDivisionRel) {
		return getService().deleteLoopDivisionRel(loopDivisionRel);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopDivisionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopDivisionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.loop.model.LoopDivisionRel fetchLoopDivisionRel(
		long loopDivisionRelId) {
		return getService().fetchLoopDivisionRel(loopDivisionRelId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the loop division rel with the primary key.
	*
	* @param loopDivisionRelId the primary key of the loop division rel
	* @return the loop division rel
	* @throws PortalException if a loop division rel with the primary key could not be found
	*/
	public static com.liferay.osb.loop.model.LoopDivisionRel getLoopDivisionRel(
		long loopDivisionRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLoopDivisionRel(loopDivisionRelId);
	}

	/**
	* Returns a range of all the loop division rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopDivisionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop division rels
	* @param end the upper bound of the range of loop division rels (not inclusive)
	* @return the range of loop division rels
	*/
	public static java.util.List<com.liferay.osb.loop.model.LoopDivisionRel> getLoopDivisionRels(
		int start, int end) {
		return getService().getLoopDivisionRels(start, end);
	}

	/**
	* Returns the number of loop division rels.
	*
	* @return the number of loop division rels
	*/
	public static int getLoopDivisionRelsCount() {
		return getService().getLoopDivisionRelsCount();
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

	/**
	* Updates the loop division rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopDivisionRel the loop division rel
	* @return the loop division rel that was updated
	*/
	public static com.liferay.osb.loop.model.LoopDivisionRel updateLoopDivisionRel(
		com.liferay.osb.loop.model.LoopDivisionRel loopDivisionRel) {
		return getService().updateLoopDivisionRel(loopDivisionRel);
	}

	public static LoopDivisionRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopDivisionRelLocalService, LoopDivisionRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopDivisionRelLocalService.class);

		ServiceTracker<LoopDivisionRelLocalService, LoopDivisionRelLocalService> serviceTracker =
			new ServiceTracker<LoopDivisionRelLocalService, LoopDivisionRelLocalService>(bundle.getBundleContext(),
				LoopDivisionRelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}