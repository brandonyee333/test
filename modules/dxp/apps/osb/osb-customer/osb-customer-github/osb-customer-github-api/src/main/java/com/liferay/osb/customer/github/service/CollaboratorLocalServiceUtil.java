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

package com.liferay.osb.customer.github.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Collaborator. This utility wraps
 * {@link com.liferay.osb.customer.github.service.impl.CollaboratorLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CollaboratorLocalService
 * @see com.liferay.osb.customer.github.service.base.CollaboratorLocalServiceBaseImpl
 * @see com.liferay.osb.customer.github.service.impl.CollaboratorLocalServiceImpl
 * @generated
 */
@ProviderType
public class CollaboratorLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.customer.github.service.impl.CollaboratorLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the collaborator to the database. Also notifies the appropriate model listeners.
	*
	* @param collaborator the collaborator
	* @return the collaborator that was added
	*/
	public static com.liferay.osb.customer.github.model.Collaborator addCollaborator(
		com.liferay.osb.customer.github.model.Collaborator collaborator) {
		return getService().addCollaborator(collaborator);
	}

	public static com.liferay.osb.customer.github.model.Collaborator addCollaborator(
		long userId, long accountEntryId, java.lang.String emailAddress,
		java.lang.String fullName, java.lang.String gitHubUserName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCollaborator(userId, accountEntryId, emailAddress,
			fullName, gitHubUserName);
	}

	/**
	* Creates a new collaborator with the primary key. Does not add the collaborator to the database.
	*
	* @param collaboratorId the primary key for the new collaborator
	* @return the new collaborator
	*/
	public static com.liferay.osb.customer.github.model.Collaborator createCollaborator(
		long collaboratorId) {
		return getService().createCollaborator(collaboratorId);
	}

	/**
	* Deletes the collaborator from the database. Also notifies the appropriate model listeners.
	*
	* @param collaborator the collaborator
	* @return the collaborator that was removed
	* @throws PortalException
	*/
	public static com.liferay.osb.customer.github.model.Collaborator deleteCollaborator(
		com.liferay.osb.customer.github.model.Collaborator collaborator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCollaborator(collaborator);
	}

	/**
	* Deletes the collaborator with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param collaboratorId the primary key of the collaborator
	* @return the collaborator that was removed
	* @throws PortalException if a collaborator with the primary key could not be found
	*/
	public static com.liferay.osb.customer.github.model.Collaborator deleteCollaborator(
		long collaboratorId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCollaborator(collaboratorId);
	}

	public static com.liferay.osb.customer.github.model.Collaborator fetchCollaborator(
		long collaboratorId) {
		return getService().fetchCollaborator(collaboratorId);
	}

	/**
	* Returns the collaborator with the primary key.
	*
	* @param collaboratorId the primary key of the collaborator
	* @return the collaborator
	* @throws PortalException if a collaborator with the primary key could not be found
	*/
	public static com.liferay.osb.customer.github.model.Collaborator getCollaborator(
		long collaboratorId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCollaborator(collaboratorId);
	}

	/**
	* Updates the collaborator in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param collaborator the collaborator
	* @return the collaborator that was updated
	*/
	public static com.liferay.osb.customer.github.model.Collaborator updateCollaborator(
		com.liferay.osb.customer.github.model.Collaborator collaborator) {
		return getService().updateCollaborator(collaborator);
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

	/**
	* Returns the number of collaborators.
	*
	* @return the number of collaborators
	*/
	public static int getCollaboratorsCount() {
		return getService().getCollaboratorsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.github.model.impl.CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.github.model.impl.CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the collaborators.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.github.model.impl.CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @return the range of collaborators
	*/
	public static java.util.List<com.liferay.osb.customer.github.model.Collaborator> getCollaborators(
		int start, int end) {
		return getService().getCollaborators(start, end);
	}

	public static java.util.List<com.liferay.osb.customer.github.model.Collaborator> getCollaborators(
		int status, int start, int end) {
		return getService().getCollaborators(status, start, end);
	}

	public static java.util.List<com.liferay.osb.customer.github.model.Collaborator> getCollaborators(
		java.lang.String gitHubUserName) {
		return getService().getCollaborators(gitHubUserName);
	}

	public static java.util.List<com.liferay.osb.customer.github.model.Collaborator> getCollaborators(
		java.lang.String gitHubUserName, int[] statuses) {
		return getService().getCollaborators(gitHubUserName, statuses);
	}

	public static java.util.List<com.liferay.osb.customer.github.model.Collaborator> getCollaborators(
		long accountEntryId) {
		return getService().getCollaborators(accountEntryId);
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

	public static CollaboratorLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CollaboratorLocalService, CollaboratorLocalService> _serviceTracker =
		ServiceTrackerFactory.open(CollaboratorLocalService.class);
}