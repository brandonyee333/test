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
 * Provides the local service utility for GitHubContributor. This utility wraps
 * {@link com.liferay.osb.community.github.service.impl.GitHubContributorLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Haote Chou
 * @see GitHubContributorLocalService
 * @see com.liferay.osb.community.github.service.base.GitHubContributorLocalServiceBaseImpl
 * @see com.liferay.osb.community.github.service.impl.GitHubContributorLocalServiceImpl
 * @generated
 */
@ProviderType
public class GitHubContributorLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.community.github.service.impl.GitHubContributorLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the git hub contributor to the database. Also notifies the appropriate model listeners.
	*
	* @param gitHubContributor the git hub contributor
	* @return the git hub contributor that was added
	*/
	public static com.liferay.osb.community.github.model.GitHubContributor addGitHubContributor(
		com.liferay.osb.community.github.model.GitHubContributor gitHubContributor) {
		return getService().addGitHubContributor(gitHubContributor);
	}

	/**
	* Creates a new git hub contributor with the primary key. Does not add the git hub contributor to the database.
	*
	* @param gitHubContributorId the primary key for the new git hub contributor
	* @return the new git hub contributor
	*/
	public static com.liferay.osb.community.github.model.GitHubContributor createGitHubContributor(
		long gitHubContributorId) {
		return getService().createGitHubContributor(gitHubContributorId);
	}

	/**
	* Deletes the git hub contributor from the database. Also notifies the appropriate model listeners.
	*
	* @param gitHubContributor the git hub contributor
	* @return the git hub contributor that was removed
	*/
	public static com.liferay.osb.community.github.model.GitHubContributor deleteGitHubContributor(
		com.liferay.osb.community.github.model.GitHubContributor gitHubContributor) {
		return getService().deleteGitHubContributor(gitHubContributor);
	}

	/**
	* Deletes the git hub contributor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gitHubContributorId the primary key of the git hub contributor
	* @return the git hub contributor that was removed
	* @throws PortalException if a git hub contributor with the primary key could not be found
	*/
	public static com.liferay.osb.community.github.model.GitHubContributor deleteGitHubContributor(
		long gitHubContributorId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteGitHubContributor(gitHubContributorId);
	}

	public static com.liferay.osb.community.github.model.GitHubContributor fetchGitHubContributor(
		long gitHubContributorId) {
		return getService().fetchGitHubContributor(gitHubContributorId);
	}

	/**
	* Returns the git hub contributor with the primary key.
	*
	* @param gitHubContributorId the primary key of the git hub contributor
	* @return the git hub contributor
	* @throws PortalException if a git hub contributor with the primary key could not be found
	*/
	public static com.liferay.osb.community.github.model.GitHubContributor getGitHubContributor(
		long gitHubContributorId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGitHubContributor(gitHubContributorId);
	}

	/**
	* Updates the git hub contributor in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param gitHubContributor the git hub contributor
	* @return the git hub contributor that was updated
	*/
	public static com.liferay.osb.community.github.model.GitHubContributor updateGitHubContributor(
		com.liferay.osb.community.github.model.GitHubContributor gitHubContributor) {
		return getService().updateGitHubContributor(gitHubContributor);
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
	* Returns the number of git hub contributors.
	*
	* @return the number of git hub contributors
	*/
	public static int getGitHubContributorsCount() {
		return getService().getGitHubContributorsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.community.github.model.impl.GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.community.github.model.impl.GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the git hub contributors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.community.github.model.impl.GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @return the range of git hub contributors
	*/
	public static java.util.List<com.liferay.osb.community.github.model.GitHubContributor> getGitHubContributors(
		int start, int end) {
		return getService().getGitHubContributors(start, end);
	}

	public static java.util.List<com.liferay.osb.community.github.model.GitHubContributor> getTopGitHubContributors(
		long userId, java.lang.String owner, java.lang.String name, int count)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTopGitHubContributors(userId, owner, name, count);
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

	public static GitHubContributorLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<GitHubContributorLocalService, GitHubContributorLocalService> _serviceTracker =
		ServiceTrackerFactory.open(GitHubContributorLocalService.class);
}