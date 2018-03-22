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

package com.liferay.osb.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchCorpProjectException;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.impl.CorpProjectImpl;
import com.liferay.osb.model.impl.CorpProjectModelImpl;
import com.liferay.osb.service.persistence.CorpProjectPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

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
 * The persistence implementation for the corp project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectPersistence
 * @see com.liferay.osb.service.persistence.CorpProjectUtil
 * @generated
 */
@ProviderType
public class CorpProjectPersistenceImpl extends BasePersistenceImpl<CorpProject>
	implements CorpProjectPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CorpProjectUtil} to access the corp project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CorpProjectImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CorpProjectModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the corp projects where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching corp projects
	 */
	@Override
	public List<CorpProject> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp projects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @return the range of matching corp projects
	 */
	@Override
	public List<CorpProject> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp projects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching corp projects
	 */
	@Override
	public List<CorpProject> findByUuid(String uuid, int start, int end,
		OrderByComparator<CorpProject> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the corp projects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching corp projects
	 */
	@Override
	public List<CorpProject> findByUuid(String uuid, int start, int end,
		OrderByComparator<CorpProject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<CorpProject> list = null;

		if (retrieveFromCache) {
			list = (List<CorpProject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CorpProject corpProject : list) {
					if (!Objects.equals(uuid, corpProject.getUuid())) {
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

			query.append(_SQL_SELECT_CORPPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CorpProjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<CorpProject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CorpProject>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first corp project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project
	 * @throws NoSuchCorpProjectException if a matching corp project could not be found
	 */
	@Override
	public CorpProject findByUuid_First(String uuid,
		OrderByComparator<CorpProject> orderByComparator)
		throws NoSuchCorpProjectException {
		CorpProject corpProject = fetchByUuid_First(uuid, orderByComparator);

		if (corpProject != null) {
			return corpProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectException(msg.toString());
	}

	/**
	 * Returns the first corp project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project, or <code>null</code> if a matching corp project could not be found
	 */
	@Override
	public CorpProject fetchByUuid_First(String uuid,
		OrderByComparator<CorpProject> orderByComparator) {
		List<CorpProject> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last corp project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project
	 * @throws NoSuchCorpProjectException if a matching corp project could not be found
	 */
	@Override
	public CorpProject findByUuid_Last(String uuid,
		OrderByComparator<CorpProject> orderByComparator)
		throws NoSuchCorpProjectException {
		CorpProject corpProject = fetchByUuid_Last(uuid, orderByComparator);

		if (corpProject != null) {
			return corpProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectException(msg.toString());
	}

	/**
	 * Returns the last corp project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project, or <code>null</code> if a matching corp project could not be found
	 */
	@Override
	public CorpProject fetchByUuid_Last(String uuid,
		OrderByComparator<CorpProject> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CorpProject> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the corp projects before and after the current corp project in the ordered set where uuid = &#63;.
	 *
	 * @param corpProjectId the primary key of the current corp project
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next corp project
	 * @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject[] findByUuid_PrevAndNext(long corpProjectId,
		String uuid, OrderByComparator<CorpProject> orderByComparator)
		throws NoSuchCorpProjectException {
		CorpProject corpProject = findByPrimaryKey(corpProjectId);

		Session session = null;

		try {
			session = openSession();

			CorpProject[] array = new CorpProjectImpl[3];

			array[0] = getByUuid_PrevAndNext(session, corpProject, uuid,
					orderByComparator, true);

			array[1] = corpProject;

			array[2] = getByUuid_PrevAndNext(session, corpProject, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CorpProject getByUuid_PrevAndNext(Session session,
		CorpProject corpProject, String uuid,
		OrderByComparator<CorpProject> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CORPPROJECT_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
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
			query.append(CorpProjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(corpProject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CorpProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the corp projects where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CorpProject corpProject : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(corpProject);
		}
	}

	/**
	 * Returns the number of corp projects where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching corp projects
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "corpProject.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "corpProject.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(corpProject.uuid IS NULL OR corpProject.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDossieraProjectKey",
			new String[] { String.class.getName() },
			CorpProjectModelImpl.DOSSIERAPROJECTKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DOSSIERAPROJECTKEY = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDossieraProjectKey", new String[] { String.class.getName() });

	/**
	 * Returns the corp project where dossieraProjectKey = &#63; or throws a {@link NoSuchCorpProjectException} if it could not be found.
	 *
	 * @param dossieraProjectKey the dossiera project key
	 * @return the matching corp project
	 * @throws NoSuchCorpProjectException if a matching corp project could not be found
	 */
	@Override
	public CorpProject findByDossieraProjectKey(String dossieraProjectKey)
		throws NoSuchCorpProjectException {
		CorpProject corpProject = fetchByDossieraProjectKey(dossieraProjectKey);

		if (corpProject == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossieraProjectKey=");
			msg.append(dossieraProjectKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCorpProjectException(msg.toString());
		}

		return corpProject;
	}

	/**
	 * Returns the corp project where dossieraProjectKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossieraProjectKey the dossiera project key
	 * @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	 */
	@Override
	public CorpProject fetchByDossieraProjectKey(String dossieraProjectKey) {
		return fetchByDossieraProjectKey(dossieraProjectKey, true);
	}

	/**
	 * Returns the corp project where dossieraProjectKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossieraProjectKey the dossiera project key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	 */
	@Override
	public CorpProject fetchByDossieraProjectKey(String dossieraProjectKey,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossieraProjectKey };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
					finderArgs, this);
		}

		if (result instanceof CorpProject) {
			CorpProject corpProject = (CorpProject)result;

			if (!Objects.equals(dossieraProjectKey,
						corpProject.getDossieraProjectKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CORPPROJECT_WHERE);

			boolean bindDossieraProjectKey = false;

			if (dossieraProjectKey == null) {
				query.append(_FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_1);
			}
			else if (dossieraProjectKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_3);
			}
			else {
				bindDossieraProjectKey = true;

				query.append(_FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossieraProjectKey) {
					qPos.add(dossieraProjectKey);
				}

				List<CorpProject> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CorpProjectPersistenceImpl.fetchByDossieraProjectKey(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CorpProject corpProject = list.get(0);

					result = corpProject;

					cacheResult(corpProject);

					if ((corpProject.getDossieraProjectKey() == null) ||
							!corpProject.getDossieraProjectKey()
											.equals(dossieraProjectKey)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
							finderArgs, corpProject);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
					finderArgs);

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
			return (CorpProject)result;
		}
	}

	/**
	 * Removes the corp project where dossieraProjectKey = &#63; from the database.
	 *
	 * @param dossieraProjectKey the dossiera project key
	 * @return the corp project that was removed
	 */
	@Override
	public CorpProject removeByDossieraProjectKey(String dossieraProjectKey)
		throws NoSuchCorpProjectException {
		CorpProject corpProject = findByDossieraProjectKey(dossieraProjectKey);

		return remove(corpProject);
	}

	/**
	 * Returns the number of corp projects where dossieraProjectKey = &#63;.
	 *
	 * @param dossieraProjectKey the dossiera project key
	 * @return the number of matching corp projects
	 */
	@Override
	public int countByDossieraProjectKey(String dossieraProjectKey) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DOSSIERAPROJECTKEY;

		Object[] finderArgs = new Object[] { dossieraProjectKey };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPPROJECT_WHERE);

			boolean bindDossieraProjectKey = false;

			if (dossieraProjectKey == null) {
				query.append(_FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_1);
			}
			else if (dossieraProjectKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_3);
			}
			else {
				bindDossieraProjectKey = true;

				query.append(_FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossieraProjectKey) {
					qPos.add(dossieraProjectKey);
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

	private static final String _FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_1 =
		"corpProject.dossieraProjectKey IS NULL";
	private static final String _FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_2 =
		"corpProject.dossieraProjectKey = ?";
	private static final String _FINDER_COLUMN_DOSSIERAPROJECTKEY_DOSSIERAPROJECTKEY_3 =
		"(corpProject.dossieraProjectKey IS NULL OR corpProject.dossieraProjectKey = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_NAME = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the corp projects where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching corp projects
	 */
	@Override
	public List<CorpProject> findByName(String name) {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp projects where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @return the range of matching corp projects
	 */
	@Override
	public List<CorpProject> findByName(String name, int start, int end) {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp projects where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching corp projects
	 */
	@Override
	public List<CorpProject> findByName(String name, int start, int end,
		OrderByComparator<CorpProject> orderByComparator) {
		return findByName(name, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the corp projects where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching corp projects
	 */
	@Override
	public List<CorpProject> findByName(String name, int start, int end,
		OrderByComparator<CorpProject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
		finderArgs = new Object[] { name, start, end, orderByComparator };

		List<CorpProject> list = null;

		if (retrieveFromCache) {
			list = (List<CorpProject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CorpProject corpProject : list) {
					if (!StringUtil.wildcardMatches(corpProject.getName(),
								name, CharPool.UNDERLINE, CharPool.PERCENT,
								CharPool.BACK_SLASH, true)) {
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

			query.append(_SQL_SELECT_CORPPROJECT_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CorpProjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<CorpProject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CorpProject>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first corp project in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project
	 * @throws NoSuchCorpProjectException if a matching corp project could not be found
	 */
	@Override
	public CorpProject findByName_First(String name,
		OrderByComparator<CorpProject> orderByComparator)
		throws NoSuchCorpProjectException {
		CorpProject corpProject = fetchByName_First(name, orderByComparator);

		if (corpProject != null) {
			return corpProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectException(msg.toString());
	}

	/**
	 * Returns the first corp project in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching corp project, or <code>null</code> if a matching corp project could not be found
	 */
	@Override
	public CorpProject fetchByName_First(String name,
		OrderByComparator<CorpProject> orderByComparator) {
		List<CorpProject> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last corp project in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project
	 * @throws NoSuchCorpProjectException if a matching corp project could not be found
	 */
	@Override
	public CorpProject findByName_Last(String name,
		OrderByComparator<CorpProject> orderByComparator)
		throws NoSuchCorpProjectException {
		CorpProject corpProject = fetchByName_Last(name, orderByComparator);

		if (corpProject != null) {
			return corpProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCorpProjectException(msg.toString());
	}

	/**
	 * Returns the last corp project in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching corp project, or <code>null</code> if a matching corp project could not be found
	 */
	@Override
	public CorpProject fetchByName_Last(String name,
		OrderByComparator<CorpProject> orderByComparator) {
		int count = countByName(name);

		if (count == 0) {
			return null;
		}

		List<CorpProject> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the corp projects before and after the current corp project in the ordered set where name LIKE &#63;.
	 *
	 * @param corpProjectId the primary key of the current corp project
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next corp project
	 * @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject[] findByName_PrevAndNext(long corpProjectId,
		String name, OrderByComparator<CorpProject> orderByComparator)
		throws NoSuchCorpProjectException {
		CorpProject corpProject = findByPrimaryKey(corpProjectId);

		Session session = null;

		try {
			session = openSession();

			CorpProject[] array = new CorpProjectImpl[3];

			array[0] = getByName_PrevAndNext(session, corpProject, name,
					orderByComparator, true);

			array[1] = corpProject;

			array[2] = getByName_PrevAndNext(session, corpProject, name,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CorpProject getByName_PrevAndNext(Session session,
		CorpProject corpProject, String name,
		OrderByComparator<CorpProject> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CORPPROJECT_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_NAME_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_NAME_NAME_2);
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
			query.append(CorpProjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(corpProject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CorpProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the corp projects where name LIKE &#63; from the database.
	 *
	 * @param name the name
	 */
	@Override
	public void removeByName(String name) {
		for (CorpProject corpProject : findByName(name, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(corpProject);
		}
	}

	/**
	 * Returns the number of corp projects where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching corp projects
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPPROJECT_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "corpProject.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "corpProject.name LIKE ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(corpProject.name IS NULL OR corpProject.name LIKE '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_ORGANIZATIONID = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, CorpProjectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByOrganizationId",
			new String[] { Long.class.getName() },
			CorpProjectModelImpl.ORGANIZATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ORGANIZATIONID = new FinderPath(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOrganizationId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the corp project where organizationId = &#63; or throws a {@link NoSuchCorpProjectException} if it could not be found.
	 *
	 * @param organizationId the organization ID
	 * @return the matching corp project
	 * @throws NoSuchCorpProjectException if a matching corp project could not be found
	 */
	@Override
	public CorpProject findByOrganizationId(long organizationId)
		throws NoSuchCorpProjectException {
		CorpProject corpProject = fetchByOrganizationId(organizationId);

		if (corpProject == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("organizationId=");
			msg.append(organizationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCorpProjectException(msg.toString());
		}

		return corpProject;
	}

	/**
	 * Returns the corp project where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	 */
	@Override
	public CorpProject fetchByOrganizationId(long organizationId) {
		return fetchByOrganizationId(organizationId, true);
	}

	/**
	 * Returns the corp project where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param organizationId the organization ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	 */
	@Override
	public CorpProject fetchByOrganizationId(long organizationId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { organizationId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
					finderArgs, this);
		}

		if (result instanceof CorpProject) {
			CorpProject corpProject = (CorpProject)result;

			if ((organizationId != corpProject.getOrganizationId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CORPPROJECT_WHERE);

			query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

				List<CorpProject> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CorpProjectPersistenceImpl.fetchByOrganizationId(long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CorpProject corpProject = list.get(0);

					result = corpProject;

					cacheResult(corpProject);

					if ((corpProject.getOrganizationId() != organizationId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
							finderArgs, corpProject);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
					finderArgs);

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
			return (CorpProject)result;
		}
	}

	/**
	 * Removes the corp project where organizationId = &#63; from the database.
	 *
	 * @param organizationId the organization ID
	 * @return the corp project that was removed
	 */
	@Override
	public CorpProject removeByOrganizationId(long organizationId)
		throws NoSuchCorpProjectException {
		CorpProject corpProject = findByOrganizationId(organizationId);

		return remove(corpProject);
	}

	/**
	 * Returns the number of corp projects where organizationId = &#63;.
	 *
	 * @param organizationId the organization ID
	 * @return the number of matching corp projects
	 */
	@Override
	public int countByOrganizationId(long organizationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ORGANIZATIONID;

		Object[] finderArgs = new Object[] { organizationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CORPPROJECT_WHERE);

			query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(organizationId);

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

	private static final String _FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2 = "corpProject.organizationId = ?";

	public CorpProjectPersistenceImpl() {
		setModelClass(CorpProject.class);
	}

	/**
	 * Caches the corp project in the entity cache if it is enabled.
	 *
	 * @param corpProject the corp project
	 */
	@Override
	public void cacheResult(CorpProject corpProject) {
		entityCache.putResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectImpl.class, corpProject.getPrimaryKey(), corpProject);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
			new Object[] { corpProject.getDossieraProjectKey() }, corpProject);

		finderCache.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID,
			new Object[] { corpProject.getOrganizationId() }, corpProject);

		corpProject.resetOriginalValues();
	}

	/**
	 * Caches the corp projects in the entity cache if it is enabled.
	 *
	 * @param corpProjects the corp projects
	 */
	@Override
	public void cacheResult(List<CorpProject> corpProjects) {
		for (CorpProject corpProject : corpProjects) {
			if (entityCache.getResult(
						CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
						CorpProjectImpl.class, corpProject.getPrimaryKey()) == null) {
				cacheResult(corpProject);
			}
			else {
				corpProject.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all corp projects.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CorpProjectImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the corp project.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CorpProject corpProject) {
		entityCache.removeResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectImpl.class, corpProject.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CorpProjectModelImpl)corpProject, true);
	}

	@Override
	public void clearCache(List<CorpProject> corpProjects) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CorpProject corpProject : corpProjects) {
			entityCache.removeResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
				CorpProjectImpl.class, corpProject.getPrimaryKey());

			clearUniqueFindersCache((CorpProjectModelImpl)corpProject, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CorpProjectModelImpl corpProjectModelImpl) {
		Object[] args = new Object[] {
				corpProjectModelImpl.getDossieraProjectKey()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DOSSIERAPROJECTKEY, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY, args,
			corpProjectModelImpl, false);

		args = new Object[] { corpProjectModelImpl.getOrganizationId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID, args,
			corpProjectModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CorpProjectModelImpl corpProjectModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					corpProjectModelImpl.getDossieraProjectKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAPROJECTKEY,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
				args);
		}

		if ((corpProjectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					corpProjectModelImpl.getOriginalDossieraProjectKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAPROJECTKEY,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAPROJECTKEY,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					corpProjectModelImpl.getOrganizationId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID, args);
		}

		if ((corpProjectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ORGANIZATIONID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					corpProjectModelImpl.getOriginalOrganizationId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ORGANIZATIONID, args);
		}
	}

	/**
	 * Creates a new corp project with the primary key. Does not add the corp project to the database.
	 *
	 * @param corpProjectId the primary key for the new corp project
	 * @return the new corp project
	 */
	@Override
	public CorpProject create(long corpProjectId) {
		CorpProject corpProject = new CorpProjectImpl();

		corpProject.setNew(true);
		corpProject.setPrimaryKey(corpProjectId);

		String uuid = PortalUUIDUtil.generate();

		corpProject.setUuid(uuid);

		return corpProject;
	}

	/**
	 * Removes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param corpProjectId the primary key of the corp project
	 * @return the corp project that was removed
	 * @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject remove(long corpProjectId)
		throws NoSuchCorpProjectException {
		return remove((Serializable)corpProjectId);
	}

	/**
	 * Removes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the corp project
	 * @return the corp project that was removed
	 * @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject remove(Serializable primaryKey)
		throws NoSuchCorpProjectException {
		Session session = null;

		try {
			session = openSession();

			CorpProject corpProject = (CorpProject)session.get(CorpProjectImpl.class,
					primaryKey);

			if (corpProject == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCorpProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(corpProject);
		}
		catch (NoSuchCorpProjectException nsee) {
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
	protected CorpProject removeImpl(CorpProject corpProject) {
		corpProject = toUnwrappedModel(corpProject);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(corpProject)) {
				corpProject = (CorpProject)session.get(CorpProjectImpl.class,
						corpProject.getPrimaryKeyObj());
			}

			if (corpProject != null) {
				session.delete(corpProject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (corpProject != null) {
			clearCache(corpProject);
		}

		return corpProject;
	}

	@Override
	public CorpProject updateImpl(CorpProject corpProject) {
		corpProject = toUnwrappedModel(corpProject);

		boolean isNew = corpProject.isNew();

		CorpProjectModelImpl corpProjectModelImpl = (CorpProjectModelImpl)corpProject;

		if (Validator.isNull(corpProject.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			corpProject.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (corpProject.getCreateDate() == null)) {
			if (serviceContext == null) {
				corpProject.setCreateDate(now);
			}
			else {
				corpProject.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!corpProjectModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				corpProject.setModifiedDate(now);
			}
			else {
				corpProject.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (corpProject.isNew()) {
				session.save(corpProject);

				corpProject.setNew(false);
			}
			else {
				corpProject = (CorpProject)session.merge(corpProject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CorpProjectModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { corpProjectModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((corpProjectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						corpProjectModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { corpProjectModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
			CorpProjectImpl.class, corpProject.getPrimaryKey(), corpProject,
			false);

		clearUniqueFindersCache(corpProjectModelImpl, false);
		cacheUniqueFindersCache(corpProjectModelImpl);

		corpProject.resetOriginalValues();

		return corpProject;
	}

	protected CorpProject toUnwrappedModel(CorpProject corpProject) {
		if (corpProject instanceof CorpProjectImpl) {
			return corpProject;
		}

		CorpProjectImpl corpProjectImpl = new CorpProjectImpl();

		corpProjectImpl.setNew(corpProject.isNew());
		corpProjectImpl.setPrimaryKey(corpProject.getPrimaryKey());

		corpProjectImpl.setUuid(corpProject.getUuid());
		corpProjectImpl.setCorpProjectId(corpProject.getCorpProjectId());
		corpProjectImpl.setUserId(corpProject.getUserId());
		corpProjectImpl.setUserName(corpProject.getUserName());
		corpProjectImpl.setCreateDate(corpProject.getCreateDate());
		corpProjectImpl.setModifiedDate(corpProject.getModifiedDate());
		corpProjectImpl.setDossieraProjectKey(corpProject.getDossieraProjectKey());
		corpProjectImpl.setSalesforceProjectKey(corpProject.getSalesforceProjectKey());
		corpProjectImpl.setName(corpProject.getName());
		corpProjectImpl.setOrganizationId(corpProject.getOrganizationId());

		return corpProjectImpl;
	}

	/**
	 * Returns the corp project with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp project
	 * @return the corp project
	 * @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCorpProjectException {
		CorpProject corpProject = fetchByPrimaryKey(primaryKey);

		if (corpProject == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCorpProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return corpProject;
	}

	/**
	 * Returns the corp project with the primary key or throws a {@link NoSuchCorpProjectException} if it could not be found.
	 *
	 * @param corpProjectId the primary key of the corp project
	 * @return the corp project
	 * @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject findByPrimaryKey(long corpProjectId)
		throws NoSuchCorpProjectException {
		return findByPrimaryKey((Serializable)corpProjectId);
	}

	/**
	 * Returns the corp project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the corp project
	 * @return the corp project, or <code>null</code> if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
				CorpProjectImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CorpProject corpProject = (CorpProject)serializable;

		if (corpProject == null) {
			Session session = null;

			try {
				session = openSession();

				corpProject = (CorpProject)session.get(CorpProjectImpl.class,
						primaryKey);

				if (corpProject != null) {
					cacheResult(corpProject);
				}
				else {
					entityCache.putResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
						CorpProjectImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
					CorpProjectImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return corpProject;
	}

	/**
	 * Returns the corp project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param corpProjectId the primary key of the corp project
	 * @return the corp project, or <code>null</code> if a corp project with the primary key could not be found
	 */
	@Override
	public CorpProject fetchByPrimaryKey(long corpProjectId) {
		return fetchByPrimaryKey((Serializable)corpProjectId);
	}

	@Override
	public Map<Serializable, CorpProject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CorpProject> map = new HashMap<Serializable, CorpProject>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CorpProject corpProject = fetchByPrimaryKey(primaryKey);

			if (corpProject != null) {
				map.put(primaryKey, corpProject);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
					CorpProjectImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CorpProject)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CORPPROJECT_WHERE_PKS_IN);

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

			for (CorpProject corpProject : (List<CorpProject>)q.list()) {
				map.put(corpProject.getPrimaryKeyObj(), corpProject);

				cacheResult(corpProject);

				uncachedPrimaryKeys.remove(corpProject.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CorpProjectModelImpl.ENTITY_CACHE_ENABLED,
					CorpProjectImpl.class, primaryKey, nullModel);
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
	 * Returns all the corp projects.
	 *
	 * @return the corp projects
	 */
	@Override
	public List<CorpProject> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the corp projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @return the range of corp projects
	 */
	@Override
	public List<CorpProject> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the corp projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of corp projects
	 */
	@Override
	public List<CorpProject> findAll(int start, int end,
		OrderByComparator<CorpProject> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the corp projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of corp projects
	 * @param end the upper bound of the range of corp projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of corp projects
	 */
	@Override
	public List<CorpProject> findAll(int start, int end,
		OrderByComparator<CorpProject> orderByComparator,
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

		List<CorpProject> list = null;

		if (retrieveFromCache) {
			list = (List<CorpProject>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CORPPROJECT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CORPPROJECT;

				if (pagination) {
					sql = sql.concat(CorpProjectModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CorpProject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CorpProject>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the corp projects from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CorpProject corpProject : findAll()) {
			remove(corpProject);
		}
	}

	/**
	 * Returns the number of corp projects.
	 *
	 * @return the number of corp projects
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CORPPROJECT);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CorpProjectModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the corp project persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CorpProjectImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_CORPPROJECT = "SELECT corpProject FROM CorpProject corpProject";
	private static final String _SQL_SELECT_CORPPROJECT_WHERE_PKS_IN = "SELECT corpProject FROM CorpProject corpProject WHERE corpProjectId IN (";
	private static final String _SQL_SELECT_CORPPROJECT_WHERE = "SELECT corpProject FROM CorpProject corpProject WHERE ";
	private static final String _SQL_COUNT_CORPPROJECT = "SELECT COUNT(corpProject) FROM CorpProject corpProject";
	private static final String _SQL_COUNT_CORPPROJECT_WHERE = "SELECT COUNT(corpProject) FROM CorpProject corpProject WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "corpProject.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CorpProject exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CorpProject exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CorpProjectPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}