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

package com.liferay.osb.community.github.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GitHubContributorLocalService}.
 *
 * @author Haote Chou
 * @see GitHubContributorLocalService
 * @generated
 */
public class GitHubContributorLocalServiceWrapper
	implements GitHubContributorLocalService,
			   ServiceWrapper<GitHubContributorLocalService> {

	public GitHubContributorLocalServiceWrapper(
		GitHubContributorLocalService gitHubContributorLocalService) {

		_gitHubContributorLocalService = gitHubContributorLocalService;
	}

	/**
	 * Adds the git hub contributor to the database. Also notifies the appropriate model listeners.
	 *
	 * @param gitHubContributor the git hub contributor
	 * @return the git hub contributor that was added
	 */
	@Override
	public com.liferay.osb.community.github.model.GitHubContributor
		addGitHubContributor(
			com.liferay.osb.community.github.model.GitHubContributor
				gitHubContributor) {

		return _gitHubContributorLocalService.addGitHubContributor(
			gitHubContributor);
	}

	/**
	 * Creates a new git hub contributor with the primary key. Does not add the git hub contributor to the database.
	 *
	 * @param gitHubContributorId the primary key for the new git hub contributor
	 * @return the new git hub contributor
	 */
	@Override
	public com.liferay.osb.community.github.model.GitHubContributor
		createGitHubContributor(long gitHubContributorId) {

		return _gitHubContributorLocalService.createGitHubContributor(
			gitHubContributorId);
	}

	/**
	 * Deletes the git hub contributor from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gitHubContributor the git hub contributor
	 * @return the git hub contributor that was removed
	 */
	@Override
	public com.liferay.osb.community.github.model.GitHubContributor
		deleteGitHubContributor(
			com.liferay.osb.community.github.model.GitHubContributor
				gitHubContributor) {

		return _gitHubContributorLocalService.deleteGitHubContributor(
			gitHubContributor);
	}

	/**
	 * Deletes the git hub contributor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gitHubContributorId the primary key of the git hub contributor
	 * @return the git hub contributor that was removed
	 * @throws PortalException if a git hub contributor with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.community.github.model.GitHubContributor
			deleteGitHubContributor(long gitHubContributorId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gitHubContributorLocalService.deleteGitHubContributor(
			gitHubContributorId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gitHubContributorLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _gitHubContributorLocalService.dynamicQuery();
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

		return _gitHubContributorLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.github.model.impl.GitHubContributorModelImpl</code>.
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

		return _gitHubContributorLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.github.model.impl.GitHubContributorModelImpl</code>.
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

		return _gitHubContributorLocalService.dynamicQuery(
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

		return _gitHubContributorLocalService.dynamicQueryCount(dynamicQuery);
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

		return _gitHubContributorLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.community.github.model.GitHubContributor
		fetchGitHubContributor(long gitHubContributorId) {

		return _gitHubContributorLocalService.fetchGitHubContributor(
			gitHubContributorId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _gitHubContributorLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the git hub contributor with the primary key.
	 *
	 * @param gitHubContributorId the primary key of the git hub contributor
	 * @return the git hub contributor
	 * @throws PortalException if a git hub contributor with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.community.github.model.GitHubContributor
			getGitHubContributor(long gitHubContributorId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gitHubContributorLocalService.getGitHubContributor(
			gitHubContributorId);
	}

	/**
	 * Returns a range of all the git hub contributors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.github.model.impl.GitHubContributorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of git hub contributors
	 * @param end the upper bound of the range of git hub contributors (not inclusive)
	 * @return the range of git hub contributors
	 */
	@Override
	public java.util.List
		<com.liferay.osb.community.github.model.GitHubContributor>
			getGitHubContributors(int start, int end) {

		return _gitHubContributorLocalService.getGitHubContributors(start, end);
	}

	/**
	 * Returns the number of git hub contributors.
	 *
	 * @return the number of git hub contributors
	 */
	@Override
	public int getGitHubContributorsCount() {
		return _gitHubContributorLocalService.getGitHubContributorsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _gitHubContributorLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _gitHubContributorLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gitHubContributorLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List
		<com.liferay.osb.community.github.model.GitHubContributor>
				getTopGitHubContributors(
					long userId, String owner, String name, int count)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _gitHubContributorLocalService.getTopGitHubContributors(
			userId, owner, name, count);
	}

	/**
	 * Updates the git hub contributor in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param gitHubContributor the git hub contributor
	 * @return the git hub contributor that was updated
	 */
	@Override
	public com.liferay.osb.community.github.model.GitHubContributor
		updateGitHubContributor(
			com.liferay.osb.community.github.model.GitHubContributor
				gitHubContributor) {

		return _gitHubContributorLocalService.updateGitHubContributor(
			gitHubContributor);
	}

	@Override
	public GitHubContributorLocalService getWrappedService() {
		return _gitHubContributorLocalService;
	}

	@Override
	public void setWrappedService(
		GitHubContributorLocalService gitHubContributorLocalService) {

		_gitHubContributorLocalService = gitHubContributorLocalService;
	}

	private GitHubContributorLocalService _gitHubContributorLocalService;

}