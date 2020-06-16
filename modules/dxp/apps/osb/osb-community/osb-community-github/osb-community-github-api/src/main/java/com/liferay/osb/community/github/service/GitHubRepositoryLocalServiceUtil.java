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

package com.liferay.osb.community.github.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for GitHubRepository. This utility wraps
 * {@link com.liferay.osb.community.github.service.impl.GitHubRepositoryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Haote Chou
 * @see GitHubRepositoryLocalService
 * @see com.liferay.osb.community.github.service.base.GitHubRepositoryLocalServiceBaseImpl
 * @see com.liferay.osb.community.github.service.impl.GitHubRepositoryLocalServiceImpl
 * @generated
 */
@ProviderType
public class GitHubRepositoryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.community.github.service.impl.GitHubRepositoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the git hub repository to the database. Also notifies the appropriate model listeners.
	*
	* @param gitHubRepository the git hub repository
	* @return the git hub repository that was added
	*/
	public static com.liferay.osb.community.github.model.GitHubRepository addGitHubRepository(
		com.liferay.osb.community.github.model.GitHubRepository gitHubRepository) {
		return getService().addGitHubRepository(gitHubRepository);
	}

	/**
	* Creates a new git hub repository with the primary key. Does not add the git hub repository to the database.
	*
	* @param gitHubRepositoryId the primary key for the new git hub repository
	* @return the new git hub repository
	*/
	public static com.liferay.osb.community.github.model.GitHubRepository createGitHubRepository(
		long gitHubRepositoryId) {
		return getService().createGitHubRepository(gitHubRepositoryId);
	}

	/**
	* Deletes the git hub repository from the database. Also notifies the appropriate model listeners.
	*
	* @param gitHubRepository the git hub repository
	* @return the git hub repository that was removed
	*/
	public static com.liferay.osb.community.github.model.GitHubRepository deleteGitHubRepository(
		com.liferay.osb.community.github.model.GitHubRepository gitHubRepository) {
		return getService().deleteGitHubRepository(gitHubRepository);
	}

	public static com.liferay.osb.community.github.model.GitHubRepository deleteGitHubRepository(
		java.lang.String owner, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteGitHubRepository(owner, name);
	}

	/**
	* Deletes the git hub repository with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gitHubRepositoryId the primary key of the git hub repository
	* @return the git hub repository that was removed
	* @throws PortalException if a git hub repository with the primary key could not be found
	*/
	public static com.liferay.osb.community.github.model.GitHubRepository deleteGitHubRepository(
		long gitHubRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteGitHubRepository(gitHubRepositoryId);
	}

	public static com.liferay.osb.community.github.model.GitHubRepository fetchGitHubRepository(
		long gitHubRepositoryId) {
		return getService().fetchGitHubRepository(gitHubRepositoryId);
	}

	/**
	* Returns the git hub repository with the primary key.
	*
	* @param gitHubRepositoryId the primary key of the git hub repository
	* @return the git hub repository
	* @throws PortalException if a git hub repository with the primary key could not be found
	*/
	public static com.liferay.osb.community.github.model.GitHubRepository getGitHubRepository(
		long gitHubRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGitHubRepository(gitHubRepositoryId);
	}

	public static com.liferay.osb.community.github.model.GitHubRepository getGitHubRepository(
		long userId, java.lang.String owner, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGitHubRepository(userId, owner, name);
	}

	/**
	* Updates the git hub repository in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param gitHubRepository the git hub repository
	* @return the git hub repository that was updated
	*/
	public static com.liferay.osb.community.github.model.GitHubRepository updateGitHubRepository(
		com.liferay.osb.community.github.model.GitHubRepository gitHubRepository) {
		return getService().updateGitHubRepository(gitHubRepository);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of git hub repositories.
	*
	* @return the number of git hub repositories
	*/
	public static int getGitHubRepositoriesCount() {
		return getService().getGitHubRepositoriesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.community.github.model.impl.GitHubRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.community.github.model.impl.GitHubRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the git hub repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.community.github.model.impl.GitHubRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of git hub repositories
	* @param end the upper bound of the range of git hub repositories (not inclusive)
	* @return the range of git hub repositories
	*/
	public static java.util.List<com.liferay.osb.community.github.model.GitHubRepository> getGitHubRepositories(
		int start, int end) {
		return getService().getGitHubRepositories(start, end);
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

	public static void updateGitHubRepositoryCache()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateGitHubRepositoryCache();
	}

	public static GitHubRepositoryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<GitHubRepositoryLocalService, GitHubRepositoryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(GitHubRepositoryLocalService.class);
}