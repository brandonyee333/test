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

package com.liferay.osb.community.github.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.github.exception.NoSuchGitHubRepositoryException;
import com.liferay.osb.community.github.model.GitHubRepository;
import com.liferay.osb.community.github.model.impl.GitHubRepositoryImpl;
import com.liferay.osb.community.github.model.impl.GitHubRepositoryModelImpl;
import com.liferay.osb.community.github.service.persistence.GitHubRepositoryPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the git hub repository service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Haote Chou
 * @see GitHubRepositoryPersistence
 * @see com.liferay.osb.community.github.service.persistence.GitHubRepositoryUtil
 * @generated
 */
@ProviderType
public class GitHubRepositoryPersistenceImpl extends BasePersistenceImpl<GitHubRepository>
	implements GitHubRepositoryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GitHubRepositoryUtil} to access the git hub repository persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GitHubRepositoryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			GitHubRepositoryModelImpl.FINDER_CACHE_ENABLED,
			GitHubRepositoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			GitHubRepositoryModelImpl.FINDER_CACHE_ENABLED,
			GitHubRepositoryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			GitHubRepositoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_O_N = new FinderPath(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			GitHubRepositoryModelImpl.FINDER_CACHE_ENABLED,
			GitHubRepositoryImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByO_N",
			new String[] { String.class.getName(), String.class.getName() },
			GitHubRepositoryModelImpl.OWNER_COLUMN_BITMASK |
			GitHubRepositoryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_O_N = new FinderPath(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			GitHubRepositoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByO_N",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns the git hub repository where owner = &#63; and name = &#63; or throws a {@link NoSuchGitHubRepositoryException} if it could not be found.
	 *
	 * @param owner the owner
	 * @param name the name
	 * @return the matching git hub repository
	 * @throws NoSuchGitHubRepositoryException if a matching git hub repository could not be found
	 */
	@Override
	public GitHubRepository findByO_N(String owner, String name)
		throws NoSuchGitHubRepositoryException {
		GitHubRepository gitHubRepository = fetchByO_N(owner, name);

		if (gitHubRepository == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("owner=");
			msg.append(owner);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchGitHubRepositoryException(msg.toString());
		}

		return gitHubRepository;
	}

	/**
	 * Returns the git hub repository where owner = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param owner the owner
	 * @param name the name
	 * @return the matching git hub repository, or <code>null</code> if a matching git hub repository could not be found
	 */
	@Override
	public GitHubRepository fetchByO_N(String owner, String name) {
		return fetchByO_N(owner, name, true);
	}

	/**
	 * Returns the git hub repository where owner = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param owner the owner
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching git hub repository, or <code>null</code> if a matching git hub repository could not be found
	 */
	@Override
	public GitHubRepository fetchByO_N(String owner, String name,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { owner, name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_O_N,
					finderArgs, this);
		}

		if (result instanceof GitHubRepository) {
			GitHubRepository gitHubRepository = (GitHubRepository)result;

			if (!Objects.equals(owner, gitHubRepository.getOwner()) ||
					!Objects.equals(name, gitHubRepository.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_GITHUBREPOSITORY_WHERE);

			boolean bindOwner = false;

			if (owner == null) {
				query.append(_FINDER_COLUMN_O_N_OWNER_1);
			}
			else if (owner.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_O_N_OWNER_3);
			}
			else {
				bindOwner = true;

				query.append(_FINDER_COLUMN_O_N_OWNER_2);
			}

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_O_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_O_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_O_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOwner) {
					qPos.add(owner);
				}

				if (bindName) {
					qPos.add(name);
				}

				List<GitHubRepository> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_O_N, finderArgs,
						list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"GitHubRepositoryPersistenceImpl.fetchByO_N(String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					GitHubRepository gitHubRepository = list.get(0);

					result = gitHubRepository;

					cacheResult(gitHubRepository);

					if ((gitHubRepository.getOwner() == null) ||
							!gitHubRepository.getOwner().equals(owner) ||
							(gitHubRepository.getName() == null) ||
							!gitHubRepository.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_O_N,
							finderArgs, gitHubRepository);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_O_N, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (GitHubRepository)result;
		}
	}

	/**
	 * Removes the git hub repository where owner = &#63; and name = &#63; from the database.
	 *
	 * @param owner the owner
	 * @param name the name
	 * @return the git hub repository that was removed
	 */
	@Override
	public GitHubRepository removeByO_N(String owner, String name)
		throws NoSuchGitHubRepositoryException {
		GitHubRepository gitHubRepository = findByO_N(owner, name);

		return remove(gitHubRepository);
	}

	/**
	 * Returns the number of git hub repositories where owner = &#63; and name = &#63;.
	 *
	 * @param owner the owner
	 * @param name the name
	 * @return the number of matching git hub repositories
	 */
	@Override
	public int countByO_N(String owner, String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_O_N;

		Object[] finderArgs = new Object[] { owner, name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_GITHUBREPOSITORY_WHERE);

			boolean bindOwner = false;

			if (owner == null) {
				query.append(_FINDER_COLUMN_O_N_OWNER_1);
			}
			else if (owner.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_O_N_OWNER_3);
			}
			else {
				bindOwner = true;

				query.append(_FINDER_COLUMN_O_N_OWNER_2);
			}

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_O_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_O_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_O_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOwner) {
					qPos.add(owner);
				}

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_O_N_OWNER_1 = "gitHubRepository.owner IS NULL AND ";
	private static final String _FINDER_COLUMN_O_N_OWNER_2 = "gitHubRepository.owner = ? AND ";
	private static final String _FINDER_COLUMN_O_N_OWNER_3 = "(gitHubRepository.owner IS NULL OR gitHubRepository.owner = '') AND ";
	private static final String _FINDER_COLUMN_O_N_NAME_1 = "gitHubRepository.name IS NULL";
	private static final String _FINDER_COLUMN_O_N_NAME_2 = "gitHubRepository.name = ?";
	private static final String _FINDER_COLUMN_O_N_NAME_3 = "(gitHubRepository.name IS NULL OR gitHubRepository.name = '')";

	public GitHubRepositoryPersistenceImpl() {
		setModelClass(GitHubRepository.class);
	}

	/**
	 * Caches the git hub repository in the entity cache if it is enabled.
	 *
	 * @param gitHubRepository the git hub repository
	 */
	@Override
	public void cacheResult(GitHubRepository gitHubRepository) {
		entityCache.putResult(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			GitHubRepositoryImpl.class, gitHubRepository.getPrimaryKey(),
			gitHubRepository);

		finderCache.putResult(FINDER_PATH_FETCH_BY_O_N,
			new Object[] { gitHubRepository.getOwner(), gitHubRepository.getName() },
			gitHubRepository);

		gitHubRepository.resetOriginalValues();
	}

	/**
	 * Caches the git hub repositories in the entity cache if it is enabled.
	 *
	 * @param gitHubRepositories the git hub repositories
	 */
	@Override
	public void cacheResult(List<GitHubRepository> gitHubRepositories) {
		for (GitHubRepository gitHubRepository : gitHubRepositories) {
			if (entityCache.getResult(
						GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
						GitHubRepositoryImpl.class,
						gitHubRepository.getPrimaryKey()) == null) {
				cacheResult(gitHubRepository);
			}
			else {
				gitHubRepository.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all git hub repositories.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GitHubRepositoryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the git hub repository.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GitHubRepository gitHubRepository) {
		entityCache.removeResult(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			GitHubRepositoryImpl.class, gitHubRepository.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((GitHubRepositoryModelImpl)gitHubRepository,
			true);
	}

	@Override
	public void clearCache(List<GitHubRepository> gitHubRepositories) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GitHubRepository gitHubRepository : gitHubRepositories) {
			entityCache.removeResult(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
				GitHubRepositoryImpl.class, gitHubRepository.getPrimaryKey());

			clearUniqueFindersCache((GitHubRepositoryModelImpl)gitHubRepository,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		GitHubRepositoryModelImpl gitHubRepositoryModelImpl) {
		Object[] args = new Object[] {
				gitHubRepositoryModelImpl.getOwner(),
				gitHubRepositoryModelImpl.getName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_O_N, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_O_N, args,
			gitHubRepositoryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		GitHubRepositoryModelImpl gitHubRepositoryModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					gitHubRepositoryModelImpl.getOwner(),
					gitHubRepositoryModelImpl.getName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_O_N, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_O_N, args);
		}

		if ((gitHubRepositoryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_O_N.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					gitHubRepositoryModelImpl.getOriginalOwner(),
					gitHubRepositoryModelImpl.getOriginalName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_O_N, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_O_N, args);
		}
	}

	/**
	 * Creates a new git hub repository with the primary key. Does not add the git hub repository to the database.
	 *
	 * @param gitHubRepositoryId the primary key for the new git hub repository
	 * @return the new git hub repository
	 */
	@Override
	public GitHubRepository create(long gitHubRepositoryId) {
		GitHubRepository gitHubRepository = new GitHubRepositoryImpl();

		gitHubRepository.setNew(true);
		gitHubRepository.setPrimaryKey(gitHubRepositoryId);

		gitHubRepository.setCompanyId(companyProvider.getCompanyId());

		return gitHubRepository;
	}

	/**
	 * Removes the git hub repository with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gitHubRepositoryId the primary key of the git hub repository
	 * @return the git hub repository that was removed
	 * @throws NoSuchGitHubRepositoryException if a git hub repository with the primary key could not be found
	 */
	@Override
	public GitHubRepository remove(long gitHubRepositoryId)
		throws NoSuchGitHubRepositoryException {
		return remove((Serializable)gitHubRepositoryId);
	}

	/**
	 * Removes the git hub repository with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the git hub repository
	 * @return the git hub repository that was removed
	 * @throws NoSuchGitHubRepositoryException if a git hub repository with the primary key could not be found
	 */
	@Override
	public GitHubRepository remove(Serializable primaryKey)
		throws NoSuchGitHubRepositoryException {
		Session session = null;

		try {
			session = openSession();

			GitHubRepository gitHubRepository = (GitHubRepository)session.get(GitHubRepositoryImpl.class,
					primaryKey);

			if (gitHubRepository == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGitHubRepositoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(gitHubRepository);
		}
		catch (NoSuchGitHubRepositoryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected GitHubRepository removeImpl(GitHubRepository gitHubRepository) {
		gitHubRepository = toUnwrappedModel(gitHubRepository);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(gitHubRepository)) {
				gitHubRepository = (GitHubRepository)session.get(GitHubRepositoryImpl.class,
						gitHubRepository.getPrimaryKeyObj());
			}

			if (gitHubRepository != null) {
				session.delete(gitHubRepository);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (gitHubRepository != null) {
			clearCache(gitHubRepository);
		}

		return gitHubRepository;
	}

	@Override
	public GitHubRepository updateImpl(GitHubRepository gitHubRepository) {
		gitHubRepository = toUnwrappedModel(gitHubRepository);

		boolean isNew = gitHubRepository.isNew();

		GitHubRepositoryModelImpl gitHubRepositoryModelImpl = (GitHubRepositoryModelImpl)gitHubRepository;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (gitHubRepository.getCreateDate() == null)) {
			if (serviceContext == null) {
				gitHubRepository.setCreateDate(now);
			}
			else {
				gitHubRepository.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!gitHubRepositoryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				gitHubRepository.setModifiedDate(now);
			}
			else {
				gitHubRepository.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (gitHubRepository.isNew()) {
				session.save(gitHubRepository);

				gitHubRepository.setNew(false);
			}
			else {
				gitHubRepository = (GitHubRepository)session.merge(gitHubRepository);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!GitHubRepositoryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			GitHubRepositoryImpl.class, gitHubRepository.getPrimaryKey(),
			gitHubRepository, false);

		clearUniqueFindersCache(gitHubRepositoryModelImpl, false);
		cacheUniqueFindersCache(gitHubRepositoryModelImpl);

		gitHubRepository.resetOriginalValues();

		return gitHubRepository;
	}

	protected GitHubRepository toUnwrappedModel(
		GitHubRepository gitHubRepository) {
		if (gitHubRepository instanceof GitHubRepositoryImpl) {
			return gitHubRepository;
		}

		GitHubRepositoryImpl gitHubRepositoryImpl = new GitHubRepositoryImpl();

		gitHubRepositoryImpl.setNew(gitHubRepository.isNew());
		gitHubRepositoryImpl.setPrimaryKey(gitHubRepository.getPrimaryKey());

		gitHubRepositoryImpl.setGitHubRepositoryId(gitHubRepository.getGitHubRepositoryId());
		gitHubRepositoryImpl.setCompanyId(gitHubRepository.getCompanyId());
		gitHubRepositoryImpl.setUserId(gitHubRepository.getUserId());
		gitHubRepositoryImpl.setUserName(gitHubRepository.getUserName());
		gitHubRepositoryImpl.setCreateDate(gitHubRepository.getCreateDate());
		gitHubRepositoryImpl.setModifiedDate(gitHubRepository.getModifiedDate());
		gitHubRepositoryImpl.setOwner(gitHubRepository.getOwner());
		gitHubRepositoryImpl.setName(gitHubRepository.getName());
		gitHubRepositoryImpl.setCommits(gitHubRepository.getCommits());
		gitHubRepositoryImpl.setOpenIssues(gitHubRepository.getOpenIssues());
		gitHubRepositoryImpl.setStars(gitHubRepository.getStars());
		gitHubRepositoryImpl.setUrl(gitHubRepository.getUrl());
		gitHubRepositoryImpl.setRepositoryCreateDate(gitHubRepository.getRepositoryCreateDate());

		return gitHubRepositoryImpl;
	}

	/**
	 * Returns the git hub repository with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the git hub repository
	 * @return the git hub repository
	 * @throws NoSuchGitHubRepositoryException if a git hub repository with the primary key could not be found
	 */
	@Override
	public GitHubRepository findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGitHubRepositoryException {
		GitHubRepository gitHubRepository = fetchByPrimaryKey(primaryKey);

		if (gitHubRepository == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGitHubRepositoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return gitHubRepository;
	}

	/**
	 * Returns the git hub repository with the primary key or throws a {@link NoSuchGitHubRepositoryException} if it could not be found.
	 *
	 * @param gitHubRepositoryId the primary key of the git hub repository
	 * @return the git hub repository
	 * @throws NoSuchGitHubRepositoryException if a git hub repository with the primary key could not be found
	 */
	@Override
	public GitHubRepository findByPrimaryKey(long gitHubRepositoryId)
		throws NoSuchGitHubRepositoryException {
		return findByPrimaryKey((Serializable)gitHubRepositoryId);
	}

	/**
	 * Returns the git hub repository with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the git hub repository
	 * @return the git hub repository, or <code>null</code> if a git hub repository with the primary key could not be found
	 */
	@Override
	public GitHubRepository fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
				GitHubRepositoryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		GitHubRepository gitHubRepository = (GitHubRepository)serializable;

		if (gitHubRepository == null) {
			Session session = null;

			try {
				session = openSession();

				gitHubRepository = (GitHubRepository)session.get(GitHubRepositoryImpl.class,
						primaryKey);

				if (gitHubRepository != null) {
					cacheResult(gitHubRepository);
				}
				else {
					entityCache.putResult(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
						GitHubRepositoryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
					GitHubRepositoryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return gitHubRepository;
	}

	/**
	 * Returns the git hub repository with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gitHubRepositoryId the primary key of the git hub repository
	 * @return the git hub repository, or <code>null</code> if a git hub repository with the primary key could not be found
	 */
	@Override
	public GitHubRepository fetchByPrimaryKey(long gitHubRepositoryId) {
		return fetchByPrimaryKey((Serializable)gitHubRepositoryId);
	}

	@Override
	public Map<Serializable, GitHubRepository> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, GitHubRepository> map = new HashMap<Serializable, GitHubRepository>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			GitHubRepository gitHubRepository = fetchByPrimaryKey(primaryKey);

			if (gitHubRepository != null) {
				map.put(primaryKey, gitHubRepository);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
					GitHubRepositoryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (GitHubRepository)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_GITHUBREPOSITORY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (GitHubRepository gitHubRepository : (List<GitHubRepository>)q.list()) {
				map.put(gitHubRepository.getPrimaryKeyObj(), gitHubRepository);

				cacheResult(gitHubRepository);

				uncachedPrimaryKeys.remove(gitHubRepository.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(GitHubRepositoryModelImpl.ENTITY_CACHE_ENABLED,
					GitHubRepositoryImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the git hub repositories.
	 *
	 * @return the git hub repositories
	 */
	@Override
	public List<GitHubRepository> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<GitHubRepository> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<GitHubRepository> findAll(int start, int end,
		OrderByComparator<GitHubRepository> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<GitHubRepository> findAll(int start, int end,
		OrderByComparator<GitHubRepository> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<GitHubRepository> list = null;

		if (retrieveFromCache) {
			list = (List<GitHubRepository>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_GITHUBREPOSITORY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GITHUBREPOSITORY;

				if (pagination) {
					sql = sql.concat(GitHubRepositoryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<GitHubRepository>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<GitHubRepository>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the git hub repositories from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GitHubRepository gitHubRepository : findAll()) {
			remove(gitHubRepository);
		}
	}

	/**
	 * Returns the number of git hub repositories.
	 *
	 * @return the number of git hub repositories
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GITHUBREPOSITORY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return GitHubRepositoryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the git hub repository persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(GitHubRepositoryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_GITHUBREPOSITORY = "SELECT gitHubRepository FROM GitHubRepository gitHubRepository";
	private static final String _SQL_SELECT_GITHUBREPOSITORY_WHERE_PKS_IN = "SELECT gitHubRepository FROM GitHubRepository gitHubRepository WHERE gitHubRepositoryId IN (";
	private static final String _SQL_SELECT_GITHUBREPOSITORY_WHERE = "SELECT gitHubRepository FROM GitHubRepository gitHubRepository WHERE ";
	private static final String _SQL_COUNT_GITHUBREPOSITORY = "SELECT COUNT(gitHubRepository) FROM GitHubRepository gitHubRepository";
	private static final String _SQL_COUNT_GITHUBREPOSITORY_WHERE = "SELECT COUNT(gitHubRepository) FROM GitHubRepository gitHubRepository WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "gitHubRepository.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GitHubRepository exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No GitHubRepository exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(GitHubRepositoryPersistenceImpl.class);
}