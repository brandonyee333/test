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
 * Provides a wrapper for {@link GitHubRepositoryLocalService}.
 *
 * @author Haote Chou
 * @see GitHubRepositoryLocalService
 * @generated
 */
public class GitHubRepositoryLocalServiceWrapper
	implements GitHubRepositoryLocalService,
			   ServiceWrapper<GitHubRepositoryLocalService> {

	public GitHubRepositoryLocalServiceWrapper(
		GitHubRepositoryLocalService gitHubRepositoryLocalService) {

		_gitHubRepositoryLocalService = gitHubRepositoryLocalService;
	}

	/**
	 * Adds the git hub repository to the database. Also notifies the appropriate model listeners.
	 *
	 * @param gitHubRepository the git hub repository
	 * @return the git hub repository that was added
	 */
	@Override
	public com.liferay.osb.community.github.model.GitHubRepository
		addGitHubRepository(
			com.liferay.osb.community.github.model.GitHubRepository
				gitHubRepository) {

		return _gitHubRepositoryLocalService.addGitHubRepository(
			gitHubRepository);
	}

	/**
	 * Creates a new git hub repository with the primary key. Does not add the git hub repository to the database.
	 *
	 * @param gitHubRepositoryId the primary key for the new git hub repository
	 * @return the new git hub repository
	 */
	@Override
	public com.liferay.osb.community.github.model.GitHubRepository
		createGitHubRepository(long gitHubRepositoryId) {

		return _gitHubRepositoryLocalService.createGitHubRepository(
			gitHubRepositoryId);
	}

	/**
	 * Deletes the git hub repository from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gitHubRepository the git hub repository
	 * @return the git hub repository that was removed
	 */
	@Override
	public com.liferay.osb.community.github.model.GitHubRepository
		deleteGitHubRepository(
			com.liferay.osb.community.github.model.GitHubRepository
				gitHubRepository) {

		return _gitHubRepositoryLocalService.deleteGitHubRepository(
			gitHubRepository);
	}

	/**
	 * Deletes the git hub repository with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gitHubRepositoryId the primary key of the git hub repository
	 * @return the git hub repository that was removed
	 * @throws PortalException if a git hub repository with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.community.github.model.GitHubRepository
			deleteGitHubRepository(long gitHubRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gitHubRepositoryLocalService.deleteGitHubRepository(
			gitHubRepositoryId);
	}

	@Override
	public com.liferay.osb.community.github.model.GitHubRepository
			deleteGitHubRepository(String owner, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gitHubRepositoryLocalService.deleteGitHubRepository(
			owner, name);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gitHubRepositoryLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _gitHubRepositoryLocalService.dynamicQuery();
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

		return _gitHubRepositoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.github.model.impl.GitHubRepositoryModelImpl</code>.
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

		return _gitHubRepositoryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.github.model.impl.GitHubRepositoryModelImpl</code>.
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

		return _gitHubRepositoryLocalService.dynamicQuery(
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

		return _gitHubRepositoryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _gitHubRepositoryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.community.github.model.GitHubRepository
		fetchGitHubRepository(long gitHubRepositoryId) {

		return _gitHubRepositoryLocalService.fetchGitHubRepository(
			gitHubRepositoryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _gitHubRepositoryLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the git hub repositories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.community.github.model.impl.GitHubRepositoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of git hub repositories
	 * @param end the upper bound of the range of git hub repositories (not inclusive)
	 * @return the range of git hub repositories
	 */
	@Override
	public java.util.List
		<com.liferay.osb.community.github.model.GitHubRepository>
			getGitHubRepositories(int start, int end) {

		return _gitHubRepositoryLocalService.getGitHubRepositories(start, end);
	}

	/**
	 * Returns the number of git hub repositories.
	 *
	 * @return the number of git hub repositories
	 */
	@Override
	public int getGitHubRepositoriesCount() {
		return _gitHubRepositoryLocalService.getGitHubRepositoriesCount();
	}

	/**
	 * Returns the git hub repository with the primary key.
	 *
	 * @param gitHubRepositoryId the primary key of the git hub repository
	 * @return the git hub repository
	 * @throws PortalException if a git hub repository with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.community.github.model.GitHubRepository
			getGitHubRepository(long gitHubRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gitHubRepositoryLocalService.getGitHubRepository(
			gitHubRepositoryId);
	}

	@Override
	public com.liferay.osb.community.github.model.GitHubRepository
			getGitHubRepository(long userId, String owner, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gitHubRepositoryLocalService.getGitHubRepository(
			userId, owner, name);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _gitHubRepositoryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _gitHubRepositoryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _gitHubRepositoryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the git hub repository in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param gitHubRepository the git hub repository
	 * @return the git hub repository that was updated
	 */
	@Override
	public com.liferay.osb.community.github.model.GitHubRepository
		updateGitHubRepository(
			com.liferay.osb.community.github.model.GitHubRepository
				gitHubRepository) {

		return _gitHubRepositoryLocalService.updateGitHubRepository(
			gitHubRepository);
	}

	@Override
	public void updateGitHubRepositoryCache()
		throws com.liferay.portal.kernel.exception.PortalException {

		_gitHubRepositoryLocalService.updateGitHubRepositoryCache();
	}

	@Override
	public GitHubRepositoryLocalService getWrappedService() {
		return _gitHubRepositoryLocalService;
	}

	@Override
	public void setWrappedService(
		GitHubRepositoryLocalService gitHubRepositoryLocalService) {

		_gitHubRepositoryLocalService = gitHubRepositoryLocalService;
	}

	private GitHubRepositoryLocalService _gitHubRepositoryLocalService;

}