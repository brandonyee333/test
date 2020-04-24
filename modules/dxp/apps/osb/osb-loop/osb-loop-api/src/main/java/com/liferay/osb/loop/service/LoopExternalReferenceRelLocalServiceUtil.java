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
 * Provides the local service utility for LoopExternalReferenceRel. This utility wraps
 * {@link com.liferay.osb.loop.service.impl.LoopExternalReferenceRelLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see LoopExternalReferenceRelLocalService
 * @see com.liferay.osb.loop.service.base.LoopExternalReferenceRelLocalServiceBaseImpl
 * @see com.liferay.osb.loop.service.impl.LoopExternalReferenceRelLocalServiceImpl
 * @generated
 */
@ProviderType
public class LoopExternalReferenceRelLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.loop.service.impl.LoopExternalReferenceRelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the loop external reference rel to the database. Also notifies the appropriate model listeners.
	*
	* @param loopExternalReferenceRel the loop external reference rel
	* @return the loop external reference rel that was added
	*/
	public static com.liferay.osb.loop.model.LoopExternalReferenceRel addLoopExternalReferenceRel(
		com.liferay.osb.loop.model.LoopExternalReferenceRel loopExternalReferenceRel) {
		return getService().addLoopExternalReferenceRel(loopExternalReferenceRel);
	}

	/**
	* Creates a new loop external reference rel with the primary key. Does not add the loop external reference rel to the database.
	*
	* @param loopExternalReferenceRelId the primary key for the new loop external reference rel
	* @return the new loop external reference rel
	*/
	public static com.liferay.osb.loop.model.LoopExternalReferenceRel createLoopExternalReferenceRel(
		long loopExternalReferenceRelId) {
		return getService()
				   .createLoopExternalReferenceRel(loopExternalReferenceRelId);
	}

	/**
	* Deletes the loop external reference rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopExternalReferenceRelId the primary key of the loop external reference rel
	* @return the loop external reference rel that was removed
	* @throws PortalException if a loop external reference rel with the primary key could not be found
	*/
	public static com.liferay.osb.loop.model.LoopExternalReferenceRel deleteLoopExternalReferenceRel(
		long loopExternalReferenceRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteLoopExternalReferenceRel(loopExternalReferenceRelId);
	}

	/**
	* Deletes the loop external reference rel from the database. Also notifies the appropriate model listeners.
	*
	* @param loopExternalReferenceRel the loop external reference rel
	* @return the loop external reference rel that was removed
	*/
	public static com.liferay.osb.loop.model.LoopExternalReferenceRel deleteLoopExternalReferenceRel(
		com.liferay.osb.loop.model.LoopExternalReferenceRel loopExternalReferenceRel) {
		return getService()
				   .deleteLoopExternalReferenceRel(loopExternalReferenceRel);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopExternalReferenceRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopExternalReferenceRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.loop.model.LoopExternalReferenceRel fetchLoopExternalReferenceRel(
		long loopExternalReferenceRelId) {
		return getService()
				   .fetchLoopExternalReferenceRel(loopExternalReferenceRelId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the loop external reference rel with the primary key.
	*
	* @param loopExternalReferenceRelId the primary key of the loop external reference rel
	* @return the loop external reference rel
	* @throws PortalException if a loop external reference rel with the primary key could not be found
	*/
	public static com.liferay.osb.loop.model.LoopExternalReferenceRel getLoopExternalReferenceRel(
		long loopExternalReferenceRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLoopExternalReferenceRel(loopExternalReferenceRelId);
	}

	/**
	* Returns a range of all the loop external reference rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopExternalReferenceRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop external reference rels
	* @param end the upper bound of the range of loop external reference rels (not inclusive)
	* @return the range of loop external reference rels
	*/
	public static java.util.List<com.liferay.osb.loop.model.LoopExternalReferenceRel> getLoopExternalReferenceRels(
		int start, int end) {
		return getService().getLoopExternalReferenceRels(start, end);
	}

	/**
	* Returns the number of loop external reference rels.
	*
	* @return the number of loop external reference rels
	*/
	public static int getLoopExternalReferenceRelsCount() {
		return getService().getLoopExternalReferenceRelsCount();
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
	* Updates the loop external reference rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopExternalReferenceRel the loop external reference rel
	* @return the loop external reference rel that was updated
	*/
	public static com.liferay.osb.loop.model.LoopExternalReferenceRel updateLoopExternalReferenceRel(
		com.liferay.osb.loop.model.LoopExternalReferenceRel loopExternalReferenceRel) {
		return getService()
				   .updateLoopExternalReferenceRel(loopExternalReferenceRel);
	}

	public static LoopExternalReferenceRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopExternalReferenceRelLocalService, LoopExternalReferenceRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopExternalReferenceRelLocalService.class);

		ServiceTracker<LoopExternalReferenceRelLocalService, LoopExternalReferenceRelLocalService> serviceTracker =
			new ServiceTracker<LoopExternalReferenceRelLocalService, LoopExternalReferenceRelLocalService>(bundle.getBundleContext(),
				LoopExternalReferenceRelLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}