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

package com.liferay.osb.customer.release.notes.jira.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for JIRAProjectVersion. This utility wraps
 * {@link com.liferay.osb.customer.release.notes.jira.service.impl.JIRAProjectVersionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProjectVersionLocalService
 * @see com.liferay.osb.customer.release.notes.jira.service.base.JIRAProjectVersionLocalServiceBaseImpl
 * @see com.liferay.osb.customer.release.notes.jira.service.impl.JIRAProjectVersionLocalServiceImpl
 * @generated
 */
@ProviderType
public class JIRAProjectVersionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.customer.release.notes.jira.service.impl.JIRAProjectVersionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the jira project version to the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProjectVersion the jira project version
	* @return the jira project version that was added
	*/
	public static com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion addJIRAProjectVersion(
		com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion jiraProjectVersion) {
		return getService().addJIRAProjectVersion(jiraProjectVersion);
	}

	/**
	* Creates a new jira project version with the primary key. Does not add the jira project version to the database.
	*
	* @param jiraProjectVersionId the primary key for the new jira project version
	* @return the new jira project version
	*/
	public static com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion createJIRAProjectVersion(
		long jiraProjectVersionId) {
		return getService().createJIRAProjectVersion(jiraProjectVersionId);
	}

	/**
	* Deletes the jira project version from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProjectVersion the jira project version
	* @return the jira project version that was removed
	*/
	public static com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion deleteJIRAProjectVersion(
		com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion jiraProjectVersion) {
		return getService().deleteJIRAProjectVersion(jiraProjectVersion);
	}

	/**
	* Deletes the jira project version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProjectVersionId the primary key of the jira project version
	* @return the jira project version that was removed
	* @throws PortalException if a jira project version with the primary key could not be found
	*/
	public static com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion deleteJIRAProjectVersion(
		long jiraProjectVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteJIRAProjectVersion(jiraProjectVersionId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion fetchJIRAProjectVersion(
		long jiraProjectVersionId) {
		return getService().fetchJIRAProjectVersion(jiraProjectVersionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	public static java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion> getJIRAProjectJIRAProjectVersion(
		long jiraProjectId, java.lang.String name) {
		return getService().getJIRAProjectJIRAProjectVersion(jiraProjectId, name);
	}

	/**
	* Returns the jira project version with the primary key.
	*
	* @param jiraProjectVersionId the primary key of the jira project version
	* @return the jira project version
	* @throws PortalException if a jira project version with the primary key could not be found
	*/
	public static com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion getJIRAProjectVersion(
		long jiraProjectVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getJIRAProjectVersion(jiraProjectVersionId);
	}

	/**
	* Returns a range of all the jira project versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira project versions
	* @param end the upper bound of the range of jira project versions (not inclusive)
	* @return the range of jira project versions
	*/
	public static java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion> getJIRAProjectVersions(
		int start, int end) {
		return getService().getJIRAProjectVersions(start, end);
	}

	/**
	* Returns the number of jira project versions.
	*
	* @return the number of jira project versions
	*/
	public static int getJIRAProjectVersionsCount() {
		return getService().getJIRAProjectVersionsCount();
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
	* Updates the jira project version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraProjectVersion the jira project version
	* @return the jira project version that was updated
	*/
	public static com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion updateJIRAProjectVersion(
		com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion jiraProjectVersion) {
		return getService().updateJIRAProjectVersion(jiraProjectVersion);
	}

	public static JIRAProjectVersionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<JIRAProjectVersionLocalService, JIRAProjectVersionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(JIRAProjectVersionLocalService.class);
}