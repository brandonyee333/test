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
 * Provides a wrapper for {@link JIRAProjectVersionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProjectVersionLocalService
 * @generated
 */
public class JIRAProjectVersionLocalServiceWrapper
	implements JIRAProjectVersionLocalService,
			   ServiceWrapper<JIRAProjectVersionLocalService> {

	public JIRAProjectVersionLocalServiceWrapper(
		JIRAProjectVersionLocalService jiraProjectVersionLocalService) {

		_jiraProjectVersionLocalService = jiraProjectVersionLocalService;
	}

	/**
	 * Adds the jira project version to the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraProjectVersion the jira project version
	 * @return the jira project version that was added
	 */
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion
		addJIRAProjectVersion(
			com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion
				jiraProjectVersion) {

		return _jiraProjectVersionLocalService.addJIRAProjectVersion(
			jiraProjectVersion);
	}

	/**
	 * Creates a new jira project version with the primary key. Does not add the jira project version to the database.
	 *
	 * @param jiraProjectVersionId the primary key for the new jira project version
	 * @return the new jira project version
	 */
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion
		createJIRAProjectVersion(long jiraProjectVersionId) {

		return _jiraProjectVersionLocalService.createJIRAProjectVersion(
			jiraProjectVersionId);
	}

	/**
	 * Deletes the jira project version from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraProjectVersion the jira project version
	 * @return the jira project version that was removed
	 */
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion
		deleteJIRAProjectVersion(
			com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion
				jiraProjectVersion) {

		return _jiraProjectVersionLocalService.deleteJIRAProjectVersion(
			jiraProjectVersion);
	}

	/**
	 * Deletes the jira project version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraProjectVersionId the primary key of the jira project version
	 * @return the jira project version that was removed
	 * @throws PortalException if a jira project version with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion
			deleteJIRAProjectVersion(long jiraProjectVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jiraProjectVersionLocalService.deleteJIRAProjectVersion(
			jiraProjectVersionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jiraProjectVersionLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _jiraProjectVersionLocalService.dynamicQuery();
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

		return _jiraProjectVersionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectVersionModelImpl</code>.
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

		return _jiraProjectVersionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectVersionModelImpl</code>.
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

		return _jiraProjectVersionLocalService.dynamicQuery(
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

		return _jiraProjectVersionLocalService.dynamicQueryCount(dynamicQuery);
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

		return _jiraProjectVersionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion
		fetchJIRAProjectVersion(long jiraProjectVersionId) {

		return _jiraProjectVersionLocalService.fetchJIRAProjectVersion(
			jiraProjectVersionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _jiraProjectVersionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _jiraProjectVersionLocalService.
			getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion>
			getJIRAProjectJIRAProjectVersion(long jiraProjectId, String name) {

		return _jiraProjectVersionLocalService.getJIRAProjectJIRAProjectVersion(
			jiraProjectId, name);
	}

	/**
	 * Returns the jira project version with the primary key.
	 *
	 * @param jiraProjectVersionId the primary key of the jira project version
	 * @return the jira project version
	 * @throws PortalException if a jira project version with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion
			getJIRAProjectVersion(long jiraProjectVersionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jiraProjectVersionLocalService.getJIRAProjectVersion(
			jiraProjectVersionId);
	}

	/**
	 * Returns a range of all the jira project versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.release.notes.jira.model.impl.JIRAProjectVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira project versions
	 * @param end the upper bound of the range of jira project versions (not inclusive)
	 * @return the range of jira project versions
	 */
	@Override
	public java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion>
			getJIRAProjectVersions(int start, int end) {

		return _jiraProjectVersionLocalService.getJIRAProjectVersions(
			start, end);
	}

	/**
	 * Returns the number of jira project versions.
	 *
	 * @return the number of jira project versions
	 */
	@Override
	public int getJIRAProjectVersionsCount() {
		return _jiraProjectVersionLocalService.getJIRAProjectVersionsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _jiraProjectVersionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jiraProjectVersionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the jira project version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param jiraProjectVersion the jira project version
	 * @return the jira project version that was updated
	 */
	@Override
	public com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion
		updateJIRAProjectVersion(
			com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion
				jiraProjectVersion) {

		return _jiraProjectVersionLocalService.updateJIRAProjectVersion(
			jiraProjectVersion);
	}

	@Override
	public JIRAProjectVersionLocalService getWrappedService() {
		return _jiraProjectVersionLocalService;
	}

	@Override
	public void setWrappedService(
		JIRAProjectVersionLocalService jiraProjectVersionLocalService) {

		_jiraProjectVersionLocalService = jiraProjectVersionLocalService;
	}

	private JIRAProjectVersionLocalService _jiraProjectVersionLocalService;

}