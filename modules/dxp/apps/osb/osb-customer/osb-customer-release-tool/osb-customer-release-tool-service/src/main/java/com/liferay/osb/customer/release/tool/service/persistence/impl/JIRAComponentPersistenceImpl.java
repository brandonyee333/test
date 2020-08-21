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

package com.liferay.osb.customer.release.tool.service.persistence.impl;

import com.liferay.osb.customer.release.tool.exception.NoSuchJIRAComponentException;
import com.liferay.osb.customer.release.tool.model.JIRAComponent;
import com.liferay.osb.customer.release.tool.model.impl.JIRAComponentImpl;
import com.liferay.osb.customer.release.tool.model.impl.JIRAComponentModelImpl;
import com.liferay.osb.customer.release.tool.service.persistence.JIRAComponentPersistence;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the jira component service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAComponentPersistenceImpl
	extends BasePersistenceImpl<JIRAComponent>
	implements JIRAComponentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>JIRAComponentUtil</code> to access the jira component persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		JIRAComponentImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByRemoteId;
	private FinderPath _finderPathCountByRemoteId;

	/**
	 * Returns the jira component where remoteId = &#63; or throws a <code>NoSuchJIRAComponentException</code> if it could not be found.
	 *
	 * @param remoteId the remote ID
	 * @return the matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent findByRemoteId(long remoteId)
		throws NoSuchJIRAComponentException {

		JIRAComponent jiraComponent = fetchByRemoteId(remoteId);

		if (jiraComponent == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("remoteId=");
			sb.append(remoteId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchJIRAComponentException(sb.toString());
		}

		return jiraComponent;
	}

	/**
	 * Returns the jira component where remoteId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param remoteId the remote ID
	 * @return the matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent fetchByRemoteId(long remoteId) {
		return fetchByRemoteId(remoteId, true);
	}

	/**
	 * Returns the jira component where remoteId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param remoteId the remote ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent fetchByRemoteId(
		long remoteId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {remoteId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByRemoteId, finderArgs, this);
		}

		if (result instanceof JIRAComponent) {
			JIRAComponent jiraComponent = (JIRAComponent)result;

			if (remoteId != jiraComponent.getRemoteId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_JIRACOMPONENT_WHERE);

			sb.append(_FINDER_COLUMN_REMOTEID_REMOTEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(remoteId);

				List<JIRAComponent> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByRemoteId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {remoteId};
							}

							_log.warn(
								"JIRAComponentPersistenceImpl.fetchByRemoteId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					JIRAComponent jiraComponent = list.get(0);

					result = jiraComponent;

					cacheResult(jiraComponent);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByRemoteId, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (JIRAComponent)result;
		}
	}

	/**
	 * Removes the jira component where remoteId = &#63; from the database.
	 *
	 * @param remoteId the remote ID
	 * @return the jira component that was removed
	 */
	@Override
	public JIRAComponent removeByRemoteId(long remoteId)
		throws NoSuchJIRAComponentException {

		JIRAComponent jiraComponent = findByRemoteId(remoteId);

		return remove(jiraComponent);
	}

	/**
	 * Returns the number of jira components where remoteId = &#63;.
	 *
	 * @param remoteId the remote ID
	 * @return the number of matching jira components
	 */
	@Override
	public int countByRemoteId(long remoteId) {
		FinderPath finderPath = _finderPathCountByRemoteId;

		Object[] finderArgs = new Object[] {remoteId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_JIRACOMPONENT_WHERE);

			sb.append(_FINDER_COLUMN_REMOTEID_REMOTEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(remoteId);

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

	private static final String _FINDER_COLUMN_REMOTEID_REMOTEID_2 =
		"jiraComponent.remoteId = ?";

	private FinderPath _finderPathWithPaginationFindByVisible;
	private FinderPath _finderPathWithoutPaginationFindByVisible;
	private FinderPath _finderPathCountByVisible;

	/**
	 * Returns all the jira components where visible = &#63;.
	 *
	 * @param visible the visible
	 * @return the matching jira components
	 */
	@Override
	public List<JIRAComponent> findByVisible(boolean visible) {
		return findByVisible(
			visible, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jira components where visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>.
	 * </p>
	 *
	 * @param visible the visible
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @return the range of matching jira components
	 */
	@Override
	public List<JIRAComponent> findByVisible(
		boolean visible, int start, int end) {

		return findByVisible(visible, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jira components where visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>.
	 * </p>
	 *
	 * @param visible the visible
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jira components
	 */
	@Override
	public List<JIRAComponent> findByVisible(
		boolean visible, int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator) {

		return findByVisible(visible, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jira components where visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>.
	 * </p>
	 *
	 * @param visible the visible
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching jira components
	 */
	@Override
	public List<JIRAComponent> findByVisible(
		boolean visible, int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByVisible;
				finderArgs = new Object[] {visible};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByVisible;
			finderArgs = new Object[] {visible, start, end, orderByComparator};
		}

		List<JIRAComponent> list = null;

		if (useFinderCache) {
			list = (List<JIRAComponent>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (JIRAComponent jiraComponent : list) {
					if (visible != jiraComponent.isVisible()) {
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

			sb.append(_SQL_SELECT_JIRACOMPONENT_WHERE);

			sb.append(_FINDER_COLUMN_VISIBLE_VISIBLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(JIRAComponentModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(visible);

				list = (List<JIRAComponent>)QueryUtil.list(
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
	 * Returns the first jira component in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent findByVisible_First(
			boolean visible, OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException {

		JIRAComponent jiraComponent = fetchByVisible_First(
			visible, orderByComparator);

		if (jiraComponent != null) {
			return jiraComponent;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("visible=");
		sb.append(visible);

		sb.append("}");

		throw new NoSuchJIRAComponentException(sb.toString());
	}

	/**
	 * Returns the first jira component in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent fetchByVisible_First(
		boolean visible, OrderByComparator<JIRAComponent> orderByComparator) {

		List<JIRAComponent> list = findByVisible(
			visible, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last jira component in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent findByVisible_Last(
			boolean visible, OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException {

		JIRAComponent jiraComponent = fetchByVisible_Last(
			visible, orderByComparator);

		if (jiraComponent != null) {
			return jiraComponent;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("visible=");
		sb.append(visible);

		sb.append("}");

		throw new NoSuchJIRAComponentException(sb.toString());
	}

	/**
	 * Returns the last jira component in the ordered set where visible = &#63;.
	 *
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent fetchByVisible_Last(
		boolean visible, OrderByComparator<JIRAComponent> orderByComparator) {

		int count = countByVisible(visible);

		if (count == 0) {
			return null;
		}

		List<JIRAComponent> list = findByVisible(
			visible, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jira components before and after the current jira component in the ordered set where visible = &#63;.
	 *
	 * @param jiraComponentId the primary key of the current jira component
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next jira component
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent[] findByVisible_PrevAndNext(
			long jiraComponentId, boolean visible,
			OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException {

		JIRAComponent jiraComponent = findByPrimaryKey(jiraComponentId);

		Session session = null;

		try {
			session = openSession();

			JIRAComponent[] array = new JIRAComponentImpl[3];

			array[0] = getByVisible_PrevAndNext(
				session, jiraComponent, visible, orderByComparator, true);

			array[1] = jiraComponent;

			array[2] = getByVisible_PrevAndNext(
				session, jiraComponent, visible, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAComponent getByVisible_PrevAndNext(
		Session session, JIRAComponent jiraComponent, boolean visible,
		OrderByComparator<JIRAComponent> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_JIRACOMPONENT_WHERE);

		sb.append(_FINDER_COLUMN_VISIBLE_VISIBLE_2);

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
			sb.append(JIRAComponentModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(visible);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						jiraComponent)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<JIRAComponent> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jira components where visible = &#63; from the database.
	 *
	 * @param visible the visible
	 */
	@Override
	public void removeByVisible(boolean visible) {
		for (JIRAComponent jiraComponent :
				findByVisible(
					visible, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(jiraComponent);
		}
	}

	/**
	 * Returns the number of jira components where visible = &#63;.
	 *
	 * @param visible the visible
	 * @return the number of matching jira components
	 */
	@Override
	public int countByVisible(boolean visible) {
		FinderPath finderPath = _finderPathCountByVisible;

		Object[] finderArgs = new Object[] {visible};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_JIRACOMPONENT_WHERE);

			sb.append(_FINDER_COLUMN_VISIBLE_VISIBLE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(visible);

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

	private static final String _FINDER_COLUMN_VISIBLE_VISIBLE_2 =
		"jiraComponent.visible = ?";

	public JIRAComponentPersistenceImpl() {
		setModelClass(JIRAComponent.class);
	}

	/**
	 * Caches the jira component in the entity cache if it is enabled.
	 *
	 * @param jiraComponent the jira component
	 */
	@Override
	public void cacheResult(JIRAComponent jiraComponent) {
		entityCache.putResult(
			JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentImpl.class, jiraComponent.getPrimaryKey(),
			jiraComponent);

		finderCache.putResult(
			_finderPathFetchByRemoteId,
			new Object[] {jiraComponent.getRemoteId()}, jiraComponent);

		jiraComponent.resetOriginalValues();
	}

	/**
	 * Caches the jira components in the entity cache if it is enabled.
	 *
	 * @param jiraComponents the jira components
	 */
	@Override
	public void cacheResult(List<JIRAComponent> jiraComponents) {
		for (JIRAComponent jiraComponent : jiraComponents) {
			if (entityCache.getResult(
					JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
					JIRAComponentImpl.class, jiraComponent.getPrimaryKey()) ==
						null) {

				cacheResult(jiraComponent);
			}
			else {
				jiraComponent.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all jira components.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(JIRAComponentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the jira component.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAComponent jiraComponent) {
		entityCache.removeResult(
			JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentImpl.class, jiraComponent.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((JIRAComponentModelImpl)jiraComponent, true);
	}

	@Override
	public void clearCache(List<JIRAComponent> jiraComponents) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JIRAComponent jiraComponent : jiraComponents) {
			entityCache.removeResult(
				JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
				JIRAComponentImpl.class, jiraComponent.getPrimaryKey());

			clearUniqueFindersCache(
				(JIRAComponentModelImpl)jiraComponent, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
				JIRAComponentImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		JIRAComponentModelImpl jiraComponentModelImpl) {

		Object[] args = new Object[] {jiraComponentModelImpl.getRemoteId()};

		finderCache.putResult(
			_finderPathCountByRemoteId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByRemoteId, args, jiraComponentModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		JIRAComponentModelImpl jiraComponentModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {jiraComponentModelImpl.getRemoteId()};

			finderCache.removeResult(_finderPathCountByRemoteId, args);
			finderCache.removeResult(_finderPathFetchByRemoteId, args);
		}

		if ((jiraComponentModelImpl.getColumnBitmask() &
			 _finderPathFetchByRemoteId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				jiraComponentModelImpl.getOriginalRemoteId()
			};

			finderCache.removeResult(_finderPathCountByRemoteId, args);
			finderCache.removeResult(_finderPathFetchByRemoteId, args);
		}
	}

	/**
	 * Creates a new jira component with the primary key. Does not add the jira component to the database.
	 *
	 * @param jiraComponentId the primary key for the new jira component
	 * @return the new jira component
	 */
	@Override
	public JIRAComponent create(long jiraComponentId) {
		JIRAComponent jiraComponent = new JIRAComponentImpl();

		jiraComponent.setNew(true);
		jiraComponent.setPrimaryKey(jiraComponentId);

		return jiraComponent;
	}

	/**
	 * Removes the jira component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component that was removed
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent remove(long jiraComponentId)
		throws NoSuchJIRAComponentException {

		return remove((Serializable)jiraComponentId);
	}

	/**
	 * Removes the jira component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the jira component
	 * @return the jira component that was removed
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent remove(Serializable primaryKey)
		throws NoSuchJIRAComponentException {

		Session session = null;

		try {
			session = openSession();

			JIRAComponent jiraComponent = (JIRAComponent)session.get(
				JIRAComponentImpl.class, primaryKey);

			if (jiraComponent == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJIRAComponentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(jiraComponent);
		}
		catch (NoSuchJIRAComponentException noSuchEntityException) {
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
	protected JIRAComponent removeImpl(JIRAComponent jiraComponent) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jiraComponent)) {
				jiraComponent = (JIRAComponent)session.get(
					JIRAComponentImpl.class, jiraComponent.getPrimaryKeyObj());
			}

			if (jiraComponent != null) {
				session.delete(jiraComponent);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (jiraComponent != null) {
			clearCache(jiraComponent);
		}

		return jiraComponent;
	}

	@Override
	public JIRAComponent updateImpl(JIRAComponent jiraComponent) {
		boolean isNew = jiraComponent.isNew();

		if (!(jiraComponent instanceof JIRAComponentModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(jiraComponent.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					jiraComponent);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in jiraComponent proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom JIRAComponent implementation " +
					jiraComponent.getClass());
		}

		JIRAComponentModelImpl jiraComponentModelImpl =
			(JIRAComponentModelImpl)jiraComponent;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(jiraComponent);

				jiraComponent.setNew(false);
			}
			else {
				jiraComponent = (JIRAComponent)session.merge(jiraComponent);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!JIRAComponentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {jiraComponentModelImpl.isVisible()};

			finderCache.removeResult(_finderPathCountByVisible, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByVisible, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((jiraComponentModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByVisible.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					jiraComponentModelImpl.getOriginalVisible()
				};

				finderCache.removeResult(_finderPathCountByVisible, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByVisible, args);

				args = new Object[] {jiraComponentModelImpl.isVisible()};

				finderCache.removeResult(_finderPathCountByVisible, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByVisible, args);
			}
		}

		entityCache.putResult(
			JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentImpl.class, jiraComponent.getPrimaryKey(),
			jiraComponent, false);

		clearUniqueFindersCache(jiraComponentModelImpl, false);
		cacheUniqueFindersCache(jiraComponentModelImpl);

		jiraComponent.resetOriginalValues();

		return jiraComponent;
	}

	/**
	 * Returns the jira component with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the jira component
	 * @return the jira component
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJIRAComponentException {

		JIRAComponent jiraComponent = fetchByPrimaryKey(primaryKey);

		if (jiraComponent == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJIRAComponentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return jiraComponent;
	}

	/**
	 * Returns the jira component with the primary key or throws a <code>NoSuchJIRAComponentException</code> if it could not be found.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent findByPrimaryKey(long jiraComponentId)
		throws NoSuchJIRAComponentException {

		return findByPrimaryKey((Serializable)jiraComponentId);
	}

	/**
	 * Returns the jira component with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the jira component
	 * @return the jira component, or <code>null</code> if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		JIRAComponent jiraComponent = (JIRAComponent)serializable;

		if (jiraComponent == null) {
			Session session = null;

			try {
				session = openSession();

				jiraComponent = (JIRAComponent)session.get(
					JIRAComponentImpl.class, primaryKey);

				if (jiraComponent != null) {
					cacheResult(jiraComponent);
				}
				else {
					entityCache.putResult(
						JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
						JIRAComponentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
					JIRAComponentImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return jiraComponent;
	}

	/**
	 * Returns the jira component with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraComponentId the primary key of the jira component
	 * @return the jira component, or <code>null</code> if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent fetchByPrimaryKey(long jiraComponentId) {
		return fetchByPrimaryKey((Serializable)jiraComponentId);
	}

	@Override
	public Map<Serializable, JIRAComponent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, JIRAComponent> map =
			new HashMap<Serializable, JIRAComponent>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JIRAComponent jiraComponent = fetchByPrimaryKey(primaryKey);

			if (jiraComponent != null) {
				map.put(primaryKey, jiraComponent);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
				JIRAComponentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (JIRAComponent)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_JIRACOMPONENT_WHERE_PKS_IN);

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

			for (JIRAComponent jiraComponent :
					(List<JIRAComponent>)query.list()) {

				map.put(jiraComponent.getPrimaryKeyObj(), jiraComponent);

				cacheResult(jiraComponent);

				uncachedPrimaryKeys.remove(jiraComponent.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
					JIRAComponentImpl.class, primaryKey, nullModel);
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
	 * Returns all the jira components.
	 *
	 * @return the jira components
	 */
	@Override
	public List<JIRAComponent> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @return the range of jira components
	 */
	@Override
	public List<JIRAComponent> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jira components
	 */
	@Override
	public List<JIRAComponent> findAll(
		int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>JIRAComponentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of jira components
	 */
	@Override
	public List<JIRAComponent> findAll(
		int start, int end, OrderByComparator<JIRAComponent> orderByComparator,
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

		List<JIRAComponent> list = null;

		if (useFinderCache) {
			list = (List<JIRAComponent>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_JIRACOMPONENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_JIRACOMPONENT;

				sql = sql.concat(JIRAComponentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<JIRAComponent>)QueryUtil.list(
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
	 * Removes all the jira components from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JIRAComponent jiraComponent : findAll()) {
			remove(jiraComponent);
		}
	}

	/**
	 * Returns the number of jira components.
	 *
	 * @return the number of jira components
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_JIRACOMPONENT);

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
		return JIRAComponentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the jira component persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByRemoteId = new FinderPath(
			JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByRemoteId", new String[] {Long.class.getName()},
			JIRAComponentModelImpl.REMOTEID_COLUMN_BITMASK);

		_finderPathCountByRemoteId = new FinderPath(
			JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRemoteId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByVisible = new FinderPath(
			JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByVisible",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByVisible = new FinderPath(
			JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByVisible", new String[] {Boolean.class.getName()},
			JIRAComponentModelImpl.VISIBLE_COLUMN_BITMASK |
			JIRAComponentModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByVisible = new FinderPath(
			JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByVisible",
			new String[] {Boolean.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(JIRAComponentImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_JIRACOMPONENT =
		"SELECT jiraComponent FROM JIRAComponent jiraComponent";

	private static final String _SQL_SELECT_JIRACOMPONENT_WHERE_PKS_IN =
		"SELECT jiraComponent FROM JIRAComponent jiraComponent WHERE jiraComponentId IN (";

	private static final String _SQL_SELECT_JIRACOMPONENT_WHERE =
		"SELECT jiraComponent FROM JIRAComponent jiraComponent WHERE ";

	private static final String _SQL_COUNT_JIRACOMPONENT =
		"SELECT COUNT(jiraComponent) FROM JIRAComponent jiraComponent";

	private static final String _SQL_COUNT_JIRACOMPONENT_WHERE =
		"SELECT COUNT(jiraComponent) FROM JIRAComponent jiraComponent WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraComponent.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No JIRAComponent exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No JIRAComponent exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		JIRAComponentPersistenceImpl.class);

}