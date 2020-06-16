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

import com.liferay.osb.community.github.exception.NoSuchGitHubRepositoryException;
import com.liferay.osb.community.github.model.GitHubRepository;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the git hub repository service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Haote Chou
 * @see com.liferay.osb.community.github.service.persistence.impl.GitHubRepositoryPersistenceImpl
 * @see GitHubRepositoryUtil
 * @generated
 */
@ProviderType
public interface GitHubRepositoryPersistence extends BasePersistence<GitHubRepository> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GitHubRepositoryUtil} to access the git hub repository persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the git hub repository where owner = &#63; and name = &#63; or throws a {@link NoSuchGitHubRepositoryException} if it could not be found.
	*
	* @param owner the owner
	* @param name the name
	* @return the matching git hub repository
	* @throws NoSuchGitHubRepositoryException if a matching git hub repository could not be found
	*/
	public GitHubRepository findByO_N(java.lang.String owner,
		java.lang.String name) throws NoSuchGitHubRepositoryException;

	/**
	* Returns the git hub repository where owner = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param owner the owner
	* @param name the name
	* @return the matching git hub repository, or <code>null</code> if a matching git hub repository could not be found
	*/
	public GitHubRepository fetchByO_N(java.lang.String owner,
		java.lang.String name);

	/**
	* Returns the git hub repository where owner = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param owner the owner
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching git hub repository, or <code>null</code> if a matching git hub repository could not be found
	*/
	public GitHubRepository fetchByO_N(java.lang.String owner,
		java.lang.String name, boolean retrieveFromCache);

	/**
	* Removes the git hub repository where owner = &#63; and name = &#63; from the database.
	*
	* @param owner the owner
	* @param name the name
	* @return the git hub repository that was removed
	*/
	public GitHubRepository removeByO_N(java.lang.String owner,
		java.lang.String name) throws NoSuchGitHubRepositoryException;

	/**
	* Returns the number of git hub repositories where owner = &#63; and name = &#63;.
	*
	* @param owner the owner
	* @param name the name
	* @return the number of matching git hub repositories
	*/
	public int countByO_N(java.lang.String owner, java.lang.String name);

	/**
	* Caches the git hub repository in the entity cache if it is enabled.
	*
	* @param gitHubRepository the git hub repository
	*/
	public void cacheResult(GitHubRepository gitHubRepository);

	/**
	* Caches the git hub repositories in the entity cache if it is enabled.
	*
	* @param gitHubRepositories the git hub repositories
	*/
	public void cacheResult(java.util.List<GitHubRepository> gitHubRepositories);

	/**
	* Creates a new git hub repository with the primary key. Does not add the git hub repository to the database.
	*
	* @param gitHubRepositoryId the primary key for the new git hub repository
	* @return the new git hub repository
	*/
	public GitHubRepository create(long gitHubRepositoryId);

	/**
	* Removes the git hub repository with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gitHubRepositoryId the primary key of the git hub repository
	* @return the git hub repository that was removed
	* @throws NoSuchGitHubRepositoryException if a git hub repository with the primary key could not be found
	*/
	public GitHubRepository remove(long gitHubRepositoryId)
		throws NoSuchGitHubRepositoryException;

	public GitHubRepository updateImpl(GitHubRepository gitHubRepository);

	/**
	* Returns the git hub repository with the primary key or throws a {@link NoSuchGitHubRepositoryException} if it could not be found.
	*
	* @param gitHubRepositoryId the primary key of the git hub repository
	* @return the git hub repository
	* @throws NoSuchGitHubRepositoryException if a git hub repository with the primary key could not be found
	*/
	public GitHubRepository findByPrimaryKey(long gitHubRepositoryId)
		throws NoSuchGitHubRepositoryException;

	/**
	* Returns the git hub repository with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gitHubRepositoryId the primary key of the git hub repository
	* @return the git hub repository, or <code>null</code> if a git hub repository with the primary key could not be found
	*/
	public GitHubRepository fetchByPrimaryKey(long gitHubRepositoryId);

	@Override
	public java.util.Map<java.io.Serializable, GitHubRepository> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the git hub repositories.
	*
	* @return the git hub repositories
	*/
	public java.util.List<GitHubRepository> findAll();

	/**
	* Returns a range of all the git hub repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of git hub repositories
	* @param end the upper bound of the range of git hub repositories (not inclusive)
	* @return the range of git hub repositories
	*/
	public java.util.List<GitHubRepository> findAll(int start, int end);

	/**
	* Returns an ordered range of all the git hub repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of git hub repositories
	* @param end the upper bound of the range of git hub repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of git hub repositories
	*/
	public java.util.List<GitHubRepository> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GitHubRepository> orderByComparator);

	/**
	* Returns an ordered range of all the git hub repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GitHubRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of git hub repositories
	* @param end the upper bound of the range of git hub repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of git hub repositories
	*/
	public java.util.List<GitHubRepository> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GitHubRepository> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the git hub repositories from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of git hub repositories.
	*
	* @return the number of git hub repositories
	*/
	public int countAll();
}