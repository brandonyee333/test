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

package com.liferay.osb.service.persistence;

import com.liferay.osb.NoSuchHolidayCalendarRelException;
import com.liferay.osb.model.HolidayCalendarRel;
import com.liferay.osb.model.impl.HolidayCalendarRelImpl;
import com.liferay.osb.model.impl.HolidayCalendarRelModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the holiday calendar rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HolidayCalendarRelPersistence
 * @see HolidayCalendarRelUtil
 * @generated
 */
public class HolidayCalendarRelPersistenceImpl extends BasePersistenceImpl<HolidayCalendarRel>
	implements HolidayCalendarRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HolidayCalendarRelUtil} to access the holiday calendar rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HolidayCalendarRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_HOLIDAYCALENDARID =
		new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByHolidayCalendarId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDARID =
		new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByHolidayCalendarId", new String[] { Long.class.getName() },
			HolidayCalendarRelModelImpl.HOLIDAYCALENDARID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_HOLIDAYCALENDARID = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByHolidayCalendarId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			HolidayCalendarRelModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_HC_U = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarRelImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByHC_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			HolidayCalendarRelModelImpl.HOLIDAYCALENDARID_COLUMN_BITMASK |
			HolidayCalendarRelModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_HC_U = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByHC_U",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED,
			HolidayCalendarRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the holiday calendar rel in the entity cache if it is enabled.
	 *
	 * @param holidayCalendarRel the holiday calendar rel
	 */
	public void cacheResult(HolidayCalendarRel holidayCalendarRel) {
		EntityCacheUtil.putResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelImpl.class, holidayCalendarRel.getPrimaryKey(),
			holidayCalendarRel);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HC_U,
			new Object[] {
				Long.valueOf(holidayCalendarRel.getHolidayCalendarId()),
				Long.valueOf(holidayCalendarRel.getUserId())
			}, holidayCalendarRel);

		holidayCalendarRel.resetOriginalValues();
	}

	/**
	 * Caches the holiday calendar rels in the entity cache if it is enabled.
	 *
	 * @param holidayCalendarRels the holiday calendar rels
	 */
	public void cacheResult(List<HolidayCalendarRel> holidayCalendarRels) {
		for (HolidayCalendarRel holidayCalendarRel : holidayCalendarRels) {
			if (EntityCacheUtil.getResult(
						HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
						HolidayCalendarRelImpl.class,
						holidayCalendarRel.getPrimaryKey()) == null) {
				cacheResult(holidayCalendarRel);
			}
			else {
				holidayCalendarRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all holiday calendar rels.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HolidayCalendarRelImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HolidayCalendarRelImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the holiday calendar rel.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HolidayCalendarRel holidayCalendarRel) {
		EntityCacheUtil.removeResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelImpl.class, holidayCalendarRel.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(holidayCalendarRel);
	}

	@Override
	public void clearCache(List<HolidayCalendarRel> holidayCalendarRels) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HolidayCalendarRel holidayCalendarRel : holidayCalendarRels) {
			EntityCacheUtil.removeResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
				HolidayCalendarRelImpl.class, holidayCalendarRel.getPrimaryKey());

			clearUniqueFindersCache(holidayCalendarRel);
		}
	}

	protected void cacheUniqueFindersCache(
		HolidayCalendarRel holidayCalendarRel) {
		if (holidayCalendarRel.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(holidayCalendarRel.getHolidayCalendarId()),
					Long.valueOf(holidayCalendarRel.getUserId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_HC_U, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HC_U, args,
				holidayCalendarRel);
		}
		else {
			HolidayCalendarRelModelImpl holidayCalendarRelModelImpl = (HolidayCalendarRelModelImpl)holidayCalendarRel;

			if ((holidayCalendarRelModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_HC_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(holidayCalendarRel.getHolidayCalendarId()),
						Long.valueOf(holidayCalendarRel.getUserId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_HC_U, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HC_U, args,
					holidayCalendarRel);
			}
		}
	}

	protected void clearUniqueFindersCache(
		HolidayCalendarRel holidayCalendarRel) {
		HolidayCalendarRelModelImpl holidayCalendarRelModelImpl = (HolidayCalendarRelModelImpl)holidayCalendarRel;

		Object[] args = new Object[] {
				Long.valueOf(holidayCalendarRel.getHolidayCalendarId()),
				Long.valueOf(holidayCalendarRel.getUserId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HC_U, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_HC_U, args);

		if ((holidayCalendarRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_HC_U.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(holidayCalendarRelModelImpl.getOriginalHolidayCalendarId()),
					Long.valueOf(holidayCalendarRelModelImpl.getOriginalUserId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HC_U, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_HC_U, args);
		}
	}

	/**
	 * Creates a new holiday calendar rel with the primary key. Does not add the holiday calendar rel to the database.
	 *
	 * @param holidayCalendarRelId the primary key for the new holiday calendar rel
	 * @return the new holiday calendar rel
	 */
	public HolidayCalendarRel create(long holidayCalendarRelId) {
		HolidayCalendarRel holidayCalendarRel = new HolidayCalendarRelImpl();

		holidayCalendarRel.setNew(true);
		holidayCalendarRel.setPrimaryKey(holidayCalendarRelId);

		return holidayCalendarRel;
	}

	/**
	 * Removes the holiday calendar rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param holidayCalendarRelId the primary key of the holiday calendar rel
	 * @return the holiday calendar rel that was removed
	 * @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel remove(long holidayCalendarRelId)
		throws NoSuchHolidayCalendarRelException, SystemException {
		return remove(Long.valueOf(holidayCalendarRelId));
	}

	/**
	 * Removes the holiday calendar rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the holiday calendar rel
	 * @return the holiday calendar rel that was removed
	 * @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HolidayCalendarRel remove(Serializable primaryKey)
		throws NoSuchHolidayCalendarRelException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HolidayCalendarRel holidayCalendarRel = (HolidayCalendarRel)session.get(HolidayCalendarRelImpl.class,
					primaryKey);

			if (holidayCalendarRel == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHolidayCalendarRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(holidayCalendarRel);
		}
		catch (NoSuchHolidayCalendarRelException nsee) {
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
	protected HolidayCalendarRel removeImpl(
		HolidayCalendarRel holidayCalendarRel) throws SystemException {
		holidayCalendarRel = toUnwrappedModel(holidayCalendarRel);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, holidayCalendarRel);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(holidayCalendarRel);

		return holidayCalendarRel;
	}

	@Override
	public HolidayCalendarRel updateImpl(
		com.liferay.osb.model.HolidayCalendarRel holidayCalendarRel,
		boolean merge) throws SystemException {
		holidayCalendarRel = toUnwrappedModel(holidayCalendarRel);

		boolean isNew = holidayCalendarRel.isNew();

		HolidayCalendarRelModelImpl holidayCalendarRelModelImpl = (HolidayCalendarRelModelImpl)holidayCalendarRel;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, holidayCalendarRel, merge);

			holidayCalendarRel.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !HolidayCalendarRelModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((holidayCalendarRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDARID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(holidayCalendarRelModelImpl.getOriginalHolidayCalendarId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HOLIDAYCALENDARID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDARID,
					args);

				args = new Object[] {
						Long.valueOf(holidayCalendarRelModelImpl.getHolidayCalendarId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_HOLIDAYCALENDARID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDARID,
					args);
			}

			if ((holidayCalendarRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(holidayCalendarRelModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(holidayCalendarRelModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}
		}

		EntityCacheUtil.putResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
			HolidayCalendarRelImpl.class, holidayCalendarRel.getPrimaryKey(),
			holidayCalendarRel);

		clearUniqueFindersCache(holidayCalendarRel);
		cacheUniqueFindersCache(holidayCalendarRel);

		return holidayCalendarRel;
	}

	protected HolidayCalendarRel toUnwrappedModel(
		HolidayCalendarRel holidayCalendarRel) {
		if (holidayCalendarRel instanceof HolidayCalendarRelImpl) {
			return holidayCalendarRel;
		}

		HolidayCalendarRelImpl holidayCalendarRelImpl = new HolidayCalendarRelImpl();

		holidayCalendarRelImpl.setNew(holidayCalendarRel.isNew());
		holidayCalendarRelImpl.setPrimaryKey(holidayCalendarRel.getPrimaryKey());

		holidayCalendarRelImpl.setHolidayCalendarRelId(holidayCalendarRel.getHolidayCalendarRelId());
		holidayCalendarRelImpl.setHolidayCalendarId(holidayCalendarRel.getHolidayCalendarId());
		holidayCalendarRelImpl.setUserId(holidayCalendarRel.getUserId());

		return holidayCalendarRelImpl;
	}

	/**
	 * Returns the holiday calendar rel with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday calendar rel
	 * @return the holiday calendar rel
	 * @throws com.liferay.portal.NoSuchModelException if a holiday calendar rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HolidayCalendarRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the holiday calendar rel with the primary key or throws a {@link com.liferay.osb.NoSuchHolidayCalendarRelException} if it could not be found.
	 *
	 * @param holidayCalendarRelId the primary key of the holiday calendar rel
	 * @return the holiday calendar rel
	 * @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel findByPrimaryKey(long holidayCalendarRelId)
		throws NoSuchHolidayCalendarRelException, SystemException {
		HolidayCalendarRel holidayCalendarRel = fetchByPrimaryKey(holidayCalendarRelId);

		if (holidayCalendarRel == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					holidayCalendarRelId);
			}

			throw new NoSuchHolidayCalendarRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				holidayCalendarRelId);
		}

		return holidayCalendarRel;
	}

	/**
	 * Returns the holiday calendar rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the holiday calendar rel
	 * @return the holiday calendar rel, or <code>null</code> if a holiday calendar rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HolidayCalendarRel fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the holiday calendar rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param holidayCalendarRelId the primary key of the holiday calendar rel
	 * @return the holiday calendar rel, or <code>null</code> if a holiday calendar rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel fetchByPrimaryKey(long holidayCalendarRelId)
		throws SystemException {
		HolidayCalendarRel holidayCalendarRel = (HolidayCalendarRel)EntityCacheUtil.getResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
				HolidayCalendarRelImpl.class, holidayCalendarRelId);

		if (holidayCalendarRel == _nullHolidayCalendarRel) {
			return null;
		}

		if (holidayCalendarRel == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				holidayCalendarRel = (HolidayCalendarRel)session.get(HolidayCalendarRelImpl.class,
						Long.valueOf(holidayCalendarRelId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (holidayCalendarRel != null) {
					cacheResult(holidayCalendarRel);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HolidayCalendarRelModelImpl.ENTITY_CACHE_ENABLED,
						HolidayCalendarRelImpl.class, holidayCalendarRelId,
						_nullHolidayCalendarRel);
				}

				closeSession(session);
			}
		}

		return holidayCalendarRel;
	}

	/**
	 * Returns all the holiday calendar rels where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @return the matching holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId) throws SystemException {
		return findByHolidayCalendarId(holidayCalendarId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the holiday calendar rels where holidayCalendarId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param start the lower bound of the range of holiday calendar rels
	 * @param end the upper bound of the range of holiday calendar rels (not inclusive)
	 * @return the range of matching holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId, int start, int end) throws SystemException {
		return findByHolidayCalendarId(holidayCalendarId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the holiday calendar rels where holidayCalendarId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param start the lower bound of the range of holiday calendar rels
	 * @param end the upper bound of the range of holiday calendar rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<HolidayCalendarRel> findByHolidayCalendarId(
		long holidayCalendarId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HOLIDAYCALENDARID;
			finderArgs = new Object[] { holidayCalendarId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_HOLIDAYCALENDARID;
			finderArgs = new Object[] {
					holidayCalendarId,
					
					start, end, orderByComparator
				};
		}

		List<HolidayCalendarRel> list = (List<HolidayCalendarRel>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (HolidayCalendarRel holidayCalendarRel : list) {
				if ((holidayCalendarId != holidayCalendarRel.getHolidayCalendarId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_HOLIDAYCALENDARREL_WHERE);

			query.append(_FINDER_COLUMN_HOLIDAYCALENDARID_HOLIDAYCALENDARID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(holidayCalendarId);

				list = (List<HolidayCalendarRel>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday calendar rel
	 * @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel findByHolidayCalendarId_First(
		long holidayCalendarId, OrderByComparator orderByComparator)
		throws NoSuchHolidayCalendarRelException, SystemException {
		HolidayCalendarRel holidayCalendarRel = fetchByHolidayCalendarId_First(holidayCalendarId,
				orderByComparator);

		if (holidayCalendarRel != null) {
			return holidayCalendarRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("holidayCalendarId=");
		msg.append(holidayCalendarId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHolidayCalendarRelException(msg.toString());
	}

	/**
	 * Returns the first holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel fetchByHolidayCalendarId_First(
		long holidayCalendarId, OrderByComparator orderByComparator)
		throws SystemException {
		List<HolidayCalendarRel> list = findByHolidayCalendarId(holidayCalendarId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday calendar rel
	 * @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel findByHolidayCalendarId_Last(
		long holidayCalendarId, OrderByComparator orderByComparator)
		throws NoSuchHolidayCalendarRelException, SystemException {
		HolidayCalendarRel holidayCalendarRel = fetchByHolidayCalendarId_Last(holidayCalendarId,
				orderByComparator);

		if (holidayCalendarRel != null) {
			return holidayCalendarRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("holidayCalendarId=");
		msg.append(holidayCalendarId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHolidayCalendarRelException(msg.toString());
	}

	/**
	 * Returns the last holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel fetchByHolidayCalendarId_Last(
		long holidayCalendarId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByHolidayCalendarId(holidayCalendarId);

		List<HolidayCalendarRel> list = findByHolidayCalendarId(holidayCalendarId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the holiday calendar rels before and after the current holiday calendar rel in the ordered set where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarRelId the primary key of the current holiday calendar rel
	 * @param holidayCalendarId the holiday calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next holiday calendar rel
	 * @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel[] findByHolidayCalendarId_PrevAndNext(
		long holidayCalendarRelId, long holidayCalendarId,
		OrderByComparator orderByComparator)
		throws NoSuchHolidayCalendarRelException, SystemException {
		HolidayCalendarRel holidayCalendarRel = findByPrimaryKey(holidayCalendarRelId);

		Session session = null;

		try {
			session = openSession();

			HolidayCalendarRel[] array = new HolidayCalendarRelImpl[3];

			array[0] = getByHolidayCalendarId_PrevAndNext(session,
					holidayCalendarRel, holidayCalendarId, orderByComparator,
					true);

			array[1] = holidayCalendarRel;

			array[2] = getByHolidayCalendarId_PrevAndNext(session,
					holidayCalendarRel, holidayCalendarId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected HolidayCalendarRel getByHolidayCalendarId_PrevAndNext(
		Session session, HolidayCalendarRel holidayCalendarRel,
		long holidayCalendarId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_HOLIDAYCALENDARREL_WHERE);

		query.append(_FINDER_COLUMN_HOLIDAYCALENDARID_HOLIDAYCALENDARID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(holidayCalendarId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(holidayCalendarRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<HolidayCalendarRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the holiday calendar rels where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<HolidayCalendarRel> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the holiday calendar rels where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of holiday calendar rels
	 * @param end the upper bound of the range of holiday calendar rels (not inclusive)
	 * @return the range of matching holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<HolidayCalendarRel> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the holiday calendar rels where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of holiday calendar rels
	 * @param end the upper bound of the range of holiday calendar rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<HolidayCalendarRel> findByUserId(long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<HolidayCalendarRel> list = (List<HolidayCalendarRel>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (HolidayCalendarRel holidayCalendarRel : list) {
				if ((userId != holidayCalendarRel.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_HOLIDAYCALENDARREL_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<HolidayCalendarRel>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first holiday calendar rel in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday calendar rel
	 * @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchHolidayCalendarRelException, SystemException {
		HolidayCalendarRel holidayCalendarRel = fetchByUserId_First(userId,
				orderByComparator);

		if (holidayCalendarRel != null) {
			return holidayCalendarRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHolidayCalendarRelException(msg.toString());
	}

	/**
	 * Returns the first holiday calendar rel in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<HolidayCalendarRel> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last holiday calendar rel in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday calendar rel
	 * @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchHolidayCalendarRelException, SystemException {
		HolidayCalendarRel holidayCalendarRel = fetchByUserId_Last(userId,
				orderByComparator);

		if (holidayCalendarRel != null) {
			return holidayCalendarRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHolidayCalendarRelException(msg.toString());
	}

	/**
	 * Returns the last holiday calendar rel in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<HolidayCalendarRel> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the holiday calendar rels before and after the current holiday calendar rel in the ordered set where userId = &#63;.
	 *
	 * @param holidayCalendarRelId the primary key of the current holiday calendar rel
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next holiday calendar rel
	 * @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a holiday calendar rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel[] findByUserId_PrevAndNext(
		long holidayCalendarRelId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchHolidayCalendarRelException, SystemException {
		HolidayCalendarRel holidayCalendarRel = findByPrimaryKey(holidayCalendarRelId);

		Session session = null;

		try {
			session = openSession();

			HolidayCalendarRel[] array = new HolidayCalendarRelImpl[3];

			array[0] = getByUserId_PrevAndNext(session, holidayCalendarRel,
					userId, orderByComparator, true);

			array[1] = holidayCalendarRel;

			array[2] = getByUserId_PrevAndNext(session, holidayCalendarRel,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected HolidayCalendarRel getByUserId_PrevAndNext(Session session,
		HolidayCalendarRel holidayCalendarRel, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_HOLIDAYCALENDARREL_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(holidayCalendarRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<HolidayCalendarRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; or throws a {@link com.liferay.osb.NoSuchHolidayCalendarRelException} if it could not be found.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param userId the user ID
	 * @return the matching holiday calendar rel
	 * @throws com.liferay.osb.NoSuchHolidayCalendarRelException if a matching holiday calendar rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel findByHC_U(long holidayCalendarId, long userId)
		throws NoSuchHolidayCalendarRelException, SystemException {
		HolidayCalendarRel holidayCalendarRel = fetchByHC_U(holidayCalendarId,
				userId);

		if (holidayCalendarRel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("holidayCalendarId=");
			msg.append(holidayCalendarId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchHolidayCalendarRelException(msg.toString());
		}

		return holidayCalendarRel;
	}

	/**
	 * Returns the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param userId the user ID
	 * @return the matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel fetchByHC_U(long holidayCalendarId, long userId)
		throws SystemException {
		return fetchByHC_U(holidayCalendarId, userId, true);
	}

	/**
	 * Returns the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param userId the user ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching holiday calendar rel, or <code>null</code> if a matching holiday calendar rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel fetchByHC_U(long holidayCalendarId, long userId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { holidayCalendarId, userId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_HC_U,
					finderArgs, this);
		}

		if (result instanceof HolidayCalendarRel) {
			HolidayCalendarRel holidayCalendarRel = (HolidayCalendarRel)result;

			if ((holidayCalendarId != holidayCalendarRel.getHolidayCalendarId()) ||
					(userId != holidayCalendarRel.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HOLIDAYCALENDARREL_WHERE);

			query.append(_FINDER_COLUMN_HC_U_HOLIDAYCALENDARID_2);

			query.append(_FINDER_COLUMN_HC_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(holidayCalendarId);

				qPos.add(userId);

				List<HolidayCalendarRel> list = q.list();

				result = list;

				HolidayCalendarRel holidayCalendarRel = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HC_U,
						finderArgs, list);
				}
				else {
					holidayCalendarRel = list.get(0);

					cacheResult(holidayCalendarRel);

					if ((holidayCalendarRel.getHolidayCalendarId() != holidayCalendarId) ||
							(holidayCalendarRel.getUserId() != userId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_HC_U,
							finderArgs, holidayCalendarRel);
					}
				}

				return holidayCalendarRel;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_HC_U,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (HolidayCalendarRel)result;
			}
		}
	}

	/**
	 * Returns all the holiday calendar rels.
	 *
	 * @return the holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<HolidayCalendarRel> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the holiday calendar rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday calendar rels
	 * @param end the upper bound of the range of holiday calendar rels (not inclusive)
	 * @return the range of holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<HolidayCalendarRel> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the holiday calendar rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of holiday calendar rels
	 * @param end the upper bound of the range of holiday calendar rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<HolidayCalendarRel> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<HolidayCalendarRel> list = (List<HolidayCalendarRel>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HOLIDAYCALENDARREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HOLIDAYCALENDARREL;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HolidayCalendarRel>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HolidayCalendarRel>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the holiday calendar rels where holidayCalendarId = &#63; from the database.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByHolidayCalendarId(long holidayCalendarId)
		throws SystemException {
		for (HolidayCalendarRel holidayCalendarRel : findByHolidayCalendarId(
				holidayCalendarId)) {
			remove(holidayCalendarRel);
		}
	}

	/**
	 * Removes all the holiday calendar rels where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (HolidayCalendarRel holidayCalendarRel : findByUserId(userId)) {
			remove(holidayCalendarRel);
		}
	}

	/**
	 * Removes the holiday calendar rel where holidayCalendarId = &#63; and userId = &#63; from the database.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param userId the user ID
	 * @return the holiday calendar rel that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public HolidayCalendarRel removeByHC_U(long holidayCalendarId, long userId)
		throws NoSuchHolidayCalendarRelException, SystemException {
		HolidayCalendarRel holidayCalendarRel = findByHC_U(holidayCalendarId,
				userId);

		return remove(holidayCalendarRel);
	}

	/**
	 * Removes all the holiday calendar rels from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HolidayCalendarRel holidayCalendarRel : findAll()) {
			remove(holidayCalendarRel);
		}
	}

	/**
	 * Returns the number of holiday calendar rels where holidayCalendarId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @return the number of matching holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public int countByHolidayCalendarId(long holidayCalendarId)
		throws SystemException {
		Object[] finderArgs = new Object[] { holidayCalendarId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_HOLIDAYCALENDARID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HOLIDAYCALENDARREL_WHERE);

			query.append(_FINDER_COLUMN_HOLIDAYCALENDARID_HOLIDAYCALENDARID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(holidayCalendarId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_HOLIDAYCALENDARID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of holiday calendar rels where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HOLIDAYCALENDARREL_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of holiday calendar rels where holidayCalendarId = &#63; and userId = &#63;.
	 *
	 * @param holidayCalendarId the holiday calendar ID
	 * @param userId the user ID
	 * @return the number of matching holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public int countByHC_U(long holidayCalendarId, long userId)
		throws SystemException {
		Object[] finderArgs = new Object[] { holidayCalendarId, userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_HC_U,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HOLIDAYCALENDARREL_WHERE);

			query.append(_FINDER_COLUMN_HC_U_HOLIDAYCALENDARID_2);

			query.append(_FINDER_COLUMN_HC_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(holidayCalendarId);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_HC_U,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of holiday calendar rels.
	 *
	 * @return the number of holiday calendar rels
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HOLIDAYCALENDARREL);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the holiday calendar rel persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.HolidayCalendarRel")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HolidayCalendarRel>> listenersList = new ArrayList<ModelListener<HolidayCalendarRel>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<HolidayCalendarRel>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(HolidayCalendarRelImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = AccountAttachmentPersistence.class)
	protected AccountAttachmentPersistence accountAttachmentPersistence;
	@BeanReference(type = AccountCallPersistence.class)
	protected AccountCallPersistence accountCallPersistence;
	@BeanReference(type = AccountCustomerPersistence.class)
	protected AccountCustomerPersistence accountCustomerPersistence;
	@BeanReference(type = AccountEntryPersistence.class)
	protected AccountEntryPersistence accountEntryPersistence;
	@BeanReference(type = AccountEntryLanguagePersistence.class)
	protected AccountEntryLanguagePersistence accountEntryLanguagePersistence;
	@BeanReference(type = AccountEnvironmentPersistence.class)
	protected AccountEnvironmentPersistence accountEnvironmentPersistence;
	@BeanReference(type = AccountEnvironmentAttachmentPersistence.class)
	protected AccountEnvironmentAttachmentPersistence accountEnvironmentAttachmentPersistence;
	@BeanReference(type = AccountInformationPersistence.class)
	protected AccountInformationPersistence accountInformationPersistence;
	@BeanReference(type = AccountLinkPersistence.class)
	protected AccountLinkPersistence accountLinkPersistence;
	@BeanReference(type = AccountProjectPersistence.class)
	protected AccountProjectPersistence accountProjectPersistence;
	@BeanReference(type = AccountWorkerPersistence.class)
	protected AccountWorkerPersistence accountWorkerPersistence;
	@BeanReference(type = AppAuditPersistence.class)
	protected AppAuditPersistence appAuditPersistence;
	@BeanReference(type = AppEntryPersistence.class)
	protected AppEntryPersistence appEntryPersistence;
	@BeanReference(type = AppEntryRelPersistence.class)
	protected AppEntryRelPersistence appEntryRelPersistence;
	@BeanReference(type = AppFlagPersistence.class)
	protected AppFlagPersistence appFlagPersistence;
	@BeanReference(type = AppPackagePersistence.class)
	protected AppPackagePersistence appPackagePersistence;
	@BeanReference(type = AppPackagePluginPersistence.class)
	protected AppPackagePluginPersistence appPackagePluginPersistence;
	@BeanReference(type = AppPricingPersistence.class)
	protected AppPricingPersistence appPricingPersistence;
	@BeanReference(type = AppPricingItemPersistence.class)
	protected AppPricingItemPersistence appPricingItemPersistence;
	@BeanReference(type = AppVersionPersistence.class)
	protected AppVersionPersistence appVersionPersistence;
	@BeanReference(type = AssetAttachmentPersistence.class)
	protected AssetAttachmentPersistence assetAttachmentPersistence;
	@BeanReference(type = AssetAuditPersistence.class)
	protected AssetAuditPersistence assetAuditPersistence;
	@BeanReference(type = AssetLicensePersistence.class)
	protected AssetLicensePersistence assetLicensePersistence;
	@BeanReference(type = AssetListPersistence.class)
	protected AssetListPersistence assetListPersistence;
	@BeanReference(type = AssetListAssetEntryPersistence.class)
	protected AssetListAssetEntryPersistence assetListAssetEntryPersistence;
	@BeanReference(type = AssetReceiptPersistence.class)
	protected AssetReceiptPersistence assetReceiptPersistence;
	@BeanReference(type = AssetReceiptLicensePersistence.class)
	protected AssetReceiptLicensePersistence assetReceiptLicensePersistence;
	@BeanReference(type = AssetReceiptRedeemTokenPersistence.class)
	protected AssetReceiptRedeemTokenPersistence assetReceiptRedeemTokenPersistence;
	@BeanReference(type = AssetReceiptSupportPersistence.class)
	protected AssetReceiptSupportPersistence assetReceiptSupportPersistence;
	@BeanReference(type = AssetRecommendationEntryPersistence.class)
	protected AssetRecommendationEntryPersistence assetRecommendationEntryPersistence;
	@BeanReference(type = AssetRecommendationSetPersistence.class)
	protected AssetRecommendationSetPersistence assetRecommendationSetPersistence;
	@BeanReference(type = AssetStatsDayPersistence.class)
	protected AssetStatsDayPersistence assetStatsDayPersistence;
	@BeanReference(type = AssetStatsMonthPersistence.class)
	protected AssetStatsMonthPersistence assetStatsMonthPersistence;
	@BeanReference(type = AssetStatsWeekPersistence.class)
	protected AssetStatsWeekPersistence assetStatsWeekPersistence;
	@BeanReference(type = AuditActionPersistence.class)
	protected AuditActionPersistence auditActionPersistence;
	@BeanReference(type = AuditEntryPersistence.class)
	protected AuditEntryPersistence auditEntryPersistence;
	@BeanReference(type = ContractAuditPersistence.class)
	protected ContractAuditPersistence contractAuditPersistence;
	@BeanReference(type = ContractEntryPersistence.class)
	protected ContractEntryPersistence contractEntryPersistence;
	@BeanReference(type = CorpEntryPersistence.class)
	protected CorpEntryPersistence corpEntryPersistence;
	@BeanReference(type = CorpGroupPersistence.class)
	protected CorpGroupPersistence corpGroupPersistence;
	@BeanReference(type = CorpMembershipRequestPersistence.class)
	protected CorpMembershipRequestPersistence corpMembershipRequestPersistence;
	@BeanReference(type = CorpProjectPersistence.class)
	protected CorpProjectPersistence corpProjectPersistence;
	@BeanReference(type = CorpProjectMessagePersistence.class)
	protected CorpProjectMessagePersistence corpProjectMessagePersistence;
	@BeanReference(type = CountryAppPricingPersistence.class)
	protected CountryAppPricingPersistence countryAppPricingPersistence;
	@BeanReference(type = CurrencyEntryPersistence.class)
	protected CurrencyEntryPersistence currencyEntryPersistence;
	@BeanReference(type = DeveloperEntryPersistence.class)
	protected DeveloperEntryPersistence developerEntryPersistence;
	@BeanReference(type = ExternalIdMapperPersistence.class)
	protected ExternalIdMapperPersistence externalIdMapperPersistence;
	@BeanReference(type = FeedbackEntryPersistence.class)
	protected FeedbackEntryPersistence feedbackEntryPersistence;
	@BeanReference(type = HolidayCalendarPersistence.class)
	protected HolidayCalendarPersistence holidayCalendarPersistence;
	@BeanReference(type = HolidayCalendarRelPersistence.class)
	protected HolidayCalendarRelPersistence holidayCalendarRelPersistence;
	@BeanReference(type = HolidayEntryPersistence.class)
	protected HolidayEntryPersistence holidayEntryPersistence;
	@BeanReference(type = LCSSubscriptionEntryPersistence.class)
	protected LCSSubscriptionEntryPersistence lcsSubscriptionEntryPersistence;
	@BeanReference(type = LicenseEntryPersistence.class)
	protected LicenseEntryPersistence licenseEntryPersistence;
	@BeanReference(type = LicenseKeyPersistence.class)
	protected LicenseKeyPersistence licenseKeyPersistence;
	@BeanReference(type = LicenseKeySetPersistence.class)
	protected LicenseKeySetPersistence licenseKeySetPersistence;
	@BeanReference(type = MarketingEventPersistence.class)
	protected MarketingEventPersistence marketingEventPersistence;
	@BeanReference(type = OfferingBundlePersistence.class)
	protected OfferingBundlePersistence offeringBundlePersistence;
	@BeanReference(type = OfferingDefinitionPersistence.class)
	protected OfferingDefinitionPersistence offeringDefinitionPersistence;
	@BeanReference(type = OfferingEntryPersistence.class)
	protected OfferingEntryPersistence offeringEntryPersistence;
	@BeanReference(type = OrderEntryPersistence.class)
	protected OrderEntryPersistence orderEntryPersistence;
	@BeanReference(type = PartnerEntryPersistence.class)
	protected PartnerEntryPersistence partnerEntryPersistence;
	@BeanReference(type = PartnerWorkerPersistence.class)
	protected PartnerWorkerPersistence partnerWorkerPersistence;
	@BeanReference(type = PortalReleasePersistence.class)
	protected PortalReleasePersistence portalReleasePersistence;
	@BeanReference(type = ProductEntryPersistence.class)
	protected ProductEntryPersistence productEntryPersistence;
	@BeanReference(type = SearchFilterPersistence.class)
	protected SearchFilterPersistence searchFilterPersistence;
	@BeanReference(type = SecurityPatchPersistence.class)
	protected SecurityPatchPersistence securityPatchPersistence;
	@BeanReference(type = SupportLaborPersistence.class)
	protected SupportLaborPersistence supportLaborPersistence;
	@BeanReference(type = SupportRegionPersistence.class)
	protected SupportRegionPersistence supportRegionPersistence;
	@BeanReference(type = SupportResponsePersistence.class)
	protected SupportResponsePersistence supportResponsePersistence;
	@BeanReference(type = SupportTeamPersistence.class)
	protected SupportTeamPersistence supportTeamPersistence;
	@BeanReference(type = SupportTeamLanguagePersistence.class)
	protected SupportTeamLanguagePersistence supportTeamLanguagePersistence;
	@BeanReference(type = SupportWorkerPersistence.class)
	protected SupportWorkerPersistence supportWorkerPersistence;
	@BeanReference(type = SupportWorkerAccountTierPersistence.class)
	protected SupportWorkerAccountTierPersistence supportWorkerAccountTierPersistence;
	@BeanReference(type = SupportWorkerComponentPersistence.class)
	protected SupportWorkerComponentPersistence supportWorkerComponentPersistence;
	@BeanReference(type = SupportWorkerSeverityPersistence.class)
	protected SupportWorkerSeverityPersistence supportWorkerSeverityPersistence;
	@BeanReference(type = TicketAttachmentPersistence.class)
	protected TicketAttachmentPersistence ticketAttachmentPersistence;
	@BeanReference(type = TicketCallPersistence.class)
	protected TicketCallPersistence ticketCallPersistence;
	@BeanReference(type = TicketCannedResponsePersistence.class)
	protected TicketCannedResponsePersistence ticketCannedResponsePersistence;
	@BeanReference(type = TicketCommentPersistence.class)
	protected TicketCommentPersistence ticketCommentPersistence;
	@BeanReference(type = TicketEntryPersistence.class)
	protected TicketEntryPersistence ticketEntryPersistence;
	@BeanReference(type = TicketFeedbackPersistence.class)
	protected TicketFeedbackPersistence ticketFeedbackPersistence;
	@BeanReference(type = TicketFlagPersistence.class)
	protected TicketFlagPersistence ticketFlagPersistence;
	@BeanReference(type = TicketInformationPersistence.class)
	protected TicketInformationPersistence ticketInformationPersistence;
	@BeanReference(type = TicketLinkPersistence.class)
	protected TicketLinkPersistence ticketLinkPersistence;
	@BeanReference(type = TicketSolutionPersistence.class)
	protected TicketSolutionPersistence ticketSolutionPersistence;
	@BeanReference(type = TicketWorkerPersistence.class)
	protected TicketWorkerPersistence ticketWorkerPersistence;
	@BeanReference(type = TrainingCertificatePersistence.class)
	protected TrainingCertificatePersistence trainingCertificatePersistence;
	@BeanReference(type = TrainingCertificateTemplatePersistence.class)
	protected TrainingCertificateTemplatePersistence trainingCertificateTemplatePersistence;
	@BeanReference(type = TrainingCoursePersistence.class)
	protected TrainingCoursePersistence trainingCoursePersistence;
	@BeanReference(type = TrainingCustomerPersistence.class)
	protected TrainingCustomerPersistence trainingCustomerPersistence;
	@BeanReference(type = TrainingEventPersistence.class)
	protected TrainingEventPersistence trainingEventPersistence;
	@BeanReference(type = TrainingExamPersistence.class)
	protected TrainingExamPersistence trainingExamPersistence;
	@BeanReference(type = TrainingExamResultPersistence.class)
	protected TrainingExamResultPersistence trainingExamResultPersistence;
	@BeanReference(type = TrainingExamResultItemPersistence.class)
	protected TrainingExamResultItemPersistence trainingExamResultItemPersistence;
	@BeanReference(type = TrainingExamResultSectionPersistence.class)
	protected TrainingExamResultSectionPersistence trainingExamResultSectionPersistence;
	@BeanReference(type = TrainingImportLogPersistence.class)
	protected TrainingImportLogPersistence trainingImportLogPersistence;
	@BeanReference(type = TrainingLinkedUserPersistence.class)
	protected TrainingLinkedUserPersistence trainingLinkedUserPersistence;
	@BeanReference(type = TrainingLocationPersistence.class)
	protected TrainingLocationPersistence trainingLocationPersistence;
	@BeanReference(type = TrainingWorkerPersistence.class)
	protected TrainingWorkerPersistence trainingWorkerPersistence;
	@BeanReference(type = UserProfilePersistence.class)
	protected UserProfilePersistence userProfilePersistence;
	@BeanReference(type = UserProfileHistoryPersistence.class)
	protected UserProfileHistoryPersistence userProfileHistoryPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_HOLIDAYCALENDARREL = "SELECT holidayCalendarRel FROM HolidayCalendarRel holidayCalendarRel";
	private static final String _SQL_SELECT_HOLIDAYCALENDARREL_WHERE = "SELECT holidayCalendarRel FROM HolidayCalendarRel holidayCalendarRel WHERE ";
	private static final String _SQL_COUNT_HOLIDAYCALENDARREL = "SELECT COUNT(holidayCalendarRel) FROM HolidayCalendarRel holidayCalendarRel";
	private static final String _SQL_COUNT_HOLIDAYCALENDARREL_WHERE = "SELECT COUNT(holidayCalendarRel) FROM HolidayCalendarRel holidayCalendarRel WHERE ";
	private static final String _FINDER_COLUMN_HOLIDAYCALENDARID_HOLIDAYCALENDARID_2 =
		"holidayCalendarRel.holidayCalendarId = ?";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "holidayCalendarRel.userId = ?";
	private static final String _FINDER_COLUMN_HC_U_HOLIDAYCALENDARID_2 = "holidayCalendarRel.holidayCalendarId = ? AND ";
	private static final String _FINDER_COLUMN_HC_U_USERID_2 = "holidayCalendarRel.userId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "holidayCalendarRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HolidayCalendarRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HolidayCalendarRel exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HolidayCalendarRelPersistenceImpl.class);
	private static HolidayCalendarRel _nullHolidayCalendarRel = new HolidayCalendarRelImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HolidayCalendarRel> toCacheModel() {
				return _nullHolidayCalendarRelCacheModel;
			}
		};

	private static CacheModel<HolidayCalendarRel> _nullHolidayCalendarRelCacheModel =
		new CacheModel<HolidayCalendarRel>() {
			public HolidayCalendarRel toEntityModel() {
				return _nullHolidayCalendarRel;
			}
		};
}