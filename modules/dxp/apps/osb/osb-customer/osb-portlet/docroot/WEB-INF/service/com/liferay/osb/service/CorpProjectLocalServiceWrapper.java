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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CorpProjectLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectLocalService
 * @generated
 */
@ProviderType
public class CorpProjectLocalServiceWrapper implements CorpProjectLocalService,
	ServiceWrapper<CorpProjectLocalService> {
	public CorpProjectLocalServiceWrapper(
		CorpProjectLocalService corpProjectLocalService) {
		_corpProjectLocalService = corpProjectLocalService;
	}

	/**
	* Adds the corp project to the database. Also notifies the appropriate model listeners.
	*
	* @param corpProject the corp project
	* @return the corp project that was added
	*/
	@Override
	public com.liferay.osb.model.CorpProject addCorpProject(
		com.liferay.osb.model.CorpProject corpProject) {
		return _corpProjectLocalService.addCorpProject(corpProject);
	}

	@Override
	public com.liferay.osb.model.CorpProject addCorpProject(
		com.liferay.portal.kernel.json.JSONObject jsonObject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectLocalService.addCorpProject(jsonObject);
	}

	/**
	* Creates a new corp project with the primary key. Does not add the corp project to the database.
	*
	* @param corpProjectId the primary key for the new corp project
	* @return the new corp project
	*/
	@Override
	public com.liferay.osb.model.CorpProject createCorpProject(
		long corpProjectId) {
		return _corpProjectLocalService.createCorpProject(corpProjectId);
	}

	/**
	* Deletes the corp project from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProject the corp project
	* @return the corp project that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.osb.model.CorpProject deleteCorpProject(
		com.liferay.osb.model.CorpProject corpProject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectLocalService.deleteCorpProject(corpProject);
	}

	@Override
	public com.liferay.osb.model.CorpProject deleteCorpProject(
		com.liferay.portal.kernel.json.JSONObject jsonObject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectLocalService.deleteCorpProject(jsonObject);
	}

	/**
	* Deletes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project that was removed
	* @throws PortalException if a corp project with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.CorpProject deleteCorpProject(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectLocalService.deleteCorpProject(corpProjectId);
	}

	@Override
	public com.liferay.osb.model.CorpProject fetchCorpProject(
		java.lang.String dossieraProjectKey) {
		return _corpProjectLocalService.fetchCorpProject(dossieraProjectKey);
	}

	@Override
	public com.liferay.osb.model.CorpProject fetchCorpProject(
		long corpProjectId) {
		return _corpProjectLocalService.fetchCorpProject(corpProjectId);
	}

	/**
	* Returns the corp project with the primary key.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project
	* @throws PortalException if a corp project with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.CorpProject getCorpProject(long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectLocalService.getCorpProject(corpProjectId);
	}

	@Override
	public com.liferay.osb.model.CorpProject getCorpProjectByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectLocalService.getCorpProjectByUuid(uuid);
	}

	/**
	* Updates the corp project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpProject the corp project
	* @return the corp project that was updated
	*/
	@Override
	public com.liferay.osb.model.CorpProject updateCorpProject(
		com.liferay.osb.model.CorpProject corpProject) {
		return _corpProjectLocalService.updateCorpProject(corpProject);
	}

	@Override
	public com.liferay.osb.model.CorpProject updateCorpProject(
		com.liferay.portal.kernel.json.JSONObject jsonObject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectLocalService.updateCorpProject(jsonObject);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _corpProjectLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _corpProjectLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _corpProjectLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of corp projects.
	*
	* @return the number of corp projects
	*/
	@Override
	public int getCorpProjectsCount() {
		return _corpProjectLocalService.getCorpProjectsCount();
	}

	@Override
	public int getCorpProjectsCount(java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectLocalService.getCorpProjectsCount(name);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _corpProjectLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _corpProjectLocalService.getOSGiServiceIdentifier();
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
		return _corpProjectLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _corpProjectLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _corpProjectLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the corp projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @return the range of corp projects
	*/
	@Override
	public java.util.List<com.liferay.osb.model.CorpProject> getCorpProjects(
		int start, int end) {
		return _corpProjectLocalService.getCorpProjects(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.CorpProject> getCorpProjects(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectLocalService.getCorpProjects(name, start, end, obc);
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
		return _corpProjectLocalService.dynamicQueryCount(dynamicQuery);
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
		return _corpProjectLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public CorpProjectLocalService getWrappedService() {
		return _corpProjectLocalService;
	}

	@Override
	public void setWrappedService(
		CorpProjectLocalService corpProjectLocalService) {
		_corpProjectLocalService = corpProjectLocalService;
	}

	private CorpProjectLocalService _corpProjectLocalService;
}