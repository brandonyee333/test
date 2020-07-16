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

package com.liferay.osb.community.github.service.persistence;

import com.liferay.osb.community.github.model.GitHubRepository;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the git hub repository service. This utility wraps <code>com.liferay.osb.community.github.service.persistence.impl.GitHubRepositoryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Haote Chou
 * @see GitHubRepositoryPersistence
 * @generated
 */
public class GitHubRepositoryUtil {

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
	public static void clearCache(GitHubRepository gitHubRepository) {
		getPersistence().clearCache(gitHubRepository);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, GitHubRepository> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<GitHubRepository> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GitHubRepository> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GitHubRepository> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<GitHubRepository> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GitHubRepository update(GitHubRepository gitHubRepository) {
		return getPersistence().update(gitHubRepository);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GitHubRepository update(
		GitHubRepository gitHubRepository, ServiceContext serviceContext) {

		return getPersistence().update(gitHubRepository, serviceContext);
	}

	/**
	 * Returns the git hub repository where owner = &#63; and name = &#63; or throws a <code>NoSuchGitHubRepositoryException</code> if it could not be found.
	 *
	 * @param owner the owner
	 * @param name the name
	 * @return the matching git hub repository
	 * @throws NoSuchGitHubRepositoryException if a matching git hub repository could not be found
	 */
	public static GitHubRepository findByO_N(String owner, String name)
		throws com.liferay.osb.community.github.exception.
			NoSuchGitHubRepositoryException {

		return getPersistence().findByO_N(owner, name);
	}

	/**
	 * Returns the git hub repository where owner = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param owner the owner
	 * @param name the name
	 * @return the matching git hub repository, or <code>null</code> if a matching git hub repository could not be found
	 */
	public static GitHubRepository fetchByO_N(String owner, String name) {
		return getPersistence().fetchByO_N(owner, name);
	}

	/**
	 * Returns the git hub repository where owner = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param owner the owner
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching git hub repository, or <code>null</code> if a matching git hub repository could not be found
	 */
	public static GitHubRepository fetchByO_N(
		String owner, String name, boolean useFinderCache) {

		return getPersistence().fetchByO_N(owner, name, useFinderCache);
	}

	/**
	 * Removes the git hub repository where owner = &#63; and name = &#63; from the database.
	 *
	 * @param owner the owner
	 * @param name the name
	 * @return the git hub repository that was removed
	 */
	public static GitHubRepository removeByO_N(String owner, String name)
		throws com.liferay.osb.community.github.exception.
			NoSuchGitHubRepositoryException {

		return getPersistence().removeByO_N(owner, name);
	}

	/**
	 * Returns the number of git hub repositories where owner = &#63; and name = &#63;.
	 *
	 * @param owner the owner
	 * @param name the name
	 * @return the number of matching git hub repositories
	 */
	public static int countByO_N(String owner, String name) {
		return getPersistence().countByO_N(owner, name);
	}

	/**
	 * Caches the git hub repository in the entity cache if it is enabled.
	 *
	 * @param gitHubRepository the git hub repository
	 */
	public static void cacheResult(GitHubRepository gitHubRepository) {
		getPersistence().cacheResult(gitHubRepository);
	}

	/**
	 * Caches the git hub repositories in the entity cache if it is enabled.
	 *
	 * @param gitHubRepositories the git hub repositories
	 */
	public static void cacheResult(List<GitHubRepository> gitHubRepositories) {
		getPersistence().cacheResult(gitHubRepositories);
	}

	/**
	 * Creates a new git hub repository with the primary key. Does not add the git hub repository to the database.
	 *
	 * @param gitHubRepositoryId the primary key for the new git hub repository
	 * @return the new git hub repository
	 */
	public static GitHubRepository create(long gitHubRepositoryId) {
		return getPersistence().create(gitHubRepositoryId);
	}

	/**
	 * Removes the git hub repository with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gitHubRepositoryId the primary key of the git hub repository
	 * @return the git hub repository that was removed
	 * @throws NoSuchGitHubRepositoryException if a git hub repository with the primary key could not be found
	 */
	public static GitHubRepository remove(long gitHubRepositoryId)
		throws com.liferay.osb.community.github.exception.
			NoSuchGitHubRepositoryException {

		return getPersistence().remove(gitHubRepositoryId);
	}

	public static GitHubRepository updateImpl(
		GitHubRepository gitHubRepository) {

		return getPersistence().updateImpl(gitHubRepository);
	}

	/**
	 * Returns the git hub repository with the primary key or throws a <code>NoSuchGitHubRepositoryException</code> if it could not be found.
	 *
	 * @param gitHubRepositoryId the primary key of the git hub repository
	 * @return the git hub repository
	 * @throws NoSuchGitHubRepositoryException if a git hub repository with the primary key could not be found
	 */
	public static GitHubRepository findByPrimaryKey(long gitHubRepositoryId)
		throws com.liferay.osb.community.github.exception.
			NoSuchGitHubRepositoryException {

		return getPersistence().findByPrimaryKey(gitHubRepositoryId);
	}

	/**
	 * Returns the git hub repository with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gitHubRepositoryId the primary key of the git hub repository
	 * @return the git hub repository, or <code>null</code> if a git hub repository with the primary key could not be found
	 */
	public static GitHubRepository fetchByPrimaryKey(long gitHubRepositoryId) {
		return getPersistence().fetchByPrimaryKey(gitHubRepositoryId);
	}

	/**
	 * Returns all the git hub repositories.
	 *
	 * @return the git hub repositories
	 */
	public static List<GitHubRepository> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the git hub repositories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GitHubRepositoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of git hub repositories
	 * @param end the upper bound of the range of git hub repositories (not inclusive)
	 * @return the range of git hub repositories
	 */
	public static List<GitHubRepository> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the git hub repositories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GitHubRepositoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of git hub repositories
	 * @param end the upper bound of the range of git hub repositories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of git hub repositories
	 */
	public static List<GitHubRepository> findAll(
		int start, int end,
		OrderByComparator<GitHubRepository> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the git hub repositories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GitHubRepositoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of git hub repositories
	 * @param end the upper bound of the range of git hub repositories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of git hub repositories
	 */
	public static List<GitHubRepository> findAll(
		int start, int end,
		OrderByComparator<GitHubRepository> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the git hub repositories from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of git hub repositories.
	 *
	 * @return the number of git hub repositories
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static GitHubRepositoryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<GitHubRepositoryPersistence, GitHubRepositoryPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			GitHubRepositoryPersistence.class);

		ServiceTracker<GitHubRepositoryPersistence, GitHubRepositoryPersistence>
			serviceTracker =
				new ServiceTracker
					<GitHubRepositoryPersistence, GitHubRepositoryPersistence>(
						bundle.getBundleContext(),
						GitHubRepositoryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}