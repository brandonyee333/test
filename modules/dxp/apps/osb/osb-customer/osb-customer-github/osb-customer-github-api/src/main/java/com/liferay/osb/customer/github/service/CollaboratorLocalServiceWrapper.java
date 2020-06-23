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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CollaboratorLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CollaboratorLocalService
 * @generated
 */
public class CollaboratorLocalServiceWrapper
	implements CollaboratorLocalService,
			   ServiceWrapper<CollaboratorLocalService> {

	public CollaboratorLocalServiceWrapper(
		CollaboratorLocalService collaboratorLocalService) {

		_collaboratorLocalService = collaboratorLocalService;
	}

	/**
	 * Adds the collaborator to the database. Also notifies the appropriate model listeners.
	 *
	 * @param collaborator the collaborator
	 * @return the collaborator that was added
	 */
	@Override
	public com.liferay.osb.customer.github.model.Collaborator addCollaborator(
		com.liferay.osb.customer.github.model.Collaborator collaborator) {

		return _collaboratorLocalService.addCollaborator(collaborator);
	}

	@Override
	public com.liferay.osb.customer.github.model.Collaborator addCollaborator(
			long userId, long accountEntryId, String emailAddress,
			String fullName, String gitHubUserName)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _collaboratorLocalService.addCollaborator(
			userId, accountEntryId, emailAddress, fullName, gitHubUserName);
	}

	/**
	 * Creates a new collaborator with the primary key. Does not add the collaborator to the database.
	 *
	 * @param collaboratorId the primary key for the new collaborator
	 * @return the new collaborator
	 */
	@Override
	public com.liferay.osb.customer.github.model.Collaborator
		createCollaborator(long collaboratorId) {

		return _collaboratorLocalService.createCollaborator(collaboratorId);
	}

	/**
	 * Deletes the collaborator from the database. Also notifies the appropriate model listeners.
	 *
	 * @param collaborator the collaborator
	 * @return the collaborator that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.osb.customer.github.model.Collaborator
			deleteCollaborator(
				com.liferay.osb.customer.github.model.Collaborator collaborator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _collaboratorLocalService.deleteCollaborator(collaborator);
	}

	/**
	 * Deletes the collaborator with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param collaboratorId the primary key of the collaborator
	 * @return the collaborator that was removed
	 * @throws PortalException if a collaborator with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.github.model.Collaborator
			deleteCollaborator(long collaboratorId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _collaboratorLocalService.deleteCollaborator(collaboratorId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _collaboratorLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _collaboratorLocalService.dynamicQuery();
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

		return _collaboratorLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _collaboratorLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _collaboratorLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _collaboratorLocalService.dynamicQueryCount(dynamicQuery);
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

		return _collaboratorLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.github.model.Collaborator fetchCollaborator(
		long collaboratorId) {

		return _collaboratorLocalService.fetchCollaborator(collaboratorId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _collaboratorLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the collaborator with the primary key.
	 *
	 * @param collaboratorId the primary key of the collaborator
	 * @return the collaborator
	 * @throws PortalException if a collaborator with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.github.model.Collaborator getCollaborator(
			long collaboratorId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _collaboratorLocalService.getCollaborator(collaboratorId);
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
	@Override
	public java.util.List<com.liferay.osb.customer.github.model.Collaborator>
		getCollaborators(int start, int end) {

		return _collaboratorLocalService.getCollaborators(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.github.model.Collaborator>
		getCollaborators(int status, int start, int end) {

		return _collaboratorLocalService.getCollaborators(status, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.github.model.Collaborator>
		getCollaborators(long accountEntryId) {

		return _collaboratorLocalService.getCollaborators(accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.github.model.Collaborator>
		getCollaborators(String gitHubUserName) {

		return _collaboratorLocalService.getCollaborators(gitHubUserName);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.github.model.Collaborator>
		getCollaborators(String gitHubUserName, int[] statuses) {

		return _collaboratorLocalService.getCollaborators(
			gitHubUserName, statuses);
	}

	/**
	 * Returns the number of collaborators.
	 *
	 * @return the number of collaborators
	 */
	@Override
	public int getCollaboratorsCount() {
		return _collaboratorLocalService.getCollaboratorsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _collaboratorLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _collaboratorLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _collaboratorLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the collaborator in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param collaborator the collaborator
	 * @return the collaborator that was updated
	 */
	@Override
	public com.liferay.osb.customer.github.model.Collaborator
		updateCollaborator(
			com.liferay.osb.customer.github.model.Collaborator collaborator) {

		return _collaboratorLocalService.updateCollaborator(collaborator);
	}

	@Override
	public CollaboratorLocalService getWrappedService() {
		return _collaboratorLocalService;
	}

	@Override
	public void setWrappedService(
		CollaboratorLocalService collaboratorLocalService) {

		_collaboratorLocalService = collaboratorLocalService;
	}

	private CollaboratorLocalService _collaboratorLocalService;

}