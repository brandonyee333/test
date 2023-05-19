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

package com.liferay.osb.customer.release.notes.service.persistence.impl;

import com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException;
import com.liferay.osb.customer.release.notes.model.ReleaseNotes;
import com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesImpl;
import com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesModelImpl;
import com.liferay.osb.customer.release.notes.service.persistence.ReleaseNotesPersistence;
import com.liferay.osb.customer.release.notes.service.persistence.ReleaseNotesUtil;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

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
 * @generated
 */
public class ReleaseNotesPersistenceImpl
	extends BasePersistenceImpl<ReleaseNotes>
	implements ReleaseNotesPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ReleaseNotesUtil</code> to access the release notes persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ReleaseNotesImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching release noteses
	 */
	@Override
	public List<ReleaseNotes> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the release noteses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching release noteses
	 */
	@Override
	public List<ReleaseNotes> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<ReleaseNotes> list = null;

		if (useFinderCache) {
			list = (List<ReleaseNotes>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReleaseNotes releaseNotes : list) {
					if (!uuid.equals(releaseNotes.getUuid())) {
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

			sb.append(_SQL_SELECT_RELEASENOTES_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ReleaseNotesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<ReleaseNotes>)QueryUtil.list(
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
	 * Returns the first release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes findByUuid_First(
			String uuid, OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException {

		ReleaseNotes releaseNotes = fetchByUuid_First(uuid, orderByComparator);

		if (releaseNotes != null) {
			return releaseNotes;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchReleaseNotesException(sb.toString());
	}

	/**
	 * Returns the first release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByUuid_First(
		String uuid, OrderByComparator<ReleaseNotes> orderByComparator) {

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
	public ReleaseNotes findByUuid_Last(
			String uuid, OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException {

		ReleaseNotes releaseNotes = fetchByUuid_Last(uuid, orderByComparator);

		if (releaseNotes != null) {
			return releaseNotes;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchReleaseNotesException(sb.toString());
	}

	/**
	 * Returns the last release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByUuid_Last(
		String uuid, OrderByComparator<ReleaseNotes> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ReleaseNotes> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

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
	public ReleaseNotes[] findByUuid_PrevAndNext(
			long releaseNotesId, String uuid,
			OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException {

		uuid = Objects.toString(uuid, "");

		ReleaseNotes releaseNotes = findByPrimaryKey(releaseNotesId);

		Session session = null;

		try {
			session = openSession();

			ReleaseNotes[] array = new ReleaseNotesImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, releaseNotes, uuid, orderByComparator, true);

			array[1] = releaseNotes;

			array[2] = getByUuid_PrevAndNext(
				session, releaseNotes, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ReleaseNotes getByUuid_PrevAndNext(
		Session session, ReleaseNotes releaseNotes, String uuid,
		OrderByComparator<ReleaseNotes> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_RELEASENOTES_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

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
			sb.append(ReleaseNotesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(releaseNotes)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ReleaseNotes> list = query.list();

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
		for (ReleaseNotes releaseNotes :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

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
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_RELEASENOTES_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"releaseNotes.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(releaseNotes.uuid IS NULL OR releaseNotes.uuid = '')";

	private FinderPath _finderPathFetchByName;
	private FinderPath _finderPathCountByName;

	/**
	 * Returns the release notes where name = &#63; or throws a <code>NoSuchReleaseNotesException</code> if it could not be found.
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
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("name=");
			sb.append(name);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchReleaseNotesException(sb.toString());
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
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByName(String name, boolean useFinderCache) {
		name = Objects.toString(name, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {name};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByName, finderArgs, this);
		}

		if (result instanceof ReleaseNotes) {
			ReleaseNotes releaseNotes = (ReleaseNotes)result;

			if (!Objects.equals(name, releaseNotes.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_RELEASENOTES_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindName) {
					queryPos.add(name);
				}

				List<ReleaseNotes> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByName, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {name};
							}

							_log.warn(
								"ReleaseNotesPersistenceImpl.fetchByName(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ReleaseNotes releaseNotes = list.get(0);

					result = releaseNotes;

					cacheResult(releaseNotes);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByName, finderArgs);
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
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByName;

		Object[] finderArgs = new Object[] {name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_RELEASENOTES_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindName) {
					queryPos.add(name);
				}

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

	private static final String _FINDER_COLUMN_NAME_NAME_2 =
		"releaseNotes.name = ?";

	private static final String _FINDER_COLUMN_NAME_NAME_3 =
		"(releaseNotes.name IS NULL OR releaseNotes.name = '')";

	private FinderPath _finderPathWithPaginationFindByLikeName;
	private FinderPath _finderPathWithPaginationCountByLikeName;

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching release noteses
	 */
	@Override
	public List<ReleaseNotes> findByLikeName(
		String name, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator) {

		return findByLikeName(name, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the release noteses where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching release noteses
	 */
	@Override
	public List<ReleaseNotes> findByLikeName(
		String name, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator,
		boolean useFinderCache) {

		name = Objects.toString(name, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByLikeName;
		finderArgs = new Object[] {name, start, end, orderByComparator};

		List<ReleaseNotes> list = null;

		if (useFinderCache) {
			list = (List<ReleaseNotes>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ReleaseNotes releaseNotes : list) {
					if (!StringUtil.wildcardMatches(
							releaseNotes.getName(), name, '_', '%', '\\',
							true)) {

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

			sb.append(_SQL_SELECT_RELEASENOTES_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_LIKENAME_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_LIKENAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ReleaseNotesModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindName) {
					queryPos.add(name);
				}

				list = (List<ReleaseNotes>)QueryUtil.list(
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
	 * Returns the first release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes findByLikeName_First(
			String name, OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException {

		ReleaseNotes releaseNotes = fetchByLikeName_First(
			name, orderByComparator);

		if (releaseNotes != null) {
			return releaseNotes;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("nameLIKE");
		sb.append(name);

		sb.append("}");

		throw new NoSuchReleaseNotesException(sb.toString());
	}

	/**
	 * Returns the first release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByLikeName_First(
		String name, OrderByComparator<ReleaseNotes> orderByComparator) {

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
	public ReleaseNotes findByLikeName_Last(
			String name, OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException {

		ReleaseNotes releaseNotes = fetchByLikeName_Last(
			name, orderByComparator);

		if (releaseNotes != null) {
			return releaseNotes;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("nameLIKE");
		sb.append(name);

		sb.append("}");

		throw new NoSuchReleaseNotesException(sb.toString());
	}

	/**
	 * Returns the last release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByLikeName_Last(
		String name, OrderByComparator<ReleaseNotes> orderByComparator) {

		int count = countByLikeName(name);

		if (count == 0) {
			return null;
		}

		List<ReleaseNotes> list = findByLikeName(
			name, count - 1, count, orderByComparator);

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
	public ReleaseNotes[] findByLikeName_PrevAndNext(
			long releaseNotesId, String name,
			OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException {

		name = Objects.toString(name, "");

		ReleaseNotes releaseNotes = findByPrimaryKey(releaseNotesId);

		Session session = null;

		try {
			session = openSession();

			ReleaseNotes[] array = new ReleaseNotesImpl[3];

			array[0] = getByLikeName_PrevAndNext(
				session, releaseNotes, name, orderByComparator, true);

			array[1] = releaseNotes;

			array[2] = getByLikeName_PrevAndNext(
				session, releaseNotes, name, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ReleaseNotes getByLikeName_PrevAndNext(
		Session session, ReleaseNotes releaseNotes, String name,
		OrderByComparator<ReleaseNotes> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_RELEASENOTES_WHERE);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_LIKENAME_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_LIKENAME_NAME_2);
		}

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
			sb.append(ReleaseNotesModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindName) {
			queryPos.add(name);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(releaseNotes)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ReleaseNotes> list = query.list();

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
		for (ReleaseNotes releaseNotes :
				findByLikeName(
					name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

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
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathWithPaginationCountByLikeName;

		Object[] finderArgs = new Object[] {name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_RELEASENOTES_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_LIKENAME_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_LIKENAME_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindName) {
					queryPos.add(name);
				}

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

	private static final String _FINDER_COLUMN_LIKENAME_NAME_2 =
		"releaseNotes.name LIKE ?";

	private static final String _FINDER_COLUMN_LIKENAME_NAME_3 =
		"(releaseNotes.name IS NULL OR releaseNotes.name LIKE '')";

	private FinderPath _finderPathFetchByJIRAIssueKeys;
	private FinderPath _finderPathCountByJIRAIssueKeys;

	/**
	 * Returns the release notes where jiraIssueKeys = &#63; or throws a <code>NoSuchReleaseNotesException</code> if it could not be found.
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
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("jiraIssueKeys=");
			sb.append(jiraIssueKeys);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchReleaseNotesException(sb.toString());
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
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	@Override
	public ReleaseNotes fetchByJIRAIssueKeys(
		String jiraIssueKeys, boolean useFinderCache) {

		jiraIssueKeys = Objects.toString(jiraIssueKeys, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {jiraIssueKeys};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByJIRAIssueKeys, finderArgs, this);
		}

		if (result instanceof ReleaseNotes) {
			ReleaseNotes releaseNotes = (ReleaseNotes)result;

			if (!Objects.equals(
					jiraIssueKeys, releaseNotes.getJiraIssueKeys())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_RELEASENOTES_WHERE);

			boolean bindJiraIssueKeys = false;

			if (jiraIssueKeys.isEmpty()) {
				sb.append(_FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_3);
			}
			else {
				bindJiraIssueKeys = true;

				sb.append(_FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindJiraIssueKeys) {
					queryPos.add(jiraIssueKeys);
				}

				List<ReleaseNotes> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByJIRAIssueKeys, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {jiraIssueKeys};
							}

							_log.warn(
								"ReleaseNotesPersistenceImpl.fetchByJIRAIssueKeys(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ReleaseNotes releaseNotes = list.get(0);

					result = releaseNotes;

					cacheResult(releaseNotes);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByJIRAIssueKeys, finderArgs);
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
		jiraIssueKeys = Objects.toString(jiraIssueKeys, "");

		FinderPath finderPath = _finderPathCountByJIRAIssueKeys;

		Object[] finderArgs = new Object[] {jiraIssueKeys};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_RELEASENOTES_WHERE);

			boolean bindJiraIssueKeys = false;

			if (jiraIssueKeys.isEmpty()) {
				sb.append(_FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_3);
			}
			else {
				bindJiraIssueKeys = true;

				sb.append(_FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindJiraIssueKeys) {
					queryPos.add(jiraIssueKeys);
				}

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

	private static final String _FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_2 =
		"releaseNotes.jiraIssueKeys = ?";

	private static final String _FINDER_COLUMN_JIRAISSUEKEYS_JIRAISSUEKEYS_3 =
		"(releaseNotes.jiraIssueKeys IS NULL OR releaseNotes.jiraIssueKeys = '')";

	public ReleaseNotesPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		setModelClass(ReleaseNotes.class);
	}

	/**
	 * Caches the release notes in the entity cache if it is enabled.
	 *
	 * @param releaseNotes the release notes
	 */
	@Override
	public void cacheResult(ReleaseNotes releaseNotes) {
		entityCache.putResult(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED, ReleaseNotesImpl.class,
			releaseNotes.getPrimaryKey(), releaseNotes);

		finderCache.putResult(
			_finderPathFetchByName, new Object[] {releaseNotes.getName()},
			releaseNotes);

		finderCache.putResult(
			_finderPathFetchByJIRAIssueKeys,
			new Object[] {releaseNotes.getJiraIssueKeys()}, releaseNotes);

		releaseNotes.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the release noteses in the entity cache if it is enabled.
	 *
	 * @param releaseNoteses the release noteses
	 */
	@Override
	public void cacheResult(List<ReleaseNotes> releaseNoteses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (releaseNoteses.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ReleaseNotes releaseNotes : releaseNoteses) {
			if (entityCache.getResult(
					ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
					ReleaseNotesImpl.class, releaseNotes.getPrimaryKey()) ==
						null) {

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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
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
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReleaseNotes releaseNotes) {
		entityCache.removeResult(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED, ReleaseNotesImpl.class,
			releaseNotes.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ReleaseNotesModelImpl)releaseNotes, true);
	}

	@Override
	public void clearCache(List<ReleaseNotes> releaseNoteses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ReleaseNotes releaseNotes : releaseNoteses) {
			entityCache.removeResult(
				ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
				ReleaseNotesImpl.class, releaseNotes.getPrimaryKey());

			clearUniqueFindersCache((ReleaseNotesModelImpl)releaseNotes, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
				ReleaseNotesImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ReleaseNotesModelImpl releaseNotesModelImpl) {

		Object[] args = new Object[] {releaseNotesModelImpl.getName()};

		finderCache.putResult(
			_finderPathCountByName, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByName, args, releaseNotesModelImpl, false);

		args = new Object[] {releaseNotesModelImpl.getJiraIssueKeys()};

		finderCache.putResult(
			_finderPathCountByJIRAIssueKeys, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByJIRAIssueKeys, args, releaseNotesModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		ReleaseNotesModelImpl releaseNotesModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {releaseNotesModelImpl.getName()};

			finderCache.removeResult(_finderPathCountByName, args);
			finderCache.removeResult(_finderPathFetchByName, args);
		}

		if ((releaseNotesModelImpl.getColumnBitmask() &
			 _finderPathFetchByName.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				releaseNotesModelImpl.getOriginalName()
			};

			finderCache.removeResult(_finderPathCountByName, args);
			finderCache.removeResult(_finderPathFetchByName, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				releaseNotesModelImpl.getJiraIssueKeys()
			};

			finderCache.removeResult(_finderPathCountByJIRAIssueKeys, args);
			finderCache.removeResult(_finderPathFetchByJIRAIssueKeys, args);
		}

		if ((releaseNotesModelImpl.getColumnBitmask() &
			 _finderPathFetchByJIRAIssueKeys.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				releaseNotesModelImpl.getOriginalJiraIssueKeys()
			};

			finderCache.removeResult(_finderPathCountByJIRAIssueKeys, args);
			finderCache.removeResult(_finderPathFetchByJIRAIssueKeys, args);
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

			ReleaseNotes releaseNotes = (ReleaseNotes)session.get(
				ReleaseNotesImpl.class, primaryKey);

			if (releaseNotes == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReleaseNotesException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(releaseNotes);
		}
		catch (NoSuchReleaseNotesException noSuchEntityException) {
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
	protected ReleaseNotes removeImpl(ReleaseNotes releaseNotes) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(releaseNotes)) {
				releaseNotes = (ReleaseNotes)session.get(
					ReleaseNotesImpl.class, releaseNotes.getPrimaryKeyObj());
			}

			if (releaseNotes != null) {
				session.delete(releaseNotes);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
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
		boolean isNew = releaseNotes.isNew();

		if (!(releaseNotes instanceof ReleaseNotesModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(releaseNotes.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					releaseNotes);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in releaseNotes proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ReleaseNotes implementation " +
					releaseNotes.getClass());
		}

		ReleaseNotesModelImpl releaseNotesModelImpl =
			(ReleaseNotesModelImpl)releaseNotes;

		if (Validator.isNull(releaseNotes.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			releaseNotes.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (releaseNotes.getCreateDate() == null)) {
			if (serviceContext == null) {
				releaseNotes.setCreateDate(date);
			}
			else {
				releaseNotes.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!releaseNotesModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				releaseNotes.setModifiedDate(date);
			}
			else {
				releaseNotes.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(releaseNotes);

				releaseNotes.setNew(false);
			}
			else {
				releaseNotes = (ReleaseNotes)session.merge(releaseNotes);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ReleaseNotesModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {releaseNotesModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((releaseNotesModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					releaseNotesModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {releaseNotesModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}
		}

		entityCache.putResult(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED, ReleaseNotesImpl.class,
			releaseNotes.getPrimaryKey(), releaseNotes, false);

		clearUniqueFindersCache(releaseNotesModelImpl, false);
		cacheUniqueFindersCache(releaseNotesModelImpl);

		releaseNotes.resetOriginalValues();

		return releaseNotes;
	}

	/**
	 * Returns the release notes with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
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

			throw new NoSuchReleaseNotesException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return releaseNotes;
	}

	/**
	 * Returns the release notes with the primary key or throws a <code>NoSuchReleaseNotesException</code> if it could not be found.
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
		Serializable serializable = entityCache.getResult(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED, ReleaseNotesImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ReleaseNotes releaseNotes = (ReleaseNotes)serializable;

		if (releaseNotes == null) {
			Session session = null;

			try {
				session = openSession();

				releaseNotes = (ReleaseNotes)session.get(
					ReleaseNotesImpl.class, primaryKey);

				if (releaseNotes != null) {
					cacheResult(releaseNotes);
				}
				else {
					entityCache.putResult(
						ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
						ReleaseNotesImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
					ReleaseNotesImpl.class, primaryKey);

				throw processException(exception);
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

		Map<Serializable, ReleaseNotes> map =
			new HashMap<Serializable, ReleaseNotes>();

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
			Serializable serializable = entityCache.getResult(
				ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
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

		StringBundler sb = new StringBundler(
			(uncachedPrimaryKeys.size() * 2) + 1);

		sb.append(_SQL_SELECT_RELEASENOTES_WHERE_PKS_IN);

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

			for (ReleaseNotes releaseNotes : (List<ReleaseNotes>)query.list()) {
				map.put(releaseNotes.getPrimaryKeyObj(), releaseNotes);

				cacheResult(releaseNotes);

				uncachedPrimaryKeys.remove(releaseNotes.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
					ReleaseNotesImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of release noteses
	 */
	@Override
	public List<ReleaseNotes> findAll(
		int start, int end, OrderByComparator<ReleaseNotes> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the release noteses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of release noteses
	 */
	@Override
	public List<ReleaseNotes> findAll(
		int start, int end, OrderByComparator<ReleaseNotes> orderByComparator,
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

		List<ReleaseNotes> list = null;

		if (useFinderCache) {
			list = (List<ReleaseNotes>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_RELEASENOTES);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_RELEASENOTES;

				sql = sql.concat(ReleaseNotesModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ReleaseNotes>)QueryUtil.list(
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
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_RELEASENOTES);

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
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get("value.object.finder.cache.list.threshold"));

		_finderPathWithPaginationFindAll = new FinderPath(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			ReleaseNotesModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByName = new FinderPath(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] {String.class.getName()},
			ReleaseNotesModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByName = new FinderPath(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByLikeName = new FinderPath(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLikeName",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByLikeName = new FinderPath(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLikeName",
			new String[] {String.class.getName()});

		_finderPathFetchByJIRAIssueKeys = new FinderPath(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, ReleaseNotesImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByJIRAIssueKeys",
			new String[] {String.class.getName()},
			ReleaseNotesModelImpl.JIRAISSUEKEYS_COLUMN_BITMASK);

		_finderPathCountByJIRAIssueKeys = new FinderPath(
			ReleaseNotesModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseNotesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJIRAIssueKeys",
			new String[] {String.class.getName()});

		ReleaseNotesUtil.setPersistence(this);
	}

	public void destroy() {
		ReleaseNotesUtil.setPersistence(null);

		entityCache.removeCache(ReleaseNotesImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_RELEASENOTES =
		"SELECT releaseNotes FROM ReleaseNotes releaseNotes";

	private static final String _SQL_SELECT_RELEASENOTES_WHERE_PKS_IN =
		"SELECT releaseNotes FROM ReleaseNotes releaseNotes WHERE releaseNotesId IN (";

	private static final String _SQL_SELECT_RELEASENOTES_WHERE =
		"SELECT releaseNotes FROM ReleaseNotes releaseNotes WHERE ";

	private static final String _SQL_COUNT_RELEASENOTES =
		"SELECT COUNT(releaseNotes) FROM ReleaseNotes releaseNotes";

	private static final String _SQL_COUNT_RELEASENOTES_WHERE =
		"SELECT COUNT(releaseNotes) FROM ReleaseNotes releaseNotes WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "releaseNotes.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ReleaseNotes exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ReleaseNotes exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ReleaseNotesPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}