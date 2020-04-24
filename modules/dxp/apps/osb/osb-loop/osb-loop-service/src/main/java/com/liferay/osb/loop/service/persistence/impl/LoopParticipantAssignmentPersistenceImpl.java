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

import com.liferay.osb.loop.exception.NoSuchLoopParticipantAssignmentException;
import com.liferay.osb.loop.model.LoopParticipantAssignment;
import com.liferay.osb.loop.model.impl.LoopParticipantAssignmentImpl;
import com.liferay.osb.loop.model.impl.LoopParticipantAssignmentModelImpl;
import com.liferay.osb.loop.service.persistence.LoopParticipantAssignmentPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the loop participant assignment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopParticipantAssignmentPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopParticipantAssignmentUtil
 * @generated
 */
@ProviderType
public class LoopParticipantAssignmentPersistenceImpl
	extends BasePersistenceImpl<LoopParticipantAssignment>
	implements LoopParticipantAssignmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopParticipantAssignmentUtil} to access the loop participant assignment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopParticipantAssignmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopParticipantAssignmentModelImpl.FINDER_CACHE_ENABLED,
			LoopParticipantAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopParticipantAssignmentModelImpl.FINDER_CACHE_ENABLED,
			LoopParticipantAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopParticipantAssignmentModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_LDI_LPI = new FinderPath(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopParticipantAssignmentModelImpl.FINDER_CACHE_ENABLED,
			LoopParticipantAssignmentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByLDI_LPI",
			new String[] { Long.class.getName(), Long.class.getName() },
			LoopParticipantAssignmentModelImpl.LOOPDIVISIONID_COLUMN_BITMASK |
			LoopParticipantAssignmentModelImpl.LOOPPERSONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LDI_LPI = new FinderPath(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopParticipantAssignmentModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLDI_LPI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the loop participant assignment where loopDivisionId = &#63; and loopPersonId = &#63; or throws a {@link NoSuchLoopParticipantAssignmentException} if it could not be found.
	 *
	 * @param loopDivisionId the loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the matching loop participant assignment
	 * @throws NoSuchLoopParticipantAssignmentException if a matching loop participant assignment could not be found
	 */
	@Override
	public LoopParticipantAssignment findByLDI_LPI(long loopDivisionId,
		long loopPersonId) throws NoSuchLoopParticipantAssignmentException {
		LoopParticipantAssignment loopParticipantAssignment = fetchByLDI_LPI(loopDivisionId,
				loopPersonId);

		if (loopParticipantAssignment == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("loopDivisionId=");
			msg.append(loopDivisionId);

			msg.append(", loopPersonId=");
			msg.append(loopPersonId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoopParticipantAssignmentException(msg.toString());
		}

		return loopParticipantAssignment;
	}

	/**
	 * Returns the loop participant assignment where loopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param loopDivisionId the loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the matching loop participant assignment, or <code>null</code> if a matching loop participant assignment could not be found
	 */
	@Override
	public LoopParticipantAssignment fetchByLDI_LPI(long loopDivisionId,
		long loopPersonId) {
		return fetchByLDI_LPI(loopDivisionId, loopPersonId, true);
	}

	/**
	 * Returns the loop participant assignment where loopDivisionId = &#63; and loopPersonId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param loopDivisionId the loop division ID
	 * @param loopPersonId the loop person ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching loop participant assignment, or <code>null</code> if a matching loop participant assignment could not be found
	 */
	@Override
	public LoopParticipantAssignment fetchByLDI_LPI(long loopDivisionId,
		long loopPersonId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { loopDivisionId, loopPersonId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_LDI_LPI,
					finderArgs, this);
		}

		if (result instanceof LoopParticipantAssignment) {
			LoopParticipantAssignment loopParticipantAssignment = (LoopParticipantAssignment)result;

			if ((loopDivisionId != loopParticipantAssignment.getLoopDivisionId()) ||
					(loopPersonId != loopParticipantAssignment.getLoopPersonId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LOOPPARTICIPANTASSIGNMENT_WHERE);

			query.append(_FINDER_COLUMN_LDI_LPI_LOOPDIVISIONID_2);

			query.append(_FINDER_COLUMN_LDI_LPI_LOOPPERSONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(loopDivisionId);

				qPos.add(loopPersonId);

				List<LoopParticipantAssignment> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_LDI_LPI,
						finderArgs, list);
				}
				else {
					LoopParticipantAssignment loopParticipantAssignment = list.get(0);

					result = loopParticipantAssignment;

					cacheResult(loopParticipantAssignment);

					if ((loopParticipantAssignment.getLoopDivisionId() != loopDivisionId) ||
							(loopParticipantAssignment.getLoopPersonId() != loopPersonId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_LDI_LPI,
							finderArgs, loopParticipantAssignment);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_LDI_LPI,
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
			return (LoopParticipantAssignment)result;
		}
	}

	/**
	 * Removes the loop participant assignment where loopDivisionId = &#63; and loopPersonId = &#63; from the database.
	 *
	 * @param loopDivisionId the loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the loop participant assignment that was removed
	 */
	@Override
	public LoopParticipantAssignment removeByLDI_LPI(long loopDivisionId,
		long loopPersonId) throws NoSuchLoopParticipantAssignmentException {
		LoopParticipantAssignment loopParticipantAssignment = findByLDI_LPI(loopDivisionId,
				loopPersonId);

		return remove(loopParticipantAssignment);
	}

	/**
	 * Returns the number of loop participant assignments where loopDivisionId = &#63; and loopPersonId = &#63;.
	 *
	 * @param loopDivisionId the loop division ID
	 * @param loopPersonId the loop person ID
	 * @return the number of matching loop participant assignments
	 */
	@Override
	public int countByLDI_LPI(long loopDivisionId, long loopPersonId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LDI_LPI;

		Object[] finderArgs = new Object[] { loopDivisionId, loopPersonId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LOOPPARTICIPANTASSIGNMENT_WHERE);

			query.append(_FINDER_COLUMN_LDI_LPI_LOOPDIVISIONID_2);

			query.append(_FINDER_COLUMN_LDI_LPI_LOOPPERSONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(loopDivisionId);

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

	private static final String _FINDER_COLUMN_LDI_LPI_LOOPDIVISIONID_2 = "loopParticipantAssignment.loopDivisionId = ? AND ";
	private static final String _FINDER_COLUMN_LDI_LPI_LOOPPERSONID_2 = "loopParticipantAssignment.loopPersonId = ?";

	public LoopParticipantAssignmentPersistenceImpl() {
		setModelClass(LoopParticipantAssignment.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the loop participant assignment in the entity cache if it is enabled.
	 *
	 * @param loopParticipantAssignment the loop participant assignment
	 */
	@Override
	public void cacheResult(LoopParticipantAssignment loopParticipantAssignment) {
		entityCache.putResult(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopParticipantAssignmentImpl.class,
			loopParticipantAssignment.getPrimaryKey(), loopParticipantAssignment);

		finderCache.putResult(FINDER_PATH_FETCH_BY_LDI_LPI,
			new Object[] {
				loopParticipantAssignment.getLoopDivisionId(),
				loopParticipantAssignment.getLoopPersonId()
			}, loopParticipantAssignment);

		loopParticipantAssignment.resetOriginalValues();
	}

	/**
	 * Caches the loop participant assignments in the entity cache if it is enabled.
	 *
	 * @param loopParticipantAssignments the loop participant assignments
	 */
	@Override
	public void cacheResult(
		List<LoopParticipantAssignment> loopParticipantAssignments) {
		for (LoopParticipantAssignment loopParticipantAssignment : loopParticipantAssignments) {
			if (entityCache.getResult(
						LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
						LoopParticipantAssignmentImpl.class,
						loopParticipantAssignment.getPrimaryKey()) == null) {
				cacheResult(loopParticipantAssignment);
			}
			else {
				loopParticipantAssignment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop participant assignments.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopParticipantAssignmentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop participant assignment.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopParticipantAssignment loopParticipantAssignment) {
		entityCache.removeResult(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopParticipantAssignmentImpl.class,
			loopParticipantAssignment.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LoopParticipantAssignmentModelImpl)loopParticipantAssignment,
			true);
	}

	@Override
	public void clearCache(
		List<LoopParticipantAssignment> loopParticipantAssignments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopParticipantAssignment loopParticipantAssignment : loopParticipantAssignments) {
			entityCache.removeResult(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
				LoopParticipantAssignmentImpl.class,
				loopParticipantAssignment.getPrimaryKey());

			clearUniqueFindersCache((LoopParticipantAssignmentModelImpl)loopParticipantAssignment,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		LoopParticipantAssignmentModelImpl loopParticipantAssignmentModelImpl) {
		Object[] args = new Object[] {
				loopParticipantAssignmentModelImpl.getLoopDivisionId(),
				loopParticipantAssignmentModelImpl.getLoopPersonId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_LDI_LPI, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_LDI_LPI, args,
			loopParticipantAssignmentModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LoopParticipantAssignmentModelImpl loopParticipantAssignmentModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					loopParticipantAssignmentModelImpl.getLoopDivisionId(),
					loopParticipantAssignmentModelImpl.getLoopPersonId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LDI_LPI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LDI_LPI, args);
		}

		if ((loopParticipantAssignmentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_LDI_LPI.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					loopParticipantAssignmentModelImpl.getOriginalLoopDivisionId(),
					loopParticipantAssignmentModelImpl.getOriginalLoopPersonId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LDI_LPI, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_LDI_LPI, args);
		}
	}

	/**
	 * Creates a new loop participant assignment with the primary key. Does not add the loop participant assignment to the database.
	 *
	 * @param loopParticipantAssignmentId the primary key for the new loop participant assignment
	 * @return the new loop participant assignment
	 */
	@Override
	public LoopParticipantAssignment create(long loopParticipantAssignmentId) {
		LoopParticipantAssignment loopParticipantAssignment = new LoopParticipantAssignmentImpl();

		loopParticipantAssignment.setNew(true);
		loopParticipantAssignment.setPrimaryKey(loopParticipantAssignmentId);

		return loopParticipantAssignment;
	}

	/**
	 * Removes the loop participant assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopParticipantAssignmentId the primary key of the loop participant assignment
	 * @return the loop participant assignment that was removed
	 * @throws NoSuchLoopParticipantAssignmentException if a loop participant assignment with the primary key could not be found
	 */
	@Override
	public LoopParticipantAssignment remove(long loopParticipantAssignmentId)
		throws NoSuchLoopParticipantAssignmentException {
		return remove((Serializable)loopParticipantAssignmentId);
	}

	/**
	 * Removes the loop participant assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop participant assignment
	 * @return the loop participant assignment that was removed
	 * @throws NoSuchLoopParticipantAssignmentException if a loop participant assignment with the primary key could not be found
	 */
	@Override
	public LoopParticipantAssignment remove(Serializable primaryKey)
		throws NoSuchLoopParticipantAssignmentException {
		Session session = null;

		try {
			session = openSession();

			LoopParticipantAssignment loopParticipantAssignment = (LoopParticipantAssignment)session.get(LoopParticipantAssignmentImpl.class,
					primaryKey);

			if (loopParticipantAssignment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopParticipantAssignmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopParticipantAssignment);
		}
		catch (NoSuchLoopParticipantAssignmentException nsee) {
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
	protected LoopParticipantAssignment removeImpl(
		LoopParticipantAssignment loopParticipantAssignment) {
		loopParticipantAssignment = toUnwrappedModel(loopParticipantAssignment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopParticipantAssignment)) {
				loopParticipantAssignment = (LoopParticipantAssignment)session.get(LoopParticipantAssignmentImpl.class,
						loopParticipantAssignment.getPrimaryKeyObj());
			}

			if (loopParticipantAssignment != null) {
				session.delete(loopParticipantAssignment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopParticipantAssignment != null) {
			clearCache(loopParticipantAssignment);
		}

		return loopParticipantAssignment;
	}

	@Override
	public LoopParticipantAssignment updateImpl(
		LoopParticipantAssignment loopParticipantAssignment) {
		loopParticipantAssignment = toUnwrappedModel(loopParticipantAssignment);

		boolean isNew = loopParticipantAssignment.isNew();

		LoopParticipantAssignmentModelImpl loopParticipantAssignmentModelImpl = (LoopParticipantAssignmentModelImpl)loopParticipantAssignment;

		Session session = null;

		try {
			session = openSession();

			if (loopParticipantAssignment.isNew()) {
				session.save(loopParticipantAssignment);

				loopParticipantAssignment.setNew(false);
			}
			else {
				loopParticipantAssignment = (LoopParticipantAssignment)session.merge(loopParticipantAssignment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LoopParticipantAssignmentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			LoopParticipantAssignmentImpl.class,
			loopParticipantAssignment.getPrimaryKey(),
			loopParticipantAssignment, false);

		clearUniqueFindersCache(loopParticipantAssignmentModelImpl, false);
		cacheUniqueFindersCache(loopParticipantAssignmentModelImpl);

		loopParticipantAssignment.resetOriginalValues();

		return loopParticipantAssignment;
	}

	protected LoopParticipantAssignment toUnwrappedModel(
		LoopParticipantAssignment loopParticipantAssignment) {
		if (loopParticipantAssignment instanceof LoopParticipantAssignmentImpl) {
			return loopParticipantAssignment;
		}

		LoopParticipantAssignmentImpl loopParticipantAssignmentImpl = new LoopParticipantAssignmentImpl();

		loopParticipantAssignmentImpl.setNew(loopParticipantAssignment.isNew());
		loopParticipantAssignmentImpl.setPrimaryKey(loopParticipantAssignment.getPrimaryKey());

		loopParticipantAssignmentImpl.setLoopParticipantAssignmentId(loopParticipantAssignment.getLoopParticipantAssignmentId());
		loopParticipantAssignmentImpl.setLoopDivisionId(loopParticipantAssignment.getLoopDivisionId());
		loopParticipantAssignmentImpl.setLoopPersonId(loopParticipantAssignment.getLoopPersonId());
		loopParticipantAssignmentImpl.setDescription(loopParticipantAssignment.getDescription());
		loopParticipantAssignmentImpl.setType(loopParticipantAssignment.getType());

		return loopParticipantAssignmentImpl;
	}

	/**
	 * Returns the loop participant assignment with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop participant assignment
	 * @return the loop participant assignment
	 * @throws NoSuchLoopParticipantAssignmentException if a loop participant assignment with the primary key could not be found
	 */
	@Override
	public LoopParticipantAssignment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopParticipantAssignmentException {
		LoopParticipantAssignment loopParticipantAssignment = fetchByPrimaryKey(primaryKey);

		if (loopParticipantAssignment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopParticipantAssignmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopParticipantAssignment;
	}

	/**
	 * Returns the loop participant assignment with the primary key or throws a {@link NoSuchLoopParticipantAssignmentException} if it could not be found.
	 *
	 * @param loopParticipantAssignmentId the primary key of the loop participant assignment
	 * @return the loop participant assignment
	 * @throws NoSuchLoopParticipantAssignmentException if a loop participant assignment with the primary key could not be found
	 */
	@Override
	public LoopParticipantAssignment findByPrimaryKey(
		long loopParticipantAssignmentId)
		throws NoSuchLoopParticipantAssignmentException {
		return findByPrimaryKey((Serializable)loopParticipantAssignmentId);
	}

	/**
	 * Returns the loop participant assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop participant assignment
	 * @return the loop participant assignment, or <code>null</code> if a loop participant assignment with the primary key could not be found
	 */
	@Override
	public LoopParticipantAssignment fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
				LoopParticipantAssignmentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopParticipantAssignment loopParticipantAssignment = (LoopParticipantAssignment)serializable;

		if (loopParticipantAssignment == null) {
			Session session = null;

			try {
				session = openSession();

				loopParticipantAssignment = (LoopParticipantAssignment)session.get(LoopParticipantAssignmentImpl.class,
						primaryKey);

				if (loopParticipantAssignment != null) {
					cacheResult(loopParticipantAssignment);
				}
				else {
					entityCache.putResult(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
						LoopParticipantAssignmentImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
					LoopParticipantAssignmentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopParticipantAssignment;
	}

	/**
	 * Returns the loop participant assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopParticipantAssignmentId the primary key of the loop participant assignment
	 * @return the loop participant assignment, or <code>null</code> if a loop participant assignment with the primary key could not be found
	 */
	@Override
	public LoopParticipantAssignment fetchByPrimaryKey(
		long loopParticipantAssignmentId) {
		return fetchByPrimaryKey((Serializable)loopParticipantAssignmentId);
	}

	@Override
	public Map<Serializable, LoopParticipantAssignment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopParticipantAssignment> map = new HashMap<Serializable, LoopParticipantAssignment>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopParticipantAssignment loopParticipantAssignment = fetchByPrimaryKey(primaryKey);

			if (loopParticipantAssignment != null) {
				map.put(primaryKey, loopParticipantAssignment);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
					LoopParticipantAssignmentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopParticipantAssignment)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPPARTICIPANTASSIGNMENT_WHERE_PKS_IN);

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

			for (LoopParticipantAssignment loopParticipantAssignment : (List<LoopParticipantAssignment>)q.list()) {
				map.put(loopParticipantAssignment.getPrimaryKeyObj(),
					loopParticipantAssignment);

				cacheResult(loopParticipantAssignment);

				uncachedPrimaryKeys.remove(loopParticipantAssignment.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopParticipantAssignmentModelImpl.ENTITY_CACHE_ENABLED,
					LoopParticipantAssignmentImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop participant assignments.
	 *
	 * @return the loop participant assignments
	 */
	@Override
	public List<LoopParticipantAssignment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop participant assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopParticipantAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop participant assignments
	 * @param end the upper bound of the range of loop participant assignments (not inclusive)
	 * @return the range of loop participant assignments
	 */
	@Override
	public List<LoopParticipantAssignment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop participant assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopParticipantAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop participant assignments
	 * @param end the upper bound of the range of loop participant assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop participant assignments
	 */
	@Override
	public List<LoopParticipantAssignment> findAll(int start, int end,
		OrderByComparator<LoopParticipantAssignment> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop participant assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopParticipantAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop participant assignments
	 * @param end the upper bound of the range of loop participant assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop participant assignments
	 */
	@Override
	public List<LoopParticipantAssignment> findAll(int start, int end,
		OrderByComparator<LoopParticipantAssignment> orderByComparator,
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

		List<LoopParticipantAssignment> list = null;

		if (retrieveFromCache) {
			list = (List<LoopParticipantAssignment>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPPARTICIPANTASSIGNMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPPARTICIPANTASSIGNMENT;

				if (pagination) {
					sql = sql.concat(LoopParticipantAssignmentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopParticipantAssignment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopParticipantAssignment>)QueryUtil.list(q,
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
	 * Removes all the loop participant assignments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopParticipantAssignment loopParticipantAssignment : findAll()) {
			remove(loopParticipantAssignment);
		}
	}

	/**
	 * Returns the number of loop participant assignments.
	 *
	 * @return the number of loop participant assignments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPPARTICIPANTASSIGNMENT);

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
		return LoopParticipantAssignmentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop participant assignment persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopParticipantAssignmentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOOPPARTICIPANTASSIGNMENT = "SELECT loopParticipantAssignment FROM LoopParticipantAssignment loopParticipantAssignment";
	private static final String _SQL_SELECT_LOOPPARTICIPANTASSIGNMENT_WHERE_PKS_IN =
		"SELECT loopParticipantAssignment FROM LoopParticipantAssignment loopParticipantAssignment WHERE loopParticipantAssignmentId IN (";
	private static final String _SQL_SELECT_LOOPPARTICIPANTASSIGNMENT_WHERE = "SELECT loopParticipantAssignment FROM LoopParticipantAssignment loopParticipantAssignment WHERE ";
	private static final String _SQL_COUNT_LOOPPARTICIPANTASSIGNMENT = "SELECT COUNT(loopParticipantAssignment) FROM LoopParticipantAssignment loopParticipantAssignment";
	private static final String _SQL_COUNT_LOOPPARTICIPANTASSIGNMENT_WHERE = "SELECT COUNT(loopParticipantAssignment) FROM LoopParticipantAssignment loopParticipantAssignment WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopParticipantAssignment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopParticipantAssignment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LoopParticipantAssignment exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LoopParticipantAssignmentPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}