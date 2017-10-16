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

package com.liferay.osb.customer.release.notes.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ReleaseNotesLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReleaseNotesLocalService
 * @generated
 */
@ProviderType
public class ReleaseNotesLocalServiceWrapper implements ReleaseNotesLocalService,
	ServiceWrapper<ReleaseNotesLocalService> {
	public ReleaseNotesLocalServiceWrapper(
		ReleaseNotesLocalService releaseNotesLocalService) {
		_releaseNotesLocalService = releaseNotesLocalService;
	}

	@Override
	public com.liferay.osb.customer.release.notes.model.ReleaseNotes addReleaseNotes(
		long userId, java.lang.String name, java.lang.String jiraIssueKeys)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _releaseNotesLocalService.addReleaseNotes(userId, name,
			jiraIssueKeys);
	}

	/**
	* Adds the release notes to the database. Also notifies the appropriate model listeners.
	*
	* @param releaseNotes the release notes
	* @return the release notes that was added
	*/
	@Override
	public com.liferay.osb.customer.release.notes.model.ReleaseNotes addReleaseNotes(
		com.liferay.osb.customer.release.notes.model.ReleaseNotes releaseNotes) {
		return _releaseNotesLocalService.addReleaseNotes(releaseNotes);
	}

	/**
	* Creates a new release notes with the primary key. Does not add the release notes to the database.
	*
	* @param releaseNotesId the primary key for the new release notes
	* @return the new release notes
	*/
	@Override
	public com.liferay.osb.customer.release.notes.model.ReleaseNotes createReleaseNotes(
		long releaseNotesId) {
		return _releaseNotesLocalService.createReleaseNotes(releaseNotesId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _releaseNotesLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the release notes with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param releaseNotesId the primary key of the release notes
	* @return the release notes that was removed
	* @throws PortalException if a release notes with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.release.notes.model.ReleaseNotes deleteReleaseNotes(
		long releaseNotesId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _releaseNotesLocalService.deleteReleaseNotes(releaseNotesId);
	}

	/**
	* Deletes the release notes from the database. Also notifies the appropriate model listeners.
	*
	* @param releaseNotes the release notes
	* @return the release notes that was removed
	*/
	@Override
	public com.liferay.osb.customer.release.notes.model.ReleaseNotes deleteReleaseNotes(
		com.liferay.osb.customer.release.notes.model.ReleaseNotes releaseNotes) {
		return _releaseNotesLocalService.deleteReleaseNotes(releaseNotes);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _releaseNotesLocalService.dynamicQuery();
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
		return _releaseNotesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _releaseNotesLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _releaseNotesLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _releaseNotesLocalService.dynamicQueryCount(dynamicQuery);
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
		return _releaseNotesLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.osb.customer.release.notes.model.ReleaseNotes fetchReleaseNotes(
		long releaseNotesId) {
		return _releaseNotesLocalService.fetchReleaseNotes(releaseNotesId);
	}

	@Override
	public com.liferay.osb.customer.release.notes.model.ReleaseNotes fetchReleaseNotes(
		java.lang.String name) {
		return _releaseNotesLocalService.fetchReleaseNotes(name);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _releaseNotesLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _releaseNotesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _releaseNotesLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _releaseNotesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the release notes with the primary key.
	*
	* @param releaseNotesId the primary key of the release notes
	* @return the release notes
	* @throws PortalException if a release notes with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.release.notes.model.ReleaseNotes getReleaseNotes(
		long releaseNotesId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _releaseNotesLocalService.getReleaseNotes(releaseNotesId);
	}

	@Override
	public com.liferay.osb.customer.release.notes.model.ReleaseNotes getReleaseNotesByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _releaseNotesLocalService.getReleaseNotesByUuid(uuid);
	}

	/**
	* Returns a range of all the release noteses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of release noteses
	* @param end the upper bound of the range of release noteses (not inclusive)
	* @return the range of release noteses
	*/
	@Override
	public java.util.List<com.liferay.osb.customer.release.notes.model.ReleaseNotes> getReleaseNoteses(
		int start, int end) {
		return _releaseNotesLocalService.getReleaseNoteses(start, end);
	}

	/**
	* Returns the number of release noteses.
	*
	* @return the number of release noteses
	*/
	@Override
	public int getReleaseNotesesCount() {
		return _releaseNotesLocalService.getReleaseNotesesCount();
	}

	@Override
	public java.util.List<com.liferay.osb.customer.release.notes.model.ReleaseNotes> search(
		java.lang.String name, int start, int end) {
		return _releaseNotesLocalService.search(name, start, end);
	}

	@Override
	public int searchCount(java.lang.String name) {
		return _releaseNotesLocalService.searchCount(name);
	}

	@Override
	public com.liferay.osb.customer.release.notes.model.ReleaseNotes updateReleaseNotes(
		long releaseNotesId, java.lang.String name,
		java.lang.String jiraIssueKeys)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _releaseNotesLocalService.updateReleaseNotes(releaseNotesId,
			name, jiraIssueKeys);
	}

	/**
	* Updates the release notes in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param releaseNotes the release notes
	* @return the release notes that was updated
	*/
	@Override
	public com.liferay.osb.customer.release.notes.model.ReleaseNotes updateReleaseNotes(
		com.liferay.osb.customer.release.notes.model.ReleaseNotes releaseNotes) {
		return _releaseNotesLocalService.updateReleaseNotes(releaseNotes);
	}

	@Override
	public ReleaseNotesLocalService getWrappedService() {
		return _releaseNotesLocalService;
	}

	@Override
	public void setWrappedService(
		ReleaseNotesLocalService releaseNotesLocalService) {
		_releaseNotesLocalService = releaseNotesLocalService;
	}

	private ReleaseNotesLocalService _releaseNotesLocalService;
}