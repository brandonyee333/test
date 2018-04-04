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

package com.liferay.akismet.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Akismet. This utility wraps
 * {@link com.liferay.akismet.service.impl.AkismetLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Jamie Sammons
 * @see AkismetLocalService
 * @see com.liferay.akismet.service.base.AkismetLocalServiceBaseImpl
 * @see com.liferay.akismet.service.impl.AkismetLocalServiceImpl
 * @generated
 */
@ProviderType
public class AkismetLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.akismet.service.impl.AkismetLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the akismet to the database. Also notifies the appropriate model listeners.
	*
	* @param akismet the akismet
	* @return the akismet that was added
	*/
	public static com.liferay.akismet.model.Akismet addAkismet(
		com.liferay.akismet.model.Akismet akismet) {
		return getService().addAkismet(akismet);
	}

	/**
	* Creates a new akismet with the primary key. Does not add the akismet to the database.
	*
	* @param akismetId the primary key for the new akismet
	* @return the new akismet
	*/
	public static com.liferay.akismet.model.Akismet createAkismet(
		long akismetId) {
		return getService().createAkismet(akismetId);
	}

	/**
	* Deletes the akismet from the database. Also notifies the appropriate model listeners.
	*
	* @param akismet the akismet
	* @return the akismet that was removed
	*/
	public static com.liferay.akismet.model.Akismet deleteAkismet(
		com.liferay.akismet.model.Akismet akismet) {
		return getService().deleteAkismet(akismet);
	}

	/**
	* Deletes the akismet with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param akismetId the primary key of the akismet
	* @return the akismet that was removed
	* @throws PortalException if a akismet with the primary key could not be found
	*/
	public static com.liferay.akismet.model.Akismet deleteAkismet(
		long akismetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAkismet(akismetId);
	}

	public static void deleteAkismetData(java.util.Date modifiedDate) {
		getService().deleteAkismetData(modifiedDate);
	}

	public static void deleteAkismetData(java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteAkismetData(className, classPK);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.akismet.model.Akismet fetchAkismet(long akismetId) {
		return getService().fetchAkismet(akismetId);
	}

	public static com.liferay.akismet.model.Akismet fetchAkismetData(
		java.lang.String className, long classPK) {
		return getService().fetchAkismetData(className, classPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the akismet with the primary key.
	*
	* @param akismetId the primary key of the akismet
	* @return the akismet
	* @throws PortalException if a akismet with the primary key could not be found
	*/
	public static com.liferay.akismet.model.Akismet getAkismet(long akismetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAkismet(akismetId);
	}

	/**
	* Returns a range of all the akismets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismets
	* @param end the upper bound of the range of akismets (not inclusive)
	* @return the range of akismets
	*/
	public static java.util.List<com.liferay.akismet.model.Akismet> getAkismets(
		int start, int end) {
		return getService().getAkismets(start, end);
	}

	/**
	* Returns the number of akismets.
	*
	* @return the number of akismets
	*/
	public static int getAkismetsCount() {
		return getService().getAkismetsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the akismet in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param akismet the akismet
	* @return the akismet that was updated
	*/
	public static com.liferay.akismet.model.Akismet updateAkismet(
		com.liferay.akismet.model.Akismet akismet) {
		return getService().updateAkismet(akismet);
	}

	public static com.liferay.akismet.model.Akismet updateAkismetData(
		java.lang.String className, long classPK, java.lang.String type,
		java.lang.String permalink, java.lang.String referrer,
		java.lang.String userAgent, java.lang.String userIP,
		java.lang.String userURL) {
		return getService()
				   .updateAkismetData(className, classPK, type, permalink,
			referrer, userAgent, userIP, userURL);
	}

	public static AkismetLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AkismetLocalService, AkismetLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AkismetLocalService.class);

		ServiceTracker<AkismetLocalService, AkismetLocalService> serviceTracker = new ServiceTracker<AkismetLocalService, AkismetLocalService>(bundle.getBundleContext(),
				AkismetLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}