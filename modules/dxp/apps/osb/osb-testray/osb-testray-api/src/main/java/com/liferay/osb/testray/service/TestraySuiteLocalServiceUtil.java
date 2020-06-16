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

package com.liferay.osb.testray.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TestraySuite. This utility wraps
 * {@link com.liferay.osb.testray.service.impl.TestraySuiteLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestraySuiteLocalService
 * @see com.liferay.osb.testray.service.base.TestraySuiteLocalServiceBaseImpl
 * @see com.liferay.osb.testray.service.impl.TestraySuiteLocalServiceImpl
 * @generated
 */
@ProviderType
public class TestraySuiteLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.testray.service.impl.TestraySuiteLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasTestrayCaseTestraySuite(long testrayCaseId,
		long testraySuiteId) {
		return getService()
				   .hasTestrayCaseTestraySuite(testrayCaseId, testraySuiteId);
	}

	public static boolean hasTestrayCaseTestraySuites(long testrayCaseId) {
		return getService().hasTestrayCaseTestraySuites(testrayCaseId);
	}

	/**
	* Adds the testray suite to the database. Also notifies the appropriate model listeners.
	*
	* @param testraySuite the testray suite
	* @return the testray suite that was added
	*/
	public static com.liferay.osb.testray.model.TestraySuite addTestraySuite(
		com.liferay.osb.testray.model.TestraySuite testraySuite) {
		return getService().addTestraySuite(testraySuite);
	}

	/**
	* Creates a new testray suite with the primary key. Does not add the testray suite to the database.
	*
	* @param testraySuiteId the primary key for the new testray suite
	* @return the new testray suite
	*/
	public static com.liferay.osb.testray.model.TestraySuite createTestraySuite(
		long testraySuiteId) {
		return getService().createTestraySuite(testraySuiteId);
	}

	/**
	* Deletes the testray suite from the database. Also notifies the appropriate model listeners.
	*
	* @param testraySuite the testray suite
	* @return the testray suite that was removed
	*/
	public static com.liferay.osb.testray.model.TestraySuite deleteTestraySuite(
		com.liferay.osb.testray.model.TestraySuite testraySuite) {
		return getService().deleteTestraySuite(testraySuite);
	}

	/**
	* Deletes the testray suite with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testraySuiteId the primary key of the testray suite
	* @return the testray suite that was removed
	* @throws PortalException if a testray suite with the primary key could not be found
	*/
	public static com.liferay.osb.testray.model.TestraySuite deleteTestraySuite(
		long testraySuiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTestraySuite(testraySuiteId);
	}

	public static com.liferay.osb.testray.model.TestraySuite fetchTestraySuite(
		long testraySuiteId) {
		return getService().fetchTestraySuite(testraySuiteId);
	}

	/**
	* Returns the testray suite with the primary key.
	*
	* @param testraySuiteId the primary key of the testray suite
	* @return the testray suite
	* @throws PortalException if a testray suite with the primary key could not be found
	*/
	public static com.liferay.osb.testray.model.TestraySuite getTestraySuite(
		long testraySuiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTestraySuite(testraySuiteId);
	}

	/**
	* Updates the testray suite in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testraySuite the testray suite
	* @return the testray suite that was updated
	*/
	public static com.liferay.osb.testray.model.TestraySuite updateTestraySuite(
		com.liferay.osb.testray.model.TestraySuite testraySuite) {
		return getService().updateTestraySuite(testraySuite);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static int getTestrayCaseTestraySuitesCount(long testrayCaseId) {
		return getService().getTestrayCaseTestraySuitesCount(testrayCaseId);
	}

	/**
	* Returns the number of testray suites.
	*
	* @return the number of testray suites
	*/
	public static int getTestraySuitesCount() {
		return getService().getTestraySuitesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestraySuiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestraySuiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.osb.testray.model.TestraySuite> getTestrayCaseTestraySuites(
		long testrayCaseId) {
		return getService().getTestrayCaseTestraySuites(testrayCaseId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestraySuite> getTestrayCaseTestraySuites(
		long testrayCaseId, int start, int end) {
		return getService()
				   .getTestrayCaseTestraySuites(testrayCaseId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestraySuite> getTestrayCaseTestraySuites(
		long testrayCaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.testray.model.TestraySuite> orderByComparator) {
		return getService()
				   .getTestrayCaseTestraySuites(testrayCaseId, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the testray suites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestraySuiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray suites
	* @param end the upper bound of the range of testray suites (not inclusive)
	* @return the range of testray suites
	*/
	public static java.util.List<com.liferay.osb.testray.model.TestraySuite> getTestraySuites(
		int start, int end) {
		return getService().getTestraySuites(start, end);
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

	/**
	* Returns the testrayCaseIds of the testray cases associated with the testray suite.
	*
	* @param testraySuiteId the testraySuiteId of the testray suite
	* @return long[] the testrayCaseIds of testray cases associated with the testray suite
	*/
	public static long[] getTestrayCasePrimaryKeys(long testraySuiteId) {
		return getService().getTestrayCasePrimaryKeys(testraySuiteId);
	}

	public static void addTestrayCaseTestraySuite(long testrayCaseId,
		com.liferay.osb.testray.model.TestraySuite testraySuite) {
		getService().addTestrayCaseTestraySuite(testrayCaseId, testraySuite);
	}

	public static void addTestrayCaseTestraySuite(long testrayCaseId,
		long testraySuiteId) {
		getService().addTestrayCaseTestraySuite(testrayCaseId, testraySuiteId);
	}

	public static void addTestrayCaseTestraySuites(long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestraySuite> testraySuites) {
		getService().addTestrayCaseTestraySuites(testrayCaseId, testraySuites);
	}

	public static void addTestrayCaseTestraySuites(long testrayCaseId,
		long[] testraySuiteIds) {
		getService().addTestrayCaseTestraySuites(testrayCaseId, testraySuiteIds);
	}

	public static void clearTestrayCaseTestraySuites(long testrayCaseId) {
		getService().clearTestrayCaseTestraySuites(testrayCaseId);
	}

	public static void deleteTestrayCaseTestraySuite(long testrayCaseId,
		com.liferay.osb.testray.model.TestraySuite testraySuite) {
		getService().deleteTestrayCaseTestraySuite(testrayCaseId, testraySuite);
	}

	public static void deleteTestrayCaseTestraySuite(long testrayCaseId,
		long testraySuiteId) {
		getService().deleteTestrayCaseTestraySuite(testrayCaseId, testraySuiteId);
	}

	public static void deleteTestrayCaseTestraySuites(long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestraySuite> testraySuites) {
		getService().deleteTestrayCaseTestraySuites(testrayCaseId, testraySuites);
	}

	public static void deleteTestrayCaseTestraySuites(long testrayCaseId,
		long[] testraySuiteIds) {
		getService()
			.deleteTestrayCaseTestraySuites(testrayCaseId, testraySuiteIds);
	}

	public static void setTestrayCaseTestraySuites(long testrayCaseId,
		long[] testraySuiteIds) {
		getService().setTestrayCaseTestraySuites(testrayCaseId, testraySuiteIds);
	}

	public static TestraySuiteLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestraySuiteLocalService, TestraySuiteLocalService> _serviceTracker =
		ServiceTrackerFactory.open(TestraySuiteLocalService.class);
}