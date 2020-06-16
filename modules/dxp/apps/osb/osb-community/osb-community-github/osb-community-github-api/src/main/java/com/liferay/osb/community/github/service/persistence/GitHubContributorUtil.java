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

package com.liferay.osb.community.github.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.github.model.GitHubContributor;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the git hub contributor service. This utility wraps {@link com.liferay.osb.community.github.service.persistence.impl.GitHubContributorPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Haote Chou
 * @see GitHubContributorPersistence
 * @see com.liferay.osb.community.github.service.persistence.impl.GitHubContributorPersistenceImpl
 * @generated
 */
@ProviderType
public class GitHubContributorUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(GitHubContributor gitHubContributor) {
		getPersistence().clearCache(gitHubContributor);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<GitHubContributor> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GitHubContributor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GitHubContributor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GitHubContributor update(GitHubContributor gitHubContributor) {
		return getPersistence().update(gitHubContributor);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GitHubContributor update(
		GitHubContributor gitHubContributor, ServiceContext serviceContext) {
		return getPersistence().update(gitHubContributor, serviceContext);
	}

	/**
	* Returns all the git hub contributors where gitHubRepositoryId = &#63;.
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @return the matching git hub contributors
	*/
	public static List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId) {
		return getPersistence().findByGitHubRepositoryId(gitHubRepositoryId);
	}

	/**
	* Returns a range of all the git hub contributors where gitHubRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @return the range of matching git hub contributors
	*/
	public static List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId, int start, int end) {
		return getPersistence()
				   .findByGitHubRepositoryId(gitHubRepositoryId, start, end);
	}

	/**
	* Returns an ordered range of all the git hub contributors where gitHubRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching git hub contributors
	*/
	public static List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId, int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator) {
		return getPersistence()
				   .findByGitHubRepositoryId(gitHubRepositoryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the git hub contributors where gitHubRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching git hub contributors
	*/
	public static List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId, int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGitHubRepositoryId(gitHubRepositoryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching git hub contributor
	* @throws NoSuchGitHubContributorException if a matching git hub contributor could not be found
	*/
	public static GitHubContributor findByGitHubRepositoryId_First(
		long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator)
		throws com.liferay.osb.community.github.exception.NoSuchGitHubContributorException {
		return getPersistence()
				   .findByGitHubRepositoryId_First(gitHubRepositoryId,
			orderByComparator);
	}

	/**
	* Returns the first git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching git hub contributor, or <code>null</code> if a matching git hub contributor could not be found
	*/
	public static GitHubContributor fetchByGitHubRepositoryId_First(
		long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator) {
		return getPersistence()
				   .fetchByGitHubRepositoryId_First(gitHubRepositoryId,
			orderByComparator);
	}

	/**
	* Returns the last git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching git hub contributor
	* @throws NoSuchGitHubContributorException if a matching git hub contributor could not be found
	*/
	public static GitHubContributor findByGitHubRepositoryId_Last(
		long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator)
		throws com.liferay.osb.community.github.exception.NoSuchGitHubContributorException {
		return getPersistence()
				   .findByGitHubRepositoryId_Last(gitHubRepositoryId,
			orderByComparator);
	}

	/**
	* Returns the last git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching git hub contributor, or <code>null</code> if a matching git hub contributor could not be found
	*/
	public static GitHubContributor fetchByGitHubRepositoryId_Last(
		long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator) {
		return getPersistence()
				   .fetchByGitHubRepositoryId_Last(gitHubRepositoryId,
			orderByComparator);
	}

	/**
	* Returns the git hub contributors before and after the current git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	*
	* @param gitHubContributorId the primary key of the current git hub contributor
	* @param gitHubRepositoryId the git hub repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next git hub contributor
	* @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	*/
	public static GitHubContributor[] findByGitHubRepositoryId_PrevAndNext(
		long gitHubContributorId, long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator)
		throws com.liferay.osb.community.github.exception.NoSuchGitHubContributorException {
		return getPersistence()
				   .findByGitHubRepositoryId_PrevAndNext(gitHubContributorId,
			gitHubRepositoryId, orderByComparator);
	}

	/**
	* Removes all the git hub contributors where gitHubRepositoryId = &#63; from the database.
	*
	* @param gitHubRepositoryId the git hub repository ID
	*/
	public static void removeByGitHubRepositoryId(long gitHubRepositoryId) {
		getPersistence().removeByGitHubRepositoryId(gitHubRepositoryId);
	}

	/**
	* Returns the number of git hub contributors where gitHubRepositoryId = &#63;.
	*
	* @param gitHubRepositoryId the git hub repository ID
	* @return the number of matching git hub contributors
	*/
	public static int countByGitHubRepositoryId(long gitHubRepositoryId) {
		return getPersistence().countByGitHubRepositoryId(gitHubRepositoryId);
	}

	/**
	* Caches the git hub contributor in the entity cache if it is enabled.
	*
	* @param gitHubContributor the git hub contributor
	*/
	public static void cacheResult(GitHubContributor gitHubContributor) {
		getPersistence().cacheResult(gitHubContributor);
	}

	/**
	* Caches the git hub contributors in the entity cache if it is enabled.
	*
	* @param gitHubContributors the git hub contributors
	*/
	public static void cacheResult(List<GitHubContributor> gitHubContributors) {
		getPersistence().cacheResult(gitHubContributors);
	}

	/**
	* Creates a new git hub contributor with the primary key. Does not add the git hub contributor to the database.
	*
	* @param gitHubContributorId the primary key for the new git hub contributor
	* @return the new git hub contributor
	*/
	public static GitHubContributor create(long gitHubContributorId) {
		return getPersistence().create(gitHubContributorId);
	}

	/**
	* Removes the git hub contributor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gitHubContributorId the primary key of the git hub contributor
	* @return the git hub contributor that was removed
	* @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	*/
	public static GitHubContributor remove(long gitHubContributorId)
		throws com.liferay.osb.community.github.exception.NoSuchGitHubContributorException {
		return getPersistence().remove(gitHubContributorId);
	}

	public static GitHubContributor updateImpl(
		GitHubContributor gitHubContributor) {
		return getPersistence().updateImpl(gitHubContributor);
	}

	/**
	* Returns the git hub contributor with the primary key or throws a {@link NoSuchGitHubContributorException} if it could not be found.
	*
	* @param gitHubContributorId the primary key of the git hub contributor
	* @return the git hub contributor
	* @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	*/
	public static GitHubContributor findByPrimaryKey(long gitHubContributorId)
		throws com.liferay.osb.community.github.exception.NoSuchGitHubContributorException {
		return getPersistence().findByPrimaryKey(gitHubContributorId);
	}

	/**
	* Returns the git hub contributor with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gitHubContributorId the primary key of the git hub contributor
	* @return the git hub contributor, or <code>null</code> if a git hub contributor with the primary key could not be found
	*/
	public static GitHubContributor fetchByPrimaryKey(long gitHubContributorId) {
		return getPersistence().fetchByPrimaryKey(gitHubContributorId);
	}

	public static java.util.Map<java.io.Serializable, GitHubContributor> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the git hub contributors.
	*
	* @return the git hub contributors
	*/
	public static List<GitHubContributor> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the git hub contributors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @return the range of git hub contributors
	*/
	public static List<GitHubContributor> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the git hub contributors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of git hub contributors
	*/
	public static List<GitHubContributor> findAll(int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the git hub contributors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubContributorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of git hub contributors
	* @param end the upper bound of the range of git hub contributors (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of git hub contributors
	*/
	public static List<GitHubContributor> findAll(int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the git hub contributors from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of git hub contributors.
	*
	* @return the number of git hub contributors
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static GitHubContributorPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<GitHubContributorPersistence, GitHubContributorPersistence> _serviceTracker =
		ServiceTrackerFactory.open(GitHubContributorPersistence.class);
}