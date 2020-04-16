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

package com.liferay.osb.community.github.service.persistence.impl;

import com.liferay.osb.community.github.exception.NoSuchGitHubContributorException;
import com.liferay.osb.community.github.model.GitHubContributor;
import com.liferay.osb.community.github.model.impl.GitHubContributorImpl;
import com.liferay.osb.community.github.model.impl.GitHubContributorModelImpl;
import com.liferay.osb.community.github.service.persistence.GitHubContributorPersistence;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the git hub contributor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Haote Chou
 * @generated
 */
public class GitHubContributorPersistenceImpl
	extends BasePersistenceImpl<GitHubContributor>
	implements GitHubContributorPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>GitHubContributorUtil</code> to access the git hub contributor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		GitHubContributorImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByGitHubRepositoryId;
	private FinderPath _finderPathWithoutPaginationFindByGitHubRepositoryId;
	private FinderPath _finderPathCountByGitHubRepositoryId;

	/**
	 * Returns all the git hub contributors where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @return the matching git hub contributors
	 */
	@Override
	public List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId) {

		return findByGitHubRepositoryId(
			gitHubRepositoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the git hub contributors where gitHubRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GitHubContributorModelImpl</code>.
	 * </p>
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @param start the lower bound of the range of git hub contributors
	 * @param end the upper bound of the range of git hub contributors (not inclusive)
	 * @return the range of matching git hub contributors
	 */
	@Override
	public List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId, int start, int end) {

		return findByGitHubRepositoryId(gitHubRepositoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the git hub contributors where gitHubRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GitHubContributorModelImpl</code>.
	 * </p>
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @param start the lower bound of the range of git hub contributors
	 * @param end the upper bound of the range of git hub contributors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching git hub contributors
	 */
	@Override
	public List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId, int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator) {

		return findByGitHubRepositoryId(
			gitHubRepositoryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the git hub contributors where gitHubRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GitHubContributorModelImpl</code>.
	 * </p>
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @param start the lower bound of the range of git hub contributors
	 * @param end the upper bound of the range of git hub contributors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching git hub contributors
	 */
	@Override
	public List<GitHubContributor> findByGitHubRepositoryId(
		long gitHubRepositoryId, int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByGitHubRepositoryId;
				finderArgs = new Object[] {gitHubRepositoryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGitHubRepositoryId;
			finderArgs = new Object[] {
				gitHubRepositoryId, start, end, orderByComparator
			};
		}

		List<GitHubContributor> list = null;

		if (useFinderCache) {
			list = (List<GitHubContributor>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GitHubContributor gitHubContributor : list) {
					if (gitHubRepositoryId !=
							gitHubContributor.getGitHubRepositoryId()) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_GITHUBCONTRIBUTOR_WHERE);

			sb.append(_FINDER_COLUMN_GITHUBREPOSITORYID_GITHUBREPOSITORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(GitHubContributorModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(gitHubRepositoryId);

				list = (List<GitHubContributor>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching git hub contributor
	 * @throws NoSuchGitHubContributorException if a matching git hub contributor could not be found
	 */
	@Override
	public GitHubContributor findByGitHubRepositoryId_First(
			long gitHubRepositoryId,
			OrderByComparator<GitHubContributor> orderByComparator)
		throws NoSuchGitHubContributorException {

		GitHubContributor gitHubContributor = fetchByGitHubRepositoryId_First(
			gitHubRepositoryId, orderByComparator);

		if (gitHubContributor != null) {
			return gitHubContributor;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("gitHubRepositoryId=");
		sb.append(gitHubRepositoryId);

		sb.append("}");

		throw new NoSuchGitHubContributorException(sb.toString());
	}

	/**
	 * Returns the first git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching git hub contributor, or <code>null</code> if a matching git hub contributor could not be found
	 */
	@Override
	public GitHubContributor fetchByGitHubRepositoryId_First(
		long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator) {

		List<GitHubContributor> list = findByGitHubRepositoryId(
			gitHubRepositoryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching git hub contributor
	 * @throws NoSuchGitHubContributorException if a matching git hub contributor could not be found
	 */
	@Override
	public GitHubContributor findByGitHubRepositoryId_Last(
			long gitHubRepositoryId,
			OrderByComparator<GitHubContributor> orderByComparator)
		throws NoSuchGitHubContributorException {

		GitHubContributor gitHubContributor = fetchByGitHubRepositoryId_Last(
			gitHubRepositoryId, orderByComparator);

		if (gitHubContributor != null) {
			return gitHubContributor;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("gitHubRepositoryId=");
		sb.append(gitHubRepositoryId);

		sb.append("}");

		throw new NoSuchGitHubContributorException(sb.toString());
	}

	/**
	 * Returns the last git hub contributor in the ordered set where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching git hub contributor, or <code>null</code> if a matching git hub contributor could not be found
	 */
	@Override
	public GitHubContributor fetchByGitHubRepositoryId_Last(
		long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator) {

		int count = countByGitHubRepositoryId(gitHubRepositoryId);

		if (count == 0) {
			return null;
		}

		List<GitHubContributor> list = findByGitHubRepositoryId(
			gitHubRepositoryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public GitHubContributor[] findByGitHubRepositoryId_PrevAndNext(
			long gitHubContributorId, long gitHubRepositoryId,
			OrderByComparator<GitHubContributor> orderByComparator)
		throws NoSuchGitHubContributorException {

		GitHubContributor gitHubContributor = findByPrimaryKey(
			gitHubContributorId);

		Session session = null;

		try {
			session = openSession();

			GitHubContributor[] array = new GitHubContributorImpl[3];

			array[0] = getByGitHubRepositoryId_PrevAndNext(
				session, gitHubContributor, gitHubRepositoryId,
				orderByComparator, true);

			array[1] = gitHubContributor;

			array[2] = getByGitHubRepositoryId_PrevAndNext(
				session, gitHubContributor, gitHubRepositoryId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected GitHubContributor getByGitHubRepositoryId_PrevAndNext(
		Session session, GitHubContributor gitHubContributor,
		long gitHubRepositoryId,
		OrderByComparator<GitHubContributor> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_GITHUBCONTRIBUTOR_WHERE);

		sb.append(_FINDER_COLUMN_GITHUBREPOSITORYID_GITHUBREPOSITORYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(GitHubContributorModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(gitHubRepositoryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						gitHubContributor)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<GitHubContributor> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the git hub contributors where gitHubRepositoryId = &#63; from the database.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 */
	@Override
	public void removeByGitHubRepositoryId(long gitHubRepositoryId) {
		for (GitHubContributor gitHubContributor :
				findByGitHubRepositoryId(
					gitHubRepositoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(gitHubContributor);
		}
	}

	/**
	 * Returns the number of git hub contributors where gitHubRepositoryId = &#63;.
	 *
	 * @param gitHubRepositoryId the git hub repository ID
	 * @return the number of matching git hub contributors
	 */
	@Override
	public int countByGitHubRepositoryId(long gitHubRepositoryId) {
		FinderPath finderPath = _finderPathCountByGitHubRepositoryId;

		Object[] finderArgs = new Object[] {gitHubRepositoryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_GITHUBCONTRIBUTOR_WHERE);

			sb.append(_FINDER_COLUMN_GITHUBREPOSITORYID_GITHUBREPOSITORYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(gitHubRepositoryId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_GITHUBREPOSITORYID_GITHUBREPOSITORYID_2 =
			"gitHubContributor.gitHubRepositoryId = ?";

	public GitHubContributorPersistenceImpl() {
		setModelClass(GitHubContributor.class);
	}

	/**
	 * Caches the git hub contributor in the entity cache if it is enabled.
	 *
	 * @param gitHubContributor the git hub contributor
	 */
	@Override
	public void cacheResult(GitHubContributor gitHubContributor) {
		entityCache.putResult(
			GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorImpl.class, gitHubContributor.getPrimaryKey(),
			gitHubContributor);

		gitHubContributor.resetOriginalValues();
	}

	/**
	 * Caches the git hub contributors in the entity cache if it is enabled.
	 *
	 * @param gitHubContributors the git hub contributors
	 */
	@Override
	public void cacheResult(List<GitHubContributor> gitHubContributors) {
		for (GitHubContributor gitHubContributor : gitHubContributors) {
			if (entityCache.getResult(
					GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
					GitHubContributorImpl.class,
					gitHubContributor.getPrimaryKey()) == null) {

				cacheResult(gitHubContributor);
			}
			else {
				gitHubContributor.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all git hub contributors.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GitHubContributorImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the git hub contributor.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GitHubContributor gitHubContributor) {
		entityCache.removeResult(
			GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorImpl.class, gitHubContributor.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<GitHubContributor> gitHubContributors) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GitHubContributor gitHubContributor : gitHubContributors) {
			entityCache.removeResult(
				GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
				GitHubContributorImpl.class, gitHubContributor.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
				GitHubContributorImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new git hub contributor with the primary key. Does not add the git hub contributor to the database.
	 *
	 * @param gitHubContributorId the primary key for the new git hub contributor
	 * @return the new git hub contributor
	 */
	@Override
	public GitHubContributor create(long gitHubContributorId) {
		GitHubContributor gitHubContributor = new GitHubContributorImpl();

		gitHubContributor.setNew(true);
		gitHubContributor.setPrimaryKey(gitHubContributorId);

		gitHubContributor.setCompanyId(CompanyThreadLocal.getCompanyId());

		return gitHubContributor;
	}

	/**
	 * Removes the git hub contributor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gitHubContributorId the primary key of the git hub contributor
	 * @return the git hub contributor that was removed
	 * @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor remove(long gitHubContributorId)
		throws NoSuchGitHubContributorException {

		return remove((Serializable)gitHubContributorId);
	}

	/**
	 * Removes the git hub contributor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the git hub contributor
	 * @return the git hub contributor that was removed
	 * @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor remove(Serializable primaryKey)
		throws NoSuchGitHubContributorException {

		Session session = null;

		try {
			session = openSession();

			GitHubContributor gitHubContributor =
				(GitHubContributor)session.get(
					GitHubContributorImpl.class, primaryKey);

			if (gitHubContributor == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGitHubContributorException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(gitHubContributor);
		}
		catch (NoSuchGitHubContributorException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected GitHubContributor removeImpl(
		GitHubContributor gitHubContributor) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(gitHubContributor)) {
				gitHubContributor = (GitHubContributor)session.get(
					GitHubContributorImpl.class,
					gitHubContributor.getPrimaryKeyObj());
			}

			if (gitHubContributor != null) {
				session.delete(gitHubContributor);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (gitHubContributor != null) {
			clearCache(gitHubContributor);
		}

		return gitHubContributor;
	}

	@Override
	public GitHubContributor updateImpl(GitHubContributor gitHubContributor) {
		boolean isNew = gitHubContributor.isNew();

		if (!(gitHubContributor instanceof GitHubContributorModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(gitHubContributor.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					gitHubContributor);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in gitHubContributor proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom GitHubContributor implementation " +
					gitHubContributor.getClass());
		}

		GitHubContributorModelImpl gitHubContributorModelImpl =
			(GitHubContributorModelImpl)gitHubContributor;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (gitHubContributor.getCreateDate() == null)) {
			if (serviceContext == null) {
				gitHubContributor.setCreateDate(now);
			}
			else {
				gitHubContributor.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!gitHubContributorModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				gitHubContributor.setModifiedDate(now);
			}
			else {
				gitHubContributor.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (gitHubContributor.isNew()) {
				session.save(gitHubContributor);

				gitHubContributor.setNew(false);
			}
			else {
				gitHubContributor = (GitHubContributor)session.merge(
					gitHubContributor);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!GitHubContributorModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				gitHubContributorModelImpl.getGitHubRepositoryId()
			};

			finderCache.removeResult(
				_finderPathCountByGitHubRepositoryId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGitHubRepositoryId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((gitHubContributorModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGitHubRepositoryId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					gitHubContributorModelImpl.getOriginalGitHubRepositoryId()
				};

				finderCache.removeResult(
					_finderPathCountByGitHubRepositoryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGitHubRepositoryId, args);

				args = new Object[] {
					gitHubContributorModelImpl.getGitHubRepositoryId()
				};

				finderCache.removeResult(
					_finderPathCountByGitHubRepositoryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGitHubRepositoryId, args);
			}
		}

		entityCache.putResult(
			GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorImpl.class, gitHubContributor.getPrimaryKey(),
			gitHubContributor, false);

		gitHubContributor.resetOriginalValues();

		return gitHubContributor;
	}

	/**
	 * Returns the git hub contributor with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the git hub contributor
	 * @return the git hub contributor
	 * @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGitHubContributorException {

		GitHubContributor gitHubContributor = fetchByPrimaryKey(primaryKey);

		if (gitHubContributor == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGitHubContributorException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return gitHubContributor;
	}

	/**
	 * Returns the git hub contributor with the primary key or throws a <code>NoSuchGitHubContributorException</code> if it could not be found.
	 *
	 * @param gitHubContributorId the primary key of the git hub contributor
	 * @return the git hub contributor
	 * @throws NoSuchGitHubContributorException if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor findByPrimaryKey(long gitHubContributorId)
		throws NoSuchGitHubContributorException {

		return findByPrimaryKey((Serializable)gitHubContributorId);
	}

	/**
	 * Returns the git hub contributor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the git hub contributor
	 * @return the git hub contributor, or <code>null</code> if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		GitHubContributor gitHubContributor = (GitHubContributor)serializable;

		if (gitHubContributor == null) {
			Session session = null;

			try {
				session = openSession();

				gitHubContributor = (GitHubContributor)session.get(
					GitHubContributorImpl.class, primaryKey);

				if (gitHubContributor != null) {
					cacheResult(gitHubContributor);
				}
				else {
					entityCache.putResult(
						GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
						GitHubContributorImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
					GitHubContributorImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return gitHubContributor;
	}

	/**
	 * Returns the git hub contributor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gitHubContributorId the primary key of the git hub contributor
	 * @return the git hub contributor, or <code>null</code> if a git hub contributor with the primary key could not be found
	 */
	@Override
	public GitHubContributor fetchByPrimaryKey(long gitHubContributorId) {
		return fetchByPrimaryKey((Serializable)gitHubContributorId);
	}

	@Override
	public Map<Serializable, GitHubContributor> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, GitHubContributor> map =
			new HashMap<Serializable, GitHubContributor>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			GitHubContributor gitHubContributor = fetchByPrimaryKey(primaryKey);

			if (gitHubContributor != null) {
				map.put(primaryKey, gitHubContributor);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
				GitHubContributorImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (GitHubContributor)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_GITHUBCONTRIBUTOR_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (GitHubContributor gitHubContributor :
					(List<GitHubContributor>)query.list()) {

				map.put(
					gitHubContributor.getPrimaryKeyObj(), gitHubContributor);

				cacheResult(gitHubContributor);

				uncachedPrimaryKeys.remove(
					gitHubContributor.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
					GitHubContributorImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the git hub contributors.
	 *
	 * @return the git hub contributors
	 */
	@Override
	public List<GitHubContributor> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the git hub contributors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GitHubContributorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of git hub contributors
	 * @param end the upper bound of the range of git hub contributors (not inclusive)
	 * @return the range of git hub contributors
	 */
	@Override
	public List<GitHubContributor> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the git hub contributors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GitHubContributorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of git hub contributors
	 * @param end the upper bound of the range of git hub contributors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of git hub contributors
	 */
	@Override
	public List<GitHubContributor> findAll(
		int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the git hub contributors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GitHubContributorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of git hub contributors
	 * @param end the upper bound of the range of git hub contributors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of git hub contributors
	 */
	@Override
	public List<GitHubContributor> findAll(
		int start, int end,
		OrderByComparator<GitHubContributor> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<GitHubContributor> list = null;

		if (useFinderCache) {
			list = (List<GitHubContributor>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_GITHUBCONTRIBUTOR);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_GITHUBCONTRIBUTOR;

				sql = sql.concat(GitHubContributorModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<GitHubContributor>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the git hub contributors from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GitHubContributor gitHubContributor : findAll()) {
			remove(gitHubContributor);
		}
	}

	/**
	 * Returns the number of git hub contributors.
	 *
	 * @return the number of git hub contributors
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_GITHUBCONTRIBUTOR);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return GitHubContributorModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the git hub contributor persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorModelImpl.FINDER_CACHE_ENABLED,
			GitHubContributorImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorModelImpl.FINDER_CACHE_ENABLED,
			GitHubContributorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByGitHubRepositoryId = new FinderPath(
			GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorModelImpl.FINDER_CACHE_ENABLED,
			GitHubContributorImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGitHubRepositoryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGitHubRepositoryId = new FinderPath(
			GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorModelImpl.FINDER_CACHE_ENABLED,
			GitHubContributorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGitHubRepositoryId", new String[] {Long.class.getName()},
			GitHubContributorModelImpl.GITHUBREPOSITORYID_COLUMN_BITMASK |
			GitHubContributorModelImpl.CONTRIBUTIONS_COLUMN_BITMASK);

		_finderPathCountByGitHubRepositoryId = new FinderPath(
			GitHubContributorModelImpl.ENTITY_CACHE_ENABLED,
			GitHubContributorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGitHubRepositoryId", new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(GitHubContributorImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_GITHUBCONTRIBUTOR =
		"SELECT gitHubContributor FROM GitHubContributor gitHubContributor";

	private static final String _SQL_SELECT_GITHUBCONTRIBUTOR_WHERE_PKS_IN =
		"SELECT gitHubContributor FROM GitHubContributor gitHubContributor WHERE gitHubContributorId IN (";

	private static final String _SQL_SELECT_GITHUBCONTRIBUTOR_WHERE =
		"SELECT gitHubContributor FROM GitHubContributor gitHubContributor WHERE ";

	private static final String _SQL_COUNT_GITHUBCONTRIBUTOR =
		"SELECT COUNT(gitHubContributor) FROM GitHubContributor gitHubContributor";

	private static final String _SQL_COUNT_GITHUBCONTRIBUTOR_WHERE =
		"SELECT COUNT(gitHubContributor) FROM GitHubContributor gitHubContributor WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "gitHubContributor.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No GitHubContributor exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No GitHubContributor exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		GitHubContributorPersistenceImpl.class);

}