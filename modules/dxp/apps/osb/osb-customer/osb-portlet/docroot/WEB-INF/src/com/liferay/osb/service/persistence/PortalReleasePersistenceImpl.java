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

import com.liferay.osb.NoSuchPortalReleaseException;
import com.liferay.osb.model.PortalRelease;
import com.liferay.osb.model.impl.PortalReleaseImpl;
import com.liferay.osb.model.impl.PortalReleaseModelImpl;

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
 * The persistence implementation for the portal release service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortalReleasePersistence
 * @see PortalReleaseUtil
 * @generated
 */
public class PortalReleasePersistenceImpl extends BasePersistenceImpl<PortalRelease>
	implements PortalReleasePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PortalReleaseUtil} to access the portal release persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PortalReleaseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_BUILDNUMBER = new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED,
			PortalReleaseImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByBuildNumber", new String[] { Integer.class.getName() },
			PortalReleaseModelImpl.BUILDNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BUILDNUMBER = new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBuildNumber",
			new String[] { Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EE = new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED,
			PortalReleaseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEE",
			new String[] {
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EE = new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED,
			PortalReleaseImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByEE", new String[] { Boolean.class.getName() },
			PortalReleaseModelImpl.EE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EE = new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEE",
			new String[] { Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETPLACESUPPORT =
		new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED,
			PortalReleaseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByMarketplaceSupport",
			new String[] {
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACESUPPORT =
		new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED,
			PortalReleaseImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByMarketplaceSupport",
			new String[] { Boolean.class.getName() },
			PortalReleaseModelImpl.MARKETPLACESUPPORT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MARKETPLACESUPPORT = new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByMarketplaceSupport",
			new String[] { Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OSGISUPPORT =
		new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED,
			PortalReleaseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByOSGiSupport",
			new String[] {
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OSGISUPPORT =
		new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED,
			PortalReleaseImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByOSGiSupport", new String[] { Boolean.class.getName() },
			PortalReleaseModelImpl.OSGISUPPORT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OSGISUPPORT = new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOSGiSupport",
			new String[] { Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PACLSUPPORT =
		new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED,
			PortalReleaseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPACLSupport",
			new String[] {
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PACLSUPPORT =
		new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED,
			PortalReleaseImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPACLSupport", new String[] { Boolean.class.getName() },
			PortalReleaseModelImpl.PACLSUPPORT_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PACLSUPPORT = new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPACLSupport",
			new String[] { Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED,
			PortalReleaseImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED,
			PortalReleaseImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the portal release in the entity cache if it is enabled.
	 *
	 * @param portalRelease the portal release
	 */
	public void cacheResult(PortalRelease portalRelease) {
		EntityCacheUtil.putResult(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseImpl.class, portalRelease.getPrimaryKey(),
			portalRelease);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_BUILDNUMBER,
			new Object[] { Integer.valueOf(portalRelease.getBuildNumber()) },
			portalRelease);

		portalRelease.resetOriginalValues();
	}

	/**
	 * Caches the portal releases in the entity cache if it is enabled.
	 *
	 * @param portalReleases the portal releases
	 */
	public void cacheResult(List<PortalRelease> portalReleases) {
		for (PortalRelease portalRelease : portalReleases) {
			if (EntityCacheUtil.getResult(
						PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
						PortalReleaseImpl.class, portalRelease.getPrimaryKey()) == null) {
				cacheResult(portalRelease);
			}
			else {
				portalRelease.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all portal releases.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PortalReleaseImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PortalReleaseImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the portal release.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PortalRelease portalRelease) {
		EntityCacheUtil.removeResult(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseImpl.class, portalRelease.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(portalRelease);
	}

	@Override
	public void clearCache(List<PortalRelease> portalReleases) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PortalRelease portalRelease : portalReleases) {
			EntityCacheUtil.removeResult(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
				PortalReleaseImpl.class, portalRelease.getPrimaryKey());

			clearUniqueFindersCache(portalRelease);
		}
	}

	protected void cacheUniqueFindersCache(PortalRelease portalRelease) {
		if (portalRelease.isNew()) {
			Object[] args = new Object[] {
					Integer.valueOf(portalRelease.getBuildNumber())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_BUILDNUMBER, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_BUILDNUMBER, args,
				portalRelease);
		}
		else {
			PortalReleaseModelImpl portalReleaseModelImpl = (PortalReleaseModelImpl)portalRelease;

			if ((portalReleaseModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_BUILDNUMBER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(portalRelease.getBuildNumber())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_BUILDNUMBER,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_BUILDNUMBER,
					args, portalRelease);
			}
		}
	}

	protected void clearUniqueFindersCache(PortalRelease portalRelease) {
		PortalReleaseModelImpl portalReleaseModelImpl = (PortalReleaseModelImpl)portalRelease;

		Object[] args = new Object[] {
				Integer.valueOf(portalRelease.getBuildNumber())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BUILDNUMBER, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_BUILDNUMBER, args);

		if ((portalReleaseModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_BUILDNUMBER.getColumnBitmask()) != 0) {
			args = new Object[] {
					Integer.valueOf(portalReleaseModelImpl.getOriginalBuildNumber())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BUILDNUMBER, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_BUILDNUMBER, args);
		}
	}

	/**
	 * Creates a new portal release with the primary key. Does not add the portal release to the database.
	 *
	 * @param portalReleaseId the primary key for the new portal release
	 * @return the new portal release
	 */
	public PortalRelease create(long portalReleaseId) {
		PortalRelease portalRelease = new PortalReleaseImpl();

		portalRelease.setNew(true);
		portalRelease.setPrimaryKey(portalReleaseId);

		return portalRelease;
	}

	/**
	 * Removes the portal release with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param portalReleaseId the primary key of the portal release
	 * @return the portal release that was removed
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease remove(long portalReleaseId)
		throws NoSuchPortalReleaseException, SystemException {
		return remove(Long.valueOf(portalReleaseId));
	}

	/**
	 * Removes the portal release with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the portal release
	 * @return the portal release that was removed
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PortalRelease remove(Serializable primaryKey)
		throws NoSuchPortalReleaseException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PortalRelease portalRelease = (PortalRelease)session.get(PortalReleaseImpl.class,
					primaryKey);

			if (portalRelease == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPortalReleaseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(portalRelease);
		}
		catch (NoSuchPortalReleaseException nsee) {
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
	protected PortalRelease removeImpl(PortalRelease portalRelease)
		throws SystemException {
		portalRelease = toUnwrappedModel(portalRelease);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, portalRelease);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(portalRelease);

		return portalRelease;
	}

	@Override
	public PortalRelease updateImpl(
		com.liferay.osb.model.PortalRelease portalRelease, boolean merge)
		throws SystemException {
		portalRelease = toUnwrappedModel(portalRelease);

		boolean isNew = portalRelease.isNew();

		PortalReleaseModelImpl portalReleaseModelImpl = (PortalReleaseModelImpl)portalRelease;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, portalRelease, merge);

			portalRelease.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PortalReleaseModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((portalReleaseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Boolean.valueOf(portalReleaseModelImpl.getOriginalEe())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EE,
					args);

				args = new Object[] {
						Boolean.valueOf(portalReleaseModelImpl.getEe())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EE,
					args);
			}

			if ((portalReleaseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACESUPPORT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Boolean.valueOf(portalReleaseModelImpl.getOriginalMarketplaceSupport())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MARKETPLACESUPPORT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACESUPPORT,
					args);

				args = new Object[] {
						Boolean.valueOf(portalReleaseModelImpl.getMarketplaceSupport())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MARKETPLACESUPPORT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACESUPPORT,
					args);
			}

			if ((portalReleaseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OSGISUPPORT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Boolean.valueOf(portalReleaseModelImpl.getOriginalOsgiSupport())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OSGISUPPORT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OSGISUPPORT,
					args);

				args = new Object[] {
						Boolean.valueOf(portalReleaseModelImpl.getOsgiSupport())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OSGISUPPORT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OSGISUPPORT,
					args);
			}

			if ((portalReleaseModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PACLSUPPORT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Boolean.valueOf(portalReleaseModelImpl.getOriginalPaclSupport())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PACLSUPPORT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PACLSUPPORT,
					args);

				args = new Object[] {
						Boolean.valueOf(portalReleaseModelImpl.getPaclSupport())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PACLSUPPORT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PACLSUPPORT,
					args);
			}
		}

		EntityCacheUtil.putResult(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
			PortalReleaseImpl.class, portalRelease.getPrimaryKey(),
			portalRelease);

		clearUniqueFindersCache(portalRelease);
		cacheUniqueFindersCache(portalRelease);

		return portalRelease;
	}

	protected PortalRelease toUnwrappedModel(PortalRelease portalRelease) {
		if (portalRelease instanceof PortalReleaseImpl) {
			return portalRelease;
		}

		PortalReleaseImpl portalReleaseImpl = new PortalReleaseImpl();

		portalReleaseImpl.setNew(portalRelease.isNew());
		portalReleaseImpl.setPrimaryKey(portalRelease.getPrimaryKey());

		portalReleaseImpl.setPortalReleaseId(portalRelease.getPortalReleaseId());
		portalReleaseImpl.setVersionName(portalRelease.getVersionName());
		portalReleaseImpl.setBuildNumber(portalRelease.getBuildNumber());
		portalReleaseImpl.setFixPackName(portalRelease.getFixPackName());
		portalReleaseImpl.setEe(portalRelease.isEe());
		portalReleaseImpl.setMarketplaceSupport(portalRelease.isMarketplaceSupport());
		portalReleaseImpl.setOsgiSupport(portalRelease.isOsgiSupport());
		portalReleaseImpl.setPaclSupport(portalRelease.isPaclSupport());
		portalReleaseImpl.setHidden(portalRelease.isHidden());

		return portalReleaseImpl;
	}

	/**
	 * Returns the portal release with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the portal release
	 * @return the portal release
	 * @throws com.liferay.portal.NoSuchModelException if a portal release with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PortalRelease findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the portal release with the primary key or throws a {@link com.liferay.osb.NoSuchPortalReleaseException} if it could not be found.
	 *
	 * @param portalReleaseId the primary key of the portal release
	 * @return the portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease findByPrimaryKey(long portalReleaseId)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = fetchByPrimaryKey(portalReleaseId);

		if (portalRelease == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + portalReleaseId);
			}

			throw new NoSuchPortalReleaseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				portalReleaseId);
		}

		return portalRelease;
	}

	/**
	 * Returns the portal release with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the portal release
	 * @return the portal release, or <code>null</code> if a portal release with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PortalRelease fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the portal release with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param portalReleaseId the primary key of the portal release
	 * @return the portal release, or <code>null</code> if a portal release with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease fetchByPrimaryKey(long portalReleaseId)
		throws SystemException {
		PortalRelease portalRelease = (PortalRelease)EntityCacheUtil.getResult(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
				PortalReleaseImpl.class, portalReleaseId);

		if (portalRelease == _nullPortalRelease) {
			return null;
		}

		if (portalRelease == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				portalRelease = (PortalRelease)session.get(PortalReleaseImpl.class,
						Long.valueOf(portalReleaseId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (portalRelease != null) {
					cacheResult(portalRelease);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(PortalReleaseModelImpl.ENTITY_CACHE_ENABLED,
						PortalReleaseImpl.class, portalReleaseId,
						_nullPortalRelease);
				}

				closeSession(session);
			}
		}

		return portalRelease;
	}

	/**
	 * Returns the portal release where buildNumber = &#63; or throws a {@link com.liferay.osb.NoSuchPortalReleaseException} if it could not be found.
	 *
	 * @param buildNumber the build number
	 * @return the matching portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease findByBuildNumber(int buildNumber)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = fetchByBuildNumber(buildNumber);

		if (portalRelease == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("buildNumber=");
			msg.append(buildNumber);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPortalReleaseException(msg.toString());
		}

		return portalRelease;
	}

	/**
	 * Returns the portal release where buildNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param buildNumber the build number
	 * @return the matching portal release, or <code>null</code> if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease fetchByBuildNumber(int buildNumber)
		throws SystemException {
		return fetchByBuildNumber(buildNumber, true);
	}

	/**
	 * Returns the portal release where buildNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param buildNumber the build number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching portal release, or <code>null</code> if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease fetchByBuildNumber(int buildNumber,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { buildNumber };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_BUILDNUMBER,
					finderArgs, this);
		}

		if (result instanceof PortalRelease) {
			PortalRelease portalRelease = (PortalRelease)result;

			if ((buildNumber != portalRelease.getBuildNumber())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PORTALRELEASE_WHERE);

			query.append(_FINDER_COLUMN_BUILDNUMBER_BUILDNUMBER_2);

			query.append(PortalReleaseModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(buildNumber);

				List<PortalRelease> list = q.list();

				result = list;

				PortalRelease portalRelease = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_BUILDNUMBER,
						finderArgs, list);
				}
				else {
					portalRelease = list.get(0);

					cacheResult(portalRelease);

					if ((portalRelease.getBuildNumber() != buildNumber)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_BUILDNUMBER,
							finderArgs, portalRelease);
					}
				}

				return portalRelease;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_BUILDNUMBER,
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
				return (PortalRelease)result;
			}
		}
	}

	/**
	 * Returns all the portal releases where ee = &#63;.
	 *
	 * @param ee the ee
	 * @return the matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findByEE(boolean ee) throws SystemException {
		return findByEE(ee, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portal releases where ee = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ee the ee
	 * @param start the lower bound of the range of portal releases
	 * @param end the upper bound of the range of portal releases (not inclusive)
	 * @return the range of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findByEE(boolean ee, int start, int end)
		throws SystemException {
		return findByEE(ee, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portal releases where ee = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ee the ee
	 * @param start the lower bound of the range of portal releases
	 * @param end the upper bound of the range of portal releases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findByEE(boolean ee, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EE;
			finderArgs = new Object[] { ee };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EE;
			finderArgs = new Object[] { ee, start, end, orderByComparator };
		}

		List<PortalRelease> list = (List<PortalRelease>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PortalRelease portalRelease : list) {
				if ((ee != portalRelease.getEe())) {
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

			query.append(_SQL_SELECT_PORTALRELEASE_WHERE);

			query.append(_FINDER_COLUMN_EE_EE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PortalReleaseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ee);

				list = (List<PortalRelease>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first portal release in the ordered set where ee = &#63;.
	 *
	 * @param ee the ee
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease findByEE_First(boolean ee,
		OrderByComparator orderByComparator)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = fetchByEE_First(ee, orderByComparator);

		if (portalRelease != null) {
			return portalRelease;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ee=");
		msg.append(ee);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPortalReleaseException(msg.toString());
	}

	/**
	 * Returns the first portal release in the ordered set where ee = &#63;.
	 *
	 * @param ee the ee
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portal release, or <code>null</code> if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease fetchByEE_First(boolean ee,
		OrderByComparator orderByComparator) throws SystemException {
		List<PortalRelease> list = findByEE(ee, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portal release in the ordered set where ee = &#63;.
	 *
	 * @param ee the ee
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease findByEE_Last(boolean ee,
		OrderByComparator orderByComparator)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = fetchByEE_Last(ee, orderByComparator);

		if (portalRelease != null) {
			return portalRelease;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ee=");
		msg.append(ee);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPortalReleaseException(msg.toString());
	}

	/**
	 * Returns the last portal release in the ordered set where ee = &#63;.
	 *
	 * @param ee the ee
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portal release, or <code>null</code> if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease fetchByEE_Last(boolean ee,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByEE(ee);

		List<PortalRelease> list = findByEE(ee, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portal releases before and after the current portal release in the ordered set where ee = &#63;.
	 *
	 * @param portalReleaseId the primary key of the current portal release
	 * @param ee the ee
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease[] findByEE_PrevAndNext(long portalReleaseId,
		boolean ee, OrderByComparator orderByComparator)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = findByPrimaryKey(portalReleaseId);

		Session session = null;

		try {
			session = openSession();

			PortalRelease[] array = new PortalReleaseImpl[3];

			array[0] = getByEE_PrevAndNext(session, portalRelease, ee,
					orderByComparator, true);

			array[1] = portalRelease;

			array[2] = getByEE_PrevAndNext(session, portalRelease, ee,
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

	protected PortalRelease getByEE_PrevAndNext(Session session,
		PortalRelease portalRelease, boolean ee,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PORTALRELEASE_WHERE);

		query.append(_FINDER_COLUMN_EE_EE_2);

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
			query.append(PortalReleaseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ee);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(portalRelease);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PortalRelease> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the portal releases where marketplaceSupport = &#63;.
	 *
	 * @param marketplaceSupport the marketplace support
	 * @return the matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findByMarketplaceSupport(
		boolean marketplaceSupport) throws SystemException {
		return findByMarketplaceSupport(marketplaceSupport, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portal releases where marketplaceSupport = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param marketplaceSupport the marketplace support
	 * @param start the lower bound of the range of portal releases
	 * @param end the upper bound of the range of portal releases (not inclusive)
	 * @return the range of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findByMarketplaceSupport(
		boolean marketplaceSupport, int start, int end)
		throws SystemException {
		return findByMarketplaceSupport(marketplaceSupport, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portal releases where marketplaceSupport = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param marketplaceSupport the marketplace support
	 * @param start the lower bound of the range of portal releases
	 * @param end the upper bound of the range of portal releases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findByMarketplaceSupport(
		boolean marketplaceSupport, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETPLACESUPPORT;
			finderArgs = new Object[] { marketplaceSupport };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETPLACESUPPORT;
			finderArgs = new Object[] {
					marketplaceSupport,
					
					start, end, orderByComparator
				};
		}

		List<PortalRelease> list = (List<PortalRelease>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PortalRelease portalRelease : list) {
				if ((marketplaceSupport != portalRelease.getMarketplaceSupport())) {
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

			query.append(_SQL_SELECT_PORTALRELEASE_WHERE);

			query.append(_FINDER_COLUMN_MARKETPLACESUPPORT_MARKETPLACESUPPORT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PortalReleaseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(marketplaceSupport);

				list = (List<PortalRelease>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first portal release in the ordered set where marketplaceSupport = &#63;.
	 *
	 * @param marketplaceSupport the marketplace support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease findByMarketplaceSupport_First(
		boolean marketplaceSupport, OrderByComparator orderByComparator)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = fetchByMarketplaceSupport_First(marketplaceSupport,
				orderByComparator);

		if (portalRelease != null) {
			return portalRelease;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("marketplaceSupport=");
		msg.append(marketplaceSupport);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPortalReleaseException(msg.toString());
	}

	/**
	 * Returns the first portal release in the ordered set where marketplaceSupport = &#63;.
	 *
	 * @param marketplaceSupport the marketplace support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portal release, or <code>null</code> if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease fetchByMarketplaceSupport_First(
		boolean marketplaceSupport, OrderByComparator orderByComparator)
		throws SystemException {
		List<PortalRelease> list = findByMarketplaceSupport(marketplaceSupport,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portal release in the ordered set where marketplaceSupport = &#63;.
	 *
	 * @param marketplaceSupport the marketplace support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease findByMarketplaceSupport_Last(
		boolean marketplaceSupport, OrderByComparator orderByComparator)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = fetchByMarketplaceSupport_Last(marketplaceSupport,
				orderByComparator);

		if (portalRelease != null) {
			return portalRelease;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("marketplaceSupport=");
		msg.append(marketplaceSupport);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPortalReleaseException(msg.toString());
	}

	/**
	 * Returns the last portal release in the ordered set where marketplaceSupport = &#63;.
	 *
	 * @param marketplaceSupport the marketplace support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portal release, or <code>null</code> if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease fetchByMarketplaceSupport_Last(
		boolean marketplaceSupport, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByMarketplaceSupport(marketplaceSupport);

		List<PortalRelease> list = findByMarketplaceSupport(marketplaceSupport,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portal releases before and after the current portal release in the ordered set where marketplaceSupport = &#63;.
	 *
	 * @param portalReleaseId the primary key of the current portal release
	 * @param marketplaceSupport the marketplace support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease[] findByMarketplaceSupport_PrevAndNext(
		long portalReleaseId, boolean marketplaceSupport,
		OrderByComparator orderByComparator)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = findByPrimaryKey(portalReleaseId);

		Session session = null;

		try {
			session = openSession();

			PortalRelease[] array = new PortalReleaseImpl[3];

			array[0] = getByMarketplaceSupport_PrevAndNext(session,
					portalRelease, marketplaceSupport, orderByComparator, true);

			array[1] = portalRelease;

			array[2] = getByMarketplaceSupport_PrevAndNext(session,
					portalRelease, marketplaceSupport, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PortalRelease getByMarketplaceSupport_PrevAndNext(
		Session session, PortalRelease portalRelease,
		boolean marketplaceSupport, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PORTALRELEASE_WHERE);

		query.append(_FINDER_COLUMN_MARKETPLACESUPPORT_MARKETPLACESUPPORT_2);

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
			query.append(PortalReleaseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(marketplaceSupport);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(portalRelease);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PortalRelease> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the portal releases where osgiSupport = &#63;.
	 *
	 * @param osgiSupport the osgi support
	 * @return the matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findByOSGiSupport(boolean osgiSupport)
		throws SystemException {
		return findByOSGiSupport(osgiSupport, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portal releases where osgiSupport = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param osgiSupport the osgi support
	 * @param start the lower bound of the range of portal releases
	 * @param end the upper bound of the range of portal releases (not inclusive)
	 * @return the range of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findByOSGiSupport(boolean osgiSupport,
		int start, int end) throws SystemException {
		return findByOSGiSupport(osgiSupport, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portal releases where osgiSupport = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param osgiSupport the osgi support
	 * @param start the lower bound of the range of portal releases
	 * @param end the upper bound of the range of portal releases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findByOSGiSupport(boolean osgiSupport,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OSGISUPPORT;
			finderArgs = new Object[] { osgiSupport };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OSGISUPPORT;
			finderArgs = new Object[] { osgiSupport, start, end, orderByComparator };
		}

		List<PortalRelease> list = (List<PortalRelease>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PortalRelease portalRelease : list) {
				if ((osgiSupport != portalRelease.getOsgiSupport())) {
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

			query.append(_SQL_SELECT_PORTALRELEASE_WHERE);

			query.append(_FINDER_COLUMN_OSGISUPPORT_OSGISUPPORT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PortalReleaseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(osgiSupport);

				list = (List<PortalRelease>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first portal release in the ordered set where osgiSupport = &#63;.
	 *
	 * @param osgiSupport the osgi support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease findByOSGiSupport_First(boolean osgiSupport,
		OrderByComparator orderByComparator)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = fetchByOSGiSupport_First(osgiSupport,
				orderByComparator);

		if (portalRelease != null) {
			return portalRelease;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("osgiSupport=");
		msg.append(osgiSupport);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPortalReleaseException(msg.toString());
	}

	/**
	 * Returns the first portal release in the ordered set where osgiSupport = &#63;.
	 *
	 * @param osgiSupport the osgi support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portal release, or <code>null</code> if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease fetchByOSGiSupport_First(boolean osgiSupport,
		OrderByComparator orderByComparator) throws SystemException {
		List<PortalRelease> list = findByOSGiSupport(osgiSupport, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portal release in the ordered set where osgiSupport = &#63;.
	 *
	 * @param osgiSupport the osgi support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease findByOSGiSupport_Last(boolean osgiSupport,
		OrderByComparator orderByComparator)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = fetchByOSGiSupport_Last(osgiSupport,
				orderByComparator);

		if (portalRelease != null) {
			return portalRelease;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("osgiSupport=");
		msg.append(osgiSupport);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPortalReleaseException(msg.toString());
	}

	/**
	 * Returns the last portal release in the ordered set where osgiSupport = &#63;.
	 *
	 * @param osgiSupport the osgi support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portal release, or <code>null</code> if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease fetchByOSGiSupport_Last(boolean osgiSupport,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOSGiSupport(osgiSupport);

		List<PortalRelease> list = findByOSGiSupport(osgiSupport, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portal releases before and after the current portal release in the ordered set where osgiSupport = &#63;.
	 *
	 * @param portalReleaseId the primary key of the current portal release
	 * @param osgiSupport the osgi support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease[] findByOSGiSupport_PrevAndNext(long portalReleaseId,
		boolean osgiSupport, OrderByComparator orderByComparator)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = findByPrimaryKey(portalReleaseId);

		Session session = null;

		try {
			session = openSession();

			PortalRelease[] array = new PortalReleaseImpl[3];

			array[0] = getByOSGiSupport_PrevAndNext(session, portalRelease,
					osgiSupport, orderByComparator, true);

			array[1] = portalRelease;

			array[2] = getByOSGiSupport_PrevAndNext(session, portalRelease,
					osgiSupport, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PortalRelease getByOSGiSupport_PrevAndNext(Session session,
		PortalRelease portalRelease, boolean osgiSupport,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PORTALRELEASE_WHERE);

		query.append(_FINDER_COLUMN_OSGISUPPORT_OSGISUPPORT_2);

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
			query.append(PortalReleaseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(osgiSupport);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(portalRelease);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PortalRelease> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the portal releases where paclSupport = &#63;.
	 *
	 * @param paclSupport the pacl support
	 * @return the matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findByPACLSupport(boolean paclSupport)
		throws SystemException {
		return findByPACLSupport(paclSupport, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portal releases where paclSupport = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param paclSupport the pacl support
	 * @param start the lower bound of the range of portal releases
	 * @param end the upper bound of the range of portal releases (not inclusive)
	 * @return the range of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findByPACLSupport(boolean paclSupport,
		int start, int end) throws SystemException {
		return findByPACLSupport(paclSupport, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portal releases where paclSupport = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param paclSupport the pacl support
	 * @param start the lower bound of the range of portal releases
	 * @param end the upper bound of the range of portal releases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findByPACLSupport(boolean paclSupport,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PACLSUPPORT;
			finderArgs = new Object[] { paclSupport };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PACLSUPPORT;
			finderArgs = new Object[] { paclSupport, start, end, orderByComparator };
		}

		List<PortalRelease> list = (List<PortalRelease>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PortalRelease portalRelease : list) {
				if ((paclSupport != portalRelease.getPaclSupport())) {
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

			query.append(_SQL_SELECT_PORTALRELEASE_WHERE);

			query.append(_FINDER_COLUMN_PACLSUPPORT_PACLSUPPORT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PortalReleaseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(paclSupport);

				list = (List<PortalRelease>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first portal release in the ordered set where paclSupport = &#63;.
	 *
	 * @param paclSupport the pacl support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease findByPACLSupport_First(boolean paclSupport,
		OrderByComparator orderByComparator)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = fetchByPACLSupport_First(paclSupport,
				orderByComparator);

		if (portalRelease != null) {
			return portalRelease;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("paclSupport=");
		msg.append(paclSupport);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPortalReleaseException(msg.toString());
	}

	/**
	 * Returns the first portal release in the ordered set where paclSupport = &#63;.
	 *
	 * @param paclSupport the pacl support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portal release, or <code>null</code> if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease fetchByPACLSupport_First(boolean paclSupport,
		OrderByComparator orderByComparator) throws SystemException {
		List<PortalRelease> list = findByPACLSupport(paclSupport, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last portal release in the ordered set where paclSupport = &#63;.
	 *
	 * @param paclSupport the pacl support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease findByPACLSupport_Last(boolean paclSupport,
		OrderByComparator orderByComparator)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = fetchByPACLSupport_Last(paclSupport,
				orderByComparator);

		if (portalRelease != null) {
			return portalRelease;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("paclSupport=");
		msg.append(paclSupport);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPortalReleaseException(msg.toString());
	}

	/**
	 * Returns the last portal release in the ordered set where paclSupport = &#63;.
	 *
	 * @param paclSupport the pacl support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portal release, or <code>null</code> if a matching portal release could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease fetchByPACLSupport_Last(boolean paclSupport,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPACLSupport(paclSupport);

		List<PortalRelease> list = findByPACLSupport(paclSupport, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the portal releases before and after the current portal release in the ordered set where paclSupport = &#63;.
	 *
	 * @param portalReleaseId the primary key of the current portal release
	 * @param paclSupport the pacl support
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portal release
	 * @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease[] findByPACLSupport_PrevAndNext(long portalReleaseId,
		boolean paclSupport, OrderByComparator orderByComparator)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = findByPrimaryKey(portalReleaseId);

		Session session = null;

		try {
			session = openSession();

			PortalRelease[] array = new PortalReleaseImpl[3];

			array[0] = getByPACLSupport_PrevAndNext(session, portalRelease,
					paclSupport, orderByComparator, true);

			array[1] = portalRelease;

			array[2] = getByPACLSupport_PrevAndNext(session, portalRelease,
					paclSupport, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PortalRelease getByPACLSupport_PrevAndNext(Session session,
		PortalRelease portalRelease, boolean paclSupport,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PORTALRELEASE_WHERE);

		query.append(_FINDER_COLUMN_PACLSUPPORT_PACLSUPPORT_2);

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
			query.append(PortalReleaseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(paclSupport);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(portalRelease);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PortalRelease> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the portal releases.
	 *
	 * @return the portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portal releases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of portal releases
	 * @param end the upper bound of the range of portal releases (not inclusive)
	 * @return the range of portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the portal releases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of portal releases
	 * @param end the upper bound of the range of portal releases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortalRelease> findAll(int start, int end,
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

		List<PortalRelease> list = (List<PortalRelease>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PORTALRELEASE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PORTALRELEASE.concat(PortalReleaseModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<PortalRelease>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<PortalRelease>)QueryUtil.list(q, getDialect(),
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
	 * Removes the portal release where buildNumber = &#63; from the database.
	 *
	 * @param buildNumber the build number
	 * @return the portal release that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public PortalRelease removeByBuildNumber(int buildNumber)
		throws NoSuchPortalReleaseException, SystemException {
		PortalRelease portalRelease = findByBuildNumber(buildNumber);

		return remove(portalRelease);
	}

	/**
	 * Removes all the portal releases where ee = &#63; from the database.
	 *
	 * @param ee the ee
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEE(boolean ee) throws SystemException {
		for (PortalRelease portalRelease : findByEE(ee)) {
			remove(portalRelease);
		}
	}

	/**
	 * Removes all the portal releases where marketplaceSupport = &#63; from the database.
	 *
	 * @param marketplaceSupport the marketplace support
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMarketplaceSupport(boolean marketplaceSupport)
		throws SystemException {
		for (PortalRelease portalRelease : findByMarketplaceSupport(
				marketplaceSupport)) {
			remove(portalRelease);
		}
	}

	/**
	 * Removes all the portal releases where osgiSupport = &#63; from the database.
	 *
	 * @param osgiSupport the osgi support
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOSGiSupport(boolean osgiSupport)
		throws SystemException {
		for (PortalRelease portalRelease : findByOSGiSupport(osgiSupport)) {
			remove(portalRelease);
		}
	}

	/**
	 * Removes all the portal releases where paclSupport = &#63; from the database.
	 *
	 * @param paclSupport the pacl support
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPACLSupport(boolean paclSupport)
		throws SystemException {
		for (PortalRelease portalRelease : findByPACLSupport(paclSupport)) {
			remove(portalRelease);
		}
	}

	/**
	 * Removes all the portal releases from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (PortalRelease portalRelease : findAll()) {
			remove(portalRelease);
		}
	}

	/**
	 * Returns the number of portal releases where buildNumber = &#63;.
	 *
	 * @param buildNumber the build number
	 * @return the number of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public int countByBuildNumber(int buildNumber) throws SystemException {
		Object[] finderArgs = new Object[] { buildNumber };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_BUILDNUMBER,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PORTALRELEASE_WHERE);

			query.append(_FINDER_COLUMN_BUILDNUMBER_BUILDNUMBER_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(buildNumber);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_BUILDNUMBER,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of portal releases where ee = &#63;.
	 *
	 * @param ee the ee
	 * @return the number of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEE(boolean ee) throws SystemException {
		Object[] finderArgs = new Object[] { ee };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PORTALRELEASE_WHERE);

			query.append(_FINDER_COLUMN_EE_EE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ee);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EE, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of portal releases where marketplaceSupport = &#63;.
	 *
	 * @param marketplaceSupport the marketplace support
	 * @return the number of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMarketplaceSupport(boolean marketplaceSupport)
		throws SystemException {
		Object[] finderArgs = new Object[] { marketplaceSupport };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MARKETPLACESUPPORT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PORTALRELEASE_WHERE);

			query.append(_FINDER_COLUMN_MARKETPLACESUPPORT_MARKETPLACESUPPORT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(marketplaceSupport);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MARKETPLACESUPPORT,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of portal releases where osgiSupport = &#63;.
	 *
	 * @param osgiSupport the osgi support
	 * @return the number of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOSGiSupport(boolean osgiSupport)
		throws SystemException {
		Object[] finderArgs = new Object[] { osgiSupport };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_OSGISUPPORT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PORTALRELEASE_WHERE);

			query.append(_FINDER_COLUMN_OSGISUPPORT_OSGISUPPORT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(osgiSupport);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_OSGISUPPORT,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of portal releases where paclSupport = &#63;.
	 *
	 * @param paclSupport the pacl support
	 * @return the number of matching portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPACLSupport(boolean paclSupport)
		throws SystemException {
		Object[] finderArgs = new Object[] { paclSupport };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PACLSUPPORT,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PORTALRELEASE_WHERE);

			query.append(_FINDER_COLUMN_PACLSUPPORT_PACLSUPPORT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(paclSupport);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PACLSUPPORT,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of portal releases.
	 *
	 * @return the number of portal releases
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PORTALRELEASE);

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
	 * Initializes the portal release persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.PortalRelease")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PortalRelease>> listenersList = new ArrayList<ModelListener<PortalRelease>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<PortalRelease>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(PortalReleaseImpl.class.getName());
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
	private static final String _SQL_SELECT_PORTALRELEASE = "SELECT portalRelease FROM PortalRelease portalRelease";
	private static final String _SQL_SELECT_PORTALRELEASE_WHERE = "SELECT portalRelease FROM PortalRelease portalRelease WHERE ";
	private static final String _SQL_COUNT_PORTALRELEASE = "SELECT COUNT(portalRelease) FROM PortalRelease portalRelease";
	private static final String _SQL_COUNT_PORTALRELEASE_WHERE = "SELECT COUNT(portalRelease) FROM PortalRelease portalRelease WHERE ";
	private static final String _FINDER_COLUMN_BUILDNUMBER_BUILDNUMBER_2 = "portalRelease.buildNumber = ?";
	private static final String _FINDER_COLUMN_EE_EE_2 = "portalRelease.ee = ?";
	private static final String _FINDER_COLUMN_MARKETPLACESUPPORT_MARKETPLACESUPPORT_2 =
		"portalRelease.marketplaceSupport = ?";
	private static final String _FINDER_COLUMN_OSGISUPPORT_OSGISUPPORT_2 = "portalRelease.osgiSupport = ?";
	private static final String _FINDER_COLUMN_PACLSUPPORT_PACLSUPPORT_2 = "portalRelease.paclSupport = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "portalRelease.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PortalRelease exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PortalRelease exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PortalReleasePersistenceImpl.class);
	private static PortalRelease _nullPortalRelease = new PortalReleaseImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PortalRelease> toCacheModel() {
				return _nullPortalReleaseCacheModel;
			}
		};

	private static CacheModel<PortalRelease> _nullPortalReleaseCacheModel = new CacheModel<PortalRelease>() {
			public PortalRelease toEntityModel() {
				return _nullPortalRelease;
			}
		};
}