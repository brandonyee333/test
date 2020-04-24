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

import com.liferay.osb.loop.exception.NoSuchLoopDivisionRelException;
import com.liferay.osb.loop.model.LoopDivisionRel;
import com.liferay.osb.loop.model.impl.LoopDivisionRelImpl;
import com.liferay.osb.loop.model.impl.LoopDivisionRelModelImpl;
import com.liferay.osb.loop.service.persistence.LoopDivisionRelPersistence;

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
 * The persistence implementation for the loop division rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopDivisionRelPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopDivisionRelUtil
 * @generated
 */
@ProviderType
public class LoopDivisionRelPersistenceImpl extends BasePersistenceImpl<LoopDivisionRel>
	implements LoopDivisionRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopDivisionRelUtil} to access the loop division rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopDivisionRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionRelModelImpl.FINDER_CACHE_ENABLED,
			LoopDivisionRelImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionRelModelImpl.FINDER_CACHE_ENABLED,
			LoopDivisionRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_CLDI_LPI = new FinderPath(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionRelModelImpl.FINDER_CACHE_ENABLED,
			LoopDivisionRelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCLDI_LPI",
			new String[] { Long.class.getName(), Long.class.getName() },
			LoopDivisionRelModelImpl.CHILDLOOPDIVISIONID_COLUMN_BITMASK |
			LoopDivisionRelModelImpl.LOOPPERSONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLDI_LPI = new FinderPath(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCLDI_LPI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; or throws a {@link NoSuchLoopDivisionRelException} if it could not be found.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the matching loop division rel
	 * @throws NoSuchLoopDivisionRelException if a matching loop division rel could not be found
	 */
	@Override
	public LoopDivisionRel findByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId) throws NoSuchLoopDivisionRelException {
		LoopDivisionRel loopDivisionRel = fetchByCLDI_LPI(childLoopDivisionId,
				loopPersonId);

		if (loopDivisionRel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("childLoopDivisionId=");
			msg.append(childLoopDivisionId);

			msg.append(", loopPersonId=");
			msg.append(loopPersonId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoopDivisionRelException(msg.toString());
		}

		return loopDivisionRel;
	}

	/**
	 * Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	 */
	@Override
	public LoopDivisionRel fetchByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId) {
		return fetchByCLDI_LPI(childLoopDivisionId, loopPersonId, true);
	}

	/**
	 * Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	 */
	@Override
	public LoopDivisionRel fetchByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { childLoopDivisionId, loopPersonId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CLDI_LPI,
					finderArgs, this);
		}

		if (result instanceof LoopDivisionRel) {
			LoopDivisionRel loopDivisionRel = (LoopDivisionRel)result;

			if ((childLoopDivisionId != loopDivisionRel.getChildLoopDivisionId()) ||
					(loopPersonId != loopDivisionRel.getLoopPersonId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LOOPDIVISIONREL_WHERE);

			query.append(_FINDER_COLUMN_CLDI_LPI_CHILDLOOPDIVISIONID_2);

			query.append(_FINDER_COLUMN_CLDI_LPI_LOOPPERSONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(childLoopDivisionId);

				qPos.add(loopPersonId);

				List<LoopDivisionRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CLDI_LPI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"LoopDivisionRelPersistenceImpl.fetchByCLDI_LPI(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LoopDivisionRel loopDivisionRel = list.get(0);

					result = loopDivisionRel;

					cacheResult(loopDivisionRel);

					if ((loopDivisionRel.getChildLoopDivisionId() != childLoopDivisionId) ||
							(loopDivisionRel.getLoopPersonId() != loopPersonId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_CLDI_LPI,
							finderArgs, loopDivisionRel);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CLDI_LPI,
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
			return (LoopDivisionRel)result;
		}
	}

	/**
	 * Removes the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; from the database.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the loop division rel that was removed
	 */
	@Override
	public LoopDivisionRel removeByCLDI_LPI(long childLoopDivisionId,
		long loopPersonId) throws NoSuchLoopDivisionRelException {
		LoopDivisionRel loopDivisionRel = findByCLDI_LPI(childLoopDivisionId,
				loopPersonId);

		return remove(loopDivisionRel);
	}

	/**
	 * Returns the number of loop division rels where childLoopDivisionId = &#63; and loopPersonId = &#63;.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the number of matching loop division rels
	 */
	@Override
	public int countByCLDI_LPI(long childLoopDivisionId, long loopPersonId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLDI_LPI;

		Object[] finderArgs = new Object[] { childLoopDivisionId, loopPersonId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LOOPDIVISIONREL_WHERE);

			query.append(_FINDER_COLUMN_CLDI_LPI_CHILDLOOPDIVISIONID_2);

			query.append(_FINDER_COLUMN_CLDI_LPI_LOOPPERSONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(childLoopDivisionId);

				qPos.add(loopPersonId);

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

	private static final String _FINDER_COLUMN_CLDI_LPI_CHILDLOOPDIVISIONID_2 = "loopDivisionRel.childLoopDivisionId = ? AND ";
	private static final String _FINDER_COLUMN_CLDI_LPI_LOOPPERSONID_2 = "loopDivisionRel.loopPersonId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_LPI_PLDI = new FinderPath(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionRelModelImpl.FINDER_CACHE_ENABLED,
			LoopDivisionRelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByLPI_PLDI",
			new String[] { Long.class.getName(), Long.class.getName() },
			LoopDivisionRelModelImpl.LOOPPERSONID_COLUMN_BITMASK |
			LoopDivisionRelModelImpl.PARENTLOOPDIVISIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LPI_PLDI = new FinderPath(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLPI_PLDI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; or throws a {@link NoSuchLoopDivisionRelException} if it could not be found.
	 *
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the matching loop division rel
	 * @throws NoSuchLoopDivisionRelException if a matching loop division rel could not be found
	 */
	@Override
	public LoopDivisionRel findByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId) throws NoSuchLoopDivisionRelException {
		LoopDivisionRel loopDivisionRel = fetchByLPI_PLDI(loopPersonId,
				parentLoopDivisionId);

		if (loopDivisionRel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("loopPersonId=");
			msg.append(loopPersonId);

			msg.append(", parentLoopDivisionId=");
			msg.append(parentLoopDivisionId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoopDivisionRelException(msg.toString());
		}

		return loopDivisionRel;
	}

	/**
	 * Returns the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	 */
	@Override
	public LoopDivisionRel fetchByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId) {
		return fetchByLPI_PLDI(loopPersonId, parentLoopDivisionId, true);
	}

	/**
	 * Returns the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	 */
	@Override
	public LoopDivisionRel fetchByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { loopPersonId, parentLoopDivisionId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_LPI_PLDI,
					finderArgs, this);
		}

		if (result instanceof LoopDivisionRel) {
			LoopDivisionRel loopDivisionRel = (LoopDivisionRel)result;

			if ((loopPersonId != loopDivisionRel.getLoopPersonId()) ||
					(parentLoopDivisionId != loopDivisionRel.getParentLoopDivisionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LOOPDIVISIONREL_WHERE);

			query.append(_FINDER_COLUMN_LPI_PLDI_LOOPPERSONID_2);

			query.append(_FINDER_COLUMN_LPI_PLDI_PARENTLOOPDIVISIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(loopPersonId);

				qPos.add(parentLoopDivisionId);

				List<LoopDivisionRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_LPI_PLDI,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"LoopDivisionRelPersistenceImpl.fetchByLPI_PLDI(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LoopDivisionRel loopDivisionRel = list.get(0);

					result = loopDivisionRel;

					cacheResult(loopDivisionRel);

					if ((loopDivisionRel.getLoopPersonId() != loopPersonId) ||
							(loopDivisionRel.getParentLoopDivisionId() != parentLoopDivisionId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_LPI_PLDI,
							finderArgs, loopDivisionRel);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_LPI_PLDI,
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
			return (LoopDivisionRel)result;
		}
	}

	/**
	 * Removes the loop division rel where loopPersonId = &#63; and parentLoopDivisionId = &#63; from the database.
	 *
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the loop division rel that was removed
	 */
	@Override
	public LoopDivisionRel removeByLPI_PLDI(long loopPersonId,
		long parentLoopDivisionId) throws NoSuchLoopDivisionRelException {
		LoopDivisionRel loopDivisionRel = findByLPI_PLDI(loopPersonId,
				parentLoopDivisionId);

		return remove(loopDivisionRel);
	}

	/**
	 * Returns the number of loop division rels where loopPersonId = &#63; and parentLoopDivisionId = &#63;.
	 *
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the number of matching loop division rels
	 */
	@Override
	public int countByLPI_PLDI(long loopPersonId, long parentLoopDivisionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LPI_PLDI;

		Object[] finderArgs = new Object[] { loopPersonId, parentLoopDivisionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LOOPDIVISIONREL_WHERE);

			query.append(_FINDER_COLUMN_LPI_PLDI_LOOPPERSONID_2);

			query.append(_FINDER_COLUMN_LPI_PLDI_PARENTLOOPDIVISIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(loopPersonId);

				qPos.add(parentLoopDivisionId);

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

	private static final String _FINDER_COLUMN_LPI_PLDI_LOOPPERSONID_2 = "loopDivisionRel.loopPersonId = ? AND ";
	private static final String _FINDER_COLUMN_LPI_PLDI_PARENTLOOPDIVISIONID_2 = "loopDivisionRel.parentLoopDivisionId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_CLDI_LPI_PLDI = new FinderPath(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionRelModelImpl.FINDER_CACHE_ENABLED,
			LoopDivisionRelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCLDI_LPI_PLDI",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			LoopDivisionRelModelImpl.CHILDLOOPDIVISIONID_COLUMN_BITMASK |
			LoopDivisionRelModelImpl.LOOPPERSONID_COLUMN_BITMASK |
			LoopDivisionRelModelImpl.PARENTLOOPDIVISIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLDI_LPI_PLDI = new FinderPath(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCLDI_LPI_PLDI",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; or throws a {@link NoSuchLoopDivisionRelException} if it could not be found.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the matching loop division rel
	 * @throws NoSuchLoopDivisionRelException if a matching loop division rel could not be found
	 */
	@Override
	public LoopDivisionRel findByCLDI_LPI_PLDI(long childLoopDivisionId,
		long loopPersonId, long parentLoopDivisionId)
		throws NoSuchLoopDivisionRelException {
		LoopDivisionRel loopDivisionRel = fetchByCLDI_LPI_PLDI(childLoopDivisionId,
				loopPersonId, parentLoopDivisionId);

		if (loopDivisionRel == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("childLoopDivisionId=");
			msg.append(childLoopDivisionId);

			msg.append(", loopPersonId=");
			msg.append(loopPersonId);

			msg.append(", parentLoopDivisionId=");
			msg.append(parentLoopDivisionId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoopDivisionRelException(msg.toString());
		}

		return loopDivisionRel;
	}

	/**
	 * Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	 */
	@Override
	public LoopDivisionRel fetchByCLDI_LPI_PLDI(long childLoopDivisionId,
		long loopPersonId, long parentLoopDivisionId) {
		return fetchByCLDI_LPI_PLDI(childLoopDivisionId, loopPersonId,
			parentLoopDivisionId, true);
	}

	/**
	 * Returns the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching loop division rel, or <code>null</code> if a matching loop division rel could not be found
	 */
	@Override
	public LoopDivisionRel fetchByCLDI_LPI_PLDI(long childLoopDivisionId,
		long loopPersonId, long parentLoopDivisionId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				childLoopDivisionId, loopPersonId, parentLoopDivisionId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CLDI_LPI_PLDI,
					finderArgs, this);
		}

		if (result instanceof LoopDivisionRel) {
			LoopDivisionRel loopDivisionRel = (LoopDivisionRel)result;

			if ((childLoopDivisionId != loopDivisionRel.getChildLoopDivisionId()) ||
					(loopPersonId != loopDivisionRel.getLoopPersonId()) ||
					(parentLoopDivisionId != loopDivisionRel.getParentLoopDivisionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_LOOPDIVISIONREL_WHERE);

			query.append(_FINDER_COLUMN_CLDI_LPI_PLDI_CHILDLOOPDIVISIONID_2);

			query.append(_FINDER_COLUMN_CLDI_LPI_PLDI_LOOPPERSONID_2);

			query.append(_FINDER_COLUMN_CLDI_LPI_PLDI_PARENTLOOPDIVISIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(childLoopDivisionId);

				qPos.add(loopPersonId);

				qPos.add(parentLoopDivisionId);

				List<LoopDivisionRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CLDI_LPI_PLDI,
						finderArgs, list);
				}
				else {
					LoopDivisionRel loopDivisionRel = list.get(0);

					result = loopDivisionRel;

					cacheResult(loopDivisionRel);

					if ((loopDivisionRel.getChildLoopDivisionId() != childLoopDivisionId) ||
							(loopDivisionRel.getLoopPersonId() != loopPersonId) ||
							(loopDivisionRel.getParentLoopDivisionId() != parentLoopDivisionId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_CLDI_LPI_PLDI,
							finderArgs, loopDivisionRel);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CLDI_LPI_PLDI,
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
			return (LoopDivisionRel)result;
		}
	}

	/**
	 * Removes the loop division rel where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63; from the database.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the loop division rel that was removed
	 */
	@Override
	public LoopDivisionRel removeByCLDI_LPI_PLDI(long childLoopDivisionId,
		long loopPersonId, long parentLoopDivisionId)
		throws NoSuchLoopDivisionRelException {
		LoopDivisionRel loopDivisionRel = findByCLDI_LPI_PLDI(childLoopDivisionId,
				loopPersonId, parentLoopDivisionId);

		return remove(loopDivisionRel);
	}

	/**
	 * Returns the number of loop division rels where childLoopDivisionId = &#63; and loopPersonId = &#63; and parentLoopDivisionId = &#63;.
	 *
	 * @param childLoopDivisionId the child loop division ID
	 * @param loopPersonId the loop person ID
	 * @param parentLoopDivisionId the parent loop division ID
	 * @return the number of matching loop division rels
	 */
	@Override
	public int countByCLDI_LPI_PLDI(long childLoopDivisionId,
		long loopPersonId, long parentLoopDivisionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLDI_LPI_PLDI;

		Object[] finderArgs = new Object[] {
				childLoopDivisionId, loopPersonId, parentLoopDivisionId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LOOPDIVISIONREL_WHERE);

			query.append(_FINDER_COLUMN_CLDI_LPI_PLDI_CHILDLOOPDIVISIONID_2);

			query.append(_FINDER_COLUMN_CLDI_LPI_PLDI_LOOPPERSONID_2);

			query.append(_FINDER_COLUMN_CLDI_LPI_PLDI_PARENTLOOPDIVISIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(childLoopDivisionId);

				qPos.add(loopPersonId);

				qPos.add(parentLoopDivisionId);

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

	private static final String _FINDER_COLUMN_CLDI_LPI_PLDI_CHILDLOOPDIVISIONID_2 =
		"loopDivisionRel.childLoopDivisionId = ? AND ";
	private static final String _FINDER_COLUMN_CLDI_LPI_PLDI_LOOPPERSONID_2 = "loopDivisionRel.loopPersonId = ? AND ";
	private static final String _FINDER_COLUMN_CLDI_LPI_PLDI_PARENTLOOPDIVISIONID_2 =
		"loopDivisionRel.parentLoopDivisionId = ?";

	public LoopDivisionRelPersistenceImpl() {
		setModelClass(LoopDivisionRel.class);
	}

	/**
	 * Caches the loop division rel in the entity cache if it is enabled.
	 *
	 * @param loopDivisionRel the loop division rel
	 */
	@Override
	public void cacheResult(LoopDivisionRel loopDivisionRel) {
		entityCache.putResult(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionRelImpl.class, loopDivisionRel.getPrimaryKey(),
			loopDivisionRel);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CLDI_LPI,
			new Object[] {
				loopDivisionRel.getChildLoopDivisionId(),
				loopDivisionRel.getLoopPersonId()
			}, loopDivisionRel);

		finderCache.putResult(FINDER_PATH_FETCH_BY_LPI_PLDI,
			new Object[] {
				loopDivisionRel.getLoopPersonId(),
				loopDivisionRel.getParentLoopDivisionId()
			}, loopDivisionRel);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CLDI_LPI_PLDI,
			new Object[] {
				loopDivisionRel.getChildLoopDivisionId(),
				loopDivisionRel.getLoopPersonId(),
				loopDivisionRel.getParentLoopDivisionId()
			}, loopDivisionRel);

		loopDivisionRel.resetOriginalValues();
	}

	/**
	 * Caches the loop division rels in the entity cache if it is enabled.
	 *
	 * @param loopDivisionRels the loop division rels
	 */
	@Override
	public void cacheResult(List<LoopDivisionRel> loopDivisionRels) {
		for (LoopDivisionRel loopDivisionRel : loopDivisionRels) {
			if (entityCache.getResult(
						LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
						LoopDivisionRelImpl.class,
						loopDivisionRel.getPrimaryKey()) == null) {
				cacheResult(loopDivisionRel);
			}
			else {
				loopDivisionRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop division rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopDivisionRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop division rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopDivisionRel loopDivisionRel) {
		entityCache.removeResult(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionRelImpl.class, loopDivisionRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LoopDivisionRelModelImpl)loopDivisionRel, true);
	}

	@Override
	public void clearCache(List<LoopDivisionRel> loopDivisionRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopDivisionRel loopDivisionRel : loopDivisionRels) {
			entityCache.removeResult(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
				LoopDivisionRelImpl.class, loopDivisionRel.getPrimaryKey());

			clearUniqueFindersCache((LoopDivisionRelModelImpl)loopDivisionRel,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		LoopDivisionRelModelImpl loopDivisionRelModelImpl) {
		Object[] args = new Object[] {
				loopDivisionRelModelImpl.getChildLoopDivisionId(),
				loopDivisionRelModelImpl.getLoopPersonId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_CLDI_LPI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CLDI_LPI, args,
			loopDivisionRelModelImpl, false);

		args = new Object[] {
				loopDivisionRelModelImpl.getLoopPersonId(),
				loopDivisionRelModelImpl.getParentLoopDivisionId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_LPI_PLDI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_LPI_PLDI, args,
			loopDivisionRelModelImpl, false);

		args = new Object[] {
				loopDivisionRelModelImpl.getChildLoopDivisionId(),
				loopDivisionRelModelImpl.getLoopPersonId(),
				loopDivisionRelModelImpl.getParentLoopDivisionId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_CLDI_LPI_PLDI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CLDI_LPI_PLDI, args,
			loopDivisionRelModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LoopDivisionRelModelImpl loopDivisionRelModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					loopDivisionRelModelImpl.getChildLoopDivisionId(),
					loopDivisionRelModelImpl.getLoopPersonId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CLDI_LPI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CLDI_LPI, args);
		}

		if ((loopDivisionRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CLDI_LPI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					loopDivisionRelModelImpl.getOriginalChildLoopDivisionId(),
					loopDivisionRelModelImpl.getOriginalLoopPersonId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CLDI_LPI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CLDI_LPI, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					loopDivisionRelModelImpl.getLoopPersonId(),
					loopDivisionRelModelImpl.getParentLoopDivisionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_PLDI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LPI_PLDI, args);
		}

		if ((loopDivisionRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_LPI_PLDI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					loopDivisionRelModelImpl.getOriginalLoopPersonId(),
					loopDivisionRelModelImpl.getOriginalParentLoopDivisionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LPI_PLDI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LPI_PLDI, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					loopDivisionRelModelImpl.getChildLoopDivisionId(),
					loopDivisionRelModelImpl.getLoopPersonId(),
					loopDivisionRelModelImpl.getParentLoopDivisionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CLDI_LPI_PLDI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CLDI_LPI_PLDI, args);
		}

		if ((loopDivisionRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CLDI_LPI_PLDI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					loopDivisionRelModelImpl.getOriginalChildLoopDivisionId(),
					loopDivisionRelModelImpl.getOriginalLoopPersonId(),
					loopDivisionRelModelImpl.getOriginalParentLoopDivisionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CLDI_LPI_PLDI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CLDI_LPI_PLDI, args);
		}
	}

	/**
	 * Creates a new loop division rel with the primary key. Does not add the loop division rel to the database.
	 *
	 * @param loopDivisionRelId the primary key for the new loop division rel
	 * @return the new loop division rel
	 */
	@Override
	public LoopDivisionRel create(long loopDivisionRelId) {
		LoopDivisionRel loopDivisionRel = new LoopDivisionRelImpl();

		loopDivisionRel.setNew(true);
		loopDivisionRel.setPrimaryKey(loopDivisionRelId);

		return loopDivisionRel;
	}

	/**
	 * Removes the loop division rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopDivisionRelId the primary key of the loop division rel
	 * @return the loop division rel that was removed
	 * @throws NoSuchLoopDivisionRelException if a loop division rel with the primary key could not be found
	 */
	@Override
	public LoopDivisionRel remove(long loopDivisionRelId)
		throws NoSuchLoopDivisionRelException {
		return remove((Serializable)loopDivisionRelId);
	}

	/**
	 * Removes the loop division rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop division rel
	 * @return the loop division rel that was removed
	 * @throws NoSuchLoopDivisionRelException if a loop division rel with the primary key could not be found
	 */
	@Override
	public LoopDivisionRel remove(Serializable primaryKey)
		throws NoSuchLoopDivisionRelException {
		Session session = null;

		try {
			session = openSession();

			LoopDivisionRel loopDivisionRel = (LoopDivisionRel)session.get(LoopDivisionRelImpl.class,
					primaryKey);

			if (loopDivisionRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopDivisionRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopDivisionRel);
		}
		catch (NoSuchLoopDivisionRelException nsee) {
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
	protected LoopDivisionRel removeImpl(LoopDivisionRel loopDivisionRel) {
		loopDivisionRel = toUnwrappedModel(loopDivisionRel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopDivisionRel)) {
				loopDivisionRel = (LoopDivisionRel)session.get(LoopDivisionRelImpl.class,
						loopDivisionRel.getPrimaryKeyObj());
			}

			if (loopDivisionRel != null) {
				session.delete(loopDivisionRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopDivisionRel != null) {
			clearCache(loopDivisionRel);
		}

		return loopDivisionRel;
	}

	@Override
	public LoopDivisionRel updateImpl(LoopDivisionRel loopDivisionRel) {
		loopDivisionRel = toUnwrappedModel(loopDivisionRel);

		boolean isNew = loopDivisionRel.isNew();

		LoopDivisionRelModelImpl loopDivisionRelModelImpl = (LoopDivisionRelModelImpl)loopDivisionRel;

		Session session = null;

		try {
			session = openSession();

			if (loopDivisionRel.isNew()) {
				session.save(loopDivisionRel);

				loopDivisionRel.setNew(false);
			}
			else {
				loopDivisionRel = (LoopDivisionRel)session.merge(loopDivisionRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LoopDivisionRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopDivisionRelImpl.class, loopDivisionRel.getPrimaryKey(),
			loopDivisionRel, false);

		clearUniqueFindersCache(loopDivisionRelModelImpl, false);
		cacheUniqueFindersCache(loopDivisionRelModelImpl);

		loopDivisionRel.resetOriginalValues();

		return loopDivisionRel;
	}

	protected LoopDivisionRel toUnwrappedModel(LoopDivisionRel loopDivisionRel) {
		if (loopDivisionRel instanceof LoopDivisionRelImpl) {
			return loopDivisionRel;
		}

		LoopDivisionRelImpl loopDivisionRelImpl = new LoopDivisionRelImpl();

		loopDivisionRelImpl.setNew(loopDivisionRel.isNew());
		loopDivisionRelImpl.setPrimaryKey(loopDivisionRel.getPrimaryKey());

		loopDivisionRelImpl.setLoopDivisionRelId(loopDivisionRel.getLoopDivisionRelId());
		loopDivisionRelImpl.setChildLoopDivisionId(loopDivisionRel.getChildLoopDivisionId());
		loopDivisionRelImpl.setLoopPersonId(loopDivisionRel.getLoopPersonId());
		loopDivisionRelImpl.setParentLoopDivisionId(loopDivisionRel.getParentLoopDivisionId());

		return loopDivisionRelImpl;
	}

	/**
	 * Returns the loop division rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop division rel
	 * @return the loop division rel
	 * @throws NoSuchLoopDivisionRelException if a loop division rel with the primary key could not be found
	 */
	@Override
	public LoopDivisionRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopDivisionRelException {
		LoopDivisionRel loopDivisionRel = fetchByPrimaryKey(primaryKey);

		if (loopDivisionRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopDivisionRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopDivisionRel;
	}

	/**
	 * Returns the loop division rel with the primary key or throws a {@link NoSuchLoopDivisionRelException} if it could not be found.
	 *
	 * @param loopDivisionRelId the primary key of the loop division rel
	 * @return the loop division rel
	 * @throws NoSuchLoopDivisionRelException if a loop division rel with the primary key could not be found
	 */
	@Override
	public LoopDivisionRel findByPrimaryKey(long loopDivisionRelId)
		throws NoSuchLoopDivisionRelException {
		return findByPrimaryKey((Serializable)loopDivisionRelId);
	}

	/**
	 * Returns the loop division rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop division rel
	 * @return the loop division rel, or <code>null</code> if a loop division rel with the primary key could not be found
	 */
	@Override
	public LoopDivisionRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
				LoopDivisionRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopDivisionRel loopDivisionRel = (LoopDivisionRel)serializable;

		if (loopDivisionRel == null) {
			Session session = null;

			try {
				session = openSession();

				loopDivisionRel = (LoopDivisionRel)session.get(LoopDivisionRelImpl.class,
						primaryKey);

				if (loopDivisionRel != null) {
					cacheResult(loopDivisionRel);
				}
				else {
					entityCache.putResult(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
						LoopDivisionRelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
					LoopDivisionRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopDivisionRel;
	}

	/**
	 * Returns the loop division rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopDivisionRelId the primary key of the loop division rel
	 * @return the loop division rel, or <code>null</code> if a loop division rel with the primary key could not be found
	 */
	@Override
	public LoopDivisionRel fetchByPrimaryKey(long loopDivisionRelId) {
		return fetchByPrimaryKey((Serializable)loopDivisionRelId);
	}

	@Override
	public Map<Serializable, LoopDivisionRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopDivisionRel> map = new HashMap<Serializable, LoopDivisionRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopDivisionRel loopDivisionRel = fetchByPrimaryKey(primaryKey);

			if (loopDivisionRel != null) {
				map.put(primaryKey, loopDivisionRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
					LoopDivisionRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopDivisionRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPDIVISIONREL_WHERE_PKS_IN);

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

			for (LoopDivisionRel loopDivisionRel : (List<LoopDivisionRel>)q.list()) {
				map.put(loopDivisionRel.getPrimaryKeyObj(), loopDivisionRel);

				cacheResult(loopDivisionRel);

				uncachedPrimaryKeys.remove(loopDivisionRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopDivisionRelModelImpl.ENTITY_CACHE_ENABLED,
					LoopDivisionRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop division rels.
	 *
	 * @return the loop division rels
	 */
	@Override
	public List<LoopDivisionRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop division rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop division rels
	 * @param end the upper bound of the range of loop division rels (not inclusive)
	 * @return the range of loop division rels
	 */
	@Override
	public List<LoopDivisionRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop division rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop division rels
	 * @param end the upper bound of the range of loop division rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop division rels
	 */
	@Override
	public List<LoopDivisionRel> findAll(int start, int end,
		OrderByComparator<LoopDivisionRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop division rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop division rels
	 * @param end the upper bound of the range of loop division rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop division rels
	 */
	@Override
	public List<LoopDivisionRel> findAll(int start, int end,
		OrderByComparator<LoopDivisionRel> orderByComparator,
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

		List<LoopDivisionRel> list = null;

		if (retrieveFromCache) {
			list = (List<LoopDivisionRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPDIVISIONREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPDIVISIONREL;

				if (pagination) {
					sql = sql.concat(LoopDivisionRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopDivisionRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopDivisionRel>)QueryUtil.list(q,
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
	 * Removes all the loop division rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopDivisionRel loopDivisionRel : findAll()) {
			remove(loopDivisionRel);
		}
	}

	/**
	 * Returns the number of loop division rels.
	 *
	 * @return the number of loop division rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPDIVISIONREL);

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
		return LoopDivisionRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop division rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopDivisionRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOOPDIVISIONREL = "SELECT loopDivisionRel FROM LoopDivisionRel loopDivisionRel";
	private static final String _SQL_SELECT_LOOPDIVISIONREL_WHERE_PKS_IN = "SELECT loopDivisionRel FROM LoopDivisionRel loopDivisionRel WHERE loopDivisionRelId IN (";
	private static final String _SQL_SELECT_LOOPDIVISIONREL_WHERE = "SELECT loopDivisionRel FROM LoopDivisionRel loopDivisionRel WHERE ";
	private static final String _SQL_COUNT_LOOPDIVISIONREL = "SELECT COUNT(loopDivisionRel) FROM LoopDivisionRel loopDivisionRel";
	private static final String _SQL_COUNT_LOOPDIVISIONREL_WHERE = "SELECT COUNT(loopDivisionRel) FROM LoopDivisionRel loopDivisionRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopDivisionRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopDivisionRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LoopDivisionRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LoopDivisionRelPersistenceImpl.class);
}