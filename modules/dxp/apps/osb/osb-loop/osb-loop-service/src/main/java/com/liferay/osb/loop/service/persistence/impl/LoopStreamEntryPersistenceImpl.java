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

package com.liferay.osb.loop.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopStreamEntryException;
import com.liferay.osb.loop.model.LoopStreamEntry;
import com.liferay.osb.loop.model.impl.LoopStreamEntryImpl;
import com.liferay.osb.loop.model.impl.LoopStreamEntryModelImpl;
import com.liferay.osb.loop.service.persistence.LoopStreamEntryPersistence;

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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the loop stream entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopStreamEntryPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopStreamEntryUtil
 * @generated
 */
@ProviderType
public class LoopStreamEntryPersistenceImpl extends BasePersistenceImpl<LoopStreamEntry>
	implements LoopStreamEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopStreamEntryUtil} to access the loop stream entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopStreamEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamEntryModelImpl.FINDER_CACHE_ENABLED,
			LoopStreamEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamEntryModelImpl.FINDER_CACHE_ENABLED,
			LoopStreamEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_CPI_CSI_F = new FinderPath(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamEntryModelImpl.FINDER_CACHE_ENABLED,
			LoopStreamEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCPI_CSI_F",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			LoopStreamEntryModelImpl.LOOPPERSONID_COLUMN_BITMASK |
			LoopStreamEntryModelImpl.LOOPSTREAMID_COLUMN_BITMASK |
			LoopStreamEntryModelImpl.FOLLOWING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CPI_CSI_F = new FinderPath(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPI_CSI_F",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and following = &#63; or throws a {@link NoSuchLoopStreamEntryException} if it could not be found.
	 *
	 * @param loopPersonId the loop person ID
	 * @param loopStreamId the loop stream ID
	 * @param following the following
	 * @return the matching loop stream entry
	 * @throws NoSuchLoopStreamEntryException if a matching loop stream entry could not be found
	 */
	@Override
	public LoopStreamEntry findByCPI_CSI_F(long loopPersonId,
		long loopStreamId, boolean following)
		throws NoSuchLoopStreamEntryException {
		LoopStreamEntry loopStreamEntry = fetchByCPI_CSI_F(loopPersonId,
				loopStreamId, following);

		if (loopStreamEntry == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("loopPersonId=");
			msg.append(loopPersonId);

			msg.append(", loopStreamId=");
			msg.append(loopStreamId);

			msg.append(", following=");
			msg.append(following);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoopStreamEntryException(msg.toString());
		}

		return loopStreamEntry;
	}

	/**
	 * Returns the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and following = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param loopPersonId the loop person ID
	 * @param loopStreamId the loop stream ID
	 * @param following the following
	 * @return the matching loop stream entry, or <code>null</code> if a matching loop stream entry could not be found
	 */
	@Override
	public LoopStreamEntry fetchByCPI_CSI_F(long loopPersonId,
		long loopStreamId, boolean following) {
		return fetchByCPI_CSI_F(loopPersonId, loopStreamId, following, true);
	}

	/**
	 * Returns the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and following = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param loopPersonId the loop person ID
	 * @param loopStreamId the loop stream ID
	 * @param following the following
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching loop stream entry, or <code>null</code> if a matching loop stream entry could not be found
	 */
	@Override
	public LoopStreamEntry fetchByCPI_CSI_F(long loopPersonId,
		long loopStreamId, boolean following, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { loopPersonId, loopStreamId, following };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CPI_CSI_F,
					finderArgs, this);
		}

		if (result instanceof LoopStreamEntry) {
			LoopStreamEntry loopStreamEntry = (LoopStreamEntry)result;

			if ((loopPersonId != loopStreamEntry.getLoopPersonId()) ||
					(loopStreamId != loopStreamEntry.getLoopStreamId()) ||
					(following != loopStreamEntry.isFollowing())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_LOOPSTREAMENTRY_WHERE);

			query.append(_FINDER_COLUMN_CPI_CSI_F_LOOPPERSONID_2);

			query.append(_FINDER_COLUMN_CPI_CSI_F_LOOPSTREAMID_2);

			query.append(_FINDER_COLUMN_CPI_CSI_F_FOLLOWING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(loopPersonId);

				qPos.add(loopStreamId);

				qPos.add(following);

				List<LoopStreamEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CPI_CSI_F,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"LoopStreamEntryPersistenceImpl.fetchByCPI_CSI_F(long, long, boolean, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LoopStreamEntry loopStreamEntry = list.get(0);

					result = loopStreamEntry;

					cacheResult(loopStreamEntry);

					if ((loopStreamEntry.getLoopPersonId() != loopPersonId) ||
							(loopStreamEntry.getLoopStreamId() != loopStreamId) ||
							(loopStreamEntry.isFollowing() != following)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_CPI_CSI_F,
							finderArgs, loopStreamEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CPI_CSI_F,
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
			return (LoopStreamEntry)result;
		}
	}

	/**
	 * Removes the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and following = &#63; from the database.
	 *
	 * @param loopPersonId the loop person ID
	 * @param loopStreamId the loop stream ID
	 * @param following the following
	 * @return the loop stream entry that was removed
	 */
	@Override
	public LoopStreamEntry removeByCPI_CSI_F(long loopPersonId,
		long loopStreamId, boolean following)
		throws NoSuchLoopStreamEntryException {
		LoopStreamEntry loopStreamEntry = findByCPI_CSI_F(loopPersonId,
				loopStreamId, following);

		return remove(loopStreamEntry);
	}

	/**
	 * Returns the number of loop stream entries where loopPersonId = &#63; and loopStreamId = &#63; and following = &#63;.
	 *
	 * @param loopPersonId the loop person ID
	 * @param loopStreamId the loop stream ID
	 * @param following the following
	 * @return the number of matching loop stream entries
	 */
	@Override
	public int countByCPI_CSI_F(long loopPersonId, long loopStreamId,
		boolean following) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CPI_CSI_F;

		Object[] finderArgs = new Object[] { loopPersonId, loopStreamId, following };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LOOPSTREAMENTRY_WHERE);

			query.append(_FINDER_COLUMN_CPI_CSI_F_LOOPPERSONID_2);

			query.append(_FINDER_COLUMN_CPI_CSI_F_LOOPSTREAMID_2);

			query.append(_FINDER_COLUMN_CPI_CSI_F_FOLLOWING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(loopPersonId);

				qPos.add(loopStreamId);

				qPos.add(following);

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

	private static final String _FINDER_COLUMN_CPI_CSI_F_LOOPPERSONID_2 = "loopStreamEntry.loopPersonId = ? AND ";
	private static final String _FINDER_COLUMN_CPI_CSI_F_LOOPSTREAMID_2 = "loopStreamEntry.loopStreamId = ? AND ";
	private static final String _FINDER_COLUMN_CPI_CSI_F_FOLLOWING_2 = "loopStreamEntry.following = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_CPI_CNI_CP = new FinderPath(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamEntryModelImpl.FINDER_CACHE_ENABLED,
			LoopStreamEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCPI_CNI_CP",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			LoopStreamEntryModelImpl.LOOPPERSONID_COLUMN_BITMASK |
			LoopStreamEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			LoopStreamEntryModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CPI_CNI_CP = new FinderPath(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPI_CNI_CP",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the loop stream entry where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchLoopStreamEntryException} if it could not be found.
	 *
	 * @param loopPersonId the loop person ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching loop stream entry
	 * @throws NoSuchLoopStreamEntryException if a matching loop stream entry could not be found
	 */
	@Override
	public LoopStreamEntry findByCPI_CNI_CP(long loopPersonId,
		long classNameId, long classPK) throws NoSuchLoopStreamEntryException {
		LoopStreamEntry loopStreamEntry = fetchByCPI_CNI_CP(loopPersonId,
				classNameId, classPK);

		if (loopStreamEntry == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("loopPersonId=");
			msg.append(loopPersonId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoopStreamEntryException(msg.toString());
		}

		return loopStreamEntry;
	}

	/**
	 * Returns the loop stream entry where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param loopPersonId the loop person ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching loop stream entry, or <code>null</code> if a matching loop stream entry could not be found
	 */
	@Override
	public LoopStreamEntry fetchByCPI_CNI_CP(long loopPersonId,
		long classNameId, long classPK) {
		return fetchByCPI_CNI_CP(loopPersonId, classNameId, classPK, true);
	}

	/**
	 * Returns the loop stream entry where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param loopPersonId the loop person ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching loop stream entry, or <code>null</code> if a matching loop stream entry could not be found
	 */
	@Override
	public LoopStreamEntry fetchByCPI_CNI_CP(long loopPersonId,
		long classNameId, long classPK, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { loopPersonId, classNameId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CPI_CNI_CP,
					finderArgs, this);
		}

		if (result instanceof LoopStreamEntry) {
			LoopStreamEntry loopStreamEntry = (LoopStreamEntry)result;

			if ((loopPersonId != loopStreamEntry.getLoopPersonId()) ||
					(classNameId != loopStreamEntry.getClassNameId()) ||
					(classPK != loopStreamEntry.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_LOOPSTREAMENTRY_WHERE);

			query.append(_FINDER_COLUMN_CPI_CNI_CP_LOOPPERSONID_2);

			query.append(_FINDER_COLUMN_CPI_CNI_CP_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CPI_CNI_CP_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(loopPersonId);

				qPos.add(classNameId);

				qPos.add(classPK);

				List<LoopStreamEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CPI_CNI_CP,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"LoopStreamEntryPersistenceImpl.fetchByCPI_CNI_CP(long, long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LoopStreamEntry loopStreamEntry = list.get(0);

					result = loopStreamEntry;

					cacheResult(loopStreamEntry);

					if ((loopStreamEntry.getLoopPersonId() != loopPersonId) ||
							(loopStreamEntry.getClassNameId() != classNameId) ||
							(loopStreamEntry.getClassPK() != classPK)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_CPI_CNI_CP,
							finderArgs, loopStreamEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CPI_CNI_CP,
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
			return (LoopStreamEntry)result;
		}
	}

	/**
	 * Removes the loop stream entry where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param loopPersonId the loop person ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the loop stream entry that was removed
	 */
	@Override
	public LoopStreamEntry removeByCPI_CNI_CP(long loopPersonId,
		long classNameId, long classPK) throws NoSuchLoopStreamEntryException {
		LoopStreamEntry loopStreamEntry = findByCPI_CNI_CP(loopPersonId,
				classNameId, classPK);

		return remove(loopStreamEntry);
	}

	/**
	 * Returns the number of loop stream entries where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param loopPersonId the loop person ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching loop stream entries
	 */
	@Override
	public int countByCPI_CNI_CP(long loopPersonId, long classNameId,
		long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CPI_CNI_CP;

		Object[] finderArgs = new Object[] { loopPersonId, classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LOOPSTREAMENTRY_WHERE);

			query.append(_FINDER_COLUMN_CPI_CNI_CP_LOOPPERSONID_2);

			query.append(_FINDER_COLUMN_CPI_CNI_CP_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CPI_CNI_CP_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(loopPersonId);

				qPos.add(classNameId);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_CPI_CNI_CP_LOOPPERSONID_2 = "loopStreamEntry.loopPersonId = ? AND ";
	private static final String _FINDER_COLUMN_CPI_CNI_CP_CLASSNAMEID_2 = "loopStreamEntry.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_CPI_CNI_CP_CLASSPK_2 = "loopStreamEntry.classPK = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_CPI_CSI_CNI_CP = new FinderPath(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamEntryModelImpl.FINDER_CACHE_ENABLED,
			LoopStreamEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCPI_CSI_CNI_CP",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			LoopStreamEntryModelImpl.LOOPPERSONID_COLUMN_BITMASK |
			LoopStreamEntryModelImpl.LOOPSTREAMID_COLUMN_BITMASK |
			LoopStreamEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			LoopStreamEntryModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CPI_CSI_CNI_CP = new FinderPath(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCPI_CSI_CNI_CP",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchLoopStreamEntryException} if it could not be found.
	 *
	 * @param loopPersonId the loop person ID
	 * @param loopStreamId the loop stream ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching loop stream entry
	 * @throws NoSuchLoopStreamEntryException if a matching loop stream entry could not be found
	 */
	@Override
	public LoopStreamEntry findByCPI_CSI_CNI_CP(long loopPersonId,
		long loopStreamId, long classNameId, long classPK)
		throws NoSuchLoopStreamEntryException {
		LoopStreamEntry loopStreamEntry = fetchByCPI_CSI_CNI_CP(loopPersonId,
				loopStreamId, classNameId, classPK);

		if (loopStreamEntry == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("loopPersonId=");
			msg.append(loopPersonId);

			msg.append(", loopStreamId=");
			msg.append(loopStreamId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoopStreamEntryException(msg.toString());
		}

		return loopStreamEntry;
	}

	/**
	 * Returns the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param loopPersonId the loop person ID
	 * @param loopStreamId the loop stream ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching loop stream entry, or <code>null</code> if a matching loop stream entry could not be found
	 */
	@Override
	public LoopStreamEntry fetchByCPI_CSI_CNI_CP(long loopPersonId,
		long loopStreamId, long classNameId, long classPK) {
		return fetchByCPI_CSI_CNI_CP(loopPersonId, loopStreamId, classNameId,
			classPK, true);
	}

	/**
	 * Returns the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param loopPersonId the loop person ID
	 * @param loopStreamId the loop stream ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching loop stream entry, or <code>null</code> if a matching loop stream entry could not be found
	 */
	@Override
	public LoopStreamEntry fetchByCPI_CSI_CNI_CP(long loopPersonId,
		long loopStreamId, long classNameId, long classPK,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				loopPersonId, loopStreamId, classNameId, classPK
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CPI_CSI_CNI_CP,
					finderArgs, this);
		}

		if (result instanceof LoopStreamEntry) {
			LoopStreamEntry loopStreamEntry = (LoopStreamEntry)result;

			if ((loopPersonId != loopStreamEntry.getLoopPersonId()) ||
					(loopStreamId != loopStreamEntry.getLoopStreamId()) ||
					(classNameId != loopStreamEntry.getClassNameId()) ||
					(classPK != loopStreamEntry.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_LOOPSTREAMENTRY_WHERE);

			query.append(_FINDER_COLUMN_CPI_CSI_CNI_CP_LOOPPERSONID_2);

			query.append(_FINDER_COLUMN_CPI_CSI_CNI_CP_LOOPSTREAMID_2);

			query.append(_FINDER_COLUMN_CPI_CSI_CNI_CP_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CPI_CSI_CNI_CP_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(loopPersonId);

				qPos.add(loopStreamId);

				qPos.add(classNameId);

				qPos.add(classPK);

				List<LoopStreamEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CPI_CSI_CNI_CP,
						finderArgs, list);
				}
				else {
					LoopStreamEntry loopStreamEntry = list.get(0);

					result = loopStreamEntry;

					cacheResult(loopStreamEntry);

					if ((loopStreamEntry.getLoopPersonId() != loopPersonId) ||
							(loopStreamEntry.getLoopStreamId() != loopStreamId) ||
							(loopStreamEntry.getClassNameId() != classNameId) ||
							(loopStreamEntry.getClassPK() != classPK)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_CPI_CSI_CNI_CP,
							finderArgs, loopStreamEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CPI_CSI_CNI_CP,
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
			return (LoopStreamEntry)result;
		}
	}

	/**
	 * Removes the loop stream entry where loopPersonId = &#63; and loopStreamId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param loopPersonId the loop person ID
	 * @param loopStreamId the loop stream ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the loop stream entry that was removed
	 */
	@Override
	public LoopStreamEntry removeByCPI_CSI_CNI_CP(long loopPersonId,
		long loopStreamId, long classNameId, long classPK)
		throws NoSuchLoopStreamEntryException {
		LoopStreamEntry loopStreamEntry = findByCPI_CSI_CNI_CP(loopPersonId,
				loopStreamId, classNameId, classPK);

		return remove(loopStreamEntry);
	}

	/**
	 * Returns the number of loop stream entries where loopPersonId = &#63; and loopStreamId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param loopPersonId the loop person ID
	 * @param loopStreamId the loop stream ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching loop stream entries
	 */
	@Override
	public int countByCPI_CSI_CNI_CP(long loopPersonId, long loopStreamId,
		long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CPI_CSI_CNI_CP;

		Object[] finderArgs = new Object[] {
				loopPersonId, loopStreamId, classNameId, classPK
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_LOOPSTREAMENTRY_WHERE);

			query.append(_FINDER_COLUMN_CPI_CSI_CNI_CP_LOOPPERSONID_2);

			query.append(_FINDER_COLUMN_CPI_CSI_CNI_CP_LOOPSTREAMID_2);

			query.append(_FINDER_COLUMN_CPI_CSI_CNI_CP_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CPI_CSI_CNI_CP_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(loopPersonId);

				qPos.add(loopStreamId);

				qPos.add(classNameId);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_CPI_CSI_CNI_CP_LOOPPERSONID_2 = "loopStreamEntry.loopPersonId = ? AND ";
	private static final String _FINDER_COLUMN_CPI_CSI_CNI_CP_LOOPSTREAMID_2 = "loopStreamEntry.loopStreamId = ? AND ";
	private static final String _FINDER_COLUMN_CPI_CSI_CNI_CP_CLASSNAMEID_2 = "loopStreamEntry.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_CPI_CSI_CNI_CP_CLASSPK_2 = "loopStreamEntry.classPK = ?";

	public LoopStreamEntryPersistenceImpl() {
		setModelClass(LoopStreamEntry.class);
	}

	/**
	 * Caches the loop stream entry in the entity cache if it is enabled.
	 *
	 * @param loopStreamEntry the loop stream entry
	 */
	@Override
	public void cacheResult(LoopStreamEntry loopStreamEntry) {
		entityCache.putResult(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamEntryImpl.class, loopStreamEntry.getPrimaryKey(),
			loopStreamEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CPI_CSI_F,
			new Object[] {
				loopStreamEntry.getLoopPersonId(),
				loopStreamEntry.getLoopStreamId(), loopStreamEntry.isFollowing()
			}, loopStreamEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CPI_CNI_CP,
			new Object[] {
				loopStreamEntry.getLoopPersonId(),
				loopStreamEntry.getClassNameId(), loopStreamEntry.getClassPK()
			}, loopStreamEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CPI_CSI_CNI_CP,
			new Object[] {
				loopStreamEntry.getLoopPersonId(),
				loopStreamEntry.getLoopStreamId(),
				loopStreamEntry.getClassNameId(), loopStreamEntry.getClassPK()
			}, loopStreamEntry);

		loopStreamEntry.resetOriginalValues();
	}

	/**
	 * Caches the loop stream entries in the entity cache if it is enabled.
	 *
	 * @param loopStreamEntries the loop stream entries
	 */
	@Override
	public void cacheResult(List<LoopStreamEntry> loopStreamEntries) {
		for (LoopStreamEntry loopStreamEntry : loopStreamEntries) {
			if (entityCache.getResult(
						LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
						LoopStreamEntryImpl.class,
						loopStreamEntry.getPrimaryKey()) == null) {
				cacheResult(loopStreamEntry);
			}
			else {
				loopStreamEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop stream entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopStreamEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop stream entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopStreamEntry loopStreamEntry) {
		entityCache.removeResult(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamEntryImpl.class, loopStreamEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LoopStreamEntryModelImpl)loopStreamEntry, true);
	}

	@Override
	public void clearCache(List<LoopStreamEntry> loopStreamEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopStreamEntry loopStreamEntry : loopStreamEntries) {
			entityCache.removeResult(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
				LoopStreamEntryImpl.class, loopStreamEntry.getPrimaryKey());

			clearUniqueFindersCache((LoopStreamEntryModelImpl)loopStreamEntry,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		LoopStreamEntryModelImpl loopStreamEntryModelImpl) {
		Object[] args = new Object[] {
				loopStreamEntryModelImpl.getLoopPersonId(),
				loopStreamEntryModelImpl.getLoopStreamId(),
				loopStreamEntryModelImpl.isFollowing()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_CPI_CSI_F, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CPI_CSI_F, args,
			loopStreamEntryModelImpl, false);

		args = new Object[] {
				loopStreamEntryModelImpl.getLoopPersonId(),
				loopStreamEntryModelImpl.getClassNameId(),
				loopStreamEntryModelImpl.getClassPK()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_CPI_CNI_CP, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CPI_CNI_CP, args,
			loopStreamEntryModelImpl, false);

		args = new Object[] {
				loopStreamEntryModelImpl.getLoopPersonId(),
				loopStreamEntryModelImpl.getLoopStreamId(),
				loopStreamEntryModelImpl.getClassNameId(),
				loopStreamEntryModelImpl.getClassPK()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_CPI_CSI_CNI_CP, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CPI_CSI_CNI_CP, args,
			loopStreamEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LoopStreamEntryModelImpl loopStreamEntryModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					loopStreamEntryModelImpl.getLoopPersonId(),
					loopStreamEntryModelImpl.getLoopStreamId(),
					loopStreamEntryModelImpl.isFollowing()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CPI_CSI_F, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CPI_CSI_F, args);
		}

		if ((loopStreamEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CPI_CSI_F.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					loopStreamEntryModelImpl.getOriginalLoopPersonId(),
					loopStreamEntryModelImpl.getOriginalLoopStreamId(),
					loopStreamEntryModelImpl.getOriginalFollowing()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CPI_CSI_F, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CPI_CSI_F, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					loopStreamEntryModelImpl.getLoopPersonId(),
					loopStreamEntryModelImpl.getClassNameId(),
					loopStreamEntryModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CPI_CNI_CP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CPI_CNI_CP, args);
		}

		if ((loopStreamEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CPI_CNI_CP.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					loopStreamEntryModelImpl.getOriginalLoopPersonId(),
					loopStreamEntryModelImpl.getOriginalClassNameId(),
					loopStreamEntryModelImpl.getOriginalClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CPI_CNI_CP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CPI_CNI_CP, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					loopStreamEntryModelImpl.getLoopPersonId(),
					loopStreamEntryModelImpl.getLoopStreamId(),
					loopStreamEntryModelImpl.getClassNameId(),
					loopStreamEntryModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CPI_CSI_CNI_CP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CPI_CSI_CNI_CP, args);
		}

		if ((loopStreamEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CPI_CSI_CNI_CP.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					loopStreamEntryModelImpl.getOriginalLoopPersonId(),
					loopStreamEntryModelImpl.getOriginalLoopStreamId(),
					loopStreamEntryModelImpl.getOriginalClassNameId(),
					loopStreamEntryModelImpl.getOriginalClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CPI_CSI_CNI_CP, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CPI_CSI_CNI_CP, args);
		}
	}

	/**
	 * Creates a new loop stream entry with the primary key. Does not add the loop stream entry to the database.
	 *
	 * @param loopStreamEntryId the primary key for the new loop stream entry
	 * @return the new loop stream entry
	 */
	@Override
	public LoopStreamEntry create(long loopStreamEntryId) {
		LoopStreamEntry loopStreamEntry = new LoopStreamEntryImpl();

		loopStreamEntry.setNew(true);
		loopStreamEntry.setPrimaryKey(loopStreamEntryId);

		return loopStreamEntry;
	}

	/**
	 * Removes the loop stream entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopStreamEntryId the primary key of the loop stream entry
	 * @return the loop stream entry that was removed
	 * @throws NoSuchLoopStreamEntryException if a loop stream entry with the primary key could not be found
	 */
	@Override
	public LoopStreamEntry remove(long loopStreamEntryId)
		throws NoSuchLoopStreamEntryException {
		return remove((Serializable)loopStreamEntryId);
	}

	/**
	 * Removes the loop stream entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop stream entry
	 * @return the loop stream entry that was removed
	 * @throws NoSuchLoopStreamEntryException if a loop stream entry with the primary key could not be found
	 */
	@Override
	public LoopStreamEntry remove(Serializable primaryKey)
		throws NoSuchLoopStreamEntryException {
		Session session = null;

		try {
			session = openSession();

			LoopStreamEntry loopStreamEntry = (LoopStreamEntry)session.get(LoopStreamEntryImpl.class,
					primaryKey);

			if (loopStreamEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopStreamEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopStreamEntry);
		}
		catch (NoSuchLoopStreamEntryException nsee) {
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
	protected LoopStreamEntry removeImpl(LoopStreamEntry loopStreamEntry) {
		loopStreamEntry = toUnwrappedModel(loopStreamEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopStreamEntry)) {
				loopStreamEntry = (LoopStreamEntry)session.get(LoopStreamEntryImpl.class,
						loopStreamEntry.getPrimaryKeyObj());
			}

			if (loopStreamEntry != null) {
				session.delete(loopStreamEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopStreamEntry != null) {
			clearCache(loopStreamEntry);
		}

		return loopStreamEntry;
	}

	@Override
	public LoopStreamEntry updateImpl(LoopStreamEntry loopStreamEntry) {
		loopStreamEntry = toUnwrappedModel(loopStreamEntry);

		boolean isNew = loopStreamEntry.isNew();

		LoopStreamEntryModelImpl loopStreamEntryModelImpl = (LoopStreamEntryModelImpl)loopStreamEntry;

		Session session = null;

		try {
			session = openSession();

			if (loopStreamEntry.isNew()) {
				session.save(loopStreamEntry);

				loopStreamEntry.setNew(false);
			}
			else {
				loopStreamEntry = (LoopStreamEntry)session.merge(loopStreamEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LoopStreamEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
			LoopStreamEntryImpl.class, loopStreamEntry.getPrimaryKey(),
			loopStreamEntry, false);

		clearUniqueFindersCache(loopStreamEntryModelImpl, false);
		cacheUniqueFindersCache(loopStreamEntryModelImpl);

		loopStreamEntry.resetOriginalValues();

		return loopStreamEntry;
	}

	protected LoopStreamEntry toUnwrappedModel(LoopStreamEntry loopStreamEntry) {
		if (loopStreamEntry instanceof LoopStreamEntryImpl) {
			return loopStreamEntry;
		}

		LoopStreamEntryImpl loopStreamEntryImpl = new LoopStreamEntryImpl();

		loopStreamEntryImpl.setNew(loopStreamEntry.isNew());
		loopStreamEntryImpl.setPrimaryKey(loopStreamEntry.getPrimaryKey());

		loopStreamEntryImpl.setLoopStreamEntryId(loopStreamEntry.getLoopStreamEntryId());
		loopStreamEntryImpl.setLoopPersonId(loopStreamEntry.getLoopPersonId());
		loopStreamEntryImpl.setLoopStreamId(loopStreamEntry.getLoopStreamId());
		loopStreamEntryImpl.setClassNameId(loopStreamEntry.getClassNameId());
		loopStreamEntryImpl.setClassPK(loopStreamEntry.getClassPK());
		loopStreamEntryImpl.setFollowing(loopStreamEntry.isFollowing());
		loopStreamEntryImpl.setFollowingType(loopStreamEntry.getFollowingType());

		return loopStreamEntryImpl;
	}

	/**
	 * Returns the loop stream entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop stream entry
	 * @return the loop stream entry
	 * @throws NoSuchLoopStreamEntryException if a loop stream entry with the primary key could not be found
	 */
	@Override
	public LoopStreamEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopStreamEntryException {
		LoopStreamEntry loopStreamEntry = fetchByPrimaryKey(primaryKey);

		if (loopStreamEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopStreamEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopStreamEntry;
	}

	/**
	 * Returns the loop stream entry with the primary key or throws a {@link NoSuchLoopStreamEntryException} if it could not be found.
	 *
	 * @param loopStreamEntryId the primary key of the loop stream entry
	 * @return the loop stream entry
	 * @throws NoSuchLoopStreamEntryException if a loop stream entry with the primary key could not be found
	 */
	@Override
	public LoopStreamEntry findByPrimaryKey(long loopStreamEntryId)
		throws NoSuchLoopStreamEntryException {
		return findByPrimaryKey((Serializable)loopStreamEntryId);
	}

	/**
	 * Returns the loop stream entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop stream entry
	 * @return the loop stream entry, or <code>null</code> if a loop stream entry with the primary key could not be found
	 */
	@Override
	public LoopStreamEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
				LoopStreamEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopStreamEntry loopStreamEntry = (LoopStreamEntry)serializable;

		if (loopStreamEntry == null) {
			Session session = null;

			try {
				session = openSession();

				loopStreamEntry = (LoopStreamEntry)session.get(LoopStreamEntryImpl.class,
						primaryKey);

				if (loopStreamEntry != null) {
					cacheResult(loopStreamEntry);
				}
				else {
					entityCache.putResult(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
						LoopStreamEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
					LoopStreamEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopStreamEntry;
	}

	/**
	 * Returns the loop stream entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopStreamEntryId the primary key of the loop stream entry
	 * @return the loop stream entry, or <code>null</code> if a loop stream entry with the primary key could not be found
	 */
	@Override
	public LoopStreamEntry fetchByPrimaryKey(long loopStreamEntryId) {
		return fetchByPrimaryKey((Serializable)loopStreamEntryId);
	}

	@Override
	public Map<Serializable, LoopStreamEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopStreamEntry> map = new HashMap<Serializable, LoopStreamEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopStreamEntry loopStreamEntry = fetchByPrimaryKey(primaryKey);

			if (loopStreamEntry != null) {
				map.put(primaryKey, loopStreamEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
					LoopStreamEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopStreamEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPSTREAMENTRY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (LoopStreamEntry loopStreamEntry : (List<LoopStreamEntry>)q.list()) {
				map.put(loopStreamEntry.getPrimaryKeyObj(), loopStreamEntry);

				cacheResult(loopStreamEntry);

				uncachedPrimaryKeys.remove(loopStreamEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopStreamEntryModelImpl.ENTITY_CACHE_ENABLED,
					LoopStreamEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop stream entries.
	 *
	 * @return the loop stream entries
	 */
	@Override
	public List<LoopStreamEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop stream entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop stream entries
	 * @param end the upper bound of the range of loop stream entries (not inclusive)
	 * @return the range of loop stream entries
	 */
	@Override
	public List<LoopStreamEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop stream entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop stream entries
	 * @param end the upper bound of the range of loop stream entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop stream entries
	 */
	@Override
	public List<LoopStreamEntry> findAll(int start, int end,
		OrderByComparator<LoopStreamEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop stream entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopStreamEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop stream entries
	 * @param end the upper bound of the range of loop stream entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop stream entries
	 */
	@Override
	public List<LoopStreamEntry> findAll(int start, int end,
		OrderByComparator<LoopStreamEntry> orderByComparator,
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

		List<LoopStreamEntry> list = null;

		if (retrieveFromCache) {
			list = (List<LoopStreamEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPSTREAMENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPSTREAMENTRY;

				if (pagination) {
					sql = sql.concat(LoopStreamEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopStreamEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopStreamEntry>)QueryUtil.list(q,
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
	 * Removes all the loop stream entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopStreamEntry loopStreamEntry : findAll()) {
			remove(loopStreamEntry);
		}
	}

	/**
	 * Returns the number of loop stream entries.
	 *
	 * @return the number of loop stream entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPSTREAMENTRY);

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
		return LoopStreamEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop stream entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopStreamEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOOPSTREAMENTRY = "SELECT loopStreamEntry FROM LoopStreamEntry loopStreamEntry";
	private static final String _SQL_SELECT_LOOPSTREAMENTRY_WHERE_PKS_IN = "SELECT loopStreamEntry FROM LoopStreamEntry loopStreamEntry WHERE loopStreamEntryId IN (";
	private static final String _SQL_SELECT_LOOPSTREAMENTRY_WHERE = "SELECT loopStreamEntry FROM LoopStreamEntry loopStreamEntry WHERE ";
	private static final String _SQL_COUNT_LOOPSTREAMENTRY = "SELECT COUNT(loopStreamEntry) FROM LoopStreamEntry loopStreamEntry";
	private static final String _SQL_COUNT_LOOPSTREAMENTRY_WHERE = "SELECT COUNT(loopStreamEntry) FROM LoopStreamEntry loopStreamEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopStreamEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopStreamEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LoopStreamEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LoopStreamEntryPersistenceImpl.class);
}