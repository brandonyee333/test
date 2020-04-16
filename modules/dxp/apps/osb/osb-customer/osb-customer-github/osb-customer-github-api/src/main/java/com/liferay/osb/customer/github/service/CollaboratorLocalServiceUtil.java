/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.github.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Collaborator. This utility wraps
 * <code>com.liferay.osb.customer.github.service.impl.CollaboratorLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CollaboratorLocalService
 * @generated
 */
public class CollaboratorLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.github.service.impl.CollaboratorLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the collaborator to the database. Also notifies the appropriate model listeners.
	 *
	 * @param collaborator the collaborator
	 * @return the collaborator that was added
	 */
	public static com.liferay.osb.customer.github.model.Collaborator
		addCollaborator(
			com.liferay.osb.customer.github.model.Collaborator collaborator) {

		return getService().addCollaborator(collaborator);
	}

	public static com.liferay.osb.customer.github.model.Collaborator
			addCollaborator(
				long userId, long accountEntryId, String emailAddress,
				String fullName, String gitHubUserName)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCollaborator(
			userId, accountEntryId, emailAddress, fullName, gitHubUserName);
	}

	/**
	 * Creates a new collaborator with the primary key. Does not add the collaborator to the database.
	 *
	 * @param collaboratorId the primary key for the new collaborator
	 * @return the new collaborator
	 */
	public static com.liferay.osb.customer.github.model.Collaborator
		createCollaborator(long collaboratorId) {

		return getService().createCollaborator(collaboratorId);
	}

	/**
	 * Deletes the collaborator from the database. Also notifies the appropriate model listeners.
	 *
	 * @param collaborator the collaborator
	 * @return the collaborator that was removed
	 * @throws PortalException
	 */
	public static com.liferay.osb.customer.github.model.Collaborator
			deleteCollaborator(
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
	public static com.liferay.osb.customer.github.model.Collaborator
			deleteCollaborator(long collaboratorId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCollaborator(collaboratorId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.github.model.impl.CollaboratorModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.github.model.impl.CollaboratorModelImpl</code>.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

	public static com.liferay.osb.customer.github.model.Collaborator
		fetchCollaborator(long collaboratorId) {

		return getService().fetchCollaborator(collaboratorId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the collaborator with the primary key.
	 *
	 * @param collaboratorId the primary key of the collaborator
	 * @return the collaborator
	 * @throws PortalException if a collaborator with the primary key could not be found
	 */
	public static com.liferay.osb.customer.github.model.Collaborator
			getCollaborator(long collaboratorId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCollaborator(collaboratorId);
	}

	/**
	 * Returns a range of all the collaborators.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.github.model.impl.CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @return the range of collaborators
	 */
	public static java.util.List
		<com.liferay.osb.customer.github.model.Collaborator> getCollaborators(
			int start, int end) {

		return getService().getCollaborators(start, end);
	}

	public static java.util.List
		<com.liferay.osb.customer.github.model.Collaborator> getCollaborators(
			int status, int start, int end) {

		return getService().getCollaborators(status, start, end);
	}

	public static java.util.List
		<com.liferay.osb.customer.github.model.Collaborator> getCollaborators(
			long accountEntryId) {

		return getService().getCollaborators(accountEntryId);
	}

	/**
	 * Returns the number of collaborators.
	 *
	 * @return the number of collaborators
	 */
	public static int getCollaboratorsCount() {
		return getService().getCollaboratorsCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the collaborator in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param collaborator the collaborator
	 * @return the collaborator that was updated
	 */
	public static com.liferay.osb.customer.github.model.Collaborator
		updateCollaborator(
			com.liferay.osb.customer.github.model.Collaborator collaborator) {

		return getService().updateCollaborator(collaborator);
	}

	public static CollaboratorLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CollaboratorLocalService, CollaboratorLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CollaboratorLocalService.class);

		ServiceTracker<CollaboratorLocalService, CollaboratorLocalService>
			serviceTracker =
				new ServiceTracker
					<CollaboratorLocalService, CollaboratorLocalService>(
						bundle.getBundleContext(),
						CollaboratorLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}