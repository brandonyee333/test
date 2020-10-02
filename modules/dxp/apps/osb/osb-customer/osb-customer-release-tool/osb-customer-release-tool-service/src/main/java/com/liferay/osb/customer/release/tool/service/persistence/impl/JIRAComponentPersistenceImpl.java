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

package com.liferay.osb.customer.release.tool.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the jira component service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAComponentPersistence
 * @see com.liferay.osb.customer.release.tool.service.persistence.JIRAComponentUtil
 * @generated
 */
@ProviderType
public class JIRAComponentPersistenceImpl extends BasePersistenceImpl<JIRAComponent>
	implements JIRAComponentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JIRAComponentUtil} to access the jira component persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAComponentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REMOTEPROJECT =
		new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByRemoteProject",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOTEPROJECT =
		new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByRemoteProject", new String[] { String.class.getName() },
			JIRAComponentModelImpl.REMOTEPROJECT_COLUMN_BITMASK |
			JIRAComponentModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REMOTEPROJECT = new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRemoteProject",
			new String[] { String.class.getName() });

	/**
	 * Returns all the jira components where remoteProject = &#63;.
	 *
	 * @param remoteProject the remote project
	 * @return the matching jira components
	 */
	@Override
	public List<JIRAComponent> findByRemoteProject(String remoteProject) {
		return findByRemoteProject(remoteProject, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jira components where remoteProject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param remoteProject the remote project
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @return the range of matching jira components
	 */
	@Override
	public List<JIRAComponent> findByRemoteProject(String remoteProject,
		int start, int end) {
		return findByRemoteProject(remoteProject, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jira components where remoteProject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param remoteProject the remote project
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jira components
	 */
	@Override
	public List<JIRAComponent> findByRemoteProject(String remoteProject,
		int start, int end, OrderByComparator<JIRAComponent> orderByComparator) {
		return findByRemoteProject(remoteProject, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jira components where remoteProject = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param remoteProject the remote project
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jira components
	 */
	@Override
	public List<JIRAComponent> findByRemoteProject(String remoteProject,
		int start, int end, OrderByComparator<JIRAComponent> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOTEPROJECT;
			finderArgs = new Object[] { remoteProject };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REMOTEPROJECT;
			finderArgs = new Object[] {
					remoteProject,
					
					start, end, orderByComparator
				};
		}

		List<JIRAComponent> list = null;

		if (retrieveFromCache) {
			list = (List<JIRAComponent>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (JIRAComponent jiraComponent : list) {
					if (!Objects.equals(remoteProject,
								jiraComponent.getRemoteProject())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_JIRACOMPONENT_WHERE);

			boolean bindRemoteProject = false;

			if (remoteProject == null) {
				query.append(_FINDER_COLUMN_REMOTEPROJECT_REMOTEPROJECT_1);
			}
			else if (remoteProject.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_REMOTEPROJECT_REMOTEPROJECT_3);
			}
			else {
				bindRemoteProject = true;

				query.append(_FINDER_COLUMN_REMOTEPROJECT_REMOTEPROJECT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAComponentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRemoteProject) {
					qPos.add(remoteProject);
				}

				if (!pagination) {
					list = (List<JIRAComponent>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAComponent>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first jira component in the ordered set where remoteProject = &#63;.
	 *
	 * @param remoteProject the remote project
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent findByRemoteProject_First(String remoteProject,
		OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException {
		JIRAComponent jiraComponent = fetchByRemoteProject_First(remoteProject,
				orderByComparator);

		if (jiraComponent != null) {
			return jiraComponent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("remoteProject=");
		msg.append(remoteProject);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAComponentException(msg.toString());
	}

	/**
	 * Returns the first jira component in the ordered set where remoteProject = &#63;.
	 *
	 * @param remoteProject the remote project
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent fetchByRemoteProject_First(String remoteProject,
		OrderByComparator<JIRAComponent> orderByComparator) {
		List<JIRAComponent> list = findByRemoteProject(remoteProject, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last jira component in the ordered set where remoteProject = &#63;.
	 *
	 * @param remoteProject the remote project
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent findByRemoteProject_Last(String remoteProject,
		OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException {
		JIRAComponent jiraComponent = fetchByRemoteProject_Last(remoteProject,
				orderByComparator);

		if (jiraComponent != null) {
			return jiraComponent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("remoteProject=");
		msg.append(remoteProject);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAComponentException(msg.toString());
	}

	/**
	 * Returns the last jira component in the ordered set where remoteProject = &#63;.
	 *
	 * @param remoteProject the remote project
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent fetchByRemoteProject_Last(String remoteProject,
		OrderByComparator<JIRAComponent> orderByComparator) {
		int count = countByRemoteProject(remoteProject);

		if (count == 0) {
			return null;
		}

		List<JIRAComponent> list = findByRemoteProject(remoteProject,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jira components before and after the current jira component in the ordered set where remoteProject = &#63;.
	 *
	 * @param jiraComponentId the primary key of the current jira component
	 * @param remoteProject the remote project
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next jira component
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent[] findByRemoteProject_PrevAndNext(
		long jiraComponentId, String remoteProject,
		OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException {
		JIRAComponent jiraComponent = findByPrimaryKey(jiraComponentId);

		Session session = null;

		try {
			session = openSession();

			JIRAComponent[] array = new JIRAComponentImpl[3];

			array[0] = getByRemoteProject_PrevAndNext(session, jiraComponent,
					remoteProject, orderByComparator, true);

			array[1] = jiraComponent;

			array[2] = getByRemoteProject_PrevAndNext(session, jiraComponent,
					remoteProject, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAComponent getByRemoteProject_PrevAndNext(Session session,
		JIRAComponent jiraComponent, String remoteProject,
		OrderByComparator<JIRAComponent> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRACOMPONENT_WHERE);

		boolean bindRemoteProject = false;

		if (remoteProject == null) {
			query.append(_FINDER_COLUMN_REMOTEPROJECT_REMOTEPROJECT_1);
		}
		else if (remoteProject.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_REMOTEPROJECT_REMOTEPROJECT_3);
		}
		else {
			bindRemoteProject = true;

			query.append(_FINDER_COLUMN_REMOTEPROJECT_REMOTEPROJECT_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(JIRAComponentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindRemoteProject) {
			qPos.add(remoteProject);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraComponent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAComponent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jira components where remoteProject = &#63; from the database.
	 *
	 * @param remoteProject the remote project
	 */
	@Override
	public void removeByRemoteProject(String remoteProject) {
		for (JIRAComponent jiraComponent : findByRemoteProject(remoteProject,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraComponent);
		}
	}

	/**
	 * Returns the number of jira components where remoteProject = &#63;.
	 *
	 * @param remoteProject the remote project
	 * @return the number of matching jira components
	 */
	@Override
	public int countByRemoteProject(String remoteProject) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_REMOTEPROJECT;

		Object[] finderArgs = new Object[] { remoteProject };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRACOMPONENT_WHERE);

			boolean bindRemoteProject = false;

			if (remoteProject == null) {
				query.append(_FINDER_COLUMN_REMOTEPROJECT_REMOTEPROJECT_1);
			}
			else if (remoteProject.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_REMOTEPROJECT_REMOTEPROJECT_3);
			}
			else {
				bindRemoteProject = true;

				query.append(_FINDER_COLUMN_REMOTEPROJECT_REMOTEPROJECT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRemoteProject) {
					qPos.add(remoteProject);
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

	private static final String _FINDER_COLUMN_REMOTEPROJECT_REMOTEPROJECT_1 = "jiraComponent.remoteProject IS NULL";
	private static final String _FINDER_COLUMN_REMOTEPROJECT_REMOTEPROJECT_2 = "jiraComponent.remoteProject = ?";
	private static final String _FINDER_COLUMN_REMOTEPROJECT_REMOTEPROJECT_3 = "(jiraComponent.remoteProject IS NULL OR jiraComponent.remoteProject = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_RI_RP = new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByRI_RP",
			new String[] { Long.class.getName(), String.class.getName() },
			JIRAComponentModelImpl.REMOTEID_COLUMN_BITMASK |
			JIRAComponentModelImpl.REMOTEPROJECT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RI_RP = new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRI_RP",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the jira component where remoteId = &#63; and remoteProject = &#63; or throws a {@link NoSuchJIRAComponentException} if it could not be found.
	 *
	 * @param remoteId the remote ID
	 * @param remoteProject the remote project
	 * @return the matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent findByRI_RP(long remoteId, String remoteProject)
		throws NoSuchJIRAComponentException {
		JIRAComponent jiraComponent = fetchByRI_RP(remoteId, remoteProject);

		if (jiraComponent == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("remoteId=");
			msg.append(remoteId);

			msg.append(", remoteProject=");
			msg.append(remoteProject);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchJIRAComponentException(msg.toString());
		}

		return jiraComponent;
	}

	/**
	 * Returns the jira component where remoteId = &#63; and remoteProject = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param remoteId the remote ID
	 * @param remoteProject the remote project
	 * @return the matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent fetchByRI_RP(long remoteId, String remoteProject) {
		return fetchByRI_RP(remoteId, remoteProject, true);
	}

	/**
	 * Returns the jira component where remoteId = &#63; and remoteProject = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param remoteId the remote ID
	 * @param remoteProject the remote project
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent fetchByRI_RP(long remoteId, String remoteProject,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { remoteId, remoteProject };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_RI_RP,
					finderArgs, this);
		}

		if (result instanceof JIRAComponent) {
			JIRAComponent jiraComponent = (JIRAComponent)result;

			if ((remoteId != jiraComponent.getRemoteId()) ||
					!Objects.equals(remoteProject,
						jiraComponent.getRemoteProject())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_JIRACOMPONENT_WHERE);

			query.append(_FINDER_COLUMN_RI_RP_REMOTEID_2);

			boolean bindRemoteProject = false;

			if (remoteProject == null) {
				query.append(_FINDER_COLUMN_RI_RP_REMOTEPROJECT_1);
			}
			else if (remoteProject.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_RI_RP_REMOTEPROJECT_3);
			}
			else {
				bindRemoteProject = true;

				query.append(_FINDER_COLUMN_RI_RP_REMOTEPROJECT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(remoteId);

				if (bindRemoteProject) {
					qPos.add(remoteProject);
				}

				List<JIRAComponent> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_RI_RP,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"JIRAComponentPersistenceImpl.fetchByRI_RP(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					JIRAComponent jiraComponent = list.get(0);

					result = jiraComponent;

					cacheResult(jiraComponent);

					if ((jiraComponent.getRemoteId() != remoteId) ||
							(jiraComponent.getRemoteProject() == null) ||
							!jiraComponent.getRemoteProject()
											  .equals(remoteProject)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_RI_RP,
							finderArgs, jiraComponent);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_RI_RP, finderArgs);

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
			return (JIRAComponent)result;
		}
	}

	/**
	 * Removes the jira component where remoteId = &#63; and remoteProject = &#63; from the database.
	 *
	 * @param remoteId the remote ID
	 * @param remoteProject the remote project
	 * @return the jira component that was removed
	 */
	@Override
	public JIRAComponent removeByRI_RP(long remoteId, String remoteProject)
		throws NoSuchJIRAComponentException {
		JIRAComponent jiraComponent = findByRI_RP(remoteId, remoteProject);

		return remove(jiraComponent);
	}

	/**
	 * Returns the number of jira components where remoteId = &#63; and remoteProject = &#63;.
	 *
	 * @param remoteId the remote ID
	 * @param remoteProject the remote project
	 * @return the number of matching jira components
	 */
	@Override
	public int countByRI_RP(long remoteId, String remoteProject) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RI_RP;

		Object[] finderArgs = new Object[] { remoteId, remoteProject };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JIRACOMPONENT_WHERE);

			query.append(_FINDER_COLUMN_RI_RP_REMOTEID_2);

			boolean bindRemoteProject = false;

			if (remoteProject == null) {
				query.append(_FINDER_COLUMN_RI_RP_REMOTEPROJECT_1);
			}
			else if (remoteProject.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_RI_RP_REMOTEPROJECT_3);
			}
			else {
				bindRemoteProject = true;

				query.append(_FINDER_COLUMN_RI_RP_REMOTEPROJECT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(remoteId);

				if (bindRemoteProject) {
					qPos.add(remoteProject);
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

	private static final String _FINDER_COLUMN_RI_RP_REMOTEID_2 = "jiraComponent.remoteId = ? AND ";
	private static final String _FINDER_COLUMN_RI_RP_REMOTEPROJECT_1 = "jiraComponent.remoteProject IS NULL";
	private static final String _FINDER_COLUMN_RI_RP_REMOTEPROJECT_2 = "jiraComponent.remoteProject = ?";
	private static final String _FINDER_COLUMN_RI_RP_REMOTEPROJECT_3 = "(jiraComponent.remoteProject IS NULL OR jiraComponent.remoteProject = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RP_V = new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByRP_V",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RP_V = new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED,
			JIRAComponentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByRP_V",
			new String[] { String.class.getName(), Boolean.class.getName() },
			JIRAComponentModelImpl.REMOTEPROJECT_COLUMN_BITMASK |
			JIRAComponentModelImpl.VISIBLE_COLUMN_BITMASK |
			JIRAComponentModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RP_V = new FinderPath(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRP_V",
			new String[] { String.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the jira components where remoteProject = &#63; and visible = &#63;.
	 *
	 * @param remoteProject the remote project
	 * @param visible the visible
	 * @return the matching jira components
	 */
	@Override
	public List<JIRAComponent> findByRP_V(String remoteProject, boolean visible) {
		return findByRP_V(remoteProject, visible, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the jira components where remoteProject = &#63; and visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param remoteProject the remote project
	 * @param visible the visible
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @return the range of matching jira components
	 */
	@Override
	public List<JIRAComponent> findByRP_V(String remoteProject,
		boolean visible, int start, int end) {
		return findByRP_V(remoteProject, visible, start, end, null);
	}

	/**
	 * Returns an ordered range of all the jira components where remoteProject = &#63; and visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param remoteProject the remote project
	 * @param visible the visible
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching jira components
	 */
	@Override
	public List<JIRAComponent> findByRP_V(String remoteProject,
		boolean visible, int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator) {
		return findByRP_V(remoteProject, visible, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jira components where remoteProject = &#63; and visible = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param remoteProject the remote project
	 * @param visible the visible
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching jira components
	 */
	@Override
	public List<JIRAComponent> findByRP_V(String remoteProject,
		boolean visible, int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RP_V;
			finderArgs = new Object[] { remoteProject, visible };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RP_V;
			finderArgs = new Object[] {
					remoteProject, visible,
					
					start, end, orderByComparator
				};
		}

		List<JIRAComponent> list = null;

		if (retrieveFromCache) {
			list = (List<JIRAComponent>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (JIRAComponent jiraComponent : list) {
					if (!Objects.equals(remoteProject,
								jiraComponent.getRemoteProject()) ||
							(visible != jiraComponent.getVisible())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_JIRACOMPONENT_WHERE);

			boolean bindRemoteProject = false;

			if (remoteProject == null) {
				query.append(_FINDER_COLUMN_RP_V_REMOTEPROJECT_1);
			}
			else if (remoteProject.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_RP_V_REMOTEPROJECT_3);
			}
			else {
				bindRemoteProject = true;

				query.append(_FINDER_COLUMN_RP_V_REMOTEPROJECT_2);
			}

			query.append(_FINDER_COLUMN_RP_V_VISIBLE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAComponentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRemoteProject) {
					qPos.add(remoteProject);
				}

				qPos.add(visible);

				if (!pagination) {
					list = (List<JIRAComponent>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAComponent>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first jira component in the ordered set where remoteProject = &#63; and visible = &#63;.
	 *
	 * @param remoteProject the remote project
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent findByRP_V_First(String remoteProject,
		boolean visible, OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException {
		JIRAComponent jiraComponent = fetchByRP_V_First(remoteProject, visible,
				orderByComparator);

		if (jiraComponent != null) {
			return jiraComponent;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("remoteProject=");
		msg.append(remoteProject);

		msg.append(", visible=");
		msg.append(visible);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAComponentException(msg.toString());
	}

	/**
	 * Returns the first jira component in the ordered set where remoteProject = &#63; and visible = &#63;.
	 *
	 * @param remoteProject the remote project
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent fetchByRP_V_First(String remoteProject,
		boolean visible, OrderByComparator<JIRAComponent> orderByComparator) {
		List<JIRAComponent> list = findByRP_V(remoteProject, visible, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last jira component in the ordered set where remoteProject = &#63; and visible = &#63;.
	 *
	 * @param remoteProject the remote project
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching jira component
	 * @throws NoSuchJIRAComponentException if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent findByRP_V_Last(String remoteProject, boolean visible,
		OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException {
		JIRAComponent jiraComponent = fetchByRP_V_Last(remoteProject, visible,
				orderByComparator);

		if (jiraComponent != null) {
			return jiraComponent;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("remoteProject=");
		msg.append(remoteProject);

		msg.append(", visible=");
		msg.append(visible);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAComponentException(msg.toString());
	}

	/**
	 * Returns the last jira component in the ordered set where remoteProject = &#63; and visible = &#63;.
	 *
	 * @param remoteProject the remote project
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching jira component, or <code>null</code> if a matching jira component could not be found
	 */
	@Override
	public JIRAComponent fetchByRP_V_Last(String remoteProject,
		boolean visible, OrderByComparator<JIRAComponent> orderByComparator) {
		int count = countByRP_V(remoteProject, visible);

		if (count == 0) {
			return null;
		}

		List<JIRAComponent> list = findByRP_V(remoteProject, visible,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the jira components before and after the current jira component in the ordered set where remoteProject = &#63; and visible = &#63;.
	 *
	 * @param jiraComponentId the primary key of the current jira component
	 * @param remoteProject the remote project
	 * @param visible the visible
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next jira component
	 * @throws NoSuchJIRAComponentException if a jira component with the primary key could not be found
	 */
	@Override
	public JIRAComponent[] findByRP_V_PrevAndNext(long jiraComponentId,
		String remoteProject, boolean visible,
		OrderByComparator<JIRAComponent> orderByComparator)
		throws NoSuchJIRAComponentException {
		JIRAComponent jiraComponent = findByPrimaryKey(jiraComponentId);

		Session session = null;

		try {
			session = openSession();

			JIRAComponent[] array = new JIRAComponentImpl[3];

			array[0] = getByRP_V_PrevAndNext(session, jiraComponent,
					remoteProject, visible, orderByComparator, true);

			array[1] = jiraComponent;

			array[2] = getByRP_V_PrevAndNext(session, jiraComponent,
					remoteProject, visible, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAComponent getByRP_V_PrevAndNext(Session session,
		JIRAComponent jiraComponent, String remoteProject, boolean visible,
		OrderByComparator<JIRAComponent> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_JIRACOMPONENT_WHERE);

		boolean bindRemoteProject = false;

		if (remoteProject == null) {
			query.append(_FINDER_COLUMN_RP_V_REMOTEPROJECT_1);
		}
		else if (remoteProject.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_RP_V_REMOTEPROJECT_3);
		}
		else {
			bindRemoteProject = true;

			query.append(_FINDER_COLUMN_RP_V_REMOTEPROJECT_2);
		}

		query.append(_FINDER_COLUMN_RP_V_VISIBLE_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(JIRAComponentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindRemoteProject) {
			qPos.add(remoteProject);
		}

		qPos.add(visible);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraComponent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAComponent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the jira components where remoteProject = &#63; and visible = &#63; from the database.
	 *
	 * @param remoteProject the remote project
	 * @param visible the visible
	 */
	@Override
	public void removeByRP_V(String remoteProject, boolean visible) {
		for (JIRAComponent jiraComponent : findByRP_V(remoteProject, visible,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraComponent);
		}
	}

	/**
	 * Returns the number of jira components where remoteProject = &#63; and visible = &#63;.
	 *
	 * @param remoteProject the remote project
	 * @param visible the visible
	 * @return the number of matching jira components
	 */
	@Override
	public int countByRP_V(String remoteProject, boolean visible) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RP_V;

		Object[] finderArgs = new Object[] { remoteProject, visible };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JIRACOMPONENT_WHERE);

			boolean bindRemoteProject = false;

			if (remoteProject == null) {
				query.append(_FINDER_COLUMN_RP_V_REMOTEPROJECT_1);
			}
			else if (remoteProject.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_RP_V_REMOTEPROJECT_3);
			}
			else {
				bindRemoteProject = true;

				query.append(_FINDER_COLUMN_RP_V_REMOTEPROJECT_2);
			}

			query.append(_FINDER_COLUMN_RP_V_VISIBLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindRemoteProject) {
					qPos.add(remoteProject);
				}

				qPos.add(visible);

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

	private static final String _FINDER_COLUMN_RP_V_REMOTEPROJECT_1 = "jiraComponent.remoteProject IS NULL AND ";
	private static final String _FINDER_COLUMN_RP_V_REMOTEPROJECT_2 = "jiraComponent.remoteProject = ? AND ";
	private static final String _FINDER_COLUMN_RP_V_REMOTEPROJECT_3 = "(jiraComponent.remoteProject IS NULL OR jiraComponent.remoteProject = '') AND ";
	private static final String _FINDER_COLUMN_RP_V_VISIBLE_2 = "jiraComponent.visible = ?";

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
		entityCache.putResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentImpl.class, jiraComponent.getPrimaryKey(),
			jiraComponent);

		finderCache.putResult(FINDER_PATH_FETCH_BY_RI_RP,
			new Object[] {
				jiraComponent.getRemoteId(), jiraComponent.getRemoteProject()
			}, jiraComponent);

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
						JIRAComponentImpl.class, jiraComponent.getPrimaryKey()) == null) {
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAComponent jiraComponent) {
		entityCache.removeResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
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
			entityCache.removeResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
				JIRAComponentImpl.class, jiraComponent.getPrimaryKey());

			clearUniqueFindersCache((JIRAComponentModelImpl)jiraComponent, true);
		}
	}

	protected void cacheUniqueFindersCache(
		JIRAComponentModelImpl jiraComponentModelImpl) {
		Object[] args = new Object[] {
				jiraComponentModelImpl.getRemoteId(),
				jiraComponentModelImpl.getRemoteProject()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_RI_RP, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_RI_RP, args,
			jiraComponentModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		JIRAComponentModelImpl jiraComponentModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					jiraComponentModelImpl.getRemoteId(),
					jiraComponentModelImpl.getRemoteProject()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RI_RP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_RI_RP, args);
		}

		if ((jiraComponentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_RI_RP.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					jiraComponentModelImpl.getOriginalRemoteId(),
					jiraComponentModelImpl.getOriginalRemoteProject()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RI_RP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_RI_RP, args);
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

			JIRAComponent jiraComponent = (JIRAComponent)session.get(JIRAComponentImpl.class,
					primaryKey);

			if (jiraComponent == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJIRAComponentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(jiraComponent);
		}
		catch (NoSuchJIRAComponentException nsee) {
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
	protected JIRAComponent removeImpl(JIRAComponent jiraComponent) {
		jiraComponent = toUnwrappedModel(jiraComponent);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jiraComponent)) {
				jiraComponent = (JIRAComponent)session.get(JIRAComponentImpl.class,
						jiraComponent.getPrimaryKeyObj());
			}

			if (jiraComponent != null) {
				session.delete(jiraComponent);
			}
		}
		catch (Exception e) {
			throw processException(e);
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
		jiraComponent = toUnwrappedModel(jiraComponent);

		boolean isNew = jiraComponent.isNew();

		JIRAComponentModelImpl jiraComponentModelImpl = (JIRAComponentModelImpl)jiraComponent;

		Session session = null;

		try {
			session = openSession();

			if (jiraComponent.isNew()) {
				session.save(jiraComponent);

				jiraComponent.setNew(false);
			}
			else {
				jiraComponent = (JIRAComponent)session.merge(jiraComponent);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!JIRAComponentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					jiraComponentModelImpl.getRemoteProject()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_REMOTEPROJECT, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOTEPROJECT,
				args);

			args = new Object[] {
					jiraComponentModelImpl.getRemoteProject(),
					jiraComponentModelImpl.getVisible()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_RP_V, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RP_V,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((jiraComponentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOTEPROJECT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraComponentModelImpl.getOriginalRemoteProject()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_REMOTEPROJECT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOTEPROJECT,
					args);

				args = new Object[] { jiraComponentModelImpl.getRemoteProject() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_REMOTEPROJECT,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REMOTEPROJECT,
					args);
			}

			if ((jiraComponentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RP_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraComponentModelImpl.getOriginalRemoteProject(),
						jiraComponentModelImpl.getOriginalVisible()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RP_V, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RP_V,
					args);

				args = new Object[] {
						jiraComponentModelImpl.getRemoteProject(),
						jiraComponentModelImpl.getVisible()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RP_V, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RP_V,
					args);
			}
		}

		entityCache.putResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
			JIRAComponentImpl.class, jiraComponent.getPrimaryKey(),
			jiraComponent, false);

		clearUniqueFindersCache(jiraComponentModelImpl, false);
		cacheUniqueFindersCache(jiraComponentModelImpl);

		jiraComponent.resetOriginalValues();

		return jiraComponent;
	}

	protected JIRAComponent toUnwrappedModel(JIRAComponent jiraComponent) {
		if (jiraComponent instanceof JIRAComponentImpl) {
			return jiraComponent;
		}

		JIRAComponentImpl jiraComponentImpl = new JIRAComponentImpl();

		jiraComponentImpl.setNew(jiraComponent.isNew());
		jiraComponentImpl.setPrimaryKey(jiraComponent.getPrimaryKey());

		jiraComponentImpl.setJiraComponentId(jiraComponent.getJiraComponentId());
		jiraComponentImpl.setRemoteId(jiraComponent.getRemoteId());
		jiraComponentImpl.setRemoteProject(jiraComponent.getRemoteProject());
		jiraComponentImpl.setName(jiraComponent.getName());
		jiraComponentImpl.setVisible(jiraComponent.isVisible());

		return jiraComponentImpl;
	}

	/**
	 * Returns the jira component with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
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

			throw new NoSuchJIRAComponentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return jiraComponent;
	}

	/**
	 * Returns the jira component with the primary key or throws a {@link NoSuchJIRAComponentException} if it could not be found.
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
		Serializable serializable = entityCache.getResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
				JIRAComponentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		JIRAComponent jiraComponent = (JIRAComponent)serializable;

		if (jiraComponent == null) {
			Session session = null;

			try {
				session = openSession();

				jiraComponent = (JIRAComponent)session.get(JIRAComponentImpl.class,
						primaryKey);

				if (jiraComponent != null) {
					cacheResult(jiraComponent);
				}
				else {
					entityCache.putResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
						JIRAComponentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
					JIRAComponentImpl.class, primaryKey);

				throw processException(e);
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

		Map<Serializable, JIRAComponent> map = new HashMap<Serializable, JIRAComponent>();

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
			Serializable serializable = entityCache.getResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_JIRACOMPONENT_WHERE_PKS_IN);

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

			for (JIRAComponent jiraComponent : (List<JIRAComponent>)q.list()) {
				map.put(jiraComponent.getPrimaryKeyObj(), jiraComponent);

				cacheResult(jiraComponent);

				uncachedPrimaryKeys.remove(jiraComponent.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(JIRAComponentModelImpl.ENTITY_CACHE_ENABLED,
					JIRAComponentImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of jira components
	 */
	@Override
	public List<JIRAComponent> findAll(int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the jira components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of jira components
	 * @param end the upper bound of the range of jira components (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of jira components
	 */
	@Override
	public List<JIRAComponent> findAll(int start, int end,
		OrderByComparator<JIRAComponent> orderByComparator,
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

		List<JIRAComponent> list = null;

		if (retrieveFromCache) {
			list = (List<JIRAComponent>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_JIRACOMPONENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JIRACOMPONENT;

				if (pagination) {
					sql = sql.concat(JIRAComponentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<JIRAComponent>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAComponent>)QueryUtil.list(q, getDialect(),
							start, end);
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JIRACOMPONENT);

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
		return JIRAComponentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the jira component persistence.
	 */
	public void afterPropertiesSet() {
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
	private static final String _SQL_SELECT_JIRACOMPONENT = "SELECT jiraComponent FROM JIRAComponent jiraComponent";
	private static final String _SQL_SELECT_JIRACOMPONENT_WHERE_PKS_IN = "SELECT jiraComponent FROM JIRAComponent jiraComponent WHERE jiraComponentId IN (";
	private static final String _SQL_SELECT_JIRACOMPONENT_WHERE = "SELECT jiraComponent FROM JIRAComponent jiraComponent WHERE ";
	private static final String _SQL_COUNT_JIRACOMPONENT = "SELECT COUNT(jiraComponent) FROM JIRAComponent jiraComponent";
	private static final String _SQL_COUNT_JIRACOMPONENT_WHERE = "SELECT COUNT(jiraComponent) FROM JIRAComponent jiraComponent WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraComponent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JIRAComponent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JIRAComponent exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(JIRAComponentPersistenceImpl.class);
}