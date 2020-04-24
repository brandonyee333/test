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

import com.liferay.osb.loop.exception.NoSuchLoopExternalReferenceRelException;
import com.liferay.osb.loop.model.LoopExternalReferenceRel;
import com.liferay.osb.loop.model.impl.LoopExternalReferenceRelImpl;
import com.liferay.osb.loop.model.impl.LoopExternalReferenceRelModelImpl;
import com.liferay.osb.loop.service.persistence.LoopExternalReferenceRelPersistence;

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
 * The persistence implementation for the loop external reference rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopExternalReferenceRelPersistence
 * @see com.liferay.osb.loop.service.persistence.LoopExternalReferenceRelUtil
 * @generated
 */
@ProviderType
public class LoopExternalReferenceRelPersistenceImpl extends BasePersistenceImpl<LoopExternalReferenceRel>
	implements LoopExternalReferenceRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoopExternalReferenceRelUtil} to access the loop external reference rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoopExternalReferenceRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopExternalReferenceRelModelImpl.FINDER_CACHE_ENABLED,
			LoopExternalReferenceRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopExternalReferenceRelModelImpl.FINDER_CACHE_ENABLED,
			LoopExternalReferenceRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopExternalReferenceRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_ERP_ESN = new FinderPath(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopExternalReferenceRelModelImpl.FINDER_CACHE_ENABLED,
			LoopExternalReferenceRelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByERP_ESN",
			new String[] { String.class.getName(), String.class.getName() },
			LoopExternalReferenceRelModelImpl.EXTERNALREFERENCENAME_COLUMN_BITMASK |
			LoopExternalReferenceRelModelImpl.EXTERNALREFERENCEPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ERP_ESN = new FinderPath(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopExternalReferenceRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByERP_ESN",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns the loop external reference rel where externalReferenceName = &#63; and externalReferencePK = &#63; or throws a {@link NoSuchLoopExternalReferenceRelException} if it could not be found.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @return the matching loop external reference rel
	 * @throws NoSuchLoopExternalReferenceRelException if a matching loop external reference rel could not be found
	 */
	@Override
	public LoopExternalReferenceRel findByERP_ESN(
		String externalReferenceName, String externalReferencePK)
		throws NoSuchLoopExternalReferenceRelException {
		LoopExternalReferenceRel loopExternalReferenceRel = fetchByERP_ESN(externalReferenceName,
				externalReferencePK);

		if (loopExternalReferenceRel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("externalReferenceName=");
			msg.append(externalReferenceName);

			msg.append(", externalReferencePK=");
			msg.append(externalReferencePK);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoopExternalReferenceRelException(msg.toString());
		}

		return loopExternalReferenceRel;
	}

	/**
	 * Returns the loop external reference rel where externalReferenceName = &#63; and externalReferencePK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @return the matching loop external reference rel, or <code>null</code> if a matching loop external reference rel could not be found
	 */
	@Override
	public LoopExternalReferenceRel fetchByERP_ESN(
		String externalReferenceName, String externalReferencePK) {
		return fetchByERP_ESN(externalReferenceName, externalReferencePK, true);
	}

	/**
	 * Returns the loop external reference rel where externalReferenceName = &#63; and externalReferencePK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching loop external reference rel, or <code>null</code> if a matching loop external reference rel could not be found
	 */
	@Override
	public LoopExternalReferenceRel fetchByERP_ESN(
		String externalReferenceName, String externalReferencePK,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				externalReferenceName, externalReferencePK
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_ERP_ESN,
					finderArgs, this);
		}

		if (result instanceof LoopExternalReferenceRel) {
			LoopExternalReferenceRel loopExternalReferenceRel = (LoopExternalReferenceRel)result;

			if (!Objects.equals(externalReferenceName,
						loopExternalReferenceRel.getExternalReferenceName()) ||
					!Objects.equals(externalReferencePK,
						loopExternalReferenceRel.getExternalReferencePK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LOOPEXTERNALREFERENCEREL_WHERE);

			boolean bindExternalReferenceName = false;

			if (externalReferenceName == null) {
				query.append(_FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCENAME_1);
			}
			else if (externalReferenceName.equals("")) {
				query.append(_FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCENAME_3);
			}
			else {
				bindExternalReferenceName = true;

				query.append(_FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCENAME_2);
			}

			boolean bindExternalReferencePK = false;

			if (externalReferencePK == null) {
				query.append(_FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCEPK_1);
			}
			else if (externalReferencePK.equals("")) {
				query.append(_FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCEPK_3);
			}
			else {
				bindExternalReferencePK = true;

				query.append(_FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCEPK_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExternalReferenceName) {
					qPos.add(externalReferenceName);
				}

				if (bindExternalReferencePK) {
					qPos.add(externalReferencePK);
				}

				List<LoopExternalReferenceRel> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_ERP_ESN,
						finderArgs, list);
				}
				else {
					LoopExternalReferenceRel loopExternalReferenceRel = list.get(0);

					result = loopExternalReferenceRel;

					cacheResult(loopExternalReferenceRel);

					if ((loopExternalReferenceRel.getExternalReferenceName() == null) ||
							!loopExternalReferenceRel.getExternalReferenceName()
														 .equals(externalReferenceName) ||
							(loopExternalReferenceRel.getExternalReferencePK() == null) ||
							!loopExternalReferenceRel.getExternalReferencePK()
														 .equals(externalReferencePK)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_ERP_ESN,
							finderArgs, loopExternalReferenceRel);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_ERP_ESN,
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
			return (LoopExternalReferenceRel)result;
		}
	}

	/**
	 * Removes the loop external reference rel where externalReferenceName = &#63; and externalReferencePK = &#63; from the database.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @return the loop external reference rel that was removed
	 */
	@Override
	public LoopExternalReferenceRel removeByERP_ESN(
		String externalReferenceName, String externalReferencePK)
		throws NoSuchLoopExternalReferenceRelException {
		LoopExternalReferenceRel loopExternalReferenceRel = findByERP_ESN(externalReferenceName,
				externalReferencePK);

		return remove(loopExternalReferenceRel);
	}

	/**
	 * Returns the number of loop external reference rels where externalReferenceName = &#63; and externalReferencePK = &#63;.
	 *
	 * @param externalReferenceName the external reference name
	 * @param externalReferencePK the external reference pk
	 * @return the number of matching loop external reference rels
	 */
	@Override
	public int countByERP_ESN(String externalReferenceName,
		String externalReferencePK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ERP_ESN;

		Object[] finderArgs = new Object[] {
				externalReferenceName, externalReferencePK
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LOOPEXTERNALREFERENCEREL_WHERE);

			boolean bindExternalReferenceName = false;

			if (externalReferenceName == null) {
				query.append(_FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCENAME_1);
			}
			else if (externalReferenceName.equals("")) {
				query.append(_FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCENAME_3);
			}
			else {
				bindExternalReferenceName = true;

				query.append(_FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCENAME_2);
			}

			boolean bindExternalReferencePK = false;

			if (externalReferencePK == null) {
				query.append(_FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCEPK_1);
			}
			else if (externalReferencePK.equals("")) {
				query.append(_FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCEPK_3);
			}
			else {
				bindExternalReferencePK = true;

				query.append(_FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCEPK_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExternalReferenceName) {
					qPos.add(externalReferenceName);
				}

				if (bindExternalReferencePK) {
					qPos.add(externalReferencePK);
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

	private static final String _FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCENAME_1 = "loopExternalReferenceRel.externalReferenceName IS NULL AND ";
	private static final String _FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCENAME_2 = "loopExternalReferenceRel.externalReferenceName = ? AND ";
	private static final String _FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCENAME_3 = "(loopExternalReferenceRel.externalReferenceName IS NULL OR loopExternalReferenceRel.externalReferenceName = '') AND ";
	private static final String _FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCEPK_1 = "loopExternalReferenceRel.externalReferencePK IS NULL";
	private static final String _FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCEPK_2 = "loopExternalReferenceRel.externalReferencePK = ?";
	private static final String _FINDER_COLUMN_ERP_ESN_EXTERNALREFERENCEPK_3 = "(loopExternalReferenceRel.externalReferencePK IS NULL OR loopExternalReferenceRel.externalReferencePK = '')";

	public LoopExternalReferenceRelPersistenceImpl() {
		setModelClass(LoopExternalReferenceRel.class);
	}

	/**
	 * Caches the loop external reference rel in the entity cache if it is enabled.
	 *
	 * @param loopExternalReferenceRel the loop external reference rel
	 */
	@Override
	public void cacheResult(LoopExternalReferenceRel loopExternalReferenceRel) {
		entityCache.putResult(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopExternalReferenceRelImpl.class,
			loopExternalReferenceRel.getPrimaryKey(), loopExternalReferenceRel);

		finderCache.putResult(FINDER_PATH_FETCH_BY_ERP_ESN,
			new Object[] {
				loopExternalReferenceRel.getExternalReferenceName(),
				loopExternalReferenceRel.getExternalReferencePK()
			}, loopExternalReferenceRel);

		loopExternalReferenceRel.resetOriginalValues();
	}

	/**
	 * Caches the loop external reference rels in the entity cache if it is enabled.
	 *
	 * @param loopExternalReferenceRels the loop external reference rels
	 */
	@Override
	public void cacheResult(
		List<LoopExternalReferenceRel> loopExternalReferenceRels) {
		for (LoopExternalReferenceRel loopExternalReferenceRel : loopExternalReferenceRels) {
			if (entityCache.getResult(
						LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
						LoopExternalReferenceRelImpl.class,
						loopExternalReferenceRel.getPrimaryKey()) == null) {
				cacheResult(loopExternalReferenceRel);
			}
			else {
				loopExternalReferenceRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all loop external reference rels.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoopExternalReferenceRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the loop external reference rel.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LoopExternalReferenceRel loopExternalReferenceRel) {
		entityCache.removeResult(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopExternalReferenceRelImpl.class,
			loopExternalReferenceRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LoopExternalReferenceRelModelImpl)loopExternalReferenceRel,
			true);
	}

	@Override
	public void clearCache(
		List<LoopExternalReferenceRel> loopExternalReferenceRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LoopExternalReferenceRel loopExternalReferenceRel : loopExternalReferenceRels) {
			entityCache.removeResult(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
				LoopExternalReferenceRelImpl.class,
				loopExternalReferenceRel.getPrimaryKey());

			clearUniqueFindersCache((LoopExternalReferenceRelModelImpl)loopExternalReferenceRel,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		LoopExternalReferenceRelModelImpl loopExternalReferenceRelModelImpl) {
		Object[] args = new Object[] {
				loopExternalReferenceRelModelImpl.getExternalReferenceName(),
				loopExternalReferenceRelModelImpl.getExternalReferencePK()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_ERP_ESN, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_ERP_ESN, args,
			loopExternalReferenceRelModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LoopExternalReferenceRelModelImpl loopExternalReferenceRelModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					loopExternalReferenceRelModelImpl.getExternalReferenceName(),
					loopExternalReferenceRelModelImpl.getExternalReferencePK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ERP_ESN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ERP_ESN, args);
		}

		if ((loopExternalReferenceRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ERP_ESN.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					loopExternalReferenceRelModelImpl.getOriginalExternalReferenceName(),
					loopExternalReferenceRelModelImpl.getOriginalExternalReferencePK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ERP_ESN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ERP_ESN, args);
		}
	}

	/**
	 * Creates a new loop external reference rel with the primary key. Does not add the loop external reference rel to the database.
	 *
	 * @param loopExternalReferenceRelId the primary key for the new loop external reference rel
	 * @return the new loop external reference rel
	 */
	@Override
	public LoopExternalReferenceRel create(long loopExternalReferenceRelId) {
		LoopExternalReferenceRel loopExternalReferenceRel = new LoopExternalReferenceRelImpl();

		loopExternalReferenceRel.setNew(true);
		loopExternalReferenceRel.setPrimaryKey(loopExternalReferenceRelId);

		return loopExternalReferenceRel;
	}

	/**
	 * Removes the loop external reference rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopExternalReferenceRelId the primary key of the loop external reference rel
	 * @return the loop external reference rel that was removed
	 * @throws NoSuchLoopExternalReferenceRelException if a loop external reference rel with the primary key could not be found
	 */
	@Override
	public LoopExternalReferenceRel remove(long loopExternalReferenceRelId)
		throws NoSuchLoopExternalReferenceRelException {
		return remove((Serializable)loopExternalReferenceRelId);
	}

	/**
	 * Removes the loop external reference rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the loop external reference rel
	 * @return the loop external reference rel that was removed
	 * @throws NoSuchLoopExternalReferenceRelException if a loop external reference rel with the primary key could not be found
	 */
	@Override
	public LoopExternalReferenceRel remove(Serializable primaryKey)
		throws NoSuchLoopExternalReferenceRelException {
		Session session = null;

		try {
			session = openSession();

			LoopExternalReferenceRel loopExternalReferenceRel = (LoopExternalReferenceRel)session.get(LoopExternalReferenceRelImpl.class,
					primaryKey);

			if (loopExternalReferenceRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoopExternalReferenceRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(loopExternalReferenceRel);
		}
		catch (NoSuchLoopExternalReferenceRelException nsee) {
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
	protected LoopExternalReferenceRel removeImpl(
		LoopExternalReferenceRel loopExternalReferenceRel) {
		loopExternalReferenceRel = toUnwrappedModel(loopExternalReferenceRel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(loopExternalReferenceRel)) {
				loopExternalReferenceRel = (LoopExternalReferenceRel)session.get(LoopExternalReferenceRelImpl.class,
						loopExternalReferenceRel.getPrimaryKeyObj());
			}

			if (loopExternalReferenceRel != null) {
				session.delete(loopExternalReferenceRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (loopExternalReferenceRel != null) {
			clearCache(loopExternalReferenceRel);
		}

		return loopExternalReferenceRel;
	}

	@Override
	public LoopExternalReferenceRel updateImpl(
		LoopExternalReferenceRel loopExternalReferenceRel) {
		loopExternalReferenceRel = toUnwrappedModel(loopExternalReferenceRel);

		boolean isNew = loopExternalReferenceRel.isNew();

		LoopExternalReferenceRelModelImpl loopExternalReferenceRelModelImpl = (LoopExternalReferenceRelModelImpl)loopExternalReferenceRel;

		Session session = null;

		try {
			session = openSession();

			if (loopExternalReferenceRel.isNew()) {
				session.save(loopExternalReferenceRel);

				loopExternalReferenceRel.setNew(false);
			}
			else {
				loopExternalReferenceRel = (LoopExternalReferenceRel)session.merge(loopExternalReferenceRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LoopExternalReferenceRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
			LoopExternalReferenceRelImpl.class,
			loopExternalReferenceRel.getPrimaryKey(), loopExternalReferenceRel,
			false);

		clearUniqueFindersCache(loopExternalReferenceRelModelImpl, false);
		cacheUniqueFindersCache(loopExternalReferenceRelModelImpl);

		loopExternalReferenceRel.resetOriginalValues();

		return loopExternalReferenceRel;
	}

	protected LoopExternalReferenceRel toUnwrappedModel(
		LoopExternalReferenceRel loopExternalReferenceRel) {
		if (loopExternalReferenceRel instanceof LoopExternalReferenceRelImpl) {
			return loopExternalReferenceRel;
		}

		LoopExternalReferenceRelImpl loopExternalReferenceRelImpl = new LoopExternalReferenceRelImpl();

		loopExternalReferenceRelImpl.setNew(loopExternalReferenceRel.isNew());
		loopExternalReferenceRelImpl.setPrimaryKey(loopExternalReferenceRel.getPrimaryKey());

		loopExternalReferenceRelImpl.setLoopExternalReferenceRelId(loopExternalReferenceRel.getLoopExternalReferenceRelId());
		loopExternalReferenceRelImpl.setClassNameId(loopExternalReferenceRel.getClassNameId());
		loopExternalReferenceRelImpl.setClassPK(loopExternalReferenceRel.getClassPK());
		loopExternalReferenceRelImpl.setExternalReferenceName(loopExternalReferenceRel.getExternalReferenceName());
		loopExternalReferenceRelImpl.setExternalReferencePK(loopExternalReferenceRel.getExternalReferencePK());

		return loopExternalReferenceRelImpl;
	}

	/**
	 * Returns the loop external reference rel with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop external reference rel
	 * @return the loop external reference rel
	 * @throws NoSuchLoopExternalReferenceRelException if a loop external reference rel with the primary key could not be found
	 */
	@Override
	public LoopExternalReferenceRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoopExternalReferenceRelException {
		LoopExternalReferenceRel loopExternalReferenceRel = fetchByPrimaryKey(primaryKey);

		if (loopExternalReferenceRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoopExternalReferenceRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return loopExternalReferenceRel;
	}

	/**
	 * Returns the loop external reference rel with the primary key or throws a {@link NoSuchLoopExternalReferenceRelException} if it could not be found.
	 *
	 * @param loopExternalReferenceRelId the primary key of the loop external reference rel
	 * @return the loop external reference rel
	 * @throws NoSuchLoopExternalReferenceRelException if a loop external reference rel with the primary key could not be found
	 */
	@Override
	public LoopExternalReferenceRel findByPrimaryKey(
		long loopExternalReferenceRelId)
		throws NoSuchLoopExternalReferenceRelException {
		return findByPrimaryKey((Serializable)loopExternalReferenceRelId);
	}

	/**
	 * Returns the loop external reference rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the loop external reference rel
	 * @return the loop external reference rel, or <code>null</code> if a loop external reference rel with the primary key could not be found
	 */
	@Override
	public LoopExternalReferenceRel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
				LoopExternalReferenceRelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LoopExternalReferenceRel loopExternalReferenceRel = (LoopExternalReferenceRel)serializable;

		if (loopExternalReferenceRel == null) {
			Session session = null;

			try {
				session = openSession();

				loopExternalReferenceRel = (LoopExternalReferenceRel)session.get(LoopExternalReferenceRelImpl.class,
						primaryKey);

				if (loopExternalReferenceRel != null) {
					cacheResult(loopExternalReferenceRel);
				}
				else {
					entityCache.putResult(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
						LoopExternalReferenceRelImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
					LoopExternalReferenceRelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return loopExternalReferenceRel;
	}

	/**
	 * Returns the loop external reference rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopExternalReferenceRelId the primary key of the loop external reference rel
	 * @return the loop external reference rel, or <code>null</code> if a loop external reference rel with the primary key could not be found
	 */
	@Override
	public LoopExternalReferenceRel fetchByPrimaryKey(
		long loopExternalReferenceRelId) {
		return fetchByPrimaryKey((Serializable)loopExternalReferenceRelId);
	}

	@Override
	public Map<Serializable, LoopExternalReferenceRel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LoopExternalReferenceRel> map = new HashMap<Serializable, LoopExternalReferenceRel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LoopExternalReferenceRel loopExternalReferenceRel = fetchByPrimaryKey(primaryKey);

			if (loopExternalReferenceRel != null) {
				map.put(primaryKey, loopExternalReferenceRel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
					LoopExternalReferenceRelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LoopExternalReferenceRel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOOPEXTERNALREFERENCEREL_WHERE_PKS_IN);

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

			for (LoopExternalReferenceRel loopExternalReferenceRel : (List<LoopExternalReferenceRel>)q.list()) {
				map.put(loopExternalReferenceRel.getPrimaryKeyObj(),
					loopExternalReferenceRel);

				cacheResult(loopExternalReferenceRel);

				uncachedPrimaryKeys.remove(loopExternalReferenceRel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoopExternalReferenceRelModelImpl.ENTITY_CACHE_ENABLED,
					LoopExternalReferenceRelImpl.class, primaryKey, nullModel);
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
	 * Returns all the loop external reference rels.
	 *
	 * @return the loop external reference rels
	 */
	@Override
	public List<LoopExternalReferenceRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the loop external reference rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopExternalReferenceRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop external reference rels
	 * @param end the upper bound of the range of loop external reference rels (not inclusive)
	 * @return the range of loop external reference rels
	 */
	@Override
	public List<LoopExternalReferenceRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the loop external reference rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopExternalReferenceRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop external reference rels
	 * @param end the upper bound of the range of loop external reference rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop external reference rels
	 */
	@Override
	public List<LoopExternalReferenceRel> findAll(int start, int end,
		OrderByComparator<LoopExternalReferenceRel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the loop external reference rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopExternalReferenceRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop external reference rels
	 * @param end the upper bound of the range of loop external reference rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of loop external reference rels
	 */
	@Override
	public List<LoopExternalReferenceRel> findAll(int start, int end,
		OrderByComparator<LoopExternalReferenceRel> orderByComparator,
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

		List<LoopExternalReferenceRel> list = null;

		if (retrieveFromCache) {
			list = (List<LoopExternalReferenceRel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOOPEXTERNALREFERENCEREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOOPEXTERNALREFERENCEREL;

				if (pagination) {
					sql = sql.concat(LoopExternalReferenceRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LoopExternalReferenceRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LoopExternalReferenceRel>)QueryUtil.list(q,
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
	 * Removes all the loop external reference rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LoopExternalReferenceRel loopExternalReferenceRel : findAll()) {
			remove(loopExternalReferenceRel);
		}
	}

	/**
	 * Returns the number of loop external reference rels.
	 *
	 * @return the number of loop external reference rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOOPEXTERNALREFERENCEREL);

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
		return LoopExternalReferenceRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the loop external reference rel persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoopExternalReferenceRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_LOOPEXTERNALREFERENCEREL = "SELECT loopExternalReferenceRel FROM LoopExternalReferenceRel loopExternalReferenceRel";
	private static final String _SQL_SELECT_LOOPEXTERNALREFERENCEREL_WHERE_PKS_IN =
		"SELECT loopExternalReferenceRel FROM LoopExternalReferenceRel loopExternalReferenceRel WHERE loopExternalReferenceRelId IN (";
	private static final String _SQL_SELECT_LOOPEXTERNALREFERENCEREL_WHERE = "SELECT loopExternalReferenceRel FROM LoopExternalReferenceRel loopExternalReferenceRel WHERE ";
	private static final String _SQL_COUNT_LOOPEXTERNALREFERENCEREL = "SELECT COUNT(loopExternalReferenceRel) FROM LoopExternalReferenceRel loopExternalReferenceRel";
	private static final String _SQL_COUNT_LOOPEXTERNALREFERENCEREL_WHERE = "SELECT COUNT(loopExternalReferenceRel) FROM LoopExternalReferenceRel loopExternalReferenceRel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "loopExternalReferenceRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoopExternalReferenceRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LoopExternalReferenceRel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LoopExternalReferenceRelPersistenceImpl.class);
}