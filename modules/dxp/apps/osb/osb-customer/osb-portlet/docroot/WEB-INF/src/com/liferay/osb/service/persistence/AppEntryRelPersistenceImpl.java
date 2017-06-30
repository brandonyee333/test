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

import com.liferay.osb.NoSuchAppEntryRelException;
import com.liferay.osb.model.AppEntryRel;
import com.liferay.osb.model.impl.AppEntryRelImpl;
import com.liferay.osb.model.impl.AppEntryRelModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
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
 * The persistence implementation for the app entry rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppEntryRelPersistence
 * @see AppEntryRelUtil
 * @generated
 */
public class AppEntryRelPersistenceImpl extends BasePersistenceImpl<AppEntryRel>
	implements AppEntryRelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AppEntryRelUtil} to access the app entry rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AppEntryRelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelModelImpl.FINDER_CACHE_ENABLED, AppEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelModelImpl.FINDER_CACHE_ENABLED, AppEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			AppEntryRelModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI1_T = new FinderPath(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelModelImpl.FINDER_CACHE_ENABLED, AppEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAEI1_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI1_T =
		new FinderPath(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelModelImpl.FINDER_CACHE_ENABLED, AppEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAEI1_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			AppEntryRelModelImpl.APPENTRYID1_COLUMN_BITMASK |
			AppEntryRelModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI1_T = new FinderPath(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI1_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_AEI1_AEI2_T = new FinderPath(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelModelImpl.FINDER_CACHE_ENABLED, AppEntryRelImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAEI1_AEI2_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			AppEntryRelModelImpl.APPENTRYID1_COLUMN_BITMASK |
			AppEntryRelModelImpl.APPENTRYID2_COLUMN_BITMASK |
			AppEntryRelModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AEI1_AEI2_T = new FinderPath(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAEI1_AEI2_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelModelImpl.FINDER_CACHE_ENABLED, AppEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelModelImpl.FINDER_CACHE_ENABLED, AppEntryRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the app entry rel in the entity cache if it is enabled.
	 *
	 * @param appEntryRel the app entry rel
	 */
	public void cacheResult(AppEntryRel appEntryRel) {
		EntityCacheUtil.putResult(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelImpl.class, appEntryRel.getPrimaryKey(), appEntryRel);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI1_AEI2_T,
			new Object[] {
				Long.valueOf(appEntryRel.getAppEntryId1()),
				Long.valueOf(appEntryRel.getAppEntryId2()),
				Integer.valueOf(appEntryRel.getType())
			}, appEntryRel);

		appEntryRel.resetOriginalValues();
	}

	/**
	 * Caches the app entry rels in the entity cache if it is enabled.
	 *
	 * @param appEntryRels the app entry rels
	 */
	public void cacheResult(List<AppEntryRel> appEntryRels) {
		for (AppEntryRel appEntryRel : appEntryRels) {
			if (EntityCacheUtil.getResult(
						AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
						AppEntryRelImpl.class, appEntryRel.getPrimaryKey()) == null) {
				cacheResult(appEntryRel);
			}
			else {
				appEntryRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all app entry rels.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AppEntryRelImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AppEntryRelImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the app entry rel.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AppEntryRel appEntryRel) {
		EntityCacheUtil.removeResult(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelImpl.class, appEntryRel.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(appEntryRel);
	}

	@Override
	public void clearCache(List<AppEntryRel> appEntryRels) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AppEntryRel appEntryRel : appEntryRels) {
			EntityCacheUtil.removeResult(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
				AppEntryRelImpl.class, appEntryRel.getPrimaryKey());

			clearUniqueFindersCache(appEntryRel);
		}
	}

	protected void cacheUniqueFindersCache(AppEntryRel appEntryRel) {
		if (appEntryRel.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(appEntryRel.getAppEntryId1()),
					Long.valueOf(appEntryRel.getAppEntryId2()),
					Integer.valueOf(appEntryRel.getType())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI1_AEI2_T, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI1_AEI2_T, args,
				appEntryRel);
		}
		else {
			AppEntryRelModelImpl appEntryRelModelImpl = (AppEntryRelModelImpl)appEntryRel;

			if ((appEntryRelModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_AEI1_AEI2_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appEntryRel.getAppEntryId1()),
						Long.valueOf(appEntryRel.getAppEntryId2()),
						Integer.valueOf(appEntryRel.getType())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI1_AEI2_T,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI1_AEI2_T,
					args, appEntryRel);
			}
		}
	}

	protected void clearUniqueFindersCache(AppEntryRel appEntryRel) {
		AppEntryRelModelImpl appEntryRelModelImpl = (AppEntryRelModelImpl)appEntryRel;

		Object[] args = new Object[] {
				Long.valueOf(appEntryRel.getAppEntryId1()),
				Long.valueOf(appEntryRel.getAppEntryId2()),
				Integer.valueOf(appEntryRel.getType())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI1_AEI2_T, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI1_AEI2_T, args);

		if ((appEntryRelModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_AEI1_AEI2_T.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(appEntryRelModelImpl.getOriginalAppEntryId1()),
					Long.valueOf(appEntryRelModelImpl.getOriginalAppEntryId2()),
					Integer.valueOf(appEntryRelModelImpl.getOriginalType())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI1_AEI2_T, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI1_AEI2_T, args);
		}
	}

	/**
	 * Creates a new app entry rel with the primary key. Does not add the app entry rel to the database.
	 *
	 * @param appEntryRelId the primary key for the new app entry rel
	 * @return the new app entry rel
	 */
	public AppEntryRel create(long appEntryRelId) {
		AppEntryRel appEntryRel = new AppEntryRelImpl();

		appEntryRel.setNew(true);
		appEntryRel.setPrimaryKey(appEntryRelId);

		String uuid = PortalUUIDUtil.generate();

		appEntryRel.setUuid(uuid);

		return appEntryRel;
	}

	/**
	 * Removes the app entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param appEntryRelId the primary key of the app entry rel
	 * @return the app entry rel that was removed
	 * @throws com.liferay.osb.NoSuchAppEntryRelException if a app entry rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel remove(long appEntryRelId)
		throws NoSuchAppEntryRelException, SystemException {
		return remove(Long.valueOf(appEntryRelId));
	}

	/**
	 * Removes the app entry rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the app entry rel
	 * @return the app entry rel that was removed
	 * @throws com.liferay.osb.NoSuchAppEntryRelException if a app entry rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppEntryRel remove(Serializable primaryKey)
		throws NoSuchAppEntryRelException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AppEntryRel appEntryRel = (AppEntryRel)session.get(AppEntryRelImpl.class,
					primaryKey);

			if (appEntryRel == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAppEntryRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(appEntryRel);
		}
		catch (NoSuchAppEntryRelException nsee) {
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
	protected AppEntryRel removeImpl(AppEntryRel appEntryRel)
		throws SystemException {
		appEntryRel = toUnwrappedModel(appEntryRel);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, appEntryRel);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(appEntryRel);

		return appEntryRel;
	}

	@Override
	public AppEntryRel updateImpl(
		com.liferay.osb.model.AppEntryRel appEntryRel, boolean merge)
		throws SystemException {
		appEntryRel = toUnwrappedModel(appEntryRel);

		boolean isNew = appEntryRel.isNew();

		AppEntryRelModelImpl appEntryRelModelImpl = (AppEntryRelModelImpl)appEntryRel;

		if (Validator.isNull(appEntryRel.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			appEntryRel.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, appEntryRel, merge);

			appEntryRel.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AppEntryRelModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((appEntryRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						appEntryRelModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { appEntryRelModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((appEntryRelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI1_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(appEntryRelModelImpl.getOriginalAppEntryId1()),
						Integer.valueOf(appEntryRelModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI1_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI1_T,
					args);

				args = new Object[] {
						Long.valueOf(appEntryRelModelImpl.getAppEntryId1()),
						Integer.valueOf(appEntryRelModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AEI1_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI1_T,
					args);
			}
		}

		EntityCacheUtil.putResult(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
			AppEntryRelImpl.class, appEntryRel.getPrimaryKey(), appEntryRel);

		clearUniqueFindersCache(appEntryRel);
		cacheUniqueFindersCache(appEntryRel);

		return appEntryRel;
	}

	protected AppEntryRel toUnwrappedModel(AppEntryRel appEntryRel) {
		if (appEntryRel instanceof AppEntryRelImpl) {
			return appEntryRel;
		}

		AppEntryRelImpl appEntryRelImpl = new AppEntryRelImpl();

		appEntryRelImpl.setNew(appEntryRel.isNew());
		appEntryRelImpl.setPrimaryKey(appEntryRel.getPrimaryKey());

		appEntryRelImpl.setUuid(appEntryRel.getUuid());
		appEntryRelImpl.setAppEntryRelId(appEntryRel.getAppEntryRelId());
		appEntryRelImpl.setAppEntryId1(appEntryRel.getAppEntryId1());
		appEntryRelImpl.setAppEntryId2(appEntryRel.getAppEntryId2());
		appEntryRelImpl.setType(appEntryRel.getType());

		return appEntryRelImpl;
	}

	/**
	 * Returns the app entry rel with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the app entry rel
	 * @return the app entry rel
	 * @throws com.liferay.portal.NoSuchModelException if a app entry rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppEntryRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app entry rel with the primary key or throws a {@link com.liferay.osb.NoSuchAppEntryRelException} if it could not be found.
	 *
	 * @param appEntryRelId the primary key of the app entry rel
	 * @return the app entry rel
	 * @throws com.liferay.osb.NoSuchAppEntryRelException if a app entry rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel findByPrimaryKey(long appEntryRelId)
		throws NoSuchAppEntryRelException, SystemException {
		AppEntryRel appEntryRel = fetchByPrimaryKey(appEntryRelId);

		if (appEntryRel == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + appEntryRelId);
			}

			throw new NoSuchAppEntryRelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				appEntryRelId);
		}

		return appEntryRel;
	}

	/**
	 * Returns the app entry rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the app entry rel
	 * @return the app entry rel, or <code>null</code> if a app entry rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AppEntryRel fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the app entry rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param appEntryRelId the primary key of the app entry rel
	 * @return the app entry rel, or <code>null</code> if a app entry rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel fetchByPrimaryKey(long appEntryRelId)
		throws SystemException {
		AppEntryRel appEntryRel = (AppEntryRel)EntityCacheUtil.getResult(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
				AppEntryRelImpl.class, appEntryRelId);

		if (appEntryRel == _nullAppEntryRel) {
			return null;
		}

		if (appEntryRel == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				appEntryRel = (AppEntryRel)session.get(AppEntryRelImpl.class,
						Long.valueOf(appEntryRelId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (appEntryRel != null) {
					cacheResult(appEntryRel);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AppEntryRelModelImpl.ENTITY_CACHE_ENABLED,
						AppEntryRelImpl.class, appEntryRelId, _nullAppEntryRel);
				}

				closeSession(session);
			}
		}

		return appEntryRel;
	}

	/**
	 * Returns all the app entry rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntryRel> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entry rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of app entry rels
	 * @param end the upper bound of the range of app entry rels (not inclusive)
	 * @return the range of matching app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntryRel> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entry rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of app entry rels
	 * @param end the upper bound of the range of app entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntryRel> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<AppEntryRel> list = (List<AppEntryRel>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppEntryRel appEntryRel : list) {
				if (!Validator.equals(uuid, appEntryRel.getUuid())) {
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

			query.append(_SQL_SELECT_APPENTRYREL_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

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

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<AppEntryRel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first app entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry rel
	 * @throws com.liferay.osb.NoSuchAppEntryRelException if a matching app entry rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryRelException, SystemException {
		AppEntryRel appEntryRel = fetchByUuid_First(uuid, orderByComparator);

		if (appEntryRel != null) {
			return appEntryRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryRelException(msg.toString());
	}

	/**
	 * Returns the first app entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry rel, or <code>null</code> if a matching app entry rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppEntryRel> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry rel
	 * @throws com.liferay.osb.NoSuchAppEntryRelException if a matching app entry rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryRelException, SystemException {
		AppEntryRel appEntryRel = fetchByUuid_Last(uuid, orderByComparator);

		if (appEntryRel != null) {
			return appEntryRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryRelException(msg.toString());
	}

	/**
	 * Returns the last app entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry rel, or <code>null</code> if a matching app entry rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<AppEntryRel> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app entry rels before and after the current app entry rel in the ordered set where uuid = &#63;.
	 *
	 * @param appEntryRelId the primary key of the current app entry rel
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app entry rel
	 * @throws com.liferay.osb.NoSuchAppEntryRelException if a app entry rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel[] findByUuid_PrevAndNext(long appEntryRelId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchAppEntryRelException, SystemException {
		AppEntryRel appEntryRel = findByPrimaryKey(appEntryRelId);

		Session session = null;

		try {
			session = openSession();

			AppEntryRel[] array = new AppEntryRelImpl[3];

			array[0] = getByUuid_PrevAndNext(session, appEntryRel, uuid,
					orderByComparator, true);

			array[1] = appEntryRel;

			array[2] = getByUuid_PrevAndNext(session, appEntryRel, uuid,
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

	protected AppEntryRel getByUuid_PrevAndNext(Session session,
		AppEntryRel appEntryRel, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPENTRYREL_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appEntryRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppEntryRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the app entry rels where appEntryId1 = &#63; and type = &#63;.
	 *
	 * @param appEntryId1 the app entry id1
	 * @param type the type
	 * @return the matching app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntryRel> findByAEI1_T(long appEntryId1, int type)
		throws SystemException {
		return findByAEI1_T(appEntryId1, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entry rels where appEntryId1 = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appEntryId1 the app entry id1
	 * @param type the type
	 * @param start the lower bound of the range of app entry rels
	 * @param end the upper bound of the range of app entry rels (not inclusive)
	 * @return the range of matching app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntryRel> findByAEI1_T(long appEntryId1, int type,
		int start, int end) throws SystemException {
		return findByAEI1_T(appEntryId1, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entry rels where appEntryId1 = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appEntryId1 the app entry id1
	 * @param type the type
	 * @param start the lower bound of the range of app entry rels
	 * @param end the upper bound of the range of app entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntryRel> findByAEI1_T(long appEntryId1, int type,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AEI1_T;
			finderArgs = new Object[] { appEntryId1, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AEI1_T;
			finderArgs = new Object[] {
					appEntryId1, type,
					
					start, end, orderByComparator
				};
		}

		List<AppEntryRel> list = (List<AppEntryRel>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AppEntryRel appEntryRel : list) {
				if ((appEntryId1 != appEntryRel.getAppEntryId1()) ||
						(type != appEntryRel.getType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_APPENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_AEI1_T_APPENTRYID1_2);

			query.append(_FINDER_COLUMN_AEI1_T_TYPE_2);

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

				qPos.add(appEntryId1);

				qPos.add(type);

				list = (List<AppEntryRel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first app entry rel in the ordered set where appEntryId1 = &#63; and type = &#63;.
	 *
	 * @param appEntryId1 the app entry id1
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry rel
	 * @throws com.liferay.osb.NoSuchAppEntryRelException if a matching app entry rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel findByAEI1_T_First(long appEntryId1, int type,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryRelException, SystemException {
		AppEntryRel appEntryRel = fetchByAEI1_T_First(appEntryId1, type,
				orderByComparator);

		if (appEntryRel != null) {
			return appEntryRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appEntryId1=");
		msg.append(appEntryId1);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryRelException(msg.toString());
	}

	/**
	 * Returns the first app entry rel in the ordered set where appEntryId1 = &#63; and type = &#63;.
	 *
	 * @param appEntryId1 the app entry id1
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching app entry rel, or <code>null</code> if a matching app entry rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel fetchByAEI1_T_First(long appEntryId1, int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<AppEntryRel> list = findByAEI1_T(appEntryId1, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last app entry rel in the ordered set where appEntryId1 = &#63; and type = &#63;.
	 *
	 * @param appEntryId1 the app entry id1
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry rel
	 * @throws com.liferay.osb.NoSuchAppEntryRelException if a matching app entry rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel findByAEI1_T_Last(long appEntryId1, int type,
		OrderByComparator orderByComparator)
		throws NoSuchAppEntryRelException, SystemException {
		AppEntryRel appEntryRel = fetchByAEI1_T_Last(appEntryId1, type,
				orderByComparator);

		if (appEntryRel != null) {
			return appEntryRel;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appEntryId1=");
		msg.append(appEntryId1);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAppEntryRelException(msg.toString());
	}

	/**
	 * Returns the last app entry rel in the ordered set where appEntryId1 = &#63; and type = &#63;.
	 *
	 * @param appEntryId1 the app entry id1
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching app entry rel, or <code>null</code> if a matching app entry rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel fetchByAEI1_T_Last(long appEntryId1, int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAEI1_T(appEntryId1, type);

		List<AppEntryRel> list = findByAEI1_T(appEntryId1, type, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the app entry rels before and after the current app entry rel in the ordered set where appEntryId1 = &#63; and type = &#63;.
	 *
	 * @param appEntryRelId the primary key of the current app entry rel
	 * @param appEntryId1 the app entry id1
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next app entry rel
	 * @throws com.liferay.osb.NoSuchAppEntryRelException if a app entry rel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel[] findByAEI1_T_PrevAndNext(long appEntryRelId,
		long appEntryId1, int type, OrderByComparator orderByComparator)
		throws NoSuchAppEntryRelException, SystemException {
		AppEntryRel appEntryRel = findByPrimaryKey(appEntryRelId);

		Session session = null;

		try {
			session = openSession();

			AppEntryRel[] array = new AppEntryRelImpl[3];

			array[0] = getByAEI1_T_PrevAndNext(session, appEntryRel,
					appEntryId1, type, orderByComparator, true);

			array[1] = appEntryRel;

			array[2] = getByAEI1_T_PrevAndNext(session, appEntryRel,
					appEntryId1, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AppEntryRel getByAEI1_T_PrevAndNext(Session session,
		AppEntryRel appEntryRel, long appEntryId1, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPENTRYREL_WHERE);

		query.append(_FINDER_COLUMN_AEI1_T_APPENTRYID1_2);

		query.append(_FINDER_COLUMN_AEI1_T_TYPE_2);

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

		qPos.add(appEntryId1);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(appEntryRel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AppEntryRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the app entry rel where appEntryId1 = &#63; and appEntryId2 = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchAppEntryRelException} if it could not be found.
	 *
	 * @param appEntryId1 the app entry id1
	 * @param appEntryId2 the app entry id2
	 * @param type the type
	 * @return the matching app entry rel
	 * @throws com.liferay.osb.NoSuchAppEntryRelException if a matching app entry rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel findByAEI1_AEI2_T(long appEntryId1, long appEntryId2,
		int type) throws NoSuchAppEntryRelException, SystemException {
		AppEntryRel appEntryRel = fetchByAEI1_AEI2_T(appEntryId1, appEntryId2,
				type);

		if (appEntryRel == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("appEntryId1=");
			msg.append(appEntryId1);

			msg.append(", appEntryId2=");
			msg.append(appEntryId2);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAppEntryRelException(msg.toString());
		}

		return appEntryRel;
	}

	/**
	 * Returns the app entry rel where appEntryId1 = &#63; and appEntryId2 = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param appEntryId1 the app entry id1
	 * @param appEntryId2 the app entry id2
	 * @param type the type
	 * @return the matching app entry rel, or <code>null</code> if a matching app entry rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel fetchByAEI1_AEI2_T(long appEntryId1, long appEntryId2,
		int type) throws SystemException {
		return fetchByAEI1_AEI2_T(appEntryId1, appEntryId2, type, true);
	}

	/**
	 * Returns the app entry rel where appEntryId1 = &#63; and appEntryId2 = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param appEntryId1 the app entry id1
	 * @param appEntryId2 the app entry id2
	 * @param type the type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching app entry rel, or <code>null</code> if a matching app entry rel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel fetchByAEI1_AEI2_T(long appEntryId1, long appEntryId2,
		int type, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { appEntryId1, appEntryId2, type };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_AEI1_AEI2_T,
					finderArgs, this);
		}

		if (result instanceof AppEntryRel) {
			AppEntryRel appEntryRel = (AppEntryRel)result;

			if ((appEntryId1 != appEntryRel.getAppEntryId1()) ||
					(appEntryId2 != appEntryRel.getAppEntryId2()) ||
					(type != appEntryRel.getType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_APPENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_AEI1_AEI2_T_APPENTRYID1_2);

			query.append(_FINDER_COLUMN_AEI1_AEI2_T_APPENTRYID2_2);

			query.append(_FINDER_COLUMN_AEI1_AEI2_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId1);

				qPos.add(appEntryId2);

				qPos.add(type);

				List<AppEntryRel> list = q.list();

				result = list;

				AppEntryRel appEntryRel = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI1_AEI2_T,
						finderArgs, list);
				}
				else {
					appEntryRel = list.get(0);

					cacheResult(appEntryRel);

					if ((appEntryRel.getAppEntryId1() != appEntryId1) ||
							(appEntryRel.getAppEntryId2() != appEntryId2) ||
							(appEntryRel.getType() != type)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_AEI1_AEI2_T,
							finderArgs, appEntryRel);
					}
				}

				return appEntryRel;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_AEI1_AEI2_T,
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
				return (AppEntryRel)result;
			}
		}
	}

	/**
	 * Returns all the app entry rels.
	 *
	 * @return the app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntryRel> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the app entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app entry rels
	 * @param end the upper bound of the range of app entry rels (not inclusive)
	 * @return the range of app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntryRel> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the app entry rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of app entry rels
	 * @param end the upper bound of the range of app entry rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public List<AppEntryRel> findAll(int start, int end,
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

		List<AppEntryRel> list = (List<AppEntryRel>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_APPENTRYREL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APPENTRYREL;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AppEntryRel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AppEntryRel>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the app entry rels where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (AppEntryRel appEntryRel : findByUuid(uuid)) {
			remove(appEntryRel);
		}
	}

	/**
	 * Removes all the app entry rels where appEntryId1 = &#63; and type = &#63; from the database.
	 *
	 * @param appEntryId1 the app entry id1
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAEI1_T(long appEntryId1, int type)
		throws SystemException {
		for (AppEntryRel appEntryRel : findByAEI1_T(appEntryId1, type)) {
			remove(appEntryRel);
		}
	}

	/**
	 * Removes the app entry rel where appEntryId1 = &#63; and appEntryId2 = &#63; and type = &#63; from the database.
	 *
	 * @param appEntryId1 the app entry id1
	 * @param appEntryId2 the app entry id2
	 * @param type the type
	 * @return the app entry rel that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public AppEntryRel removeByAEI1_AEI2_T(long appEntryId1, long appEntryId2,
		int type) throws NoSuchAppEntryRelException, SystemException {
		AppEntryRel appEntryRel = findByAEI1_AEI2_T(appEntryId1, appEntryId2,
				type);

		return remove(appEntryRel);
	}

	/**
	 * Removes all the app entry rels from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AppEntryRel appEntryRel : findAll()) {
			remove(appEntryRel);
		}
	}

	/**
	 * Returns the number of app entry rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPENTRYREL_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app entry rels where appEntryId1 = &#63; and type = &#63;.
	 *
	 * @param appEntryId1 the app entry id1
	 * @param type the type
	 * @return the number of matching app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI1_T(long appEntryId1, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] { appEntryId1, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI1_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_AEI1_T_APPENTRYID1_2);

			query.append(_FINDER_COLUMN_AEI1_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId1);

				qPos.add(type);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI1_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app entry rels where appEntryId1 = &#63; and appEntryId2 = &#63; and type = &#63;.
	 *
	 * @param appEntryId1 the app entry id1
	 * @param appEntryId2 the app entry id2
	 * @param type the type
	 * @return the number of matching app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAEI1_AEI2_T(long appEntryId1, long appEntryId2, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] { appEntryId1, appEntryId2, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_AEI1_AEI2_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_APPENTRYREL_WHERE);

			query.append(_FINDER_COLUMN_AEI1_AEI2_T_APPENTRYID1_2);

			query.append(_FINDER_COLUMN_AEI1_AEI2_T_APPENTRYID2_2);

			query.append(_FINDER_COLUMN_AEI1_AEI2_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appEntryId1);

				qPos.add(appEntryId2);

				qPos.add(type);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_AEI1_AEI2_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of app entry rels.
	 *
	 * @return the number of app entry rels
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APPENTRYREL);

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
	 * Initializes the app entry rel persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.AppEntryRel")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AppEntryRel>> listenersList = new ArrayList<ModelListener<AppEntryRel>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<AppEntryRel>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AppEntryRelImpl.class.getName());
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
	private static final String _SQL_SELECT_APPENTRYREL = "SELECT appEntryRel FROM AppEntryRel appEntryRel";
	private static final String _SQL_SELECT_APPENTRYREL_WHERE = "SELECT appEntryRel FROM AppEntryRel appEntryRel WHERE ";
	private static final String _SQL_COUNT_APPENTRYREL = "SELECT COUNT(appEntryRel) FROM AppEntryRel appEntryRel";
	private static final String _SQL_COUNT_APPENTRYREL_WHERE = "SELECT COUNT(appEntryRel) FROM AppEntryRel appEntryRel WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "appEntryRel.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "appEntryRel.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(appEntryRel.uuid IS NULL OR appEntryRel.uuid = ?)";
	private static final String _FINDER_COLUMN_AEI1_T_APPENTRYID1_2 = "appEntryRel.appEntryId1 = ? AND ";
	private static final String _FINDER_COLUMN_AEI1_T_TYPE_2 = "appEntryRel.type = ?";
	private static final String _FINDER_COLUMN_AEI1_AEI2_T_APPENTRYID1_2 = "appEntryRel.appEntryId1 = ? AND ";
	private static final String _FINDER_COLUMN_AEI1_AEI2_T_APPENTRYID2_2 = "appEntryRel.appEntryId2 = ? AND ";
	private static final String _FINDER_COLUMN_AEI1_AEI2_T_TYPE_2 = "appEntryRel.type = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "appEntryRel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AppEntryRel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AppEntryRel exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AppEntryRelPersistenceImpl.class);
	private static AppEntryRel _nullAppEntryRel = new AppEntryRelImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AppEntryRel> toCacheModel() {
				return _nullAppEntryRelCacheModel;
			}
		};

	private static CacheModel<AppEntryRel> _nullAppEntryRelCacheModel = new CacheModel<AppEntryRel>() {
			public AppEntryRel toEntityModel() {
				return _nullAppEntryRel;
			}
		};
}