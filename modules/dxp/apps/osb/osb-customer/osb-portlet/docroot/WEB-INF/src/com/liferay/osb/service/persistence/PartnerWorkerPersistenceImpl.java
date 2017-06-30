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

import com.liferay.osb.NoSuchPartnerWorkerException;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.impl.PartnerWorkerImpl;
import com.liferay.osb.model.impl.PartnerWorkerModelImpl;

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
import com.liferay.portal.kernel.util.ArrayUtil;
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
import com.liferay.portal.service.persistence.OrganizationPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the partner worker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorkerPersistence
 * @see PartnerWorkerUtil
 * @generated
 */
public class PartnerWorkerPersistenceImpl extends BasePersistenceImpl<PartnerWorker>
	implements PartnerWorkerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PartnerWorkerUtil} to access the partner worker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PartnerWorkerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUserId", new String[] { Long.class.getName() },
			PartnerWorkerModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARTNERENTRYID =
		new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPartnerEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID =
		new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPartnerEntryId", new String[] { Long.class.getName() },
			PartnerWorkerModelImpl.PARTNERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARTNERENTRYID = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPartnerEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_PEI = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByU_PEI",
			new String[] { Long.class.getName(), Long.class.getName() },
			PartnerWorkerModelImpl.USERID_COLUMN_BITMASK |
			PartnerWorkerModelImpl.PARTNERENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_PEI = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_PEI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_R",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() },
			PartnerWorkerModelImpl.USERID_COLUMN_BITMASK |
			PartnerWorkerModelImpl.ROLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_R",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPEI_R",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPEI_R",
			new String[] { Long.class.getName(), Integer.class.getName() },
			PartnerWorkerModelImpl.PARTNERENTRYID_COLUMN_BITMASK |
			PartnerWorkerModelImpl.ROLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PEI_R = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPEI_R",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_NOTN = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPEI_NotN",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_NOTN =
		new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByPEI_NotN",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED,
			PartnerWorkerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the partner worker in the entity cache if it is enabled.
	 *
	 * @param partnerWorker the partner worker
	 */
	public void cacheResult(PartnerWorker partnerWorker) {
		EntityCacheUtil.putResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerImpl.class, partnerWorker.getPrimaryKey(),
			partnerWorker);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_PEI,
			new Object[] {
				Long.valueOf(partnerWorker.getUserId()),
				Long.valueOf(partnerWorker.getPartnerEntryId())
			}, partnerWorker);

		partnerWorker.resetOriginalValues();
	}

	/**
	 * Caches the partner workers in the entity cache if it is enabled.
	 *
	 * @param partnerWorkers the partner workers
	 */
	public void cacheResult(List<PartnerWorker> partnerWorkers) {
		for (PartnerWorker partnerWorker : partnerWorkers) {
			if (EntityCacheUtil.getResult(
						PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
						PartnerWorkerImpl.class, partnerWorker.getPrimaryKey()) == null) {
				cacheResult(partnerWorker);
			}
			else {
				partnerWorker.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all partner workers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PartnerWorkerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PartnerWorkerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the partner worker.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PartnerWorker partnerWorker) {
		EntityCacheUtil.removeResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerImpl.class, partnerWorker.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(partnerWorker);
	}

	@Override
	public void clearCache(List<PartnerWorker> partnerWorkers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PartnerWorker partnerWorker : partnerWorkers) {
			EntityCacheUtil.removeResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
				PartnerWorkerImpl.class, partnerWorker.getPrimaryKey());

			clearUniqueFindersCache(partnerWorker);
		}
	}

	protected void cacheUniqueFindersCache(PartnerWorker partnerWorker) {
		if (partnerWorker.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(partnerWorker.getUserId()),
					Long.valueOf(partnerWorker.getPartnerEntryId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_PEI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_PEI, args,
				partnerWorker);
		}
		else {
			PartnerWorkerModelImpl partnerWorkerModelImpl = (PartnerWorkerModelImpl)partnerWorker;

			if ((partnerWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_PEI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(partnerWorker.getUserId()),
						Long.valueOf(partnerWorker.getPartnerEntryId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_PEI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_PEI, args,
					partnerWorker);
			}
		}
	}

	protected void clearUniqueFindersCache(PartnerWorker partnerWorker) {
		PartnerWorkerModelImpl partnerWorkerModelImpl = (PartnerWorkerModelImpl)partnerWorker;

		Object[] args = new Object[] {
				Long.valueOf(partnerWorker.getUserId()),
				Long.valueOf(partnerWorker.getPartnerEntryId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_PEI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_PEI, args);

		if ((partnerWorkerModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_PEI.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(partnerWorkerModelImpl.getOriginalUserId()),
					Long.valueOf(partnerWorkerModelImpl.getOriginalPartnerEntryId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_PEI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_PEI, args);
		}
	}

	/**
	 * Creates a new partner worker with the primary key. Does not add the partner worker to the database.
	 *
	 * @param partnerWorkerId the primary key for the new partner worker
	 * @return the new partner worker
	 */
	public PartnerWorker create(long partnerWorkerId) {
		PartnerWorker partnerWorker = new PartnerWorkerImpl();

		partnerWorker.setNew(true);
		partnerWorker.setPrimaryKey(partnerWorkerId);

		return partnerWorker;
	}

	/**
	 * Removes the partner worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param partnerWorkerId the primary key of the partner worker
	 * @return the partner worker that was removed
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker remove(long partnerWorkerId)
		throws NoSuchPartnerWorkerException, SystemException {
		return remove(Long.valueOf(partnerWorkerId));
	}

	/**
	 * Removes the partner worker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the partner worker
	 * @return the partner worker that was removed
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PartnerWorker remove(Serializable primaryKey)
		throws NoSuchPartnerWorkerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PartnerWorker partnerWorker = (PartnerWorker)session.get(PartnerWorkerImpl.class,
					primaryKey);

			if (partnerWorker == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPartnerWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(partnerWorker);
		}
		catch (NoSuchPartnerWorkerException nsee) {
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
	protected PartnerWorker removeImpl(PartnerWorker partnerWorker)
		throws SystemException {
		partnerWorker = toUnwrappedModel(partnerWorker);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, partnerWorker);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(partnerWorker);

		return partnerWorker;
	}

	@Override
	public PartnerWorker updateImpl(
		com.liferay.osb.model.PartnerWorker partnerWorker, boolean merge)
		throws SystemException {
		partnerWorker = toUnwrappedModel(partnerWorker);

		boolean isNew = partnerWorker.isNew();

		PartnerWorkerModelImpl partnerWorkerModelImpl = (PartnerWorkerModelImpl)partnerWorker;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, partnerWorker, merge);

			partnerWorker.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PartnerWorkerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((partnerWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(partnerWorkerModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(partnerWorkerModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((partnerWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(partnerWorkerModelImpl.getOriginalPartnerEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(partnerWorkerModelImpl.getPartnerEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID,
					args);
			}

			if ((partnerWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(partnerWorkerModelImpl.getOriginalUserId()),
						Integer.valueOf(partnerWorkerModelImpl.getOriginalRole())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);

				args = new Object[] {
						Long.valueOf(partnerWorkerModelImpl.getUserId()),
						Integer.valueOf(partnerWorkerModelImpl.getRole())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R,
					args);
			}

			if ((partnerWorkerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(partnerWorkerModelImpl.getOriginalPartnerEntryId()),
						Integer.valueOf(partnerWorkerModelImpl.getOriginalRole())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PEI_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_R,
					args);

				args = new Object[] {
						Long.valueOf(partnerWorkerModelImpl.getPartnerEntryId()),
						Integer.valueOf(partnerWorkerModelImpl.getRole())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PEI_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_R,
					args);
			}
		}

		EntityCacheUtil.putResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
			PartnerWorkerImpl.class, partnerWorker.getPrimaryKey(),
			partnerWorker);

		clearUniqueFindersCache(partnerWorker);
		cacheUniqueFindersCache(partnerWorker);

		return partnerWorker;
	}

	protected PartnerWorker toUnwrappedModel(PartnerWorker partnerWorker) {
		if (partnerWorker instanceof PartnerWorkerImpl) {
			return partnerWorker;
		}

		PartnerWorkerImpl partnerWorkerImpl = new PartnerWorkerImpl();

		partnerWorkerImpl.setNew(partnerWorker.isNew());
		partnerWorkerImpl.setPrimaryKey(partnerWorker.getPrimaryKey());

		partnerWorkerImpl.setPartnerWorkerId(partnerWorker.getPartnerWorkerId());
		partnerWorkerImpl.setUserId(partnerWorker.getUserId());
		partnerWorkerImpl.setPartnerEntryId(partnerWorker.getPartnerEntryId());
		partnerWorkerImpl.setRole(partnerWorker.getRole());
		partnerWorkerImpl.setNotifications(partnerWorker.getNotifications());

		return partnerWorkerImpl;
	}

	/**
	 * Returns the partner worker with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the partner worker
	 * @return the partner worker
	 * @throws com.liferay.portal.NoSuchModelException if a partner worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PartnerWorker findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the partner worker with the primary key or throws a {@link com.liferay.osb.NoSuchPartnerWorkerException} if it could not be found.
	 *
	 * @param partnerWorkerId the primary key of the partner worker
	 * @return the partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker findByPrimaryKey(long partnerWorkerId)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = fetchByPrimaryKey(partnerWorkerId);

		if (partnerWorker == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + partnerWorkerId);
			}

			throw new NoSuchPartnerWorkerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				partnerWorkerId);
		}

		return partnerWorker;
	}

	/**
	 * Returns the partner worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the partner worker
	 * @return the partner worker, or <code>null</code> if a partner worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PartnerWorker fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the partner worker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param partnerWorkerId the primary key of the partner worker
	 * @return the partner worker, or <code>null</code> if a partner worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByPrimaryKey(long partnerWorkerId)
		throws SystemException {
		PartnerWorker partnerWorker = (PartnerWorker)EntityCacheUtil.getResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
				PartnerWorkerImpl.class, partnerWorkerId);

		if (partnerWorker == _nullPartnerWorker) {
			return null;
		}

		if (partnerWorker == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				partnerWorker = (PartnerWorker)session.get(PartnerWorkerImpl.class,
						Long.valueOf(partnerWorkerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (partnerWorker != null) {
					cacheResult(partnerWorker);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(PartnerWorkerModelImpl.ENTITY_CACHE_ENABLED,
						PartnerWorkerImpl.class, partnerWorkerId,
						_nullPartnerWorker);
				}

				closeSession(session);
			}
		}

		return partnerWorker;
	}

	/**
	 * Returns all the partner workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<PartnerWorker> list = (List<PartnerWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PartnerWorker partnerWorker : list) {
				if ((userId != partnerWorker.getUserId())) {
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

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

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

				list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first partner worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = fetchByUserId_First(userId,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the first partner worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PartnerWorker> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last partner worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = fetchByUserId_Last(userId,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the last partner worker in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<PartnerWorker> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the partner workers before and after the current partner worker in the ordered set where userId = &#63;.
	 *
	 * @param partnerWorkerId the primary key of the current partner worker
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker[] findByUserId_PrevAndNext(long partnerWorkerId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = findByPrimaryKey(partnerWorkerId);

		Session session = null;

		try {
			session = openSession();

			PartnerWorker[] array = new PartnerWorkerImpl[3];

			array[0] = getByUserId_PrevAndNext(session, partnerWorker, userId,
					orderByComparator, true);

			array[1] = partnerWorker;

			array[2] = getByUserId_PrevAndNext(session, partnerWorker, userId,
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

	protected PartnerWorker getByUserId_PrevAndNext(Session session,
		PartnerWorker partnerWorker, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(partnerWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PartnerWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the partner workers where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @return the matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByPartnerEntryId(long partnerEntryId)
		throws SystemException {
		return findByPartnerEntryId(partnerEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner workers where partnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByPartnerEntryId(long partnerEntryId,
		int start, int end) throws SystemException {
		return findByPartnerEntryId(partnerEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers where partnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByPartnerEntryId(long partnerEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARTNERENTRYID;
			finderArgs = new Object[] { partnerEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARTNERENTRYID;
			finderArgs = new Object[] {
					partnerEntryId,
					
					start, end, orderByComparator
				};
		}

		List<PartnerWorker> list = (List<PartnerWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PartnerWorker partnerWorker : list) {
				if ((partnerEntryId != partnerWorker.getPartnerEntryId())) {
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

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2);

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

				qPos.add(partnerEntryId);

				list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first partner worker in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker findByPartnerEntryId_First(long partnerEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = fetchByPartnerEntryId_First(partnerEntryId,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the first partner worker in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByPartnerEntryId_First(long partnerEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PartnerWorker> list = findByPartnerEntryId(partnerEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last partner worker in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker findByPartnerEntryId_Last(long partnerEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = fetchByPartnerEntryId_Last(partnerEntryId,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the last partner worker in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByPartnerEntryId_Last(long partnerEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPartnerEntryId(partnerEntryId);

		List<PartnerWorker> list = findByPartnerEntryId(partnerEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the partner workers before and after the current partner worker in the ordered set where partnerEntryId = &#63;.
	 *
	 * @param partnerWorkerId the primary key of the current partner worker
	 * @param partnerEntryId the partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker[] findByPartnerEntryId_PrevAndNext(
		long partnerWorkerId, long partnerEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = findByPrimaryKey(partnerWorkerId);

		Session session = null;

		try {
			session = openSession();

			PartnerWorker[] array = new PartnerWorkerImpl[3];

			array[0] = getByPartnerEntryId_PrevAndNext(session, partnerWorker,
					partnerEntryId, orderByComparator, true);

			array[1] = partnerWorker;

			array[2] = getByPartnerEntryId_PrevAndNext(session, partnerWorker,
					partnerEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PartnerWorker getByPartnerEntryId_PrevAndNext(Session session,
		PartnerWorker partnerWorker, long partnerEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

		query.append(_FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2);

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

		qPos.add(partnerEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(partnerWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PartnerWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or throws a {@link com.liferay.osb.NoSuchPartnerWorkerException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param partnerEntryId the partner entry ID
	 * @return the matching partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker findByU_PEI(long userId, long partnerEntryId)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = fetchByU_PEI(userId, partnerEntryId);

		if (partnerWorker == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", partnerEntryId=");
			msg.append(partnerEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPartnerWorkerException(msg.toString());
		}

		return partnerWorker;
	}

	/**
	 * Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param partnerEntryId the partner entry ID
	 * @return the matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByU_PEI(long userId, long partnerEntryId)
		throws SystemException {
		return fetchByU_PEI(userId, partnerEntryId, true);
	}

	/**
	 * Returns the partner worker where userId = &#63; and partnerEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param partnerEntryId the partner entry ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByU_PEI(long userId, long partnerEntryId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, partnerEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_PEI,
					finderArgs, this);
		}

		if (result instanceof PartnerWorker) {
			PartnerWorker partnerWorker = (PartnerWorker)result;

			if ((userId != partnerWorker.getUserId()) ||
					(partnerEntryId != partnerWorker.getPartnerEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_PEI_USERID_2);

			query.append(_FINDER_COLUMN_U_PEI_PARTNERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(partnerEntryId);

				List<PartnerWorker> list = q.list();

				result = list;

				PartnerWorker partnerWorker = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_PEI,
						finderArgs, list);
				}
				else {
					partnerWorker = list.get(0);

					cacheResult(partnerWorker);

					if ((partnerWorker.getUserId() != userId) ||
							(partnerWorker.getPartnerEntryId() != partnerEntryId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_PEI,
							finderArgs, partnerWorker);
					}
				}

				return partnerWorker;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_PEI,
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
				return (PartnerWorker)result;
			}
		}
	}

	/**
	 * Returns all the partner workers where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @return the matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByU_R(long userId, int role)
		throws SystemException {
		return findByU_R(userId, role, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the partner workers where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByU_R(long userId, int role, int start,
		int end) throws SystemException {
		return findByU_R(userId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers where userId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByU_R(long userId, int role, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R;
			finderArgs = new Object[] { userId, role };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R;
			finderArgs = new Object[] {
					userId, role,
					
					start, end, orderByComparator
				};
		}

		List<PartnerWorker> list = (List<PartnerWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PartnerWorker partnerWorker : list) {
				if ((userId != partnerWorker.getUserId()) ||
						(role != partnerWorker.getRole())) {
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

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_R_USERID_2);

			query.append(_FINDER_COLUMN_U_R_ROLE_2);

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

				qPos.add(role);

				list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first partner worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker findByU_R_First(long userId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = fetchByU_R_First(userId, role,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the first partner worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByU_R_First(long userId, int role,
		OrderByComparator orderByComparator) throws SystemException {
		List<PartnerWorker> list = findByU_R(userId, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last partner worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker findByU_R_Last(long userId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = fetchByU_R_Last(userId, role,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the last partner worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByU_R_Last(long userId, int role,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_R(userId, role);

		List<PartnerWorker> list = findByU_R(userId, role, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the partner workers before and after the current partner worker in the ordered set where userId = &#63; and role = &#63;.
	 *
	 * @param partnerWorkerId the primary key of the current partner worker
	 * @param userId the user ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker[] findByU_R_PrevAndNext(long partnerWorkerId,
		long userId, int role, OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = findByPrimaryKey(partnerWorkerId);

		Session session = null;

		try {
			session = openSession();

			PartnerWorker[] array = new PartnerWorkerImpl[3];

			array[0] = getByU_R_PrevAndNext(session, partnerWorker, userId,
					role, orderByComparator, true);

			array[1] = partnerWorker;

			array[2] = getByU_R_PrevAndNext(session, partnerWorker, userId,
					role, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PartnerWorker getByU_R_PrevAndNext(Session session,
		PartnerWorker partnerWorker, long userId, int role,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

		query.append(_FINDER_COLUMN_U_R_USERID_2);

		query.append(_FINDER_COLUMN_U_R_ROLE_2);

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

		qPos.add(role);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(partnerWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PartnerWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the partner workers where userId = any &#63; and role = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userIds the user IDs
	 * @param roles the roles
	 * @return the matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByU_R(long[] userIds, int[] roles)
		throws SystemException {
		return findByU_R(userIds, roles, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the partner workers where userId = any &#63; and role = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userIds the user IDs
	 * @param roles the roles
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByU_R(long[] userIds, int[] roles,
		int start, int end) throws SystemException {
		return findByU_R(userIds, roles, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers where userId = any &#63; and role = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userIds the user IDs
	 * @param roles the roles
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByU_R(long[] userIds, int[] roles,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderArgs = new Object[] {
					StringUtil.merge(userIds), StringUtil.merge(roles)
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(userIds), StringUtil.merge(roles),
					
					start, end, orderByComparator
				};
		}

		List<PartnerWorker> list = (List<PartnerWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PartnerWorker partnerWorker : list) {
				if (!ArrayUtil.contains(userIds, partnerWorker.getUserId()) ||
						!ArrayUtil.contains(roles, partnerWorker.getRole())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

			boolean conjunctionable = false;

			if ((userIds == null) || (userIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < userIds.length; i++) {
					query.append(_FINDER_COLUMN_U_R_USERID_5);

					if ((i + 1) < userIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((roles == null) || (roles.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < roles.length; i++) {
					query.append(_FINDER_COLUMN_U_R_ROLE_5);

					if ((i + 1) < roles.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
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

				if (userIds != null) {
					qPos.add(userIds);
				}

				if (roles != null) {
					qPos.add(roles);
				}

				list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns all the partner workers where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @return the matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByPEI_R(long partnerEntryId, int role)
		throws SystemException {
		return findByPEI_R(partnerEntryId, role, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner workers where partnerEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByPEI_R(long partnerEntryId, int role,
		int start, int end) throws SystemException {
		return findByPEI_R(partnerEntryId, role, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers where partnerEntryId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByPEI_R(long partnerEntryId, int role,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_R;
			finderArgs = new Object[] { partnerEntryId, role };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_R;
			finderArgs = new Object[] {
					partnerEntryId, role,
					
					start, end, orderByComparator
				};
		}

		List<PartnerWorker> list = (List<PartnerWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PartnerWorker partnerWorker : list) {
				if ((partnerEntryId != partnerWorker.getPartnerEntryId()) ||
						(role != partnerWorker.getRole())) {
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

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_PEI_R_PARTNERENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_R_ROLE_2);

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

				qPos.add(partnerEntryId);

				qPos.add(role);

				list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker findByPEI_R_First(long partnerEntryId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = fetchByPEI_R_First(partnerEntryId, role,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the first partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByPEI_R_First(long partnerEntryId, int role,
		OrderByComparator orderByComparator) throws SystemException {
		List<PartnerWorker> list = findByPEI_R(partnerEntryId, role, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker findByPEI_R_Last(long partnerEntryId, int role,
		OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = fetchByPEI_R_Last(partnerEntryId, role,
				orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(", role=");
		msg.append(role);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the last partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByPEI_R_Last(long partnerEntryId, int role,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPEI_R(partnerEntryId, role);

		List<PartnerWorker> list = findByPEI_R(partnerEntryId, role, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the partner workers before and after the current partner worker in the ordered set where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerWorkerId the primary key of the current partner worker
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker[] findByPEI_R_PrevAndNext(long partnerWorkerId,
		long partnerEntryId, int role, OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = findByPrimaryKey(partnerWorkerId);

		Session session = null;

		try {
			session = openSession();

			PartnerWorker[] array = new PartnerWorkerImpl[3];

			array[0] = getByPEI_R_PrevAndNext(session, partnerWorker,
					partnerEntryId, role, orderByComparator, true);

			array[1] = partnerWorker;

			array[2] = getByPEI_R_PrevAndNext(session, partnerWorker,
					partnerEntryId, role, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PartnerWorker getByPEI_R_PrevAndNext(Session session,
		PartnerWorker partnerWorker, long partnerEntryId, int role,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

		query.append(_FINDER_COLUMN_PEI_R_PARTNERENTRYID_2);

		query.append(_FINDER_COLUMN_PEI_R_ROLE_2);

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

		qPos.add(partnerEntryId);

		qPos.add(role);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(partnerWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PartnerWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the partner workers where partnerEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param notifications the notifications
	 * @return the matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByPEI_NotN(long partnerEntryId,
		int notifications) throws SystemException {
		return findByPEI_NotN(partnerEntryId, notifications, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner workers where partnerEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param notifications the notifications
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByPEI_NotN(long partnerEntryId,
		int notifications, int start, int end) throws SystemException {
		return findByPEI_NotN(partnerEntryId, notifications, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers where partnerEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param notifications the notifications
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findByPEI_NotN(long partnerEntryId,
		int notifications, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_NOTN;
		finderArgs = new Object[] {
				partnerEntryId, notifications,
				
				start, end, orderByComparator
			};

		List<PartnerWorker> list = (List<PartnerWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PartnerWorker partnerWorker : list) {
				if ((partnerEntryId != partnerWorker.getPartnerEntryId()) ||
						(notifications != partnerWorker.getNotifications())) {
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

			query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_PEI_NOTN_PARTNERENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_NOTN_NOTIFICATIONS_2);

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

				qPos.add(partnerEntryId);

				qPos.add(notifications);

				list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first partner worker in the ordered set where partnerEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker findByPEI_NotN_First(long partnerEntryId,
		int notifications, OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = fetchByPEI_NotN_First(partnerEntryId,
				notifications, orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(", notifications=");
		msg.append(notifications);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the first partner worker in the ordered set where partnerEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByPEI_NotN_First(long partnerEntryId,
		int notifications, OrderByComparator orderByComparator)
		throws SystemException {
		List<PartnerWorker> list = findByPEI_NotN(partnerEntryId,
				notifications, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last partner worker in the ordered set where partnerEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker findByPEI_NotN_Last(long partnerEntryId,
		int notifications, OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = fetchByPEI_NotN_Last(partnerEntryId,
				notifications, orderByComparator);

		if (partnerWorker != null) {
			return partnerWorker;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("partnerEntryId=");
		msg.append(partnerEntryId);

		msg.append(", notifications=");
		msg.append(notifications);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerWorkerException(msg.toString());
	}

	/**
	 * Returns the last partner worker in the ordered set where partnerEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner worker, or <code>null</code> if a matching partner worker could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker fetchByPEI_NotN_Last(long partnerEntryId,
		int notifications, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPEI_NotN(partnerEntryId, notifications);

		List<PartnerWorker> list = findByPEI_NotN(partnerEntryId,
				notifications, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the partner workers before and after the current partner worker in the ordered set where partnerEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param partnerWorkerId the primary key of the current partner worker
	 * @param partnerEntryId the partner entry ID
	 * @param notifications the notifications
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next partner worker
	 * @throws com.liferay.osb.NoSuchPartnerWorkerException if a partner worker with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker[] findByPEI_NotN_PrevAndNext(long partnerWorkerId,
		long partnerEntryId, int notifications,
		OrderByComparator orderByComparator)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = findByPrimaryKey(partnerWorkerId);

		Session session = null;

		try {
			session = openSession();

			PartnerWorker[] array = new PartnerWorkerImpl[3];

			array[0] = getByPEI_NotN_PrevAndNext(session, partnerWorker,
					partnerEntryId, notifications, orderByComparator, true);

			array[1] = partnerWorker;

			array[2] = getByPEI_NotN_PrevAndNext(session, partnerWorker,
					partnerEntryId, notifications, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PartnerWorker getByPEI_NotN_PrevAndNext(Session session,
		PartnerWorker partnerWorker, long partnerEntryId, int notifications,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PARTNERWORKER_WHERE);

		query.append(_FINDER_COLUMN_PEI_NOTN_PARTNERENTRYID_2);

		query.append(_FINDER_COLUMN_PEI_NOTN_NOTIFICATIONS_2);

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

		qPos.add(partnerEntryId);

		qPos.add(notifications);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(partnerWorker);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PartnerWorker> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the partner workers.
	 *
	 * @return the partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @return the range of partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner workers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of partner workers
	 * @param end the upper bound of the range of partner workers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public List<PartnerWorker> findAll(int start, int end,
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

		List<PartnerWorker> list = (List<PartnerWorker>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PARTNERWORKER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PARTNERWORKER;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<PartnerWorker>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the partner workers where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (PartnerWorker partnerWorker : findByUserId(userId)) {
			remove(partnerWorker);
		}
	}

	/**
	 * Removes all the partner workers where partnerEntryId = &#63; from the database.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPartnerEntryId(long partnerEntryId)
		throws SystemException {
		for (PartnerWorker partnerWorker : findByPartnerEntryId(partnerEntryId)) {
			remove(partnerWorker);
		}
	}

	/**
	 * Removes the partner worker where userId = &#63; and partnerEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param partnerEntryId the partner entry ID
	 * @return the partner worker that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public PartnerWorker removeByU_PEI(long userId, long partnerEntryId)
		throws NoSuchPartnerWorkerException, SystemException {
		PartnerWorker partnerWorker = findByU_PEI(userId, partnerEntryId);

		return remove(partnerWorker);
	}

	/**
	 * Removes all the partner workers where userId = &#63; and role = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_R(long userId, int role) throws SystemException {
		for (PartnerWorker partnerWorker : findByU_R(userId, role)) {
			remove(partnerWorker);
		}
	}

	/**
	 * Removes all the partner workers where partnerEntryId = &#63; and role = &#63; from the database.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPEI_R(long partnerEntryId, int role)
		throws SystemException {
		for (PartnerWorker partnerWorker : findByPEI_R(partnerEntryId, role)) {
			remove(partnerWorker);
		}
	}

	/**
	 * Removes all the partner workers where partnerEntryId = &#63; and notifications &ne; &#63; from the database.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param notifications the notifications
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPEI_NotN(long partnerEntryId, int notifications)
		throws SystemException {
		for (PartnerWorker partnerWorker : findByPEI_NotN(partnerEntryId,
				notifications)) {
			remove(partnerWorker);
		}
	}

	/**
	 * Removes all the partner workers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (PartnerWorker partnerWorker : findAll()) {
			remove(partnerWorker);
		}
	}

	/**
	 * Returns the number of partner workers where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

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
	 * Returns the number of partner workers where partnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @return the number of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPartnerEntryId(long partnerEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { partnerEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(partnerEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PARTNERENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of partner workers where userId = &#63; and partnerEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param partnerEntryId the partner entry ID
	 * @return the number of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_PEI(long userId, long partnerEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, partnerEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_PEI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_PEI_USERID_2);

			query.append(_FINDER_COLUMN_U_PEI_PARTNERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(partnerEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_PEI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of partner workers where userId = &#63; and role = &#63;.
	 *
	 * @param userId the user ID
	 * @param role the role
	 * @return the number of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_R(long userId, int role) throws SystemException {
		Object[] finderArgs = new Object[] { userId, role };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_U_R_USERID_2);

			query.append(_FINDER_COLUMN_U_R_ROLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(role);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_R, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of partner workers where userId = any &#63; and role = any &#63;.
	 *
	 * @param userIds the user IDs
	 * @param roles the roles
	 * @return the number of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_R(long[] userIds, int[] roles)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(userIds), StringUtil.merge(roles)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

			boolean conjunctionable = false;

			if ((userIds == null) || (userIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < userIds.length; i++) {
					query.append(_FINDER_COLUMN_U_R_USERID_5);

					if ((i + 1) < userIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if ((roles == null) || (roles.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < roles.length; i++) {
					query.append(_FINDER_COLUMN_U_R_ROLE_5);

					if ((i + 1) < roles.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (userIds != null) {
					qPos.add(userIds);
				}

				if (roles != null) {
					qPos.add(roles);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_R,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of partner workers where partnerEntryId = &#63; and role = &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param role the role
	 * @return the number of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPEI_R(long partnerEntryId, int role)
		throws SystemException {
		Object[] finderArgs = new Object[] { partnerEntryId, role };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PEI_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_PEI_R_PARTNERENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_R_ROLE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(partnerEntryId);

				qPos.add(role);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PEI_R,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of partner workers where partnerEntryId = &#63; and notifications &ne; &#63;.
	 *
	 * @param partnerEntryId the partner entry ID
	 * @param notifications the notifications
	 * @return the number of matching partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPEI_NotN(long partnerEntryId, int notifications)
		throws SystemException {
		Object[] finderArgs = new Object[] { partnerEntryId, notifications };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_NOTN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PARTNERWORKER_WHERE);

			query.append(_FINDER_COLUMN_PEI_NOTN_PARTNERENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_NOTN_NOTIFICATIONS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(partnerEntryId);

				qPos.add(notifications);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_NOTN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of partner workers.
	 *
	 * @return the number of partner workers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PARTNERWORKER);

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
	 * Initializes the partner worker persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.osb.model.PartnerWorker")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PartnerWorker>> listenersList = new ArrayList<ModelListener<PartnerWorker>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<PartnerWorker>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(PartnerWorkerImpl.class.getName());
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
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_PARTNERWORKER = "SELECT partnerWorker FROM PartnerWorker partnerWorker";
	private static final String _SQL_SELECT_PARTNERWORKER_WHERE = "SELECT partnerWorker FROM PartnerWorker partnerWorker WHERE ";
	private static final String _SQL_COUNT_PARTNERWORKER = "SELECT COUNT(partnerWorker) FROM PartnerWorker partnerWorker";
	private static final String _SQL_COUNT_PARTNERWORKER_WHERE = "SELECT COUNT(partnerWorker) FROM PartnerWorker partnerWorker WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "partnerWorker.userId = ?";
	private static final String _FINDER_COLUMN_PARTNERENTRYID_PARTNERENTRYID_2 = "partnerWorker.partnerEntryId = ?";
	private static final String _FINDER_COLUMN_U_PEI_USERID_2 = "partnerWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_PEI_PARTNERENTRYID_2 = "partnerWorker.partnerEntryId = ?";
	private static final String _FINDER_COLUMN_U_R_USERID_2 = "partnerWorker.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_R_USERID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_U_R_USERID_2) + ")";
	private static final String _FINDER_COLUMN_U_R_ROLE_2 = "partnerWorker.role = ?";
	private static final String _FINDER_COLUMN_U_R_ROLE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_U_R_ROLE_2) + ")";
	private static final String _FINDER_COLUMN_PEI_R_PARTNERENTRYID_2 = "partnerWorker.partnerEntryId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_R_ROLE_2 = "partnerWorker.role = ?";
	private static final String _FINDER_COLUMN_PEI_NOTN_PARTNERENTRYID_2 = "partnerWorker.partnerEntryId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_NOTN_NOTIFICATIONS_2 = "partnerWorker.notifications != ?";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "partnerWorker.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PartnerWorker exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PartnerWorker exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PartnerWorkerPersistenceImpl.class);
	private static PartnerWorker _nullPartnerWorker = new PartnerWorkerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PartnerWorker> toCacheModel() {
				return _nullPartnerWorkerCacheModel;
			}
		};

	private static CacheModel<PartnerWorker> _nullPartnerWorkerCacheModel = new CacheModel<PartnerWorker>() {
			public PartnerWorker toEntityModel() {
				return _nullPartnerWorker;
			}
		};
}