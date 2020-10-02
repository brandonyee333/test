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

package com.liferay.osb.customer.release.tool.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link JIRAComponentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAComponentLocalService
 * @generated
 */
@ProviderType
public class JIRAComponentLocalServiceWrapper
	implements JIRAComponentLocalService,
		ServiceWrapper<JIRAComponentLocalService> {
	public JIRAComponentLocalServiceWrapper(
		JIRAComponentLocalService jiraComponentLocalService) {
		_jiraComponentLocalService = jiraComponentLocalService;
	}

	/**
	* Adds the jira component to the database. Also notifies the appropriate model listeners.
	*
	* @param jiraComponent the jira component
	* @return the jira component that was added
	*/
	@Override
	public com.liferay.osb.customer.release.tool.model.JIRAComponent addJIRAComponent(
		com.liferay.osb.customer.release.tool.model.JIRAComponent jiraComponent) {
		return _jiraComponentLocalService.addJIRAComponent(jiraComponent);
	}

	@Override
	public com.liferay.osb.customer.release.tool.model.JIRAComponent addJIRAComponent(
		long remoteId, java.lang.String remoteProject, java.lang.String name,
		boolean visible)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraComponentLocalService.addJIRAComponent(remoteId,
			remoteProject, name, visible);
	}

	/**
	* Creates a new jira component with the primary key. Does not add the jira component to the database.
	*
	* @param jiraComponentId the primary key for the new jira component
	* @return the new jira component
	*/
	@Override
	public com.liferay.osb.customer.release.tool.model.JIRAComponent createJIRAComponent(
		long jiraComponentId) {
		return _jiraComponentLocalService.createJIRAComponent(jiraComponentId);
	}

	/**
	* Deletes the jira component from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraComponent the jira component
	* @return the jira component that was removed
	*/
	@Override
	public com.liferay.osb.customer.release.tool.model.JIRAComponent deleteJIRAComponent(
		com.liferay.osb.customer.release.tool.model.JIRAComponent jiraComponent) {
		return _jiraComponentLocalService.deleteJIRAComponent(jiraComponent);
	}

	/**
	* Deletes the jira component with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraComponentId the primary key of the jira component
	* @return the jira component that was removed
	* @throws PortalException if a jira component with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.release.tool.model.JIRAComponent deleteJIRAComponent(
		long jiraComponentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraComponentLocalService.deleteJIRAComponent(jiraComponentId);
	}

	@Override
	public com.liferay.osb.customer.release.tool.model.JIRAComponent fetchJIRAComponent(
		long jiraComponentId) {
		return _jiraComponentLocalService.fetchJIRAComponent(jiraComponentId);
	}

	/**
	* Returns the jira component with the primary key.
	*
	* @param jiraComponentId the primary key of the jira component
	* @return the jira component
	* @throws PortalException if a jira component with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.release.tool.model.JIRAComponent getJIRAComponent(
		long jiraComponentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraComponentLocalService.getJIRAComponent(jiraComponentId);
	}

	/**
	* Updates the jira component in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraComponent the jira component
	* @return the jira component that was updated
	*/
	@Override
	public com.liferay.osb.customer.release.tool.model.JIRAComponent updateJIRAComponent(
		com.liferay.osb.customer.release.tool.model.JIRAComponent jiraComponent) {
		return _jiraComponentLocalService.updateJIRAComponent(jiraComponent);
	}

	@Override
	public com.liferay.osb.customer.release.tool.model.JIRAComponent updateJIRAComponent(
		long jiraComponentId, boolean visible)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraComponentLocalService.updateJIRAComponent(jiraComponentId,
			visible);
	}

	@Override
	public com.liferay.osb.customer.release.tool.model.JIRAComponent updateJIRAComponent(
		long remoteId, java.lang.String remoteProject, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraComponentLocalService.updateJIRAComponent(remoteId,
			remoteProject, name);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _jiraComponentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _jiraComponentLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _jiraComponentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraComponentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraComponentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of jira components.
	*
	* @return the number of jira components
	*/
	@Override
	public int getJIRAComponentsCount() {
		return _jiraComponentLocalService.getJIRAComponentsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _jiraComponentLocalService.getOSGiServiceIdentifier();
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
		return _jiraComponentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.tool.model.impl.JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _jiraComponentLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.tool.model.impl.JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _jiraComponentLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the jira components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.tool.model.impl.JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira components
	* @param end the upper bound of the range of jira components (not inclusive)
	* @return the range of jira components
	*/
	@Override
	public java.util.List<com.liferay.osb.customer.release.tool.model.JIRAComponent> getJIRAComponents(
		int start, int end) {
		return _jiraComponentLocalService.getJIRAComponents(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.release.tool.model.JIRAComponent> getJIRAComponents(
		java.lang.String remoteProject) {
		return _jiraComponentLocalService.getJIRAComponents(remoteProject);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.release.tool.model.JIRAComponent> getJIRAComponents(
		java.lang.String remoteProject, boolean visible) {
		return _jiraComponentLocalService.getJIRAComponents(remoteProject,
			visible);
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
		return _jiraComponentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _jiraComponentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public JIRAComponentLocalService getWrappedService() {
		return _jiraComponentLocalService;
	}

	@Override
	public void setWrappedService(
		JIRAComponentLocalService jiraComponentLocalService) {
		_jiraComponentLocalService = jiraComponentLocalService;
	}

	private JIRAComponentLocalService _jiraComponentLocalService;
}