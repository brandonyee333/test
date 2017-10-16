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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link JIRAIssueLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssueLocalService
 * @generated
 */
@ProviderType
public class JIRAIssueLocalServiceWrapper implements JIRAIssueLocalService,
	ServiceWrapper<JIRAIssueLocalService> {
	public JIRAIssueLocalServiceWrapper(
		JIRAIssueLocalService jiraIssueLocalService) {
		_jiraIssueLocalService = jiraIssueLocalService;
	}

	/**
	* Adds the jira issue to the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the jira issue
	* @return the jira issue that was added
	*/
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAIssue addJIRAIssue(
		com.liferay.osb.customer.release.notes.jira.model.JIRAIssue jiraIssue) {
		return _jiraIssueLocalService.addJIRAIssue(jiraIssue);
	}

	/**
	* Creates a new jira issue with the primary key. Does not add the jira issue to the database.
	*
	* @param jiraIssueId the primary key for the new jira issue
	* @return the new jira issue
	*/
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAIssue createJIRAIssue(
		long jiraIssueId) {
		return _jiraIssueLocalService.createJIRAIssue(jiraIssueId);
	}

	/**
	* Deletes the jira issue from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the jira issue
	* @return the jira issue that was removed
	*/
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAIssue deleteJIRAIssue(
		com.liferay.osb.customer.release.notes.jira.model.JIRAIssue jiraIssue) {
		return _jiraIssueLocalService.deleteJIRAIssue(jiraIssue);
	}

	/**
	* Deletes the jira issue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssueId the primary key of the jira issue
	* @return the jira issue that was removed
	* @throws PortalException if a jira issue with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAIssue deleteJIRAIssue(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.deleteJIRAIssue(jiraIssueId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _jiraIssueLocalService.dynamicQuery();
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
		return _jiraIssueLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.notes.jira.model.impl.JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _jiraIssueLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.notes.jira.model.impl.JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _jiraIssueLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _jiraIssueLocalService.dynamicQueryCount(dynamicQuery);
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
		return _jiraIssueLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAIssue fetchJIRAIssue(
		long jiraIssueId) {
		return _jiraIssueLocalService.fetchJIRAIssue(jiraIssueId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _jiraIssueLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _jiraIssueLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> getIsRelatedToJIRAIssues(
		long jiraIssueId) {
		return _jiraIssueLocalService.getIsRelatedToJIRAIssues(jiraIssueId);
	}

	/**
	* Returns the jira issue with the primary key.
	*
	* @param jiraIssueId the primary key of the jira issue
	* @return the jira issue
	* @throws PortalException if a jira issue with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAIssue getJIRAIssue(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.getJIRAIssue(jiraIssueId);
	}

	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAIssue getJIRAIssue(
		java.lang.String jiraIssueKey) {
		return _jiraIssueLocalService.getJIRAIssue(jiraIssueKey);
	}

	/**
	* Returns a range of all the jira issues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.release.notes.jira.model.impl.JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of jira issues
	* @param end the upper bound of the range of jira issues (not inclusive)
	* @return the range of jira issues
	*/
	@Override
	public java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> getJIRAIssues(
		int start, int end) {
		return _jiraIssueLocalService.getJIRAIssues(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> getJIRAIssues(
		java.lang.String[] jiraIssueKeys)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.getJIRAIssues(jiraIssueKeys);
	}

	/**
	* Returns the number of jira issues.
	*
	* @return the number of jira issues
	*/
	@Override
	public int getJIRAIssuesCount() {
		return _jiraIssueLocalService.getJIRAIssuesCount();
	}

	@Override
	public java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> getJIRALabelJIRAIssues(
		java.lang.String label, java.lang.String jiraProjectKey) {
		return _jiraIssueLocalService.getJIRALabelJIRAIssues(label,
			jiraProjectKey);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> getJIRAProjectVersionJIRAIssues(
		long jiraProjectVersionId) {
		return _jiraIssueLocalService.getJIRAProjectVersionJIRAIssues(jiraProjectVersionId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _jiraIssueLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraIssueLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the jira issue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the jira issue
	* @return the jira issue that was updated
	*/
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAIssue updateJIRAIssue(
		com.liferay.osb.customer.release.notes.jira.model.JIRAIssue jiraIssue) {
		return _jiraIssueLocalService.updateJIRAIssue(jiraIssue);
	}

	@Override
	public JIRAIssueLocalService getWrappedService() {
		return _jiraIssueLocalService;
	}

	@Override
	public void setWrappedService(JIRAIssueLocalService jiraIssueLocalService) {
		_jiraIssueLocalService = jiraIssueLocalService;
	}

	private JIRAIssueLocalService _jiraIssueLocalService;
}