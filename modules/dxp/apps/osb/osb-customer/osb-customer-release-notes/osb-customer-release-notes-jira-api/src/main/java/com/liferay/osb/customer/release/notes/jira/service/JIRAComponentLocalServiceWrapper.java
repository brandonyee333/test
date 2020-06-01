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

package com.liferay.osb.customer.release.notes.jira.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link JIRAComponentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAComponentLocalService
 * @generated
 */
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
	public com.liferay.osb.customer.release.notes.jira.model.JIRAComponent
		addJIRAComponent(
			com.liferay.osb.customer.release.notes.jira.model.JIRAComponent
				jiraComponent) {

		return _jiraComponentLocalService.addJIRAComponent(jiraComponent);
	}

	/**
	 * Creates a new jira component with the primary key. Does not add the jira component to the database.
	 *
	 * @param jiraComponentId the primary key for the new jira component
	 * @return the new jira component
	 */
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAComponent
		createJIRAComponent(long jiraComponentId) {

		return _jiraComponentLocalService.createJIRAComponent(jiraComponentId);
	}

	/**
	 * Deletes the jira component from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraComponent the jira component
	 * @return the jira component that was removed
	 */
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAComponent
		deleteJIRAComponent(
			com.liferay.osb.customer.release.notes.jira.model.JIRAComponent
				jiraComponent) {

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
	public com.liferay.osb.customer.release.notes.jira.model.JIRAComponent
			deleteJIRAComponent(long jiraComponentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jiraComponentLocalService.deleteJIRAComponent(jiraComponentId);
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
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _jiraComponentLocalService.dynamicQuery();
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.jira.model.impl.JIRAComponentModelImpl</code>.
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

		return _jiraComponentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.jira.model.impl.JIRAComponentModelImpl</code>.
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

		return _jiraComponentLocalService.dynamicQuery(
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

		return _jiraComponentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAComponent
		fetchJIRAComponent(long jiraComponentId) {

		return _jiraComponentLocalService.fetchJIRAComponent(jiraComponentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _jiraComponentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _jiraComponentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the jira component with the primary key.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component
	 * @throws PortalException if a jira component with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAComponent
			getJIRAComponent(long jiraComponentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jiraComponentLocalService.getJIRAComponent(jiraComponentId);
	}

	/**
	 * Returns a range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.jira.model.impl.JIRAComponentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @return the range of jira components
	 */
	@Override
	public java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAComponent>
			getJIRAComponents(int start, int end) {

		return _jiraComponentLocalService.getJIRAComponents(start, end);
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

	@Override
	public java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAComponent>
			getJIRAIssueJIRAComponents(long jiraIssueId) {

		return _jiraComponentLocalService.getJIRAIssueJIRAComponents(
			jiraIssueId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _jiraComponentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jiraComponentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the jira component in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param jiraComponent the jira component
	 * @return the jira component that was updated
	 */
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAComponent
		updateJIRAComponent(
			com.liferay.osb.customer.release.notes.jira.model.JIRAComponent
				jiraComponent) {

		return _jiraComponentLocalService.updateJIRAComponent(jiraComponent);
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