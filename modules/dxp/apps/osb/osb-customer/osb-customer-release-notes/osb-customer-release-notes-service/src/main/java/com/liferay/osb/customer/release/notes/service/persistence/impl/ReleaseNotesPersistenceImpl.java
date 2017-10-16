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

package com.liferay.osb.customer.release.notes.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException;
import com.liferay.osb.customer.release.notes.model.ReleaseNotes;
import com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesImpl;
import com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesModelImpl;
import com.liferay.osb.customer.release.notes.service.persistence.ReleaseNotesPersistence;

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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

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
 * The persistence implementation for the release notes service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReleaseNotesPersistence
 * @see com.liferay.osb.customer.release.notes.service.persistence.ReleaseNotesUtil
 * @generated
 */
@ProviderType
public class ReleaseNotesPersistenceImpl extends BasePersistenceImpl<ReleaseNotes>
	implements ReleaseNotesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ReleaseNotesUtil} to access the release notes persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ReleaseNotesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ReleaseNotesModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the release noteses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching release noteses
	 */
	@Override
	public List<ReleaseNotes> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the release noteses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @return the range of matching release noteses
	 */
	@Override
	public List<ReleaseNotes> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the release noteses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching release noteses
	 */
	@Override
	public List<ReleaseNotes> findByUuid(String uuid, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the release noteses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching release noteses
	 */
	@Override
	public List<ReleaseNotes> findByUuid(String uuid, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator,
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

		List<ReleaseNotes> list = null;

		if (retrieveFromCache) {
			list = (List<ReleaseNotes>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReleaseNotes releaseNotes : list) {
					if (!Objects.equals(uuid, releaseNotes.getUuid())) {
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

			query.append(_SQL_SELECT_RELEASENOTES_WHERE);

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
				query.append(ReleaseNotesModelImpl.ORDER_BY_JPQL);
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
					list = (List<ReleaseNotes>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReleaseNotes>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes findByUuid_First(String uuid,
		OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException {
		ReleaseNotes releaseNotes = fetchByUuid_First(uuid, orderByComparator);

		if (releaseNotes != null) {
			return releaseNotes;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchReleaseNotesException(msg.toString());
	}

	/**
	 * Returns the first release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByUuid_First(String uuid,
		OrderByComparator<ReleaseNotes> orderByComparator) {
		List<ReleaseNotes> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes findByUuid_Last(String uuid,
		OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException {
		ReleaseNotes releaseNotes = fetchByUuid_Last(uuid, orderByComparator);

		if (releaseNotes != null) {
			return releaseNotes;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchReleaseNotesException(msg.toString());
	}

	/**
	 * Returns the last release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByUuid_Last(String uuid,
		OrderByComparator<ReleaseNotes> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ReleaseNotes> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the release noteses before and after the current release notes in the ordered set where uuid = &#63;.
	 *
	 * @param releaseNotesId the primary key of the current release notes
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next release notes
	 * @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	 */
	@Override
	public ReleaseNotes[] findByUuid_PrevAndNext(long releaseNotesId,
		String uuid, OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException {
		ReleaseNotes releaseNotes = findByPrimaryKey(releaseNotesId);

		Session session = null;

		try {
			session = openSession();

			ReleaseNotes[] array = new ReleaseNotesImpl[3];

			array[0] = getByUuid_PrevAndNext(session, releaseNotes, uuid,
					orderByComparator, true);

			array[1] = releaseNotes;

			array[2] = getByUuid_PrevAndNext(session, releaseNotes, uuid,
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

	protected ReleaseNotes getByUuid_PrevAndNext(Session session,
		ReleaseNotes releaseNotes, String uuid,
		OrderByComparator<ReleaseNotes> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RELEASENOTES_WHERE);

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
			query.append(ReleaseNotesModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(releaseNotes);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReleaseNotes> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the release noteses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ReleaseNotes releaseNotes : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(releaseNotes);
		}
	}

	/**
	 * Returns the number of release noteses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching release noteses
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RELEASENOTES_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "releaseNotes.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "releaseNotes.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(releaseNotes.uuid IS NULL OR releaseNotes.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			ReleaseNotesModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the release notes where name = &#63; or throws a {@link NoSuchReleaseNotesException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes findByName(String name)
		throws NoSuchReleaseNotesException {
		ReleaseNotes releaseNotes = fetchByName(name);

		if (releaseNotes == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchReleaseNotesException(msg.toString());
		}

		return releaseNotes;
	}

	/**
	 * Returns the release notes where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the release notes where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByName(String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof ReleaseNotes) {
			ReleaseNotes releaseNotes = (ReleaseNotes)result;

			if (!Objects.equals(name, releaseNotes.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_RELEASENOTES_WHERE);

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

				List<ReleaseNotes> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ReleaseNotesPersistenceImpl.fetchByName(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ReleaseNotes releaseNotes = list.get(0);

					result = releaseNotes;

					cacheResult(releaseNotes);

					if ((releaseNotes.getName() == null) ||
							!releaseNotes.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, releaseNotes);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, finderArgs);

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
			return (ReleaseNotes)result;
		}
	}

	/**
	 * Removes the release notes where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the release notes that was removed
	 */
	@Override
	public ReleaseNotes removeByName(String name)
		throws NoSuchReleaseNotesException {
		ReleaseNotes releaseNotes = findByName(name);

		return remove(releaseNotes);
	}

	/**
	 * Returns the number of release noteses where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching release noteses
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RELEASENOTES_WHERE);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "releaseNotes.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "releaseNotes.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(releaseNotes.name IS NULL OR releaseNotes.name = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_JIRAISSUEKEYS = new FinderPath(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByJIRAIssueKeys",
			new String[] { String.class.getName() },
			ReleaseNotesModelImpl.JIRAISSUEKEYS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRAISSUEKEYS = new FinderPath(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJIRAIssueKeys",
			new String[] { String.class.getName() });

	/**
	 * Returns the release notes where jiraIssueKeys = &#63; or throws a {@link NoSuchReleaseNotesException} if it could not be found.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @return the matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes findByJIRAIssueKeys(String jiraIssueKeys)
		throws NoSuchReleaseNotesException {
		ReleaseNotes releaseNotes = fetchByJIRAIssueKeys(jiraIssueKeys);

		if (releaseNotes == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jiraIssueKeys=");
			msg.append(jiraIssueKeys);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchReleaseNotesException(msg.toString());
		}

		return releaseNotes;
	}

	/**
	 * Returns the release notes where jiraIssueKeys = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByJIRAIssueKeys(String jiraIssueKeys) {
		return fetchByJIRAIssueKeys(jiraIssueKeys, true);
	}

	/**
	 * Returns the release notes where jiraIssueKeys = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByJIRAIssueKeys(String jiraIssueKeys,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { jiraIssueKeys };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_JIRAISSUEKEYS,
					finderArgs, this);
		}

		if (result instanceof ReleaseNotes) {
			ReleaseNotes releaseNotes = (ReleaseNotes)result;

			if (!Objects.equals(jiraIssueKeys, releaseNotes.getJiraIssueKeys())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_RELEASENOTES_WHERE);

			boolean bindJiraIssueKeys = false;

			if (jiraIssueKeys == null) {
				query.append(_FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_1);
			}
			else if (jiraIssueKeys.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_3);
			}
			else {
				bindJiraIssueKeys = true;

				query.append(_FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindJiraIssueKeys) {
					qPos.add(jiraIssueKeys);
				}

				List<ReleaseNotes> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_JIRAISSUEKEYS,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ReleaseNotesPersistenceImpl.fetchByJIRAIssueKeys(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ReleaseNotes releaseNotes = list.get(0);

					result = releaseNotes;

					cacheResult(releaseNotes);

					if ((releaseNotes.getJiraIssueKeys() == null) ||
							!releaseNotes.getJiraIssueKeys()
											 .equals(jiraIssueKeys)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_JIRAISSUEKEYS,
							finderArgs, releaseNotes);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_JIRAISSUEKEYS,
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
			return (ReleaseNotes)result;
		}
	}

	/**
	 * Removes the release notes where jiraIssueKeys = &#63; from the database.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @return the release notes that was removed
	 */
	@Override
	public ReleaseNotes removeByJIRAIssueKeys(String jiraIssueKeys)
		throws NoSuchReleaseNotesException {
		ReleaseNotes releaseNotes = findByJIRAIssueKeys(jiraIssueKeys);

		return remove(releaseNotes);
	}

	/**
	 * Returns the number of release noteses where jiraIssueKeys = &#63;.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @return the number of matching release noteses
	 */
	@Override
	public int countByJIRAIssueKeys(String jiraIssueKeys) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JIRAISSUEKEYS;

		Object[] finderArgs = new Object[] { jiraIssueKeys };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RELEASENOTES_WHERE);

			boolean bindJiraIssueKeys = false;

			if (jiraIssueKeys == null) {
				query.append(_FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_1);
			}
			else if (jiraIssueKeys.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_3);
			}
			else {
				bindJiraIssueKeys = true;

				query.append(_FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindJiraIssueKeys) {
					qPos.add(jiraIssueKeys);
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

	private static final String _FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_1 = "releaseNotes.jiraIssueKeys IS NULL";
	private static final String _FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_2 = "releaseNotes.jiraIssueKeys = ?";
	private static final String _FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_3 = "(releaseNotes.jiraIssueKeys IS NULL OR releaseNotes.jiraIssueKeys = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LIKENAME = new FinderPath(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLikeName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LIKENAME =
		new FinderPath(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLikeName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the release noteses where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching release noteses
	 */
	@Override
	public List<ReleaseNotes> findByLikeName(String name) {
		return findByLikeName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the release noteses where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @return the range of matching release noteses
	 */
	@Override
	public List<ReleaseNotes> findByLikeName(String name, int start, int end) {
		return findByLikeName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the release noteses where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching release noteses
	 */
	@Override
	public List<ReleaseNotes> findByLikeName(String name, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator) {
		return findByLikeName(name, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the release noteses where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching release noteses
	 */
	@Override
	public List<ReleaseNotes> findByLikeName(String name, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LIKENAME;
		finderArgs = new Object[] { name, start, end, orderByComparator };

		List<ReleaseNotes> list = null;

		if (retrieveFromCache) {
			list = (List<ReleaseNotes>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReleaseNotes releaseNotes : list) {
					if (!StringUtil.wildcardMatches(releaseNotes.getName(),
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

			query.append(_SQL_SELECT_RELEASENOTES_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_LIKENAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LIKENAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_LIKENAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ReleaseNotesModelImpl.ORDER_BY_JPQL);
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
					list = (List<ReleaseNotes>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReleaseNotes>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes findByLikeName_First(String name,
		OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException {
		ReleaseNotes releaseNotes = fetchByLikeName_First(name,
				orderByComparator);

		if (releaseNotes != null) {
			return releaseNotes;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchReleaseNotesException(msg.toString());
	}

	/**
	 * Returns the first release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByLikeName_First(String name,
		OrderByComparator<ReleaseNotes> orderByComparator) {
		List<ReleaseNotes> list = findByLikeName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes findByLikeName_Last(String name,
		OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException {
		ReleaseNotes releaseNotes = fetchByLikeName_Last(name, orderByComparator);

		if (releaseNotes != null) {
			return releaseNotes;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchReleaseNotesException(msg.toString());
	}

	/**
	 * Returns the last release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByLikeName_Last(String name,
		OrderByComparator<ReleaseNotes> orderByComparator) {
		int count = countByLikeName(name);

		if (count == 0) {
			return null;
		}

		List<ReleaseNotes> list = findByLikeName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the release noteses before and after the current release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param releaseNotesId the primary key of the current release notes
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next release notes
	 * @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	 */
	@Override
	public ReleaseNotes[] findByLikeName_PrevAndNext(long releaseNotesId,
		String name, OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException {
		ReleaseNotes releaseNotes = findByPrimaryKey(releaseNotesId);

		Session session = null;

		try {
			session = openSession();

			ReleaseNotes[] array = new ReleaseNotesImpl[3];

			array[0] = getByLikeName_PrevAndNext(session, releaseNotes, name,
					orderByComparator, true);

			array[1] = releaseNotes;

			array[2] = getByLikeName_PrevAndNext(session, releaseNotes, name,
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

	protected ReleaseNotes getByLikeName_PrevAndNext(Session session,
		ReleaseNotes releaseNotes, String name,
		OrderByComparator<ReleaseNotes> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RELEASENOTES_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_LIKENAME_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LIKENAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_LIKENAME_NAME_2);
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
			query.append(ReleaseNotesModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(releaseNotes);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ReleaseNotes> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the release noteses where name LIKE &#63; from the database.
	 *
	 * @param name the name
	 */
	@Override
	public void removeByLikeName(String name) {
		for (ReleaseNotes releaseNotes : findByLikeName(name,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(releaseNotes);
		}
	}

	/**
	 * Returns the number of release noteses where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching release noteses
	 */
	@Override
	public int countByLikeName(String name) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LIKENAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RELEASENOTES_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_LIKENAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LIKENAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_LIKENAME_NAME_2);
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

	private static final String _FINDER_COLUMN_LIKENAME_NAME_1 = "releaseNotes.name IS NULL";
	private static final String _FINDER_COLUMN_LIKENAME_NAME_2 = "releaseNotes.name LIKE ?";
	private static final String _FINDER_COLUMN_LIKENAME_NAME_3 = "(releaseNotes.name IS NULL OR releaseNotes.name LIKE '')";

	public ReleaseNotesPersistenceImpl() {
		setModelClass(ReleaseNotes.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the release notes in the entity cache if it is enabled.
	 *
	 * @param releaseNotes the release notes
	 */
	@Override
	public void cacheResult(ReleaseNotes releaseNotes) {
		entityCache.putResult(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesImpl.class, releaseNotes.getPrimaryKey(), releaseNotes);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { releaseNotes.getName() }, releaseNotes);

		finderCache.putResult(FINDER_PATH_FETCH_BY_JIRAISSUEKEYS,
			new Object[] { releaseNotes.getJiraIssueKeys() }, releaseNotes);

		releaseNotes.resetOriginalValues();
	}

	/**
	 * Caches the release noteses in the entity cache if it is enabled.
	 *
	 * @param releaseNoteses the release noteses
	 */
	@Override
	public void cacheResult(List<ReleaseNotes> releaseNoteses) {
		for (ReleaseNotes releaseNotes : releaseNoteses) {
			if (entityCache.getResult(
						ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
						ReleaseNotesImpl.class, releaseNotes.getPrimaryKey()) == null) {
				cacheResult(releaseNotes);
			}
			else {
				releaseNotes.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all release noteses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReleaseNotesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the release notes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReleaseNotes releaseNotes) {
		entityCache.removeResult(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesImpl.class, releaseNotes.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ReleaseNotesModelImpl)releaseNotes, true);
	}

	@Override
	public void clearCache(List<ReleaseNotes> releaseNoteses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ReleaseNotes releaseNotes : releaseNoteses) {
			entityCache.removeResult(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
				ReleaseNotesImpl.class, releaseNotes.getPrimaryKey());

			clearUniqueFindersCache((ReleaseNotesModelImpl)releaseNotes, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ReleaseNotesModelImpl releaseNotesModelImpl) {
		Object[] args = new Object[] { releaseNotesModelImpl.getName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args,
			releaseNotesModelImpl, false);

		args = new Object[] { releaseNotesModelImpl.getJiraIssueKeys() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_JIRAISSUEKEYS, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_JIRAISSUEKEYS, args,
			releaseNotesModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ReleaseNotesModelImpl releaseNotesModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { releaseNotesModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if ((releaseNotesModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { releaseNotesModelImpl.getOriginalName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					releaseNotesModelImpl.getJiraIssueKeys()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_JIRAISSUEKEYS, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_JIRAISSUEKEYS, args);
		}

		if ((releaseNotesModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_JIRAISSUEKEYS.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					releaseNotesModelImpl.getOriginalJiraIssueKeys()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_JIRAISSUEKEYS, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_JIRAISSUEKEYS, args);
		}
	}

	/**
	 * Creates a new release notes with the primary key. Does not add the release notes to the database.
	 *
	 * @param releaseNotesId the primary key for the new release notes
	 * @return the new release notes
	 */
	@Override
	public ReleaseNotes create(long releaseNotesId) {
		ReleaseNotes releaseNotes = new ReleaseNotesImpl();

		releaseNotes.setNew(true);
		releaseNotes.setPrimaryKey(releaseNotesId);

		String uuid = PortalUUIDUtil.generate();

		releaseNotes.setUuid(uuid);

		return releaseNotes;
	}

	/**
	 * Removes the release notes with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param releaseNotesId the primary key of the release notes
	 * @return the release notes that was removed
	 * @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	 */
	@Override
	public ReleaseNotes remove(long releaseNotesId)
		throws NoSuchReleaseNotesException {
		return remove((Serializable)releaseNotesId);
	}

	/**
	 * Removes the release notes with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the release notes
	 * @return the release notes that was removed
	 * @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	 */
	@Override
	public ReleaseNotes remove(Serializable primaryKey)
		throws NoSuchReleaseNotesException {
		Session session = null;

		try {
			session = openSession();

			ReleaseNotes releaseNotes = (ReleaseNotes)session.get(ReleaseNotesImpl.class,
					primaryKey);

			if (releaseNotes == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReleaseNotesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(releaseNotes);
		}
		catch (NoSuchReleaseNotesException nsee) {
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
	protected ReleaseNotes removeImpl(ReleaseNotes releaseNotes) {
		releaseNotes = toUnwrappedModel(releaseNotes);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(releaseNotes)) {
				releaseNotes = (ReleaseNotes)session.get(ReleaseNotesImpl.class,
						releaseNotes.getPrimaryKeyObj());
			}

			if (releaseNotes != null) {
				session.delete(releaseNotes);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (releaseNotes != null) {
			clearCache(releaseNotes);
		}

		return releaseNotes;
	}

	@Override
	public ReleaseNotes updateImpl(ReleaseNotes releaseNotes) {
		releaseNotes = toUnwrappedModel(releaseNotes);

		boolean isNew = releaseNotes.isNew();

		ReleaseNotesModelImpl releaseNotesModelImpl = (ReleaseNotesModelImpl)releaseNotes;

		if (Validator.isNull(releaseNotes.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			releaseNotes.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (releaseNotes.getCreateDate() == null)) {
			if (serviceContext == null) {
				releaseNotes.setCreateDate(now);
			}
			else {
				releaseNotes.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!releaseNotesModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				releaseNotes.setModifiedDate(now);
			}
			else {
				releaseNotes.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (releaseNotes.isNew()) {
				session.save(releaseNotes);

				releaseNotes.setNew(false);
			}
			else {
				releaseNotes = (ReleaseNotes)session.merge(releaseNotes);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ReleaseNotesModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { releaseNotesModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((releaseNotesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						releaseNotesModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { releaseNotesModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesImpl.class, releaseNotes.getPrimaryKey(), releaseNotes,
			false);

		clearUniqueFindersCache(releaseNotesModelImpl, false);
		cacheUniqueFindersCache(releaseNotesModelImpl);

		releaseNotes.resetOriginalValues();

		return releaseNotes;
	}

	protected ReleaseNotes toUnwrappedModel(ReleaseNotes releaseNotes) {
		if (releaseNotes instanceof ReleaseNotesImpl) {
			return releaseNotes;
		}

		ReleaseNotesImpl releaseNotesImpl = new ReleaseNotesImpl();

		releaseNotesImpl.setNew(releaseNotes.isNew());
		releaseNotesImpl.setPrimaryKey(releaseNotes.getPrimaryKey());

		releaseNotesImpl.setUuid(releaseNotes.getUuid());
		releaseNotesImpl.setReleaseNotesId(releaseNotes.getReleaseNotesId());
		releaseNotesImpl.setUserId(releaseNotes.getUserId());
		releaseNotesImpl.setUserName(releaseNotes.getUserName());
		releaseNotesImpl.setCreateDate(releaseNotes.getCreateDate());
		releaseNotesImpl.setModifiedDate(releaseNotes.getModifiedDate());
		releaseNotesImpl.setName(releaseNotes.getName());
		releaseNotesImpl.setJiraIssueKeys(releaseNotes.getJiraIssueKeys());

		return releaseNotesImpl;
	}

	/**
	 * Returns the release notes with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the release notes
	 * @return the release notes
	 * @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	 */
	@Override
	public ReleaseNotes findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReleaseNotesException {
		ReleaseNotes releaseNotes = fetchByPrimaryKey(primaryKey);

		if (releaseNotes == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReleaseNotesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return releaseNotes;
	}

	/**
	 * Returns the release notes with the primary key or throws a {@link NoSuchReleaseNotesException} if it could not be found.
	 *
	 * @param releaseNotesId the primary key of the release notes
	 * @return the release notes
	 * @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	 */
	@Override
	public ReleaseNotes findByPrimaryKey(long releaseNotesId)
		throws NoSuchReleaseNotesException {
		return findByPrimaryKey((Serializable)releaseNotesId);
	}

	/**
	 * Returns the release notes with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the release notes
	 * @return the release notes, or <code>null</code> if a release notes with the primary key could not be found
	 */
	@Override
	public ReleaseNotes fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
				ReleaseNotesImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ReleaseNotes releaseNotes = (ReleaseNotes)serializable;

		if (releaseNotes == null) {
			Session session = null;

			try {
				session = openSession();

				releaseNotes = (ReleaseNotes)session.get(ReleaseNotesImpl.class,
						primaryKey);

				if (releaseNotes != null) {
					cacheResult(releaseNotes);
				}
				else {
					entityCache.putResult(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
						ReleaseNotesImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
					ReleaseNotesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return releaseNotes;
	}

	/**
	 * Returns the release notes with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param releaseNotesId the primary key of the release notes
	 * @return the release notes, or <code>null</code> if a release notes with the primary key could not be found
	 */
	@Override
	public ReleaseNotes fetchByPrimaryKey(long releaseNotesId) {
		return fetchByPrimaryKey((Serializable)releaseNotesId);
	}

	@Override
	public Map<Serializable, ReleaseNotes> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ReleaseNotes> map = new HashMap<Serializable, ReleaseNotes>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ReleaseNotes releaseNotes = fetchByPrimaryKey(primaryKey);

			if (releaseNotes != null) {
				map.put(primaryKey, releaseNotes);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
					ReleaseNotesImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ReleaseNotes)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RELEASENOTES_WHERE_PKS_IN);

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

			for (ReleaseNotes releaseNotes : (List<ReleaseNotes>)q.list()) {
				map.put(releaseNotes.getPrimaryKeyObj(), releaseNotes);

				cacheResult(releaseNotes);

				uncachedPrimaryKeys.remove(releaseNotes.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
					ReleaseNotesImpl.class, primaryKey, nullModel);
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
	 * Returns all the release noteses.
	 *
	 * @return the release noteses
	 */
	@Override
	public List<ReleaseNotes> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the release noteses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @return the range of release noteses
	 */
	@Override
	public List<ReleaseNotes> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the release noteses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of release noteses
	 */
	@Override
	public List<ReleaseNotes> findAll(int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the release noteses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of release noteses
	 */
	@Override
	public List<ReleaseNotes> findAll(int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator,
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

		List<ReleaseNotes> list = null;

		if (retrieveFromCache) {
			list = (List<ReleaseNotes>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RELEASENOTES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RELEASENOTES;

				if (pagination) {
					sql = sql.concat(ReleaseNotesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ReleaseNotes>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReleaseNotes>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the release noteses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReleaseNotes releaseNotes : findAll()) {
			remove(releaseNotes);
		}
	}

	/**
	 * Returns the number of release noteses.
	 *
	 * @return the number of release noteses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RELEASENOTES);

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
		return ReleaseNotesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the release notes persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ReleaseNotesImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_RELEASENOTES = "SELECT releaseNotes FROM ReleaseNotes releaseNotes";
	private static final String _SQL_SELECT_RELEASENOTES_WHERE_PKS_IN = "SELECT releaseNotes FROM ReleaseNotes releaseNotes WHERE releaseNotesId IN (";
	private static final String _SQL_SELECT_RELEASENOTES_WHERE = "SELECT releaseNotes FROM ReleaseNotes releaseNotes WHERE ";
	private static final String _SQL_COUNT_RELEASENOTES = "SELECT COUNT(releaseNotes) FROM ReleaseNotes releaseNotes";
	private static final String _SQL_COUNT_RELEASENOTES_WHERE = "SELECT COUNT(releaseNotes) FROM ReleaseNotes releaseNotes WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "releaseNotes.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ReleaseNotes exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ReleaseNotes exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ReleaseNotesPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}