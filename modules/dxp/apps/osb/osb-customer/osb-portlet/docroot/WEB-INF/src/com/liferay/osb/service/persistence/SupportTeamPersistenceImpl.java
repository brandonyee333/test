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

import com.liferay.osb.NoSuchSupportTeamException;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.model.impl.SupportTeamImpl;
import com.liferay.osb.model.impl.SupportTeamModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
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
import java.util.Set;

/**
 * The persistence implementation for the support team service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamPersistence
 * @see SupportTeamUtil
 * @generated
 */
public class SupportTeamPersistenceImpl extends BasePersistenceImpl<SupportTeam>
	implements SupportTeamPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SupportTeamUtil} to access the support team persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SupportTeamImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID =
		new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByParentSupportTeamId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID =
		new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByParentSupportTeamId", new String[] { Long.class.getName() },
			SupportTeamModelImpl.PARENTSUPPORTTEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARENTSUPPORTTEAMID = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByParentSupportTeamId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTLABORID =
		new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySupportLaborId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID =
		new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySupportLaborId",
			new String[] { Long.class.getName() },
			SupportTeamModelImpl.SUPPORTLABORID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTLABORID = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySupportLaborId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			SupportTeamModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, SupportTeamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the support team in the entity cache if it is enabled.
	 *
	 * @param supportTeam the support team
	 */
	public void cacheResult(SupportTeam supportTeam) {
		EntityCacheUtil.putResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamImpl.class, supportTeam.getPrimaryKey(), supportTeam);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { supportTeam.getName() }, supportTeam);

		supportTeam.resetOriginalValues();
	}

	/**
	 * Caches the support teams in the entity cache if it is enabled.
	 *
	 * @param supportTeams the support teams
	 */
	public void cacheResult(List<SupportTeam> supportTeams) {
		for (SupportTeam supportTeam : supportTeams) {
			if (EntityCacheUtil.getResult(
						SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
						SupportTeamImpl.class, supportTeam.getPrimaryKey()) == null) {
				cacheResult(supportTeam);
			}
			else {
				supportTeam.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all support teams.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SupportTeamImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SupportTeamImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support team.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SupportTeam supportTeam) {
		EntityCacheUtil.removeResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamImpl.class, supportTeam.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(supportTeam);
	}

	@Override
	public void clearCache(List<SupportTeam> supportTeams) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SupportTeam supportTeam : supportTeams) {
			EntityCacheUtil.removeResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
				SupportTeamImpl.class, supportTeam.getPrimaryKey());

			clearUniqueFindersCache(supportTeam);
		}
	}

	protected void cacheUniqueFindersCache(SupportTeam supportTeam) {
		if (supportTeam.isNew()) {
			Object[] args = new Object[] { supportTeam.getName() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME, args,
				supportTeam);
		}
		else {
			SupportTeamModelImpl supportTeamModelImpl = (SupportTeamModelImpl)supportTeam;

			if ((supportTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { supportTeam.getName() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME, args,
					supportTeam);
			}
		}
	}

	protected void clearUniqueFindersCache(SupportTeam supportTeam) {
		SupportTeamModelImpl supportTeamModelImpl = (SupportTeamModelImpl)supportTeam;

		Object[] args = new Object[] { supportTeam.getName() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);

		if ((supportTeamModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			args = new Object[] { supportTeamModelImpl.getOriginalName() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new support team with the primary key. Does not add the support team to the database.
	 *
	 * @param supportTeamId the primary key for the new support team
	 * @return the new support team
	 */
	public SupportTeam create(long supportTeamId) {
		SupportTeam supportTeam = new SupportTeamImpl();

		supportTeam.setNew(true);
		supportTeam.setPrimaryKey(supportTeamId);

		return supportTeam;
	}

	/**
	 * Removes the support team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportTeamId the primary key of the support team
	 * @return the support team that was removed
	 * @throws com.liferay.osb.NoSuchSupportTeamException if a support team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam remove(long supportTeamId)
		throws NoSuchSupportTeamException, SystemException {
		return remove(Long.valueOf(supportTeamId));
	}

	/**
	 * Removes the support team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support team
	 * @return the support team that was removed
	 * @throws com.liferay.osb.NoSuchSupportTeamException if a support team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportTeam remove(Serializable primaryKey)
		throws NoSuchSupportTeamException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SupportTeam supportTeam = (SupportTeam)session.get(SupportTeamImpl.class,
					primaryKey);

			if (supportTeam == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSupportTeamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(supportTeam);
		}
		catch (NoSuchSupportTeamException nsee) {
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
	protected SupportTeam removeImpl(SupportTeam supportTeam)
		throws SystemException {
		supportTeam = toUnwrappedModel(supportTeam);

		try {
			clearAccountEntries.clear(supportTeam.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}

		try {
			clearSupportRegions.clear(supportTeam.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, supportTeam);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(supportTeam);

		return supportTeam;
	}

	@Override
	public SupportTeam updateImpl(
		com.liferay.osb.model.SupportTeam supportTeam, boolean merge)
		throws SystemException {
		supportTeam = toUnwrappedModel(supportTeam);

		boolean isNew = supportTeam.isNew();

		SupportTeamModelImpl supportTeamModelImpl = (SupportTeamModelImpl)supportTeam;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, supportTeam, merge);

			supportTeam.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SupportTeamModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((supportTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(supportTeamModelImpl.getOriginalParentSupportTeamId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTSUPPORTTEAMID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID,
					args);

				args = new Object[] {
						Long.valueOf(supportTeamModelImpl.getParentSupportTeamId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTSUPPORTTEAMID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID,
					args);
			}

			if ((supportTeamModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(supportTeamModelImpl.getOriginalSupportLaborId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID,
					args);

				args = new Object[] {
						Long.valueOf(supportTeamModelImpl.getSupportLaborId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID,
					args);
			}
		}

		EntityCacheUtil.putResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamImpl.class, supportTeam.getPrimaryKey(), supportTeam);

		clearUniqueFindersCache(supportTeam);
		cacheUniqueFindersCache(supportTeam);

		return supportTeam;
	}

	protected SupportTeam toUnwrappedModel(SupportTeam supportTeam) {
		if (supportTeam instanceof SupportTeamImpl) {
			return supportTeam;
		}

		SupportTeamImpl supportTeamImpl = new SupportTeamImpl();

		supportTeamImpl.setNew(supportTeam.isNew());
		supportTeamImpl.setPrimaryKey(supportTeam.getPrimaryKey());

		supportTeamImpl.setSupportTeamId(supportTeam.getSupportTeamId());
		supportTeamImpl.setUserId(supportTeam.getUserId());
		supportTeamImpl.setUserName(supportTeam.getUserName());
		supportTeamImpl.setCreateDate(supportTeam.getCreateDate());
		supportTeamImpl.setModifiedDate(supportTeam.getModifiedDate());
		supportTeamImpl.setParentSupportTeamId(supportTeam.getParentSupportTeamId());
		supportTeamImpl.setSupportLaborId(supportTeam.getSupportLaborId());
		supportTeamImpl.setLocationSupportRegionId(supportTeam.getLocationSupportRegionId());
		supportTeamImpl.setName(supportTeam.getName());
		supportTeamImpl.setDescription(supportTeam.getDescription());
		supportTeamImpl.setType(supportTeam.getType());
		supportTeamImpl.setAssignedWork(supportTeam.getAssignedWork());
		supportTeamImpl.setMaxWork(supportTeam.getMaxWork());

		return supportTeamImpl;
	}

	/**
	 * Returns the support team with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the support team
	 * @return the support team
	 * @throws com.liferay.portal.NoSuchModelException if a support team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportTeam findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the support team with the primary key or throws a {@link com.liferay.osb.NoSuchSupportTeamException} if it could not be found.
	 *
	 * @param supportTeamId the primary key of the support team
	 * @return the support team
	 * @throws com.liferay.osb.NoSuchSupportTeamException if a support team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam findByPrimaryKey(long supportTeamId)
		throws NoSuchSupportTeamException, SystemException {
		SupportTeam supportTeam = fetchByPrimaryKey(supportTeamId);

		if (supportTeam == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + supportTeamId);
			}

			throw new NoSuchSupportTeamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				supportTeamId);
		}

		return supportTeam;
	}

	/**
	 * Returns the support team with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support team
	 * @return the support team, or <code>null</code> if a support team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SupportTeam fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the support team with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportTeamId the primary key of the support team
	 * @return the support team, or <code>null</code> if a support team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam fetchByPrimaryKey(long supportTeamId)
		throws SystemException {
		SupportTeam supportTeam = (SupportTeam)EntityCacheUtil.getResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
				SupportTeamImpl.class, supportTeamId);

		if (supportTeam == _nullSupportTeam) {
			return null;
		}

		if (supportTeam == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				supportTeam = (SupportTeam)session.get(SupportTeamImpl.class,
						Long.valueOf(supportTeamId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (supportTeam != null) {
					cacheResult(supportTeam);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SupportTeamModelImpl.ENTITY_CACHE_ENABLED,
						SupportTeamImpl.class, supportTeamId, _nullSupportTeam);
				}

				closeSession(session);
			}
		}

		return supportTeam;
	}

	/**
	 * Returns all the support teams where parentSupportTeamId = &#63;.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @return the matching support teams
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeam> findByParentSupportTeamId(long parentSupportTeamId)
		throws SystemException {
		return findByParentSupportTeamId(parentSupportTeamId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support teams where parentSupportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @return the range of matching support teams
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeam> findByParentSupportTeamId(
		long parentSupportTeamId, int start, int end) throws SystemException {
		return findByParentSupportTeamId(parentSupportTeamId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support teams where parentSupportTeamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support teams
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeam> findByParentSupportTeamId(
		long parentSupportTeamId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID;
			finderArgs = new Object[] { parentSupportTeamId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTSUPPORTTEAMID;
			finderArgs = new Object[] {
					parentSupportTeamId,
					
					start, end, orderByComparator
				};
		}

		List<SupportTeam> list = (List<SupportTeam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SupportTeam supportTeam : list) {
				if ((parentSupportTeamId != supportTeam.getParentSupportTeamId())) {
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
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SUPPORTTEAM_WHERE);

			query.append(_FINDER_COLUMN_PARENTSUPPORTTEAMID_PARENTSUPPORTTEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SupportTeamModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentSupportTeamId);

				list = (List<SupportTeam>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first support team in the ordered set where parentSupportTeamId = &#63;.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support team
	 * @throws com.liferay.osb.NoSuchSupportTeamException if a matching support team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam findByParentSupportTeamId_First(
		long parentSupportTeamId, OrderByComparator orderByComparator)
		throws NoSuchSupportTeamException, SystemException {
		SupportTeam supportTeam = fetchByParentSupportTeamId_First(parentSupportTeamId,
				orderByComparator);

		if (supportTeam != null) {
			return supportTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentSupportTeamId=");
		msg.append(parentSupportTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportTeamException(msg.toString());
	}

	/**
	 * Returns the first support team in the ordered set where parentSupportTeamId = &#63;.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support team, or <code>null</code> if a matching support team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam fetchByParentSupportTeamId_First(
		long parentSupportTeamId, OrderByComparator orderByComparator)
		throws SystemException {
		List<SupportTeam> list = findByParentSupportTeamId(parentSupportTeamId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support team in the ordered set where parentSupportTeamId = &#63;.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support team
	 * @throws com.liferay.osb.NoSuchSupportTeamException if a matching support team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam findByParentSupportTeamId_Last(
		long parentSupportTeamId, OrderByComparator orderByComparator)
		throws NoSuchSupportTeamException, SystemException {
		SupportTeam supportTeam = fetchByParentSupportTeamId_Last(parentSupportTeamId,
				orderByComparator);

		if (supportTeam != null) {
			return supportTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentSupportTeamId=");
		msg.append(parentSupportTeamId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportTeamException(msg.toString());
	}

	/**
	 * Returns the last support team in the ordered set where parentSupportTeamId = &#63;.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support team, or <code>null</code> if a matching support team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam fetchByParentSupportTeamId_Last(
		long parentSupportTeamId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByParentSupportTeamId(parentSupportTeamId);

		List<SupportTeam> list = findByParentSupportTeamId(parentSupportTeamId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support teams before and after the current support team in the ordered set where parentSupportTeamId = &#63;.
	 *
	 * @param supportTeamId the primary key of the current support team
	 * @param parentSupportTeamId the parent support team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support team
	 * @throws com.liferay.osb.NoSuchSupportTeamException if a support team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam[] findByParentSupportTeamId_PrevAndNext(
		long supportTeamId, long parentSupportTeamId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportTeamException, SystemException {
		SupportTeam supportTeam = findByPrimaryKey(supportTeamId);

		Session session = null;

		try {
			session = openSession();

			SupportTeam[] array = new SupportTeamImpl[3];

			array[0] = getByParentSupportTeamId_PrevAndNext(session,
					supportTeam, parentSupportTeamId, orderByComparator, true);

			array[1] = supportTeam;

			array[2] = getByParentSupportTeamId_PrevAndNext(session,
					supportTeam, parentSupportTeamId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportTeam getByParentSupportTeamId_PrevAndNext(
		Session session, SupportTeam supportTeam, long parentSupportTeamId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTTEAM_WHERE);

		query.append(_FINDER_COLUMN_PARENTSUPPORTTEAMID_PARENTSUPPORTTEAMID_2);

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
			query.append(SupportTeamModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentSupportTeamId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the support teams where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @return the matching support teams
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeam> findBySupportLaborId(long supportLaborId)
		throws SystemException {
		return findBySupportLaborId(supportLaborId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support teams where supportLaborId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportLaborId the support labor ID
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @return the range of matching support teams
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeam> findBySupportLaborId(long supportLaborId,
		int start, int end) throws SystemException {
		return findBySupportLaborId(supportLaborId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support teams where supportLaborId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param supportLaborId the support labor ID
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support teams
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeam> findBySupportLaborId(long supportLaborId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTLABORID;
			finderArgs = new Object[] { supportLaborId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTLABORID;
			finderArgs = new Object[] {
					supportLaborId,
					
					start, end, orderByComparator
				};
		}

		List<SupportTeam> list = (List<SupportTeam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SupportTeam supportTeam : list) {
				if ((supportLaborId != supportTeam.getSupportLaborId())) {
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
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SUPPORTTEAM_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SupportTeamModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportLaborId);

				list = (List<SupportTeam>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first support team in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support team
	 * @throws com.liferay.osb.NoSuchSupportTeamException if a matching support team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam findBySupportLaborId_First(long supportLaborId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportTeamException, SystemException {
		SupportTeam supportTeam = fetchBySupportLaborId_First(supportLaborId,
				orderByComparator);

		if (supportTeam != null) {
			return supportTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportLaborId=");
		msg.append(supportLaborId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportTeamException(msg.toString());
	}

	/**
	 * Returns the first support team in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support team, or <code>null</code> if a matching support team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam fetchBySupportLaborId_First(long supportLaborId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SupportTeam> list = findBySupportLaborId(supportLaborId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support team in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support team
	 * @throws com.liferay.osb.NoSuchSupportTeamException if a matching support team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam findBySupportLaborId_Last(long supportLaborId,
		OrderByComparator orderByComparator)
		throws NoSuchSupportTeamException, SystemException {
		SupportTeam supportTeam = fetchBySupportLaborId_Last(supportLaborId,
				orderByComparator);

		if (supportTeam != null) {
			return supportTeam;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportLaborId=");
		msg.append(supportLaborId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSupportTeamException(msg.toString());
	}

	/**
	 * Returns the last support team in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support team, or <code>null</code> if a matching support team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam fetchBySupportLaborId_Last(long supportLaborId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySupportLaborId(supportLaborId);

		List<SupportTeam> list = findBySupportLaborId(supportLaborId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support teams before and after the current support team in the ordered set where supportLaborId = &#63;.
	 *
	 * @param supportTeamId the primary key of the current support team
	 * @param supportLaborId the support labor ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support team
	 * @throws com.liferay.osb.NoSuchSupportTeamException if a support team with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam[] findBySupportLaborId_PrevAndNext(long supportTeamId,
		long supportLaborId, OrderByComparator orderByComparator)
		throws NoSuchSupportTeamException, SystemException {
		SupportTeam supportTeam = findByPrimaryKey(supportTeamId);

		Session session = null;

		try {
			session = openSession();

			SupportTeam[] array = new SupportTeamImpl[3];

			array[0] = getBySupportLaborId_PrevAndNext(session, supportTeam,
					supportLaborId, orderByComparator, true);

			array[1] = supportTeam;

			array[2] = getBySupportLaborId_PrevAndNext(session, supportTeam,
					supportLaborId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SupportTeam getBySupportLaborId_PrevAndNext(Session session,
		SupportTeam supportTeam, long supportLaborId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SUPPORTTEAM_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2);

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
			query.append(SupportTeamModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(supportLaborId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(supportTeam);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SupportTeam> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the support team where name = &#63; or throws a {@link com.liferay.osb.NoSuchSupportTeamException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching support team
	 * @throws com.liferay.osb.NoSuchSupportTeamException if a matching support team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam findByName(String name)
		throws NoSuchSupportTeamException, SystemException {
		SupportTeam supportTeam = fetchByName(name);

		if (supportTeam == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSupportTeamException(msg.toString());
		}

		return supportTeam;
	}

	/**
	 * Returns the support team where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching support team, or <code>null</code> if a matching support team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam fetchByName(String name) throws SystemException {
		return fetchByName(name, true);
	}

	/**
	 * Returns the support team where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching support team, or <code>null</code> if a matching support team could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam fetchByName(String name, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof SupportTeam) {
			SupportTeam supportTeam = (SupportTeam)result;

			if (!Validator.equals(name, supportTeam.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SUPPORTTEAM_WHERE);

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NAME_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NAME_NAME_2);
				}
			}

			query.append(SupportTeamModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				List<SupportTeam> list = q.list();

				result = list;

				SupportTeam supportTeam = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					supportTeam = list.get(0);

					cacheResult(supportTeam);

					if ((supportTeam.getName() == null) ||
							!supportTeam.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, supportTeam);
					}
				}

				return supportTeam;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
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
				return (SupportTeam)result;
			}
		}
	}

	/**
	 * Returns all the support teams.
	 *
	 * @return the support teams
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeam> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @return the range of support teams
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeam> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support teams
	 * @throws SystemException if a system exception occurred
	 */
	public List<SupportTeam> findAll(int start, int end,
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

		List<SupportTeam> list = (List<SupportTeam>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SUPPORTTEAM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTTEAM.concat(SupportTeamModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SupportTeam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SupportTeam>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the support teams where parentSupportTeamId = &#63; from the database.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByParentSupportTeamId(long parentSupportTeamId)
		throws SystemException {
		for (SupportTeam supportTeam : findByParentSupportTeamId(
				parentSupportTeamId)) {
			remove(supportTeam);
		}
	}

	/**
	 * Removes all the support teams where supportLaborId = &#63; from the database.
	 *
	 * @param supportLaborId the support labor ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySupportLaborId(long supportLaborId)
		throws SystemException {
		for (SupportTeam supportTeam : findBySupportLaborId(supportLaborId)) {
			remove(supportTeam);
		}
	}

	/**
	 * Removes the support team where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the support team that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public SupportTeam removeByName(String name)
		throws NoSuchSupportTeamException, SystemException {
		SupportTeam supportTeam = findByName(name);

		return remove(supportTeam);
	}

	/**
	 * Removes all the support teams from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SupportTeam supportTeam : findAll()) {
			remove(supportTeam);
		}
	}

	/**
	 * Returns the number of support teams where parentSupportTeamId = &#63;.
	 *
	 * @param parentSupportTeamId the parent support team ID
	 * @return the number of matching support teams
	 * @throws SystemException if a system exception occurred
	 */
	public int countByParentSupportTeamId(long parentSupportTeamId)
		throws SystemException {
		Object[] finderArgs = new Object[] { parentSupportTeamId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PARENTSUPPORTTEAMID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTTEAM_WHERE);

			query.append(_FINDER_COLUMN_PARENTSUPPORTTEAMID_PARENTSUPPORTTEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentSupportTeamId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PARENTSUPPORTTEAMID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support teams where supportLaborId = &#63;.
	 *
	 * @param supportLaborId the support labor ID
	 * @return the number of matching support teams
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySupportLaborId(long supportLaborId)
		throws SystemException {
		Object[] finderArgs = new Object[] { supportLaborId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTTEAM_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportLaborId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SUPPORTLABORID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support teams where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching support teams
	 * @throws SystemException if a system exception occurred
	 */
	public int countByName(String name) throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SUPPORTTEAM_WHERE);

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NAME_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_NAME_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of support teams.
	 *
	 * @return the number of support teams
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SUPPORTTEAM);

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
	 * Returns all the account entries associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @return the account entries associated with the support team
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.AccountEntry> getAccountEntries(long pk)
		throws SystemException {
		return getAccountEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the account entries associated with the support team.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the support team
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @return the range of account entries associated with the support team
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.AccountEntry> getAccountEntries(long pk,
		int start, int end) throws SystemException {
		return getAccountEntries(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_ACCOUNTENTRIES = new FinderPath(com.liferay.osb.model.impl.AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED_OSB_ACCOUNTENTRIES_SUPPORTTEAMS,
			com.liferay.osb.model.impl.AccountEntryImpl.class,
			SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME,
			"getAccountEntries",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_ACCOUNTENTRIES.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the account entries associated with the support team.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the support team
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account entries associated with the support team
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.AccountEntry> getAccountEntries(long pk,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.osb.model.AccountEntry> list = (List<com.liferay.osb.model.AccountEntry>)FinderCacheUtil.getResult(FINDER_PATH_GET_ACCOUNTENTRIES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETACCOUNTENTRIES.concat(ORDER_BY_CLAUSE)
												.concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETACCOUNTENTRIES.concat(com.liferay.osb.model.impl.AccountEntryModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OSB_AccountEntry",
					com.liferay.osb.model.impl.AccountEntryImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.osb.model.AccountEntry>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_ACCOUNTENTRIES,
						finderArgs);
				}
				else {
					accountEntryPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_ACCOUNTENTRIES,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_ACCOUNTENTRIES_SIZE = new FinderPath(com.liferay.osb.model.impl.AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED_OSB_ACCOUNTENTRIES_SUPPORTTEAMS,
			Long.class,
			SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME,
			"getAccountEntriesSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_ACCOUNTENTRIES_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of account entries associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @return the number of account entries associated with the support team
	 * @throws SystemException if a system exception occurred
	 */
	public int getAccountEntriesSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_ACCOUNTENTRIES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETACCOUNTENTRIESSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_ACCOUNTENTRIES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_ACCOUNTENTRY = new FinderPath(com.liferay.osb.model.impl.AccountEntryModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED_OSB_ACCOUNTENTRIES_SUPPORTTEAMS,
			Boolean.class,
			SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME,
			"containsAccountEntry",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the account entry is associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntryPK the primary key of the account entry
	 * @return <code>true</code> if the account entry is associated with the support team; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsAccountEntry(long pk, long accountEntryPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, accountEntryPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_ACCOUNTENTRY,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsAccountEntry.contains(pk,
							accountEntryPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_ACCOUNTENTRY,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the support team has any account entries associated with it.
	 *
	 * @param pk the primary key of the support team to check for associations with account entries
	 * @return <code>true</code> if the support team has any account entries associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsAccountEntries(long pk) throws SystemException {
		if (getAccountEntriesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the support team and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntryPK the primary key of the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public void addAccountEntry(long pk, long accountEntryPK)
		throws SystemException {
		try {
			addAccountEntry.add(pk, accountEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Adds an association between the support team and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntry the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public void addAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry)
		throws SystemException {
		try {
			addAccountEntry.add(pk, accountEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Adds an association between the support team and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntryPKs the primary keys of the account entries
	 * @throws SystemException if a system exception occurred
	 */
	public void addAccountEntries(long pk, long[] accountEntryPKs)
		throws SystemException {
		try {
			for (long accountEntryPK : accountEntryPKs) {
				addAccountEntry.add(pk, accountEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Adds an association between the support team and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntries the account entries
	 * @throws SystemException if a system exception occurred
	 */
	public void addAccountEntries(long pk,
		List<com.liferay.osb.model.AccountEntry> accountEntries)
		throws SystemException {
		try {
			for (com.liferay.osb.model.AccountEntry accountEntry : accountEntries) {
				addAccountEntry.add(pk, accountEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Clears all associations between the support team and its account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team to clear the associated account entries from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearAccountEntries(long pk) throws SystemException {
		try {
			clearAccountEntries.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Removes the association between the support team and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntryPK the primary key of the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAccountEntry(long pk, long accountEntryPK)
		throws SystemException {
		try {
			removeAccountEntry.remove(pk, accountEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Removes the association between the support team and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntry the account entry
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry)
		throws SystemException {
		try {
			removeAccountEntry.remove(pk, accountEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Removes the association between the support team and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntryPKs the primary keys of the account entries
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAccountEntries(long pk, long[] accountEntryPKs)
		throws SystemException {
		try {
			for (long accountEntryPK : accountEntryPKs) {
				removeAccountEntry.remove(pk, accountEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Removes the association between the support team and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntries the account entries
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAccountEntries(long pk,
		List<com.liferay.osb.model.AccountEntry> accountEntries)
		throws SystemException {
		try {
			for (com.liferay.osb.model.AccountEntry accountEntry : accountEntries) {
				removeAccountEntry.remove(pk, accountEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Sets the account entries associated with the support team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntryPKs the primary keys of the account entries to be associated with the support team
	 * @throws SystemException if a system exception occurred
	 */
	public void setAccountEntries(long pk, long[] accountEntryPKs)
		throws SystemException {
		try {
			Set<Long> accountEntryPKSet = SetUtil.fromArray(accountEntryPKs);

			List<com.liferay.osb.model.AccountEntry> accountEntries = getAccountEntries(pk);

			for (com.liferay.osb.model.AccountEntry accountEntry : accountEntries) {
				if (!accountEntryPKSet.remove(accountEntry.getPrimaryKey())) {
					removeAccountEntry.remove(pk, accountEntry.getPrimaryKey());
				}
			}

			for (Long accountEntryPK : accountEntryPKSet) {
				addAccountEntry.add(pk, accountEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Sets the account entries associated with the support team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param accountEntries the account entries to be associated with the support team
	 * @throws SystemException if a system exception occurred
	 */
	public void setAccountEntries(long pk,
		List<com.liferay.osb.model.AccountEntry> accountEntries)
		throws SystemException {
		try {
			long[] accountEntryPKs = new long[accountEntries.size()];

			for (int i = 0; i < accountEntries.size(); i++) {
				com.liferay.osb.model.AccountEntry accountEntry = accountEntries.get(i);

				accountEntryPKs[i] = accountEntry.getPrimaryKey();
			}

			setAccountEntries(pk, accountEntryPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTTEAMS_NAME);
		}
	}

	/**
	 * Returns all the support regions associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @return the support regions associated with the support team
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(long pk)
		throws SystemException {
		return getSupportRegions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the support regions associated with the support team.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the support team
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @return the range of support regions associated with the support team
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end) throws SystemException {
		return getSupportRegions(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_SUPPORTREGIONS = new FinderPath(com.liferay.osb.model.impl.SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED_OSB_SUPPORTTEAMS_SUPPORTREGIONS,
			com.liferay.osb.model.impl.SupportRegionImpl.class,
			SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME,
			"getSupportRegions",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_SUPPORTREGIONS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the support regions associated with the support team.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the support team
	 * @param start the lower bound of the range of support teams
	 * @param end the upper bound of the range of support teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support regions associated with the support team
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.osb.model.SupportRegion> list = (List<com.liferay.osb.model.SupportRegion>)FinderCacheUtil.getResult(FINDER_PATH_GET_SUPPORTREGIONS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETSUPPORTREGIONS.concat(ORDER_BY_CLAUSE)
												.concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETSUPPORTREGIONS.concat(com.liferay.osb.model.impl.SupportRegionModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OSB_SupportRegion",
					com.liferay.osb.model.impl.SupportRegionImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.osb.model.SupportRegion>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_SUPPORTREGIONS,
						finderArgs);
				}
				else {
					supportRegionPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_SUPPORTREGIONS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_SUPPORTREGIONS_SIZE = new FinderPath(com.liferay.osb.model.impl.SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED_OSB_SUPPORTTEAMS_SUPPORTREGIONS,
			Long.class,
			SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME,
			"getSupportRegionsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_SUPPORTREGIONS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of support regions associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @return the number of support regions associated with the support team
	 * @throws SystemException if a system exception occurred
	 */
	public int getSupportRegionsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_SUPPORTREGIONS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETSUPPORTREGIONSSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_SUPPORTREGIONS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_SUPPORTREGION = new FinderPath(com.liferay.osb.model.impl.SupportRegionModelImpl.ENTITY_CACHE_ENABLED,
			SupportTeamModelImpl.FINDER_CACHE_ENABLED_OSB_SUPPORTTEAMS_SUPPORTREGIONS,
			Boolean.class,
			SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME,
			"containsSupportRegion",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the support region is associated with the support team.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegionPK the primary key of the support region
	 * @return <code>true</code> if the support region is associated with the support team; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsSupportRegion(long pk, long supportRegionPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, supportRegionPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_SUPPORTREGION,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsSupportRegion.contains(pk,
							supportRegionPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_SUPPORTREGION,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the support team has any support regions associated with it.
	 *
	 * @param pk the primary key of the support team to check for associations with support regions
	 * @return <code>true</code> if the support team has any support regions associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsSupportRegions(long pk) throws SystemException {
		if (getSupportRegionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the support team and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegionPK the primary key of the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportRegion(long pk, long supportRegionPK)
		throws SystemException {
		try {
			addSupportRegion.add(pk, supportRegionPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the support team and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegion the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws SystemException {
		try {
			addSupportRegion.add(pk, supportRegion.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the support team and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegionPKs the primary keys of the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportRegions(long pk, long[] supportRegionPKs)
		throws SystemException {
		try {
			for (long supportRegionPK : supportRegionPKs) {
				addSupportRegion.add(pk, supportRegionPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Adds an association between the support team and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegions the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public void addSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws SystemException {
		try {
			for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
				addSupportRegion.add(pk, supportRegion.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Clears all associations between the support team and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team to clear the associated support regions from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearSupportRegions(long pk) throws SystemException {
		try {
			clearSupportRegions.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support team and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegionPK the primary key of the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportRegion(long pk, long supportRegionPK)
		throws SystemException {
		try {
			removeSupportRegion.remove(pk, supportRegionPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support team and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegion the support region
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws SystemException {
		try {
			removeSupportRegion.remove(pk, supportRegion.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support team and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegionPKs the primary keys of the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportRegions(long pk, long[] supportRegionPKs)
		throws SystemException {
		try {
			for (long supportRegionPK : supportRegionPKs) {
				removeSupportRegion.remove(pk, supportRegionPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Removes the association between the support team and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegions the support regions
	 * @throws SystemException if a system exception occurred
	 */
	public void removeSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws SystemException {
		try {
			for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
				removeSupportRegion.remove(pk, supportRegion.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Sets the support regions associated with the support team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegionPKs the primary keys of the support regions to be associated with the support team
	 * @throws SystemException if a system exception occurred
	 */
	public void setSupportRegions(long pk, long[] supportRegionPKs)
		throws SystemException {
		try {
			Set<Long> supportRegionPKSet = SetUtil.fromArray(supportRegionPKs);

			List<com.liferay.osb.model.SupportRegion> supportRegions = getSupportRegions(pk);

			for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
				if (!supportRegionPKSet.remove(supportRegion.getPrimaryKey())) {
					removeSupportRegion.remove(pk, supportRegion.getPrimaryKey());
				}
			}

			for (Long supportRegionPK : supportRegionPKSet) {
				addSupportRegion.add(pk, supportRegionPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Sets the support regions associated with the support team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support team
	 * @param supportRegions the support regions to be associated with the support team
	 * @throws SystemException if a system exception occurred
	 */
	public void setSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws SystemException {
		try {
			long[] supportRegionPKs = new long[supportRegions.size()];

			for (int i = 0; i < supportRegions.size(); i++) {
				com.liferay.osb.model.SupportRegion supportRegion = supportRegions.get(i);

				supportRegionPKs[i] = supportRegion.getPrimaryKey();
			}

			setSupportRegions(pk, supportRegionPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(SupportTeamModelImpl.MAPPING_TABLE_OSB_SUPPORTTEAMS_SUPPORTREGIONS_NAME);
		}
	}

	/**
	 * Initializes the support team persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.SupportTeam")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SupportTeam>> listenersList = new ArrayList<ModelListener<SupportTeam>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<SupportTeam>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsAccountEntry = new ContainsAccountEntry();

		addAccountEntry = new AddAccountEntry();
		clearAccountEntries = new ClearAccountEntries();
		removeAccountEntry = new RemoveAccountEntry();

		containsSupportRegion = new ContainsSupportRegion();

		addSupportRegion = new AddSupportRegion();
		clearSupportRegions = new ClearSupportRegions();
		removeSupportRegion = new RemoveSupportRegion();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SupportTeamImpl.class.getName());
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
	protected ContainsAccountEntry containsAccountEntry;
	protected AddAccountEntry addAccountEntry;
	protected ClearAccountEntries clearAccountEntries;
	protected RemoveAccountEntry removeAccountEntry;
	protected ContainsSupportRegion containsSupportRegion;
	protected AddSupportRegion addSupportRegion;
	protected ClearSupportRegions clearSupportRegions;
	protected RemoveSupportRegion removeSupportRegion;

	protected class ContainsAccountEntry {
		protected ContainsAccountEntry() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSACCOUNTENTRY,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long supportTeamId, long accountEntryId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(supportTeamId), new Long(accountEntryId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	protected class AddAccountEntry {
		protected AddAccountEntry() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO OSB_AccountEntries_SupportTeams (supportTeamId, accountEntryId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long supportTeamId, long accountEntryId)
			throws SystemException {
			if (!containsAccountEntry.contains(supportTeamId, accountEntryId)) {
				ModelListener<com.liferay.osb.model.AccountEntry>[] accountEntryListeners =
					accountEntryPersistence.getListeners();

				for (ModelListener<SupportTeam> listener : listeners) {
					listener.onBeforeAddAssociation(supportTeamId,
						com.liferay.osb.model.AccountEntry.class.getName(),
						accountEntryId);
				}

				for (ModelListener<com.liferay.osb.model.AccountEntry> listener : accountEntryListeners) {
					listener.onBeforeAddAssociation(accountEntryId,
						SupportTeam.class.getName(), supportTeamId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(supportTeamId), new Long(accountEntryId)
					});

				for (ModelListener<SupportTeam> listener : listeners) {
					listener.onAfterAddAssociation(supportTeamId,
						com.liferay.osb.model.AccountEntry.class.getName(),
						accountEntryId);
				}

				for (ModelListener<com.liferay.osb.model.AccountEntry> listener : accountEntryListeners) {
					listener.onAfterAddAssociation(accountEntryId,
						SupportTeam.class.getName(), supportTeamId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearAccountEntries {
		protected ClearAccountEntries() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_AccountEntries_SupportTeams WHERE supportTeamId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long supportTeamId) throws SystemException {
			ModelListener<com.liferay.osb.model.AccountEntry>[] accountEntryListeners =
				accountEntryPersistence.getListeners();

			List<com.liferay.osb.model.AccountEntry> accountEntries = null;

			if ((listeners.length > 0) || (accountEntryListeners.length > 0)) {
				accountEntries = getAccountEntries(supportTeamId);

				for (com.liferay.osb.model.AccountEntry accountEntry : accountEntries) {
					for (ModelListener<SupportTeam> listener : listeners) {
						listener.onBeforeRemoveAssociation(supportTeamId,
							com.liferay.osb.model.AccountEntry.class.getName(),
							accountEntry.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.AccountEntry> listener : accountEntryListeners) {
						listener.onBeforeRemoveAssociation(accountEntry.getPrimaryKey(),
							SupportTeam.class.getName(), supportTeamId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(supportTeamId) });

			if ((listeners.length > 0) || (accountEntryListeners.length > 0)) {
				for (com.liferay.osb.model.AccountEntry accountEntry : accountEntries) {
					for (ModelListener<SupportTeam> listener : listeners) {
						listener.onAfterRemoveAssociation(supportTeamId,
							com.liferay.osb.model.AccountEntry.class.getName(),
							accountEntry.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.AccountEntry> listener : accountEntryListeners) {
						listener.onAfterRemoveAssociation(accountEntry.getPrimaryKey(),
							SupportTeam.class.getName(), supportTeamId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveAccountEntry {
		protected RemoveAccountEntry() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_AccountEntries_SupportTeams WHERE supportTeamId = ? AND accountEntryId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long supportTeamId, long accountEntryId)
			throws SystemException {
			if (containsAccountEntry.contains(supportTeamId, accountEntryId)) {
				ModelListener<com.liferay.osb.model.AccountEntry>[] accountEntryListeners =
					accountEntryPersistence.getListeners();

				for (ModelListener<SupportTeam> listener : listeners) {
					listener.onBeforeRemoveAssociation(supportTeamId,
						com.liferay.osb.model.AccountEntry.class.getName(),
						accountEntryId);
				}

				for (ModelListener<com.liferay.osb.model.AccountEntry> listener : accountEntryListeners) {
					listener.onBeforeRemoveAssociation(accountEntryId,
						SupportTeam.class.getName(), supportTeamId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(supportTeamId), new Long(accountEntryId)
					});

				for (ModelListener<SupportTeam> listener : listeners) {
					listener.onAfterRemoveAssociation(supportTeamId,
						com.liferay.osb.model.AccountEntry.class.getName(),
						accountEntryId);
				}

				for (ModelListener<com.liferay.osb.model.AccountEntry> listener : accountEntryListeners) {
					listener.onAfterRemoveAssociation(accountEntryId,
						SupportTeam.class.getName(), supportTeamId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ContainsSupportRegion {
		protected ContainsSupportRegion() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSSUPPORTREGION,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long supportTeamId, long supportRegionId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(supportTeamId), new Long(supportRegionId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	protected class AddSupportRegion {
		protected AddSupportRegion() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO OSB_SupportTeams_SupportRegions (supportTeamId, supportRegionId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long supportTeamId, long supportRegionId)
			throws SystemException {
			if (!containsSupportRegion.contains(supportTeamId, supportRegionId)) {
				ModelListener<com.liferay.osb.model.SupportRegion>[] supportRegionListeners =
					supportRegionPersistence.getListeners();

				for (ModelListener<SupportTeam> listener : listeners) {
					listener.onBeforeAddAssociation(supportTeamId,
						com.liferay.osb.model.SupportRegion.class.getName(),
						supportRegionId);
				}

				for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
					listener.onBeforeAddAssociation(supportRegionId,
						SupportTeam.class.getName(), supportTeamId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(supportTeamId), new Long(supportRegionId)
					});

				for (ModelListener<SupportTeam> listener : listeners) {
					listener.onAfterAddAssociation(supportTeamId,
						com.liferay.osb.model.SupportRegion.class.getName(),
						supportRegionId);
				}

				for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
					listener.onAfterAddAssociation(supportRegionId,
						SupportTeam.class.getName(), supportTeamId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearSupportRegions {
		protected ClearSupportRegions() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_SupportTeams_SupportRegions WHERE supportTeamId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long supportTeamId) throws SystemException {
			ModelListener<com.liferay.osb.model.SupportRegion>[] supportRegionListeners =
				supportRegionPersistence.getListeners();

			List<com.liferay.osb.model.SupportRegion> supportRegions = null;

			if ((listeners.length > 0) || (supportRegionListeners.length > 0)) {
				supportRegions = getSupportRegions(supportTeamId);

				for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
					for (ModelListener<SupportTeam> listener : listeners) {
						listener.onBeforeRemoveAssociation(supportTeamId,
							com.liferay.osb.model.SupportRegion.class.getName(),
							supportRegion.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
						listener.onBeforeRemoveAssociation(supportRegion.getPrimaryKey(),
							SupportTeam.class.getName(), supportTeamId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(supportTeamId) });

			if ((listeners.length > 0) || (supportRegionListeners.length > 0)) {
				for (com.liferay.osb.model.SupportRegion supportRegion : supportRegions) {
					for (ModelListener<SupportTeam> listener : listeners) {
						listener.onAfterRemoveAssociation(supportTeamId,
							com.liferay.osb.model.SupportRegion.class.getName(),
							supportRegion.getPrimaryKey());
					}

					for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
						listener.onAfterRemoveAssociation(supportRegion.getPrimaryKey(),
							SupportTeam.class.getName(), supportTeamId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveSupportRegion {
		protected RemoveSupportRegion() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM OSB_SupportTeams_SupportRegions WHERE supportTeamId = ? AND supportRegionId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long supportTeamId, long supportRegionId)
			throws SystemException {
			if (containsSupportRegion.contains(supportTeamId, supportRegionId)) {
				ModelListener<com.liferay.osb.model.SupportRegion>[] supportRegionListeners =
					supportRegionPersistence.getListeners();

				for (ModelListener<SupportTeam> listener : listeners) {
					listener.onBeforeRemoveAssociation(supportTeamId,
						com.liferay.osb.model.SupportRegion.class.getName(),
						supportRegionId);
				}

				for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
					listener.onBeforeRemoveAssociation(supportRegionId,
						SupportTeam.class.getName(), supportTeamId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(supportTeamId), new Long(supportRegionId)
					});

				for (ModelListener<SupportTeam> listener : listeners) {
					listener.onAfterRemoveAssociation(supportTeamId,
						com.liferay.osb.model.SupportRegion.class.getName(),
						supportRegionId);
				}

				for (ModelListener<com.liferay.osb.model.SupportRegion> listener : supportRegionListeners) {
					listener.onAfterRemoveAssociation(supportRegionId,
						SupportTeam.class.getName(), supportTeamId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_SELECT_SUPPORTTEAM = "SELECT supportTeam FROM SupportTeam supportTeam";
	private static final String _SQL_SELECT_SUPPORTTEAM_WHERE = "SELECT supportTeam FROM SupportTeam supportTeam WHERE ";
	private static final String _SQL_COUNT_SUPPORTTEAM = "SELECT COUNT(supportTeam) FROM SupportTeam supportTeam";
	private static final String _SQL_COUNT_SUPPORTTEAM_WHERE = "SELECT COUNT(supportTeam) FROM SupportTeam supportTeam WHERE ";
	private static final String _SQL_GETACCOUNTENTRIES = "SELECT {OSB_AccountEntry.*} FROM OSB_AccountEntry INNER JOIN OSB_AccountEntries_SupportTeams ON (OSB_AccountEntries_SupportTeams.accountEntryId = OSB_AccountEntry.accountEntryId) WHERE (OSB_AccountEntries_SupportTeams.supportTeamId = ?)";
	private static final String _SQL_GETACCOUNTENTRIESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_AccountEntries_SupportTeams WHERE supportTeamId = ?";
	private static final String _SQL_CONTAINSACCOUNTENTRY = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_AccountEntries_SupportTeams WHERE supportTeamId = ? AND accountEntryId = ?";
	private static final String _SQL_GETSUPPORTREGIONS = "SELECT {OSB_SupportRegion.*} FROM OSB_SupportRegion INNER JOIN OSB_SupportTeams_SupportRegions ON (OSB_SupportTeams_SupportRegions.supportRegionId = OSB_SupportRegion.supportRegionId) WHERE (OSB_SupportTeams_SupportRegions.supportTeamId = ?)";
	private static final String _SQL_GETSUPPORTREGIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_SupportTeams_SupportRegions WHERE supportTeamId = ?";
	private static final String _SQL_CONTAINSSUPPORTREGION = "SELECT COUNT(*) AS COUNT_VALUE FROM OSB_SupportTeams_SupportRegions WHERE supportTeamId = ? AND supportRegionId = ?";
	private static final String _FINDER_COLUMN_PARENTSUPPORTTEAMID_PARENTSUPPORTTEAMID_2 =
		"supportTeam.parentSupportTeamId = ?";
	private static final String _FINDER_COLUMN_SUPPORTLABORID_SUPPORTLABORID_2 = "supportTeam.supportLaborId = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_1 = "supportTeam.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "supportTeam.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(supportTeam.name IS NULL OR supportTeam.name = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "supportTeam.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SupportTeam exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SupportTeam exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SupportTeamPersistenceImpl.class);
	private static SupportTeam _nullSupportTeam = new SupportTeamImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SupportTeam> toCacheModel() {
				return _nullSupportTeamCacheModel;
			}
		};

	private static CacheModel<SupportTeam> _nullSupportTeamCacheModel = new CacheModel<SupportTeam>() {
			public SupportTeam toEntityModel() {
				return _nullSupportTeam;
			}
		};
}